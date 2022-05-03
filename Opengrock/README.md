https://oracle.github.io/opengrok/

https://github.com/oracle/opengrok/wiki/How-to-setup-OpenGrok

# install tomcat

+ install tomcat. unzip tomcat zip in /user/local.
+ run bin/startup.sh.
+ see if localhost:8080 run tomcat.

# Install opengrok

```shell
mkdir /opengrok/{src,data,dist,etc,log}
tar -C /opengrok/dist --strip-components=1 -xzf opengrok-X.Y.Z.tar.gz
cp /opengrok/dist/doc/logging.properties /opengrok/etc
cd /opengrok/src

# use one of the training modules at GitHub as an example small app.      
git clone https://github.com/githubtraining/hellogitworld.git

# use OpenGrok as an example large app
git clone https://github.com/OpenGrok/OpenGrok

# This is assuming you have extracted the OpenGrok release tarball already and you are using bash:
$ cd tools
$ python3 -m venv env
$ . ./env/bin/activate
$ pip install opengrok-tools.tar.gz

# in Unbutu
apt-get install universal-ctags
# find the path ctags
whereis ctags-universal

# pay attention to replace the ctags path
opengrok-indexer \
    -J=-Djava.util.logging.config.file=/opengrok/etc/logging.properties \
    -a /opengrok/dist/lib/opengrok.jar -- \
    -c /usr/bin/ctags-universal  \
    -s /opengrok/src -d /opengrok/data -H -P -S -G \
    -W /opengrok/etc/configuration.xml -U http://localhost:8080/source
```



