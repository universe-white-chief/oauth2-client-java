package sddtc.oauth2.client;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Created by hchang on 2017/4/21.
 */
public class Base64Test {

    @Test
    public void base64() {
        String auth = "sddtcClient" + ":" + "sddtcClientSecret";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        System.out.println(authHeader);
    }
}
