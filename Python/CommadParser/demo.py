from optparse import OptionGroup, OptionParser
import sys
# %prog is os.path.basename(sys.argv[0])
myusage = "use python %prog [ooptions]"

# --version print version
parser = OptionParser(usage = myusage, version = "%prog 1.0")
parser.add_option('-x', '--xxx',help = "place help information")

# no opption, set true or false to --a
parser.add_option('--a', action="store_false", help="use xxx --a. no options")
parser.add_option('--b', action="store_true", dest = "verbose", help="use xxx --b. no options")

# call function directly
def xxx(a,b):
    pass
x=1
parser.add_option('-l', action = "callback", callback = xxx, help = "call the function")
parser.add_option("-u", action="callback", callback = xxx, callback_args=(x,x), help="call the function with parameter")

#store option. use options.filename to get value
parser.add_option("-c", action ="store", type="string", dest="filename", default = "c.txt")
parser.set_defaults(filename="c.txt", verbose="true")

# metavar to remind users and information will convert to uppercase
parser.add_option("-f", "--filename",  
                  metavar="FILE", help="write output to FILE"),
#option group
group = OptionGroup(parser,"HEAD", "line1 ")
group.add_option("-g", action ="store", type="string", dest="filename", default = "c.txt")
parser.add_option_group(group)
(options, arges) = parser.parse_args()
(options, arges) = parser.parse_args(sys.argv)
if options.xxx is None:
    pass

# use -h or --help will call print_help
parser.print_help()

#print error
parser.error("say somrthing")
#and you can inherit OptionParser and override exit() and erro()

