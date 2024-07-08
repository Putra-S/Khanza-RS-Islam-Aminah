/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package rekammedis;

import fungsi.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author dosen
 */
public class RMCariPemeriksaanTTV extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String norawat="";
    private int z=0,i=0,jml=0,index=0;
    private boolean[] pilih;
    private String[] tanggal,jam,suhu,tensi,nadi,respirasi,spo2,gcs;
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public RMCariPemeriksaanTTV(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

//        Object[] row={"Tanggal","Jam","Pemeriksaan"};
//        tabMode=new DefaultTableModel(null,row){
//              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
//        };
//        tbKamar.setModel(tabMode);
        tabMode=new DefaultTableModel(null,new Object[]{
                "P","Tanggal","Jam","Suhu","Tensi","Nadi","Respirasi","SpO2","GCS"
            }){
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
             };
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
               boolean a = false;
               if (colIndex==0) {
                 a=true;
               }
               return a;
             }
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbKamar.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (z= 0; z < 9; z++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(z);
            if(z==0){
                column.setPreferredWidth(15);
            }else if(z==1){
                column.setPreferredWidth(65);
            }else if(z==2){
                column.setPreferredWidth(50);
            }else if(z==3){
                column.setPreferredWidth(75);
            }else if(z==4){
                column.setPreferredWidth(75);
            }else if(z==5){
                column.setPreferredWidth(75);
            }else if(z==6){
                column.setPreferredWidth(75);
            }else if(z==7){
                column.setPreferredWidth(75);
            }else if(z==8){
                column.setPreferredWidth(75);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
    tbKamar = new widget.Table();
    panelisi3 = new widget.panelisi();
    label9 = new widget.Label();
    TCari = new widget.TextBox();
    BtnCari = new widget.Button();
    BtnAll = new widget.Button();
    label10 = new widget.Label();
    LCount = new widget.Label();
    BtnKeluar = new widget.Button();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setUndecorated(true);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowActivated(java.awt.event.WindowEvent evt) {
        formWindowActivated(evt);
      }
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Riwayat Pemeriksaan TTV ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
    internalFrame1.setName("internalFrame1"); // NOI18N
    internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

    Scroll.setName("Scroll"); // NOI18N
    Scroll.setOpaque(true);

    tbKamar.setAutoCreateRowSorter(true);
    tbKamar.setName("tbKamar"); // NOI18N
    tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tbKamarMouseClicked(evt);
      }
    });
    tbKamar.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        tbKamarKeyPressed(evt);
      }
    });
    Scroll.setViewportView(tbKamar);

    internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

    panelisi3.setName("panelisi3"); // NOI18N
    panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
    panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

    label9.setText("Key Word :");
    label9.setName("label9"); // NOI18N
    label9.setPreferredSize(new java.awt.Dimension(68, 23));
    panelisi3.add(label9);

    TCari.setName("TCari"); // NOI18N
    TCari.setPreferredSize(new java.awt.Dimension(300, 23));
    TCari.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        TCariKeyPressed(evt);
      }
    });
    panelisi3.add(TCari);

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
    panelisi3.add(BtnCari);

    BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
    BtnAll.setMnemonic('2');
    BtnAll.setToolTipText("2Alt+2");
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
    panelisi3.add(BtnAll);

    label10.setText("Record :");
    label10.setName("label10"); // NOI18N
    label10.setPreferredSize(new java.awt.Dimension(60, 23));
    panelisi3.add(label10);

    LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    LCount.setText("0");
    LCount.setName("LCount"); // NOI18N
    LCount.setPreferredSize(new java.awt.Dimension(50, 23));
    panelisi3.add(LCount);

    BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
    BtnKeluar.setMnemonic('4');
    BtnKeluar.setToolTipText("Alt+4");
    BtnKeluar.setName("BtnKeluar"); // NOI18N
    BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
    BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BtnKeluarActionPerformed(evt);
      }
    });
    panelisi3.add(BtnKeluar);

    internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

    getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbKamar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if(tabMode.getRowCount()!=0){
            if(evt.getClickCount()==2){
                dispose();
            }
        }
}//GEN-LAST:event_tbKamarMouseClicked

    private void tbKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamarKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbKamarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCariPemeriksaanTTV dialog = new RMCariPemeriksaanTTV(new javax.swing.JFrame(), true);
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
  private widget.Label LCount;
  private widget.ScrollPane Scroll;
  private widget.TextBox TCari;
  private widget.InternalFrame internalFrame1;
  private widget.Label label10;
  private widget.Label label9;
  private widget.panelisi panelisi3;
  private widget.Table tbKamar;
  // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        jml=0;
        for(i=0;i<tabMode.getRowCount();i++){
            if(tabMode.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }

        pilih=null;
        pilih=new boolean[jml]; 
        tanggal=null;
        tanggal=new String[jml];
        jam=null;
        jam=new String[jml];
        suhu=null;
        suhu=new String[jml];
        tensi=null;
        tensi=new String[jml];
        nadi=null;
        nadi=new String[jml];
        respirasi=null;
        respirasi=new String[jml];
        spo2=null;
        spo2=new String[jml];
        gcs=null;
        gcs=new String[jml];
        
        index=0;        
        for(i=0;i<tabMode.getRowCount();i++){
            if(tabMode.getValueAt(i,0).toString().equals("true")){
                pilih[index]=true;
                tanggal[index]=tabMode.getValueAt(i,1).toString();
                jam[index]=tabMode.getValueAt(i,2).toString();
                suhu[index]=tabMode.getValueAt(i,3).toString();
                tensi[index]=tabMode.getValueAt(i,4).toString();
                nadi[index]=tabMode.getValueAt(i,5).toString();
                respirasi[index]=tabMode.getValueAt(i,6).toString();
                spo2[index]=tabMode.getValueAt(i,7).toString();
                gcs[index]=tabMode.getValueAt(i,8).toString();
                index++;
            }
        }       

        Valid.tabelKosong(tabMode);

        for(i=0;i<jml;i++){
            tabMode.addRow(new Object[] {
                pilih[i],tanggal[i],jam[i],suhu[i],tensi[i],nadi[i],respirasi[i],spo2[i],gcs[i]
            });
        }
        Valid.tabelKosong(tabMode);
                        
        try{
            ps=koneksi.prepareStatement(
                    "select pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.spo2,pemeriksaan_ranap.gcs "+
                    "from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat=? and "+
                    "(pemeriksaan_ranap.tgl_perawatan like ?) "+
                    "order by pemeriksaan_ranap.tgl_perawatan DESC,pemeriksaan_ranap.jam_rawat DESC");
            try{
                ps.setString(1,norawat);
                ps.setString(2,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[] {
                        false,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
                    });
                }
            }catch(Exception ex){
                System.out.println(ex);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {   
        TCari.requestFocus();
    }
    
    public void setNoRawat(String norawat){
        this.norawat=norawat;
    }

    public JTable getTable(){
        return tbKamar;
    }
    
}
