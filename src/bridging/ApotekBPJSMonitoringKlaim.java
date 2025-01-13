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
import fungsi.batasInput;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
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
public class ApotekBPJSMonitoringKlaim extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private validasi Valid = new validasi();

    private sekuel Sequel = new sekuel();

    private int i = 0;

    private ApiApotekBPJS api = new ApiApotekBPJS();

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
    public ApotekBPJSMonitoringKlaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new String[]{"No.SEP Apotek", "No.SEP Asal", "Nomor Kartu",
            "Nama Peserta", "No.Resep", "Jenis Obat", "Tgl.Pelayanan", "Biaya Pengajuan", "Biaya Disetujui"}) {
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

        for (i = 0; i < 9; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(76);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

            });
        }

        try {
            link = koneksiDB.URLAPIAPOTEKBPJS();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }

        Valid.LoadTahun(Tahun);
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
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        label14 = new widget.Label();
        LCountPengajuan = new widget.Label();
        label15 = new widget.Label();
        LCountDisetujui = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tahun = new widget.ComboBox();
        Bulan = new widget.ComboBox();
        label12 = new widget.Label();
        Jenis = new widget.ComboBox();
        label13 = new widget.Label();
        Status = new widget.ComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Monitoring Klaim Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setText("Keyword :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass6.add(jLabel16);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(150, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass6.add(TCari);

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

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(57, 23));
        panelGlass6.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass6.add(LCount);

        label14.setText("Pengajuan :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(68, 23));
        panelGlass6.add(label14);

        LCountPengajuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountPengajuan.setText("0");
        LCountPengajuan.setName("LCountPengajuan"); // NOI18N
        LCountPengajuan.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass6.add(LCountPengajuan);

        label15.setText("Disetujui :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(58, 23));
        panelGlass6.add(label15);

        LCountDisetujui.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountDisetujui.setText("0");
        LCountDisetujui.setName("LCountDisetujui"); // NOI18N
        LCountDisetujui.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass6.add(LCountDisetujui);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnPrint);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
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

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tahun & Bulan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(label11);

        Tahun.setName("Tahun"); // NOI18N
        Tahun.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi1.add(Tahun);

        Bulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        Bulan.setName("Bulan"); // NOI18N
        Bulan.setPreferredSize(new java.awt.Dimension(62, 23));
        panelisi1.add(Bulan);

        label12.setText("Jenis Obat :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(105, 23));
        panelisi1.add(label12);

        Jenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Semua", "1. Obat PRB", "2. Obat Kronis Belum Stabil", "3. Obat Kemoterapi" }));
        Jenis.setName("Jenis"); // NOI18N
        Jenis.setPreferredSize(new java.awt.Dimension(185, 23));
        panelisi1.add(Jenis);

        label13.setText("Status :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(85, 23));
        panelisi1.add(label13);

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Belum diverifikasi", "2. Sudah Verifikasi" }));
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(150, 23));
        panelisi1.add(Status);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_START);

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
                        + tabMode.getValueAt(r, 4).toString() + "','"
                        + tabMode.getValueAt(r, 5).toString() + "','"
                        + tabMode.getValueAt(r, 6).toString() + "','"
                        + tabMode.getValueAt(r, 7).toString() + "','"
                        + tabMode.getValueAt(r, 8).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                        getalamatip() + "'", "Rekap Harian Pengadaan Ipsrs");
            }

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            Valid.MyReportqry("rptApotekBPJSMonitoringKlaim.jasper", "report",
                    "[ Monitoring Klaim Obat Apotek BPJS ]",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampil();
            BtnPrint.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            tampil();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSMonitoringKlaim dialog = new ApotekBPJSMonitoringKlaim(new javax.swing.JFrame(), true);
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
    private widget.ComboBox Bulan;
    private widget.ComboBox Jenis;
    private widget.Label LCount;
    private widget.Label LCountDisetujui;
    private widget.Label LCountPengajuan;
    private widget.ScrollPane Scroll;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.ComboBox Tahun;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelisi1;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        try {
            LCountPengajuan.setText("0");
            LCountDisetujui.setText("0");
            LCount.setText("0");
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "monitoring/klaim/" + Bulan.getSelectedItem().toString() + "/"
                    + Tahun.getSelectedItem().toString() + "/" + Jenis.getSelectedItem().toString().substring(0, 1)
                    + "/" + Status.getSelectedItem().toString().substring(0, 1);
            System.out.println(URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                LCountPengajuan.setText(Valid.SetAngka(response.path("rekap").path("totalbiayapengajuan").asDouble()));
                LCountDisetujui.setText(Valid.SetAngka(response.path("rekap").path("totalbiayasetuju").asDouble()));
                LCount.setText(response.path("rekap").path("jumlahdata").asText());
                if (response.path("rekap").path("listsep").isArray()) {
                    if (TCari.getText().trim().isEmpty()) {
                        for (JsonNode list : response.path("rekap").path("listsep")) {
                            tabMode.addRow(new Object[]{list.path("nosepapotek").asText(),
                                list.path("nosepaasal").asText(), list.path("nokartu").asText(),
                                list.path("namapeserta").asText(), list.path("noresep").asText(),
                                list.path("jnsobat").asText(), list.path("tglpelayanan").asText(),
                                Valid.SetAngka(list.path("biayapengajuan").asDouble()),
                                Valid.SetAngka(list.path("biayasetuju").asDouble())});
                        }
                    } else {
                        for (JsonNode list : response.path("rekap").path("listsep")) {
                            if (list.path("nosepapotek").asText().contains(TCari.getText())
                                    || list.path("nosepaasal").asText().contains(TCari.getText())
                                    || list.path("nokartu").asText().contains(TCari.getText())
                                    || list.path("namapeserta").asText().contains(TCari.getText())
                                    || list.path("tglpelayanan").asText().contains(TCari.getText())) {
                                tabMode.addRow(new Object[]{list.path("nosepapotek").asText(),
                                    list.path("nosepaasal").asText(), list.path("nokartu").asText(),
                                    list.path("namapeserta").asText(), list.path("noresep").asText(),
                                    list.path("jnsobat").asText(), list.path("tglpelayanan").asText(),
                                    Valid.SetAngka(list.path("biayapengajuan").asDouble()),
                                    Valid.SetAngka(list.path("biayasetuju").asDouble())});
                            }
                        }
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
    }

    public JTable getTable() {
        return tbKamar;
    }

    private static final Logger LOG = Logger.getLogger(ApotekBPJSMonitoringKlaim.class.getName());

}
