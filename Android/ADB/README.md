adb devices  
adb start-server  
adb nodaemon server  

# adb shell
+ ```getprop```
+ ```setprop```
+ ```watchprops```: If property change, it will print some information.

---
+ ```dumpsys activity```: Print activity information
+ ```dumpsys activity top``````
+ ``````am stack```
---
+ ```screencap -p filepath```:Caption the screen. You can use ```screencap -help``` for more information.
+ ```screenrecord```: Record the screen. ```screenrecord -help```for more information.

----
adb bugreport

----
+ ```showmap```
+ ```dumpsys meminfo```
+ dumpsys meminfo package_name|pid

---

```adb shell setting put global hidden_api_policy 1``` : app can access to  android api in framework

---



am start -n [package_name/.acticity_name]

am start -D -n [package_name/.acticity_name]

am startservice -n package_name.service_name

am stopservice -n package_name.service_name

am broadcast [--user <User_ID> | all | current]  intent

am broadcast -a intent

am stack list

start|stop

dumpsys package name

pm install [--user USER_ID] apk

pm uninstall  [--user USER_ID] apk

pm list [--user USER_ID] package_name

pm path [--user USER_ID] package_name

pm disable|enable|clear [--user USER_ID] package_name



procrank

dumpsys dbinfo package_name

splite3 db_name.db

dumpsys network_management

dumpsys ethernet|wifi

adb install -s

adb install -r

adb uninstall -k

run_as package_name

netcfg

netstate

appp_process [code_dir] [class_name]

aapt dump xmltree apk_name.apk xml_name.xml

aapt d badging apk_name.apk 

cat /proc/pid/maps

cat /proc/pid/status

cat proc/wakeup_sources

adb devices

adb start-server

adb kill-server

logcat -C

adb reboot

adb reboot bootloader

adb reboot recovery

cat /sys/calss/net/wlan0/address

cat /proc/cpuinfo

top -m

top -n

ps -x pid

service list

cat /proc/meminfo

cat  /proc/iomem

adb -s device_name shell

logcat ActivityManage:I MyAPP:D.*S -d -f path

dumpsys window

date -s time

am kill

am force-stop

pm list permissions -d -g

dumpsys activity services









