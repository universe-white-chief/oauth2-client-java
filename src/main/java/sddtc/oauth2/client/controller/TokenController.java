package sddtc.oauth2.client.controller;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sddtc on 2017/3/31.
 */
@RestController
public class TokenController {

    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;

    @RequestMapping(value = "/gettokeninfo", method = RequestMethod.GET)
    @ResponseBody
    public String getToken() {
        String uri = "http://localhost:9000/services/oauth/token";
        MultiValueMap<String, String> postParams = new LinkedMultiValueMap<>();
        postParams.add("username", "sddtc");
        postParams.add("password", "sddtc");
        postParams.add("grant_type", "password");

        HttpHeaders headers = new HttpHeaders();
        String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(postParams, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, httpEntity, String.class);

        return result;
    }

//    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
//    @ResponseBody
//    public String getTokenByRefreshToken(String token) {
//
//    }
}
