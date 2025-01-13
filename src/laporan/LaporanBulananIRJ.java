package laporan;

import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * @author Kanit SIRS
 */
public class LaporanBulananIRJ extends javax.swing.JDialog {

    private final sekuel Sequel = new sekuel();

    private final validasi Valid = new validasi();

    private final Connection koneksi = koneksiDB.condb();

    private PreparedStatement ps, ps2;

    private ResultSet rs, rs2;

    private StringBuilder htmlContent;

    private int tgl1 = 0, tgl2 = 0, tgl3 = 0, tgl4 = 0, tgl5 = 0, tgl6 = 0, tgl7 = 0, tgl8 = 0, tgl9 = 0, tgl10 = 0,
            tgl11 = 0, tgl12 = 0, tgl13 = 0, tgl14 = 0, tgl15 = 0, tgl16 = 0, tgl17 = 0, tgl18 = 0, tgl19 = 0,
            tgl20 = 0, tgl21 = 0, tgl22 = 0, tgl23 = 0, tgl24 = 0, tgl25 = 0, tgl26 = 0, tgl27 = 0, tgl28 = 0,
            tgl29 = 0, tgl30 = 0, tgl31 = 0, ttltgl1 = 0, ttltgl2 = 0, ttltgl3 = 0, ttltgl4 = 0, ttltgl5 = 0,
            ttltgl6 = 0, ttltgl7 = 0, ttltgl8 = 0, ttltgl9 = 0, ttltgl10 = 0, ttltgl11 = 0, ttltgl12 = 0, ttltgl13 = 0,
            ttltgl14 = 0, ttltgl15 = 0, ttltgl16 = 0, ttltgl17 = 0, ttltgl18 = 0, ttltgl19 = 0, ttltgl20 = 0,
            ttltgl21 = 0, ttltgl22 = 0, ttltgl23 = 0, ttltgl24 = 0, ttltgl25 = 0, ttltgl26 = 0, ttltgl27 = 0,
            ttltgl28 = 0, ttltgl29 = 0, ttltgl30 = 0, ttltgl31 = 0, tglbaru1 = 0, tglbaru2 = 0, tglbaru3 = 0,
            tglbaru4 = 0, tglbaru5 = 0, tglbaru6 = 0, tglbaru7 = 0, tglbaru8 = 0, tglbaru9 = 0, tglbaru10 = 0,
            tglbaru11 = 0, tglbaru12 = 0, tglbaru13 = 0, tglbaru14 = 0, tglbaru15 = 0, tglbaru16 = 0, tglbaru17 = 0,
            tglbaru18 = 0, tglbaru19 = 0, tglbaru20 = 0, tglbaru21 = 0, tglbaru22 = 0, tglbaru23 = 0, tglbaru24 = 0,
            tglbaru25 = 0, tglbaru26 = 0, tglbaru27 = 0, tglbaru28 = 0, tglbaru29 = 0, tglbaru30 = 0, tglbaru31 = 0,
            ttltglbaru1 = 0, ttltglbaru2 = 0, ttltglbaru3 = 0, ttltglbaru4 = 0, ttltglbaru5 = 0, ttltglbaru6 = 0,
            ttltglbaru7 = 0, ttltglbaru8 = 0, ttltglbaru9 = 0, ttltglbaru10 = 0, ttltglbaru11 = 0, ttltglbaru12 = 0,
            ttltglbaru13 = 0, ttltglbaru14 = 0, ttltglbaru15 = 0, ttltglbaru16 = 0, ttltglbaru17 = 0, ttltglbaru18 = 0,
            ttltglbaru19 = 0, ttltglbaru20 = 0, ttltglbaru21 = 0, ttltglbaru22 = 0, ttltglbaru23 = 0, ttltglbaru24 = 0,
            ttltglbaru25 = 0, ttltglbaru26 = 0, ttltglbaru27 = 0, ttltglbaru28 = 0, ttltglbaru29 = 0, ttltglbaru30 = 0,
            ttltglbaru31 = 0;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public LaporanBulananIRJ(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);
        LoadHTML3.setEditable(true);
        LoadHTML3.setEditorKit(kit);
        LoadHTML4.setEditable(true);
        LoadHTML4.setEditorKit(kit);
        LoadHTML5.setEditable(true);
        LoadHTML5.setEditorKit(kit);
        LoadHTML6.setEditable(true);
        LoadHTML6.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML1.setDocument(doc);
        LoadHTML2.setDocument(doc);
        LoadHTML3.setDocument(doc);
        LoadHTML4.setDocument(doc);
        LoadHTML5.setDocument(doc);
        LoadHTML6.setDocument(doc);

