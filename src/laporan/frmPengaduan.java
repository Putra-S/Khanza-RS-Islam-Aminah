/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laporan;

import fungsi.BackgroundMusic;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import org.joda.time.LocalDateTime;

/**
 *
 * @author khanzamedia
 */
public class frmPengaduan extends javax.swing.JFrame {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, nilai_detik, pesanbaru = 0;
    private boolean aktif = false;
    private String nol_detik, detik, alarm = "", nopengaduan = "";
    private BackgroundMusic music;

    /**
     * Creates new form frmPengaduan
     */
    public frmPengaduan() {
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "P", "Id", "Tanggal", "No.RM", "Nama Pasien", "Pesan", "Balasan"
        }) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbPengaduan.setModel(tabMode);
        tbPengaduan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPengaduan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPengaduan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(115);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(300);
            }
        }
        tbPengaduan.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
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

        try {
            alarm = koneksiDB.ALARMPENGADUANPASIEN();
        } catch (Exception ex) {
            alarm = "no";
        }

        if (alarm.equals("yes")) {
            jam();
        }

        HTMLEditorKit kit = new HTMLEditorKit();
        Document doc = kit.createDefaultDocument();
        RiwayatPengaduan.setEditorKit(kit);
        RiwayatPengaduan.setDocument(doc);
        RiwayatPengaduan.setEditable(false);
        RiwayatPengaduan.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPengaduan = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        internalFrame6 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        BtnSimpanBalas = new widget.Button();
        BtnBatal = new widget.Button();
        scrollPane4 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        Alamat = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel18 = new widget.Label();
        NoTelp = new widget.TextBox();
        jLabel20 = new widget.Label();
        Email = new widget.TextBox();
        TNo = new widget.TextBox();
        NamaPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel21 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel23 = new widget.Label();
        Kelurahan = new widget.TextBox();
        Kecamatan = new widget.TextBox();
        Kabupaten = new widget.TextBox();
        Propinsi = new widget.TextBox();
        scrollPane5 = new widget.ScrollPane();
        TambahanPesan = new widget.TextArea();
        jLabel57 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel9 = new widget.Label();
        CmbJk = new widget.ComboBox();
        scrollPane6 = new widget.ScrollPane();
        BalasanPesan = new widget.TextArea();
        jLabel58 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        RiwayatPengaduan = new widget.editorpane();
        PanelDataDicari = new widget.panelisi();
        label17 = new widget.Label();
        NoRMDicari = new widget.Label();
        label19 = new widget.Label();
        TglDicari = new widget.Label();
        label20 = new widget.Label();
        PasienDicari = new widget.Label();
        jPanel3 = new javax.swing.JPanel();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        R2 = new widget.RadioButton();
        Tgl1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        panelisi3 = new widget.panelisi();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnAll = new widget.Button();
        BtnJawab = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnHapusTotal = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("::[ Pengaduan/Chat ]::");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        internalFrame1.setPreferredSize(new java.awt.Dimension(1500, 507));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setOpaque(true);

        tbPengaduan.setAutoCreateRowSorter(true);
        tbPengaduan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPengaduanMouseClicked(evt);
            }
        });
        tbPengaduan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPengaduanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPengaduan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setPreferredSize(new java.awt.Dimension(700, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Balasan Pengaduan/Chat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpanBalas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanBalas.setMnemonic('S');
        BtnSimpanBalas.setText("Simpan & Balas");
        BtnSimpanBalas.setToolTipText("Alt+S");
        BtnSimpanBalas.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnSimpanBalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanBalasActionPerformed(evt);
            }
        });
        BtnSimpanBalas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanBalasKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnSimpanBalas);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
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
        panelGlass7.add(BtnBatal);

        internalFrame6.add(panelGlass7, java.awt.BorderLayout.PAGE_END);

        scrollPane4.setPreferredSize(new java.awt.Dimension(1093, 138));

        FormInput1.setBorder(null);
        FormInput1.setPreferredSize(new java.awt.Dimension(643, 503));
        FormInput1.setLayout(null);

        Alamat.setEditable(false);
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput1.add(Alamat);
        Alamat.setBounds(120, 80, 220, 23);

        jLabel16.setText("Alamat Pasien :");
        FormInput1.add(jLabel16);
        jLabel16.setBounds(0, 80, 116, 23);

        jLabel18.setText("No.HP/Telp :");
        FormInput1.add(jLabel18);
        jLabel18.setBounds(320, 20, 70, 23);

        NoTelp.setEditable(false);
        FormInput1.add(NoTelp);
        NoTelp.setBounds(400, 20, 110, 23);

        jLabel20.setText("E-Mail :");
        FormInput1.add(jLabel20);
        jLabel20.setBounds(320, 50, 70, 23);

        Email.setEditable(false);
        FormInput1.add(Email);
        Email.setBounds(400, 50, 210, 23);

        TNo.setEditable(false);
        TNo.setBackground(new java.awt.Color(245, 250, 240));
        TNo.setHighlighter(null);
        TNo.setOpaque(true);
        TNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoKeyPressed(evt);
            }
        });
        FormInput1.add(TNo);
        TNo.setBounds(120, 20, 110, 23);

        NamaPasien.setEditable(false);
        FormInput1.add(NamaPasien);
        NamaPasien.setBounds(120, 50, 150, 23);

        jLabel4.setText("No.Rekam Medis :");
        FormInput1.add(jLabel4);
        jLabel4.setBounds(0, 20, 116, 23);

        jLabel21.setText("Nama Pasien :");
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 50, 116, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("A. DATA PASIEN :");
        FormInput1.add(jLabel54);
        jLabel54.setBounds(10, 0, 180, 23);

        jLabel23.setText("Pesan :");
        FormInput1.add(jLabel23);
        jLabel23.setBounds(320, 80, 70, 23);

        Kelurahan.setEditable(false);
        Kelurahan.setText("KELURAHAN");
        Kelurahan.setHighlighter(null);
        FormInput1.add(Kelurahan);
        Kelurahan.setBounds(120, 110, 110, 23);

        Kecamatan.setEditable(false);
        Kecamatan.setText("KECAMATAN");
        Kecamatan.setHighlighter(null);
        FormInput1.add(Kecamatan);
        Kecamatan.setBounds(240, 110, 100, 23);

        Kabupaten.setEditable(false);
        Kabupaten.setText("KABUPATEN");
        Kabupaten.setHighlighter(null);
        FormInput1.add(Kabupaten);
        Kabupaten.setBounds(120, 140, 110, 23);

        Propinsi.setEditable(false);
        Propinsi.setText("PROPINSI");
        Propinsi.setHighlighter(null);
        FormInput1.add(Propinsi);
        Propinsi.setBounds(240, 140, 100, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TambahanPesan.setEditable(false);
        TambahanPesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TambahanPesan.setColumns(20);
        TambahanPesan.setRows(5);
        scrollPane5.setViewportView(TambahanPesan);

        FormInput1.add(scrollPane5);
        scrollPane5.setBounds(400, 80, 210, 83);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("B. BALASAN KE PASIEN :");
        FormInput1.add(jLabel57);
        jLabel57.setBounds(10, 170, 300, 23);

        jLabel32.setText("Pesan Balasan :");
        FormInput1.add(jLabel32);
        jLabel32.setBounds(0, 190, 116, 23);

        jLabel9.setText("J.K. :");
        FormInput1.add(jLabel9);
        jLabel9.setBounds(520, 20, 30, 23);

        CmbJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L", "P" }));
        CmbJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJkKeyPressed(evt);
            }
        });
        FormInput1.add(CmbJk);
        CmbJk.setBounds(550, 20, 55, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BalasanPesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BalasanPesan.setColumns(20);
        BalasanPesan.setRows(5);
        scrollPane6.setViewportView(BalasanPesan);

        FormInput1.add(scrollPane6);
        scrollPane6.setBounds(120, 195, 490, 83);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("C. RIWAYAT PESAN PENGADUAN :");
        FormInput1.add(jLabel58);
        jLabel58.setBounds(10, 290, 300, 23);

        Scroll2.setBorder(null);
        Scroll2.setOpaque(true);

        RiwayatPengaduan.setBorder(null);
        Scroll2.setViewportView(RiwayatPengaduan);

        FormInput1.add(Scroll2);
        Scroll2.setBounds(120, 320, 490, 160);

        scrollPane4.setViewportView(FormInput1);

        internalFrame6.add(scrollPane4, java.awt.BorderLayout.CENTER);

        PanelAccor.add(internalFrame6, java.awt.BorderLayout.CENTER);

        PanelDataDicari.setBackground(new java.awt.Color(255, 250, 250));
        PanelDataDicari.setBorder(null);
        PanelDataDicari.setPreferredSize(new java.awt.Dimension(100, 44));
        PanelDataDicari.setWarnaAtas(new java.awt.Color(255, 250, 250));
        PanelDataDicari.setWarnaBawah(new java.awt.Color(255, 250, 250));
        PanelDataDicari.setLayout(null);

        label17.setText("No.Rekam Medis :");
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        PanelDataDicari.add(label17);
        label17.setBounds(0, 10, 100, 23);

        NoRMDicari.setBackground(new java.awt.Color(255, 255, 255));
        NoRMDicari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NoRMDicari.setPreferredSize(new java.awt.Dimension(105, 23));
        PanelDataDicari.add(NoRMDicari);
        NoRMDicari.setBounds(103, 10, 80, 23);

        label19.setText("Tanggal :");
        label19.setPreferredSize(new java.awt.Dimension(53, 23));
        PanelDataDicari.add(label19);
        label19.setBounds(185, 10, 55, 23);

        TglDicari.setBackground(new java.awt.Color(255, 255, 255));
        TglDicari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TglDicari.setPreferredSize(new java.awt.Dimension(65, 23));
        PanelDataDicari.add(TglDicari);
        TglDicari.setBounds(244, 10, 130, 23);

        label20.setText("Pasien :");
        label20.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelDataDicari.add(label20);
        label20.setBounds(368, 10, 51, 23);

        PasienDicari.setBackground(new java.awt.Color(255, 255, 255));
        PasienDicari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PasienDicari.setPreferredSize(new java.awt.Dimension(470, 23));
        PanelDataDicari.add(PasienDicari);
        PasienDicari.setBounds(423, 10, 270, 23);

        PanelAccor.add(PanelDataDicari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Belum Dibalas");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setPreferredSize(new java.awt.Dimension(100, 23));
        R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1ActionPerformed(evt);
            }
        });
        panelCari.add(R1);

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tanggal Pengaduan :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setPreferredSize(new java.awt.Dimension(135, 23));
        R2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2ActionPerformed(evt);
            }
        });
        panelCari.add(R2);

        Tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-05-2024" }));
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setOpaque(false);
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tgl1ItemStateChanged(evt);
            }
        });
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelCari.add(Tgl1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        Tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-05-2024" }));
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setOpaque(false);
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelCari.add(Tgl2);

        label9.setText("Key Word :");
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelCari.add(label9);

        TCari.setPreferredSize(new java.awt.Dimension(177, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelCari.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelCari.add(BtnCari);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setText("Record :");
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(LCount);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("2Alt+2");
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
        panelisi3.add(BtnAll);

        BtnJawab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
        BtnJawab.setMnemonic('J');
        BtnJawab.setText("Jawab");
        BtnJawab.setToolTipText("Alt+J");
        BtnJawab.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnJawab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJawabActionPerformed(evt);
            }
        });
        BtnJawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnJawabKeyPressed(evt);
            }
        });
        panelisi3.add(BtnJawab);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Hapus Yang DIpilih");
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
        panelisi3.add(BtnHapus);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Cetak");
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
        panelisi3.add(BtnPrint);

        BtnHapusTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnHapusTotal.setMnemonic('B');
        BtnHapusTotal.setText("Clear");
        BtnHapusTotal.setToolTipText("Bersihkan Semua");
        BtnHapusTotal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusTotalActionPerformed(evt);
            }
        });
        BtnHapusTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusTotalKeyPressed(evt);
            }
        });
        panelisi3.add(BtnHapusTotal);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Keluar");
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        jPanel3.add(panelisi3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPengaduanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPengaduanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
                isPhoto();
                riwayatpengaduan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPengaduanMouseClicked

    private void tbPengaduanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPengaduanKeyPressed

    }//GEN-LAST:event_tbPengaduanKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPengaduan.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed

    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed

    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        for (i = 0; i < tbPengaduan.getRowCount(); i++) {
            if (tbPengaduan.getValueAt(i, 0).toString().equals("true")) {
                Sequel.queryu2("delete from pengaduan where id=?", 1,
                        new String[]{tbPengaduan.getValueAt(i, 1).toString()});
            }
        }
        BtnCariActionPerformed(evt);
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed

    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnHapusTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusTotalActionPerformed
        i = JOptionPane.showConfirmDialog(null,
                "Yakin semua data pengaduan mau dihapus ????", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            Sequel.queryu("delete from pengaduan");
            tampil();
        }
    }//GEN-LAST:event_BtnHapusTotalActionPerformed

    private void BtnHapusTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusTotalKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        aktif = true;
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
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
            Valid.MyReportqry("rptPengaduan.jasper", "report",
                    "::[ Data Pengaduan ]::",
                    "select id, date_time, username, message from pengaduan where "
                    + "date_time between '" + Valid.SetTgl(Tgl1.
                            getSelectedItem() + "") + " 00:00:00' and '" + Valid.
                            SetTgl(Tgl2.getSelectedItem() + "") + " 00:00:00' and id like '%" + TCari.
                    getText().trim() + "%' or "
                    + "date_time between '" + Valid.SetTgl(Tgl1.
                            getSelectedItem() + "") + " 00:00:00' and '" + Valid.
                            SetTgl(Tgl2.getSelectedItem() + "") + " 00:00:00' and username like '%" + TCari.
                    getText().trim() + "%' or "
                    + "date_time between '" + Valid.SetTgl(Tgl1.
                            getSelectedItem() + "") + " 00:00:00' and '" + Valid.
                            SetTgl(Tgl2.getSelectedItem() + "") + " 00:00:00' and message like '%" + TCari.
                    getText().trim() + "%' order by id ", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, Tgl2, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        aktif = false;
    }//GEN-LAST:event_formWindowClosed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed

    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tgl1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_Tgl1ItemStateChanged

    private void R2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2ActionPerformed

    }//GEN-LAST:event_R2ActionPerformed

    private void R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1ActionPerformed

    }//GEN-LAST:event_R1ActionPerformed

    private void BtnJawabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJawabActionPerformed
        if (tbPengaduan.getSelectedRow() > -1) {
            nopengaduan = tbPengaduan.
                    getValueAt(tbPengaduan.getSelectedRow(), 1).toString();
            try {
                ps = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk,pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop, "
                        + "pasien.no_tlp,pasien.jk,pasien.email from pasien inner join kelurahan inner join kecamatan inner join kabupaten inner join propinsi on pasien.kd_prop=propinsi.kd_prop "
                        + "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis=? ");
                try {
                    ps.setString(1, tbPengaduan.getValueAt(tbPengaduan.
                            getSelectedRow(), 3).toString());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        TNo.setText(rs.getString("no_rkm_medis"));
                        NamaPasien.setText(rs.getString("nm_pasien"));
                        CmbJk.setSelectedItem(rs.getString("jk"));
                        Alamat.setText(rs.getString("alamat"));
                        Kelurahan.setText(rs.getString("nm_kel"));
                        Kecamatan.setText(rs.getString("nm_kec"));
                        Kabupaten.setText(rs.getString("nm_kab"));
                        Propinsi.setText(rs.getString("nm_prop"));
                        NoTelp.setText(rs.getString("no_tlp"));
                        Email.setText(rs.getString("email"));
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
                System.out.println("Notif : " + e);
            }

            TambahanPesan.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 5).toString());
            BalasanPesan.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 6).toString());
            BalasanPesan.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnJawabActionPerformed

    private void BtnJawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnJawabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnJawabKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbPengaduan.getSelectedRow() > -1) {
            isPhoto();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnSimpanBalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanBalasActionPerformed
        if (!nopengaduan.isEmpty()) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            Sequel.meghapus("balasan_pengaduan", "id_pengaduan", nopengaduan);
            if (Sequel.menyimpantf("balasan_pengaduan", "?,?,?",
                    "Balasan Pengaduan", 3, new String[]{nopengaduan,
                        BalasanPesan.getText(), currentDateTime.toString()}) == true) {
                nopengaduan = "";
                BalasanPesan.setText("");
                tampil();
            }
            riwayatpengaduan();
        }
    }//GEN-LAST:event_BtnSimpanBalasActionPerformed

    private void BtnSimpanBalasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanBalasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanBalasKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        BalasanPesan.setText("");
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatKeyPressed

    private void TNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoKeyPressed

    private void CmbJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJkKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.
                    getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPengaduan.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPengaduan.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPengaduan.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPengaduan.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmPengaduan().setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox Alamat;
    private widget.TextArea BalasanPesan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusTotal;
    private widget.Button BtnJawab;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpanBalas;
    private widget.CekBox ChkAccor;
    private widget.ComboBox CmbJk;
    private widget.TextBox Email;
    private widget.PanelBiasa FormInput1;
    private widget.TextBox Kabupaten;
    private widget.TextBox Kecamatan;
    private widget.TextBox Kelurahan;
    private widget.Label LCount;
    private widget.TextBox NamaPasien;
    private widget.Label NoRMDicari;
    private widget.TextBox NoTelp;
    private widget.PanelBiasa PanelAccor;
    private widget.panelisi PanelDataDicari;
    private widget.Label PasienDicari;
    private widget.TextBox Propinsi;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.editorpane RiwayatPengaduan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private widget.TextBox TNo;
    private widget.TextArea TambahanPesan;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Label TglDicari;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel32;
    private widget.Label jLabel4;
    private widget.Label jLabel54;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.Label label10;
    private widget.Label label17;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label9;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelisi3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbPengaduan;
    // End of variables declaration//GEN-END:variables
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (R1.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "select pengaduan.id,pengaduan.tanggal,pengaduan.no_rkm_medis,pasien.nm_pasien,pengaduan.pesan "
                        + "from pengaduan inner join pasien on pengaduan.no_rkm_medis=pasien.no_rkm_medis where pengaduan.id not in(select id_pengaduan from balasan_pengaduan)"
                        + (TCari.getText().isEmpty() ? "" : "and (pengaduan.no_rkm_medis like ? or pasien.nm_pasien like ?)") + " order by pengaduan.tanggal ");
                try {
                    if (!TCari.getText().isEmpty()) {
                        ps.setString(1, "%" + TCari.getText().trim() + "%");
                        ps.setString(2, "%" + TCari.getText().trim() + "%");
                    }
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("id"), rs.getString("tanggal"),
                            rs.getString("no_rkm_medis"), rs.getString(
                            "nm_pasien"), rs.getString("pesan"), ""
                        });
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
            } else if (R2.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "select pengaduan.id,pengaduan.tanggal,pengaduan.no_rkm_medis,pasien.nm_pasien,pengaduan.pesan,"
                        + "ifnull(balasan_pengaduan.pesan_balasan,'') as pesan_balasan "
                        + "from pengaduan inner join pasien on pengaduan.no_rkm_medis=pasien.no_rkm_medis "
                        + "left join balasan_pengaduan on pengaduan.id=balasan_pengaduan.id_pengaduan where "
                        + "pengaduan.tanggal between ? and ? " + (TCari.
                                getText().isEmpty() ? "" : "and (pengaduan.no_rkm_medis like ? or pasien.nm_pasien like ? or pengaduan.pesan like ?)") + " order by pengaduan.tanggal ");
                try {
                    ps.setString(1,
                            Valid.SetTgl(Tgl1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2,
                            Valid.SetTgl(Tgl2.getSelectedItem() + "") + " 23:59:59");
                    if (!TCari.getText().isEmpty()) {
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
                        ps.setString(4, "%" + TCari.getText().trim() + "%");
                        ps.setString(5, "%" + TCari.getText().trim() + "%");
                    }
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("id"), rs.getString("tanggal"),
                            rs.getString("no_rkm_medis"), rs.getString(
                            "nm_pasien"), rs.getString("pesan"), rs.getString(
                            "pesan_balasan")
                        });
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
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    private void jam() {
        ActionListener taskPerformer = (ActionEvent e) -> {
            if (aktif == true) {
                nol_detik = "";
                Date now = Calendar.getInstance().getTime();
                nilai_detik = now.getSeconds();
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                detik = nol_detik + Integer.toString(nilai_detik);
                if (detik.equals("05")) {
                    pesanbaru = 0;
                    tampil();
                    for (i = 0; i < tbPengaduan.getRowCount(); i++) {
                        if (tbPengaduan.getValueAt(i, 6).toString().isEmpty()) {
                            pesanbaru++;
                        }
                    }
                    if (pesanbaru > 0) {
                        try {
                            music = new BackgroundMusic("./suara/alarm.mp3");
                            music.start();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        };
        new Timer(1000, taskPerformer).start();
    }

    /**
     *
     */
    public void isCek() {

    }

    private void getData() {
        if (tbPengaduan.getSelectedRow() != -1) {
            nopengaduan = tbPengaduan.
                    getValueAt(tbPengaduan.getSelectedRow(), 1).toString();
            try {
                ps = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk,pasien.alamat,kelurahan.nm_kel,kecamatan.nm_kec,kabupaten.nm_kab,propinsi.nm_prop, "
                        + "pasien.no_tlp,pasien.jk,pasien.email from pasien inner join kelurahan inner join kecamatan inner join kabupaten inner join propinsi on pasien.kd_prop=propinsi.kd_prop "
                        + "and pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis=? ");
                try {
                    ps.setString(1, tbPengaduan.getValueAt(tbPengaduan.
                            getSelectedRow(), 3).toString());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        CmbJk.setSelectedItem(rs.getString("jk"));
                        Alamat.setText(rs.getString("alamat"));
                        Kelurahan.setText(rs.getString("nm_kel"));
                        Kecamatan.setText(rs.getString("nm_kec"));
                        Kabupaten.setText(rs.getString("nm_kab"));
                        Propinsi.setText(rs.getString("nm_prop"));
                        NoTelp.setText(rs.getString("no_tlp"));
                        Email.setText(rs.getString("email"));
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
                System.out.println("Notif : " + e);
            }
            NoRMDicari.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 3).toString());
            TglDicari.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 2).toString());
            PasienDicari.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 4).toString());
            TNo.setText(tbPengaduan.getValueAt(tbPengaduan.getSelectedRow(), 3).
                    toString());
            NamaPasien.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 4).toString());
            TambahanPesan.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 5).toString());
            BalasanPesan.setText(tbPengaduan.getValueAt(tbPengaduan.
                    getSelectedRow(), 6).toString());
            riwayatpengaduan();
        }
    }

    private void isPhoto() {
        if (ChkAccor.isSelected() == true) {
            PanelDataDicari.setVisible(true);
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(700, HEIGHT));
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            PanelDataDicari.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void riwayatpengaduan() {
        if (!TNo.getText().isEmpty()) {
            try {
                ps = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(pengaduan.tanggal,'%d/%m/%y %H:%i:%s')as tanggal,pengaduan.pesan,pengaduan.id,balasan_pengaduan.pesan_balasan,DATE_FORMAT(balasan_pengaduan.tanggal,'%d/%m/%y %H:%i:%s') AS tanggal_balasan FROM pengaduan inner join balasan_pengaduan on pengaduan.id=balasan_pengaduan.id_pengaduan where pengaduan.no_rkm_medis=? ORDER BY tanggal DESC limit 30");
                try {
                    ps.setString(1, tbPengaduan.getValueAt(tbPengaduan.
                            getSelectedRow(), 3).toString());
                    rs = ps.executeQuery();
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><table border='0' width='100%'>");
                    while (rs.next()) {
                        sb.append("<tr>")
                                .append("<td align='left'><b>").append(
                                NamaPasien.getText()).append("</b><br>")
                                .append(rs.getString("pesan")).append("<br>")
                                .append(rs.getString("tanggal")).append(
                                "<br><br></td>")
                                .append("</tr>")
                                .append("<tr>")
                                .append("<td align='right'><b>You</b><br>")
                                .append(rs.getString("pesan_balasan")).append(
                                "<br>")
                                .append(rs.getString("tanggal_balasan")).append(
                                "<br><br></td>")
                                .append("</tr>");
                    }
                    sb.append("</table></html>");

                    // Set text to JEditorPane
                    RiwayatPengaduan.setText(sb.toString());

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
                System.out.println("Notif : " + e);
            }
        }
    }

//    private void panggilPhoto() {
//        if(TabData.isVisible()==true){
//            if(tbDokter.getSelectedRow()!= -1){
//                if((!Kd2.getText().isEmpty())&&(!Petugas.getText().isEmpty())){
//                     NoRawatDicari.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
//                     PasienDicari.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),1).toString());
//                     TglDicari.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),3).toString());
//                     JamDicari.setText(tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString());
//                     try {
//                        ps=koneksi.prepareStatement("select gambar_radiologi.lokasi_gambar from gambar_radiologi where gambar_radiologi.no_rawat=? and gambar_radiologi.tgl_periksa=? and gambar_radiologi.jam=? ");
//                        try {
//                            ps.setString(1,tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
//                            ps.setString(2,tbDokter.getValueAt(tbDokter.getSelectedRow(),3).toString());
//                            ps.setString(3,tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString());
//                            rs=ps.executeQuery();
//                            htmlContent = new StringBuilder();
//                            while(rs.next()){
//                                htmlContent.append("<tr><td border='0' align='center'><a href='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/radiologi/"+rs.getString("lokasi_gambar")+"'><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/radiologi/"+rs.getString("lokasi_gambar")+"' alt='photo' width='"+(internalFrame1.getWidth()-370)+"' height='"+(internalFrame1.getWidth()-370)+"'/></a></td></tr>");
//                            }
//                            LoadHTML.setText(
//                                "<html>"+
//                                  "<table width='100%' border='0' align='center' cellpadding='1px' cellspacing='1px' class='tbl_form'>"+
//                                    htmlContent.toString()+
//                                  "</table>"+
//                                "</html>"
//                            );
//                        } catch (Exception e) {
//                            System.out.println("Notif : "+e);
//                        } finally{
//                            if(rs!=null){
//                                rs.close();
//                            }
//                            if(ps!=null){
//                                ps.close();
//                            }
//                        }
//                        
//                        ps5=koneksi.prepareStatement("select hasil_radiologi.hasil from hasil_radiologi where hasil_radiologi.no_rawat=? and hasil_radiologi.tgl_periksa=? and hasil_radiologi.jam=?");  
//                        try {
//                            ps5.setString(1,tbDokter.getValueAt(tbDokter.getSelectedRow(),0).toString());
//                            ps5.setString(2,tbDokter.getValueAt(tbDokter.getSelectedRow(),3).toString());
//                            ps5.setString(3,tbDokter.getValueAt(tbDokter.getSelectedRow(),4).toString());
//                            rs5=ps5.executeQuery();
//                            if(rs5.next()){  
//                                HasilPeriksa.setText(rs5.getString("hasil"));
//                            }else{
//                                HasilPeriksa.setText("");
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Notif ps5 : "+e);
//                        } finally{
//                            if(rs5!=null){
//                                rs5.close();
//                            }
//                            if(ps5!=null){
//                                ps5.close();
//                            }
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Notif : "+e);
//                    } 
//                }else{
//                    NoRawatDicari.setText("");
//                    PasienDicari.setText("");
//                    TglDicari.setText("");
//                    JamDicari.setText("");
//                    LoadHTML.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'></font></center></body></html>");
//                    HasilPeriksa.setText("");
//                }
//            }
//        }
//    }
    private static final Logger LOG = Logger.getLogger(frmPengaduan.class.
            getName());
}
