/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAdmin.java
 *
 * Created on 21 Jun 10, 20:53:44
 */

package setting;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public class DlgRunTeksApotek extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private DefaultTableModel tabmode2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();

    /** Creates new form DlgAdmin
     * @param parent */
    public DlgRunTeksApotek(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{"Teks Berjalan","Aktifkan?","Wallpaper"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbAdmin.setModel(tabMode);
        //tampil();
        //tbJabatan.setDefaultRenderer(Object.class, new WarnaTable(Scroll.getBackground(),Color.GREEN));
        tbAdmin.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbAdmin.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(300);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(200);
            }
        }

        tbAdmin.setDefaultRenderer(Object.class, new WarnaTable());
        TTeks.setDocument(new batasInput(1500).getKata(TTeks));
    }
    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private javax.swing.JFileChooser jfc = new JFileChooser();
    private FileFilter jpgFilter = new FileNameExtensionFilter("Gambar JPEG", "jpg");
    private FileFilter gifFilter = new FileNameExtensionFilter("Gambar GIF", "gif");
    private FileFilter pngFilter = new FileNameExtensionFilter("Gambar PNG", "png");
    private FileFilter bothFilter = new FileNameExtensionFilter("Gambar JPEG dan GIF dan PNG", "jpg", "gif", "png");

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
        tbAdmin = new widget.Table();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass1 = new widget.panelGlass();
        label35 = new widget.Label();
        label37 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        PhotoGambar = new Painter();
        BtnCariGb = new widget.Button();
        EGb = new widget.TextBox();
        label12 = new widget.Label();
        YesNo = new widget.ComboBox();
        Scroll2 = new widget.ScrollPane();
        TTeks = new widget.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Set Display Antrian Apotek ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setForeground(new java.awt.Color(50,50,50));
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAdmin.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAdmin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbAdmin.setName("tbAdmin"); // NOI18N
        tbAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAdminMouseClicked(evt);
            }
        });
        tbAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAdminKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAdmin);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 54));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnSimpan);

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
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnHapus);

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
        panelisi1.add(BtnEdit);

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

        panelGlass1.setName("panelGlass1"); // NOI18N
        panelGlass1.setPreferredSize(new java.awt.Dimension(200, 192));
        panelGlass1.setLayout(null);

        label35.setText("Teks Berjalan :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label35);
        label35.setBounds(0, 10, 88, 23);

        label37.setText("Display :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label37);
        label37.setBounds(0, 130, 88, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        PhotoGambar.setBackground(new java.awt.Color(235, 255, 235));
        PhotoGambar.setForeground(new java.awt.Color(232, 232, 172));
        PhotoGambar.setName("PhotoGambar"); // NOI18N
        scrollPane2.setViewportView(PhotoGambar);

        panelGlass1.add(scrollPane2);
        scrollPane2.setBounds(340, 10, 280, 170);

        BtnCariGb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariGb.setMnemonic('1');
        BtnCariGb.setToolTipText("Alt+1");
        BtnCariGb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariGb.setName("BtnCariGb"); // NOI18N
        BtnCariGb.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariGb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariGbActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnCariGb);
        BtnCariGb.setBounds(287, 130, 25, 23);

        EGb.setEditable(false);
        EGb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        EGb.setName("EGb"); // NOI18N
        EGb.setPreferredSize(new java.awt.Dimension(207, 23));
        EGb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EGbKeyPressed(evt);
            }
        });
        panelGlass1.add(EGb);
        EGb.setBounds(92, 130, 193, 23);

        label12.setText("Mau Aktifkan Wallpaper ?");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass1.add(label12);
        label12.setBounds(0, 160, 232, 23);

        YesNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        YesNo.setName("YesNo"); // NOI18N
        YesNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                YesNoKeyPressed(evt);
            }
        });
        panelGlass1.add(YesNo);
        YesNo.setBounds(242, 160, 70, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Scroll2.setForeground(new java.awt.Color(50,50,50));
        Scroll2.setName("Scroll2"); // NOI18N

        TTeks.setBorder(null);
        TTeks.setColumns(20);
        TTeks.setRows(5);
        TTeks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TTeks.setName("TTeks"); // NOI18N
        TTeks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTeksKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(TTeks);

        panelGlass1.add(Scroll2);
        Scroll2.setBounds(92, 10, 220, 110);

        internalFrame1.add(panelGlass1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TTeks.getText().trim().isEmpty()){
            Valid.textKosong(TTeks,"Teks");
        }else { 
            if(EGb.getText().trim().isEmpty()){
                Sequel.mengedit("runtextapotek","teks='"+TTeks.getText()+"'","aktifkan='"+YesNo.getSelectedItem()+"'");
            }else{
                Sequel.mengedit("runtextapotek","teks='"+TTeks.getText()+"'","aktifkan='"+YesNo.getSelectedItem()+"',gambar=?",EGb);
                
            }            
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void tbAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAdminMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAdminMouseClicked

    private void tbAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAdminKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAdminKeyPressed

    private void BtnCariGbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariGbActionPerformed
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(jpgFilter);
        jfc.addChoosableFileFilter(gifFilter);
        jfc.addChoosableFileFilter(pngFilter);
        jfc.addChoosableFileFilter(bothFilter);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc.getSelectedFile().toString();
            EGb.setText(f);
            //lGambar.setIcon(new ImageIcon(f));
            ((Painter) PhotoGambar).setImage(f);

        }
    }//GEN-LAST:event_BtnCariGbActionPerformed

    private void EGbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EGbKeyPressed
        Valid.pindah(evt,TTeks,BtnSimpan);
    }//GEN-LAST:event_EGbKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TTeks.getText().trim().isEmpty()){
            Valid.textKosong(TTeks,"Teks");
        }else if(EGb.getText().trim().isEmpty()){
            YesNo.setSelectedItem("No");
            EGb.setText("./setting/wallpaper.png");
        }else if(tabMode.getRowCount()==0){  
            Sequel.menyimpan("runtextapotek","'"+TTeks.getText()+"','"+YesNo.getSelectedItem()+"'","Display",EGb);
            tampil();
            emptTeks();
        }else if(tabMode.getRowCount()>0){
            JOptionPane.showMessageDialog(null,"Maaf, Hanya diijinkan satu runtext...!!!!");
            TTeks.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TTeks,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TTeks.requestFocus();
        }else if(TTeks.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        }else if(! TTeks.getText().trim().isEmpty()){
            Sequel.queryu("delete from runtextapotek ");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
          dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void YesNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YesNoKeyPressed
        Valid.pindah(evt, TTeks,BtnSimpan);
    }//GEN-LAST:event_YesNoKeyPressed

