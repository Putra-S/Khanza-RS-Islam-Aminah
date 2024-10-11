package inventory;

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgKehadiran2;
import simrskhanza.DlgCariBangsal;

/**
 *
 * @author Kanit SIRS
 */
public class InventoryStokAkhirFarmasiPerTanggal extends javax.swing.JDialog {

    private DefaultTableModel tabMode;
    private final sekuel Sequel = new sekuel();
    private final validasi Valid = new validasi();
    private final Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private String pilihan = "";
    private String dateString, dayOfWeek, hari, h1 = "", h2 = "", h3 = "", h4 = "", h5 = "", h6 = "", h7 = "", h8 = "", h9 = "", h10 = "", h11 = "", h12 = "", h13 = "",
            h14 = "", h15 = "", h16 = "", h17 = "", h18 = "", h19 = "", h20 = "", h21 = "", h22 = "", h23 = "", h24 = "", h25 = "", h26 = "", h27 = "", h28 = "", h29 = "", h30 = "", h31 = "";
    private String stokawal = "", s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "", s7 = "", s8 = "", s9 = "", s10 = "", s11 = "", s12 = "", s13 = "", s14 = "", s15 = "", s16 = "", s17 = "", s18 = "", s19 = "", s20 = "",
            s21 = "", s22 = "", s23 = "", s24 = "", s25 = "", s26 = "", s27 = "", s28 = "", s29 = "", s30 = "", s31 = "";
    private Date date = null;
    private StringBuilder htmlContent;
    private int i = 0;

