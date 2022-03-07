import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileIO {
    public static void main(String[] args) {
        OutputStream out = null;
!!        try {
            out = new FileOutputStream("");
        } catch (IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //java7
        //implement closeable class can be put in try()catch without finally
        try (OutputStream newOut = new FileOutputStream("");
             OutputStream newOut2 = new FileOutputStream("");) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
