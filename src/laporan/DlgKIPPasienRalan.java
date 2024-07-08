package laporan;
import fungsi.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 *
 * @author Kanit SIRS
 */
public class DlgKIPPasienRalan extends javax.swing.JDialog {
    private final validasi Valid=new validasi();
    private final sekuel Sequel=new sekuel();
    private final Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private String[] carabayar,kodebayar;
    private int[] jumlahcarabayar;
    private StringBuilder htmlContent;
    private DlgCariPenyakit penyakit=new DlgCariPenyakit(null,false);
    private int hr0s6l=0,hr0s6p=0,hr7s28l=0,hr7s28p=0,hr28s1thl=0,hr28s1thp=0,th1s4l=0,th1s4p=0,th5s14l=0,th5s14p=0,
                th15s24l=0,th15s24p=0,th25s44l=0,th25s44p=0,th45s64l=0,th45s64p=0,lbth65l=0,lbth65p=0,kolom=0,
                pulang=0,rawat=0,rujuk=0,kbl=0,kbp=0,kll=0,klp=0;
    private String sthr0s6l="",sthr0s6p="",sthr7s28l="",sthr7s28p="",sthr28s1thl="",sthr28s1thp="",stth1s4l="",stth1s4p="",
                stth5s14l="",stth5s14p="",stth15s24l="",stth15s24p="",stth25s44l="",stth25s44p="",stth45s64l="",stth45s64p="",
                stlbth65l="",stlbth65p="",diagnosa="",stpulang="",strawat="",strujuk="",
                stkbl="",stkbp="",stkll="",stklp="";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgKIPPasienRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penyakit.getTable().getSelectedRow()!= -1){                   
                    kdpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),0).toString());
                    nmpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                }     
                kdpenyakit.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
    private final Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private int i=0,z=0;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        kdpenyakit = new widget.TextBox();
        nmpenyakit = new widget.TextBox();
        btnBangsal = new widget.Button();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        btnCari = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        Scroll = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Kartu Indeks Pasien Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel4.setText("Penyakit :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi1.add(jLabel4);

        kdpenyakit.setEditable(false);
        kdpenyakit.setHighlighter(null);
        kdpenyakit.setName("kdpenyakit"); // NOI18N
        kdpenyakit.setPreferredSize(new java.awt.Dimension(50, 23));
        kdpenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenyakitKeyPressed(evt);
            }
        });
        panelisi1.add(kdpenyakit);

        nmpenyakit.setEditable(false);
        nmpenyakit.setName("nmpenyakit"); // NOI18N
        nmpenyakit.setPreferredSize(new java.awt.Dimension(200, 23));
        nmpenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpenyakitKeyPressed(evt);
            }
        });
        panelisi1.add(nmpenyakit);

        btnBangsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBangsal.setMnemonic('1');
        btnBangsal.setToolTipText("Alt+1");
        btnBangsal.setName("btnBangsal"); // NOI18N
        btnBangsal.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBangsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBangsalActionPerformed(evt);
            }
        });
        btnBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBangsalKeyPressed(evt);
            }
        });
        panelisi1.add(btnBangsal);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi1.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(Tgl2);

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
        label9.setPreferredSize(new java.awt.Dimension(15, 23));
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

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll.setViewportView(LoadHTML);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            
            File g = new File("file2.css");            
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+                    
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
            );
            bg.close();
            
            File f = new File("kipralan.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
            bw.write(LoadHTML.getText().replaceAll("<head>","<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                        "<table width='"+(1750+(70*kolom))+"' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                            "<tr class='isi2'>"+
                                "<td valign='top' align='center'>"+
                                    "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                    akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                    akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                    "<font size='2' face='Tahoma'>KARTU INDEKS PENYAKIT PASIEN RAWAT JALAN PERIODE "+Tgl1.getSelectedItem()+" S.D. "+Tgl2.getSelectedItem()+"<br>"+
                                    "Diagnosa : "+nmpenyakit.getText()+"<br>Kode ICD X : "+kdpenyakit.getText()+"<br><br></font>"+        
                                "</td>"+
                           "</tr>"+
                        "</table>")
            );
            bw.close();                         
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }     
        
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    if(nmpenyakit.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(null,"Silahkan masukkan penyakit yang mau dicari terlebih dahulu..!!!");
    }else{
        prosesCari();
    }
}//GEN-LAST:event_btnCariActionPerformed

