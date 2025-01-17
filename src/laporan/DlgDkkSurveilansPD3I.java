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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author perpustakaan
 */
public class DlgDkkSurveilansPD3I extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0;

    private String meninggal = "", pulang = "", inap = "";

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgDkkSurveilansPD3I(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] rowRwJlDr = {"No.", "No.Rawat", "Nama", "Alamat", "L", "P", "Diagnosa", "Tgl.Masuk RS",
            "Status Imunisasi", "Masih Dirawat", "Sudah Pulang", "Meninggal"};
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

        for (i = 0; i < 12; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(40);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(87);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));
        try {
            ps = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.keluarga,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,"
                    + "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) alamat,pasien.namakeluarga,"
                    + "diagnosa_pasien.kd_penyakit,SUBSTRING(penyakit.ciri_ciri,1,80) as ciri_ciri from diagnosa_pasien inner join reg_periksa inner join pasien inner join "
                    + "kelurahan inner join kecamatan inner join kabupaten inner join penyakit "
                    + "on reg_periksa.no_rawat=diagnosa_pasien.no_rawat and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where "
                    + "diagnosa_pasien.kd_penyakit in(select penyakit_pd3i.kd_penyakit from penyakit_pd3i) and diagnosa_pasien.prioritas='1' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.tgl_registrasi order by reg_periksa.tgl_registrasi");
        } catch (Exception e) {
            System.out.println(e);
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
        BtnCari1 = new widget.Button();
        label12 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surveilans Data AFP & PD3I ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
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

        label11.setText("Tgl.Masuk :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(Tgl2);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari1);

        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(label12);

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

            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");
            for (int r = 0; r < tabMode.getRowCount(); r++) {
                Sequel.menyimpan("temporary", "'" + r + "','"
                        + tabMode.getValueAt(r, 0).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 1).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 2).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 3).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 4).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 5).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 6).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 7).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 8).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 9).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 10).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 11).toString().replaceAll("'",
                                "`") + "','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                                getalamatip() + "'", "Rekap Nota Pembayaran");
            }

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            Valid.MyReportqry("rptSurveilansPD3I.jasper", "report",
                    "::[ Surveilans PD3I ]::",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
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

private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed

    tampil();
}//GEN-LAST:event_BtnCari1ActionPerformed

private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    } else {
        Valid.pindah(evt, TKd, BtnPrint);
    }
}//GEN-LAST:event_BtnCari1KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDkkSurveilansPD3I dialog = new DlgDkkSurveilansPD3I(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ScrollPane Scroll;
    private widget.TextBox TKd;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            rs = ps.executeQuery();
            i = 1;
            while (rs.next()) {
                meninggal = "";
                pulang = "";
                inap = "";
                if (Sequel.cariIsi(
                        "select ifnull(kamar_inap.tgl_keluar,'0000-00-00') from kamar_inap where kamar_inap.no_rawat=? "
                        + "order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') "
                        + "desc limit 1",
                        rs.getString("no_rawat"))
                        .equals("0000-00-00")) {
                    inap = "Masih dirawat";
                } else if (!Sequel.cariIsi(
                        "select ifnull(kamar_inap.tgl_keluar,'0000-00-00') from kamar_inap where kamar_inap.no_rawat=? "
                        + "order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') "
                        + "desc limit 1",
                        rs.getString("no_rawat"))
                        .equals("0000-00-00")) {
                    pulang = "Sudah Pulang";
                }

                if (Sequel.cariInteger("select count(no_rkm_medis) from pasien_mati where no_rkm_medis=?",
                        rs.getString("no_rkm_medis")) > 0) {
                    meninggal = "Meninggal";
                    inap = "";
                    pulang = "";
                }
                switch (rs.getString("jk")) {
                    case "P":
                        tabMode.addRow(new Object[]{i + "", rs.getString("no_rawat"),
                            "Penderita : " + rs.getString("nm_pasien") + ", " + rs.getString("keluarga") + " : "
                            + rs.getString("namakeluarga"),
                            rs.getString("alamat"), "", rs.getString("umur"),
                            rs.getString("kd_penyakit") + ", " + rs.getString("ciri_ciri"),
                            rs.getString("tgl_registrasi"), "", inap, pulang, meninggal});
                        break;
                    case "L":
                        tabMode.addRow(new Object[]{i + "", rs.getString("no_rawat"),
                            "Penderita : " + rs.getString("nm_pasien") + ", " + rs.getString("keluarga") + " : "
                            + rs.getString("namakeluarga"),
                            rs.getString("alamat"), rs.getString("umur"), "",
                            rs.getString("kd_penyakit") + ", " + rs.getString("ciri_ciri"),
                            rs.getString("tgl_registrasi"), "", inap, pulang, meninggal});
                        break;
                }
                i++;
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

    private static final Logger LOG = Logger.getLogger(DlgDkkSurveilansPD3I.class.getName());

}
