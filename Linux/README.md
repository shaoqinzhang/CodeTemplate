# Memory

USS: unique set size

PSS: proportional set size

VSS: virtual set size

RSS: Resident set size

VSS >= RSS >=PSS >= USS

# proc/PID/smaps

# RISC CISC

Reduced Instruction Set Computer

Complex Instruction Set Computer

# iptables

table -> filter nat mangle raw

iptables -t table op chain rule -j target

op:
+ -A append
+ -D del
+ -R replace
+ -I insert
+ -L list

chain:
+ output
+ input
+ postrouting
+ forward

rule:
+ -d -s -dport -sport
  
# Regular

[^A-Za-z-0-9_] = \W

# ag and grep

