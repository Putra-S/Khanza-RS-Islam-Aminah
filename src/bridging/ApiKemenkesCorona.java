package bridging;

import fungsi.koneksiDB;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ApiKemenkesCorona {

    private static final Logger LOG = Logger.getLogger(ApiKemenkesCorona.class.getName());

    private String Key, pass;

    /**
     *
     */
    public ApiKemenkesCorona() {
        try {
            pass = koneksiDB.PASSCORONA();
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    public String getHmac() {
        Key = pass;
        return Key;
    }

    /**
     * @return
     */
    public long GetUTCdatetimeAsString() {
        long millis = System.currentTimeMillis();
        return millis / 1000;
    }

    /**
     * @return @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory
                = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
}
