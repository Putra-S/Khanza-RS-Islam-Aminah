/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLihatPesan.java
 *
 * Created on Aug 6, 2010, 6:54:00 PM
 */

package smsui;

import fungsi.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author dosen3
 */
public class DlgLihatPesan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();

    /** Creates new form DlgLihatPesan
     * @param parent */
    public DlgLihatPesan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,10);
        setSize(545,600);

        Object[] row={"<html><font color='#AAAAAA' face='Tahoma'>Id",
                      "<html><font color='#AAAAAA' face='Tahoma'>SMS ",
                      "<html><font color='#AAAAAA' face='Tahoma'>No.HP",
                      "<html><font color='#AAAAAA' face='Tahoma'>PDU",
                      "<html><font color='#AAAAAA' face='Tahoma'>Encoding",
                      "<html><font color='#AAAAAA' face='Tahoma'>Id Gtateway",
                      "<html><font color='#AAAAAA' face='Tahoma'>Tanggal&Jam SMS",
                      "<html><font color='#AAAAAA' face='Tahoma'>SMS Balasan",
                      "<html><font color='#AAAAAA' face='Tahoma'>Stts.SMS"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPesan.setModel(tabMode);
        tbPesan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPesan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbPesan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(332);
            }else{
                column.setPreferredWidth(200);
            }
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

        panel1 = new widget.Panel();
        jLabel5 = new javax.swing.JLabel();
        button1 = new widget.Button();
        jSeparator2 = new javax.swing.JSeparator();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPesan = new widget.Table();
        panelJudul1 = new widget.PanelJudul();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panel1.setName("panel1"); // NOI18N
        panel1.setPreferredSize(new java.awt.Dimension(440, 27));
        panel1.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(180, 180, 180));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setName("jLabel5"); // NOI18N
        panel1.add(jLabel5, java.awt.BorderLayout.CENTER);

        button1.setForeground(new java.awt.Color(180, 180, 180));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smsimage/control_power.png"))); // NOI18N
        button1.setMnemonic('C');
        button1.setText("Close");
        button1.setToolTipText("Alt+C");
        button1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        button1.setName("button1"); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(100, 27));
        button1.setRoundRect(false);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panel1.add(button1, java.awt.BorderLayout.LINE_END);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 51));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 51));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 1));
        panel1.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panel1, java.awt.BorderLayout.PAGE_END);

        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(185, 185, 185)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPesan.setAutoCreateRowSorter(true);
        tbPesan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbPesan.setName("tbPesan"); // NOI18N
        Scroll.setViewportView(tbPesan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        panelJudul1.setName("panelJudul1"); // NOI18N
        panelJudul1.setPreferredSize(new java.awt.Dimension(440, 40));
        panelJudul1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(java.awt.Color.darkGray);
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(180, 180, 180));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":: DATA PESAN MASUK ::        ");
        jLabel1.setIconTextGap(2);
        jLabel1.setName("jLabel1"); // NOI18N
        panelJudul1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-32x32.png"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        panelJudul1.add(jLabel2, java.awt.BorderLayout.WEST);

        getContentPane().add(panelJudul1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        dispose();
}//GEN-LAST:event_button1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgLihatPesan dialog = new DlgLihatPesan(new javax.swing.JFrame(), true);
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
    private widget.ScrollPane Scroll;
    private widget.Button button1;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator2;
    private widget.Panel panel1;
    private widget.PanelJudul panelJudul1;
    private widget.Table tbPesan;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    public void tampil() {
        String sql="select * from sms";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try{
            java.sql.Statement stat=koneksi.createStatement();
            ResultSet rs=stat.executeQuery(sql);
            while(rs.next()){
                String[] data={rs.getString(1),
                               rs.getString(2),
                               rs.getString(3),
                               rs.getString(4),
                               rs.getString(5),
                               rs.getString(6),
                               rs.getString(7),
                               rs.getString(8),
                               rs.getString(9)};
                tabMode.addRow(data);
             }
            stat.close();
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
    }

}
