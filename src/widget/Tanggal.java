/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widget;

import java.awt.Color;
import java.util.Locale;
import java.util.logging.Logger;
import uz.ncipro.calendar.JDateTimePicker;

/**
 * @author khanzasoft
 */
public class Tanggal extends JDateTimePicker {

    /**
     *
     */
    public Tanggal() {
        super();
        // setBackground(new Color(245,160,245));
        // setForeground(new Color(90,90,90));
        setForeground(new Color(50, 50, 50));
        setBackground(new Color(255, 255, 255));
        setFont(new java.awt.Font("Tahoma", 0, 11));
        // setBorder(javax.swing.BorderFactory.createLineBorder(new Color(212,212,152)));
        setSize(WIDTH, 23);
        setLocale(Locale.forLanguageTag("id-ID"));

    }

    private static final Logger LOG = Logger.getLogger(Tanggal.class.getName());

}
