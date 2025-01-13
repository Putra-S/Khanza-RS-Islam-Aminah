package keuangan;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import simrskhanza.DlgCariCaraBayar;

public class DlgRHJS extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private Jurnal jur = new Jurnal();

    private Connection koneksi = koneksiDB.condb();

    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private DlgCariCaraBayar penjab = new DlgCariCaraBayar(null, false);

    private int i = 0, z = 0;

    double total = 0, totaljm = 0, detail_lab = 0;

    private PreparedStatement ps, psrawatjalandr, psrawatjalandrpr, psrawatjalanpr, psrawatinapdr, psrawatinapdrpr,
            psrawatinappr, psbiayaalat, psbiayasewaok, psakomodasi, psbiayasarpras, psperiksa_lab, psdetail_lab,
            psperiksa_radiologi;

    private ResultSet rs, rsrawatjalandr, rsrawatjalandrpr, rsrawatjalanpr, rsrawatinapdr, rsrawatinapdrpr,
            rsrawatinappr, rsbiayaalat, rsbiayasewaok, rsakomodasi, rsbiayasarpras, rsperiksa_lab, rsdetail_lab,
            rsperiksa_radiologi;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgRHJS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No.", "Cara Bayar", "Tgl.Tindakan", "Nama Pasien", "Tindakan Medis", "Jasa Sarana"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(135);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(280);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        kdbayar.setDocument(new batasInput((byte) 20).getKata(kdbayar));

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penjab.getTable().getSelectedRow() != -1) {
                    kdbayar.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                    nmbayar.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    prosesCari();
                }
                kdbayar.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                penjab.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penjab.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        kdbayar = new widget.TextBox();
        nmbayar = new widget.TextBox();
        btnDokter = new widget.Button();
        BtnCari = new widget.Button();
        panelisi1 = new widget.panelisi();
        chkRalan = new widget.CekBox();
        chkRanap = new widget.CekBox();
        chkOperasi = new widget.CekBox();
        chkLaborat = new widget.CekBox();
        chkRadiologi = new widget.CekBox();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Harian Jasa Sarana Rumah Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setToolTipText("");
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tgl.Tindakan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(85, 23));
        panelisi4.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl2);

        label17.setText("Cara Bayar :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(label17);

        kdbayar.setName("kdbayar"); // NOI18N
        kdbayar.setPreferredSize(new java.awt.Dimension(70, 23));
        kdbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbayarKeyPressed(evt);
            }
        });
        panelisi4.add(kdbayar);

        nmbayar.setEditable(false);
        nmbayar.setName("nmbayar"); // NOI18N
        nmbayar.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(nmbayar);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('3');
        btnDokter.setToolTipText("Alt+3");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        btnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokterKeyPressed(evt);
            }
        });
        panelisi4.add(btnDokter);

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
        panelisi4.add(BtnCari);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        chkRalan.setBorder(null);
        chkRalan.setSelected(true);
        chkRalan.setText("Ralan");
        chkRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRalan.setName("chkRalan"); // NOI18N
        chkRalan.setOpaque(false);
        chkRalan.setPreferredSize(new java.awt.Dimension(85, 30));
        chkRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRalanActionPerformed(evt);
            }
        });
        panelisi1.add(chkRalan);

        chkRanap.setBorder(null);
        chkRanap.setSelected(true);
        chkRanap.setText("Ranap");
        chkRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRanap.setName("chkRanap"); // NOI18N
        chkRanap.setOpaque(false);
        chkRanap.setPreferredSize(new java.awt.Dimension(85, 30));
        chkRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRanapActionPerformed(evt);
            }
        });
        panelisi1.add(chkRanap);

        chkOperasi.setBorder(null);
        chkOperasi.setSelected(true);
        chkOperasi.setText("Operasi");
        chkOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOperasi.setName("chkOperasi"); // NOI18N
        chkOperasi.setOpaque(false);
        chkOperasi.setPreferredSize(new java.awt.Dimension(85, 30));
        chkOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkOperasiActionPerformed(evt);
            }
        });
        panelisi1.add(chkOperasi);

        chkLaborat.setBorder(null);
        chkLaborat.setSelected(true);
        chkLaborat.setText("Laboratorium");
        chkLaborat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLaborat.setName("chkLaborat"); // NOI18N
        chkLaborat.setOpaque(false);
        chkLaborat.setPreferredSize(new java.awt.Dimension(95, 30));
        chkLaborat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLaboratActionPerformed(evt);
            }
        });
        panelisi1.add(chkLaborat);

        chkRadiologi.setBorder(null);
        chkRadiologi.setSelected(true);
        chkRadiologi.setText("Radiologi");
        chkRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRadiologi.setName("chkRadiologi"); // NOI18N
        chkRadiologi.setOpaque(false);
        chkRadiologi.setPreferredSize(new java.awt.Dimension(85, 30));
        chkRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRadiologiActionPerformed(evt);
            }
        });
        panelisi1.add(chkRadiologi);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi1.add(BtnAll);

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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
	/*
	 * private void KdKeyPressed(java.awt.event.KeyEvent evt) {
	 * Valid.pindah(evt,BtnCari,Nm); }
     */
