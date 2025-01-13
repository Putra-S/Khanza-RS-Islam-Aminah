
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
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class BPJSDataPRB extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0;

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
    public BPJSDataPRB(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{"No SEP", "No Kartu", "No SRB", "Dokter", "Pasien",
            "Kode Program PRB", "Nama Program PRB", "Keterangan", "Saran", "Tanggal SRB"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbJnsPerawatan.setModel(tabMode);

        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
//GEN-BEGIN:initComponents
    internalFrame1 = new widget.InternalFrame();
    Scroll = new widget.ScrollPane();
    tbJnsPerawatan = new widget.Table();
    jPanel2 = new javax.swing.JPanel();
    panelGlass9 = new widget.panelisi();
    jLabel19 = new widget.Label();
    DTPCari1 = new widget.Tanggal();
    jLabel21 = new widget.Label();
    DTPCari2 = new widget.Tanggal();
    BtnCari = new widget.Button();
    jLabel7 = new widget.Label();
    LCount = new widget.Label();
    BtnKeluar1 = new widget.Button();
    panelGlass8 = new widget.panelisi();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setUndecorated(true);
    setResizable(false);

    internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ List Data Pasien PRB ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

    jLabel19.setText("Tanggal :");
    jLabel19.setName("jLabel19"); // NOI18N
    jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
    panelGlass9.add(jLabel19);

    DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
    DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-11-2023" }));
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
    DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-11-2023" }));
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

    jPanel2.add(panelGlass9, java.awt.BorderLayout.CENTER);

    panelGlass8.setName("panelGlass8"); // NOI18N
    panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
    panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));
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
            BPJSDataPRB dialog = new BPJSDataPRB(new javax.swing.JFrame(), true);
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

    }

    // Variables declaration - do not modify
    private widget.Button BtnCari;

    private widget.Button BtnKeluar1;

    private widget.Tanggal DTPCari1;

    private widget.Tanggal DTPCari2;

    private widget.Label LCount;
  private widget.ScrollPane Scroll;//GEN-BEGIN:variables
  private widget.InternalFrame internalFrame1;
  private widget.Label jLabel19;
  private widget.Label jLabel21;
  private widget.Label jLabel7;
  private javax.swing.JPanel jPanel2;
  private widget.panelisi panelGlass8;
  private widget.panelisi panelGlass9;
  private widget.Table tbJnsPerawatan;
  // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        // try{
        // ps=koneksi.prepareStatement(
        // "SELECT reg_periksa.tgl_registrasi,pasien.no_peserta,pasien.nm_pasien FROM
        // reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis
        // WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? group by
        // reg_periksa.tgl_registrasi");
        // try {
        // ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
        // ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
        // rs=ps.executeQuery();
        // while(rs.next()){
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/prb/tglMulai/" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "/tglAkhir/"
                    + Valid.SetTgl(DTPCari2.getSelectedItem() + "");
            System.out.println("URL : " + URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("prb").path("list");
                if (response.isArray()) {
                    i = 1;
                    for (JsonNode list : response) {
                        tabMode.addRow(new Object[]{list.path("noSEP").asText(),
                            list.path("peserta").path("noKartu").asText(), list.path("noSRB").asText(),
                            list.path("DPJP").path("nama").asText(), list.path("peserta").path("nama").asText(),
                            list.path("programPRB").path("kode").asText(),
                            list.path("programPRB").path("nama").asText(), list.path("keterangan").asText(),
                            list.path("saran").asText(), list.path("tglSRB").asText()});
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, nameNode.path("message").asText());
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
    // } catch (Exception e) {
    // System.out.println("Notif : "+e);
    // } finally{
    // if(rs!=null){
    // rs.close();
    // }
    // if(ps!=null){
    // ps.close();
    // }
    // }
    // }catch(Exception e){
    // System.out.println("Notifikasi : "+e);
    // }

    // }
    private static final Logger LOG = Logger.getLogger(BPJSDataPRB.class.getName());

}