        Valid.LoadTahun(Tahun);
    }

    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private int i = 0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tahun = new widget.ComboBox();
        Bulan = new widget.ComboBox();
        btnCari = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        Scroll1 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        Scroll2 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        Scroll3 = new widget.ScrollPane();
        LoadHTML3 = new widget.editorpane();
        Scroll4 = new widget.ScrollPane();
        LoadHTML4 = new widget.editorpane();
        Scroll5 = new widget.ScrollPane();
        LoadHTML5 = new widget.editorpane();
        Scroll6 = new widget.ScrollPane();
        LoadHTML6 = new widget.editorpane();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Bulanan IRJ ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tahun & Pelayanan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(label11);

        Tahun.setName("Tahun"); // NOI18N
        Tahun.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi1.add(Tahun);

        Bulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        Bulan.setName("Bulan"); // NOI18N
        Bulan.setPreferredSize(new java.awt.Dimension(62, 23));
        panelisi1.add(Bulan);

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCari.setMnemonic('2');
        btnCari.setToolTipText("Alt+2");
        btnCari.setName("btnCari"); // NOI18N
        btnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        btnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariKeyPressed(evt);
            }
        });
        panelisi1.add(btnCari);

        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(100, 30));
        panelisi1.add(label9);

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

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setBorder(null);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll.setViewportView(LoadHTML);

        TabRawat.addTab("Pasien Per Poli", Scroll);

        Scroll1.setBorder(null);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll1.setViewportView(LoadHTML1);

        TabRawat.addTab("Pasien Per Dokter", Scroll1);

        Scroll2.setBorder(null);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll2.setViewportView(LoadHTML2);

        TabRawat.addTab("Pasien Lama & Baru", Scroll2);

        Scroll3.setBorder(null);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        LoadHTML3.setBorder(null);
        LoadHTML3.setName("LoadHTML3"); // NOI18N
        Scroll3.setViewportView(LoadHTML3);

        TabRawat.addTab("Pasien Laki-laki & Perempuan", Scroll3);

        Scroll4.setBorder(null);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        LoadHTML4.setBorder(null);
        LoadHTML4.setName("LoadHTML4"); // NOI18N
        Scroll4.setViewportView(LoadHTML4);

        TabRawat.addTab("Jenis Pasien", Scroll4);

        Scroll5.setBorder(null);
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML5.setBorder(null);
        LoadHTML5.setName("LoadHTML5"); // NOI18N
        Scroll5.setViewportView(LoadHTML5);

        TabRawat.addTab("Jenis Pasien Per Poli", Scroll5);

        Scroll6.setBorder(null);
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        LoadHTML6.setBorder(null);
        LoadHTML6.setName("LoadHTML6"); // NOI18N
        Scroll6.setViewportView(LoadHTML6);

        TabRawat.addTab("Status Pelayanan", Scroll6);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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
        try {
            File g = new File("file2.css");
            try (BufferedWriter bg = new BufferedWriter(new FileWriter(g))) {
                bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"
                        + ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                        + ".isi5 td{font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
            }

            File f = new File("LaporanTahunanIRJ.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            if (TabRawat.getSelectedIndex() == 0) {
                bw.write(LoadHTML.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 1) {
                bw.write(LoadHTML1.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 2) {
                bw.write(LoadHTML2.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 3) {
                bw.write(LoadHTML3.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 4) {
                bw.write(LoadHTML4.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 5) {
                bw.write(LoadHTML5.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            } else if (TabRawat.getSelectedIndex() == 6) {
                bw.write(LoadHTML6.getText().replaceAll("<head>",
                        "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi5'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + akses.getnamars() + "</font><br>"
                        + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.
                        getpropinsirs() + "<br>"
                        + akses.getkontakrs() + ", E-mail : " + akses.
                        getemailrs() + "<br><br>"
                        + "<font size='2' face='Tahoma'>LAPORAN BULANAN INSTALASI RAWAT JALAN PERIODE " + Tahun.
                                getSelectedItem() + "<br><br></font>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
                bw.close();
            }
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            //Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, Tahun);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    TabRawatMouseClicked(null);
}//GEN-LAST:event_btnCariActionPerformed

private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        btnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, Tahun, BtnPrint);
    }
}//GEN-LAST:event_btnCariKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            prosesCari();
        } else if (TabRawat.getSelectedIndex() == 1) {
            prosesCari2();
        } else if (TabRawat.getSelectedIndex() == 2) {
            prosesCari3();
        } else if (TabRawat.getSelectedIndex() == 3) {
            prosesCari4();
        } else if (TabRawat.getSelectedIndex() == 4) {
            prosesCari5();
        } else if (TabRawat.getSelectedIndex() == 5) {
            prosesCari6();
        } else if (TabRawat.getSelectedIndex() == 6) {
            prosesCari7();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-").
                    append(Bulan.getSelectedItem()).append("-").append(Tahun.
                    getSelectedItem()).append(
                            "</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");

            LoadHTML.setText(
                    "<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            LaporanBulananIRJ dialog = new LaporanBulananIRJ(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.ComboBox Bulan;
    private widget.TextBox Kd2;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private widget.editorpane LoadHTML3;
    private widget.editorpane LoadHTML4;
    private widget.editorpane LoadHTML5;
    private widget.editorpane LoadHTML6;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ComboBox Tahun;
    private widget.Button btnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label label11;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_poli,poliklinik.nm_poli from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_poli order by reg_periksa.kd_poli");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' align='left'>")
                            .append(rs.getString("nm_poli"))
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");
                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void prosesCari2() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_poli,poliklinik.nm_poli from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_poli order by reg_periksa.kd_poli");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='left'>")
                            .append(rs.getString("nm_poli"))
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");
                    ps2 = koneksi.prepareStatement(
                            "select reg_periksa.kd_dokter,dokter.nm_dokter from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                            + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.kd_poli=? group by reg_periksa.kd_dokter order by reg_periksa.kd_dokter");
                    try {
                        ps2.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                        ps2.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                        ps2.setString(3, rs.getString("kd_poli"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            tgl1 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl2 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl3 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl4 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl5 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl6 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl7 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl8 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl9 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl10 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl11 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl12 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl13 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl14 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl15 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl16 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl17 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl18 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl19 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl20 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl21 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl22 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl23 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl24 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl25 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl26 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl27 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl28 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl29 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl30 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            tgl31 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                                    + "' and reg_periksa.kd_dokter='" + rs2.getString("kd_dokter") + "'");
                            htmlContent.append(
                                    "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>")
                                    .append(rs2.getString("nm_dokter"))
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl1)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl2)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl3)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl4)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl5)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl6)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl7)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl8)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl9)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl10)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl11)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl12)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl13)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl14)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl15)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl16)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl17)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl18)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl19)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl20)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl21)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl22)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl23)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl24)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl25)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl26)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl27)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl28)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl29)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl30)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl31)
                                    .append("</td><td valign='middle' align='center'>")
                                    .append(tgl1)
                                    .append(tgl2)
                                    .append(tgl3)
                                    .append(tgl4)
                                    .append(tgl5)
                                    .append(tgl6)
                                    .append(tgl7)
                                    .append(tgl8)
                                    .append(tgl9)
                                    .append(tgl10)
                                    .append(tgl11)
                                    .append(tgl12)
                                    .append(tgl13)
                                    .append(tgl14)
                                    .append(tgl15)
                                    .append(tgl16)
                                    .append(tgl17)
                                    .append(tgl18)
                                    .append(tgl19)
                                    .append(tgl20)
                                    .append(tgl21)
                                    .append(tgl22)
                                    .append(tgl23)
                                    .append(tgl24)
                                    .append(tgl25)
                                    .append(tgl26)
                                    .append(tgl27)
                                    .append(tgl28)
                                    .append(tgl29)
                                    .append(tgl30)
                                    .append(tgl31)
                                    .append("</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif2 : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void isCek() {
        BtnPrint.setEnabled(akses.getlaporan_tahunan_irj());
    }

    private void prosesCari3() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_poli,poliklinik.nm_poli from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_poli order by reg_periksa.kd_poli");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                ttltglbaru1 = 0;
                ttltglbaru2 = 0;
                ttltglbaru3 = 0;
                ttltglbaru4 = 0;
                ttltglbaru5 = 0;
                ttltglbaru6 = 0;
                ttltglbaru7 = 0;
                ttltglbaru8 = 0;
                ttltglbaru9 = 0;
                ttltglbaru10 = 0;
                ttltglbaru11 = 0;
                ttltglbaru12 = 0;
                ttltglbaru13 = 0;
                ttltglbaru14 = 0;
                ttltglbaru15 = 0;
                ttltglbaru16 = 0;
                ttltglbaru17 = 0;
                ttltglbaru18 = 0;
                ttltglbaru19 = 0;
                ttltglbaru20 = 0;
                ttltglbaru21 = 0;
                ttltglbaru22 = 0;
                ttltglbaru23 = 0;
                ttltglbaru24 = 0;
                ttltglbaru25 = 0;
                ttltglbaru26 = 0;
                ttltglbaru27 = 0;
                ttltglbaru28 = 0;
                ttltglbaru29 = 0;
                ttltglbaru30 = 0;
                ttltglbaru31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='left'>")
                            .append(rs.getString("nm_poli"))
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");

                    tglbaru1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");
                    tglbaru31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and reg_periksa.stts_daftar='Baru'");

                    ttltglbaru1 += tglbaru1;
                    ttltglbaru2 += tglbaru2;
                    ttltglbaru3 += tglbaru3;
                    ttltglbaru4 += tglbaru4;
                    ttltglbaru5 += tglbaru5;
                    ttltglbaru6 += tglbaru6;
                    ttltglbaru7 += tglbaru7;
                    ttltglbaru8 += tglbaru8;
                    ttltglbaru9 += tglbaru9;
                    ttltglbaru10 += tglbaru10;
                    ttltglbaru11 += tglbaru11;
                    ttltglbaru12 += tglbaru12;
                    ttltglbaru13 += tglbaru13;
                    ttltglbaru14 += tglbaru14;
                    ttltglbaru15 += tglbaru15;
                    ttltglbaru16 += tglbaru16;
                    ttltglbaru17 += tglbaru17;
                    ttltglbaru18 += tglbaru18;
                    ttltglbaru19 += tglbaru19;
                    ttltglbaru20 += tglbaru20;
                    ttltglbaru21 += tglbaru21;
                    ttltglbaru22 += tglbaru22;
                    ttltglbaru23 += tglbaru23;
                    ttltglbaru24 += tglbaru24;
                    ttltglbaru25 += tglbaru25;
                    ttltglbaru26 += tglbaru26;
                    ttltglbaru27 += tglbaru27;
                    ttltglbaru28 += tglbaru28;
                    ttltglbaru29 += tglbaru29;
                    ttltglbaru30 += tglbaru30;
                    ttltglbaru31 += tglbaru31;

                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>Baru</td><td valign='middle' align='center'>")
                            .append(tglbaru1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru31)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru1)
                            .append(tglbaru2)
                            .append(tglbaru3)
                            .append(tglbaru4)
                            .append(tglbaru5)
                            .append(tglbaru6)
                            .append(tglbaru7)
                            .append(tglbaru8)
                            .append(tglbaru9)
                            .append(tglbaru10)
                            .append(tglbaru11)
                            .append(tglbaru12)
                            .append(tglbaru13)
                            .append(tglbaru14)
                            .append(tglbaru15)
                            .append(tglbaru16)
                            .append(tglbaru17)
                            .append(tglbaru18)
                            .append(tglbaru19)
                            .append(tglbaru20)
                            .append(tglbaru21)
                            .append(tglbaru22)
                            .append(tglbaru23)
                            .append(tglbaru24)
                            .append(tglbaru25)
                            .append(tglbaru26)
                            .append(tglbaru27)
                            .append(tglbaru28)
                            .append(tglbaru29)
                            .append(tglbaru30)
                            .append(tglbaru31)
                            .append("</td></tr>");

                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>Lama</td><td valign='middle' align='center'>")
                            .append(tgl1 - tglbaru1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl2 - tglbaru2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl3 - tglbaru3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl4 - tglbaru4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl5 - tglbaru5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl6 - tglbaru6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl7 - tglbaru7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl8 - tglbaru8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl9 - tglbaru9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl10 - tglbaru10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl11 - tglbaru11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl12 - tglbaru12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl13 - tglbaru13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl14 - tglbaru14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl15 - tglbaru15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl16 - tglbaru16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl17 - tglbaru17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl18 - tglbaru18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl19 - tglbaru19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl20 - tglbaru20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl21 - tglbaru21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl22 - tglbaru22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl23 - tglbaru23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl24 - tglbaru24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl25 - tglbaru25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl26 - tglbaru26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl27 - tglbaru27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl28 - tglbaru28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl29 - tglbaru29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl30 - tglbaru30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl31 - tglbaru31)
                            .append("</td><td valign='middle' align='center'>")
                            .append((tgl1 + tgl2 + tgl3 + tgl4 + tgl5 + tgl6 + tgl7 + tgl8 + tgl9 + tgl10 + tgl11 + tgl12
                                    + tgl13 + tgl14 + tgl15 + tgl16 + tgl17 + tgl18 + tgl19 + tgl20 + tgl21 + tgl22 + tgl23
                                    + tgl24 + tgl25 + tgl26 + tgl27 + tgl28 + tgl29 + tgl30 + tgl31)
                                    - (tglbaru1 + tglbaru2 + tglbaru3 + tglbaru4 + tglbaru5 + tglbaru6 + tglbaru7 + tglbaru8
                                    + tglbaru9 + tglbaru10 + tglbaru11 + tglbaru12 + tglbaru13 + tglbaru14
                                    + tglbaru15 + tglbaru16 + tglbaru17 + tglbaru18 + tglbaru19 + tglbaru20
                                    + tglbaru21 + tglbaru22 + tglbaru23 + tglbaru24 + tglbaru25 + tglbaru26
                                    + tglbaru27 + tglbaru28 + tglbaru29 + tglbaru30 + tglbaru31))
                            .append("</td></tr>");

                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH BARU : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru1)
                            .append(ttltglbaru2)
                            .append(ttltglbaru3)
                            .append(ttltglbaru4)
                            .append(ttltglbaru5)
                            .append(ttltglbaru6)
                            .append(ttltglbaru7)
                            .append(ttltglbaru8)
                            .append(ttltglbaru9)
                            .append(ttltglbaru10)
                            .append(ttltglbaru11)
                            .append(ttltglbaru12)
                            .append(ttltglbaru13)
                            .append(ttltglbaru14)
                            .append(ttltglbaru15)
                            .append(ttltglbaru16)
                            .append(ttltglbaru17)
                            .append(ttltglbaru18)
                            .append(ttltglbaru19)
                            .append(ttltglbaru20)
                            .append(ttltglbaru21)
                            .append(ttltglbaru22)
                            .append(ttltglbaru23)
                            .append(ttltglbaru24)
                            .append(ttltglbaru25)
                            .append(ttltglbaru26)
                            .append(ttltglbaru27)
                            .append(ttltglbaru28)
                            .append(ttltglbaru29)
                            .append(ttltglbaru30)
                            .append(ttltglbaru31)
                            .append("</td></tr>");
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH LAMA : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1 - ttltglbaru1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2 - ttltglbaru2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3 - ttltglbaru3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4 - ttltglbaru4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5 - ttltglbaru5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6 - ttltglbaru6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7 - ttltglbaru7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8 - ttltglbaru8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9 - ttltglbaru9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10 - ttltglbaru10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11 - ttltglbaru11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12 - ttltglbaru12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13 - ttltglbaru13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14 - ttltglbaru14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15 - ttltglbaru15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16 - ttltglbaru16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17 - ttltglbaru17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18 - ttltglbaru18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19 - ttltglbaru19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20 - ttltglbaru20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21 - ttltglbaru21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22 - ttltglbaru22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23 - ttltglbaru23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24 - ttltglbaru24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25 - ttltglbaru25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26 - ttltglbaru26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27 - ttltglbaru27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28 - ttltglbaru28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29 - ttltglbaru29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30 - ttltglbaru30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31 - ttltglbaru31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append((ttltgl1 + ttltgl2 + ttltgl3 + ttltgl4 + ttltgl5 + ttltgl6 + ttltgl7 + ttltgl8 + ttltgl9
                                    + ttltgl10 + ttltgl11 + ttltgl12 + ttltgl13 + ttltgl14 + ttltgl15 + ttltgl16 + ttltgl17
                                    + ttltgl18 + ttltgl19 + ttltgl20 + ttltgl21 + ttltgl22 + ttltgl23 + ttltgl24 + ttltgl25
                                    + ttltgl26 + ttltgl27 + ttltgl28 + ttltgl29 + ttltgl30 + ttltgl31)
                                    - (ttltglbaru1 + ttltglbaru2 + ttltglbaru3 + ttltglbaru4 + ttltglbaru5 + ttltglbaru6
                                    + ttltglbaru7 + ttltglbaru8 + ttltglbaru9 + ttltglbaru10 + ttltglbaru11
                                    + ttltglbaru12 + ttltglbaru13 + ttltglbaru14 + ttltglbaru15 + ttltglbaru16
                                    + ttltglbaru17 + ttltglbaru18 + ttltglbaru19 + ttltglbaru20 + ttltglbaru21
                                    + ttltglbaru22 + ttltglbaru23 + ttltglbaru24 + ttltglbaru25 + ttltglbaru26
                                    + ttltglbaru27 + ttltglbaru28 + ttltglbaru29 + ttltglbaru30 + ttltglbaru31))
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void prosesCari4() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_poli,poliklinik.nm_poli from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_poli order by reg_periksa.kd_poli");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                ttltglbaru1 = 0;
                ttltglbaru2 = 0;
                ttltglbaru3 = 0;
                ttltglbaru4 = 0;
                ttltglbaru5 = 0;
                ttltglbaru6 = 0;
                ttltglbaru7 = 0;
                ttltglbaru8 = 0;
                ttltglbaru9 = 0;
                ttltglbaru10 = 0;
                ttltglbaru11 = 0;
                ttltglbaru12 = 0;
                ttltglbaru13 = 0;
                ttltglbaru14 = 0;
                ttltglbaru15 = 0;
                ttltglbaru16 = 0;
                ttltglbaru17 = 0;
                ttltglbaru18 = 0;
                ttltglbaru19 = 0;
                ttltglbaru20 = 0;
                ttltglbaru21 = 0;
                ttltglbaru22 = 0;
                ttltglbaru23 = 0;
                ttltglbaru24 = 0;
                ttltglbaru25 = 0;
                ttltglbaru26 = 0;
                ttltglbaru27 = 0;
                ttltglbaru28 = 0;
                ttltglbaru29 = 0;
                ttltglbaru30 = 0;
                ttltglbaru31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='left'>")
                            .append(rs.getString("nm_poli"))
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");

                    tglbaru1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");
                    tglbaru31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_poli='" + rs.getString("kd_poli")
                            + "' and pasien.jk='L'");

                    ttltglbaru1 += tglbaru1;
                    ttltglbaru2 += tglbaru2;
                    ttltglbaru3 += tglbaru3;
                    ttltglbaru4 += tglbaru4;
                    ttltglbaru5 += tglbaru5;
                    ttltglbaru6 += tglbaru6;
                    ttltglbaru7 += tglbaru7;
                    ttltglbaru8 += tglbaru8;
                    ttltglbaru9 += tglbaru9;
                    ttltglbaru10 += tglbaru10;
                    ttltglbaru11 += tglbaru11;
                    ttltglbaru12 += tglbaru12;
                    ttltglbaru13 += tglbaru13;
                    ttltglbaru14 += tglbaru14;
                    ttltglbaru15 += tglbaru15;
                    ttltglbaru16 += tglbaru16;
                    ttltglbaru17 += tglbaru17;
                    ttltglbaru18 += tglbaru18;
                    ttltglbaru19 += tglbaru19;
                    ttltglbaru20 += tglbaru20;
                    ttltglbaru21 += tglbaru21;
                    ttltglbaru22 += tglbaru22;
                    ttltglbaru23 += tglbaru23;
                    ttltglbaru24 += tglbaru24;
                    ttltglbaru25 += tglbaru25;
                    ttltglbaru26 += tglbaru26;
                    ttltglbaru27 += tglbaru27;
                    ttltglbaru28 += tglbaru28;
                    ttltglbaru29 += tglbaru29;
                    ttltglbaru30 += tglbaru30;
                    ttltglbaru31 += tglbaru31;

                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>Laki-Laki</td><td valign='middle' align='center'>")
                            .append(tglbaru1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru31)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tglbaru1)
                            .append(tglbaru2)
                            .append(tglbaru3)
                            .append(tglbaru4)
                            .append(tglbaru5)
                            .append(tglbaru6)
                            .append(tglbaru7)
                            .append(tglbaru8)
                            .append(tglbaru9)
                            .append(tglbaru10)
                            .append(tglbaru11)
                            .append(tglbaru12)
                            .append(tglbaru13)
                            .append(tglbaru14)
                            .append(tglbaru15)
                            .append(tglbaru16)
                            .append(tglbaru17)
                            .append(tglbaru18)
                            .append(tglbaru19)
                            .append(tglbaru20)
                            .append(tglbaru21)
                            .append(tglbaru22)
                            .append(tglbaru23)
                            .append(tglbaru24)
                            .append(tglbaru25)
                            .append(tglbaru26)
                            .append(tglbaru27)
                            .append(tglbaru28)
                            .append(tglbaru29)
                            .append(tglbaru30)
                            .append(tglbaru31)
                            .append("</td></tr>");

                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>Perempuan</td><td valign='middle' align='center'>")
                            .append(tgl1 - tglbaru1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl2 - tglbaru2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl3 - tglbaru3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl4 - tglbaru4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl5 - tglbaru5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl6 - tglbaru6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl7 - tglbaru7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl8 - tglbaru8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl9 - tglbaru9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl10 - tglbaru10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl11 - tglbaru11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl12 - tglbaru12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl13 - tglbaru13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl14 - tglbaru14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl15 - tglbaru15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl16 - tglbaru16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl17 - tglbaru17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl18 - tglbaru18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl19 - tglbaru19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl20 - tglbaru20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl21 - tglbaru21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl22 - tglbaru22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl23 - tglbaru23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl24 - tglbaru24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl25 - tglbaru25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl26 - tglbaru26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl27 - tglbaru27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl28 - tglbaru28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl29 - tglbaru29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl30 - tglbaru30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl31 - tglbaru31)
                            .append("</td><td valign='middle' align='center'>")
                            .append((tgl1 + tgl2 + tgl3 + tgl4 + tgl5 + tgl6 + tgl7 + tgl8 + tgl9 + tgl10 + tgl11 + tgl12
                                    + tgl13 + tgl14 + tgl15 + tgl16 + tgl17 + tgl18 + tgl19 + tgl20 + tgl21 + tgl22 + tgl23
                                    + tgl24 + tgl25 + tgl26 + tgl27 + tgl28 + tgl29 + tgl30 + tgl31)
                                    - (tglbaru1 + tglbaru2 + tglbaru3 + tglbaru4 + tglbaru5 + tglbaru6 + tglbaru7 + tglbaru8
                                    + tglbaru9 + tglbaru10 + tglbaru11 + tglbaru12 + tglbaru13 + tglbaru14
                                    + tglbaru15 + tglbaru16 + tglbaru17 + tglbaru18 + tglbaru19 + tglbaru20
                                    + tglbaru21 + tglbaru22 + tglbaru23 + tglbaru24 + tglbaru25 + tglbaru26
                                    + tglbaru27 + tglbaru28 + tglbaru29 + tglbaru30 + tglbaru31))
                            .append("</td></tr>");

                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH LAKI-LAKI : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltglbaru1)
                            .append(ttltglbaru2)
                            .append(ttltglbaru3)
                            .append(ttltglbaru4)
                            .append(ttltglbaru5)
                            .append(ttltglbaru6)
                            .append(ttltglbaru7)
                            .append(ttltglbaru8)
                            .append(ttltglbaru9)
                            .append(ttltglbaru10)
                            .append(ttltglbaru11)
                            .append(ttltglbaru12)
                            .append(ttltglbaru13)
                            .append(ttltglbaru14)
                            .append(ttltglbaru15)
                            .append(ttltglbaru16)
                            .append(ttltglbaru17)
                            .append(ttltglbaru18)
                            .append(ttltglbaru19)
                            .append(ttltglbaru20)
                            .append(ttltglbaru21)
                            .append(ttltglbaru22)
                            .append(ttltglbaru23)
                            .append(ttltglbaru24)
                            .append(ttltglbaru25)
                            .append(ttltglbaru26)
                            .append(ttltglbaru27)
                            .append(ttltglbaru28)
                            .append(ttltglbaru29)
                            .append(ttltglbaru30)
                            .append(ttltglbaru31)
                            .append("</td></tr>");
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH PEREMPUAN : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1 - ttltglbaru1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2 - ttltglbaru2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3 - ttltglbaru3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4 - ttltglbaru4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5 - ttltglbaru5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6 - ttltglbaru6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7 - ttltglbaru7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8 - ttltglbaru8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9 - ttltglbaru9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10 - ttltglbaru10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11 - ttltglbaru11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12 - ttltglbaru12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13 - ttltglbaru13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14 - ttltglbaru14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15 - ttltglbaru15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16 - ttltglbaru16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17 - ttltglbaru17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18 - ttltglbaru18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19 - ttltglbaru19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20 - ttltglbaru20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21 - ttltglbaru21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22 - ttltglbaru22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23 - ttltglbaru23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24 - ttltglbaru24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25 - ttltglbaru25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26 - ttltglbaru26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27 - ttltglbaru27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28 - ttltglbaru28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29 - ttltglbaru29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30 - ttltglbaru30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31 - ttltglbaru31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append((ttltgl1 + ttltgl2 + ttltgl3 + ttltgl4 + ttltgl5 + ttltgl6 + ttltgl7 + ttltgl8 + ttltgl9
                                    + ttltgl10 + ttltgl11 + ttltgl12 + ttltgl13 + ttltgl14 + ttltgl15 + ttltgl16 + ttltgl17
                                    + ttltgl18 + ttltgl19 + ttltgl20 + ttltgl21 + ttltgl22 + ttltgl23 + ttltgl24 + ttltgl25
                                    + ttltgl26 + ttltgl27 + ttltgl28 + ttltgl29 + ttltgl30 + ttltgl31)
                                    - (ttltglbaru1 + ttltglbaru2 + ttltglbaru3 + ttltglbaru4 + ttltglbaru5 + ttltglbaru6
                                    + ttltglbaru7 + ttltglbaru8 + ttltglbaru9 + ttltglbaru10 + ttltglbaru11
                                    + ttltglbaru12 + ttltglbaru13 + ttltglbaru14 + ttltglbaru15 + ttltglbaru16
                                    + ttltglbaru17 + ttltglbaru18 + ttltglbaru19 + ttltglbaru20 + ttltglbaru21
                                    + ttltglbaru22 + ttltglbaru23 + ttltglbaru24 + ttltglbaru25 + ttltglbaru26
                                    + ttltglbaru27 + ttltglbaru28 + ttltglbaru29 + ttltglbaru30 + ttltglbaru31))
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void prosesCari5() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Uraian</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_pj,penjab.png_jawab from reg_periksa inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_pj order by reg_periksa.kd_pj");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' align='left'>")
                            .append(rs.getString("png_jawab"))
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");
                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void prosesCari6() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Uraian</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");
            i = 1;
            ps = koneksi.prepareStatement(
                    "select reg_periksa.kd_pj,penjab.png_jawab from reg_periksa inner join penjab on reg_periksa.kd_pj=penjab.kd_pj "
                    + "where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi between ? and ? group by reg_periksa.kd_pj order by reg_periksa.kd_pj");
            try {
                ps.setString(1, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-01");
                ps.setString(2, Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem() + "-31");
                rs = ps.executeQuery();
                ttltgl1 = 0;
                ttltgl2 = 0;
                ttltgl3 = 0;
                ttltgl4 = 0;
                ttltgl5 = 0;
                ttltgl6 = 0;
                ttltgl7 = 0;
                ttltgl8 = 0;
                ttltgl9 = 0;
                ttltgl10 = 0;
                ttltgl11 = 0;
                ttltgl12 = 0;
                ttltgl13 = 0;
                ttltgl14 = 0;
                ttltgl15 = 0;
                ttltgl16 = 0;
                ttltgl17 = 0;
                ttltgl18 = 0;
                ttltgl19 = 0;
                ttltgl20 = 0;
                ttltgl21 = 0;
                ttltgl22 = 0;
                ttltgl23 = 0;
                ttltgl24 = 0;
                ttltgl25 = 0;
                ttltgl26 = 0;
                ttltgl27 = 0;
                ttltgl28 = 0;
                ttltgl29 = 0;
                ttltgl30 = 0;
                ttltgl31 = 0;
                while (rs.next()) {
                    tgl1 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl2 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl3 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl4 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl5 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl6 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl7 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl8 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl9 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl10 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl11 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl12 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl13 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl14 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl15 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl16 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl17 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl18 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl19 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl20 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl21 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl22 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl23 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl24 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl25 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl26 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl27 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl28 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl29 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl30 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");
                    tgl31 = Sequel.cariInteger(
                            "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.kd_pj='" + rs.getString("kd_pj") + "'");

                    ttltgl1 += tgl1;
                    ttltgl2 += tgl2;
                    ttltgl3 += tgl3;
                    ttltgl4 += tgl4;
                    ttltgl5 += tgl5;
                    ttltgl6 += tgl6;
                    ttltgl7 += tgl7;
                    ttltgl8 += tgl8;
                    ttltgl9 += tgl9;
                    ttltgl10 += tgl10;
                    ttltgl11 += tgl11;
                    ttltgl12 += tgl12;
                    ttltgl13 += tgl13;
                    ttltgl14 += tgl14;
                    ttltgl15 += tgl15;
                    ttltgl16 += tgl16;
                    ttltgl17 += tgl17;
                    ttltgl18 += tgl18;
                    ttltgl19 += tgl19;
                    ttltgl20 += tgl20;
                    ttltgl21 += tgl21;
                    ttltgl22 += tgl22;
                    ttltgl23 += tgl23;
                    ttltgl24 += tgl24;
                    ttltgl25 += tgl25;
                    ttltgl26 += tgl26;
                    ttltgl27 += tgl27;
                    ttltgl28 += tgl28;
                    ttltgl29 += tgl29;
                    ttltgl30 += tgl30;
                    ttltgl31 += tgl31;

                    htmlContent.append("<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(i)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='left'>")
                            .append(rs.getString("png_jawab"))
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(tgl1)
                            .append(tgl2)
                            .append(tgl3)
                            .append(tgl4)
                            .append(tgl5)
                            .append(tgl6)
                            .append(tgl7)
                            .append(tgl8)
                            .append(tgl9)
                            .append(tgl10)
                            .append(tgl11)
                            .append(tgl12)
                            .append(tgl13)
                            .append(tgl14)
                            .append(tgl15)
                            .append(tgl16)
                            .append(tgl17)
                            .append(tgl18)
                            .append(tgl19)
                            .append(tgl20)
                            .append(tgl21)
                            .append(tgl22)
                            .append(tgl23)
                            .append(tgl24)
                            .append(tgl25)
                            .append(tgl26)
                            .append(tgl27)
                            .append(tgl28)
                            .append(tgl29)
                            .append(tgl30)
                            .append(tgl31)
                            .append("</td></tr>");

                    ps2 = koneksi.prepareStatement(
                            "select poliklinik.kd_poli,poliklinik.nm_poli from poliklinik where poliklinik.kd_poli<>'IGDK' order by poliklinik.kd_poli");
                    try {
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            tgl1 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-01' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl2 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-02' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl3 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-03' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl4 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-04' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl5 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-05' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl6 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-06' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl7 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-07' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl8 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-08' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl9 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-09' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl10 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-10' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl11 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-11' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl12 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-12' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl13 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-13' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl14 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-14' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl15 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-15' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl16 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-16' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl17 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-17' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl18 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-18' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl19 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-19' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl20 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-20' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl21 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-21' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl22 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-22' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl23 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-23' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl24 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-24' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl25 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-25' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl26 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-26' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl27 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-27' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl28 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-28' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl29 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-29' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl30 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-30' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            tgl31 = Sequel.cariInteger(
                                    "select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.kd_poli<>'IGDK' and reg_periksa.tgl_registrasi='"
                                    + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                                    + "-31' and reg_periksa.kd_pj='" + rs.getString("kd_pj")
                                    + "' and reg_periksa.kd_poli='" + rs2.getString("kd_poli") + "'");
                            if ((tgl1 + tgl2 + tgl3 + tgl4 + tgl5 + tgl6 + tgl7 + tgl8 + tgl9 + tgl10 + tgl11 + tgl12
                                    + tgl13 + tgl14 + tgl15 + tgl16 + tgl17 + tgl18 + tgl19 + tgl20 + tgl21 + tgl22
                                    + tgl23 + tgl24 + tgl25 + tgl26 + tgl27 + tgl28 + tgl29 + tgl30 + tgl31) > 0) {
                                htmlContent.append(
                                        "<tr class='isi'><td valign='middle' align='center'></td><td valign='middle' align='left'>")
                                        .append(rs2.getString("nm_poli"))
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl1)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl2)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl3)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl4)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl5)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl6)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl7)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl8)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl9)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl10)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl11)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl12)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl13)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl14)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl15)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl16)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl17)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl18)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl19)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl20)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl21)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl22)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl23)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl24)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl25)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl26)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl27)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl28)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl29)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl30)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl31)
                                        .append("</td><td valign='middle' align='center'>")
                                        .append(tgl1)
                                        .append(tgl2)
                                        .append(tgl3)
                                        .append(tgl4)
                                        .append(tgl5)
                                        .append(tgl6)
                                        .append(tgl7)
                                        .append(tgl8)
                                        .append(tgl9)
                                        .append(tgl10)
                                        .append(tgl11)
                                        .append(tgl12)
                                        .append(tgl13)
                                        .append(tgl14)
                                        .append(tgl15)
                                        .append(tgl16)
                                        .append(tgl17)
                                        .append(tgl18)
                                        .append(tgl19)
                                        .append(tgl20)
                                        .append(tgl21)
                                        .append(tgl22)
                                        .append(tgl23)
                                        .append(tgl24)
                                        .append(tgl25)
                                        .append(tgl26)
                                        .append(tgl27)
                                        .append(tgl28)
                                        .append(tgl29)
                                        .append(tgl30)
                                        .append(tgl31)
                                        .append("</td></tr>");
                            }
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
                    i++;
                }

                if (i > 1) {
                    htmlContent.append(
                            "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl2)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl3)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl4)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl5)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl6)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl7)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl8)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl9)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl10)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl11)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl12)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl13)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl14)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl15)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl16)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl17)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl18)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl19)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl20)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl21)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl22)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl23)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl24)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl25)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl26)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl27)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl28)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl29)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl30)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl31)
                            .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                            .append(ttltgl1)
                            .append(ttltgl2)
                            .append(ttltgl3)
                            .append(ttltgl4)
                            .append(ttltgl5)
                            .append(ttltgl6)
                            .append(ttltgl7)
                            .append(ttltgl8)
                            .append(ttltgl9)
                            .append(ttltgl10)
                            .append(ttltgl11)
                            .append(ttltgl12)
                            .append(ttltgl13)
                            .append(ttltgl14)
                            .append(ttltgl15)
                            .append(ttltgl16)
                            .append(ttltgl17)
                            .append(ttltgl18)
                            .append(ttltgl19)
                            .append(ttltgl20)
                            .append(ttltgl21)
                            .append(ttltgl22)
                            .append(ttltgl23)
                            .append(ttltgl24)
                            .append(ttltgl25)
                            .append(ttltgl26)
                            .append(ttltgl27)
                            .append(ttltgl28)
                            .append(ttltgl29)
                            .append(ttltgl30)
                            .append(ttltgl31)
                            .append("</td></tr>");
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
            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void prosesCari7() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='2%' rowspan='2'>No</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='13%' rowspan='2'>Jenis Cakupan Pelayanan</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='85%' colspan='32'>Jumlah Pasien</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>01-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>02-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>03-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>04-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>05-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>06-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>07-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>08-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>09-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>10-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>11-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>12-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>13-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>14-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>15-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>16-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>17-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>18-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>19-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>20-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>21-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>22-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>23-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>24-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>25-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>26-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>27-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>28-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>29-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>30-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>31-")
                    .append(Bulan.getSelectedItem())
                    .append("-")
                    .append(Tahun.getSelectedItem())
                    .append("</td><td valign='middle' bgcolor='#FFFAFA' align='center'>Total</td></tr>");

            ttltgl1 = 0;
            ttltgl2 = 0;
            ttltgl3 = 0;
            ttltgl4 = 0;
            ttltgl5 = 0;
            ttltgl6 = 0;
            ttltgl7 = 0;
            ttltgl8 = 0;
            ttltgl9 = 0;
            ttltgl10 = 0;
            ttltgl11 = 0;
            ttltgl12 = 0;
            ttltgl13 = 0;
            ttltgl14 = 0;
            ttltgl15 = 0;
            ttltgl16 = 0;
            ttltgl17 = 0;
            ttltgl18 = 0;
            ttltgl19 = 0;
            ttltgl20 = 0;
            ttltgl21 = 0;
            ttltgl22 = 0;
            ttltgl23 = 0;
            ttltgl24 = 0;
            ttltgl25 = 0;
            ttltgl26 = 0;
            ttltgl27 = 0;
            ttltgl28 = 0;
            ttltgl29 = 0;
            ttltgl30 = 0;
            ttltgl31 = 0;

            tgl1 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl2 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl3 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl4 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl5 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl6 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl7 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl8 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl9 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl10 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl11 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl12 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl13 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl14 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl15 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl16 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl17 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl18 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl19 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl20 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl21 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl22 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl23 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl24 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl25 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl26 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl27 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl28 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl29 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl30 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");
            tgl31 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.stts='Batal' and reg_periksa.kd_poli<>'IGDK'");

            ttltgl1 += tgl1;
            ttltgl2 += tgl2;
            ttltgl3 += tgl3;
            ttltgl4 += tgl4;
            ttltgl5 += tgl5;
            ttltgl6 += tgl6;
            ttltgl7 += tgl7;
            ttltgl8 += tgl8;
            ttltgl9 += tgl9;
            ttltgl10 += tgl10;
            ttltgl11 += tgl11;
            ttltgl12 += tgl12;
            ttltgl13 += tgl13;
            ttltgl14 += tgl14;
            ttltgl15 += tgl15;
            ttltgl16 += tgl16;
            ttltgl17 += tgl17;
            ttltgl18 += tgl18;
            ttltgl19 += tgl19;
            ttltgl20 += tgl20;
            ttltgl21 += tgl21;
            ttltgl22 += tgl22;
            ttltgl23 += tgl23;
            ttltgl24 += tgl24;
            ttltgl25 += tgl25;
            ttltgl26 += tgl26;
            ttltgl27 += tgl27;
            ttltgl28 += tgl28;
            ttltgl29 += tgl29;
            ttltgl30 += tgl30;
            ttltgl31 += tgl31;

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' align='center'>1</td><td valign='middle' align='left'>Batal</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl2)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl3)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl4)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl5)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl6)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl7)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl8)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl9)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl10)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl11)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl12)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl13)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl14)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl15)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl16)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl17)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl18)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl19)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl20)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl21)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl22)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl23)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl24)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl25)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl26)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl27)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl28)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl29)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl30)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl31)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append(tgl2)
                    .append(tgl3)
                    .append(tgl4)
                    .append(tgl5)
                    .append(tgl6)
                    .append(tgl7)
                    .append(tgl8)
                    .append(tgl9)
                    .append(tgl10)
                    .append(tgl11)
                    .append(tgl12)
                    .append(tgl13)
                    .append(tgl14)
                    .append(tgl15)
                    .append(tgl16)
                    .append(tgl17)
                    .append(tgl18)
                    .append(tgl19)
                    .append(tgl20)
                    .append(tgl21)
                    .append(tgl22)
                    .append(tgl23)
                    .append(tgl24)
                    .append(tgl25)
                    .append(tgl26)
                    .append(tgl27)
                    .append(tgl28)
                    .append(tgl29)
                    .append(tgl30)
                    .append(tgl31)
                    .append("</td></tr>");

            tgl1 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl2 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl3 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl4 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl5 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl6 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl7 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl8 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl9 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl10 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl11 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl12 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl13 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl14 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl15 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl16 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl17 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl18 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl19 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl20 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl21 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl22 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl23 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl24 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl25 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl26 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl27 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl28 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl29 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl30 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");
            tgl31 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.stts='Dirujuk' and reg_periksa.kd_poli<>'IGDK'");

            ttltgl1 += tgl1;
            ttltgl2 += tgl2;
            ttltgl3 += tgl3;
            ttltgl4 += tgl4;
            ttltgl5 += tgl5;
            ttltgl6 += tgl6;
            ttltgl7 += tgl7;
            ttltgl8 += tgl8;
            ttltgl9 += tgl9;
            ttltgl10 += tgl10;
            ttltgl11 += tgl11;
            ttltgl12 += tgl12;
            ttltgl13 += tgl13;
            ttltgl14 += tgl14;
            ttltgl15 += tgl15;
            ttltgl16 += tgl16;
            ttltgl17 += tgl17;
            ttltgl18 += tgl18;
            ttltgl19 += tgl19;
            ttltgl20 += tgl20;
            ttltgl21 += tgl21;
            ttltgl22 += tgl22;
            ttltgl23 += tgl23;
            ttltgl24 += tgl24;
            ttltgl25 += tgl25;
            ttltgl26 += tgl26;
            ttltgl27 += tgl27;
            ttltgl28 += tgl28;
            ttltgl29 += tgl29;
            ttltgl30 += tgl30;
            ttltgl31 += tgl31;

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' align='center'>2</td><td valign='middle' align='left'>Dirujuk</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl2)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl3)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl4)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl5)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl6)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl7)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl8)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl9)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl10)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl11)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl12)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl13)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl14)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl15)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl16)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl17)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl18)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl19)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl20)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl21)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl22)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl23)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl24)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl25)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl26)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl27)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl28)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl29)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl30)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl31)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append(tgl2)
                    .append(tgl3)
                    .append(tgl4)
                    .append(tgl5)
                    .append(tgl6)
                    .append(tgl7)
                    .append(tgl8)
                    .append(tgl9)
                    .append(tgl10)
                    .append(tgl11)
                    .append(tgl12)
                    .append(tgl13)
                    .append(tgl14)
                    .append(tgl15)
                    .append(tgl16)
                    .append(tgl17)
                    .append(tgl18)
                    .append(tgl19)
                    .append(tgl20)
                    .append(tgl21)
                    .append(tgl22)
                    .append(tgl23)
                    .append(tgl24)
                    .append(tgl25)
                    .append(tgl26)
                    .append(tgl27)
                    .append(tgl28)
                    .append(tgl29)
                    .append(tgl30)
                    .append(tgl31)
                    .append("</td></tr>");

            tgl1 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl2 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl3 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl4 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl5 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl6 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl7 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl8 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl9 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl10 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl11 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl12 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl13 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl14 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl15 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl16 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl17 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl18 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl19 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl20 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl21 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl22 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl23 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl24 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl25 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl26 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl27 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl28 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl29 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl30 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");
            tgl31 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.stts='Meninggal' and reg_periksa.kd_poli<>'IGDK'");

            ttltgl1 += tgl1;
            ttltgl2 += tgl2;
            ttltgl3 += tgl3;
            ttltgl4 += tgl4;
            ttltgl5 += tgl5;
            ttltgl6 += tgl6;
            ttltgl7 += tgl7;
            ttltgl8 += tgl8;
            ttltgl9 += tgl9;
            ttltgl10 += tgl10;
            ttltgl11 += tgl11;
            ttltgl12 += tgl12;
            ttltgl13 += tgl13;
            ttltgl14 += tgl14;
            ttltgl15 += tgl15;
            ttltgl16 += tgl16;
            ttltgl17 += tgl17;
            ttltgl18 += tgl18;
            ttltgl19 += tgl19;
            ttltgl20 += tgl20;
            ttltgl21 += tgl21;
            ttltgl22 += tgl22;
            ttltgl23 += tgl23;
            ttltgl24 += tgl24;
            ttltgl25 += tgl25;
            ttltgl26 += tgl26;
            ttltgl27 += tgl27;
            ttltgl28 += tgl28;
            ttltgl29 += tgl29;
            ttltgl30 += tgl30;
            ttltgl31 += tgl31;

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' align='center'>3</td><td valign='middle' align='left'>Meninggal</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl2)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl3)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl4)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl5)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl6)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl7)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl8)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl9)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl10)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl11)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl12)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl13)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl14)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl15)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl16)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl17)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl18)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl19)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl20)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl21)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl22)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl23)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl24)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl25)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl26)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl27)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl28)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl29)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl30)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl31)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append(tgl2)
                    .append(tgl3)
                    .append(tgl4)
                    .append(tgl5)
                    .append(tgl6)
                    .append(tgl7)
                    .append(tgl8)
                    .append(tgl9)
                    .append(tgl10)
                    .append(tgl11)
                    .append(tgl12)
                    .append(tgl13)
                    .append(tgl14)
                    .append(tgl15)
                    .append(tgl16)
                    .append(tgl17)
                    .append(tgl18)
                    .append(tgl19)
                    .append(tgl20)
                    .append(tgl21)
                    .append(tgl22)
                    .append(tgl23)
                    .append(tgl24)
                    .append(tgl25)
                    .append(tgl26)
                    .append(tgl27)
                    .append(tgl28)
                    .append(tgl29)
                    .append(tgl30)
                    .append(tgl31)
                    .append("</td></tr>");

            tgl1 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl2 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl3 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl4 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl5 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl6 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl7 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl8 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl9 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl10 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl11 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl12 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl13 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl14 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl15 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl16 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl17 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl18 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl19 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl20 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl21 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl22 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl23 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl24 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl25 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl26 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl27 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl28 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl29 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl30 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");
            tgl31 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.stts='Pulang Paksa' and reg_periksa.kd_poli<>'IGDK'");

            ttltgl1 += tgl1;
            ttltgl2 += tgl2;
            ttltgl3 += tgl3;
            ttltgl4 += tgl4;
            ttltgl5 += tgl5;
            ttltgl6 += tgl6;
            ttltgl7 += tgl7;
            ttltgl8 += tgl8;
            ttltgl9 += tgl9;
            ttltgl10 += tgl10;
            ttltgl11 += tgl11;
            ttltgl12 += tgl12;
            ttltgl13 += tgl13;
            ttltgl14 += tgl14;
            ttltgl15 += tgl15;
            ttltgl16 += tgl16;
            ttltgl17 += tgl17;
            ttltgl18 += tgl18;
            ttltgl19 += tgl19;
            ttltgl20 += tgl20;
            ttltgl21 += tgl21;
            ttltgl22 += tgl22;
            ttltgl23 += tgl23;
            ttltgl24 += tgl24;
            ttltgl25 += tgl25;
            ttltgl26 += tgl26;
            ttltgl27 += tgl27;
            ttltgl28 += tgl28;
            ttltgl29 += tgl29;
            ttltgl30 += tgl30;
            ttltgl31 += tgl31;

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' align='center'>4</td><td valign='middle' align='left'>Pulang Paksa</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl2)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl3)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl4)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl5)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl6)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl7)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl8)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl9)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl10)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl11)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl12)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl13)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl14)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl15)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl16)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl17)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl18)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl19)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl20)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl21)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl22)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl23)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl24)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl25)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl26)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl27)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl28)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl29)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl30)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl31)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append(tgl2)
                    .append(tgl3)
                    .append(tgl4)
                    .append(tgl5)
                    .append(tgl6)
                    .append(tgl7)
                    .append(tgl8)
                    .append(tgl9)
                    .append(tgl10)
                    .append(tgl11)
                    .append(tgl12)
                    .append(tgl13)
                    .append(tgl14)
                    .append(tgl15)
                    .append(tgl16)
                    .append(tgl17)
                    .append(tgl18)
                    .append(tgl19)
                    .append(tgl20)
                    .append(tgl21)
                    .append(tgl22)
                    .append(tgl23)
                    .append(tgl24)
                    .append(tgl25)
                    .append(tgl26)
                    .append(tgl27)
                    .append(tgl28)
                    .append(tgl29)
                    .append(tgl30)
                    .append(tgl31)
                    .append("</td></tr>");

            tgl1 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-01' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl2 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-02' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl3 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-03' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl4 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-04' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl5 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-05' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl6 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-06' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl7 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-07' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl8 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-08' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl9 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-09' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl10 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-10' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl11 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-11' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl12 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-12' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl13 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-13' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl14 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-14' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl15 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-15' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl16 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-16' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl17 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-17' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl18 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-18' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl19 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-19' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl20 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-20' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl21 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-21' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl22 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-22' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl23 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-23' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl24 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-24' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl25 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-25' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl26 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-26' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl27 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-27' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl28 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-28' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl29 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-29' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl30 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-30' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");
            tgl31 = Sequel
                    .cariInteger("select count(reg_periksa.no_rawat) from reg_periksa where reg_periksa.tgl_registrasi='"
                            + Tahun.getSelectedItem() + "-" + Bulan.getSelectedItem()
                            + "-31' and reg_periksa.stts in ('Belum','Sudah','Berkas Diterima','Dirawat') and reg_periksa.kd_poli<>'IGDK'");

            ttltgl1 += tgl1;
            ttltgl2 += tgl2;
            ttltgl3 += tgl3;
            ttltgl4 += tgl4;
            ttltgl5 += tgl5;
            ttltgl6 += tgl6;
            ttltgl7 += tgl7;
            ttltgl8 += tgl8;
            ttltgl9 += tgl9;
            ttltgl10 += tgl10;
            ttltgl11 += tgl11;
            ttltgl12 += tgl12;
            ttltgl13 += tgl13;
            ttltgl14 += tgl14;
            ttltgl15 += tgl15;
            ttltgl16 += tgl16;
            ttltgl17 += tgl17;
            ttltgl18 += tgl18;
            ttltgl19 += tgl19;
            ttltgl20 += tgl20;
            ttltgl21 += tgl21;
            ttltgl22 += tgl22;
            ttltgl23 += tgl23;
            ttltgl24 += tgl24;
            ttltgl25 += tgl25;
            ttltgl26 += tgl26;
            ttltgl27 += tgl27;
            ttltgl28 += tgl28;
            ttltgl29 += tgl29;
            ttltgl30 += tgl30;
            ttltgl31 += tgl31;

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' align='center'>5</td><td valign='middle' align='left'>Terlayani</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl2)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl3)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl4)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl5)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl6)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl7)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl8)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl9)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl10)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl11)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl12)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl13)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl14)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl15)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl16)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl17)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl18)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl19)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl20)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl21)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl22)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl23)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl24)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl25)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl26)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl27)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl28)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl29)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl30)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl31)
                    .append("</td><td valign='middle' align='center'>")
                    .append(tgl1)
                    .append(tgl2)
                    .append(tgl3)
                    .append(tgl4)
                    .append(tgl5)
                    .append(tgl6)
                    .append(tgl7)
                    .append(tgl8)
                    .append(tgl9)
                    .append(tgl10)
                    .append(tgl11)
                    .append(tgl12)
                    .append(tgl13)
                    .append(tgl14)
                    .append(tgl15)
                    .append(tgl16)
                    .append(tgl17)
                    .append(tgl18)
                    .append(tgl19)
                    .append(tgl20)
                    .append(tgl21)
                    .append(tgl22)
                    .append(tgl23)
                    .append(tgl24)
                    .append(tgl25)
                    .append(tgl26)
                    .append(tgl27)
                    .append(tgl28)
                    .append(tgl29)
                    .append(tgl30)
                    .append(tgl31)
                    .append("</td></tr>");

            htmlContent.append(
                    "<tr class='isi'><td valign='middle' bgcolor='#FFFFF8' align='right' colspan='2'>JUMLAH : </td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl1)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl2)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl3)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl4)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl5)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl6)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl7)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl8)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl9)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl10)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl11)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl12)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl13)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl14)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl15)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl16)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl17)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl18)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl19)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl20)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl21)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl22)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl23)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl24)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl25)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl26)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl27)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl28)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl29)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl30)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl31)
                    .append("</td><td valign='middle' bgcolor='#FFFFF8' align='center'>")
                    .append(ttltgl1)
                    .append(ttltgl2)
                    .append(ttltgl3)
                    .append(ttltgl4)
                    .append(ttltgl5)
                    .append(ttltgl6)
                    .append(ttltgl7)
                    .append(ttltgl8)
                    .append(ttltgl9)
                    .append(ttltgl10)
                    .append(ttltgl11)
                    .append(ttltgl12)
                    .append(ttltgl13)
                    .append(ttltgl14)
                    .append(ttltgl15)
                    .append(ttltgl16)
                    .append(ttltgl17)
                    .append(ttltgl18)
                    .append(ttltgl19)
                    .append(ttltgl20)
                    .append(ttltgl21)
                    .append(ttltgl22)
                    .append(ttltgl23)
                    .append(ttltgl24)
                    .append(ttltgl25)
                    .append(ttltgl26)
                    .append(ttltgl27)
                    .append(ttltgl28)
                    .append(ttltgl29)
                    .append(ttltgl30)
                    .append(ttltgl31)
                    .append("</td></tr>");

            LoadHTML.setText("<html>"
                    + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString() + "</table>" + "</html>");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private static final Logger LOG = Logger.getLogger(LaporanBulananIRJ.class.getName());

}
