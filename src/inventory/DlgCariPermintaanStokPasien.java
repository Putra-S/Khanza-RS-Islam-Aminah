package inventory;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgCariPermintaanStokPasien extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private String norm="",kddokter="";
    private final Properties prop = new Properties();
    
    /** Creates new form 
     * @param parent
     * @param modal */
    public DlgCariPermintaanStokPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Object[] row={"No.Permintaan","Tanggal","Jam","No.Rawat","No.RM","Pasien","Dokter Yang Meminta","Kode Dokter"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbPemisahan.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbPemisahan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemisahan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbPemisahan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(95);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(53);
            }else if(i==3){
                column.setPreferredWidth(105);
            }else if(i==4){
                column.setPreferredWidth(170);
            }else if(i==5){
                column.setPreferredWidth(1030);
            }else if(i==6){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemisahan.setDefaultRenderer(Object.class, new WarnaTable());
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
        panelisi1 = new widget.panelisi();
        ChkTanggal = new widget.CekBox();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbPemisahan = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Permintaan Stok Obat & BHP Medis Pasien Di Ranap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(55, 55));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkTanggal.setText("Tanggal :");
        ChkTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setName("ChkTanggal"); // NOI18N
        ChkTanggal.setOpaque(false);
        ChkTanggal.setPreferredSize(new java.awt.Dimension(80, 23));
        ChkTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTanggalItemStateChanged(evt);
            }
        });
        panelisi1.add(ChkTanggal);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2020" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(24, 23));
        panelisi1.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2020" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi1.add(DTPCari2);

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
        panelisi1.add(BtnCari);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(20, 23));
        panelisi1.add(jLabel7);

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

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPemisahan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPemisahan.setName("tbPemisahan"); // NOI18N
        scrollPane1.setViewportView(tbPemisahan);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
        }else{
            Valid.pindah(evt, DTPCari1,BtnKeluar);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){            
            dispose();              
        }else{Valid.pindah(evt,DTPCari1,BtnHapus);}
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void ChkTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTanggalItemStateChanged
        tampil();
    }//GEN-LAST:event_ChkTanggalItemStateChanged

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
        }else if(tbPemisahan.getSelectedRow()<= -1){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan pilih data permintaan dokter..!!");
        }else{
            if(tbPemisahan.getValueAt(tbPemisahan.getSelectedRow(),0).toString().isEmpty()){
                JOptionPane.showMessageDialog(rootPane,"Silahkan pilih No.Permintaan yang mau dihapus ..!!");
            }else {
                Sequel.meghapus("permintaan_stok_obat_pasien","no_permintaan",tbPemisahan.getValueAt(tbPemisahan.getSelectedRow(),0).toString()); 
                tampil();               
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPermintaanStokPasien dialog = new DlgCariPermintaanStokPasien(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.CekBox ChkTanggal;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel21;
    private widget.Label jLabel7;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbPemisahan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{  
            if(ChkTanggal.isSelected()==true){
                ps=koneksi.prepareStatement("select permintaan_stok_obat_pasien.no_permintaan,permintaan_stok_obat_pasien.tgl_permintaan,permintaan_stok_obat_pasien.jam,"+
                    " permintaan_stok_obat_pasien.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,permintaan_stok_obat_pasien.kd_dokter,dokter.nm_dokter, "+
                    " if(permintaan_stok_obat_pasien.jam=permintaan_stok_obat_pasien.jam,'Belum Terlayani','Sudah Terlayani') as status,permintaan_stok_obat_pasien.status as status_asal "+
                    " from permintaan_stok_obat_pasien inner join reg_periksa inner join pasien inner join dokter on permintaan_stok_obat_pasien.no_rawat=reg_periksa.no_rawat  "+
                    " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and permintaan_stok_obat_pasien.kd_dokter=dokter.kd_dokter where "+
                    " permintaan_stok_obat_pasien.tgl_permintaan between ? and ? and pasien.no_rkm_medis=? and permintaan_stok_obat_pasien.kd_dokter=? order by permintaan_stok_obat_pasien.tgl_permintaan,permintaan_stok_obat_pasien.jam desc");
            }else{
                ps=koneksi.prepareStatement("select permintaan_stok_obat_pasien.no_permintaan,permintaan_stok_obat_pasien.tgl_permintaan,permintaan_stok_obat_pasien.jam,"+
                    " permintaan_stok_obat_pasien.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,permintaan_stok_obat_pasien.kd_dokter,dokter.nm_dokter, "+
                    " if(permintaan_stok_obat_pasien.jam=permintaan_stok_obat_pasien.jam,'Belum Terlayani','Sudah Terlayani') as status,permintaan_stok_obat_pasien.status as status_asal "+
                    " from permintaan_stok_obat_pasien inner join reg_periksa inner join pasien inner join dokter on permintaan_stok_obat_pasien.no_rawat=reg_periksa.no_rawat  "+
                    " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and permintaan_stok_obat_pasien.kd_dokter=dokter.kd_dokter where "+
                    " pasien.no_rkm_medis=? and permintaan_stok_obat_pasien.kd_dokter=? order by permintaan_stok_obat_pasien.tgl_permintaan,permintaan_stok_obat_pasien.jam desc");
            }
            try{
                if(ChkTanggal.isSelected()==true){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(3,norm);
                    ps.setString(4,kddokter);
                }else{
                    ps.setString(1,norm);
                    ps.setString(2,kddokter);
                }                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_permintaan"),rs.getString("tgl_permintaan"),rs.getString("jam"),rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("nm_dokter"),rs.getString("kd_dokter")
                    });  
                    ps2=koneksi.prepareStatement("select databarang.kode_brng,databarang.nama_brng,detail_permintaan_stok_obat_pasien.jml,"+
                        "databarang.kode_sat,detail_permintaan_stok_obat_pasien.aturan_pakai,detail_permintaan_stok_obat_pasien.jam00,"+
                        "detail_permintaan_stok_obat_pasien.jam01,detail_permintaan_stok_obat_pasien.jam02,detail_permintaan_stok_obat_pasien.jam03,"+
                        "detail_permintaan_stok_obat_pasien.jam04,detail_permintaan_stok_obat_pasien.jam05,detail_permintaan_stok_obat_pasien.jam06,"+
                        "detail_permintaan_stok_obat_pasien.jam07,detail_permintaan_stok_obat_pasien.jam08,detail_permintaan_stok_obat_pasien.jam09,"+
                        "detail_permintaan_stok_obat_pasien.jam10,detail_permintaan_stok_obat_pasien.jam11,detail_permintaan_stok_obat_pasien.jam12,"+
                        "detail_permintaan_stok_obat_pasien.jam13,detail_permintaan_stok_obat_pasien.jam14,detail_permintaan_stok_obat_pasien.jam15,"+
                        "detail_permintaan_stok_obat_pasien.jam16,detail_permintaan_stok_obat_pasien.jam17,detail_permintaan_stok_obat_pasien.jam18,"+
                        "detail_permintaan_stok_obat_pasien.jam19,detail_permintaan_stok_obat_pasien.jam20,detail_permintaan_stok_obat_pasien.jam21,"+
                        "detail_permintaan_stok_obat_pasien.jam22,detail_permintaan_stok_obat_pasien.jam23 "+
                        "from detail_permintaan_stok_obat_pasien inner join databarang on "+
                        "detail_permintaan_stok_obat_pasien.kode_brng=databarang.kode_brng where detail_permintaan_stok_obat_pasien.no_permintaan=? order by databarang.kode_brng");
                    try {
                        ps2.setString(1,rs.getString("no_permintaan"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            tabMode.addRow(new String[]{
                                "",rs2.getString("jml"),rs2.getString("kode_sat"),rs2.getString("kode_brng"),rs2.getString("nama_brng"),
                                "00 : "+rs2.getString("jam00").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "01 : "+rs2.getString("jam01").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "02 : "+rs2.getString("jam02").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "03 : "+rs2.getString("jam03").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "04 : "+rs2.getString("jam04").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "05 : "+rs2.getString("jam05").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "06 : "+rs2.getString("jam06").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "07 : "+rs2.getString("jam07").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "08 : "+rs2.getString("jam08").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "09 : "+rs2.getString("jam09").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "10 : "+rs2.getString("jam10").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "11 : "+rs2.getString("jam11").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "12 : "+rs2.getString("jam12").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "13 : "+rs2.getString("jam13").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "14 : "+rs2.getString("jam14").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "15 : "+rs2.getString("jam15").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "16 : "+rs2.getString("jam16").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "17 : "+rs2.getString("jam17").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "18 : "+rs2.getString("jam18").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "19 : "+rs2.getString("jam19").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "20 : "+rs2.getString("jam20").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "21 : "+rs2.getString("jam21").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "22 : "+rs2.getString("jam22").replaceAll("true","V").replaceAll("false","X")+"  "+
                                "23 : "+rs2.getString("jam23").replaceAll("true","V").replaceAll("false","X")+"  |  "+
                                rs2.getString("aturan_pakai"),"",""
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi 2 : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                }                
            } catch(Exception ex){
                System.out.println("Notifikasi : "+ex);
            } finally{
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

    public void isCek(){
        BtnHapus.setEnabled(akses.getpermintaan_stok_obat_pasien());
    }
    
    public void setRM(String norm,String kodedokter){
        this.norm=norm;
        this.kddokter=kodedokter;
    }
}