    /**
     * @param parent
     * @param modal
     */
    public InventoryStokAkhirFarmasiPerTanggal(java.awt.Frame parent,
            boolean modal) {
        super(parent, modal);
        initComponents();

        tbDokter.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(
                    new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        prosesCari();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        prosesCari();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        prosesCari();
                    }
                }

            });
        }

        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().
                        equals("InventoryStokAkhirFarmasiPerTanggal")) {
                    if (bangsal.getTable().getSelectedRow() != -1) {
                        KdGudang.setText(bangsal.getTable().getValueAt(bangsal.
                                getTable().getSelectedRow(), 0).toString());
                        NmGudang.setText(bangsal.getTable().getValueAt(bangsal.
                                getTable().getSelectedRow(), 1).toString());
                    }
                    KdGudang.requestFocus();
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

        Valid.LoadTahun(ThnCari);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        ThnCari = new widget.ComboBox();
        BlnCari = new widget.ComboBox();
        label19 = new widget.Label();
        KdGudang = new widget.TextBox();
        NmGudang = new widget.TextBox();
        btnBarang1 = new widget.Button();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        Kd2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Stok Akhir Farmasi Per Tanggal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tahun & Bulan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(85, 23));
        panelisi4.add(label11);

        ThnCari.setName("ThnCari"); // NOI18N
        ThnCari.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(ThnCari);

        BlnCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        BlnCari.setName("BlnCari"); // NOI18N
        BlnCari.setPreferredSize(new java.awt.Dimension(62, 23));
        panelisi4.add(BlnCari);

        label19.setText("Lokasi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(label19);

        KdGudang.setName("KdGudang"); // NOI18N
        KdGudang.setPreferredSize(new java.awt.Dimension(80, 23));
        KdGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdGudangKeyPressed(evt);
            }
        });
        panelisi4.add(KdGudang);

        NmGudang.setEditable(false);
        NmGudang.setName("NmGudang"); // NOI18N
        NmGudang.setPreferredSize(new java.awt.Dimension(215, 23));
        panelisi4.add(NmGudang);

        btnBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBarang1.setMnemonic('1');
        btnBarang1.setToolTipText("Alt+1");
        btnBarang1.setName("btnBarang1"); // NOI18N
        btnBarang1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarang1ActionPerformed(evt);
            }
        });
        panelisi4.add(btnBarang1);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(62, 23));
        panelisi1.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('A');
        BtnAll.setToolTipText("Alt+A");
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

        label9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(49, 30));
        panelisi1.add(label9);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+P");
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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnPrint.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                File g = new File("file2.css");
                try (BufferedWriter bg = new BufferedWriter(new FileWriter(g))) {
                    bg.write(
                            ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"
                            + ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    );
                }

                File f;
                BufferedWriter bw;

                pilihan = (String) JOptionPane.showInputDialog(null,
                        "Silahkan pilih laporan..!", "Pilihan Cetak",
                        JOptionPane.QUESTION_MESSAGE, null, new Object[]{
                            "Laporan 1 (HTML)", "Laporan 2 (WPS)",
                            "Laporan 3 (CSV)"}, "Laporan 1 (HTML)");
                switch (pilihan) {
                    case "Laporan 1 (HTML)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Kode Barang</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='190px'>Nama Barang</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>1(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 1)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>2(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 2)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>3(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 3)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>4(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 4)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>5(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 5)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>6(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 6)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>7(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 7)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>8(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 8)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>9(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 9)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>10(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 10)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>11(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 11)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>12(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 12)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>13(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 13)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>14(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 14)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>15(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 15)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>16(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 16)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>17(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 17)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>18(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 18)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>19(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 19)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>20(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 20)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>21(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 21)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>22(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 22)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>23(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 23)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>24(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 24)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>25(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 25)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>26(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 26)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>27(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 27)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>28(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 28)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>29(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 29)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>30(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 30)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>31(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 31)).
                                append(")</td></tr>");
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append(
                                    "<tr class='isi'><td valign='top'>").append(
                                            tabMode.getValueAt(i, 0)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 1)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 2)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 3)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 4)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 5)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 6)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 7)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 8)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 9)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 10)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 11)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 12)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 13)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 14)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 15)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 16)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 17)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 18)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 19)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 20)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 21)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 22)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 23)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 24)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 25)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 26)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 27)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 28)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 29)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 30)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 31)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 32)).append(
                                    "</td></tr>");
                        }

                        f = new File("StokAkhirFarmasi.html");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"
                                + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                + "<body>"
                                + "<table width='1900px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + "<tr class='isi2'>"
                                + "<td valign='top' align='center'>"
                                + "<font size='4' face='Tahoma'>" + akses.
                                        getnamars() + "</font><br>"
                                + akses.getalamatrs() + ", " + akses.
                                getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                                + akses.getkontakrs() + ", E-mail : " + akses.
                                getemailrs() + "<br><br>"
                                + "<font size='2' face='Tahoma'>STOK AKHIR FARMASI TAHUN " + ThnCari.
                                        getSelectedItem() + " BULAN " + BlnCari.
                                        getSelectedItem() + "<br><br></font>"
                                + "</td>"
                                + "</tr>"
                                + "</table>"
                                + "<table width='1900px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + htmlContent.toString()
                                + "</table>"
                                + "</body>"
                                + "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 2 (WPS)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Kode Barang</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='190px'>Nama Barang</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>1(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 1)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>2(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 2)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>3(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 3)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>4(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 4)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>5(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 5)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>6(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 6)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>7(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 7)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>8(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 8)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>9(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 9)).append(
                                        ")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>10(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 10)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>11(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 11)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>12(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 12)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>13(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 13)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>14(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 14)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>15(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 15)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>16(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 16)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>17(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 17)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>18(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 18)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>19(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 19)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>20(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 20)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>21(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 21)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>22(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 22)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>23(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 23)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>24(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 24)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>25(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 25)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>26(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 26)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>27(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 27)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>28(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 28)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>29(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 29)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>30(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 30)).
                                append(")</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>31(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 31)).
                                append(")</td></tr>");
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append(
                                    "<tr class='isi'><td valign='top'>").append(
                                            tabMode.getValueAt(i, 0)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 1)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 2)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 3)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 4)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 5)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 6)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 7)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 8)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 9)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 10)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 11)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 12)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 13)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 14)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 15)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 16)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 17)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 18)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 19)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 20)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 21)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 22)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 23)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 24)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 25)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 26)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 27)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 28)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 29)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 30)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 31)).append(
                                    "</td><td valign='top'>").append(tabMode.
                                            getValueAt(i, 32)).append(
                                    "</td></tr>");
                        }

                        f = new File("StokAkhirFarmasi.wps");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"
                                + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                + "<body>"
                                + "<table width='1900px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + "<tr class='isi2'>"
                                + "<td valign='top' align='center'>"
                                + "<font size='4' face='Tahoma'>" + akses.
                                        getnamars() + "</font><br>"
                                + akses.getalamatrs() + ", " + akses.
                                getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                                + akses.getkontakrs() + ", E-mail : " + akses.
                                getemailrs() + "<br><br>"
                                + "<font size='2' face='Tahoma'>STOK AKHIR FARMASI TAHUN " + ThnCari.
                                        getSelectedItem() + " BULAN " + BlnCari.
                                        getSelectedItem() + "<br><br></font>"
                                + "</td>"
                                + "</tr>"
                                + "</table>"
                                + "<table width='1900px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + htmlContent.toString()
                                + "</table>"
                                + "</body>"
                                + "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                    case "Laporan 3 (CSV)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                                "\"Kode Barang\";\"Nama Barang\";\"1(").append(
                                        konversi(Integer.parseInt(ThnCari.
                                                getSelectedItem().toString()),
                                                Integer.parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 1)).append(
                                        ")\";\"2(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 2)).append(
                                        ")\";\"3(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 3)).append(
                                        ")\";\"4(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 4)).append(
                                        ")\";\"5(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 5)).append(
                                        ")\";\"6(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 6)).append(
                                        ")\";\"7(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 7)).append(
                                        ")\";\"8(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 8)).append(
                                        ")\";\"9(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 9)).append(
                                        ")\";\"10(").append(konversi(Integer.
                                        parseInt(ThnCari.getSelectedItem().
                                                toString()), Integer.parseInt(
                                                BlnCari.getSelectedItem().
                                                        toString()), 10)).
                                append(")\";\"11(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 11)).append(")\";\"12(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 12)).
                                append(")\";\"13(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 13)).append(")\";\"14(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 14)).
                                append(")\";\"15(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 15)).append(")\";\"16(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 16)).
                                append(")\";\"17(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 17)).append(")\";\"18(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 18)).
                                append(")\";\"19(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 19)).append(")\";\"20(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 20)).
                                append(")\";\"21(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 21)).append(")\";\"22(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 22)).
                                append(")\";\"23(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 23)).append(")\";\"24(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 24)).
                                append(")\";\"25(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 25)).append(")\";\"26(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 26)).
                                append(")\";\"27(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 27)).append(")\";\"28(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 28)).
                                append(")\";\"29(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 29)).append(")\";\"30(").
                                append(konversi(Integer.parseInt(ThnCari.
                                        getSelectedItem().toString()), Integer.
                                                parseInt(BlnCari.
                                                        getSelectedItem().
                                                        toString()), 30)).
                                append(")\";\"31(").append(konversi(Integer.
                                parseInt(ThnCari.getSelectedItem().toString()),
                                Integer.parseInt(BlnCari.getSelectedItem().
                                        toString()), 31)).append(")\"\n");
                        for (i = 0; i < tabMode.getRowCount(); i++) {
                            htmlContent.append("\"").append(tabMode.
                                    getValueAt(i, 0)).append("\";\"").append(
                                    tabMode.getValueAt(i, 1)).append("\";\"").
                                    append(tabMode.getValueAt(i, 2)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 3)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    4)).append("\";\"").append(tabMode.
                                    getValueAt(i, 5)).append("\";\"").append(
                                    tabMode.getValueAt(i, 6)).append("\";\"").
                                    append(tabMode.getValueAt(i, 7)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 8)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    9)).append("\";\"").append(tabMode.
                                    getValueAt(i, 10)).append("\";\"").append(
                                    tabMode.getValueAt(i, 11)).append("\";\"").
                                    append(tabMode.getValueAt(i, 12)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 13)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    14)).append("\";\"").append(tabMode.
                                    getValueAt(i, 15)).append("\";\"").append(
                                    tabMode.getValueAt(i, 16)).append("\";\"").
                                    append(tabMode.getValueAt(i, 17)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 18)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    19)).append("\";\"").append(tabMode.
                                    getValueAt(i, 20)).append("\";\"").append(
                                    tabMode.getValueAt(i, 21)).append("\";\"").
                                    append(tabMode.getValueAt(i, 22)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 23)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    24)).append("\";\"").append(tabMode.
                                    getValueAt(i, 25)).append("\";\"").append(
                                    tabMode.getValueAt(i, 26)).append("\";\"").
                                    append(tabMode.getValueAt(i, 27)).append(
                                    "\";\"").append(tabMode.getValueAt(i, 28)).
                                    append("\";\"").append(tabMode.getValueAt(i,
                                    29)).append("\";\"").append(tabMode.
                                    getValueAt(i, 30)).append("\";\"").append(
                                    tabMode.getValueAt(i, 31)).append("\";\"").
                                    append(tabMode.getValueAt(i, 32)).append(
                                    "\"\n");
                        }

                        f = new File("StokAkhirFarmasi.csv");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write(htmlContent.toString());

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                }
            } catch (Exception e) {
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BlnCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, ThnCari);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

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
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari();
        this.setCursor(Cursor.getDefaultCursor());
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
        KdGudang.setText("");
        NmGudang.setText("");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void btnBarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarang1ActionPerformed
        akses.setform("InventoryStokAkhirFarmasiPerTanggal");
        bangsal.emptTeks();
        bangsal.isCek();
        bangsal.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setAlwaysOnTop(false);
        bangsal.setVisible(true);
    }//GEN-LAST:event_btnBarang1ActionPerformed

    private void KdGudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdGudangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdGudangKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            InventoryStokAkhirFarmasiPerTanggal dialog = new InventoryStokAkhirFarmasiPerTanggal(
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
    private widget.ComboBox BlnCari;
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.TextBox Kd2;
    private widget.TextBox KdGudang;
    private widget.TextBox NmGudang;
    private widget.TextBox TCari;
    private widget.ComboBox ThnCari;
    private widget.Button btnBarang1;
    private widget.InternalFrame internalFrame1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label19;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        if (KdGudang.getText().isEmpty() || NmGudang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Silahkan pilih lokasi stok...!!");
        } else {
            Object[] row = {"Kode Barang", "Nama Barang",
                "1(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 1) + ")",
                "2(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 2) + ")",
                "3(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 3) + ")",
                "4(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 4) + ")",
                "5(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 5) + ")",
                "6(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 6) + ")",
                "7(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 7) + ")",
                "8(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 8) + ")",
                "9(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 9) + ")",
                "10(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 10) + ")",
                "11(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 11) + ")",
                "12(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 12) + ")",
                "13(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 13) + ")",
                "14(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 14) + ")",
                "15(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 15) + ")",
                "16(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 16) + ")",
                "17(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 17) + ")",
                "18(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 18) + ")",
                "19(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 19) + ")",
                "20(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 20) + ")",
                "21(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 21) + ")",
                "22(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 22) + ")",
                "23(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 23) + ")",
                "24(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 24) + ")",
                "25(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 25) + ")",
                "26(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 26) + ")",
                "27(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 27) + ")",
                "28(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 28) + ")",
                "29(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 29) + ")",
                "30(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 30) + ")",
                "31(" + konversi(Integer.parseInt(ThnCari.getSelectedItem().
                toString()), Integer.parseInt(BlnCari.getSelectedItem().
                toString()), 31) + ")"
            };
            tabMode = new DefaultTableModel(null, row) {
                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class
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
            tbDokter.setModel(tabMode);

            for (i = 0; i < 33; i++) {
                TableColumn column = tbDokter.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setPreferredWidth(85);
                } else if (i == 1) {
                    column.setPreferredWidth(190);
                } else {
                    column.setPreferredWidth(63);
                }
            }
            tbDokter.setDefaultRenderer(Object.class, new WarnaTable());

            Valid.tabelKosong(tabMode);
            try {
                ps = koneksi.prepareStatement(
                        "select databarang.kode_brng,databarang.nama_brng from databarang where databarang.status='1' " + (TCari.
                                getText().trim().isEmpty() ? ""
                                : "and (databarang.kode_brng like ? or databarang.nama_brng like ?) ") + "order by databarang.nama_brng");
                try {
                    if (!TCari.getText().trim().isEmpty()) {
                        ps.setString(1, "%" + TCari.getText().trim() + "%");
                        ps.setString(2, "%" + TCari.getText().trim() + "%");
                    }
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        stokawal = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal < '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-01' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        s1 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-01' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s1)) {
                            s1 = stokawal;
                        }
                        s2 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-02' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s2)) {
                            s2 = s1;
                        }
                        s3 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-03' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s3)) {
                            s3 = s2;
                        }
                        s4 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-04' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s4)) {
                            s4 = s3;
                        }
                        s5 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-05' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s5)) {
                            s5 = s4;
                        }
                        s6 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-06' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s6)) {
                            s6 = s5;
                        }
                        s7 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-07' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s7)) {
                            s7 = s6;
                        }
                        s8 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-08' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s8)) {
                            s8 = s7;
                        }
                        s9 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-09' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s9)) {
                            s9 = s8;
                        }
                        s10 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-10' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s10)) {
                            s10 = s9;
                        }
                        s11 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-11' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s11)) {
                            s11 = s10;
                        }
                        s12 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-12' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s12)) {
                            s12 = s11;
                        }
                        s13 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-13' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s13)) {
                            s13 = s12;
                        }
                        s14 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-14' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s14)) {
                            s14 = s13;
                        }
                        s15 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-15' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s15)) {
                            s15 = s14;
                        }
                        s16 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-16' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s16)) {
                            s16 = s15;
                        }
                        s17 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-17' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s17)) {
                            s17 = s16;
                        }
                        s18 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-18' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s18)) {
                            s18 = s17;
                        }
                        s19 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-19' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s19)) {
                            s19 = s18;
                        }
                        s20 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-20' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s20)) {
                            s20 = s19;
                        }
                        s21 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-21' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s21)) {
                            s21 = s20;
                        }
                        s22 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-22' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s22)) {
                            s22 = s21;
                        }
                        s23 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-23' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s23)) {
                            s23 = s22;
                        }
                        s24 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-24' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s24)) {
                            s24 = s23;
                        }
                        s25 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-25' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s25)) {
                            s25 = s24;
                        }
                        s26 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-26' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s26)) {
                            s26 = s25;
                        }
                        s27 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-27' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s27)) {
                            s27 = s26;
                        }
                        s28 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-28' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s28)) {
                            s28 = s27;
                        }
                        s29 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-29' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s29)) {
                            s29 = s28;
                        }
                        s30 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-30' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s30)) {
                            s30 = s29;
                        }
                        s31 = Sequel.cariIsi(
                                "select riwayat_barang_medis.stok_akhir from riwayat_barang_medis where riwayat_barang_medis.tanggal = '" + ThnCari.
                                        getSelectedItem().toString() + "-" + BlnCari.
                                        getSelectedItem().toString() + "-31' and riwayat_barang_medis.kode_brng='" + rs.
                                        getString("kode_brng") + "' and riwayat_barang_medis.kd_bangsal='" + KdGudang.
                                getText() + "' order by concat(riwayat_barang_medis.tanggal,' ',riwayat_barang_medis.jam) desc limit 1");
                        if ("".equals(s31)) {
                            s31 = s30;
                        }
                        if ("".equals(stokawal)) {
                            stokawal = "0";
                        }
                        if ("".equals(s1)) {
                            s1 = "0";
                        }
                        if ("".equals(s2)) {
                            s2 = "0";
                        }
                        if ("".equals(s3)) {
                            s3 = "0";
                        }
                        if ("".equals(s4)) {
                            s4 = "0";
                        }
                        if ("".equals(s5)) {
                            s5 = "0";
                        }
                        if ("".equals(s6)) {
                            s6 = "0";
                        }
                        if ("".equals(s7)) {
                            s7 = "0";
                        }
                        if ("".equals(s8)) {
                            s8 = "0";
                        }
                        if ("".equals(s9)) {
                            s9 = "0";
                        }
                        if ("".equals(s10)) {
                            s10 = "0";
                        }
                        if ("".equals(s11)) {
                            s11 = "0";
                        }
                        if ("".equals(s12)) {
                            s12 = "0";
                        }
                        if ("".equals(s13)) {
                            s13 = "0";
                        }
                        if ("".equals(s14)) {
                            s14 = "0";
                        }
                        if ("".equals(s15)) {
                            s15 = "0";
                        }
                        if ("".equals(s16)) {
                            s16 = "0";
                        }
                        if ("".equals(s17)) {
                            s17 = "0";
                        }
                        if ("".equals(s18)) {
                            s18 = "0";
                        }
                        if ("".equals(s19)) {
                            s19 = "0";
                        }
                        if ("".equals(s20)) {
                            s20 = "0";
                        }
                        if ("".equals(s21)) {
                            s21 = "0";
                        }
                        if ("".equals(s22)) {
                            s22 = "0";
                        }
                        if ("".equals(s23)) {
                            s23 = "0";
                        }
                        if ("".equals(s24)) {
                            s24 = "0";
                        }
                        if ("".equals(s25)) {
                            s25 = "0";
                        }
                        if ("".equals(s26)) {
                            s26 = "0";
                        }
                        if ("".equals(s27)) {
                            s27 = "0";
                        }
                        if ("".equals(s28)) {
                            s28 = "0";
                        }
                        if ("".equals(s29)) {
                            s29 = "0";
                        }
                        if ("".equals(s30)) {
                            s30 = "0";
                        }
                        if ("".equals(s31)) {
                            s31 = "0";
                        }
                        tabMode.addRow(new String[]{
                            rs.getString("kode_brng"), rs.getString("nama_brng"),
                            s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12,
                            s13, s14, s15, s16, s17, s18, s19, s20, s21, s22,
                            s23, s24, s25, s26, s27, s28, s29, s30, s31
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
        }

    }

    /**
     *
     */
    public void isCek() {
        BtnPrint.setEnabled(akses.getstok_akhir_farmasi_pertanggal());
    }

    String konversi(int year, int month, int day) {
        dateString = String.format("%d-%d-%d", year, month, day);
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException ex) {
            Logger.getLogger(DlgKehadiran2.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        // Then get the day of week from the Date based on specific locale.
        dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);

        switch (dayOfWeek) {
            case "Monday":
                hari = "Senin";
                break;
            case "Tuesday":
                hari = "Selasa";
                break;
            case "Wednesday":
                hari = "Rabu";
                break;
            case "Thursday":
                hari = "Kamis";
                break;
            case "Friday":
                hari = "Jumat";
                break;
            case "Saturday":
                hari = "Sabtu";
                break;
            case "Sunday":
                hari = "Minggu";
                break;
        }
        return hari;
    }

    private static final Logger LOG = Logger.getLogger(
            InventoryStokAkhirFarmasiPerTanggal.class.getName());
}
