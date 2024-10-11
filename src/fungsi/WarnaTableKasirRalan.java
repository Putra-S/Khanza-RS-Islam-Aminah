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
public class WarnaTableKasirRalan extends DefaultTableCellRenderer {

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
    if (table.getValueAt(row, 10).toString().equals("Sudah")) {
      component.setBackground(new Color(153, 255, 255));
      component.setForeground(new Color(50, 50, 50));
    } else if (table.getValueAt(row, 10).toString().equals("Batal")) {
      component.setBackground(new Color(255, 153, 153));
      component.setForeground(new Color(50, 50, 50));
    } else if (table.getValueAt(row, 10).toString().equals("Dirujuk")
        || table.getValueAt(row, 10).toString().equals("Meninggal")
        || table.getValueAt(row, 10).toString().equals("Pulang Paksa")) {
      component.setBackground(new Color(152, 152, 156));
      component.setForeground(new Color(245, 245, 255));
    } else if (table.getValueAt(row, 10).toString().equals("Dirawat")) {
      component.setBackground(new Color(255, 255, 0));
      component.setForeground(new Color(245, 255, 245));
    } else if (table.getValueAt(row, 10).toString().equals("Berkas Diterima")) {
      component.setBackground(new Color(204, 153, 255));
      component.setForeground(new Color(50, 50, 50));
    }
    if (table.getValueAt(row, 15).toString().equals("Sudah Bayar")) {
      component.setBackground(new Color(153, 255, 153));
      component.setForeground(new Color(50, 50, 50));
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTableKasirRalan.class.getName());
}
