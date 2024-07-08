/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package laporan;

import fungsi.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author perpustakaan
 */
public class DlgRekapLabPerTahun extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0,jan=0,feb=0,mar=0,apr=0,mei=0,jun=0,jul=0,agu=0,sep=0,okt=0,nov=0,des=0;   
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgRekapLabPerTahun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.","Jenis Pemeriksaan","Jan","Feb","Mar","Apr",
                "Mei","Jun","Jul","Agu","Sep","Okt","Nov","Des","Total"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(300);
            }else{
                column.setPreferredWidth(40);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput(200).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        
        Valid.LoadTahun(ThnCari);
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        ThnCari = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Pemeriksaan Laboratorium Per Tahun ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setName("tbBangsal"); // NOI18N
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tahun :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass5.add(label11);

        ThnCari.setName("ThnCari"); // NOI18N
        ThnCari.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass5.add(ThnCari);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

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
        panelGlass5.add(BtnCari);

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
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

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
        panelGlass5.add(BtnPrint);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            
            Map<String, Object> param = new HashMap<>();         
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
            for(int r=0;r<tabMode.getRowCount();r++){ 
                if(!tbBangsal.getValueAt(r,0).toString().contains(">>")){
                    Sequel.menyimpan("temporary","'"+r+"','"+
                                    tabMode.getValueAt(r,0).toString()+"','"+
                                    tabMode.getValueAt(r,1).toString()+"','"+
                                    tabMode.getValueAt(r,2).toString()+"','"+
                                    tabMode.getValueAt(r,3).toString()+"','"+
                                    tabMode.getValueAt(r,4).toString()+"','"+
                                    tabMode.getValueAt(r,5).toString()+"','"+
                                    tabMode.getValueAt(r,6).toString()+"','"+
                                    tabMode.getValueAt(r,7).toString()+"','"+
                                    tabMode.getValueAt(r,8).toString()+"','"+
                                    tabMode.getValueAt(r,9).toString()+"','"+
                                    tabMode.getValueAt(r,10).toString()+"','"+
                                    tabMode.getValueAt(r,11).toString()+"','"+
                                    tabMode.getValueAt(r,12).toString()+"','"+
                                    tabMode.getValueAt(r,13).toString()+"','"+
                                    tabMode.getValueAt(r,14).toString()+"','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                }                    
            }
               
            Valid.MyReportqry("rptRekapLabPerTahun.jasper","report","::[ Laporan Rekap Laboratorium Per Tahun ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            //Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
       tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TCari, BtnPrint);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
           TCari.setText("");
           tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekapLabPerTahun dialog = new DlgRekapLabPerTahun(new javax.swing.JFrame(), true);
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
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.ComboBox ThnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label label11;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode);
            ps=koneksi.prepareStatement(
                "select jns_perawatan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan "+
                "from jns_perawatan_lab where jns_perawatan_lab.status='1' and jns_perawatan_lab.nm_perawatan like ? order by jns_perawatan_lab.nm_perawatan");
            try {
                ps.setString(1,"%"+TCari.getText()+"%");
                rs=ps.executeQuery();
                i=1;
                while(rs.next()){
                    jan=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-01%");
                    feb=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-02%");
                    mar=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-03%");
                    apr=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-04%");
                    mei=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-05%");
                    jun=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-06%");
                    jul=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-07%");
                    agu=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-08%");
                    sep=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-09%");
                    okt=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-10%");
                    nov=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-11%");
                    des=Sequel.cariInteger("select count(kd_jenis_prw) from periksa_lab where kd_jenis_prw=? and tgl_periksa like ?",rs.getString("kd_jenis_prw"),"%"+ThnCari.getSelectedItem()+"-12%");
                    tabMode.addRow(new String[]{
                        i+"",rs.getString("kd_jenis_prw")+" "+rs.getString("nm_perawatan"),jan+"",feb+"",mar+"",apr+"",mei+"",jun+"",jul+"",agu+"",
                        sep+"",okt+"",nov+"",des+"",(jan+feb+mar+apr+mei+jun+jul+agu+sep+okt+nov+des)+""
                    });
                    ps2=koneksi.prepareStatement("select id_template,Pemeriksaan from template_laboratorium where kd_jenis_prw=? order by urut");
                    try {
                        ps2.setString(1,rs.getString("kd_jenis_prw"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            jan=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-01%");
                            feb=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-02%");
                            mar=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-03%");
                            apr=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-04%");
                            mei=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-05%");
                            jun=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-06%");
                            jul=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-07%");
                            agu=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-08%");
                            sep=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-09%");
                            okt=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-10%");
                            nov=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-11%");
                            des=Sequel.cariInteger("select count(id_template) from detail_periksa_lab where id_template=? and tgl_periksa like ?",rs2.getString("id_template"),"%"+ThnCari.getSelectedItem()+"-12%");
                            tabMode.addRow(new String[]{
                                "","    "+rs2.getString("Pemeriksaan"),jan+"",feb+"",mar+"",apr+"",mei+"",jun+"",jul+"",agu+"",
                                sep+"",okt+"",nov+"",des+"",(jan+feb+mar+apr+mei+jun+jul+agu+sep+okt+nov+des)+""
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 2 : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
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
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notif : "+e);
        }    
    }

    

}
