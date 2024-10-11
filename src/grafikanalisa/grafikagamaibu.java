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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Via
 */
public class grafikagamaibu extends JDialog {

  /**
   * Creates a dataset.
   *
   * @return A dataset.
   */
  /**
   * Creates a dataset.
   *
   * @param symbol
   * @return A dataset.
   */
  public static CategoryDataset createDataset1(String symbol) { // data grafik nilai K dan D

    DefaultCategoryDataset result = new DefaultCategoryDataset();
    String series1 = "Agama Ibu";
    String series2 = "Agama";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs =
          stat.executeQuery(
              "SELECT agama,count(agama) as jumlah  FROM pasien_ibu "
                  + " "
                  + symbol
                  + " group by agama");
      while (rs.next()) {
        String tksbr = rs.getString(1) + "(" + rs.getString(2) + ")";
        double field1 = rs.getDouble(2);
        double field2 = rs.getDouble(2);

        // result.addValue(field1, series1,tksbr);
        result.addValue(field2, series1, tksbr);
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }
    return result;
  }

  /**
   * Creates a dataset.
   *
   * @param symbol
   * @return A dataset.
   */
  public static CategoryDataset createDataset2(String symbol) { // grafik volume
    DefaultCategoryDataset result = new DefaultCategoryDataset();

    String series1 = "Agama Ibu";
    String series2 = "Agama";

    try {
      Statement stat = koneksiDB.condb().createStatement();
      ResultSet rs =
          stat.executeQuery(
              "SELECT agama,count(agama) as jumlah  FROM pasien_ibu "
                  + " "
                  + symbol
                  + " group by agama");
      while (rs.next()) {
        String tksbr = rs.getString(1) + "(" + rs.getString(2) + ")";
        double field1 = rs.getDouble(2);
        double field2 = rs.getDouble(2);

        result.addValue(field1, series1, tksbr);
        // result.addValue(field2, series2,tksbr);
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
  private static JFreeChart createChart(String symbol) {

    CategoryDataset dataset1 = createDataset1(symbol);
    NumberAxis rangeAxis1 = new NumberAxis("Jumlah");
    rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
    renderer1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
    CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
    subplot1.setDomainGridlinesVisible(true);

    CategoryDataset dataset2 = createDataset2(symbol);
    NumberAxis rangeAxis2 = new NumberAxis("Jumlah");
    rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    BarRenderer renderer2 = new BarRenderer();
    renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
    CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
    subplot2.setDomainGridlinesVisible(true);

    CategoryAxis domainAxis = new CategoryAxis("Agama Ibu");
    CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
    //             CombinedCategoryPlot plot = new CombinedCategoryPlot(
    //                     domainAxis, new NumberAxis("Range"));
    plot.add(subplot1, 2);
    plot.add(subplot2, 1);

    return new JFreeChart("", new Font("SansSerif", Font.PLAIN, 6), plot, true);
  }

  /**
   * Creates a panel for the demo (used by SuperDemo.java).
   *
   * @param symbol
   * @return A panel.
   */
  public static JPanel createDemoPanel(String symbol) {
    JFreeChart chart = createChart(symbol);
    return new ChartPanel(chart);
  }

  sekuel Sequel = new sekuel();
  validasi Valid = new validasi();
  Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

  /**
   * @param title
   * @param symbol
   */
  public grafikagamaibu(String title, String symbol) {
    // super(title);
    setTitle(title);
    JPanel chartPanel = createDemoPanel(symbol);

    chartPanel.setSize(screen.width, screen.height);
    setContentPane(chartPanel);

    // setSize(screen.width,screen.height);
    setModal(true);
    // setUndecorated(true);
    setIconImage(
        new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
    pack();
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  private static final Logger LOG = Logger.getLogger(grafikagamaibu.class.getName());

  /**
   * Starting point for the demonstration application.
   *
   * @param args ignored.
   */
  //        public static void main(String args[]){
  //            //        String title = "test Combined Category Plot Demo 1";
  //        cocografik demo = new cocografik("aali");
  //        JFrame v = new JFrame(title);
  //        v.add(demo);
  //        v.setBackground(Color.BLUE);
  //        v.setSize(new Dimension(1200, 700));
  //        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
  //        v.setVisible(true);
  //        }
  //           public static void main(String[] args) {
  //             String title = "Combined Category Plot Demo ";
  //             CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
  //             demo.pack();
  ////             RefineryUtilities.centerFrameOnScreen(demo);
  //             demo.setVisible(true);
  //
  //         }
}
