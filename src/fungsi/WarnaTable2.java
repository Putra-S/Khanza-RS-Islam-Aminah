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
public class WarnaTable2 extends DefaultTableCellRenderer {

  /** */
  public int kolom;

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
      component.setBackground(new Color(255, 244, 244));
    } else {
      component.setBackground(new Color(255, 255, 255));
    }
    if (column == kolom) {
      component.setBackground(new Color(215, 215, 255));
      component.setForeground(new Color(255, 255, 255));
      try {
        if (!table.getValueAt(row, kolom).toString().isEmpty()) {
          component.setBackground(new Color(255, 255, 255));
          component.setForeground(new Color(55, 55, 175));
        }
      } catch (Exception e) {
      }
    } else {
      component.setForeground(new Color(70, 70, 70));
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTable2.class.getName());
}
