/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */
package grafikanalisa;

import fungsi.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

/**
 *
 * @author dosen
 */
public class GrafikReportEMRDokter extends javax.swing.JDialog {

    private final Connection koneksi = koneksiDB.condb();
    private final validasi Valid = new validasi();
    private sekuel Sequel = new sekuel();
    private ResultSet rs;
    private PreparedStatement ps;
    private final DefaultTableModel tabMode;
    private double total = 0;
    private int i = 0;

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public GrafikReportEMRDokter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{"NIP", "Nama", "Jumlah Pasien", "Awal Medis IGD",
            "CPPT/SOAP Ralan", "Awal Medis Ranap", "CPPT/SOAP ranap", "Resep NonRacikan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbBangsal.setModel(tabMode);
        //tampil();
        //tbJabatan.setDefaultRenderer(Object.class, new WarnaTable(Scroll.getBackground(),Color.GREEN));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            }
        }

        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

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
        panelGlass5 = new widget.panelisi();
        jLabel33 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel34 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnPrint4 = new widget.Button();
        BtnPrint3 = new widget.Button();
        BtnPrint5 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Grafik Report EMR ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel33.setText("Periode :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass5.add(jLabel33);

        Tanggal1.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setOpaque(false);
        Tanggal1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tanggal1);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s.d");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(jLabel32);

