
/*
 * Kontribusi Oleh Ferry Ardiansyah - RSIAP 3326051
 *
 * Created on May 22, 2010, 11:58:21 PM
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;

/**
 * @author dosen
 */
public class BPJSAntreanPerTanggal extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0, tot_belum = 0, tot_selesai = 0, tot_batal = 0, jkn_capaian_angka = 0, mjkn_capaian_angka = 0,
            quality_rate_angka = 0, quantity_rate_angka = 0;

    private double jkn_capaian, mjkn_capaian, jkn_belum, jkn_selesai, mjkn_belum, mjkn_selesai, umum_belum,
            umum_selesai, sep, quality_rate, quantity_rate;

    private ApiMobileJKN api = new ApiMobileJKN();

    private String URL = "", link = "", utc = "";

    private HttpHeaders headers;

    private HttpEntity requestEntity;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode root;

    private JsonNode nameNode;

    private JsonNode response;

    /**
     * Creates new form DlgJnsPerawatanRalan
     *
     * @param parent
     * @param modal
     */
    public BPJSAntreanPerTanggal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null,
                new Object[]{"Kode Booking", "Tanggal", "Kode Poli",
                    "Kode Dokter", "Jam Praktek", "NIK", "Noka",
                    "No. HP", "RM", "Jenis Kunjungan", "No. Ref", "Sumber Data",
                    "Peserta", "No. Antrean",
                    "Estimasi Dilayani", "Created Time", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(
                new Dimension(500, 500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(83);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(140);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(70);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(120);
            } else if (i == 15) {
                column.setPreferredWidth(120);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            link = koneksiDB.URLAPIMOBILEJKN();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel12 = new widget.Label();
        MJknCapaian = new widget.Label();
        MJknBelum = new widget.Label();
        jLabel13 = new widget.Label();
        MJknSelesai = new widget.Label();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar1 = new widget.Button();
        jLabel17 = new widget.Label();
        qualityrate = new widget.Label();
        jLabel18 = new widget.Label();
        quantityrate = new widget.Label();
        panelGlass8 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TotBelum = new widget.Label();
        jLabel9 = new widget.Label();
        TotSelesai = new widget.Label();
        jLabel20 = new widget.Label();
        TotBatal = new widget.Label();
        jLabel14 = new widget.Label();
        SEPTerbit = new widget.Label();
        jLabel10 = new widget.Label();
        JknBelum = new widget.Label();
        jLabel11 = new widget.Label();
        JknSelesai = new widget.Label();
        JknCapaian = new widget.Label();
        jLabel15 = new widget.Label();
        NonJKNBelum = new widget.Label();
        jLabel16 = new widget.Label();
        NonJKNSelesai = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Antrean Per Tanggal Mobile JKN ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel12.setForeground(new java.awt.Color(0, 153, 0));
        jLabel12.setText("MJKN Belum :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel12);

        MJknCapaian.setForeground(new java.awt.Color(0, 153, 0));
        MJknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknCapaian.setText("0");
        MJknCapaian.setName("MJknCapaian"); // NOI18N
        MJknCapaian.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknCapaian);

        MJknBelum.setForeground(new java.awt.Color(0, 153, 0));
        MJknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknBelum.setText("0");
        MJknBelum.setName("MJknBelum"); // NOI18N
        MJknBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknBelum);

        jLabel13.setForeground(new java.awt.Color(0, 153, 0));
        jLabel13.setText("MJKN Selesai :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel13);

        MJknSelesai.setForeground(new java.awt.Color(0, 153, 0));
        MJknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknSelesai.setText("0");
        MJknSelesai.setName("MJknSelesai"); // NOI18N
        MJknSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(MJknSelesai);

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-05-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-05-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(LCount);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnKeluar1);

        jLabel17.setForeground(new java.awt.Color(0, 153, 0));
        jLabel17.setText("Quality Rate :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel17);

        qualityrate.setForeground(new java.awt.Color(0, 153, 0));
        qualityrate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        qualityrate.setText("0");
        qualityrate.setName("qualityrate"); // NOI18N
        qualityrate.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(qualityrate);

        jLabel18.setForeground(new java.awt.Color(0, 153, 0));
        jLabel18.setText("Quantity Rate :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel18);

        quantityrate.setForeground(new java.awt.Color(0, 153, 0));
        quantityrate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quantityrate.setText("0");
        quantityrate.setName("quantityrate"); // NOI18N
        quantityrate.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass9.add(quantityrate);

        jPanel2.add(panelGlass9, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Total Belum :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(72, 23));
        panelGlass8.add(jLabel8);

        TotBelum.setForeground(new java.awt.Color(255, 153, 0));
        TotBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotBelum.setText("0");
        TotBelum.setName("TotBelum"); // NOI18N
        TotBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(TotBelum);

        jLabel9.setForeground(new java.awt.Color(102, 153, 0));
        jLabel9.setText("Total Selesai :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(77, 23));
        panelGlass8.add(jLabel9);

        TotSelesai.setForeground(new java.awt.Color(102, 153, 0));
        TotSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotSelesai.setText("0");
        TotSelesai.setName("TotSelesai"); // NOI18N
        TotSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(TotSelesai);

        jLabel20.setForeground(new java.awt.Color(102, 153, 0));
        jLabel20.setText("Total Batal :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(77, 23));
        panelGlass8.add(jLabel20);

        TotBatal.setForeground(new java.awt.Color(102, 153, 0));
        TotBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotBatal.setText("0");
        TotBatal.setName("TotBatal"); // NOI18N
        TotBatal.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(TotBatal);

        jLabel14.setForeground(new java.awt.Color(0, 153, 255));
        jLabel14.setText("SEP Terbit :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel14);

        SEPTerbit.setForeground(new java.awt.Color(0, 153, 255));
        SEPTerbit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SEPTerbit.setText("0");
        SEPTerbit.setName("SEPTerbit"); // NOI18N
        SEPTerbit.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(SEPTerbit);

        jLabel10.setForeground(new java.awt.Color(204, 204, 0));
        jLabel10.setText("JKN Belum :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel10);

        JknBelum.setForeground(new java.awt.Color(204, 204, 0));
        JknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknBelum.setText("0");
        JknBelum.setName("JknBelum"); // NOI18N
        JknBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknBelum);

        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setText("JKN Selesai :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel11);

        JknSelesai.setForeground(new java.awt.Color(204, 204, 0));
        JknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknSelesai.setText("0");
        JknSelesai.setName("JknSelesai"); // NOI18N
        JknSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknSelesai);

        JknCapaian.setForeground(new java.awt.Color(204, 204, 0));
        JknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknCapaian.setText("0 ");
        JknCapaian.setName("JknCapaian"); // NOI18N
        JknCapaian.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(JknCapaian);

        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Non JKN Belum :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel15);

        NonJKNBelum.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNBelum.setText("0");
        NonJKNBelum.setName("NonJKNBelum"); // NOI18N
        NonJKNBelum.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(NonJKNBelum);

        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Non JKN Selesai :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(92, 23));
        panelGlass8.add(jLabel16);

        NonJKNSelesai.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNSelesai.setText("0");
        NonJKNSelesai.setName("NonJKNSelesai"); // NOI18N
        NonJKNSelesai.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass8.add(NonJKNSelesai);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
//            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSAntreanPerTanggal dialog = new BPJSAntreanPerTanggal(
                    new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }

            });
            dialog.setVisible(true);
        });
    }

    private void emptTeks() {
        SEPTerbit.setText("");
        TotBelum.setText("");
        TotSelesai.setText("");
        JknBelum.setText("");
        JknSelesai.setText("");
        MJknBelum.setText("");
        MJknSelesai.setText("");
        NonJKNBelum.setText("");
        NonJKNSelesai.setText("");
        JknCapaian.setText("");
        MJknCapaian.setText("");
        sep = 0;
        tot_belum = 0;
        tot_selesai = 0;
        jkn_belum = 0;
        jkn_selesai = 0;
        mjkn_belum = 0;
        mjkn_selesai = 0;
        umum_belum = 0;
        umum_selesai = 0;
        jkn_capaian = 0;
        mjkn_capaian = 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCari;
    private widget.Button BtnKeluar1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label JknBelum;
    private widget.Label JknCapaian;
    private widget.Label JknSelesai;
    private widget.Label LCount;
    private widget.Label MJknBelum;
    private widget.Label MJknCapaian;
    private widget.Label MJknSelesai;
    private widget.Label NonJKNBelum;
    private widget.Label NonJKNSelesai;
    private widget.Label SEPTerbit;
    private widget.ScrollPane Scroll;
    private widget.Label TotBatal;
    private widget.Label TotBelum;
    private widget.Label TotSelesai;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Label qualityrate;
    private widget.Label quantityrate;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT reg_periksa.tgl_registrasi FROM reg_periksa WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? group by reg_periksa.tgl_registrasi");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
                        utc = String.valueOf(api.GetUTCdatetimeAsString());
                        headers.add("x-timestamp", utc);
                        headers.add("x-signature", api.getHmac(utc));
                        headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
                        requestEntity = new HttpEntity(headers);
                        URL = link + "/antrean/pendaftaran/tanggal/" + rs.
                                getString("tgl_registrasi");
                        System.out.println("URL : " + URL);
                        root = mapper.readTree(
                                api.getRest().exchange(URL, HttpMethod.GET,
                                        requestEntity, String.class).getBody());
                        nameNode = root.path("metadata");
                        if (nameNode.path("code").asText().equals("200")) {
                            response = mapper.readTree(api.Decrypt(root.path(
                                    "response").asText(), utc));
                            if (response.isArray()) {
                                for (JsonNode list : response) {
                                    tabMode.addRow(new Object[]{list.path(
                                        "kodebooking").asText(),
                                        list.path("tanggal").asText(), list.
                                        path("kodepoli").asText(),
                                        list.path("kodedokter").asText(), list.
                                        path("jampraktek").asText(),
                                        list.path("nik").asText(), list.path(
                                        "nokapst").asText(),
                                        list.path("nohp").asText(), list.path(
                                        "norekammedis").asText(),
                                        list.path("jeniskunjungan").asText(),
                                        list.path("nomorreferensi").asText(),
                                        list.path("sumberdata").asText(),
                                        list.path("ispeserta").asText().equals(
                                        "true") ? "Ya" : "Tidak",
                                        list.path("noantrean").asText(), list.
                                        path("estimasidilayani").asText(),
                                        list.path("createdtime").asText(), list.
                                        path("status").asText()});
                                    if (list.path("status").asText().equals(
                                            "Belum dilayani")) {
                                        tot_belum += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Selesai dilayani")) {
                                        tot_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Batal")) {
                                        tot_batal += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Belum dilayani")
                                            && list.path("sumberdata").asText().
                                                    equals("Bridging Antrean")
                                            && list.path("ispeserta").asText().
                                                    equals("true")) {
                                        jkn_belum += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Selesai dilayani")
                                            && list.path("sumberdata").asText().
                                                    equals("Bridging Antrean")
                                            && list.path("ispeserta").asText().
                                                    equals("true")) {
                                        jkn_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Belum dilayani")
                                            && list.path("sumberdata").asText().
                                                    equals("Mobile JKN")) {
                                        mjkn_belum += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Selesai dilayani")
                                            && list.path("sumberdata").asText().
                                                    equals("Mobile JKN")) {
                                        mjkn_selesai += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Belum dilayani")
                                            && list.path("ispeserta").asText().
                                                    equals("false")) {
                                        umum_belum += 1;
                                    }
                                    if (list.path("status").asText().equals(
                                            "Selesai dilayani")
                                            && list.path("ispeserta").asText().
                                                    equals("false")) {
                                        umum_selesai += 1;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Notif : " + nameNode.path(
                                    "message").asText());
                        }
                    } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException
                            | KeyManagementException | NoSuchAlgorithmException | SQLException | BadPaddingException
                            | IllegalBlockSizeException | NoSuchPaddingException | RestClientException ex) {
                        System.out.println("Notifikasi : " + ex);
                        if (ex.toString().contains("UnknownHostException")) {
                            JOptionPane.showMessageDialog(rootPane,
                                    "Koneksi ke server BPJS terputus...!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        sep = Sequel
                .cariInteger(
                        "select count(bridging_sep.no_rawat) from bridging_sep where bridging_sep.tglsep between '"
                        + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '"
                        + Valid.SetTgl(DTPCari2.getSelectedItem() + "")
                        + "' and bridging_sep.jnspelayanan = '2' and bridging_sep.kdpolitujuan <> 'IGD'")
                + Sequel.cariInteger(
                        "select count(bridging_sep_internal.no_rawat) from bridging_sep_internal where bridging_sep_internal.tglsep between '"
                        + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '"
                        + Valid.SetTgl(DTPCari2.getSelectedItem() + "")
                        + "' and bridging_sep_internal.jnspelayanan = '2' and bridging_sep_internal.kdpolitujuan <> 'IGD'");
        jkn_capaian = (jkn_selesai / sep) * 100;
        mjkn_capaian = (mjkn_selesai / sep) * 100;
        jkn_capaian_angka = (int) jkn_capaian;
        mjkn_capaian_angka = (int) mjkn_capaian;
        LCount.setText("" + tabMode.getRowCount());
        SEPTerbit.setText("" + sep);
        TotBelum.setText("" + tot_belum);
        TotSelesai.setText("" + tot_selesai);
        TotBatal.setText("" + tot_batal);
        JknBelum.setText("" + jkn_belum);
        JknSelesai.setText("" + jkn_selesai);
        JknCapaian.setText("(" + jkn_capaian_angka + "%)");
        MJknBelum.setText("" + mjkn_belum);
        MJknSelesai.setText("" + mjkn_selesai);
        MJknCapaian.setText("(" + mjkn_capaian_angka + "%)");
        NonJKNBelum.setText("" + umum_belum);
        NonJKNSelesai.setText("" + umum_selesai);
        quality_rate = (tot_selesai / (tot_belum + tot_selesai + tot_batal) * 100);
        quantity_rate = ((jkn_selesai + mjkn_selesai) / sep) * 100;
        quality_rate_angka = (int) quality_rate;
        quantity_rate_angka = (int) quantity_rate;
        qualityrate.setText("" + quality_rate_angka + "%");
        quantityrate.setText("" + quantity_rate_angka + "%");
    }

    private static final Logger LOG = Logger.getLogger(
            BPJSAntreanPerTanggal.class.getName());

}
