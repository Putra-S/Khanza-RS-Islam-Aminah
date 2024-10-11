package permintaan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public class DlgPengingatVaksin extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps3;
    private ResultSet rs;
    private int i = 0;
    private DlgPasien pasien = new DlgPasien(null, false);
    private String status = "", nohp = "", requestJson, URL = "";
    private StringBuilder htmlContent;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private SSLContext sslContext;
    private SSLSocketFactory sslFactory;
    private SecretKeySpec secretKey;
    private Scheme scheme;
    private HttpComponentsClientHttpRequestFactory factory;

    /**
     *
     * @return @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public RestTemplate getRest() throws NoSuchAlgorithmException,
            KeyManagementException {
        sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustManagers = {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0,
                        String arg1) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0,
                        String arg1) throws CertificateException {
                }

            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        sslFactory = new SSLSocketFactory(sslContext,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        scheme = new Scheme("https", 443, sslFactory);
        factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().
                register(scheme);
        return new RestTemplate(factory);
    }

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgPengingatVaksin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Input", "Jam Input", "No.RM", "Nama Pasien", "Tgl.Vaksin",
            "Nama PJ", "Vaksin", "Catatan", "No.Telp/HP", "Status Kirim WA"
        }) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,
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
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(170);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(170);
            } else if (i == 8) {
                column.setPreferredWidth(170);
            } else if (i == 9) {
                column.setPreferredWidth(160);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRM.setDocument(new batasInput((byte) 17).getKata(TNoRM));
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

        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable().getValueAt(
                            pasien.getTable().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.
                            getTable().getSelectedRow(), 2).toString());
                }
                if (pasien.getTable2().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable2().getValueAt(pasien.
                            getTable2().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable2().getValueAt(pasien.
                            getTable2().getSelectedRow(), 2).toString());
                }
                if (pasien.getTable3().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable3().getValueAt(pasien.
                            getTable3().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable3().getValueAt(pasien.
                            getTable3().getSelectedRow(), 2).toString());
                }
                TNoRM.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgBooking")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        ppCSVWARocket = new javax.swing.JMenuItem();
        ppCetakBuktiBooking = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnKirimWA = new widget.Button();
        BtnKirimWA1 = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        TanggalBooking = new widget.Tanggal();
        jLabel10 = new widget.Label();
        jLabel14 = new widget.Label();
        TanggalPeriksa = new widget.Tanggal();
        JnsVaksin = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel20 = new widget.Label();
        Catatan = new widget.TextBox();

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(170, 25));
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
        ppSemua.setPreferredSize(new java.awt.Dimension(170, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        ppCSVWARocket.setBackground(new java.awt.Color(255, 255, 254));
        ppCSVWARocket.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCSVWARocket.setForeground(new java.awt.Color(50, 50, 50));
        ppCSVWARocket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCSVWARocket.setText("CSV WA Rocket");
        ppCSVWARocket.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCSVWARocket.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCSVWARocket.setName("ppCSVWARocket"); // NOI18N
        ppCSVWARocket.setPreferredSize(new java.awt.Dimension(170, 25));
        ppCSVWARocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCSVWARocketActionPerformed(evt);
            }
        });
        Popup.add(ppCSVWARocket);

        ppCetakBuktiBooking.setBackground(new java.awt.Color(255, 255, 254));
        ppCetakBuktiBooking.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakBuktiBooking.setForeground(new java.awt.Color(50, 50, 50));
        ppCetakBuktiBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCetakBuktiBooking.setText("Cetak Bukti Booking");
        ppCetakBuktiBooking.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetakBuktiBooking.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetakBuktiBooking.setName("ppCetakBuktiBooking"); // NOI18N
        ppCetakBuktiBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakBuktiBookingActionPerformed(evt);
            }
        });
        Popup.add(ppCetakBuktiBooking);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pengingat Vaksin ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setComponentPopupMenu(Popup);
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
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

        BtnKirimWA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        BtnKirimWA.setMnemonic('M');
        BtnKirimWA.setText("Kirim WA");
        BtnKirimWA.setToolTipText("Alt+M");
        BtnKirimWA.setEnabled(false);
        BtnKirimWA.setName("BtnKirimWA"); // NOI18N
        BtnKirimWA.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirimWA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimWAActionPerformed(evt);
            }
        });
        BtnKirimWA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKirimWAKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKirimWA);

        BtnKirimWA1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        BtnKirimWA1.setMnemonic('M');
        BtnKirimWA1.setText("Kirim WA Langsung");
        BtnKirimWA1.setToolTipText("Alt+M");
        BtnKirimWA1.setEnabled(false);
        BtnKirimWA1.setName("BtnKirimWA1"); // NOI18N
        BtnKirimWA1.setPreferredSize(new java.awt.Dimension(170, 30));
        BtnKirimWA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimWA1ActionPerformed(evt);
            }
        });
        BtnKirimWA1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKirimWA1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKirimWA1);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setSelected(true);
        R2.setText("Tanggal Input :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setOpaque(true);
        R2.setPreferredSize(new java.awt.Dimension(125, 23));
        panelCari.add(R2);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Tanggal Vaksin :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setOpaque(true);
        R3.setPreferredSize(new java.awt.Dimension(135, 23));
        panelCari.add(R3);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari3ItemStateChanged(evt);
            }
        });
        DTPCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari3KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari3);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari4ItemStateChanged(evt);
            }
        });
        DTPCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari4KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari4);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 70, 23);

        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(80, 10, 90, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(170, 10, 290, 23);

        TanggalBooking.setForeground(new java.awt.Color(50, 70, 50));
        TanggalBooking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023 13:27:55" }));
        TanggalBooking.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalBooking.setName("TanggalBooking"); // NOI18N
        TanggalBooking.setOpaque(false);
        TanggalBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalBookingKeyPressed(evt);
            }
        });
        FormInput.add(TanggalBooking);
        TanggalBooking.setBounds(580, 10, 140, 23);

        jLabel10.setText("Tgl.Input :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(506, 10, 70, 23);

        jLabel14.setText("Tgl.Vaksin :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(506, 40, 70, 23);

        TanggalPeriksa.setForeground(new java.awt.Color(50, 70, 50));
        TanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-07-2023 13:27:55" }));
        TanggalPeriksa.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalPeriksa.setName("TanggalPeriksa"); // NOI18N
        TanggalPeriksa.setOpaque(false);
        TanggalPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalPeriksaItemStateChanged(evt);
            }
        });
        TanggalPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalPeriksaKeyPressed(evt);
            }
        });
        FormInput.add(TanggalPeriksa);
        TanggalPeriksa.setBounds(580, 40, 140, 23);

        JnsVaksin.setHighlighter(null);
        JnsVaksin.setName("JnsVaksin"); // NOI18N
        JnsVaksin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JnsVaksinKeyPressed(evt);
            }
        });
        FormInput.add(JnsVaksin);
        JnsVaksin.setBounds(80, 40, 300, 23);

        jLabel18.setText("Jns. Vaksin :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 40, 70, 23);

        jLabel20.setText("Catatan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 70, 70, 23);

        Catatan.setHighlighter(null);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        FormInput.add(Catatan);
        Catatan.setBounds(80, 70, 300, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isCekPasien();
        }
}//GEN-LAST:event_TNoRMKeyPressed

    private void TanggalBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalBookingKeyPressed

}//GEN-LAST:event_TanggalBookingKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
            if (R2.isSelected() == true) {
                status = " penjadwalan_vaksin.tanggal_booking between '" + Valid.
                        SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.
                        SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            } else if (R3.isSelected() == true) {
                status = " penjadwalan_vaksin.tanggal_periksa between '" + Valid.
                        SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.
                        SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
            }
            Valid.MyReportqry("rptBookingRegistrasi.jasper", "report",
                    "::[ Laporan Daftar Booking Registrasi ]::",
                    "select penjadwalan_vaksin.tanggal_booking,penjadwalan_vaksin.jam_booking,penjadwalan_vaksin.no_rkm_medis, "
                    + "pasien.nm_pasien,penjadwalan_vaksin.tanggal_periksa,penjadwalan_vaksin.kd_dokter,"
                    + "dokter.nm_dokter,penjadwalan_vaksin.kd_poli,poliklinik.nm_poli,penjadwalan_vaksin.no_reg, "
                    + "pasien.namakeluarga,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab)as alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.no_tlp,"
                    + "pasien.kabupatenpj,pasien.propinsipj,pasien.keluarga,TIMESTAMPDIFF(YEAR, pasien.tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, pasien.tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, pasien.tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(pasien.tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, pasien.tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, pasien.tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, pasien.tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                    + "penjadwalan_vaksin.limit_reg,penjadwalan_vaksin.status,penjadwalan_vaksin.kd_pj,penjab.png_jawab "
                    + "from penjadwalan_vaksin inner join pasien on penjadwalan_vaksin.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join dokter on penjadwalan_vaksin.kd_dokter=dokter.kd_dokter "
                    + "inner join poliklinik on penjadwalan_vaksin.kd_poli=poliklinik.kd_poli "
                    + "inner join penjab on penjadwalan_vaksin.kd_pj=penjab.kd_pj "
                    + "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "
                    + "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "
                    + "where " + status + " and penjadwalan_vaksin.no_rkm_medis like '%" + TCari.
                            getText().trim() + "%' or "
                    + status + " and pasien.nm_pasien like '%" + TCari.getText().
                            trim() + "%' or "
                    + status + " and poliklinik.nm_poli like '%" + TCari.
                            getText().trim() + "%' or "
                    + status + " and dokter.nm_dokter like '%" + TCari.getText().
                            trim() + "%' order by penjadwalan_vaksin.tanggal_booking,dokter.nm_dokter",
                    param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed

    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari3KeyPressed

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari3ItemStateChanged

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void TanggalPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalPeriksaKeyPressed
        Valid.pindah(evt, TanggalBooking, JnsVaksin);
    }//GEN-LAST:event_TanggalPeriksaKeyPressed

    private void TanggalPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalPeriksaItemStateChanged

    }//GEN-LAST:event_TanggalPeriksaItemStateChanged

    private void JnsVaksinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JnsVaksinKeyPressed
        Valid.pindah(evt, TanggalPeriksa, BtnSimpan);
    }//GEN-LAST:event_JnsVaksinKeyPressed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            tbObat.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            tbObat.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CatatanKeyPressed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                Sequel.queryu2(
                        "delete from penjadwalan_vaksin where no_rkm_medis=? and tanggal_periksa=?",
                        2, new String[]{
                            tbObat.getValueAt(i, 3).toString(), tbObat.
                            getValueAt(i, 5).toString()
                        });
            }
        }
        tampil();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, JnsVaksin, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().isEmpty() || TPasien.getText().trim().
                isEmpty()) {
            Valid.textKosong(TNoRM, "pasien");
        } else if (JnsVaksin.getText().trim().isEmpty()) {
            Valid.textKosong(JnsVaksin, "Jenis Vaksin");
        } else {
            isBooking();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void ppCSVWARocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCSVWARocketActionPerformed
        try {
            File f;
            BufferedWriter bw;
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "\"No.HP\";\"Isi Pesan\""
            );

            for (i = 0; i < tabMode.getRowCount(); i++) {
                try {
                    nohp = "";
                    if (tabMode.getValueAt(i, 26).toString().substring(0, 1).
                            equals("0")) {
                        nohp = "62" + tabMode.getValueAt(i, 26).toString().
                                substring(1, tabMode.getValueAt(i, 26).
                                        toString().length());
                    } else {
                        nohp = tabMode.getValueAt(i, 26).toString();
                    }
                    htmlContent.append("\" ").append(nohp).append(
                            "\";\"Mengingatkan kembali kepada saudara ").append(
                                    tabMode.getValueAt(i, 4)).append(
                            " dengan No.RM ").append(tabMode.getValueAt(i, 3)).
                            append(", berdasarkan booking pada tanggal ").
                            append(tabMode.getValueAt(i, 1)).append(" ").append(
                            tabMode.getValueAt(i, 2)).append(
                            " dengan tujuan pemeriksaan di ").append(tabMode.
                                    getValueAt(i, 9)).append(" pada tanggal ").
                            append(tabMode.getValueAt(i, 5)).append(
                            " agar bisa datang dengan nomor antrian ").append(
                                    tabMode.getValueAt(i, 10)).append(
                            ". Customer Service ").append(akses.getnamars()).
                            append("\"");
                } catch (Exception e) {
                }
            }

            f = new File("WARocket.csv");
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(htmlContent.toString());
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ppCSVWARocketActionPerformed

    private void ppCetakBuktiBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakBuktiBookingActionPerformed
        if (TPasien.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            Valid.MyReportqry("rptBuktiBookingRegister.jasper", "report",
                    "::[ Bukti Booking Register ]::",
                    "select pasien.nm_pasien,DATE_FORMAT(pasien.tgl_lahir, '%d-%m-%Y') as tgl_lahir,penjadwalan_vaksin.no_rkm_medis,pasien.umur,pasien.jk,pasien.no_tlp,"
                    + "dokter.nm_dokter,poliklinik.nm_poli,penjadwalan_vaksin.tanggal_booking,penjadwalan_vaksin.jam_booking,"
                    + "penjadwalan_vaksin.tanggal_periksa,penjadwalan_vaksin.no_reg,penjab.png_jawab,pasien.alamat "
                    + "from dokter inner join pasien inner join poliklinik inner join penjab inner join penjadwalan_vaksin "
                    + "on dokter.kd_dokter=penjadwalan_vaksin.kd_dokter and penjab.kd_pj=penjadwalan_vaksin.kd_pj and poliklinik.kd_poli=penjadwalan_vaksin.kd_poli "
                    + "and pasien.no_rkm_medis=penjadwalan_vaksin.no_rkm_medis where penjadwalan_vaksin.no_rkm_medis='" + TNoRM.
                            getText() + "' and penjadwalan_vaksin.tanggal_periksa='" + Valid.
                            SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'",
                    param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCetakBuktiBookingActionPerformed

    private void BtnKirimWAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimWAActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (tbObat.getValueAt(i, 0).toString().equals("true") && !tbObat.
                    getValueAt(i, 10).toString().equals("Terkirim")) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "#RpNCYp0ALM@9uuN!G#F");
                    URL = "https://api.fonnte.com/send";
                    String kirim = Sequel.cariIsi("select date_sub('" + tbObat.
                            getValueAt(i, 5).toString() + "', interval 1 day)");
                    requestJson = "{\"target\":\"" + tbObat.getValueAt(i, 9).
                            toString() + "\","
                            + "\"schedule\":" + Sequel.cariIsi(
                                    "select unix_timestamp('" + kirim + " 08:00:00')") + ","
                            + "\"message\":\"Assalamu'alaikum Warahmatullahi Wabarakatuh, \\r\\n\\r\\nHallo bunda mengingatkan kembali kepada anak " + tbObat.
                                    getValueAt(i, 4).toString() + " \\r\\nDengan No.Rekam Medis " + tbObat.
                            getValueAt(i, 3).toString() + " \\r\\nUntuk dilakukan vaksinasi " + tbObat.
                            getValueAt(i, 7).toString() + " \\r\\nPada tanggal " + tbObat.
                            getValueAt(i, 5).toString() + " \\r\\nAyo sukseskan program vaksinasi pada anak. \\r\\n\\r\\nCustomer Service " + akses.
                            getnamars() + "\\r\\n#AYO_KE_RSI\""
                            + "}";
                    System.out.println("JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    requestJson = getRest().exchange(URL, HttpMethod.POST,
                            requestEntity, String.class).getBody();
                    System.out.println("JSON : " + requestJson);
                    root = mapper.readTree(requestJson);
                    if (root.path("detail").asText().equals(
                            "success! message will be sent on scheduled time")) {
                        Sequel.queryu2(
                                "update penjadwalan_vaksin set status_kirim_wa='Terkirim' where no_rkm_medis=? and tanggal_periksa=?",
                                2, new String[]{
                                    tbObat.getValueAt(i, 3).toString(), tbObat.
                                    getValueAt(i, 5).toString()
                                });
                    }
                } catch (IOException | KeyManagementException | NoSuchAlgorithmException | RestClientException ez) {
                    System.out.println("Notif : " + ez);
                }
            }
        }
        tampil();
    }//GEN-LAST:event_BtnKirimWAActionPerformed

    private void BtnKirimWAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKirimWAKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKirimWAKeyPressed

    private void BtnKirimWA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimWA1ActionPerformed
        for (i = 0; i < tbObat.getRowCount(); i++) {
            if (tbObat.getValueAt(i, 0).toString().equals("true") && !tbObat.
                    getValueAt(i, 10).toString().equals("Terkirim")) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Authorization", "#RpNCYp0ALM@9uuN!G#F");
                    URL = "https://api.fonnte.com/send";
//                    String kirim = Sequel.cariIsi("select date_sub('"+tbObat.getValueAt(i, 5).toString()+"', interval 1 day)");
                    requestJson = "{\"target\":\"" + tbObat.getValueAt(i, 9).
                            toString() + "\","
                            + //                                "\"schedule\":"+Sequel.cariIsi("select unix_timestamp('"+kirim+" 08:00:00')")+","+
                            "\"message\":\"Assalamu'alaikum Warahmatullahi Wabarakatuh, \\r\\n\\r\\nHallo bunda mengingatkan kembali kepada anak " + tbObat.
                                    getValueAt(i, 4).toString() + " \\r\\nDengan No.Rekam Medis " + tbObat.
                            getValueAt(i, 3).toString() + " \\r\\nUntuk dilakukan vaksinasi " + tbObat.
                            getValueAt(i, 7).toString() + " \\r\\nPada tanggal " + tbObat.
                            getValueAt(i, 5).toString() + " \\r\\nAyo sukseskan program vaksinasi pada anak. \\r\\n\\r\\nCustomer Service " + akses.
                            getnamars() + "\\r\\n#AYO_KE_RSI\""
                            + "}";
                    System.out.println("JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    requestJson = getRest().exchange(URL, HttpMethod.POST,
                            requestEntity, String.class).getBody();
                    System.out.println("JSON : " + requestJson);
                    root = mapper.readTree(requestJson);
                    if (root.path("detail").asText().equals(
                            "success! message in queue")) {
                        Sequel.queryu2(
                                "update penjadwalan_vaksin set status_kirim_wa='Terkirim' where no_rkm_medis=? and tanggal_periksa=?",
                                2, new String[]{
                                    tbObat.getValueAt(i, 3).toString(), tbObat.
                                    getValueAt(i, 5).toString()
                                });
                    }
                } catch (IOException | KeyManagementException | NoSuchAlgorithmException | RestClientException ez) {
                    System.out.println("Notif : " + ez);
                }
            }
        }
        tampil();
    }//GEN-LAST:event_BtnKirimWA1ActionPerformed

    private void BtnKirimWA1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKirimWA1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKirimWA1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPengingatVaksin dialog = new DlgPengingatVaksin(
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirimWA;
    private widget.Button BtnKirimWA1;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox Catatan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JnsVaksin;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalBooking;
    private widget.Tanggal TanggalPeriksa;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel14;
    private widget.Label jLabel18;
    private widget.Label jLabel20;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppCSVWARocket;
    private javax.swing.JMenuItem ppCetakBuktiBooking;
    private javax.swing.JMenuItem ppSemua;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        if (R2.isSelected() == true) {
            status = " penjadwalan_vaksin.tanggal_booking between '" + Valid.
                    SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.
                    SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        } else if (R3.isSelected() == true) {
            status = " penjadwalan_vaksin.tanggal_periksa between '" + Valid.
                    SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.
                    SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
        }
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select penjadwalan_vaksin.tanggal_booking,penjadwalan_vaksin.jam_booking,penjadwalan_vaksin.no_rkm_medis, "
                    + "pasien.nm_pasien,penjadwalan_vaksin.tanggal_periksa,"
                    + "penjadwalan_vaksin.jns_vaksin, "
                    + "pasien.namakeluarga,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamatpj,pasien.kelurahanpj,pasien.kecamatanpj,pasien.no_tlp,"
                    + "pasien.kabupatenpj,pasien.propinsipj,pasien.keluarga, "
                    + "penjadwalan_vaksin.catatan,penjadwalan_vaksin.status_kirim_wa "
                    + "from penjadwalan_vaksin inner join pasien on penjadwalan_vaksin.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "
                    + "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "
                    + "where " + status + " and penjadwalan_vaksin.no_rkm_medis like ? or "
                    + status + " and pasien.nm_pasien like ? order by penjadwalan_vaksin.tanggal_booking");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("tanggal_booking"), rs.getString(
                        "jam_booking"), rs.getString("no_rkm_medis"), rs.
                        getString("nm_pasien"),
                        rs.getString("tanggal_periksa"), rs.getString(
                        "namakeluarga"), rs.getString("jns_vaksin"), rs.
                        getString("catatan"),
                        rs.getString("no_tlp"), rs.getString("status_kirim_wa")
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
            System.out.println("Notif : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoRM.setText("");
        TPasien.setText("");
        TanggalBooking.setDate(new Date());
        JnsVaksin.setText("");
        Catatan.setText("");
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            Valid.SetTgl(TanggalBooking, tbObat.getValueAt(tbObat.
                    getSelectedRow(), 1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).
                    toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).
                    toString());
            Valid.SetTgl(TanggalPeriksa, tbObat.getValueAt(tbObat.
                    getSelectedRow(), 5).toString());
            JnsVaksin.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).
                    toString());
            Catatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).
                    toString());
        }
    }

    /**
     *
     * @param norm
     * @param nama
     */
    public void setNoRm(String norm, String nama) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    /**
     *
     * @param norm
     * @param nama
     * @param kodepoli
     * @param namapoli
     * @param kodedokter
     * @param namadokter
     */
    public void setNoRm(String norm, String nama, String kodepoli,
            String namapoli, String kodedokter, String namadokter) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
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

    private String jam() {
        int nilai_jam;
        int nilai_menit;
        int nilai_detik;
        String nol_jam = "";
        String nol_menit = "";
        String nol_detik = "";
        Date now = Calendar.getInstance().getTime();
        nilai_jam = now.getHours();
        nilai_menit = now.getMinutes();
        nilai_detik = now.getSeconds();

        // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
        if (nilai_jam <= 9) {
            // Tambahkan "0" didepannya
            nol_jam = "0";
        }
        // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
        if (nilai_menit <= 9) {
            // Tambahkan "0" didepannya
            nol_menit = "0";
        }
        // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
        if (nilai_detik <= 9) {
            // Tambahkan "0" didepannya
            nol_detik = "0";
        }
        String jam = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String detik = nol_detik + Integer.toString(nilai_detik);
        return jam + ":" + menit + ":" + detik;
    }

    private void isBooking() {
        if (Sequel.menyimpantf("penjadwalan_vaksin", "?,?,?,?,?,?,?,?",
                "Pasien dan Tanggal", 8, new String[]{
                    Valid.SetTgl(TanggalBooking.getSelectedItem() + ""),
                    TanggalBooking.getSelectedItem().toString().
                            substring(11, 19), TNoRM.getText(),
                    Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""),
                    Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + " " + TanggalBooking.
                    getSelectedItem().toString().substring(11, 19),
                    JnsVaksin.getText(), Catatan.getText(), "Belum"
                }) == true) {
            emptTeks();
            tampil();
        }
    }

    private void isCekPasien() {
        try {
            ps3 = koneksi.prepareStatement(
                    "select pasien.nm_pasien from pasien where pasien.no_rkm_medis=?");
            try {
                ps3.setString(1, TNoRM.getText());
                rs = ps3.executeQuery();
                while (rs.next()) {
                    TPasien.setText(rs.getString("nm_pasien"));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static final Logger LOG = Logger.getLogger(DlgPengingatVaksin.class.
            getName());
}
