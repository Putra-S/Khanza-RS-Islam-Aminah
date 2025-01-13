package keuangan;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
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

/**
 * @author Kanit SIRS
 */
public class DlgRBObatAll extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private PreparedStatement pspoli, pspenjab, psobat;

    private ResultSet rspoli, rspenjab, rsobat;

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

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgRBObatAll(java.awt.Frame parent, boolean modal) {
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

        kdpoli.setDocument(new batasInput((byte) 8).getKata(kdpoli));

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (poli.getTable().getSelectedRow() != -1) {
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    // nmpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                }
                kdpoli.requestFocus();
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

        asalstok.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (asalstok.getTable().getSelectedRow() != -1) {
                    kdasal.setText(asalstok.getTable().getValueAt(asalstok.getTable().getSelectedRow(), 0).toString());
                    nmasal.setText(asalstok.getTable().getValueAt(asalstok.getTable().getSelectedRow(), 1).toString());
                }
                kdasal.requestFocus();
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

        asalstok.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    asalstok.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        jenis.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (jenis.getTable().getSelectedRow() != -1) {
                    kdjenis.setText(jenis.getTable().getValueAt(jenis.getTable().getSelectedRow(), 0).toString());
                    nmjns.setText(jenis.getTable().getValueAt(jenis.getTable().getSelectedRow(), 1).toString());
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

        golongan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (golongan.getTable().getSelectedRow() != -1) {
                    kdgolongan
                            .setText(golongan.getTable().getValueAt(golongan.getTable().getSelectedRow(), 0).toString());
                    nmgolongan
                            .setText(golongan.getTable().getValueAt(golongan.getTable().getSelectedRow(), 1).toString());
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
                golongan.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        kategori.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kategori.getTable().getSelectedRow() != -1) {
                    kdkategori
                            .setText(kategori.getTable().getValueAt(kategori.getTable().getSelectedRow(), 0).toString());
                    nmkategori
                            .setText(kategori.getTable().getValueAt(kategori.getTable().getSelectedRow(), 1).toString());
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
                kategori.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        barang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRBObatAll")) {
                    if (barang.getTable().getSelectedRow() != -1) {
                        nmobat.setText(barang.getTable().getValueAt(barang.getTable().getSelectedRow(), 2).toString());
                    }
                    nmobat.requestFocus();
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

        barang.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRBObatAll")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        barang.dispose();
                    }
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
        BtnExcel = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label19 = new widget.Label();
        nmpenjab = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        label20 = new widget.Label();
        nmasal = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        label21 = new widget.Label();
        nmjns = new widget.TextBox();
        BtnJenis = new widget.Button();
        label22 = new widget.Label();
        nmkategori = new widget.TextBox();
        BtnKategori = new widget.Button();
        label23 = new widget.Label();
        nmgolongan = new widget.TextBox();
        BtnGolongan = new widget.Button();
        label24 = new widget.Label();
        nmobat = new widget.TextBox();
        BtnObat = new widget.Button();

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
        panelisi1.add(BtnExcel);

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
        label19.setBounds(410, 10, 65, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(168, 23));
        FormInput.add(nmpenjab);
        nmpenjab.setBounds(480, 10, 150, 23);

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
        BtnSeek3.setBounds(640, 10, 28, 23);

        label20.setText("Asal Stok :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput.add(label20);
        label20.setBounds(670, 10, 60, 23);

        nmasal.setEditable(false);
        nmasal.setName("nmasal"); // NOI18N
        nmasal.setPreferredSize(new java.awt.Dimension(215, 23));
        FormInput.add(nmasal);
        nmasal.setBounds(740, 10, 150, 23);

        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('3');
        BtnSeek4.setToolTipText("Alt+3");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek4);
        BtnSeek4.setBounds(890, 10, 28, 23);

        label21.setText("Jenis :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(40, 23));
        FormInput.add(label21);
        label21.setBounds(0, 40, 43, 23);

        nmjns.setEditable(false);
        nmjns.setName("nmjns"); // NOI18N
        nmjns.setPreferredSize(new java.awt.Dimension(205, 23));
        FormInput.add(nmjns);
        nmjns.setBounds(46, 40, 150, 23);

        BtnJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnJenis.setMnemonic('2');
        BtnJenis.setToolTipText("Alt+2");
        BtnJenis.setName("BtnJenis"); // NOI18N
        BtnJenis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJenisActionPerformed(evt);
            }
        });
        FormInput.add(BtnJenis);
        BtnJenis.setBounds(199, 40, 28, 23);

        label22.setText("Kategori :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label22);
        label22.setBounds(410, 40, 65, 23);

        nmkategori.setEditable(false);
        nmkategori.setName("nmkategori"); // NOI18N
        nmkategori.setPreferredSize(new java.awt.Dimension(205, 23));
        FormInput.add(nmkategori);
        nmkategori.setBounds(480, 40, 150, 23);

        BtnKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKategori.setMnemonic('2');
        BtnKategori.setToolTipText("Alt+2");
        BtnKategori.setName("BtnKategori"); // NOI18N
        BtnKategori.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKategoriActionPerformed(evt);
            }
        });
        FormInput.add(BtnKategori);
        BtnKategori.setBounds(640, 40, 28, 23);

        label23.setText("Golongan :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label23);
        label23.setBounds(670, 40, 60, 23);

        nmgolongan.setEditable(false);
        nmgolongan.setName("nmgolongan"); // NOI18N
        nmgolongan.setPreferredSize(new java.awt.Dimension(205, 23));
        FormInput.add(nmgolongan);
        nmgolongan.setBounds(740, 40, 150, 23);

        BtnGolongan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnGolongan.setMnemonic('2');
        BtnGolongan.setToolTipText("Alt+2");
        BtnGolongan.setName("BtnGolongan"); // NOI18N
        BtnGolongan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnGolongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGolonganActionPerformed(evt);
            }
        });
        FormInput.add(BtnGolongan);
        BtnGolongan.setBounds(890, 40, 28, 23);

        label24.setText("Obat :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label24);
        label24.setBounds(0, 10, 43, 23);

        nmobat.setEditable(false);
        nmobat.setName("nmobat"); // NOI18N
        nmobat.setPreferredSize(new java.awt.Dimension(205, 23));
        FormInput.add(nmobat);
        nmobat.setBounds(46, 10, 330, 23);

        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat.setMnemonic('2');
        BtnObat.setToolTipText("Alt+2");
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnObat);
        BtnObat.setBounds(380, 10, 28, 23);

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
        nmasal.setText("");
        kdjenis.setText("");
        nmjns.setText("");
        kdkategori.setText("");
        nmkategori.setText("");
        kdgolongan.setText("");
        nmgolongan.setText("");
        nmobat.setText("");
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

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        asalstok.isCek();
        asalstok.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        asalstok.setLocationRelativeTo(internalFrame1);
        asalstok.setAlwaysOnTop(false);
        asalstok.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void BtnJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJenisActionPerformed
        jenis.isCek();
        jenis.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        jenis.setLocationRelativeTo(internalFrame1);
        jenis.setAlwaysOnTop(false);
        jenis.setVisible(true);
    }//GEN-LAST:event_BtnJenisActionPerformed

    private void BtnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKategoriActionPerformed
        kategori.isCek();
        kategori.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        kategori.setLocationRelativeTo(internalFrame1);
        kategori.setAlwaysOnTop(false);
        kategori.setVisible(true);
    }//GEN-LAST:event_BtnKategoriActionPerformed

    private void BtnGolonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGolonganActionPerformed
        golongan.isCek();
        golongan.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        golongan.setLocationRelativeTo(internalFrame1);
        golongan.setAlwaysOnTop(false);
        golongan.setVisible(true);
    }//GEN-LAST:event_BtnGolonganActionPerformed

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

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        akses.setform("DlgRBObatAll");
        barang.emptTeks();
        barang.isCek();
        barang.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        barang.setLocationRelativeTo(internalFrame1);
        barang.setAlwaysOnTop(false);
        barang.setVisible(true);
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcelActionPerformed
        Valid.exportToExcel(tbDokter);
    }//GEN-LAST:event_BtnExcelActionPerformed

    private void BtnExcelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnExcelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnExcelKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRBObatAll dialog = new DlgRBObatAll(new javax.swing.JFrame(), true);
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
    private widget.Button BtnExcel;
    private widget.Button BtnGolongan;
    private widget.Button BtnJenis;
    private widget.Button BtnKategori;
    private widget.Button BtnKeluar;
    private widget.Button BtnObat;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
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
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.TextBox nmasal;
    private widget.TextBox nmgolongan;
    private widget.TextBox nmjns;
    private widget.TextBox nmkategori;
    private widget.TextBox nmobat;
    private widget.TextBox nmpenjab;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode);
            pspoli = koneksi.prepareStatement("select distinct status from detail_pemberian_obat");
            try {
                rspoli = pspoli.executeQuery();
                i = 1;
                ttlbiaya = 0;
                ttlembalase = 0;
                ttltuslah = 0;
                ttltotal = 0;
                while (rspoli.next()) {
                    tabMode.addRow(new Object[]{i + ". ", rspoli.getString(1), "", "", "", "", "", ""});
                    pspenjab.setString(1, "%" + kdpenjab.getText() + "%");
                    rspenjab = pspenjab.executeQuery();
                    a = 1;
                    while (rspenjab.next()) {
                        tabMode.addRow(new Object[]{"", a + ". " + rspenjab.getString(2), "", "", "", "", "", ""});
                        if (nmjns.getText().isEmpty() && nmkategori.getText().isEmpty()
                                && nmgolongan.getText().isEmpty() && nmasal.getText().isEmpty()
                                && nmpenjab.getText().isEmpty() && nmobat.getText().isEmpty()) {
                            psobat = koneksi.prepareStatement(
                                    "select detail_pemberian_obat.kode_brng,databarang.nama_brng,sum(detail_pemberian_obat.jml) as jml,"
                                    + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as biaya,"
                                    + "sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah,"
                                    + "sum(detail_pemberian_obat.total) as total "
                                    + "from detail_pemberian_obat inner join reg_periksa on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                                    + "inner join databarang on detail_pemberian_obat.kode_brng=databarang.kode_brng "
                                    + "where detail_pemberian_obat.tgl_perawatan between ? and ? and detail_pemberian_obat.status=? group by detail_pemberian_obat.kode_brng order by databarang.nama_brng");
                        } else {
                            psobat = koneksi.prepareStatement(
                                    "select detail_pemberian_obat.kode_brng,databarang.nama_brng,sum(detail_pemberian_obat.jml) as jml,"
                                    + "(sum(detail_pemberian_obat.total)-sum(detail_pemberian_obat.embalase+detail_pemberian_obat.tuslah)) as biaya,"
                                    + "sum(detail_pemberian_obat.embalase) as embalase, sum(detail_pemberian_obat.tuslah) as tuslah,"
                                    + "sum(detail_pemberian_obat.total) as total "
                                    + "from detail_pemberian_obat inner join reg_periksa on detail_pemberian_obat.no_rawat=reg_periksa.no_rawat "
                                    + "inner join databarang on detail_pemberian_obat.kode_brng=databarang.kode_brng "
                                    + "inner join jenis on databarang.kdjns=jenis.kdjns "
                                    + "inner join kategori_barang on kategori_barang.kode=databarang.kode_kategori "
                                    + "inner join golongan_barang on golongan_barang.kode=databarang.kode_golongan "
                                    + "inner join bangsal on detail_pemberian_obat.kd_bangsal=bangsal.kd_bangsal "
                                    + "inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                                    + "where detail_pemberian_obat.tgl_perawatan between ? and ? and detail_pemberian_obat.status=? "
                                    + "and concat(databarang.kdjns,jenis.nama) like ? and concat(databarang.kode_kategori,kategori_barang.nama) like ? "
                                    + "and concat(databarang.kode_golongan,golongan_barang.nama) like ? and concat(detail_pemberian_obat.kd_bangsal,bangsal.nm_bangsal) like ? "
                                    + "and concat(reg_periksa.kd_pj,penjab.png_jawab) like ? and databarang.nama_brng like ? group by detail_pemberian_obat.kode_brng order by databarang.nama_brng");
                        }

                        try {
                            if (nmjns.getText().isEmpty() && nmkategori.getText().isEmpty()
                                    && nmgolongan.getText().isEmpty() && nmasal.getText().isEmpty()
                                    && nmpenjab.getText().isEmpty() && nmobat.getText().isEmpty()) {
                                psobat.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                                psobat.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                                psobat.setString(3, rspoli.getString(1));
                            } else {
                                psobat.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                                psobat.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                                psobat.setString(3, rspoli.getString(1));
                                psobat.setString(4, "%" + kdjenis.getText() + nmjns.getText() + "%");
                                psobat.setString(5, "%" + kdkategori.getText() + nmkategori.getText() + "%");
                                psobat.setString(6, "%" + kdgolongan.getText() + nmgolongan.getText() + "%");
                                psobat.setString(7, "%" + kdasal.getText() + nmasal.getText() + "%");
                                psobat.setString(8, "%" + kdpenjab.getText() + nmpenjab.getText() + "%");
                                psobat.setString(9, "%" + nmobat.getText() + "%");
                            }

                            rsobat = psobat.executeQuery();
                            jmlbiaya = 0;
                            jmlembalase = 0;
                            jmltotal = 0;
                            jmltuslah = 0;
                            if (rsobat.next()) {
                                rsobat.beforeFirst();
                                while (rsobat.next()) {
                                    tabMode.addRow(new Object[]{"", "", rsobat.getString(3),
                                        rsobat.getString(1) + " " + rsobat.getString(2),
                                        Valid.SetAngka(rsobat.getDouble(4)), Valid.SetAngka(rsobat.getDouble(5)),
                                        Valid.SetAngka(rsobat.getDouble(6)), Valid.SetAngka(rsobat.getDouble(7))});
                                    jmlbiaya += rsobat.getDouble(4);
                                    ttlbiaya += rsobat.getDouble(4);
                                    jmlembalase += rsobat.getDouble(5);
                                    ttlembalase += rsobat.getDouble(5);
                                    jmltuslah += rsobat.getDouble(6);
                                    ttltuslah += rsobat.getDouble(6);
                                    jmltotal += rsobat.getDouble(7);
                                    ttltotal += rsobat.getDouble(7);
                                }
                            }

                            if (jmltotal > 0) {
                                tabMode.addRow(new Object[]{"", "", "", "Subtotal :", Valid.SetAngka(jmlbiaya),
                                    Valid.SetAngka(jmlembalase), Valid.SetAngka(jmltuslah),
                                    Valid.SetAngka(jmltotal)});
                            }
                            a++;
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsobat != null) {
                                rsobat.close();
                            }
                            if (psobat != null) {
                                psobat.close();
                            }
                        }
                        // }
                        i++;
                        // } catch (Exception e) {
                        // System.out.println("Notif : "+e);
                        // } finally{
                        // if(rsdokter!=null){
                        // rsdokter.close();
                        // }
                        // if(psdokter!=null){
                        // psdokter.close();
                        // }
                        // }
                    }

                }
                tabMode.addRow(new Object[]{">>", "Total ", ":", "", Valid.SetAngka(ttlbiaya),
                    Valid.SetAngka(ttlembalase), Valid.SetAngka(ttltuslah), Valid.SetAngka(ttltotal)});
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

    private static final Logger LOG = Logger.getLogger(DlgRBObatAll.class.getName());

}
