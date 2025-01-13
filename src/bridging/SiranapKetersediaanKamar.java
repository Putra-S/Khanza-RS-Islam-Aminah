/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgJnsPerawatanRalan.java
*
* Created on May 22, 2010, 11:58:21 PM
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import simrskhanza.DlgCariBangsal;

/**
 * @author dosen
 */
public class SiranapKetersediaanKamar extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private Connection koneksi = koneksiDB.condb();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0;

    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);

    private final Properties prop = new Properties();

    private String requestJson, utc = "", URL = "", respon = "", idrs = "", passrs = "";

    private ApiKemenkesSirs api = new ApiKemenkesSirs();

    private HttpHeaders headers;

    private HttpEntity requestEntity;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode root;

    /**
     * Creates new form DlgJnsPerawatanRalan
     *
     * @param parent
     * @param modal
     */
    public SiranapKetersediaanKamar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(628, 674);

        Object[] row = {"P", "Kelas SIRANAP", "Kode Ruang", "Kamar/Ruang", "Kelas", "Kapasitas", "Terpakai",
            "Terpakai Suspek", "Terpakai Konfirmasi", "Antrian", "Prepare", "Prepare Plan", "Covid"};
        tabMode = new DefaultTableModel(null, row) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class};

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
        tbJnsPerawatan.setModel(tabMode);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbJnsPerawatan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJnsPerawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbJnsPerawatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(65);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            }
        }
        tbJnsPerawatan.setDefaultRenderer(Object.class, new WarnaTable());

        Terpakai.setDocument(new batasInput((byte) 4).getOnlyAngka(Terpakai));
        Kapasitas.setDocument(new batasInput((byte) 4).getOnlyAngka(Kapasitas));
        TerpakaiKonfirmasi.setDocument(new batasInput((byte) 4).getOnlyAngka(TerpakaiKonfirmasi));
        TerpakaiSuspek.setDocument(new batasInput((byte) 4).getOnlyAngka(TerpakaiSuspek));
        Antrian.setDocument(new batasInput((byte) 4).getOnlyAngka(Antrian));
        KdKamar.setDocument(new batasInput((byte) 5).getKata(KdKamar));
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
        ChkInput.setSelected(false);
        isForm();

        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (bangsal.getTable().getSelectedRow() != -1) {
                    KdKamar.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString());
                    NmKamar.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
                }
                isCariKetersediaan();
                KdKamar.requestFocus();
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

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPISIRS");
            idrs = koneksiDB.IDSIRS();
            passrs = koneksiDB.PASSSIRS();
        } catch (Exception e) {
            System.out.println("E : " + e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tanggal = new widget.Tanggal();
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
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        Kapasitas = new widget.TextBox();
        jLabel19 = new widget.Label();
        KdKamar = new widget.TextBox();
        NmKamar = new widget.TextBox();
        btnKamar = new widget.Button();
        jLabel5 = new widget.Label();
        Kelas = new widget.ComboBox();
        jLabel9 = new widget.Label();
        Terpakai = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        TerpakaiKonfirmasi = new widget.TextBox();
        TerpakaiSuspek = new widget.TextBox();
        jLabel12 = new widget.Label();
        Antrian = new widget.TextBox();
        KelasSiranap = new widget.ComboBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        Prepare = new widget.TextBox();
        PreparePlan = new widget.TextBox();
        jLabel16 = new widget.Label();
        Covid = new widget.ComboBox();
        ChkInput = new widget.CekBox();

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2023-04-17 08:18:14" }));
        Tanggal.setDisplayFormat("yyyy-MM-dd HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.setPreferredSize(new java.awt.Dimension(95, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Ketersediaan Kamar SIRANAP KEMENKES ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJnsPerawatan.setAutoCreateRowSorter(true);
        tbJnsPerawatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJnsPerawatan.setName("tbJnsPerawatan"); // NOI18N
        tbJnsPerawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJnsPerawatanMouseClicked(evt);
            }
        });
        tbJnsPerawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbJnsPerawatanKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbJnsPerawatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 130));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 197));
        FormInput.setLayout(null);

        jLabel8.setText("Kapasitas/Jumlah Bed :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(228, 72, 120, 23);

        Kapasitas.setText("0");
        Kapasitas.setHighlighter(null);
        Kapasitas.setName("Kapasitas"); // NOI18N
        Kapasitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KapasitasKeyPressed(evt);
            }
        });
        FormInput.add(Kapasitas);
        Kapasitas.setBounds(351, 72, 50, 23);

        jLabel19.setText("Kamar/Ruang :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 42, 97, 23);

        KdKamar.setEditable(false);
        KdKamar.setHighlighter(null);
        KdKamar.setName("KdKamar"); // NOI18N
        KdKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKamarKeyPressed(evt);
            }
        });
        FormInput.add(KdKamar);
        KdKamar.setBounds(100, 42, 77, 23);

        NmKamar.setEditable(false);
        NmKamar.setName("NmKamar"); // NOI18N
        FormInput.add(NmKamar);
        NmKamar.setBounds(180, 42, 190, 23);

        btnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar.setMnemonic('3');
        btnKamar.setToolTipText("ALt+3");
        btnKamar.setName("btnKamar"); // NOI18N
        btnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamarActionPerformed(evt);
            }
        });
        FormInput.add(btnKamar);
        btnKamar.setBounds(373, 42, 28, 23);

        jLabel5.setText("Kelas :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 72, 97, 23);

        Kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 1", "Kelas 2", "Kelas 3", "Kelas Utama", "Kelas VIP", "Kelas VVIP" }));
        Kelas.setName("Kelas"); // NOI18N
        Kelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KelasItemStateChanged(evt);
            }
        });
        Kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelasKeyPressed(evt);
            }
        });
        FormInput.add(Kelas);
        Kelas.setBounds(100, 72, 120, 23);

        jLabel9.setText("Terpakai :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(418, 42, 77, 23);

        Terpakai.setText("0");
        Terpakai.setHighlighter(null);
        Terpakai.setName("Terpakai"); // NOI18N
        Terpakai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerpakaiKeyPressed(evt);
            }
        });
        FormInput.add(Terpakai);
        Terpakai.setBounds(498, 42, 50, 23);

        jLabel10.setText("Terpakai Suspek :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(405, 72, 90, 23);

        jLabel11.setText("Terpakai Konfirmasi :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(548, 42, 125, 23);

        TerpakaiKonfirmasi.setText("0");
        TerpakaiKonfirmasi.setHighlighter(null);
        TerpakaiKonfirmasi.setName("TerpakaiKonfirmasi"); // NOI18N
        TerpakaiKonfirmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerpakaiKonfirmasiKeyPressed(evt);
            }
        });
        FormInput.add(TerpakaiKonfirmasi);
        TerpakaiKonfirmasi.setBounds(676, 42, 50, 23);

        TerpakaiSuspek.setText("0");
        TerpakaiSuspek.setHighlighter(null);
        TerpakaiSuspek.setName("TerpakaiSuspek"); // NOI18N
        TerpakaiSuspek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerpakaiSuspekKeyPressed(evt);
            }
        });
        FormInput.add(TerpakaiSuspek);
        TerpakaiSuspek.setBounds(498, 72, 50, 23);

        jLabel12.setText("Antrian :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(548, 72, 125, 23);

        Antrian.setText("0");
        Antrian.setHighlighter(null);
        Antrian.setName("Antrian"); // NOI18N
        Antrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntrianKeyPressed(evt);
            }
        });
        FormInput.add(Antrian);
        Antrian.setBounds(676, 72, 50, 23);

        KelasSiranap.setMaximumRowCount(40);
        KelasSiranap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0001 VVIP/Super VIP", "0002 VIP", "0003 Kelas 1", "0004 Kelas 2", "0005 Kelas 3", "0006 ICU Tanpa Ventilator", "0007 HCU", "0008 ICCU/ICVCU Tanpa Ventilator", "0009 RICU Tanpa Ventilator", "0010 NICU Tanpa Ventilator", "0011 PICU Tanpa Ventilator", "0012 Isolasi", "0014 Perinatologi", "0024 ICU Tekanan Negatif dengan Ventilator", "0025 ICU Tekanan Negatif tanpa Ventilator", "0026 ICU Tanpa Tekanan Negatif Dengan Ventilator", "0027 ICU Tanpa Tekanan Negatif Tanpa Ventilator", "0028 Isolasi Tekanan Negatif", "0029 Isolasi Tanpa Tekanan Negatif", "0030 NICU Khusus Covid", "0031 PICU Khusus Covid", "0032 IGD Khusus Covid", "0033 VK (Ibu Melahirkan) Khusus Covid", "0034 Isolasi Perinatologi Khusus Covid", "0036 VK (Ibu Melahirkan) Non Covid", "0037 Intermediate Ward (IGD)", "0038 ICU Dengan Ventilator", "0039 NICU Dengan Ventilator", "0040 PICU Dengan Ventilator", "0050 RICU Dengan Ventilator", "0051 ICCU/ICVCU Dengan Ventilator" }));
        KelasSiranap.setName("KelasSiranap"); // NOI18N
        KelasSiranap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelasSiranapKeyPressed(evt);
            }
        });
        FormInput.add(KelasSiranap);
        KelasSiranap.setBounds(100, 10, 450, 23);

        jLabel13.setText("Kelas SIRANAP :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(10, 10, 90, 23);

        jLabel14.setText("Prepare :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(730, 40, 90, 23);

        jLabel15.setText("Prepare Plan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(730, 70, 90, 23);

        Prepare.setText("0");
        Prepare.setHighlighter(null);
        Prepare.setName("Prepare"); // NOI18N
        Prepare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PrepareKeyPressed(evt);
            }
        });
        FormInput.add(Prepare);
        Prepare.setBounds(830, 40, 50, 23);

        PreparePlan.setText("0");
        PreparePlan.setHighlighter(null);
        PreparePlan.setName("PreparePlan"); // NOI18N
        PreparePlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PreparePlanKeyPressed(evt);
            }
        });
        FormInput.add(PreparePlan);
        PreparePlan.setBounds(830, 70, 50, 23);

        jLabel16.setText("Covid :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(560, 10, 50, 23);

        Covid.setMaximumRowCount(2);
        Covid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));
        Covid.setName("Covid"); // NOI18N
        Covid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CovidItemStateChanged(evt);
            }
        });
        Covid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CovidKeyPressed(evt);
            }
        });
        FormInput.add(Covid);
        Covid.setBounds(620, 10, 40, 23);

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

    private void KapasitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KapasitasKeyPressed
        Valid.pindah(evt, Kelas, Terpakai);
}//GEN-LAST:event_KapasitasKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (KdKamar.getText().trim().isEmpty() || NmKamar.getText().trim().
                isEmpty()) {
            Valid.textKosong(KdKamar, "Kode Kamar/Ruang");
        } else if (Kapasitas.getText().trim().isEmpty()) {
            Valid.textKosong(Kapasitas, "Kapasitas");
        } else if (Terpakai.getText().trim().isEmpty()) {
            Valid.textKosong(Terpakai, "Terpakai");
        } else if (TerpakaiSuspek.getText().trim().isEmpty()) {
            Valid.textKosong(TerpakaiSuspek, "Terpakai Suspek");
        } else if (TerpakaiKonfirmasi.getText().trim().isEmpty()) {
            Valid.textKosong(TerpakaiKonfirmasi, "Terpakai Konfirmasi");
        } else if (Antrian.getText().trim().isEmpty()) {
            Valid.textKosong(Antrian, "Antrian");
        } else if (Prepare.getText().trim().isEmpty()) {
            Valid.textKosong(Prepare, "Prepare");
        } else if (PreparePlan.getText().trim().isEmpty()) {
            Valid.textKosong(PreparePlan, "Prepare Plan");
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("X-rs-id", idrs);
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-pass", passrs);
                requestJson = "{"
                        + "\"id_tt\":\"" + KelasSiranap.getSelectedItem().
                                toString().substring(0, 4) + "\","
                        + "\"ruang\":\"" + NmKamar.getText() + "\","
                        + "\"jumlah_ruang\":\"1\","
                        + "\"jumlah\":\"" + Kapasitas.getText() + "\","
                        + "\"terpakai\":\"" + Terpakai.getText() + "\","
                        + "\"terpakai_suspek\":\"" + TerpakaiSuspek.getText() + "\","
                        + "\"terpakai_konfirmasi\":\"" + TerpakaiKonfirmasi.
                                getText() + "\","
                        + "\"antrian\":\"" + Antrian.getText() + "\","
                        + "\"prepare\":\"" + Prepare.getText() + "\","
                        + "\"prepare_plan\":\"" + PreparePlan.getText() + "\","
                        + "\"covid\":\"" + Covid.getSelectedItem().toString() + "\","
                        + "\"terpakai_dbd\":\"0\","
                        + "\"terpakai_dbd_anak\":\"0\","
                        + "\"jumlah_dbd\":\"0\""
                        + "}";
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL,
                        HttpMethod.POST, requestEntity, String.class).getBody());
                System.out.println(root);
                respon = root.path("fasyankes").asText();
                if (respon != null) {
                    if (Sequel.menyimpantf("siranap_ketersediaan_kamar",
                            "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                                KelasSiranap.getSelectedItem().toString(),
                                KdKamar.getText(),
                                Kelas.getSelectedItem().toString(), "1",
                                Kapasitas.getText(), Terpakai.getText(),
                                TerpakaiSuspek.getText(),
                                TerpakaiKonfirmasi.getText(), Antrian.getText(),
                                Prepare.getText(), PreparePlan.getText(), Covid.
                                getSelectedItem().toString()
                            }) == true) {
                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, root.path("fasyankes").
                            asText());
                }
            } catch (HeadlessException | IOException | KeyManagementException | NoSuchAlgorithmException | RestClientException ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null,
                            "Koneksi ke server SIRANAP terputus...!");
                } else if (ex.toString().contains("502")) {
                    JOptionPane.showMessageDialog(null,
                            "Connection timed out. Hayati lelah bang...!");
                } else {
                    JOptionPane.showMessageDialog(null, respon);
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Antrian, BtnBatal);
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
        for (i = 0; i < tbJnsPerawatan.getRowCount(); i++) {
            if (tbJnsPerawatan.getValueAt(i, 0).toString().equals("true")) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("X-rs-id", idrs);
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-pass", passrs);
                    requestJson = "{"
                            + "\"id_tt\":\"" + KelasSiranap.getSelectedItem().
                                    toString().substring(0, 4) + "\""
                            + "}";
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL,
                            HttpMethod.DELETE, requestEntity, String.class).
                            getBody());
                    System.out.println(root);
                    respon = root.path("fasyankes").asText();
                    if (respon != null) {
                        for (i = 0; i < tbJnsPerawatan.getRowCount(); i++) {
                            Sequel.queryu2(
                                    "delete from siranap_ketersediaan_kamar where id_tt=? and kd_bangsal=? and kelas=?",
                                    3, new String[]{
                                        tbJnsPerawatan.getValueAt(i, 1).
                                                toString(), tbJnsPerawatan.
                                                getValueAt(i, 2).toString(),
                                        tbJnsPerawatan.getValueAt(i, 4).
                                                toString()
                                    });
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, root.path(
                                "fasyankes").asText());
                    }
                } catch (HeadlessException | IOException | KeyManagementException | NoSuchAlgorithmException | RestClientException ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null,
                                "Koneksi ke server SIRANAP terputus...!");
                    } else if (ex.toString().contains("502")) {
                        JOptionPane.showMessageDialog(null,
                                "Connection timed out. Hayati lelah bang...!");
                    } else {
                        JOptionPane.showMessageDialog(null, respon);
                    }
                }
            }
        }
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
        if (KdKamar.getText().trim().isEmpty() || NmKamar.getText().trim().
                isEmpty()) {
            Valid.textKosong(KdKamar, "Kode Kamar/Ruang");
        } else if (Kapasitas.getText().trim().isEmpty()) {
            Valid.textKosong(Kapasitas, "Kapasitas");
        } else if (Terpakai.getText().trim().isEmpty()) {
            Valid.textKosong(Terpakai, "Terpakai");
        } else if (TerpakaiSuspek.getText().trim().isEmpty()) {
            Valid.textKosong(TerpakaiSuspek, "Terpakai Suspek");
        } else if (TerpakaiKonfirmasi.getText().trim().isEmpty()) {
            Valid.textKosong(TerpakaiKonfirmasi, "Terpakai Konfirmasi");
        } else if (Antrian.getText().trim().isEmpty()) {
            Valid.textKosong(Antrian, "Antrian");
        } else if (Prepare.getText().trim().isEmpty()) {
            Valid.textKosong(Prepare, "Prepare");
        } else if (PreparePlan.getText().trim().isEmpty()) {
            Valid.textKosong(PreparePlan, "Prepare Plan");
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("X-rs-id", idrs);
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-pass", passrs);
                requestJson = "{"
                        + "\"id_tt\":\"" + KelasSiranap.getSelectedItem().
                                toString().substring(0, 4) + "\","
                        + "\"ruang\":\"" + NmKamar.getText() + "\","
                        + "\"jumlah_ruang\":\"1\","
                        + "\"jumlah\":\"" + Kapasitas.getText() + "\","
                        + "\"terpakai\":\"" + Terpakai.getText() + "\","
                        + "\"terpakai_suspek\":\"" + TerpakaiSuspek.getText() + "\","
                        + "\"terpakai_konfirmasi\":\"" + TerpakaiKonfirmasi.
                                getText() + "\","
                        + "\"antrian\":\"" + Antrian.getText() + "\","
                        + "\"prepare\":\"" + Prepare.getText() + "\","
                        + "\"prepare_plan\":\"" + PreparePlan.getText() + "\","
                        + "\"covid\":\"" + Covid.getSelectedItem().toString() + "\","
                        + "\"terpakai_dbd\":\"0\","
                        + "\"terpakai_dbd_anak\":\"0\","
                        + "\"jumlah_dbd\":\"0\""
                        + "}";
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL,
                        HttpMethod.PUT, requestEntity, String.class).getBody());
                System.out.println(root);
                respon = root.path("fasyankes").asText();
                if (respon != null) {
                    if (Sequel.mengedittf("siranap_ketersediaan_kamar",
                            "id_tt=? and kd_bangsal=? and kelas=?",
                            "id_tt=?,kd_bangsal=?,kelas=?,jumlahruang=?,kapasitas=?,terpakai=?,terpakaisuspek=?,terpakaikonfirmasi=?,antrian=?,prepare=?,prepareplan=?,covid=?",
                            15, new String[]{
                                KelasSiranap.getSelectedItem().toString(),
                                KdKamar.getText(),
                                Kelas.getSelectedItem().toString(), "1",
                                Kapasitas.getText(), Terpakai.getText(),
                                TerpakaiSuspek.getText(),
                                TerpakaiKonfirmasi.getText(), Antrian.getText(),
                                Prepare.getText(), PreparePlan.getText(), Covid.
                                getSelectedItem().toString(),
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                                        getSelectedRow(), 1).toString(),
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                                        getSelectedRow(), 2).toString(),
                                tbJnsPerawatan.getValueAt(tbJnsPerawatan.
                                        getSelectedRow(), 4).toString()
                            }) == true) {
                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, root.path("fasyankes").
                            asText());
                }
            } catch (HeadlessException | IOException | KeyManagementException | NoSuchAlgorithmException | RestClientException ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null,
                            "Koneksi ke server SIRANAP terputus...!");
                } else if (ex.toString().contains("502")) {
                    JOptionPane.showMessageDialog(null,
                            "Connection timed out. Hayati lelah bang...!");
                } else {
                    JOptionPane.showMessageDialog(null, respon);
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
        if (!TCari.getText().trim().isEmpty()) {
            BtnCariActionPerformed(evt);
        }
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
            param.put("parameter", "%" + TCari.getText().trim() + "%");
            Valid.MyReport("rptKamarSiranap.jasper", "report",
                    "::[ Data Ketersediaan Kamar Siranap ]::", param);
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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {

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

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void KdKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKamarKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnKamarActionPerformed(null);
    } else {
        Valid.pindah(evt, KelasSiranap, Kelas);
    }
}//GEN-LAST:event_KdKamarKeyPressed

