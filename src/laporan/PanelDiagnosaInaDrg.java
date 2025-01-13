/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laporan;

import bridging.ApiINACBG;
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
import java.awt.event.KeyEvent;
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

/**
 * @author khanzamedia
 */
public class PanelDiagnosaInaDrg extends widget.panelisi {

    private final DefaultTableModel TabModeDiagnosaPasien, tabModeDiagnosa, tabModeProsedur, TabModeTindakanPasien,
            TabModeDiagnosaPasienICD,TabModeTindakanPasienICD;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement pspenyakit, psdiagnosapasien, psprosedur, pstindakanpasien, psnomor, psupdatenomor, ps,
            ps2;

    private ResultSet rs, rsnomor, rs2;

    private int jml = 0, i = 0, index = 0;

    private String[] kode, nama, ciripny, keterangan, kategori, cirium, kode2, panjang, pendek, namaindonesia;

    private boolean[] pilih;

    public String norawat = "",status = "",norm = "", tanggal1 = "",tanggal2 = "", keyword = "",requestJson = "";
    private ApiINACBG inacbg = new ApiINACBG();
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root,metadata,response;

    /**
     * Creates new form panelDiagnosa
     */
    public PanelDiagnosaInaDrg() {
        initComponents();
        TabModeDiagnosaPasien = new DefaultTableModel(null, new Object[]{"P", "Tgl.Rawat", "No.Rawat", "No.R.M.",
            "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Prioritas"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
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
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(350);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosa = new DefaultTableModel(null, new Object[]{"P", "Kode", "Nama Penyakit", "Valid Kode", "IM"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
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
        tbDiagnosa.setModel(tabModeDiagnosa);
        // tbPenyakit.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 5; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(500);
            } else if (i == 3) {
                column.setPreferredWidth(85);
            } else if (i == 4) {
                column.setPreferredWidth(85);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeProsedur = new DefaultTableModel(null,
                new Object[]{"P", "Kode", "Deskripsi", "Valid Kode", "IM"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
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
        tbProsedur.setModel(tabModeProsedur);
        // tbPenyakit.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(500);
            } else if (i == 3) {
                column.setPreferredWidth(85);
            } else if (i == 4) {
                column.setPreferredWidth(85);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());

        TabModeTindakanPasien = new DefaultTableModel(null, new Object[]{"P", "Tgl.Rawat", "No.Rawat", "No.R.M.",
            "Nama Pasien", "Kode", "Nama Prosedur", "Status", "Prioritas"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
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
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(300);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeDiagnosaPasienICD = new DefaultTableModel(null, new Object[]{"Tgl.Rawat", "No.Rawat",
            "Nama Pasien", "Kode", "Nama Penyakit", "Prioritas"}) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};

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
        tbDiagnosaICD.setModel(TabModeDiagnosaPasienICD);
        tbDiagnosaICD.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaICD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbDiagnosaICD.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            }
        }
        tbDiagnosaICD.setDefaultRenderer(Object.class, new WarnaTable());
        
        TabModeTindakanPasienICD = new DefaultTableModel(null, new Object[]{"Tgl.Rawat", "No.Rawat",
            "Nama Pasien", "Kode", "Nama Prosedur","Prioritas"}) {
            Class[] types = new Class[]{java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class, java.lang.Object.class};

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
        tbProsedurICD.setModel(TabModeTindakanPasienICD);
        tbProsedurICD.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedurICD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbProsedurICD.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            }
        }
        tbProsedurICD.setDefaultRenderer(Object.class, new WarnaTable());

        Diagnosa.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Prosedur.setDocument(new batasInput((byte) 100).getKata(Prosedur));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPrioritas = new javax.swing.JMenuItem();
        TabRawat = new javax.swing.JTabbedPane();
        ScrollInput = new widget.ScrollPane();
        FormData = new widget.PanelBiasa();
        jLabel13 = new widget.Label();
        Diagnosa = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        jLabel15 = new widget.Label();
        Prosedur = new widget.TextBox();
        BtnCariProsedur = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbDiagnosaICD = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbProsedurICD = new widget.Table();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();

        MnPrioritas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrioritas.setForeground(new java.awt.Color(50, 50, 50));
        MnPrioritas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPrioritas.setText("Jadikan Prioritas");
        MnPrioritas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrioritas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrioritas.setPreferredSize(new java.awt.Dimension(170, 26));
        MnPrioritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrioritasActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPrioritas);

        setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        ScrollInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ScrollInput.setOpaque(true);

        FormData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        FormData.setPreferredSize(new java.awt.Dimension(865, 417));
        FormData.setLayout(null);

        jLabel13.setText("Diagnosa Ina Drg :");
        FormData.add(jLabel13);
        jLabel13.setBounds(0, 10, 110, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormData.add(Diagnosa);
        Diagnosa.setBounds(118, 10, 640, 23);

        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormData.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(761, 10, 28, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setOpaque(true);
        Scroll1.setViewportView(tbDiagnosa);

        FormData.add(Scroll1);
        Scroll1.setBounds(11, 36, 810, 165);

        jLabel15.setText("Prosedur Ina Drg :");
        FormData.add(jLabel15);
        jLabel15.setBounds(0, 211, 110, 23);

        Prosedur.setHighlighter(null);
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormData.add(Prosedur);
        Prosedur.setBounds(118, 211, 640, 23);

        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormData.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(761, 211, 28, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setOpaque(true);

        tbProsedur.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Scroll2.setViewportView(tbProsedur);

        FormData.add(Scroll2);
        Scroll2.setBounds(11, 237, 810, 165);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setOpaque(true);
        Scroll4.setViewportView(tbDiagnosaICD);

        FormData.add(Scroll4);
        Scroll4.setBounds(850, 36, 810, 165);

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll5.setOpaque(true);

        tbProsedurICD.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        Scroll5.setViewportView(tbProsedurICD);

        FormData.add(Scroll5);
        Scroll5.setBounds(850, 237, 810, 165);

        jLabel14.setText("Diagnosa ICD 10 :");
        FormData.add(jLabel14);
        jLabel14.setBounds(850, 10, 100, 23);
        jLabel14.getAccessibleContext().setAccessibleName("Diagnosa ICD 10 :");

        jLabel16.setText("Prosedur ICD 9 :");
        FormData.add(jLabel16);
        jLabel16.setBounds(850, 211, 90, 23);

        ScrollInput.setViewportView(FormData);

        TabRawat.addTab("Input Data Ina Drg", ScrollInput);

        internalFrame2.setBorder(null);
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setOpaque(true);

        tbDiagnosaPasien.setAutoCreateRowSorter(true);
        tbDiagnosaPasien.setComponentPopupMenu(jPopupMenu1);
        Scroll.setViewportView(tbDiagnosaPasien);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Data Diagnosa Ina Drg", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll3.setOpaque(true);

        tbTindakanPasien.setAutoCreateRowSorter(true);
        tbTindakanPasien.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setViewportView(tbTindakanPasien);

        internalFrame3.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Data Prosedur Ina Drg", internalFrame3);

        add(TabRawat, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            tampildiagnosa();
        }
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        tampildiagnosa();
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            tampilprosedure();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        tampilprosedure();
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        pilihTab();
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnPrioritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrioritasActionPerformed
        if (norawat.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                    prioritas(tbDiagnosaPasien.getValueAt(i, 5).toString(), 1);
                }
            }
        }
    }//GEN-LAST:event_MnPrioritasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariProsedur;
    public widget.TextBox Diagnosa;
    public widget.PanelBiasa FormData;
    private javax.swing.JMenuItem MnPrioritas;
    private widget.TextBox Prosedur;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    public widget.ScrollPane ScrollInput;
    public javax.swing.JTabbedPane TabRawat;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private javax.swing.JPopupMenu jPopupMenu1;
    public widget.Table tbDiagnosa;
    public widget.Table tbDiagnosaICD;
    public widget.Table tbDiagnosaPasien;
    public widget.Table tbProsedur;
    public widget.Table tbProsedurICD;
    public widget.Table tbTindakanPasien;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        try {
            psdiagnosapasien = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien_inadrg.kd_penyakit,diagnosa_pasien_inadrg.deskripsi,diagnosa_pasien_inadrg.status,"
                    + "IF (diagnosa_pasien_inadrg.prioritas = '1','Primer','Sekunder') prioritas "
                    + "from diagnosa_pasien_inadrg inner join reg_periksa on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                    + (keyword.trim().isEmpty() ? ""
                    : "and (diagnosa_pasien_inadrg.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or diagnosa_pasien_inadrg.kd_penyakit like ? or diagnosa_pasien_inadrg.deskripsi like ? or "
                    + "diagnosa_pasien_inadrg.status like ?)")
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas ");
            try {
                psdiagnosapasien.setString(1, tanggal1);
                psdiagnosapasien.setString(2, tanggal2);
                psdiagnosapasien.setString(3, "%" + norawat + "%");
                if (!keyword.trim().isEmpty()) {
                    psdiagnosapasien.setString(4, "%" + keyword + "%");
                    psdiagnosapasien.setString(5, "%" + keyword + "%");
                    psdiagnosapasien.setString(6, "%" + keyword + "%");
                    psdiagnosapasien.setString(7, "%" + keyword + "%");
                    psdiagnosapasien.setString(8, "%" + keyword + "%");
                    psdiagnosapasien.setString(9, "%" + keyword + "%");
                }

                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasien.addRow(new Object[]{false, rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public int getRecord() {
        if (TabRawat.getSelectedIndex() == 0) {
            i = 0;
        } else if (TabRawat.getSelectedIndex() == 1) {
            i = tbDiagnosaPasien.getRowCount();
        } else if (TabRawat.getSelectedIndex() == 2) {
            i = tbTindakanPasien.getRowCount();
        }
        return i;
    }

    private void tampildiagnosa() {
        try {
            if(Diagnosa.getText().trim().isEmpty()){
                tabModeDiagnosa.setRowCount(0);
            }else{
                requestJson = 
                    "{" +
                        "\"metadata\": {" +
                            "\"method\": \"search_diagnosis_inagrouper\""+
                        "}," +
                        "\"data\": {" +
                            "\"keyword\": \""+Diagnosa.getText().trim()+"\"" +
                        "}" +
                    "}";

                String request = requestJson;
                root = inacbg.request3(request);
                try {
                    mapper = new ObjectMapper();
                    System.out.println("Response : "+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
                    metadata = root.get("metadata");
                    int code = metadata.get("code").asInt();
                    response = root.get("response").get("data");
                    if(code == 200){
                        tabModeDiagnosa.setRowCount(0);

                        for(JsonNode row : response){
                            tabModeDiagnosa.addRow(new Object[]{false, row.get("code").asText(), row.get("description").asText(),
                                row.get("validcode").asText().replace("0", "Kode Tidak Valid").replace("1", "Kode Valid"),row.get("im").asText().replace("0", "Bukan IM").replace("1", "IM")});
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Data tidak ada !!!!");
                    }
                } catch (Exception e) {
                    System.err.println("Terjadi kesalahan saat mengambil nilai 'code': " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilprosedure() {
        try {
            if(Prosedur.getText().trim().isEmpty()){
                tabModeProsedur.setRowCount(0);
            }else{
                requestJson = 
                    "{" +
                        "\"metadata\": {" +
                            "\"method\": \"search_procedures_inagrouper\""+
                        "}," +
                        "\"data\": {" +
                            "\"keyword\": \""+Prosedur.getText().trim()+"\"" +
                        "}" +
                    "}";

                String request = requestJson;
                root = inacbg.request3(request);
                try {
                    System.out.println("Response : "+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
                    metadata = root.get("metadata");
                    int code = metadata.get("code").asInt();
                    response = root.get("response").get("data");
                    if(code == 200){
                        tabModeProsedur.setRowCount(0);

                        for(JsonNode row : response){
                            tabModeProsedur.addRow(new Object[]{false, row.get("code").asText(), row.get("description").asText(),
                                row.get("validcode").asText().replace("0", "Kode Tidak Valid").replace("1", "Kode Valid"),row.get("im").asText().replace("0", "Bukan IM").replace("1", "IM")});
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Data tidak ada !!!!");
                    }
                } catch (Exception e) {
                    System.err.println("Terjadi kesalahan saat mengambil nilai 'code': " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    /**
     *
     */
    public void tampil2() {
        Valid.tabelKosong(TabModeTindakanPasien);
        try {
            pstindakanpasien = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien_inadrg.kode,prosedur_pasien_inadrg.deskripsi, prosedur_pasien_inadrg.status,prosedur_pasien_inadrg.prioritas "
                    + "from prosedur_pasien_inadrg inner join reg_periksa on prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? "
                    + (keyword.trim().isEmpty() ? ""
                    : "and (prosedur_pasien_inadrg.no_rawat like ? or reg_periksa.no_rkm_medis like ? or "
                    + "pasien.nm_pasien like ? or prosedur_pasien_inadrg.kode like ? or prosedur_pasien_inadrg.deskripsi like ? or "
                    + "prosedur_pasien_inadrg.status like ?) ")
                    + "order by reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas ");
            try {
                pstindakanpasien.setString(1, tanggal1);
                pstindakanpasien.setString(2, tanggal2);
                pstindakanpasien.setString(3, "%" + norawat + "%");
                if (!keyword.trim().isEmpty()) {
                    pstindakanpasien.setString(4, "%" + keyword + "%");
                    pstindakanpasien.setString(5, "%" + keyword + "%");
                    pstindakanpasien.setString(6, "%" + keyword + "%");
                    pstindakanpasien.setString(7, "%" + keyword + "%");
                    pstindakanpasien.setString(8, "%" + keyword + "%");
                    pstindakanpasien.setString(9, "%" + keyword + "%");
                }

                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasien
                            .addRow(new Object[]{false, rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void setRM(String norawat, String norm, String tanggal1, String tanggal2, String status, String keyword) {
        this.norawat = norawat;
        this.norm = norm;
        this.tanggal1 = tanggal1;
        this.tanggal2 = tanggal2;
        this.status = status;
        this.keyword = keyword;
    }

    public void simpan() {
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5,
                            new String[]{norawat, tbDiagnosa.getValueAt(i, 1).toString(), tbDiagnosa.getValueAt(i, 2).toString(), status, Sequel.cariIsi(
                                "select ifnull(MAX(diagnosa_pasien_inadrg.prioritas)+1,1) from diagnosa_pasien_inadrg where diagnosa_pasien_inadrg.no_rawat=? and diagnosa_pasien_inadrg.status='"
                                + status + "'",
                                norawat)});
                }
            }
            koneksi.setAutoCommit(true);
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                tbDiagnosa.setValueAt(false, i, 0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }
        try {
            koneksi.setAutoCommit(false);
            index = 1;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien_inadrg", "?,?,?,?,?", "ICD 9", 5,
                            new String[]{norawat, tbProsedur.getValueAt(i, 1).toString(), tbProsedur.getValueAt(i, 2).toString(), status, Sequel.cariIsi(
                                "select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='"
                                + status + "'",
                                norawat)});
                }
            }
            koneksi.setAutoCommit(true);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                tbProsedur.setValueAt(false, i, 0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }
        pilihTab();
    }

    public void pilihTab() {
        if (TabRawat.getSelectedIndex() == 0) {
            tampil3();
            tampil4();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil2();
        }
    }

    public void batal() {
        Diagnosa.setText("");
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(false, i, 0);
        }
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            tbProsedur.setValueAt(false, i, 0);
        }
        Prosedur.setText("");
    }

    /**
     *
     */
    public void hapus() {
        if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?", 2,
                                new String[]{tbDiagnosaPasien.getValueAt(i, 2).toString(),
                                    tbDiagnosaPasien.getValueAt(i, 5).toString()});
                        try {
                            psnomor = koneksi.prepareStatement(
                                    "select prioritas from diagnosa_pasien_inadrg where no_rawat=? order by prioritas");
                            try {
                                psnomor.setString(1, tbDiagnosaPasien.getValueAt(i, 2).toString());
                                rsnomor = psnomor.executeQuery();
                                int nomorbarud = 1;
                                while (rsnomor.next()) {
                                    int nomorsekarangd = rsnomor.getInt("prioritas");
                                    if (nomorsekarangd != nomorbarud) {
                                        psupdatenomor = koneksi.prepareStatement(
                                                "update diagnosa_pasien_inadrg set prioritas=? where prioritas=? and no_rawat=?");
                                        psupdatenomor.setInt(1, nomorbarud);
                                        psupdatenomor.setInt(2, nomorsekarangd);
                                        psupdatenomor.setString(3, tbDiagnosaPasien.getValueAt(i, 2).toString());
                                        psupdatenomor.executeUpdate();
                                    }
                                    nomorbarud++;
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsnomor != null) {
                                    rsnomor.close();
                                }
                                if (psnomor != null) {
                                    psnomor.close();
                                }
                                if (psupdatenomor != null) {
                                    psupdatenomor.close();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else {
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    if (tbTindakanPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?", 2,
                                new String[]{tbTindakanPasien.getValueAt(i, 2).toString(),
                                    tbTindakanPasien.getValueAt(i, 5).toString()});
                        try {
                            psnomor = koneksi.prepareStatement(
                                    "select prioritas from prosedur_pasien_inadrg where no_rawat=? order by prioritas");
                            try {
                                psnomor.setString(1, tbTindakanPasien.getValueAt(i, 2).toString());
                                rsnomor = psnomor.executeQuery();
                                int nomorbarup = 1;
                                while (rsnomor.next()) {
                                    int nomorsekarangp = rsnomor.getInt("prioritas");
                                    if (nomorsekarangp != nomorbarup) {
                                        psupdatenomor = koneksi.prepareStatement(
                                                "update prosedur_pasien_inadrg set prioritas=? where prioritas=? and no_rawat=?");
                                        psupdatenomor.setInt(1, nomorbarup);
                                        psupdatenomor.setInt(2, nomorsekarangp);
                                        psupdatenomor.setString(3, tbTindakanPasien.getValueAt(i, 2).toString());
                                        psupdatenomor.executeUpdate();
                                    }
                                    nomorbarup++;
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsnomor != null) {
                                    rsnomor.close();
                                }
                                if (psnomor != null) {
                                    psnomor.close();
                                }
                                if (psupdatenomor != null) {
                                    psupdatenomor.close();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    }
                }
            }
        }
        pilihTab();
    }

    public void cetak() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            } else if (TabModeDiagnosaPasien.getRowCount() != 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                Valid.MyReportqry("rptDiagnosa.jasper", "report", "::[ Data Diagnosa Pasien ]::",
                        "select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "diagnosa_pasien_inadrg.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien_inadrg.status  "
                        + "from diagnosa_pasien inner join reg_periksa inner join pasien inner join penyakit "
                        + "on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and diagnosa_pasien_inadrg.kd_penyakit=penyakit.kd_penyakit "
                        + "where reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and reg_periksa.tgl_registrasi like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and diagnosa_pasien_inadrg.no_rawat like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and reg_periksa.no_rkm_medis like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm + "%' and pasien.nm_pasien like '%"
                        + keyword + "%' or " + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '"
                        + tanggal2 + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and diagnosa_pasien_inadrg.kd_penyakit like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and penyakit.nm_penyakit like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and diagnosa_pasien_inadrg.status like '%" + keyword + "%' "
                        + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas ",
                        param);
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            } else if (TabModeTindakanPasien.getRowCount() != 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                Valid.MyReportqry("rptProsedur.jasper", "report", "::[ Data Prosedur Tindakan Pasien ]::",
                        "select reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "prosedur_pasien_inadrg.kode,icd9.deskripsi_panjang, prosedur_pasien_inadrg.status "
                        + "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "
                        + "on prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and prosedur_pasien_inadrg.kode=icd9.kode " + "where reg_periksa.tgl_registrasi between '"
                        + tanggal1 + "' and '" + tanggal2 + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and reg_periksa.tgl_registrasi like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and prosedur_pasien_inadrg.no_rawat like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and reg_periksa.no_rkm_medis like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm + "%' and pasien.nm_pasien like '%"
                        + keyword + "%' or " + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '"
                        + tanggal2 + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and prosedur_pasien_inadrg.kode like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and icd9.deskripsi_panjang like '%" + keyword + "%' or "
                        + "reg_periksa.tgl_registrasi between '" + tanggal1 + "' and '" + tanggal2
                        + "' and reg_periksa.no_rkm_medis like '%" + norm
                        + "%' and prosedur_pasien_inadrg.status like '%" + keyword + "%' "
                        + "order by reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas ",
                        param);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void tampil3() {
        Valid.tabelKosong(TabModeDiagnosaPasienICD);
        try {
            psdiagnosapasien = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.status,"
                    + "diagnosa_pasien.status_penyakit,IF (diagnosa_pasien.prioritas = '1','Primer','Sekunder') prioritas "
                    + "from diagnosa_pasien inner join reg_periksa on diagnosa_pasien.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? order by diagnosa_pasien.prioritas asc");
            try {
                psdiagnosapasien.setString(1, tanggal1);
                psdiagnosapasien.setString(2, tanggal2);
                psdiagnosapasien.setString(3, "%" + norawat + "%");
                
                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasienICD.addRow(new Object[]{rs.getString(1), rs.getString(2),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(9)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampil4() {
        Valid.tabelKosong(TabModeTindakanPasienICD);
        try {
            pstindakanpasien = koneksi.prepareStatement(
                    "select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status,prosedur_pasien.prioritas "
                    + "from prosedur_pasien inner join reg_periksa on prosedur_pasien.no_rawat=reg_periksa.no_rawat "
                    + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join icd9 on prosedur_pasien.kode=icd9.kode "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? order by prosedur_pasien.prioritas asc");
            try {
                pstindakanpasien.setString(1, tanggal1);
                pstindakanpasien.setString(2, tanggal2);
                pstindakanpasien.setString(3, "%" + norawat + "%");

                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasienICD
                            .addRow(new Object[]{rs.getString(1), rs.getString(2),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void prioritas(String kode, int newOrder) {
        if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        int oldOrder = 0;
                        try {
                            psnomor = koneksi.prepareStatement(
                                    "select prioritas from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?");
                            try {
                                psnomor.setString(1, tbDiagnosaPasien.getValueAt(i, 2).toString());
                                psnomor.setString(2, kode);
                                rsnomor = psnomor.executeQuery();
                                if(rsnomor.next()){
                                    oldOrder = rsnomor.getInt("prioritas");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsnomor != null) {
                                    rsnomor.close();
                                }
                                if (psnomor != null) {
                                    psnomor.close();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                        
                        if(newOrder > oldOrder){
                            try {
                                psnomor = koneksi.prepareStatement(
                                        "update diagnosa_pasien_inadrg set diagnosa_pasien_inadrg.prioritas = diagnosa_pasien_inadrg.prioritas - 1  where diagnosa_pasien_inadrg.prioritas > ? and diagnosa_pasien_inadrg.prioritas <= ?");
                                try {
                                    psnomor.setInt(1, oldOrder);
                                    psnomor.setInt(2, newOrder);
                                    psnomor.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (psnomor != null) {
                                        psnomor.close();
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                        } else if(newOrder < oldOrder){
                            try {
                                psnomor = koneksi.prepareStatement(
                                        "update diagnosa_pasien_inadrg set diagnosa_pasien_inadrg.prioritas = diagnosa_pasien_inadrg.prioritas + 1 where diagnosa_pasien_inadrg.prioritas < ? and diagnosa_pasien_inadrg.prioritas >= ?");
                                try {
                                    psnomor.setInt(1, oldOrder);
                                    psnomor.setInt(2, newOrder);
                                    psnomor.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (psnomor != null) {
                                        psnomor.close();
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                        }
                        
                        try {
                            psnomor = koneksi.prepareStatement(
                                    "update diagnosa_pasien_inadrg set diagnosa_pasien_inadrg.prioritas = ? where diagnosa_pasien_inadrg.kd_penyakit = ?");
                            try {
                                psnomor.setInt(1, newOrder);
                                psnomor.setString(2, kode);
                                psnomor.executeUpdate();
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (psnomor != null) {
                                    psnomor.close();
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    }
                }
            }
        }
        pilihTab();
    }

    private static final Logger LOG = Logger.getLogger(PanelDiagnosaInaDrg.class.getName());

}
