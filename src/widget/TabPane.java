/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widget;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;

/**
 * @author khanzamedia
 */
public class TabPane extends JTabbedPane {

  public TabPane() {
    super();
    setBackground(new Color(255, 255, 255));
    setForeground(new Color(50, 50, 50));
    // this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(245,140,245)));
  }

  private static final Logger LOG = Logger.getLogger(TabPane.class.getName());
}