private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            btnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, Tgl2, BtnPrint);
        }
}//GEN-LAST:event_btnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void btnBangsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBangsalActionPerformed
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnBangsalActionPerformed

    private void btnBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBangsalKeyPressed
        Valid.pindah(evt,kdpenyakit,btnCari);
    }//GEN-LAST:event_btnBangsalKeyPressed

    private void nmpenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpenyakitKeyPressed
        Valid.pindah(evt,kdpenyakit,btnCari);
    }//GEN-LAST:event_nmpenyakitKeyPressed

    private void kdpenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenyakitKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select penyakit.nm_penyakit from penyakit where penyakit.kd_penyakit=?", nmpenyakit,kdpenyakit.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnBangsalActionPerformed(null);
        }else{
            Valid.pindah(evt, kdpenyakit,btnCari);
        }
    }//GEN-LAST:event_kdpenyakitKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKIPPasienRalan dialog = new DlgKIPPasienRalan(new javax.swing.JFrame(), true);
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
    private widget.TextBox Kd2;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnBangsal;
    private widget.Button btnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel4;
    private widget.TextBox kdpenyakit;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.TextBox nmpenyakit;
    private widget.panelisi panelisi1;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            htmlContent = new StringBuilder();
            kolom=Sequel.cariInteger("select count(kd_pj) from penjab");
            carabayar=new String[kolom];
            kodebayar=new String[kolom];
            jumlahcarabayar=new int[kolom];
            
            htmlContent.append("<tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' width='10px' rowspan='3'>NO.</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='40px' rowspan='3'>NO.R.M.</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='150px' rowspan='3'>Nama Pasien</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='90px' rowspan='3'>NIK</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='50px' rowspan='3'>TGL.MASUK</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='200px' colspan='18'>GOLONGAN UMUR</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='60px' rowspan='3'>DIAGNOSA KOMPILASI</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='70px' rowspan='2' colspan='3'>TINDAK LANJUT PERAWATAN</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='50px' rowspan='2' colspan='2'>KASUS BARU</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='50px' rowspan='2' colspan='2'>KASUS LAMA</td><td valign='middle' bgcolor='#FFFAFA' align='center' width='").append(70*kolom).append("px' rowspan='2' colspan='").append(kolom).append("'>KETERANGAN</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>0-6 hr</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>7-27hr</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>28hr-&lsaquo;1th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>1-4th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>5-14th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>15-24th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>25-44th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>45-64th</td><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='2'>&rsaquo;&nbsp;64</td></tr><tr class='isi'><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>PULANG</td><td valign='middle' bgcolor='#FFFAFA' align='center'>RAWAT</td><td valign='middle' bgcolor='#FFFAFA' align='center'>RUJUK</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td><td valign='middle' bgcolor='#FFFAFA' align='center'>L</td><td valign='middle' bgcolor='#FFFAFA' align='center'>P</td>");
            i=0;
            ps2=koneksi.prepareStatement("select kd_pj,png_jawab from penjab order by png_jawab");
            try {
                rs2=ps2.executeQuery();
                while(rs2.next()){
                    kodebayar[i]=rs2.getString("kd_pj");
                    jumlahcarabayar[i]=0;
                    i++;
                    htmlContent.append("<td valign='middle' bgcolor='#FFFAFA' align='center'>").append(rs2.getString("png_jawab")).append("</td>");
                }
            } catch (Exception e) {
                System.out.println("Akun Bayar : "+e);
            } finally{
                if(rs2!=null){
                    rs2.close();
                }
                if(ps2!=null){
                    ps2.close();
                }
            }
            htmlContent.append(
                "</tr>"
            );    
            
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.no_ktp,reg_periksa.tgl_registrasi,reg_periksa.no_rawat,"+
                    "reg_periksa.umurdaftar,reg_periksa.sttsumur,pasien.jk,reg_periksa.stts,diagnosa_pasien.status_penyakit, "+
                    "reg_periksa.kd_pj from reg_periksa inner join diagnosa_pasien on reg_periksa.no_rawat=diagnosa_pasien.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between ? and ? "+
                    "and reg_periksa.status_lanjut='Ralan' and diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and "+
                    "diagnosa_pasien.kd_penyakit=? order by reg_periksa.tgl_registrasi");
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(3,kdpenyakit.getText());
                rs=ps.executeQuery();
                i=1;hr0s6l=0;hr0s6p=0;hr7s28l=0;hr7s28p=0;hr28s1thl=0;hr28s1thp=0;th1s4l=0;th1s4p=0;th5s14l=0;th5s14p=0;
                th15s24l=0;th15s24p=0;th25s44l=0;th25s44p=0;th45s64l=0;th45s64p=0;lbth65l=0;lbth65p=0;pulang=0;rawat=0;
                rujuk=0;kbl=0;kbp=0;kll=0;klp=0;
                while(rs.next()){
                    sthr0s6l="";sthr0s6p="";sthr7s28l="";sthr7s28p="";sthr28s1thl="";sthr28s1thp="";stth1s4l="";stth1s4p="";
                    stth5s14l="";stth5s14p="";stth15s24l="";stth15s24p="";stth25s44l="";stth25s44p="";stth45s64l="";stth45s64p="";
                    stlbth65l="";stlbth65p="";stpulang="";strawat="";strujuk="";stkbl="";stkbp="";stkll="";stklp="";
                    if(rs.getString("sttsumur").equals("Hr")){
                        if((rs.getInt("umurdaftar")>=0)&&(rs.getInt("umurdaftar")<=6)){
                            if(rs.getString("jk").equals("L")){
                                hr0s6l++;
                                sthr0s6l="V";
                            }else if(rs.getString("jk").equals("P")){
                                hr0s6p++;
                                sthr0s6p="V";
                            }
                        }else if((rs.getInt("umurdaftar")>=7)&&(rs.getInt("umurdaftar")<=27)){
                            if(rs.getString("jk").equals("L")){
                                hr7s28l++;
                                sthr7s28l="V";
                            }else if(rs.getString("jk").equals("P")){
                                hr7s28p++;
                                sthr7s28p="V";
                            }
                        }else if(rs.getInt("umurdaftar")>=28){
                            if(rs.getString("jk").equals("L")){
                                hr28s1thl++;
                                sthr28s1thl="V";
                            }else if(rs.getString("jk").equals("P")){
                                hr28s1thp++;
                                sthr28s1thp="V";
                            }
                        }
                    }else if(rs.getString("sttsumur").equals("Bl")){
                        if(rs.getString("jk").equals("L")){
                            hr28s1thl++;
                            sthr28s1thl="V";
                        }else if(rs.getString("jk").equals("P")){
                            hr28s1thp++;
                            sthr28s1thp="V";
                        }
                    }else if(rs.getString("sttsumur").equals("Th")){
                        if((rs.getInt("umurdaftar")>=0)&&(rs.getInt("umurdaftar")<=4)){
                            if(rs.getString("jk").equals("L")){
                                th1s4l++;
                                stth1s4l="V";
                            }else if(rs.getString("jk").equals("P")){
                                th1s4p++;
                                stth1s4p="V";
                            }
                        }else if((rs.getInt("umurdaftar")>=5)&&(rs.getInt("umurdaftar")<=14)){
                            if(rs.getString("jk").equals("L")){
                                th5s14l++;
                                stth5s14l="V";
                            }else if(rs.getString("jk").equals("P")){
                                th5s14p++;
                                stth5s14p="V";
                            }
                        }else if((rs.getInt("umurdaftar")>=15)&&(rs.getInt("umurdaftar")<=24)){
                            if(rs.getString("jk").equals("L")){
                                th15s24l++;
                                stth15s24l="V";
                            }else if(rs.getString("jk").equals("P")){
                                th15s24p++;
                                stth15s24p="V";
                            }
                        }else if((rs.getInt("umurdaftar")>=25)&&(rs.getInt("umurdaftar")<=44)){
                            if(rs.getString("jk").equals("L")){
                                th25s44l++;
                                stth25s44l="V";
                            }else if(rs.getString("jk").equals("P")){
                                th25s44p++;
                                stth25s44p="V";
                            }
                        }else if((rs.getInt("umurdaftar")>=45)&&(rs.getInt("umurdaftar")<=64)){
                            if(rs.getString("jk").equals("L")){
                                th45s64l++;
                                stth45s64l="V";
                            }else if(rs.getString("jk").equals("P")){
                                th45s64p++;
                                stth45s64p="V";
                            }
                        }else if(rs.getInt("umurdaftar")>=65){
                            if(rs.getString("jk").equals("L")){
                                lbth65l++;
                                stlbth65l="V";
                            }else if(rs.getString("jk").equals("P")){
                                lbth65p++;
                                stlbth65p="V";
                            }
                        }
                    }
                    
                    if(rs.getString("stts").equals("Belum")){
                        pulang++;
                        stpulang="V";
                    }else if(rs.getString("stts").equals("Sudah")){
                        pulang++;
                        stpulang="V";
                    }else if(rs.getString("stts").equals("Batal")){
                        pulang++;
                        stpulang="V";
                    }else if(rs.getString("stts").equals("Berkas Diterima")){
                        pulang++;
                        stpulang="V";
                    }else if(rs.getString("stts").equals("Dirujuk")){
                        rujuk++;
                        strujuk="V";
                    }else if(rs.getString("stts").equals("Meninggal")){
                        pulang++;
                        stpulang="V";
                    }else if(rs.getString("stts").equals("Dirawat")){
                        rawat++;
                        strawat="V";
                    }else if(rs.getString("stts").equals("Pulang Paksa")){
                        pulang++;
                        stpulang="V";
                    }
                    
                    if(rs.getString("status_penyakit").equals("Lama")){
                        if(rs.getString("jk").equals("L")){
                            kll++;
                            stkll="V";
                        }else if(rs.getString("jk").equals("P")){
                            klp++;
                            stklp="V";
                        }
                    }else if(rs.getString("status_penyakit").equals("Baru")){
                        if(rs.getString("jk").equals("L")){
                            kbl++;
                            stkbl="V";
                        }else if(rs.getString("jk").equals("P")){
                            kbp++;
                            stkbp="V";
                        }
                    }
                    
                    diagnosa="";
                    ps2=koneksi.prepareStatement("select diagnosa_pasien.kd_penyakit from diagnosa_pasien where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas>1 and diagnosa_pasien.no_rawat=?");    
                    try {
                        ps2.setString(1,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            if(diagnosa.isEmpty()){
                                diagnosa=rs2.getString(1);
                            }else{
                                diagnosa=diagnosa+", "+rs2.getString(1);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 1 : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }  
                    if(diagnosa.endsWith(",")){
                        diagnosa = diagnosa.substring(0,diagnosa.length() - 1);
                    }
                    
                    htmlContent.append("<tr class='isi'><td valign='middle' align='center'>").append(i).append("</td><td valign='middle' align='center'>").append(rs.getString("no_rkm_medis")).append("</td><td valign='middle' align='left'>").append(rs.getString("nm_pasien")).append("</td><td valign='middle' align='center'>").append(rs.getString("no_ktp")).append("</td><td valign='middle' align='center'>").append(rs.getString("tgl_registrasi")).append("</td><td valign='middle' align='center'>").append(sthr0s6l).append("</td><td valign='middle' align='center'>").append(sthr0s6p).append("</td><td valign='middle' align='center'>").append(sthr7s28l).append("</td><td valign='middle' align='center'>").append(sthr7s28p).append("</td><td valign='middle' align='center'>").append(sthr28s1thl).append("</td><td valign='middle' align='center'>").append(sthr28s1thp).append("</td><td valign='middle' align='center'>").append(stth1s4l).append("</td><td valign='middle' align='center'>").append(stth1s4p).append("</td><td valign='middle' align='center'>").append(stth5s14l).append("</td><td valign='middle' align='center'>").append(stth5s14p).append("</td><td valign='middle' align='center'>").append(stth15s24l).append("</td><td valign='middle' align='center'>").append(stth15s24p).append("</td><td valign='middle' align='center'>").append(stth25s44l).append("</td><td valign='middle' align='center'>").append(stth25s44p).append("</td><td valign='middle' align='center'>").append(stth45s64l).append("</td><td valign='middle' align='center'>").append(stth45s64p).append("</td><td valign='middle' align='center'>").append(stlbth65l).append("</td><td valign='middle' align='center'>").append(stlbth65p).append("</td><td valign='middle' align='center'>").append(diagnosa).append("</td><td valign='middle' align='center'>").append(stpulang).append("</td><td valign='middle' align='center'>").append(strawat).append("</td><td valign='middle' align='center'>").append(strujuk).append("</td><td valign='middle' align='center'>").append(stkbl).append("</td><td valign='middle' align='center'>").append(stkbp).append("</td><td valign='middle' align='center'>").append(stkll).append("</td><td valign='middle' align='center'>").append(stklp).append("</td>");
                    for(z=0;z<kolom;z++){
                        carabayar[z]="";
                        if(rs.getString("kd_pj").equals(kodebayar[z])){
                            carabayar[z]="V";
                            jumlahcarabayar[z]++;
                        }
                        htmlContent.append("<td valign='middle' align='center'>").append(carabayar[z]).append("</td>");
                    }
                    htmlContent.append(
                        "</tr>"
                    );
                    i++;
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
            if((i-1)>0){
                htmlContent.append("<tr class='isi'><td valign='middle' align='right' colspan='5'>Total : </td><td valign='middle' align='center'>").append(hr0s6l).append("</td><td valign='middle' align='center'>").append(hr0s6p).append("</td><td valign='middle' align='center'>").append(hr7s28l).append("</td><td valign='middle' align='center'>").append(hr7s28p).append("</td><td valign='middle' align='center'>").append(hr28s1thl).append("</td><td valign='middle' align='center'>").append(hr28s1thp).append("</td><td valign='middle' align='center'>").append(th1s4l).append("</td><td valign='middle' align='center'>").append(th1s4p).append("</td><td valign='middle' align='center'>").append(th5s14l).append("</td><td valign='middle' align='center'>").append(th5s14p).append("</td><td valign='middle' align='center'>").append(th15s24l).append("</td><td valign='middle' align='center'>").append(th15s24p).append("</td><td valign='middle' align='center'>").append(th25s44l).append("</td><td valign='middle' align='center'>").append(th25s44p).append("</td><td valign='middle' align='center'>").append(th45s64l).append("</td><td valign='middle' align='center'>").append(th45s64p).append("</td><td valign='middle' align='center'>").append(lbth65l).append("</td><td valign='middle' align='center'>").append(lbth65p).append("</td><td valign='middle' align='center'></td><td valign='middle' align='center'>").append(pulang).append("</td><td valign='middle' align='center'>").append(rawat).append("</td><td valign='middle' align='center'>").append(rujuk).append("</td><td valign='middle' align='center'>").append(kbl).append("</td><td valign='middle' align='center'>").append(kbp).append("</td><td valign='middle' align='center'>").append(kll).append("</td><td valign='middle' align='center'>").append(klp).append("</td>");
                for(z=0;z<kolom;z++){
                    htmlContent.append("<td valign='middle' align='center'>").append(jumlahcarabayar[z]).append("</td>");
                }
                htmlContent.append(
                    "</tr>"
                );
            }
            
            LoadHTML.setText(
                    "<html>"+
                      "<table width='"+(1750+(70*kolom))+"px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        } 
        this.setCursor(Cursor.getDefaultCursor());
        
    }
    
    public void isCek(){
        BtnPrint.setEnabled(akses.getkip_pasien_ralan());
    }
    
}