        Tanggal2.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-10-2023" }));
        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setOpaque(false);
        Tanggal2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tanggal2);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(jLabel34);

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
        panelGlass5.add(BtnPrint);

        BtnPrint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint4.setMnemonic('G');
        BtnPrint4.setText("CPPT");
        BtnPrint4.setToolTipText("Alt+G");
        BtnPrint4.setName("BtnPrint4"); // NOI18N
        BtnPrint4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint4ActionPerformed(evt);
            }
        });
        BtnPrint4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint4KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint4);

        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint3.setMnemonic('G');
        BtnPrint3.setText("Resep");
        BtnPrint3.setToolTipText("Alt+G");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        BtnPrint3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint3KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint3);

        BtnPrint5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint5.setMnemonic('G');
        BtnPrint5.setText("Pasien");
        BtnPrint5.setToolTipText("Alt+G");
        BtnPrint5.setName("BtnPrint5"); // NOI18N
        BtnPrint5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint5ActionPerformed(evt);
            }
        });
        BtnPrint5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint5KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint5);

        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar3);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal.setName("tbBangsal"); // NOI18N
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {
            rs = koneksi.prepareStatement("SELECT dokter.nm_dokter,\n"
                    + "	(SELECT count( resep_obat.no_resep ) \n"
                    + "	FROM resep_obat \n"
                    + "	WHERE resep_obat.tgl_peresepan BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' \n"
                    + "		AND resep_obat.kd_dokter = reg_periksa.kd_dokter \n"
                    + "	AND resep_obat.no_resep IN ( SELECT resep_dokter.no_resep FROM resep_dokter )) AS RESEPNONRACIK \n"
                    + "FROM\n"
                    + "	reg_periksa\n"
                    + "	INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter \n"
                    + "WHERE\n"
                    + "	reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "'  AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' \n"
                    + "	AND reg_periksa.status_bayar = 'Sudah Bayar' \n"
                    + "	AND dokter.STATUS = '1' \n"
                    + "GROUP BY\n"
                    + "	reg_periksa.kd_dokter order by RESEPNONRACIK DESC ").executeQuery();
            while (rs.next()) {
                dcd.setValue(rs.getDouble(2), rs.getString(1) + "(" + rs.getString(2) + ")", rs.getString(1));
            }

            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        JFreeChart freeChart = ChartFactory.createBarChart("Input Resep Dokter Periode " + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tanggal2.getSelectedItem() + ""), "Bulan", "Jumlah", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Resep Dokter", freeChart);
        cf.setSize(Scroll.getWidth(), Scroll.getHeight());
        cf.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        cf.setLocationRelativeTo(Scroll);
        cf.setAlwaysOnTop(true);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_BtnPrint3ActionPerformed

    private void BtnPrint3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint3KeyPressed

    }//GEN-LAST:event_BtnPrint3KeyPressed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed

    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void BtnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint4ActionPerformed
        grafiksql2 kas = new grafiksql2("Report EMR Dokter " + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tanggal2.getSelectedItem() + ""),
                "SELECT dokter.nm_dokter, ( SELECT count( pemeriksaan_ralan.no_rawat ) as jumlah FROM pemeriksaan_ralan WHERE pemeriksaan_ralan.tgl_perawatan "
                + "BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "'and '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' AND pemeriksaan_ralan.nip = reg_periksa.kd_dokter ) AS CPPTRALANDOKTER \n"
                + "FROM reg_periksa INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter "
                + "where reg_periksa.status_bayar = 'Sudah Bayar'  AND dokter.STATUS = '1'  AND reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' "
                + "group by reg_periksa.kd_dokter order by CPPTRALANDOKTER DESC ", "NIP");
        kas.setSize(Scroll.getWidth(), Scroll.getHeight());
        kas.setModal(true);
        kas.setAlwaysOnTop(true);
        kas.setLocationRelativeTo(Scroll);
        kas.setVisible(true);
    }//GEN-LAST:event_BtnPrint4ActionPerformed

    private void BtnPrint4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint4KeyPressed

    private void BtnPrint5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint5ActionPerformed
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {
            rs = koneksi.prepareStatement("SELECT dokter.nm_dokter, count( reg_periksa.no_rawat ) AS jumlahpasien "
                    + "from reg_periksa INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter \n"
                    + "WHERE reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' group by reg_periksa.kd_dokter").executeQuery();
            while (rs.next()) {
                dpd.setValue(rs.getString(1) + "(" + rs.getString(2) + ")", rs.getDouble(2));
            }

            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        JFreeChart freeChart = ChartFactory.createPieChart("Jumlah Pasien Periode " + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tanggal2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url
        ChartFrame cf = new ChartFrame("Jumlah Pasien", freeChart);
        cf.setSize(Scroll.getWidth(), Scroll.getHeight());
        cf.setLocationRelativeTo(Scroll);
        cf.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        cf.setAlwaysOnTop(true);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_BtnPrint5ActionPerformed

    private void BtnPrint5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint5KeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "Periode " + Tanggal1.getSelectedItem() + " s.d. " + Tanggal2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Sequel.queryu("truncate table temporary_grafik");
            for (int r = 0; r < tabMode.getRowCount(); r++) {
                Sequel.menyimpan("temporary_grafik", "'0','"
                        + tabMode.getValueAt(r, 0).toString() + "','"
                        + tabMode.getValueAt(r, 1).toString() + "','"
                        + tabMode.getValueAt(r, 2).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Nota Pembayaran");
            }

            Valid.MyReport("rptMeninggalPerBulan.jasper", "report", "::[ Laporan Jumlah Pasien Meninggal Per Bulan ]::", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            GrafikReportEMRDokter dialog = new GrafikReportEMRDokter(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar3;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint3;
    private widget.Button BtnPrint4;
    private widget.Button BtnPrint5;
    private widget.ScrollPane Scroll;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "SELECT "+
                    "reg_periksa.kd_dokter,"+
                    "dokter.nm_dokter,"+
                    "count( reg_periksa.no_rawat ) AS jumlahpasien,"+
                    "(SELECT count( penilaian_medis_igd.no_rawat ) FROM penilaian_medis_igd WHERE penilaian_medis_igd.tanggal BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+
                    "AND penilaian_medis_igd.kd_dokter = reg_periksa.kd_dokter ) AS awalmedisigd,"+
                    "((SELECT	count( penilaian_medis_ranap.no_rawat )	FROM	penilaian_medis_ranap WHERE	penilaian_medis_ranap.tanggal BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+
                    "AND penilaian_medis_ranap.kd_dokter = reg_periksa.kd_dokter) +"+ 
                    "(SELECT count( penilaian_medis_ranap_kandungan.no_rawat ) FROM penilaian_medis_ranap_kandungan WHERE penilaian_medis_ranap_kandungan.tanggal BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+
                    "AND penilaian_medis_ranap_kandungan.kd_dokter = reg_periksa.kd_dokter )) AS AWRANAP,"+
                    "(SELECT count( pemeriksaan_ralan.no_rawat ) FROM pemeriksaan_ralan WHERE pemeriksaan_ralan.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+
                    "AND pemeriksaan_ralan.nip = reg_periksa.kd_dokter ) AS CPPTRALANDOKTER,"+
                    "(SELECT count( pemeriksaan_ranap.no_rawat ) FROM pemeriksaan_ranap WHERE pemeriksaan_ranap.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+
                    "AND pemeriksaan_ranap.nip = reg_periksa.kd_dokter ) AS CPPTRANAP,"+
                    "(SELECT	count( resep_obat.no_resep ) FROM	resep_obat WHERE resep_obat.tgl_peresepan BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+ 
                    "AND resep_obat.kd_dokter = reg_periksa.kd_dokter 	AND resep_obat.no_resep IN ( SELECT resep_dokter.no_resep FROM resep_dokter )) AS RESEPNONRACIK "+ 

                    "FROM "+
                    "        reg_periksa "+
                    "INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter "+ 
                    "WHERE "+
                    "        reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "'"+ 
                    "        AND reg_periksa.status_bayar = 'Sudah Bayar' "+ 
                    "        AND dokter.STATUS = '1' "+ 
                    "GROUP BY "+
                    "        reg_periksa.kd_dokter"
            );
            try {
                rs = ps.executeQuery();

                while (rs.next()) {

                    tabMode.addRow(new String[]{
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("jumlahpasien"),
                        rs.getString("awalmedisigd"),
                        rs.getString("CPPTRALANDOKTER"),
                        rs.getString("AWRANAP"),
                        rs.getString("CPPTRANAP"),
                        rs.getString("RESEPNONRACIK")

                    });
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