//GEN-FIRST:event_TKdKeyPressed
    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_TKdKeyPressed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {

            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");
            int row = tabMode.getRowCount();
            for (int r = 0; r < row; r++) {
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
                                "`") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                                getalamatip() + "'",
                        "Rekap Harian Tindakan Dokter");
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
            Valid.MyReportqry("rptRHJasaSarana.jasper", "report",
                    "::[ Rekap Harian Jasa Sarana Rumah Sakit ]::",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAll, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, Tgl1);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void kdbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbayarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.
                    cariIsi("select nm_penjab from penjab where kd_pj=?",
                            nmbayar, kdbayar.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.
                    cariIsi("select nm_penjab from penjab where kd_pj=?",
                            nmbayar, kdbayar.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.
                    cariIsi("select nm_penjab from penjab where kd_pj=?",
                            nmbayar, kdbayar.getText());
            BtnAll.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnDokterActionPerformed(null);
        }
    }//GEN-LAST:event_kdbayarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        //TCari.setText("");
        kdbayar.setText("");
        nmbayar.setText("");
        prosesCari();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, kdbayar, BtnPrint);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
    penjab.isCek();
    penjab.setSize(internalFrame1.getWidth() - 20,
            internalFrame1.getHeight() - 20);
    penjab.setLocationRelativeTo(internalFrame1);
    penjab.setAlwaysOnTop(false);
    penjab.setVisible(true);
}//GEN-LAST:event_btnDokterActionPerformed

