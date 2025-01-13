/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgRujuk.java
*
* Created on 31 Mei 10, 20:19:56
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import laporan.DlgCariPenyakit;
import laporan.DlgDiagnosaPenyakit;

/**
 * @author perpustakaan
 */
public class DlgOdontogram extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode2;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0;

    private String bagian;

    private DlgCariDokter dokter = new DlgCariDokter(null, false);

    private RMCariKeluhan carikeluhan = new RMCariKeluhan(null, false);

    private RMCariPemeriksaan caripemeriksaan = new RMCariPemeriksaan(null, false);

    private RMCariHasilRadiologi cariradiologi = new RMCariHasilRadiologi(null, false);

    private RMCariHasilLaborat carilaborat = new RMCariHasilLaborat(null, false);

    private RMCariJumlahObat cariobat = new RMCariJumlahObat(null, false);

    private DlgDiagnosaPenyakit penyakit = new DlgDiagnosaPenyakit(null, false);

    private RMCariDiagnosa1 rmcaridiagnosa1 = new RMCariDiagnosa1(null, false);

    private RMCariDiagnosa2 rmcaridiagnosa2 = new RMCariDiagnosa2(null, false);

    private RMCariDiagnosa3 rmcaridiagnosa3 = new RMCariDiagnosa3(null, false);

    private RMCariDiagnosa4 rmcaridiagnosa4 = new RMCariDiagnosa4(null, false);

    private RMCariDiagnosa5 rmcaridiagnosa5 = new RMCariDiagnosa5(null, false);

    private RMCariProsedur1 rmcariprosedur1 = new RMCariProsedur1(null, false);

    private RMCariProsedur2 rmcariprosedur2 = new RMCariProsedur2(null, false);

    private RMCariProsedur3 rmcariprosedur3 = new RMCariProsedur3(null, false);

    private RMCariProsedur4 rmcariprosedur4 = new RMCariProsedur4(null, false);

    private RMCariRencana carirencana = new RMCariRencana(null, false);

    private RMCariTerapi cariterapi = new RMCariTerapi(null, false);

    private DlgCariPenyakit penyakit1 = new DlgCariPenyakit(null, false);

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public DlgOdontogram(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null,
                new Object[]{"Tgl.Rawat", "Status", "No.Rawat", "No.RM", "Nama Pasien", "Kode Dokter", "Dokter Gigi",
                    "Bagian Gigi", "Diagnosa Gigi", "ICD10", "Hasil Pemeriksaan", "Catatan Pemeriksaan",
                    "Tanggal Permintaan", "No Permintaan", "Rahang"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbObat.setModel(tabMode);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode2 = new DefaultTableModel(null,
                new Object[]{"Tgl.Rawat", "Status", "No.Rawat", "No.RM", "Nama Pasien", "Occlusi", "Torus Palatinus",
                    "Torus Mandibularis", "Palatum", "Diastema", "Tambahan Diastema", "Gigi Anomali",
                    "Tambahan Gigi Anomali", "Lain-Lain", "D", "M", "F"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbObat1.setModel(tabMode2);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 17; i++) {
            TableColumn column = tbObat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(65);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(105);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            }
        }
        tbObat1.setDefaultRenderer(Object.class, new WarnaTable());

        TNoPermintaan.setDocument(new batasInput((byte) 15).getKata(TNoPermintaan));
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        DiagnosaUtama.setDocument(new batasInput(80).getKata(DiagnosaUtama));
        KodeDiagnosaUtama.setDocument(new batasInput(10).getKata(KodeDiagnosaUtama));
        Catatan.setDocument(new batasInput(1000).getKata(Catatan));
        TCari.setDocument(new batasInput(100).getKata(TCari));

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
                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    KodeDokter.requestFocus();
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

        cariobat.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (cariobat.getTable().getSelectedRow() != -1) {
                    Catatan.append(
                            cariobat.getTable().getValueAt(cariobat.getTable().getSelectedRow(), 2).toString() + ", ");
                    Catatan.requestFocus();
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

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                tampil();
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

        rmcaridiagnosa1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (rmcaridiagnosa1.getTable().getSelectedRow() != -1) {
                    KodeDiagnosaUtama.setText(rmcaridiagnosa1.getTable()
                            .getValueAt(rmcaridiagnosa1.getTable().getSelectedRow(), 0)
                            .toString());
                    DiagnosaUtama.setText(rmcaridiagnosa1.getTable()
                            .getValueAt(rmcaridiagnosa1.getTable().getSelectedRow(), 1)
                            .toString());
                    KodeDiagnosaUtama.requestFocus();
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

        carirencana.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (carirencana.getTable().getSelectedRow() != -1) {
                    Catatan
                            .append(carirencana.getTable().getValueAt(carirencana.getTable().getSelectedRow(), 2).toString()
                                    + ", ");
                    Catatan.requestFocus();
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

        penyakit1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit1.getTable().getSelectedRow() != -1) {
                    KodeDiagnosaUtama
                            .setText(penyakit1.getTable().getValueAt(penyakit1.getTable().getSelectedRow(), 0).toString());
                    DiagnosaUtama
                            .setText(penyakit1.getTable().getValueAt(penyakit1.getTable().getSelectedRow(), 1).toString());
                }
                DiagnosaUtama.requestFocus();
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

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        FormInput1 = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TabOdontogram = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        DiagnosaUtama = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel11 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Catatan = new widget.TextArea();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        KodeDiagnosaUtama = new widget.TextBox();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        BtnDokter6 = new widget.Button();
        PanelWall = new usu.widget.glass.PanelGlass();
        PanelWall1 = new usu.widget.glass.PanelGlass();
        PanelWall2 = new usu.widget.glass.PanelGlass();
        PanelWall3 = new usu.widget.glass.PanelGlass();
        PanelWall4 = new usu.widget.glass.PanelGlass();
        PanelWall5 = new usu.widget.glass.PanelGlass();
        PanelWall6 = new usu.widget.glass.PanelGlass();
        PanelWall7 = new usu.widget.glass.PanelGlass();
        PanelWall10 = new usu.widget.glass.PanelGlass();
        PanelWall9 = new usu.widget.glass.PanelGlass();
        PanelWall8 = new usu.widget.glass.PanelGlass();
        PanelWall11 = new usu.widget.glass.PanelGlass();
        PanelWall12 = new usu.widget.glass.PanelGlass();
        PanelWall13 = new usu.widget.glass.PanelGlass();
        PanelWall14 = new usu.widget.glass.PanelGlass();
        PanelWall15 = new usu.widget.glass.PanelGlass();
        PanelWall16 = new usu.widget.glass.PanelGlass();
        PanelWall17 = new usu.widget.glass.PanelGlass();
        PanelWall18 = new usu.widget.glass.PanelGlass();
        PanelWall19 = new usu.widget.glass.PanelGlass();
        PanelWall20 = new usu.widget.glass.PanelGlass();
        PanelWall21 = new usu.widget.glass.PanelGlass();
        PanelWall22 = new usu.widget.glass.PanelGlass();
        PanelWall23 = new usu.widget.glass.PanelGlass();
        PanelWall24 = new usu.widget.glass.PanelGlass();
        PanelWall25 = new usu.widget.glass.PanelGlass();
        PanelWall26 = new usu.widget.glass.PanelGlass();
        PanelWall27 = new usu.widget.glass.PanelGlass();
        PanelWall28 = new usu.widget.glass.PanelGlass();
        PanelWall29 = new usu.widget.glass.PanelGlass();
        PanelWall30 = new usu.widget.glass.PanelGlass();
        PanelWall31 = new usu.widget.glass.PanelGlass();
        PanelWall32 = new usu.widget.glass.PanelGlass();
        PanelWall33 = new usu.widget.glass.PanelGlass();
        PanelWall34 = new usu.widget.glass.PanelGlass();
        PanelWall35 = new usu.widget.glass.PanelGlass();
        PanelWall36 = new usu.widget.glass.PanelGlass();
        PanelWall37 = new usu.widget.glass.PanelGlass();
        PanelWall38 = new usu.widget.glass.PanelGlass();
        PanelWall39 = new usu.widget.glass.PanelGlass();
        PanelWall40 = new usu.widget.glass.PanelGlass();
        PanelWall41 = new usu.widget.glass.PanelGlass();
        PanelWall42 = new usu.widget.glass.PanelGlass();
        PanelWall43 = new usu.widget.glass.PanelGlass();
        PanelWall44 = new usu.widget.glass.PanelGlass();
        PanelWall45 = new usu.widget.glass.PanelGlass();
        PanelWall46 = new usu.widget.glass.PanelGlass();
        PanelWall47 = new usu.widget.glass.PanelGlass();
        PanelWall48 = new usu.widget.glass.PanelGlass();
        PanelWall49 = new usu.widget.glass.PanelGlass();
        PanelWall50 = new usu.widget.glass.PanelGlass();
        PanelWall51 = new usu.widget.glass.PanelGlass();
        label15 = new widget.Label();
        gigi18 = new widget.RadioButton();
        gigi17 = new widget.RadioButton();
        gigi28 = new widget.RadioButton();
        gigi16 = new widget.RadioButton();
        gigi15 = new widget.RadioButton();
        gigi14 = new widget.RadioButton();
        gigi13 = new widget.RadioButton();
        gigi12 = new widget.RadioButton();
        gigi11 = new widget.RadioButton();
        gigi21 = new widget.RadioButton();
        gigi22 = new widget.RadioButton();
        gigi23 = new widget.RadioButton();
        gigi24 = new widget.RadioButton();
        gigi25 = new widget.RadioButton();
        gigi26 = new widget.RadioButton();
        gigi27 = new widget.RadioButton();
        gigi31 = new widget.RadioButton();
        gigi32 = new widget.RadioButton();
        gigi33 = new widget.RadioButton();
        gigi34 = new widget.RadioButton();
        gigi35 = new widget.RadioButton();
        gigi36 = new widget.RadioButton();
        gigi37 = new widget.RadioButton();
        gigi38 = new widget.RadioButton();
        gigi41 = new widget.RadioButton();
        gigi42 = new widget.RadioButton();
        gigi43 = new widget.RadioButton();
        gigi44 = new widget.RadioButton();
        gigi45 = new widget.RadioButton();
        gigi46 = new widget.RadioButton();
        gigi47 = new widget.RadioButton();
        gigi48 = new widget.RadioButton();
        gigi51 = new widget.RadioButton();
        gigi52 = new widget.RadioButton();
        gigi53 = new widget.RadioButton();
        gigi54 = new widget.RadioButton();
        gigi55 = new widget.RadioButton();
        gigi61 = new widget.RadioButton();
        gigi62 = new widget.RadioButton();
        gigi63 = new widget.RadioButton();
        gigi64 = new widget.RadioButton();
        gigi65 = new widget.RadioButton();
        gigi71 = new widget.RadioButton();
        gigi72 = new widget.RadioButton();
        gigi73 = new widget.RadioButton();
        gigi74 = new widget.RadioButton();
        gigi75 = new widget.RadioButton();
        gigi81 = new widget.RadioButton();
        gigi82 = new widget.RadioButton();
        gigi83 = new widget.RadioButton();
        gigi84 = new widget.RadioButton();
        gigi85 = new widget.RadioButton();
        Hasil = new widget.ComboBox();
        jLabel4 = new widget.Label();
        TNoPermintaan = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tanggal = new widget.Tanggal();
        jLabel32 = new widget.Label();
        Rahang = new widget.ComboBox();
        internalFrame3 = new widget.InternalFrame();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        scrollInput1 = new widget.ScrollPane();
        FormInput2 = new widget.PanelBiasa();
        jLabel33 = new widget.Label();
        Occlusi = new widget.ComboBox();
        jLabel35 = new widget.Label();
        TorusPalatinus = new widget.ComboBox();
        jLabel34 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        TorusMandibularis = new widget.ComboBox();
        Palatum = new widget.ComboBox();
        GigiAnomali = new widget.ComboBox();
        Diastema = new widget.ComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        TGigiAnomali = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TLain = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        TDiastema = new javax.swing.JTextArea();
        TextF = new javax.swing.JTextField();
        TextD = new javax.swing.JTextField();
        TextM = new javax.swing.JTextField();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pemeriksaan Odontogram ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setBackground(new java.awt.Color(250, 255, 245));
        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(100, 40));
        FormInput1.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput1.add(TNoRw);
        TNoRw.setBounds(70, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput1.add(TPasien);
        TPasien.setBounds(330, 10, 424, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput1.add(TNoRM);
        TNoRM.setBounds(215, 10, 112, 23);

        jLabel8.setText("No.Rawat :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput1.add(jLabel8);
        jLabel8.setBounds(10, 10, 60, 23);

        internalFrame1.add(FormInput1, java.awt.BorderLayout.PAGE_START);

        TabOdontogram.setBackground(new java.awt.Color(255, 255, 253));
        TabOdontogram.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabOdontogram.setForeground(new java.awt.Color(50, 50, 50));
        TabOdontogram.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabOdontogram.setName("TabOdontogram"); // NOI18N
        TabOdontogram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabOdontogramMouseClicked(evt);
            }
        });
        TabOdontogram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TabOdontogramKeyPressed(evt);
            }
        });

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 330));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 330));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 330));
        FormInput.setLayout(null);

        DiagnosaUtama.setHighlighter(null);
        DiagnosaUtama.setName("DiagnosaUtama"); // NOI18N
        DiagnosaUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaUtamaKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaUtama);
        DiagnosaUtama.setBounds(670, 120, 520, 23);

        jLabel27.setText("Diagnosa Gigi :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(540, 120, 120, 23);

        jLabel11.setText("Catatan Pemeriksaan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(540, 200, 120, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Catatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Catatan.setColumns(20);
        Catatan.setRows(5);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Catatan);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(670, 200, 541, 50);

        jLabel30.setText("Hasil Pemeriksaan :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(540, 160, 120, 23);

        jLabel31.setText("Kode ICD :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(1040, 100, 210, 23);

        KodeDiagnosaUtama.setHighlighter(null);
        KodeDiagnosaUtama.setName("KodeDiagnosaUtama"); // NOI18N
        KodeDiagnosaUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDiagnosaUtamaKeyPressed(evt);
            }
        });
        FormInput.add(KodeDiagnosaUtama);
        KodeDiagnosaUtama.setBounds(1190, 120, 75, 23);

        KodeDokter.setEditable(false);
        KodeDokter.setName("KodeDokter"); // NOI18N
        KodeDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokter);
        KodeDokter.setBounds(75, 8, 141, 23);

        NamaDokter.setEditable(false);
        NamaDokter.setName("NamaDokter"); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokter);
        NamaDokter.setBounds(220, 8, 270, 23);

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
        BtnDokter.setBounds(490, 8, 28, 23);

        BtnDokter6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter6.setMnemonic('2');
        BtnDokter6.setToolTipText("Alt+2");
        BtnDokter6.setName("BtnDokter6"); // NOI18N
        BtnDokter6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter6ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter6);
        BtnDokter6.setBounds(1270, 120, 28, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(290, 70, 40, 30);

        PanelWall1.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall1.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall1.setRound(false);
        PanelWall1.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall1.setLayout(null);
        FormInput.add(PanelWall1);
        PanelWall1.setBounds(10, 70, 40, 30);

        PanelWall2.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall2.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall2.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall2.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall2.setRound(false);
        PanelWall2.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall2.setLayout(null);
        FormInput.add(PanelWall2);
        PanelWall2.setBounds(50, 70, 40, 30);

        PanelWall3.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall3.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall3.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall3.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall3.setRound(false);
        PanelWall3.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall3.setLayout(null);
        FormInput.add(PanelWall3);
        PanelWall3.setBounds(90, 70, 40, 30);

        PanelWall4.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall4.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall4.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall4.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall4.setRound(false);
        PanelWall4.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall4.setLayout(null);
        FormInput.add(PanelWall4);
        PanelWall4.setBounds(130, 70, 40, 30);

        PanelWall5.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall5.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall5.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall5.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall5.setRound(false);
        PanelWall5.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall5.setLayout(null);
        FormInput.add(PanelWall5);
        PanelWall5.setBounds(170, 70, 40, 30);

        PanelWall6.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall6.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall6.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall6.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall6.setRound(false);
        PanelWall6.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall6.setLayout(null);
        FormInput.add(PanelWall6);
        PanelWall6.setBounds(210, 70, 40, 30);

        PanelWall7.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall7.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall7.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall7.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall7.setRound(false);
        PanelWall7.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall7.setLayout(null);
        FormInput.add(PanelWall7);
        PanelWall7.setBounds(250, 70, 40, 30);

        PanelWall10.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall10.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall10.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall10.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall10.setRound(false);
        PanelWall10.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall10.setLayout(null);
        FormInput.add(PanelWall10);
        PanelWall10.setBounds(420, 70, 40, 30);

        PanelWall9.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall9.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall9.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall9.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall9.setRound(false);
        PanelWall9.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall9.setLayout(null);
        FormInput.add(PanelWall9);
        PanelWall9.setBounds(380, 70, 40, 30);

        PanelWall8.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall8.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall8.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall8.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall8.setRound(false);
        PanelWall8.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall8.setLayout(null);
        FormInput.add(PanelWall8);
        PanelWall8.setBounds(340, 70, 40, 30);

        PanelWall11.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall11.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall11.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall11.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall11.setRound(false);
        PanelWall11.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall11.setLayout(null);
        FormInput.add(PanelWall11);
        PanelWall11.setBounds(460, 70, 40, 30);

        PanelWall12.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall12.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall12.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall12.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall12.setRound(false);
        PanelWall12.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall12.setLayout(null);
        FormInput.add(PanelWall12);
        PanelWall12.setBounds(500, 70, 40, 30);

        PanelWall13.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall13.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall13.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall13.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall13.setRound(false);
        PanelWall13.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall13.setLayout(null);
        FormInput.add(PanelWall13);
        PanelWall13.setBounds(540, 70, 40, 30);

        PanelWall14.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall14.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall14.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall14.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall14.setRound(false);
        PanelWall14.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall14.setLayout(null);
        FormInput.add(PanelWall14);
        PanelWall14.setBounds(580, 70, 40, 30);

        PanelWall15.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall15.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall15.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall15.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall15.setRound(false);
        PanelWall15.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall15.setLayout(null);
        FormInput.add(PanelWall15);
        PanelWall15.setBounds(620, 70, 40, 30);

        PanelWall16.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall16.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall16.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall16.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall16.setRound(false);
        PanelWall16.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall16.setLayout(null);
        FormInput.add(PanelWall16);
        PanelWall16.setBounds(210, 110, 40, 30);

        PanelWall17.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall17.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall17.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall17.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall17.setRound(false);
        PanelWall17.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall17.setLayout(null);
        FormInput.add(PanelWall17);
        PanelWall17.setBounds(250, 110, 40, 30);

        PanelWall18.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall18.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall18.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall18.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall18.setRound(false);
        PanelWall18.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall18.setLayout(null);
        FormInput.add(PanelWall18);
        PanelWall18.setBounds(290, 110, 40, 30);

        PanelWall19.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall19.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall19.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall19.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall19.setRound(false);
        PanelWall19.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall19.setLayout(null);
        FormInput.add(PanelWall19);
        PanelWall19.setBounds(340, 110, 40, 30);

        PanelWall20.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall20.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall20.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall20.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall20.setRound(false);
        PanelWall20.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall20.setLayout(null);
        FormInput.add(PanelWall20);
        PanelWall20.setBounds(380, 110, 40, 30);

        PanelWall21.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall21.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall21.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall21.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall21.setRound(false);
        PanelWall21.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall21.setLayout(null);
        FormInput.add(PanelWall21);
        PanelWall21.setBounds(420, 110, 40, 30);

        PanelWall22.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall22.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall22.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall22.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall22.setRound(false);
        PanelWall22.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall22.setLayout(null);
        FormInput.add(PanelWall22);
        PanelWall22.setBounds(460, 110, 40, 30);

        PanelWall23.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall23.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall23.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall23.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall23.setRound(false);
        PanelWall23.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall23.setLayout(null);
        FormInput.add(PanelWall23);
        PanelWall23.setBounds(500, 110, 40, 30);

        PanelWall24.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall24.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall24.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall24.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall24.setRound(false);
        PanelWall24.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall24.setLayout(null);
        FormInput.add(PanelWall24);
        PanelWall24.setBounds(130, 110, 40, 30);

        PanelWall25.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall25.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall25.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall25.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall25.setRound(false);
        PanelWall25.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall25.setLayout(null);
        FormInput.add(PanelWall25);
        PanelWall25.setBounds(170, 110, 40, 30);

        PanelWall26.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall26.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall26.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall26.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall26.setRound(false);
        PanelWall26.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall26.setLayout(null);
        FormInput.add(PanelWall26);
        PanelWall26.setBounds(130, 200, 40, 30);

        PanelWall27.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall27.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall27.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall27.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall27.setRound(false);
        PanelWall27.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall27.setLayout(null);
        FormInput.add(PanelWall27);
        PanelWall27.setBounds(170, 200, 40, 30);

        PanelWall28.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall28.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall28.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall28.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall28.setRound(false);
        PanelWall28.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall28.setLayout(null);
        FormInput.add(PanelWall28);
        PanelWall28.setBounds(210, 200, 40, 30);

        PanelWall29.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall29.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall29.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall29.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall29.setRound(false);
        PanelWall29.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall29.setLayout(null);
        FormInput.add(PanelWall29);
        PanelWall29.setBounds(250, 200, 40, 30);

        PanelWall30.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall30.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall30.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall30.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall30.setRound(false);
        PanelWall30.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall30.setLayout(null);
        FormInput.add(PanelWall30);
        PanelWall30.setBounds(290, 200, 40, 30);

        PanelWall31.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall31.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall31.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall31.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall31.setRound(false);
        PanelWall31.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall31.setLayout(null);
        FormInput.add(PanelWall31);
        PanelWall31.setBounds(340, 200, 40, 30);

        PanelWall32.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall32.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall32.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall32.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall32.setRound(false);
        PanelWall32.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall32.setLayout(null);
        FormInput.add(PanelWall32);
        PanelWall32.setBounds(380, 200, 40, 30);

        PanelWall33.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall33.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall33.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall33.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall33.setRound(false);
        PanelWall33.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall33.setLayout(null);
        FormInput.add(PanelWall33);
        PanelWall33.setBounds(420, 200, 40, 30);

        PanelWall34.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall34.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall34.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall34.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall34.setRound(false);
        PanelWall34.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall34.setLayout(null);
        FormInput.add(PanelWall34);
        PanelWall34.setBounds(460, 200, 40, 30);

        PanelWall35.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall35.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall35.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall35.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall35.setRound(false);
        PanelWall35.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall35.setLayout(null);
        FormInput.add(PanelWall35);
        PanelWall35.setBounds(500, 200, 40, 30);

        PanelWall36.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall36.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall36.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall36.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall36.setRound(false);
        PanelWall36.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall36.setLayout(null);
        FormInput.add(PanelWall36);
        PanelWall36.setBounds(10, 240, 40, 30);

        PanelWall37.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall37.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall37.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall37.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall37.setRound(false);
        PanelWall37.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall37.setLayout(null);
        FormInput.add(PanelWall37);
        PanelWall37.setBounds(50, 240, 40, 30);

        PanelWall38.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall38.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall38.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall38.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall38.setRound(false);
        PanelWall38.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall38.setLayout(null);
        FormInput.add(PanelWall38);
        PanelWall38.setBounds(90, 240, 40, 30);

        PanelWall39.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall39.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall39.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall39.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall39.setRound(false);
        PanelWall39.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall39.setLayout(null);
        FormInput.add(PanelWall39);
        PanelWall39.setBounds(130, 240, 40, 30);

        PanelWall40.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall40.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall40.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall40.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall40.setRound(false);
        PanelWall40.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall40.setLayout(null);
        FormInput.add(PanelWall40);
        PanelWall40.setBounds(170, 240, 40, 30);

        PanelWall41.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall41.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall41.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall41.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall41.setRound(false);
        PanelWall41.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall41.setLayout(null);
        FormInput.add(PanelWall41);
        PanelWall41.setBounds(210, 240, 40, 30);

        PanelWall42.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall42.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall42.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall42.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall42.setRound(false);
        PanelWall42.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall42.setLayout(null);
        FormInput.add(PanelWall42);
        PanelWall42.setBounds(250, 240, 40, 30);

        PanelWall43.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall43.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall43.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall43.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall43.setRound(false);
        PanelWall43.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall43.setLayout(null);
        FormInput.add(PanelWall43);
        PanelWall43.setBounds(290, 240, 40, 30);

        PanelWall44.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall44.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall44.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall44.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall44.setRound(false);
        PanelWall44.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall44.setLayout(null);
        FormInput.add(PanelWall44);
        PanelWall44.setBounds(340, 240, 40, 30);

        PanelWall45.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall45.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall45.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall45.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall45.setRound(false);
        PanelWall45.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall45.setLayout(null);
        FormInput.add(PanelWall45);
        PanelWall45.setBounds(380, 240, 40, 30);

        PanelWall46.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall46.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anterior.png"))); // NOI18N
        PanelWall46.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall46.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall46.setRound(false);
        PanelWall46.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall46.setLayout(null);
        FormInput.add(PanelWall46);
        PanelWall46.setBounds(420, 240, 40, 30);

        PanelWall47.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall47.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall47.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall47.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall47.setRound(false);
        PanelWall47.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall47.setLayout(null);
        FormInput.add(PanelWall47);
        PanelWall47.setBounds(460, 240, 40, 30);

        PanelWall48.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall48.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall48.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall48.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall48.setRound(false);
        PanelWall48.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall48.setLayout(null);
        FormInput.add(PanelWall48);
        PanelWall48.setBounds(500, 240, 40, 30);

        PanelWall49.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall49.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall49.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall49.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall49.setRound(false);
        PanelWall49.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall49.setLayout(null);
        FormInput.add(PanelWall49);
        PanelWall49.setBounds(540, 240, 40, 30);

        PanelWall50.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall50.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall50.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall50.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall50.setRound(false);
        PanelWall50.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall50.setLayout(null);
        FormInput.add(PanelWall50);
        PanelWall50.setBounds(580, 240, 40, 30);

        PanelWall51.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall51.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/posterior.png"))); // NOI18N
        PanelWall51.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall51.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall51.setRound(false);
        PanelWall51.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall51.setLayout(null);
        FormInput.add(PanelWall51);
        PanelWall51.setBounds(620, 240, 40, 30);

        label15.setText("Dokter Gigi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(5, 8, 70, 23);

        buttonGroup1.add(gigi18);
        gigi18.setText("18");
        gigi18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi18.setName("gigi18"); // NOI18N
        gigi18.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi18ActionPerformed(evt);
            }
        });
        FormInput.add(gigi18);
        gigi18.setBounds(10, 50, 40, 23);

        buttonGroup1.add(gigi17);
        gigi17.setText("17");
        gigi17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi17.setName("gigi17"); // NOI18N
        gigi17.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi17ActionPerformed(evt);
            }
        });
        FormInput.add(gigi17);
        gigi17.setBounds(50, 50, 40, 23);

        buttonGroup1.add(gigi28);
        gigi28.setText("28");
        gigi28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi28.setName("gigi28"); // NOI18N
        gigi28.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi28ActionPerformed(evt);
            }
        });
        FormInput.add(gigi28);
        gigi28.setBounds(620, 50, 40, 23);

        buttonGroup1.add(gigi16);
        gigi16.setText("16");
        gigi16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi16.setName("gigi16"); // NOI18N
        gigi16.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi16ActionPerformed(evt);
            }
        });
        FormInput.add(gigi16);
        gigi16.setBounds(90, 50, 40, 23);

        buttonGroup1.add(gigi15);
        gigi15.setText("15");
        gigi15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi15.setName("gigi15"); // NOI18N
        gigi15.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi15ActionPerformed(evt);
            }
        });
        FormInput.add(gigi15);
        gigi15.setBounds(130, 50, 40, 23);

        buttonGroup1.add(gigi14);
        gigi14.setText("14");
        gigi14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi14.setName("gigi14"); // NOI18N
        gigi14.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi14ActionPerformed(evt);
            }
        });
        FormInput.add(gigi14);
        gigi14.setBounds(170, 50, 40, 23);

        buttonGroup1.add(gigi13);
        gigi13.setText("13");
        gigi13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi13.setName("gigi13"); // NOI18N
        gigi13.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi13ActionPerformed(evt);
            }
        });
        FormInput.add(gigi13);
        gigi13.setBounds(210, 50, 40, 23);

        buttonGroup1.add(gigi12);
        gigi12.setText("12");
        gigi12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi12.setName("gigi12"); // NOI18N
        gigi12.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi12ActionPerformed(evt);
            }
        });
        FormInput.add(gigi12);
        gigi12.setBounds(250, 50, 40, 23);

        buttonGroup1.add(gigi11);
        gigi11.setText("11");
        gigi11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi11.setName("gigi11"); // NOI18N
        gigi11.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi11ActionPerformed(evt);
            }
        });
        FormInput.add(gigi11);
        gigi11.setBounds(290, 50, 40, 23);

        buttonGroup1.add(gigi21);
        gigi21.setText("21");
        gigi21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi21.setName("gigi21"); // NOI18N
        gigi21.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi21ActionPerformed(evt);
            }
        });
        FormInput.add(gigi21);
        gigi21.setBounds(340, 50, 40, 23);

        buttonGroup1.add(gigi22);
        gigi22.setText("22");
        gigi22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi22.setName("gigi22"); // NOI18N
        gigi22.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi22ActionPerformed(evt);
            }
        });
        FormInput.add(gigi22);
        gigi22.setBounds(380, 50, 40, 23);

        buttonGroup1.add(gigi23);
        gigi23.setText("23");
        gigi23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi23.setName("gigi23"); // NOI18N
        gigi23.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi23ActionPerformed(evt);
            }
        });
        FormInput.add(gigi23);
        gigi23.setBounds(420, 50, 40, 23);

        buttonGroup1.add(gigi24);
        gigi24.setText("24");
        gigi24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi24.setName("gigi24"); // NOI18N
        gigi24.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi24ActionPerformed(evt);
            }
        });
        FormInput.add(gigi24);
        gigi24.setBounds(460, 50, 40, 23);

        buttonGroup1.add(gigi25);
        gigi25.setText("25");
        gigi25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi25.setName("gigi25"); // NOI18N
        gigi25.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi25ActionPerformed(evt);
            }
        });
        FormInput.add(gigi25);
        gigi25.setBounds(500, 50, 40, 23);

        buttonGroup1.add(gigi26);
        gigi26.setText("26");
        gigi26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi26.setName("gigi26"); // NOI18N
        gigi26.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi26ActionPerformed(evt);
            }
        });
        FormInput.add(gigi26);
        gigi26.setBounds(540, 50, 40, 23);

        buttonGroup1.add(gigi27);
        gigi27.setText("27");
        gigi27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi27.setName("gigi27"); // NOI18N
        gigi27.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi27ActionPerformed(evt);
            }
        });
        FormInput.add(gigi27);
        gigi27.setBounds(580, 50, 40, 23);

        buttonGroup1.add(gigi31);
        gigi31.setText("31");
        gigi31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi31.setName("gigi31"); // NOI18N
        gigi31.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi31ActionPerformed(evt);
            }
        });
        FormInput.add(gigi31);
        gigi31.setBounds(340, 270, 40, 23);

        buttonGroup1.add(gigi32);
        gigi32.setText("32");
        gigi32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi32.setName("gigi32"); // NOI18N
        gigi32.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi32ActionPerformed(evt);
            }
        });
        FormInput.add(gigi32);
        gigi32.setBounds(380, 270, 40, 23);

        buttonGroup1.add(gigi33);
        gigi33.setText("33");
        gigi33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi33.setName("gigi33"); // NOI18N
        gigi33.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi33ActionPerformed(evt);
            }
        });
        FormInput.add(gigi33);
        gigi33.setBounds(420, 270, 40, 23);

        buttonGroup1.add(gigi34);
        gigi34.setText("34");
        gigi34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi34.setName("gigi34"); // NOI18N
        gigi34.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi34ActionPerformed(evt);
            }
        });
        FormInput.add(gigi34);
        gigi34.setBounds(460, 270, 40, 23);

        buttonGroup1.add(gigi35);
        gigi35.setText("35");
        gigi35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi35.setName("gigi35"); // NOI18N
        gigi35.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi35ActionPerformed(evt);
            }
        });
        FormInput.add(gigi35);
        gigi35.setBounds(500, 270, 40, 23);

        buttonGroup1.add(gigi36);
        gigi36.setText("36");
        gigi36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi36.setName("gigi36"); // NOI18N
        gigi36.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi36ActionPerformed(evt);
            }
        });
        FormInput.add(gigi36);
        gigi36.setBounds(540, 270, 40, 23);

        buttonGroup1.add(gigi37);
        gigi37.setText("37");
        gigi37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi37.setName("gigi37"); // NOI18N
        gigi37.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi37ActionPerformed(evt);
            }
        });
        FormInput.add(gigi37);
        gigi37.setBounds(580, 270, 40, 23);

        buttonGroup1.add(gigi38);
        gigi38.setText("38");
        gigi38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi38.setName("gigi38"); // NOI18N
        gigi38.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi38ActionPerformed(evt);
            }
        });
        FormInput.add(gigi38);
        gigi38.setBounds(620, 270, 40, 23);

        buttonGroup1.add(gigi41);
        gigi41.setText("41");
        gigi41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi41.setName("gigi41"); // NOI18N
        gigi41.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi41ActionPerformed(evt);
            }
        });
        FormInput.add(gigi41);
        gigi41.setBounds(290, 270, 40, 23);

        buttonGroup1.add(gigi42);
        gigi42.setText("42");
        gigi42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi42.setName("gigi42"); // NOI18N
        gigi42.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi42ActionPerformed(evt);
            }
        });
        FormInput.add(gigi42);
        gigi42.setBounds(250, 270, 40, 23);

        buttonGroup1.add(gigi43);
        gigi43.setText("43");
        gigi43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi43.setName("gigi43"); // NOI18N
        gigi43.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi43ActionPerformed(evt);
            }
        });
        FormInput.add(gigi43);
        gigi43.setBounds(210, 270, 40, 23);

        buttonGroup1.add(gigi44);
        gigi44.setText("44");
        gigi44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi44.setName("gigi44"); // NOI18N
        gigi44.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi44ActionPerformed(evt);
            }
        });
        FormInput.add(gigi44);
        gigi44.setBounds(170, 270, 40, 23);

        buttonGroup1.add(gigi45);
        gigi45.setText("45");
        gigi45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi45.setName("gigi45"); // NOI18N
        gigi45.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi45ActionPerformed(evt);
            }
        });
        FormInput.add(gigi45);
        gigi45.setBounds(130, 270, 40, 23);

        buttonGroup1.add(gigi46);
        gigi46.setText("46");
        gigi46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi46.setName("gigi46"); // NOI18N
        gigi46.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi46ActionPerformed(evt);
            }
        });
        FormInput.add(gigi46);
        gigi46.setBounds(90, 270, 40, 23);

        buttonGroup1.add(gigi47);
        gigi47.setText("47");
        gigi47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi47.setName("gigi47"); // NOI18N
        gigi47.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi47ActionPerformed(evt);
            }
        });
        FormInput.add(gigi47);
        gigi47.setBounds(50, 270, 40, 23);

        buttonGroup1.add(gigi48);
        gigi48.setText("48");
        gigi48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi48.setName("gigi48"); // NOI18N
        gigi48.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi48ActionPerformed(evt);
            }
        });
        FormInput.add(gigi48);
        gigi48.setBounds(10, 270, 40, 23);

        buttonGroup1.add(gigi51);
        gigi51.setText("51");
        gigi51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi51.setName("gigi51"); // NOI18N
        gigi51.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi51ActionPerformed(evt);
            }
        });
        FormInput.add(gigi51);
        gigi51.setBounds(290, 140, 40, 23);

        buttonGroup1.add(gigi52);
        gigi52.setText("52");
        gigi52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi52.setName("gigi52"); // NOI18N
        gigi52.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi52ActionPerformed(evt);
            }
        });
        FormInput.add(gigi52);
        gigi52.setBounds(250, 140, 40, 23);

        buttonGroup1.add(gigi53);
        gigi53.setText("53");
        gigi53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi53.setName("gigi53"); // NOI18N
        gigi53.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi53ActionPerformed(evt);
            }
        });
        FormInput.add(gigi53);
        gigi53.setBounds(210, 140, 40, 23);

        buttonGroup1.add(gigi54);
        gigi54.setText("54");
        gigi54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi54.setName("gigi54"); // NOI18N
        gigi54.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi54ActionPerformed(evt);
            }
        });
        FormInput.add(gigi54);
        gigi54.setBounds(170, 140, 40, 23);

        buttonGroup1.add(gigi55);
        gigi55.setText("55");
        gigi55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi55.setName("gigi55"); // NOI18N
        gigi55.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi55ActionPerformed(evt);
            }
        });
        FormInput.add(gigi55);
        gigi55.setBounds(130, 140, 40, 23);

        buttonGroup1.add(gigi61);
        gigi61.setText("61");
        gigi61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi61.setName("gigi61"); // NOI18N
        gigi61.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi61ActionPerformed(evt);
            }
        });
        FormInput.add(gigi61);
        gigi61.setBounds(340, 140, 40, 23);

        buttonGroup1.add(gigi62);
        gigi62.setText("62");
        gigi62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi62.setName("gigi62"); // NOI18N
        gigi62.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi62ActionPerformed(evt);
            }
        });
        FormInput.add(gigi62);
        gigi62.setBounds(380, 140, 40, 23);

        buttonGroup1.add(gigi63);
        gigi63.setText("63");
        gigi63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi63.setName("gigi63"); // NOI18N
        gigi63.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi63ActionPerformed(evt);
            }
        });
        FormInput.add(gigi63);
        gigi63.setBounds(420, 140, 40, 23);

        buttonGroup1.add(gigi64);
        gigi64.setText("64");
        gigi64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi64.setName("gigi64"); // NOI18N
        gigi64.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi64ActionPerformed(evt);
            }
        });
        FormInput.add(gigi64);
        gigi64.setBounds(460, 140, 40, 23);

        buttonGroup1.add(gigi65);
        gigi65.setText("65");
        gigi65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi65.setName("gigi65"); // NOI18N
        gigi65.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi65ActionPerformed(evt);
            }
        });
        FormInput.add(gigi65);
        gigi65.setBounds(500, 140, 40, 23);

        buttonGroup1.add(gigi71);
        gigi71.setText("71");
        gigi71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi71.setName("gigi71"); // NOI18N
        gigi71.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi71ActionPerformed(evt);
            }
        });
        FormInput.add(gigi71);
        gigi71.setBounds(340, 180, 40, 23);

        buttonGroup1.add(gigi72);
        gigi72.setText("72");
        gigi72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi72.setName("gigi72"); // NOI18N
        gigi72.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi72ActionPerformed(evt);
            }
        });
        FormInput.add(gigi72);
        gigi72.setBounds(380, 180, 40, 23);

        buttonGroup1.add(gigi73);
        gigi73.setText("73");
        gigi73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi73.setName("gigi73"); // NOI18N
        gigi73.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi73ActionPerformed(evt);
            }
        });
        FormInput.add(gigi73);
        gigi73.setBounds(420, 180, 40, 23);

        buttonGroup1.add(gigi74);
        gigi74.setText("74");
        gigi74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi74.setName("gigi74"); // NOI18N
        gigi74.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi74ActionPerformed(evt);
            }
        });
        FormInput.add(gigi74);
        gigi74.setBounds(460, 180, 40, 23);

        buttonGroup1.add(gigi75);
        gigi75.setText("75");
        gigi75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi75.setName("gigi75"); // NOI18N
        gigi75.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi75ActionPerformed(evt);
            }
        });
        FormInput.add(gigi75);
        gigi75.setBounds(500, 180, 40, 23);

        buttonGroup1.add(gigi81);
        gigi81.setText("81");
        gigi81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi81.setName("gigi81"); // NOI18N
        gigi81.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi81ActionPerformed(evt);
            }
        });
        FormInput.add(gigi81);
        gigi81.setBounds(290, 180, 40, 23);

        buttonGroup1.add(gigi82);
        gigi82.setText("82");
        gigi82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi82.setName("gigi82"); // NOI18N
        gigi82.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi82ActionPerformed(evt);
            }
        });
        FormInput.add(gigi82);
        gigi82.setBounds(250, 180, 40, 23);

        buttonGroup1.add(gigi83);
        gigi83.setText("83");
        gigi83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi83.setName("gigi83"); // NOI18N
        gigi83.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi83ActionPerformed(evt);
            }
        });
        FormInput.add(gigi83);
        gigi83.setBounds(210, 180, 40, 23);

        buttonGroup1.add(gigi84);
        gigi84.setText("84");
        gigi84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi84.setName("gigi84"); // NOI18N
        gigi84.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi84ActionPerformed(evt);
            }
        });
        FormInput.add(gigi84);
        gigi84.setBounds(170, 180, 40, 23);

        buttonGroup1.add(gigi85);
        gigi85.setText("85");
        gigi85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gigi85.setName("gigi85"); // NOI18N
        gigi85.setPreferredSize(new java.awt.Dimension(40, 20));
        gigi85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gigi85ActionPerformed(evt);
            }
        });
        FormInput.add(gigi85);
        gigi85.setBounds(130, 180, 40, 23);

        Hasil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Erupsi", "Hilang", "Karies", "Sisa Akar", "Tumpatan", "Goyang", "Calculus", "Abces", "Impaksi", "Protesa", "Hiperemia", "Persistensi", "Malposisi" }));
        Hasil.setName("Hasil"); // NOI18N
        Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilKeyPressed(evt);
            }
        });
        FormInput.add(Hasil);
        Hasil.setBounds(670, 160, 130, 23);

        jLabel4.setText("No.Permintaan :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(680, 8, 98, 23);

        TNoPermintaan.setHighlighter(null);
        TNoPermintaan.setName("TNoPermintaan"); // NOI18N
        TNoPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoPermintaanKeyPressed(evt);
            }
        });
        FormInput.add(TNoPermintaan);
        TNoPermintaan.setBounds(780, 8, 130, 23);

        jLabel15.setText("Tanggal :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(520, 8, 67, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-04-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalItemStateChanged(evt);
            }
        });
        Tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalActionPerformed(evt);
            }
        });
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(590, 8, 90, 23);

        jLabel32.setText("Rahang :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(800, 160, 60, 23);

        Rahang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rahang Atas", "Rahang Bawah" }));
        Rahang.setName("Rahang"); // NOI18N
        Rahang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RahangKeyPressed(evt);
            }
        });
        FormInput.add(Rahang);
        Rahang.setBounds(870, 160, 130, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabOdontogram.addTab("Odontogram", internalFrame2);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 330));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('I');
        ChkInput1.setText(".: Input Data");
        ChkInput1.setToolTipText("Alt+I");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 330));

        FormInput2.setBackground(new java.awt.Color(250, 255, 245));
        FormInput2.setBorder(null);
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(100, 330));
        FormInput2.setLayout(null);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Occlusi :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput2.add(jLabel33);
        jLabel33.setBounds(10, 10, 50, 23);

        Occlusi.setMaximumRowCount(3);
        Occlusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal Bite", "Cross Bite", "Steep Bite" }));
        Occlusi.setName("Occlusi"); // NOI18N
        Occlusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OcclusiKeyPressed(evt);
            }
        });
        FormInput2.add(Occlusi);
        Occlusi.setBounds(140, 10, 130, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Torus Palatinus :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput2.add(jLabel35);
        jLabel35.setBounds(10, 40, 90, 23);

        TorusPalatinus.setMaximumRowCount(5);
        TorusPalatinus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Kecil", "Sedang", "Besar", "Multiple" }));
        TorusPalatinus.setName("TorusPalatinus"); // NOI18N
        TorusPalatinus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TorusPalatinusKeyPressed(evt);
            }
        });
        FormInput2.add(TorusPalatinus);
        TorusPalatinus.setBounds(140, 40, 130, 23);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Torus Mandibularis :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput2.add(jLabel34);
        jLabel34.setBounds(10, 70, 110, 23);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Palatum :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput2.add(jLabel36);
        jLabel36.setBounds(10, 100, 60, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("F :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput2.add(jLabel37);
        jLabel37.setBounds(690, 250, 20, 23);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Catatan tambahan Gigi Anomali, jika Ada");
        jLabel38.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput2.add(jLabel38);
        jLabel38.setBounds(530, 110, 210, 20);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Lain-lain :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput2.add(jLabel40);
        jLabel40.setBounds(450, 160, 60, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("D :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput2.add(jLabel41);
        jLabel41.setBounds(450, 250, 20, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("M :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput2.add(jLabel42);
        jLabel42.setBounds(570, 250, 20, 23);

        TorusMandibularis.setMaximumRowCount(4);
        TorusMandibularis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Sisi Kiri", "Sisi Kanan", "Kedua Sisi" }));
        TorusMandibularis.setName("TorusMandibularis"); // NOI18N
        TorusMandibularis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TorusMandibularisKeyPressed(evt);
            }
        });
        FormInput2.add(TorusMandibularis);
        TorusMandibularis.setBounds(140, 70, 130, 23);

        Palatum.setMaximumRowCount(3);
        Palatum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dalam", "Sedang", "Rendah" }));
        Palatum.setName("Palatum"); // NOI18N
        Palatum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PalatumKeyPressed(evt);
            }
        });
        FormInput2.add(Palatum);
        Palatum.setBounds(140, 100, 130, 23);

        GigiAnomali.setMaximumRowCount(2);
        GigiAnomali.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        GigiAnomali.setName("GigiAnomali"); // NOI18N
        GigiAnomali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GigiAnomaliActionPerformed(evt);
            }
        });
        GigiAnomali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GigiAnomaliKeyPressed(evt);
            }
        });
        FormInput2.add(GigiAnomali);
        GigiAnomali.setBounds(530, 10, 130, 23);

        Diastema.setMaximumRowCount(2);
        Diastema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        Diastema.setName("Diastema"); // NOI18N
        Diastema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiastemaActionPerformed(evt);
            }
        });
        Diastema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiastemaKeyPressed(evt);
            }
        });
        FormInput2.add(Diastema);
        Diastema.setBounds(140, 130, 130, 23);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setForeground(new java.awt.Color(50, 50, 50));
        jScrollPane1.setEnabled(false);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TGigiAnomali.setColumns(20);
        TGigiAnomali.setRows(5);
        TGigiAnomali.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        TGigiAnomali.setEnabled(false);
        TGigiAnomali.setName("TGigiAnomali"); // NOI18N
        jScrollPane1.setViewportView(TGigiAnomali);

        FormInput2.add(jScrollPane1);
        jScrollPane1.setBounds(530, 40, 290, 70);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane2.setForeground(new java.awt.Color(50, 50, 50));
        jScrollPane2.setName("jScrollPane2"); // NOI18N

        TLain.setColumns(20);
        TLain.setRows(5);
        TLain.setName("TLain"); // NOI18N
        jScrollPane2.setViewportView(TLain);

        FormInput2.add(jScrollPane2);
        jScrollPane2.setBounds(530, 160, 290, 70);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane3.setForeground(new java.awt.Color(50, 50, 50));
        jScrollPane3.setEnabled(false);
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        TDiastema.setColumns(20);
        TDiastema.setRows(5);
        TDiastema.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        TDiastema.setEnabled(false);
        TDiastema.setName("TDiastema"); // NOI18N
        jScrollPane3.setViewportView(TDiastema);

        FormInput2.add(jScrollPane3);
        jScrollPane3.setBounds(140, 160, 290, 70);

        TextF.setForeground(new java.awt.Color(50, 50, 50));
        TextF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        TextF.setName("TextF"); // NOI18N
        FormInput2.add(TextF);
        TextF.setBounds(710, 250, 90, 20);

        TextD.setForeground(new java.awt.Color(50, 50, 50));
        TextD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        TextD.setName("TextD"); // NOI18N
        FormInput2.add(TextD);
        TextD.setBounds(470, 250, 90, 20);

        TextM.setForeground(new java.awt.Color(50, 50, 50));
        TextM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        TextM.setName("TextM"); // NOI18N
        FormInput2.add(TextM);
        TextM.setBounds(590, 250, 90, 20);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Diastema :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput2.add(jLabel43);
        jLabel43.setBounds(10, 130, 60, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Gigi Anomali :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput2.add(jLabel44);
        jLabel44.setBounds(450, 10, 80, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Catatan tambahan diastema, jika Ada");
        jLabel45.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput2.add(jLabel45);
        jLabel45.setBounds(140, 230, 190, 20);

        scrollInput1.setViewportView(FormInput2);

        PanelInput1.add(scrollInput1, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat1.setAutoCreateRowSorter(true);
        tbObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat1.setName("tbObat1"); // NOI18N
        tbObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat1MouseClicked(evt);
            }
        });
        tbObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObat1KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbObat1);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabOdontogram.addTab("Tambahan Odontogram", internalFrame3);

        internalFrame1.add(TabOdontogram, java.awt.BorderLayout.CENTER);
        TabOdontogram.getAccessibleContext().setAccessibleName("Odontogram");

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-04-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-04-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void autoNomor() {
        Valid.autoNomer3(
                "select ifnull(MAX(CONVERT(RIGHT(noorder,4),signed)),0) from pemeriksaan_gigi where tanggal='"
                + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' ",
                "OD" + Valid.SetTgl(Tanggal.getSelectedItem() + "").replaceAll("-", ""), 5, TNoPermintaan);
    }

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().isEmpty() || TNoRM.getText().isEmpty() || TPasien.
                getText().isEmpty()) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            switch (TabOdontogram.getSelectedIndex()) {
                case 0:
                    if (gigi11.isSelected() == true) {
                        bagian = "1.1";
                    }
                    if (gigi12.isSelected() == true) {
                        bagian = "1.2";
                    }
                    if (gigi13.isSelected() == true) {
                        bagian = "1.3";
                    }
                    if (gigi14.isSelected() == true) {
                        bagian = "1.4";
                    }
                    if (gigi15.isSelected() == true) {
                        bagian = "1.5";
                    }
                    if (gigi16.isSelected() == true) {
                        bagian = "1.6";
                    }
                    if (gigi17.isSelected() == true) {
                        bagian = "1.7";
                    }
                    if (gigi18.isSelected() == true) {
                        bagian = "1.8";
                    }
                    if (gigi21.isSelected() == true) {
                        bagian = "2.1";
                    }
                    if (gigi22.isSelected() == true) {
                        bagian = "2.2";
                    }
                    if (gigi23.isSelected() == true) {
                        bagian = "2.3";
                    }
                    if (gigi24.isSelected() == true) {
                        bagian = "2.4";
                    }
                    if (gigi25.isSelected() == true) {
                        bagian = "2.5";
                    }
                    if (gigi26.isSelected() == true) {
                        bagian = "2.6";
                    }
                    if (gigi27.isSelected() == true) {
                        bagian = "2.7";
                    }
                    if (gigi28.isSelected() == true) {
                        bagian = "2.8";
                    }
                    if (gigi31.isSelected() == true) {
                        bagian = "3.1";
                    }
                    if (gigi32.isSelected() == true) {
                        bagian = "3.2";
                    }
                    if (gigi33.isSelected() == true) {
                        bagian = "3.3";
                    }
                    if (gigi34.isSelected() == true) {
                        bagian = "3.4";
                    }
                    if (gigi35.isSelected() == true) {
                        bagian = "3.5";
                    }
                    if (gigi36.isSelected() == true) {
                        bagian = "3.6";
                    }
                    if (gigi37.isSelected() == true) {
                        bagian = "3.7";
                    }
                    if (gigi38.isSelected() == true) {
                        bagian = "3.8";
                    }
                    if (gigi41.isSelected() == true) {
                        bagian = "4.1";
                    }
                    if (gigi42.isSelected() == true) {
                        bagian = "4.2";
                    }
                    if (gigi43.isSelected() == true) {
                        bagian = "4.3";
                    }
                    if (gigi44.isSelected() == true) {
                        bagian = "4.4";
                    }
                    if (gigi45.isSelected() == true) {
                        bagian = "4.5";
                    }
                    if (gigi46.isSelected() == true) {
                        bagian = "4.6";
                    }
                    if (gigi47.isSelected() == true) {
                        bagian = "4.7";
                    }
                    if (gigi48.isSelected() == true) {
                        bagian = "4.8";
                    }
                    if (gigi51.isSelected() == true) {
                        bagian = "5.1";
                    }
                    if (gigi52.isSelected() == true) {
                        bagian = "5.2";
                    }
                    if (gigi53.isSelected() == true) {
                        bagian = "5.3";
                    }
                    if (gigi54.isSelected() == true) {
                        bagian = "5.4";
                    }
                    if (gigi55.isSelected() == true) {
                        bagian = "5.5";
                    }
                    if (gigi61.isSelected() == true) {
                        bagian = "6.1";
                    }
                    if (gigi62.isSelected() == true) {
                        bagian = "6.2";
                    }
                    if (gigi63.isSelected() == true) {
                        bagian = "6.3";
                    }
                    if (gigi64.isSelected() == true) {
                        bagian = "6.4";
                    }
                    if (gigi65.isSelected() == true) {
                        bagian = "6.5";
                    }
                    if (gigi71.isSelected() == true) {
                        bagian = "7.1";
                    }
                    if (gigi72.isSelected() == true) {
                        bagian = "7.2";
                    }
                    if (gigi73.isSelected() == true) {
                        bagian = "7.3";
                    }
                    if (gigi74.isSelected() == true) {
                        bagian = "7.4";
                    }
                    if (gigi75.isSelected() == true) {
                        bagian = "7.5";
                    }
                    if (gigi81.isSelected() == true) {
                        bagian = "8.1";
                    }
                    if (gigi82.isSelected() == true) {
                        bagian = "8.2";
                    }
                    if (gigi83.isSelected() == true) {
                        bagian = "8.3";
                    }
                    if (gigi84.isSelected() == true) {
                        bagian = "8.4";
                    }
                    if (gigi85.isSelected() == true) {
                        bagian = "8.5";
                    }
                    if (Sequel.menyimpantf("pemeriksaan_gigi",
                            "?,?,?,?,?,?,?,?,?", "No.Rawat", 9, new String[]{
                                TNoRw.getText(), KodeDokter.getText(), bagian,
                                KodeDiagnosaUtama.getText(),
                                Hasil.getSelectedItem().toString(), Catatan.
                                getText(), Valid.SetTgl(Tanggal.
                                        getSelectedItem() + ""),
                                TNoPermintaan.getText(), Rahang.
                                getSelectedItem().toString()
                            }) == true) {
                        tampil();
                        emptTeks();
                    }
                    break;
                case 1:
                    if (Sequel.menyimpantf("tambahan_pemeriksaan_gigi",
                            "?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 13,
                            new String[]{
                                TNoRw.getText(), Occlusi.getSelectedItem().
                                toString(), TorusPalatinus.getSelectedItem().
                                        toString(),
                                TorusMandibularis.getSelectedItem().toString(),
                                Palatum.getSelectedItem().toString(), Diastema.
                                getSelectedItem().toString(),
                                TDiastema.getText(), GigiAnomali.
                                getSelectedItem().toString(), TGigiAnomali.
                                        getText(), TLain.getText(), TextD.
                                getText(),
                                TextM.getText(), TextF.getText()
                            }) == true) {
                        tampil2();
                        emptTeks();
                    }
                    break;
                default:
                    break;
            }

        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Catatan, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        switch (TabOdontogram.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() > -1) {
                    if (Sequel.queryu2tf(
                            "delete from pemeriksaan_gigi where noorder=?", 1,
                            new String[]{
                                tbObat.getValueAt(tbObat.getSelectedRow(), 13).
                                        toString()
                            }) == true) {
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Gagal menghapus..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
            case 1:
                if (tbObat1.getSelectedRow() > -1) {
                    if (Sequel.queryu2tf(
                            "delete from tambahan_pemeriksaan_gigi where no_rawat=?",
                            1, new String[]{
                                tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).
                                        toString()
                            }) == true) {
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Gagal menghapus..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "Silahkan anda pilih data terlebih dahulu..!!");
                }
                break;
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
        if (TNoRw.getText().isEmpty() || TNoRM.getText().isEmpty() || TPasien.
                getText().isEmpty()) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            switch (TabOdontogram.getSelectedIndex()) {
                case 0:
                    if (tbObat.getSelectedRow() > -1) {
                        if (gigi11.isSelected() == true) {
                            bagian = "1.1";
                        }
                        if (gigi12.isSelected() == true) {
                            bagian = "1.2";
                        }
                        if (gigi13.isSelected() == true) {
                            bagian = "1.3";
                        }
                        if (gigi14.isSelected() == true) {
                            bagian = "1.4";
                        }
                        if (gigi15.isSelected() == true) {
                            bagian = "1.5";
                        }
                        if (gigi16.isSelected() == true) {
                            bagian = "1.6";
                        }
                        if (gigi17.isSelected() == true) {
                            bagian = "1.7";
                        }
                        if (gigi18.isSelected() == true) {
                            bagian = "1.8";
                        }
                        if (gigi21.isSelected() == true) {
                            bagian = "2.1";
                        }
                        if (gigi22.isSelected() == true) {
                            bagian = "2.2";
                        }
                        if (gigi23.isSelected() == true) {
                            bagian = "2.3";
                        }
                        if (gigi24.isSelected() == true) {
                            bagian = "2.4";
                        }
                        if (gigi25.isSelected() == true) {
                            bagian = "2.5";
                        }
                        if (gigi26.isSelected() == true) {
                            bagian = "2.6";
                        }
                        if (gigi27.isSelected() == true) {
                            bagian = "2.7";
                        }
                        if (gigi28.isSelected() == true) {
                            bagian = "2.8";
                        }
                        if (gigi31.isSelected() == true) {
                            bagian = "3.1";
                        }
                        if (gigi32.isSelected() == true) {
                            bagian = "3.2";
                        }
                        if (gigi33.isSelected() == true) {
                            bagian = "3.3";
                        }
                        if (gigi34.isSelected() == true) {
                            bagian = "3.4";
                        }
                        if (gigi35.isSelected() == true) {
                            bagian = "3.5";
                        }
                        if (gigi36.isSelected() == true) {
                            bagian = "3.6";
                        }
                        if (gigi37.isSelected() == true) {
                            bagian = "3.7";
                        }
                        if (gigi38.isSelected() == true) {
                            bagian = "3.8";
                        }
                        if (gigi41.isSelected() == true) {
                            bagian = "4.1";
                        }
                        if (gigi42.isSelected() == true) {
                            bagian = "4.2";
                        }
                        if (gigi43.isSelected() == true) {
                            bagian = "4.3";
                        }
                        if (gigi44.isSelected() == true) {
                            bagian = "4.4";
                        }
                        if (gigi45.isSelected() == true) {
                            bagian = "4.5";
                        }
                        if (gigi46.isSelected() == true) {
                            bagian = "4.6";
                        }
                        if (gigi47.isSelected() == true) {
                            bagian = "4.7";
                        }
                        if (gigi48.isSelected() == true) {
                            bagian = "4.8";
                        }
                        if (gigi51.isSelected() == true) {
                            bagian = "5.1";
                        }
                        if (gigi52.isSelected() == true) {
                            bagian = "5.2";
                        }
                        if (gigi53.isSelected() == true) {
                            bagian = "5.3";
                        }
                        if (gigi54.isSelected() == true) {
                            bagian = "5.4";
                        }
                        if (gigi55.isSelected() == true) {
                            bagian = "5.5";
                        }
                        if (gigi61.isSelected() == true) {
                            bagian = "6.1";
                        }
                        if (gigi62.isSelected() == true) {
                            bagian = "6.2";
                        }
                        if (gigi63.isSelected() == true) {
                            bagian = "6.3";
                        }
                        if (gigi64.isSelected() == true) {
                            bagian = "6.4";
                        }
                        if (gigi65.isSelected() == true) {
                            bagian = "6.5";
                        }
                        if (gigi71.isSelected() == true) {
                            bagian = "7.1";
                        }
                        if (gigi72.isSelected() == true) {
                            bagian = "7.2";
                        }
                        if (gigi73.isSelected() == true) {
                            bagian = "7.3";
                        }
                        if (gigi74.isSelected() == true) {
                            bagian = "7.4";
                        }
                        if (gigi75.isSelected() == true) {
                            bagian = "7.5";
                        }
                        if (gigi81.isSelected() == true) {
                            bagian = "8.1";
                        }
                        if (gigi82.isSelected() == true) {
                            bagian = "8.2";
                        }
                        if (gigi83.isSelected() == true) {
                            bagian = "8.3";
                        }
                        if (gigi84.isSelected() == true) {
                            bagian = "8.4";
                        }
                        if (gigi85.isSelected() == true) {
                            bagian = "8.5";
                        }
                    } else if (DiagnosaUtama.getText().isEmpty()) {
                        Valid.textKosong(DiagnosaUtama, "Diagnosa Gigi");
                    } else if (Catatan.getText().isEmpty()) {
                        Valid.textKosong(Catatan, "Catatan Pemeriksaan");
                    } else {
                        if (tbObat.getSelectedRow() > -1) {
                            if (Sequel.mengedittf("pemeriksaan_gigi",
                                    "no_rawat=?",
                                    "no_rawat=?,kd_dokter=?,bagian=?,kd_diagnosa=?,hasil=?,catatan=?,tanggal=?,rahang=?",
                                    9, new String[]{
                                        TNoRw.getText(), KodeDokter.getText(),
                                        bagian, KodeDiagnosaUtama.getText(),
                                        Hasil.getSelectedItem().toString(),
                                        Catatan.getText(), Valid.SetTgl(Tanggal.
                                        getSelectedItem() + ""),
                                        Rahang.getSelectedItem().toString(),
                                        tbObat.getValueAt(tbObat.
                                                getSelectedRow(), 2).toString()
                                    }) == true) {
                                tampil();
                                emptTeks();
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane,
                                    "Silahkan anda pilih data terlebih dahulu..!!");
                        }
                    }
                    break;
                case 1:
                    if (tbObat1.getSelectedRow() > -1) {
                        if (Sequel.mengedittf("tambahan_pemeriksaan_gigi",
                                "no_rawat=?",
                                "no_rawat=?,occlusi=?,torus_palatinus=?,torus_mandibularis=?,palatum=?,diastema=?,tambahan_diastema=?,gigi_anomali=?,tambahan_gigi_anomali=?,lain=?,d=?,m=?,f=?",
                                14, new String[]{
                                    TNoRw.getText(), Occlusi.getSelectedItem().
                                    toString(),
                                    TorusPalatinus.getSelectedItem().toString(),
                                    TorusMandibularis.getSelectedItem().
                                            toString(),
                                    Palatum.getSelectedItem().toString(),
                                    Diastema.getSelectedItem().toString(),
                                    TDiastema.getText(), GigiAnomali.
                                    getSelectedItem().toString(),
                                    TGigiAnomali.getText(), TLain.getText(),
                                    TextD.getText(), TextM.getText(), TextF.
                                    getText(),
                                    tbObat1.getValueAt(tbObat1.getSelectedRow(),
                                            2).toString()
                                }) == true) {
                            tampil2();
                            emptTeks();
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                "Silahkan anda pilih data terlebih dahulu..!!");
                    }
                    break;
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
        dokter.dispose();
        carikeluhan.dispose();
        caripemeriksaan.dispose();
        carilaborat.dispose();
        cariobat.dispose();
        cariradiologi.dispose();
        penyakit.dispose();
        rmcaridiagnosa1.dispose();
        rmcaridiagnosa2.dispose();
        rmcaridiagnosa3.dispose();
        rmcaridiagnosa4.dispose();
        rmcaridiagnosa5.dispose();
        rmcariprosedur1.dispose();
        rmcariprosedur2.dispose();
        rmcariprosedur3.dispose();
        rmcariprosedur4.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed

}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed

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
        TampilkanData();
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
        TampilkanData();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TampilkanData();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    ChkInput.setSelected(true);
                    isForm();
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void DiagnosaUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaUtamaKeyPressed
        Valid.pindah(evt, Hasil, KodeDiagnosaUtama);
    }//GEN-LAST:event_DiagnosaUtamaKeyPressed

    private void KodeDiagnosaUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDiagnosaUtamaKeyPressed
        Valid.pindah(evt, DiagnosaUtama, Hasil);
    }//GEN-LAST:event_KodeDiagnosaUtamaKeyPressed

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
        Valid.pindah(evt, TCari, DiagnosaUtama);
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt, TCari, DiagnosaUtama);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.isShiftDown()) {
                BtnSimpan.requestFocus();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Hasil.requestFocus();
        }
    }//GEN-LAST:event_CatatanKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void BtnDokter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter6ActionPerformed
        penyakit1.isCek();
        penyakit1.emptTeks();
        penyakit1.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        penyakit1.setLocationRelativeTo(internalFrame1);
        penyakit1.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_BtnDokter6ActionPerformed

    private void gigi18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi18ActionPerformed
        if (gigi18.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi18ActionPerformed

    private void gigi17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi17ActionPerformed
        if (gigi17.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi17ActionPerformed

    private void gigi28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi28ActionPerformed
        if (gigi28.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi28ActionPerformed

    private void gigi16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi16ActionPerformed
        if (gigi16.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi16ActionPerformed

    private void gigi15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi15ActionPerformed
        if (gigi15.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi15ActionPerformed

    private void gigi14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi14ActionPerformed
        if (gigi14.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi14ActionPerformed

    private void gigi13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi13ActionPerformed
        if (gigi13.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi13ActionPerformed

    private void gigi12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi12ActionPerformed
        if (gigi12.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi12ActionPerformed

    private void gigi11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi11ActionPerformed
        if (gigi11.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi11ActionPerformed

    private void gigi21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi21ActionPerformed
        if (gigi21.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi21ActionPerformed

    private void gigi22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi22ActionPerformed
        if (gigi22.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi22ActionPerformed

    private void gigi23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi23ActionPerformed
        if (gigi23.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi23ActionPerformed

    private void gigi24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi24ActionPerformed
        if (gigi24.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi24ActionPerformed

    private void gigi25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi25ActionPerformed
        if (gigi25.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi25ActionPerformed

    private void gigi26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi26ActionPerformed
        if (gigi26.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi26ActionPerformed

    private void gigi27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi27ActionPerformed
        if (gigi27.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi27ActionPerformed

    private void gigi31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi31ActionPerformed
        if (gigi31.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi31ActionPerformed

    private void gigi32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi32ActionPerformed
        if (gigi32.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi32ActionPerformed

    private void gigi33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi33ActionPerformed
        if (gigi33.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi33ActionPerformed

    private void gigi34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi34ActionPerformed
        if (gigi34.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi34ActionPerformed

    private void gigi35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi35ActionPerformed
        if (gigi35.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi35ActionPerformed

    private void gigi36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi36ActionPerformed
        if (gigi36.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi36ActionPerformed

    private void gigi37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi37ActionPerformed
        if (gigi37.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi37ActionPerformed

    private void gigi38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi38ActionPerformed
        if (gigi38.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi38ActionPerformed

    private void gigi41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi41ActionPerformed
        if (gigi41.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi41ActionPerformed

    private void gigi42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi42ActionPerformed
        if (gigi42.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi42ActionPerformed

    private void gigi43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi43ActionPerformed
        if (gigi43.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi43ActionPerformed

    private void gigi44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi44ActionPerformed
        if (gigi44.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi44ActionPerformed

    private void gigi45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi45ActionPerformed
        if (gigi45.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi45ActionPerformed

    private void gigi46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi46ActionPerformed
        if (gigi46.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi46ActionPerformed

    private void gigi47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi47ActionPerformed
        if (gigi47.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi47ActionPerformed

    private void gigi48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi48ActionPerformed
        if (gigi48.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi48ActionPerformed

    private void gigi51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi51ActionPerformed
        if (gigi51.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi51ActionPerformed

    private void gigi52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi52ActionPerformed
        if (gigi52.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi52ActionPerformed

    private void gigi53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi53ActionPerformed
        if (gigi53.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi53ActionPerformed

    private void gigi54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi54ActionPerformed
        if (gigi54.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi54ActionPerformed

    private void gigi55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi55ActionPerformed
        if (gigi55.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi55ActionPerformed

    private void gigi61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi61ActionPerformed
        if (gigi61.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi61ActionPerformed

    private void gigi62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi62ActionPerformed
        if (gigi62.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi62ActionPerformed

    private void gigi63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi63ActionPerformed
        if (gigi63.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi63ActionPerformed

    private void gigi64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi64ActionPerformed
        if (gigi64.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi64ActionPerformed

    private void gigi65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi65ActionPerformed
        if (gigi65.isSelected() == true) {
            Rahang.setSelectedIndex(1);
        }
    }//GEN-LAST:event_gigi65ActionPerformed

    private void gigi71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi71ActionPerformed
        if (gigi71.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi71ActionPerformed

    private void gigi72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi72ActionPerformed
        if (gigi72.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi72ActionPerformed

    private void gigi73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi73ActionPerformed
        if (gigi73.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi73ActionPerformed

    private void gigi74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi74ActionPerformed
        if (gigi74.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi74ActionPerformed

    private void gigi75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi75ActionPerformed
        if (gigi75.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi75ActionPerformed

    private void gigi81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi81ActionPerformed
        if (gigi81.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi81ActionPerformed

    private void gigi82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi82ActionPerformed
        if (gigi82.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi82ActionPerformed

    private void gigi83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi83ActionPerformed
        if (gigi83.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi83ActionPerformed

    private void gigi84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi84ActionPerformed
        if (gigi84.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi84ActionPerformed

    private void gigi85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gigi85ActionPerformed
        if (gigi85.isSelected() == true) {
            Rahang.setSelectedIndex(2);
        }
    }//GEN-LAST:event_gigi85ActionPerformed

    private void HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilKeyPressed
        Valid.pindah(evt, Hasil, Catatan);
    }//GEN-LAST:event_HasilKeyPressed

    private void TNoPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoPermintaanKeyPressed
        //Valid.pindah(evt,TNoReg,DTPReg);
    }//GEN-LAST:event_TNoPermintaanKeyPressed

    private void TanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalItemStateChanged
        try {
            autoNomor();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TanggalItemStateChanged

    private void TanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, Hasil, Catatan);
    }//GEN-LAST:event_TanggalKeyPressed

    private void RahangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RahangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RahangKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPasienKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMKeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void OcclusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OcclusiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OcclusiKeyPressed

    private void TorusPalatinusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TorusPalatinusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TorusPalatinusKeyPressed

    private void TorusMandibularisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TorusMandibularisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TorusMandibularisKeyPressed

    private void PalatumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PalatumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PalatumKeyPressed

    private void GigiAnomaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GigiAnomaliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GigiAnomaliKeyPressed

    private void DiastemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiastemaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiastemaKeyPressed

    private void DiastemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiastemaActionPerformed
        if (Diastema.getSelectedIndex() == 1) {
            jScrollPane3.setEnabled(true);
            TDiastema.setEnabled(true);
        } else {
            jScrollPane3.setEnabled(false);
            TDiastema.setEnabled(false);
        }
    }//GEN-LAST:event_DiastemaActionPerformed

    private void GigiAnomaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GigiAnomaliActionPerformed
        if (GigiAnomali.getSelectedIndex() == 1) {
            jScrollPane1.setEnabled(true);
            TGigiAnomali.setEnabled(true);
        } else {
            jScrollPane1.setEnabled(false);
            TGigiAnomali.setEnabled(false);
        }
    }//GEN-LAST:event_GigiAnomaliActionPerformed

    private void tbObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat1MouseClicked

    private void tbObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat1KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    ChkInput.setSelected(true);
                    isForm();
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObat1KeyPressed

    private void TabOdontogramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabOdontogramMouseClicked
        switch (TabOdontogram.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
        }
    }//GEN-LAST:event_TabOdontogramMouseClicked

    private void TabOdontogramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabOdontogramKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TabOdontogramKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgOdontogram dialog = new DlgOdontogram(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnDokter6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextArea Catatan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DiagnosaUtama;
    private widget.ComboBox Diastema;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.ComboBox GigiAnomali;
    private widget.ComboBox Hasil;
    private widget.TextBox KodeDiagnosaUtama;
    private widget.TextBox KodeDokter;
    private widget.Label LCount;
    private widget.TextBox NamaDokter;
    private widget.ComboBox Occlusi;
    private widget.ComboBox Palatum;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private usu.widget.glass.PanelGlass PanelWall;
    private usu.widget.glass.PanelGlass PanelWall1;
    private usu.widget.glass.PanelGlass PanelWall10;
    private usu.widget.glass.PanelGlass PanelWall11;
    private usu.widget.glass.PanelGlass PanelWall12;
    private usu.widget.glass.PanelGlass PanelWall13;
    private usu.widget.glass.PanelGlass PanelWall14;
    private usu.widget.glass.PanelGlass PanelWall15;
    private usu.widget.glass.PanelGlass PanelWall16;
    private usu.widget.glass.PanelGlass PanelWall17;
    private usu.widget.glass.PanelGlass PanelWall18;
    private usu.widget.glass.PanelGlass PanelWall19;
    private usu.widget.glass.PanelGlass PanelWall2;
    private usu.widget.glass.PanelGlass PanelWall20;
    private usu.widget.glass.PanelGlass PanelWall21;
    private usu.widget.glass.PanelGlass PanelWall22;
    private usu.widget.glass.PanelGlass PanelWall23;
    private usu.widget.glass.PanelGlass PanelWall24;
    private usu.widget.glass.PanelGlass PanelWall25;
    private usu.widget.glass.PanelGlass PanelWall26;
    private usu.widget.glass.PanelGlass PanelWall27;
    private usu.widget.glass.PanelGlass PanelWall28;
    private usu.widget.glass.PanelGlass PanelWall29;
    private usu.widget.glass.PanelGlass PanelWall3;
    private usu.widget.glass.PanelGlass PanelWall30;
    private usu.widget.glass.PanelGlass PanelWall31;
    private usu.widget.glass.PanelGlass PanelWall32;
    private usu.widget.glass.PanelGlass PanelWall33;
    private usu.widget.glass.PanelGlass PanelWall34;
    private usu.widget.glass.PanelGlass PanelWall35;
    private usu.widget.glass.PanelGlass PanelWall36;
    private usu.widget.glass.PanelGlass PanelWall37;
    private usu.widget.glass.PanelGlass PanelWall38;
    private usu.widget.glass.PanelGlass PanelWall39;
    private usu.widget.glass.PanelGlass PanelWall4;
    private usu.widget.glass.PanelGlass PanelWall40;
    private usu.widget.glass.PanelGlass PanelWall41;
    private usu.widget.glass.PanelGlass PanelWall42;
    private usu.widget.glass.PanelGlass PanelWall43;
    private usu.widget.glass.PanelGlass PanelWall44;
    private usu.widget.glass.PanelGlass PanelWall45;
    private usu.widget.glass.PanelGlass PanelWall46;
    private usu.widget.glass.PanelGlass PanelWall47;
    private usu.widget.glass.PanelGlass PanelWall48;
    private usu.widget.glass.PanelGlass PanelWall49;
    private usu.widget.glass.PanelGlass PanelWall5;
    private usu.widget.glass.PanelGlass PanelWall50;
    private usu.widget.glass.PanelGlass PanelWall51;
    private usu.widget.glass.PanelGlass PanelWall6;
    private usu.widget.glass.PanelGlass PanelWall7;
    private usu.widget.glass.PanelGlass PanelWall8;
    private usu.widget.glass.PanelGlass PanelWall9;
    private widget.ComboBox Rahang;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private javax.swing.JTextArea TDiastema;
    private javax.swing.JTextArea TGigiAnomali;
    private javax.swing.JTextArea TLain;
    private widget.TextBox TNoPermintaan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabOdontogram;
    private widget.Tanggal Tanggal;
    private javax.swing.JTextField TextD;
    private javax.swing.JTextField TextF;
    private javax.swing.JTextField TextM;
    private widget.ComboBox TorusMandibularis;
    private widget.ComboBox TorusPalatinus;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.RadioButton gigi11;
    private widget.RadioButton gigi12;
    private widget.RadioButton gigi13;
    private widget.RadioButton gigi14;
    private widget.RadioButton gigi15;
    private widget.RadioButton gigi16;
    private widget.RadioButton gigi17;
    private widget.RadioButton gigi18;
    private widget.RadioButton gigi21;
    private widget.RadioButton gigi22;
    private widget.RadioButton gigi23;
    private widget.RadioButton gigi24;
    private widget.RadioButton gigi25;
    private widget.RadioButton gigi26;
    private widget.RadioButton gigi27;
    private widget.RadioButton gigi28;
    private widget.RadioButton gigi31;
    private widget.RadioButton gigi32;
    private widget.RadioButton gigi33;
    private widget.RadioButton gigi34;
    private widget.RadioButton gigi35;
    private widget.RadioButton gigi36;
    private widget.RadioButton gigi37;
    private widget.RadioButton gigi38;
    private widget.RadioButton gigi41;
    private widget.RadioButton gigi42;
    private widget.RadioButton gigi43;
    private widget.RadioButton gigi44;
    private widget.RadioButton gigi45;
    private widget.RadioButton gigi46;
    private widget.RadioButton gigi47;
    private widget.RadioButton gigi48;
    private widget.RadioButton gigi51;
    private widget.RadioButton gigi52;
    private widget.RadioButton gigi53;
    private widget.RadioButton gigi54;
    private widget.RadioButton gigi55;
    private widget.RadioButton gigi61;
    private widget.RadioButton gigi62;
    private widget.RadioButton gigi63;
    private widget.RadioButton gigi64;
    private widget.RadioButton gigi65;
    private widget.RadioButton gigi71;
    private widget.RadioButton gigi72;
    private widget.RadioButton gigi73;
    private widget.RadioButton gigi74;
    private widget.RadioButton gigi75;
    private widget.RadioButton gigi81;
    private widget.RadioButton gigi82;
    private widget.RadioButton gigi83;
    private widget.RadioButton gigi84;
    private widget.RadioButton gigi85;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel11;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel27;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private widget.Label label15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollInput1;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().isEmpty()) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.tgl_registrasi,reg_periksa.no_rawat,reg_periksa.status_lanjut,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                        + "pemeriksaan_gigi.kd_dokter,dokter.nm_dokter,pemeriksaan_gigi.bagian,pemeriksaan_gigi.kd_diagnosa,penyakit.nm_penyakit, "
                        + "pemeriksaan_gigi.hasil,pemeriksaan_gigi.catatan,pemeriksaan_gigi.tanggal,pemeriksaan_gigi.noorder,pemeriksaan_gigi.rahang from pemeriksaan_gigi inner join reg_periksa on pemeriksaan_gigi.no_rawat=reg_periksa.no_rawat  "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on pemeriksaan_gigi.kd_dokter=dokter.kd_dokter inner join penyakit on pemeriksaan_gigi.kd_diagnosa = penyakit.kd_penyakit "
                        + "where reg_periksa.tgl_registrasi between ? and ? order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.tgl_registrasi,reg_periksa.no_rawat,reg_periksa.status_lanjut,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                        + "pemeriksaan_gigi.kd_dokter,dokter.nm_dokter,pemeriksaan_gigi.bagian,pemeriksaan_gigi.kd_diagnosa,penyakit.nm_penyakit, "
                        + "pemeriksaan_gigi.hasil,pemeriksaan_gigi.catatan,pemeriksaan_gigi.tanggal,pemeriksaan_gigi.noorder,pemeriksaan_gigi.rahang from pemeriksaan_gigi inner join reg_periksa on pemeriksaan_gigi.no_rawat=reg_periksa.no_rawat  "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on pemeriksaan_gigi.kd_dokter=dokter.kd_dokter inner join penyakit on pemeriksaan_gigi.kd_diagnosa = penyakit.kd_penyakit "
                        + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and pemeriksaan_gigi.kd_dokter like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and pemeriksaan_gigi.kd_diagnosa like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                        + "order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut");
            }
            try {
                if (TCari.getText().isEmpty()) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText() + "%");
                    ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari.getText() + "%");
                    ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari.getText() + "%");
                    ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{rs.getString("tgl_registrasi"), rs.getString("status_lanjut"),
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("kd_dokter"), rs.getString("nm_dokter"), rs.getString("bagian"),
                        rs.getString("nm_penyakit"), rs.getString("kd_diagnosa"), rs.getString("hasil"),
                        rs.getString("catatan"), rs.getString("tanggal"), rs.getString("noorder"),
                        rs.getString("rahang")});
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
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }

    /**
     *
     */
    public void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            if (TCari.getText().isEmpty()) {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.tgl_registrasi,reg_periksa.no_rawat,reg_periksa.status_lanjut,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "tambahan_pemeriksaan_gigi.occlusi,tambahan_pemeriksaan_gigi.torus_palatinus,tambahan_pemeriksaan_gigi.torus_mandibularis,"
                        + "tambahan_pemeriksaan_gigi.palatum,tambahan_pemeriksaan_gigi.diastema,tambahan_pemeriksaan_gigi.tambahan_diastema,tambahan_pemeriksaan_gigi.gigi_anomali,tambahan_pemeriksaan_gigi.tambahan_gigi_anomali,"
                        + "tambahan_pemeriksaan_gigi.lain,tambahan_pemeriksaan_gigi.d,tambahan_pemeriksaan_gigi.m,tambahan_pemeriksaan_gigi.f from tambahan_pemeriksaan_gigi inner join reg_periksa on tambahan_pemeriksaan_gigi.no_rawat=reg_periksa.no_rawat  "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where reg_periksa.tgl_registrasi between ? and ? order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut");
            } else {
                ps = koneksi.prepareStatement(
                        "select reg_periksa.tgl_registrasi,reg_periksa.no_rawat,reg_periksa.status_lanjut,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "tambahan_pemeriksaan_gigi.occlusi,tambahan_pemeriksaan_gigi.torus_palatinus,tambahan_pemeriksaan_gigi.torus_mandibularis,"
                        + "tambahan_pemeriksaan_gigi.palatum,tambahan_pemeriksaan_gigi.diastema,tambahan_pemeriksaan_gigi.tambahan_diastema,tambahan_pemeriksaan_gigi.gigi_anomali,tambahan_pemeriksaan_gigi.tambahan_gigi_anomali,"
                        + "tambahan_pemeriksaan_gigi.lain,tambahan_pemeriksaan_gigi.d,tambahan_pemeriksaan_gigi.m,tambahan_pemeriksaan_gigi.f from tambahan_pemeriksaan_gigi inner join reg_periksa on tambahan_pemeriksaan_gigi.no_rawat=reg_periksa.no_rawat  "
                        + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.status_lanjut like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                        + "order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut");
            }
            try {
                if (TCari.getText().isEmpty()) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText() + "%");
                    ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new String[]{rs.getString("tgl_registrasi"), rs.getString("status_lanjut"),
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("occlusi"), rs.getString("torus_palatinus"),
                        rs.getString("torus_mandibularis"), rs.getString("palatum"), rs.getString("diastema"),
                        rs.getString("tambahan_diastema"), rs.getString("gigi_anomali"),
                        rs.getString("tambahan_gigi_anomali"), rs.getString("lain"), rs.getString("d"),
                        rs.getString("m"), rs.getString("f")});
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
        int b = tabMode2.getRowCount();
        LCount.setText("" + b);
    }

    public void emptTeks() {
        switch (TabOdontogram.getSelectedIndex()) {
            case 0:
                Valid.autoNomer3(
                        "select ifnull(MAX(CONVERT(RIGHT(noorder,4),signed)),0) from pemeriksaan_gigi where tanggal='"
                        + Valid.SetTgl(Tanggal.getSelectedItem() + "") + "' ",
                        "OD" + Valid.SetTgl(Tanggal.getSelectedItem() + "").replaceAll("-", ""), 5, TNoPermintaan);
                Catatan.setText("");
                DiagnosaUtama.setText("");
                KodeDiagnosaUtama.setText("");
                Catatan.setText("");
                Hasil.requestFocus();
                Rahang.setSelectedIndex(0);
                buttonGroup1.clearSelection();
                break;
            case 1:
                Occlusi.setSelectedIndex(0);
                TorusPalatinus.setSelectedIndex(0);
                TorusMandibularis.setSelectedIndex(0);
                Palatum.setSelectedIndex(0);
                Diastema.setSelectedIndex(0);
                TDiastema.setText("");
                GigiAnomali.setSelectedIndex(0);
                TGigiAnomali.setText("");
                TLain.setText("");
                TextD.setText("");
                TextM.setText("");
                TextF.setText("");
                break;
        }

    }

    private void getData() {
        switch (TabOdontogram.getSelectedIndex()) {
            case 0:
                if (tbObat.getSelectedRow() != -1) {
                    TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
                    TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
                    TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
                    KodeDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
                    NamaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.1")) {
                        gigi11.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.2")) {
                        gigi12.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.3")) {
                        gigi13.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.4")) {
                        gigi14.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.5")) {
                        gigi15.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.6")) {
                        gigi16.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.7")) {
                        gigi17.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("1.8")) {
                        gigi18.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.1")) {
                        gigi21.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.2")) {
                        gigi22.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.3")) {
                        gigi23.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.4")) {
                        gigi24.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.5")) {
                        gigi25.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.6")) {
                        gigi26.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.7")) {
                        gigi27.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("2.8")) {
                        gigi28.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.1")) {
                        gigi31.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.2")) {
                        gigi32.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.3")) {
                        gigi33.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.4")) {
                        gigi34.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.5")) {
                        gigi35.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.6")) {
                        gigi36.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.7")) {
                        gigi37.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("3.8")) {
                        gigi38.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.1")) {
                        gigi41.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.2")) {
                        gigi42.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.3")) {
                        gigi43.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.4")) {
                        gigi44.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.5")) {
                        gigi45.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.6")) {
                        gigi46.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.7")) {
                        gigi47.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("4.8")) {
                        gigi48.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("5.1")) {
                        gigi51.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("5.2")) {
                        gigi52.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("5.3")) {
                        gigi53.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("5.4")) {
                        gigi54.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("5.5")) {
                        gigi55.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("6.1")) {
                        gigi61.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("6.2")) {
                        gigi62.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("6.3")) {
                        gigi63.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("6.4")) {
                        gigi64.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("6.5")) {
                        gigi65.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("7.1")) {
                        gigi71.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("7.2")) {
                        gigi72.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("7.3")) {
                        gigi73.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("7.4")) {
                        gigi74.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("7.5")) {
                        gigi75.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("8.1")) {
                        gigi81.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("8.2")) {
                        gigi82.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("8.3")) {
                        gigi83.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("8.4")) {
                        gigi84.setSelected(true);
                    }
                    if (tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString().equals("8.5")) {
                        gigi85.setSelected(true);
                    }
                    DiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
                    KodeDiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
                    Hasil.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
                    Catatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
                    Valid.SetTgl2(Tanggal, tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
                    TNoPermintaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
                    Rahang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
                }
                break;
            case 1:
                if (tbObat1.getSelectedRow() != -1) {
                    TNoRw.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 2).toString());
                    TNoRM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 3).toString());
                    TPasien.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 4).toString());
                    Occlusi.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 5).toString());
                    TorusPalatinus.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 6).toString());
                    TorusMandibularis.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 7).toString());
                    Palatum.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 8).toString());
                    Diastema.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 9).toString());
                    TDiastema.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 10).toString());
                    GigiAnomali.setSelectedItem(tbObat1.getValueAt(tbObat1.getSelectedRow(), 11).toString());
                    TGigiAnomali.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 12).toString());
                    TLain.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 13).toString());
                    TextD.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 14).toString());
                    TextM.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 15).toString());
                    TextF.setText(tbObat1.getValueAt(tbObat1.getSelectedRow(), 16).toString());
                }
                break;
        }

    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "' ", TNoRM);
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", TPasien);
    }

    /**
     * @param norwt
     * @param tgl2
     */
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
        Hasil.requestFocus();
        try {
            ps = koneksi
                    .prepareStatement("select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.prioritas "
                            + "from diagnosa_pasien inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                            + "where diagnosa_pasien.no_rawat=? order by diagnosa_pasien.prioritas ");
            try {
                ps.setString(1, norwt);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("prioritas") == 1) {
                        KodeDiagnosaUtama.setText(rs.getString("kd_penyakit"));
                        DiagnosaUtama.setText(rs.getString("nm_penyakit"));
                    }

                    // if(rs.getInt("prioritas")==2){
                    // KodeDiagnosaSekunder1.setText(rs.getString("kd_penyakit"));
                    // DiagnosaSekunder1.setText(rs.getString("nm_penyakit"));
                    // }
                    //
                    // if(rs.getInt("prioritas")==3){
                    // KodeDiagnosaSekunder2.setText(rs.getString("kd_penyakit"));
                    // DiagnosaSekunder2.setText(rs.getString("nm_penyakit"));
                    // }
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
        try {
            ps = koneksi
                    .prepareStatement("select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.prioritas "
                            + "from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode "
                            + "where prosedur_pasien.no_rawat=? order by prosedur_pasien.prioritas ");
            try {
                ps.setString(1, norwt);
                rs = ps.executeQuery();
                while (rs.next()) {
                    // if(rs.getInt("prioritas")==1){
                    // KodeProsedurUtama.setText(rs.getString("kode"));
                    // ProsedurUtama.setText(rs.getString("deskripsi_panjang"));
                    // }
                    //
                    // if(rs.getInt("prioritas")==2){
                    // KodeProsedurSekunder1.setText(rs.getString("kode"));
                    // ProsedurSekunder1.setText(rs.getString("deskripsi_panjang"));
                    // }
                    //
                    // if(rs.getInt("prioritas")==3){
                    // KodeProsedurSekunder2.setText(rs.getString("kode"));
                    // ProsedurSekunder2.setText(rs.getString("deskripsi_panjang"));
                    // }
                    //
                    // if(rs.getInt("prioritas")==4){
                    // KodeProsedurSekunder3.setText(rs.getString("kode"));
                    // ProsedurSekunder3.setText(rs.getString("deskripsi_panjang"));
                    // }
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
    }

    private void isForm() {
        if (ChkInput.isSelected() == true || ChkInput1.isSelected() == true) {
            ChkInput.setVisible(false);
            // PanelInput.setPreferredSize(new Dimension(WIDTH,this.getHeight()-122));
            PanelInput.setPreferredSize(new Dimension(WIDTH, 350));
            scrollInput.setVisible(true);
            ChkInput.setVisible(true);
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 350));
            scrollInput1.setVisible(true);
            ChkInput1.setVisible(true);
        } else if (ChkInput.isSelected() == false || ChkInput1.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            scrollInput.setVisible(false);
            ChkInput.setVisible(true);
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            scrollInput1.setVisible(false);
            ChkInput1.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getdata_resume_pasien());
        BtnHapus.setEnabled(akses.getdata_resume_pasien());
        BtnEdit.setEnabled(akses.getdata_resume_pasien());
        BtnPrint.setEnabled(akses.getdata_resume_pasien());
        if (akses.getjml2() >= 1) {
            KodeDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KodeDokter.setText(akses.getkode());
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", NamaDokter, KodeDokter.getText());
            if (NamaDokter.getText().isEmpty()) {
                KodeDokter.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }
    }

    private void TampilkanData() {
        switch (TabOdontogram.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
            default:
                break;
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgOdontogram.class.getName());

}
