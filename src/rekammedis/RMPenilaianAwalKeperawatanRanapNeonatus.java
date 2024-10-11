/*
  Dilarang keras memperjualbelikan/mengambil keuntungan dari Software 
  ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public class RMPenilaianAwalKeperawatanRanapNeonatus extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeMasalah, tabModeDetailMasalah, tabModeImunisasi, tabModeImunisasi2, tabModeRencana, tabModeDetailRencana;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2, ps3;
    private ResultSet rs, rs2, rs3;
    private int i = 0, jml = 0, index = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPegawai penolong = new DlgCariPegawai(null, false);
    private String pilih1 = "", pilih2 = "", pilih3 = "", pilih4 = "", pilih5 = "", pilih6 = "", pilih7 = "", pilih8 = "";
    private boolean[] pilih;
    private String[] kode, masalah;
    private String masalahkeperawatan = "", htmlke1 = "", htmlke2 = "", htmlke3 = "", htmlke4 = "", htmlke5 = "", htmlke6 = "", finger = "";
    private StringBuilder htmlContent;
    private MasterCariImunisasi imunisasi = new MasterCariImunisasi(null, false);
    private boolean ke1 = false, ke2 = false, ke3 = false, ke4 = false, ke5 = false, ke6 = false;
    private File file;
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    private String TANGGALMUNDUR = "yes";

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMPenilaianAwalKeperawatanRanapNeonatus(java.awt.Frame parent,
            boolean modal) {
        super(parent, modal);
        initComponents();

        DlgRiwayatImunisasi.setSize(465, 112);

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl.Lahir", "J.K.",
            "NIP Pengkaji 1", "Nama Pengkaji 1", "NIP Pengkaji 2",
            "Nama Pengkaji 2", "Kode DPJP",
            "Nama DPJP", "Tgl.Asuhan", "Macam Kasus", "Anamnesis",
            "Tiba Di Ruang Rawat", "Cara Masuk", "Keluhan Utama",
            "Riwayat Penyakit Saat Ini", "Riwayat Penyakit Dahulu",
            "Riwayat Penyakit Keluarga",
            "Riwayat Penggunaan Obat", "Alergi", "Kesadaran Mental",
            "Keadaan Umum", "TD(mmHg)", "Nadi(x/menit)", "SpO2(%)",
            "RR(x/menit)", "Suhu(°C)", "GCS(E,V,M)", "BB(Kg)",
            "TB(cm)", "LP(cm)", "LK(cm)", "LD(cm)", "B1 Nafas Spontan",
            "B1 Jenis", "B1 Alat Bantu", "B1 Ket. Alat Bantu", "B1 Irama",
            "B1 Suara Nafas", "B2 Irama Jantung",
            "B2 Acral", "B2 Tunggal", "B2 Conjungtiva Anemis", "B3 Kesadaran",
            "B3 Istirahat Tidur", "B3 Gangguan Tidur", "B3 Ket. Gangguan Tidur",
            "B3 Tingkat Kesadaran", "B3 Tangisan", "B3 Kepala",
            "B3 Kelainan", "B3 Ubun Ubun", "B3 Pupil", "B3 Skelera Mata",
            "B3 Gerakan", "B3 Panca Indra", "B3 Ket. Panca Indra", "B3 Kejang",
            "B3 Reflek Rooting", "B4 Kebersihan",
            "B4 Sekret", "B4 Ket. Sekret", "B4 Produksi Urine", "B4 Warna",
            "B4 Ket. Warna", "B4 Gangguan", "B4 Alat Bantu", "B5 Nafsu Makan",
            "B5 Frekuensi", "B5 Porsi Makan",
            "B5 Minum", "B5 Jenis", "B5 Cara Minum", "B5 Anus", "B5 BAB",
            "B5 Konsisten", "B5 Warna", "B5 Ket. Warna", "B5 Perut",
            "B5 Paristaltik",
            "B5 Reflek Rooting", "B5 Kelainan", "B5 Lidah", "B5 Selaput Lender",
            "B6 Pergerakan Sendi", "B6 Ket. Pergerakan Sendi", "B6 Warna Kulit",
            "B6 Integritas Kulit", "B6 Kepala", "B6 Tali Pusat",
            "B6 Tugor", "B6 Odem", "B6 Lokasi", "B6 Otot Kiri Atas",
            "B6 Otot Kanan Atas", "B6 Otot Kiri Bawah", "B6 Otot Kanan Bawah",
            "Genital Laki-Laki", "Genital Perempuan", "Derajat Ikterus",
            "Daerah Ikterus", "Kadar Bilirubin", "Apgar Score", "Down Score",
            "Anak Ke", "Dari", "Cara Kelahiran", "Ket.Cara Kelahiran",
            "Umur Kelahiran", "Kelainan Bawaan",
            "Ket.Kelainan Bawaan", "Warna Ketuban", "Kelainan Persalinan",
            "Usia Kehamilan", "Penolong", "Penolong Persalinan", "Tengkurap",
            "Duduk", "Berdiri", "Gigi Pertama", "Berjalan", "Bicara", "Membaca",
            "Menulis", "Gangguan Emosi",
            "Alat Bantu", "Ket.Alat Bantu", "Prothesa", "Ket.Prothesa", "ADL",
            "Status Psikologis", "Ket.Psikologis", "Hubungan Keluarga",
            "Pengasuh", "Ket.Pengasuh",
            "Ekonomi", "Budaya", "Ket.Budaya", "Edukasi", "Ket.Edukasi", "Agama",
            "Wajib", "Halangan Lain", "Toharah", "Sholat",
            "Motivasi Kesembuhan & Ibadah",
            "Humpty Dumpty 1", "N.S. 1", "Humpty Dumpty 2", "N.S. 2",
            "Humpty Dumpty 3",
            "N.S. 3", "Humpty Dumpty 4", "N.S. 4", "Humpty Dumpty 5", "N.S. 5",
            "Humpty Dumpty 6", "N.S. 6", "Humpty Dumpty 7", "N.S. 7", "T.S.",
            "S.G. 1", "N.G. 1", "S.G. 2", "N.G. 2", "S.G. 3", "N.G. 3", "S.G. 4",
            "N.G. 4", "T.S. Gizi", "Umur > 65 Tahun",
            "Keterbatasan mobilitas", "Perawatan atau pengobatan lanjutan",
            "Bantuan untuk melakukan aktifitas sehari-hari", "Perencanaan 1",
            "Perencanaan 2", "Perencanaan 3", "Perencanaan 4", "Perencanaan 5",
            "Perencanaan 6", "Perencanaan 7",
            "Perencanaan 8", "Skala Wajah", "N.S. Wajah", "Skala Tangisan",
            "N.S. Tangisan", "Skala Pola Napas", "N.S. Pola Napas",
            "Skala Lengan", "N.S. Lengan", "Skala Tungkai",
            "N.S. Tungkai", "Skala Aktivitas", "N.S. Aktivitas", "Skala Nyeri",
            "Rencana Keperawatan Lainnya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 198; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(90);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setPreferredWidth(100);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(180);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(180);
            } else if (i == 23) {
                column.setPreferredWidth(180);
            } else if (i == 24) {
                column.setPreferredWidth(100);
            } else if (i == 25) {
                column.setPreferredWidth(100);
            } else if (i == 26) {
                column.setPreferredWidth(100);
            } else if (i == 27) {
                column.setPreferredWidth(100);
            } else if (i == 28) {
                column.setPreferredWidth(130);
            } else if (i == 29) {
                column.setPreferredWidth(85);
            } else if (i == 30) {
                column.setPreferredWidth(90);
            } else if (i == 31) {
                column.setPreferredWidth(130);
            } else if (i == 32) {
                column.setPreferredWidth(70);
            } else if (i == 33) {
                column.setPreferredWidth(70);
            } else if (i == 34) {
                column.setPreferredWidth(100);
            } else if (i == 35) {
                column.setPreferredWidth(100);
            } else if (i == 36) {
                column.setPreferredWidth(100);
            } else if (i == 37) {
                column.setPreferredWidth(100);
            } else if (i == 38) {
                column.setPreferredWidth(100);
            } else if (i == 39) {
                column.setPreferredWidth(100);
            } else if (i == 40) {
                column.setPreferredWidth(120);
            } else if (i == 41) {
                column.setPreferredWidth(100);
            } else if (i == 42) {
                column.setPreferredWidth(120);
            } else if (i == 43) {
                column.setPreferredWidth(100);
            } else if (i == 44) {
                column.setPreferredWidth(120);
            } else if (i == 45) {
                column.setPreferredWidth(100);
            } else if (i == 46) {
                column.setPreferredWidth(90);
            } else if (i == 47) {
                column.setPreferredWidth(120);
            } else if (i == 48) {
                column.setPreferredWidth(110);
            } else if (i == 49) {
                column.setPreferredWidth(90);
            } else if (i == 50) {
                column.setPreferredWidth(120);
            } else if (i == 51) {
                column.setPreferredWidth(100);
            } else if (i == 52) {
                column.setPreferredWidth(100);
            } else if (i == 53) {
                column.setPreferredWidth(120);
            } else if (i == 54) {
                column.setPreferredWidth(100);
            } else if (i == 55) {
                column.setPreferredWidth(120);
            } else if (i == 56) {
                column.setPreferredWidth(100);
            } else if (i == 57) {
                column.setPreferredWidth(100);
            } else if (i == 58) {
                column.setPreferredWidth(115);
            } else if (i == 59) {
                column.setPreferredWidth(190);
            } else if (i == 60) {
                column.setPreferredWidth(100);
            } else if (i == 61) {
                column.setPreferredWidth(65);
            } else if (i == 62) {
                column.setPreferredWidth(100);
            } else if (i == 63) {
                column.setPreferredWidth(100);
            } else if (i == 64) {
                column.setPreferredWidth(100);
            } else if (i == 65) {
                column.setPreferredWidth(100);
            } else if (i == 66) {
                column.setPreferredWidth(100);
            } else if (i == 67) {
                column.setPreferredWidth(40);
            } else if (i == 68) {
                column.setPreferredWidth(100);
            } else if (i == 69) {
                column.setPreferredWidth(100);
            } else if (i == 70) {
                column.setPreferredWidth(100);
            } else if (i == 71) {
                column.setPreferredWidth(180);
            } else if (i == 72) {
                column.setPreferredWidth(62);
            } else if (i == 73) {
                column.setPreferredWidth(170);
            } else if (i == 74) {
                column.setPreferredWidth(55);
            } else if (i == 75) {
                column.setPreferredWidth(180);
            } else if (i == 76) {
                column.setPreferredWidth(70);
            } else if (i == 77) {
                column.setPreferredWidth(170);
            } else if (i == 78) {
                column.setPreferredWidth(75);
            } else if (i == 79) {
                column.setPreferredWidth(170);
            } else if (i == 80) {
                column.setPreferredWidth(75);
            } else if (i == 81) {
                column.setPreferredWidth(65);
            } else if (i == 82) {
                column.setPreferredWidth(85);
            } else if (i == 83) {
                column.setPreferredWidth(110);
            } else if (i == 84) {
                column.setPreferredWidth(65);
            } else if (i == 85) {
                column.setPreferredWidth(65);
            } else if (i == 86) {
                column.setPreferredWidth(105);
            } else if (i == 87) {
                column.setPreferredWidth(110);
            } else if (i == 88) {
                column.setPreferredWidth(110);
            } else if (i == 89) {
                column.setPreferredWidth(100);
            } else if (i == 90) {
                column.setPreferredWidth(250);
            } else if (i == 91) {
                column.setPreferredWidth(90);
            } else if (i == 92) {
                column.setPreferredWidth(150);
            } else if (i == 93) {
                column.setPreferredWidth(65);
            } else if (i == 94) {
                column.setPreferredWidth(160);
            } else if (i == 95) {
                column.setPreferredWidth(50);
            } else if (i == 96) {
                column.setPreferredWidth(60);
            } else if (i == 97) {
                column.setPreferredWidth(90);
            } else if (i == 98) {
                column.setPreferredWidth(90);
            } else if (i == 99) {
                column.setPreferredWidth(65);
            } else if (i == 100) {
                column.setPreferredWidth(120);
            } else if (i == 101) {
                column.setPreferredWidth(90);
            } else if (i == 102) {
                column.setPreferredWidth(100);
            } else if (i == 103) {
                column.setPreferredWidth(10);
            } else if (i == 104) {
                column.setPreferredWidth(100);
            } else if (i == 105) {
                column.setPreferredWidth(100);
            } else if (i == 106) {
                column.setPreferredWidth(100);
            } else if (i == 107) {
                column.setPreferredWidth(100);
            } else if (i == 108) {
                column.setPreferredWidth(100);
            } else if (i == 109) {
                column.setPreferredWidth(100);
            } else if (i == 110) {
                column.setPreferredWidth(100);
            } else if (i == 111) {
                column.setPreferredWidth(100);
            } else if (i == 112) {
                column.setPreferredWidth(180);
            } else if (i == 113) {
                column.setPreferredWidth(180);
            } else if (i == 114) {
                column.setPreferredWidth(180);
            } else if (i == 115) {
                column.setPreferredWidth(180);
            } else if (i == 116) {
                column.setPreferredWidth(100);
            } else if (i == 117) {
                column.setPreferredWidth(100);
            } else if (i == 118) {
                column.setPreferredWidth(100);
            } else if (i == 119) {
                column.setPreferredWidth(80);
            } else if (i == 120) {
                column.setPreferredWidth(130);
            } else if (i == 121) {
                column.setPreferredWidth(85);
            } else if (i == 122) {
                column.setPreferredWidth(90);
            } else if (i == 123) {
                column.setPreferredWidth(130);
            } else if (i == 124) {
                column.setPreferredWidth(70);
            } else if (i == 125) {
                column.setPreferredWidth(70);
            } else if (i == 126) {
                column.setPreferredWidth(70);
            } else if (i == 127) {
                column.setPreferredWidth(70);
            } else if (i == 128) {
                column.setPreferredWidth(70);
            } else if (i == 129) {
                column.setPreferredWidth(70);
            } else if (i == 130) {
                column.setPreferredWidth(70);
            } else if (i == 131) {
                column.setPreferredWidth(70);
            } else if (i == 132) {
                column.setPreferredWidth(120);
            } else if (i == 133) {
                column.setPreferredWidth(60);
            } else if (i == 134) {
                column.setPreferredWidth(120);
            } else if (i == 135) {
                column.setPreferredWidth(60);
            } else if (i == 136) {
                column.setPreferredWidth(120);
            } else if (i == 137) {
                column.setPreferredWidth(55);
            } else if (i == 138) {
                column.setPreferredWidth(90);
            } else if (i == 139) {
                column.setPreferredWidth(120);
            } else if (i == 140) {
                column.setPreferredWidth(110);
            } else if (i == 141) {
                column.setPreferredWidth(90);
            } else if (i == 142) {
                column.setPreferredWidth(120);
            } else if (i == 143) {
                column.setPreferredWidth(55);
            } else if (i == 144) {
                column.setPreferredWidth(55);
            } else if (i == 145) {
                column.setPreferredWidth(120);
            } else if (i == 146) {
                column.setPreferredWidth(60);
            } else if (i == 147) {
                column.setPreferredWidth(120);
            } else if (i == 148) {
                column.setPreferredWidth(100);
            } else if (i == 149) {
                column.setPreferredWidth(100);
            } else if (i == 150) {
                column.setPreferredWidth(115);
            } else if (i == 151) {
                column.setPreferredWidth(190);
            } else if (i == 152) {
                column.setPreferredWidth(100);
            } else if (i == 153) {
                column.setPreferredWidth(65);
            } else if (i == 154) {
                column.setPreferredWidth(40);
            } else if (i == 155) {
                column.setPreferredWidth(40);
            } else if (i == 156) {
                column.setPreferredWidth(40);
            } else if (i == 157) {
                column.setPreferredWidth(40);
            } else if (i == 158) {
                column.setPreferredWidth(40);
            } else if (i == 159) {
                column.setPreferredWidth(40);
            } else if (i == 160) {
                column.setPreferredWidth(40);
            } else if (i == 161) {
                column.setPreferredWidth(40);
            } else if (i == 162) {
                column.setPreferredWidth(50);
            } else if (i == 163) {
                column.setPreferredWidth(180);
            } else if (i == 164) {
                column.setPreferredWidth(62);
            } else if (i == 165) {
                column.setPreferredWidth(170);
            } else if (i == 166) {
                column.setPreferredWidth(55);
            } else if (i == 167) {
                column.setPreferredWidth(180);
            } else if (i == 168) {
                column.setPreferredWidth(70);
            } else if (i == 169) {
                column.setPreferredWidth(170);
            } else if (i == 170) {
                column.setPreferredWidth(75);
            } else if (i == 171) {
                column.setPreferredWidth(170);
            } else if (i == 172) {
                column.setPreferredWidth(75);
            } else if (i == 173) {
                column.setPreferredWidth(65);
            } else if (i == 174) {
                column.setPreferredWidth(85);
            } else if (i == 175) {
                column.setPreferredWidth(110);
            } else if (i == 176) {
                column.setPreferredWidth(65);
            } else if (i == 177) {
                column.setPreferredWidth(65);
            } else if (i == 178) {
                column.setPreferredWidth(105);
            } else if (i == 179) {
                column.setPreferredWidth(110);
            } else if (i == 180) {
                column.setPreferredWidth(110);
            } else if (i == 181) {
                column.setPreferredWidth(100);
            } else if (i == 182) {
                column.setPreferredWidth(250);
            } else if (i == 183) {
                column.setPreferredWidth(90);
            } else if (i == 184) {
                column.setPreferredWidth(150);
            } else if (i == 185) {
                column.setPreferredWidth(110);
            } else if (i == 186) {
                column.setPreferredWidth(100);
            } else if (i == 187) {
                column.setPreferredWidth(250);
            } else if (i == 188) {
                column.setPreferredWidth(90);
            } else if (i == 189) {
                column.setPreferredWidth(150);
            } else if (i == 190) {
                column.setPreferredWidth(150);
            } else if (i == 191) {
                column.setPreferredWidth(150);
            } else if (i == 192) {
                column.setPreferredWidth(100);
            } else if (i == 193) {
                column.setPreferredWidth(250);
            } else if (i == 194) {
                column.setPreferredWidth(90);
            } else if (i == 195) {
                column.setPreferredWidth(150);
            } else if (i == 196) {
                column.setPreferredWidth(150);
            } else if (i == 197) {
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeMasalah = new DefaultTableModel(null, new Object[]{
            "P", "KODE", "MASALAH KEPERAWATAN"
        }) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Double.class
            };

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
        tbMasalahKeperawatan.setModel(tabModeMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahKeperawatan.setPreferredScrollableViewportSize(new Dimension(
                500, 500));
        tbMasalahKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbMasalahKeperawatan.getColumnModel().
                    getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }
        tbMasalahKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailMasalah = new DefaultTableModel(null, new Object[]{
            "Kode", "Masalah Keperawatan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbMasalahDetailMasalah.setModel(tabModeDetailMasalah);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahDetailMasalah.setPreferredScrollableViewportSize(new Dimension(
                500, 500));
        tbMasalahDetailMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasalahDetailMasalah.getColumnModel().
                    getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbMasalahDetailMasalah.
                setDefaultRenderer(Object.class, new WarnaTable());

        tabModeImunisasi = new DefaultTableModel(null, new Object[]{
            "Kode", "Nama Imunisasi", "Ke 1", "Ke 2", "Ke 3", "Ke 4", "Ke 5",
            "Ke 6"
        }) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbImunisasi.setModel(tabModeImunisasi);
        tbImunisasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbImunisasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbImunisasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else {
                column.setPreferredWidth(50);
            }
        }
        tbImunisasi.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeImunisasi2 = new DefaultTableModel(null, new Object[]{
            "Kode", "Nama Imunisasi", "Ke 1", "Ke 2", "Ke 3", "Ke 4", "Ke 5",
            "Ke 6"
        }) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

        };
        tbImunisasi2.setModel(tabModeImunisasi2);
        tbImunisasi2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbImunisasi2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbImunisasi2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(205);
            } else {
                column.setPreferredWidth(35);
            }
        }
        tbImunisasi2.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRencana = new DefaultTableModel(null, new Object[]{
            "P", "KODE", "RENCANA KEPERAWATAN"
        }) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Double.class
            };

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
        tbRencanaKeperawatan.setModel(tabModeRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaKeperawatan.setPreferredScrollableViewportSize(new Dimension(
                500, 500));
        tbRencanaKeperawatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaKeperawatan.getColumnModel().
                    getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }
        tbRencanaKeperawatan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailRencana = new DefaultTableModel(null, new Object[]{
            "Kode", "Rencana Keperawatan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbRencanaDetail.setModel(tabModeDetailRencana);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaDetail.setPreferredScrollableViewportSize(new Dimension(500,
                500));
        tbRencanaDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbRencanaDetail.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbRencanaDetail.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TD.setDocument(new batasInput((byte) 8).getKata(TD));
        Nadi.setDocument(new batasInput((byte) 5).getKata(Nadi));
        RR.setDocument(new batasInput((byte) 5).getKata(RR));
        Suhu.setDocument(new batasInput((byte) 5).getKata(Suhu));
        GCS.setDocument(new batasInput((byte) 5).getKata(GCS));
        BB.setDocument(new batasInput((byte) 5).getKata(BB));
        TB.setDocument(new batasInput((byte) 5).getKata(TB));
        LP.setDocument(new batasInput((byte) 5).getKata(LP));
        LK.setDocument(new batasInput((byte) 5).getKata(LK));
        LD.setDocument(new batasInput((byte) 5).getKata(LD));
        KeluhanUtama.setDocument(new batasInput(150).getKata(KeluhanUtama));
        RPD.setDocument(new batasInput(100).getKata(RPD));
        RPK.setDocument(new batasInput(100).getKata(RPK));
        RPO.setDocument(new batasInput(100).getKata(RPO));
        Alergi.setDocument(new batasInput(25).getKata(Alergi));
        Anakke.setDocument(new batasInput((byte) 2).getKata(Anakke));
        DariSaudara.setDocument(new batasInput((byte) 2).getKata(DariSaudara));
        KetCaraKelahiran.setDocument(new batasInput((byte) 30).getKata(
                KetCaraKelahiran));
        KetKelainanBawaan.setDocument(new batasInput((byte) 30).getKata(
                KetKelainanBawaan));
        UsiaTengkurap.setDocument(new batasInput((byte) 15).getKata(
                UsiaTengkurap));
        UsiaDuduk.setDocument(new batasInput((byte) 15).getKata(UsiaDuduk));
        UsiaBerdiri.setDocument(new batasInput((byte) 15).getKata(UsiaBerdiri));
        UsiaGigi.setDocument(new batasInput((byte) 15).getKata(UsiaGigi));
        UsiaBerjalan.
                setDocument(new batasInput((byte) 15).getKata(UsiaBerjalan));
        UsiaBicara.setDocument(new batasInput((byte) 15).getKata(UsiaBicara));
        UsiaMembaca.setDocument(new batasInput((byte) 15).getKata(UsiaMembaca));
        UsiaMenulis.setDocument(new batasInput((byte) 15).getKata(UsiaMenulis));
        GangguanEmosi.setDocument(new batasInput(50).getKata(GangguanEmosi));
        KetBantu.setDocument(new batasInput(50).getKata(KetBantu));
        KetProthesa.setDocument(new batasInput(50).getKata(KetProthesa));
        KetBudaya.setDocument(new batasInput(50).getKata(KetBudaya));
        KetPsiko.setDocument(new batasInput(70).getKata(KetPsiko));
        KetPengasuh.setDocument(new batasInput(40).getKata(KetPengasuh));
        KetEdukasi.setDocument(new batasInput(50).getKata(KetEdukasi));
        Rencana.setDocument(new batasInput(200).getKata(Rencana));

        TCari.setDocument(new batasInput(100).getKata(TCari));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(
                    new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

            });

            TCariMasalah.getDocument().addDocumentListener(
                    new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCariMasalah.getText().length() > 2) {
                        tampilMasalah2();
                    }
                }

            });
        }

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    if (i == 1) {
                        KdPetugas.setText(petugas.getTable().getValueAt(petugas.
                                getTable().getSelectedRow(), 0).toString());
                        NmPetugas.setText(petugas.getTable().getValueAt(petugas.
                                getTable().getSelectedRow(), 1).toString());
                    } else {
                        KdPetugas2.setText(petugas.getTable().getValueAt(
                                petugas.getTable().getSelectedRow(), 0).
                                toString());
                        NmPetugas2.setText(petugas.getTable().getValueAt(
                                petugas.getTable().getSelectedRow(), 1).
                                toString());
                    }

                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.
                            getTable().getSelectedRow(), 0).toString());
                    NmDPJP.setText(dokter.getTable().getValueAt(dokter.
                            getTable().getSelectedRow(), 1).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        penolong.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penolong.getTable().getSelectedRow() != -1) {
//                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    PenolongPersalinan.setText(penolong.getTable().getValueAt(
                            penolong.getTable().getSelectedRow(), 1).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        imunisasi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (imunisasi.getTable().getSelectedRow() != -1) {
                    KdImunisasi.setText(imunisasi.getTable().getValueAt(
                            imunisasi.getTable().getSelectedRow(), 0).toString());
                    NmImunisasi.setText(imunisasi.getTable().getValueAt(
                            imunisasi.getTable().getSelectedRow(), 1).toString());
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });

        imunisasi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    imunisasi.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);

        try {
            TANGGALMUNDUR = koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR = "yes";
        }

        ChkAccor.setSelected(false);
        isMenu();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TanggalRegistrasi = new widget.TextBox();
        LoadHTML = new widget.editorpane();
        DlgRiwayatImunisasi = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        jLabel99 = new widget.Label();
        BtnKeluarImunisasi = new widget.Button();
        BtnSimpanImunisasi = new widget.Button();
        BtnImunisasi = new widget.Button();
        NmImunisasi = new widget.TextBox();
        KdImunisasi = new widget.TextBox();
        jLabel43 = new widget.Label();
        ImunisasiKe = new widget.ComboBox();
        BtnHapusImunisasi = new widget.Button();
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel16 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel25 = new widget.Label();
        RR = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel24 = new widget.Label();
        TB = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel29 = new widget.Label();
        LK = new widget.TextBox();
        jLabel30 = new widget.Label();
        BB = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        LP = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel37 = new widget.Label();
        LD = new widget.TextBox();
        jLabel38 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel39 = new widget.Label();
        Alergi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel40 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        RPD = new widget.TextArea();
        jLabel41 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        RPK = new widget.TextArea();
        jLabel42 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RPO = new widget.TextArea();
        jLabel94 = new widget.Label();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel95 = new widget.Label();
        jLabel44 = new widget.Label();
        Anakke = new widget.TextBox();
        DariSaudara = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel55 = new widget.Label();
        CaraKelahiran = new widget.ComboBox();
        KetCaraKelahiran = new widget.TextBox();
        KelainanBawaan = new widget.ComboBox();
        jLabel56 = new widget.Label();
        KetKelainanBawaan = new widget.TextBox();
        UmurKelahiran = new widget.ComboBox();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel96 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        tbImunisasi = new widget.Table();
        BtnTambahImunisasi = new widget.Button();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel97 = new widget.Label();
        jLabel59 = new widget.Label();
        UsiaTengkurap = new widget.TextBox();
        jLabel60 = new widget.Label();
        UsiaDuduk = new widget.TextBox();
        jLabel61 = new widget.Label();
        UsiaBerdiri = new widget.TextBox();
        jLabel62 = new widget.Label();
        UsiaGigi = new widget.TextBox();
        jLabel63 = new widget.Label();
        UsiaBerjalan = new widget.TextBox();
        jLabel64 = new widget.Label();
        UsiaBicara = new widget.TextBox();
        jLabel65 = new widget.Label();
        UsiaMembaca = new widget.TextBox();
        UsiaMenulis = new widget.TextBox();
        jLabel66 = new widget.Label();
        GangguanEmosi = new widget.TextBox();
        jLabel67 = new widget.Label();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel125 = new widget.Label();
        jLabel127 = new widget.Label();
        AlatBantu = new widget.ComboBox();
        KetBantu = new widget.TextBox();
        Prothesa = new widget.ComboBox();
        KetProthesa = new widget.TextBox();
        jLabel128 = new widget.Label();
        ADL = new widget.ComboBox();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        CacatFisik = new widget.TextBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel131 = new widget.Label();
        StatusPsiko = new widget.ComboBox();
        KetPsiko = new widget.TextBox();
        jLabel132 = new widget.Label();
        Bahasa = new widget.TextBox();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        HubunganKeluarga = new widget.ComboBox();
        Pengasuh = new widget.ComboBox();
        KetPengasuh = new widget.TextBox();
        jLabel136 = new widget.Label();
        Ekonomi = new widget.ComboBox();
        jLabel137 = new widget.Label();
        jLabel138 = new widget.Label();
        StatusBudaya = new widget.ComboBox();
        KetBudaya = new widget.TextBox();
        jLabel139 = new widget.Label();
        Edukasi = new widget.ComboBox();
        KetEdukasi = new widget.TextBox();
        jLabel140 = new widget.Label();
        Agama = new widget.TextBox();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel141 = new widget.Label();
        jLabel149 = new widget.Label();
        jSeparator10 = new javax.swing.JSeparator();
        SG1 = new widget.ComboBox();
        jLabel150 = new widget.Label();
        jLabel152 = new widget.Label();
        SG2 = new widget.ComboBox();
        jLabel154 = new widget.Label();
        jLabel155 = new widget.Label();
        jLabel156 = new widget.Label();
        SG3 = new widget.ComboBox();
        jLabel158 = new widget.Label();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        SkalaWajah = new widget.ComboBox();
        jLabel162 = new widget.Label();
        NilaiGizi1 = new widget.TextBox();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        jLabel167 = new widget.Label();
        jLabel168 = new widget.Label();
        jLabel169 = new widget.Label();
        SG4 = new widget.ComboBox();
        NilaiGizi2 = new widget.TextBox();
        NilaiGizi3 = new widget.TextBox();
        NilaiGizi4 = new widget.TextBox();
        NilaiWajah = new widget.TextBox();
        TotalNilaiGizi = new widget.TextBox();
        SkalaTangisan = new widget.ComboBox();
        NilaiTangisan = new widget.TextBox();
        SkalaPolaNapas = new widget.ComboBox();
        NilaiPolaNapas = new widget.TextBox();
        SkalaLengan = new widget.ComboBox();
        NilaiLengan = new widget.TextBox();
        SkalaTungkai = new widget.ComboBox();
        NilaiTungkai = new widget.TextBox();
        jLabel170 = new widget.Label();
        SkalaNyeri = new widget.TextBox();
        jSeparator13 = new javax.swing.JSeparator();
        Scroll8 = new widget.ScrollPane();
        tbMasalahKeperawatan = new widget.Table();
        BtnPanggilHapusImunisasi = new widget.Button();
        TabRencanaKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa1 = new widget.PanelBiasa();
        Scroll9 = new widget.ScrollPane();
        tbRencanaKeperawatan = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        label13 = new widget.Label();
        TCariRencana = new widget.TextBox();
        BtnCariRencana = new widget.Button();
        BtnAllRencana = new widget.Button();
        BtnTambahRencana = new widget.Button();
        label12 = new widget.Label();
        TCariMasalah = new widget.TextBox();
        BtnCariMasalah = new widget.Button();
        BtnAllMasalah = new widget.Button();
        BtnTambahMasalah = new widget.Button();
        jLabel47 = new widget.Label();
        jLabel98 = new widget.Label();
        jLabel48 = new widget.Label();
        KesadaranMental = new widget.TextBox();
        jLabel151 = new widget.Label();
        KeadaanMentalUmum = new widget.ComboBox();
        jLabel73 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel153 = new widget.Label();
        jLabel157 = new widget.Label();
        B1Irama = new widget.ComboBox();
        B1NafasSpontan = new widget.ComboBox();
        jLabel161 = new widget.Label();
        B1SuaraNafas = new widget.ComboBox();
        B1KetO2Nafas = new widget.TextBox();
        jLabel171 = new widget.Label();
        B1JenisNafas = new widget.ComboBox();
        B1O2Nafas = new widget.TextBox();
        jLabel172 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel173 = new widget.Label();
        B2IramaJantung = new widget.ComboBox();
        jLabel174 = new widget.Label();
        B2Acral = new widget.ComboBox();
        jLabel175 = new widget.Label();
        B2ConjungtivaAnemis = new widget.ComboBox();
        jLabel78 = new widget.Label();
        jLabel176 = new widget.Label();
        jLabel177 = new widget.Label();
        B3TingkatKesadaran = new widget.ComboBox();
        B3Kesadaran = new widget.ComboBox();
        jLabel178 = new widget.Label();
        jLabel179 = new widget.Label();
        B3KetGangguanTidur = new widget.ComboBox();
        B3Tangisan = new widget.ComboBox();
        jLabel180 = new widget.Label();
        jLabel181 = new widget.Label();
        jLabel182 = new widget.Label();
        B3Kelainan = new widget.ComboBox();
        scrollPane8 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jLabel85 = new widget.Label();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        Kriteria1 = new widget.ComboBox();
        Kriteria2 = new widget.ComboBox();
        Kriteria3 = new widget.ComboBox();
        Kriteria4 = new widget.ComboBox();
        jLabel100 = new widget.Label();
        pilihan1 = new javax.swing.JCheckBox();
        pilihan2 = new javax.swing.JCheckBox();
        pilihan3 = new javax.swing.JCheckBox();
        pilihan4 = new javax.swing.JCheckBox();
        pilihan5 = new javax.swing.JCheckBox();
        pilihan6 = new javax.swing.JCheckBox();
        pilihan7 = new javax.swing.JCheckBox();
        pilihan8 = new javax.swing.JCheckBox();
        jLabel289 = new widget.Label();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel206 = new widget.Label();
        jLabel207 = new widget.Label();
        jLabel208 = new widget.Label();
        B2S1S2 = new widget.ComboBox();
        jLabel209 = new widget.Label();
        B3JamIstirahatTidur = new widget.TextBox();
        jLabel183 = new widget.Label();
        B3GangguanTidur = new widget.TextBox();
        jLabel184 = new widget.Label();
        B3LingkarKepala = new widget.TextBox();
        jLabel185 = new widget.Label();
        jLabel186 = new widget.Label();
        B3UbunUbun = new widget.ComboBox();
        jLabel210 = new widget.Label();
        jLabel187 = new widget.Label();
        B3Pupil = new widget.ComboBox();
        jLabel188 = new widget.Label();
        B3SkleraMata = new widget.ComboBox();
        B3Gerakan = new widget.ComboBox();
        jLabel189 = new widget.Label();
        jLabel190 = new widget.Label();
        B3PancaIndra = new widget.ComboBox();
        jLabel192 = new widget.Label();
        B3Kejang = new widget.ComboBox();
        jLabel193 = new widget.Label();
        B3ReflekRooting = new widget.ComboBox();
        B3KetPancaIndra = new widget.TextBox();
        jLabel79 = new widget.Label();
        jLabel191 = new widget.Label();
        B4Kebersihan = new widget.ComboBox();
        jLabel194 = new widget.Label();
        B4ProduksiUrine = new widget.TextBox();
        B4KetSekret = new widget.TextBox();
        jLabel211 = new widget.Label();
        jLabel197 = new widget.Label();
        B4Sekret = new widget.ComboBox();
        jLabel198 = new widget.Label();
        B4Warna = new widget.ComboBox();
        jLabel203 = new widget.Label();
        B4Gangguan = new widget.ComboBox();
        jLabel214 = new widget.Label();
        B4AlatBantu = new widget.ComboBox();
        B4KetWarna = new widget.TextBox();
        jLabel80 = new widget.Label();
        jLabel195 = new widget.Label();
        B5NafsuMakan = new widget.ComboBox();
        jLabel196 = new widget.Label();
        B5FrekuensiMakan = new widget.TextBox();
        jLabel199 = new widget.Label();
        jLabel200 = new widget.Label();
        B5PorsiMakan = new widget.TextBox();
        jLabel212 = new widget.Label();
        jLabel201 = new widget.Label();
        jLabel202 = new widget.Label();
        B5KetWarnaBAB = new widget.ComboBox();
        jLabel204 = new widget.Label();
        B5Konsisten = new widget.TextBox();
        jLabel213 = new widget.Label();
        jLabel215 = new widget.Label();
        jLabel216 = new widget.Label();
        B5CaraMinum = new widget.ComboBox();
        jLabel217 = new widget.Label();
        B5Perut = new widget.ComboBox();
        jLabel219 = new widget.Label();
        jLabel220 = new widget.Label();
        B5Kelainan = new widget.ComboBox();
        jLabel221 = new widget.Label();
        B5Anus = new widget.ComboBox();
        jLabel222 = new widget.Label();
        B5Lidah = new widget.ComboBox();
        jLabel223 = new widget.Label();
        B5ReflekRooting = new widget.ComboBox();
        B5Minum = new widget.TextBox();
        jLabel224 = new widget.Label();
        B5JenisMinum = new widget.TextBox();
        B5BAB = new widget.TextBox();
        B5WarnaBAB = new widget.TextBox();
        B5Peristaltik = new widget.TextBox();
        jLabel205 = new widget.Label();
        jLabel218 = new widget.Label();
        B5SelaputLender = new widget.ComboBox();
        jLabel81 = new widget.Label();
        jLabel225 = new widget.Label();
        B6PergerakanSendi = new widget.ComboBox();
        B6KetPergerakanSendi = new widget.TextBox();
        jLabel232 = new widget.Label();
        B6WarnaKulit = new widget.ComboBox();
        jLabel233 = new widget.Label();
        B6Kepala = new widget.ComboBox();
        jLabel234 = new widget.Label();
        jLabel236 = new widget.Label();
        jLabel238 = new widget.Label();
        B6IntergitasKulit = new widget.ComboBox();
        jLabel245 = new widget.Label();
        B6TaliPusat = new widget.ComboBox();
        B6Tugor = new widget.ComboBox();
        B6Odem = new widget.ComboBox();
        B6KetOdem = new widget.TextBox();
        jLabel237 = new widget.Label();
        jLabel239 = new widget.Label();
        B6KekuatanOtotKiriAtas = new widget.TextBox();
        jSeparator15 = new javax.swing.JSeparator();
        B6KekuatanOtotKananAtas = new widget.TextBox();
        B6KekuatanOtotKiriBawah = new widget.TextBox();
        B6KekuatanOtotKananBawah = new widget.TextBox();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel82 = new widget.Label();
        jLabel226 = new widget.Label();
        AlatGenitalLaki = new widget.ComboBox();
        jLabel235 = new widget.Label();
        AlatGenitalPerampuan = new widget.ComboBox();
        jLabel84 = new widget.Label();
        jLabel227 = new widget.Label();
        DerajatIkterus = new widget.ComboBox();
        DaerahIkterus = new widget.TextBox();
        KadarBilirubin = new widget.TextBox();
        jLabel228 = new widget.Label();
        jLabel229 = new widget.Label();
        jLabel49 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        PenilaianApgarScore = new widget.TextArea();
        jLabel50 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        PenilaianDownScore = new widget.TextArea();
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        label11 = new widget.Label();
        jLabel36 = new widget.Label();
        Anamnesis = new widget.ComboBox();
        TglAsuhan = new widget.Tanggal();
        NmPetugas2 = new widget.TextBox();
        BtnPetugas2 = new widget.Button();
        KdPetugas2 = new widget.TextBox();
        label15 = new widget.Label();
        label16 = new widget.Label();
        KdDPJP = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        BtnDPJP = new widget.Button();
        TibadiRuang = new widget.ComboBox();
        jLabel51 = new widget.Label();
        CaraMasuk = new widget.ComboBox();
        jLabel52 = new widget.Label();
        MacamKasus = new widget.ComboBox();
        jLabel53 = new widget.Label();
        KetAnamnesis = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel240 = new widget.Label();
        jLabel241 = new widget.Label();
        SkalaHumptyDumpty1 = new widget.ComboBox();
        jLabel242 = new widget.Label();
        NilaiHumptyDumpty1 = new widget.TextBox();
        jLabel243 = new widget.Label();
        jLabel244 = new widget.Label();
        SkalaHumptyDumpty2 = new widget.ComboBox();
        jLabel246 = new widget.Label();
        NilaiHumptyDumpty2 = new widget.TextBox();
        jLabel247 = new widget.Label();
        jLabel248 = new widget.Label();
        SkalaHumptyDumpty3 = new widget.ComboBox();
        jLabel249 = new widget.Label();
        NilaiHumptyDumpty3 = new widget.TextBox();
        jLabel250 = new widget.Label();
        jLabel251 = new widget.Label();
        SkalaHumptyDumpty4 = new widget.ComboBox();
        jLabel252 = new widget.Label();
        NilaiHumptyDumpty4 = new widget.TextBox();
        jLabel253 = new widget.Label();
        jLabel254 = new widget.Label();
        SkalaHumptyDumpty5 = new widget.ComboBox();
        jLabel255 = new widget.Label();
        NilaiHumptyDumpty5 = new widget.TextBox();
        jLabel256 = new widget.Label();
        jLabel257 = new widget.Label();
        SkalaHumptyDumpty6 = new widget.ComboBox();
        jLabel258 = new widget.Label();
        NilaiHumptyDumpty6 = new widget.TextBox();
        jLabel259 = new widget.Label();
        jLabel260 = new widget.Label();
        SkalaHumptyDumpty7 = new widget.ComboBox();
        jLabel261 = new widget.Label();
        NilaiHumptyDumpty7 = new widget.TextBox();
        TingkatHumptyDumpty = new widget.Label();
        jLabel270 = new widget.Label();
        NilaiHumptyDumptyTotal = new widget.TextBox();
        jLabel70 = new widget.Label();
        WarnaKetuban = new widget.ComboBox();
        jLabel71 = new widget.Label();
        KelainanPersalinan = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel75 = new widget.Label();
        UsiaKehamilan = new widget.TextBox();
        PenolongPersalinan = new widget.TextBox();
        jLabel101 = new widget.Label();
        Penolong = new widget.ComboBox();
        BtnPenolong = new widget.Button();
        jLabel230 = new widget.Label();
        SkalaAktivitas = new widget.ComboBox();
        NilaiAktivitas = new widget.TextBox();
        jLabel231 = new widget.Label();
        Wajib = new widget.ComboBox();
        KetHalanganLain = new widget.TextBox();
        jLabel262 = new widget.Label();
        Toharah = new widget.ComboBox();
        jLabel263 = new widget.Label();
        jLabel264 = new widget.Label();
        Sholat = new widget.ComboBox();
        jLabel265 = new widget.Label();
        MotivasiKesembuhanIbadah = new widget.ComboBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        BtnPrint1 = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        scrollPane9 = new widget.ScrollPane();
        tbImunisasi2 = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbMasalahDetailMasalah = new widget.Table();
        Scroll10 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        DlgRiwayatImunisasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRiwayatImunisasi.setName("DlgRiwayatImunisasi"); // NOI18N
        DlgRiwayatImunisasi.setUndecorated(true);
        DlgRiwayatImunisasi.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Riwayat Imunisasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        jLabel99.setText("Imunisasi :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelBiasa2.add(jLabel99);
        jLabel99.setBounds(0, 13, 67, 23);

        BtnKeluarImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluarImunisasi.setMnemonic('U');
        BtnKeluarImunisasi.setText("Tutup");
        BtnKeluarImunisasi.setToolTipText("Alt+U");
        BtnKeluarImunisasi.setName("BtnKeluarImunisasi"); // NOI18N
        BtnKeluarImunisasi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarImunisasiActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluarImunisasi);
        BtnKeluarImunisasi.setBounds(340, 50, 100, 30);

        BtnSimpanImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanImunisasi.setMnemonic('S');
        BtnSimpanImunisasi.setText("Simpan");
        BtnSimpanImunisasi.setToolTipText("Alt+S");
        BtnSimpanImunisasi.setName("BtnSimpanImunisasi"); // NOI18N
        BtnSimpanImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanImunisasiActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSimpanImunisasi);
        BtnSimpanImunisasi.setBounds(10, 50, 100, 30);

        BtnImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnImunisasi.setMnemonic('2');
        BtnImunisasi.setToolTipText("Alt+2");
        BtnImunisasi.setName("BtnImunisasi"); // NOI18N
        BtnImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImunisasiActionPerformed(evt);
            }
        });
        BtnImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnImunisasi);
        BtnImunisasi.setBounds(307, 13, 28, 23);

        NmImunisasi.setEditable(false);
        NmImunisasi.setName("NmImunisasi"); // NOI18N
        NmImunisasi.setPreferredSize(new java.awt.Dimension(207, 23));
        panelBiasa2.add(NmImunisasi);
        NmImunisasi.setBounds(124, 13, 180, 23);

        KdImunisasi.setEditable(false);
        KdImunisasi.setName("KdImunisasi"); // NOI18N
        KdImunisasi.setPreferredSize(new java.awt.Dimension(80, 23));
        KdImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(KdImunisasi);
        KdImunisasi.setBounds(71, 13, 50, 23);

        jLabel43.setText("Ke :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelBiasa2.add(jLabel43);
        jLabel43.setBounds(343, 13, 30, 23);

        ImunisasiKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        ImunisasiKe.setName("ImunisasiKe"); // NOI18N
        ImunisasiKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ImunisasiKeKeyPressed(evt);
            }
        });
        panelBiasa2.add(ImunisasiKe);
        ImunisasiKe.setBounds(377, 13, 60, 23);

        BtnHapusImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusImunisasi.setMnemonic('H');
        BtnHapusImunisasi.setText("Hapus");
        BtnHapusImunisasi.setToolTipText("Alt+H");
        BtnHapusImunisasi.setName("BtnHapusImunisasi"); // NOI18N
        BtnHapusImunisasi.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusImunisasiActionPerformed(evt);
            }
        });
        BtnHapusImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusImunisasiKeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnHapusImunisasi);
        BtnHapusImunisasi.setBounds(230, 50, 100, 30);

        internalFrame4.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgRiwayatImunisasi.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSemua.setText("Pilih Semua");
        ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSemua.setName("ppSemua"); // NOI18N
        ppSemua.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Keperawatan Rawat Inap Neonatus ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass8.add(BtnSimpan);

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
        panelGlass8.add(BtnBatal);

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
        panelGlass8.add(BtnHapus);

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
        panelGlass8.add(BtnEdit);

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
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(924, 3100));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(740, 330, 50, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(690, 330, 45, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(650, 330, 40, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(160, 360, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(200, 360, 45, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(480, 330, 70, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(550, 330, 60, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(250, 360, 30, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(610, 330, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(120, 360, 50, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(70, 360, 45, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(30, 360, 40, 23);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(250, 360, 90, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(340, 360, 60, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 130, 880, 1);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("cm");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(590, 360, 50, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(540, 360, 45, 23);

        jLabel27.setText("TB :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(500, 360, 40, 23);

        jLabel29.setText("LK :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(690, 360, 40, 23);

        LK.setFocusTraversalPolicyProvider(true);
        LK.setName("LK"); // NOI18N
        LK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LKKeyPressed(evt);
            }
        });
        FormInput.add(LK);
        LK.setBounds(730, 360, 45, 23);

        jLabel30.setText("BB :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(370, 360, 70, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(440, 360, 45, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("cm");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(870, 360, 30, 23);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Kg");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(490, 360, 50, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("cm");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(690, 360, 50, 23);

        LP.setFocusTraversalPolicyProvider(true);
        LP.setName("LP"); // NOI18N
        LP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LPKeyPressed(evt);
            }
        });
        FormInput.add(LP);
        LP.setBounds(640, 360, 45, 23);

        jLabel35.setText("LP :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(600, 360, 40, 23);

        jLabel37.setText("LD :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(790, 360, 30, 23);

        LD.setFocusTraversalPolicyProvider(true);
        LD.setName("LD"); // NOI18N
        LD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LDKeyPressed(evt);
            }
        });
        FormInput.add(LD);
        LD.setBounds(820, 360, 45, 23);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("cm");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(780, 360, 30, 23);

        jLabel9.setText("Riwayat Pengobatan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(440, 200, 150, 23);

        jLabel39.setText("Riwayat Alergi :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(410, 250, 175, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(590, 250, 260, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        KeluhanUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanUtama.setColumns(20);
        KeluhanUtama.setRows(5);
        KeluhanUtama.setName("KeluhanUtama"); // NOI18N
        KeluhanUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanUtamaKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(KeluhanUtama);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(180, 150, 260, 43);

        jLabel40.setText("Keluhan Utama :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 150, 175, 20);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        RPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPD.setColumns(20);
        RPD.setRows(5);
        RPD.setName("RPD"); // NOI18N
        RPD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPDKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(RPD);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(180, 200, 260, 43);

        jLabel41.setText("Riwayat Penyakit Dahulu :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 200, 175, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        RPK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPK.setColumns(20);
        RPK.setRows(5);
        RPK.setName("RPK"); // NOI18N
        RPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPKKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(RPK);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(590, 150, 260, 42);

        jLabel42.setText("Riwayat Penyakit Keluarga :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(440, 150, 150, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RPO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPO.setColumns(20);
        RPO.setRows(5);
        RPO.setName("RPO"); // NOI18N
        RPO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPOKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RPO);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(590, 200, 260, 42);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("I. RIWAYAT KESEHATAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 130, 180, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 310, 880, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1200, 880, 1);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("III. PERINATAL CARE");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(10, 1200, 350, 23);

        jLabel44.setText("Anak ke :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(100, 1220, 55, 23);

        Anakke.setFocusTraversalPolicyProvider(true);
        Anakke.setName("Anakke"); // NOI18N
        Anakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnakkeKeyPressed(evt);
            }
        });
        FormInput.add(Anakke);
        Anakke.setBounds(160, 1220, 40, 23);

        DariSaudara.setFocusTraversalPolicyProvider(true);
        DariSaudara.setName("DariSaudara"); // NOI18N
        DariSaudara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DariSaudaraKeyPressed(evt);
            }
        });
        FormInput.add(DariSaudara);
        DariSaudara.setBounds(230, 1220, 40, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("dari");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(200, 1220, 24, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("saudara");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(270, 1220, 50, 23);

        jLabel55.setText("Cara Kelahiran :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(310, 1220, 100, 23);

        CaraKelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Spontan", "Sectio Caesaria", "Lain-Lain" }));
        CaraKelahiran.setName("CaraKelahiran"); // NOI18N
        CaraKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(CaraKelahiran);
        CaraKelahiran.setBounds(420, 1220, 127, 23);

        KetCaraKelahiran.setFocusTraversalPolicyProvider(true);
        KetCaraKelahiran.setName("KetCaraKelahiran"); // NOI18N
        KetCaraKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetCaraKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(KetCaraKelahiran);
        KetCaraKelahiran.setBounds(550, 1220, 140, 23);

        KelainanBawaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        KelainanBawaan.setName("KelainanBawaan"); // NOI18N
        KelainanBawaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelainanBawaanKeyPressed(evt);
            }
        });
        FormInput.add(KelainanBawaan);
        KelainanBawaan.setBounds(360, 1250, 100, 23);

        jLabel56.setText("Kelainan Bawaan :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(240, 1250, 110, 23);

        KetKelainanBawaan.setFocusTraversalPolicyProvider(true);
        KetKelainanBawaan.setName("KetKelainanBawaan"); // NOI18N
        KetKelainanBawaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKelainanBawaanKeyPressed(evt);
            }
        });
        FormInput.add(KetKelainanBawaan);
        KetKelainanBawaan.setBounds(460, 1250, 100, 23);

        UmurKelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cukup Bulan", "Kurang Bulan" }));
        UmurKelahiran.setName("UmurKelahiran"); // NOI18N
        UmurKelahiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurKelahiranKeyPressed(evt);
            }
        });
        FormInput.add(UmurKelahiran);
        UmurKelahiran.setBounds(100, 1250, 140, 23);

        jLabel57.setText("Riwayat Kelahiran :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 1220, 110, 23);

        jLabel58.setText("Umur Kelahiran :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 1250, 100, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 1330, 880, 1);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("IV. RIWAYAT IMUNISASI");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(10, 1330, 350, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbImunisasi.setName("tbImunisasi"); // NOI18N
        Scroll6.setViewportView(tbImunisasi);

        FormInput.add(Scroll6);
        Scroll6.setBounds(100, 1350, 760, 93);

        BtnTambahImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahImunisasi.setMnemonic('3');
        BtnTambahImunisasi.setToolTipText("Alt+3");
        BtnTambahImunisasi.setName("BtnTambahImunisasi"); // NOI18N
        BtnTambahImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahImunisasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahImunisasi);
        BtnTambahImunisasi.setBounds(60, 1350, 28, 23);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 1460, 880, 1);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("V. RIWAYAT TUMBUH KEMBANG ANAK");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(10, 1460, 350, 23);

        jLabel59.setText("a. Tengkurap, usia :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(10, 1480, 133, 23);

        UsiaTengkurap.setFocusTraversalPolicyProvider(true);
        UsiaTengkurap.setName("UsiaTengkurap"); // NOI18N
        UsiaTengkurap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaTengkurapKeyPressed(evt);
            }
        });
        FormInput.add(UsiaTengkurap);
        UsiaTengkurap.setBounds(140, 1480, 90, 23);

        jLabel60.setText("b. Duduk, usia :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(250, 1480, 90, 23);

        UsiaDuduk.setFocusTraversalPolicyProvider(true);
        UsiaDuduk.setName("UsiaDuduk"); // NOI18N
        UsiaDuduk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaDudukKeyPressed(evt);
            }
        });
        FormInput.add(UsiaDuduk);
        UsiaDuduk.setBounds(340, 1480, 90, 23);

        jLabel61.setText("c. Berdiri, usia :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(440, 1480, 90, 23);

        UsiaBerdiri.setFocusTraversalPolicyProvider(true);
        UsiaBerdiri.setName("UsiaBerdiri"); // NOI18N
        UsiaBerdiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBerdiriKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBerdiri);
        UsiaBerdiri.setBounds(540, 1480, 90, 23);

        jLabel62.setText("d. Gigi pertama, usia :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(630, 1480, 130, 23);

        UsiaGigi.setFocusTraversalPolicyProvider(true);
        UsiaGigi.setName("UsiaGigi"); // NOI18N
        UsiaGigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaGigiKeyPressed(evt);
            }
        });
        FormInput.add(UsiaGigi);
        UsiaGigi.setBounds(770, 1480, 90, 23);

        jLabel63.setText("e. Berjalan, usia :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(10, 1510, 122, 23);

        UsiaBerjalan.setFocusTraversalPolicyProvider(true);
        UsiaBerjalan.setName("UsiaBerjalan"); // NOI18N
        UsiaBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBerjalan);
        UsiaBerjalan.setBounds(130, 1510, 90, 23);

        jLabel64.setText("f. Bicara Usia, usia :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(300, 1510, 110, 23);

        UsiaBicara.setFocusTraversalPolicyProvider(true);
        UsiaBicara.setName("UsiaBicara"); // NOI18N
        UsiaBicara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaBicaraKeyPressed(evt);
            }
        });
        FormInput.add(UsiaBicara);
        UsiaBicara.setBounds(420, 1510, 90, 23);

        jLabel65.setText("g. Mulai bisa membaca, usia :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(580, 1510, 180, 23);

        UsiaMembaca.setFocusTraversalPolicyProvider(true);
        UsiaMembaca.setName("UsiaMembaca"); // NOI18N
        UsiaMembaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaMembacaKeyPressed(evt);
            }
        });
        FormInput.add(UsiaMembaca);
        UsiaMembaca.setBounds(770, 1510, 90, 23);

        UsiaMenulis.setFocusTraversalPolicyProvider(true);
        UsiaMenulis.setName("UsiaMenulis"); // NOI18N
        UsiaMenulis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaMenulisKeyPressed(evt);
            }
        });
        FormInput.add(UsiaMenulis);
        UsiaMenulis.setBounds(180, 1540, 90, 23);

        jLabel66.setText("h. Mulai bisa menulis, usia :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(10, 1540, 172, 23);

        GangguanEmosi.setFocusTraversalPolicyProvider(true);
        GangguanEmosi.setName("GangguanEmosi"); // NOI18N
        GangguanEmosi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanEmosiKeyPressed(evt);
            }
        });
        FormInput.add(GangguanEmosi);
        GangguanEmosi.setBounds(620, 1540, 240, 23);

        jLabel67.setText("Gangguan perkembangan mental / emosi, bila ada, jelaskan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(290, 1540, 320, 23);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 1580, 880, 1);

        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("VI. FUNGSIONAL");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(10, 1580, 230, 23);

        jLabel127.setText("Prothesa :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(480, 1600, 60, 23);

        AlatBantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        AlatBantu.setName("AlatBantu"); // NOI18N
        AlatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatBantuKeyPressed(evt);
            }
        });
        FormInput.add(AlatBantu);
        AlatBantu.setBounds(130, 1600, 90, 23);

        KetBantu.setFocusTraversalPolicyProvider(true);
        KetBantu.setName("KetBantu"); // NOI18N
        KetBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBantuKeyPressed(evt);
            }
        });
        FormInput.add(KetBantu);
        KetBantu.setBounds(220, 1600, 220, 23);

        Prothesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Prothesa.setName("Prothesa"); // NOI18N
        Prothesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProthesaKeyPressed(evt);
            }
        });
        FormInput.add(Prothesa);
        Prothesa.setBounds(540, 1600, 90, 23);

        KetProthesa.setFocusTraversalPolicyProvider(true);
        KetProthesa.setName("KetProthesa"); // NOI18N
        KetProthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProthesaKeyPressed(evt);
            }
        });
        FormInput.add(KetProthesa);
        KetProthesa.setBounds(640, 1600, 220, 23);

        jLabel128.setText("Alat Bantu :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(10, 1600, 120, 23);

        ADL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Dibantu" }));
        ADL.setName("ADL"); // NOI18N
        ADL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ADLKeyPressed(evt);
            }
        });
        FormInput.add(ADL);
        ADL.setBounds(730, 1630, 130, 23);

        jLabel129.setText("Aktivitas Kehidupan Sehari-hari ( ADL ) :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(440, 1630, 280, 23);

        jLabel130.setText("Cacat Fisik :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(10, 1630, 120, 23);

        CacatFisik.setEditable(false);
        CacatFisik.setFocusTraversalPolicyProvider(true);
        CacatFisik.setName("CacatFisik"); // NOI18N
        FormInput.add(CacatFisik);
        CacatFisik.setBounds(130, 1630, 314, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 1670, 880, 1);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("VII. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(10, 1670, 380, 23);

        StatusPsiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tenang", "Takut", "Tempertantrum", "Cemas", "Depresi", "Lain-lain" }));
        StatusPsiko.setName("StatusPsiko"); // NOI18N
        StatusPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusPsikoKeyPressed(evt);
            }
        });
        FormInput.add(StatusPsiko);
        StatusPsiko.setBounds(140, 1690, 140, 23);

        KetPsiko.setFocusTraversalPolicyProvider(true);
        KetPsiko.setName("KetPsiko"); // NOI18N
        KetPsiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPsikoKeyPressed(evt);
            }
        });
        FormInput.add(KetPsiko);
        KetPsiko.setBounds(280, 1690, 200, 23);

        jLabel132.setText("Status Psikologis :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(10, 1690, 130, 23);

        Bahasa.setEditable(false);
        Bahasa.setFocusTraversalPolicyProvider(true);
        Bahasa.setName("Bahasa"); // NOI18N
        FormInput.add(Bahasa);
        Bahasa.setBounds(690, 1690, 170, 23);

        jLabel133.setText("Bahasa yang digunakan sehari-hari :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(450, 1690, 230, 23);

        jLabel134.setText("Status Sosial dan ekonomi :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(10, 1720, 176, 23);

        jLabel135.setText("a. Hubungan dengan anggota keluarga :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(40, 1740, 210, 23);

        HubunganKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Tidak Baik" }));
        HubunganKeluarga.setName("HubunganKeluarga"); // NOI18N
        HubunganKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(HubunganKeluarga);
        HubunganKeluarga.setBounds(250, 1740, 100, 23);

        Pengasuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Orang Tua", "Kakek/Nenek", "Keluarga Lainnya" }));
        Pengasuh.setName("Pengasuh"); // NOI18N
        Pengasuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PengasuhKeyPressed(evt);
            }
        });
        FormInput.add(Pengasuh);
        Pengasuh.setBounds(430, 1740, 135, 23);

        KetPengasuh.setFocusTraversalPolicyProvider(true);
        KetPengasuh.setName("KetPengasuh"); // NOI18N
        KetPengasuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPengasuhKeyPressed(evt);
            }
        });
        FormInput.add(KetPengasuh);
        KetPengasuh.setBounds(570, 1740, 85, 23);

        jLabel136.setText("b. Pengasuh :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(350, 1740, 75, 23);

        Ekonomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Cukup", "Kurang" }));
        Ekonomi.setName("Ekonomi"); // NOI18N
        Ekonomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkonomiKeyPressed(evt);
            }
        });
        FormInput.add(Ekonomi);
        Ekonomi.setBounds(770, 1740, 84, 23);

        jLabel137.setText("c. Ekonomi (Ortu) :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(660, 1740, 110, 23);

        jLabel138.setText("Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(10, 1770, 366, 23);

        StatusBudaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        StatusBudaya.setName("StatusBudaya"); // NOI18N
        StatusBudaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusBudayaKeyPressed(evt);
            }
        });
        FormInput.add(StatusBudaya);
        StatusBudaya.setBounds(380, 1770, 110, 23);

        KetBudaya.setFocusTraversalPolicyProvider(true);
        KetBudaya.setName("KetBudaya"); // NOI18N
        KetBudaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBudayaKeyPressed(evt);
            }
        });
        FormInput.add(KetBudaya);
        KetBudaya.setBounds(490, 1770, 370, 23);

        jLabel139.setText("Edukasi diberikan kepada :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(230, 1800, 140, 23);

        Edukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Orang Tua", "Keluarga" }));
        Edukasi.setName("Edukasi"); // NOI18N
        Edukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiKeyPressed(evt);
            }
        });
        FormInput.add(Edukasi);
        Edukasi.setBounds(370, 1800, 110, 23);

        KetEdukasi.setFocusTraversalPolicyProvider(true);
        KetEdukasi.setName("KetEdukasi"); // NOI18N
        KetEdukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetEdukasiKeyPressed(evt);
            }
        });
        FormInput.add(KetEdukasi);
        KetEdukasi.setBounds(490, 1800, 370, 23);

        jLabel140.setText("Agama :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(10, 1800, 82, 23);

        Agama.setEditable(false);
        Agama.setFocusTraversalPolicyProvider(true);
        Agama.setName("Agama"); // NOI18N
        FormInput.add(Agama);
        Agama.setBounds(100, 1800, 110, 23);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(0, 1900, 880, 1);

        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel141.setText("VIII. PENILAIAN RESIKO JATUH");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(10, 1900, 380, 23);

        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel149.setText("IX. SKRINING GIZI (Strong kid)");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(10, 2220, 380, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 2220, 880, 1);

        SG1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG1.setName("SG1"); // NOI18N
        SG1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG1ItemStateChanged(evt);
            }
        });
        SG1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG1KeyPressed(evt);
            }
        });
        FormInput.add(SG1);
        SG1.setBounds(700, 2240, 80, 23);

        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("1.  Apakah pasien tampak kurus");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(40, 2240, 610, 23);

        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("Apakah terdapat salah satu dari kondisi tersebut? Diare > 5 kali/hari dan/muntah > 3 kali/hari dalam seminggu terakhir;");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(60, 2320, 610, 23);

        SG2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG2.setName("SG2"); // NOI18N
        SG2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG2ItemStateChanged(evt);
            }
        });
        SG2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG2KeyPressed(evt);
            }
        });
        FormInput.add(SG2);
        SG2.setBounds(700, 2280, 80, 23);

        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel154.setText("bila ada atau untuk bayi < 1 tahun ; berat badan tidak naik selama 3 bulan terakhir)");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(60, 2290, 600, 23);

        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("2.");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(40, 2270, 20, 37);

        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel156.setText("Asupan makanan berkurang selama 1 minggu terakhir");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(60, 2340, 610, 23);

        SG3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG3.setName("SG3"); // NOI18N
        SG3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG3ItemStateChanged(evt);
            }
        });
        SG3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG3KeyPressed(evt);
            }
        });
        FormInput.add(SG3);
        SG3.setBounds(700, 2320, 80, 23);

        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel158.setText("Apakah terdapat penurunan berat badan selama satu bulan terakhir? (berdasarkan penilaian objektif data berat badan");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(60, 2270, 600, 23);

        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel159.setText("3.");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(40, 2320, 20, 37);

        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel160.setText("4.  Apakah terdapat penyakit atau keadaan yang menyebabkan pasien beresiko mengalami malnutrisi?");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(40, 2360, 610, 23);

        SkalaWajah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rileks", "Meringis" }));
        SkalaWajah.setName("SkalaWajah"); // NOI18N
        SkalaWajah.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaWajahItemStateChanged(evt);
            }
        });
        SkalaWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaWajahKeyPressed(evt);
            }
        });
        FormInput.add(SkalaWajah);
        SkalaWajah.setBounds(110, 2770, 310, 23);

        jLabel162.setText("Total Skor :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(680, 2390, 90, 23);

        NilaiGizi1.setEditable(false);
        NilaiGizi1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi1.setText("0");
        NilaiGizi1.setFocusTraversalPolicyProvider(true);
        NilaiGizi1.setName("NilaiGizi1"); // NOI18N
        NilaiGizi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi1KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi1);
        NilaiGizi1.setBounds(800, 2240, 60, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 2430, 880, 1);

        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel163.setText("XI. PENILAIAN TINGKAT NYERI");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(10, 2730, 380, 23);

        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel164.setText("Skala NIPS :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(40, 2750, 210, 23);

        jLabel165.setText("Wajah :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(50, 2770, 60, 23);

        jLabel166.setText("Tangisan :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(50, 2800, 60, 23);

        jLabel167.setText("Pola Napas :");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(40, 2830, 70, 23);

        jLabel168.setText("Lengan :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(480, 2770, 60, 23);

        jLabel169.setText("Tungkai :");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(480, 2800, 60, 23);

        SG4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SG4.setName("SG4"); // NOI18N
        SG4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SG4ItemStateChanged(evt);
            }
        });
        SG4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SG4KeyPressed(evt);
            }
        });
        FormInput.add(SG4);
        SG4.setBounds(700, 2360, 80, 23);

        NilaiGizi2.setEditable(false);
        NilaiGizi2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi2.setText("0");
        NilaiGizi2.setFocusTraversalPolicyProvider(true);
        NilaiGizi2.setName("NilaiGizi2"); // NOI18N
        NilaiGizi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi2KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi2);
        NilaiGizi2.setBounds(800, 2280, 60, 23);

        NilaiGizi3.setEditable(false);
        NilaiGizi3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi3.setText("0");
        NilaiGizi3.setFocusTraversalPolicyProvider(true);
        NilaiGizi3.setName("NilaiGizi3"); // NOI18N
        NilaiGizi3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi3KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi3);
        NilaiGizi3.setBounds(800, 2320, 60, 23);

        NilaiGizi4.setEditable(false);
        NilaiGizi4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiGizi4.setText("0");
        NilaiGizi4.setFocusTraversalPolicyProvider(true);
        NilaiGizi4.setName("NilaiGizi4"); // NOI18N
        NilaiGizi4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiGizi4KeyPressed(evt);
            }
        });
        FormInput.add(NilaiGizi4);
        NilaiGizi4.setBounds(800, 2360, 60, 23);

        NilaiWajah.setEditable(false);
        NilaiWajah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiWajah.setText("0");
        NilaiWajah.setFocusTraversalPolicyProvider(true);
        NilaiWajah.setName("NilaiWajah"); // NOI18N
        NilaiWajah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiWajahKeyPressed(evt);
            }
        });
        FormInput.add(NilaiWajah);
        NilaiWajah.setBounds(420, 2770, 40, 23);

        TotalNilaiGizi.setEditable(false);
        TotalNilaiGizi.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TotalNilaiGizi.setText("0");
        TotalNilaiGizi.setFocusTraversalPolicyProvider(true);
        TotalNilaiGizi.setName("TotalNilaiGizi"); // NOI18N
        TotalNilaiGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TotalNilaiGiziKeyPressed(evt);
            }
        });
        FormInput.add(TotalNilaiGizi);
        TotalNilaiGizi.setBounds(780, 2390, 80, 23);

        SkalaTangisan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Menangis", "Merengek", "Menangis Kuat" }));
        SkalaTangisan.setName("SkalaTangisan"); // NOI18N
        SkalaTangisan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaTangisanItemStateChanged(evt);
            }
        });
        SkalaTangisan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaTangisanKeyPressed(evt);
            }
        });
        FormInput.add(SkalaTangisan);
        SkalaTangisan.setBounds(110, 2800, 310, 23);

        NilaiTangisan.setEditable(false);
        NilaiTangisan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiTangisan.setText("0");
        NilaiTangisan.setFocusTraversalPolicyProvider(true);
        NilaiTangisan.setName("NilaiTangisan"); // NOI18N
        NilaiTangisan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiTangisanKeyPressed(evt);
            }
        });
        FormInput.add(NilaiTangisan);
        NilaiTangisan.setBounds(420, 2800, 40, 23);

        SkalaPolaNapas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rileks", "Perubahan Pola Napas" }));
        SkalaPolaNapas.setName("SkalaPolaNapas"); // NOI18N
        SkalaPolaNapas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaPolaNapasItemStateChanged(evt);
            }
        });
        SkalaPolaNapas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaPolaNapasKeyPressed(evt);
            }
        });
        FormInput.add(SkalaPolaNapas);
        SkalaPolaNapas.setBounds(110, 2830, 310, 23);

        NilaiPolaNapas.setEditable(false);
        NilaiPolaNapas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiPolaNapas.setText("0");
        NilaiPolaNapas.setFocusTraversalPolicyProvider(true);
        NilaiPolaNapas.setName("NilaiPolaNapas"); // NOI18N
        NilaiPolaNapas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiPolaNapasKeyPressed(evt);
            }
        });
        FormInput.add(NilaiPolaNapas);
        NilaiPolaNapas.setBounds(420, 2830, 40, 23);

        SkalaLengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Restrain", "Rileks", "Fleksi", "Ekstensi" }));
        SkalaLengan.setName("SkalaLengan"); // NOI18N
        SkalaLengan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaLenganItemStateChanged(evt);
            }
        });
        SkalaLengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaLenganKeyPressed(evt);
            }
        });
        FormInput.add(SkalaLengan);
        SkalaLengan.setBounds(550, 2770, 266, 23);

        NilaiLengan.setEditable(false);
        NilaiLengan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiLengan.setText("0");
        NilaiLengan.setFocusTraversalPolicyProvider(true);
        NilaiLengan.setName("NilaiLengan"); // NOI18N
        NilaiLengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiLenganKeyPressed(evt);
            }
        });
        FormInput.add(NilaiLengan);
        NilaiLengan.setBounds(820, 2770, 40, 23);

        SkalaTungkai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Restrain", "Rileks", "Fleksi", "Ekstensi" }));
        SkalaTungkai.setName("SkalaTungkai"); // NOI18N
        SkalaTungkai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaTungkaiItemStateChanged(evt);
            }
        });
        SkalaTungkai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaTungkaiKeyPressed(evt);
            }
        });
        FormInput.add(SkalaTungkai);
        SkalaTungkai.setBounds(550, 2800, 266, 23);

        NilaiTungkai.setEditable(false);
        NilaiTungkai.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiTungkai.setText("0");
        NilaiTungkai.setFocusTraversalPolicyProvider(true);
        NilaiTungkai.setName("NilaiTungkai"); // NOI18N
        NilaiTungkai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiTungkaiKeyPressed(evt);
            }
        });
        FormInput.add(NilaiTungkai);
        NilaiTungkai.setBounds(820, 2800, 40, 23);

        jLabel170.setText("Skala nyeri :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(680, 2860, 90, 23);

        SkalaNyeri.setEditable(false);
        SkalaNyeri.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        SkalaNyeri.setText("0");
        SkalaNyeri.setFocusTraversalPolicyProvider(true);
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(780, 2860, 80, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 2890, 880, 1);

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbMasalahKeperawatan.setName("tbMasalahKeperawatan"); // NOI18N
        tbMasalahKeperawatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatanMouseClicked(evt);
            }
        });
        tbMasalahKeperawatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbMasalahKeperawatan);

        FormInput.add(Scroll8);
        Scroll8.setBounds(10, 2900, 400, 143);

        BtnPanggilHapusImunisasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnPanggilHapusImunisasi.setMnemonic('3');
        BtnPanggilHapusImunisasi.setToolTipText("Alt+3");
        BtnPanggilHapusImunisasi.setName("BtnPanggilHapusImunisasi"); // NOI18N
        BtnPanggilHapusImunisasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPanggilHapusImunisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPanggilHapusImunisasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPanggilHapusImunisasi);
        BtnPanggilHapusImunisasi.setBounds(60, 1380, 28, 23);

        TabRencanaKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        TabRencanaKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabRencanaKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabRencanaKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRencanaKeperawatan.setName("TabRencanaKeperawatan"); // NOI18N

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setLayout(new java.awt.BorderLayout());

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbRencanaKeperawatan.setComponentPopupMenu(Popup);
        tbRencanaKeperawatan.setName("tbRencanaKeperawatan"); // NOI18N
        Scroll9.setViewportView(tbRencanaKeperawatan);

        panelBiasa1.add(Scroll9, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan", panelBiasa1);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Rencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Rencana.setColumns(20);
        Rencana.setRows(5);
        Rencana.setName("Rencana"); // NOI18N
        Rencana.setOpaque(true);
        Rencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Rencana);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Lainnya", scrollPane5);

        FormInput.add(TabRencanaKeperawatan);
        TabRencanaKeperawatan.setBounds(430, 2900, 420, 143);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(440, 3050, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(500, 3050, 235, 23);

        BtnCariRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariRencana.setMnemonic('1');
        BtnCariRencana.setToolTipText("Alt+1");
        BtnCariRencana.setName("BtnCariRencana"); // NOI18N
        BtnCariRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariRencanaActionPerformed(evt);
            }
        });
        BtnCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariRencana);
        BtnCariRencana.setBounds(740, 3050, 28, 23);

        BtnAllRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllRencana.setMnemonic('2');
        BtnAllRencana.setToolTipText("2Alt+2");
        BtnAllRencana.setName("BtnAllRencana"); // NOI18N
        BtnAllRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllRencanaActionPerformed(evt);
            }
        });
        BtnAllRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllRencanaKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllRencana);
        BtnAllRencana.setBounds(780, 3050, 28, 23);

        BtnTambahRencana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahRencana.setMnemonic('3');
        BtnTambahRencana.setToolTipText("Alt+3");
        BtnTambahRencana.setName("BtnTambahRencana"); // NOI18N
        BtnTambahRencana.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahRencanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahRencana);
        BtnTambahRencana.setBounds(810, 3050, 28, 23);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(20, 3050, 60, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(80, 3050, 215, 23);

        BtnCariMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariMasalah.setMnemonic('1');
        BtnCariMasalah.setToolTipText("Alt+1");
        BtnCariMasalah.setName("BtnCariMasalah"); // NOI18N
        BtnCariMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariMasalahActionPerformed(evt);
            }
        });
        BtnCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariMasalah);
        BtnCariMasalah.setBounds(300, 3050, 28, 23);

        BtnAllMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllMasalah.setMnemonic('2');
        BtnAllMasalah.setToolTipText("2Alt+2");
        BtnAllMasalah.setName("BtnAllMasalah"); // NOI18N
        BtnAllMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllMasalahActionPerformed(evt);
            }
        });
        BtnAllMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllMasalahKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllMasalah);
        BtnAllMasalah.setBounds(330, 3050, 28, 23);

        BtnTambahMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah.setMnemonic('3');
        BtnTambahMasalah.setToolTipText("Alt+3");
        BtnTambahMasalah.setName("BtnTambahMasalah"); // NOI18N
        BtnTambahMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalahActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(360, 3050, 28, 23);

        jLabel47.setText("Riwayat Penyakit Saat Ini :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(20, 250, 150, 23);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("II. PEMERIKSAAN FISIK");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 310, 180, 23);

        jLabel48.setText("Kesadaran Mental :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(10, 330, 138, 23);

        KesadaranMental.setFocusTraversalPolicyProvider(true);
        KesadaranMental.setName("KesadaranMental"); // NOI18N
        KesadaranMental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranMentalKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranMental);
        KesadaranMental.setBounds(150, 330, 100, 23);

        jLabel151.setText("Keadaan Umum Bayi Lahir :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(250, 330, 150, 23);

        KeadaanMentalUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IMD", "VTP - 1 SIKLUS", "VTP - 2 SIKLUS", "Segera Menangis", "Menangis Beberapa Saat", "Tidak Menangis" }));
        KeadaanMentalUmum.setName("KeadaanMentalUmum"); // NOI18N
        KeadaanMentalUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanMentalUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanMentalUmum);
        KeadaanMentalUmum.setBounds(400, 330, 120, 23);

        jLabel73.setText("SpO2 :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(780, 330, 40, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        FormInput.add(SpO2);
        SpO2.setBounds(820, 330, 50, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("%");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(870, 330, 30, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("B1 :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(50, 390, 187, 23);

        jLabel153.setText("Nafas Spontan :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(10, 410, 109, 23);

        jLabel157.setText("Irama :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(10, 440, 109, 23);

        B1Irama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        B1Irama.setName("B1Irama"); // NOI18N
        B1Irama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B1IramaKeyPressed(evt);
            }
        });
        FormInput.add(B1Irama);
        B1Irama.setBounds(120, 440, 155, 23);

        B1NafasSpontan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        B1NafasSpontan.setName("B1NafasSpontan"); // NOI18N
        FormInput.add(B1NafasSpontan);
        B1NafasSpontan.setBounds(120, 410, 103, 23);

        jLabel161.setText("Suara Nafas :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(290, 440, 80, 23);

        B1SuaraNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Vesikuler", "Stridor", "Wheezing", "Ronchi" }));
        B1SuaraNafas.setName("B1SuaraNafas"); // NOI18N
        B1SuaraNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B1SuaraNafasKeyPressed(evt);
            }
        });
        FormInput.add(B1SuaraNafas);
        B1SuaraNafas.setBounds(370, 440, 80, 23);

        B1KetO2Nafas.setFocusTraversalPolicyProvider(true);
        B1KetO2Nafas.setName("B1KetO2Nafas"); // NOI18N
        B1KetO2Nafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B1KetO2NafasKeyPressed(evt);
            }
        });
        FormInput.add(B1KetO2Nafas);
        B1KetO2Nafas.setBounds(690, 410, 184, 23);

        jLabel171.setText("Jenis :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(230, 410, 80, 23);

        B1JenisNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dyspnea", "Kussmaul", "Ceyne Stokes", "Lain-lain" }));
        B1JenisNafas.setName("B1JenisNafas"); // NOI18N
        B1JenisNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B1JenisNafasKeyPressed(evt);
            }
        });
        FormInput.add(B1JenisNafas);
        B1JenisNafas.setBounds(310, 410, 150, 23);

        B1O2Nafas.setFocusTraversalPolicyProvider(true);
        B1O2Nafas.setName("B1O2Nafas"); // NOI18N
        B1O2Nafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B1O2NafasKeyPressed(evt);
            }
        });
        FormInput.add(B1O2Nafas);
        B1O2Nafas.setBounds(570, 410, 80, 23);

        jLabel172.setText("lpm,");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(650, 410, 30, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("B2 :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(50, 470, 122, 23);

        jLabel173.setText("Irama Jantung :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(10, 490, 109, 23);

        B2IramaJantung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        B2IramaJantung.setName("B2IramaJantung"); // NOI18N
        B2IramaJantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B2IramaJantungKeyPressed(evt);
            }
        });
        FormInput.add(B2IramaJantung);
        B2IramaJantung.setBounds(120, 490, 96, 23);

        jLabel174.setText("Acral :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(220, 490, 60, 23);

        B2Acral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hangat", "Kering", "Merah", "Pucat", "Dingin" }));
        B2Acral.setName("B2Acral"); // NOI18N
        B2Acral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B2AcralKeyPressed(evt);
            }
        });
        FormInput.add(B2Acral);
        B2Acral.setBounds(280, 490, 120, 23);

        jLabel175.setText("Conjungtiva Anemis :");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(620, 490, 110, 23);

        B2ConjungtivaAnemis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        B2ConjungtivaAnemis.setName("B2ConjungtivaAnemis"); // NOI18N
        B2ConjungtivaAnemis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B2ConjungtivaAnemisKeyPressed(evt);
            }
        });
        FormInput.add(B2ConjungtivaAnemis);
        B2ConjungtivaAnemis.setBounds(740, 490, 120, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("B3 :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(50, 520, 96, 23);

        jLabel176.setText("Kesadaran :");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(10, 540, 109, 23);

        jLabel177.setText("Berespon terhadap nyeri :");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(120, 570, 130, 23);

        B3TingkatKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        B3TingkatKesadaran.setName("B3TingkatKesadaran"); // NOI18N
        B3TingkatKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3TingkatKesadaranKeyPressed(evt);
            }
        });
        FormInput.add(B3TingkatKesadaran);
        B3TingkatKesadaran.setBounds(250, 570, 70, 23);

        B3Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Composmentis", "Somnolen", "Delirium", "Apatis", "Stupor", "Coma" }));
        B3Kesadaran.setName("B3Kesadaran"); // NOI18N
        B3Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(B3Kesadaran);
        B3Kesadaran.setBounds(120, 540, 110, 23);

        jLabel178.setText("Jam/Hari,");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(360, 540, 50, 23);

        jLabel179.setText("Tangisan :");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(330, 570, 60, 23);

        B3KetGangguanTidur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lebih Banyak Siang Hari", "Lebih Banyak Malam Hari", "Tidak Tidur", "Tidur Terus" }));
        B3KetGangguanTidur.setName("B3KetGangguanTidur"); // NOI18N
        B3KetGangguanTidur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3KetGangguanTidurKeyPressed(evt);
            }
        });
        FormInput.add(B3KetGangguanTidur);
        B3KetGangguanTidur.setBounds(710, 540, 150, 23);

        B3Tangisan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kuat", "Lemah", "Tidak Ada", "Melengking", "Merintih" }));
        B3Tangisan.setName("B3Tangisan"); // NOI18N
        B3Tangisan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3TangisanKeyPressed(evt);
            }
        });
        FormInput.add(B3Tangisan);
        B3Tangisan.setBounds(390, 570, 100, 23);

        jLabel180.setText("Gangguan Tidur :");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(410, 540, 90, 23);

        jLabel181.setText("cm,");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(680, 570, 20, 23);

        jLabel182.setText("Kelainan :");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(700, 570, 60, 23);

        B3Kelainan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        B3Kelainan.setName("B3Kelainan"); // NOI18N
        B3Kelainan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3KelainanKeyPressed(evt);
            }
        });
        FormInput.add(B3Kelainan);
        B3Kelainan.setBounds(770, 570, 90, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(RPS);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(180, 250, 260, 43);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("Kriteria Discharge Planning :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(40, 2450, 590, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("1. Umur > 65 Tahun");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(40, 2470, 260, 23);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("2. Keterbatasan Mobilitas");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(40, 2500, 260, 23);

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("3. Perawatan Atau Pengobatan Lanjutan");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(40, 2530, 260, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("4. Bantuan Untuk Melakukan Aktifitas Sehari-Hari");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(40, 2560, 260, 23);

        Kriteria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria1.setName("Kriteria1"); // NOI18N
        FormInput.add(Kriteria1);
        Kriteria1.setBounds(330, 2470, 80, 23);

        Kriteria2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria2.setName("Kriteria2"); // NOI18N
        FormInput.add(Kriteria2);
        Kriteria2.setBounds(330, 2500, 80, 23);

        Kriteria3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria3.setName("Kriteria3"); // NOI18N
        FormInput.add(Kriteria3);
        Kriteria3.setBounds(330, 2530, 80, 23);

        Kriteria4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria4.setName("Kriteria4"); // NOI18N
        FormInput.add(Kriteria4);
        Kriteria4.setBounds(330, 2560, 80, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Apabila salah satu jawaban YA dari kriteria diatas, maka akan dilanjutkan dengan perencanaan sebagai berikut :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(40, 2610, 570, 23);

        pilihan1.setBackground(new java.awt.Color(255, 255, 255));
        pilihan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan1.setForeground(new java.awt.Color(50, 50, 50));
        pilihan1.setText("Perawatan diri (Mandi, BAB, BAK)");
        pilihan1.setName("pilihan1"); // NOI18N
        FormInput.add(pilihan1);
        pilihan1.setBounds(50, 2640, 200, 19);

        pilihan2.setBackground(new java.awt.Color(255, 255, 255));
        pilihan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan2.setForeground(new java.awt.Color(50, 50, 50));
        pilihan2.setText("Pemantauan pemberian obat");
        pilihan2.setName("pilihan2"); // NOI18N
        FormInput.add(pilihan2);
        pilihan2.setBounds(50, 2660, 180, 19);

        pilihan3.setBackground(new java.awt.Color(255, 255, 255));
        pilihan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan3.setForeground(new java.awt.Color(50, 50, 50));
        pilihan3.setText("Pemantauan diet");
        pilihan3.setName("pilihan3"); // NOI18N
        FormInput.add(pilihan3);
        pilihan3.setBounds(50, 2680, 120, 19);

        pilihan4.setBackground(new java.awt.Color(255, 255, 255));
        pilihan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan4.setForeground(new java.awt.Color(50, 50, 50));
        pilihan4.setText("Bantuan medis / perawatan di rumah (Homecare)");
        pilihan4.setName("pilihan4"); // NOI18N
        FormInput.add(pilihan4);
        pilihan4.setBounds(50, 2700, 280, 19);

        pilihan5.setBackground(new java.awt.Color(255, 255, 255));
        pilihan5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan5.setForeground(new java.awt.Color(50, 50, 50));
        pilihan5.setText("Perawatan luka");
        pilihan5.setName("pilihan5"); // NOI18N
        FormInput.add(pilihan5);
        pilihan5.setBounds(330, 2640, 120, 19);

        pilihan6.setBackground(new java.awt.Color(255, 255, 255));
        pilihan6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan6.setForeground(new java.awt.Color(50, 50, 50));
        pilihan6.setText("Latihan fisik lanjutan");
        pilihan6.setName("pilihan6"); // NOI18N
        FormInput.add(pilihan6);
        pilihan6.setBounds(330, 2660, 130, 19);

        pilihan7.setBackground(new java.awt.Color(255, 255, 255));
        pilihan7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan7.setForeground(new java.awt.Color(50, 50, 50));
        pilihan7.setText("Pendampingan tenaga khusus di rumah");
        pilihan7.setName("pilihan7"); // NOI18N
        FormInput.add(pilihan7);
        pilihan7.setBounds(330, 2680, 220, 19);

        pilihan8.setBackground(new java.awt.Color(255, 255, 255));
        pilihan8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan8.setForeground(new java.awt.Color(50, 50, 50));
        pilihan8.setText("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)");
        pilihan8.setName("pilihan8"); // NOI18N
        FormInput.add(pilihan8);
        pilihan8.setBounds(330, 2700, 370, 19);

        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel289.setText("X. SKRINING PERENCANAAN PEMULANGAN");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(10, 2430, 380, 23);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        FormInput.add(jSeparator14);
        jSeparator14.setBounds(0, 2730, 880, 1);

        jLabel206.setText("Alat Bantu :");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(460, 410, 80, 23);

        jLabel207.setText("O2");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(540, 410, 20, 23);

        jLabel208.setText("S1/S2 Tunggal :");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(400, 490, 90, 23);

        B2S1S2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        B2S1S2.setName("B2S1S2"); // NOI18N
        B2S1S2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B2S1S2KeyPressed(evt);
            }
        });
        FormInput.add(B2S1S2);
        B2S1S2.setBounds(490, 490, 120, 23);

        jLabel209.setText("Tingkat Kesadaran :");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(10, 570, 109, 23);

        B3JamIstirahatTidur.setFocusTraversalPolicyProvider(true);
        B3JamIstirahatTidur.setName("B3JamIstirahatTidur"); // NOI18N
        B3JamIstirahatTidur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3JamIstirahatTidurKeyPressed(evt);
            }
        });
        FormInput.add(B3JamIstirahatTidur);
        B3JamIstirahatTidur.setBounds(310, 540, 50, 23);

        jLabel183.setText("Istirahat Tidur :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(230, 540, 80, 23);

        B3GangguanTidur.setFocusTraversalPolicyProvider(true);
        B3GangguanTidur.setName("B3GangguanTidur"); // NOI18N
        B3GangguanTidur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3GangguanTidurKeyPressed(evt);
            }
        });
        FormInput.add(B3GangguanTidur);
        B3GangguanTidur.setBounds(500, 540, 200, 23);

        jLabel184.setText("Kepala :");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(500, 570, 50, 23);

        B3LingkarKepala.setFocusTraversalPolicyProvider(true);
        B3LingkarKepala.setName("B3LingkarKepala"); // NOI18N
        B3LingkarKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3LingkarKepalaKeyPressed(evt);
            }
        });
        FormInput.add(B3LingkarKepala);
        B3LingkarKepala.setBounds(630, 570, 50, 23);

        jLabel185.setText("Lingkar Kepala");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(550, 570, 80, 23);

        jLabel186.setText("Ubun-ubun :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(50, 600, 70, 23);

        B3UbunUbun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Datar", "Cekung", "Cembung" }));
        B3UbunUbun.setName("B3UbunUbun"); // NOI18N
        B3UbunUbun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3UbunUbunKeyPressed(evt);
            }
        });
        FormInput.add(B3UbunUbun);
        B3UbunUbun.setBounds(120, 600, 100, 23);

        jLabel210.setText("Pupil :");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(220, 600, 40, 23);

        jLabel187.setText("Bereaksi Terhadap Cahaya :");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(260, 600, 140, 23);

        B3Pupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        B3Pupil.setName("B3Pupil"); // NOI18N
        B3Pupil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3PupilKeyPressed(evt);
            }
        });
        FormInput.add(B3Pupil);
        B3Pupil.setBounds(400, 600, 70, 23);

        jLabel188.setText("Sklera Mata :");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(470, 600, 70, 23);

        B3SkleraMata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ikterus", "Tidak Ikterus" }));
        B3SkleraMata.setName("B3SkleraMata"); // NOI18N
        B3SkleraMata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3SkleraMataKeyPressed(evt);
            }
        });
        FormInput.add(B3SkleraMata);
        B3SkleraMata.setBounds(540, 600, 100, 23);

        B3Gerakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lemah", "Paralise", "Aktif" }));
        B3Gerakan.setName("B3Gerakan"); // NOI18N
        B3Gerakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3GerakanKeyPressed(evt);
            }
        });
        FormInput.add(B3Gerakan);
        B3Gerakan.setBounds(700, 600, 160, 23);

        jLabel189.setText("Gerakan :");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(640, 600, 60, 23);

        jLabel190.setText("Panca Indra :");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(50, 630, 70, 23);

        B3PancaIndra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Gangguan", "Ada Gangguan" }));
        B3PancaIndra.setName("B3PancaIndra"); // NOI18N
        B3PancaIndra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3PancaIndraKeyPressed(evt);
            }
        });
        FormInput.add(B3PancaIndra);
        B3PancaIndra.setBounds(120, 630, 140, 23);

        jLabel192.setText("Kejang :");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(470, 630, 60, 23);

        B3Kejang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Subtle", "Tonik/Klonik" }));
        B3Kejang.setName("B3Kejang"); // NOI18N
        B3Kejang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3KejangKeyPressed(evt);
            }
        });
        FormInput.add(B3Kejang);
        B3Kejang.setBounds(530, 630, 100, 23);

        jLabel193.setText("Reflek Rooting :");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(630, 630, 90, 23);

        B3ReflekRooting.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        B3ReflekRooting.setName("B3ReflekRooting"); // NOI18N
        B3ReflekRooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3ReflekRootingKeyPressed(evt);
            }
        });
        FormInput.add(B3ReflekRooting);
        B3ReflekRooting.setBounds(720, 630, 140, 23);

        B3KetPancaIndra.setFocusTraversalPolicyProvider(true);
        B3KetPancaIndra.setName("B3KetPancaIndra"); // NOI18N
        B3KetPancaIndra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B3KetPancaIndraKeyPressed(evt);
            }
        });
        FormInput.add(B3KetPancaIndra);
        B3KetPancaIndra.setBounds(270, 630, 200, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("B4 :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(50, 660, 96, 23);

        jLabel191.setText("Kebersihan :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(10, 680, 109, 23);

        B4Kebersihan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Kotor" }));
        B4Kebersihan.setName("B4Kebersihan"); // NOI18N
        B4Kebersihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4KebersihanKeyPressed(evt);
            }
        });
        FormInput.add(B4Kebersihan);
        B4Kebersihan.setBounds(120, 680, 200, 23);

        jLabel194.setText("Sekret :");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(320, 680, 50, 23);

        B4ProduksiUrine.setFocusTraversalPolicyProvider(true);
        B4ProduksiUrine.setName("B4ProduksiUrine"); // NOI18N
        B4ProduksiUrine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4ProduksiUrineKeyPressed(evt);
            }
        });
        FormInput.add(B4ProduksiUrine);
        B4ProduksiUrine.setBounds(770, 680, 50, 23);

        B4KetSekret.setFocusTraversalPolicyProvider(true);
        B4KetSekret.setName("B4KetSekret"); // NOI18N
        B4KetSekret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4KetSekretKeyPressed(evt);
            }
        });
        FormInput.add(B4KetSekret);
        B4KetSekret.setBounds(480, 680, 200, 23);

        jLabel211.setText("Produksi Urine :");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(690, 680, 80, 23);

        jLabel197.setText("Cc/Jam");
        jLabel197.setName("jLabel197"); // NOI18N
        FormInput.add(jLabel197);
        jLabel197.setBounds(820, 680, 40, 23);

        B4Sekret.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        B4Sekret.setName("B4Sekret"); // NOI18N
        B4Sekret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4SekretKeyPressed(evt);
            }
        });
        FormInput.add(B4Sekret);
        B4Sekret.setBounds(370, 680, 100, 23);

        jLabel198.setText("Warna :");
        jLabel198.setName("jLabel198"); // NOI18N
        FormInput.add(jLabel198);
        jLabel198.setBounds(60, 710, 60, 23);

        B4Warna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jernih", "Keruh" }));
        B4Warna.setName("B4Warna"); // NOI18N
        B4Warna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4WarnaKeyPressed(evt);
            }
        });
        FormInput.add(B4Warna);
        B4Warna.setBounds(120, 710, 100, 23);

        jLabel203.setText("Gangguan :");
        jLabel203.setName("jLabel203"); // NOI18N
        FormInput.add(jLabel203);
        jLabel203.setBounds(490, 710, 70, 23);

        B4Gangguan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Anuri", "Oligari", "Retensi", "Inkontinensia", "Nokturia" }));
        B4Gangguan.setName("B4Gangguan"); // NOI18N
        B4Gangguan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4GangguanKeyPressed(evt);
            }
        });
        FormInput.add(B4Gangguan);
        B4Gangguan.setBounds(560, 710, 100, 23);

        jLabel214.setText("Alat Bantu :");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(650, 710, 70, 23);

        B4AlatBantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Kateter", "Cystotomi" }));
        B4AlatBantu.setName("B4AlatBantu"); // NOI18N
        B4AlatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4AlatBantuKeyPressed(evt);
            }
        });
        FormInput.add(B4AlatBantu);
        B4AlatBantu.setBounds(720, 710, 140, 23);

        B4KetWarna.setFocusTraversalPolicyProvider(true);
        B4KetWarna.setName("B4KetWarna"); // NOI18N
        B4KetWarna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B4KetWarnaKeyPressed(evt);
            }
        });
        FormInput.add(B4KetWarna);
        B4KetWarna.setBounds(230, 710, 260, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("B5 :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(50, 740, 96, 23);

        jLabel195.setText("Nafsu Makan :");
        jLabel195.setName("jLabel195"); // NOI18N
        FormInput.add(jLabel195);
        jLabel195.setBounds(10, 760, 109, 23);

        B5NafsuMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Menurun" }));
        B5NafsuMakan.setName("B5NafsuMakan"); // NOI18N
        B5NafsuMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5NafsuMakanKeyPressed(evt);
            }
        });
        FormInput.add(B5NafsuMakan);
        B5NafsuMakan.setBounds(120, 760, 110, 23);

        jLabel196.setText("Frekuensi :");
        jLabel196.setName("jLabel196"); // NOI18N
        FormInput.add(jLabel196);
        jLabel196.setBounds(230, 760, 60, 23);

        B5FrekuensiMakan.setFocusTraversalPolicyProvider(true);
        B5FrekuensiMakan.setName("B5FrekuensiMakan"); // NOI18N
        B5FrekuensiMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5FrekuensiMakanKeyPressed(evt);
            }
        });
        FormInput.add(B5FrekuensiMakan);
        B5FrekuensiMakan.setBounds(290, 760, 50, 23);

        jLabel199.setText("X/Hari,");
        jLabel199.setName("jLabel199"); // NOI18N
        FormInput.add(jLabel199);
        jLabel199.setBounds(340, 760, 40, 23);

        jLabel200.setText("Porsi Makan :");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(380, 760, 70, 23);

        B5PorsiMakan.setFocusTraversalPolicyProvider(true);
        B5PorsiMakan.setName("B5PorsiMakan"); // NOI18N
        B5PorsiMakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5PorsiMakanKeyPressed(evt);
            }
        });
        FormInput.add(B5PorsiMakan);
        B5PorsiMakan.setBounds(450, 760, 110, 23);

        jLabel212.setText("Minum :");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(560, 760, 50, 23);

        jLabel201.setText("Cc/Hari,");
        jLabel201.setName("jLabel201"); // NOI18N
        FormInput.add(jLabel201);
        jLabel201.setBounds(680, 760, 40, 23);

        jLabel202.setText("BAB :");
        jLabel202.setName("jLabel202"); // NOI18N
        FormInput.add(jLabel202);
        jLabel202.setBounds(390, 790, 40, 23);

        B5KetWarnaBAB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Darah", "Lendir" }));
        B5KetWarnaBAB.setName("B5KetWarnaBAB"); // NOI18N
        B5KetWarnaBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5KetWarnaBABKeyPressed(evt);
            }
        });
        FormInput.add(B5KetWarnaBAB);
        B5KetWarnaBAB.setBounds(760, 790, 100, 23);

        jLabel204.setText("Konsisten :");
        jLabel204.setName("jLabel204"); // NOI18N
        FormInput.add(jLabel204);
        jLabel204.setBounds(530, 790, 60, 23);

        B5Konsisten.setFocusTraversalPolicyProvider(true);
        B5Konsisten.setName("B5Konsisten"); // NOI18N
        B5Konsisten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5KonsistenKeyPressed(evt);
            }
        });
        FormInput.add(B5Konsisten);
        B5Konsisten.setBounds(590, 790, 50, 23);

        jLabel213.setText("X/Hari,");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(490, 790, 40, 23);

        jLabel215.setText("Warna :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(640, 790, 50, 23);

        jLabel216.setText("Cara Minum :");
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(50, 790, 70, 23);

        B5CaraMinum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menetek", "Peroral", "Sonde Lambung", "Muntah", "Puasa" }));
        B5CaraMinum.setName("B5CaraMinum"); // NOI18N
        B5CaraMinum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5CaraMinumKeyPressed(evt);
            }
        });
        FormInput.add(B5CaraMinum);
        B5CaraMinum.setBounds(120, 790, 150, 23);

        jLabel217.setText("Perut :");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(80, 820, 40, 23);

        B5Perut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tegang", "Kembung", "Nyeri Tekan", "Supel" }));
        B5Perut.setName("B5Perut"); // NOI18N
        B5Perut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5PerutKeyPressed(evt);
            }
        });
        FormInput.add(B5Perut);
        B5Perut.setBounds(120, 820, 80, 23);

        jLabel219.setText("Peristaltik :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(200, 820, 70, 23);

        jLabel220.setText("Kelainan :");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(540, 820, 60, 23);

        B5Kelainan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Labio Schizis", "Palato Schizis", "Gnato Schizis", "Tidak" }));
        B5Kelainan.setName("B5Kelainan"); // NOI18N
        B5Kelainan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5KelainanKeyPressed(evt);
            }
        });
        FormInput.add(B5Kelainan);
        B5Kelainan.setBounds(600, 820, 120, 23);

        jLabel221.setText("Anus :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(270, 790, 40, 23);

        B5Anus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        B5Anus.setName("B5Anus"); // NOI18N
        B5Anus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5AnusKeyPressed(evt);
            }
        });
        FormInput.add(B5Anus);
        B5Anus.setBounds(310, 790, 80, 23);

        jLabel222.setText("Lidah :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(720, 820, 40, 23);

        B5Lidah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lembab Kering", "Kotor" }));
        B5Lidah.setName("B5Lidah"); // NOI18N
        B5Lidah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5LidahKeyPressed(evt);
            }
        });
        FormInput.add(B5Lidah);
        B5Lidah.setBounds(760, 820, 100, 23);

        jLabel223.setText("Reflek Rooting :");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(370, 820, 90, 23);

        B5ReflekRooting.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ada", "Tidak Ada" }));
        B5ReflekRooting.setName("B5ReflekRooting"); // NOI18N
        B5ReflekRooting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5ReflekRootingKeyPressed(evt);
            }
        });
        FormInput.add(B5ReflekRooting);
        B5ReflekRooting.setBounds(460, 820, 80, 23);

        B5Minum.setFocusTraversalPolicyProvider(true);
        B5Minum.setName("B5Minum"); // NOI18N
        B5Minum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5MinumKeyPressed(evt);
            }
        });
        FormInput.add(B5Minum);
        B5Minum.setBounds(610, 760, 70, 23);

        jLabel224.setText("Jenis :");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(720, 760, 40, 23);

        B5JenisMinum.setFocusTraversalPolicyProvider(true);
        B5JenisMinum.setName("B5JenisMinum"); // NOI18N
        B5JenisMinum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5JenisMinumKeyPressed(evt);
            }
        });
        FormInput.add(B5JenisMinum);
        B5JenisMinum.setBounds(760, 760, 100, 23);

        B5BAB.setFocusTraversalPolicyProvider(true);
        B5BAB.setName("B5BAB"); // NOI18N
        B5BAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5BABKeyPressed(evt);
            }
        });
        FormInput.add(B5BAB);
        B5BAB.setBounds(430, 790, 64, 23);

        B5WarnaBAB.setFocusTraversalPolicyProvider(true);
        B5WarnaBAB.setName("B5WarnaBAB"); // NOI18N
        B5WarnaBAB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5WarnaBABKeyPressed(evt);
            }
        });
        FormInput.add(B5WarnaBAB);
        B5WarnaBAB.setBounds(690, 790, 70, 23);

        B5Peristaltik.setFocusTraversalPolicyProvider(true);
        B5Peristaltik.setName("B5Peristaltik"); // NOI18N
        B5Peristaltik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5PeristaltikKeyPressed(evt);
            }
        });
        FormInput.add(B5Peristaltik);
        B5Peristaltik.setBounds(270, 820, 50, 23);

        jLabel205.setText("X/Menit,");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(320, 820, 50, 23);

        jLabel218.setText("Selaput Lender :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(30, 850, 90, 23);

        B5SelaputLender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kering", "Lesi", "Lembab" }));
        B5SelaputLender.setName("B5SelaputLender"); // NOI18N
        B5SelaputLender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B5SelaputLenderKeyPressed(evt);
            }
        });
        FormInput.add(B5SelaputLender);
        B5SelaputLender.setBounds(120, 850, 80, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("B6 :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(50, 880, 96, 23);

        jLabel225.setText("Pergerakan Sendi :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(10, 900, 109, 23);

        B6PergerakanSendi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bebas", "Terbatas" }));
        B6PergerakanSendi.setName("B6PergerakanSendi"); // NOI18N
        B6PergerakanSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6PergerakanSendiKeyPressed(evt);
            }
        });
        FormInput.add(B6PergerakanSendi);
        B6PergerakanSendi.setBounds(120, 900, 110, 23);

        B6KetPergerakanSendi.setFocusTraversalPolicyProvider(true);
        B6KetPergerakanSendi.setName("B6KetPergerakanSendi"); // NOI18N
        B6KetPergerakanSendi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KetPergerakanSendiKeyPressed(evt);
            }
        });
        FormInput.add(B6KetPergerakanSendi);
        B6KetPergerakanSendi.setBounds(230, 900, 110, 23);

        jLabel232.setText("Warna Kulit :");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(340, 900, 70, 23);

        B6WarnaKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pucat", "Ikterus", "Sianotik", "Hiperpigmentasi", "Kemerahan" }));
        B6WarnaKulit.setName("B6WarnaKulit"); // NOI18N
        B6WarnaKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6WarnaKulitKeyPressed(evt);
            }
        });
        FormInput.add(B6WarnaKulit);
        B6WarnaKulit.setBounds(410, 900, 150, 23);

        jLabel233.setText("Kepala :");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(60, 930, 50, 23);

        B6Kepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Kotor", "Bau" }));
        B6Kepala.setName("B6Kepala"); // NOI18N
        B6Kepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KepalaKeyPressed(evt);
            }
        });
        FormInput.add(B6Kepala);
        B6Kepala.setBounds(120, 930, 80, 23);

        jLabel234.setText("Turgor :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(380, 930, 40, 23);

        jLabel236.setText("Lokasi :");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(670, 930, 50, 23);

        jLabel238.setText("Intergitas Kulit :");
        jLabel238.setName("jLabel238"); // NOI18N
        FormInput.add(jLabel238);
        jLabel238.setBounds(560, 900, 90, 23);

        B6IntergitasKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Utuh", "Kering", "Rash", "Bullae", "Pustula", "Kemerahan", "Petchiae", "Lesi" }));
        B6IntergitasKulit.setName("B6IntergitasKulit"); // NOI18N
        B6IntergitasKulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6IntergitasKulitKeyPressed(evt);
            }
        });
        FormInput.add(B6IntergitasKulit);
        B6IntergitasKulit.setBounds(650, 900, 210, 23);

        jLabel245.setText("Tali Pusat :");
        jLabel245.setName("jLabel245"); // NOI18N
        FormInput.add(jLabel245);
        jLabel245.setBounds(200, 930, 70, 23);

        B6TaliPusat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kering", "Basah", "Pus", "Kemerahan", "Bau" }));
        B6TaliPusat.setName("B6TaliPusat"); // NOI18N
        B6TaliPusat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6TaliPusatKeyPressed(evt);
            }
        });
        FormInput.add(B6TaliPusat);
        B6TaliPusat.setBounds(270, 930, 100, 23);

        B6Tugor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Jelek" }));
        B6Tugor.setName("B6Tugor"); // NOI18N
        B6Tugor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6TugorKeyPressed(evt);
            }
        });
        FormInput.add(B6Tugor);
        B6Tugor.setBounds(420, 930, 100, 23);

        B6Odem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        B6Odem.setName("B6Odem"); // NOI18N
        B6Odem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6OdemKeyPressed(evt);
            }
        });
        FormInput.add(B6Odem);
        B6Odem.setBounds(570, 930, 100, 23);

        B6KetOdem.setFocusTraversalPolicyProvider(true);
        B6KetOdem.setName("B6KetOdem"); // NOI18N
        B6KetOdem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KetOdemKeyPressed(evt);
            }
        });
        FormInput.add(B6KetOdem);
        B6KetOdem.setBounds(720, 930, 140, 23);

        jLabel237.setText("Odem :");
        jLabel237.setName("jLabel237"); // NOI18N
        FormInput.add(jLabel237);
        jLabel237.setBounds(520, 930, 50, 23);

        jLabel239.setText("Kekuatan Otot :");
        jLabel239.setName("jLabel239"); // NOI18N
        FormInput.add(jLabel239);
        jLabel239.setBounds(20, 960, 90, 23);

        B6KekuatanOtotKiriAtas.setFocusTraversalPolicyProvider(true);
        B6KekuatanOtotKiriAtas.setName("B6KekuatanOtotKiriAtas"); // NOI18N
        B6KekuatanOtotKiriAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KekuatanOtotKiriAtasKeyPressed(evt);
            }
        });
        FormInput.add(B6KekuatanOtotKiriAtas);
        B6KekuatanOtotKiriAtas.setBounds(120, 960, 140, 23);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        FormInput.add(jSeparator15);
        jSeparator15.setBounds(265, 960, 1, 80);

        B6KekuatanOtotKananAtas.setFocusTraversalPolicyProvider(true);
        B6KekuatanOtotKananAtas.setName("B6KekuatanOtotKananAtas"); // NOI18N
        B6KekuatanOtotKananAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KekuatanOtotKananAtasKeyPressed(evt);
            }
        });
        FormInput.add(B6KekuatanOtotKananAtas);
        B6KekuatanOtotKananAtas.setBounds(270, 960, 140, 23);

        B6KekuatanOtotKiriBawah.setFocusTraversalPolicyProvider(true);
        B6KekuatanOtotKiriBawah.setName("B6KekuatanOtotKiriBawah"); // NOI18N
        B6KekuatanOtotKiriBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KekuatanOtotKiriBawahKeyPressed(evt);
            }
        });
        FormInput.add(B6KekuatanOtotKiriBawah);
        B6KekuatanOtotKiriBawah.setBounds(120, 1000, 140, 23);

        B6KekuatanOtotKananBawah.setFocusTraversalPolicyProvider(true);
        B6KekuatanOtotKananBawah.setName("B6KekuatanOtotKananBawah"); // NOI18N
        B6KekuatanOtotKananBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B6KekuatanOtotKananBawahKeyPressed(evt);
            }
        });
        FormInput.add(B6KekuatanOtotKananBawah);
        B6KekuatanOtotKananBawah.setBounds(270, 1000, 140, 23);

        jSeparator16.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator16.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator16.setName("jSeparator16"); // NOI18N
        FormInput.add(jSeparator16);
        jSeparator16.setBounds(120, 990, 280, 1);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("Alat Genital :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(40, 1040, 96, 23);

        jLabel226.setText("Laki-laki :");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(10, 1060, 109, 23);

        AlatGenitalLaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Testis Sudah/Belum Turun", "Rugae Jelas/Tidak Jelas", "Hipospadi Ada/Tidak Ada" }));
        AlatGenitalLaki.setName("AlatGenitalLaki"); // NOI18N
        AlatGenitalLaki.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatGenitalLakiKeyPressed(evt);
            }
        });
        FormInput.add(AlatGenitalLaki);
        AlatGenitalLaki.setBounds(120, 1060, 190, 23);

        jLabel235.setText("Perempuan :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(320, 1060, 70, 23);

        AlatGenitalPerampuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Labia mayor sudah menutupi labia minor", "Labia mayor dan minor sama menonjol" }));
        AlatGenitalPerampuan.setName("AlatGenitalPerampuan"); // NOI18N
        AlatGenitalPerampuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatGenitalPerampuanKeyPressed(evt);
            }
        });
        FormInput.add(AlatGenitalPerampuan);
        AlatGenitalPerampuan.setBounds(390, 1060, 240, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("Derajat Ikterus :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(40, 1090, 96, 23);

        jLabel227.setText("Perkiraan Kadar Bilirubin :");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(570, 1110, 140, 23);

        DerajatIkterus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "I", "II", "III", "IV", "V" }));
        DerajatIkterus.setName("DerajatIkterus"); // NOI18N
        DerajatIkterus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DerajatIkterusItemStateChanged(evt);
            }
        });
        DerajatIkterus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DerajatIkterusKeyPressed(evt);
            }
        });
        FormInput.add(DerajatIkterus);
        DerajatIkterus.setBounds(120, 1110, 50, 23);

        DaerahIkterus.setEditable(false);
        DaerahIkterus.setText("Tidak ada ikterus");
        DaerahIkterus.setFocusTraversalPolicyProvider(true);
        DaerahIkterus.setName("DaerahIkterus"); // NOI18N
        DaerahIkterus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DaerahIkterusKeyPressed(evt);
            }
        });
        FormInput.add(DaerahIkterus);
        DaerahIkterus.setBounds(270, 1110, 300, 23);

        KadarBilirubin.setEditable(false);
        KadarBilirubin.setFocusTraversalPolicyProvider(true);
        KadarBilirubin.setName("KadarBilirubin"); // NOI18N
        KadarBilirubin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KadarBilirubinKeyPressed(evt);
            }
        });
        FormInput.add(KadarBilirubin);
        KadarBilirubin.setBounds(710, 1110, 140, 23);

        jLabel228.setText("Derajat Ikterus :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(10, 1110, 109, 23);

        jLabel229.setText("Daerah Ikterus :");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(170, 1110, 100, 23);

        jLabel49.setText("Penilaian Apgar Score :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(0, 1140, 175, 20);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        PenilaianApgarScore.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PenilaianApgarScore.setColumns(20);
        PenilaianApgarScore.setRows(5);
        PenilaianApgarScore.setName("PenilaianApgarScore"); // NOI18N
        PenilaianApgarScore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenilaianApgarScoreKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(PenilaianApgarScore);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(180, 1140, 260, 43);

        jLabel50.setText("Penilaian Down Score :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(440, 1140, 150, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        PenilaianDownScore.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PenilaianDownScore.setColumns(20);
        PenilaianDownScore.setRows(5);
        PenilaianDownScore.setName("PenilaianDownScore"); // NOI18N
        PenilaianDownScore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenilaianDownScoreKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(PenilaianDownScore);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(590, 1140, 260, 42);

        label14.setText("Pengkaji 1 :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(74, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(176, 40, 210, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(388, 40, 28, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(438, 70, 70, 23);

        jLabel36.setText("Anamnesis :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 100, 70, 23);

        Anamnesis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Autoanamnesis", "Alloanamnesis" }));
        Anamnesis.setName("Anamnesis"); // NOI18N
        Anamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(Anamnesis);
        Anamnesis.setBounds(74, 100, 130, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024 09:25:26" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(512, 70, 135, 23);

        NmPetugas2.setEditable(false);
        NmPetugas2.setName("NmPetugas2"); // NOI18N
        NmPetugas2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas2);
        NmPetugas2.setBounds(614, 40, 210, 23);

        BtnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas2.setMnemonic('2');
        BtnPetugas2.setToolTipText("Alt+2");
        BtnPetugas2.setName("BtnPetugas2"); // NOI18N
        BtnPetugas2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas2ActionPerformed(evt);
            }
        });
        BtnPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugas2KeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas2);
        BtnPetugas2.setBounds(826, 40, 28, 23);

        KdPetugas2.setEditable(false);
        KdPetugas2.setName("KdPetugas2"); // NOI18N
        KdPetugas2.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugas2KeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas2);
        KdPetugas2.setBounds(512, 40, 100, 23);

        label15.setText("Pengkaji 2 :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(438, 40, 70, 23);

        label16.setText("DPJP :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 70, 70, 23);

        KdDPJP.setEditable(false);
        KdDPJP.setName("KdDPJP"); // NOI18N
        KdDPJP.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDPJPKeyPressed(evt);
            }
        });
        FormInput.add(KdDPJP);
        KdDPJP.setBounds(74, 70, 110, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setName("NmDPJP"); // NOI18N
        NmDPJP.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(186, 70, 230, 23);

        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('2');
        BtnDPJP.setToolTipText("Alt+2");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        BtnDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDPJPKeyPressed(evt);
            }
        });
        FormInput.add(BtnDPJP);
        BtnDPJP.setBounds(418, 70, 28, 23);

        TibadiRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Box Bayi", "Incubator", "Transport" }));
        TibadiRuang.setName("TibadiRuang"); // NOI18N
        TibadiRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TibadiRuangKeyPressed(evt);
            }
        });
        FormInput.add(TibadiRuang);
        TibadiRuang.setBounds(516, 100, 155, 23);

        jLabel51.setText("Tiba Di Ruang Rawat :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(392, 100, 120, 23);

        CaraMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UBS", "VK", "IGD", "Poli", "Lain-lain" }));
        CaraMasuk.setName("CaraMasuk"); // NOI18N
        CaraMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraMasukKeyPressed(evt);
            }
        });
        FormInput.add(CaraMasuk);
        CaraMasuk.setBounds(759, 100, 95, 23);

        jLabel52.setText("Cara Masuk :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(685, 100, 70, 23);

        MacamKasus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Trauma", "Non Trauma" }));
        MacamKasus.setSelectedIndex(1);
        MacamKasus.setName("MacamKasus"); // NOI18N
        MacamKasus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MacamKasusKeyPressed(evt);
            }
        });
        FormInput.add(MacamKasus);
        MacamKasus.setBounds(742, 70, 112, 23);

        jLabel53.setText("Macam Kasus :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(658, 70, 80, 23);

        KetAnamnesis.setFocusTraversalPolicyProvider(true);
        KetAnamnesis.setName("KetAnamnesis"); // NOI18N
        KetAnamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetAnamnesisKeyPressed(evt);
            }
        });
        FormInput.add(KetAnamnesis);
        KetAnamnesis.setBounds(208, 100, 175, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Skala Humpty Dumpty :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(40, 1920, 120, 23);

        jLabel240.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel240.setText("1. Usia");
        jLabel240.setName("jLabel240"); // NOI18N
        FormInput.add(jLabel240);
        jLabel240.setBounds(60, 1940, 300, 23);

        jLabel241.setText("Skala :");
        jLabel241.setName("jLabel241"); // NOI18N
        FormInput.add(jLabel241);
        jLabel241.setBounds(320, 1940, 80, 23);

        SkalaHumptyDumpty1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "< 3 Tahun", "3-7 Tahun", "7-13 Tahun", "13 Tahun" }));
        SkalaHumptyDumpty1.setName("SkalaHumptyDumpty1"); // NOI18N
        SkalaHumptyDumpty1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty1ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty1);
        SkalaHumptyDumpty1.setBounds(400, 1940, 350, 23);

        jLabel242.setText("Nilai :");
        jLabel242.setName("jLabel242"); // NOI18N
        FormInput.add(jLabel242);
        jLabel242.setBounds(710, 1940, 75, 23);

        NilaiHumptyDumpty1.setEditable(false);
        NilaiHumptyDumpty1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty1.setText("4");
        NilaiHumptyDumpty1.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty1.setName("NilaiHumptyDumpty1"); // NOI18N
        FormInput.add(NilaiHumptyDumpty1);
        NilaiHumptyDumpty1.setBounds(790, 1940, 60, 23);

        jLabel243.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel243.setText("2. Jenis Kelamin");
        jLabel243.setName("jLabel243"); // NOI18N
        FormInput.add(jLabel243);
        jLabel243.setBounds(60, 1970, 300, 23);

        jLabel244.setText("Skala :");
        jLabel244.setName("jLabel244"); // NOI18N
        FormInput.add(jLabel244);
        jLabel244.setBounds(320, 1970, 80, 23);

        SkalaHumptyDumpty2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        SkalaHumptyDumpty2.setName("SkalaHumptyDumpty2"); // NOI18N
        SkalaHumptyDumpty2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty2ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty2);
        SkalaHumptyDumpty2.setBounds(400, 1970, 350, 23);

        jLabel246.setText("Nilai :");
        jLabel246.setName("jLabel246"); // NOI18N
        FormInput.add(jLabel246);
        jLabel246.setBounds(710, 1970, 75, 23);

        NilaiHumptyDumpty2.setEditable(false);
        NilaiHumptyDumpty2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty2.setText("2");
        NilaiHumptyDumpty2.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty2.setName("NilaiHumptyDumpty2"); // NOI18N
        FormInput.add(NilaiHumptyDumpty2);
        NilaiHumptyDumpty2.setBounds(790, 1970, 60, 23);

        jLabel247.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel247.setText("3. Diagnosis");
        jLabel247.setName("jLabel247"); // NOI18N
        FormInput.add(jLabel247);
        jLabel247.setBounds(60, 2000, 300, 23);

        jLabel248.setText("Skala :");
        jLabel248.setName("jLabel248"); // NOI18N
        FormInput.add(jLabel248);
        jLabel248.setBounds(320, 2000, 80, 23);

        SkalaHumptyDumpty3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diagnosis neurologi", "Perubahan oksigenasi", "Gangguan prilaku", "Diagnosis lainnya" }));
        SkalaHumptyDumpty3.setName("SkalaHumptyDumpty3"); // NOI18N
        SkalaHumptyDumpty3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty3ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty3);
        SkalaHumptyDumpty3.setBounds(400, 2000, 350, 23);

        jLabel249.setText("Nilai :");
        jLabel249.setName("jLabel249"); // NOI18N
        FormInput.add(jLabel249);
        jLabel249.setBounds(710, 2000, 75, 23);

        NilaiHumptyDumpty3.setEditable(false);
        NilaiHumptyDumpty3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty3.setText("4");
        NilaiHumptyDumpty3.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty3.setName("NilaiHumptyDumpty3"); // NOI18N
        NilaiHumptyDumpty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NilaiHumptyDumpty3ActionPerformed(evt);
            }
        });
        FormInput.add(NilaiHumptyDumpty3);
        NilaiHumptyDumpty3.setBounds(790, 2000, 60, 23);

        jLabel250.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel250.setText("4. Gangguan Kognitif");
        jLabel250.setName("jLabel250"); // NOI18N
        FormInput.add(jLabel250);
        jLabel250.setBounds(60, 2030, 300, 23);

        jLabel251.setText("Skala :");
        jLabel251.setName("jLabel251"); // NOI18N
        FormInput.add(jLabel251);
        jLabel251.setBounds(320, 2030, 80, 23);

        SkalaHumptyDumpty4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak menyadari keterbatasan diri", "Lupa akan adanya keterbatasan", "Orientasi baik" }));
        SkalaHumptyDumpty4.setName("SkalaHumptyDumpty4"); // NOI18N
        SkalaHumptyDumpty4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty4ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty4);
        SkalaHumptyDumpty4.setBounds(400, 2030, 350, 23);

        jLabel252.setText("Nilai :");
        jLabel252.setName("jLabel252"); // NOI18N
        FormInput.add(jLabel252);
        jLabel252.setBounds(710, 2030, 75, 23);

        NilaiHumptyDumpty4.setEditable(false);
        NilaiHumptyDumpty4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty4.setText("3");
        NilaiHumptyDumpty4.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty4.setName("NilaiHumptyDumpty4"); // NOI18N
        FormInput.add(NilaiHumptyDumpty4);
        NilaiHumptyDumpty4.setBounds(790, 2030, 60, 23);

        jLabel253.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel253.setText("5. Faktor Lingkungan");
        jLabel253.setName("jLabel253"); // NOI18N
        FormInput.add(jLabel253);
        jLabel253.setBounds(60, 2060, 300, 23);

        jLabel254.setText("Skala :");
        jLabel254.setName("jLabel254"); // NOI18N
        FormInput.add(jLabel254);
        jLabel254.setBounds(320, 2060, 80, 23);

        SkalaHumptyDumpty5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Riwayat jatuh/ditempat tidur dewasa", "Pasien/bayi menggunakan alat bantu/tempat tidur bayi", "Pasien/bayi diletakan di tempat tidur standart", "Area luar RS" }));
        SkalaHumptyDumpty5.setName("SkalaHumptyDumpty5"); // NOI18N
        SkalaHumptyDumpty5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty5ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty5);
        SkalaHumptyDumpty5.setBounds(400, 2060, 350, 23);

        jLabel255.setText("Nilai :");
        jLabel255.setName("jLabel255"); // NOI18N
        FormInput.add(jLabel255);
        jLabel255.setBounds(710, 2060, 75, 23);

        NilaiHumptyDumpty5.setEditable(false);
        NilaiHumptyDumpty5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty5.setText("4");
        NilaiHumptyDumpty5.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty5.setName("NilaiHumptyDumpty5"); // NOI18N
        FormInput.add(NilaiHumptyDumpty5);
        NilaiHumptyDumpty5.setBounds(790, 2060, 60, 23);

        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel256.setText("6. Respon Terhadap Pembedahan Sedasi / Anastesi");
        jLabel256.setName("jLabel256"); // NOI18N
        FormInput.add(jLabel256);
        jLabel256.setBounds(60, 2090, 300, 23);

        jLabel257.setText("Skala :");
        jLabel257.setName("jLabel257"); // NOI18N
        FormInput.add(jLabel257);
        jLabel257.setBounds(320, 2090, 80, 23);

        SkalaHumptyDumpty6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dalam 24 jam", "Dalam 48 jam", "1>48 jam/tidak menjalani pembedahan/sedasi/anestesi" }));
        SkalaHumptyDumpty6.setName("SkalaHumptyDumpty6"); // NOI18N
        SkalaHumptyDumpty6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty6ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty6);
        SkalaHumptyDumpty6.setBounds(400, 2090, 350, 23);

        jLabel258.setText("Nilai :");
        jLabel258.setName("jLabel258"); // NOI18N
        FormInput.add(jLabel258);
        jLabel258.setBounds(710, 2090, 75, 23);

        NilaiHumptyDumpty6.setEditable(false);
        NilaiHumptyDumpty6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty6.setText("3");
        NilaiHumptyDumpty6.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty6.setName("NilaiHumptyDumpty6"); // NOI18N
        FormInput.add(NilaiHumptyDumpty6);
        NilaiHumptyDumpty6.setBounds(790, 2090, 60, 23);

        jLabel259.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel259.setText("7. Penggunaan Medikamentosa");
        jLabel259.setName("jLabel259"); // NOI18N
        FormInput.add(jLabel259);
        jLabel259.setBounds(60, 2120, 290, 23);

        jLabel260.setText("Skala :");
        jLabel260.setName("jLabel260"); // NOI18N
        FormInput.add(jLabel260);
        jLabel260.setBounds(320, 2120, 80, 23);

        SkalaHumptyDumpty7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Penggunaan multiple obat", "Penggunaan salah satu obat", "Tidak ada medikasi" }));
        SkalaHumptyDumpty7.setName("SkalaHumptyDumpty7"); // NOI18N
        SkalaHumptyDumpty7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaHumptyDumpty7ItemStateChanged(evt);
            }
        });
        SkalaHumptyDumpty7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaHumptyDumpty7KeyPressed(evt);
            }
        });
        FormInput.add(SkalaHumptyDumpty7);
        SkalaHumptyDumpty7.setBounds(400, 2120, 350, 23);

        jLabel261.setText("Nilai :");
        jLabel261.setName("jLabel261"); // NOI18N
        FormInput.add(jLabel261);
        jLabel261.setBounds(710, 2120, 75, 23);

        NilaiHumptyDumpty7.setEditable(false);
        NilaiHumptyDumpty7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumpty7.setText("3");
        NilaiHumptyDumpty7.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumpty7.setName("NilaiHumptyDumpty7"); // NOI18N
        FormInput.add(NilaiHumptyDumpty7);
        NilaiHumptyDumpty7.setBounds(790, 2120, 60, 23);

        TingkatHumptyDumpty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatHumptyDumpty.setText("Tingkat Resiko : Risiko Rendah (7 - 11), Tindakan : Intervensi pencegahan risiko jatuh standar");
        TingkatHumptyDumpty.setName("TingkatHumptyDumpty"); // NOI18N
        FormInput.add(TingkatHumptyDumpty);
        TingkatHumptyDumpty.setBounds(60, 2170, 650, 23);

        jLabel270.setText("Total :");
        jLabel270.setName("jLabel270"); // NOI18N
        FormInput.add(jLabel270);
        jLabel270.setBounds(710, 2170, 75, 23);

        NilaiHumptyDumptyTotal.setEditable(false);
        NilaiHumptyDumptyTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiHumptyDumptyTotal.setText("23");
        NilaiHumptyDumptyTotal.setFocusTraversalPolicyProvider(true);
        NilaiHumptyDumptyTotal.setName("NilaiHumptyDumptyTotal"); // NOI18N
        FormInput.add(NilaiHumptyDumptyTotal);
        NilaiHumptyDumptyTotal.setBounds(790, 2170, 60, 23);

        jLabel70.setText("Warna Ketuban :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(690, 1220, 90, 23);

        WarnaKetuban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jernih", "Keruh", "Meconial" }));
        WarnaKetuban.setName("WarnaKetuban"); // NOI18N
        WarnaKetuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WarnaKetubanKeyPressed(evt);
            }
        });
        FormInput.add(WarnaKetuban);
        WarnaKetuban.setBounds(790, 1220, 80, 23);

        jLabel71.setText("Kelainan Persalinan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(560, 1250, 110, 23);

        KelainanPersalinan.setFocusTraversalPolicyProvider(true);
        KelainanPersalinan.setName("KelainanPersalinan"); // NOI18N
        KelainanPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelainanPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(KelainanPersalinan);
        KelainanPersalinan.setBounds(680, 1250, 190, 23);

        jLabel72.setText("Usia Kehamilan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1280, 100, 23);

        jLabel75.setText("Penolong Persalinan :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(320, 1280, 110, 23);

        UsiaKehamilan.setFocusTraversalPolicyProvider(true);
        UsiaKehamilan.setName("UsiaKehamilan"); // NOI18N
        UsiaKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(UsiaKehamilan);
        UsiaKehamilan.setBounds(100, 1280, 60, 23);

        PenolongPersalinan.setEditable(false);
        PenolongPersalinan.setFocusTraversalPolicyProvider(true);
        PenolongPersalinan.setName("PenolongPersalinan"); // NOI18N
        PenolongPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenolongPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(PenolongPersalinan);
        PenolongPersalinan.setBounds(440, 1280, 320, 23);

        jLabel101.setText("Penolong :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(160, 1280, 60, 23);

        Penolong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dokter", "Bidan" }));
        Penolong.setName("Penolong"); // NOI18N
        Penolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenolongKeyPressed(evt);
            }
        });
        FormInput.add(Penolong);
        Penolong.setBounds(220, 1280, 100, 23);

        BtnPenolong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenolong.setMnemonic('2');
        BtnPenolong.setToolTipText("Alt+2");
        BtnPenolong.setName("BtnPenolong"); // NOI18N
        BtnPenolong.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPenolong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenolongActionPerformed(evt);
            }
        });
        BtnPenolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPenolongKeyPressed(evt);
            }
        });
        FormInput.add(BtnPenolong);
        BtnPenolong.setBounds(760, 1280, 28, 23);

        jLabel230.setText("Aktivitas :");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(480, 2830, 60, 23);

        SkalaAktivitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidur", "Terjaga", "Rewel" }));
        SkalaAktivitas.setName("SkalaAktivitas"); // NOI18N
        SkalaAktivitas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaAktivitasItemStateChanged(evt);
            }
        });
        SkalaAktivitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaAktivitasKeyPressed(evt);
            }
        });
        FormInput.add(SkalaAktivitas);
        SkalaAktivitas.setBounds(550, 2830, 266, 23);

        NilaiAktivitas.setEditable(false);
        NilaiAktivitas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        NilaiAktivitas.setText("0");
        NilaiAktivitas.setFocusTraversalPolicyProvider(true);
        NilaiAktivitas.setName("NilaiAktivitas"); // NOI18N
        NilaiAktivitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiAktivitasKeyPressed(evt);
            }
        });
        FormInput.add(NilaiAktivitas);
        NilaiAktivitas.setBounds(820, 2830, 40, 23);

        jLabel231.setText("Wajib :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(0, 1830, 80, 23);

        Wajib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baligh", "Non baligh" }));
        Wajib.setName("Wajib"); // NOI18N
        Wajib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WajibKeyPressed(evt);
            }
        });
        FormInput.add(Wajib);
        Wajib.setBounds(90, 1830, 110, 23);

        KetHalanganLain.setFocusTraversalPolicyProvider(true);
        KetHalanganLain.setName("KetHalanganLain"); // NOI18N
        KetHalanganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetHalanganLainKeyPressed(evt);
            }
        });
        FormInput.add(KetHalanganLain);
        KetHalanganLain.setBounds(300, 1830, 210, 23);

        jLabel262.setText("Toharah :");
        jLabel262.setName("jLabel262"); // NOI18N
        FormInput.add(jLabel262);
        jLabel262.setBounds(510, 1830, 60, 23);

        Toharah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tayamum", "Wudhu", "Lain-lain" }));
        Toharah.setName("Toharah"); // NOI18N
        Toharah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToharahKeyPressed(evt);
            }
        });
        FormInput.add(Toharah);
        Toharah.setBounds(580, 1830, 100, 23);

        jLabel263.setText("Halangan Lain :");
        jLabel263.setName("jLabel263"); // NOI18N
        FormInput.add(jLabel263);
        jLabel263.setBounds(200, 1830, 90, 23);

        jLabel264.setText("Sholat :");
        jLabel264.setName("jLabel264"); // NOI18N
        FormInput.add(jLabel264);
        jLabel264.setBounds(680, 1830, 70, 23);

        Sholat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdiri", "Duduk", "Berbaring" }));
        Sholat.setName("Sholat"); // NOI18N
        Sholat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SholatKeyPressed(evt);
            }
        });
        FormInput.add(Sholat);
        Sholat.setBounds(750, 1830, 100, 23);

        jLabel265.setText("Motivasi Kesembuhan Dan Ibadah :");
        jLabel265.setName("jLabel265"); // NOI18N
        jLabel265.setPreferredSize(new java.awt.Dimension(180, 14));
        FormInput.add(jLabel265);
        jLabel265.setBounds(0, 1860, 220, 23);

        MotivasiKesembuhanIbadah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        MotivasiKesembuhanIbadah.setName("MotivasiKesembuhanIbadah"); // NOI18N
        MotivasiKesembuhanIbadah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotivasiKesembuhanIbadahKeyPressed(evt);
            }
        });
        FormInput.add(MotivasiKesembuhanIbadah);
        MotivasiKesembuhanIbadah.setBounds(230, 1860, 60, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel34.setText("Pasien :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(55, 23));
        FormMenu.add(jLabel34);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        TNoRM1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormMenu.add(TNoRM1);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(250, 23));
        FormMenu.add(TPasien1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrint1);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(4, 0, 1, 1));

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Riwayat Imunisasi :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        tbImunisasi2.setName("tbImunisasi2"); // NOI18N
        scrollPane9.setViewportView(tbImunisasi2);

        FormMasalahRencana.add(scrollPane9);

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahDetailMasalah.setName("tbMasalahDetailMasalah"); // NOI18N
        Scroll7.setViewportView(tbMasalahDetailMasalah);

        FormMasalahRencana.add(Scroll7);

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbRencanaDetail.setName("tbRencanaDetail"); // NOI18N
        Scroll10.setViewportView(tbRencanaDetail);

        FormMasalahRencana.add(Scroll10);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Rencana Keperawatan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        DetailRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailRencana.setColumns(20);
        DetailRencana.setRows(5);
        DetailRencana.setName("DetailRencana"); // NOI18N
        scrollPane6.setViewportView(DetailRencana);

        FormMasalahRencana.add(scrollPane6);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, BtnPetugas);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().isEmpty()) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else if (RPD.getText().trim().isEmpty()) {
            Valid.textKosong(RPD, "Riwayat Penyakit Dahulu");
        } else if (RPO.getText().trim().isEmpty()) {
            Valid.textKosong(RPO, "Riwayat Penggunaan Obat");
        } else if (NmPetugas.getText().trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Petugas");
        } else {
            if (akses.getkode().equals("Admin Utama")) {
                simpan();
            } else {
                if (TanggalRegistrasi.getText().isEmpty()) {
                    TanggalRegistrasi.setText(Sequel.cariIsi(
                            "select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",
                            TNoRw.getText()));
                }
                if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),
                        Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                        getSelectedItem().toString().substring(11, 19)) == true) {
                    simpan();
                }
            }
        }

}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Rencana, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (KdPetugas.getText().equals(tbObat.getValueAt(tbObat.
                        getSelectedRow(), 5).toString())) {
                    if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.
                            getSelectedRow(), 11).toString(), Sequel.
                                    ambiltanggalsekarang()) == true) {
                        hapus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }

}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRM.getText().trim().isEmpty()) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else if (RPD.getText().trim().isEmpty()) {
            Valid.textKosong(RPD, "Riwayat Penyakit Dahulu");
        } else if (RPO.getText().trim().isEmpty()) {
            Valid.textKosong(RPO, "Riwayat Penggunaan Obat");
        } else if (NmPetugas.getText().trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Petugas");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (KdPetugas.getText().equals(tbObat.getValueAt(tbObat.
                            getSelectedRow(), 5).toString())) {
                        if (Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.
                                getSelectedRow(), 11).toString(), Sequel.
                                        ambiltanggalsekarang()) == true) {
                            if (TanggalRegistrasi.getText().isEmpty()) {
                                TanggalRegistrasi.setText(Sequel.cariIsi(
                                        "select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",
                                        TNoRw.getText()));
                            }
                            if (Sequel.cekTanggalRegistrasi(TanggalRegistrasi.
                                    getText(), Valid.SetTgl(TglAsuhan.
                                            getSelectedItem() + "") + " " + TglAsuhan.
                                            getSelectedItem().toString().
                                            substring(11, 19)) == true) {
                                ganti();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            try {
                if (TCari.getText().isEmpty()) {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ranap_bayi.tanggal,"
                            + "penilaian_awal_keperawatan_ranap_bayi.informasi,penilaian_awal_keperawatan_ranap_bayi.td,penilaian_awal_keperawatan_ranap_bayi.nadi,penilaian_awal_keperawatan_ranap_bayi.rr,penilaian_awal_keperawatan_ranap_bayi.suhu,penilaian_awal_keperawatan_ranap_bayi.gcs,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bb,penilaian_awal_keperawatan_ranap_bayi.tb,penilaian_awal_keperawatan_ranap_bayi.lp,penilaian_awal_keperawatan_ranap_bayi.lk,penilaian_awal_keperawatan_ranap_bayi.ld,penilaian_awal_keperawatan_ranap_bayi.keluhan_utama,"
                            + "penilaian_awal_keperawatan_ranap_bayi.rpd,penilaian_awal_keperawatan_ranap_bayi.rpk,penilaian_awal_keperawatan_ranap_bayi.rpo,penilaian_awal_keperawatan_ranap_bayi.alergi,penilaian_awal_keperawatan_ranap_bayi.anakke,penilaian_awal_keperawatan_ranap_bayi.darisaudara,"
                            + "penilaian_awal_keperawatan_ranap_bayi.caralahir,penilaian_awal_keperawatan_ranap_bayi.ket_caralahir,penilaian_awal_keperawatan_ranap_bayi.umurkelahiran,penilaian_awal_keperawatan_ranap_bayi.kelainanbawaan,penilaian_awal_keperawatan_ranap_bayi.ket_kelainan_bawaan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiatengkurap,penilaian_awal_keperawatan_ranap_bayi.usiaduduk,penilaian_awal_keperawatan_ranap_bayi.usiaberdiri,penilaian_awal_keperawatan_ranap_bayi.usiagigipertama,penilaian_awal_keperawatan_ranap_bayi.usiaberjalan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiabicara,penilaian_awal_keperawatan_ranap_bayi.usiamembaca,penilaian_awal_keperawatan_ranap_bayi.usiamenulis,penilaian_awal_keperawatan_ranap_bayi.gangguanemosi,penilaian_awal_keperawatan_ranap_bayi.alat_bantu,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_bantu,penilaian_awal_keperawatan_ranap_bayi.prothesa,penilaian_awal_keperawatan_ranap_bayi.ket_pro,penilaian_awal_keperawatan_ranap_bayi.adl,penilaian_awal_keperawatan_ranap_bayi.status_psiko,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_psiko,penilaian_awal_keperawatan_ranap_bayi.hub_keluarga,penilaian_awal_keperawatan_ranap_bayi.pengasuh,penilaian_awal_keperawatan_ranap_bayi.ket_pengasuh,penilaian_awal_keperawatan_ranap_bayi.ekonomi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.budaya,penilaian_awal_keperawatan_ranap_bayi.ket_budaya,penilaian_awal_keperawatan_ranap_bayi.edukasi,penilaian_awal_keperawatan_ranap_bayi.ket_edukasi,penilaian_awal_keperawatan_ranap_bayi.berjalan_a,"
                            + "penilaian_awal_keperawatan_ranap_bayi.berjalan_b,penilaian_awal_keperawatan_ranap_bayi.berjalan_c,penilaian_awal_keperawatan_ranap_bayi.hasil,penilaian_awal_keperawatan_ranap_bayi.lapor,penilaian_awal_keperawatan_ranap_bayi.ket_lapor,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg1,penilaian_awal_keperawatan_ranap_bayi.nilai1,penilaian_awal_keperawatan_ranap_bayi.sg2,penilaian_awal_keperawatan_ranap_bayi.nilai2,penilaian_awal_keperawatan_ranap_bayi.sg3,penilaian_awal_keperawatan_ranap_bayi.nilai3,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg4,penilaian_awal_keperawatan_ranap_bayi.nilai4,penilaian_awal_keperawatan_ranap_bayi.total_hasil,penilaian_awal_keperawatan_ranap_bayi.wajah,penilaian_awal_keperawatan_ranap_bayi.nilaiwajah,penilaian_awal_keperawatan_ranap_bayi.kaki,"
                            + "penilaian_awal_keperawatan_ranap_bayi.nilaikaki,penilaian_awal_keperawatan_ranap_bayi.aktifitas,penilaian_awal_keperawatan_ranap_bayi.nilaiaktifitas,penilaian_awal_keperawatan_ranap_bayi.menangis,penilaian_awal_keperawatan_ranap_bayi.nilaimenangis,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bersuara,penilaian_awal_keperawatan_ranap_bayi.nilaibersuara,penilaian_awal_keperawatan_ranap_bayi.hasilnyeri,penilaian_awal_keperawatan_ranap_bayi.nyeri,penilaian_awal_keperawatan_ranap_bayi.lokasi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.durasi,penilaian_awal_keperawatan_ranap_bayi.frekuensi,penilaian_awal_keperawatan_ranap_bayi.nyeri_hilang,penilaian_awal_keperawatan_ranap_bayi.ket_nyeri,penilaian_awal_keperawatan_ranap_bayi.pada_dokter,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_dokter,penilaian_awal_keperawatan_ranap_bayi.rencana,penilaian_awal_keperawatan_ranap_bayi.nip,petugas.nama "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penilaian_awal_keperawatan_ranap_bayi on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_bayi.no_rawat "
                            + "inner join petugas on penilaian_awal_keperawatan_ranap_bayi.nip=petugas.nip "
                            + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                            + "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? order by penilaian_awal_keperawatan_ranap_bayi.tanggal");
                } else {
                    ps = koneksi.prepareStatement(
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ranap_bayi.tanggal,"
                            + "penilaian_awal_keperawatan_ranap_bayi.informasi,penilaian_awal_keperawatan_ranap_bayi.td,penilaian_awal_keperawatan_ranap_bayi.nadi,penilaian_awal_keperawatan_ranap_bayi.rr,penilaian_awal_keperawatan_ranap_bayi.suhu,penilaian_awal_keperawatan_ranap_bayi.gcs,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bb,penilaian_awal_keperawatan_ranap_bayi.tb,penilaian_awal_keperawatan_ranap_bayi.lp,penilaian_awal_keperawatan_ranap_bayi.lk,penilaian_awal_keperawatan_ranap_bayi.ld,penilaian_awal_keperawatan_ranap_bayi.keluhan_utama,"
                            + "penilaian_awal_keperawatan_ranap_bayi.rpd,penilaian_awal_keperawatan_ranap_bayi.rpk,penilaian_awal_keperawatan_ranap_bayi.rpo,penilaian_awal_keperawatan_ranap_bayi.alergi,penilaian_awal_keperawatan_ranap_bayi.anakke,penilaian_awal_keperawatan_ranap_bayi.darisaudara,"
                            + "penilaian_awal_keperawatan_ranap_bayi.caralahir,penilaian_awal_keperawatan_ranap_bayi.ket_caralahir,penilaian_awal_keperawatan_ranap_bayi.umurkelahiran,penilaian_awal_keperawatan_ranap_bayi.kelainanbawaan,penilaian_awal_keperawatan_ranap_bayi.ket_kelainan_bawaan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiatengkurap,penilaian_awal_keperawatan_ranap_bayi.usiaduduk,penilaian_awal_keperawatan_ranap_bayi.usiaberdiri,penilaian_awal_keperawatan_ranap_bayi.usiagigipertama,penilaian_awal_keperawatan_ranap_bayi.usiaberjalan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiabicara,penilaian_awal_keperawatan_ranap_bayi.usiamembaca,penilaian_awal_keperawatan_ranap_bayi.usiamenulis,penilaian_awal_keperawatan_ranap_bayi.gangguanemosi,penilaian_awal_keperawatan_ranap_bayi.alat_bantu,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_bantu,penilaian_awal_keperawatan_ranap_bayi.prothesa,penilaian_awal_keperawatan_ranap_bayi.ket_pro,penilaian_awal_keperawatan_ranap_bayi.adl,penilaian_awal_keperawatan_ranap_bayi.status_psiko,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_psiko,penilaian_awal_keperawatan_ranap_bayi.hub_keluarga,penilaian_awal_keperawatan_ranap_bayi.pengasuh,penilaian_awal_keperawatan_ranap_bayi.ket_pengasuh,penilaian_awal_keperawatan_ranap_bayi.ekonomi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.budaya,penilaian_awal_keperawatan_ranap_bayi.ket_budaya,penilaian_awal_keperawatan_ranap_bayi.edukasi,penilaian_awal_keperawatan_ranap_bayi.ket_edukasi,penilaian_awal_keperawatan_ranap_bayi.berjalan_a,"
                            + "penilaian_awal_keperawatan_ranap_bayi.berjalan_b,penilaian_awal_keperawatan_ranap_bayi.berjalan_c,penilaian_awal_keperawatan_ranap_bayi.hasil,penilaian_awal_keperawatan_ranap_bayi.lapor,penilaian_awal_keperawatan_ranap_bayi.ket_lapor,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg1,penilaian_awal_keperawatan_ranap_bayi.nilai1,penilaian_awal_keperawatan_ranap_bayi.sg2,penilaian_awal_keperawatan_ranap_bayi.nilai2,penilaian_awal_keperawatan_ranap_bayi.sg3,penilaian_awal_keperawatan_ranap_bayi.nilai3,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg4,penilaian_awal_keperawatan_ranap_bayi.nilai4,penilaian_awal_keperawatan_ranap_bayi.total_hasil,penilaian_awal_keperawatan_ranap_bayi.wajah,penilaian_awal_keperawatan_ranap_bayi.nilaiwajah,penilaian_awal_keperawatan_ranap_bayi.kaki,"
                            + "penilaian_awal_keperawatan_ranap_bayi.nilaikaki,penilaian_awal_keperawatan_ranap_bayi.aktifitas,penilaian_awal_keperawatan_ranap_bayi.nilaiaktifitas,penilaian_awal_keperawatan_ranap_bayi.menangis,penilaian_awal_keperawatan_ranap_bayi.nilaimenangis,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bersuara,penilaian_awal_keperawatan_ranap_bayi.nilaibersuara,penilaian_awal_keperawatan_ranap_bayi.hasilnyeri,penilaian_awal_keperawatan_ranap_bayi.nyeri,penilaian_awal_keperawatan_ranap_bayi.lokasi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.durasi,penilaian_awal_keperawatan_ranap_bayi.frekuensi,penilaian_awal_keperawatan_ranap_bayi.nyeri_hilang,penilaian_awal_keperawatan_ranap_bayi.ket_nyeri,penilaian_awal_keperawatan_ranap_bayi.pada_dokter,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_dokter,penilaian_awal_keperawatan_ranap_bayi.rencana,penilaian_awal_keperawatan_ranap_bayi.nip,petugas.nama "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penilaian_awal_keperawatan_ranap_bayi on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_bayi.no_rawat "
                            + "inner join petugas on penilaian_awal_keperawatan_ranap_bayi.nip=petugas.nip "
                            + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                            + "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? and reg_periksa.no_rawat like ? or "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? and pasien.no_rkm_medis like ? or "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? and pasien.nm_pasien like ? or "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? and penilaian_awal_keperawatan_ranap_bayi.nip like ? or "
                            + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? and petugas.nama like ? order by penilaian_awal_keperawatan_ranap_bayi.tanggal");
                }

                try {
                    if (TCari.getText().isEmpty()) {
                        ps.setString(1, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(2, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                    } else {
                        ps.setString(1, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(2, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        ps.setString(3, "%" + TCari.getText() + "%");
                        ps.setString(4, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(5, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        ps.setString(6, "%" + TCari.getText() + "%");
                        ps.setString(7, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(8, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        ps.setString(9, "%" + TCari.getText() + "%");
                        ps.setString(10, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(11, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        ps.setString(12, "%" + TCari.getText() + "%");
                        ps.setString(13, Valid.SetTgl(
                                DTPCari1.getSelectedItem() + "") + " 00:00:00");
                        ps.setString(14, Valid.SetTgl(
                                DTPCari2.getSelectedItem() + "") + " 23:59:59");
                        ps.setString(15, "%" + TCari.getText() + "%");
                    }
                    rs = ps.executeQuery();
                    htmlContent = new StringBuilder();
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='13%'><b>PASIEN & PETUGAS</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='15%'><b>II. RIWAYAT KESEHATAN DAHULU</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='13%'><b>V. RIWAYAT TUMBUH KEMBANG ANAK</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='15%'><b>VII. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='15%'><b>VIII. PENILAIAN RESIKO JATUH</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='13%'><b>IX. SKRINING GIZI (Strong kid)</b></td>"
                            + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='15%'><b>X. PENILAIAN TINGKAT NYERI</b></td>"
                            + "</tr>"
                    );
                    while (rs.next()) {
                        masalahkeperawatan = "";
                        ps2 = koneksi.prepareStatement(
                                "select master_masalah_keperawatan_anak.kode_masalah,master_masalah_keperawatan_anak.nama_masalah from master_masalah_keperawatan_anak "
                                + "inner join penilaian_awal_keperawatan_ranap_masalah_bayi on penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah=master_masalah_keperawatan_anak.kode_masalah "
                                + "where penilaian_awal_keperawatan_ranap_masalah_bayi.no_rawat=? order by kode_masalah");
                        try {
                            ps2.setString(1, rs.getString("no_rawat"));
                            rs2 = ps2.executeQuery();
                            while (rs2.next()) {
                                masalahkeperawatan = rs2.getString(
                                        "nama_masalah") + ", " + masalahkeperawatan;
                            }
                        } catch (SQLException e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rs2 != null) {
                                rs2.close();
                            }
                            if (ps2 != null) {
                                ps2.close();
                            }
                        }
                        htmlContent.append(
                                "<tr class='isi'><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='32%' valign='top' align='justify'>No.Rawat</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("no_rawat")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>No.R.M.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("no_rkm_medis")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Nama Pasien</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("nm_pasien")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>J.K.</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("jk")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Agama</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("agama")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Bahasa</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("nama_bahasa")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Tgl.Lahir</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("nama_cacat")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Cacat Fisik</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("tgl_lahir")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Petugas</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("nip")).append(" ").append(
                                rs.getString("nama")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Tgl.Asuhan</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("tanggal")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Informasi</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("informasi")).append(
                                "</td></tr><tr class='isi2'><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='3' width='100%'><b>I. KEADAAN UMUM</b></td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>TD</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("td")).append(
                                "mmHg</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>Nadi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("nadi")).append(
                                "x/menit</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>RR</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("rr")).append(
                                "x/menit</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>Suhu</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("suhu")).append(
                                "\u00b0C</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>GCS(E,V,M)</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("gcs")).append(
                                "</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>BB</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("bb")).append(
                                "Kg</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>TB</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("tb")).append(
                                "cm</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>LP</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("lp")).append(
                                "cm</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>LK</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("lk")).append(
                                "cm</td></tr><tr class='isi2'><td width='34%' valign='top' align='justify'>LD</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>").
                                append(rs.getString("ld")).append(
                                "cm</td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='32%' valign='top' align='justify'>Keluhan Utama</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("keluhan_utama")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>RPD</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("rpd")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>RPK</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("rpk")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>RPO</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("rpo")).append(
                                "</td></tr><tr class='isi2'><td width='32%' valign='top' align='justify'>Alergi</td><td valign='top'>:&nbsp;</td><td width='67%' valign='top'>").
                                append(rs.getString("alergi")).append(
                                "</td></tr><tr class='isi2'><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='3' width='100%'><b>III. RIWAYAT TUMBUH KEMBANG DAN PERINATAL CARE</b></td></tr><tr class='isi2'><td width='40%' valign='top' align='justify'>Riwayat Kelahiran</td><td valign='top'>:&nbsp;</td><td width='59%' valign='top'> Anak ke ").
                                append(rs.getString("anakke")).append(" dari ").
                                append(rs.getString("darisaudara")).append(
                                " saudara</td></tr><tr class='isi2'><td width='40%' valign='top' align='justify'>Umur Kelahiran</td><td valign='top'>:&nbsp;</td><td width='59%' valign='top'>").
                                append(rs.getString("umurkelahiran")).append(
                                "</td></tr><tr class='isi2'><td width='40%' valign='top' align='justify'>Cara kelahiran</td><td valign='top'>:&nbsp;</td><td width='59%' valign='top'>").
                                append(rs.getString("caralahir")).append("<br>").
                                append(rs.getString("ket_caralahir")).append(
                                "</td></tr><tr class='isi2'><td width='40%' valign='top' align='justify'>Kelainan Bawaan</td><td valign='top'>:&nbsp;</td><td width='59%' valign='top'>").
                                append(rs.getString("kelainanbawaan")).append(
                                "<br>").append(rs.getString(
                                        "ket_kelainan_bawaan")).append(
                                        "</td></tr><tr class='isi2'><td valign='top' cellpadding='0' cellspacing='0' colspan='3'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='7'><b>IV. RIWAYAT IMUNISASI</b></td></tr><tr class='isi2'><td width='70%' valign='top'>Nama Imunisasi</td><td width='5%' valign='top'>Ke 1</td><td width='5%' valign='top'>Ke 2</td><td width='5%' valign='top'>Ke 3</td><td width='5%' valign='top'>Ke 4</td><td width='5%' valign='top'>Ke 5</td><td width='5%' valign='top'>Ke 6</td>");
                        ps2 = koneksi.prepareStatement(
                                "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                                + "where riwayat_imunisasi.no_rkm_medis=? group by master_imunisasi.kode_imunisasi order by master_imunisasi.kode_imunisasi  ");
                        try {
                            ps2.setString(1, rs.getString("no_rkm_medis"));
                            rs2 = ps2.executeQuery();
                            while (rs2.next()) {
                                htmlke1 = "&nbsp;";
                                htmlke2 = "&nbsp;";
                                htmlke3 = "&nbsp;";
                                htmlke4 = "&nbsp;";
                                htmlke5 = "&nbsp;";
                                htmlke6 = "&nbsp;";
                                ps3 = koneksi.prepareStatement(
                                        "select * from riwayat_imunisasi where no_rkm_medis=? and kode_imunisasi=?");
                                try {
                                    ps3.setString(1, rs.
                                            getString("no_rkm_medis"));
                                    ps3.setString(2, rs2.getString(1));
                                    rs3 = ps3.executeQuery();
                                    while (rs3.next()) {
                                        if (rs3.getInt("no_imunisasi") == 1) {
                                            htmlke1 = "V";
                                        }
                                        if (rs3.getInt("no_imunisasi") == 2) {
                                            htmlke2 = "V";
                                        }
                                        if (rs3.getInt("no_imunisasi") == 3) {
                                            htmlke3 = "V";
                                        }
                                        if (rs3.getInt("no_imunisasi") == 4) {
                                            htmlke4 = "V";
                                        }
                                        if (rs3.getInt("no_imunisasi") == 5) {
                                            htmlke5 = "V";
                                        }
                                        if (rs3.getInt("no_imunisasi") == 6) {
                                            htmlke6 = "V";
                                        }
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Notif : " + e);
                                } finally {
                                    if (rs3 != null) {
                                        rs3.close();
                                    }
                                    if (ps3 != null) {
                                        ps3.close();
                                    }
                                }

                                htmlContent.append("<tr class='isi2'><td>").
                                        append(rs2.getString(2)).append(
                                        "</td><td>").append(htmlke1).append(
                                        "</td><td>").append(htmlke2).append(
                                        "</td><td>").append(htmlke3).append(
                                        "</td><td>").append(htmlke4).append(
                                        "</td><td>").append(htmlke5).append(
                                        "</td><td>").append(htmlke6).append(
                                        "</td></tr>");
                            }
                        } catch (SQLException e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rs2 != null) {
                                rs2.close();
                            }
                            if (ps2 != null) {
                                ps2.close();
                            }
                        }
                        htmlContent.append(
                                "</table></td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='80%' valign='top' align='justify'>a. Tengkurap, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiatengkurap")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>b. Duduk, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiaduduk")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>c. Berdiri, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiaberdiri")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>d. Gigi pertama, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiagigipertama")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>e. Berjalan, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiaberjalan")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>f. Bicara Usia, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiabicara")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>g. Mulai bisa membaca, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiamembaca")).append(
                                "</td></tr><tr class='isi2'><td width='80%' valign='top' align='justify'>h. Mulai bisa menulis, usia</td><td valign='top'>:&nbsp;</td><td width='19%' valign='top'> ").
                                append(rs.getString("usiamenulis")).append(
                                "</td></tr><tr class='isi2'><td width='100%' valign='top' colspan='3' align='justify'>Gangguan perkembangan mental / emosi : ").
                                append(rs.getString("gangguanemosi")).append(
                                "</td></tr><tr class='isi2'><td valign='middle' bgcolor='#FFFAFA' align='center' colspan='3'><b>VI. FUNGSIONAL</b></td></tr><tr class='isi2'><td width='30%' valign='top' align='justify'>Alat Bantu</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("alat_bantu")).
                                append("<br>").append(rs.getString("ket_bantu")).
                                append("</td></tr><tr class='isi2'><td width='30%' valign='top' align='justify'>Prothesa</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("prothesa")).append("<br>").
                                append(rs.getString("ket_pro")).append(
                                "</td></tr><tr class='isi2'><td width='30%' valign='top' align='justify'>Cacat Fisik</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("nama_cacat")).append(
                                "</td></tr><tr class='isi2'><td width='30%' valign='top' align='justify'>ADL</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("adl")).append(
                                "</td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='50%' valign='top' align='justify'>Status Psikologis</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("status_psiko")).append(
                                "<br>").append(rs.getString("ket_psiko")).
                                append("</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Bahasa yang digunakan sehari-hari</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("nama_bahasa")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' colspan='3' align='justify'>Status Sosial dan ekonomi :</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>a. Hubungan dengan anggota keluarga</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("hub_keluarga")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>b. Pengasuh</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("pengasuh")).append(" ").
                                append(rs.getString("ket_pengasuh")).append(
                                " </td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>c. Ekonomi (Ortu)</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("ekonomi")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("budaya")).append("<br>").
                                append(rs.getString("ket_budaya")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Agama</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("agama")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Edukasi diberikan kepada</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("edukasi")).append("<br>").
                                append(rs.getString("ket_edukasi")).append(
                                "</td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='100%' valign='top' colspan='3' align='justify'>a. Cara berjalan pasien (salah satu atau lebih) :</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>1. Tidak seimbang/ Sempoyongan/ Limbung</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("berjalan_a")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>2. Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("berjalan_b")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>b. Duduk di kursi tanpa menggunakan tangan sebagai penopang (tampak memegang kursi atau meja/ benda lain)</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("berjalan_c")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Hasil</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("hasil")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Dilaporkan kepada dokter</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("lapor")).append(
                                "</td></tr><tr class='isi2'><td width='50%' valign='top' align='justify'>Jam dilaporkan</td><td valign='top'>:&nbsp;</td><td width='49%' valign='top'>").
                                append(rs.getString("ket_lapor")).append(
                                "</td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='85%' valign='top' align='justify'>1. Apakah pasien tampak kurus</td><td valign='top'>:&nbsp;</td><td width='9%' valign='top'>").
                                append(rs.getString("sg1")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilai1")).append(
                                "</td></tr><tr class='isi2'><td width='85%' valign='top' align='justify'>2. Apakah terdapat penurunan berat badan selama satu bulan terakhir? (berdasarkan penilaian objektif data berat badan bila ada atau untuk bayi < 1 tahun ; berat badan tidak naik selama 3 bulan terakhir)</td><td valign='top'>:&nbsp;</td><td width='9%' valign='top'>").
                                append(rs.getString("sg2")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilai2")).append(
                                "</td></tr><tr class='isi2'><td width='85%' valign='top' align='justify'>3. Apakah terdapat salah satu dari kondisi tersebut? Diare > 5 kali/hari dan/muntah > 3 kali/hari salam seminggu terakhir;Asupan makanan berkurang selama 1 minggu terakhir</td><td valign='top'>:&nbsp;</td><td width='9%' valign='top'>").
                                append(rs.getString("sg3")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilai3")).append(
                                "</td></tr><tr class='isi2'><td width='85%' valign='top' align='justify'>4. Apakah terdapat penyakit atau keadaan yang menyebabkan pasien beresiko mengalami malnutrisi?</td><td valign='top'>:&nbsp;</td><td width='9%' valign='top'>").
                                append(rs.getString("sg4")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilai4")).append(
                                "</td></tr><tr class='isi2'><td width='85%' valign='top' align='justify'>Total Skor</td><td valign='top'>:&nbsp;</td><td width='9%' valign='top'></td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("total_hasil")).append(
                                "</td></tr></table></td><td valign='top' cellpadding='0' cellspacing='0'><table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'><tr class='isi2'><td width='25%' valign='top' align='justify'>Wajah</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("wajah")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilaiwajah")).append(
                                "</td></tr><tr class='isi2'><td width='25%' valign='top' align='justify'>Kaki</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("kaki")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilaikaki")).append(
                                "</td></tr><tr class='isi2'><td width='25%' valign='top' align='justify'>Aktifitas</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("aktifitas")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilaiaktifitas")).append(
                                "</td></tr><tr class='isi2'><td width='25%' valign='top' align='justify'>Menangis</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("menangis")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilaimenangis")).append(
                                "</td></tr><tr class='isi2'><td width='25%' valign='top' align='justify'>Bersuara</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'>").
                                append(rs.getString("bersuara")).append(
                                "</td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("nilaibersuara")).append(
                                "</td></tr><tr class='isi2'><td width='25%' valign='top' align='justify'>Skala nyeri</td><td valign='top'>:&nbsp;</td><td width='69%' valign='top'></td><td width='5%' valign='top' align='right'>").
                                append(rs.getString("hasilnyeri")).append(
                                "</td></tr></table><br><center><b>MASALAH & RENCANA KEPERAWATAN</b></center><br>Masalah Keperawatan : ").
                                append(masalahkeperawatan).append(
                                "<br><br>Rencana Keperawatan : ").append(rs.
                                        getString("rencana")).append(
                                "</td></tr>");
                    }
                    LoadHTML.setText(
                            "<html>"
                            + "<table width='1800px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"
                            + htmlContent.toString()
                            + "</table>"
                            + "</html>"
                    );

                    File g = new File("file2.css");
                    try (BufferedWriter bg = new BufferedWriter(
                            new FileWriter(g))) {
                        bg.write(
                                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                                + ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"
                                + ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                                + ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"
                                + ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"
                                + ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"
                                + ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"
                                + ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                        );
                    }

                    File f = new File(
                            "DataPenilaianAwalKeperawatanRalanBayiAnak.html");
                    try (BufferedWriter bw = new BufferedWriter(
                            new FileWriter(f))) {
                        bw.write(LoadHTML.getText().replaceAll("<head>",
                                "<head>"
                                + "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"
                                + "<table width='1800px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                + "<tr class='isi2'>"
                                + "<td valign='top' align='center'>"
                                + "<font size='4' face='Tahoma'>" + akses.
                                        getnamars() + "</font><br>"
                                + akses.getalamatrs() + ", " + akses.
                                getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                                + akses.getkontakrs() + ", E-mail : " + akses.
                                getemailrs() + "<br><br>"
                                + "<font size='2' face='Tahoma'>DATA PENILAIAN AWAL KEPERAWATAN RAWAT JALAN BAYI/ANAK<br><br></font>"
                                + "</td>"
                                + "</tr>"
                                + "</table>")
                        );
                    }
                    Desktop.getDesktop().browse(f.toURI());
                } catch (IOException | SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }

            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
                getImunisasi();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbObat.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
                    getImunisasi();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt, TD, RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, RR, GCS);
    }//GEN-LAST:event_SuhuKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed

    }//GEN-LAST:event_TDKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt, Nadi, Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt, Suhu, BB);
    }//GEN-LAST:event_GCSKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if (Valid.daysOld("./cache/masalahkeperawatanbayi.iyem") < 8) {
                tampilMasalah2();
            } else {
                tampilMasalah();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            isMenu();
        } else {
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (tbObat.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar(
                    "select setting.logo from setting"));
            param.put("nyeri", Sequel.cariGambar(
                    "select gambar.nyeri from gambar"));
            finger = Sequel.cariIsi(
                    "select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString());
            param.put("finger",
                    "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.
                    getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 92).toString() + "\nID " + (finger.
                    isEmpty() ? tbObat.getValueAt(tbObat.getSelectedRow(), 91).
                                    toString() : finger) + "\n" + Valid.SetTgl3(
                            tbObat.getValueAt(tbObat.getSelectedRow(), 8).
                                    toString()));
            try {
                masalahkeperawatan = "";
                ps2 = koneksi.prepareStatement(
                        "select master_masalah_keperawatan_anak.kode_masalah,master_masalah_keperawatan_anak.nama_masalah from master_masalah_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_bayi on penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah=master_masalah_keperawatan_anak.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah");
                try {
                    ps2.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(),
                            0).toString());
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        masalahkeperawatan = rs2.getString("nama_masalah") + ", " + masalahkeperawatan;
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (ps2 != null) {
                        ps2.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            param.put("masalah", masalahkeperawatan);
            try {
                masalahkeperawatan = "";
                ps2 = koneksi.prepareStatement(
                        "select master_rencana_keperawatan_anak.kode_rencana,master_rencana_keperawatan_anak.rencana_keperawatan from master_rencana_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_bayi on penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana=master_rencana_keperawatan_anak.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana");
                try {
                    ps2.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(),
                            0).toString());
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        masalahkeperawatan = rs2.
                                getString("rencana_keperawatan") + ", " + masalahkeperawatan;
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (ps2 != null) {
                        ps2.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            param.put("rencana", masalahkeperawatan);
            try {
                masalahkeperawatan = "";
                ps2 = koneksi.prepareStatement(
                        "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi,riwayat_imunisasi.no_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                        + "where riwayat_imunisasi.no_rkm_medis=? group by riwayat_imunisasi.no_imunisasi order by riwayat_imunisasi.no_imunisasi desc ");
                try {
                    ps2.setString(1, TNoRM1.getText());
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        masalahkeperawatan = rs2.getString("nama_imunisasi") + " Ke " + rs2.
                                getString("no_imunisasi") + ", " + masalahkeperawatan;
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (ps2 != null) {
                        ps2.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            param.put("imunisasi", masalahkeperawatan);
            Valid.
                    MyReportqry(
                            "rptCetakPenilaianAwalKeperawatanRalanAnak.jasper",
                            "report",
                            "::[ Laporan Penilaian Awal Keperawatan Ralan Bayi/Anak ]::",
                            "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,cacat_fisik.nama_cacat,penilaian_awal_keperawatan_ranap_bayi.tanggal,"
                            + "penilaian_awal_keperawatan_ranap_bayi.informasi,penilaian_awal_keperawatan_ranap_bayi.td,penilaian_awal_keperawatan_ranap_bayi.nadi,penilaian_awal_keperawatan_ranap_bayi.rr,penilaian_awal_keperawatan_ranap_bayi.suhu,penilaian_awal_keperawatan_ranap_bayi.gcs,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bb,penilaian_awal_keperawatan_ranap_bayi.tb,penilaian_awal_keperawatan_ranap_bayi.lp,penilaian_awal_keperawatan_ranap_bayi.lk,penilaian_awal_keperawatan_ranap_bayi.ld,penilaian_awal_keperawatan_ranap_bayi.keluhan_utama,"
                            + "penilaian_awal_keperawatan_ranap_bayi.rpd,penilaian_awal_keperawatan_ranap_bayi.rpk,penilaian_awal_keperawatan_ranap_bayi.rpo,penilaian_awal_keperawatan_ranap_bayi.alergi,penilaian_awal_keperawatan_ranap_bayi.anakke,penilaian_awal_keperawatan_ranap_bayi.darisaudara,"
                            + "penilaian_awal_keperawatan_ranap_bayi.caralahir,penilaian_awal_keperawatan_ranap_bayi.ket_caralahir,penilaian_awal_keperawatan_ranap_bayi.umurkelahiran,penilaian_awal_keperawatan_ranap_bayi.kelainanbawaan,penilaian_awal_keperawatan_ranap_bayi.ket_kelainan_bawaan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiatengkurap,penilaian_awal_keperawatan_ranap_bayi.usiaduduk,penilaian_awal_keperawatan_ranap_bayi.usiaberdiri,penilaian_awal_keperawatan_ranap_bayi.usiagigipertama,penilaian_awal_keperawatan_ranap_bayi.usiaberjalan,"
                            + "penilaian_awal_keperawatan_ranap_bayi.usiabicara,penilaian_awal_keperawatan_ranap_bayi.usiamembaca,penilaian_awal_keperawatan_ranap_bayi.usiamenulis,penilaian_awal_keperawatan_ranap_bayi.gangguanemosi,penilaian_awal_keperawatan_ranap_bayi.alat_bantu,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_bantu,penilaian_awal_keperawatan_ranap_bayi.prothesa,penilaian_awal_keperawatan_ranap_bayi.ket_pro,penilaian_awal_keperawatan_ranap_bayi.adl,penilaian_awal_keperawatan_ranap_bayi.status_psiko,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_psiko,penilaian_awal_keperawatan_ranap_bayi.hub_keluarga,penilaian_awal_keperawatan_ranap_bayi.pengasuh,penilaian_awal_keperawatan_ranap_bayi.ket_pengasuh,penilaian_awal_keperawatan_ranap_bayi.ekonomi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.budaya,penilaian_awal_keperawatan_ranap_bayi.ket_budaya,penilaian_awal_keperawatan_ranap_bayi.edukasi,penilaian_awal_keperawatan_ranap_bayi.ket_edukasi,penilaian_awal_keperawatan_ranap_bayi.berjalan_a,"
                            + "penilaian_awal_keperawatan_ranap_bayi.berjalan_b,penilaian_awal_keperawatan_ranap_bayi.berjalan_c,penilaian_awal_keperawatan_ranap_bayi.hasil,penilaian_awal_keperawatan_ranap_bayi.lapor,penilaian_awal_keperawatan_ranap_bayi.ket_lapor,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg1,penilaian_awal_keperawatan_ranap_bayi.nilai1,penilaian_awal_keperawatan_ranap_bayi.sg2,penilaian_awal_keperawatan_ranap_bayi.nilai2,penilaian_awal_keperawatan_ranap_bayi.sg3,penilaian_awal_keperawatan_ranap_bayi.nilai3,"
                            + "penilaian_awal_keperawatan_ranap_bayi.sg4,penilaian_awal_keperawatan_ranap_bayi.nilai4,penilaian_awal_keperawatan_ranap_bayi.total_hasil,penilaian_awal_keperawatan_ranap_bayi.wajah,penilaian_awal_keperawatan_ranap_bayi.nilaiwajah,penilaian_awal_keperawatan_ranap_bayi.kaki,"
                            + "penilaian_awal_keperawatan_ranap_bayi.nilaikaki,penilaian_awal_keperawatan_ranap_bayi.aktifitas,penilaian_awal_keperawatan_ranap_bayi.nilaiaktifitas,penilaian_awal_keperawatan_ranap_bayi.menangis,penilaian_awal_keperawatan_ranap_bayi.nilaimenangis,"
                            + "penilaian_awal_keperawatan_ranap_bayi.bersuara,penilaian_awal_keperawatan_ranap_bayi.nilaibersuara,penilaian_awal_keperawatan_ranap_bayi.hasilnyeri,penilaian_awal_keperawatan_ranap_bayi.nyeri,penilaian_awal_keperawatan_ranap_bayi.lokasi,"
                            + "penilaian_awal_keperawatan_ranap_bayi.durasi,penilaian_awal_keperawatan_ranap_bayi.frekuensi,penilaian_awal_keperawatan_ranap_bayi.nyeri_hilang,penilaian_awal_keperawatan_ranap_bayi.ket_nyeri,penilaian_awal_keperawatan_ranap_bayi.pada_dokter,"
                            + "penilaian_awal_keperawatan_ranap_bayi.ket_dokter,penilaian_awal_keperawatan_ranap_bayi.rencana,penilaian_awal_keperawatan_ranap_bayi.nip,petugas.nama "
                            + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "inner join penilaian_awal_keperawatan_ranap_bayi on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_bayi.no_rawat "
                            + "inner join petugas on penilaian_awal_keperawatan_ranap_bayi.nip=petugas.nip "
                            + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                            + "inner join cacat_fisik on cacat_fisik.id=pasien.cacat_fisik where reg_periksa.no_rawat='" + tbObat.
                                    getValueAt(tbObat.getSelectedRow(), 0).
                                    toString() + "'", param);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt, BB, LP);
    }//GEN-LAST:event_TBKeyPressed

    private void LKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LKKeyPressed
        Valid.pindah(evt, LP, LD);
    }//GEN-LAST:event_LKKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt, GCS, TB);
    }//GEN-LAST:event_BBKeyPressed

    private void LPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LPKeyPressed
        Valid.pindah(evt, TB, LK);
    }//GEN-LAST:event_LPKeyPressed

    private void LDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LDKeyPressed
        Valid.pindah(evt, LK, KeluhanUtama);
    }//GEN-LAST:event_LDKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt, RPO, Anakke);
    }//GEN-LAST:event_AlergiKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt, LD, RPK);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void RPDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPDKeyPressed
        Valid.pindah2(evt, RPK, RPO);
    }//GEN-LAST:event_RPDKeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        Valid.pindah2(evt, KeluhanUtama, RPD);
    }//GEN-LAST:event_RPKKeyPressed

    private void RPOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPOKeyPressed
        Valid.pindah2(evt, RPD, Alergi);
    }//GEN-LAST:event_RPOKeyPressed

    private void AnakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnakkeKeyPressed
        Valid.pindah(evt, AlatBantu, DariSaudara);
    }//GEN-LAST:event_AnakkeKeyPressed

    private void DariSaudaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DariSaudaraKeyPressed
        Valid.pindah(evt, Anakke, UmurKelahiran);
    }//GEN-LAST:event_DariSaudaraKeyPressed

    private void CaraKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraKelahiranKeyPressed
        Valid.pindah(evt, UmurKelahiran, KetCaraKelahiran);
    }//GEN-LAST:event_CaraKelahiranKeyPressed

    private void KetCaraKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetCaraKelahiranKeyPressed
        Valid.pindah(evt, CaraKelahiran, KelainanBawaan);
    }//GEN-LAST:event_KetCaraKelahiranKeyPressed

    private void KelainanBawaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelainanBawaanKeyPressed
        Valid.pindah(evt, KetCaraKelahiran, KetKelainanBawaan);
    }//GEN-LAST:event_KelainanBawaanKeyPressed

    private void KetKelainanBawaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKelainanBawaanKeyPressed
        Valid.pindah(evt, KelainanBawaan, UsiaTengkurap);
    }//GEN-LAST:event_KetKelainanBawaanKeyPressed

    private void UmurKelahiranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurKelahiranKeyPressed
        Valid.pindah(evt, DariSaudara, CaraKelahiran);
    }//GEN-LAST:event_UmurKelahiranKeyPressed

    private void BtnTambahImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahImunisasiActionPerformed
        if (TNoRM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Pilih terlebih dahulu pasien yang mau dimasukkan data riwayat imunisasinya...");
            Anamnesis.requestFocus();
        } else {
            KdImunisasi.setText("");
            NmImunisasi.setText("");
            ImunisasiKe.setSelectedIndex(0);
            BtnImunisasi.setEnabled(true);
            BtnSimpanImunisasi.setVisible(true);
            BtnHapusImunisasi.setVisible(false);
            DlgRiwayatImunisasi.setLocationRelativeTo(internalFrame1);
            DlgRiwayatImunisasi.setVisible(true);
        }
    }//GEN-LAST:event_BtnTambahImunisasiActionPerformed

    private void UsiaTengkurapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaTengkurapKeyPressed
        Valid.pindah(evt, KetKelainanBawaan, UsiaDuduk);
    }//GEN-LAST:event_UsiaTengkurapKeyPressed

    private void UsiaDudukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaDudukKeyPressed
        Valid.pindah(evt, UsiaTengkurap, UsiaBerdiri);
    }//GEN-LAST:event_UsiaDudukKeyPressed

    private void UsiaBerdiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaBerdiriKeyPressed
        Valid.pindah(evt, UsiaDuduk, UsiaGigi);
    }//GEN-LAST:event_UsiaBerdiriKeyPressed

    private void UsiaGigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaGigiKeyPressed
        Valid.pindah(evt, UsiaBerdiri, UsiaBerjalan);
    }//GEN-LAST:event_UsiaGigiKeyPressed

    private void UsiaBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaBerjalanKeyPressed
        Valid.pindah(evt, UsiaGigi, UsiaBicara);
    }//GEN-LAST:event_UsiaBerjalanKeyPressed

    private void UsiaBicaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaBicaraKeyPressed
        Valid.pindah(evt, UsiaBerjalan, UsiaMembaca);
    }//GEN-LAST:event_UsiaBicaraKeyPressed

    private void UsiaMembacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaMembacaKeyPressed
        Valid.pindah(evt, UsiaBicara, UsiaMenulis);
    }//GEN-LAST:event_UsiaMembacaKeyPressed

    private void UsiaMenulisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaMenulisKeyPressed
        Valid.pindah(evt, UsiaMembaca, GangguanEmosi);
    }//GEN-LAST:event_UsiaMenulisKeyPressed

    private void GangguanEmosiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GangguanEmosiKeyPressed
        Valid.pindah(evt, UsiaMenulis, AlatBantu);
    }//GEN-LAST:event_GangguanEmosiKeyPressed

    private void AlatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatBantuKeyPressed
        Valid.pindah(evt, GangguanEmosi, KetBantu);
    }//GEN-LAST:event_AlatBantuKeyPressed

    private void KetBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBantuKeyPressed
        Valid.pindah(evt, CaraKelahiran, Prothesa);
    }//GEN-LAST:event_KetBantuKeyPressed

    private void ProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProthesaKeyPressed
        Valid.pindah(evt, KetCaraKelahiran, KetProthesa);
    }//GEN-LAST:event_ProthesaKeyPressed

    private void KetProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetProthesaKeyPressed
        Valid.pindah(evt, Prothesa, ADL);
    }//GEN-LAST:event_KetProthesaKeyPressed

    private void ADLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ADLKeyPressed
        Valid.pindah(evt, KetProthesa, StatusPsiko);
    }//GEN-LAST:event_ADLKeyPressed

    private void StatusPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusPsikoKeyPressed
        Valid.pindah(evt, ADL, KetPsiko);
    }//GEN-LAST:event_StatusPsikoKeyPressed

    private void KetPsikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetPsikoKeyPressed
        Valid.pindah(evt, StatusPsiko, HubunganKeluarga);
    }//GEN-LAST:event_KetPsikoKeyPressed

    private void HubunganKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeluargaKeyPressed
        Valid.pindah(evt, KetPsiko, Pengasuh);
    }//GEN-LAST:event_HubunganKeluargaKeyPressed

    private void PengasuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengasuhKeyPressed
        Valid.pindah(evt, HubunganKeluarga, KetPengasuh);
    }//GEN-LAST:event_PengasuhKeyPressed

    private void KetPengasuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetPengasuhKeyPressed
        Valid.pindah(evt, Pengasuh, Ekonomi);
    }//GEN-LAST:event_KetPengasuhKeyPressed

    private void EkonomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkonomiKeyPressed
        Valid.pindah(evt, KetPengasuh, StatusBudaya);
    }//GEN-LAST:event_EkonomiKeyPressed

    private void StatusBudayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusBudayaKeyPressed
        Valid.pindah(evt, Ekonomi, KetBudaya);
    }//GEN-LAST:event_StatusBudayaKeyPressed

    private void KetBudayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBudayaKeyPressed
        Valid.pindah(evt, StatusBudaya, Edukasi);
    }//GEN-LAST:event_KetBudayaKeyPressed

    private void EdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiKeyPressed
        Valid.pindah(evt, KetBudaya, KetEdukasi);
    }//GEN-LAST:event_EdukasiKeyPressed

    private void KetEdukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetEdukasiKeyPressed

    }//GEN-LAST:event_KetEdukasiKeyPressed

    private void SG1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG1ItemStateChanged
        NilaiGizi1.setText(SG1.getSelectedIndex() + "");
        TotalNilaiGizi.setText(
                "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.
                parseInt(NilaiGizi4.getText())));
    }//GEN-LAST:event_SG1ItemStateChanged

    private void SG1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG1KeyPressed

    }//GEN-LAST:event_SG1KeyPressed

    private void SG2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG2ItemStateChanged
        NilaiGizi2.setText(SG2.getSelectedIndex() + "");
        TotalNilaiGizi.setText(
                "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.
                parseInt(NilaiGizi4.getText())));
    }//GEN-LAST:event_SG2ItemStateChanged

    private void SG2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG2KeyPressed
        Valid.pindah(evt, SG1, SG3);
    }//GEN-LAST:event_SG2KeyPressed

    private void SG3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG3ItemStateChanged
        NilaiGizi3.setText(SG3.getSelectedIndex() + "");
        TotalNilaiGizi.setText(
                "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.
                parseInt(NilaiGizi4.getText())));
    }//GEN-LAST:event_SG3ItemStateChanged

    private void SG3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG3KeyPressed
        Valid.pindah(evt, SG2, SG4);
    }//GEN-LAST:event_SG3KeyPressed

    private void SkalaWajahItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaWajahItemStateChanged
        NilaiWajah.setText(SkalaWajah.getSelectedIndex() + "");
        SkalaNyeri.setText(
                "" + (Integer.parseInt(NilaiWajah.getText()) + Integer.parseInt(
                NilaiTangisan.getText()) + Integer.parseInt(NilaiPolaNapas.
                getText()) + Integer.parseInt(NilaiLengan.getText()) + Integer.
                parseInt(NilaiTungkai.getText()) + Integer.parseInt(
                NilaiAktivitas.getText())));
    }//GEN-LAST:event_SkalaWajahItemStateChanged

    private void SkalaWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaWajahKeyPressed
        Valid.pindah(evt, SG4, SkalaTangisan);
    }//GEN-LAST:event_SkalaWajahKeyPressed

    private void NilaiGizi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiGizi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiGizi1KeyPressed

    private void SG4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SG4ItemStateChanged
        NilaiGizi4.setText(SG4.getSelectedIndex() + "");
        TotalNilaiGizi.setText(
                "" + (Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(
                NilaiGizi2.getText()) + Integer.parseInt(NilaiGizi3.getText()) + Integer.
                parseInt(NilaiGizi4.getText())));
    }//GEN-LAST:event_SG4ItemStateChanged

    private void SG4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SG4KeyPressed
        Valid.pindah(evt, SG3, SkalaWajah);
    }//GEN-LAST:event_SG4KeyPressed

    private void NilaiGizi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiGizi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiGizi2KeyPressed

    private void NilaiGizi3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiGizi3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiGizi3KeyPressed

    private void NilaiGizi4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiGizi4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiGizi4KeyPressed

    private void NilaiWajahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiWajahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiWajahKeyPressed

    private void TotalNilaiGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TotalNilaiGiziKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalNilaiGiziKeyPressed

    private void SkalaTangisanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaTangisanItemStateChanged
        NilaiTangisan.setText(SkalaTangisan.getSelectedIndex() + "");
        SkalaNyeri.setText(
                "" + (Integer.parseInt(NilaiWajah.getText()) + Integer.parseInt(
                NilaiTangisan.getText()) + Integer.parseInt(NilaiPolaNapas.
                getText()) + Integer.parseInt(NilaiLengan.getText()) + Integer.
                parseInt(NilaiTungkai.getText()) + Integer.parseInt(
                NilaiAktivitas.getText())));
    }//GEN-LAST:event_SkalaTangisanItemStateChanged

    private void SkalaTangisanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaTangisanKeyPressed
        Valid.pindah(evt, SkalaWajah, SkalaPolaNapas);
    }//GEN-LAST:event_SkalaTangisanKeyPressed

    private void NilaiTangisanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiTangisanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiTangisanKeyPressed

    private void SkalaPolaNapasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaPolaNapasItemStateChanged
        NilaiPolaNapas.setText(SkalaPolaNapas.getSelectedIndex() + "");
        SkalaNyeri.setText(
                "" + (Integer.parseInt(NilaiWajah.getText()) + Integer.parseInt(
                NilaiTangisan.getText()) + Integer.parseInt(NilaiPolaNapas.
                getText()) + Integer.parseInt(NilaiLengan.getText()) + Integer.
                parseInt(NilaiTungkai.getText()) + Integer.parseInt(
                NilaiAktivitas.getText())));
    }//GEN-LAST:event_SkalaPolaNapasItemStateChanged

    private void SkalaPolaNapasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaPolaNapasKeyPressed
        Valid.pindah(evt, SkalaTangisan, SkalaLengan);
    }//GEN-LAST:event_SkalaPolaNapasKeyPressed

    private void NilaiPolaNapasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiPolaNapasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiPolaNapasKeyPressed

    private void SkalaLenganItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaLenganItemStateChanged
        NilaiLengan.setText(SkalaLengan.getSelectedIndex() + "");
        SkalaNyeri.setText(
                "" + (Integer.parseInt(NilaiWajah.getText()) + Integer.parseInt(
                NilaiTangisan.getText()) + Integer.parseInt(NilaiPolaNapas.
                getText()) + Integer.parseInt(NilaiLengan.getText()) + Integer.
                parseInt(NilaiTungkai.getText()) + Integer.parseInt(
                NilaiAktivitas.getText())));
    }//GEN-LAST:event_SkalaLenganItemStateChanged

    private void SkalaLenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaLenganKeyPressed
        Valid.pindah(evt, SkalaPolaNapas, SkalaTungkai);
    }//GEN-LAST:event_SkalaLenganKeyPressed

    private void NilaiLenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiLenganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiLenganKeyPressed

    private void SkalaTungkaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaTungkaiItemStateChanged
        NilaiTungkai.setText(SkalaTungkai.getSelectedIndex() + "");
        SkalaNyeri.setText(
                "" + (Integer.parseInt(NilaiWajah.getText()) + Integer.parseInt(
                NilaiTangisan.getText()) + Integer.parseInt(NilaiPolaNapas.
                getText()) + Integer.parseInt(NilaiLengan.getText()) + Integer.
                parseInt(NilaiTungkai.getText()) + Integer.parseInt(
                NilaiAktivitas.getText())));
    }//GEN-LAST:event_SkalaTungkaiItemStateChanged

    private void SkalaTungkaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaTungkaiKeyPressed
        Valid.pindah(evt, SkalaLengan, SkalaAktivitas);
    }//GEN-LAST:event_SkalaTungkaiKeyPressed

    private void NilaiTungkaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiTungkaiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiTungkaiKeyPressed

    private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaNyeriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaNyeriKeyPressed

    private void BtnKeluarImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarImunisasiActionPerformed
        DlgRiwayatImunisasi.dispose();
    }//GEN-LAST:event_BtnKeluarImunisasiActionPerformed

    private void BtnSimpanImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanImunisasiActionPerformed
        if (KdImunisasi.getText().trim().isEmpty() || NmImunisasi.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnImunisasi, "Imunisasi");
        } else {
            if (Sequel.menyimpantf("riwayat_imunisasi", "?,?,?",
                    "Riwayat Imunisasi", 3, new String[]{
                        TNoRM.getText(), KdImunisasi.getText(), ImunisasiKe.
                        getSelectedItem().toString()
                    }) == true) {
                KdImunisasi.setText("");
                NmImunisasi.setText("");
                ImunisasiKe.setSelectedIndex(0);
                tampilImunisasi();
            }
        }
    }//GEN-LAST:event_BtnSimpanImunisasiActionPerformed

    private void BtnImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImunisasiActionPerformed
        imunisasi.isCek();
        imunisasi.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        imunisasi.setLocationRelativeTo(internalFrame1);
        imunisasi.setVisible(true);
    }//GEN-LAST:event_BtnImunisasiActionPerformed

    private void BtnImunisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnImunisasiKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnImunisasiKeyPressed

    private void KdImunisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdImunisasiKeyPressed

    }//GEN-LAST:event_KdImunisasiKeyPressed

    private void ImunisasiKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ImunisasiKeKeyPressed
        Valid.pindah(evt, TglAsuhan, TD);
    }//GEN-LAST:event_ImunisasiKeKeyPressed

    private void BtnHapusImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusImunisasiActionPerformed
        Sequel.queryu2(
                "delete from riwayat_imunisasi where no_rkm_medis=? and kode_imunisasi=? and no_imunisasi=?",
                3, new String[]{TNoRM.getText(), KdImunisasi.getText(),
                    ImunisasiKe.getSelectedItem().toString()});
        DlgRiwayatImunisasi.dispose();
        tampilImunisasi();
    }//GEN-LAST:event_BtnHapusImunisasiActionPerformed

    private void BtnHapusImunisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusImunisasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusImunisasiKeyPressed

    private void BtnPanggilHapusImunisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPanggilHapusImunisasiActionPerformed
        if (tbImunisasi.getSelectedRow() > -1) {
            if (TNoRM.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Pilih terlebih dahulu pasien yang mau dihapus data riwayat imunisasinya...");
                Anamnesis.requestFocus();
            } else {
                BtnImunisasi.setEnabled(false);
                KdImunisasi.setText(tbImunisasi.getValueAt(tbImunisasi.
                        getSelectedRow(), 0).toString());
                NmImunisasi.setText(tbImunisasi.getValueAt(tbImunisasi.
                        getSelectedRow(), 1).toString());
                BtnSimpanImunisasi.setVisible(false);
                BtnHapusImunisasi.setVisible(true);
                DlgRiwayatImunisasi.setLocationRelativeTo(internalFrame1);
                DlgRiwayatImunisasi.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnPanggilHapusImunisasiActionPerformed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        Valid.pindah2(evt, TCariMasalah, BtnSimpan);
    }//GEN-LAST:event_RencanaKeyPressed

    private void TCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilRencana2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            BtnCariRencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCariMasalah.requestFocus();
        }
    }//GEN-LAST:event_TCariRencanaKeyPressed

    private void BtnCariRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariRencanaActionPerformed
        tampilRencana2();
    }//GEN-LAST:event_BtnCariRencanaActionPerformed

    private void BtnCariRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilRencana2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TCariRencana.requestFocus();
        }
    }//GEN-LAST:event_BtnCariRencanaKeyPressed

    private void BtnAllRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllRencanaActionPerformed
        TCariRencana.setText("");
        tampilRencana();
        tampilRencana2();
    }//GEN-LAST:event_BtnAllRencanaActionPerformed

    private void BtnAllRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllRencanaActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariRencana, TCariRencana);
        }
    }//GEN-LAST:event_BtnAllRencanaKeyPressed

    private void BtnTambahRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahRencanaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterRencanaKeperawatanAnak form = new MasterRencanaKeperawatanAnak(
                null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            SkalaAktivitas.requestFocus();
        }
    }//GEN-LAST:event_TCariMasalahKeyPressed

    private void BtnCariMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariMasalahActionPerformed
        tampilMasalah2();
    }//GEN-LAST:event_BtnCariMasalahActionPerformed

    private void BtnCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            SkalaAktivitas.requestFocus();
        }
    }//GEN-LAST:event_BtnCariMasalahKeyPressed

    private void BtnAllMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllMasalahActionPerformed
        TCari.setText("");
        tampilMasalah();
    }//GEN-LAST:event_BtnAllMasalahActionPerformed

    private void BtnAllMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllMasalahActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariMasalah, TCariMasalah);
        }
    }//GEN-LAST:event_BtnAllMasalahKeyPressed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatanAnak form = new MasterMasalahKeperawatanAnak(
                null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void tbMasalahKeperawatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanMouseClicked
        if (tabModeMasalah.getRowCount() != 0) {
            try {
                tampilRencana2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanMouseClicked

    private void tbMasalahKeperawatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyPressed
        if (tabModeMasalah.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyPressed

    private void tbMasalahKeperawatanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKeyReleased
        if (tabModeMasalah.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilRencana2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKeyReleased

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
            tbRencanaKeperawatan.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
            tbRencanaKeperawatan.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void KesadaranMentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranMentalKeyPressed
        Valid.pindah(evt, Alergi, KeadaanMentalUmum);
    }//GEN-LAST:event_KesadaranMentalKeyPressed

    private void KeadaanMentalUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanMentalUmumKeyPressed
        Valid.pindah(evt, KesadaranMental, GCS);
    }//GEN-LAST:event_KeadaanMentalUmumKeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        Valid.pindah(evt, Suhu, BB);
    }//GEN-LAST:event_SpO2KeyPressed

    private void B1IramaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B1IramaKeyPressed
        Valid.pindah(evt, B1O2Nafas, B1SuaraNafas);
    }//GEN-LAST:event_B1IramaKeyPressed

    private void B1SuaraNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B1SuaraNafasKeyPressed
        Valid.pindah(evt, B1Irama, B1KetO2Nafas);
    }//GEN-LAST:event_B1SuaraNafasKeyPressed

    private void B1KetO2NafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B1KetO2NafasKeyPressed
//        Valid.pindah(evt,SuaraNafas,AlatBantuNafas);
    }//GEN-LAST:event_B1KetO2NafasKeyPressed

    private void B1JenisNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B1JenisNafasKeyPressed
//        Valid.pindah(evt,KetSistemSarafKepala,O2Nafas);
    }//GEN-LAST:event_B1JenisNafasKeyPressed

    private void B1O2NafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B1O2NafasKeyPressed
        Valid.pindah(evt, B1JenisNafas, B1Irama);
    }//GEN-LAST:event_B1O2NafasKeyPressed

    private void B2IramaJantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B2IramaJantungKeyPressed
//        Valid.pindah(evt,AlatBantuNafas,Acral);
    }//GEN-LAST:event_B2IramaJantungKeyPressed

    private void B2AcralKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B2AcralKeyPressed
//        Valid.pindah(evt,IramaJantung,KetKardiovaskularSirkulasi);
    }//GEN-LAST:event_B2AcralKeyPressed

    private void B2ConjungtivaAnemisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B2ConjungtivaAnemisKeyPressed
//        Valid.pindah(evt,KetKardiovaskularSirkulasi,Kesadaran);
    }//GEN-LAST:event_B2ConjungtivaAnemisKeyPressed

    private void B3TingkatKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3TingkatKesadaranKeyPressed
        Valid.pindah(evt, B6Kepala, B3Tangisan);
    }//GEN-LAST:event_B3TingkatKesadaranKeyPressed

    private void B3KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3KesadaranKeyPressed
        Valid.pindah(evt, B2ConjungtivaAnemis, B3KetGangguanTidur);
    }//GEN-LAST:event_B3KesadaranKeyPressed

    private void B3KetGangguanTidurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3KetGangguanTidurKeyPressed
//        Valid.pindah(evt,Kesadaran,RespirasiSuaraNafas);
    }//GEN-LAST:event_B3KetGangguanTidurKeyPressed

    private void B3TangisanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3TangisanKeyPressed
//        Valid.pindah(evt,TingkatKesadaran,KetRespirasiJenisPernafasan);
    }//GEN-LAST:event_B3TangisanKeyPressed

    private void B3KelainanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3KelainanKeyPressed
//        Valid.pindah(evt,KetRespirasiJenisPernafasan,GastrointestinalMulut);
    }//GEN-LAST:event_B3KelainanKeyPressed

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        Valid.pindah2(evt, RPD, RPK);
    }//GEN-LAST:event_RPSKeyPressed

    private void B2S1S2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B2S1S2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B2S1S2KeyPressed

    private void B3JamIstirahatTidurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3JamIstirahatTidurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3JamIstirahatTidurKeyPressed

    private void B3GangguanTidurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3GangguanTidurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3GangguanTidurKeyPressed

    private void B3LingkarKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3LingkarKepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3LingkarKepalaKeyPressed

    private void B3UbunUbunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3UbunUbunKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3UbunUbunKeyPressed

    private void B3PupilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3PupilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3PupilKeyPressed

    private void B3SkleraMataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3SkleraMataKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3SkleraMataKeyPressed

    private void B3GerakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3GerakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3GerakanKeyPressed

    private void B3PancaIndraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3PancaIndraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3PancaIndraKeyPressed

    private void B3KejangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3KejangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3KejangKeyPressed

    private void B3ReflekRootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3ReflekRootingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3ReflekRootingKeyPressed

    private void B3KetPancaIndraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B3KetPancaIndraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B3KetPancaIndraKeyPressed

    private void B4KebersihanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4KebersihanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4KebersihanKeyPressed

    private void B4ProduksiUrineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4ProduksiUrineKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4ProduksiUrineKeyPressed

    private void B4KetSekretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4KetSekretKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4KetSekretKeyPressed

    private void B4SekretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4SekretKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4SekretKeyPressed

    private void B4WarnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4WarnaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4WarnaKeyPressed

    private void B4GangguanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4GangguanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4GangguanKeyPressed

    private void B4AlatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4AlatBantuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4AlatBantuKeyPressed

    private void B4KetWarnaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B4KetWarnaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B4KetWarnaKeyPressed

    private void B5NafsuMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5NafsuMakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5NafsuMakanKeyPressed

    private void B5FrekuensiMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5FrekuensiMakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5FrekuensiMakanKeyPressed

    private void B5PorsiMakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5PorsiMakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5PorsiMakanKeyPressed

    private void B5KetWarnaBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5KetWarnaBABKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5KetWarnaBABKeyPressed

    private void B5KonsistenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5KonsistenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5KonsistenKeyPressed

    private void B5CaraMinumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5CaraMinumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5CaraMinumKeyPressed

    private void B5PerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5PerutKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5PerutKeyPressed

    private void B5KelainanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5KelainanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5KelainanKeyPressed

    private void B5AnusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5AnusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5AnusKeyPressed

    private void B5LidahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5LidahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5LidahKeyPressed

    private void B5ReflekRootingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5ReflekRootingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5ReflekRootingKeyPressed

    private void B5MinumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5MinumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5MinumKeyPressed

    private void B5JenisMinumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5JenisMinumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5JenisMinumKeyPressed

    private void B5BABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5BABKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5BABKeyPressed

    private void B5WarnaBABKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5WarnaBABKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5WarnaBABKeyPressed

    private void B5PeristaltikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5PeristaltikKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5PeristaltikKeyPressed

    private void B5SelaputLenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B5SelaputLenderKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B5SelaputLenderKeyPressed

    private void B6PergerakanSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6PergerakanSendiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6PergerakanSendiKeyPressed

    private void B6KetPergerakanSendiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KetPergerakanSendiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KetPergerakanSendiKeyPressed

    private void B6WarnaKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6WarnaKulitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6WarnaKulitKeyPressed

    private void B6KepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KepalaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KepalaKeyPressed

    private void B6IntergitasKulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6IntergitasKulitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6IntergitasKulitKeyPressed

    private void B6TaliPusatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6TaliPusatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6TaliPusatKeyPressed

    private void B6TugorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6TugorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6TugorKeyPressed

    private void B6OdemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6OdemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6OdemKeyPressed

    private void B6KetOdemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KetOdemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KetOdemKeyPressed

    private void B6KekuatanOtotKiriAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KekuatanOtotKiriAtasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KekuatanOtotKiriAtasKeyPressed

    private void B6KekuatanOtotKananAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KekuatanOtotKananAtasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KekuatanOtotKananAtasKeyPressed

    private void B6KekuatanOtotKiriBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KekuatanOtotKiriBawahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KekuatanOtotKiriBawahKeyPressed

    private void B6KekuatanOtotKananBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B6KekuatanOtotKananBawahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_B6KekuatanOtotKananBawahKeyPressed

    private void AlatGenitalLakiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatGenitalLakiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlatGenitalLakiKeyPressed

    private void AlatGenitalPerampuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatGenitalPerampuanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlatGenitalPerampuanKeyPressed

    private void DerajatIkterusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DerajatIkterusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DerajatIkterusKeyPressed

    private void DaerahIkterusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DaerahIkterusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DaerahIkterusKeyPressed

    private void KadarBilirubinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KadarBilirubinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KadarBilirubinKeyPressed

    private void DerajatIkterusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DerajatIkterusItemStateChanged
        if (DerajatIkterus.getSelectedItem().equals("0")) {
            DaerahIkterus.setText("Tidak ada ikterus");
            KadarBilirubin.setText("");
        } else if (DerajatIkterus.getSelectedItem().equals("I")) {
            DaerahIkterus.setText("Daerah kepala dan leher");
            KadarBilirubin.setText("5,0 mg %");
        } else if (DerajatIkterus.getSelectedItem().equals("II")) {
            DaerahIkterus.setText("Sampai badan atas");
            KadarBilirubin.setText("9,0 mg %");
        } else if (DerajatIkterus.getSelectedItem().equals("III")) {
            DaerahIkterus.setText("Sampai badan bawah hingga tungkai");
            KadarBilirubin.setText("11,4 mg %");
        } else if (DerajatIkterus.getSelectedItem().equals("IV")) {
            DaerahIkterus.setText("Sampai daerah lengan, kaki bawah, lutut");
            KadarBilirubin.setText("12,4 mg %");
        } else {
            DaerahIkterus.setText("Sampai daerah telapak tangan dan kaki");
            KadarBilirubin.setText("16,0 mg %");
        }
    }//GEN-LAST:event_DerajatIkterusItemStateChanged

    private void PenilaianApgarScoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenilaianApgarScoreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenilaianApgarScoreKeyPressed

    private void PenilaianDownScoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenilaianDownScoreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenilaianDownScoreKeyPressed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed

    }//GEN-LAST:event_KdPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        i = 1;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        Valid.pindah(evt, BtnSimpan, BtnPetugas2);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt, MacamKasus, KetAnamnesis);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        Valid.pindah(evt, BtnDPJP, MacamKasus);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void BtnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas2ActionPerformed
        i = 2;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas2ActionPerformed

    private void BtnPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugas2KeyPressed
        Valid.pindah(evt, BtnPetugas, BtnDPJP);
    }//GEN-LAST:event_BtnPetugas2KeyPressed

    private void KdPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugas2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugas2KeyPressed

    private void KdDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdDPJPKeyPressed

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void BtnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDPJPKeyPressed
        Valid.pindah(evt, BtnPetugas2, MacamKasus);
    }//GEN-LAST:event_BtnDPJPKeyPressed

    private void TibadiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TibadiRuangKeyPressed
        Valid.pindah(evt, KetAnamnesis, CaraMasuk);
    }//GEN-LAST:event_TibadiRuangKeyPressed

    private void CaraMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraMasukKeyPressed
        Valid.pindah(evt, TibadiRuang, RPS);
    }//GEN-LAST:event_CaraMasukKeyPressed

    private void MacamKasusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MacamKasusKeyPressed
        Valid.pindah(evt, BtnDPJP, Anamnesis);
    }//GEN-LAST:event_MacamKasusKeyPressed

    private void KetAnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetAnamnesisKeyPressed
        Valid.pindah(evt, Anamnesis, TibadiRuang);
    }//GEN-LAST:event_KetAnamnesisKeyPressed

    private void SkalaHumptyDumpty1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty1ItemStateChanged
        if (SkalaHumptyDumpty1.getSelectedIndex() == 0) {
            NilaiHumptyDumpty1.setText("4");
        } else if (SkalaHumptyDumpty1.getSelectedIndex() == 1) {
            NilaiHumptyDumpty1.setText("3");
        } else if (SkalaHumptyDumpty1.getSelectedIndex() == 2) {
            NilaiHumptyDumpty1.setText("2");
        } else {
            NilaiHumptyDumpty1.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty1ItemStateChanged

    private void SkalaHumptyDumpty1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty1KeyPressed

    }//GEN-LAST:event_SkalaHumptyDumpty1KeyPressed

    private void SkalaHumptyDumpty2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty2ItemStateChanged
        if (SkalaHumptyDumpty2.getSelectedIndex() == 0) {
            NilaiHumptyDumpty2.setText("2");
        } else {
            NilaiHumptyDumpty2.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty2ItemStateChanged

    private void SkalaHumptyDumpty2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty2KeyPressed
        Valid.pindah(evt, SkalaHumptyDumpty1, SkalaHumptyDumpty3);
    }//GEN-LAST:event_SkalaHumptyDumpty2KeyPressed

    private void SkalaHumptyDumpty3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty3ItemStateChanged
        if (SkalaHumptyDumpty3.getSelectedIndex() == 0) {
            NilaiHumptyDumpty3.setText("4");
        } else if (SkalaHumptyDumpty3.getSelectedIndex() == 1) {
            NilaiHumptyDumpty3.setText("3");
        } else if (SkalaHumptyDumpty3.getSelectedIndex() == 2) {
            NilaiHumptyDumpty3.setText("2");
        } else {
            NilaiHumptyDumpty3.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty3ItemStateChanged

    private void SkalaHumptyDumpty3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty3KeyPressed
        Valid.pindah(evt, SkalaHumptyDumpty2, SkalaHumptyDumpty4);
    }//GEN-LAST:event_SkalaHumptyDumpty3KeyPressed

    private void SkalaHumptyDumpty4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty4ItemStateChanged
        if (SkalaHumptyDumpty4.getSelectedIndex() == 0) {
            NilaiHumptyDumpty4.setText("3");
        } else if (SkalaHumptyDumpty4.getSelectedIndex() == 1) {
            NilaiHumptyDumpty4.setText("2");
        } else {
            NilaiHumptyDumpty4.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty4ItemStateChanged

    private void SkalaHumptyDumpty4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty4KeyPressed
        Valid.pindah(evt, SkalaHumptyDumpty3, SkalaHumptyDumpty5);
    }//GEN-LAST:event_SkalaHumptyDumpty4KeyPressed

    private void SkalaHumptyDumpty5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty5ItemStateChanged
        if (SkalaHumptyDumpty5.getSelectedIndex() == 0) {
            NilaiHumptyDumpty5.setText("4");
        } else if (SkalaHumptyDumpty5.getSelectedIndex() == 1) {
            NilaiHumptyDumpty5.setText("3");
        } else if (SkalaHumptyDumpty5.getSelectedIndex() == 2) {
            NilaiHumptyDumpty5.setText("2");
        } else {
            NilaiHumptyDumpty5.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty5ItemStateChanged

    private void SkalaHumptyDumpty5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty5KeyPressed
        Valid.pindah(evt, SkalaHumptyDumpty4, SkalaHumptyDumpty6);
    }//GEN-LAST:event_SkalaHumptyDumpty5KeyPressed

    private void SkalaHumptyDumpty6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty6ItemStateChanged
        if (SkalaHumptyDumpty6.getSelectedIndex() == 0) {
            NilaiHumptyDumpty6.setText("3");
        } else if (SkalaHumptyDumpty6.getSelectedIndex() == 1) {
            NilaiHumptyDumpty6.setText("2");
        } else {
            NilaiHumptyDumpty6.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty6ItemStateChanged

    private void SkalaHumptyDumpty6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty6KeyPressed
        Valid.pindah(evt, SkalaHumptyDumpty5, SkalaHumptyDumpty7);
    }//GEN-LAST:event_SkalaHumptyDumpty6KeyPressed

    private void SkalaHumptyDumpty7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty7ItemStateChanged
        if (SkalaHumptyDumpty7.getSelectedIndex() == 0) {
            NilaiHumptyDumpty4.setText("3");
        } else if (SkalaHumptyDumpty7.getSelectedIndex() == 1) {
            NilaiHumptyDumpty7.setText("2");
        } else {
            NilaiHumptyDumpty7.setText("1");
        }
        isTotalResikoHumptyDumpty();
    }//GEN-LAST:event_SkalaHumptyDumpty7ItemStateChanged

    private void SkalaHumptyDumpty7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaHumptyDumpty7KeyPressed

    }//GEN-LAST:event_SkalaHumptyDumpty7KeyPressed

  private void WarnaKetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WarnaKetubanKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_WarnaKetubanKeyPressed

  private void KelainanPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelainanPersalinanKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_KelainanPersalinanKeyPressed

  private void UsiaKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaKehamilanKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_UsiaKehamilanKeyPressed

  private void PenolongPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenolongPersalinanKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_PenolongPersalinanKeyPressed

  private void PenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenolongKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_PenolongKeyPressed

  private void BtnPenolongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenolongActionPerformed
      i = 1;
//    penolong.isCek();
      penolong.setSize(internalFrame1.getWidth() - 20, internalFrame1.
              getHeight() - 20);
      penolong.setLocationRelativeTo(internalFrame1);
      penolong.setAlwaysOnTop(false);
      penolong.setVisible(true);
  }//GEN-LAST:event_BtnPenolongActionPerformed

  private void BtnPenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPenolongKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_BtnPenolongKeyPressed

  private void SkalaAktivitasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaAktivitasItemStateChanged
      NilaiAktivitas.setText(SkalaAktivitas.getSelectedIndex() + "");
      SkalaNyeri.setText("" + (Integer.parseInt(NilaiWajah.getText()) + Integer.
              parseInt(NilaiTangisan.getText()) + Integer.parseInt(
              NilaiPolaNapas.getText()) + Integer.
              parseInt(NilaiLengan.getText()) + Integer.parseInt(NilaiTungkai.
              getText()) + Integer.parseInt(NilaiAktivitas.getText())));
  }//GEN-LAST:event_SkalaAktivitasItemStateChanged

  private void SkalaAktivitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaAktivitasKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_SkalaAktivitasKeyPressed

  private void NilaiAktivitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiAktivitasKeyPressed
      // TODO add your handling code here:
  }//GEN-LAST:event_NilaiAktivitasKeyPressed

  private void NilaiHumptyDumpty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NilaiHumptyDumpty3ActionPerformed
      // TODO add your handling code here:
  }//GEN-LAST:event_NilaiHumptyDumpty3ActionPerformed

    private void WajibKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WajibKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_WajibKeyPressed

    private void KetHalanganLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetHalanganLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetHalanganLainKeyPressed

    private void ToharahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToharahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ToharahKeyPressed

    private void SholatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SholatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SholatKeyPressed

    private void MotivasiKesembuhanIbadahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MotivasiKesembuhanIbadahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MotivasiKesembuhanIbadahKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalKeperawatanRanapNeonatus dialog = new RMPenilaianAwalKeperawatanRanapNeonatus(
                    new javax.swing.JFrame(), true);
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
    private widget.ComboBox ADL;
    private widget.TextBox Agama;
    private widget.ComboBox AlatBantu;
    private widget.ComboBox AlatGenitalLaki;
    private widget.ComboBox AlatGenitalPerampuan;
    private widget.TextBox Alergi;
    private widget.TextBox Anakke;
    private widget.ComboBox Anamnesis;
    private widget.ComboBox B1Irama;
    private widget.ComboBox B1JenisNafas;
    private widget.TextBox B1KetO2Nafas;
    private widget.ComboBox B1NafasSpontan;
    private widget.TextBox B1O2Nafas;
    private widget.ComboBox B1SuaraNafas;
    private widget.ComboBox B2Acral;
    private widget.ComboBox B2ConjungtivaAnemis;
    private widget.ComboBox B2IramaJantung;
    private widget.ComboBox B2S1S2;
    private widget.TextBox B3GangguanTidur;
    private widget.ComboBox B3Gerakan;
    private widget.TextBox B3JamIstirahatTidur;
    private widget.ComboBox B3Kejang;
    private widget.ComboBox B3Kelainan;
    private widget.ComboBox B3Kesadaran;
    private widget.ComboBox B3KetGangguanTidur;
    private widget.TextBox B3KetPancaIndra;
    private widget.TextBox B3LingkarKepala;
    private widget.ComboBox B3PancaIndra;
    private widget.ComboBox B3Pupil;
    private widget.ComboBox B3ReflekRooting;
    private widget.ComboBox B3SkleraMata;
    private widget.ComboBox B3Tangisan;
    private widget.ComboBox B3TingkatKesadaran;
    private widget.ComboBox B3UbunUbun;
    private widget.ComboBox B4AlatBantu;
    private widget.ComboBox B4Gangguan;
    private widget.ComboBox B4Kebersihan;
    private widget.TextBox B4KetSekret;
    private widget.TextBox B4KetWarna;
    private widget.TextBox B4ProduksiUrine;
    private widget.ComboBox B4Sekret;
    private widget.ComboBox B4Warna;
    private widget.ComboBox B5Anus;
    private widget.TextBox B5BAB;
    private widget.ComboBox B5CaraMinum;
    private widget.TextBox B5FrekuensiMakan;
    private widget.TextBox B5JenisMinum;
    private widget.ComboBox B5Kelainan;
    private widget.ComboBox B5KetWarnaBAB;
    private widget.TextBox B5Konsisten;
    private widget.ComboBox B5Lidah;
    private widget.TextBox B5Minum;
    private widget.ComboBox B5NafsuMakan;
    private widget.TextBox B5Peristaltik;
    private widget.ComboBox B5Perut;
    private widget.TextBox B5PorsiMakan;
    private widget.ComboBox B5ReflekRooting;
    private widget.ComboBox B5SelaputLender;
    private widget.TextBox B5WarnaBAB;
    private widget.ComboBox B6IntergitasKulit;
    private widget.TextBox B6KekuatanOtotKananAtas;
    private widget.TextBox B6KekuatanOtotKananBawah;
    private widget.TextBox B6KekuatanOtotKiriAtas;
    private widget.TextBox B6KekuatanOtotKiriBawah;
    private widget.ComboBox B6Kepala;
    private widget.TextBox B6KetOdem;
    private widget.TextBox B6KetPergerakanSendi;
    private widget.ComboBox B6Odem;
    private widget.ComboBox B6PergerakanSendi;
    private widget.ComboBox B6TaliPusat;
    private widget.ComboBox B6Tugor;
    private widget.ComboBox B6WarnaKulit;
    private widget.TextBox BB;
    private widget.TextBox Bahasa;
    private widget.Button BtnAll;
    private widget.Button BtnAllMasalah;
    private widget.Button BtnAllRencana;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariMasalah;
    private widget.Button BtnCariRencana;
    private widget.Button BtnDPJP;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusImunisasi;
    private widget.Button BtnImunisasi;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluarImunisasi;
    private widget.Button BtnPanggilHapusImunisasi;
    private widget.Button BtnPenolong;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanImunisasi;
    private widget.Button BtnTambahImunisasi;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahRencana;
    private widget.TextBox CacatFisik;
    private widget.ComboBox CaraKelahiran;
    private widget.ComboBox CaraMasuk;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DaerahIkterus;
    private widget.TextBox DariSaudara;
    private widget.ComboBox DerajatIkterus;
    private widget.TextArea DetailRencana;
    private javax.swing.JDialog DlgRiwayatImunisasi;
    private widget.ComboBox Edukasi;
    private widget.ComboBox Ekonomi;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox GCS;
    private widget.TextBox GangguanEmosi;
    private widget.ComboBox HubunganKeluarga;
    private widget.ComboBox ImunisasiKe;
    private widget.TextBox Jk;
    private widget.TextBox KadarBilirubin;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdImunisasi;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas2;
    private widget.ComboBox KeadaanMentalUmum;
    private widget.ComboBox KelainanBawaan;
    private widget.TextBox KelainanPersalinan;
    private widget.TextArea KeluhanUtama;
    private widget.TextBox KesadaranMental;
    private widget.TextBox KetAnamnesis;
    private widget.TextBox KetBantu;
    private widget.TextBox KetBudaya;
    private widget.TextBox KetCaraKelahiran;
    private widget.TextBox KetEdukasi;
    private widget.TextBox KetHalanganLain;
    private widget.TextBox KetKelainanBawaan;
    private widget.TextBox KetPengasuh;
    private widget.TextBox KetProthesa;
    private widget.TextBox KetPsiko;
    private widget.ComboBox Kriteria1;
    private widget.ComboBox Kriteria2;
    private widget.ComboBox Kriteria3;
    private widget.ComboBox Kriteria4;
    private widget.Label LCount;
    private widget.TextBox LD;
    private widget.TextBox LK;
    private widget.TextBox LP;
    private widget.editorpane LoadHTML;
    private widget.ComboBox MacamKasus;
    private widget.ComboBox MotivasiKesembuhanIbadah;
    private widget.TextBox Nadi;
    private widget.TextBox NilaiAktivitas;
    private widget.TextBox NilaiGizi1;
    private widget.TextBox NilaiGizi2;
    private widget.TextBox NilaiGizi3;
    private widget.TextBox NilaiGizi4;
    private widget.TextBox NilaiHumptyDumpty1;
    private widget.TextBox NilaiHumptyDumpty2;
    private widget.TextBox NilaiHumptyDumpty3;
    private widget.TextBox NilaiHumptyDumpty4;
    private widget.TextBox NilaiHumptyDumpty5;
    private widget.TextBox NilaiHumptyDumpty6;
    private widget.TextBox NilaiHumptyDumpty7;
    private widget.TextBox NilaiHumptyDumptyTotal;
    private widget.TextBox NilaiLengan;
    private widget.TextBox NilaiPolaNapas;
    private widget.TextBox NilaiTangisan;
    private widget.TextBox NilaiTungkai;
    private widget.TextBox NilaiWajah;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmImunisasi;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas2;
    private widget.PanelBiasa PanelAccor;
    private widget.ComboBox Pengasuh;
    private widget.TextArea PenilaianApgarScore;
    private widget.TextArea PenilaianDownScore;
    private widget.ComboBox Penolong;
    private widget.TextBox PenolongPersalinan;
    private javax.swing.JPopupMenu Popup;
    private widget.ComboBox Prothesa;
    private widget.TextArea RPD;
    private widget.TextArea RPK;
    private widget.TextArea RPO;
    private widget.TextArea RPS;
    private widget.TextBox RR;
    private widget.TextArea Rencana;
    private widget.ComboBox SG1;
    private widget.ComboBox SG2;
    private widget.ComboBox SG3;
    private widget.ComboBox SG4;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox Sholat;
    private widget.ComboBox SkalaAktivitas;
    private widget.ComboBox SkalaHumptyDumpty1;
    private widget.ComboBox SkalaHumptyDumpty2;
    private widget.ComboBox SkalaHumptyDumpty3;
    private widget.ComboBox SkalaHumptyDumpty4;
    private widget.ComboBox SkalaHumptyDumpty5;
    private widget.ComboBox SkalaHumptyDumpty6;
    private widget.ComboBox SkalaHumptyDumpty7;
    private widget.ComboBox SkalaLengan;
    private widget.TextBox SkalaNyeri;
    private widget.ComboBox SkalaPolaNapas;
    private widget.ComboBox SkalaTangisan;
    private widget.ComboBox SkalaTungkai;
    private widget.ComboBox SkalaWajah;
    private widget.TextBox SpO2;
    private widget.ComboBox StatusBudaya;
    private widget.ComboBox StatusPsiko;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextBox TCariRencana;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRencanaKeperawatan;
    private widget.TextBox TanggalRegistrasi;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox TibadiRuang;
    private widget.Label TingkatHumptyDumpty;
    private widget.ComboBox Toharah;
    private widget.TextBox TotalNilaiGizi;
    private widget.ComboBox UmurKelahiran;
    private widget.TextBox UsiaBerdiri;
    private widget.TextBox UsiaBerjalan;
    private widget.TextBox UsiaBicara;
    private widget.TextBox UsiaDuduk;
    private widget.TextBox UsiaGigi;
    private widget.TextBox UsiaKehamilan;
    private widget.TextBox UsiaMembaca;
    private widget.TextBox UsiaMenulis;
    private widget.TextBox UsiaTengkurap;
    private widget.ComboBox Wajib;
    private widget.ComboBox WarnaKetuban;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel11;
    private widget.Label jLabel125;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel149;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel176;
    private widget.Label jLabel177;
    private widget.Label jLabel178;
    private widget.Label jLabel179;
    private widget.Label jLabel18;
    private widget.Label jLabel180;
    private widget.Label jLabel181;
    private widget.Label jLabel182;
    private widget.Label jLabel183;
    private widget.Label jLabel184;
    private widget.Label jLabel185;
    private widget.Label jLabel186;
    private widget.Label jLabel187;
    private widget.Label jLabel188;
    private widget.Label jLabel189;
    private widget.Label jLabel19;
    private widget.Label jLabel190;
    private widget.Label jLabel191;
    private widget.Label jLabel192;
    private widget.Label jLabel193;
    private widget.Label jLabel194;
    private widget.Label jLabel195;
    private widget.Label jLabel196;
    private widget.Label jLabel197;
    private widget.Label jLabel198;
    private widget.Label jLabel199;
    private widget.Label jLabel20;
    private widget.Label jLabel200;
    private widget.Label jLabel201;
    private widget.Label jLabel202;
    private widget.Label jLabel203;
    private widget.Label jLabel204;
    private widget.Label jLabel205;
    private widget.Label jLabel206;
    private widget.Label jLabel207;
    private widget.Label jLabel208;
    private widget.Label jLabel209;
    private widget.Label jLabel21;
    private widget.Label jLabel210;
    private widget.Label jLabel211;
    private widget.Label jLabel212;
    private widget.Label jLabel213;
    private widget.Label jLabel214;
    private widget.Label jLabel215;
    private widget.Label jLabel216;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel22;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel23;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel237;
    private widget.Label jLabel238;
    private widget.Label jLabel239;
    private widget.Label jLabel24;
    private widget.Label jLabel240;
    private widget.Label jLabel241;
    private widget.Label jLabel242;
    private widget.Label jLabel243;
    private widget.Label jLabel244;
    private widget.Label jLabel245;
    private widget.Label jLabel246;
    private widget.Label jLabel247;
    private widget.Label jLabel248;
    private widget.Label jLabel249;
    private widget.Label jLabel25;
    private widget.Label jLabel250;
    private widget.Label jLabel251;
    private widget.Label jLabel252;
    private widget.Label jLabel253;
    private widget.Label jLabel254;
    private widget.Label jLabel255;
    private widget.Label jLabel256;
    private widget.Label jLabel257;
    private widget.Label jLabel258;
    private widget.Label jLabel259;
    private widget.Label jLabel26;
    private widget.Label jLabel260;
    private widget.Label jLabel261;
    private widget.Label jLabel262;
    private widget.Label jLabel263;
    private widget.Label jLabel264;
    private widget.Label jLabel265;
    private widget.Label jLabel27;
    private widget.Label jLabel270;
    private widget.Label jLabel28;
    private widget.Label jLabel289;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel9;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.PanelBiasa panelBiasa1;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JCheckBox pilihan1;
    private javax.swing.JCheckBox pilihan2;
    private javax.swing.JCheckBox pilihan3;
    private javax.swing.JCheckBox pilihan4;
    private javax.swing.JCheckBox pilihan5;
    private javax.swing.JCheckBox pilihan6;
    private javax.swing.JCheckBox pilihan7;
    private javax.swing.JCheckBox pilihan8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppSemua;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbImunisasi;
    private widget.Table tbImunisasi2;
    private widget.Table tbMasalahDetailMasalah;
    private widget.Table tbMasalahKeperawatan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaKeperawatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select penilaian_awal_keperawatan_ranap_bayi.no_rawat,penilaian_awal_keperawatan_ranap_bayi.tanggal,"
                    + "penilaian_awal_keperawatan_ranap_bayi.informasi,penilaian_awal_keperawatan_ranap_bayi.ket_informasi,penilaian_awal_keperawatan_ranap_bayi.tiba_diruang_rawat,penilaian_awal_keperawatan_ranap_bayi.kasus_trauma,penilaian_awal_keperawatan_ranap_bayi.cara_masuk,penilaian_awal_keperawatan_ranap_bayi.keluhan_utama,penilaian_awal_keperawatan_ranap_bayi.rps,penilaian_awal_keperawatan_ranap_bayi.rpd,"
                    + "penilaian_awal_keperawatan_ranap_bayi.rpk,penilaian_awal_keperawatan_ranap_bayi.rpo,penilaian_awal_keperawatan_ranap_bayi.alergi,penilaian_awal_keperawatan_ranap_bayi.pemeriksaan_mental,penilaian_awal_keperawatan_ranap_bayi.pemeriksaan_keadaan_umum,penilaian_awal_keperawatan_ranap_bayi.td,"
                    + "penilaian_awal_keperawatan_ranap_bayi.nadi,penilaian_awal_keperawatan_ranap_bayi.spo2,penilaian_awal_keperawatan_ranap_bayi.rr,penilaian_awal_keperawatan_ranap_bayi.suhu,penilaian_awal_keperawatan_ranap_bayi.gcs,penilaian_awal_keperawatan_ranap_bayi.bb,penilaian_awal_keperawatan_ranap_bayi.tb,penilaian_awal_keperawatan_ranap_bayi.lp,penilaian_awal_keperawatan_ranap_bayi.lk,penilaian_awal_keperawatan_ranap_bayi.ld,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b1_nafas_spontan,penilaian_awal_keperawatan_ranap_bayi.b1_jenis,penilaian_awal_keperawatan_ranap_bayi.b1_alat_bantu,penilaian_awal_keperawatan_ranap_bayi.b1_ket_alat_bantu,penilaian_awal_keperawatan_ranap_bayi.b1_irama,penilaian_awal_keperawatan_ranap_bayi.b1_suara_nafas,penilaian_awal_keperawatan_ranap_bayi.b2_irama_jantung,penilaian_awal_keperawatan_ranap_bayi.b2_acral,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b2_tunggal,penilaian_awal_keperawatan_ranap_bayi.b2_conjungtiva_anemis,penilaian_awal_keperawatan_ranap_bayi.b3_kesadaran,penilaian_awal_keperawatan_ranap_bayi.b3_istirahat_tidur,penilaian_awal_keperawatan_ranap_bayi.b3_gangguan_tidur,penilaian_awal_keperawatan_ranap_bayi.b3_ket_gangguan_tidur,penilaian_awal_keperawatan_ranap_bayi.b3_tingkat_kesadaran,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b3_tangisan,penilaian_awal_keperawatan_ranap_bayi.b3_kepala,penilaian_awal_keperawatan_ranap_bayi.b3_kelainan,penilaian_awal_keperawatan_ranap_bayi.b3_ubun_ubun,penilaian_awal_keperawatan_ranap_bayi.b3_pupil,penilaian_awal_keperawatan_ranap_bayi.b3_sklera_mata,penilaian_awal_keperawatan_ranap_bayi.b3_gerakan,penilaian_awal_keperawatan_ranap_bayi.b3_panca_indra,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b3_ket_panca_indra,penilaian_awal_keperawatan_ranap_bayi.b3_kejang,penilaian_awal_keperawatan_ranap_bayi.b3_reflek_rooting,penilaian_awal_keperawatan_ranap_bayi.b4_kebersihan,penilaian_awal_keperawatan_ranap_bayi.b4_sekret,penilaian_awal_keperawatan_ranap_bayi.b4_ket_sekret,penilaian_awal_keperawatan_ranap_bayi.b4_produksi_urine,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b4_warna,penilaian_awal_keperawatan_ranap_bayi.b4_ket_warna,penilaian_awal_keperawatan_ranap_bayi.b4_gangguan,penilaian_awal_keperawatan_ranap_bayi.b4_alat_bantu,penilaian_awal_keperawatan_ranap_bayi.b5_nafsu_makan,penilaian_awal_keperawatan_ranap_bayi.b5_frekuensi,penilaian_awal_keperawatan_ranap_bayi.b5_porsi_makan,penilaian_awal_keperawatan_ranap_bayi.b5_minum,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b5_jenis,penilaian_awal_keperawatan_ranap_bayi.b5_cara_minum,penilaian_awal_keperawatan_ranap_bayi.b5_anus,penilaian_awal_keperawatan_ranap_bayi.b5_bab,penilaian_awal_keperawatan_ranap_bayi.b5_konsisten,penilaian_awal_keperawatan_ranap_bayi.b5_warna,penilaian_awal_keperawatan_ranap_bayi.b5_ket_warna,penilaian_awal_keperawatan_ranap_bayi.b5_perut,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b5_paristaltik,penilaian_awal_keperawatan_ranap_bayi.b5_reflek_rooting,penilaian_awal_keperawatan_ranap_bayi.b5_kelainan,penilaian_awal_keperawatan_ranap_bayi.b5_lidah,penilaian_awal_keperawatan_ranap_bayi.b5_selaput_lender,penilaian_awal_keperawatan_ranap_bayi.b6_pergerakan_sendi,penilaian_awal_keperawatan_ranap_bayi.b6_ket_pergerakan_sendi,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b6_warna_kulit,penilaian_awal_keperawatan_ranap_bayi.b6_intergitas_kulit,penilaian_awal_keperawatan_ranap_bayi.b6_kepala,penilaian_awal_keperawatan_ranap_bayi.b6_tali_pusat,penilaian_awal_keperawatan_ranap_bayi.b6_tugor,penilaian_awal_keperawatan_ranap_bayi.b6_odem,penilaian_awal_keperawatan_ranap_bayi.b6_lokasi,penilaian_awal_keperawatan_ranap_bayi.b6_otot_kiri_atas,"
                    + "penilaian_awal_keperawatan_ranap_bayi.b6_otot_kanan_atas,penilaian_awal_keperawatan_ranap_bayi.b6_otot_kiri_bawah,penilaian_awal_keperawatan_ranap_bayi.b6_otot_kanan_bawah,penilaian_awal_keperawatan_ranap_bayi.genital_laki_laki,penilaian_awal_keperawatan_ranap_bayi.genital_perempuan,penilaian_awal_keperawatan_ranap_bayi.derajat_ikterus,penilaian_awal_keperawatan_ranap_bayi.daerah_ikterus,penilaian_awal_keperawatan_ranap_bayi.perkiraan_kadar_bilirubin,"
                    + "penilaian_awal_keperawatan_ranap_bayi.apgar_score,penilaian_awal_keperawatan_ranap_bayi.down_score,penilaian_awal_keperawatan_ranap_bayi.anakke,penilaian_awal_keperawatan_ranap_bayi.darisaudara,penilaian_awal_keperawatan_ranap_bayi.caralahir,penilaian_awal_keperawatan_ranap_bayi.ket_caralahir,penilaian_awal_keperawatan_ranap_bayi.umurkelahiran,penilaian_awal_keperawatan_ranap_bayi.kelainanbawaan,penilaian_awal_keperawatan_ranap_bayi.ket_kelainan_bawaan,penilaian_awal_keperawatan_ranap_bayi.warnaketuban,penilaian_awal_keperawatan_ranap_bayi.kelainanpersalinan,penilaian_awal_keperawatan_ranap_bayi.usiakehamilan,penilaian_awal_keperawatan_ranap_bayi.penolong,penilaian_awal_keperawatan_ranap_bayi.penolongpersalinan,"
                    + "penilaian_awal_keperawatan_ranap_bayi.usiatengkurap,penilaian_awal_keperawatan_ranap_bayi.usiaduduk,penilaian_awal_keperawatan_ranap_bayi.usiaberdiri,penilaian_awal_keperawatan_ranap_bayi.usiagigipertama,penilaian_awal_keperawatan_ranap_bayi.usiaberjalan,penilaian_awal_keperawatan_ranap_bayi.usiabicara,penilaian_awal_keperawatan_ranap_bayi.usiamembaca,penilaian_awal_keperawatan_ranap_bayi.usiamenulis,penilaian_awal_keperawatan_ranap_bayi.gangguanemosi,"
                    + "penilaian_awal_keperawatan_ranap_bayi.alat_bantu,penilaian_awal_keperawatan_ranap_bayi.ket_bantu,penilaian_awal_keperawatan_ranap_bayi.prothesa,penilaian_awal_keperawatan_ranap_bayi.ket_pro,penilaian_awal_keperawatan_ranap_bayi.adl,penilaian_awal_keperawatan_ranap_bayi.status_psiko,penilaian_awal_keperawatan_ranap_bayi.ket_psiko,penilaian_awal_keperawatan_ranap_bayi.hub_keluarga,penilaian_awal_keperawatan_ranap_bayi.pengasuh,"
                    + "penilaian_awal_keperawatan_ranap_bayi.ket_pengasuh,penilaian_awal_keperawatan_ranap_bayi.ekonomi,penilaian_awal_keperawatan_ranap_bayi.budaya,penilaian_awal_keperawatan_ranap_bayi.ket_budaya,penilaian_awal_keperawatan_ranap_bayi.edukasi,penilaian_awal_keperawatan_ranap_bayi.ket_edukasi,penilaian_awal_keperawatan_ranap_bayi.agama,penilaian_awal_keperawatan_ranap_bayi.wajib,penilaian_awal_keperawatan_ranap_bayi.ket_halangan_lain,penilaian_awal_keperawatan_ranap_bayi.toharah,penilaian_awal_keperawatan_ranap_bayi.sholat,penilaian_awal_keperawatan_ranap_bayi.motivasi_kesembuhan_ibadah,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_usia,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_usia,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_jk,"
                    + "penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_jk,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_diagnosis,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_diagnosis,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_gangguan_kognitif,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_gangguan_kognitif,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_faktor_lingkungan,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_faktor_lingkungan,penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_respon_pembedahan,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_respon_pembedahan,"
                    + "penilaian_awal_keperawatan_ranap_bayi.resiko_jatuh_medikamentosa,penilaian_awal_keperawatan_ranap_bayi.nilai_resiko_jatuh_medikamentosa,penilaian_awal_keperawatan_ranap_bayi.total_hasil_resiko_jatuh,penilaian_awal_keperawatan_ranap_bayi.sg1,penilaian_awal_keperawatan_ranap_bayi.nilai1,penilaian_awal_keperawatan_ranap_bayi.sg2,penilaian_awal_keperawatan_ranap_bayi.nilai2,penilaian_awal_keperawatan_ranap_bayi.sg3,penilaian_awal_keperawatan_ranap_bayi.nilai3,"
                    + "penilaian_awal_keperawatan_ranap_bayi.sg4,penilaian_awal_keperawatan_ranap_bayi.nilai4,penilaian_awal_keperawatan_ranap_bayi.total_hasil,penilaian_awal_keperawatan_ranap_bayi.kriteria1,penilaian_awal_keperawatan_ranap_bayi.kriteria2,penilaian_awal_keperawatan_ranap_bayi.kriteria3,penilaian_awal_keperawatan_ranap_bayi.kriteria4,penilaian_awal_keperawatan_ranap_bayi.pilihan1,penilaian_awal_keperawatan_ranap_bayi.pilihan2,"
                    + "penilaian_awal_keperawatan_ranap_bayi.pilihan3,penilaian_awal_keperawatan_ranap_bayi.pilihan4,penilaian_awal_keperawatan_ranap_bayi.pilihan5,penilaian_awal_keperawatan_ranap_bayi.pilihan6,penilaian_awal_keperawatan_ranap_bayi.pilihan7,penilaian_awal_keperawatan_ranap_bayi.pilihan8,penilaian_awal_keperawatan_ranap_bayi.wajah,penilaian_awal_keperawatan_ranap_bayi.nilaiwajah,penilaian_awal_keperawatan_ranap_bayi.tangisan,"
                    + "penilaian_awal_keperawatan_ranap_bayi.nilaitangisan,penilaian_awal_keperawatan_ranap_bayi.polanapas,penilaian_awal_keperawatan_ranap_bayi.nilaipolanapas,penilaian_awal_keperawatan_ranap_bayi.lengan,penilaian_awal_keperawatan_ranap_bayi.nilailengan,penilaian_awal_keperawatan_ranap_bayi.tungkai,penilaian_awal_keperawatan_ranap_bayi.nilaitungkai,penilaian_awal_keperawatan_ranap_bayi.aktivitas,penilaian_awal_keperawatan_ranap_bayi.nilaiaktivitas,penilaian_awal_keperawatan_ranap_bayi.hasilnyeri,"
                    + "penilaian_awal_keperawatan_ranap_bayi.rencana,penilaian_awal_keperawatan_ranap_bayi.nip1,"
                    + "penilaian_awal_keperawatan_ranap_bayi.nip2,penilaian_awal_keperawatan_ranap_bayi.kd_dokter,pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,"
                    + "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab,bahasa_pasien.nama_bahasa "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_ranap_bayi on reg_periksa.no_rawat=penilaian_awal_keperawatan_ranap_bayi.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_ranap_bayi.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_ranap_bayi.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_ranap_bayi.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                    + "penilaian_awal_keperawatan_ranap_bayi.tanggal between ? and ? "
                    + (TCari.getText().trim().isEmpty() ? "" : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_ranap_bayi.nip1 like ? or "
                    + "pengkaji1.nama like ? or penilaian_awal_keperawatan_ranap_bayi.kd_dokter like ? or dokter.nm_dokter like ?)")
                    + " order by penilaian_awal_keperawatan_ranap_bayi.tanggal");

            try {
                ps.setString(1,
                        Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2,
                        Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                if (!TCari.getText().isEmpty()) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                    ps.setString(9, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), rs.getString("tgl_lahir"),
                        rs.getString("jk"), rs.getString("nip1"), rs.getString(
                        "pengkaji1"), rs.getString("nip2"), rs.getString(
                        "pengkaji2"),
                        rs.getString("kd_dokter"), rs.getString("nm_dokter"),
                        rs.getString("tanggal"), rs.getString("kasus_trauma"),
                        rs.getString("informasi") + ", " + rs.getString(
                        "ket_informasi"), rs.getString("tiba_diruang_rawat"),
                        rs.getString("cara_masuk"),
                        rs.getString("keluhan_utama"), rs.getString("rps"), rs.
                        getString("rpd"), rs.getString("rpk"), rs.getString(
                        "rpo"), rs.getString("alergi"), rs.getString(
                        "pemeriksaan_mental"), rs.getString(
                        "pemeriksaan_keadaan_umum"), rs.getString("td"),
                        rs.getString("nadi"), rs.getString("spo2"), rs.
                        getString("rr"), rs.getString("suhu"), rs.getString(
                        "gcs"), rs.getString("bb"), rs.getString("tb"), rs.
                        getString("lp"), rs.getString("lk"), rs.getString("ld"),
                        rs.getString("b1_nafas_spontan"), rs.getString(
                        "b1_jenis"),
                        rs.getString("b1_alat_bantu"), rs.getString(
                        "b1_ket_alat_bantu"), rs.getString("b1_irama"), rs.
                        getString("b1_suara_nafas"), rs.getString(
                        "b2_irama_jantung"), rs.getString("b2_acral"), rs.
                        getString("b2_tunggal"), rs.getString(
                        "b2_conjungtiva_anemis"),
                        rs.getString("b3_kesadaran"), rs.getString(
                        "b3_istirahat_tidur"), rs.getString("b3_gangguan_tidur"),
                        rs.getString("b3_ket_gangguan_tidur"), rs.getString(
                        "b3_tingkat_kesadaran"), rs.getString("b3_tangisan"),
                        rs.getString("b3_kepala"), rs.getString("b3_kelainan"),
                        rs.getString("b3_ubun_ubun"), rs.getString("b3_pupil"),
                        rs.getString("b3_sklera_mata"), rs.getString(
                        "b3_gerakan"), rs.getString("b3_panca_indra"), rs.
                        getString("b3_ket_panca_indra"), rs.getString(
                        "b3_kejang"), rs.getString("b3_reflek_rooting"),
                        rs.getString("b4_kebersihan"), rs.getString("b4_sekret"),
                        rs.getString("b4_ket_sekret"), rs.getString(
                        "b4_produksi_urine"), rs.getString("b4_warna"), rs.
                        getString("b4_ket_warna"), rs.getString("b4_gangguan"),
                        rs.getString("b4_alat_bantu"), rs.getString(
                        "b5_nafsu_makan"),
                        rs.getString("b5_frekuensi"), rs.getString(
                        "b5_porsi_makan"), rs.getString("b5_minum"), rs.
                        getString("b5_jenis"), rs.getString("b5_cara_minum"),
                        rs.getString("b5_anus"), rs.getString("b5_bab"), rs.
                        getString("b5_konsisten"), rs.getString("b5_warna"),
                        rs.getString("b5_ket_warna"), rs.getString("b5_perut"),
                        rs.getString("b5_paristaltik"), rs.getString(
                        "b5_reflek_rooting"), rs.getString("b5_kelainan"), rs.
                        getString("b5_lidah"), rs.getString("b5_selaput_lender"),
                        rs.getString("b6_pergerakan_sendi"), rs.getString(
                        "b6_ket_pergerakan_sendi"),
                        rs.getString("b6_warna_kulit"), rs.getString(
                        "b6_intergitas_kulit"), rs.getString("b6_kepala"), rs.
                        getString("b6_tali_pusat"), rs.getString("b6_tugor"),
                        rs.getString("b6_odem"), rs.getString("b6_lokasi"), rs.
                        getString("b6_otot_kiri_atas"), rs.getString(
                        "b6_otot_kanan_atas"),
                        rs.getString("b6_otot_kiri_bawah"), rs.getString(
                        "b6_otot_kanan_bawah"), rs.
                        getString("genital_laki_laki"), rs.getString(
                        "genital_perempuan"), rs.getString("derajat_ikterus"),
                        rs.getString("daerah_ikterus"), rs.getString(
                        "perkiraan_kadar_bilirubin"),
                        rs.getString("apgar_score"), rs.getString("down_score"),
                        rs.getString("anakke"), rs.getString("darisaudara"), rs.
                        getString("caralahir"), rs.getString("ket_caralahir"),
                        rs.getString("umurkelahiran"), rs.getString(
                        "kelainanbawaan"), rs.getString("ket_kelainan_bawaan"),
                        rs.getString("warnaketuban"), rs.getString(
                        "kelainanpersalinan"), rs.getString("usiakehamilan"),
                        rs.getString("penolong"), rs.getString(
                        "penolongpersalinan"),
                        rs.getString("usiatengkurap"), rs.getString("usiaduduk"),
                        rs.getString("usiaberdiri"), rs.getString(
                        "usiagigipertama"), rs.getString("usiaberjalan"), rs.
                        getString("usiabicara"), rs.getString("usiamembaca"),
                        rs.getString("usiamenulis"), rs.getString(
                        "gangguanemosi"),
                        rs.getString("alat_bantu"), rs.getString("ket_bantu"),
                        rs.getString("prothesa"), rs.getString("ket_pro"), rs.
                        getString("adl"), rs.getString("status_psiko"), rs.
                        getString("ket_psiko"), rs.getString("hub_keluarga"),
                        rs.getString("pengasuh"), rs.getString("ket_pengasuh"),
                        rs.getString("ekonomi"), rs.getString("budaya"), rs.
                        getString("ket_budaya"), rs.getString("edukasi"), rs.
                        getString("ket_edukasi"), rs.getString("agama"), rs.
                        getString("wajib"), rs.getString("ket_halangan_lain"),
                        rs.getString("toharah"), rs.getString("sholat"), rs.
                        getString("motivasi_kesembuhan_ibadah"),
                        rs.getString("resiko_jatuh_usia"), rs.getString(
                        "nilai_resiko_jatuh_usia"), rs.getString(
                        "resiko_jatuh_jk"), rs.
                        getString("nilai_resiko_jatuh_jk"), rs.getString(
                        "resiko_jatuh_diagnosis"),
                        rs.getString("nilai_resiko_jatuh_diagnosis"), rs.
                        getString("resiko_jatuh_gangguan_kognitif"), rs.
                        getString("nilai_resiko_jatuh_gangguan_kognitif"), rs.
                        getString("resiko_jatuh_faktor_lingkungan"), rs.
                        getString("nilai_resiko_jatuh_faktor_lingkungan"), rs.
                        getString("resiko_jatuh_respon_pembedahan"),
                        rs.getString("nilai_resiko_jatuh_respon_pembedahan"),
                        rs.getString("resiko_jatuh_medikamentosa"), rs.
                        getString("nilai_resiko_jatuh_medikamentosa"), rs.
                        getString("total_hasil_resiko_jatuh"), rs.getString(
                        "sg1"), rs.getString("nilai1"), rs.getString("sg2"), rs.
                        getString("nilai2"), rs.getString("sg3"),
                        rs.getString("nilai3"), rs.getString("sg4"), rs.
                        getString("nilai4"), rs.getString("total_hasil"),
                        rs.getString("kriteria1"), rs.getString("kriteria2"),
                        rs.getString("kriteria3"), rs.getString("kriteria4"),
                        rs.getString("pilihan1"),
                        rs.getString("pilihan2"), rs.getString("pilihan3"), rs.
                        getString("pilihan4"), rs.getString("pilihan5"),
                        rs.getString("pilihan6"), rs.getString("pilihan7"), rs.
                        getString("pilihan8"), rs.getString("wajah"), rs.
                        getString("nilaiwajah"), rs.getString("tangisan"), rs.
                        getString("nilaitangisan"), rs.getString("polanapas"),
                        rs.getString("nilaipolanapas"),
                        rs.getString("lengan"), rs.getString("nilailengan"), rs.
                        getString("tungkai"), rs.getString("nilaitungkai"), rs.
                        getString("aktivitas"), rs.getString("nilaiaktivitas"),
                        rs.getString("hasilnyeri"), rs.getString("rencana")
                    });
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TglAsuhan.setDate(new Date());
        Anamnesis.setSelectedIndex(0);
        KetAnamnesis.setText("");
        TibadiRuang.setSelectedIndex(0);
        CaraMasuk.setSelectedIndex(0);
        MacamKasus.setSelectedIndex(0);
        KeluhanUtama.setText("");
        RPS.setText("");
        RPD.setText("");
        RPK.setText("");
        RPO.setText("");
        Alergi.setText("");
        KesadaranMental.setText("");
        KeadaanMentalUmum.setSelectedIndex(0);
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        GCS.setText("");
        BB.setText("");
        TB.setText("");
        LP.setText("");
        LK.setText("");
        LD.setText("");
        B1Irama.setSelectedIndex(0);
        B1JenisNafas.setSelectedIndex(0);
        B1KetO2Nafas.setText("");
        B1NafasSpontan.setSelectedIndex(0);
        B1O2Nafas.setText("");
        B1SuaraNafas.setSelectedIndex(0);
        B2Acral.setSelectedIndex(0);
        B2ConjungtivaAnemis.setSelectedIndex(0);
        B2IramaJantung.setSelectedIndex(0);
        B2S1S2.setSelectedIndex(0);
        B3GangguanTidur.setText("");
        B3Gerakan.setSelectedIndex(0);
        B3JamIstirahatTidur.setText("");
        B3Kejang.setSelectedIndex(0);
        B3Kelainan.setSelectedIndex(0);
        B3Kesadaran.setSelectedIndex(0);
        B3KetGangguanTidur.setSelectedIndex(0);
        B3KetPancaIndra.setText("");
        B3LingkarKepala.setText("");
        B3PancaIndra.setSelectedIndex(0);
        B3Pupil.setSelectedIndex(0);
        B3ReflekRooting.setSelectedIndex(0);
        B3SkleraMata.setSelectedIndex(0);
        B3Tangisan.setSelectedIndex(0);
        B3TingkatKesadaran.setSelectedIndex(0);
        B3UbunUbun.setSelectedIndex(0);
        B4AlatBantu.setSelectedIndex(0);
        B4Gangguan.setSelectedIndex(0);
        B4Kebersihan.setSelectedIndex(0);
        B4KetSekret.setText("");
        B4KetWarna.setText("");
        B4ProduksiUrine.setText("");
        B4Sekret.setSelectedIndex(0);
        B4Warna.setSelectedIndex(0);
        B5Anus.setSelectedIndex(0);
        B5BAB.setText("");
        B5CaraMinum.setSelectedIndex(0);
        B5FrekuensiMakan.setText("");
        B5JenisMinum.setText("");
        B5Kelainan.setSelectedIndex(0);
        B5KetWarnaBAB.setSelectedIndex(0);
        B5Konsisten.setText("");
        B5Lidah.setSelectedIndex(0);
        B5Minum.setText("");
        B5NafsuMakan.setSelectedIndex(0);
        B5Peristaltik.setText("");
        B5Perut.setSelectedIndex(0);
        B5PorsiMakan.setText("");
        B5ReflekRooting.setSelectedIndex(0);
        B5SelaputLender.setSelectedIndex(0);
        B5WarnaBAB.setText("");
        B6IntergitasKulit.setSelectedIndex(0);
        B6IntergitasKulit.setSelectedIndex(0);
        B6KekuatanOtotKananAtas.setText("");
        B6KekuatanOtotKananBawah.setText("");
        B6KekuatanOtotKiriAtas.setText("");
        B6KekuatanOtotKiriBawah.setText("");
        B6Kepala.setSelectedIndex(0);
        B6KetOdem.setText("");
        B6KetPergerakanSendi.setText("");
        B6Odem.setSelectedIndex(0);
        B6TaliPusat.setSelectedIndex(0);
        B6Tugor.setSelectedIndex(0);
        B6WarnaKulit.setSelectedIndex(0);
        AlatGenitalLaki.setSelectedIndex(0);
        AlatGenitalPerampuan.setSelectedIndex(0);
        DerajatIkterus.setSelectedIndex(0);
        PenilaianApgarScore.setText("");
        PenilaianDownScore.setText("");
        Anakke.setText("");
        DariSaudara.setText("");
        CaraKelahiran.setSelectedIndex(0);
        KetCaraKelahiran.setText("");
        UmurKelahiran.setSelectedIndex(0);
        KelainanBawaan.setSelectedIndex(0);
        KetKelainanBawaan.setText("");
        WarnaKetuban.setSelectedIndex(0);
        KelainanPersalinan.setText("");
        UsiaKehamilan.setText("");
        Penolong.setSelectedIndex(0);
        PenolongPersalinan.setText("");
        UsiaTengkurap.setText("");
        UsiaDuduk.setText("");
        UsiaBerdiri.setText("");
        UsiaGigi.setText("");
        UsiaBerjalan.setText("");
        UsiaBicara.setText("");
        UsiaMembaca.setText("");
        UsiaMenulis.setText("");
        GangguanEmosi.setText("");
        AlatBantu.setSelectedIndex(0);
        KetBantu.setText("");
        Prothesa.setSelectedIndex(0);
        KetProthesa.setText("");
        ADL.setSelectedIndex(0);
        StatusPsiko.setSelectedIndex(0);
        KetPsiko.setText("");
        HubunganKeluarga.setSelectedIndex(0);
        Pengasuh.setSelectedIndex(0);
        KetPengasuh.setText("");
        Ekonomi.setSelectedIndex(0);
        StatusBudaya.setSelectedIndex(0);
        KetBudaya.setText("");
        Edukasi.setSelectedIndex(0);
        KetEdukasi.setText("");
        Agama.setText("");
        Wajib.setSelectedIndex(0);
        KetHalanganLain.setText("");
        Toharah.setSelectedIndex(0);
        Sholat.setSelectedIndex(0);
        MotivasiKesembuhanIbadah.setSelectedIndex(0);
        SkalaHumptyDumpty1.setSelectedIndex(0);
        NilaiHumptyDumpty1.setText("4");
        SkalaHumptyDumpty2.setSelectedIndex(0);
        NilaiHumptyDumpty2.setText("2");
        SkalaHumptyDumpty3.setSelectedIndex(0);
        NilaiHumptyDumpty3.setText("4");
        SkalaHumptyDumpty4.setSelectedIndex(0);
        NilaiHumptyDumpty4.setText("3");
        SkalaHumptyDumpty5.setSelectedIndex(0);
        NilaiHumptyDumpty5.setText("4");
        SkalaHumptyDumpty6.setSelectedIndex(0);
        NilaiHumptyDumpty6.setText("3");
        SkalaHumptyDumpty7.setSelectedIndex(0);
        NilaiHumptyDumpty7.setText("3");
        isTotalResikoHumptyDumpty();
        SG1.setSelectedIndex(0);
        NilaiGizi1.setText("0");
        SG2.setSelectedIndex(0);
        NilaiGizi2.setText("0");
        SG3.setSelectedIndex(0);
        NilaiGizi3.setText("0");
        SG4.setSelectedIndex(0);
        NilaiGizi4.setText("0");
        TotalNilaiGizi.setText("0");
        SkalaWajah.setSelectedIndex(0);
        NilaiWajah.setText("0");
        SkalaTangisan.setSelectedIndex(0);
        NilaiTangisan.setText("0");
        SkalaPolaNapas.setSelectedIndex(0);
        NilaiPolaNapas.setText("0");
        SkalaLengan.setSelectedIndex(0);
        NilaiLengan.setText("0");
        SkalaTungkai.setSelectedIndex(0);
        NilaiTungkai.setText("0");
        SkalaAktivitas.setSelectedIndex(0);
        NilaiAktivitas.setText("0");
        SkalaNyeri.setText("0");
        Rencana.setText("");
        Kriteria1.setSelectedIndex(0);
        Kriteria2.setSelectedIndex(0);
        Kriteria3.setSelectedIndex(0);
        Kriteria4.setSelectedIndex(0);
        pilihan1.setSelected(false);
        pilihan2.setSelected(false);
        pilihan3.setSelected(false);
        pilihan4.setSelected(false);
        pilihan5.setSelected(false);
        pilihan6.setSelected(false);
        pilihan7.setSelected(false);
        pilihan8.setSelected(false);
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false, i, 0);
        }
        Valid.tabelKosong(tabModeRencana);
        TabRawat.setSelectedIndex(0);
        MacamKasus.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).
                    toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).
                    toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).
                    toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).
                    toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 5).
                    toString());
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 6).
                    toString());
            KdPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).
                    toString());
            NmPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).
                    toString());
            KdDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).
                    toString());
            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).
                    toString());
            MacamKasus.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 12).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString().
                    contains("Autoanamnesis")) {
                Anamnesis.setSelectedItem("Autoanamnesis");
            } else {
                Anamnesis.setSelectedItem("Alloanamnesis");
            }
            KetAnamnesis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).
                    toString().replaceAll(
                            Anamnesis.getSelectedItem().toString() + ", ", ""));
            TibadiRuang.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 14).toString());
            CaraMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    15).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).
                    toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).
                    toString());
            RPD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).
                    toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).
                    toString());
            RPO.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).
                    toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).
                    toString());
            KesadaranMental.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    22).toString());
            KeadaanMentalUmum.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 23).toString());
            TD.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).
                            toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).
                    toString());
            SpO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).
                    toString());
            RR.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 27).
                            toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 28).
                    toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 29).
                    toString());
            BB.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 30).
                            toString());
            TB.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 31).
                            toString());
            LP.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 32).
                            toString());
            LK.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 33).
                            toString());
            LD.
                    setText(tbObat.getValueAt(tbObat.getSelectedRow(), 34).
                            toString());
            B1NafasSpontan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 35).toString());
            B1JenisNafas.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 36).toString());
            B1O2Nafas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 37).
                    toString());
            B1KetO2Nafas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 38).
                    toString());
            B1Irama.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    39).toString());
            B1SuaraNafas.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 40).toString());
            B2IramaJantung.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 41).toString());
            B2Acral.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    42).toString());
            B2S1S2.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 43).toString());
            B2ConjungtivaAnemis.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 44).toString());
            B3Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 45).toString());
            B3JamIstirahatTidur.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 46).toString());
            B3GangguanTidur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    47).toString());
            B3KetGangguanTidur.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 48).toString());
            B3TingkatKesadaran.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 49).toString());
            B3Tangisan.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 50).toString());
            B3LingkarKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    51).toString());
            B3Kelainan.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 52).toString());
            B3UbunUbun.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 53).toString());
            B3Pupil.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    54).toString());
            B3SkleraMata.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 55).toString());
            B3Gerakan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    56).toString());
            B3PancaIndra.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 57).toString());
            B3KetPancaIndra.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    58).toString());
            B3Kejang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    59).toString());
            B3ReflekRooting.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 60).toString());
            B4Kebersihan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 61).toString());
            B4Sekret.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    62).toString());
            B4KetSekret.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 63).
                    toString());
            B4ProduksiUrine.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    64).toString());
            B4Warna.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    65).toString());
            B4KetWarna.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 66).
                    toString());
            B4Gangguan.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 67).toString());
            B4AlatBantu.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 68).toString());
            B5NafsuMakan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 69).toString());
            B5FrekuensiMakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    70).toString());
            B5PorsiMakan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 71).
                    toString());
            B5Minum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 72).
                    toString());
            B5JenisMinum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 73).
                    toString());
            B5CaraMinum.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 74).toString());
            B5Anus.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 75).toString());
            B5BAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 76).
                    toString());
            B5Konsisten.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 77).
                    toString());
            B5WarnaBAB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 78).
                    toString());
            B5KetWarnaBAB.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 79).toString());
            B5Perut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    80).toString());
            B5Peristaltik.setText(
                    tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString());
            B5ReflekRooting.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 82).toString());
            B5Kelainan.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 83).toString());
            B5Lidah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    84).toString());
            B5SelaputLender.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 85).toString());
            B6PergerakanSendi.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 86).toString());
            B6KetPergerakanSendi.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 87).toString());
            B6WarnaKulit.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 88).toString());
            B6IntergitasKulit.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 89).toString());
            B6Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    90).toString());
            B6TaliPusat.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 91).toString());
            B6Tugor.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    92).toString());
            B6Odem.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 93).toString());
            B6KetOdem.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 94).
                    toString());
            B6KekuatanOtotKiriAtas.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 95).toString());
            B6KekuatanOtotKananAtas.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 96).toString());
            B6KekuatanOtotKiriBawah.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 97).toString());
            B6KekuatanOtotKananBawah.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 98).toString());
            AlatGenitalLaki.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 99).toString());
            AlatGenitalPerampuan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 100).toString());
            DerajatIkterus.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 101).toString());
            DaerahIkterus.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 102).toString());
            KadarBilirubin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    103).toString());
            PenilaianApgarScore.setText(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 104).toString());
            PenilaianDownScore.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 105).toString());
            Anakke.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 106).
                    toString());
            DariSaudara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 107).
                    toString());
            CaraKelahiran.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 108).toString());
            KetCaraKelahiran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    109).toString());
            UmurKelahiran.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 110).toString());
            KelainanBawaan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 111).toString());
            KetKelainanBawaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    112).toString());
            WarnaKetuban.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 113).toString());
            KelainanPersalinan.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 114).toString());
            UsiaKehamilan.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 115).toString());
            Penolong.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    116).toString());
            PenolongPersalinan.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 117).toString());
            UsiaTengkurap.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 118).toString());
            UsiaDuduk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 119).
                    toString());
            UsiaBerdiri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 120).
                    toString());
            UsiaGigi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 121).
                    toString());
            UsiaBerjalan.setText(
                    tbObat.getValueAt(tbObat.getSelectedRow(), 122).toString());
            UsiaBicara.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 123).
                    toString());
            UsiaMembaca.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 124).
                    toString());
            UsiaMenulis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 125).
                    toString());
            GangguanEmosi.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 126).toString());
            AlatBantu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    127).toString());
            KetBantu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 128).
                    toString());
            Prothesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    129).toString());
            KetProthesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 130).
                    toString());
            ADL.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 131).
                    toString());
            StatusPsiko.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 132).toString());
            KetPsiko.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 133).
                    toString());
            HubunganKeluarga.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 134).toString());
            Pengasuh.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    135).toString());
            KetPengasuh.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 136).
                    toString());
            Ekonomi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    137).toString());
            StatusBudaya.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 138).toString());
            KetBudaya.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 139).
                    toString());
            Edukasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    140).toString());
            KetEdukasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 141).
                    toString());
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 142).
                    toString());
            Wajib.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 143).toString());
            KetHalanganLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    144).toString());
            Toharah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    145).toString());
            Sholat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    146).toString());
            MotivasiKesembuhanIbadah.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 147).toString());
            SkalaHumptyDumpty1.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 148).toString());
            SkalaHumptyDumpty2.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 150).toString());
            SkalaHumptyDumpty3.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 152).toString());
            SkalaHumptyDumpty4.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 154).toString());
            SkalaHumptyDumpty5.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 156).toString());
            SkalaHumptyDumpty6.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 158).toString());
            SkalaHumptyDumpty7.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 160).toString());
            isTotalResikoHumptyDumpty();
            SG1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 163).
                    toString());
            NilaiGizi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 164).
                    toString());
            SG2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 165).
                    toString());
            NilaiGizi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 166).
                    toString());
            SG3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 167).
                    toString());
            NilaiGizi3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 168).
                    toString());
            SG4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 169).
                    toString());
            NilaiGizi4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 170).
                    toString());
            TotalNilaiGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),
                    171).toString());
            Kriteria1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    172).toString());
            Kriteria2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    173).toString());
            Kriteria3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    174).toString());
            Kriteria4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),
                    175).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 176).toString().
                    equals("Perawatan diri (Mandi, BAB, BAK)")) {
                pilihan1.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 177).toString().
                    equals("Pemantauan pemberian obat")) {
                pilihan2.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 178).toString().
                    equals("Pemantauan diet")) {
                pilihan3.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 179).toString().
                    equals("Bantuan medis / perawatan di rumah (Homecare)")) {
                pilihan4.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 180).toString().
                    equals("Perawatan luka")) {
                pilihan5.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 181).toString().
                    equals("Latihan fisik lanjutan")) {
                pilihan6.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 182).toString().
                    equals("Pendampingan tenaga khusus di rumah")) {
                pilihan7.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 183).toString().
                    equals("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)")) {
                pilihan8.setSelected(true);
            }
            SkalaWajah.setSelectedItem(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 184).toString());
            SkalaTangisan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 186).toString());
            SkalaPolaNapas.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 188).toString());
            SkalaLengan.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 190).toString());
            SkalaTungkai.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 192).toString());
            SkalaAktivitas.setSelectedItem(tbObat.getValueAt(tbObat.
                    getSelectedRow(), 194).toString());
            SkalaNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 196).
                    toString());
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 197).
                    toString());

            try {
                Valid.tabelKosong(tabModeMasalah);

                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan_anak.kode_masalah,master_masalah_keperawatan_anak.nama_masalah from master_masalah_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_bayi on penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah=master_masalah_keperawatan_anak.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeMasalah.addRow(
                                new Object[]{true, rs.getString(1), rs.
                                    getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            try {
                Valid.tabelKosong(tabModeRencana);
                ps = koneksi.prepareStatement(
                        "select master_rencana_keperawatan_anak.kode_rencana,master_rencana_keperawatan_anak.rencana_keperawatan from master_rencana_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_bayi on penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana=master_rencana_keperawatan_anak.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeRencana.addRow(
                                new Object[]{true, rs.getString(1), rs.
                                    getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }

            Valid.SetTgl2(TglAsuhan, tbObat.getValueAt(tbObat.getSelectedRow(),
                    11).toString());
            tampilImunisasi();
        }
    }

    private void isRawat() {
        tampilImunisasi();
        try {
            ps = koneksi.prepareStatement(
                    "select pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,"
                    + "bahasa_pasien.nama_bahasa,pasien.pnd,pasien.pekerjaan,reg_periksa.tgl_registrasi,reg_periksa.jam_reg "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Agama.setText(rs.getString("agama"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    TanggalRegistrasi.setText(
                            rs.getString("tgl_registrasi") + " " + rs.getString(
                            "jam_reg"));
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notif : " + e);
        }
    }

    public void setNoRm(String norwt, Date tgl2, String norm) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TCari.setText(norwt);
        Sequel.cariIsi(
                "select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='" + norwt + "'",
                DTPCari1);
//        CaraBayar.setText(carabayar);
        DTPCari2.setDate(tgl2);
        isRawat();
    }

    /**
     *
     */
    public void isCek() {
        BtnSimpan.setEnabled(akses.
                getpenilaian_awal_keperawatan_ranap_neonatus());
        BtnHapus.
                setEnabled(akses.getpenilaian_awal_keperawatan_ranap_neonatus());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranap_neonatus());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranap_neonatus());
        BtnTambahMasalah.setEnabled(akses.getmaster_masalah_keperawatan_anak());
        BtnTambahRencana.setEnabled(akses.getmaster_rencana_keperawatan_anak());
        if (akses.getjml2() >= 1) {
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if (NmPetugas.getText().isEmpty()) {
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,
                        "User login bukan petugas...!!");
            }
        }
    }

    /**
     *
     */
    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void tampilMasalah() {
        try {
            Valid.tabelKosong(tabModeMasalah);
            file = new File("./cache/masalahkeperawatanbayi.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_masalah_keperawatan_anak order by master_masalah_keperawatan_anak.kode_masalah");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeMasalah.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2)});
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"NamaMasalah\":\"" + rs.
                            getString(2) + "\"},";
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"masalahkeperawatanbayi\":[" + iyem.substring(0,
                    iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;
        } catch (IOException | SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilMasalah2() {
        try {
            jml = 0;
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            masalah = null;
            masalah = new String[jml];

            index = 0;
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    pilih[index] = true;
                    kode[index] = tbMasalahKeperawatan.getValueAt(i, 1).
                            toString();
                    masalah[index] = tbMasalahKeperawatan.getValueAt(i, 2).
                            toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeMasalah);

            for (i = 0; i < jml; i++) {
                tabModeMasalah.addRow(new Object[]{
                    pilih[i], kode[i], masalah[i]
                });
            }

            myObj = new FileReader("./cache/masalahkeperawatanbayi.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahkeperawatanbayi");
            if (response.isArray()) {
                for (JsonNode list : response) {
                    if (list.path("KodeMasalah").asText().toLowerCase().
                            contains(TCariMasalah.getText().toLowerCase()) || list.
                            path("NamaMasalah").asText().toLowerCase().contains(
                            TCariMasalah.getText().toLowerCase())) {
                        tabModeMasalah.addRow(new Object[]{
                            false, list.path("KodeMasalah").asText(), list.path(
                            "NamaMasalah").asText()
                        });
                    }
                }
            }
            myObj.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilRencana() {
        try {
            file = new File("./cache/rencanakeperawatanbayi.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_rencana_keperawatan_anak order by master_rencana_keperawatan_anak.kode_rencana");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"KodeRencana\":\"" + rs.
                            getString(2) + "\",\"NamaRencana\":\"" + rs.
                            getString(3) + "\"},";
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"rencanakeperawatanbayi\":[" + iyem.substring(0,
                    iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;
        } catch (IOException | SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilRencana2() {
        try {
            jml = 0;
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            masalah = null;
            masalah = new String[jml];

            index = 0;
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    pilih[index] = true;
                    kode[index] = tbRencanaKeperawatan.getValueAt(i, 1).
                            toString();
                    masalah[index] = tbRencanaKeperawatan.getValueAt(i, 2).
                            toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeRencana);

            for (i = 0; i < jml; i++) {
                tabModeRencana.addRow(new Object[]{
                    pilih[i], kode[i], masalah[i]
                });
            }

            myObj = new FileReader("./cache/rencanakeperawatanbayi.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanakeperawatanbayi");
            if (response.isArray()) {
                for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                    if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                            "true")) {
                        for (JsonNode list : response) {
                            if (list.path("KodeMasalah").asText().toLowerCase().
                                    equals(tbMasalahKeperawatan.getValueAt(i, 1).
                                            toString())
                                    && list.path("NamaRencana").asText().
                                            toLowerCase().contains(TCariRencana.
                                                    getText().toLowerCase())) {
                                tabModeRencana.addRow(new Object[]{
                                    false, list.path("KodeRencana").asText(),
                                    list.path("NamaRencana").asText()
                                });
                            }
                        }
                    }
                }
            }
            myObj.close();
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470, HEIGHT));
            FormMenu.setVisible(true);
            FormMasalahRencana.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15, HEIGHT));
            FormMenu.setVisible(false);
            FormMasalahRencana.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }

    private void getMasalah() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).
                    toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).
                    toString());
            DetailRencana.setText(tbObat.
                    getValueAt(tbObat.getSelectedRow(), 197).toString());
            try {
                Valid.tabelKosong(tabModeDetailMasalah);
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan_anak.kode_masalah,master_masalah_keperawatan_anak.nama_masalah from master_masalah_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_masalah_bayi on penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah=master_masalah_keperawatan_anak.kode_masalah "
                        + "where penilaian_awal_keperawatan_ranap_masalah_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_masalah_bayi.kode_masalah");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailMasalah.addRow(
                                new Object[]{rs.getString(1), rs.
                                    getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
            try {
                Valid.tabelKosong(tabModeDetailRencana);
                ps = koneksi.prepareStatement(
                        "select master_rencana_keperawatan_anak.kode_rencana,master_rencana_keperawatan_anak.rencana_keperawatan from master_rencana_keperawatan_anak "
                        + "inner join penilaian_awal_keperawatan_ranap_rencana_bayi on penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana=master_rencana_keperawatan_anak.kode_rencana "
                        + "where penilaian_awal_keperawatan_ranap_rencana_bayi.no_rawat=? order by penilaian_awal_keperawatan_ranap_rencana_bayi.kode_rencana");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailRencana.addRow(
                                new Object[]{rs.getString(1), rs.
                                    getString(2)});
                    }
                } catch (SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            }
        }
    }

    private void getImunisasi() {
        try {
            Valid.tabelKosong(tabModeImunisasi2);
            ps = koneksi.prepareStatement(
                    "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                    + "where riwayat_imunisasi.no_rkm_medis=? group by master_imunisasi.kode_imunisasi order by master_imunisasi.kode_imunisasi  ");
            try {
                ps.setString(1, TNoRM1.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    ke1 = false;
                    ke2 = false;
                    ke3 = false;
                    ke4 = false;
                    ke5 = false;
                    ke6 = false;
                    ps2 = koneksi.prepareStatement(
                            "select * from riwayat_imunisasi where riwayat_imunisasi.no_rkm_medis=? and riwayat_imunisasi.kode_imunisasi=?");
                    try {
                        ps2.setString(1, TNoRM1.getText());
                        ps2.setString(2, rs.getString(1));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            if (rs2.getInt("no_imunisasi") == 1) {
                                ke1 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 2) {
                                ke2 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 3) {
                                ke3 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 4) {
                                ke4 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 5) {
                                ke5 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 6) {
                                ke6 = true;
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    tabModeImunisasi2.addRow(new Object[]{rs.getString(1), rs.
                        getString(2), ke1, ke2, ke3, ke4, ke5, ke6});
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notif : " + e);
        }
    }

    private void tampilImunisasi() {
        try {
            Valid.tabelKosong(tabModeImunisasi);
            ps = koneksi.prepareStatement(
                    "select master_imunisasi.kode_imunisasi,master_imunisasi.nama_imunisasi from master_imunisasi inner join riwayat_imunisasi on riwayat_imunisasi.kode_imunisasi=master_imunisasi.kode_imunisasi "
                    + "where riwayat_imunisasi.no_rkm_medis=? group by master_imunisasi.kode_imunisasi order by master_imunisasi.kode_imunisasi  ");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                while (rs.next()) {
                    ke1 = false;
                    ke2 = false;
                    ke3 = false;
                    ke4 = false;
                    ke5 = false;
                    ke6 = false;
                    ps2 = koneksi.prepareStatement(
                            "select * from riwayat_imunisasi where riwayat_imunisasi.no_rkm_medis=? and riwayat_imunisasi.kode_imunisasi=?");
                    try {
                        ps2.setString(1, TNoRM.getText());
                        ps2.setString(2, rs.getString(1));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            if (rs2.getInt("no_imunisasi") == 1) {
                                ke1 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 2) {
                                ke2 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 3) {
                                ke3 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 4) {
                                ke4 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 5) {
                                ke5 = true;
                            }
                            if (rs2.getInt("no_imunisasi") == 6) {
                                ke6 = true;
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Notif : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }

                    tabModeImunisasi.addRow(new Object[]{rs.getString(1), rs.
                        getString(2), ke1, ke2, ke3, ke4, ke5, ke6});
                }
            } catch (SQLException e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notif : " + e);
        }
    }

    private void hapus() {
        if (Sequel.queryu2tf(
                "delete from penilaian_awal_keperawatan_ranap_bayi where no_rawat=?",
                1, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                }) == true) {
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_bayi",
                    "no_rawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).
                            toString());
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_bayi",
                    "no_rawat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).
                            toString());
            Valid.tabelKosong(tabModeDetailMasalah);
            Valid.tabelKosong(tabModeDetailRencana);
            ChkAccor.setSelected(false);
            isMenu();
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText("" + tabMode.getRowCount());
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if (pilihan1.isSelected() == true) {
            pilih1 = pilihan1.getText();
        }
        if (pilihan2.isSelected() == true) {
            pilih2 = pilihan2.getText();
        }
        if (pilihan3.isSelected() == true) {
            pilih3 = pilihan3.getText();
        }
        if (pilihan4.isSelected() == true) {
            pilih4 = pilihan4.getText();
        }
        if (pilihan5.isSelected() == true) {
            pilih5 = pilihan5.getText();
        }
        if (pilihan6.isSelected() == true) {
            pilih6 = pilihan6.getText();
        }
        if (pilihan7.isSelected() == true) {
            pilih7 = pilihan7.getText();
        }
        if (pilihan8.isSelected() == true) {
            pilih8 = pilihan8.getText();
        }
        if (Sequel.mengedittf("penilaian_awal_keperawatan_ranap_bayi",
                "no_rawat=?",
                "no_rawat=?,tanggal=?,informasi=?,ket_informasi=?,tiba_diruang_rawat=?,kasus_trauma=?,cara_masuk=?,keluhan_utama=?,rps=?,rpd=?,rpk=?,rpo=?,alergi=?,pemeriksaan_mental=?,pemeriksaan_keadaan_umum=?,td=?,nadi=?,spo2=?,rr=?,suhu=?,gcs=?,bb=?,tb=?,lp=?,lk=?,ld=?,b1_nafas_spontan=?,b1_jenis=?,b1_alat_bantu=?,b1_ket_alat_bantu=?,b1_irama=?,b1_suara_nafas=?,b2_irama_jantung=?,b2_acral=?,b2_tunggal=?,b2_conjungtiva_anemis=?,b3_kesadaran=?,b3_istirahat_tidur=?,b3_gangguan_tidur=?,b3_ket_gangguan_tidur=?,b3_tingkat_kesadaran=?,b3_tangisan=?,b3_kepala=?,b3_kelainan=?,b3_ubun_ubun=?,b3_pupil=?,b3_sklera_mata=?,b3_gerakan=?,b3_panca_indra=?,b3_ket_panca_indra=?,b3_kejang=?,b3_reflek_rooting=?,b4_kebersihan=?,b4_sekret=?,b4_ket_sekret=?,b4_produksi_urine=?,b4_warna=?,b4_ket_warna=?,b4_gangguan=?,b4_alat_bantu=?,b5_nafsu_makan=?,b5_frekuensi=?,b5_porsi_makan=?,b5_minum=?,b5_jenis=?,b5_cara_minum=?,b5_anus=?,b5_bab=?,b5_konsisten=?,b5_warna=?,b5_ket_warna=?,b5_perut=?,b5_paristaltik=?,b5_reflek_rooting=?,b5_kelainan=?,b5_lidah=?,b5_selaput_lender=?,b6_pergerakan_sendi=?,b6_ket_pergerakan_sendi=?,b6_warna_kulit=?,b6_intergitas_kulit=?,b6_kepala=?,b6_tali_pusat=?,b6_tugor=?,b6_odem=?,b6_lokasi=?,b6_otot_kiri_atas=?,b6_otot_kanan_atas=?,b6_otot_kiri_bawah=?,b6_otot_kanan_bawah=?,genital_laki_laki=?,genital_perempuan=?,derajat_ikterus=?,daerah_ikterus=?,perkiraan_kadar_bilirubin=?,apgar_score=?,down_score=?,anakke=?,darisaudara=?,caralahir=?,ket_caralahir=?,umurkelahiran=?,kelainanbawaan=?,ket_kelainan_bawaan=?,warnaketuban=?,kelainanpersalinan=?,usiakehamilan=?,penolong=?,penolongpersalinan=?,usiatengkurap=?,usiaduduk=?,usiaberdiri=?,usiagigipertama=?,usiaberjalan=?,usiabicara=?,usiamembaca=?,usiamenulis=?,gangguanemosi=?,alat_bantu=?,ket_bantu=?,prothesa=?,ket_pro=?,adl=?,status_psiko=?,ket_psiko=?,hub_keluarga=?,pengasuh=?,ket_pengasuh=?,ekonomi=?,budaya=?,ket_budaya=?,edukasi=?,ket_edukasi=?,agama=?,wajib=?,ket_halangan_lain=?,toharah=?,sholat=?,motivasi_kesembuhan_ibadah=?,resiko_jatuh_usia=?,nilai_resiko_jatuh_usia=?,resiko_jatuh_jk=?,nilai_resiko_jatuh_jk=?,resiko_jatuh_diagnosis=?,nilai_resiko_jatuh_diagnosis=?,resiko_jatuh_gangguan_kognitif=?,nilai_resiko_jatuh_gangguan_kognitif=?,resiko_jatuh_faktor_lingkungan=?,nilai_resiko_jatuh_faktor_lingkungan=?,resiko_jatuh_respon_pembedahan=?,nilai_resiko_jatuh_respon_pembedahan=?,resiko_jatuh_medikamentosa=?,nilai_resiko_jatuh_medikamentosa=?,total_hasil_resiko_jatuh=?,sg1=?,nilai1=?,sg2=?,nilai2=?,sg3=?,nilai3=?,sg4=?,nilai4=?,total_hasil=?,kriteria1=?,kriteria2=?,kriteria3=?,kriteria4=?,pilihan1=?,pilihan2=?,pilihan3=?,pilihan4=?,pilihan5=?,pilihan6=?,pilihan7=?,pilihan8=?,wajah=?,nilaiwajah=?,tangisan=?,nilaitangisan=?,polanapas=?,nilaipolanapas=?,lengan=?,nilailengan=?,tungkai=?,nilaitungkai=?,aktivitas=?,nilaiaktivitas=?,hasilnyeri=?,rencana=?,nip1=?,nip2=?,kd_dokter=?",
                193, new String[]{
                    TNoRw.getText(), Valid.SetTgl(
                    TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                    getSelectedItem().toString().substring(11, 19), Anamnesis.
                    getSelectedItem().toString(), KetAnamnesis.getText(),
                    TibadiRuang.getSelectedItem().toString(), MacamKasus.
                    getSelectedItem().toString(),
                    CaraMasuk.getSelectedItem().toString(), KeluhanUtama.
                    getText(), RPS.getText(), RPD.getText(), RPK.getText(), RPO.
                    getText(), Alergi.getText(), KesadaranMental.getText(),
                    KeadaanMentalUmum.getSelectedItem().toString(), TD.getText(),
                    Nadi.getText(), SpO2.getText(), RR.getText(),
                    Suhu.getText(), GCS.getText(), BB.getText(), TB.getText(),
                    LP.getText(), LK.getText(), LD.getText(), B1NafasSpontan.
                    getSelectedItem().toString(),
                    B1JenisNafas.getSelectedItem().toString(), B1O2Nafas.
                    getText(), B1KetO2Nafas.getText(),
                    B1Irama.getSelectedItem().toString(), B1SuaraNafas.
                    getSelectedItem().toString(),
                    B2IramaJantung.getSelectedItem().toString(), B2Acral.
                    getSelectedItem().toString(), B2S1S2.getSelectedItem().
                            toString(), B2ConjungtivaAnemis.getSelectedItem().
                            toString(), B3Kesadaran.getSelectedItem().toString(),
                    B3JamIstirahatTidur.getText(), B3GangguanTidur.getText(),
                    B3KetGangguanTidur.getSelectedItem().toString(),
                    B3TingkatKesadaran.getSelectedItem().toString(), B3Tangisan.
                    getSelectedItem().toString(), B3LingkarKepala.getText(),
                    B3Kelainan.getSelectedItem().toString(), B3UbunUbun.
                    getSelectedItem().toString(), B3Pupil.getSelectedItem().
                            toString(), B3SkleraMata.getSelectedItem().
                            toString(), B3Gerakan.getSelectedItem().toString(),
                    B3PancaIndra.getSelectedItem().toString(),
                    B3KetPancaIndra.getText(), B3Kejang.getSelectedItem().
                    toString(), B3ReflekRooting.getSelectedItem().toString(),
                    B4Kebersihan.getSelectedItem().toString(), B4Sekret.
                    getSelectedItem().toString(), B4KetSekret.getText(),
                    B4ProduksiUrine.getText(), B4Warna.getSelectedItem().
                    toString(), B4KetWarna.getText(), B4Gangguan.
                    getSelectedItem().toString(), B4AlatBantu.getSelectedItem().
                            toString(),
                    B5NafsuMakan.getSelectedItem().toString(), B5FrekuensiMakan.
                    getText(), B5PorsiMakan.getText(), B5Minum.getText(),
                    B5JenisMinum.getText(), B5CaraMinum.getSelectedItem().
                    toString(), B5Anus.getSelectedItem().toString(), B5BAB.
                    getText(), B5Konsisten.getText(), B5WarnaBAB.getText(),
                    B5KetWarnaBAB.getSelectedItem().toString(), B5Perut.
                    getSelectedItem().toString(), B5Peristaltik.getText(),
                    B5ReflekRooting.getSelectedItem().toString(), B5Kelainan.
                    getSelectedItem().toString(), B5Lidah.getSelectedItem().
                            toString(), B5SelaputLender.getSelectedItem().
                            toString(), B6PergerakanSendi.getSelectedItem().
                            toString(), B6KetPergerakanSendi.getText(),
                    B6WarnaKulit.getSelectedItem().toString(),
                    B6IntergitasKulit.getSelectedItem().toString(),
                    B6Kepala.getSelectedItem().toString(), B6TaliPusat.
                    getSelectedItem().toString(), B6Tugor.getSelectedItem().
                            toString(), B6Odem.getSelectedItem().toString(),
                    B6KetOdem.getText(), B6KekuatanOtotKiriAtas.getText(),
                    B6KekuatanOtotKananAtas.getText(), B6KekuatanOtotKiriBawah.
                    getText(), B6KekuatanOtotKananBawah.getText(),
                    AlatGenitalLaki.getSelectedItem().toString(),
                    AlatGenitalPerampuan.getSelectedItem().toString(),
                    DerajatIkterus.getSelectedItem().toString(), DaerahIkterus.
                    getText(), KadarBilirubin.getText(), PenilaianApgarScore.
                    getText(), PenilaianDownScore.getText(), Anakke.getText(),
                    DariSaudara.getText(),
                    CaraKelahiran.getSelectedItem().toString(),
                    KetCaraKelahiran.getText(), UmurKelahiran.getSelectedItem().
                    toString(), KelainanBawaan.getSelectedItem().toString(),
                    KetKelainanBawaan.getText(), WarnaKetuban.getSelectedItem().
                    toString(), KelainanPersalinan.getText(), UsiaKehamilan.
                    getText(), Penolong.getSelectedItem().toString(),
                    PenolongPersalinan.getText(), UsiaTengkurap.getText(),
                    UsiaDuduk.getText(), UsiaBerdiri.getText(), UsiaGigi.
                    getText(), UsiaBerjalan.getText(), UsiaBicara.getText(),
                    UsiaMembaca.getText(), UsiaMenulis.getText(), GangguanEmosi.
                    getText(), AlatBantu.getSelectedItem().toString(),
                    KetBantu.getText(), Prothesa.getSelectedItem().toString(),
                    KetProthesa.getText(), ADL.getSelectedItem().toString(),
                    StatusPsiko.getSelectedItem().toString(), KetPsiko.getText(),
                    HubunganKeluarga.getSelectedItem().toString(),
                    Pengasuh.getSelectedItem().toString(), KetPengasuh.getText(),
                    Ekonomi.getSelectedItem().toString(), StatusBudaya.
                    getSelectedItem().toString(), KetBudaya.getText(), Edukasi.
                    getSelectedItem().toString(), KetEdukasi.getText(), Agama.
                    getText(), Wajib.getSelectedItem().toString(),
                    KetHalanganLain.getText(), Toharah.getSelectedItem().
                    toString(), Sholat.getSelectedItem().toString(),
                    MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                    SkalaHumptyDumpty1.getSelectedItem().toString(),
                    NilaiHumptyDumpty1.getText(), SkalaHumptyDumpty2.
                    getSelectedItem().toString(), NilaiHumptyDumpty2.getText(),
                    SkalaHumptyDumpty3.getSelectedItem().toString(),
                    NilaiHumptyDumpty3.getText(), SkalaHumptyDumpty4.
                    getSelectedItem().toString(), NilaiHumptyDumpty4.getText(),
                    SkalaHumptyDumpty5.getSelectedItem().toString(),
                    NilaiHumptyDumpty5.getText(),
                    SkalaHumptyDumpty6.getSelectedItem().toString(),
                    NilaiHumptyDumpty6.getText(), SkalaHumptyDumpty7.
                    getSelectedItem().toString(), NilaiHumptyDumpty7.getText(),
                    NilaiHumptyDumptyTotal.getText(), SG1.getSelectedItem().
                    toString(),
                    NilaiGizi1.getText(), SG2.getSelectedItem().toString(),
                    NilaiGizi2.getText(), SG3.getSelectedItem().toString(),
                    NilaiGizi3.getText(), SG4.getSelectedItem().toString(),
                    NilaiGizi4.getText(), TotalNilaiGizi.getText(),
                    Kriteria1.getSelectedItem().toString(), Kriteria2.
                    getSelectedItem().toString(), Kriteria3.getSelectedItem().
                            toString(), Kriteria4.getSelectedItem().toString(),
                    pilih1, pilih2, pilih3, pilih4, pilih5, pilih6, pilih7,
                    pilih8, SkalaWajah.getSelectedItem().toString(), NilaiWajah.
                    getText(), SkalaTangisan.getSelectedItem().toString(),
                    NilaiTangisan.getText(), SkalaPolaNapas.getSelectedItem().
                    toString(), NilaiPolaNapas.getText(), SkalaLengan.
                    getSelectedItem().toString(),
                    NilaiLengan.getText(), SkalaTungkai.getSelectedItem().
                    toString(), NilaiTungkai.getText(), SkalaAktivitas.
                    getSelectedItem().toString(), NilaiAktivitas.getText(),
                    SkalaNyeri.getText(), Rencana.getText(), KdPetugas.getText(),
                    KdPetugas2.getText(), KdDPJP.getText(), tbObat.getValueAt(
                    tbObat.getSelectedRow(), 0).toString()
                }) == true) {
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_masalah_bayi",
                    "no_rawat", "tanggal", tbObat.getValueAt(tbObat.
                            getSelectedRow(), 0).toString(), tbObat.getValueAt(
                            tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    Sequel.menyimpan2(
                            "penilaian_awal_keperawatan_ranap_masalah_bayi",
                            "?,?,?", 3, new String[]{TNoRw.getText(),
                                tbMasalahKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                                getSelectedItem().toString().substring(11, 19)});
                }
            }
            Sequel.meghapus("penilaian_awal_keperawatan_ranap_rencana_bayi",
                    "no_rawat", "tanggal", tbObat.getValueAt(tbObat.
                            getSelectedRow(), 0).toString(), tbObat.getValueAt(
                            tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    Sequel.menyimpan2(
                            "penilaian_awal_keperawatan_ranap_rencana_bayi",
                            "?,?,?", 3, new String[]{TNoRw.getText(),
                                tbRencanaKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                                getSelectedItem().toString().substring(11, 19)});
                }
            }
            getMasalah();
            getImunisasi();
            tampil();
            DetailRencana.setText(Rencana.getText());
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }

    private void simpan() {
        if (pilihan1.isSelected() == true) {
            pilih1 = pilihan1.getText();
        }
        if (pilihan2.isSelected() == true) {
            pilih2 = pilihan2.getText();
        }
        if (pilihan3.isSelected() == true) {
            pilih3 = pilihan3.getText();
        }
        if (pilihan4.isSelected() == true) {
            pilih4 = pilihan4.getText();
        }
        if (pilihan5.isSelected() == true) {
            pilih5 = pilihan5.getText();
        }
        if (pilihan6.isSelected() == true) {
            pilih6 = pilihan6.getText();
        }
        if (pilihan7.isSelected() == true) {
            pilih7 = pilihan7.getText();
        }
        if (pilihan8.isSelected() == true) {
            pilih8 = pilihan8.getText();
        }
        if (Sequel.menyimpantf("penilaian_awal_keperawatan_ranap_bayi",
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                "No.Rawat", 192, new String[]{
                    TNoRw.getText(), Valid.SetTgl(
                    TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                    getSelectedItem().toString().substring(11, 19), Anamnesis.
                    getSelectedItem().toString(), KetAnamnesis.getText(),
                    TibadiRuang.getSelectedItem().toString(), MacamKasus.
                    getSelectedItem().toString(),
                    CaraMasuk.getSelectedItem().toString(), KeluhanUtama.
                    getText(), RPS.getText(), RPD.getText(), RPK.getText(), RPO.
                    getText(), Alergi.getText(), KesadaranMental.getText(),
                    KeadaanMentalUmum.getSelectedItem().toString(), TD.getText(),
                    Nadi.getText(), SpO2.getText(), RR.getText(),
                    Suhu.getText(), GCS.getText(), BB.getText(), TB.getText(),
                    LP.getText(), LK.getText(), LD.getText(), B1NafasSpontan.
                    getSelectedItem().toString(),
                    B1JenisNafas.getSelectedItem().toString(), B1O2Nafas.
                    getText(), B1KetO2Nafas.getText(),
                    B1Irama.getSelectedItem().toString(), B1SuaraNafas.
                    getSelectedItem().toString(),
                    B2IramaJantung.getSelectedItem().toString(), B2Acral.
                    getSelectedItem().toString(), B2S1S2.getSelectedItem().
                            toString(), B2ConjungtivaAnemis.getSelectedItem().
                            toString(), B3Kesadaran.getSelectedItem().toString(),
                    B3JamIstirahatTidur.getText(), B3GangguanTidur.getText(),
                    B3KetGangguanTidur.getSelectedItem().toString(),
                    B3TingkatKesadaran.getSelectedItem().toString(), B3Tangisan.
                    getSelectedItem().toString(), B3LingkarKepala.getText(),
                    B3Kelainan.getSelectedItem().toString(), B3UbunUbun.
                    getSelectedItem().toString(), B3Pupil.getSelectedItem().
                            toString(), B3SkleraMata.getSelectedItem().
                            toString(), B3Gerakan.getSelectedItem().toString(),
                    B3PancaIndra.getSelectedItem().toString(),
                    B3KetPancaIndra.getText(), B3Kejang.getSelectedItem().
                    toString(), B3ReflekRooting.getSelectedItem().toString(),
                    B4Kebersihan.getSelectedItem().toString(), B4Sekret.
                    getSelectedItem().toString(), B4KetSekret.getText(),
                    B4ProduksiUrine.getText(), B4Warna.getSelectedItem().
                    toString(), B4KetWarna.getText(), B4Gangguan.
                    getSelectedItem().toString(), B4AlatBantu.getSelectedItem().
                            toString(),
                    B5NafsuMakan.getSelectedItem().toString(), B5FrekuensiMakan.
                    getText(), B5PorsiMakan.getText(), B5Minum.getText(),
                    B5JenisMinum.getText(), B5CaraMinum.getSelectedItem().
                    toString(), B5Anus.getSelectedItem().toString(), B5BAB.
                    getText(), B5Konsisten.getText(), B5WarnaBAB.getText(),
                    B5KetWarnaBAB.getSelectedItem().toString(), B5Perut.
                    getSelectedItem().toString(), B5Peristaltik.getText(),
                    B5ReflekRooting.getSelectedItem().toString(), B5Kelainan.
                    getSelectedItem().toString(), B5Lidah.getSelectedItem().
                            toString(), B5SelaputLender.getSelectedItem().
                            toString(), B6PergerakanSendi.getSelectedItem().
                            toString(), B6KetPergerakanSendi.getText(),
                    B6WarnaKulit.getSelectedItem().toString(),
                    B6IntergitasKulit.getSelectedItem().toString(),
                    B6Kepala.getSelectedItem().toString(), B6TaliPusat.
                    getSelectedItem().toString(), B6Tugor.getSelectedItem().
                            toString(), B6Odem.getSelectedItem().toString(),
                    B6KetOdem.getText(), B6KekuatanOtotKiriAtas.getText(),
                    B6KekuatanOtotKananAtas.getText(), B6KekuatanOtotKiriBawah.
                    getText(), B6KekuatanOtotKananBawah.getText(),
                    AlatGenitalLaki.getSelectedItem().toString(),
                    AlatGenitalPerampuan.getSelectedItem().toString(),
                    DerajatIkterus.getSelectedItem().toString(), DaerahIkterus.
                    getText(), KadarBilirubin.getText(), PenilaianApgarScore.
                    getText(), PenilaianDownScore.getText(), Anakke.getText(),
                    DariSaudara.getText(),
                    CaraKelahiran.getSelectedItem().toString(),
                    KetCaraKelahiran.getText(), UmurKelahiran.getSelectedItem().
                    toString(), KelainanBawaan.getSelectedItem().toString(),
                    KetKelainanBawaan.getText(), WarnaKetuban.getSelectedItem().
                    toString(), KelainanPersalinan.getText(), UsiaKehamilan.
                    getText(), Penolong.getSelectedItem().toString(),
                    PenolongPersalinan.getText(), UsiaTengkurap.getText(),
                    UsiaDuduk.getText(), UsiaBerdiri.getText(), UsiaGigi.
                    getText(), UsiaBerjalan.getText(), UsiaBicara.getText(),
                    UsiaMembaca.getText(), UsiaMenulis.getText(), GangguanEmosi.
                    getText(), AlatBantu.getSelectedItem().toString(),
                    KetBantu.getText(), Prothesa.getSelectedItem().toString(),
                    KetProthesa.getText(), ADL.getSelectedItem().toString(),
                    StatusPsiko.getSelectedItem().toString(), KetPsiko.getText(),
                    HubunganKeluarga.getSelectedItem().toString(),
                    Pengasuh.getSelectedItem().toString(), KetPengasuh.getText(),
                    Ekonomi.getSelectedItem().toString(), StatusBudaya.
                    getSelectedItem().toString(), KetBudaya.getText(), Edukasi.
                    getSelectedItem().toString(), KetEdukasi.getText(), Agama.
                    getText(), Wajib.getSelectedItem().toString(),
                    KetHalanganLain.getText(), Toharah.getSelectedItem().
                    toString(), Sholat.getSelectedItem().toString(),
                    MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                    SkalaHumptyDumpty1.getSelectedItem().toString(),
                    NilaiHumptyDumpty1.getText(), SkalaHumptyDumpty2.
                    getSelectedItem().toString(), NilaiHumptyDumpty2.getText(),
                    SkalaHumptyDumpty3.getSelectedItem().toString(),
                    NilaiHumptyDumpty3.getText(), SkalaHumptyDumpty4.
                    getSelectedItem().toString(), NilaiHumptyDumpty4.getText(),
                    SkalaHumptyDumpty5.getSelectedItem().toString(),
                    NilaiHumptyDumpty5.getText(),
                    SkalaHumptyDumpty6.getSelectedItem().toString(),
                    NilaiHumptyDumpty6.getText(), SkalaHumptyDumpty7.
                    getSelectedItem().toString(), NilaiHumptyDumpty7.getText(),
                    NilaiHumptyDumptyTotal.getText(), SG1.getSelectedItem().
                    toString(),
                    NilaiGizi1.getText(), SG2.getSelectedItem().toString(),
                    NilaiGizi2.getText(), SG3.getSelectedItem().toString(),
                    NilaiGizi3.getText(), SG4.getSelectedItem().toString(),
                    NilaiGizi4.getText(), TotalNilaiGizi.getText(),
                    Kriteria1.getSelectedItem().toString(), Kriteria2.
                    getSelectedItem().toString(), Kriteria3.getSelectedItem().
                            toString(), Kriteria4.getSelectedItem().toString(),
                    pilih1, pilih2, pilih3, pilih4, pilih5, pilih6, pilih7,
                    pilih8, SkalaWajah.getSelectedItem().toString(), NilaiWajah.
                    getText(), SkalaTangisan.getSelectedItem().toString(),
                    NilaiTangisan.getText(), SkalaPolaNapas.getSelectedItem().
                    toString(), NilaiPolaNapas.getText(), SkalaLengan.
                    getSelectedItem().toString(),
                    NilaiLengan.getText(), SkalaTungkai.getSelectedItem().
                    toString(), NilaiTungkai.getText(), SkalaAktivitas.
                    getSelectedItem().toString(), NilaiAktivitas.getText(),
                    SkalaNyeri.getText(), Rencana.getText(), KdPetugas.getText(),
                    KdPetugas2.getText(), KdDPJP.getText()
                }) == true) {
            tabMode.addRow(new String[]{
                TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.
                getText(), Jk.getText().substring(0, 1), KdPetugas.getText(),
                NmPetugas.getText(), KdPetugas2.getText(), NmPetugas2.getText(),
                KdDPJP.getText(), NmDPJP.getText(), Valid.SetTgl(TglAsuhan.
                getSelectedItem() + "") + " " + TglAsuhan.getSelectedItem().
                toString().substring(11, 19), Anamnesis.getSelectedItem().
                toString(), KetAnamnesis.getText(), TibadiRuang.
                getSelectedItem().toString(), MacamKasus.getSelectedItem().
                toString(),
                CaraMasuk.getSelectedItem().toString(), KeluhanUtama.getText(),
                RPS.getText(), RPD.getText(), RPK.getText(), RPO.getText(),
                Alergi.getText(), KesadaranMental.getText(), KeadaanMentalUmum.
                getSelectedItem().toString(), TD.getText(), Nadi.getText(),
                SpO2.getText(), RR.getText(),
                Suhu.getText(), GCS.getText(), BB.getText(), TB.getText(), LP.
                getText(), LK.getText(), LD.getText(), B1NafasSpontan.
                getSelectedItem().toString(), B1JenisNafas.getSelectedItem().
                toString(), B1O2Nafas.getText(), B1KetO2Nafas.getText(),
                B1Irama.getSelectedItem().toString(), B1SuaraNafas.
                getSelectedItem().toString(),
                B2IramaJantung.getSelectedItem().toString(), B2Acral.
                getSelectedItem().toString(), B2S1S2.getSelectedItem().
                toString(), B2ConjungtivaAnemis.getSelectedItem().toString(),
                B3Kesadaran.getSelectedItem().toString(), B3JamIstirahatTidur.
                getText(), B3GangguanTidur.getText(), B3KetGangguanTidur.
                getSelectedItem().toString(),
                B3TingkatKesadaran.getSelectedItem().toString(), B3Tangisan.
                getSelectedItem().toString(), B3LingkarKepala.getText(),
                B3Kelainan.getSelectedItem().toString(), B3UbunUbun.
                getSelectedItem().toString(), B3Pupil.getSelectedItem().
                toString(), B3SkleraMata.getSelectedItem().toString(),
                B3Gerakan.getSelectedItem().toString(), B3PancaIndra.
                getSelectedItem().toString(),
                B3KetPancaIndra.getText(), B3Kejang.getSelectedItem().toString(),
                B3ReflekRooting.getSelectedItem().toString(), B4Kebersihan.
                getSelectedItem().toString(), B4Sekret.getSelectedItem().
                toString(), B4KetSekret.getText(), B4ProduksiUrine.getText(),
                B4Warna.getSelectedItem().toString(), B4KetWarna.getText(),
                B4Gangguan.getSelectedItem().toString(), B4AlatBantu.
                getSelectedItem().toString(),
                B5NafsuMakan.getSelectedItem().toString(), B5FrekuensiMakan.
                getText(), B5PorsiMakan.getText(), B5Minum.getText(),
                B5JenisMinum.getText(), B5CaraMinum.getSelectedItem().toString(),
                B5Anus.getSelectedItem().toString(), B5BAB.getText(),
                B5Konsisten.getText(), B5WarnaBAB.getText(), B5KetWarnaBAB.
                getSelectedItem().toString(), B5Perut.getSelectedItem().
                toString(), B5Peristaltik.getText(),
                B5ReflekRooting.getSelectedItem().toString(), B5Kelainan.
                getSelectedItem().toString(), B5Lidah.getSelectedItem().
                toString(), B5SelaputLender.getSelectedItem().toString(),
                B6PergerakanSendi.getSelectedItem().toString(),
                B6KetPergerakanSendi.getText(), B6WarnaKulit.getSelectedItem().
                toString(), B6IntergitasKulit.getSelectedItem().toString(),
                B6Kepala.getSelectedItem().toString(), B6TaliPusat.
                getSelectedItem().toString(), B6Tugor.getSelectedItem().
                toString(), B6Odem.getSelectedItem().toString(), B6KetOdem.
                getText(), B6KekuatanOtotKiriAtas.getText(),
                B6KekuatanOtotKananAtas.getText(), B6KekuatanOtotKiriBawah.
                getText(), B6KekuatanOtotKananBawah.getText(), AlatGenitalLaki.
                getSelectedItem().toString(), AlatGenitalPerampuan.
                getSelectedItem().toString(),
                DerajatIkterus.getSelectedItem().toString(), DaerahIkterus.
                getText(), KadarBilirubin.getText(), PenilaianApgarScore.
                getText(), PenilaianDownScore.getText(), Anakke.getText(),
                DariSaudara.getText(),
                CaraKelahiran.getSelectedItem().toString(), KetCaraKelahiran.
                getText(), UmurKelahiran.getSelectedItem().toString(),
                KelainanBawaan.getSelectedItem().toString(), KetKelainanBawaan.
                getText(), WarnaKetuban.getSelectedItem().toString(),
                KelainanPersalinan.getText(), UsiaKehamilan.getText(), Penolong.
                getSelectedItem().toString(), PenolongPersalinan.getText(),
                UsiaTengkurap.getText(),
                UsiaDuduk.getText(), UsiaBerdiri.getText(), UsiaGigi.getText(),
                UsiaBerjalan.getText(), UsiaBicara.getText(), UsiaMembaca.
                getText(), UsiaMenulis.getText(), GangguanEmosi.getText(),
                AlatBantu.getSelectedItem().toString(),
                KetBantu.getText(), Prothesa.getSelectedItem().toString(),
                KetProthesa.getText(), ADL.getSelectedItem().toString(),
                StatusPsiko.getSelectedItem().toString(), KetPsiko.getText(),
                HubunganKeluarga.getSelectedItem().toString(),
                Pengasuh.getSelectedItem().toString(), KetPengasuh.getText(),
                Ekonomi.getSelectedItem().toString(), StatusBudaya.
                getSelectedItem().toString(), KetBudaya.getText(), Edukasi.
                getSelectedItem().toString(), KetEdukasi.getText(), Agama.
                getText(), Wajib.getSelectedItem().toString(),
                KetHalanganLain.getText(), Toharah.getSelectedItem().toString(),
                Sholat.getSelectedItem().toString(), MotivasiKesembuhanIbadah.
                getSelectedItem().toString(),
                SkalaHumptyDumpty1.getSelectedItem().toString(),
                NilaiHumptyDumpty1.getText(), SkalaHumptyDumpty2.
                getSelectedItem().toString(), NilaiHumptyDumpty2.getText(),
                SkalaHumptyDumpty3.getSelectedItem().toString(),
                NilaiHumptyDumpty3.getText(), SkalaHumptyDumpty4.
                getSelectedItem().toString(), NilaiHumptyDumpty4.getText(),
                SkalaHumptyDumpty5.getSelectedItem().toString(),
                NilaiHumptyDumpty5.getText(),
                SkalaHumptyDumpty6.getSelectedItem().toString(),
                NilaiHumptyDumpty6.getText(), SkalaHumptyDumpty7.
                getSelectedItem().toString(), NilaiHumptyDumpty7.getText(),
                NilaiHumptyDumptyTotal.getText(), SG1.getSelectedItem().
                toString(),
                NilaiGizi1.getText(), SG2.getSelectedItem().toString(),
                NilaiGizi2.getText(), SG3.getSelectedItem().toString(),
                NilaiGizi3.getText(), SG4.getSelectedItem().toString(),
                NilaiGizi4.getText(), TotalNilaiGizi.getText(),
                Kriteria1.getSelectedItem().toString(), Kriteria2.
                getSelectedItem().toString(), Kriteria3.getSelectedItem().
                toString(), Kriteria4.getSelectedItem().toString(),
                pilih1, pilih2, pilih3, pilih4, pilih5, pilih6, pilih7, pilih8,
                SkalaWajah.getSelectedItem().toString(), NilaiWajah.getText(),
                SkalaTangisan.getSelectedItem().toString(), NilaiTangisan.
                getText(), SkalaPolaNapas.getSelectedItem().toString(),
                NilaiPolaNapas.getText(), SkalaLengan.getSelectedItem().
                toString(),
                NilaiLengan.getText(), SkalaTungkai.getSelectedItem().toString(),
                NilaiTungkai.getText(), SkalaAktivitas.getSelectedItem().
                toString(), NilaiAktivitas.getText(), SkalaNyeri.getText(),
                Rencana.getText()
            });
            LCount.setText("" + tabMode.getRowCount());
            for (i = 0; i < tbMasalahKeperawatan.getRowCount(); i++) {
                if (tbMasalahKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    if (Sequel.menyimpantf2(
                            "penilaian_awal_keperawatan_ranap_masalah_bayi",
                            "?,?,?", 3, new String[]{TNoRw.getText(),
                                tbMasalahKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                                getSelectedItem().toString().substring(11, 19)}) == true) {
                        tabModeDetailMasalah.addRow(new String[]{
                            tbMasalahKeperawatan.getValueAt(i, 1).toString(),
                            tbMasalahKeperawatan.getValueAt(i, 2).toString()
                        });
                    }
                }
            }
            for (i = 0; i < tbRencanaKeperawatan.getRowCount(); i++) {
                if (tbRencanaKeperawatan.getValueAt(i, 0).toString().equals(
                        "true")) {
                    if (Sequel.menyimpantf2(
                            "penilaian_awal_keperawatan_ranap_rencana_bayi",
                            "?,?,?", 3, new String[]{TNoRw.getText(),
                                tbRencanaKeperawatan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.
                                getSelectedItem().toString().substring(11, 19)}) == true) {
                        tabModeDetailRencana.addRow(new String[]{
                            tbRencanaKeperawatan.getValueAt(i, 1).toString(),
                            tbRencanaKeperawatan.getValueAt(i, 2).toString()
                        });
                    }
                }
            }
            TNoRM1.setText(TNoRM.getText());
            TPasien1.setText(TPasien.getText());
            emptTeks();
        }
    }

    private void isTotalResikoHumptyDumpty() {
        try {
            NilaiHumptyDumptyTotal.setText((Integer.parseInt(NilaiHumptyDumpty1.
                    getText()) + Integer.parseInt(NilaiHumptyDumpty2.getText()) + Integer.
                    parseInt(NilaiHumptyDumpty3.getText()) + Integer.parseInt(
                    NilaiHumptyDumpty4.getText()) + Integer.parseInt(
                    NilaiHumptyDumpty5.getText()) + Integer.parseInt(
                    NilaiHumptyDumpty6.getText()) + Integer.parseInt(
                    NilaiHumptyDumpty7.getText())) + "");
            if (Integer.parseInt(NilaiHumptyDumptyTotal.getText()) < 12) {
                TingkatHumptyDumpty.setText(
                        "Tingkat Resiko : Risiko Rendah (7 - 11), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiHumptyDumptyTotal.getText()) > 12) {
                TingkatHumptyDumpty.setText(
                        "Tingkat Resiko : Risiko Tinggi >12, Tindakan : Intervensi pencegahan risiko jatuh standar");
            }
        } catch (Exception e) {
            NilaiHumptyDumptyTotal.setText("0");
            TingkatHumptyDumpty.setText(
                    "Tingkat Resiko : Risiko Rendah (7 - 11), Tindakan : Intervensi pencegahan risiko standar");
        }
    }

    private static final Logger LOG = Logger.getLogger(
            RMPenilaianAwalKeperawatanRanapNeonatus.class.getName());

}
