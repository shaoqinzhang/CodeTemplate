import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class NIOTest {
    public static void main(String... args) {
//        In win, it is liitle_endian
        System.out.println(ByteOrder.nativeOrder());
//        New created buffer  is big_endian
        ByteBuffer buffer = ByteBuffer.wrap(new byte[3]);
        System.out.println( buffer.order());
    }
}
