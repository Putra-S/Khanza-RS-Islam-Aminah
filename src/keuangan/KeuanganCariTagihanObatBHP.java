package keuangan;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
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
import kepegawaian.DlgCariPetugas;

public class KeuanganCariTagihanObatBHP extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    /**
     *
     */
    public DlgCariPetugas pegawai = new DlgCariPetugas(null, false);

    private PreparedStatement ps, ps2;

    private ResultSet rs, rs2;

    private String notagihan = "", tanggal = "", status = "", petugas = "", cari = "";

    private double nilaitagihan = 0, totaltagihan = 0;

    private int i = 0;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public KeuanganCariTagihanObatBHP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No.Tagihan", "Tanggal", "Diajukan Oleh", "Keteragan", "Status Tagihan"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(380);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        NoTagihan.setDocument(new batasInput((byte) 20).getKata(NoTagihan));
        KdPeg.setDocument(new batasInput((byte) 20).getKata(KdPeg));
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

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pegawai.getTable().getSelectedRow() != -1) {
                    KdPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                    NmPeg.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());
                }
                KdPeg.requestFocus();
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

        ChkInput.setSelected(false);
        isForm();

        ChkAccor.setSelected(false);
        isMenu();
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
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label15 = new widget.Label();
        NoTagihan = new widget.TextBox();
        label16 = new widget.Label();
        KdPeg = new widget.TextBox();
        NmPeg = new widget.TextBox();
        btnPegawai = new widget.Button();
        label14 = new widget.Label();
        Status = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        label12 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        ScrollMenu = new widget.ScrollPane();
        FormMenu = new widget.PanelBiasa();
        MnHapusTagihan = new widget.Button();
        MnDitagihkan = new widget.Button();
        MnDitagihkan1 = new widget.Button();
        MnDibayar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Cari Titip Faktur/Tagihan Obat & BHP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        tbDokter.setToolTipText("Silahkan klik pada nomor tagihan untuk verifikasi pilihan");
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 65));
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

        label15.setText("No.Tagihan :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label15);
        label15.setBounds(0, 10, 81, 23);

        NoTagihan.setName("NoTagihan"); // NOI18N
        NoTagihan.setPreferredSize(new java.awt.Dimension(207, 23));
        NoTagihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTagihanKeyPressed(evt);
            }
        });
        FormInput.add(NoTagihan);
        NoTagihan.setBounds(85, 10, 170, 23);

        label16.setText("Petugas :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label16);
        label16.setBounds(515, 10, 70, 23);

        KdPeg.setEditable(false);
        KdPeg.setName("KdPeg"); // NOI18N
        KdPeg.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdPeg);
        KdPeg.setBounds(589, 10, 90, 23);

        NmPeg.setEditable(false);
        NmPeg.setName("NmPeg"); // NOI18N
        NmPeg.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPeg);
        NmPeg.setBounds(681, 10, 200, 23);

        btnPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPegawai.setMnemonic('1');
        btnPegawai.setToolTipText("Alt+1");
        btnPegawai.setName("btnPegawai"); // NOI18N
        btnPegawai.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegawaiActionPerformed(evt);
            }
        });
        FormInput.add(btnPegawai);
        btnPegawai.setBounds(884, 10, 28, 23);

        label14.setText("Status :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(285, 10, 60, 23);

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Ditagihkan", "Dibayar" }));
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(40, 23));
        FormInput.add(Status);
        Status.setBounds(349, 10, 140, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(53, 23));
        panelisi1.add(label11);

        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tanggal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal1KeyPressed(evt);
            }
        });
        panelisi1.add(Tanggal1);

        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("s.d.");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(25, 23));
        panelisi1.add(label12);

        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setMinimumSize(new java.awt.Dimension(90, 23));
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tanggal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal2KeyPressed(evt);
            }
        });
        panelisi1.add(Tanggal2);

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(150, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

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
        panelisi1.add(BtnCari);

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
        panelisi1.add(BtnAll);

        label9.setText("Total :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(40, 23));
        panelisi1.add(label9);

        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(105, 23));
        panelisi1.add(LTotal);

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

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(135, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setBackground(new java.awt.Color(255,250,250));
        ChkAccor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 255, 248)));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.EAST);

        ScrollMenu.setBorder(null);
        ScrollMenu.setName("ScrollMenu"); // NOI18N
        ScrollMenu.setOpaque(true);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 100));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

        MnHapusTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        MnHapusTagihan.setText("Hapus Tagihan");
        MnHapusTagihan.setFocusPainted(false);
        MnHapusTagihan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTagihan.setGlassColor(new java.awt.Color(255, 255, 255));
        MnHapusTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTagihan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MnHapusTagihan.setName("MnHapusTagihan"); // NOI18N
        MnHapusTagihan.setPreferredSize(new java.awt.Dimension(120, 23));
        MnHapusTagihan.setRoundRect(false);
        MnHapusTagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTagihanActionPerformed(evt);
            }
        });
        FormMenu.add(MnHapusTagihan);

        MnDitagihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        MnDitagihkan.setText("Status Ditagihkan");
        MnDitagihkan.setFocusPainted(false);
        MnDitagihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDitagihkan.setGlassColor(new java.awt.Color(255, 255, 255));
        MnDitagihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDitagihkan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MnDitagihkan.setName("MnDitagihkan"); // NOI18N
        MnDitagihkan.setPreferredSize(new java.awt.Dimension(120, 23));
        MnDitagihkan.setRoundRect(false);
        MnDitagihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDitagihkanActionPerformed(evt);
            }
        });
        FormMenu.add(MnDitagihkan);

        MnDitagihkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        MnDitagihkan1.setText("Status Dibayar");
        MnDitagihkan1.setFocusPainted(false);
        MnDitagihkan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDitagihkan1.setGlassColor(new java.awt.Color(255, 255, 255));
        MnDitagihkan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDitagihkan1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MnDitagihkan1.setName("MnDitagihkan1"); // NOI18N
        MnDitagihkan1.setPreferredSize(new java.awt.Dimension(120, 23));
        MnDitagihkan1.setRoundRect(false);
        MnDitagihkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDitagihkan1ActionPerformed(evt);
            }
        });
        FormMenu.add(MnDitagihkan1);

        MnDibayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        MnDibayar.setText("Bayar Tagihan");
        MnDibayar.setFocusPainted(false);
        MnDibayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibayar.setGlassColor(new java.awt.Color(255, 255, 255));
        MnDibayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibayar.setMargin(new java.awt.Insets(1, 1, 1, 1));
        MnDibayar.setName("MnDibayar"); // NOI18N
        MnDibayar.setPreferredSize(new java.awt.Dimension(120, 23));
        MnDibayar.setRoundRect(false);
        MnDibayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibayarActionPerformed(evt);
            }
        });
        FormMenu.add(MnDibayar);

        ScrollMenu.setViewportView(FormMenu);

        PanelAccor.add(ScrollMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.WEST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        pegawai.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, BtnCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed
    /*
 * private void KdKeyPressed(java.awt.event.KeyEvent evt) { Valid.pindah(evt,BtnCari,Nm);
 * }
     */
//GEN-FIRST:event_TKdKeyPressed
    private void btnPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_TKdKeyPressed
        pegawai.isCek();//GEN-FIRST:event_btnPegawaiActionPerformed
        pegawai.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_btnPegawaiActionPerformed

    private void Tanggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal1KeyPressed
        Valid.pindah(evt, NoTagihan, KdPeg);
    }//GEN-LAST:event_Tanggal1KeyPressed

    private void NoTagihanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTagihanKeyPressed
        Valid.pindah(evt, BtnKeluar, KdPeg);
    }//GEN-LAST:event_NoTagihanKeyPressed

    private void Tanggal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal2KeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDokter.requestFocus();
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
        NoTagihan.setText("");
        KdPeg.setText("");
        NmPeg.setText("");
        KdPeg.setText("");
        NmPeg.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.queryu("delete from temporary where temp37='" + akses.
                    getalamatip() + "'");

            int row = tabMode.getRowCount();
            for (i = 0; i < row; i++) {
                Sequel.menyimpan("temporary", "'" + i + "','"
                        + tabMode.getValueAt(i, 0).toString() + "','"
                        + tabMode.getValueAt(i, 1).toString() + "','"
                        + tabMode.getValueAt(i, 2).toString() + "','"
                        + tabMode.getValueAt(i, 3).toString() + "','"
                        + tabMode.getValueAt(i, 4).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                        getalamatip() + "'",
                        "Transaksi Penagihan Piutang Pasien");
            }
            i++;
            Sequel.menyimpan("temporary",
                    "'" + i + "','TOTAL TAGIHAN :','','','','" + Valid.SetAngka(
                            totaltagihan) + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','" + akses.
                            getalamatip() + "'",
                    "Transaksi Penagihan Piutang Pasien");
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            Valid.MyReportqry("rptTagihanObatBHP.jasper", "report",
                    "::[ Data Titip Faktur/Tagihan Obat & BHP ]::",
                    "select * from temporary where temporary.temp37='" + akses.
                            getalamatip() + "' order by temporary.no", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnAll, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void MnHapusTagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTagihanActionPerformed
        if (tbDokter.getSelectedRow() > -1) {
            if (tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString().
                    trim().isEmpty()) {
                Valid.textKosong(TCari, "pilihan data");
            } else {
                try {
                    ps = koneksi.prepareStatement(
                            "select detail_titip_faktur.no_faktur from detail_titip_faktur where detail_titip_faktur.no_tagihan=?");
                    try {
                        ps.setString(1, tbDokter.getValueAt(tbDokter.
                                getSelectedRow(), 0).toString().trim());
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            Sequel.queryu(
                                    "update pemesanan set status='Belum Lunas' where no_faktur=?",
                                    rs.getString("no_faktur"));
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
                    Sequel.queryu("delete from titip_faktur where no_tagihan=?",
                            tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).
                                    toString().trim());
                    tampil();
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            }
        }
    }//GEN-LAST:event_MnHapusTagihanActionPerformed

    private void MnDitagihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDitagihkanActionPerformed
        if (tbDokter.getSelectedRow() > -1) {
            if (tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString().
                    trim().isEmpty()) {
                Valid.textKosong(TCari, "pilihan data");
            } else {
                Sequel.queryu(
                        "update titip_faktur set status='Ditagihkan' where no_tagihan=?",
                        tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).
                                toString().trim());
                tampil();
            }
        }
    }//GEN-LAST:event_MnDitagihkanActionPerformed

    private void MnDibayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibayarActionPerformed
        if (tbDokter.getSelectedRow() > -1) {
            if (tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString().
                    trim().isEmpty()) {
                Valid.textKosong(TCari, "pilihan data");
            } else {
                if (Sequel.cariIsi(
                        "select status from titip_faktur where no_tagihan=?",
                        tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).
                                toString()).equals("Ditagihkan")) {
                    this.setCursor(Cursor.
                            getPredefinedCursor(Cursor.WAIT_CURSOR));
                    KeuanganHutangObatBelumLunas form = new KeuanganHutangObatBelumLunas(
                            null, false);
                    form.isCek();
                    form.tampilTagihan(tbDokter.getValueAt(tbDokter.
                            getSelectedRow(), 0).toString());
                    form.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                            getHeight() - 20);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    tbDokter.setValueAt("Dibayar", tbDokter.getSelectedRow(), 4);
                    JOptionPane.showMessageDialog(null,
                            "Tagihan sudah dibayar..!!!");
                }
            }
        }
    }//GEN-LAST:event_MnDibayarActionPerformed

    private void MnDitagihkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDitagihkan1ActionPerformed
        if (tbDokter.getSelectedRow() > -1) {
            if (tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString().
                    trim().isEmpty()) {
                Valid.textKosong(TCari, "pilihan data");
            } else {
                Sequel.queryu(
                        "update titip_faktur set status='Dibayar' where no_tagihan=?",
                        tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).
                                toString().trim());
                tampil();
            }
        }
    }//GEN-LAST:event_MnDitagihkan1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            KeuanganCariTagihanObatBHP dialog = new KeuanganCariTagihanObatBHP(new javax.swing.JFrame(), true);
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
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox KdPeg;
    private widget.Label LTotal;
    private widget.Button MnDibayar;
    private widget.Button MnDitagihkan;
    private widget.Button MnDitagihkan1;
    private widget.Button MnHapusTagihan;
    private widget.TextBox NmPeg;
    private widget.TextBox NoTagihan;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane ScrollMenu;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.Button btnPegawai;
    private widget.InternalFrame internalFrame1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            notagihan = "";
            status = "";
            petugas = "";
            cari = "";
            if (!NoTagihan.getText().trim().isEmpty()) {
                notagihan = " and titip_faktur.no_tagihan like '%" + NoTagihan.getText() + "%' ";
            }

            tanggal = " titip_faktur.tanggal between '" + Valid.SetTgl(Tanggal1.getSelectedItem() + "") + "' and '"
                    + Valid.SetTgl(Tanggal2.getSelectedItem() + "") + "' ";

            if (!Status.getSelectedItem().toString().equals("Semua")) {
                status = " and titip_faktur.status like '%" + Status.getSelectedItem().toString() + "%' ";
            }

            if (!NmPeg.getText().trim().isEmpty()) {
                petugas = " and concat(titip_faktur.nip,petugas.nama) like '%" + KdPeg.getText() + NmPeg.getText()
                        + "%' ";
            }

            if (!TCari.getText().trim().isEmpty()) {
                cari = " and (titip_faktur.no_tagihan like '%" + TCari.getText() + "%' or petugas.nama like '%"
                        + TCari.getText() + "%' or titip_faktur.keterangan like '%" + TCari.getText()
                        + "%' or detail_titip_faktur.no_faktur like '%" + TCari.getText()
                        + "%' or datasuplier.nama_suplier like '%" + TCari.getText() + "%' ) ";
            }

            ps = koneksi.prepareStatement(
                    "select titip_faktur.no_tagihan,titip_faktur.tanggal,titip_faktur.nip,petugas.nama as petugas,"
                    + "titip_faktur.keterangan,titip_faktur.status from titip_faktur "
                    + "inner join petugas on petugas.nip=titip_faktur.nip "
                    + "inner join detail_titip_faktur on detail_titip_faktur.no_tagihan=titip_faktur.no_tagihan "
                    + "inner join pemesanan on detail_titip_faktur.no_faktur=pemesanan.no_faktur "
                    + "inner join datasuplier on datasuplier.kode_suplier=pemesanan.kode_suplier " + "where "
                    + tanggal + status + notagihan + petugas + cari
                    + " group by titip_faktur.no_tagihan order by titip_faktur.tanggal");
            try {
                rs = ps.executeQuery();
                totaltagihan = 0;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{rs.getString("no_tagihan"), rs.getString("tanggal"),
                        rs.getString("nip") + " " + rs.getString("petugas"), rs.getString("keterangan"),
                        rs.getString("status")});
                    tabMode.addRow(new Object[]{"", "Tgl.Tempo", "No.Faktur", "Nama Suplier", "Sisa Hutang"});
                    ps2 = koneksi.prepareStatement(
                            "select pemesanan.tgl_tempo,pemesanan.no_faktur,pemesanan.kode_suplier,datasuplier.nama_suplier,pemesanan.tagihan,"
                            + "(SELECT ifnull(SUM(besar_bayar),0) FROM bayar_pemesanan where bayar_pemesanan.no_faktur=pemesanan.no_faktur) as bayar from titip_faktur "
                            + "inner join petugas on petugas.nip=titip_faktur.nip "
                            + "inner join detail_titip_faktur on detail_titip_faktur.no_tagihan=titip_faktur.no_tagihan "
                            + "inner join pemesanan on detail_titip_faktur.no_faktur=pemesanan.no_faktur "
                            + "inner join datasuplier on datasuplier.kode_suplier=pemesanan.kode_suplier "
                            + "where detail_titip_faktur.no_tagihan='" + rs.getString("no_tagihan") + "' and "
                            + tanggal + status + notagihan + petugas + cari + " order by titip_faktur.tanggal");
                    try {
                        nilaitagihan = 0;
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            nilaitagihan += (rs2.getDouble("tagihan") - rs2.getDouble("bayar"));
                            totaltagihan += (rs2.getDouble("tagihan") - rs2.getDouble("bayar"));
                            tabMode.addRow(new Object[]{"", rs2.getString("tgl_tempo"), rs2.getString("no_faktur"),
                                rs2.getString("kode_suplier") + " " + rs2.getString("nama_suplier"),
                                Valid.SetAngka((rs2.getDouble("tagihan") - rs2.getDouble("bayar")))});
                        }
                        if (nilaitagihan > 0) {
                            tabMode
                                    .addRow(new Object[]{"", "", "Jumlah Hutang : ", "", Valid.SetAngka(nilaitagihan)});
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                LTotal.setText(Valid.SetAngka(totaltagihan));
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

    }

    /**
     *
     */
    public void emptTeks() {
        TCari.setText("");
        NoTagihan.setText("");
        KdPeg.setText("");
        NmPeg.setText("");
        TCari.requestFocus();
    }

    /**
     *
     */
    public void isCek() {
        TCari.requestFocus();
        MnHapusTagihan.setEnabled(akses.gettagihan_hutang_obat());
        MnDitagihkan.setEnabled(akses.gettagihan_hutang_obat());
        MnDibayar.setEnabled(akses.getbayar_pemesanan_obat());
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 65));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(135, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private static final Logger LOG = Logger.getLogger(KeuanganCariTagihanObatBHP.class.getName());

}
