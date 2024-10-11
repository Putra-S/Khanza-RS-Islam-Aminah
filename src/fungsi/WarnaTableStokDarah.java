/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Owner
 */
public class WarnaTableStokDarah extends DefaultTableCellRenderer {

  /** */
  public int kolom = 5;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate tanggal = LocalDate.parse(table.getValueAt(row, kolom).toString(), formatter);
    LocalDate tanggalKurang10Hari = tanggal.minusDays(10);
    LocalDate tanggalHariIni = LocalDate.now();
    if (tanggalKurang10Hari.isBefore(tanggalHariIni)) {
      component.setBackground(new Color(255, 153, 153));
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTableStokDarah.class.getName());
}
