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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import fungsi.koneksiDB;
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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
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
     * @param kolom
     * @param kolom2
     * @param kolom3
     * @param kolom4
     * @param kolom5
     * @return A dataset.
     */
    public static CategoryDataset createDataset1(String query, String kolom, String kolom2, String kolom3,
            String kolom4, String kolom5) { // data grafik nilai K dan D

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series1 = "Suhu";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field1 = rs.getDouble(3);

                if (field1 != 0) {
                    result.addValue(field1, series1, tksbr);
                }
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
     * @param kolom5
     * @return
     */
    public static CategoryDataset createDataset2(String query, String kolom, String kolom2, String kolom3,
            String kolom4, String kolom5) { // data grafik nilai K dan D

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series2 = "Nadi";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field2 = rs.getDouble(4);

                if (field2 != 0) {
                    result.addValue(field2, series2, tksbr);
                }
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
     * @param kolom5
     * @return
     */
    public static CategoryDataset createDataset3(String query, String kolom, String kolom2, String kolom3,
            String kolom4, String kolom5) { // data grafik nilai K dan D

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series3 = "Respirasi";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field3 = rs.getDouble(5);

                if (field3 != 0) {
                    result.addValue(field3, series3, tksbr);
                }
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
     * @param kolom5
     * @return
     */
    public static CategoryDataset createDataset4(String query, String kolom, String kolom2, String kolom3,
            String kolom4, String kolom5) { // data grafik nilai K dan D

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series4 = "SpO2";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field4 = rs.getDouble(6);

                if (field4 != 0) {
                    result.addValue(field4, series4, tksbr);
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return result;
    }

    public static CategoryDataset createDataset5(String query, String kolom, String kolom2, String kolom3,
            String kolom4, String kolom5) { // data grafik nilai K dan D

        DefaultCategoryDataset result = new DefaultCategoryDataset();
        String series5 = "Sistole";
        String series6 = "Diastole";

        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String tksbr = rs.getString(1) + " " + rs.getString(2);
                double field5 = rs.getDouble(7);
                double field6 = rs.getDouble(8);

                if (field5 != 0) {
                    result.addValue(field5, series5, tksbr);
                }
                if (field6 != 0) {
                    result.addValue(field6, series6, tksbr);
                }
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
    private static JFreeChart createChart(String query, String kolom, String kolom2, String kolom3, String kolom4,
            String kolom5) {
        // Create datasets
        CategoryDataset dataset1 = createDataset1(query, kolom, kolom2, kolom3, kolom4, kolom5);
        CategoryDataset dataset2 = createDataset2(query, kolom, kolom2, kolom3, kolom4, kolom5);
        CategoryDataset dataset3 = createDataset3(query, kolom, kolom2, kolom3, kolom4, kolom5);
        CategoryDataset dataset4 = createDataset4(query, kolom, kolom2, kolom3, kolom4, kolom5);
        CategoryDataset dataset5 = createDataset5(query, kolom, kolom2, kolom3, kolom4, kolom5);

        // Create range axes
        NumberAxis rangeAxis1 = new NumberAxis("Suhu(ᵒC)");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis1.setRange(30.0, 40.0);
        NumberAxis rangeAxis2 = new NumberAxis("Nadi(x/menit)");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setRange(45.0, 150.0);
        NumberAxis rangeAxis3 = new NumberAxis("RR(x/menit)");
        rangeAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis3.setRange(15.0, 50.0);
        NumberAxis rangeAxis4 = new NumberAxis("SpO2(%)");
        rangeAxis4.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis4.setRange(80.0, 100.0);
        NumberAxis rangeAxis5 = new NumberAxis("Tensi(mmhg)");
        rangeAxis5.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Create renderers
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
        renderer1.setSeriesPaint(0, new Color(204, 204, 0));
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
        renderer2.setSeriesPaint(0, Color.RED);
        LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
        renderer3.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
        renderer3.setSeriesPaint(0, Color.ORANGE);
        LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
        renderer4.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
        renderer4.setSeriesPaint(0, Color.GREEN);
        LineAndShapeRenderer renderer5 = new LineAndShapeRenderer();
        renderer5.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator("{0}: {1} = {2}", NumberFormat.getInstance()));
        renderer5.setSeriesPaint(0, Color.RED);
        renderer5.setSeriesPaint(1, Color.BLUE);

        // Create plots
        CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1, renderer1);
        CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2, renderer2);
        CategoryPlot subplot3 = new CategoryPlot(dataset3, null, rangeAxis3, renderer3);
        CategoryPlot subplot4 = new CategoryPlot(dataset4, null, rangeAxis4, renderer4);
        CategoryPlot subplot5 = new CategoryPlot(dataset5, null, rangeAxis5, renderer5);

        // Customize plots
        subplot1.setDomainGridlinesVisible(true);
        subplot2.setDomainGridlinesVisible(true);
        subplot3.setDomainGridlinesVisible(true);
        subplot4.setDomainGridlinesVisible(true);
        subplot5.setDomainGridlinesVisible(true);

        // Add annotations for permanent tooltips
        addAnnotations(subplot1, dataset1);
        addAnnotations(subplot2, dataset2);
        addAnnotations(subplot3, dataset3);
        addAnnotations(subplot4, dataset4);
        addAnnotations(subplot5, dataset5);

        // Create domain axis
        CategoryAxis domainAxis = new CategoryAxis("TTV");
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Create combined plot
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 5);
        plot.add(subplot2, 5);
        plot.add(subplot3, 5);
        plot.add(subplot4, 5);
        plot.add(subplot5, 5);

        // Create chart
        return new JFreeChart("Vital Signs Monitoring", new Font("Tahoma", Font.PLAIN, 12), plot, true);
    }

//	private static JFreeChart createChart(String query, String kolom, String kolom2, String kolom3, String kolom4, String kolom5) {
//		// Create datasets
//		CategoryDataset dataset1 = createDataset1(query, kolom, kolom2, kolom3, kolom4, kolom5);
//		CategoryDataset dataset2 = createDataset2(query, kolom, kolom2, kolom3, kolom4, kolom5);
//		CategoryDataset dataset3 = createDataset3(query, kolom, kolom2, kolom3, kolom4, kolom5);
//		CategoryDataset dataset4 = createDataset4(query, kolom, kolom2, kolom3, kolom4, kolom5);
//		CategoryDataset dataset5 = createDataset5(query, kolom, kolom2, kolom3, kolom4, kolom5);
//
//		// Create the main plot
//		CategoryPlot plot = new CategoryPlot();
//		plot.setDomainAxis(new CategoryAxis("TTV"));
//		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//
//		// Create range axis for each dataset
//		NumberAxis rangeAxis1 = new NumberAxis("Suhu(ᵒC)");
////		rangeAxis1.setRange(30.0, 40.0);
//
//		NumberAxis rangeAxis2 = new NumberAxis("Nadi(x/menit)");
////		rangeAxis2.setRange(45.0, 150.0);
//
//		NumberAxis rangeAxis3 = new NumberAxis("RR(x/menit)");
////		rangeAxis3.setRange(15.0, 40.0);
//
//		NumberAxis rangeAxis4 = new NumberAxis("SpO2(%)");
////		rangeAxis4.setRange(80.0, 100.0);
//
//		NumberAxis rangeAxis5 = new NumberAxis("Tensi(mmhg)");
//
//		// Add datasets and renderers
//		LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
//		renderer1.setSeriesPaint(0, new Color(204, 204, 0));
//		plot.setDataset(0, dataset1);
//		plot.setRenderer(0, renderer1);
//		plot.setRangeAxis(0, rangeAxis1);
//		plot.mapDatasetToRangeAxis(0, 0);
//
//		LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
//		renderer2.setSeriesPaint(0, Color.RED);
//		plot.setDataset(1, dataset2);
//		plot.setRenderer(1, renderer2);
//		plot.setRangeAxis(1, rangeAxis2);
//		plot.mapDatasetToRangeAxis(1, 1);
//
//		LineAndShapeRenderer renderer3 = new LineAndShapeRenderer();
//		renderer3.setSeriesPaint(0, Color.ORANGE);
//		plot.setDataset(2, dataset3);
//		plot.setRenderer(2, renderer3);
//		plot.setRangeAxis(2, rangeAxis3);
//		plot.mapDatasetToRangeAxis(2, 2);
//
//		LineAndShapeRenderer renderer4 = new LineAndShapeRenderer();
//		renderer4.setSeriesPaint(0, Color.GREEN);
//		plot.setDataset(3, dataset4);
//		plot.setRenderer(3, renderer4);
//		plot.setRangeAxis(3, rangeAxis4);
//		plot.mapDatasetToRangeAxis(3, 3);
//
//		LineAndShapeRenderer renderer5 = new LineAndShapeRenderer();
//		renderer5.setSeriesPaint(0, Color.RED);
//		renderer5.setSeriesPaint(1, Color.BLUE);
//		plot.setDataset(4, dataset5);
//		plot.setRenderer(4, renderer5);
//		plot.setRangeAxis(4, rangeAxis5);
//		plot.mapDatasetToRangeAxis(4, 4);
//		
//		addAnnotations(plot, dataset1, 0);
//		addAnnotations(plot, dataset2, 1);
//		addAnnotations(plot, dataset3, 2);
//		addAnnotations(plot, dataset4, 3);
//		addAnnotations(plot, dataset5, 4);
//
//		// Create chart
//		JFreeChart chart = new JFreeChart("Vital Signs Monitoring", new Font("Tahoma", Font.PLAIN, 12), plot, true);
//		return chart;
//	}
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @param query
     * @param kolom
     * @param kolom2
     * @param kolom3
     * @param kolom4
     * @param kolom5
     * @return A panel.
     */
    public static JPanel createDemoPanel(String query, String kolom, String kolom2, String kolom3, String kolom4,
            String kolom5) {
        JFreeChart chart = createChart(query, kolom, kolom2, kolom3, kolom4, kolom5);
        return new ChartPanel(chart);
    }

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public grafiksqlttv(String title, String query, String kolom, String kolom2, String kolom3, String kolom4,
            String kolom5) {
        setTitle(title);
        JPanel chartPanel = createDemoPanel(query, kolom, kolom2, kolom3, kolom4, kolom5);

        chartPanel.setSize(screen.width, screen.height);
        setContentPane(chartPanel);
        setModal(true);
        setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        chartPanel.setBackground(Color.WHITE);
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
                    double adjustedValue = value.doubleValue() - 2;
                    CategoryTextAnnotation annotation = new CategoryTextAnnotation(annotationText,
                            dataset.getColumnKey(column), adjustedValue);
                    annotation.setFont(new Font("Tahoma", Font.PLAIN, 9));
                    annotation.setTextAnchor(org.jfree.chart.ui.TextAnchor.TOP_CENTER);
                    plot.addAnnotation(annotation);
                }
            }
        }
    }

//	private static void addAnnotations(CategoryPlot plot, CategoryDataset dataset, int datasetIndex) {
//		// Iterate through the dataset rows and columns
//		for (int row = 0; row < dataset.getRowCount(); row++) {
//			for (int column = 0; column < dataset.getColumnCount(); column++) {
//				Number value = dataset.getValue(row, column);
//				if (value != null) {
//					// Create annotation text
//					String annotationText = String.format("%.2f", value.doubleValue());
//
//					// Create the annotation
//					CategoryTextAnnotation annotation = new CategoryTextAnnotation(
//						annotationText, // Text of the annotation
//						dataset.getColumnKey(column), // Category (X-axis value)
//						value.doubleValue() // Value (Y-axis value)
//					);
//
//					// Customize the annotation style
//					annotation.setFont(new Font("SansSerif", Font.PLAIN, 10));
//					annotation.setPaint(Color.BLACK);
//
//					// Add annotation to the plot
//					plot.addAnnotation(annotation);
//				}
//			}
//		}
//	}
    private static final Logger LOG = Logger.getLogger(grafiksqlttv.class.getName());

}
