/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgObatPenyakit.java
*
* Created on May 23, 2010, 12:40:35 AM
 */
package setting;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgJnsPerawatanLab;

/**
 * @author dosen
 */
public class DlgSetOtoLab extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private PreparedStatement ps;

    private ResultSet rs;

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private DlgJnsPerawatanLab datatindakan = new DlgJnsPerawatanLab(null, false);

    // private DlgTemplateLaboratorium template=new DlgTemplateLaboratorium(null,false);
    /**
     * Creates new form DlgObatPenyakit
     *
     * @param parent
     * @param modal
     */
    public DlgSetOtoLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode = new DefaultTableModel(null,
                new String[]{"Kode Pemeriksaan", "Nama Pemeriksaan", "Template Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        Table1.setModel(tabMode);

        // tbObatPenyakit.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObatPenyakit.getBackground()));
        Table1.setPreferredScrollableViewportSize(new Dimension(800, 800));
        Table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = Table1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            }
        }
        Table1.setDefaultRenderer(Object.class, new WarnaTable());

        kdtindakan.setDocument(new batasInput((byte) 15).getKata(kdtindakan));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        datatindakan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (datatindakan.getTable().getSelectedRow() != -1) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        kdtindakan.setText(datatindakan.getTable()
                                .getValueAt(datatindakan.getTable().getSelectedRow(), 1)
                                .toString());
                        nmtindakan.setText(datatindakan.getTable()
                                .getValueAt(datatindakan.getTable().getSelectedRow(), 2)
                                .toString());
                        kdtindakan.requestFocus();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        datatindakan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    datatindakan.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        // template.addWindowListener(new WindowListener() {
        // @Override
        // public void windowOpened(WindowEvent e) {}
        // @Override
        // public void windowClosing(WindowEvent e) {}
        // @Override
        // public void windowClosed(WindowEvent e) {
        // if(template.getTable().getSelectedRow()!= -1){
        // if(TabRawat.getSelectedIndex()==0){
        // nmdetailtindakan.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),0).toString());
        // nmdetailtindakan.requestFocus();
        // }
        // }
        // }
        // @Override
        // public void windowIconified(WindowEvent e) {}
        // @Override
        // public void windowDeiconified(WindowEvent e) {}
        // @Override
        // public void windowActivated(WindowEvent e) {}
        // @Override
        // public void windowDeactivated(WindowEvent e) {}
        // });
        //
        // template.getTable().addKeyListener(new KeyListener() {
        // @Override
        // public void keyTyped(KeyEvent e) {}
        //
        // @Override
        // public void keyPressed(KeyEvent e) {
        // if(e.getKeyCode()==KeyEvent.VK_SPACE){
        // template.dispose();
        // }
        // }
        //
        // @Override
        // public void keyReleased(KeyEvent e) {}
        // });
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        Table1 = new widget.Table();
        panelGlass2 = new widget.panelisi();
        jLabel4 = new widget.Label();
        nmtindakan = new widget.TextBox();
        BtnSeek1 = new widget.Button();
        kdtindakan = new widget.TextBox();
        jLabel5 = new widget.Label();
        nmdetailtindakan = new widget.TextBox();
        BtnSeek2 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup Tindakan Otomatis laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

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
        panelGlass8.add(BtnPrint);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
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
        panelGlass9.add(BtnAll);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        Table1.setAutoCreateRowSorter(true);
        Table1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Table1.setName("Table1"); // NOI18N
        Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked(evt);
            }
        });
        Table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table1KeyPressed(evt);
            }
        });
        Scroll.setViewportView(Table1);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass2.setName("panelGlass2"); // NOI18N
        panelGlass2.setPreferredSize(new java.awt.Dimension(711, 85));
        panelGlass2.setLayout(null);

        jLabel4.setText("Tindakan Otomatis :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass2.add(jLabel4);
        jLabel4.setBounds(0, 10, 120, 23);

        nmtindakan.setEditable(false);
        nmtindakan.setName("nmtindakan"); // NOI18N
        nmtindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmtindakanKeyPressed(evt);
            }
        });
        panelGlass2.add(nmtindakan);
        nmtindakan.setBounds(230, 10, 252, 23);

        BtnSeek1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek1.setMnemonic('2');
        BtnSeek1.setToolTipText("Alt+2");
        BtnSeek1.setName("BtnSeek1"); // NOI18N
        BtnSeek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek1ActionPerformed(evt);
            }
        });
        BtnSeek1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek1KeyPressed(evt);
            }
        });
        panelGlass2.add(BtnSeek1);
        BtnSeek1.setBounds(480, 10, 28, 23);

        kdtindakan.setHighlighter(null);
        kdtindakan.setName("kdtindakan"); // NOI18N
        kdtindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdtindakanKeyPressed(evt);
            }
        });
        panelGlass2.add(kdtindakan);
        kdtindakan.setBounds(130, 10, 100, 23);

        jLabel5.setText("Detail Tindakan Lab :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass2.add(jLabel5);
        jLabel5.setBounds(0, 40, 120, 23);

        nmdetailtindakan.setEditable(false);
        nmdetailtindakan.setName("nmdetailtindakan"); // NOI18N
        nmdetailtindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmdetailtindakanKeyPressed(evt);
            }
        });
        panelGlass2.add(nmdetailtindakan);
        nmdetailtindakan.setBounds(132, 40, 350, 23);

        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('2');
        BtnSeek2.setToolTipText("Alt+2");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelGlass2.add(BtnSeek2);
        BtnSeek2.setBounds(480, 40, 28, 23);

        internalFrame2.add(panelGlass2, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Tindakan Lab", internalFrame2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (kdtindakan.getText().trim().isEmpty() || nmtindakan.getText().
                    trim().isEmpty() || nmdetailtindakan.getText().trim().
                            isEmpty()) {
                Valid.textKosong(kdtindakan, "Tindakan");
            } else {
                if (Sequel.menyimpantf(
                        "set_otomatis_tindakan_lab",
                        "'" + kdtindakan.getText() + "','" + nmtindakan.
                        getText() + "','" + nmdetailtindakan.getText() + "'",
                        "Tindakan") == true) {
                    tampil();
                    emptTeks();
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            if (TabRawat.getSelectedIndex() == 0) {
                Valid.pindah(evt, kdtindakan, BtnBatal);
            }
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            emptTeks();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Maaf, data sudah habis...!!!!");
                kdtindakan.requestFocus();
            } else if (nmtindakan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
            } else if (!(nmtindakan.getText().trim().isEmpty())) {
                Valid.hapusTable(tabMode, kdtindakan,
                        "set_otomatis_tindakan_lab",
                        "template_perawatan='" + nmdetailtindakan.getText() + "' and kd_jenis_prw");
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
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
            Valid.MyReportqry("rptOtoRalan.jasper", "report",
                    "::[ Data Tindakan Otomatis Dokter Ralan ]::",
                    "select set_otomatis_tindakan_ralan.kd_dokter,dokter.nm_dokter, set_otomatis_tindakan_ralan.kd_jenis_prw,jns_perawatan.nm_perawatan "
                    + "from set_otomatis_tindakan_ralan inner join dokter inner join jns_perawatan on  "
                    + "set_otomatis_tindakan_ralan.kd_dokter=dokter.kd_dokter and "
                    + "set_otomatis_tindakan_ralan.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                    + "where set_otomatis_tindakan_ralan.kd_dokter like '%" + TCari.
                            getText().trim() + "%' or "
                    + "dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "set_otomatis_tindakan_ralan.kd_jenis_prw like '%" + TCari.
                            getText().trim() + "%' or "
                    + "jns_perawatan.nm_perawatan like '%" + TCari.getText().
                            trim() + "%' "
                    + " order by dokter.nm_dokter", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, BtnSimpan);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void kdtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdtindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi(
                    "select nm_perawatan from jns_perawatan_lab where kd_jenis_prw=? ",
                    nmtindakan, kdtindakan.getText());
        }
    }//GEN-LAST:event_kdtindakanKeyPressed

    private void BtnSeek1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek1KeyPressed
        Valid.pindah(evt, kdtindakan, BtnSimpan);
    }//GEN-LAST:event_BtnSeek1KeyPressed

    private void BtnSeek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek1ActionPerformed
        datatindakan.isCek();
        datatindakan.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        datatindakan.setLocationRelativeTo(internalFrame1);
        datatindakan.setVisible(true);
    }//GEN-LAST:event_BtnSeek1ActionPerformed

    private void nmtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmtindakanKeyPressed
        //Valid.pindah(evt,TKd,BtnSimpan);
    }//GEN-LAST:event_nmtindakanKeyPressed

    private void Table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table1KeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_Table1KeyPressed

    private void Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table1MouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_Table1MouseClicked

    private void nmdetailtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmdetailtindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmdetailtindakanKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
