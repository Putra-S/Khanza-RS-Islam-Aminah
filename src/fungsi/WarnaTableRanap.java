/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Owner
 */
public class WarnaTableRanap extends DefaultTableCellRenderer {

  /** */
  public int kolom = 25;

  public int bayar = 6;

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
    try {
      String dateString = table.getValueAt(row, kolom).toString();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate dateToCompare = LocalDate.parse(dateString, formatter);
      LocalDate currentDate = LocalDate.now();
      long daysBetween = Math.abs(ChronoUnit.DAYS.between(currentDate, dateToCompare));

      if (daysBetween >= 2 && table.getValueAt(row, bayar).toString().equals("BPJS Kesehatan")) {
        component.setBackground(new Color(255, 255, 204));
      }
      if (daysBetween >= 6 && table.getValueAt(row, bayar).toString().equals("BPJS Kesehatan")) {
        component.setBackground(new Color(255, 153, 153));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return component;
  }

  private static final Logger LOG = Logger.getLogger(WarnaTableRanap.class.getName());
}
