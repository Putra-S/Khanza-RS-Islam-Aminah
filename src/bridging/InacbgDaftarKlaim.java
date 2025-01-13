package bridging;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author dosen
 */
public class InacbgDaftarKlaim extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps, ps1;

    private ResultSet rs, rs1;

    private Date tgl = new Date();

    private String jnsKlaim = "", nik = "", sep = "",tglSep = "", nilaiRWT = "", kd_payor = "", jnsRawat = "", tsep="", statusklaim="";

    private int i = 0, coder = 0;
    private double grouping = 0, finish = 0, kirimonline = 0;
    private InacbgPengajuanKlaim ajukan = new InacbgPengajuanKlaim(null, false);

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public InacbgDaftarKlaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 10);
        setSize(459, 539);

        Object[] row = {"No SEP","Tgl SEP", "No.Rawat", "Tgl Registrasi", "Jam Reg", "Dokter", "No Rekam Medis",
                        "Pasien","Jenis Kelamin","Umur","Poliklinik","Cara Bayar","Status Daftar",
                        "Status Lanjut","Status Klaim"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }

            Class[] types = new Class[]{java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbINACBG.setModel(tabMode);
        tbINACBG.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbINACBG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbINACBG.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(95);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(85);
            }
        }
        tbINACBG.setDefaultRenderer(Object.class, new WarnaTable());

        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari1));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        tampilKLAIM();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        tampilKLAIM();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari1.getText().length() > 2) {
                        tampilKLAIM();
                    }
                }
            });
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
        tbINACBG = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        nosep_klaim = new widget.TextBox();
        jLabel7 = new widget.Label();
        noRawat = new widget.TextBox();
        panelGlass10 = new widget.panelisi();
        jLabel35 = new widget.Label();
        tglA = new widget.Tanggal();
        jLabel18 = new widget.Label();
        tglB = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbLimit = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnProses = new widget.Button();
        jLabel9 = new widget.Label();
        LCount1 = new widget.Label();
        BtnKeluar1 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbINACBG.setAutoCreateRowSorter(true);
        tbINACBG.setToolTipText("");
        tbINACBG.setName("tbINACBG"); // NOI18N
        tbINACBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbINACBGMouseClicked(evt);
            }
        });
        tbINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbINACBGKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbINACBG);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("No. SEP/No. Klaim : ");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel6);

        nosep_klaim.setEditable(false);
        nosep_klaim.setForeground(new java.awt.Color(0, 0, 0));
        nosep_klaim.setName("nosep_klaim"); // NOI18N
        nosep_klaim.setPreferredSize(new java.awt.Dimension(143, 23));
        panelGlass9.add(nosep_klaim);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. Rawat : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel7);

        noRawat.setEditable(false);
        noRawat.setForeground(new java.awt.Color(0, 0, 0));
        noRawat.setName("noRawat"); // NOI18N
        noRawat.setPreferredSize(new java.awt.Dimension(135, 23));
        panelGlass9.add(noRawat);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tanggal : ");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel35);

        tglA.setEditable(false);
        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-01-2025" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglA);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("s.d");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel18);

        tglB.setEditable(false);
        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-01-2025" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(tglB);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Limit Data : ");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel11);

        cmbLimit.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "1000", "Semuanya" }));
        cmbLimit.setName("cmbLimit"); // NOI18N
        panelGlass10.add(cmbLimit);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jns. Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "JALAN", "INAP" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(cmbJnsRawat);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(23, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(23, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnAll1);

        BtnProses.setForeground(new java.awt.Color(0, 0, 0));
        BtnProses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/New.png"))); // NOI18N
        BtnProses.setMnemonic('1');
        BtnProses.setText("Proses Klaim");
        BtnProses.setToolTipText("Alt+1");
        BtnProses.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnProses.setName("BtnProses"); // NOI18N
        BtnProses.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProsesActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnProses);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('4');
        BtnKeluar1.setToolTipText("Alt+4");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(23, 23));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProsesActionPerformed
        if (noRawat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data pasien berdasarkan No. Rawat belum dipilih,...!!");
        } else {
            if (jnsRawat.equals("Rawat Jalan")) {
                if (Sequel.cariInteger("SELECT COUNT(-1) FROM bridging_sep WHERE nomr='" + Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + noRawat.getText() + "'") + "' "
                        + "AND tglsep='" + tglSep + "' AND jnspelayanan='1'") > 0) {
                    i = JOptionPane.showConfirmDialog(null, "Pasien telah memiliki SEP Rawat Jalan & Rawat Inap dihari yang sama, Apakah proses klaim akan tetap dilanjutkan,...!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (i == JOptionPane.YES_OPTION) {
                        klaimBaruJKN();
                    }
                } else {
                    klaimBaruJKN();
                }
            } else {
                klaimBaruJKN();
            }
        }
}//GEN-LAST:event_BtnProsesActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilKLAIM();
    }//GEN-LAST:event_formWindowOpened

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilKLAIM();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        nosep_klaim.setText("");
        noRawat.setText("");
        cmbJnsRawat.setSelectedIndex(0);
        TCari1.setText("");
        tampilKLAIM();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void tbINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbINACBGKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbINACBGKeyPressed

    private void tbINACBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbINACBGMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbINACBGMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InacbgDaftarKlaim dialog = new InacbgDaftarKlaim(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll1;
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar1;
    public widget.Button BtnProses;
    private widget.Label LCount1;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari1;
    public widget.ComboBox cmbJnsRawat;
    public widget.ComboBox cmbLimit;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel18;
    private widget.Label jLabel23;
    private widget.Label jLabel35;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.TextBox noRawat;
    private widget.TextBox nosep_klaim;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private widget.Table tbINACBG;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    // End of variables declaration//GEN-END:variables

    public JTable getTable() {
        return tbINACBG;
    }

    public void isCek() {
        BtnProses.setEnabled(false);
        coder = Sequel.cariInteger("SELECT COUNT(inacbg_coder_nik.nik) as nik FROM inacbg_coder_nik WHERE inacbg_coder_nik.nik=?", akses.getkode());
        if(akses.getkode().equals("Admin Utama")){
            BtnProses.setEnabled(true);
        }else{
            if(coder == 1){
                BtnProses.setEnabled(true);
            }else{
                BtnProses.setEnabled(false);
            }
        }
    }

    public void emptTeks() {
        noRawat.setText("");
        nosep_klaim.setText("");
        TCari1.setText("");
        tglA.setDate(new Date());
        tglB.setDate(new Date());
        cmbJnsRawat.setSelectedIndex(0);
    }

    private void tampilKLAIM() {
        nilaiRWT = "";
        Valid.tabelKosong(tabMode);
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            nilaiRWT = "%Ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            nilaiRWT = "%Ralan%";
        } else {
            nilaiRWT = "%%";
        }

        try {
            if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,	pasien.nm_pasien,IF(pasien.jk = 'L', 'Laki-Laki', 'Perempuan') AS jk,pasien.umur,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj," +
                        "reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,reg_periksa.status_lanjut,penjab.png_jawab " +
                        "FROM reg_periksa INNER JOIN dokter INNER JOIN pasien INNER JOIN poliklinik INNER JOIN penjab ON reg_periksa.kd_dokter = dokter.kd_dokter " +
                        "AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.kd_pj = penjab.kd_pj AND reg_periksa.kd_poli = poliklinik.kd_poli " +
                        "WHERE reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_reg LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rawat LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.tgl_registrasi LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.kd_dokter LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND dokter.nm_dokter LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.stts_daftar LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND pasien.nm_pasien LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND poliklinik.nm_poli LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND penjab.png_jawab LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "ORDER BY reg_periksa.tgl_registrasi,reg_periksa.jam_reg DESC limit "+cmbLimit.getSelectedItem().toString());

            } else {
                ps = koneksi.prepareStatement(
                        "SELECT reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,	pasien.nm_pasien,IF(pasien.jk = 'L', 'Laki-Laki', 'Perempuan') AS jk,pasien.umur,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj," +
                        "reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,reg_periksa.status_lanjut,penjab.png_jawab " +
                        "FROM reg_periksa INNER JOIN dokter INNER JOIN pasien INNER JOIN poliklinik INNER JOIN penjab ON reg_periksa.kd_dokter = dokter.kd_dokter " +
                        "AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND reg_periksa.kd_pj = penjab.kd_pj AND reg_periksa.kd_poli = poliklinik.kd_poli " +
                        "WHERE reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_reg LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rawat LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.tgl_registrasi LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.kd_dokter LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND dokter.nm_dokter LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND reg_periksa.stts_daftar LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND pasien.nm_pasien LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND poliklinik.nm_poli LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "OR reg_periksa.stts <> 'Batal' AND penjab.kd_pj = 'BPJ' AND tgl_registrasi BETWEEN ? AND ? AND penjab.png_jawab LIKE ? AND reg_periksa.status_lanjut LIKE '"+nilaiRWT+"' " +
                        "ORDER BY reg_periksa.tgl_registrasi,reg_periksa.jam_reg DESC ");
            }
            try {
                if (!cmbLimit.getSelectedItem().equals("Semuanya")) {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");
                    ps.setString(28, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(29, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(30, "%" + TCari1.getText().trim() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari1.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari1.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari1.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari1.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari1.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari1.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari1.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari1.getText().trim() + "%");
                    ps.setString(25, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(26, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(27, "%" + TCari1.getText().trim() + "%");
                    ps.setString(28, Valid.SetTgl(tglA.getSelectedItem() + ""));
                    ps.setString(29, Valid.SetTgl(tglB.getSelectedItem() + ""));
                    ps.setString(30, "%" + TCari1.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    sep = Sequel.cariIsi("select IF(COUNT(bridging_sep.no_sep)=0,'-',bridging_sep.no_sep) as no_sep from bridging_sep where bridging_sep.no_rawat=?", rs.getString("no_rawat"));
                    tsep = Sequel.cariIsi("select IF(COUNT(bridging_sep.no_sep)=0,'00-00-0000',bridging_sep.tglsep) as tglsep from bridging_sep where bridging_sep.no_sep=?", sep);
                    grouping = Sequel.cariIsiAngka("select count(eklaim_grouping.no_sep) as jml from eklaim_grouping where eklaim_grouping.no_sep =?",sep);
                    finish = Sequel.cariIsiAngka("select count(eklaim_final.no_sep) as jml from eklaim_final where eklaim_final.no_sep =?",sep);
                    kirimonline = Sequel.cariIsiAngka("select count(eklaim_online_status.no_sep) as jml from eklaim_online_status where eklaim_online_status.no_sep =?",sep);
                    if(grouping != 0 && finish == 0 && kirimonline == 0){
                        statusklaim = "Grouping";
                    } else if(grouping != 0 && finish != 0 && kirimonline == 0){
                        statusklaim = "Final";
                    }else if(grouping != 0 && finish != 0 && kirimonline != 0){
                        statusklaim = "Kirim Online";
                    } else {
                        statusklaim = "Belum";
                    }
                    tabMode.addRow(new Object[]{sep, tsep, rs.getString("no_rawat"),
                        rs.getString("tgl_registrasi"), rs.getString("jam_reg"), rs.getString("nm_dokter"),
                        rs.getString("no_rkm_medis"), rs.getString("nm_pasien"), rs.getString("jk"),
                        rs.getString("umur"), rs.getString("nm_poli"), rs.getString("png_jawab"),
                        rs.getString("stts_daftar"), rs.getString("status_lanjut"), statusklaim});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode.getRowCount());
    }

    private void getData() {
        if (tbINACBG.getSelectedRow() != -1) {
            noRawat.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 2).toString());
            nosep_klaim.setText(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 0).toString());
            tglSep = tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 1).toString();
            if(tbINACBG.getValueAt(tbINACBG.getSelectedRow(), 11).toString().equals("BPJS Kesehatan")){
               jnsKlaim = "JKN";
               kd_payor = "3";
            }
            
        }
    }

    private void klaimBaruJKN() {
        if (akses.getkode().equals("Admin Utama")) {
            ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            ajukan.setLocationRelativeTo(internalFrame1);
            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, tglSep);
            ajukan.tarifRS(noRawat.getText());
            ajukan.TabRawat.setEnabled(true);
            ajukan.TabRawat.setSelectedIndex(0);
            ajukan.TabDiagnosa1.setSelectedIndex(0);
            ajukan.TabProsedur1.setSelectedIndex(0);
            ajukan.emptTeksLAINNYA();
            ajukan.setVisible(true);
        } else {
            ajukan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            ajukan.setLocationRelativeTo(internalFrame1);
            ajukan.setKlaim(noRawat.getText(), nosep_klaim.getText(), jnsKlaim, kd_payor, tglSep);
            ajukan.tarifRS(noRawat.getText());
            ajukan.TabRawat.setEnabledAt(1, false);
            ajukan.TabRawat.setEnabledAt(0, true);
            ajukan.TabRawat.setSelectedIndex(0);
            ajukan.TabDiagnosa1.setSelectedIndex(0);
            ajukan.TabProsedur1.setSelectedIndex(0);
            ajukan.emptTeksLAINNYA();
            ajukan.setVisible(true);
        }
    }

}
