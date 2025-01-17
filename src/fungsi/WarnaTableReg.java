/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Owner
 */
public class WarnaTableReg extends DefaultTableCellRenderer {

    /**
     *
     */
    public int kolom = 23;

    /**
     *
     */
    public int statusrawat = 19;

    public int sep = 25;

    public int bayar = 12;

    /**
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            // component.setBackground(new Color(93, 156, 236));
            component.setForeground(Color.RED);
        } else {
            component.setForeground(new Color(50, 50, 50));
            if (row % 2 == 1) {
                component.setBackground(new Color(255, 246, 244));
            } else {
                component.setBackground(new Color(255, 255, 255));
            }
            if (table.getValueAt(row, kolom).toString().equals("Sudah Bayar")) {
                component.setBackground(new Color(153, 255, 153));
            }
            if (table.getValueAt(row, statusrawat).toString().equals("Batal")) {
                component.setBackground(new Color(255, 102, 102));
            }
            if (table.getValueAt(row, bayar).toString().equals("BPJS Kesehatan")
                    && table.getValueAt(row, sep).toString().equals("-")) {
                component.setBackground(new Color(255, 204, 102));
            }
        }
        return component;
    }

    private static final Logger LOG = Logger.getLogger(WarnaTableReg.class.getName());

}