private void TTeksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTeksKeyPressed
        Valid.pindah(evt,BtnKeluar,EGb);
}//GEN-LAST:event_TTeksKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgRunTeksApotek dialog = new DlgRunTeksApotek(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnBatal;
    private widget.Button BtnCariGb;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.TextBox EGb;
    private java.awt.Canvas PhotoGambar;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.TextArea TTeks;
    private widget.ComboBox YesNo;
    private widget.InternalFrame internalFrame1;
    private widget.Label label12;
    private widget.Label label35;
    private widget.Label label37;
    private widget.panelGlass panelGlass1;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbAdmin;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        String sql="select teks, aktifkan, gambar from runtextapotek";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try{
            java.sql.Statement stat=koneksi.createStatement();
            ResultSet rs=stat.executeQuery(sql);
            while(rs.next()){
                Object[] data={rs.getString(1),rs.getString(2),rs.getString(3)};
                tabMode.addRow(data);
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
    }

    private void getData() {
        int row=tbAdmin.getSelectedRow();
        if(row!= -1){
            TTeks.setText(tabMode.getValueAt(row,0).toString());
            YesNo.setSelectedItem(tabMode.getValueAt(row,1).toString());
            try {
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select gambar from runtextapotek where teks='"+tabMode.getValueAt(row,0).toString()+"'");
                for (int I = 0; hasil.next(); I++) {
                    ((Painter) PhotoGambar).setImage(gambar(tabMode.getValueAt(row,0).toString()));
                    Blob blob = hasil.getBlob(1);
                    ((Painter) PhotoGambar).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                    blob.free();
                }
                hasil.close();
            } catch (Exception ex) {
                cetak(ex.toString());
            }
        }
    }

    public void emptTeks() {
        TTeks.setText("");
        ((Painter) PhotoGambar).setImage("");
        EGb.setText("");
        YesNo.setSelectedItem("No");
        TTeks.requestFocus();
    }
    
    
    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    private String folder;

    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (Exception ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }
        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            double d = image.getHeight(this) / this.getHeight();
            double w = image.getWidth(this) / d;
            double x = this.getWidth() / 2 - w / 2;
            g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
        }
    }
    
    private void cetak(String str) {
        System.out.println(str);
    }
    
}
