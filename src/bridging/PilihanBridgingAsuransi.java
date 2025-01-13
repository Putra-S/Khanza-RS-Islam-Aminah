/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
* DlgKamar.java
*
* Created on May 23, 2010, 12:07:21 AM
 */
package bridging;

import fungsi.akses;
import java.awt.Cursor;
import java.util.logging.Logger;

/**
 * @author dosen
 */
public class PilihanBridgingAsuransi extends javax.swing.JDialog {

    private int lebar = 0, tinggi = 0;

    private String noka = "";

    /**
     * Creates new form DlgKamar
     *
     * @param parent
     * @param modal
     */
    public PilihanBridgingAsuransi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelGlass6 = new widget.panelisi();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        btnCekBPJSKartu = new widget.ButtonBig();
        btnCekBPJSRujukanKartuPCare = new widget.ButtonBig();
        btnCekBPJSRujukanKartuRS = new widget.ButtonBig();
        btnPCarePendaftaran = new widget.ButtonBig();
        btnCekEligibilitasInhealth = new widget.ButtonBig();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pilihan Bridging Asuransi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Tutup");
        BtnKeluar.setToolTipText("Alt+T");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnKeluar);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass7.setLayout(new java.awt.GridLayout(2, 0));

        btnCekBPJSKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSKartu.setText("Cek No.Kartu VClaim");
        btnCekBPJSKartu.setIconTextGap(0);
        btnCekBPJSKartu.setName("btnCekBPJSKartu"); // NOI18N
        btnCekBPJSKartu.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSKartuActionPerformed(evt);
            }
        });
        panelGlass7.add(btnCekBPJSKartu);

        btnCekBPJSRujukanKartuPCare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSRujukanKartuPCare.setText("Cek Rujukan Kartu PCare di VClaim");
        btnCekBPJSRujukanKartuPCare.setIconTextGap(0);
        btnCekBPJSRujukanKartuPCare.setName("btnCekBPJSRujukanKartuPCare"); // NOI18N
        btnCekBPJSRujukanKartuPCare.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSRujukanKartuPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSRujukanKartuPCareActionPerformed(evt);
            }
        });
        panelGlass7.add(btnCekBPJSRujukanKartuPCare);

        btnCekBPJSRujukanKartuRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/vclaim.png"))); // NOI18N
        btnCekBPJSRujukanKartuRS.setText("Cek Rujukan Kartu RS di VClaim");
        btnCekBPJSRujukanKartuRS.setIconTextGap(0);
        btnCekBPJSRujukanKartuRS.setName("btnCekBPJSRujukanKartuRS"); // NOI18N
        btnCekBPJSRujukanKartuRS.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekBPJSRujukanKartuRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekBPJSRujukanKartuRSActionPerformed(evt);
            }
        });
        panelGlass7.add(btnCekBPJSRujukanKartuRS);

        btnPCarePendaftaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pcare.png"))); // NOI18N
        btnPCarePendaftaran.setText("Data Pendaftaran PCare");
        btnPCarePendaftaran.setIconTextGap(0);
        btnPCarePendaftaran.setName("btnPCarePendaftaran"); // NOI18N
        btnPCarePendaftaran.setPreferredSize(new java.awt.Dimension(200, 90));
        btnPCarePendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCarePendaftaranActionPerformed(evt);
            }
        });
        panelGlass7.add(btnPCarePendaftaran);

        btnCekEligibilitasInhealth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/inhealth.png"))); // NOI18N
        btnCekEligibilitasInhealth.setText("Cek Eligibilitas Inhealth");
        btnCekEligibilitasInhealth.setIconTextGap(0);
        btnCekEligibilitasInhealth.setName("btnCekEligibilitasInhealth"); // NOI18N
        btnCekEligibilitasInhealth.setPreferredSize(new java.awt.Dimension(200, 90));
        btnCekEligibilitasInhealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekEligibilitasInhealthActionPerformed(evt);
            }
        });
        panelGlass7.add(btnCekEligibilitasInhealth);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void btnCekBPJSKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSKartuActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekKartu form = new BPJSCekKartu(null, false);
        form.isCek();
        form.SetNoKartu(noka);
        form.setSize(lebar, tinggi);
        form.setLocationRelativeTo(internalFrame1);
        this.dispose();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSKartuActionPerformed

    private void btnCekBPJSRujukanKartuPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSRujukanKartuPCareActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRujukanKartuPCare form = new BPJSCekRujukanKartuPCare(null, false);
        form.isCek();
        form.SetNoKartu(noka);
        form.setSize(lebar, tinggi);
        form.setLocationRelativeTo(internalFrame1);
        this.dispose();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSRujukanKartuPCareActionPerformed

    private void btnCekBPJSRujukanKartuRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekBPJSRujukanKartuRSActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BPJSCekRujukanKartuRS form = new BPJSCekRujukanKartuRS(null, false);
        form.isCek();
        form.SetNoKartu(noka);
        form.setSize(lebar, tinggi);
        form.setLocationRelativeTo(internalFrame1);
        this.dispose();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekBPJSRujukanKartuRSActionPerformed

    private void btnPCarePendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCarePendaftaranActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        PCareCekKartu form = new PCareCekKartu(null, false);
        form.isCek();
        form.SetNoKartu(noka);
        form.setSize(lebar, tinggi);
        form.setLocationRelativeTo(internalFrame1);
        this.dispose();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnPCarePendaftaranActionPerformed

    private void btnCekEligibilitasInhealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekEligibilitasInhealthActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InhealthCekEligibilitas form = new InhealthCekEligibilitas(null, false);
        form.isCek();
        form.SetNoKartu(noka);
        form.setSize(lebar, tinggi);
        form.setLocationRelativeTo(internalFrame1);
        this.dispose();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCekEligibilitasInhealthActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            PilihanBridgingAsuransi dialog = new PilihanBridgingAsuransi(new javax.swing.JFrame(), true);
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
    private widget.ButtonBig btnCekBPJSKartu;
    private widget.ButtonBig btnCekBPJSRujukanKartuPCare;
    private widget.ButtonBig btnCekBPJSRujukanKartuRS;
    private widget.ButtonBig btnCekEligibilitasInhealth;
    private widget.ButtonBig btnPCarePendaftaran;
    private widget.InternalFrame internalFrame1;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    // End of variables declaration//GEN-END:variables

    /**
     * @param tinggi
     */
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    /**
     * @param lebar
     */
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    /**
     * @param noka
     */
    public void setNoKa(String noka) {
        this.noka = noka;
    }

    public void isCek() {
        btnCekBPJSKartu.setEnabled(akses.getbpjs_cek_kartu());
        btnCekBPJSRujukanKartuPCare.setEnabled(akses.getbpjs_cek_rujukan_kartu_pcare());
        btnCekBPJSRujukanKartuRS.setEnabled(akses.getbpjs_cek_rujukan_kartu_rs());
        btnCekEligibilitasInhealth.setEnabled(akses.getinhealth_cek_eligibilitas());
        btnPCarePendaftaran.setEnabled(akses.getbridging_pcare_daftar());
    }

    private static final Logger LOG = Logger.getLogger(PilihanBridgingAsuransi.class.getName());

}