private void btnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamarActionPerformed
    bangsal.emptTeks();
    bangsal.isCek();
    bangsal.setSize(internalFrame1.getWidth() - 20,
            internalFrame1.getHeight() - 20);
    bangsal.setLocationRelativeTo(internalFrame1);
    bangsal.setVisible(true);
}//GEN-LAST:event_btnKamarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void KelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isCariKetersediaan();
            Kapasitas.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            KdKamar.requestFocus();
        }
    }//GEN-LAST:event_KelasKeyPressed

    private void TerpakaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerpakaiKeyPressed
        Valid.pindah(evt, Kapasitas, TerpakaiKonfirmasi);
    }//GEN-LAST:event_TerpakaiKeyPressed

    private void TerpakaiKonfirmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerpakaiKonfirmasiKeyPressed
        Valid.pindah(evt, Terpakai, TerpakaiSuspek);
    }//GEN-LAST:event_TerpakaiKonfirmasiKeyPressed

    private void TerpakaiSuspekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerpakaiSuspekKeyPressed
        Valid.pindah(evt, TerpakaiKonfirmasi, Antrian);
    }//GEN-LAST:event_TerpakaiSuspekKeyPressed

    private void AntrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AntrianKeyPressed
        Valid.pindah(evt, TerpakaiSuspek, BtnSimpan);
    }//GEN-LAST:event_AntrianKeyPressed

    private void KelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KelasItemStateChanged
        isCariKetersediaan();
    }//GEN-LAST:event_KelasItemStateChanged

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

    private void KelasSiranapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasSiranapKeyPressed
