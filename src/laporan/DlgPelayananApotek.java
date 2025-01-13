/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgLhtBiaya.java
*
* Created on 12 Jul 10, 16:21:34
 */
package laporan;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author perpustakaan
 */
public class DlgPelayananApotek extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0, limabelas = 0, tigapuluh = 0, satujam = 0, lebihsatujam = 0, limabelas2 = 0, tigapuluh2 = 0,
            satujam2 = 0, lebihsatujam2 = 0, limabelas3 = 0, tigapuluh3 = 0, satujam3 = 0, lebihsatujam3 = 0;

    private double lamajam = 0, lamajam2 = 0, lamajam3 = 0;

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgPelayananApotek(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] rowRwJlDr = {"No.", "No.RM", "Nama Pasien", "Dokter", "Poli", "Jam Peresepan", "Jam Validasi",
            "Jam Penyerahan", "Peresepan-Validasi", "Validasi-Penyerahan", "Peresepan-Penyerahan"};
        tabMode = new DefaultTableModel(null, rowRwJlDr) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbBangsal.setModel(tabMode);
        // tbBangsal.setDefaultRenderer(Object.class, new
        // WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(175);
            } else if (i == 3) {
                column.setPreferredWidth(175);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(103);
            } else if (i == 9) {
                column.setPreferredWidth(109);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Lama Pelayanan Apotek ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

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
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

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
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            param.put("limabelas", "" + limabelas);
            param.put("rata", "" + Valid.SetAngka6(lamajam / (i - 1)));
            param.put("tigapuluh", "" + tigapuluh);
            param.put("satujam", "" + satujam);
            param.put("lebihsatujam", "" + lebihsatujam);

            Sequel.queryu("delete from temporary_resep where temp37='" + akses.
                    getalamatip() + "'");

            for (int i = 0; i < tabMode.getRowCount(); i++) {
                Sequel.menyimpan("temporary_resep",
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                        38, new String[]{
                            "" + i, tabMode.getValueAt(i, 0).toString(),
                            tabMode.getValueAt(i, 1).toString(), tabMode.
                            getValueAt(i, 2).toString(),
                            tabMode.getValueAt(i, 3).toString(), tabMode.
                            getValueAt(i, 4).toString(), tabMode.
                            getValueAt(i, 5).toString(),
                            tabMode.getValueAt(i, 6).toString(), tabMode.
                            getValueAt(i, 7).toString(), tabMode.
                            getValueAt(i, 8).toString(),
                            tabMode.getValueAt(i, 9).toString(), tabMode.
                            getValueAt(i, 10).toString(), "", "", "", "", "", "",
                            "", "", "",
                            "", "", "", "", "", "", "", "", "", "", "", "", "",
                            "", "", "", akses.getalamatip()
                        });
            }
            Valid.MyReportqry("rptPelayananApotek.jasper", "report",
                    "::[ Laporan Data Pelayanan Apotek ]::",
                    "select * from temporary_resep where temporary_resep.temp37='" + akses.
                            getalamatip() + "' order by temporary_resep.no",
                    param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            //Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnKeluar, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    } else {
        Valid.pindah(evt, TKd, BtnPrint);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {

        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tampil();

    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPelayananApotek dialog = new DlgPelayananApotek(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            limabelas = 0;
            tigapuluh = 0;
            satujam = 0;
            lebihsatujam = 0;
            lamajam = 0;
            limabelas2 = 0;
            tigapuluh2 = 0;
            satujam2 = 0;
            lebihsatujam2 = 0;
            lamajam2 = 0;
            limabelas3 = 0;
            tigapuluh3 = 0;
            satujam3 = 0;
            lebihsatujam3 = 0;
            lamajam3 = 0;
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,dokter.nm_dokter,poliklinik.nm_poli,"
                    + "resep_obat.tgl_peresepan,resep_obat.jam_peresepan,resep_obat.tgl_perawatan,resep_obat.jam,"
                    + "resep_obat.tgl_penyerahan,resep_obat.jam_penyerahan,"
                    + "round((TIME_TO_SEC(concat(resep_obat.tgl_perawatan,' ',resep_obat.jam))-TIME_TO_SEC(concat(resep_obat.tgl_peresepan,' ',resep_obat.jam_peresepan)))/60,2) as durasivalidasi,"
                    + "round((TIME_TO_SEC(concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan))-TIME_TO_SEC(concat(resep_obat.tgl_perawatan,' ',resep_obat.jam)))/60,2) as durasipenyerahan,"
                    + "round((TIME_TO_SEC(concat(resep_obat.tgl_penyerahan,' ',resep_obat.jam_penyerahan))-TIME_TO_SEC(concat(resep_obat.tgl_peresepan,' ',resep_obat.jam_peresepan)))/60,2) as durasipelayanan "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join resep_obat "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "and reg_periksa.no_rawat=resep_obat.no_rawat "
                    + "where resep_obat.tgl_peresepan<>'0000-00-00' and resep_obat.tgl_penyerahan<>'0000-00-00' and resep_obat.tgl_perawatan<>'0000-00-00' and resep_obat.tgl_peresepan between ? and ? "
                    + "and (poliklinik.nm_poli like ? or dokter.nm_dokter like ? or reg_periksa.no_rkm_medis like ? or pasien.nm_pasien like ?)  "
                    + "order by resep_obat.tgl_peresepan,resep_obat.jam_peresepan");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{i, rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("nm_dokter"), rs.getString("nm_poli"),
                        rs.getString("tgl_peresepan") + " " + rs.getString("jam_peresepan"),
                        rs.getString("tgl_perawatan") + " " + rs.getString("jam"),
                        rs.getString("tgl_penyerahan") + " " + rs.getString("jam_penyerahan"),
                        rs.getString("durasivalidasi"), rs.getString("durasipenyerahan"),
                        rs.getString("durasipelayanan")});
                    i++;
                    lamajam += rs.getDouble("durasivalidasi");
                    if (rs.getDouble("durasivalidasi") <= 15) {
                        limabelas++;
                    } else if ((rs.getDouble("durasivalidasi") > 15) && (rs.getDouble("durasivalidasi") <= 30)) {
                        tigapuluh++;
                    } else if ((rs.getDouble("durasivalidasi") > 30) && (rs.getDouble("durasivalidasi") <= 60)) {
                        satujam++;
                    } else if (rs.getDouble("durasivalidasi") > 60) {
                        lebihsatujam++;
                    }
                    lamajam2 += rs.getDouble("durasipenyerahan");
                    if (rs.getDouble("durasipenyerahan") <= 15) {
                        limabelas2++;
                    } else if ((rs.getDouble("durasipenyerahan") > 15) && (rs.getDouble("durasipenyerahan") <= 30)) {
                        tigapuluh2++;
                    } else if ((rs.getDouble("durasipenyerahan") > 30) && (rs.getDouble("durasipenyerahan") <= 60)) {
                        satujam2++;
                    } else if (rs.getDouble("durasipenyerahan") > 60) {
                        lebihsatujam2++;
                    }
                    lamajam3 += rs.getDouble("durasipelayanan");
                    if (rs.getDouble("durasipelayanan") <= 15) {
                        limabelas3++;
                    } else if ((rs.getDouble("durasipelayanan") > 15) && (rs.getDouble("durasipelayanan") <= 30)) {
                        tigapuluh3++;
                    } else if ((rs.getDouble("durasipelayanan") > 30) && (rs.getDouble("durasipelayanan") <= 60)) {
                        satujam3++;
                    } else if (rs.getDouble("durasipelayanan") > 60) {
                        lebihsatujam3++;
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

            if (lamajam > 0) {
                tabMode.addRow(new Object[]{"", "", "Rata-rata (Menit)", ": ", "", "", "", "",
                    "" + Valid.SetAngka6(lamajam / (i - 1)), "" + Valid.SetAngka6(lamajam2 / (i - 1)),
                    "" + Valid.SetAngka6(lamajam3 / (i - 1))});
                tabMode.addRow(new Object[]{"", "", "0 - 15 Menit", ": ", "", "", "", "", "" + limabelas,
                    "" + limabelas2, "" + limabelas3});
                tabMode.addRow(new Object[]{"", "", ">15 - <=30 Menit", ": ", "", "", "", "", "" + tigapuluh,
                    "" + tigapuluh2, "" + tigapuluh3});
                tabMode.addRow(new Object[]{"", "", ">30 - <=60 Menit", ": ", "", "", "", "", "" + satujam,
                    "" + satujam2, "" + satujam3});
                tabMode.addRow(new Object[]{"", "", ">60 Menit", ": ", "", "", "", "", "" + lebihsatujam,
                    "" + lebihsatujam2, "" + lebihsatujam3});
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getData() {
        int row = tbBangsal.getSelectedRow();
        if (row != -1) {
            TKd.setText(tabMode.getValueAt(row, 0).toString());
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgPelayananApotek.class.getName());

}
