# file io and DB connection should close with try...catch...finally

class implements java.lang.AutoClosed or java.io.Closable  can use try-with-resource

# Big_endian and little_endian

```ByteOrder.nativeOrder(); ```: Retrieves the native byte order of the underlying platform.

``` ByteBuffer.order()```: The order of a newly-created byte buffer is always BIG_ENDIAN.

# FileOutputStream

public FileOutputStream(String name, boolean append)

# NIO Selector

# FileReader

# File

getPath(): get the constructor path parameter, eg. .\test.txt
getCanonicalPath: eg. E:\workspace\java\test.txt
getAbsolutePath: eg. E:\workspace\..\java\test.txt

# newByteChannel

# ZipInputStream

ZipEntry.getSize()

# IO Buffer statue

private static final int INVALIDATED = -2;

private static final int UNMARKED = -1;

private boolean markedSkipLF = false;



# three component

Buffer, Channel, Selector

