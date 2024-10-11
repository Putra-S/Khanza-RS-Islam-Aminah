/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikanalisa;

/**
 * @author Via
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Via
 */
public class grafiksqlttv extends JDialog {

  /**
   * Creates a dataset.
   *
   * @param query
   * @param kolom2
   * @param kolom3
   * @return A dataset.
   */
  public static CategoryDataset createDataset1(
      String query,
      String kolom,
      String kolom2,
      String kolom3,
      String kolom4) { // data grafik nilai K dan D

    DefaultCategoryDataset result = new DefaultCategoryDataset();
    String series1 = "Suhu";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs = stat.executeQuery(query);
      while (rs.next()) {
        String tksbr = rs.getString(1) + " " + rs.getString(2);
        double field1 = rs.getDouble(3);

        result.addValue(field1, series1, tksbr);
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }

    return result;
  }

  /**
   * @param query
   * @param kolom
   * @param kolom2
   * @param kolom3
   * @param kolom4
   * @return
   */
  public static CategoryDataset createDataset2(
      String query,
      String kolom,
      String kolom2,
      String kolom3,
      String kolom4) { // data grafik nilai K dan D

    DefaultCategoryDataset result = new DefaultCategoryDataset();
    String series2 = "Nadi";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs = stat.executeQuery(query);
      while (rs.next()) {
        String tksbr = rs.getString(1) + " " + rs.getString(2);
        double field2 = rs.getDouble(4);

        result.addValue(field2, series2, tksbr);
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }

    return result;
  }

  /**
   * @param query
   * @param kolom
   * @param kolom2
   * @param kolom3
   * @param kolom4
   * @return
   */
  public static CategoryDataset createDataset3(
      String query,
      String kolom,
      String kolom2,
      String kolom3,
      String kolom4) { // data grafik nilai K dan D

    DefaultCategoryDataset result = new DefaultCategoryDataset();
    String series3 = "Respirasi";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs = stat.executeQuery(query);
      while (rs.next()) {
        String tksbr = rs.getString(1) + " " + rs.getString(2);
        double field3 = rs.getDouble(5);

        result.addValue(field3, series3, tksbr);
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }

    return result;
  }

  /**
   * @param query
   * @param kolom
   * @param kolom2
   * @param kolom3
   * @param kolom4
   * @return
   */
  public static CategoryDataset createDataset4(
      String query,
      String kolom,
      String kolom2,
      String kolom3,
      String kolom4) { // data grafik nilai K dan D

    DefaultCategoryDataset result = new DefaultCategoryDataset();
    String series4 = "SpO2";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs = stat.executeQuery(query);
      while (rs.next()) {
        String tksbr = rs.getString(1) + " " + rs.getString(2);
        double field4 = rs.getDouble(6);

        result.addValue(field4, series4, tksbr);
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }

    return result;
  }

  /**
   * Creates a chart.
   *
   * @return A chart.
   */
  private static JFreeChart createChart(
      String query, String kolom, String kolom2, String kolom3, String kolom4) {
    // Create datasets
    CategoryDataset dataset1 = createDataset1(query, kolom, kolom2, kolom3, kolom4);
    CategoryDataset dataset2 = createDataset2(query, kolom, kolom2, kolom3, kolom4);
    CategoryDataset dataset3 = createDataset3(query, kolom, kolom2, kolom3, kolom4);
    CategoryDataset dataset4 = createDataset4(query, kolom, kolom2, kolom3, kolom4);

    // Create range axes
    NumberAxis rangeAxis1 = new NumberAxis("Suhu(ᵒC)");
    rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    NumberAxis rangeAxis2 = new NumberAxis("Nadi(x/menit)");
    rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    NumberAxis rangeAxis3 = new NumberAxis("RR(x/menit)");
    rangeAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    NumberAxis rangeAxis4 = new NumberAxis("SpO2(%)");
    rangeAxis4.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    // Create renderers
    LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
    renderer1.setDefaultToolTipGenerator(
        new StandardCategoryToolTipGenerator("{0}: {1} = {3}", NumberFormat.getInstance()));
    LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
    renderer2.setDefaultToolTipGenerator(
        new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
    LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
    renderer3.setDefaultToolTipGenerator(
        new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
    LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
    renderer4.setDefaultToolTipGenerator(
        new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
    renderer4.setSeriesPaint(0, Color.BLACK);

    // Create plots
    CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
    CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
    CategoryPlot subplot3 = new CategoryPlot(dataset3, null, rangeAxis3, renderer3);
    CategoryPlot subplot4 = new CategoryPlot(dataset4, null, rangeAxis4, renderer4);

    // Customize plots
    subplot1.setDomainGridlinesVisible(true);
    subplot2.setDomainGridlinesVisible(true);
    subplot3.setDomainGridlinesVisible(true);
    subplot4.setDomainGridlinesVisible(true);

    // Add annotations for permanent tooltips
    addAnnotations(subplot1, dataset1);
    addAnnotations(subplot2, dataset2);
    addAnnotations(subplot3, dataset3);
    addAnnotations(subplot4, dataset4);

    // Create domain axis
    CategoryAxis domainAxis = new CategoryAxis("TTV");

    // Create combined plot
    CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
    plot.add(subplot1, 1);
    plot.add(subplot2, 1);
    plot.add(subplot3, 1);
    plot.add(subplot4, 1);

    // Create chart
    return new JFreeChart("Vital Signs Monitoring", new Font("Tahoma", Font.PLAIN, 12), plot, true);
  }

  /**
   * Creates a panel for the demo (used by SuperDemo.java).
   *
   * @param kolom3
   * @return A panel.
   */
  public static JPanel createDemoPanel(
      String query, String kolom, String kolom2, String kolom3, String kolom4) {
    JFreeChart chart = createChart(query, kolom, kolom2, kolom3, kolom4);
    return new ChartPanel(chart);
  }

  sekuel Sequel = new sekuel();
  validasi Valid = new validasi();
  Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

  public grafiksqlttv(
      String title, String query, String kolom, String kolom2, String kolom3, String kolom4) {
    setTitle(title);
    JPanel chartPanel = createDemoPanel(query, kolom, kolom2, kolom3, kolom4);

    chartPanel.setSize(screen.width, screen.height);
    setContentPane(chartPanel);
    setModal(true);
    setIconImage(
        new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
    pack();
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    chartPanel.setBackground(Color.BLACK);
  }

  /**
   * Starting point for the demonstration application.
   *
   * @param args ignored.
   */
  private static void addAnnotations(CategoryPlot plot, CategoryDataset dataset) {
    for (int row = 0; row < dataset.getRowCount(); row++) {
      for (int column = 0; column < dataset.getColumnCount(); column++) {
        Number value = dataset.getValue(row, column);
        if (value != null) {
          String annotationText = value.toString();
          double adjustedValue = value.doubleValue() - 5;
          CategoryTextAnnotation annotation =
              new CategoryTextAnnotation(
                  annotationText, dataset.getColumnKey(column), adjustedValue);
          annotation.setFont(new Font("Tahoma", Font.PLAIN, 9));
          annotation.setTextAnchor(org.jfree.chart.ui.TextAnchor.TOP_CENTER);
          plot.addAnnotation(annotation);
        }
      }
    }
  }

  private static final Logger LOG = Logger.getLogger(grafiksqlttv.class.getName());
}
