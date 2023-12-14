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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class MasterCariTemplateSOAPIE extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public MasterCariTemplateSOAPIE(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

        Object[] row={"No.Template","Kode Dokter","Nama Dokter","Subyek","Obyek","Asesmen","Planning","Instrukti","Evaluasi"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbKamar.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(205);
            }else if(i==4){
                column.setPreferredWidth(205);
            }else if(i==5){
                column.setPreferredWidth(205);
            }else if(i==6){
                column.setPreferredWidth(205);
            }else if(i==7){
                column.setPreferredWidth(205);
            }else if(i==8){
                column.setPreferredWidth(205);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
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
        label11 = new widget.Label();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnTambah = new widget.Button();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Master Template Hasil SOAPIE ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(462, 664));
        internalFrame1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(800, 664));

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        tbKamar.setPreferredScrollableViewportSize(new java.awt.Dimension(2000, 700));
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
        panelisi3.setPreferredSize(new java.awt.Dimension(44, 43));

        label11.setText("Dokter:");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label11);

        kddokter.setName("kddokter"); // NOI18N
        kddokter.setPreferredSize(new java.awt.Dimension(100, 24));
        panelisi3.add(kddokter);

        nmdokter.setName("nmdokter"); // NOI18N
        nmdokter.setPreferredSize(new java.awt.Dimension(200, 24));
        panelisi3.add(nmdokter);

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariActionPerformed(evt);
            }
        });
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

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Alt+3");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);

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
        tampil2();
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
        tampil2();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        MasterTemplateSOAPIE form=new MasterTemplateSOAPIE(null,false);
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setAlwaysOnTop(false);
        form.emptTeks();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());   
        
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
/*        try {
            if(Valid.daysOld("./cache/template_pemeriksaan_dokter.iyem")<8){
                tampil2();
            }else{
                tampil();
            }
        } catch (Exception e) {
        } */
    }//GEN-LAST:event_formWindowOpened

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

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked

    }//GEN-LAST:event_tbKamarMouseClicked

    private void TCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MasterCariTemplateSOAPIE dialog = new MasterCariTemplateSOAPIE(new javax.swing.JFrame(), true);
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
    private widget.Button BtnTambah;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.TextBox kddokter;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label9;
    private widget.TextBox nmdokter;
    private widget.panelisi panelisi3;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
      /*  Valid.tabelKosong(tabMode);
        try{
            file=new File("./cache/template_pemeriksaan_dokter.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem="";
            ps=koneksi.prepareStatement("select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,template_pemeriksaan_dokter.keluhan,"+
               "template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,template_pemeriksaan_dokter.evaluasi from template_pemeriksaan_dokter inner join dokter on template_pemeriksaan_dokter.kd_dokter=dokter.kd_dokter order by template_pemeriksaan_dokter.no_template");   
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)});
                      iyem=iyem+"{\"NoTemplate\":\""+rs.getString(1)+"\",\"KodeDokter\":\""+rs.getString(2)+"\",\"NamaDokter\":\""+rs.getString(3)+"\",\"Subyek\":\""+rs.getString(4)+"\",\"Obyek\":\""+rs.getString(5)+"\",\"Asesmen\":\""+rs.getString(6)+"\",\"Planning\":\""+rs.getString(7)+"\",\"Instruksi\":\""+rs.getString(8)+"\",\"Evaluasi\":\""+rs.getString(9)+"\"},";
               
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            fileWriter.write("{\"template_pemeriksaan_dokter\":["+iyem.substring(0,iyem.length()-1)+"]}");
            fileWriter.flush();
            fileWriter.close();
            iyem=null;
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }  
        LCount.setText(""+tabMode.getRowCount()); */
    }
    
