/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgJnsPerawatan.java
 *
 * Created on May 22, 2010, 11:58:21 PM
 */
package perpustakaan;

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
 *
 * @author dosen
 */
public class PerpustakaanAnggota extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection koneksi = koneksiDB.condb();
    private String gabung = "", habis = "";

    /**
     * Creates new form DlgJnsPerawatan
     *
     * @param parent
     * @param modal
     */
    public PerpustakaanAnggota(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Anggota", "Nama Anggota", "Tempat Lahir", "Tgl.Lahir", "J.K.",
            "Alamat Anggota",
            "No.Telp", "Email", "Bergabung", "Habis Pada", "Jenis Anggota",
            "No.Rm/NIP/No.KTP"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbJnsPerawatan.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbJnsPerawatan.setPreferredScrollableViewportSize(
                new Dimension(500, 500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(170);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(30);
            } else if (i == 5) {
                column.setPreferredWidth(170);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(140);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        NoAnggota.setDocument(new batasInput((byte) 10).getKata(NoAnggota));
        NmAnggota.setDocument(new batasInput((byte) 40).getKata(NmAnggota));
        Alamat.setDocument(new batasInput((byte) 70).getKata(Alamat));
        TmpLahir.setDocument(new batasInput((byte) 20).getKata(TmpLahir));
        NoTelp.setDocument(new batasInput((byte) 13).getKata(NoTelp));
        Email.setDocument(new batasInput((byte) 25).getKata(Email));
        NoID.setDocument(new batasInput((byte) 20).getKata(NoID));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        ChkInput.setSelected(false);
        isForm();

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(
                    new javax.swing.event.DocumentListener() {
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
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        MnKartu = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJnsPerawatan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        chkGabung = new widget.CekBox();
        Gabung1 = new widget.Tanggal();
        label33 = new widget.Label();
        Gabung2 = new widget.Tanggal();
        ChkHabis = new widget.CekBox();
        Habis1 = new widget.Tanggal();
        label34 = new widget.Label();
        Habis2 = new widget.Tanggal();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        label1 = new widget.Label();
        TmpLahir = new widget.TextBox();
        NmAnggota = new widget.TextBox();
        label5 = new widget.Label();
        NoID = new widget.TextBox();
        NoTelp = new widget.TextBox();
        label7 = new widget.Label();
        Email = new widget.TextBox();
        label8 = new widget.Label();
        label9 = new widget.Label();
        label10 = new widget.Label();
        TglLahir = new widget.Tanggal();
        NoAnggota = new widget.TextBox();
        jLabel8 = new widget.Label();
        JK = new widget.ComboBox();
        label11 = new widget.Label();
        Alamat = new widget.TextBox();
        label12 = new widget.Label();
        Gabung = new widget.Tanggal();
        Habis = new widget.Tanggal();
        label13 = new widget.Label();
        jLabel9 = new widget.Label();
        Jenis = new widget.ComboBox();
        ChkInput = new widget.CekBox();

        Popup.setName("Popup"); // NOI18N

        MnKartu.setBackground(new java.awt.Color(255, 255, 254));
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setForeground(new java.awt.Color(50,50,50));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Cetak Kartu Anggota");
        MnKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setPreferredSize(new java.awt.Dimension(200, 28));
        MnKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuActionPerformed(evt);
            }
        });
        Popup.add(MnKartu);

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Anggota Perpustakaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setAutoCreateRowSorter(true);
        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setComponentPopupMenu(Popup);
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        tbJnsPerawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJnsPerawatanMouseClicked(evt);
            }
        });
        tbJnsPerawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJnsPerawatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbJnsPerawatanKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 143));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

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
        panelGlass8.add(BtnAll);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 43));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        chkGabung.setText("Tgl.Gabung :");
        chkGabung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkGabung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGabung.setName("chkGabung"); // NOI18N
        chkGabung.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(chkGabung);

        Gabung1.setDisplayFormat("dd-MM-yyyy");
        Gabung1.setName("Gabung1"); // NOI18N
        Gabung1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(Gabung1);

        label33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label33.setText("s.d.");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(27, 23));
        panelGlass9.add(label33);

        Gabung2.setDisplayFormat("dd-MM-yyyy");
        Gabung2.setName("Gabung2"); // NOI18N
        Gabung2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(Gabung2);

        ChkHabis.setText("Keanggotaan Habis Pada :");
        ChkHabis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkHabis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHabis.setName("ChkHabis"); // NOI18N
        ChkHabis.setPreferredSize(new java.awt.Dimension(178, 23));
        panelGlass9.add(ChkHabis);

        Habis1.setDisplayFormat("dd-MM-yyyy");
        Habis1.setName("Habis1"); // NOI18N
        Habis1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(Habis1);

        label34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label34.setText("s.d.");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(27, 23));
        panelGlass9.add(label34);

        Habis2.setDisplayFormat("dd-MM-yyyy");
        Habis2.setName("Habis2"); // NOI18N
        Habis2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(Habis2);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 127));
        FormInput.setLayout(null);

        label1.setText("No.Anggota :");
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(0, 10, 92, 23);

        TmpLahir.setName("TmpLahir"); // NOI18N
        TmpLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmpLahirKeyPressed(evt);
            }
        });
        FormInput.add(TmpLahir);
        TmpLahir.setBounds(95, 70, 167, 23);

        NmAnggota.setName("NmAnggota"); // NOI18N
        NmAnggota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmAnggotaKeyPressed(evt);
            }
        });
        FormInput.add(NmAnggota);
        NmAnggota.setBounds(95, 40, 260, 23);

        label5.setText("No.RM/NIP :");
        label5.setName("label5"); // NOI18N
        FormInput.add(label5);
        label5.setBounds(542, 70, 70, 23);

        NoID.setName("NoID"); // NOI18N
        NoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoIDKeyPressed(evt);
            }
        });
        FormInput.add(NoID);
        NoID.setBounds(615, 70, 100, 23);

        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        FormInput.add(NoTelp);
        NoTelp.setBounds(610, 10, 105, 23);

        label7.setText("No.Telp :");
        label7.setName("label7"); // NOI18N
        FormInput.add(label7);
        label7.setBounds(547, 10, 60, 23);

        Email.setName("Email"); // NOI18N
        Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailKeyPressed(evt);
            }
        });
        FormInput.add(Email);
        Email.setBounds(443, 40, 272, 23);

        label8.setText("E-Mail :");
        label8.setName("label8"); // NOI18N
        FormInput.add(label8);
        label8.setBounds(360, 40, 80, 23);

        label9.setText("Tmp/Tgl.Lahir :");
        label9.setName("label9"); // NOI18N
        FormInput.add(label9);
        label9.setBounds(0, 70, 92, 23);

        label10.setText("Nama Anggota :");
        label10.setName("label10"); // NOI18N
        FormInput.add(label10);
        label10.setBounds(0, 40, 92, 23);

        TglLahir.setForeground(new java.awt.Color(50, 70, 50));
        TglLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-05-2019" }));
        TglLahir.setDisplayFormat("dd-MM-yyyy");
        TglLahir.setName("TglLahir"); // NOI18N
        TglLahir.setOpaque(false);
        TglLahir.setPreferredSize(new java.awt.Dimension(100, 23));
        TglLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLahirKeyPressed(evt);
            }
        });
        FormInput.add(TglLahir);
        TglLahir.setBounds(265, 70, 90, 23);

        NoAnggota.setName("NoAnggota"); // NOI18N
        NoAnggota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoAnggotaKeyPressed(evt);
            }
        });
        FormInput.add(NoAnggota);
        NoAnggota.setBounds(95, 10, 130, 23);

        jLabel8.setText("J.K. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(360, 10, 80, 23);

        JK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        JK.setName("JK"); // NOI18N
        JK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKKeyPressed(evt);
            }
        });
        FormInput.add(JK);
        JK.setBounds(443, 10, 105, 23);

        label11.setText("Alamat :");
        label11.setName("label11"); // NOI18N
        FormInput.add(label11);
        label11.setBounds(0, 100, 92, 23);

        Alamat.setName("Alamat"); // NOI18N
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(95, 100, 260, 23);

        label12.setText("Tgl.Gabung :");
        label12.setName("label12"); // NOI18N
        FormInput.add(label12);
        label12.setBounds(360, 100, 80, 23);

        Gabung.setForeground(new java.awt.Color(50, 70, 50));
        Gabung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-05-2019" }));
        Gabung.setDisplayFormat("dd-MM-yyyy");
        Gabung.setName("Gabung"); // NOI18N
        Gabung.setOpaque(false);
        Gabung.setPreferredSize(new java.awt.Dimension(100, 23));
        Gabung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GabungKeyPressed(evt);
            }
        });
        FormInput.add(Gabung);
        Gabung.setBounds(443, 100, 90, 23);

        Habis.setForeground(new java.awt.Color(50, 70, 50));
        Habis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-05-2019" }));
        Habis.setDisplayFormat("dd-MM-yyyy");
        Habis.setName("Habis"); // NOI18N
        Habis.setOpaque(false);
        Habis.setPreferredSize(new java.awt.Dimension(100, 23));
        Habis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HabisKeyPressed(evt);
            }
        });
        FormInput.add(Habis);
        Habis.setBounds(625, 100, 90, 23);

        label13.setText("Habis Pada :");
        label13.setName("label13"); // NOI18N
        FormInput.add(label13);
        label13.setBounds(532, 100, 90, 23);

        jLabel9.setText("Jenis :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(360, 70, 80, 23);

        Jenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien", "Pegawai", "Umum" }));
        Jenis.setName("Jenis"); // NOI18N
        Jenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisKeyPressed(evt);
            }
        });
        FormInput.add(Jenis);
        Jenis.setBounds(443, 70, 95, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoAnggota.getText().trim().isEmpty()) {
            Valid.textKosong(NoAnggota, "No.Anggota");
        } else if (NmAnggota.getText().trim().isEmpty()) {
            Valid.textKosong(NmAnggota, "Nama Anggota");
        } else if (TmpLahir.getText().trim().isEmpty()) {
            Valid.textKosong(TmpLahir, "Tempat Lahir");
        } else if (Alamat.getText().trim().isEmpty()) {
            Valid.textKosong(Alamat, "Alamat Anggota");
        } else if (NoTelp.getText().trim().isEmpty()) {
            Valid.textKosong(NoTelp, "No.Telp");
        } else if (Email.getText().trim().isEmpty()) {
            Valid.textKosong(Email, "Email");
        } else if (NoID.getText().trim().isEmpty()) {
            Valid.textKosong(NoID, "No.RM/NIP/No.KTP");
        } else {
            if (Sequel.menyimpantf("perpustakaan_anggota",
                    "?,?,?,?,?,?,?,?,?,?,?,?", "No.Anggota", 12, new String[]{
                        NoAnggota.getText(), NmAnggota.getText(), TmpLahir.
                        getText(), Valid.SetTgl(TglLahir.getSelectedItem() + ""),
                        JK.getSelectedItem().toString().substring(0, 1),
                        Alamat.getText(), NoTelp.getText(), Email.getText(),
                        Valid.SetTgl(Gabung.getSelectedItem() + ""), Valid.
                        SetTgl(Habis.getSelectedItem() + ""),
                        Jenis.getSelectedItem().toString(), NoID.getText()
                    }) == true) {
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Habis, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Valid.hapusTable(tabMode, NoAnggota, "perpustakaan_anggota",
                "no_anggota");
        BtnCariActionPerformed(evt);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (NoAnggota.getText().trim().isEmpty()) {
            Valid.textKosong(NoAnggota, "No.Anggota");
        } else if (NmAnggota.getText().trim().isEmpty()) {
            Valid.textKosong(NmAnggota, "Nama Anggota");
        } else if (TmpLahir.getText().trim().isEmpty()) {
            Valid.textKosong(TmpLahir, "Tempat Lahir");
        } else if (Alamat.getText().trim().isEmpty()) {
            Valid.textKosong(Alamat, "Alamat Anggota");
        } else if (NoTelp.getText().trim().isEmpty()) {
            Valid.textKosong(NoTelp, "No.Telp");
        } else if (Email.getText().trim().isEmpty()) {
            Valid.textKosong(Email, "Email");
        } else if (NoID.getText().trim().isEmpty()) {
            Valid.textKosong(NoID, "No.RM/NIP/No.KTP");
        } else {
            if (tbJnsPerawatan.getSelectedRow() > -1) {
                if (Sequel.mengedittf("perpustakaan_anggota", "no_anggota=?",
                        "no_anggota=?,nama_anggota=?,tmp_lahir=?,tgl_lahir=?,j_kel=?,alamat=?,no_telp=?,email=?,tgl_gabung=?,masa_berlaku=?,jenis_anggota=?,nomer_id=?",
                        13, new String[]{
                            NoAnggota.getText(), NmAnggota.getText(), TmpLahir.
                            getText(), Valid.SetTgl(
                                    TglLahir.getSelectedItem() + ""), JK.
                            getSelectedItem().toString().substring(0, 1),
                            Alamat.getText(), NoTelp.getText(), Email.getText(),
                            Valid.SetTgl(Gabung.getSelectedItem() + ""), Valid.
                            SetTgl(Habis.getSelectedItem() + ""), Jenis.
                            getSelectedItem().toString(), NoID.getText(),
                            tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                                    getSelectedRow(), 0).toString()
                        }) == true) {
                    tampil();
                    emptTeks();
                }
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            gabung = "";
            if (chkGabung.isSelected() == true) {
                gabung = " tgl_gabung between '" + Valid.SetTgl(Gabung1.
                        getSelectedItem() + "") + "' and '" + Valid.SetTgl(
                                Gabung2.getSelectedItem() + "") + "' and ";
            }

            habis = "";
            if (ChkHabis.isSelected() == true) {
                habis = " masa_berlaku between '" + Valid.SetTgl(Habis1.
                        getSelectedItem() + "") + "' and '" + Valid.SetTgl(
                                Habis2.getSelectedItem() + "") + "' and ";
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
            Valid.MyReportqry("rptAnggotaPerpustakaan.jasper", "report",
                    "::[ Data Anggota Perpustakaan ]::",
                    "select no_anggota, nama_anggota, tmp_lahir, tgl_lahir, j_kel, alamat, no_telp, email,"
                    + "tgl_gabung, masa_berlaku, jenis_anggota, nomer_id from perpustakaan_anggota where "
                    + gabung + habis + " no_anggota like '%" + TCari.getText().
                            trim() + "%' or "
                    + gabung + habis + " nama_anggota like '%" + TCari.getText().
                            trim() + "%' or "
                    + gabung + habis + " alamat like '%" + TCari.getText().
                            trim() + "%' or "
                    + gabung + habis + " no_telp like '%" + TCari.getText().
                            trim() + "%' or "
                    + gabung + habis + " jenis_anggota like '%" + TCari.
                            getText().trim() + "%' or "
                    + gabung + habis + " nomer_id like '%" + TCari.getText().
                            trim() + "%' "
                    + "order by no_anggota", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbJnsPerawatan.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        ChkHabis.setSelected(false);
        chkGabung.setSelected(false);
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJnsPerawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJnsPerawatanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJnsPerawatanMouseClicked

    private void tbJnsPerawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJnsPerawatanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbJnsPerawatanKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void TmpLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmpLahirKeyPressed
    Valid.pindah(evt, NmAnggota, TglLahir);
}//GEN-LAST:event_TmpLahirKeyPressed

private void NmAnggotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmAnggotaKeyPressed
    Valid.pindah(evt, NoAnggota, TmpLahir);
}//GEN-LAST:event_NmAnggotaKeyPressed

private void NoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoIDKeyPressed
    Valid.pindah(evt, Jenis, Gabung);
}//GEN-LAST:event_NoIDKeyPressed

private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
    Valid.pindah(evt, JK, Email);
}//GEN-LAST:event_NoTelpKeyPressed

private void EmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailKeyPressed
    Valid.pindah(evt, NoTelp, Jenis);
}//GEN-LAST:event_EmailKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TCari.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void tbJnsPerawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJnsPerawatanKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbJnsPerawatanKeyReleased

    private void NoAnggotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoAnggotaKeyPressed
        Valid.pindah(evt, TCari, NmAnggota);
    }//GEN-LAST:event_NoAnggotaKeyPressed

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        Valid.pindah(evt, TglLahir, JK);
    }//GEN-LAST:event_AlamatKeyPressed

    private void GabungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GabungKeyPressed
        Valid.pindah(evt, NoID, Habis);
    }//GEN-LAST:event_GabungKeyPressed

    private void HabisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HabisKeyPressed
        Valid.pindah(evt, Gabung, BtnSimpan);
    }//GEN-LAST:event_HabisKeyPressed

    private void JenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisKeyPressed
        Valid.pindah(evt, Email, NoID);
    }//GEN-LAST:event_JenisKeyPressed

    private void TglLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLahirKeyPressed
        Valid.pindah(evt, TmpLahir, Alamat);
    }//GEN-LAST:event_TglLahirKeyPressed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
        Valid.pindah(evt, Alamat, NoTelp);
    }//GEN-LAST:event_JKKeyPressed

    private void MnKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            if (tbJnsPerawatan.getSelectedRow() > -1) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar(
                        "select setting.logo from setting"));
                Valid.MyReportqry("rptKartuAnggota.jasper", "report",
                        "::[ Kartu Anggota Perpustakaan ]::",
                        "select no_anggota, nama_anggota, tmp_lahir, tgl_lahir, j_kel, alamat, no_telp, email,"
                        + "tgl_gabung, masa_berlaku, jenis_anggota, nomer_id from perpustakaan_anggota where no_anggota='" + NoAnggota.
                                getText() + "'", param);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Silahkan pilih data yang mau dicetak kartu anggotanya ..!!!");
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnKartuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            PerpustakaanAnggota dialog = new PerpustakaanAnggota(
                    new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkHabis;
    private widget.CekBox ChkInput;
    private widget.TextBox Email;
    private widget.PanelBiasa FormInput;
    private widget.Tanggal Gabung;
    private widget.Tanggal Gabung1;
    private widget.Tanggal Gabung2;
    private widget.Tanggal Habis;
    private widget.Tanggal Habis1;
    private widget.Tanggal Habis2;
    private widget.ComboBox JK;
    private widget.ComboBox Jenis;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnKartu;
    private widget.TextBox NmAnggota;
    private widget.TextBox NoAnggota;
    private widget.TextBox NoID;
    private widget.TextBox NoTelp;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal TglLahir;
    private widget.TextBox TmpLahir;
    private widget.CekBox chkGabung;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label5;
    private widget.Label label7;
    private widget.Label label8;
    private widget.Label label9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            gabung = "";
            if (chkGabung.isSelected() == true) {
                gabung = " tgl_gabung between '" + Valid.SetTgl(Gabung1.
                        getSelectedItem() + "") + "' and '" + Valid.SetTgl(
                                Gabung2.getSelectedItem() + "") + "' and ";
            }

            habis = "";
            if (ChkHabis.isSelected() == true) {
                habis = " masa_berlaku between '" + Valid.SetTgl(Habis1.
                        getSelectedItem() + "") + "' and '" + Valid.SetTgl(
                                Habis2.getSelectedItem() + "") + "' and ";
            }

            ps = koneksi.prepareStatement(
                    "select no_anggota, nama_anggota, tmp_lahir, tgl_lahir, j_kel, alamat, no_telp, email,"
                    + "tgl_gabung, masa_berlaku, jenis_anggota, nomer_id from perpustakaan_anggota where "
                    + gabung + habis + " no_anggota like ? or " + gabung + habis + " nama_anggota like ? or "
                    + gabung + habis + " alamat like ? or " + gabung + habis + " no_telp like ? or "
                    + gabung + habis + " jenis_anggota like ? or " + gabung + habis + " nomer_id like ? "
                    + "order by no_anggota");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_anggota"), rs.getString("nama_anggota"),
                        rs.getString("tmp_lahir"), rs.getString("tgl_lahir"),
                        rs.getString("j_kel"), rs.getString("alamat"), rs.
                        getString("no_telp"), rs.getString("email"), rs.
                        getString("tgl_gabung"),
                        rs.getString("masa_berlaku"), rs.getString(
                        "jenis_anggota"), rs.getString("nomer_id")
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TmpLahir.setText("");
        NmAnggota.setText("");
        NoID.setText("");
        NoTelp.setText("");
        Email.setText("");
        Alamat.setText("");
        TCari.setText("");
        Valid.autoNomer3(
                "select ifnull(MAX(CONVERT(RIGHT(no_anggota,8),signed)),0) from perpustakaan_anggota  ",
                "AP", 8, NoAnggota);
        NoAnggota.requestFocus();
    }

    private void getData() {
        if (tbJnsPerawatan.getSelectedRow() != -1) {
            NoAnggota.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 0).toString());
            NmAnggota.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 1).toString());
            TmpLahir.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 2).toString());
            Valid.SetTgl(TglLahir, tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 3).toString());
            JK.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 4).toString().replaceAll("P", "Perempuan").
                    replaceAll("L", "Laki-Laki"));
            Alamat.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 5).toString());
            NoTelp.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 6).toString());
            Email.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 7).toString());
            Valid.SetTgl(Gabung, tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 8).toString());
            Valid.SetTgl(Habis, tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 9).toString());
            Jenis.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 10).toString());
            NoID.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                    getSelectedRow(), 11).toString());
        }
    }

    public JTable getTable() {
        return tbJnsPerawatan;
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 156));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getanggota_perpustakaan());
        BtnHapus.setEnabled(akses.getanggota_perpustakaan());
        BtnEdit.setEnabled(akses.getanggota_perpustakaan());
    }

    private static final Logger LOG = Logger.getLogger(
            PerpustakaanAnggota.class.getName());

}