//        template.KdPeriksa.setText(kdtindakan.getText());
//        template.NmPeriksa.setText(nmtindakan.getText());
//        template.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//        template.setLocationRelativeTo(internalFrame1);
//        template.tampil();
//        template.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek2KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSetOtoLab dialog = new DlgSetOtoLab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek1;
    private widget.Button BtnSeek2;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Table Table1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdtindakan;
    private widget.TextBox nmdetailtindakan;
    private widget.TextBox nmtindakan;
    private widget.panelisi panelGlass2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select set_otomatis_tindakan_lab.kd_jenis_prw,set_otomatis_tindakan_lab.nm_perawatan,set_otomatis_tindakan_lab.template_perawatan "
                    + "from set_otomatis_tindakan_lab "
                    + "where set_otomatis_tindakan_lab.kd_jenis_prw like ? or "
                    + "set_otomatis_tindakan_lab.nm_perawatan like ? "
                    + " order by set_otomatis_tindakan_lab.kd_jenis_prw");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3)});
                }
            } catch (SQLException e) {
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
            System.out.println("setting.DlgSetOtoLab.tampil() : " + e);
        }

        LCount.setText("" + tabMode.getRowCount());
    }

    /**
     *
     */
    public void emptTeks() {
        nmdetailtindakan.setText("");
        kdtindakan.requestFocus();
    }

    private void getData() {
        int row = Table1.getSelectedRow();
        if (row != -1) {
            kdtindakan.setText(Table1.getValueAt(row, 0).toString());
            nmtindakan.setText(Table1.getValueAt(row, 1).toString());
            nmdetailtindakan.setText(Table1.getValueAt(row, 2).toString());
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgSetOtoLab.class.getName());

}
