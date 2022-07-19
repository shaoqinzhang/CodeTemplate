```make update-api``` to update customized api

# Activity lifecycle

# Service lifecycle 

# 5 layout 

# Intent

setComponent setClass

# Bundle

Key-value, hashmap

# apk sign

jarsinger -verbose -certs -verify xx.apk

# Decompile

dex2jar class.dex  ==>jar file

# Handler

Handler in child thread, buils a Looper object(Looper.prepare())

# UI

handler, Activity.runOnUIThread

# AsyncTask

AsyncTask<params, progress, Result>

# Activity 

startActivityForResult <== onActivityResult,setResult

# Affinity

# launch mode

stander, singleTop, singleTask, singleInstance

# NLP

NetworkLocation Provider

# version

minsdkVersion -> Minimum System Api

the version control of Gradle will overlay the version of manifest.xml. for example:
+ use sdk
+ set API level
+ buildTypes

defaultconfig:
+ versionCode: the max num is 2100000000. it control low version app can't be install
+ versionName
  

productFlavors:
+ demo
+ full

above information can be get by using PackageManager....getPackageInfo

# Lock
Mutex, AutoLock, Condition

# Dead Lock

data/tombstone

# watchdog detect the dead lock

softlockup: timer NMI(no maskable interrupt) interrupt

hardlockup

# ANR

Application not Responding

input dispatching time out: 8s

broadcast time out: 10 and 60s

service time out 20s

# Futex

 Fast UserSpace NuTexes

# Android Runtime JIT

# Dalvik Hook and ART Hook

# DirectBoot

Context.MODE_PRIVATE

# GPS GPRS AGPS MS_based MS_Assisted

# ART command

# Build Fingerprint

# Tombstone

# panic.py

# install session

# jobschedule

# provider 

# Android 12 Privacy dashboard

# jadx

# jeb

# cgrep|sgrep|croot|resgrep|jgrep