//         Valid.pindah(evt,RuangSiranap,KdKamar);
    }//GEN-LAST:event_KelasSiranapKeyPressed

    private void PrepareKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrepareKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrepareKeyPressed

    private void PreparePlanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PreparePlanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreparePlanKeyPressed

    private void CovidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CovidItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CovidItemStateChanged

    private void CovidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CovidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CovidKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SiranapKetersediaanKamar dialog = new SiranapKetersediaanKamar(new javax.swing.JFrame(), true);
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
    private widget.TextBox Antrian;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.ComboBox Covid;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Kapasitas;
    private widget.TextBox KdKamar;
    private widget.ComboBox Kelas;
    private widget.ComboBox KelasSiranap;
    private widget.Label LCount;
    private widget.TextBox NmKamar;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Prepare;
    private widget.TextBox PreparePlan;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal Tanggal;
    private widget.TextBox Terpakai;
    private widget.TextBox TerpakaiKonfirmasi;
    private widget.TextBox TerpakaiSuspek;
    private widget.Button btnKamar;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJnsPerawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi
                    .prepareStatement("select siranap_ketersediaan_kamar.id_tt,siranap_ketersediaan_kamar.kd_bangsal,"
                            + "bangsal.nm_bangsal,siranap_ketersediaan_kamar.kelas,"
                            + "siranap_ketersediaan_kamar.kapasitas,siranap_ketersediaan_kamar.terpakai,siranap_ketersediaan_kamar.terpakaisuspek,"
                            + "siranap_ketersediaan_kamar.terpakaikonfirmasi,siranap_ketersediaan_kamar.antrian,siranap_ketersediaan_kamar.prepare,siranap_ketersediaan_kamar.prepareplan,siranap_ketersediaan_kamar.covid "
                            + "from siranap_ketersediaan_kamar inner join bangsal on siranap_ketersediaan_kamar.kd_bangsal=bangsal.kd_bangsal where "
                            + "siranap_ketersediaan_kamar.id_tt like ? or "
                            + "siranap_ketersediaan_kamar.kd_bangsal like ? or " + "bangsal.nm_bangsal like ? or "
                            + "siranap_ketersediaan_kamar.kelas like ? order by siranap_ketersediaan_kamar.id_tt");
            try {
                ps.setString(1, "%" + TCari.getText() + "%");
                ps.setString(2, "%" + TCari.getText() + "%");
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{false, rs.getString("id_tt"), rs.getString("kd_bangsal"),
                        rs.getString("nm_bangsal"), rs.getString("kelas"), rs.getString("kapasitas"),
                        rs.getString("terpakai"), rs.getString("terpakaisuspek"),
                        rs.getString("terpakaikonfirmasi"), rs.getString("antrian"), rs.getString("prepare"),
                        rs.getString("prepareplan"), rs.getString("covid")});
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : " + e);
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
        KelasSiranap.setSelectedIndex(0);
        KdKamar.setText("");
        NmKamar.setText("");
        Kelas.setSelectedIndex(0);
        Kapasitas.setText("0");
        Terpakai.setText("0");
        TerpakaiKonfirmasi.setText("0");
        TerpakaiSuspek.setText("0");
        Antrian.setText("0");
        Prepare.setText("0");
        PreparePlan.setText("0");
        Covid.setSelectedIndex(0);
    }

    private void getData() {
        if (tbJnsPerawatan.getSelectedRow() != -1) {
            KelasSiranap.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 1).toString());
            KdKamar.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 2).toString());
            NmKamar.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 3).toString());
            Kelas.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 4).toString());
            Kapasitas.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 5).toString());
            Terpakai.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 6).toString());
            TerpakaiKonfirmasi.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 8).toString());
            TerpakaiSuspek.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 7).toString());
            Antrian.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 9).toString());
            Prepare.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 10).toString());
            PreparePlan.setText(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 11).toString());
            Covid.setSelectedItem(tbJnsPerawatan.getValueAt(tbJnsPerawatan.getSelectedRow(), 12).toString());
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 130));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    /**
     *
     */
    public void isCek() {
        BtnSimpan.setEnabled(akses.getsiranap_ketersediaan_kamar());
        BtnHapus.setEnabled(akses.getsiranap_ketersediaan_kamar());
        BtnEdit.setEnabled(akses.getsiranap_ketersediaan_kamar());
        BtnPrint.setEnabled(akses.getsiranap_ketersediaan_kamar());
    }

    public JTable getTable() {
        return tbJnsPerawatan;
    }

    private void isCariKetersediaan() {
        if (!KdKamar.getText().isEmpty()) {
            Kapasitas.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"
                    + Kelas.getSelectedItem() + "' and kd_bangsal=?", KdKamar.getText()));
            Terpakai.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"
                    + Kelas.getSelectedItem() + "' and status='ISI' and kd_bangsal=?", KdKamar.getText()));
            Antrian.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"
                    + Kelas.getSelectedItem() + "' and status='DIBOOKING' and kd_bangsal=?", KdKamar.getText()));
            Prepare.setText(Sequel.cariIsi("select count(kd_kamar) from kamar where statusdata='1' and kelas='"
                    + Kelas.getSelectedItem() + "' and status='DIBERSIHKAN' and kd_bangsal=?", KdKamar.getText()));
        }
    }

    private static final Logger LOG = Logger.getLogger(SiranapKetersediaanKamar.class.getName());

}
