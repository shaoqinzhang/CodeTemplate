# [Security-enhance linux in Android](https://source.android.com/security/selinux)  

## Concept

SElinux enforce mandatory access control (MAC) over all processes.  

There are two global models:
+ Permission mode, in which permission denials are logged but note enforced.
+ Enforcing mode, in which permissions denials are both logged and enforced.

Android security mode is based in part on the concept of application sandboxes. sandboxes boundaries is defined by SELinux and a unique Linux UID for each application at time of installation.

General denials of block_device, socket_device, default_service and so on indicates that device need a special domain.

Android 7.0 broke up the monolithic **mediaserver** stack into smaller processes to reduce the scope of their permissions


Android 8.0 work with Treble, allowing device manufacturers and SOC vendors to update their parts of the  policy into vendor.img, boot.img, etc. **Vendor  image(vendor.img/odm.img)** cannot have a newer version than the platform(system.img)

Discretionary access control(DAC) system of Linux, such as owner, group and other, is coarse-grained.

Android relies on the Type Enforcement(TE) component of SELinux for its policy. 

Attributes are useful to refer to multiple types at the same time.

Objects are mapped to classes (e.g., a file, a directory, a symbolic link, a socket) and the different kinds of access for each class are represented by permissions. For instance, the permission open exists for the class file. 

A policy rule comes in the form: ```allow source target:class permissions;``` where:

+ Source - The type (or attribute) of the subject of the rule. Who is requesting the access?
+ Target - The type (or attribute) of the object. To what is the access requested?
+ Class - The kind of object (e.g. file, socket) being accessed.
+ Permissions - The operation (or set of operations) (e.g. read, write) being performed.

```Security Context and Categories```: user:role:type:sensitivity[:categories]. ```categories``` are used in Android S to isolate app data.

In Android, keep some information in mind
+ The majority of the policies in AOSP are defined using the Kernel Policy Language. There are some exceptions for using Common Intermediate Language (CIL).
+ SELinux users are not used. The only user defined is u. When necessary, physical users are represented using the categories field of a security context.
+ SELinux roles and Role-Based Access Control (RBAC) are not used. Two default roles are defined and used: r for subjects and object_r for objects.
+ SELinux sensitivities are not used. The default s0 sensitivity is always set.
+ SELinux booleans are not used. Once the policy is built for a device, it does not depend on the state of the device. This simplifies the auditing and debugging of policies.

Add dd or edit your own device-specific policy files in the ```/device/manufacturer/device-name/sepolicy``` directory. In Android 8.0 and higher, the changes you make to these files should only affect policy in your vendor directory. 

Context files:

+ ```file_contexts``` assigns labels to files and is used by various userspace components. As you create new policies, create or update this file to assign new labels to files. To apply new file_contexts, **rebuild the filesystem image or run restorecon** on the file to be relabeled. On upgrades, changes to file_contexts are automatically applied to the system and userdata partitions as part of the upgrade. Changes can also be automatically applied on upgrade to other partitions by adding **restorecon_recursive calls to your init.board.rc file after the partition has been mounted read-write**.
+ ```genfs_contexts``` assigns labels to filesystems, such as **proc or vfat** that do not support extended attributes. This configuration is loaded as part of the kernel policy but changes may not take effect for **in-core inodes**, requiring a reboot or unmounting and re-mounting the filesystem to fully apply the change. Specific labels may also be assigned to specific mounts, such as vfat using the **context=mount option**.
+ ```property_contexts``` assigns labels to Android system properties to control what processes can set them. This configuration is read by the init process during startup.
+ ```service_contexts``` assigns labels to Android binder services to control what processes can add (register) and find (lookup) a binder reference for the service. This configuration is read by the servicemanager process during startup.
+ ```seapp_contexts``` assigns labels to app processes and /data/data directories. This configuration is read by the zygote process on each app launch and by installd during startup.
+ ```mac_permissions.xml``` assigns a seinfo tag to apps based on their signature and optionally their package name. The seinfo tag can then be used as a key in the seapp_contexts file to assign a specific label to all apps with that seinfo tag. This configuration is read by system_server during startup.
+ ```keystore2_key_contexts``` assigns labels to Keystore 2.0 namespaces. These namespace are enforced by the keystore2 daemon. Keystore has always provided UID/AID based namespaces. Keystore 2.0 additionally enforces sepolicy defined namespaces. A detailed description of the format and conventions of this file can be found [here](https://source.android.com/security/keystore#access-control).

After editing or adding policy and context files, update your /device/manufacturer/device-name/BoardConfig.mk makefile to reference the sepolicy subdirectory and each new policy file. For more information about the BOARD_SEPOLICY variables, see system/sepolicy/README

SELinux is based upon the M4 computer language and therefore supports a variety of macros to save time.

## Implemention

To get started with SELinux:

1. Enable SELinux in the kernel: ```CONFIG_SECURITY_SELINUX=y```
2. Change the kernel_cmdline or bootconfig parameter so that:

```BOARD_KERNEL_CMDLINE := androidboot.selinux=permissive``` or ```BOARD_BOOTCONFIG := androidboot.selinux=permissive```

3. Boot up the system in permissive and see what denials are encountered on boot:
On Ubuntu 14.04 or newer: ```adb shell su -c dmesg | grep denied | audit2allow -p out/target/product/BOARD/root/sepolicy```. On Ubuntu 12.04: ```adb pull /sys/fs/selinux/policy```,``` adb logcat -b all | audit2allow -p policy```.
4. Services spawned from ```init``` should have their own security domains. ```adb shell su -c ps -Z | grep init```,```adb shell su -c dmesg | grep 'avc: '```.
5. Set up ```BOARD_CONFIG.mk``` to use ```BOARD_SEPOLICY_*``` variables. See the README in system/sepolicy for details on setting this up.
6. Examine the ```init.device.rc``` and ```fstab.device``` file and make sure every use of mount corresponds to a properly labeled filesystem or that a ```context= mount``` option is specified.

 In Android 8.0 and higher, adding a policy to ```BOARD_SEPOLICY_DIRS``` places the policy only in the **vendor image**.

# Path

+ system/sepolicy/public: both in vendor and system image
+ system/sepolicy/private: in system image
+ device/manufacture/device-name/sepolicy: vendor image
+ ```SYSTEM_EXT_PUBLIC_SEPOLICY_DIRS```: installed to system_ext partition. exported for used in vendor-specific policy.
+ ```SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS```: installed to system_ext partition and vendor image should have no knowledge. policy is for the functioning of the system_ext image.
+ ```PRODUCT_PUBLIC_SEPOLICY_DIRS```: installed to product partition and exported to vendor-specific policy.
+ ```PRODUCT_PRIVATE_SEPOLICY_DIRS```: similar to ```SYSTEM_EXT_PRIVATE_SEPOLICY_DIRS``` but intalled to product partition

when **GSI** is used, OEM's system_ext and product partitions will not be mounted. The rules in the vendor sepolicy that uses OEM's system_ext and product public policy become NOP because the OEM-specific type definitions are missing.