/*    private void tampil2() {
        try {
            myObj = new FileReader("./cache/template_pemeriksaan_dokter.iyem");
            root = mapper.readTree(myObj);
            Valid.tabelKosong(tabMode);
            response = root.path("template_pemeriksaan_dokter");
            if(response.isArray()){
                for(JsonNode list:response){
                    
                    if(list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&
                       list.path("Subyek").asText().toLowerCase().contains(TCari.getText().toLowerCase())||
                       list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&list.path("Obyek").asText().toLowerCase().contains(TCari.getText().toLowerCase())||
                       list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&list.path("Asesmen").asText().toLowerCase().contains(TCari.getText().toLowerCase())||
                       list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&list.path("Planning").asText().toLowerCase().contains(TCari.getText().toLowerCase())||
                       list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&list.path("Instruksi").asText().toLowerCase().contains(TCari.getText().toLowerCase())||
                       list.path("KodeDokter").asText().toLowerCase().contains(kddokter.getText().toLowerCase())&&list.path("Evaluasi").asText().toLowerCase().contains(TCari.getText().toLowerCase())){
                       tabMode.addRow(new Object[]{
                            list.path("NoTemplate").asText(),list.path("KodeDokter").asText(),list.path("NamaDokter").asText(),
                            list.path("Subyek").asText(),list.path("Obyek").asText(),list.path("Asesmen").asText(),
                            list.path("Planning").asText(),list.path("Instruksi").asText(),list.path("Evaluasi").asText()
                        });                    
                    }
                }
            }
            myObj.close();
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
        LCount.setText(""+tabMode.getRowCount());
    } */
    
    private void tampil2(){
        Valid.tabelKosong(tabMode);
        try{
            if(kddokter.getText().isEmpty()){
            ps=koneksi.prepareStatement("select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,template_pemeriksaan_dokter.keluhan,"+
               "template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,"+
               "template_pemeriksaan_dokter.evaluasi from template_pemeriksaan_dokter inner join dokter on template_pemeriksaan_dokter.kd_dokter=dokter.kd_dokter "+
               "where template_pemeriksaan_dokter.penilaian like ? order by template_pemeriksaan_dokter.no_template");        
            }
            else{
               ps=koneksi.prepareStatement("select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,template_pemeriksaan_dokter.keluhan,"+
               "template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,"+
               "template_pemeriksaan_dokter.evaluasi from template_pemeriksaan_dokter inner join dokter on template_pemeriksaan_dokter.kd_dokter=dokter.kd_dokter "+
               "where template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.penilaian like ? "+
//               "template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.keluhan like ? or "+
//               "template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.pemeriksaan like ? or "+
//               "template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.rencana like ? or "+
//               "template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.instruksi like ? or "+
//               "template_pemeriksaan_dokter.kd_dokter like ? and template_pemeriksaan_dokter.evaluasi like ? "+
               "order by template_pemeriksaan_dokter.no_template desc");
            }
            
            try{
                if(kddokter.getText().isEmpty()){
                    ps.setString(1,"%"+TCari.getText().trim()+"%");
                } 
                else{
                    ps.setString(1,"%"+kddokter.getText().trim()+"%");
                    ps.setString(2,"%"+TCari.getText().trim()+"%");
//                    ps.setString(3,"%"+kddokter.getText().trim()+"%");
//                    ps.setString(4,"%"+TCari.getText().trim()+"%");
//                    ps.setString(5,"%"+kddokter.getText().trim()+"%");
//                    ps.setString(6,"%"+TCari.getText().trim()+"%");
//                    ps.setString(7,"%"+kddokter.getText().trim()+"%");
//                    ps.setString(8,"%"+TCari.getText().trim()+"%");
//                    ps.setString(9,"%"+kddokter.getText().trim()+"%");
//                    ps.setString(10,"%"+TCari.getText().trim()+"%");
//                    ps.setString(11,"%"+kddokter.getText().trim()+"%");
//                    ps.setString(12,"%"+TCari.getText().trim()+"%");
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_template"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("keluhan"),
                        rs.getString("pemeriksaan"),rs.getString("penilaian"),rs.getString("rencana"),rs.getString("instruksi"),
                        rs.getString("evaluasi")
                        });
            }}
            catch(Exception ex){
                System.out.println("Notifikasi : "+ex);
            }   finally{
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
    }    


    public void emptTeks() {
        TCari.requestFocus();
    }


    public JTable getTable(){
        return tbKamar;
    }
    
    public void isCek(){        
        BtnTambah.setEnabled(akses.gettemplate_pemeriksaan());
    }
    
    public void setRM(String kodedokter,String namadokter){
        kddokter.setText(kodedokter);
        nmdokter.setText(namadokter);
        tampil2();
    }
}