private void btnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokterKeyPressed
    //Valid.pindah(evt,DTPCari2,TCari);
}//GEN-LAST:event_btnDokterKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    prosesCari();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, kdbayar, BtnAll);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Tgl1.requestFocus();
        prosesCari();

    }//GEN-LAST:event_formWindowOpened

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnKeluar, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1, kdbayar);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void chkRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRalanActionPerformed
        prosesCari();
    }//GEN-LAST:event_chkRalanActionPerformed

    private void chkRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRadiologiActionPerformed
        prosesCari();
    }//GEN-LAST:event_chkRadiologiActionPerformed

    private void chkLaboratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLaboratActionPerformed
        prosesCari();
    }//GEN-LAST:event_chkLaboratActionPerformed

    private void chkOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkOperasiActionPerformed
        prosesCari();
    }//GEN-LAST:event_chkOperasiActionPerformed

    private void chkRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRanapActionPerformed
        prosesCari();
    }//GEN-LAST:event_chkRanapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRHJS dialog = new DlgRHJS(new javax.swing.JFrame(), true);
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
    private widget.TextBox Kd2;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnDokter;
    private widget.CekBox chkLaborat;
    private widget.CekBox chkOperasi;
    private widget.CekBox chkRadiologi;
    private widget.CekBox chkRalan;
    private widget.CekBox chkRanap;
    private widget.InternalFrame internalFrame1;
    private widget.TextBox kdbayar;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.TextBox nmbayar;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select kd_pj,png_jawab from penjab where kd_pj like ? order by png_jawab");
            try {
                ps.setString(1, "%" + kdbayar.getText() + "%");
                rs = ps.executeQuery();
                i = 1;
                totaljm = 0;
                while (rs.next()) {
                    total = 0;
                    z = 0;
                    tabMode.addRow(new Object[]{i + ".", rs.getString("png_jawab"), "", "", "", ""});
                    // rawat jalan
                    if (chkRalan.isSelected() == true) {
                        psrawatjalandr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_jl_dr.material,"
                                + "jns_perawatan.nm_perawatan,reg_periksa.tgl_registrasi "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan inner join rawat_jl_dr "
                                + "on rawat_jl_dr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_jl_dr.material>0 order by reg_periksa.tgl_registrasi,jns_perawatan.nm_perawatan");
                        psrawatjalandrpr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_jl_drpr.material,"
                                + "jns_perawatan.nm_perawatan,reg_periksa.tgl_registrasi "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan inner join rawat_jl_drpr "
                                + "on rawat_jl_drpr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_jl_drpr.material>0 order by reg_periksa.tgl_registrasi,jns_perawatan.nm_perawatan");
                        psrawatjalanpr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_jl_pr.material,"
                                + "jns_perawatan.nm_perawatan,reg_periksa.tgl_registrasi "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan inner join rawat_jl_pr "
                                + "on rawat_jl_pr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_jl_pr.material>0 order by reg_periksa.tgl_registrasi,jns_perawatan.nm_perawatan");

                        try {
                            psrawatjalandr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatjalandr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatjalandr.setString(3, rs.getString("kd_pj"));
                            rsrawatjalandr = psrawatjalandr.executeQuery();
                            rsrawatjalandr.last();

                            psrawatjalandrpr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatjalandrpr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatjalandrpr.setString(3, rs.getString("kd_pj"));
                            rsrawatjalandrpr = psrawatjalandrpr.executeQuery();
                            rsrawatjalandrpr.last();

                            psrawatjalanpr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatjalanpr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatjalanpr.setString(3, rs.getString("kd_pj"));
                            rsrawatjalanpr = psrawatjalanpr.executeQuery();
                            rsrawatjalanpr.last();

                            if ((rsrawatjalandr.getRow() > 0) || (rsrawatjalandrpr.getRow() > 0)
                                    || (rsrawatjalanpr.getRow() > 0)) {
                                z++;
                                tabMode.addRow(new Object[]{"", "", z + ". Rawat Jalan ", "", "", ""});
                            }

                            rsrawatjalandr.beforeFirst();
                            while (rsrawatjalandr.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsrawatjalandr.getString("tgl_registrasi"),
                                    rsrawatjalandr.getString("nm_pasien"), rsrawatjalandr.getString("nm_perawatan"),
                                    Valid.SetAngka(rsrawatjalandr.getDouble("material"))});
                                total += rsrawatjalandr.getDouble("material");
                            }

                            rsrawatjalandrpr.beforeFirst();
                            while (rsrawatjalandrpr.next()) {
                                tabMode.addRow(
                                        new Object[]{"", "", "     " + rsrawatjalandrpr.getString("tgl_registrasi"),
                                            rsrawatjalandrpr.getString("nm_pasien"),
                                            rsrawatjalandrpr.getString("nm_perawatan"),
                                            Valid.SetAngka(rsrawatjalandrpr.getDouble("material"))});
                                total += rsrawatjalandrpr.getDouble("material");
                            }

                            rsrawatjalanpr.beforeFirst();
                            while (rsrawatjalanpr.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsrawatjalanpr.getString("tgl_registrasi"),
                                    rsrawatjalanpr.getString("nm_pasien"), rsrawatjalanpr.getString("nm_perawatan"),
                                    Valid.SetAngka(rsrawatjalanpr.getDouble("material"))});
                                total += rsrawatjalanpr.getDouble("material");
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsrawatjalandr != null) {
                                rsrawatjalandr.close();
                            }
                            if (rsrawatjalandrpr != null) {
                                rsrawatjalandrpr.close();
                            }
                            if (rsrawatjalanpr != null) {
                                rsrawatjalanpr.close();
                            }
                            if (psrawatjalandr != null) {
                                psrawatjalandr.close();
                            }
                            if (psrawatjalandrpr != null) {
                                psrawatjalandrpr.close();
                            }
                            if (psrawatjalanpr != null) {
                                psrawatjalanpr.close();
                            }
                        }
                    }
                    // rawat inap
                    if (chkRanap.isSelected() == true) {
                        psrawatinapdr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_inap_dr.material,"
                                + "jns_perawatan_inap.nm_perawatan,rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan_inap inner join rawat_inap_dr "
                                + "on rawat_inap_dr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                + "where rawat_inap_dr.tgl_perawatan between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_inap_dr.material>0 order by rawat_inap_dr.tgl_perawatan,jns_perawatan_inap.nm_perawatan");
                        psrawatinapdrpr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_inap_drpr.material,"
                                + "jns_perawatan_inap.nm_perawatan,rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan_inap inner join rawat_inap_drpr "
                                + "on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                + "where rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_inap_drpr.material>0 order by rawat_inap_drpr.tgl_perawatan,jns_perawatan_inap.nm_perawatan");
                        psrawatinappr = koneksi.prepareStatement("select pasien.nm_pasien,rawat_inap_pr.material,"
                                + "jns_perawatan_inap.nm_perawatan,rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat "
                                + "from pasien inner join reg_periksa  "
                                + "inner join jns_perawatan_inap inner join rawat_inap_pr "
                                + "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "
                                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                + "where rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.kd_pj=? "
                                + " and rawat_inap_pr.material>0 order by rawat_inap_pr.tgl_perawatan,jns_perawatan_inap.nm_perawatan");

                        try {
                            psrawatinapdr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatinapdr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatinapdr.setString(3, rs.getString("kd_pj"));
                            rsrawatinapdr = psrawatinapdr.executeQuery();
                            rsrawatinapdr.last();

                            psrawatinapdrpr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatinapdrpr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatinapdrpr.setString(3, rs.getString("kd_pj"));
                            rsrawatinapdrpr = psrawatinapdrpr.executeQuery();
                            rsrawatinapdrpr.last();

                            psrawatinappr.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psrawatinappr.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psrawatinappr.setString(3, rs.getString("kd_pj"));
                            rsrawatinappr = psrawatinappr.executeQuery();
                            rsrawatinappr.last();

                            if ((rsrawatinapdr.getRow() > 0) || (rsrawatinapdrpr.getRow() > 0)
                                    || (rsrawatinappr.getRow() > 0)) {
                                z++;
                                tabMode.addRow(new Object[]{"", "", z + ". Rawat Inap ", "", "", ""});
                            }

                            rsrawatinapdr.beforeFirst();
                            while (rsrawatinapdr.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsrawatinapdr.getString("tgl_perawatan") + " "
                                    + rsrawatinapdr.getString("jam_rawat"),
                                    rsrawatinapdr.getString("nm_pasien"), rsrawatinapdr.getString("nm_perawatan"),
                                    Valid.SetAngka(rsrawatinapdr.getDouble("material"))});
                                total += rsrawatinapdr.getDouble("material");
                            }

                            rsrawatinapdrpr.beforeFirst();
                            while (rsrawatinapdrpr.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsrawatinapdrpr.getString("tgl_perawatan") + " "
                                    + rsrawatinapdrpr.getString("jam_rawat"),
                                    rsrawatinapdrpr.getString("nm_pasien"),
                                    rsrawatinapdrpr.getString("nm_perawatan"),
                                    Valid.SetAngka(rsrawatinapdrpr.getDouble("material"))});
                                total += rsrawatinapdrpr.getDouble("material");
                            }

                            rsrawatinappr.beforeFirst();
                            while (rsrawatinappr.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsrawatinappr.getString("tgl_perawatan") + " "
                                    + rsrawatinappr.getString("jam_rawat"),
                                    rsrawatinappr.getString("nm_pasien"), rsrawatinappr.getString("nm_perawatan"),
                                    Valid.SetAngka(rsrawatinappr.getDouble("material"))});
                                total += rsrawatinappr.getDouble("material");
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsrawatinapdr != null) {
                                rsrawatinapdr.close();
                            }
                            if (rsrawatinapdrpr != null) {
                                rsrawatinapdrpr.close();
                            }
                            if (rsrawatinappr != null) {
                                rsrawatinappr.close();
                            }
                            if (psrawatinapdr != null) {
                                psrawatinapdr.close();
                            }
                            if (psrawatinapdrpr != null) {
                                psrawatinapdrpr.close();
                            }
                            if (psrawatinappr != null) {
                                psrawatinappr.close();
                            }
                        }
                    }

                    // operasi
                    if (chkOperasi.isSelected() == true) {
                        psbiayaalat = koneksi
                                .prepareStatement("select pasien.nm_pasien,paket_operasi.nm_perawatan,operasi.biayaalat,"
                                        + "operasi.tgl_operasi from operasi inner join reg_periksa inner join pasien inner join paket_operasi "
                                        + "on operasi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and operasi.kode_paket=paket_operasi.kode_paket "
                                        + "where operasi.tgl_operasi between ? and ? and reg_periksa.kd_pj=? "
                                        + " and operasi.biayaalat>0 order by operasi.tgl_operasi,paket_operasi.nm_perawatan  ");
                        psbiayasewaok = koneksi
                                .prepareStatement("select pasien.nm_pasien,paket_operasi.nm_perawatan,operasi.biayasewaok,"
                                        + "operasi.tgl_operasi from operasi inner join reg_periksa inner join pasien inner join paket_operasi "
                                        + "on operasi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and operasi.kode_paket=paket_operasi.kode_paket "
                                        + "where operasi.tgl_operasi between ? and ? and reg_periksa.kd_pj=? "
                                        + " and operasi.biayasewaok>0 order by operasi.tgl_operasi,paket_operasi.nm_perawatan  ");
                        psakomodasi = koneksi
                                .prepareStatement("select pasien.nm_pasien,paket_operasi.nm_perawatan,operasi.akomodasi,"
                                        + "operasi.tgl_operasi from operasi inner join reg_periksa inner join pasien inner join paket_operasi "
                                        + "on operasi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and operasi.kode_paket=paket_operasi.kode_paket "
                                        + "where operasi.tgl_operasi between ? and ? and reg_periksa.kd_pj=? "
                                        + " and operasi.akomodasi>0 order by operasi.tgl_operasi,paket_operasi.nm_perawatan  ");
                        psbiayasarpras = koneksi
                                .prepareStatement("select pasien.nm_pasien,paket_operasi.nm_perawatan,operasi.biayasarpras,"
                                        + "operasi.tgl_operasi from operasi inner join reg_periksa inner join pasien inner join paket_operasi "
                                        + "on operasi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and operasi.kode_paket=paket_operasi.kode_paket "
                                        + "where operasi.tgl_operasi between ? and ? and reg_periksa.kd_pj=? "
                                        + " and operasi.biayasarpras>0 order by operasi.tgl_operasi,paket_operasi.nm_perawatan  ");
                        try {
                            psbiayaalat.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00");
                            psbiayaalat.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59");
                            psbiayaalat.setString(3, rs.getString("kd_pj"));
                            rsbiayaalat = psbiayaalat.executeQuery();
                            rsbiayaalat.last();

                            psbiayasewaok.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00");
                            psbiayasewaok.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59");
                            psbiayasewaok.setString(3, rs.getString("kd_pj"));
                            rsbiayasewaok = psbiayasewaok.executeQuery();
                            rsbiayasewaok.last();

                            psakomodasi.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00");
                            psakomodasi.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59");
                            psakomodasi.setString(3, rs.getString("kd_pj"));
                            rsakomodasi = psakomodasi.executeQuery();
                            rsakomodasi.last();

                            psbiayasarpras.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00");
                            psbiayasarpras.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59");
                            psbiayasarpras.setString(3, rs.getString("kd_pj"));
                            rsbiayasarpras = psbiayasarpras.executeQuery();
                            rsbiayasarpras.last();

                            if ((rsbiayaalat.getRow() > 0) || (rsbiayasewaok.getRow() > 0) || (rsakomodasi.getRow() > 0)
                                    || (rsbiayasarpras.getRow() > 0)) {
                                z++;
                                tabMode.addRow(new Object[]{"", "", z + ". Operasi/VK ", "", "", ""});
                            }

                            // alat
                            rsbiayaalat.beforeFirst();
                            while (rsbiayaalat.next()) {
                                tabMode.addRow(new Object[]{"", "", "     " + rsbiayaalat.getString("tgl_operasi"),
                                    rsbiayaalat.getString("nm_pasien"),
                                    rsbiayaalat.getString("nm_perawatan") + " (Alat)",
                                    Valid.SetAngka(rsbiayaalat.getDouble("biayaalat"))});
                                total += rsbiayaalat.getDouble("biayaalat");
                            }

                            // sewa ok
                            rsbiayasewaok.beforeFirst();
                            while (rsbiayasewaok.next()) {
                                tabMode.addRow(new Object[]{"", "", "     " + rsbiayasewaok.getString("tgl_operasi"),
                                    rsbiayasewaok.getString("nm_pasien"),
                                    rsbiayasewaok.getString("nm_perawatan") + " (Sewa OK/VK)",
                                    Valid.SetAngka(rsbiayasewaok.getDouble("biayasewaok"))});
                                total += rsbiayasewaok.getDouble("biayasewaok");
                            }

                            // akomodasi
                            rsakomodasi.beforeFirst();
                            while (rsakomodasi.next()) {
                                tabMode.addRow(new Object[]{"", "", "     " + rsakomodasi.getString("tgl_operasi"),
                                    rsakomodasi.getString("nm_pasien"),
                                    rsakomodasi.getString("nm_perawatan") + " (Akomodasi)",
                                    Valid.SetAngka(rsakomodasi.getDouble("akomodasi"))});
                                total += rsakomodasi.getDouble("akomodasi");
                            }

                            // Sarpras
                            rsbiayasarpras.beforeFirst();
                            while (rsbiayasarpras.next()) {
                                tabMode.addRow(new Object[]{"", "", "     " + rsbiayasarpras.getString("tgl_operasi"),
                                    rsbiayasarpras.getString("nm_pasien"),
                                    rsbiayasarpras.getString("nm_perawatan") + " (Sarpras)",
                                    Valid.SetAngka(rsbiayasarpras.getDouble("biayasarpras"))});
                                total += rsbiayasarpras.getDouble("biayasarpras");
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsbiayaalat != null) {
                                rsbiayaalat.close();
                            }
                            if (psbiayaalat != null) {
                                psbiayaalat.close();
                            }
                            if (rsbiayasewaok != null) {
                                rsbiayasewaok.close();
                            }
                            if (psbiayasewaok != null) {
                                psbiayasewaok.close();
                            }
                            if (rsakomodasi != null) {
                                rsakomodasi.close();
                            }
                            if (psakomodasi != null) {
                                psakomodasi.close();
                            }
                            if (rsbiayasarpras != null) {
                                rsbiayasarpras.close();
                            }
                            if (psbiayasarpras != null) {
                                psbiayasarpras.close();
                            }
                        }
                    }

                    // periksa lab
                    if (chkLaborat.isSelected() == true) {
                        psperiksa_lab = koneksi.prepareStatement("select periksa_lab.bagian_rs,pasien.nm_pasien,"
                                + "jns_perawatan_lab.nm_perawatan,periksa_lab.tgl_periksa,periksa_lab.jam,periksa_lab.no_rawat,periksa_lab.kd_jenis_prw "
                                + " from periksa_lab inner join reg_periksa inner join pasien inner join jns_perawatan_lab "
                                + " on periksa_lab.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                + " and periksa_lab.kd_jenis_prw=jns_perawatan_lab.kd_jenis_prw "
                                + " where periksa_lab.tgl_periksa between ? and ? and reg_periksa.kd_pj=? order by periksa_lab.tgl_periksa,periksa_lab.jam,jns_perawatan_lab.nm_perawatan  ");
                        try {
                            psperiksa_lab.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psperiksa_lab.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psperiksa_lab.setString(3, rs.getString("kd_pj"));
                            rsperiksa_lab = psperiksa_lab.executeQuery();
                            rsperiksa_lab.last();
                            if (rsperiksa_lab.getRow() > 0) {
                                z++;
                                tabMode.addRow(new Object[]{"", "", z + ". Pemeriksaan Lab ", "", "", ""});
                            }
                            rsperiksa_lab.beforeFirst();
                            while (rsperiksa_lab.next()) {
                                detail_lab = 0;

                                psdetail_lab = koneksi.prepareStatement(
                                        "select sum(detail_periksa_lab.bagian_rs) as total from detail_periksa_lab "
                                        + "inner join jns_perawatan_lab inner join template_laboratorium "
                                        + "on jns_perawatan_lab.kd_jenis_prw=detail_periksa_lab.kd_jenis_prw and "
                                        + "template_laboratorium.id_template=detail_periksa_lab.id_template where "
                                        + "detail_periksa_lab.no_rawat=? "
                                        + "and detail_periksa_lab.kd_jenis_prw=?");
                                try {
                                    psdetail_lab.setString(1, rsperiksa_lab.getString("no_rawat"));
                                    psdetail_lab.setString(2, rsperiksa_lab.getString("kd_jenis_prw"));
                                    rsdetail_lab = psdetail_lab.executeQuery();
                                    while (rsdetail_lab.next()) {
                                        detail_lab = rsdetail_lab.getDouble("total");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsdetail_lab != null) {
                                        rsdetail_lab.close();
                                    }
                                    if (psdetail_lab != null) {
                                        psdetail_lab.close();
                                    }
                                }
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsperiksa_lab.getString("tgl_periksa") + " "
                                    + rsperiksa_lab.getString("jam"),
                                    rsperiksa_lab.getString("nm_pasien"), rsperiksa_lab.getString("nm_perawatan"),
                                    Valid.SetAngka(rsperiksa_lab.getDouble("bagian_rs") + detail_lab)});
                                total = total + rsperiksa_lab.getDouble("bagian_rs") + detail_lab;
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsperiksa_lab != null) {
                                rsperiksa_lab.close();
                            }
                            if (psperiksa_lab != null) {
                                psperiksa_lab.close();
                            }
                        }
                    }

                    // radiologi
                    if (chkRadiologi.isSelected() == true) {
                        psperiksa_radiologi = koneksi
                                .prepareStatement("select periksa_radiologi.bagian_rs,pasien.nm_pasien,"
                                        + "jns_perawatan_radiologi.nm_perawatan,periksa_radiologi.tgl_periksa,periksa_radiologi.jam,"
                                        + "periksa_radiologi.no_rawat,periksa_radiologi.kd_jenis_prw "
                                        + " from periksa_radiologi inner join reg_periksa inner join pasien inner join jns_perawatan_radiologi "
                                        + " on periksa_radiologi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                                        + " and periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw "
                                        + " where periksa_radiologi.tgl_periksa between ? and ? and reg_periksa.kd_pj=? order by periksa_radiologi.tgl_periksa,periksa_radiologi.jam,jns_perawatan_radiologi.nm_perawatan  ");

                        try {
                            psperiksa_radiologi.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                            psperiksa_radiologi.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                            psperiksa_radiologi.setString(3, rs.getString("kd_pj"));
                            rsperiksa_radiologi = psperiksa_radiologi.executeQuery();
                            rsperiksa_radiologi.last();
                            if (rsperiksa_radiologi.getRow() > 0) {
                                z++;
                                tabMode.addRow(new Object[]{"", "", z + ". Pemeriksaan Radiologi ", "", "", ""});
                            }
                            rsperiksa_radiologi.beforeFirst();
                            while (rsperiksa_radiologi.next()) {
                                tabMode.addRow(new Object[]{"", "",
                                    "     " + rsperiksa_radiologi.getString("tgl_periksa") + " "
                                    + rsperiksa_radiologi.getString("jam"),
                                    rsperiksa_radiologi.getString("nm_pasien"),
                                    rsperiksa_radiologi.getString("nm_perawatan"),
                                    Valid.SetAngka(rsperiksa_radiologi.getDouble("bagian_rs"))});
                                total += rsperiksa_radiologi.getDouble("bagian_rs");
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        } finally {
                            if (rsperiksa_radiologi != null) {
                                rsperiksa_radiologi.close();
                            }
                            if (psperiksa_radiologi != null) {
                                psperiksa_radiologi.close();
                            }
                        }
                    }

                    if (total > 0) {
                        tabMode.addRow(new Object[]{"", "", "Total :", "", "", Valid.SetAngka(total)});
                    }
                    i++;
                    totaljm += total;
                }
                if (totaljm > 0) {
                    tabMode.addRow(new Object[]{">> ", "Total Jasa Sarana :", "", "", "", Valid.SetAngka(totaljm)});
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    public void isCek() {
        // BtnPrint.setEnabled(var.getharian_penjab());
    }

    private static final Logger LOG = Logger.getLogger(DlgRHJS.class.getName());

}
