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
public class BPJSCekKlaimJasaRaharja extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private validasi Valid = new validasi();

    private sekuel Sequel = new sekuel();

    private int i = 0;

    private ApiBPJS api = new ApiBPJS();

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
    public BPJSCekKlaimJasaRaharja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        Object[] row = {"No", "No.SEP", "Tgl.SEP", "Tgl.Pulang", "No.R.M.", "Jenis Pelayanan", "Diagnosa", "No.Kartu",
            "Nama Pasien", "Tgl.Kejadian", "No.Register", "Status Dijamin", "Status Dikirim", "Biaya Dijamin",
            "Plafon", "Jumlah Dibayar", "Result", "Status"};
        tabMode = new DefaultTableModel(null, row) {
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

        for (i = 0; i < 18; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(160);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(125);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(70);
            } else if (i == 17) {
                column.setPreferredWidth(60);
            }
        }

        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            link = koneksiDB.URLAPIBPJS();
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
        jLabel18 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Klaim Jasa Raharja VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel18.setText("Tanggal :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel18);

        Tanggal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setOpaque(false);
        Tanggal1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(Tanggal1);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("s.d.");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel19);

        Tanggal2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setOpaque(false);
        Tanggal2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(Tanggal2);

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

        jLabel17.setAutoscrolls(true);
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass6.add(jLabel17);

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
                        + tabMode.getValueAt(r, 4).toString() + "','"
                        + tabMode.getValueAt(r, 5).toString() + "','"
                        + tabMode.getValueAt(r, 6).toString() + "','"
                        + tabMode.getValueAt(r, 7).toString() + "','"
                        + tabMode.getValueAt(r, 8).toString() + "','"
                        + tabMode.getValueAt(r, 9).toString() + "','"
                        + tabMode.getValueAt(r, 10).toString() + "','"
                        + tabMode.getValueAt(r, 11).toString() + "','"
                        + tabMode.getValueAt(r, 12).toString() + "','"
                        + tabMode.getValueAt(r, 13).toString() + "','"
                        + tabMode.getValueAt(r, 14).toString() + "','"
                        + tabMode.getValueAt(r, 15).toString() + "','"
                        + tabMode.getValueAt(r, 16).toString() + "','','','','','','','','','','','','','','','','','','','','" + akses.
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
            Valid.MyReportqry("rptCariBPJSKlaimJasaRaharja.jasper", "report",
                    "[ Data Klaim Jasa Raharja ]",
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
            Valid.pindah(evt, Tanggal2, BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekKlaimJasaRaharja dialog = new BPJSCekKlaimJasaRaharja(new javax.swing.JFrame(), true);
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
    private widget.ScrollPane Scroll;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
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
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/monitoring/JasaRaharja/JnsPelayanan/2/tglMulai/"
                    + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "/tglAkhir/"
                    + Valid.SetTgl(Tanggal2.getSelectedItem() + "");
            System.out.println("URL : " + URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("jaminan");
                // response = root.path("response").path("rujukan");
                if (response.isArray()) {
                    i = 1;
                    for (JsonNode list : response) {
                        tabMode.addRow(new Object[]{i + "", list.path("sep").path("noSEP").asText(),
                            list.path("sep").path("tglSEP").asText(), list.path("sep").path("tglPlgSEP").asText(),
                            list.path("sep").path("noMr").asText(),
                            list.path("sep")
                            .path("jnsPelayanan")
                            .asText()
                            .replaceAll("1", "Ranap")
                            .replaceAll("2", "Ralan"),
                            list.path("sep").path("poli").asText(), list.path("sep").path("diagnosa").asText(),
                            list.path("sep").path("peserta").path("noKartu").asText(),
                            list.path("sep").path("peserta").path("nama").asText(),
                            list.path("jasaRaharja").path("tglKejadian").asText(),
                            list.path("jasaRaharja").path("noRegister").asText(),
                            list.path("jasaRaharja").path("ketStatusDijamin").asText(),
                            list.path("jasaRaharja").path("ketStatusDikirim").asText(),
                            Valid.SetAngka(list.path("jasaRaharja").path("biayaDijamin").asDouble()),
                            Valid.SetAngka(list.path("jasaRaharja").path("plafon").asDouble()),
                            Valid.SetAngka(list.path("jasaRaharja").path("jmlDibayar").asDouble()),
                            list.path("jasaRaharja").path("resultsJasaRaharja").asText(), "Ralan"});
                        i++;
                    }
                }
            }

            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/monitoring/JasaRaharja/JnsPelayanan/1/tglMulai/"
                    + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "/tglAkhir/"
                    + Valid.SetTgl(Tanggal2.getSelectedItem() + "");
            System.out.println("URL : " + URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("jaminan");
                // response = root.path("response").path("rujukan");
                if (response.isArray()) {
                    i = 1;
                    for (JsonNode list : response) {
                        tabMode.addRow(new Object[]{i + "", list.path("sep").path("noSEP").asText(),
                            list.path("sep").path("tglSEP").asText(), list.path("sep").path("tglPlgSEP").asText(),
                            list.path("sep").path("noMr").asText(),
                            list.path("sep")
                            .path("jnsPelayanan")
                            .asText()
                            .replaceAll("1", "Ranap")
                            .replaceAll("2", "Ralan"),
                            list.path("sep").path("poli").asText(), list.path("sep").path("diagnosa").asText(),
                            list.path("sep").path("peserta").path("noKartu").asText(),
                            list.path("sep").path("peserta").path("nama").asText(),
                            list.path("jasaRaharja").path("tglKejadian").asText(),
                            list.path("jasaRaharja").path("noRegister").asText(),
                            list.path("jasaRaharja").path("ketStatusDijamin").asText(),
                            list.path("jasaRaharja").path("ketStatusDikirim").asText(),
                            Valid.SetAngka(list.path("jasaRaharja").path("biayaDijamin").asDouble()),
                            Valid.SetAngka(list.path("jasaRaharja").path("plafon").asDouble()),
                            Valid.SetAngka(list.path("jasaRaharja").path("jmlDibayar").asDouble()),
                            list.path("jasaRaharja").path("resultsJasaRaharja").asText(), "Ranap"});
                        i++;
                    }
                }
            }
        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | KeyManagementException
                | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException
                | RestClientException ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private static final Logger LOG = Logger.getLogger(BPJSCekKlaimJasaRaharja.class.getName());

}
