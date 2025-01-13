package keuangan;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import inventory.DlgBarang;
import inventory.DlgCariGolongan;
import inventory.DlgCariJenis;
import inventory.DlgCariKategori;
import java.awt.Cursor;
import java.awt.Dimension;
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
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariCaraBayar;
import simrskhanza.DlgCariPoli;

public class DlgLaporanAnalisa extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private PreparedStatement pspoli, pspenjab, psobat, pstindakan;

    private ResultSet rspoli, rspenjab, rsobat, rstindakan;

    private DlgCariPoli poli = new DlgCariPoli(null, false);

    private DlgBarang barang = new DlgBarang(null, false);

    private DlgCariCaraBayar penjab = new DlgCariCaraBayar(null, false);

    private DlgCariJenis jenis = new DlgCariJenis(null, false);

    private DlgCariKategori kategori = new DlgCariKategori(null, false);

    private DlgCariGolongan golongan = new DlgCariGolongan(null, false);

    private DlgCariBangsal asalstok = new DlgCariBangsal(null, false);

    private int i = 0, a = 0;

    private double jmlbiaya = 0, ttlbiaya = 0, jmlembalase = 0, ttlembalase = 0, jmltuslah = 0, ttltuslah = 0,
            jmltotal = 0, ttltotal = 0;

    private String dokterranap = "";

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgLaporanAnalisa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No.", "Poliklinik", "Jml", "Nama Obat", "Biaya Obat", "Embalase", "Tuslah", "Total"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(230);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else {
                column.setPreferredWidth(80);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

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
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                }
                kdpenjab.requestFocus();
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

        try {
            pspenjab = koneksi.prepareStatement("select kd_pj,png_jawab from penjab where kd_pj like ?");
        } catch (Exception e) {
            System.out.println(e);
        }

        ChkInput.setSelected(false);
        isForm();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        kdasal = new widget.TextBox();
        kdpenjab = new widget.TextBox();
        kdpoli = new widget.TextBox();
        kdjenis = new widget.TextBox();
        kdkategori = new widget.TextBox();
        kdgolongan = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label19 = new widget.Label();
        nmpenjab = new widget.TextBox();
        BtnSeek3 = new widget.Button();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        kdasal.setEditable(false);
        kdasal.setName("kdasal"); // NOI18N
        kdasal.setPreferredSize(new java.awt.Dimension(75, 23));
        kdasal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasalKeyPressed(evt);
            }
        });

        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(60, 23));
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });

        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(60, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });

        kdjenis.setEditable(false);
        kdjenis.setName("kdjenis"); // NOI18N
        kdjenis.setPreferredSize(new java.awt.Dimension(75, 23));
        kdjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdjenisKeyPressed(evt);
            }
        });

        kdkategori.setEditable(false);
        kdkategori.setName("kdkategori"); // NOI18N
        kdkategori.setPreferredSize(new java.awt.Dimension(75, 23));
        kdkategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdkategoriKeyPressed(evt);
            }
        });

        kdgolongan.setEditable(false);
        kdgolongan.setName("kdgolongan"); // NOI18N
        kdgolongan.setPreferredSize(new java.awt.Dimension(75, 23));
        kdgolongan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdgolonganKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penggunaan Obat Ralan & Ranap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tgl.Beri Obat :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(85, 23));
        panelisi1.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi1.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi1.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi1.add(Tgl2);

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
        panelisi1.add(BtnCari);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelisi1.add(jLabel7);

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

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Filter Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 74));
        FormInput.setLayout(null);

        label19.setText("Cara Bayar :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(75, 23));
        FormInput.add(label19);
        label19.setBounds(0, 10, 65, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(168, 23));
        FormInput.add(nmpenjab);
        nmpenjab.setBounds(70, 10, 150, 23);

        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('3');
        BtnSeek3.setToolTipText("Alt+3");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        BtnSeek3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek3KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek3);
        BtnSeek3.setBounds(230, 10, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

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
                                "`") + "','"
                        + tabMode.getValueAt(r, 6).toString().replaceAll("'",
                                "`") + "','"
                        + tabMode.getValueAt(r, 7).toString().replaceAll("'",
                                "`") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                                getalamatip() + "'", "Rekap Obat Perdokter Poli");
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
            Valid.MyReportqry("rptRBObatPoli.jasper", "report",
                    "[ Rekap Obat Dokter Per Poli]",
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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        kdpoli.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        kdasal.setText("");
        kdjenis.setText("");
        kdkategori.setText("");
        kdgolongan.setText("");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpoli, BtnPrint);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    prosesCari();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, kdpoli, BtnAll);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnKeluar, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1, kdpoli);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Tgl1.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek3KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek3KeyPressed

    private void kdasalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdasalKeyPressed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?",
                    nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?",
                    nmpenjab, kdpenjab.getText());
            BtnAll.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?",
                    nmpenjab, kdpenjab.getText());
            Tgl2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
