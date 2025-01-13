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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author perpustakaan
 */
public class DlgDkkPenyakitTidakMenularRalan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps, ps2;

    private ResultSet rs, rs2;

    private int i = 0, hr0s7l = 0, hr0s7p = 0, hr8s28l = 0, hr8s28p = 0, hr29sth1l = 0, hr29sth1p = 0, th1s4l = 0,
            th1s4p = 0, th5s9l = 0, th5s9p = 0, th10s14l = 0, th10s14p = 0, th15s19l = 0, th15s19p = 0, th20s44l = 0,
            th20s44p = 0, th45s54l = 0, th45s54p = 0, th55s59l = 0, th55s59p = 0, th60s69l = 0, th60s69p = 0,
            th70plusl = 0, th70plusp = 0, totall = 0, totalp = 0, totaljml = 0, matil = 0, matip = 0, thr0s7l = 0,
            thr0s7p = 0, thr8s28l = 0, thr8s28p = 0, thr29sth1l = 0, thr29sth1p = 0, tth1s4l = 0, tth1s4p = 0,
            tth5s9l = 0, tth5s9p = 0, tth10s14l = 0, tth10s14p = 0, tth15s19l = 0, tth15s19p = 0, tth20s44l = 0,
            tth20s44p = 0, tth45s54l = 0, tth45s54p = 0, tth55s59l = 0, tth55s59p = 0, tth60s69l = 0, tth60s69p = 0,
            tth70plusl = 0, tth70plusp = 0, ttotall = 0, ttotalp = 0, ttotaljml = 0, tmatil = 0, tmatip = 0;

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgDkkPenyakitTidakMenularRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] rowRwJlDr = {"No.", "ICD 10", "Jenis Penyakit", "0-7 Hr(L)", "0-7 Hr(P)", "8-28 Hr(L)", "8-28 Hr(P)",
            "29 Hr-1 Th(L)", "29 Hr-1 Th(P)", "1-4 Th(L)", "1-4 Th(P)", "5-9 Th(L)", "5-9 Th(P)", "10-14 Th(L)",
            "10-14 Th(P)", "15-19 Th(L)", "15-19 Th(P)", "20-44 Th(L)", "20-44 Th(P)", "45-54 Th(L)", "45-54 Th(P)",
            "55-59 Th(L)", "55-59 Th(P)", "60-69 Th(L)", "60-69 Th(P)", ">70 Th(L)", ">70 Th(P)", "Total(L)",
            "Total(P)", "Total(Jml)", "Meninggal(L)", "Meninggal(P)"};
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

        for (i = 0; i < 32; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else {
                column.setPreferredWidth(70);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));
        try {
            ps = koneksi.prepareStatement(
                    "select diagnosa_pasien.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as nm_penyakit from diagnosa_pasien inner join penyakit "
                    + "inner join reg_periksa on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                    + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1'  and reg_periksa.tgl_registrasi between ? and ? and left(upper(diagnosa_pasien.kd_penyakit),1)<>'A' and "
                    + "diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1'  and reg_periksa.tgl_registrasi between ? and ? and left(upper(diagnosa_pasien.kd_penyakit),1)<>'B' and "
                    + "diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1'  and reg_periksa.tgl_registrasi between ? and ? and left(upper(diagnosa_pasien.kd_penyakit),1)<>'-' group by diagnosa_pasien.kd_penyakit ");
            ps2 = koneksi.prepareStatement(
                    "select concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.jk,pasien.no_rkm_medis from pasien inner join reg_periksa inner join diagnosa_pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=diagnosa_pasien.no_rawat where "
                    + "diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and reg_periksa.tgl_registrasi between ? and ? and diagnosa_pasien.kd_penyakit=? "
                    + "group by diagnosa_pasien.no_rawat");
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
        BtnExcel = new widget.Button();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Penyakit Tidak Menular di Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        BtnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/excel.png"))); // NOI18N
        BtnExcel.setMnemonic('T');
        BtnExcel.setText("Excel");
        BtnExcel.setToolTipText("Alt+T");
        BtnExcel.setName("BtnExcel"); // NOI18N
        BtnExcel.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcelActionPerformed(evt);
            }
        });
        BtnExcel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnExcelKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnExcel);

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
            param.put("thr0s7l", (thr0s7l + thr0s7p));
            param.put("thr8s28l", (thr8s28l + thr8s28p));
            param.put("thr29sth1l", (thr29sth1l + hr29sth1p));
            param.put("tth1s4l", (tth1s4l + tth1s4p));
            param.put("tth5s9l", (tth5s9l + tth5s9p));
            param.put("tth10s14l", (tth10s14l + tth10s14p));
            param.put("tanggal", Tgl2.getDate());
            param.put("ttotall", (ttotall + ttotalp));
            param.put("ttotaljml", ttotaljml);
            param.put("tmatil", (tmatil + tmatip));
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");
            for (int r = 0; r < tabMode.getRowCount(); r++) {
                Sequel.menyimpan("temporary", "'" + r + "','"
                        + tabMode.getValueAt(r, 0).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 1).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 2).toString().replaceAll(
                                "RUMAH SAKIT",
                                "                              RUMAH SAKIT") + "','"
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
                                "`") + "','"
                        + tabMode.getValueAt(r, 12).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 13).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 14).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 15).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 16).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 17).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 18).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 19).toString().replaceAll("'",
                                "`") + "','','','','','','','','','','','','','','','','','" + akses.
                                getalamatip() + "'", "Rekap Nota Pembayaran");
            }

            Valid.MyReportqry("rptDkkPenyakitTakMenularRalan.jasper", "report",
                    "::[ Penyakit Tidak Menular Rawat Jalan ]::",
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

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        Valid.exportToExcel(tbBangsal);
    }//GEN-LAST:event_BtnExcelActionPerformed

    private void BtnExcelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnExcelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnExcelKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDkkPenyakitTidakMenularRalan dialog = new DlgDkkPenyakitTidakMenularRalan(new javax.swing.JFrame(),
                    true);
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
    private widget.Button BtnExcel;
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
            ps.setString(3, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(4, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            ps.setString(5, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
            ps.setString(6, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            rs = ps.executeQuery();
            i = 1;
            thr0s7l = 0;
            thr0s7p = 0;
            thr8s28l = 0;
            thr8s28p = 0;
            thr29sth1l = 0;
            thr29sth1p = 0;
            tth1s4l = 0;
            tth1s4p = 0;
            tth5s9l = 0;
            tth5s9p = 0;
            tth10s14l = 0;
            tth10s14p = 0;
            tth15s19l = 0;
            tth15s19p = 0;
            tth20s44l = 0;
            tth20s44p = 0;
            tth45s54l = 0;
            tth45s54p = 0;
            tth55s59l = 0;
            tth55s59p = 0;
            tth60s69l = 0;
            tth60s69p = 0;
            tth70plusl = 0;
            tth70plusp = 0;
            ttotall = 0;
            ttotalp = 0;
            ttotaljml = 0;
            tmatil = 0;
            tmatip = 0;

            List<Object[]> dataList = new ArrayList<>();
            while (rs.next()) {
                hr0s7l = 0;
                hr0s7p = 0;
                hr8s28l = 0;
                hr8s28p = 0;
                hr29sth1l = 0;
                hr29sth1p = 0;
                th1s4l = 0;
                th1s4p = 0;
                th5s9l = 0;
                th5s9p = 0;
                th10s14l = 0;
                th10s14p = 0;
                th15s19l = 0;
                th15s19p = 0;
                th20s44l = 0;
                th20s44p = 0;
                th45s54l = 0;
                th45s54p = 0;
                th55s59l = 0;
                th55s59p = 0;
                th60s69l = 0;
                th60s69p = 0;
                th70plusl = 0;
                th70plusp = 0;
                totall = 0;
                totalp = 0;
                totaljml = 0;
                matil = 0;
                matip = 0;
                ps2.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps2.setString(3, rs.getString("kd_penyakit"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    switch (rs2.getString("jk")) {
                        case "L":
                            matil += Sequel.cariInteger(
                                    "select ifnull(count(pasien_mati.no_rkm_medis),0) from pasien_mati where pasien_mati.no_rkm_medis=?",
                                    rs2.getString("no_rkm_medis"));
                            tmatil += Sequel.cariInteger(
                                    "select ifnull(count(pasien_mati.no_rkm_medis),0) from pasien_mati where pasien_mati.no_rkm_medis=?",
                                    rs2.getString("no_rkm_medis"));
                            break;
                        case "P":
                            matip += Sequel.cariInteger(
                                    "select ifnull(count(pasien_mati.no_rkm_medis),0) from pasien_mati where pasien_mati.no_rkm_medis=?",
                                    rs2.getString("no_rkm_medis"));
                            tmatip += Sequel.cariInteger(
                                    "select ifnull(count(pasien_mati.no_rkm_medis),0) from pasien_mati where pasien_mati.no_rkm_medis=?",
                                    rs2.getString("no_rkm_medis"));
                            break;
                    }

                    String umur = rs2.getString("umur");
                    double umurDalamHari = 0, umurDalamBulan = 0, umurDalamTahun = 0;

                    // Periksa satuan umur dan ubah ke dalam tahun/hari
                    if (umur.contains("Hr")) {
                        umurDalamHari = Valid
                                .SetAngka(umur.replaceAll(" Hr", "").replaceAll("Hr", "").replaceAll(" ", ""));
                        // Jika umur dalam hari, misalkan setiap 30 hari dianggap sebagai
                        // 1 bulan, maka konversi ke bulan atau langsung kategori bayi
                        if (umurDalamHari >= 0 && umurDalamHari <= 7) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    hr0s7l += 1;
                                    thr0s7l += 1;
                                    break;
                                case "P":
                                    hr0s7p += 1;
                                    thr0s7p += 1;
                                    break;
                            }
                        } else if (umurDalamHari >= 8 && umurDalamHari <= 28) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    hr8s28l += 1;
                                    thr8s28l += 1;
                                    break;
                                case "P":
                                    hr8s28p += 1;
                                    thr8s28p += 1;
                                    break;
                            }
                        } else if (umurDalamHari >= 29) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    hr29sth1l += 1;
                                    thr29sth1l += 1;
                                    break;
                                case "P":
                                    hr29sth1p += 1;
                                    thr29sth1p += 1;
                                    break;
                            }
                        }
                    } else if (umur.contains("Bl")) {
                        umurDalamBulan = Valid
                                .SetAngka(umur.replaceAll(" Bl", "").replaceAll("Bl", "").replaceAll(" ", ""));

                        if (umurDalamBulan >= 1 && umurDalamBulan <= 12) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    hr29sth1l += 1;
                                    thr29sth1l += 1;
                                    break;
                                case "P":
                                    hr29sth1p += 1;
                                    thr29sth1p += 1;
                                    break;
                            }
                        }
                    } else if (umur.contains("Th")) {
                        umurDalamTahun = Valid
                                .SetAngka(umur.replaceAll(" Th", "").replaceAll("Th", "").replaceAll(" ", ""));

                        // Pengelompokan berdasarkan umur dalam tahun
                        if (umurDalamTahun >= 1 && umurDalamTahun <= 4) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th1s4l += 1;
                                    tth1s4l += 1;
                                    break;
                                case "P":
                                    th1s4p += 1;
                                    tth1s4p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 5 && umurDalamTahun <= 9) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th5s9l += 1;
                                    tth5s9l += 1;
                                    break;
                                case "P":
                                    th5s9p += 1;
                                    tth5s9p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 10 && umurDalamTahun <= 14) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th10s14l += 1;
                                    tth10s14l += 1;
                                    break;
                                case "P":
                                    th10s14p += 1;
                                    tth10s14p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 15 && umurDalamTahun <= 19) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th15s19l += 1;
                                    tth15s19l += 1;
                                    break;
                                case "P":
                                    th15s19p += 1;
                                    tth15s19p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 20 && umurDalamTahun <= 44) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th20s44l += 1;
                                    tth20s44l += 1;
                                    break;
                                case "P":
                                    th20s44p += 1;
                                    tth20s44p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 45 && umurDalamTahun <= 54) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th45s54l += 1;
                                    tth45s54l += 1;
                                    break;
                                case "P":
                                    th45s54p += 1;
                                    tth45s54p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 55 && umurDalamTahun <= 59) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th55s59l += 1;
                                    tth55s59l += 1;
                                    break;
                                case "P":
                                    th55s59p += 1;
                                    tth55s59p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun >= 60 && umurDalamTahun <= 69) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th60s69l += 1;
                                    tth60s69l += 1;
                                    break;
                                case "P":
                                    th60s69p += 1;
                                    tth60s69p += 1;
                                    break;
                            }
                        } else if (umurDalamTahun > 70) {
                            switch (rs2.getString("jk")) {
                                case "L":
                                    th70plusl += 1;
                                    tth70plusl += 1;
                                    break;
                                case "P":
                                    th70plusp += 1;
                                    tth70plusp += 1;
                                    break;
                            }
                        }
                    }

                }
                totall = hr0s7l + hr8s28l + hr29sth1l + th1s4l + th5s9l + th10s14l + th15s19l + th20s44l + th45s54l
                        + th55s59l + th60s69l + th70plusl;
                totalp = hr0s7p + hr8s28p + hr29sth1p + th1s4p + th5s9p + th10s14p + th15s19p + th20s44p + th45s54p
                        + th55s59p + th60s69p + th70plusp;
                totaljml = totall + totalp;
                ttotall = ttotall + hr0s7l + hr8s28l + hr29sth1l + th1s4l + th5s9l + th10s14l + th15s19l + th20s44l
                        + th45s54l + th55s59l + th60s69l + th70plusl;
                ttotalp = ttotalp + hr0s7p + hr8s28p + hr29sth1p + th1s4p + th5s9p + th10s14p + th15s19p + th20s44p
                        + th45s54p + th55s59p + th60s69p + th70plusp;
                ttotaljml = ttotall + ttotalp;
