/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgKamar.java
*
* Created on May 23, 2010, 12:07:21 AM
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
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;

/**
 * @author dosen
 */
public class PCareNIK extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private final Properties prop = new Properties();

    private validasi Valid = new validasi();

    private sekuel Sequel = new sekuel();

    private ApiPcare api = new ApiPcare();

    private String URL = "", otorisasi, utc = "";

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
    public PCareNIK(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        Object[] row = {"", ""};
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

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(160);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            otorisasi = koneksiDB.USERPCARE() + ":" + koneksiDB.PASSPCARE() + ":095";
            URL = prop.getProperty("URLAPIPCARE") + "/peserta/nik/";
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
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Peserta BPJS Berdasarkan NIK PCare ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
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
                        + tabMode.getValueAt(r, 1).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
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
            Valid.MyReportqry("rptCariBPJSNik.jasper", "report",
                    "[ Pencarian Peserta BPJS Berdasarkan NIK/No.KTP ]",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            PCareNIK dialog = new PCareNIK(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    /**
     * @param nopeserta
     */
    public void tampil(String nopeserta) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-cons-id", koneksiDB.CONSIDAPIPCARE());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-timestamp", utc);
            headers.add("X-signature", api.getHmac());
            headers.add("X-authorization", "Basic " + Base64.encodeBase64String(otorisasi.getBytes()));
            headers.add("user_key", koneksiDB.USERKEYAPIPCARE());
            requestEntity = new HttpEntity(headers);
            System.out.println("URL : " + URL);
            // System.out.println(rest.exchange(URL, HttpMethod.GET, requestEntity,
            // String.class).getBody());
            root = mapper.readTree(
                    api.getRest().exchange(URL + nopeserta, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            // System.out.println("code : "+nameNode.path("code").asText());
            // System.out.println("message : "+nameNode.path("message").asText());
            if (nameNode.path("message").asText().equals("OK")) {
                Valid.tabelKosong(tabMode);
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                tabMode.addRow(new Object[]{"No.Kartu", ": " + response.path("noKartu").asText()});
                tabMode.addRow(new Object[]{"Nama", ": " + response.path("nama").asText()});
                tabMode.addRow(new Object[]{"Hubungan Keluarga", ": " + response.path("hubunganKeluarga").asText()});
                tabMode.addRow(new Object[]{"Jenis Kelamin", ": "
                    + response.path("sex").asText().replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan")});
                tabMode.addRow(new Object[]{"Tanggal Lahir", ": " + response.path("tglLahir").asText()});
                tabMode.addRow(new Object[]{"Mulai Aktif", ": " + response.path("tglMulaiAktif").asText()});
                tabMode.addRow(new Object[]{"Akhir Berlaku", ": " + response.path("tglAkhirBerlaku").asText()});
                tabMode.addRow(new Object[]{"Provider Umum", ":"});
                tabMode.addRow(new Object[]{"       Kode Provider",
                    ": " + response.path("kdProviderPst").path("kdProvider").asText()});
                tabMode.addRow(new Object[]{"       Nama Provider",
                    ": " + response.path("kdProviderPst").path("nmProvider").asText()});
                tabMode.addRow(new Object[]{"Provider Gigi", ":"});
                tabMode.addRow(new Object[]{"       Kode Provider",
                    ": " + response.path("kdProviderGigi").path("kdProvider").asText()});
                tabMode.addRow(new Object[]{"       Nama Provider",
                    ": " + response.path("kdProviderGigi").path("nmProvider").asText()});
                tabMode.addRow(new Object[]{"Kelas Tanggungan", ":"});
                tabMode.addRow(
                        new Object[]{"       Kode Kelas", ": " + response.path("jnsKelas").path("kode").asText()});
                tabMode.addRow(
                        new Object[]{"       Nama Kelas", ": " + response.path("jnsKelas").path("nama").asText()});
                tabMode.addRow(new Object[]{"Jenis Peserta", ":"});
                tabMode.addRow(
                        new Object[]{"       Kode Jenis", ": " + response.path("jnsPeserta").path("kode").asText()});
                tabMode.addRow(
                        new Object[]{"       Nama Jenis", ": " + response.path("jnsPeserta").path("nama").asText()});
                tabMode.addRow(new Object[]{"Golongan Darah", ": " + response.path("golDarah").asText()});
                tabMode.addRow(new Object[]{"Nomor HP", ": " + response.path("noHP").asText()});
                tabMode.addRow(new Object[]{"Nomor KTP", ": " + response.path("noKTP").asText()});
                tabMode.addRow(new Object[]{"Status", ": " + response.path("ketAktif").asText()});
                tabMode.addRow(new Object[]{"Asuransi/COB", ":"});
                tabMode.addRow(new Object[]{"       Kode Asuransi",
                    ": " + response.path("asuransi").path("kdAsuransi").asText()});
                tabMode.addRow(new Object[]{"       Nama Asuransi",
                    ": " + response.path("asuransi").path("nmAsuransi").asText()});
                tabMode.addRow(new Object[]{"       Nomer Asuransi",
                    ": " + response.path("asuransi").path("noAsuransi").asText()});
                tabMode.addRow(new Object[]{"       COB", ": " + response.path("asuransi").path("cob").asText()});
                tabMode.addRow(new Object[]{"Tunggakan", ": " + response.path("tunggakan").asText()});
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (HeadlessException | IOException | InvalidAlgorithmParameterException | InvalidKeyException
                | KeyManagementException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException | RestClientException ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server PCare terputus...!");
            } else if (ex.toString().contains("500")) {
                JOptionPane.showMessageDialog(null, "Server PCare baru ngambek broooh...!");
            } else if (ex.toString().contains("401")) {
                JOptionPane.showMessageDialog(null, "Username/Password salah. Lupa password? Wani piro...!");
            } else if (ex.toString().contains("408")) {
                JOptionPane.showMessageDialog(null, "Time out, hayati lelah baaaang...!");
            } else if (ex.toString().contains("424")) {
                JOptionPane.showMessageDialog(null, "Ambil data masternya yang bener dong coy...!");
            } else if (ex.toString().contains("412")) {
                JOptionPane.showMessageDialog(null, "Tidak sesuai kondisi. Aku, kamu end...!");
            } else if (ex.toString().contains("204")) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan...!");
            }
        }
    }

    private static final Logger LOG = Logger.getLogger(PCareNIK.class.getName());

}
