/*
Kontribusi dari Mas Dhiaz Shahab Dari RS Islam Bontang
*/
package bridging;

import java.util.logging.Logger;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ApiBPJSAesKeySpec {

  private SecretKeySpec key;

  private IvParameterSpec iv;

  public SecretKeySpec getKey() {
    return key;
  }

  /**
   * @param key
   */
  public void setKey(SecretKeySpec key) {
    this.key = key;
  }

  /**
   * @return
   */
  public IvParameterSpec getIv() {
    return iv;
  }

  /**
   * @param iv
   */
  public void setIv(IvParameterSpec iv) {
    this.iv = iv;
  }

  private static final Logger LOG = Logger.getLogger(ApiBPJSAesKeySpec.class.getName());
}
