# Concept

code is in src/jdk.compiler/share/classes/com/sun/tools/javac/Main.java

[step](https://openjdk.java.net/groups/compiler/doc/compilation-overview/index.html):

+ parse and enter: Source files are processed for Unicode escapes and converted into a stream of tokens by the `Scanner`.
+ annotation processing
+ analyses and generate

```java
// jdk.compiler/share/classes/com/sun/tools/javac/main/Main.java
    public Result compile(String[] args) {
        Context context = new Context();
        JavacFileManager.preRegister(context); // can't create it until Log has been set up
        Result result = compile(args, context);
        try {
            // A fresh context was created above, so the file manager can be safely closed:
            if (fileManager != null)
                fileManager.close();
        } catch (IOException ex) {
            bugMessage(ex);
        }
        return result;
    }
//jdk.compiler/share/classes/com/sun/tools/javac/main/JavaCompiler.java
public void compile(Collection<JavaFileObject> sourceFileObjects,
                        Collection<String> classnames,
                        Iterable<? extends Processor> processors,
                        Collection<String> addModules)
public void initProcessAnnotations(Iterable<? extends Processor> processors,
                                       Collection<? extends JavaFileObject> initialFiles,
                                       Collection<String> initialClassNames)
```

