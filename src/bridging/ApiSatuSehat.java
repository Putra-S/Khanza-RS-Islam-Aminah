package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kanit SIRS
 */
public class ApiSatuSehat {

  private String key, clientid, urlauth, token;

  private long millis;

  private SSLContext sslContext;

  private SSLSocketFactory sslFactory;

  private Scheme scheme;

  private HttpComponentsClientHttpRequestFactory factory;

  private ApiBPJSAesKeySpec mykey;

  private HttpHeaders header;

  private JsonNode root;

  private HttpEntity requestEntity;

  private ObjectMapper mapper = new ObjectMapper();

  public ApiSatuSehat() {
    try {
      key = koneksiDB.SECRETKEYSATUSEHAT();
      clientid = koneksiDB.CLIENTIDSATUSEHAT();
      urlauth = koneksiDB.URLAUTHSATUSEHAT();
    } catch (Exception ex) {
      System.out.println("Notifikasi : " + ex);
    }
  }

  /**
   * @return
   */
  public String TokenSatuSehat() {
    try {
      header = new HttpHeaders();
      header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      requestEntity = new HttpEntity("client_id=" + clientid + "&client_secret=" + key, header);
      root =
          mapper.readTree(
              getRest()
                  .exchange(
                      urlauth + "/accesstoken?grant_type=client_credentials",
                      HttpMethod.POST,
                      requestEntity,
                      String.class)
                  .getBody());
      token = root.path("access_token").asText();
    } catch (IOException
        | KeyManagementException
        | NoSuchAlgorithmException
        | RestClientException ex) {
      System.out.println("Notifikasi : " + ex);
    }
    return token;
  }

  /**
   * @return
   */
  public long GetUTCdatetimeAsString() {
    millis = System.currentTimeMillis();
    return millis / 1000;
  }

  /**
   * @param data
   * @param utc
   * @return
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws InvalidAlgorithmParameterException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public String Decrypt(String data, String utc)
      throws NoSuchPaddingException,
          NoSuchAlgorithmException,
          InvalidAlgorithmParameterException,
          InvalidKeyException,
          BadPaddingException,
          IllegalBlockSizeException {
    System.out.println(data);
    mykey = ApiBPJSEnc.generateKey(clientid + key + utc);
    data = ApiBPJSEnc.decrypt(data, mykey.getKey(), mykey.getIv());
    data = ApiBPJSLZString.decompressFromEncodedURIComponent(data);
    System.out.println(data);
    return data;
  }

  /**
   * @return @throws NoSuchAlgorithmException
   * @throws KeyManagementException
   */
  public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
    sslContext = SSLContext.getInstance("SSL");
    TrustManager[] trustManagers = {
      new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {}

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {}
      }
    };
    sslContext.init(null, trustManagers, new SecureRandom());
    sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    scheme = new Scheme("https", 443, sslFactory);
    factory = new HttpComponentsClientHttpRequestFactory();
    factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
    return new RestTemplate(factory);
  }

  private static final Logger LOG = Logger.getLogger(ApiSatuSehat.class.getName());
}
