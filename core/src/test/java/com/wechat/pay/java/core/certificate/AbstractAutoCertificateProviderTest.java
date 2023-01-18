package com.wechat.pay.java.core.certificate;

import static com.wechat.pay.java.core.model.TestConfig.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.wechat.pay.java.core.auth.Validator;
import com.wechat.pay.java.core.auth.WechatPay2Credential;
import com.wechat.pay.java.core.certificate.RSAAutoCertificateProvider.Builder;
import com.wechat.pay.java.core.cipher.RSASigner;
import com.wechat.pay.java.core.http.HttpClient;
import com.wechat.pay.java.core.http.HttpHeaders;
import com.wechat.pay.java.core.http.okhttp.OkHttpClientAdapter;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AbstractAutoCertificateProviderTest implements CertificateProviderTest {

  static AbstractAutoCertificateProvider spyAutoProvider;
  static HttpClient httpClient;

  @BeforeAll
  static void initRsaCertificateManager() {
    Validator validator =
        new Validator() {
          @Override
          public <T> boolean validate(HttpHeaders responseHeaders, String body) {
            return true;
          }
        };
    OkHttpClient okHttpClient =
        new OkHttpClient.Builder()
            .addInterceptor(
                chain ->
                    new Response.Builder()
                        .request(chain.request())
                        .code(HTTP_OK)
                        .header("key", "val")
                        .message("ok")
                        .protocol(Protocol.HTTP_1_1)
                        .body(
                            ResponseBody.create(
                                DOWNLOAD_CERTIFICATE_RESPONSE,
                                MediaType.parse(
                                    com.wechat.pay.java.core.http.MediaType.APPLICATION_JSON
                                        .getValue())))
                        .build())
            .build();
    httpClient =
        new OkHttpClientAdapter(
            new WechatPay2Credential(
                "7123456", new RSASigner(MERCHANT_CERTIFICATE_SERIAL_NUMBER, MERCHANT_PRIVATE_KEY)),
            validator,
            okHttpClient);
    spyAutoProvider =
        new Builder()
            .privateKey(MERCHANT_PRIVATE_KEY)
            .merchantId("5123456")
            .httpClient(httpClient)
            .apiV3Key(API_V3_KEY.getBytes(StandardCharsets.UTF_8))
            .build();
  }

  @Override
  public CertificateProvider createCertificateProvider() {
    return spyAutoProvider;
  }

  @Override
  @Test
  public void testGetCertificates() {
    assertNotNull(createCertificateProvider().getCertificate(DOWNLOAD_CERTIFICATE_SERIAL_NUMBER));
  }
}
