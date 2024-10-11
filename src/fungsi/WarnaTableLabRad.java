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
public class WarnaTableLabRad extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component component =
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    if (row % 2 == 1) {
      component.setBackground(new Color(255, 246, 244));
      component.setForeground(new Color(50, 50, 50));
    } else {
      component.setBackground(new Color(255, 255, 255));
      component.setForeground(new Color(50, 50, 50));
    }
    if (!table.getValueAt(row, 8).toString().isEmpty()
        || !table.getValueAt(row, 9).toString().isEmpty()) {
      component.setBackground(new Color(153, 255, 153));
      component.setForeground(new Color(50, 50, 50));
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTableLabRad.class.getName());
}
