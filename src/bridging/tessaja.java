/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import java.util.logging.Logger;

/**
 * @author khanzasoft
 */
public class tessaja {

  private static final ApiBRI apibri = new ApiBRI();

  /**
   * @param args
   */
  public static void main(String[] args) {
    apibri.statusVA("2204230001");
  }

  private static final Logger LOG = Logger.getLogger(tessaja.class.getName());
}
