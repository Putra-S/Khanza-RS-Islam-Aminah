package informasi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JFrame;

/**
 * @author dosen
 */
public class InformasiSIRSRSI extends javax.swing.JDialog {

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public InformasiSIRSRSI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        JFrame frame = new JFrame("Webpage Display Application");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load("https://karyawan.rsislamaminah.co.id");
        });
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screen.width, screen.height);
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
//GEN-BEGIN:initComponents
    internalFrame1 = new widget.InternalFrame();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setUndecorated(true);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Jadwal Cuti Dokter ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
    internalFrame1.setName("internalFrame1"); // NOI18N
    internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));
    getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            InformasiSIRSRSI dialog = new InformasiSIRSRSI(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }

            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private widget.InternalFrame internalFrame1;
  // End of variables declaration//GEN-LINE:variables
	private static final Logger LOG = Logger.getLogger(InformasiSIRSRSI.class.getName());

}
