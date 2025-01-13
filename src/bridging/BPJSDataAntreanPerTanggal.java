/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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
public class BPJSDataAntreanPerTanggal extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private validasi Valid = new validasi();

    private sekuel Sequel = new sekuel();

    private int i = 0, tot_belum = 0, tot_selesai = 0, jkn_capaian_angka = 0, mjkn_capaian_angka = 0;

    private double jkn_capaian, mjkn_capaian, jkn_belum, jkn_selesai, mjkn_belum, mjkn_selesai, umum_belum,
            umum_selesai, sep;

    private ApiMobileJKN api = new ApiMobileJKN();

    private String URL = "", link = "", utc = "";

    private HttpHeaders headers;

    private HttpEntity requestEntity;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode root;

    private JsonNode nameNode;

    private JsonNode response;

    /**
     * Creates new form DlgKamar
     *
     * @param parent
     * @param modal
     */
    public BPJSDataAntreanPerTanggal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null,
                new String[]{"No.", "Kode Booking", "Tanggal", "Kode Poli", "Kode Dokter", "Jam Praktek", "NIK",
                    "Nomor Kartu", "No. HP", "No. RM", "Jenis Kunjungan", "Nomor Referensi", "Sumber Data",
                    "Kepesertaan", "No Antrean", "Estimasi Dilayani", "Waktu Insert", "Status"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbKamar.setModel(tabMode);

        // tbKamar.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 18; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            link = koneksiDB.URLAPIMOBILEJKN();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        Tanggal = new widget.Tanggal();
        BtnCari = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel8 = new widget.Label();
        TotBelum = new widget.Label();
        jLabel9 = new widget.Label();
        TotSelesai = new widget.Label();
        jLabel14 = new widget.Label();
        SEPTerbit = new widget.Label();
        jLabel10 = new widget.Label();
        JknBelum = new widget.Label();
        jLabel11 = new widget.Label();
        JknSelesai = new widget.Label();
        JknCapaian = new widget.Label();
        jLabel15 = new widget.Label();
        NonJKNBelum = new widget.Label();
        jLabel17 = new widget.Label();
        NonJKNSelesai = new widget.Label();
        jLabel12 = new widget.Label();
        MJknBelum = new widget.Label();
        MJknCapaian = new widget.Label();
        jLabel13 = new widget.Label();
        MJknSelesai = new widget.Label();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Antrean Per Tanggal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setText("Tanggal :");
        jLabel16.setToolTipText("");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel16);

        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(Tanggal);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnKeluar);

        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Total Belum :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel8);

        TotBelum.setForeground(new java.awt.Color(255, 153, 0));
        TotBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotBelum.setText("0");
        TotBelum.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TotBelum.setName("TotBelum"); // NOI18N
        TotBelum.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(TotBelum);

        jLabel9.setForeground(new java.awt.Color(102, 153, 0));
        jLabel9.setText("Total Selesai :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(72, 23));
        panelGlass6.add(jLabel9);

        TotSelesai.setForeground(new java.awt.Color(102, 153, 0));
        TotSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TotSelesai.setText("0");
        TotSelesai.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        TotSelesai.setName("TotSelesai"); // NOI18N
        TotSelesai.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(TotSelesai);

        jLabel14.setForeground(new java.awt.Color(0, 153, 255));
        jLabel14.setText("SEP Terbit :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel14);

        SEPTerbit.setForeground(new java.awt.Color(0, 153, 255));
        SEPTerbit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SEPTerbit.setText("0");
        SEPTerbit.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        SEPTerbit.setName("SEPTerbit"); // NOI18N
        SEPTerbit.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(SEPTerbit);

        jLabel10.setForeground(new java.awt.Color(204, 204, 0));
        jLabel10.setText("JKN Belum :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel10);

        JknBelum.setForeground(new java.awt.Color(204, 204, 0));
        JknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknBelum.setText("0");
        JknBelum.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JknBelum.setName("JknBelum"); // NOI18N
        JknBelum.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(JknBelum);

        jLabel11.setForeground(new java.awt.Color(204, 204, 0));
        jLabel11.setText("JKN Selesai :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel11);

        JknSelesai.setForeground(new java.awt.Color(204, 204, 0));
        JknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknSelesai.setText("0");
        JknSelesai.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JknSelesai.setName("JknSelesai"); // NOI18N
        JknSelesai.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(JknSelesai);

        JknCapaian.setForeground(new java.awt.Color(204, 204, 0));
        JknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JknCapaian.setText("0 ");
        JknCapaian.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JknCapaian.setName("JknCapaian"); // NOI18N
        JknCapaian.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(JknCapaian);

        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Non JKN Belum :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass6.add(jLabel15);

        NonJKNBelum.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNBelum.setText("0");
        NonJKNBelum.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NonJKNBelum.setName("NonJKNBelum"); // NOI18N
        NonJKNBelum.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(NonJKNBelum);

        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("Non JKN Selesai :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(jLabel17);

        NonJKNSelesai.setForeground(new java.awt.Color(0, 153, 153));
        NonJKNSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NonJKNSelesai.setText("0");
        NonJKNSelesai.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NonJKNSelesai.setName("NonJKNSelesai"); // NOI18N
        NonJKNSelesai.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(NonJKNSelesai);

        jLabel12.setForeground(new java.awt.Color(0, 153, 0));
        jLabel12.setText("MJKN Belum :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel12);

        MJknBelum.setForeground(new java.awt.Color(0, 153, 0));
        MJknBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknBelum.setText("0");
        MJknBelum.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MJknBelum.setName("MJknBelum"); // NOI18N
        MJknBelum.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(MJknBelum);

        MJknCapaian.setForeground(new java.awt.Color(0, 153, 0));
        MJknCapaian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknCapaian.setText("0");
        MJknCapaian.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MJknCapaian.setName("MJknCapaian"); // NOI18N
        MJknCapaian.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(MJknCapaian);

        jLabel13.setForeground(new java.awt.Color(0, 153, 0));
        jLabel13.setText("MJKN Selesai :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass6.add(jLabel13);

        MJknSelesai.setForeground(new java.awt.Color(0, 153, 0));
        MJknSelesai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MJknSelesai.setText("0");
        MJknSelesai.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        MJknSelesai.setName("MJknSelesai"); // NOI18N
        MJknSelesai.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(MJknSelesai);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(LCount);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");
            int row = tabMode.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'" + r + "','"
                        + tabMode.getValueAt(r, 0).toString() + "','"
                        + tabMode.getValueAt(r, 1).toString() + "','"
                        + tabMode.getValueAt(r, 2).toString() + "','"
                        + tabMode.getValueAt(r, 3).toString() + "','"
                        + tabMode.getValueAt(r, 4).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                        getalamatip() + "'", "Rekap Harian Pengadaan Ipsrs");
            }

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            //param.put("peserta","No.Peserta : "+NoKartu.getText()+" Nama Peserta : "+NamaPasien.getText());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            Valid.MyReportqry("rptCariBPJSReferensiPoliHFIS.jasper", "report",
                    "[ Pencarian Referensi Poli HFIS ]",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
//            Valid.pindah(evt,Poli,BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSDataAntreanPerTanggal dialog = new BPJSDataAntreanPerTanggal(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }

            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
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
    private widget.Tanggal Tanggal;
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
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
            requestEntity = new HttpEntity(headers);
            URL = link + "/antrean/pendaftaran/tanggal/" + Valid.SetTgl(Tanggal.getSelectedItem() + "");
            System.out.println(URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                // response = root.path("response");
                if (response.isArray()) {
                    i = 1;
                    for (JsonNode list : response) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        tabMode.addRow(new Object[]{i + ".", list.path("kodebooking").asText(),
                            list.path("tanggal").asText(), list.path("kodepoli").asText(),
                            list.path("kodedokter").asText(), list.path("jampraktek").asText(),
                            list.path("nik").asText(), list.path("nokapst").asText(), list.path("nohp").asText(),
                            list.path("norekammedis").asText(), list.path("jeniskunjungan").asText(),
                            list.path("nomorreferensi").asText(), list.path("sumberdata").asText(),
                            list.path("ispeserta").asText(), list.path("noantrean").asText(),
                            sdf.format(list.path("estimasidilayani").asLong()),
                            sdf.format(list.path("createdtime").asLong()), list.path("status").asText()});
                        if (list.path("status").asText().equals("Belum dilayani")) {
                            tot_belum += 1;
                        }
                        if (list.path("status").asText().equals("Selesai dilayani")) {
                            tot_selesai += 1;
                        }
                        if (list.path("status").asText().equals("Belum dilayani")
                                && list.path("sumberdata").asText().equals("Bridging Antrean")
                                && list.path("ispeserta").asText().equals("true")) {
                            jkn_belum += 1;
                        }
                        if (list.path("status").asText().equals("Selesai dilayani")
                                && list.path("sumberdata").asText().equals("Bridging Antrean")
                                && list.path("ispeserta").asText().equals("true")) {
                            jkn_selesai += 1;
                        }
                        if (list.path("status").asText().equals("Belum dilayani")
                                && list.path("sumberdata").asText().equals("Mobile JKN")) {
                            mjkn_belum += 1;
                        }
                        if (list.path("status").asText().equals("Selesai dilayani")
                                && list.path("sumberdata").asText().equals("Mobile JKN")) {
                            mjkn_selesai += 1;
                        }
                        if (list.path("status").asText().equals("Belum dilayani")
                                && list.path("ispeserta").asText().equals("false")) {
                            umum_belum += 1;
                        }
                        if (list.path("status").asText().equals("Selesai dilayani")
                                && list.path("ispeserta").asText().equals("false")) {
                            umum_selesai += 1;
                        }

                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (HeadlessException | IOException | InvalidAlgorithmParameterException | InvalidKeyException
                | KeyManagementException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException | RestClientException ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
        sep = Sequel
                .cariInteger("select count(bridging_sep.no_rawat) from bridging_sep where bridging_sep.tglsep = '"
                        + Valid.SetTgl(Tanggal.getSelectedItem() + "")
                        + "' and bridging_sep.jnspelayanan = '2' and bridging_sep.kdpolitujuan <> 'IGD'")
                + Sequel.cariInteger(
                        "select count(bridging_sep_internal.no_rawat) from bridging_sep_internal where bridging_sep_internal.tglsep = '"
                        + Valid.SetTgl(Tanggal.getSelectedItem() + "")
                        + "' and bridging_sep_internal.jnspelayanan = '2' and bridging_sep_internal.kdpolitujuan <> 'IGD'");
        jkn_capaian = (jkn_selesai / sep) * 100;
        mjkn_capaian = (mjkn_selesai / sep) * 100;
        jkn_capaian_angka = (int) jkn_capaian;
        mjkn_capaian_angka = (int) mjkn_capaian;
        LCount.setText("" + tabMode.getRowCount());
        SEPTerbit.setText("" + sep);
        TotBelum.setText("" + tot_belum);
        TotSelesai.setText("" + tot_selesai);
        JknBelum.setText("" + jkn_belum);
        JknSelesai.setText("" + jkn_selesai);
        JknCapaian.setText("(" + jkn_capaian_angka + "%)");
        MJknBelum.setText("" + mjkn_belum);
        MJknSelesai.setText("" + mjkn_selesai);
        MJknCapaian.setText("(" + mjkn_capaian_angka + "%)");
        NonJKNBelum.setText("" + umum_belum);
        NonJKNSelesai.setText("" + umum_selesai);
    }

    /**
     * @return
     */
    public JTable getTable() {
        return tbKamar;
    }

    private static final Logger LOG = Logger.getLogger(BPJSDataAntreanPerTanggal.class.getName());

}
