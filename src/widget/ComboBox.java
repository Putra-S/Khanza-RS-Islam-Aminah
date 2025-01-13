/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widget;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.UIManager;

/**
 * @author dosen3
 */
public class ComboBox extends JComboBox {

    /**
     *
     */
    public ComboBox() {
        setFont(new java.awt.Font("Tahoma", 0, 11));
        setBackground(new Color(255, 255, 255));
        setForeground(new Color(50, 50, 50));
        setSize(WIDTH, 23);
        UIManager.put("ComboBox.selectionBackground", new Color(93, 156, 236));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

    }

    private static final Logger LOG = Logger.getLogger(ComboBox.class.getName());

}
