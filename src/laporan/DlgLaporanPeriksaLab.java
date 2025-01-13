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
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import simrskhanza.DlgCariCaraBayar;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;

/**
 * @author perpustakaan
 */
public class DlgLaporanPeriksaLab extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private DlgCariPoli poli = new DlgCariPoli(null, false);

    private DlgCariDokter dokter = new DlgCariDokter(null, false);

    private DlgKabupaten kabupaten = new DlgKabupaten(null, false);

    private DlgKecamatan kecamatan = new DlgKecamatan(null, false);

    private DlgKelurahan kelurahan = new DlgKelurahan(null, false);

    private DlgCariCaraBayar penjab = new DlgCariCaraBayar(null, false);

    private int i = 0;

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgLaporanPeriksaLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] rowRwJlDr = {"No.", "No.Rawat", "No.RM", "Nama Pasien", "Alamat", "No.KTP", "Tanggal Lahir",
            "No.Telp", "Tanggal Registrasi", "Asal Poli", "Jenis Pemeriksaan", "Hasil"};
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
                column.setPreferredWidth(50);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(110);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(180);
            } else if (i == 11) {
                column.setPreferredWidth(180);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput(90).getKata(TCari));
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

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                poli.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
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

        kabupaten.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kabupaten.getTable().getSelectedRow() != -1) {
                    nmkabupaten
                            .setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(), 0).toString());
                }
                nmkabupaten.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                kabupaten.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        kabupaten.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kabupaten.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        kecamatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kecamatan.getTable().getSelectedRow() != -1) {
                    nmkecamatan
                            .setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(), 0).toString());
                }
                nmkecamatan.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                kecamatan.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        kecamatan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kecamatan.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        kelurahan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kelurahan.getTable().getSelectedRow() != -1) {
                    nmkelurahan
                            .setText(kelurahan.getTable().getValueAt(kelurahan.getTable().getSelectedRow(), 0).toString());
                }
                nmkelurahan.requestFocus();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                kelurahan.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        kelurahan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kelurahan.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                dokter.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    dokter.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        ChkInput.setSelected(false);
        isForm();

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
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label21 = new widget.Label();
        nmkabupaten = new widget.TextBox();
        BtnSeek5 = new widget.Button();
        label22 = new widget.Label();
        nmkecamatan = new widget.TextBox();
        BtnSeek6 = new widget.Button();
        BtnSeek7 = new widget.Button();
        nmkelurahan = new widget.TextBox();
        label23 = new widget.Label();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Laporan Periksa Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput.setLayout(null);

        label21.setText("Kab/Kota :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(label21);
        label21.setBounds(7, 10, 70, 23);

        nmkabupaten.setEditable(false);
        nmkabupaten.setName("nmkabupaten"); // NOI18N
        nmkabupaten.setPreferredSize(new java.awt.Dimension(215, 23));
        FormInput.add(nmkabupaten);
        nmkabupaten.setBounds(80, 10, 260, 23);

        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek5.setMnemonic('3');
        BtnSeek5.setToolTipText("Alt+3");
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        BtnSeek5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek5KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek5);
        BtnSeek5.setBounds(340, 10, 28, 23);

        label22.setText("Kecamatan :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(label22);
        label22.setBounds(7, 40, 70, 23);

        nmkecamatan.setEditable(false);
        nmkecamatan.setName("nmkecamatan"); // NOI18N
        nmkecamatan.setPreferredSize(new java.awt.Dimension(215, 23));
        FormInput.add(nmkecamatan);
        nmkecamatan.setBounds(80, 40, 260, 23);

        BtnSeek6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek6.setMnemonic('3');
        BtnSeek6.setToolTipText("Alt+3");
        BtnSeek6.setName("BtnSeek6"); // NOI18N
        BtnSeek6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek6ActionPerformed(evt);
            }
        });
        BtnSeek6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek6KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek6);
        BtnSeek6.setBounds(340, 40, 28, 23);

        BtnSeek7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek7.setMnemonic('3');
        BtnSeek7.setToolTipText("Alt+3");
        BtnSeek7.setName("BtnSeek7"); // NOI18N
        BtnSeek7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek7ActionPerformed(evt);
            }
        });
        BtnSeek7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek7KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek7);
        BtnSeek7.setBounds(340, 70, 28, 23);

        nmkelurahan.setEditable(false);
        nmkelurahan.setName("nmkelurahan"); // NOI18N
        nmkelurahan.setPreferredSize(new java.awt.Dimension(215, 23));
        FormInput.add(nmkelurahan);
        nmkelurahan.setBounds(80, 70, 260, 23);

        label23.setText("Kelurahan :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(label23);
        label23.setBounds(7, 70, 70, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Data Laporan Periksa Laboratorium ]::");

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
            param.put("periode", Tgl1.getSelectedItem() + " s.d. " + Tgl2.
                    getSelectedItem());
            if (nmkabupaten.getText().isEmpty()) {
                param.put("kab", "Semua Kabupaten");
            } else {
                param.put("kab", nmkabupaten.getText());
            }
            if (nmkecamatan.getText().isEmpty()) {
                param.put("kec", "Semua Kecamatan");
            } else {
                param.put("kec", nmkecamatan.getText());
            }
            if (nmkelurahan.getText().isEmpty()) {
                param.put("kel", "Semua Kelurahan");
            } else {
                param.put("kel", nmkelurahan.getText());
            }

            param.put("tanggal", Tgl2.getDate());
            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");
            for (int r = 0; r < tabMode.getRowCount(); r++) {
                try {
                    Sequel.menyimpan("temporary", "'" + r + "','"
                            + tabMode.getValueAt(r, 0).toString() + "','"
                            + tabMode.getValueAt(r, 1).toString() + "','"
                            + tabMode.getValueAt(r, 2).toString() + "','"
                            + tabMode.getValueAt(r, 3).toString() + "','"
                            + tabMode.getValueAt(r, 4).toString() + "','"
                            + tabMode.getValueAt(r, 5).toString() + "','"
                            + tabMode.getValueAt(r, 6).toString() + "','"
                            + tabMode.getValueAt(r, 7).toString() + "','"
                            + tabMode.getValueAt(r, 8).toString() + "','"
                            + tabMode.getValueAt(r, 9).toString() + "','"
                            + tabMode.getValueAt(r, 10).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                            getalamatip() + "'", "Rekap Nota Pembayaran"
                    );
                } catch (Exception e) {
                }
            }

            Valid.MyReportqry("rptLaporanPeriksaLab.jasper", "report",
                    "::[ Laporan Kunjungan Laboratorium Rawat Jalan ]::",
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
        nmkabupaten.setText("");
        nmkecamatan.setText("");
        nmkelurahan.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {

        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
        kabupaten.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        kabupaten.setLocationRelativeTo(internalFrame1);
        kabupaten.setAlwaysOnTop(false);
        kabupaten.setVisible(true);
    }//GEN-LAST:event_BtnSeek5ActionPerformed

    private void BtnSeek5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek5KeyPressed

    private void BtnSeek6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek6ActionPerformed
        kecamatan.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        kecamatan.setLocationRelativeTo(internalFrame1);
        kecamatan.setAlwaysOnTop(false);
        kecamatan.setVisible(true);
    }//GEN-LAST:event_BtnSeek6ActionPerformed

    private void BtnSeek6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek6KeyPressed

    private void BtnSeek7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek7ActionPerformed
        kelurahan.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        kelurahan.setLocationRelativeTo(internalFrame1);
        kelurahan.setAlwaysOnTop(false);
        kelurahan.setVisible(true);
    }//GEN-LAST:event_BtnSeek7ActionPerformed

    private void BtnSeek7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek7KeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanPeriksaLab dialog = new DlgLaporanPeriksaLab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnSeek5;
    private widget.Button BtnSeek6;
    private widget.Button BtnSeek7;
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private javax.swing.JPanel PanelInput;
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
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.TextBox nmkabupaten;
    private widget.TextBox nmkecamatan;
    private widget.TextBox nmkelurahan;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            ps = koneksi.prepareStatement(
                    "SELECT reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,CONCAT(pasien.alamat,' ,',kelurahan.nm_kel,' ,',kecamatan.nm_kec,' ,',kabupaten.nm_kab,' ,',propinsi.nm_prop) AS alamat,"
                    + "pasien.no_ktp,DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') AS tgl_lahir,pasien.no_tlp,DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') AS tgl_registrasi,"
                    + "poliklinik.nm_poli,template_laboratorium.Pemeriksaan,detail_periksa_lab.nilai FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN kelurahan ON pasien.kd_kel = kelurahan.kd_kel INNER JOIN kecamatan ON pasien.kd_kec = kecamatan.kd_kec INNER JOIN kabupaten "
                    + "ON pasien.kd_kab = kabupaten.kd_kab INNER JOIN propinsi ON pasien.kd_prop = propinsi.kd_prop INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + "INNER JOIN detail_periksa_lab ON reg_periksa.no_rawat = detail_periksa_lab.no_rawat INNER JOIN template_laboratorium ON detail_periksa_lab.id_template = template_laboratorium.id_template "
                    + "where reg_periksa.tgl_registrasi between ? and ? and kabupaten.nm_kab like ? and kecamatan.nm_kec like ? and kelurahan.nm_kel like ? and template_laboratorium.Pemeriksaan like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and kabupaten.nm_kab like ? and kecamatan.nm_kec like ? and kelurahan.nm_kel like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and kabupaten.nm_kab like ? and kecamatan.nm_kec like ? and kelurahan.nm_kel like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and kabupaten.nm_kab like ? and kecamatan.nm_kec like ? and kelurahan.nm_kel like ? and pasien.alamat like ? "
                    + "order by reg_periksa.no_rawat");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + nmkabupaten.getText().trim() + "%");
                ps.setString(4, "%" + nmkecamatan.getText().trim() + "%");
                ps.setString(5, "%" + nmkelurahan.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + nmkabupaten.getText().trim() + "%");
                ps.setString(10, "%" + nmkecamatan.getText().trim() + "%");
                ps.setString(11, "%" + nmkelurahan.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + nmkabupaten.getText().trim() + "%");
                ps.setString(16, "%" + nmkecamatan.getText().trim() + "%");
                ps.setString(17, "%" + nmkelurahan.getText().trim() + "%");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + nmkabupaten.getText().trim() + "%");
                ps.setString(22, "%" + nmkecamatan.getText().trim() + "%");
                ps.setString(23, "%" + nmkelurahan.getText().trim() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                i = 1;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), rs.getString("alamat"), rs.getString("no_ktp"),
                        rs.getString("tgl_lahir"), rs.getString("no_tlp"), rs.getString("tgl_registrasi"),
                        rs.getString("nm_poli"), rs.getString("Pemeriksaan"), rs.getString("nilai")});
                    i++;
                }
                if (i > 1) {
                    tabMode.addRow(new Object[]{">>", "Total : ", (i - 1), "", "", "", "", "", "", "", ""});
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

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 126));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgLaporanPeriksaLab.class.getName());

}
