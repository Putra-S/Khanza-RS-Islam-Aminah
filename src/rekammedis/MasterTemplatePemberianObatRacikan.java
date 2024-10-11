package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.WarnaTable2;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import inventory.DlgCariAturanPakai;
import inventory.DlgCariMetodeRacik;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;

/**
 *
 * @author Kanit SIRS
 */
public class MasterTemplatePemberianObatRacikan extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeObatRacikan, tabModeDetailObatRacikan;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private int i, index = 0, jml = 0, r = 0;
    private String[] kode, nama, ciripny, keterangan, kategori, cirium, satuan, no;
    private double[] jumlah, p1, p2, kps;
    private WarnaTable2 warna2 = new WarnaTable2();
    private WarnaTable2 warna3 = new WarnaTable2();
    private File file;
    private FileWriter fileWriter;
    private String iyem, noracik = "";
    private double jumlahracik = 0, persenracik = 0, kapasitasracik = 0;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    private DlgCariMetodeRacik metoderacik = new DlgCariMetodeRacik(null, false);
    private DlgCariAturanPakai aturanpakai = new DlgCariAturanPakai(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private StringBuilder htmlContent;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public MasterTemplatePemberianObatRacikan(java.awt.Frame parent,
            boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No.Template", "Kode Dokter", "Nama Dokter",
            "Nama Paket"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(200);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeObatRacikan = new DefaultTableModel(null, new Object[]{
            "No", "Nama Racikan", "Kode Racik", "Metode Racik", "Jml.Racik",
            "Aturan Pakai", "Keterangan"
        }) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = true;
                if ((colIndex == 0) || (colIndex == 2) || (colIndex == 3)) {
                    a = false;
                }
                return a;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };

        tbObatRacikan.setModel(tabModeObatRacikan);
        tbObatRacikan.
                setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObatRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbObatRacikan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(25);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            }
        }

        warna2.kolom = 4;
        tbObatRacikan.setDefaultRenderer(Object.class, warna2);

        tabModeDetailObatRacikan = new DefaultTableModel(null, new Object[]{
            "No", "Kode Barang", "Nama Barang", "Satuan", "Jenis Obat",
            "Kps", "P1", "/", "P2", "Kandungan", "Jml", "I.F.", "Komposisi"
        }) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class,
                java.lang.Double.class, java.lang.Object.class,
                java.lang.Double.class,
                java.lang.Object.class, java.lang.Double.class,
                java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 6) || (colIndex == 8) || (colIndex == 9) || (colIndex == 10)) {
                    a = true;
                }
                return a;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };

        tbDetailObatRacikan.setModel(tabModeDetailObatRacikan);
        tbDetailObatRacikan.setPreferredScrollableViewportSize(
                new Dimension(500, 500));
        tbDetailObatRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbDetailObatRacikan.getColumnModel().getColumn(
                    i);
            if (i == 0) {
                column.setPreferredWidth(25);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(240);
            } else if (i == 3) {
                column.setPreferredWidth(45);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(25);
            } else if (i == 7) {
                column.setMinWidth(11);
                column.setMaxWidth(11);
            } else if (i == 8) {
                column.setPreferredWidth(25);
            } else if (i == 9) {
                column.setPreferredWidth(60);
            } else if (i == 10) {
                column.setPreferredWidth(40);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(150);
            }
        }

        warna3.kolom = 9;
        tbDetailObatRacikan.setDefaultRenderer(Object.class, warna3);

        Kd.setDocument(new batasInput((byte) 20).getKata(Kd));
        CariObatRacikan.setDocument(new batasInput((byte) 100).getKata(
                CariObatRacikan));

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

        aturanpakai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (aturanpakai.getTable().getSelectedRow() != -1) {
                    tbObatRacikan.setValueAt(aturanpakai.getTable().getValueAt(
                            aturanpakai.getTable().getSelectedRow(), 0).
                            toString(), tbObatRacikan.getSelectedRow(), 5);
                    tbObatRacikan.requestFocus();
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

        metoderacik.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (metoderacik.getTable().getSelectedRow() != -1) {
                    tbObatRacikan.setValueAt(metoderacik.getTable().getValueAt(
                            metoderacik.getTable().getSelectedRow(), 1).
                            toString(), tbObatRacikan.getSelectedRow(), 2);
                    tbObatRacikan.setValueAt(metoderacik.getTable().getValueAt(
                            metoderacik.getTable().getSelectedRow(), 2).
                            toString(), tbObatRacikan.getSelectedRow(), 3);
                    tbObatRacikan.requestFocus();
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

        metoderacik.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    metoderacik.dispose();
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
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.
                            getTable().getSelectedRow(), 0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.
                            getTable().getSelectedRow(), 1).toString());
                }
                KdDokter.requestFocus();
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

        ChkAccor.setSelected(false);
        isDetail();

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        label12 = new widget.Label();
        Kd = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel20 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        tbObatRacikan = new widget.Table();
        CariObatRacikan = new widget.TextBox();
        BtnCariObatRacikan = new widget.Button();
        Scroll11 = new widget.ScrollPane();
        tbDetailObatRacikan = new widget.Table();
        BtnAllObatRacikan = new widget.Button();
        BtnTambah1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        label13 = new widget.Label();
        NmResep = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelGlass9 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormDetail = new widget.PanelBiasa();
        Scroll13 = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSemua.setText("Pilih Semua");
        ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSemua.setName("ppSemua"); // NOI18N
        ppSemua.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Master Template Pemberian Obat Dokter Racikan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(744, 471));
        FormInput.setLayout(null);

        label12.setText("No.Template :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(75, 23));
        FormInput.add(label12);
        label12.setBounds(0, 10, 85, 23);

        Kd.setName("Kd"); // NOI18N
        Kd.setPreferredSize(new java.awt.Dimension(207, 23));
        Kd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKeyPressed(evt);
            }
        });
        FormInput.add(Kd);
        Kd.setBounds(89, 10, 150, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(230, 10, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(304, 10, 120, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(426, 10, 260, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(688, 10, 28, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Obat Racikan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(20, 80, 270, 23);

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbObatRacikan.setName("tbObatRacikan"); // NOI18N
        tbObatRacikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatRacikanKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbObatRacikan);

        FormInput.add(Scroll10);
        Scroll10.setBounds(20, 100, 700, 96);

        CariObatRacikan.setHighlighter(null);
        CariObatRacikan.setName("CariObatRacikan"); // NOI18N
        CariObatRacikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CariObatRacikanKeyPressed(evt);
            }
        });
        FormInput.add(CariObatRacikan);
        CariObatRacikan.setBounds(20, 200, 580, 23);

        BtnCariObatRacikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariObatRacikan.setMnemonic('1');
        BtnCariObatRacikan.setToolTipText("Alt+1");
        BtnCariObatRacikan.setName("BtnCariObatRacikan"); // NOI18N
        BtnCariObatRacikan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariObatRacikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariObatRacikanActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariObatRacikan);
        BtnCariObatRacikan.setBounds(600, 200, 28, 23);

        Scroll11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbDetailObatRacikan.setName("tbDetailObatRacikan"); // NOI18N
        tbDetailObatRacikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDetailObatRacikanKeyPressed(evt);
            }
        });
        Scroll11.setViewportView(tbDetailObatRacikan);

        FormInput.add(Scroll11);
        Scroll11.setBounds(20, 230, 700, 216);

        BtnAllObatRacikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllObatRacikan.setMnemonic('2');
        BtnAllObatRacikan.setToolTipText("Alt+2");
        BtnAllObatRacikan.setName("BtnAllObatRacikan"); // NOI18N
        BtnAllObatRacikan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllObatRacikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllObatRacikanActionPerformed(evt);
            }
        });
        FormInput.add(BtnAllObatRacikan);
        BtnAllObatRacikan.setBounds(630, 200, 28, 23);

        BtnTambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnTambah1.setMnemonic('3');
        BtnTambah1.setToolTipText("Alt+3");
        BtnTambah1.setName("BtnTambah1"); // NOI18N
        BtnTambah1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambah1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambah1);
        BtnTambah1.setBounds(660, 200, 28, 23);

        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapus1);
        BtnHapus1.setBounds(690, 200, 28, 23);

        label13.setText("Nama Paket :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(75, 23));
        FormInput.add(label13);
        label13.setBounds(0, 40, 85, 23);

        NmResep.setName("NmResep"); // NOI18N
        NmResep.setPreferredSize(new java.awt.Dimension(207, 23));
        NmResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmResepKeyPressed(evt);
            }
        });
        FormInput.add(NmResep);
        NmResep.setBounds(89, 40, 150, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Template", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbDokter.setAutoCreateRowSorter(true);
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
        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        tbDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDokterKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDokter);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(530, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(430, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
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

        FormDetail.setBackground(new java.awt.Color(255, 255, 255));
        FormDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), " Detail Template Pemeriksaan : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        FormDetail.setName("FormDetail"); // NOI18N
        FormDetail.setPreferredSize(new java.awt.Dimension(115, 73));
        FormDetail.setLayout(new java.awt.BorderLayout());

        Scroll13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);
        Scroll13.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll13.setViewportView(LoadHTML);

        FormDetail.add(Scroll13, java.awt.BorderLayout.CENTER);

        PanelAccor.add(FormDetail, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Template", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16i.png"))); // NOI18N
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

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(LCount);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            try {
                panggilDetail();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbDokter.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbDokterMouseClicked

    private void tbDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDokterKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    if (ChkAccor.isSelected() == false) {
                        if (tbDokter.getSelectedRow() != -1) {
                            ChkAccor.setSelected(true);
                            isDetail();
                            panggilDetail();
                            ChkAccor.setSelected(false);
                            isDetail();
                        }
                    }
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDokterKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbDokter.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (KdDokter.getText().equals(tbDokter.getValueAt(tbDokter.
                        getSelectedRow(), 1).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (Kd.getText().trim().isEmpty()) {
            Valid.textKosong(Kd, "No.Template");
        } else if (KdDokter.getText().trim().isEmpty() || NmDokter.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnDokter, "Dokter");
        } else {
            if (tbDokter.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KdDokter.getText().equals(tbDokter.getValueAt(tbDokter.
                            getSelectedRow(), 1).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (Kd.getText().trim().isEmpty()) {
            Valid.textKosong(Kd, "No.Template");
        } else if (KdDokter.getText().trim().isEmpty() || NmDokter.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnDokter, "Dokter");
        } else {
            if (Sequel.menyimpantf("template_pemeriksaan_dokter", "?,?,?",
                    "No.Template", 3, new String[]{
                        Kd.getText(), KdDokter.getText(), NmResep.getText()
                    }) == true) {

                for (i = 0; i < tbObatRacikan.getRowCount(); i++) {
                    if (Valid.
                            SetAngka(tbObatRacikan.getValueAt(i, 4).toString()) > 0) {
                        Sequel.menyimpan(
                                "template_pemeriksaan_dokter_resep_racikan",
                                "?,?,?,?,?,?,?", "Obat Racikan", 7,
                                new String[]{
                                    Kd.getText(),
                                    tbObatRacikan.getValueAt(i, 0).toString(),
                                    tbObatRacikan.getValueAt(i, 1).toString(),
                                    tbObatRacikan.getValueAt(i, 2).toString(),
                                    tbObatRacikan.getValueAt(i, 4).toString(),
                                    tbObatRacikan.getValueAt(i, 5).toString(),
                                    tbObatRacikan.getValueAt(i, 6).toString()
                                });
                    }
                }
                for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                    if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 10).
                            toString()) > 0) {
                        Sequel.menyimpan(
                                "template_pemeriksaan_dokter_resep_racikan_detail",
                                "?,?,?,?,?,?,?", "Detail Obat Racikan", 7,
                                new String[]{
                                    Kd.getText(), tbDetailObatRacikan.
                                    getValueAt(i, 0).toString(),
                                    tbDetailObatRacikan.getValueAt(i, 1).
                                            toString(),
                                    tbDetailObatRacikan.getValueAt(i, 6).
                                            toString(), tbDetailObatRacikan.
                                            getValueAt(i, 8).toString(),
                                    tbDetailObatRacikan.getValueAt(i, 9).
                                            toString(), tbDetailObatRacikan.
                                            getValueAt(i, 10).toString()
                                });
                    }
                }
                tabMode.addRow(new String[]{
                    Kd.getText(), KdDokter.getText(), NmDokter.getText(),
                    NmResep.getText()
                });
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, CariObatRacikan, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed
    /*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKeyPressed
        //Valid.pindah(evt,TCari,Nm,TCari);
    }//GEN-LAST:event_KdKeyPressed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed

    }//GEN-LAST:event_KdDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void CariObatRacikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CariObatRacikanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariObatRacikanActionPerformed(null);
        }
    }//GEN-LAST:event_CariObatRacikanKeyPressed

    private void BtnCariObatRacikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariObatRacikanActionPerformed
        if (tbObatRacikan.getRowCount() != 0) {
            if (tbObatRacikan.getSelectedRow() != -1) {
                if (tbObatRacikan.getValueAt(tbObatRacikan.getSelectedRow(), 0).
                        toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 1).toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 2).toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 3).toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 4).toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 5).toString().isEmpty()
                        || tbObatRacikan.getValueAt(tbObatRacikan.
                                getSelectedRow(), 6).toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Silahkan lengkapi data racikan..!!");
                } else {
                    tampilDetailObatRacikan();
                }
            } else {
                JOptionPane.
                        showMessageDialog(null, "Silahkan pilih racikan..!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan masukkan racikan..!!");
        }
    }//GEN-LAST:event_BtnCariObatRacikanActionPerformed

    private void BtnAllObatRacikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllObatRacikanActionPerformed
        CariObatRacikan.setText("");
        tampilObatNonRacikan();
        BtnCariObatRacikanActionPerformed(null);
    }//GEN-LAST:event_BtnAllObatRacikanActionPerformed

    private void BtnTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambah1ActionPerformed
        i = tabModeObatRacikan.getRowCount() + 1;
        if (i == 99) {
            JOptionPane.showMessageDialog(null, "Maksimal 98 Racikan..!!");
        } else {
            tabModeObatRacikan.addRow(new Object[]{"" + i, "", "", "", "", "",
                ""});
        }
    }//GEN-LAST:event_BtnTambah1ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tbObatRacikan.getValueAt(tbObatRacikan.getSelectedRow(), 1).equals(
                "") && tbObatRacikan.getValueAt(tbObatRacikan.getSelectedRow(),
                        4).equals("") && tbObatRacikan.getValueAt(tbObatRacikan.
                getSelectedRow(), 5).equals("") && tbObatRacikan.getValueAt(
                tbObatRacikan.getSelectedRow(), 6).equals("")) {
            tabModeObatRacikan.removeRow(tbObatRacikan.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Maaf sudah terisi, gak boleh dihapus..!!");
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void tbObatRacikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatRacikanKeyPressed
        if (tbObatRacikan.getRowCount() != 0) {
            i = tbObatRacikan.getSelectedColumn();
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (i == 5) {
                    index = 2;
                    aturanpakai.setSize(internalFrame1.getWidth() - 20,
                            internalFrame1.getHeight() - 20);
                    aturanpakai.setLocationRelativeTo(internalFrame1);
                    aturanpakai.setVisible(true);
                } else if (i == 3) {
                    if (tbObatRacikan.getValueAt(tbObatRacikan.getSelectedRow(),
                            1).equals("")) {
                        JOptionPane.showMessageDialog(null,
                                "Silahkan masukkan nama racikan..!!");
                        tbObatRacikan.requestFocus();
                    } else {
                        metoderacik.isCek();
                        metoderacik.setSize(internalFrame1.getWidth() - 20,
                                internalFrame1.getHeight() - 20);
                        metoderacik.setLocationRelativeTo(internalFrame1);
                        metoderacik.setVisible(true);
                    }
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                if (i == 6) {
                    TCari.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_tbObatRacikanKeyPressed

    private void tbDetailObatRacikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDetailObatRacikanKeyPressed
        if (tbDetailObatRacikan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_RIGHT) || (evt.
                    getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                i = tbDetailObatRacikan.getSelectedColumn();
                if ((i == 8) || (i == 5) || (i == 10) || (i == 11)) {
                    try {
                        if (!tbDetailObatRacikan.getValueAt(tbDetailObatRacikan.
                                getSelectedRow(), 11).toString().equals(
                                        tbDetailObatRacikan.getValueAt(
                                                tbDetailObatRacikan.
                                                        getSelectedRow(), 9).
                                                toString())) {
                            if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(
                                    tbDetailObatRacikan.getSelectedRow(), 8).
                                    toString()) == 0) {
                                JOptionPane.showMessageDialog(null,
                                        "Kapasitas obat masih kosong..!!!");
                            } else {
                                tbDetailObatRacikan.setValueAt(Valid.SetAngka8(
                                        Valid.SetAngka(tbDetailObatRacikan.
                                                getValueAt(tbDetailObatRacikan.
                                                        getSelectedRow(), 5).
                                                toString())
                                        * (Valid.SetAngka(tbDetailObatRacikan.
                                                getValueAt(tbDetailObatRacikan.
                                                        getSelectedRow(), 6).
                                                toString()) / Valid.SetAngka(
                                                tbDetailObatRacikan.getValueAt(
                                                        tbDetailObatRacikan.
                                                                getSelectedRow(),
                                                        8).toString())), 1),
                                        tbDetailObatRacikan.getSelectedRow(), 9);
                            }
                        }
                    } catch (HeadlessException e) {
                        tbDetailObatRacikan.setValueAt(0, tbDetailObatRacikan.
                                getSelectedRow(), 9);
                    }
                } else if (i == 9) {
                    if (tbDetailObatRacikan.getValueAt(tbDetailObatRacikan.
                            getSelectedRow(), 9).toString().contains("%")) {
                        if (tbDetailObatRacikan.getSelectedRow() != -1) {
                            try {
                                r = tbDetailObatRacikan.getSelectedRow();
                                noracik = tbDetailObatRacikan.getValueAt(r, 0).
                                        toString();
                                jumlahracik = 0;
                                persenracik = Double.parseDouble(
                                        tbDetailObatRacikan.getValueAt(r, 9).
                                                toString().replaceAll("%", ""));
                                kapasitasracik = Double.parseDouble(
                                        tbDetailObatRacikan.getValueAt(r, 5).
                                                toString());
                                for (i = 0; i < tbDetailObatRacikan.
                                        getRowCount(); i++) {
                                    if (noracik == null ? tbDetailObatRacikan.
                                            getValueAt(i, 0).toString() == null : noracik.
                                            equals(tbDetailObatRacikan.
                                                    getValueAt(i, 0).toString())) {
                                        if (!tbDetailObatRacikan.
                                                getValueAt(i, 9).toString().
                                                contains("%")) {
                                            jumlahracik += (Double.parseDouble(
                                                    tbDetailObatRacikan.
                                                            getValueAt(i, 5).
                                                            toString())
                                                    * Double.parseDouble(
                                                            tbDetailObatRacikan.
                                                                    getValueAt(i,
                                                                            10).
                                                                    toString()));
                                        }
                                    }
                                }
                                tbDetailObatRacikan.setValueAt(Valid.SetAngka8(
                                        (jumlahracik * (persenracik / 100)) / kapasitasracik,
                                        1), r, 10);
                            } catch (Exception e) {
                                tbDetailObatRacikan.setValueAt(0, r, 10);
                            }
                        }
                    } else {
                        if (tbDetailObatRacikan.getSelectedRow() != -1) {
                            try {
                                tbDetailObatRacikan.setValueAt(Valid.SetAngka8(
                                        (Double.parseDouble(tbObatRacikan.
                                                getValueAt(tbObatRacikan.
                                                        getSelectedRow(), 4).
                                                toString())
                                        * Double.parseDouble(
                                                tbDetailObatRacikan.getValueAt(
                                                        tbDetailObatRacikan.
                                                                getSelectedRow(),
                                                        9).toString()))
                                        / Double.parseDouble(
                                                tbDetailObatRacikan.getValueAt(
                                                        tbDetailObatRacikan.
                                                                getSelectedRow(),
                                                        5).toString()), 1),
                                        tbDetailObatRacikan.getSelectedRow(), 10);
                            } catch (Exception e) {
                                tbDetailObatRacikan.setValueAt(0,
                                        tbDetailObatRacikan.getSelectedRow(), 10);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbDetailObatRacikanKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbDokter.getSelectedRow() != -1) {
            isDetail();
            panggilDetail();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,
                    "Silahkan pilih No.Pernyataan..!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed

    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed

    }//GEN-LAST:event_ppSemuaActionPerformed

    private void NmResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmResepKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MasterTemplatePemberianObatRacikan dialog = new MasterTemplatePemberianObatRacikan(
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
    private widget.Button BtnAll;
    private widget.Button BtnAllObatRacikan;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariObatRacikan;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah1;
    public widget.TextBox CariObatRacikan;
    private widget.CekBox ChkAccor;
    private widget.PanelBiasa FormDetail;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Kd;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextBox NmDokter;
    private widget.TextBox NmResep;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll13;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel20;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppSemua;
    private widget.ScrollPane scrollInput;
    public widget.Table tbDetailObatRacikan;
    private widget.Table tbDokter;
    public widget.Table tbObatRacikan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (akses.getkode().equals("Admin Utama")) {
                ps = koneksi.prepareStatement(
                        "select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,"
                        + "template_pemeriksaan_dokter.keluhan,template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,"
                        + "template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,template_pemeriksaan_dokter.evaluasi "
                        + "from template_pemeriksaan_dokter inner join dokter on dokter.kd_dokter=template_pemeriksaan_dokter.kd_dokter "
                        + (TCari.getText().isEmpty() ? "" : "where template_pemeriksaan_dokter.no_template like ? or template_pemeriksaan_dokter.nm_dokter like ? or "
                        + "template_pemeriksaan_dokter.keluhan like ? or template_pemeriksaan_dokter.pemeriksaan like ? or "
                        + "template_pemeriksaan_dokter.penilaian like ? or template_pemeriksaan_dokter.rencana like ? or "
                        + "template_pemeriksaan_dokter.instruksi like ? or template_pemeriksaan_dokter.evaluasi like ? ")
                        + "order by template_pemeriksaan_dokter.no_template");
            } else {
                ps = koneksi.prepareStatement(
                        "select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,"
                        + "template_pemeriksaan_dokter.keluhan,template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,"
                        + "template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,template_pemeriksaan_dokter.evaluasi "
                        + "from template_pemeriksaan_dokter inner join dokter on dokter.kd_dokter=template_pemeriksaan_dokter.kd_dokter "
                        + "where template_pemeriksaan_dokter.kd_dokter=? " + (TCari.
                                getText().isEmpty() ? "" : "and (template_pemeriksaan_dokter.no_template like ? or "
                                + "template_pemeriksaan_dokter.keluhan like ? or template_pemeriksaan_dokter.pemeriksaan like ? or "
                                + "template_pemeriksaan_dokter.penilaian like ? or template_pemeriksaan_dokter.rencana like ? or "
                                + "template_pemeriksaan_dokter.instruksi like ? or template_pemeriksaan_dokter.evaluasi like ?) ")
                        + "order by template_pemeriksaan_dokter.no_template");
            }

            try {
                if (akses.getkode().equals("Admin Utama")) {
                    if (!TCari.getText().isEmpty()) {
                        ps.setString(1, "%" + TCari.getText().trim() + "%");
                        ps.setString(2, "%" + TCari.getText().trim() + "%");
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
                        ps.setString(4, "%" + TCari.getText().trim() + "%");
                        ps.setString(5, "%" + TCari.getText().trim() + "%");
                        ps.setString(6, "%" + TCari.getText().trim() + "%");
                        ps.setString(7, "%" + TCari.getText().trim() + "%");
                        ps.setString(8, "%" + TCari.getText().trim() + "%");
                    }
                } else {
                    ps.setString(1, akses.getkode());
                    if (!TCari.getText().isEmpty()) {
                        ps.setString(2, "%" + TCari.getText().trim() + "%");
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
                        ps.setString(4, "%" + TCari.getText().trim() + "%");
                        ps.setString(5, "%" + TCari.getText().trim() + "%");
                        ps.setString(6, "%" + TCari.getText().trim() + "%");
                        ps.setString(7, "%" + TCari.getText().trim() + "%");
                        ps.setString(8, "%" + TCari.getText().trim() + "%");
                    }
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_template"), rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"), rs.getString("keluhan"), rs.
                        getString("pemeriksaan"), rs.getString("penilaian"), rs.
                        getString("rencana"), rs.getString("instruksi"), rs.
                        getString("evaluasi")
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    /**
     *
     */
    public void emptTeks() {
        Kd.setText("");
        CariObatRacikan.setText("");
        Valid.tabelKosong(tabModeObatRacikan);
        Valid.tabelKosong(tabModeDetailObatRacikan);
        Valid.autoNomer("template_pemeriksaan_dokter", "TPD", 16, Kd);
        TabRawat.setSelectedIndex(0);
        CariObatRacikan.requestFocus();
    }

    private void getData() {
        if (tbDokter.getSelectedRow() != -1) {
            Kd.setText(tabMode.getValueAt(tbDokter.getSelectedRow(), 0).
                    toString());
        }
    }

    /**
     *
     * @return
     */
    public JTable getTable() {
        return tbDokter;
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.gettemplate_pemeriksaan());
        BtnHapus.setEnabled(akses.gettemplate_pemeriksaan());
        BtnEdit.setEnabled(akses.gettemplate_pemeriksaan());
        if (akses.getjml2() >= 1) {
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if (NmDokter.getText().isEmpty()) {
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,
                        "User login bukan Dokter...!!");
            }
        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void tampilObatNonRacikan() {
        try {
            file = new File("./cache/permintaanobatnonracikan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select databarang.kode_brng,databarang.nama_brng,kodesatuan.satuan,databarang.letak_barang,jenis.nama,industrifarmasi.nama_industri,databarang.kapasitas "
                    + "from databarang inner join kodesatuan on kodesatuan.kode_sat=databarang.kode_sat inner join jenis on databarang.kdjns=jenis.kdjns "
                    + "inner join industrifarmasi on industrifarmasi.kode_industri=databarang.kode_industri where databarang.status='1' order by databarang.nama_brng");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    iyem = iyem + "{\"KodeBarang\":\"" + rs.getString(1) + "\",\"NamaBarang\":\"" + rs.
                            getString(2).replaceAll("\"", "") + "\",\"Satuan\":\"" + rs.
                            getString(3) + "\",\"Komposisi\":\"" + rs.getString(
                            4) + "\",\"JenisObat\":\"" + rs.getString(5) + "\",\"Industri\":\"" + rs.
                            getString(6) + "\",\"Kapasitas\":\"" + rs.getString(
                            7) + "\"},";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi 1 : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"permintaanobatnonracikan\":[" + iyem.substring(
                    0, iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;
        } catch (Exception e) {
            System.out.println("Notifikasi 2 : " + e);
        }
    }

    private void tampilDetailObatRacikan() {
        try {
            jml = 0;
            for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 10).
                        toString()) > 0) {
                    jml++;
                }
            }

            no = null;
            no = new String[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            satuan = null;
            satuan = new String[jml];
            kategori = null;
            kategori = new String[jml];
            kps = null;
            kps = new double[jml];
            p1 = null;
            p1 = new double[jml];
            p2 = null;
            p2 = new double[jml];
            keterangan = null;
            keterangan = new String[jml];
            jumlah = null;
            jumlah = new double[jml];
            cirium = null;
            cirium = new String[jml];
            ciripny = null;
            ciripny = new String[jml];

            index = 0;
            for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 10).
                        toString()) > 0) {
                    no[index] = tbDetailObatRacikan.getValueAt(i, 0).toString();
                    kode[index] = tbDetailObatRacikan.getValueAt(i, 1).
                            toString();
                    nama[index] = tbDetailObatRacikan.getValueAt(i, 2).
                            toString();
                    satuan[index] = tbDetailObatRacikan.getValueAt(i, 3).
                            toString();
                    kategori[index] = tbDetailObatRacikan.getValueAt(i, 4).
                            toString();
                    try {
                        kps[index] = Double.parseDouble(tbDetailObatRacikan.
                                getValueAt(i, 5).toString());
                    } catch (Exception e) {
                        kps[index] = 0;
                    }
                    try {
                        p1[index] = Double.parseDouble(tbDetailObatRacikan.
                                getValueAt(i, 6).toString());
                    } catch (Exception e) {
                        p1[index] = 0;
                    }
                    try {
                        p2[index] = Double.parseDouble(tbDetailObatRacikan.
                                getValueAt(i, 8).toString());
                    } catch (Exception e) {
                        p2[index] = 0;
                    }
                    keterangan[index] = tbDetailObatRacikan.getValueAt(i, 9).
                            toString();
                    try {
                        jumlah[index] = Double.parseDouble(tbDetailObatRacikan.
                                getValueAt(i, 10).toString());
                    } catch (Exception e) {
                        jumlah[index] = 0;
                    }
                    cirium[index] = tbDetailObatRacikan.getValueAt(i, 11).
                            toString();
                    ciripny[index] = tbDetailObatRacikan.getValueAt(i, 12).
                            toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDetailObatRacikan);
            for (i = 0; i < index; i++) {
                tabModeDetailObatRacikan.addRow(new Object[]{
                    no[i], kode[i], nama[i], satuan[i], kategori[i], kps[i],
                    p1[i], "/", p2[i], keterangan[i], jumlah[i], cirium[i],
                    ciripny[i]
                });
            }

            myObj = new FileReader("./cache/permintaanobatnonracikan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("permintaanobatnonracikan");
            if (response.isArray()) {
                for (JsonNode list : response) {
                    if ((list.path("KodeBarang").asText().toLowerCase().
                            contains(CariObatRacikan.getText().toLowerCase()) || list.
                            path("NamaBarang").asText().toLowerCase().contains(
                            CariObatRacikan.getText().toLowerCase())
                            || list.path("Komposisi").asText().toLowerCase().
                                    contains(CariObatRacikan.getText().
                                            toLowerCase()) || list.path(
                                    "JenisObat").asText().toLowerCase().
                                    contains(CariObatRacikan.getText().
                                            toLowerCase()))) {
                        tabModeDetailObatRacikan.addRow(new Object[]{
                            tbObatRacikan.getValueAt(tbObatRacikan.
                            getSelectedRow(), 0).toString(), list.path(
                            "KodeBarang").asText(), list.path("NamaBarang").
                            asText(), list.path("Satuan").asText(),
                            list.path("JenisObat").asText(), list.path(
                            "Kapasitas").asDouble(), 1, "/", 1, "", 0, list.
                            path("Industri").asText(), list.path("Komposisi").
                            asText()
                        });
                    }
                }
            }
            myObj.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isDetail() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(
                    internalFrame3.getWidth() - 200, HEIGHT));
            FormDetail.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            FormDetail.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void panggilDetail() {
        if (FormDetail.isVisible() == true) {
            if (tbDokter.getSelectedRow() != -1) {
                try {
                    htmlContent = new StringBuilder();

                    ps = koneksi.prepareStatement(
                            "select template_pemeriksaan_dokter_resep_racikan.no_racik,template_pemeriksaan_dokter_resep_racikan.kd_racik,template_pemeriksaan_dokter_resep_racikan.nama_racik,metode_racik.nm_racik,template_pemeriksaan_dokter_resep_racikan.jml_dr,template_pemeriksaan_dokter_resep_racikan.aturan_pakai,"
                            + "template_pemeriksaan_dokter_resep_racikan.keterangan from template_pemeriksaan_dokter_resep_racikan inner join metode_racik on metode_racik.kd_racik=template_pemeriksaan_dokter_resep_racikan.kd_racik where template_pemeriksaan_dokter_resep_racikan.no_template=? "
                            + "order by template_pemeriksaan_dokter_resep_racikan.no_racik");
                    try {
                        ps.setString(1, tabMode.getValueAt(tbDokter.
                                getSelectedRow(), 0).toString());
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' align='left' width='100%'>"
                                    + "Obat Racikan : "
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr class='isi'>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='3%'>No.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='33%'>Nama Racikan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='11%'>Metode Racik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='6%'>Jml.Racik</td>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='25%'>Aturan Pakai</td>"
                                    + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22%'>Keterangan</td>"
                                    + "</tr>"
                            );
                            Valid.tabelKosong(tabModeObatRacikan);
                            rs.beforeFirst();
                            while (rs.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td align='center'>" + rs.getString(
                                                "no_racik") + "</td>"
                                        + "<td>" + rs.getString("nama_racik") + "</td>"
                                        + "<td align='center'>" + rs.getString(
                                                "nm_racik") + "</td>"
                                        + "<td align='center'>" + rs.getString(
                                                "jml_dr") + "</td>"
                                        + "<td>" + rs.getString("aturan_pakai") + "</td>"
                                        + "<td>" + rs.getString("keterangan") + "</td>"
                                        + "</tr>"
                                );
                                tabModeObatRacikan.addRow(new Object[]{
                                    rs.getString("no_racik"), rs.getString(
                                    "nama_racik"), rs.getString("kd_racik"), rs.
                                    getString("nm_racik"), rs.
                                    getString("jml_dr"), rs.getString(
                                    "aturan_pakai"), rs.getString("keterangan")
                                });
                                try {
                                    ps2 = koneksi.prepareStatement(
                                            "select template_pemeriksaan_dokter_resep_racikan_detail.kode_brng,databarang.nama_brng,kodesatuan.satuan,template_pemeriksaan_dokter_resep_racikan_detail.jml,jenis.nama,"
                                            + "databarang.kapasitas,template_pemeriksaan_dokter_resep_racikan_detail.p1,template_pemeriksaan_dokter_resep_racikan_detail.p2,template_pemeriksaan_dokter_resep_racikan_detail.kandungan,"
                                            + "industrifarmasi.nama_industri,databarang.letak_barang from template_pemeriksaan_dokter_resep_racikan_detail inner join databarang on template_pemeriksaan_dokter_resep_racikan_detail.kode_brng=databarang.kode_brng "
                                            + "inner join jenis on databarang.kdjns=jenis.kdjns inner join kodesatuan on kodesatuan.kode_sat=databarang.kode_sat inner join industrifarmasi on industrifarmasi.kode_industri=databarang.kode_industri "
                                            + "where template_pemeriksaan_dokter_resep_racikan_detail.no_template=? and template_pemeriksaan_dokter_resep_racikan_detail.no_racik=? order by template_pemeriksaan_dokter_resep_racikan_detail.kode_brng");
                                    ps2.setString(1, tabMode.getValueAt(
                                            tbDokter.getSelectedRow(), 0).
                                            toString());
                                    ps2.setString(2, rs.getString("no_racik"));
                                    rs2 = ps2.executeQuery();
                                    if (rs2.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td align='center' width='15%'></td>"
                                                + "<td width='85%' colspan='5'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                                + "<tr class='isi'>"
                                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='10%'>Jumlah</td>"
                                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='20%'>Satuan</td>"
                                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='20%'>Kode Obat</td>"
                                                + "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'>Nama Obat</td>"
                                                + "</tr>"
                                        );
                                        Valid.tabelKosong(
                                                tabModeDetailObatRacikan);
                                        rs2.beforeFirst();
                                        while (rs2.next()) {
                                            htmlContent.append(
                                                    "<tr class='isi'>"
                                                    + "<td align='center'>" + rs2.
                                                            getString("jml") + "</td>"
                                                    + "<td align='center'>" + rs2.
                                                            getString("satuan") + "</td>"
                                                    + "<td align='center'>" + rs2.
                                                            getString(
                                                                    "kode_brng") + "</td>"
                                                    + "<td align='left'>" + rs2.
                                                            getString(
                                                                    "nama_brng") + "</td>"
                                                    + "</tr>"
                                            );
                                            tabModeDetailObatRacikan.addRow(
                                                    new Object[]{
                                                        rs.getString("no_racik"),
                                                        rs2.getString(
                                                                "kode_brng"),
                                                        rs2.getString(
                                                                "nama_brng"),
                                                        rs2.getString("satuan"),
                                                        rs2.getString("nama"),
                                                        rs2.getDouble(
                                                                "kapasitas"),
                                                        rs2.getDouble("p1"), "/",
                                                        rs2.getDouble("p2"),
                                                        rs2.getString(
                                                                "kandungan"),
                                                        rs2.getDouble("jml"),
                                                        rs2.getString(
                                                                "nama_industri"),
                                                        rs2.getString(
                                                                "letak_barang")
                                                    });
                                        }
                                        htmlContent.append(
                                                "</table>"
                                                + "</td>"
                                                + "</tr>"
                                        );
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
                            htmlContent.append(
                                    "</table>"
                                    + "</td>"
                                    + "</tr>"
                            );
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

                    LoadHTML.setText(
                            "<html>"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + htmlContent.toString()
                            + "</table>"
                            + "</html>");
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            }
        }
    }

    private void ganti() {
        if (Sequel.queryu2tf(
                "delete from template_pemeriksaan_dokter where no_template=?", 1,
                new String[]{
                    tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString()
                }) == true) {
            if (Sequel.menyimpantf("template_pemeriksaan_dokter",
                    "?,?,?,?,?,?,?,?", "No.Template", 8, new String[]{
                        tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).
                                toString(), KdDokter.getText(), NmResep.
                        getText()
                    }) == true) {

                for (i = 0; i < tbObatRacikan.getRowCount(); i++) {
                    if (Valid.
                            SetAngka(tbObatRacikan.getValueAt(i, 4).toString()) > 0) {
                        Sequel.menyimpan(
                                "template_pemeriksaan_dokter_resep_racikan",
                                "?,?,?,?,?,?,?", "Obat Racikan", 7,
                                new String[]{
                                    tbDokter.getValueAt(tbDokter.
                                            getSelectedRow(), 0).toString(),
                                    tbObatRacikan.getValueAt(i, 0).toString(),
                                    tbObatRacikan.getValueAt(i, 1).toString(),
                                    tbObatRacikan.getValueAt(i, 2).toString(),
                                    tbObatRacikan.getValueAt(i, 4).toString(),
                                    tbObatRacikan.getValueAt(i, 5).toString(),
                                    tbObatRacikan.getValueAt(i, 6).toString()
                                });
                    }
                }
                for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                    if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 10).
                            toString()) > 0) {
                        Sequel.menyimpan(
                                "template_pemeriksaan_dokter_resep_racikan_detail",
                                "?,?,?,?,?,?,?", "Detail Obat Racikan", 7,
                                new String[]{
                                    tbDokter.getValueAt(tbDokter.
                                            getSelectedRow(), 0).toString(),
                                    tbDetailObatRacikan.getValueAt(i, 0).
                                            toString(), tbDetailObatRacikan.
                                            getValueAt(i, 1).toString(),
                                    tbDetailObatRacikan.getValueAt(i, 6).
                                            toString(), tbDetailObatRacikan.
                                            getValueAt(i, 8).toString(),
                                    tbDetailObatRacikan.getValueAt(i, 9).
                                            toString(), tbDetailObatRacikan.
                                            getValueAt(i, 10).toString()
                                });
                    }
                }
                tabMode.addRow(new String[]{
                    tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString(),
                    KdDokter.getText(), NmDokter.getText(), NmResep.getText()
                });
                tabMode.removeRow(tbDokter.getSelectedRow());
                ChkAccor.setSelected(false);
                isDetail();
                TabRawat.setSelectedIndex(1);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengganti..!!");
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf(
                "delete from template_pemeriksaan_dokter where no_template=?", 1,
                new String[]{
                    tbDokter.getValueAt(tbDokter.getSelectedRow(), 0).toString()
                }) == true) {
            tabMode.removeRow(tbDokter.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
            LoadHTML.setText("");
            TabRawat.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private static final Logger LOG = Logger.getLogger(
            MasterTemplatePemberianObatRacikan.class.getName());
}