//				tabMode.addRow(new Object[] { i, rs.getString("kd_penyakit"), rs.getString("nm_penyakit"), hr0s7l,
//						hr0s7p, hr8s28l, hr8s28p, hr29sth1l, hr29sth1p, th1s4l, th1s4p, th5s9l, th5s9p, th10s14l,
//						th10s14p, th15s19l, th15s19p, th20s44l, th20s44p, th45s54l, th45s54p, th55s59l, th55s59p,
//						th60s69l, th60s69p, th70plusl, th70plusp, totall, totalp, totaljml, matil, matip });
                dataList.add(new Object[]{
                    i, rs.getString("kd_penyakit"), rs.getString("nm_penyakit"), hr0s7l,
                    hr0s7p, hr8s28l, hr8s28p, hr29sth1l, hr29sth1p, th1s4l, th1s4p, th5s9l, th5s9p, th10s14l,
                    th10s14p, th15s19l, th15s19p, th20s44l, th20s44p, th45s54l, th45s54p, th55s59l, th55s59p,
                    th60s69l, th60s69p, th70plusl, th70plusp, totall, totalp, totaljml, matil, matip
                });
                i++;
            }

            for (Object[] row : dataList) {
                tabMode.addRow(row);
            }
            tabMode.addRow(new Object[]{"", "", "RUMAH SAKIT", thr0s7l, thr0s7p, thr8s28l, thr8s28p, thr29sth1l,
                thr29sth1p, tth1s4l, tth1s4p, tth5s9l, tth5s9p, tth10s14l, tth10s14p, tth15s19l, tth15s19p,
                tth20s44l, tth20s44p, tth45s54l, tth45s54p, tth55s59l, tth55s59p, tth60s69l, tth60s69p, tth70plusl,
                tth70plusp, ttotall, ttotalp, ttotaljml, tmatil, tmatip});

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

    private static final Logger LOG = Logger.getLogger(DlgDkkPenyakitTidakMenularRalan.class.getName());

}
