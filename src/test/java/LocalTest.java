import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class LocalTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        String a = "啊";
        System.out.println(a.length());
        System.out.println(a.getBytes("UTF-8").length);
    }


}
