    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package grafikanalisa;

import fungsi.*;
import java.sql.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

/**
 *
 * @author dosen
 */
public class GrafikKejadianIKPPerTahun extends javax.swing.JDialog {
    private final Connection koneksi=koneksiDB.condb();
    private final validasi Valid=new validasi();
    private ResultSet rs;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public GrafikKejadianIKPPerTahun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        
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
        panelBiasa3 = new widget.PanelBiasa();
        panelGlass5 = new widget.panelisi();
        jLabel33 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        jLabel34 = new widget.Label();
        BtnPrint4 = new widget.Button();
        BtnPrint3 = new widget.Button();
        BtnPrint5 = new widget.Button();
        BtnKeluar3 = new widget.Button();

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Grafik Kejadian IKP Per Tahun ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50,50,50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(null);
        internalFrame1.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel33.setText("Periode Kejadian :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass5.add(jLabel33);

        Tanggal1.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-04-2019" }));
        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setOpaque(false);
        Tanggal1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(Tanggal1);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s.d");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(jLabel32);

        Tanggal2.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-04-2019" }));
        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.setOpaque(false);
        Tanggal2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(Tanggal2);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(jLabel34);

        BtnPrint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint4.setMnemonic('G');
        BtnPrint4.setText("Plot");
        BtnPrint4.setToolTipText("Alt+G");
        BtnPrint4.setName("BtnPrint4"); // NOI18N
        BtnPrint4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint4ActionPerformed(evt);
            }
        });
        BtnPrint4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint4KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint4);

        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint3.setMnemonic('G');
        BtnPrint3.setText("Batang");
        BtnPrint3.setToolTipText("Alt+G");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        BtnPrint3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint3KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint3);

        BtnPrint5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnPrint5.setMnemonic('G');
        BtnPrint5.setText("Pie");
        BtnPrint5.setToolTipText("Alt+G");
        BtnPrint5.setName("BtnPrint5"); // NOI18N
        BtnPrint5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint5ActionPerformed(evt);
            }
        });
        BtnPrint5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint5KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint5);

        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar3);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {                
            rs = koneksi.prepareStatement("select year(insiden_keselamatan_pasien.tgl_kejadian),count(year(insiden_keselamatan_pasien.tgl_kejadian)) as jumlah "+
                "from insiden_keselamatan_pasien where tgl_kejadian between '"+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(Tanggal2.getSelectedItem()+"")+"' group by year(insiden_keselamatan_pasien.tgl_kejadian)").executeQuery();
            while(rs.next()) {
                dcd.setValue(rs.getDouble(2),rs.getString(1)+"("+rs.getString(2)+")",rs.getString(1));
            }
            
            if(rs!=null){
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        JFreeChart freeChart = ChartFactory.createBarChart("Grafik Kejadian IKP Per Tahun Tanggal "+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(Tanggal2.getSelectedItem()+""),"Tahun","Jumlah Pasien", dcd, PlotOrientation.VERTICAL,true, true,true); 
        ChartFrame cf = new ChartFrame("Grafik Kejadian IKP Per Tahun",freeChart);
        cf.setSize(panelBiasa3.getWidth(),panelBiasa3.getHeight());   
        cf.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        cf.setLocationRelativeTo(panelBiasa3);
        cf.setAlwaysOnTop(true);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);  
    }//GEN-LAST:event_BtnPrint3ActionPerformed

    private void BtnPrint3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint3KeyPressed
        
    }//GEN-LAST:event_BtnPrint3KeyPressed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void BtnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint4ActionPerformed
       grafiksql2 kas=new grafiksql2("Grafik Kejadian IKP Per Tahun Tanggal "+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(Tanggal2.getSelectedItem()+""),
               "select year(insiden_keselamatan_pasien.tgl_kejadian),count(year(insiden_keselamatan_pasien.tgl_kejadian)) as jumlah from insiden_keselamatan_pasien "+
               "where tgl_kejadian between '"+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(Tanggal2.getSelectedItem()+"")+"' "+
               "group by year(insiden_keselamatan_pasien.tgl_kejadian)","Tahun");
       kas.setSize(panelBiasa3.getWidth(),panelBiasa3.getHeight());  
       kas.setModal(true);
       kas.setAlwaysOnTop(true);
       kas.setLocationRelativeTo(panelBiasa3);
       kas.setVisible(true);
    }//GEN-LAST:event_BtnPrint4ActionPerformed

    private void BtnPrint4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint4KeyPressed

    private void BtnPrint5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint5ActionPerformed
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {                
            rs = koneksi.prepareStatement("select year(insiden_keselamatan_pasien.tgl_kejadian),count(year(insiden_keselamatan_pasien.tgl_kejadian)) as jumlah "+
                "from insiden_keselamatan_pasien where tgl_kejadian between '"+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(Tanggal2.getSelectedItem()+"")+"' group by year(insiden_keselamatan_pasien.tgl_kejadian)").executeQuery();
            while(rs.next()) {
                dpd.setValue(rs.getString(1)+"("+rs.getString(2)+")",rs.getDouble(2));
            }
            
            if(rs!=null){
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        } 
        
        JFreeChart freeChart = ChartFactory.createPieChart("Grafik Kejadian IKP Per Tahun Tanggal "+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(Tanggal2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
        ChartFrame cf = new ChartFrame("Grafik Kejadian IKP Per Tahun",freeChart);
        cf.setSize(panelBiasa3.getWidth(),panelBiasa3.getHeight());   
        cf.setLocationRelativeTo(panelBiasa3);
        cf.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        cf.setAlwaysOnTop(true);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);  
    }//GEN-LAST:event_BtnPrint5ActionPerformed

    private void BtnPrint5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint5KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            GrafikKejadianIKPPerTahun dialog = new GrafikKejadianIKPPerTahun(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar3;
    private widget.Button BtnPrint3;
    private widget.Button BtnPrint4;
    private widget.Button BtnPrint5;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.PanelBiasa panelBiasa3;
    private widget.panelisi panelGlass5;
    // End of variables declaration//GEN-END:variables

}
