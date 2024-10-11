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
public class WarnaTablePerkiraan extends DefaultTableCellRenderer {

  /** */
  public int kolom = 24;

  public int bayar = 25;

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
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component component =
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    if (row % 2 == 1) {
      component.setBackground(new Color(255, 246, 244));
    } else {
      component.setBackground(new Color(255, 255, 255));
    }
    if (table.getValueAt(row, kolom).toString().equals("Tidak Aman")) {
      component.setBackground(new Color(255, 153, 153));
    }
    if (!table.getValueAt(row, bayar).toString().equals("BPJS Kesehatan")
        && !table.getValueAt(row, bayar).toString().isEmpty()) {
      component.setBackground(new Color(255, 255, 153));
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTablePerkiraan.class.getName());
}