//        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
//            BtnAll.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            Sequel.cariIsi("select poliklinik.nm_poli from poliklinik where poliklinik.kd_poli=?", nmpoli,kdpoli.getText());
//            Tgl2.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
//            BtnSeek2ActionPerformed(null);
//        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void kdjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdjenisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdjenisKeyPressed

    private void kdkategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdkategoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdkategoriKeyPressed

    private void kdgolonganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdgolonganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdgolonganKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanAnalisa dialog = new DlgLaporanAnalisa(new javax.swing.JFrame(), true);
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
    private widget.Button BtnSeek3;
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private widget.TextBox Kd2;
    private javax.swing.JPanel PanelInput;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel7;
    private widget.TextBox kdasal;
    private widget.TextBox kdgolongan;
    private widget.TextBox kdjenis;
    private widget.TextBox kdkategori;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label19;
    private widget.TextBox nmpenjab;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            pspoli = koneksi.prepareStatement("select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between ? and ? "
                    + "and reg_periksa.status_lanjut='Ranap'");
            try {
                pspoli.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                pspoli.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                rspoli = pspoli.executeQuery();
                i = 1;
                ttlbiaya = 0;
                ttlembalase = 0;
                ttltuslah = 0;
                ttltotal = 0;
                while (rspoli.next()) {
                    // tabMode.addRow(new Object[]{i+".
                    // ",rspoli.getString(1),rspoli.getString(2),rspoli.getString(3),"","","",""});
                    pspenjab = koneksi
                            .prepareStatement("select distinct dokter.nm_dokter from rawat_inap_dr inner join dokter "
                                    + "on rawat_inap_dr.kd_dokter=dokter.kd_dokter inner join jns_perawatan_inap on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                    + "where jns_perawatan_inap.nm_perawatan like '%Visite Dokter Spesialis%' and rawat_inap_dr.no_rawat=?");
                    dokterranap = "";
                    try {
                        pspenjab.setString(1, rspoli.getString("no_rawat"));
                        rspenjab = pspenjab.executeQuery();
                        a = 1;
                        ttlbiaya = 0;
                        ttlembalase = 0;
                        ttltuslah = 0;
                        ttltotal = 0;
                        while (rspenjab.next()) {
                            dokterranap = rspenjab.getString("nm_dokter") + ", " + dokterranap;
                            // tabMode.addRow(new
                            // Object[]{"","","",rspenjab.getString(1),"","","",""});
                            a++;

                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rspenjab != null) {
                            rspenjab.close();
                        }
                        if (pspenjab != null) {
                            pspenjab.close();
                        }
                    }
                    tabMode.addRow(new Object[]{i + ". ", rspoli.getString(1), rspoli.getString(2),
                        rspoli.getString(3), dokterranap, "", "", ""});
                    i++;
                    // tindakan dr
                    pstindakan = koneksi.prepareStatement("select jns_perawatan_inap.nm_perawatan,dokter.nm_dokter, "
                            + "rawat_inap_dr.tgl_perawatan, "
                            + "rawat_inap_dr.tarif_tindakandr,(rawat_inap_dr.tarif_tindakandr) as totaljm,"
                            + "(rawat_inap_dr.material+rawat_inap_dr.bhp+rawat_inap_dr.kso+rawat_inap_dr.menejemen) as sarana,"
                            + "(rawat_inap_dr.material+rawat_inap_dr.bhp+rawat_inap_dr.kso+rawat_inap_dr.menejemen) as totalsarana "
                            + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter on "
                            + "rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and "
                            + "rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat=?");
                    try {
                        pstindakan.setString(1, rspoli.getString("no_rawat"));
                        rstindakan = pstindakan.executeQuery();
                        while (rstindakan.next()) {
                            tabMode.addRow(new Object[]{"", rspoli.getString(1), rspoli.getString(2),
                                rspoli.getString(3), dokterranap, rstindakan.getString(1), rstindakan.getString(2),
                                (rstindakan.getDouble("totaljm") + rstindakan.getDouble("totalsarana"))});
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Inap dr : " + e);
                    } finally {
                        if (rstindakan != null) {
                            rstindakan.close();
                        }
                        if (pstindakan != null) {
                            pstindakan.close();
                        }
                    }

                    // tindakan drpr
                    pstindakan = koneksi.prepareStatement("select jns_perawatan_inap.nm_perawatan,dokter.nm_dokter, "
                            + "rawat_inap_drpr.tgl_perawatan, "
                            + "rawat_inap_drpr.tarif_tindakandr,(rawat_inap_drpr.tarif_tindakandr) as totaljm,"
                            + "(rawat_inap_drpr.material+rawat_inap_drpr.bhp+rawat_inap_drpr.kso+rawat_inap_drpr.menejemen+rawat_inap_drpr.tarif_tindakanpr) as sarana,"
                            + "(rawat_inap_drpr.material+rawat_inap_drpr.bhp+rawat_inap_drpr.kso+rawat_inap_drpr.menejemen+rawat_inap_drpr.tarif_tindakanpr) as totalsarana "
                            + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter on "
                            + "rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and "
                            + "rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat=? ");
                    try {
                        pstindakan.setString(1, rspoli.getString("no_rawat"));
                        rstindakan = pstindakan.executeQuery();
                        while (rstindakan.next()) {
                            tabMode.addRow(new Object[]{"", rspoli.getString(1), rspoli.getString(2),
                                rspoli.getString(3), dokterranap, rstindakan.getString(1), rstindakan.getString(2),
                                (rstindakan.getDouble("totaljm") + rstindakan.getDouble("totalsarana"))});
                        }

                    } catch (Exception e) {
                        System.out.println("Notif Inap dr pr : " + e);
                    } finally {
                        if (rstindakan != null) {
                            rstindakan.close();
                        }
                        if (pstindakan != null) {
                            pstindakan.close();
                        }
                    }

                    pstindakan = koneksi.prepareStatement("select jns_perawatan_inap.nm_perawatan,petugas.nama, "
                            + "rawat_inap_pr.tgl_perawatan, "
                            + "rawat_inap_pr.tarif_tindakanpr,(rawat_inap_pr.tarif_tindakanpr) as totaljm,"
                            + "(rawat_inap_pr.material+rawat_inap_pr.bhp+rawat_inap_pr.kso+rawat_inap_pr.menejemen) as sarana,"
                            + "(rawat_inap_pr.material+rawat_inap_pr.bhp+rawat_inap_pr.kso+rawat_inap_pr.menejemen) as totalsarana "
                            + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas on "
                            + "rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and "
                            + "rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat=? ");
                    try {
                        pstindakan.setString(1, rspoli.getString("no_rawat"));
                        rstindakan = pstindakan.executeQuery();
                        while (rstindakan.next()) {
                            tabMode.addRow(new Object[]{"", rspoli.getString(1), rspoli.getString(2),
                                rspoli.getString(3), dokterranap, rstindakan.getString(1), rstindakan.getString(2),
                                (rstindakan.getDouble("totaljm") + rstindakan.getDouble("totalsarana"))});
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Inap dr : " + e);
                    } finally {
                        if (rstindakan != null) {
                            rstindakan.close();
                        }
                        if (pstindakan != null) {
                            pstindakan.close();
                        }
                    }

                }
                // tabMode.addRow(new Object[]{">>","Total
                // ",":","",Valid.SetAngka(ttlbiaya),Valid.SetAngka(ttlembalase),Valid.SetAngka(ttltuslah),Valid.SetAngka(ttltotal)});
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rspoli != null) {
                    rspoli.close();
                }
                if (pspoli != null) {
                    pspoli.close();
                }
            }

            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 96));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgLaporanAnalisa.class.getName());

}
