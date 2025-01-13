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
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
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
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;

/**
 * @author perpustakaan
 */
public class RMPenilaianAwalKeperawatanKebidananRanap extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabModeRiwayatKehamilan, tabModeRiwayatKehamilan2, tabModeMasalah,
            tabModeDetailMasalah, tabModeRencana, tabModeDetailRencana;

    private Connection koneksi = koneksiDB.condb();

    private sekuel Sequel = new sekuel();

    private validasi Valid = new validasi();

    private PreparedStatement ps;

    private ResultSet rs;

    private int i = 0, jml = 0, index = 0;

    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    private DlgCariDokter dokter = new DlgCariDokter(null, false);

    private boolean[] pilih;

    private String[] kode, masalah;

    private StringBuilder htmlContent;

    private String pilihan = "", finger = "", pilih1 = "", pilih2 = "", pilih3 = "", pilih4 = "", pilih5 = "",
            pilih6 = "", pilih7 = "", pilih8 = "";

    private String TANGGALMUNDUR = "yes";

    private File file;

    private FileWriter fileWriter;

    private String iyem;

    private ObjectMapper mapper = new ObjectMapper();

    private JsonNode root;

    private JsonNode response;

    private FileReader myObj;

    /**
     * Creates new form DlgRujuk
     *
     * @param parent
     * @param modal
     */
    public RMPenilaianAwalKeperawatanKebidananRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        DlgRiwayatPersalinan.setSize(650, 192);

        tabMode = new DefaultTableModel(null, new Object[]{"No.Rawat", "No.RM", "Nama Pasien", "Tgl.Lahir", "J.K.",
            "NIP Pengkaji 1", "Nama Pengkaji 1", "NIP Pengkaji 2", "Nama Pengkaji 2", "Kode DPJP", "Nama DPJP",
            "Tgl.Asuhan", "Anamnesis", "Tiba di Ruang Rawat", "Cara Masuk", "Keluhan Utama",
            "Penyakit Selama Kehamilan", "Riwayat Penyakit Keluarga", "Riwayat Pembedahan",
            "Riwayat Penyakit Saat ini", "Riwayat Pengobatan", "Riwayat Alergi", "Komplikasi Kehamilan Sebelumnya",
            "Keterangan Komplikasi Sebelumnya", "Umur Menarche", "Lamanya Mens", "Banyaknya Pembalut",
            "Siklus Haid", "Ket.Siklus Haid", "Dirasakan Saat Menstruasi", "Status Menikah", "Jml.Nikah",
            "Usia Perkawinan 1", "Status Perkawinan 1", "Usia Perkawinan 2", "Status Perkawinan 2",
            "Usia Perkawinan 3", "Status Perkawinan 3", "G", "P", "A", "Hidup", "HPHT", "Usia Hamil",
            "Tg.Perkiraan", "Riwayat Imunisasi", "ANC", "ANC Ke", "Ket. ANC", "Keluhan Hamil Muda",
            "Keluhan Hamil Tua", "Riwayat Keluarga Berencana", "Lamanya KB", "Komplikasi KB", "Ket Komplikasi KB",
            "Berhenti KB", "Alasan Berhenti KB", "Riwayat Genekologi", "Obat/Vitamin", "Keterangan Obat/Vitamin",
            "Merokok", "Rokok/Hari", "Alkohol", "Alkohol/Hari", "Obat Tidur/Narkoba", "Kesadaran Mental",
            "Keadaan Umum", "GCS(E,V,M)", "TD", "Nadi", "RR", "Suhu", "SpO2", "BB", "TB", "LILA", "TFU", "TBJ",
            "GD", "Letak", "Presentasi", "Penurunan", "Kontraksi/HIS", "Kekuatan", "Lamanya", "DJJ",
            "Keterangan DJJ", "Portio", "Serviks", "Ketuban", "Hodge", "Panggul", "Inspekulo",
            "Keterangan Inspekulo", "Lakmus", "Keterangan Lakmus", "CTG", "Keterangan CTG", "Kepala", "Muka",
            "Mata", "Hidung", "Telinga", "Mulut", "Leher", "Dada", "Perut", "Genitalia", "Ekstremitas",
            "a. Aktivitas Sehari-hari", "b. Berjalan", "Ket. Berjalan", "c. Aktifitas", "d. Alat Ambulasi",
            "e. Ekstrimitas Atas", "Ket. Ekstrimitas Atas", "f. Ekstrimitas Bawah", "Ket. Ekstrimitas Bawah",
            "g. Kemampuan Menggenggam", "Ket. Kemampuan Menggenggam", "h. Kemampuan Koordinasi",
            "Ket. Kemampuan Koordinasi", "i. Kesimpulan Gangguan Fungsi", "a. Kondisi Psikologis",
            "b. Adakah Perilaku", "Keterangan Perilaku", "c. Gangguan Jiwa di Masa Lalu",
            "d. Hubungan dengan Anggota Keluarga", "e. Agama", "f. Tinggal Dengan", "Keterangan Tinggal",
            "g. Pekerjaan", "h. Pembayaran", "i. Nilai-nilai Kepercayaan", "Ket. Nilai-nilai Kepercayaan",
            "j. Bahasa Sehari-hari", "k. Pendidikan Pasien", "l. Pendidikan P.J.", "m. Edukasi Diberikan Kepada",
            "n. Wajib", "o. Halangan Lain", "p. Toharah", "q. Sholat", "r. Motivasi Kesembuhan & Ibadah",
            "Keterangan Edukasi", "Penilaian Nyeri", "Penyebab", "Keterangan Penyebab", "Kualitas",
            "Keterangan Kualitas", "Lokasi", "Menyebar", "Skala Nyeri", "Durasi", "Nyeri hilang bila",
            "Keterangan Nyeri Hilang", "Diberitahukan Dokter", "Pada Jam", "1. Riwayat Jatuh", "Nilai 1",
            "2. Diagnosis Sekunder (≥ 2 Diagnosis Medis)", "Nilai 2", "3. Alat Bantu", "Nilai 3",
            "4. Terpasang Infuse", "Nilai 4", "5. Gaya Berjalan", "Nilai 5", "6. Status Mental", "Nilai 6",
            "Total Nilai", "1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?", "Skor 1",
            "2. Apakah asupan makan berkurang karena tidak nafsu makan ?", "Skor 2", "Total Skor",
            "Pasien dengan diagnosis khusus", "Keterangan Diagnosa Khusus",
            "Sudah dibaca dan diketahui oleh Dietisen", "Jam Dibaca Dietisen", "Umur > 65 Tahun",
            "Keterbatasan mobilitas", "Perawatan atau pengobatan lanjutan",
            "Bantuan untuk melakukan aktifitas sehari-hari", "Perencanaan 1", "Perencanaan 2", "Perencanaan 3",
            "Perencanaan 4", "Perencanaan 5", "Perencanaan 6", "Perencanaan 7", "Perencanaan 8",
            "Masalah Kebidanan Lainnya", "Rencana Kebidanan Lainnya"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbObat.setModel(tabMode);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 194; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(25);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(117);
            } else if (i == 12) {
                column.setPreferredWidth(90);
            } else if (i == 13) {
                column.setPreferredWidth(110);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(150);
            } else if (i == 17) {
                column.setPreferredWidth(150);
            } else if (i == 18) {
                column.setPreferredWidth(150);
            } else if (i == 19) {
                column.setPreferredWidth(150);
            } else if (i == 20) {
                column.setPreferredWidth(150);
            } else if (i == 21) {
                column.setPreferredWidth(100);
            } else if (i == 22) {
                column.setPreferredWidth(175);
            } else if (i == 23) {
                column.setPreferredWidth(180);
            } else if (i == 24) {
                column.setPreferredWidth(84);
            } else if (i == 25) {
                column.setPreferredWidth(78);
            } else if (i == 26) {
                column.setPreferredWidth(108);
            } else if (i == 27) {
                column.setPreferredWidth(60);
            } else if (i == 28) {
                column.setPreferredWidth(80);
            } else if (i == 29) {
                column.setPreferredWidth(136);
            } else if (i == 30) {
                column.setPreferredWidth(81);
            } else if (i == 31) {
                column.setPreferredWidth(54);
            } else if (i == 32) {
                column.setPreferredWidth(96);
            } else if (i == 33) {
                column.setPreferredWidth(106);
            } else if (i == 34) {
                column.setPreferredWidth(96);
            } else if (i == 35) {
                column.setPreferredWidth(106);
            } else if (i == 36) {
                column.setPreferredWidth(96);
            } else if (i == 37) {
                column.setPreferredWidth(106);
            } else if (i == 38) {
                column.setPreferredWidth(22);
            } else if (i == 39) {
                column.setPreferredWidth(22);
            } else if (i == 40) {
                column.setPreferredWidth(22);
            } else if (i == 41) {
                column.setPreferredWidth(37);
            } else if (i == 42) {
                column.setPreferredWidth(65);
            } else if (i == 43) {
                column.setPreferredWidth(59);
            } else if (i == 44) {
                column.setPreferredWidth(70);
            } else if (i == 45) {
                column.setPreferredWidth(97);
            } else if (i == 46) {
                column.setPreferredWidth(35);
            } else if (i == 47) {
                column.setPreferredWidth(45);
            } else if (i == 48) {
                column.setPreferredWidth(70);
            } else if (i == 49) {
                column.setPreferredWidth(105);
            } else if (i == 50) {
                column.setPreferredWidth(98);
            } else if (i == 51) {
                column.setPreferredWidth(150);
            } else if (i == 52) {
                column.setPreferredWidth(70);
            } else if (i == 53) {
                column.setPreferredWidth(90);
            } else if (i == 54) {
                column.setPreferredWidth(120);
            } else if (i == 55) {
                column.setPreferredWidth(90);
            } else if (i == 56) {
                column.setPreferredWidth(120);
            } else if (i == 57) {
                column.setPreferredWidth(104);
            } else if (i == 58) {
                column.setPreferredWidth(100);
            } else if (i == 59) {
                column.setPreferredWidth(150);
            } else if (i == 60) {
                column.setPreferredWidth(50);
            } else if (i == 61) {
                column.setPreferredWidth(60);
            } else if (i == 62) {
                column.setPreferredWidth(45);
            } else if (i == 63) {
                column.setPreferredWidth(68);
            } else if (i == 64) {
                column.setPreferredWidth(103);
            } else if (i == 65) {
                column.setPreferredWidth(105);
            } else if (i == 66) {
                column.setPreferredWidth(84);
            } else if (i == 67) {
                column.setPreferredWidth(63);
            } else if (i == 68) {
                column.setPreferredWidth(50);
            } else if (i == 69) {
                column.setPreferredWidth(35);
            } else if (i == 70) {
                column.setPreferredWidth(35);
            } else if (i == 71) {
                column.setPreferredWidth(35);
            } else if (i == 72) {
                column.setPreferredWidth(35);
            } else if (i == 73) {
                column.setPreferredWidth(35);
            } else if (i == 74) {
                column.setPreferredWidth(35);
            } else if (i == 75) {
                column.setPreferredWidth(35);
            } else if (i == 76) {
                column.setPreferredWidth(55);
            } else if (i == 77) {
                column.setPreferredWidth(55);
            } else if (i == 78) {
                column.setPreferredWidth(27);
            } else if (i == 79) {
                column.setPreferredWidth(55);
            } else if (i == 80) {
                column.setPreferredWidth(65);
            } else if (i == 81) {
                column.setPreferredWidth(65);
            } else if (i == 82) {
                column.setPreferredWidth(75);
            } else if (i == 83) {
                column.setPreferredWidth(65);
            } else if (i == 84) {
                column.setPreferredWidth(50);
            } else if (i == 85) {
                column.setPreferredWidth(40);
            } else if (i == 86) {
                column.setPreferredWidth(85);
            } else if (i == 87) {
                column.setPreferredWidth(50);
            } else if (i == 88) {
                column.setPreferredWidth(45);
            } else if (i == 89) {
                column.setPreferredWidth(53);
            } else if (i == 90) {
                column.setPreferredWidth(53);
            } else if (i == 91) {
                column.setPreferredWidth(145);
            } else if (i == 92) {
                column.setPreferredWidth(60);
            } else if (i == 93) {
                column.setPreferredWidth(115);
            } else if (i == 94) {
                column.setPreferredWidth(60);
            } else if (i == 95) {
                column.setPreferredWidth(115);
            } else if (i == 96) {
                column.setPreferredWidth(60);
            } else if (i == 97) {
                column.setPreferredWidth(115);
            } else if (i == 98) {
                column.setPreferredWidth(80);
            } else if (i == 99) {
                column.setPreferredWidth(50);
            } else if (i == 100) {
                column.setPreferredWidth(128);
            } else if (i == 101) {
                column.setPreferredWidth(50);
            } else if (i == 102) {
                column.setPreferredWidth(50);
            } else if (i == 103) {
                column.setPreferredWidth(50);
            } else if (i == 104) {
                column.setPreferredWidth(140);
            } else if (i == 105) {
                column.setPreferredWidth(87);
            } else if (i == 106) {
                column.setPreferredWidth(100);
            } else if (i == 107) {
                column.setPreferredWidth(60);
            } else if (i == 108) {
                column.setPreferredWidth(100);
            } else if (i == 109) {
                column.setPreferredWidth(120);
            } else if (i == 110) {
                column.setPreferredWidth(140);
            } else if (i == 111) {
                column.setPreferredWidth(140);
            } else if (i == 112) {
                column.setPreferredWidth(68);
            } else if (i == 113) {
                column.setPreferredWidth(101);
            } else if (i == 114) {
                column.setPreferredWidth(100);
            } else if (i == 115) {
                column.setPreferredWidth(110);
            } else if (i == 116) {
                column.setPreferredWidth(107);
            } else if (i == 117) {
                column.setPreferredWidth(118);
            } else if (i == 118) {
                column.setPreferredWidth(154);
            } else if (i == 119) {
                column.setPreferredWidth(164);
            } else if (i == 120) {
                column.setPreferredWidth(133);
            } else if (i == 121) {
                column.setPreferredWidth(143);
            } else if (i == 122) {
                column.setPreferredWidth(160);
            } else if (i == 123) {
                column.setPreferredWidth(106);
            } else if (i == 124) {
                column.setPreferredWidth(98);
            } else if (i == 125) {
                column.setPreferredWidth(107);
            } else if (i == 126) {
                column.setPreferredWidth(156);
            } else if (i == 127) {
                column.setPreferredWidth(198);
            } else if (i == 128) {
                column.setPreferredWidth(70);
            } else if (i == 129) {
                column.setPreferredWidth(95);
            } else if (i == 130) {
                column.setPreferredWidth(103);
            } else if (i == 131) {
                column.setPreferredWidth(110);
            } else if (i == 132) {
                column.setPreferredWidth(130);
            } else if (i == 133) {
                column.setPreferredWidth(130);
            } else if (i == 134) {
                column.setPreferredWidth(142);
            } else if (i == 135) {
                column.setPreferredWidth(110);
            } else if (i == 136) {
                column.setPreferredWidth(108);
            } else if (i == 137) {
                column.setPreferredWidth(100);
            } else if (i == 138) {
                column.setPreferredWidth(148);
            } else if (i == 139) {
                column.setPreferredWidth(148);
            } else if (i == 140) {
                column.setPreferredWidth(83);
            } else if (i == 141) {
                column.setPreferredWidth(83);
            } else if (i == 142) {
                column.setPreferredWidth(115);
            } else if (i == 143) {
                column.setPreferredWidth(87);
            } else if (i == 144) {
                column.setPreferredWidth(107);
            } else if (i == 145) {
                column.setPreferredWidth(100);
            } else if (i == 146) {
                column.setPreferredWidth(55);
            } else if (i == 147) {
                column.setPreferredWidth(63);
            } else if (i == 148) {
                column.setPreferredWidth(60);
            } else if (i == 149) {
                column.setPreferredWidth(87);
            } else if (i == 150) {
                column.setPreferredWidth(126);
            } else if (i == 151) {
                column.setPreferredWidth(110);
            } else if (i == 152) {
                column.setPreferredWidth(60);
            } else if (i == 153) {
                column.setPreferredWidth(90);
            } else if (i == 154) {
                column.setPreferredWidth(40);
            } else if (i == 155) {
                column.setPreferredWidth(225);
            } else if (i == 156) {
                column.setPreferredWidth(40);
            } else if (i == 157) {
                column.setPreferredWidth(218);
            } else if (i == 158) {
                column.setPreferredWidth(40);
            } else if (i == 159) {
                column.setPreferredWidth(105);
            } else if (i == 160) {
                column.setPreferredWidth(40);
            } else if (i == 161) {
                column.setPreferredWidth(160);
            } else if (i == 162) {
                column.setPreferredWidth(40);
            } else if (i == 163) {
                column.setPreferredWidth(225);
            } else if (i == 164) {
                column.setPreferredWidth(40);
            } else if (i == 165) {
                column.setPreferredWidth(58);
            } else if (i == 166) {
                column.setPreferredWidth(380);
            } else if (i == 167) {
                column.setPreferredWidth(40);
            } else if (i == 168) {
                column.setPreferredWidth(317);
            } else if (i == 169) {
                column.setPreferredWidth(40);
            } else if (i == 170) {
                column.setPreferredWidth(58);
            } else if (i == 171) {
                column.setPreferredWidth(165);
            } else if (i == 172) {
                column.setPreferredWidth(149);
            } else if (i == 173) {
                column.setPreferredWidth(209);
            } else if (i == 174) {
                column.setPreferredWidth(107);
            } else if (i == 175) {
                column.setPreferredWidth(107);
            } else if (i == 176) {
                column.setPreferredWidth(107);
            } else if (i == 177) {
                column.setPreferredWidth(107);
            } else if (i == 178) {
                column.setPreferredWidth(107);
            } else if (i == 179) {
                column.setPreferredWidth(107);
            } else if (i == 180) {
                column.setPreferredWidth(107);
            } else if (i == 181) {
                column.setPreferredWidth(107);
            } else if (i == 182) {
                column.setPreferredWidth(107);
            } else if (i == 183) {
                column.setPreferredWidth(107);
            } else if (i == 184) {
                column.setPreferredWidth(107);
            } else if (i == 185) {
                column.setPreferredWidth(107);
            } else if (i == 186) {
                column.setPreferredWidth(107);
            } else if (i == 187) {
                column.setPreferredWidth(200);
            } else if (i == 188) {
                column.setPreferredWidth(200);
            } else if (i == 189) {
                column.setPreferredWidth(107);
            } else if (i == 190) {
                column.setPreferredWidth(107);
            } else if (i == 191) {
                column.setPreferredWidth(107);
            } else if (i == 192) {
                column.setPreferredWidth(200);
            } else if (i == 193) {
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRiwayatKehamilan = new DefaultTableModel(null, new Object[]{"No", "Tgl/Thn", "Tempat Persalinan",
            "Usia Hamil", "Jenis Persalinan", "Penolong", "Penyulit", "J.K.", "BB/PB", "Keadaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbRiwayatKehamilan.setModel(tabModeRiwayatKehamilan);

        tbRiwayatKehamilan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatKehamilan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbRiwayatKehamilan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(170);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(30);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            }
        }
        tbRiwayatKehamilan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRiwayatKehamilan2 = new DefaultTableModel(null, new Object[]{"No", "Tgl/Thn", "Tempat Persalinan",
            "Usia Hamil", "Jenis Persalinan", "Penolong", "Penyulit", "J.K.", "BB/PB", "Keadaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbRiwayatKehamilan1.setModel(tabModeRiwayatKehamilan2);

        tbRiwayatKehamilan1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayatKehamilan1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbRiwayatKehamilan1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(170);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(30);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            }
        }
        tbRiwayatKehamilan1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeMasalah = new DefaultTableModel(null, new Object[]{"P", "KODE", "MASALAH KEBIDANAN"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class};

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
        tbMasalahKeperawatanKebidanan.setModel(tabModeMasalah);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahKeperawatanKebidanan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMasalahKeperawatanKebidanan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbMasalahKeperawatanKebidanan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }
        tbMasalahKeperawatanKebidanan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRencana = new DefaultTableModel(null, new Object[]{"P", "KODE", "RENCANA KEBIDANAN"}) {
            Class[] types = new Class[]{java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class};

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
        tbRencanaKeperawatanKebidanan.setModel(tabModeRencana);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaKeperawatanKebidanan.setPreferredScrollableViewportSize(new Dimension(800, 500));
        tbRencanaKeperawatanKebidanan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbRencanaKeperawatanKebidanan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(800);
            }
        }
        tbRencanaKeperawatanKebidanan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailMasalah = new DefaultTableModel(null, new Object[]{"Kode", "Masalah Kebidanan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbMasalahDetail.setModel(tabModeDetailMasalah);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbMasalahDetail.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbMasalahDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasalahDetail.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(420);
            }
        }
        tbMasalahDetail.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDetailRencana = new DefaultTableModel(null, new Object[]{"Kode", "Rencana Kebidanan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

        };
        tbRencanaDetail.setModel(tabModeDetailRencana);

        // tbObat.setDefaultRenderer(Object.class, new
        // WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbRencanaDetail.setPreferredScrollableViewportSize(new Dimension(500, 500));
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
        KeluhanUtama.setDocument(new batasInput(500).getKata(KeluhanUtama));
        RPK.setDocument(new batasInput(100).getKata(RPK));
        PSK.setDocument(new batasInput(100).getKata(PSK));
        RBedah.setDocument(new batasInput(100).getKata(RBedah));
        Alergi.setDocument(new batasInput(25).getKata(Alergi));
        KeteranganKomplikasiKehamilan.setDocument(new batasInput(30).getKata(KeteranganKomplikasiKehamilan));
        UmurMinarche.setDocument(new batasInput(10).getKata(UmurMinarche));
        LamaMenstruasi.setDocument(new batasInput(10).getKata(LamaMenstruasi));
        BanyaknyaPembalut.setDocument(new batasInput(10).getKata(BanyaknyaPembalut));
        SiklusMenstruasi.setDocument(new batasInput(10).getKata(SiklusMenstruasi));
        KaliMenikah.setDocument(new batasInput(5).getKata(KaliMenikah));
        UsiaKawin1.setDocument(new batasInput(5).getKata(UsiaKawin1));
        UsiaKawin2.setDocument(new batasInput(5).getKata(UsiaKawin2));
        UsiaKawin3.setDocument(new batasInput(5).getKata(UsiaKawin3));
        G.setDocument(new batasInput(10).getKata(G));
        P.setDocument(new batasInput(10).getKata(P));
        A.setDocument(new batasInput(10).getKata(A));
        Hidup.setDocument(new batasInput(10).getKata(Hidup));
        UsiaKehamilan.setDocument(new batasInput(10).getKata(UsiaKehamilan));
        ANC.setDocument(new batasInput(5).getKata(ANC));
        ANCKe.setDocument(new batasInput(5).getKata(ANCKe));
        LamanyaKB.setDocument(new batasInput(10).getKata(LamanyaKB));
        KeteranganKomplikasiKB.setDocument(new batasInput(50).getKata(KeteranganKomplikasiKB));
        BerhentiKB.setDocument(new batasInput(20).getKata(BerhentiKB));
        AlasanBerhentiKB.setDocument(new batasInput(50).getKata(AlasanBerhentiKB));
        KebiasaanObatDiminum.setDocument(new batasInput(100).getKata(KebiasaanObatDiminum));
        KebiasaanJumlahRokok.setDocument(new batasInput(5).getKata(KebiasaanJumlahRokok));
        KebiasaanJumlahAlkohol.setDocument(new batasInput(5).getKata(KebiasaanJumlahAlkohol));
        KesadaranMental.setDocument(new batasInput(40).getKata(KesadaranMental));
        GCS.setDocument(new batasInput(10).getKata(GCS));
        TD.setDocument(new batasInput(8).getKata(TD));
        Nadi.setDocument(new batasInput(5).getKata(Nadi));
        RR.setDocument(new batasInput(5).getKata(RR));
        Suhu.setDocument(new batasInput(5).getKata(Suhu));
        SpO2.setDocument(new batasInput(5).getKata(SpO2));
        BB.setDocument(new batasInput(5).getKata(BB));
        TB.setDocument(new batasInput(5).getKata(TB));
        LILA.setDocument(new batasInput(5).getKata(LILA));
        TFU.setDocument(new batasInput(10).getKata(TFU));
        TBJ.setDocument(new batasInput(10).getKata(TBJ));
        Letak.setDocument(new batasInput(10).getKata(Letak));
        Presentasi.setDocument(new batasInput(10).getKata(Presentasi));
        Penurunan.setDocument(new batasInput(10).getKata(Penurunan));
        Kontraksi.setDocument(new batasInput(10).getKata(Kontraksi));
        Kekuatan.setDocument(new batasInput(10).getKata(Kekuatan));
        LamanyaKontraksi.setDocument(new batasInput(5).getKata(LamanyaKontraksi));
        DJJ.setDocument(new batasInput(5).getKata(DJJ));
        Portio.setDocument(new batasInput(10).getKata(Portio));
        PembukaanServiks.setDocument(new batasInput(5).getKata(PembukaanServiks));
        Ketuban.setDocument(new batasInput(10).getKata(Ketuban));
        Hodge.setDocument(new batasInput(10).getKata(Hodge));
        KeteranganInspekulo.setDocument(new batasInput(50).getKata(KeteranganInspekulo));
        KeteranganLakmus.setDocument(new batasInput(50).getKata(KeteranganLakmus));
        KeteranganCTG.setDocument(new batasInput(50).getKata(KeteranganCTG));
        KeteranganBerjalan.setDocument(new batasInput(50).getKata(KeteranganBerjalan));
        KeteranganEkstrimitasAtas.setDocument(new batasInput(50).getKata(KeteranganEkstrimitasAtas));
        KeteranganEkstrimitasBawah.setDocument(new batasInput(50).getKata(KeteranganEkstrimitasBawah));
        KeteranganKemampuanMenggenggam.setDocument(new batasInput(50).getKata(KeteranganKemampuanMenggenggam));
        KeteranganKemampuanKoordinasi.setDocument(new batasInput(50).getKata(KeteranganKemampuanKoordinasi));
        KeteranganAdakahPerilaku.setDocument(new batasInput(50).getKata(KeteranganAdakahPerilaku));
        KeteranganTinggalDengan.setDocument(new batasInput(50).getKata(KeteranganTinggalDengan));
        KeteranganNilaiKepercayaan.setDocument(new batasInput(50).getKata(KeteranganNilaiKepercayaan));
        KeteranganEdukasiPsikologis.setDocument(new batasInput(50).getKata(KeteranganEdukasiPsikologis));
        KetProvokes.setDocument(new batasInput(50).getKata(KetProvokes));
        KetQuality.setDocument(new batasInput(50).getKata(KetQuality));
        Lokasi.setDocument(new batasInput(50).getKata(Lokasi));
        Durasi.setDocument(new batasInput(5).getKata(Durasi));
        KetNyeri.setDocument(new batasInput(50).getKata(KetNyeri));
        KetPadaDokter.setDocument(new batasInput(10).getKata(KetPadaDokter));
        KeteranganDiagnosaKhususGizi.setDocument(new batasInput(50).getKata(KeteranganDiagnosaKhususGizi));
        KeteranganDiketahuiDietisen.setDocument(new batasInput(10).getKata(KeteranganDiketahuiDietisen));
        Rencana.setDocument(new batasInput(1000).getKata(Rencana));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

            TCariMasalah.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
                        KdPetugas
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    } else {
                        KdPetugas2
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPetugas2
                                .setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
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
        ChkAccor.setSelected(false);
        isMenu();

        try {
            TANGGALMUNDUR = koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR = "yes";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DlgRiwayatPersalinan = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        TanggalPersalinan = new widget.Tanggal();
        jLabel99 = new widget.Label();
        BtnKeluarKehamilan = new widget.Button();
        jLabel106 = new widget.Label();
        UsiaHamil = new widget.TextBox();
        BtnSimpanRiwayatKehamilan = new widget.Button();
        jLabel107 = new widget.Label();
        TempatPersalinan = new widget.TextBox();
        jLabel105 = new widget.Label();
        JenisPersalinan = new widget.TextBox();
        jLabel108 = new widget.Label();
        JK = new widget.ComboBox();
        jLabel109 = new widget.Label();
        Penolong = new widget.TextBox();
        jLabel110 = new widget.Label();
        BBPB = new widget.TextBox();
        jLabel111 = new widget.Label();
        Penyulit = new widget.TextBox();
        jLabel112 = new widget.Label();
        Keadaan = new widget.TextBox();
        TanggalRegistrasi = new widget.TextBox();
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
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel36 = new widget.Label();
        Anamnesis = new widget.ComboBox();
        TglAsuhan = new widget.Tanggal();
        jSeparator1 = new javax.swing.JSeparator();
        NmPetugas2 = new widget.TextBox();
        BtnPetugas2 = new widget.Button();
        KdPetugas2 = new widget.TextBox();
        label15 = new widget.Label();
        label16 = new widget.Label();
        KdDPJP = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        BtnDPJP = new widget.Button();
        TibadiRuang = new widget.ComboBox();
        jLabel37 = new widget.Label();
        CaraMasuk = new widget.ComboBox();
        jLabel38 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel39 = new widget.Label();
        Alergi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        KeluhanUtama = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        PSK = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        RPK = new widget.TextArea();
        jLabel32 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        RBedah = new widget.TextArea();
        jLabel40 = new widget.Label();
        KomplikasiKehamilan = new widget.ComboBox();
        KeteranganKomplikasiKehamilan = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel67 = new widget.Label();
        UmurMinarche = new widget.TextBox();
        jLabel69 = new widget.Label();
        LamaMenstruasi = new widget.TextBox();
        jLabel70 = new widget.Label();
        BanyaknyaPembalut = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel74 = new widget.Label();
        SiklusMenstruasi = new widget.TextBox();
        jLabel75 = new widget.Label();
        KetSiklusMenstruasi = new widget.ComboBox();
        jLabel76 = new widget.Label();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        jLabel82 = new widget.Label();
        DirasakanMenstruasi = new widget.ComboBox();
        StatusKawin1 = new widget.ComboBox();
        jLabel66 = new widget.Label();
        jLabel71 = new widget.Label();
        StatusMenikah = new widget.ComboBox();
        jLabel77 = new widget.Label();
        jLabel81 = new widget.Label();
        UsiaKawin1 = new widget.TextBox();
        jLabel83 = new widget.Label();
        jLabel84 = new widget.Label();
        UsiaKawin3 = new widget.TextBox();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        StatusKawin3 = new widget.ComboBox();
        jLabel87 = new widget.Label();
        UsiaKawin2 = new widget.TextBox();
        StatusKawin2 = new widget.ComboBox();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        KaliMenikah = new widget.TextBox();
        jLabel90 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel101 = new widget.Label();
        G = new widget.TextBox();
        jLabel102 = new widget.Label();
        P = new widget.TextBox();
        jLabel103 = new widget.Label();
        A = new widget.TextBox();
        jLabel104 = new widget.Label();
        Hidup = new widget.TextBox();
        Scroll6 = new widget.ScrollPane();
        tbRiwayatKehamilan = new widget.Table();
        BtnTambahMasalah = new widget.Button();
        BtnHapusRiwayatPersalinan = new widget.Button();
        jLabel73 = new widget.Label();
        jLabel92 = new widget.Label();
        HPHT = new widget.Tanggal();
        jLabel93 = new widget.Label();
        UsiaKehamilan = new widget.TextBox();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        TP = new widget.Tanggal();
        jLabel97 = new widget.Label();
        RiwayatImunisasi = new widget.ComboBox();
        jLabel98 = new widget.Label();
        ANC = new widget.TextBox();
        jLabel100 = new widget.Label();
        ANCKe = new widget.TextBox();
        jLabel113 = new widget.Label();
        RiwayatANC = new widget.ComboBox();
        jLabel114 = new widget.Label();
        KeluhanHamilMuda = new widget.ComboBox();
        jLabel115 = new widget.Label();
        KeluhanHamilTua = new widget.ComboBox();
        jLabel116 = new widget.Label();
        RiwayatGenekologi = new widget.ComboBox();
        jLabel117 = new widget.Label();
        RiwayatKB = new widget.ComboBox();
        jLabel118 = new widget.Label();
        LamanyaKB = new widget.TextBox();
        KomplikasiKB = new widget.ComboBox();
        jLabel119 = new widget.Label();
        KeteranganKomplikasiKB = new widget.TextBox();
        jLabel120 = new widget.Label();
        BerhentiKB = new widget.TextBox();
        jLabel121 = new widget.Label();
        AlasanBerhentiKB = new widget.TextBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        KebiasaanObat = new widget.ComboBox();
        KebiasaanObatDiminum = new widget.TextBox();
        jLabel124 = new widget.Label();
        KebiasaanMerokok = new widget.ComboBox();
        KebiasaanJumlahRokok = new widget.TextBox();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        KebiasaanAlkohol = new widget.ComboBox();
        KebiasaanJumlahAlkohol = new widget.TextBox();
        jLabel127 = new widget.Label();
        KebiasaanNarkoba = new widget.ComboBox();
        jLabel128 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel129 = new widget.Label();
        jLabel41 = new widget.Label();
        KesadaranMental = new widget.TextBox();
        KeteranganDJJ = new widget.ComboBox();
        jLabel35 = new widget.Label();
        DJJ = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel33 = new widget.Label();
        LamanyaKontraksi = new widget.TextBox();
        jLabel43 = new widget.Label();
        Kekuatan = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel44 = new widget.Label();
        Kontraksi = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel130 = new widget.Label();
        KeadaanMentalUmum = new widget.ComboBox();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel17 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel26 = new widget.Label();
        RR = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel24 = new widget.Label();
        SpO2 = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel15 = new widget.Label();
        TB = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        LILA = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        TFU = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        TBJ = new widget.TextBox();
        jLabel53 = new widget.Label();
        GD = new widget.TextBox();
        jLabel54 = new widget.Label();
        Letak = new widget.TextBox();
        jLabel55 = new widget.Label();
        Presentasi = new widget.TextBox();
        jLabel56 = new widget.Label();
        Penurunan = new widget.TextBox();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        Portio = new widget.TextBox();
        LabelServiks = new widget.Label();
        PembukaanServiks = new widget.TextBox();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        Ketuban = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        Hodge = new widget.TextBox();
        jLabel65 = new widget.Label();
        PemeriksaanPanggul = new widget.ComboBox();
        jLabel63 = new widget.Label();
        Inspekulo = new widget.ComboBox();
        KeteranganInspekulo = new widget.TextBox();
        jLabel91 = new widget.Label();
        CTG = new widget.ComboBox();
        KeteranganCTG = new widget.TextBox();
        jLabel132 = new widget.Label();
        Lakmus = new widget.ComboBox();
        KeteranganLakmus = new widget.TextBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel131 = new widget.Label();
        jLabel64 = new widget.Label();
        PemeriksaanKepala = new widget.ComboBox();
        jLabel133 = new widget.Label();
        PemeriksaanMuka = new widget.ComboBox();
        jLabel134 = new widget.Label();
        PemeriksaanMata = new widget.ComboBox();
        jLabel135 = new widget.Label();
        PemeriksaanHidung = new widget.ComboBox();
        jLabel136 = new widget.Label();
        PemeriksaanTelinga = new widget.ComboBox();
        PemeriksaanMulut = new widget.ComboBox();
        jLabel137 = new widget.Label();
        PemeriksaanLeher = new widget.ComboBox();
        jLabel138 = new widget.Label();
        PemeriksaanDada = new widget.ComboBox();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        PemeriksaanPerut = new widget.ComboBox();
        PemeriksaanGenitalia = new widget.ComboBox();
        jLabel141 = new widget.Label();
        PemeriksaanEkstrimitas = new widget.ComboBox();
        jLabel142 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel143 = new widget.Label();
        jLabel144 = new widget.Label();
        AktifitasSehari2 = new widget.ComboBox();
        Aktifitas = new widget.ComboBox();
        jLabel145 = new widget.Label();
        jLabel146 = new widget.Label();
        Berjalan = new widget.ComboBox();
        KeteranganBerjalan = new widget.TextBox();
        jLabel147 = new widget.Label();
        AlatAmbulasi = new widget.ComboBox();
        EkstrimitasAtas = new widget.ComboBox();
        jLabel148 = new widget.Label();
        KeteranganEkstrimitasAtas = new widget.TextBox();
        jLabel149 = new widget.Label();
        EkstrimitasBawah = new widget.ComboBox();
        KeteranganEkstrimitasBawah = new widget.TextBox();
        jLabel150 = new widget.Label();
        KemampuanMenggenggam = new widget.ComboBox();
        KeteranganKemampuanMenggenggam = new widget.TextBox();
        jLabel151 = new widget.Label();
        KemampuanKoordinasi = new widget.ComboBox();
        KeteranganKemampuanKoordinasi = new widget.TextBox();
        jLabel152 = new widget.Label();
        KesimpulanGangguanFungsi = new widget.ComboBox();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel153 = new widget.Label();
        jLabel154 = new widget.Label();
        KondisiPsikologis = new widget.ComboBox();
        jLabel155 = new widget.Label();
        AdakahPerilaku = new widget.ComboBox();
        KeteranganAdakahPerilaku = new widget.TextBox();
        jLabel156 = new widget.Label();
        GangguanJiwa = new widget.ComboBox();
        jLabel157 = new widget.Label();
        HubunganAnggotaKeluarga = new widget.ComboBox();
        jLabel158 = new widget.Label();
        Bahasa = new widget.TextBox();
        jLabel159 = new widget.Label();
        TinggalDengan = new widget.ComboBox();
        KeteranganTinggalDengan = new widget.TextBox();
        jLabel160 = new widget.Label();
        PekerjaanPasien = new widget.TextBox();
        jLabel161 = new widget.Label();
        CaraBayar = new widget.TextBox();
        jLabel162 = new widget.Label();
        NilaiKepercayaan = new widget.ComboBox();
        KeteranganNilaiKepercayaan = new widget.TextBox();
        jLabel163 = new widget.Label();
        Agama = new widget.TextBox();
        PendidikanPasien = new widget.TextBox();
        jLabel164 = new widget.Label();
        jLabel165 = new widget.Label();
        PendidikanPJ = new widget.ComboBox();
        jLabel166 = new widget.Label();
        EdukasiPsikolgis = new widget.ComboBox();
        KeteranganEdukasiPsikologis = new widget.TextBox();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel167 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jSeparator9 = new javax.swing.JSeparator();
        Nyeri = new widget.ComboBox();
        jLabel168 = new widget.Label();
        Provokes = new widget.ComboBox();
        KetProvokes = new widget.TextBox();
        jLabel169 = new widget.Label();
        Quality = new widget.ComboBox();
        KetQuality = new widget.TextBox();
        jLabel170 = new widget.Label();
        jLabel171 = new widget.Label();
        Lokasi = new widget.TextBox();
        jLabel172 = new widget.Label();
        Menyebar = new widget.ComboBox();
        jLabel173 = new widget.Label();
        jLabel174 = new widget.Label();
        SkalaNyeri = new widget.ComboBox();
        jLabel175 = new widget.Label();
        Durasi = new widget.TextBox();
        jLabel176 = new widget.Label();
        jLabel177 = new widget.Label();
        NyeriHilang = new widget.ComboBox();
        KetNyeri = new widget.TextBox();
        jLabel178 = new widget.Label();
        PadaDokter = new widget.ComboBox();
        jLabel179 = new widget.Label();
        KetPadaDokter = new widget.TextBox();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel180 = new widget.Label();
        jLabel181 = new widget.Label();
        SkalaResiko1 = new widget.ComboBox();
        jLabel182 = new widget.Label();
        NilaiResiko1 = new widget.TextBox();
        jLabel183 = new widget.Label();
        jLabel184 = new widget.Label();
        jLabel185 = new widget.Label();
        SkalaResiko2 = new widget.ComboBox();
        NilaiResiko2 = new widget.TextBox();
        jLabel186 = new widget.Label();
        jLabel187 = new widget.Label();
        jLabel188 = new widget.Label();
        SkalaResiko3 = new widget.ComboBox();
        jLabel189 = new widget.Label();
        NilaiResiko3 = new widget.TextBox();
        jLabel190 = new widget.Label();
        jLabel191 = new widget.Label();
        SkalaResiko4 = new widget.ComboBox();
        jLabel192 = new widget.Label();
        NilaiResiko4 = new widget.TextBox();
        jLabel193 = new widget.Label();
        jLabel194 = new widget.Label();
        SkalaResiko5 = new widget.ComboBox();
        jLabel195 = new widget.Label();
        NilaiResiko5 = new widget.TextBox();
        jLabel196 = new widget.Label();
        jLabel197 = new widget.Label();
        SkalaResiko6 = new widget.ComboBox();
        jLabel198 = new widget.Label();
        NilaiResiko6 = new widget.TextBox();
        jLabel199 = new widget.Label();
        NilaiResikoTotal = new widget.TextBox();
        TingkatResiko = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel201 = new widget.Label();
        jLabel202 = new widget.Label();
        jLabel203 = new widget.Label();
        NilaiGizi1 = new widget.TextBox();
        SkalaGizi1 = new widget.ComboBox();
        jLabel204 = new widget.Label();
        SkalaGizi2 = new widget.ComboBox();
        jLabel205 = new widget.Label();
        NilaiGizi2 = new widget.TextBox();
        jLabel206 = new widget.Label();
        NilaiGiziTotal = new widget.TextBox();
        jLabel207 = new widget.Label();
        DiketahuiDietisen = new widget.ComboBox();
        jLabel208 = new widget.Label();
        KeteranganDiketahuiDietisen = new widget.TextBox();
        jLabel209 = new widget.Label();
        DiagnosaKhususGizi = new widget.ComboBox();
        KeteranganDiagnosaKhususGizi = new widget.TextBox();
        jSeparator12 = new javax.swing.JSeparator();
        TabMasalahKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa3 = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbMasalahKeperawatanKebidanan = new widget.Table();
        scrollPane7 = new widget.ScrollPane();
        Masalah = new widget.TextArea();
        TabRencanaKeperawatan = new javax.swing.JTabbedPane();
        panelBiasa1 = new widget.PanelBiasa();
        Scroll8 = new widget.ScrollPane();
        tbRencanaKeperawatanKebidanan = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        label12 = new widget.Label();
        TCariMasalah = new widget.TextBox();
        BtnCariMasalah = new widget.Button();
        BtnAllMasalah = new widget.Button();
        BtnTambahMasalah1 = new widget.Button();
        label13 = new widget.Label();
        TCariRencana = new widget.TextBox();
        BtnCariRencana = new widget.Button();
        BtnAllRencana = new widget.Button();
        BtnTambahRencana = new widget.Button();
        jLabel200 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        RPS = new widget.TextArea();
        jLabel14 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        RObat = new widget.TextArea();
        jLabel210 = new widget.Label();
        jLabel211 = new widget.Label();
        jLabel212 = new widget.Label();
        jLabel213 = new widget.Label();
        jLabel214 = new widget.Label();
        Kriteria1 = new widget.ComboBox();
        Kriteria2 = new widget.ComboBox();
        Kriteria3 = new widget.ComboBox();
        Kriteria4 = new widget.ComboBox();
        jLabel215 = new widget.Label();
        pilihan1 = new javax.swing.JCheckBox();
        pilihan2 = new javax.swing.JCheckBox();
        pilihan3 = new javax.swing.JCheckBox();
        pilihan4 = new javax.swing.JCheckBox();
        pilihan5 = new javax.swing.JCheckBox();
        pilihan6 = new javax.swing.JCheckBox();
        pilihan7 = new javax.swing.JCheckBox();
        pilihan8 = new javax.swing.JCheckBox();
        jLabel289 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel297 = new widget.Label();
        Wajib = new widget.ComboBox();
        KetHalanganLain = new widget.TextBox();
        jLabel216 = new widget.Label();
        Toharah = new widget.ComboBox();
        jLabel298 = new widget.Label();
        jLabel217 = new widget.Label();
        Sholat = new widget.ComboBox();
        jLabel299 = new widget.Label();
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
        tbRiwayatKehamilan1 = new widget.Table();
        Scroll9 = new widget.ScrollPane();
        tbMasalahDetail = new widget.Table();
        Scroll10 = new widget.ScrollPane();
        tbRencanaDetail = new widget.Table();
        scrollPane8 = new widget.ScrollPane();
        DetailMasalah = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        DetailRencana = new widget.TextArea();

        DlgRiwayatPersalinan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRiwayatPersalinan.setName("DlgRiwayatPersalinan"); // NOI18N
        DlgRiwayatPersalinan.setUndecorated(true);
        DlgRiwayatPersalinan.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Riwayat Persalinan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        TanggalPersalinan.setForeground(new java.awt.Color(50, 70, 50));
        TanggalPersalinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
        TanggalPersalinan.setDisplayFormat("dd-MM-yyyy");
        TanggalPersalinan.setName("TanggalPersalinan"); // NOI18N
        TanggalPersalinan.setOpaque(false);
        panelBiasa2.add(TanggalPersalinan);
        TanggalPersalinan.setBounds(530, 10, 95, 23);

        jLabel99.setText("Tempat Persalinan :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelBiasa2.add(jLabel99);
        jLabel99.setBounds(0, 10, 110, 23);

        BtnKeluarKehamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluarKehamilan.setMnemonic('U');
        BtnKeluarKehamilan.setText("Tutup");
        BtnKeluarKehamilan.setToolTipText("Alt+U");
        BtnKeluarKehamilan.setName("BtnKeluarKehamilan"); // NOI18N
        BtnKeluarKehamilan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarKehamilanActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluarKehamilan);
        BtnKeluarKehamilan.setBounds(530, 130, 100, 30);

        jLabel106.setText("Usia Hamil :");
        jLabel106.setName("jLabel106"); // NOI18N
        panelBiasa2.add(jLabel106);
        jLabel106.setBounds(430, 40, 96, 23);

        UsiaHamil.setHighlighter(null);
        UsiaHamil.setName("UsiaHamil"); // NOI18N
        UsiaHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaHamilKeyPressed(evt);
            }
        });
        panelBiasa2.add(UsiaHamil);
        UsiaHamil.setBounds(530, 40, 95, 23);

        BtnSimpanRiwayatKehamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanRiwayatKehamilan.setMnemonic('S');
        BtnSimpanRiwayatKehamilan.setText("Simpan");
        BtnSimpanRiwayatKehamilan.setToolTipText("Alt+S");
        BtnSimpanRiwayatKehamilan.setName("BtnSimpanRiwayatKehamilan"); // NOI18N
        BtnSimpanRiwayatKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanRiwayatKehamilanActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSimpanRiwayatKehamilan);
        BtnSimpanRiwayatKehamilan.setBounds(420, 130, 100, 30);

        jLabel107.setText("Tanggal/Tahun :");
        jLabel107.setName("jLabel107"); // NOI18N
        panelBiasa2.add(jLabel107);
        jLabel107.setBounds(430, 10, 96, 23);

        TempatPersalinan.setHighlighter(null);
        TempatPersalinan.setName("TempatPersalinan"); // NOI18N
        TempatPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TempatPersalinanKeyPressed(evt);
            }
        });
        panelBiasa2.add(TempatPersalinan);
        TempatPersalinan.setBounds(114, 10, 290, 23);

        jLabel105.setText("Jenis Persalinan :");
        jLabel105.setName("jLabel105"); // NOI18N
        panelBiasa2.add(jLabel105);
        jLabel105.setBounds(0, 40, 110, 23);

        JenisPersalinan.setHighlighter(null);
        JenisPersalinan.setName("JenisPersalinan"); // NOI18N
        JenisPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPersalinanKeyPressed(evt);
            }
        });
        panelBiasa2.add(JenisPersalinan);
        JenisPersalinan.setBounds(114, 40, 290, 23);

        jLabel108.setText("Jenis Kelamin :");
        jLabel108.setName("jLabel108"); // NOI18N
        panelBiasa2.add(jLabel108);
        jLabel108.setBounds(430, 70, 96, 23);

        JK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        JK.setName("JK"); // NOI18N
        JK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKKeyPressed(evt);
            }
        });
        panelBiasa2.add(JK);
        JK.setBounds(530, 70, 95, 23);

        jLabel109.setText("Penolong :");
        jLabel109.setName("jLabel109"); // NOI18N
        panelBiasa2.add(jLabel109);
        jLabel109.setBounds(0, 70, 110, 23);

        Penolong.setHighlighter(null);
        Penolong.setName("Penolong"); // NOI18N
        Penolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenolongKeyPressed(evt);
            }
        });
        panelBiasa2.add(Penolong);
        Penolong.setBounds(114, 70, 290, 23);

        jLabel110.setText("BB/PB :");
        jLabel110.setName("jLabel110"); // NOI18N
        panelBiasa2.add(jLabel110);
        jLabel110.setBounds(430, 100, 96, 23);

        BBPB.setHighlighter(null);
        BBPB.setName("BBPB"); // NOI18N
        BBPB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBPBKeyPressed(evt);
            }
        });
        panelBiasa2.add(BBPB);
        BBPB.setBounds(530, 100, 95, 23);

        jLabel111.setText("Penyulit :");
        jLabel111.setName("jLabel111"); // NOI18N
        panelBiasa2.add(jLabel111);
        jLabel111.setBounds(0, 100, 110, 23);

        Penyulit.setHighlighter(null);
        Penyulit.setName("Penyulit"); // NOI18N
        Penyulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyulitKeyPressed(evt);
            }
        });
        panelBiasa2.add(Penyulit);
        Penyulit.setBounds(114, 100, 290, 23);

        jLabel112.setText("Keadaan :");
        jLabel112.setName("jLabel112"); // NOI18N
        panelBiasa2.add(jLabel112);
        jLabel112.setBounds(0, 130, 110, 23);

        Keadaan.setHighlighter(null);
        Keadaan.setName("Keadaan"); // NOI18N
        Keadaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanKeyPressed(evt);
            }
        });
        panelBiasa2.add(Keadaan);
        Keadaan.setBounds(114, 130, 290, 23);

        internalFrame4.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgRiwayatPersalinan.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Keperawatan Rawat Inap Kebidanan & Kandungan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        FormInput.setPreferredSize(new java.awt.Dimension(954, 2643));
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

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(640, 70, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

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
        Anamnesis.setBounds(74, 100, 150, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024 10:05:49" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(714, 70, 140, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 810, 880, 1);

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
        KdDPJP.setBounds(74, 70, 130, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setName("NmDPJP"); // NOI18N
        NmDPJP.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(206, 70, 360, 23);

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
        BtnDPJP.setBounds(568, 70, 28, 23);

        TibadiRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jalan Tanpa Bantuan", "Kursi Roda", "Brankar" }));
        TibadiRuang.setName("TibadiRuang"); // NOI18N
        TibadiRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TibadiRuangKeyPressed(evt);
            }
        });
        FormInput.add(TibadiRuang);
        TibadiRuang.setBounds(391, 100, 205, 23);

        jLabel37.setText("Tiba di Ruang Rawat :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(257, 100, 130, 23);

        CaraMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Poli", "IGD", "VK", "OK", "Lain-lain" }));
        CaraMasuk.setName("CaraMasuk"); // NOI18N
        CaraMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraMasukKeyPressed(evt);
            }
        });
        FormInput.add(CaraMasuk);
        CaraMasuk.setBounds(743, 100, 110, 23);

        jLabel38.setText("Cara Masuk :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(649, 100, 90, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("I. RIWAYAT KESEHATAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 130, 180, 23);

        jLabel9.setText("Riwayat Pembedahan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(440, 200, 150, 23);

        jLabel39.setText("Riwayat Alergi :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 300, 122, 23);

        Alergi.setFocusTraversalPolicyProvider(true);
        Alergi.setName("Alergi"); // NOI18N
        Alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlergiKeyPressed(evt);
            }
        });
        FormInput.add(Alergi);
        Alergi.setBounds(120, 300, 250, 23);

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
        scrollPane1.setBounds(129, 150, 310, 43);

        jLabel30.setText("Keluhan Utama :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 150, 125, 20);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        PSK.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PSK.setColumns(20);
        PSK.setRows(5);
        PSK.setName("PSK"); // NOI18N
        PSK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSKKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(PSK);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(189, 200, 250, 43);

        jLabel31.setText("Penyakit Selama Kehamilan :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 200, 185, 23);

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
        scrollPane3.setBounds(594, 150, 260, 43);

        jLabel32.setText("Riwayat Penyakit Keluarga :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(440, 150, 150, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        RBedah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RBedah.setColumns(20);
        RBedah.setRows(5);
        RBedah.setName("RBedah"); // NOI18N
        RBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RBedahKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(RBedah);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(594, 200, 260, 43);

        jLabel40.setText("Komplikasi Kehamilan Sebelumnya :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(390, 300, 200, 23);

        KomplikasiKehamilan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "HAP", "HPP", "PEB/PER/Eklamsi", "Lain-lain" }));
        KomplikasiKehamilan.setName("KomplikasiKehamilan"); // NOI18N
        KomplikasiKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(KomplikasiKehamilan);
        KomplikasiKehamilan.setBounds(590, 300, 135, 23);

        KeteranganKomplikasiKehamilan.setHighlighter(null);
        KeteranganKomplikasiKehamilan.setName("KeteranganKomplikasiKehamilan"); // NOI18N
        KeteranganKomplikasiKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKomplikasiKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKomplikasiKehamilan);
        KeteranganKomplikasiKehamilan.setBounds(730, 300, 122, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("tahun");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(210, 340, 40, 23);

        jLabel67.setText("Umur Menarche :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 340, 160, 23);

        UmurMinarche.setFocusTraversalPolicyProvider(true);
        UmurMinarche.setName("UmurMinarche"); // NOI18N
        UmurMinarche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurMinarcheKeyPressed(evt);
            }
        });
        FormInput.add(UmurMinarche);
        UmurMinarche.setBounds(170, 340, 40, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText(")");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(850, 340, 28, 23);

        LamaMenstruasi.setFocusTraversalPolicyProvider(true);
        LamaMenstruasi.setName("LamaMenstruasi"); // NOI18N
        LamaMenstruasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaMenstruasiKeyPressed(evt);
            }
        });
        FormInput.add(LamaMenstruasi);
        LamaMenstruasi.setBounds(330, 340, 40, 23);

        jLabel70.setText("(");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(710, 340, 20, 23);

        BanyaknyaPembalut.setFocusTraversalPolicyProvider(true);
        BanyaknyaPembalut.setName("BanyaknyaPembalut"); // NOI18N
        BanyaknyaPembalut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BanyaknyaPembalutKeyPressed(evt);
            }
        });
        FormInput.add(BanyaknyaPembalut);
        BanyaknyaPembalut.setBounds(490, 340, 40, 23);

        jLabel72.setText("Dirasakan Saat Menstruasi :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 370, 213, 23);

        jLabel74.setText("Siklus :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(600, 340, 50, 23);

        SiklusMenstruasi.setFocusTraversalPolicyProvider(true);
        SiklusMenstruasi.setName("SiklusMenstruasi"); // NOI18N
        SiklusMenstruasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SiklusMenstruasiKeyPressed(evt);
            }
        });
        FormInput.add(SiklusMenstruasi);
        SiklusMenstruasi.setBounds(650, 340, 40, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("hari,");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(690, 340, 30, 23);

        KetSiklusMenstruasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        KetSiklusMenstruasi.setName("KetSiklusMenstruasi"); // NOI18N
        KetSiklusMenstruasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSiklusMenstruasiKeyPressed(evt);
            }
        });
        FormInput.add(KetSiklusMenstruasi);
        KetSiklusMenstruasi.setBounds(730, 340, 120, 23);

        jLabel76.setText("Riwayat Menstruasi :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 320, 146, 23);

        jLabel78.setText("Banyaknya :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(420, 340, 66, 23);

        jLabel79.setText("Lamanya :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(270, 340, 55, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("hari");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(370, 340, 30, 23);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("pembalut");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(540, 340, 60, 23);

        DirasakanMenstruasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Dismenorhea", "Spotting", "Menorhagia", "PMS" }));
        DirasakanMenstruasi.setName("DirasakanMenstruasi"); // NOI18N
        DirasakanMenstruasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DirasakanMenstruasiKeyPressed(evt);
            }
        });
        FormInput.add(DirasakanMenstruasi);
        DirasakanMenstruasi.setBounds(220, 370, 180, 23);

        StatusKawin1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Masih Menikah", "Cerai", "Meninggal" }));
        StatusKawin1.setName("StatusKawin1"); // NOI18N
        StatusKawin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKawin1KeyPressed(evt);
            }
        });
        FormInput.add(StatusKawin1);
        StatusKawin1.setBounds(730, 420, 125, 23);

        jLabel66.setText("Riwayat Perkawinan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 400, 149, 23);

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("tahun,");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(650, 420, 45, 23);

        StatusMenikah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menikah", "Tidak / Belum Menikah" }));
        StatusMenikah.setName("StatusMenikah"); // NOI18N
        StatusMenikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusMenikahKeyPressed(evt);
            }
        });
        FormInput.add(StatusMenikah);
        StatusMenikah.setBounds(180, 420, 175, 23);

        jLabel77.setText("Status Menikah :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 420, 173, 23);

        jLabel81.setText("Status :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(680, 420, 50, 23);

        UsiaKawin1.setFocusTraversalPolicyProvider(true);
        UsiaKawin1.setName("UsiaKawin1"); // NOI18N
        UsiaKawin1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaKawin1KeyPressed(evt);
            }
        });
        FormInput.add(UsiaKawin1);
        UsiaKawin1.setBounds(610, 420, 40, 23);

        jLabel83.setText("Usia Perkawinan Ke 1 :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(480, 420, 130, 23);

        jLabel84.setText("Usia Perkawinan Ke 3 :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(480, 450, 130, 23);

        UsiaKawin3.setFocusTraversalPolicyProvider(true);
        UsiaKawin3.setName("UsiaKawin3"); // NOI18N
        UsiaKawin3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaKawin3KeyPressed(evt);
            }
        });
        FormInput.add(UsiaKawin3);
        UsiaKawin3.setBounds(610, 450, 40, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("tahun,");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(650, 450, 45, 23);

        jLabel86.setText("Status :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(680, 450, 50, 23);

        StatusKawin3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Masih Menikah", "Cerai", "Meninggal" }));
        StatusKawin3.setName("StatusKawin3"); // NOI18N
        StatusKawin3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKawin3KeyPressed(evt);
            }
        });
        FormInput.add(StatusKawin3);
        StatusKawin3.setBounds(730, 450, 125, 23);

        jLabel87.setText("Usia Perkawinan 2 :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 450, 173, 23);

        UsiaKawin2.setFocusTraversalPolicyProvider(true);
        UsiaKawin2.setName("UsiaKawin2"); // NOI18N
        UsiaKawin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaKawin2KeyPressed(evt);
            }
        });
        FormInput.add(UsiaKawin2);
        UsiaKawin2.setBounds(180, 450, 40, 23);

        StatusKawin2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Masih Menikah", "Cerai", "Meninggal" }));
        StatusKawin2.setName("StatusKawin2"); // NOI18N
        StatusKawin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKawin2KeyPressed(evt);
            }
        });
        FormInput.add(StatusKawin2);
        StatusKawin2.setBounds(300, 450, 125, 23);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("tahun,");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(220, 450, 50, 23);

        jLabel89.setText("Status :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(250, 450, 50, 23);

        KaliMenikah.setFocusTraversalPolicyProvider(true);
        KaliMenikah.setName("KaliMenikah"); // NOI18N
        KaliMenikah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KaliMenikahKeyPressed(evt);
            }
        });
        FormInput.add(KaliMenikah);
        KaliMenikah.setBounds(360, 420, 40, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("kali");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(400, 420, 30, 23);

        jLabel68.setText("Riwayat Persalinan & Nifas :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 480, 182, 23);

        jLabel101.setText("G :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 500, 91, 23);

        G.setFocusTraversalPolicyProvider(true);
        G.setName("G"); // NOI18N
        G.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GKeyPressed(evt);
            }
        });
        FormInput.add(G);
        G.setBounds(100, 500, 50, 23);

        jLabel102.setText("P :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(150, 500, 30, 23);

        P.setFocusTraversalPolicyProvider(true);
        P.setName("P"); // NOI18N
        P.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PKeyPressed(evt);
            }
        });
        FormInput.add(P);
        P.setBounds(180, 500, 50, 23);

        jLabel103.setText("A :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(230, 500, 30, 23);

        A.setFocusTraversalPolicyProvider(true);
        A.setName("A"); // NOI18N
        A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AKeyPressed(evt);
            }
        });
        FormInput.add(A);
        A.setBounds(270, 500, 50, 23);

        jLabel104.setText("Anak Yang Hidup :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(330, 500, 110, 23);

        Hidup.setFocusTraversalPolicyProvider(true);
        Hidup.setName("Hidup"); // NOI18N
        Hidup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HidupKeyPressed(evt);
            }
        });
        FormInput.add(Hidup);
        Hidup.setBounds(450, 500, 60, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayatKehamilan.setName("tbRiwayatKehamilan"); // NOI18N
        Scroll6.setViewportView(tbRiwayatKehamilan);

        FormInput.add(Scroll6);
        Scroll6.setBounds(110, 530, 744, 93);

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
        BtnTambahMasalah.setBounds(80, 530, 28, 23);

        BtnHapusRiwayatPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusRiwayatPersalinan.setMnemonic('3');
        BtnHapusRiwayatPersalinan.setToolTipText("Alt+3");
        BtnHapusRiwayatPersalinan.setName("BtnHapusRiwayatPersalinan"); // NOI18N
        BtnHapusRiwayatPersalinan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusRiwayatPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusRiwayatPersalinanActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusRiwayatPersalinan);
        BtnHapusRiwayatPersalinan.setBounds(80, 560, 28, 23);

        jLabel73.setText("Riwayat Hamil Sekarang :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 630, 169, 23);

        jLabel92.setText("HPHT :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 650, 110, 23);

        HPHT.setForeground(new java.awt.Color(50, 70, 50));
        HPHT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
        HPHT.setDisplayFormat("dd-MM-yyyy");
        HPHT.setName("HPHT"); // NOI18N
        HPHT.setOpaque(false);
        HPHT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HPHTKeyPressed(evt);
            }
        });
        FormInput.add(HPHT);
        HPHT.setBounds(120, 650, 90, 23);

        jLabel93.setText("Usia Hamil :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(250, 650, 70, 23);

        UsiaKehamilan.setFocusTraversalPolicyProvider(true);
        UsiaKehamilan.setName("UsiaKehamilan"); // NOI18N
        UsiaKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsiaKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(UsiaKehamilan);
        UsiaKehamilan.setBounds(320, 650, 60, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("bln/mgg");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(380, 650, 60, 23);

        jLabel96.setText("TP :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(450, 650, 40, 23);

        TP.setForeground(new java.awt.Color(50, 70, 50));
        TP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-06-2024" }));
        TP.setDisplayFormat("dd-MM-yyyy");
        TP.setName("TP"); // NOI18N
        TP.setOpaque(false);
        TP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPKeyPressed(evt);
            }
        });
        FormInput.add(TP);
        TP.setBounds(500, 650, 90, 23);

        jLabel97.setText("Riwayat Imunisasi :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(620, 650, 110, 23);

        RiwayatImunisasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Pernah", "T I", "TT II", "TT III", "TT IV" }));
        RiwayatImunisasi.setName("RiwayatImunisasi"); // NOI18N
        RiwayatImunisasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatImunisasiKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatImunisasi);
        RiwayatImunisasi.setBounds(740, 650, 120, 23);

        jLabel98.setText("ANC :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 680, 110, 23);

        ANC.setFocusTraversalPolicyProvider(true);
        ANC.setName("ANC"); // NOI18N
        ANC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ANCKeyPressed(evt);
            }
        });
        FormInput.add(ANC);
        ANC.setBounds(110, 680, 35, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("X");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(150, 680, 20, 23);

        ANCKe.setFocusTraversalPolicyProvider(true);
        ANCKe.setName("ANCKe"); // NOI18N
        ANCKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ANCKeKeyPressed(evt);
            }
        });
        FormInput.add(ANCKe);
        ANCKe.setBounds(190, 680, 35, 23);

        jLabel113.setText("Ke :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(160, 680, 30, 23);

        RiwayatANC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        RiwayatANC.setName("RiwayatANC"); // NOI18N
        RiwayatANC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatANCKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatANC);
        RiwayatANC.setBounds(230, 680, 115, 23);

        jLabel114.setText("Keluhan Hamil Muda :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(390, 680, 112, 23);

        KeluhanHamilMuda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Mual", "Muntah", "Perdarahan", "Lain–lain" }));
        KeluhanHamilMuda.setName("KeluhanHamilMuda"); // NOI18N
        KeluhanHamilMuda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanHamilMudaKeyPressed(evt);
            }
        });
        FormInput.add(KeluhanHamilMuda);
        KeluhanHamilMuda.setBounds(500, 680, 106, 23);

        jLabel115.setText("Keluhan Hamil Tua :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(630, 680, 112, 23);

        KeluhanHamilTua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Mual", "Muntah", "Perdarahan", "Lain–lain" }));
        KeluhanHamilTua.setName("KeluhanHamilTua"); // NOI18N
        KeluhanHamilTua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanHamilTuaKeyPressed(evt);
            }
        });
        FormInput.add(KeluhanHamilTua);
        KeluhanHamilTua.setBounds(750, 680, 106, 23);

        jLabel116.setText("Riwayat Ginekologi :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(560, 740, 144, 23);

        RiwayatGenekologi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Infertilitas", "Infeksi Virus", "PMS", "Cervisitis Kronis", "Endometriosis", "Mioma", "Polip Cervix", "Kanker Kandungan", "Operasi Kandungan" }));
        RiwayatGenekologi.setName("RiwayatGenekologi"); // NOI18N
        RiwayatGenekologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatGenekologiKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatGenekologi);
        RiwayatGenekologi.setBounds(710, 740, 145, 23);

        jLabel117.setText("Riwayat Keluarga Berencana :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 710, 190, 23);

        RiwayatKB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum Pernah", "Suntik", "Pil", "AKDR", "MOW" }));
        RiwayatKB.setName("RiwayatKB"); // NOI18N
        RiwayatKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatKBKeyPressed(evt);
            }
        });
        FormInput.add(RiwayatKB);
        RiwayatKB.setBounds(200, 710, 117, 23);

        jLabel118.setText("Lamanya :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(320, 710, 60, 23);

        LamanyaKB.setFocusTraversalPolicyProvider(true);
        LamanyaKB.setName("LamanyaKB"); // NOI18N
        LamanyaKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamanyaKBKeyPressed(evt);
            }
        });
        FormInput.add(LamanyaKB);
        LamanyaKB.setBounds(380, 710, 50, 23);

        KomplikasiKB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        KomplikasiKB.setName("KomplikasiKB"); // NOI18N
        KomplikasiKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKBKeyPressed(evt);
            }
        });
        FormInput.add(KomplikasiKB);
        KomplikasiKB.setBounds(510, 710, 100, 23);

        jLabel119.setText("Komplikasi :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(440, 710, 67, 23);

        KeteranganKomplikasiKB.setFocusTraversalPolicyProvider(true);
        KeteranganKomplikasiKB.setName("KeteranganKomplikasiKB"); // NOI18N
        KeteranganKomplikasiKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKomplikasiKBKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKomplikasiKB);
        KeteranganKomplikasiKB.setBounds(620, 710, 239, 23);

        jLabel120.setText("Kapan Berhenti KB :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 740, 173, 23);

        BerhentiKB.setFocusTraversalPolicyProvider(true);
        BerhentiKB.setName("BerhentiKB"); // NOI18N
        BerhentiKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerhentiKBKeyPressed(evt);
            }
        });
        FormInput.add(BerhentiKB);
        BerhentiKB.setBounds(180, 740, 100, 23);

        jLabel121.setText("Alasan :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(280, 740, 50, 23);

        AlasanBerhentiKB.setFocusTraversalPolicyProvider(true);
        AlasanBerhentiKB.setName("AlasanBerhentiKB"); // NOI18N
        AlasanBerhentiKB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlasanBerhentiKBKeyPressed(evt);
            }
        });
        FormInput.add(AlasanBerhentiKB);
        AlasanBerhentiKB.setBounds(330, 740, 210, 23);

        jLabel122.setText("Riwayat Kebiasaan :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 760, 143, 23);

        jLabel123.setText("Obat/Vitamin :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 780, 150, 23);

        KebiasaanObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Obat Obatan", "Vitamin", "Jamu Jamuan" }));
        KebiasaanObat.setName("KebiasaanObat"); // NOI18N
        KebiasaanObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanObatKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanObat);
        KebiasaanObat.setBounds(160, 780, 155, 23);

        KebiasaanObatDiminum.setFocusTraversalPolicyProvider(true);
        KebiasaanObatDiminum.setName("KebiasaanObatDiminum"); // NOI18N
        KebiasaanObatDiminum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanObatDiminumKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanObatDiminum);
        KebiasaanObatDiminum.setBounds(315, 780, 542, 23);

        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("batang/hari");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(290, 820, 70, 23);

        KebiasaanMerokok.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanMerokok.setName("KebiasaanMerokok"); // NOI18N
        KebiasaanMerokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanMerokokKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanMerokok);
        KebiasaanMerokok.setBounds(160, 820, 80, 23);

        KebiasaanJumlahRokok.setFocusTraversalPolicyProvider(true);
        KebiasaanJumlahRokok.setName("KebiasaanJumlahRokok"); // NOI18N
        KebiasaanJumlahRokok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanJumlahRokokKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanJumlahRokok);
        KebiasaanJumlahRokok.setBounds(240, 820, 45, 23);

        jLabel125.setText("Merokok :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 820, 150, 23);

        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("gelas/hari");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(570, 820, 70, 23);

        KebiasaanAlkohol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanAlkohol.setName("KebiasaanAlkohol"); // NOI18N
        KebiasaanAlkohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanAlkoholKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanAlkohol);
        KebiasaanAlkohol.setBounds(440, 820, 80, 23);

        KebiasaanJumlahAlkohol.setFocusTraversalPolicyProvider(true);
        KebiasaanJumlahAlkohol.setName("KebiasaanJumlahAlkohol"); // NOI18N
        KebiasaanJumlahAlkohol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanJumlahAlkoholKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanJumlahAlkohol);
        KebiasaanJumlahAlkohol.setBounds(520, 820, 45, 23);

        jLabel127.setText("Alkohol :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(370, 820, 65, 23);

        KebiasaanNarkoba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        KebiasaanNarkoba.setName("KebiasaanNarkoba"); // NOI18N
        KebiasaanNarkoba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KebiasaanNarkobaKeyPressed(evt);
            }
        });
        FormInput.add(KebiasaanNarkoba);
        KebiasaanNarkoba.setBounds(780, 820, 80, 23);

        jLabel128.setText("Obat Tidur/Narkoba :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(650, 820, 120, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 130, 880, 1);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("II. PEMERIKSAAN KEBIDANAN");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(10, 850, 180, 23);

        jLabel41.setText("Kesadaran Mental :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 870, 138, 23);

        KesadaranMental.setFocusTraversalPolicyProvider(true);
        KesadaranMental.setName("KesadaranMental"); // NOI18N
        KesadaranMental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranMentalKeyPressed(evt);
            }
        });
        FormInput.add(KesadaranMental);
        KesadaranMental.setBounds(140, 870, 100, 23);

        KeteranganDJJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        KeteranganDJJ.setName("KeteranganDJJ"); // NOI18N
        KeteranganDJJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDJJKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDJJ);
        KeteranganDJJ.setBounds(740, 960, 115, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("/mnt");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(710, 960, 30, 23);

        DJJ.setFocusTraversalPolicyProvider(true);
        DJJ.setName("DJJ"); // NOI18N
        DJJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DJJKeyPressed(evt);
            }
        });
        FormInput.add(DJJ);
        DJJ.setBounds(650, 960, 60, 23);

        jLabel45.setText("Gerak janin x/30 menit, DJJ :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(500, 960, 150, 23);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("detik");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(470, 960, 30, 23);

        LamanyaKontraksi.setFocusTraversalPolicyProvider(true);
        LamanyaKontraksi.setName("LamanyaKontraksi"); // NOI18N
        LamanyaKontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamanyaKontraksiKeyPressed(evt);
            }
        });
        FormInput.add(LamanyaKontraksi);
        LamanyaKontraksi.setBounds(410, 960, 60, 23);

        jLabel43.setText("Lamanya :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(340, 960, 60, 23);

        Kekuatan.setFocusTraversalPolicyProvider(true);
        Kekuatan.setName("Kekuatan"); // NOI18N
        Kekuatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KekuatanKeyPressed(evt);
            }
        });
        FormInput.add(Kekuatan);
        Kekuatan.setBounds(280, 960, 60, 23);

        jLabel42.setText("Kekuatan :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(210, 960, 68, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("x/10’,");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(190, 960, 40, 23);

        Kontraksi.setFocusTraversalPolicyProvider(true);
        Kontraksi.setName("Kontraksi"); // NOI18N
        Kontraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontraksiKeyPressed(evt);
            }
        });
        FormInput.add(Kontraksi);
        Kontraksi.setBounds(120, 960, 60, 23);

        jLabel46.setText("Kontraksi/HIS :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 960, 117, 23);

        jLabel130.setText("Keadaan Umum :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(260, 870, 90, 23);

        KeadaanMentalUmum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baik", "Sedang", "Buruk" }));
        KeadaanMentalUmum.setName("KeadaanMentalUmum"); // NOI18N
        KeadaanMentalUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeadaanMentalUmumKeyPressed(evt);
            }
        });
        FormInput.add(KeadaanMentalUmum);
        KeadaanMentalUmum.setBounds(350, 870, 90, 23);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(450, 870, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput.add(GCS);
        GCS.setBounds(530, 870, 50, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(590, 870, 30, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput.add(TD);
        TD.setBounds(620, 870, 50, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(680, 870, 40, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(720, 870, 40, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(770, 870, 50, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(820, 870, 50, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 900, 65, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        FormInput.add(RR);
        RR.setBounds(70, 900, 50, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(120, 900, 50, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(170, 900, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(220, 900, 50, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(270, 900, 30, 23);

        jLabel24.setText("SpO2 :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(290, 900, 40, 23);

        SpO2.setFocusTraversalPolicyProvider(true);
        SpO2.setName("SpO2"); // NOI18N
        SpO2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SpO2KeyPressed(evt);
            }
        });
        FormInput.add(SpO2);
        SpO2.setBounds(330, 900, 50, 23);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("%");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(390, 900, 30, 23);

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(410, 900, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        FormInput.add(BB);
        BB.setBounds(440, 900, 50, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(490, 900, 30, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(510, 900, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        FormInput.add(TB);
        TB.setBounds(550, 900, 50, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("cm");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(600, 900, 30, 23);

        jLabel48.setText("LILA :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(630, 900, 40, 23);

        LILA.setFocusTraversalPolicyProvider(true);
        LILA.setName("LILA"); // NOI18N
        LILA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LILAKeyPressed(evt);
            }
        });
        FormInput.add(LILA);
        LILA.setBounds(670, 900, 50, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("cm");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(720, 900, 30, 23);

        jLabel50.setText("TFU :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(750, 900, 40, 23);

        TFU.setFocusTraversalPolicyProvider(true);
        TFU.setName("TFU"); // NOI18N
        TFU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFUKeyPressed(evt);
            }
        });
        FormInput.add(TFU);
        TFU.setBounds(790, 900, 50, 23);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("gr");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(130, 930, 25, 23);

        jLabel52.setText("TBJ :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 930, 68, 23);

        TBJ.setFocusTraversalPolicyProvider(true);
        TBJ.setName("TBJ"); // NOI18N
        TBJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBJKeyPressed(evt);
            }
        });
        FormInput.add(TBJ);
        TBJ.setBounds(70, 930, 50, 23);

        jLabel53.setText("GD :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(140, 930, 40, 23);

        GD.setEditable(false);
        GD.setFocusTraversalPolicyProvider(true);
        GD.setName("GD"); // NOI18N
        GD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GDKeyPressed(evt);
            }
        });
        FormInput.add(GD);
        GD.setBounds(180, 930, 40, 23);

        jLabel54.setText("Letak :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(230, 930, 40, 23);

        Letak.setFocusTraversalPolicyProvider(true);
        Letak.setName("Letak"); // NOI18N
        Letak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LetakKeyPressed(evt);
            }
        });
        FormInput.add(Letak);
        Letak.setBounds(280, 930, 140, 23);

        jLabel55.setText("Presentasi :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(420, 930, 70, 23);

        Presentasi.setFocusTraversalPolicyProvider(true);
        Presentasi.setName("Presentasi"); // NOI18N
        Presentasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PresentasiKeyPressed(evt);
            }
        });
        FormInput.add(Presentasi);
        Presentasi.setBounds(500, 930, 140, 23);

        jLabel56.setText("Penurunan :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(630, 930, 80, 23);

        Penurunan.setFocusTraversalPolicyProvider(true);
        Penurunan.setName("Penurunan"); // NOI18N
        Penurunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenurunanKeyPressed(evt);
            }
        });
        FormInput.add(Penurunan);
        Penurunan.setBounds(720, 930, 140, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("cm");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(840, 900, 25, 23);

        jLabel58.setText("Portio :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 990, 80, 23);

        Portio.setFocusTraversalPolicyProvider(true);
        Portio.setName("Portio"); // NOI18N
        Portio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PortioKeyPressed(evt);
            }
        });
        FormInput.add(Portio);
        Portio.setBounds(90, 990, 50, 23);

        LabelServiks.setText("Pembukaan Serviks :");
        LabelServiks.setName("LabelServiks"); // NOI18N
        FormInput.add(LabelServiks);
        LabelServiks.setBounds(140, 990, 110, 23);

        PembukaanServiks.setFocusTraversalPolicyProvider(true);
        PembukaanServiks.setName("PembukaanServiks"); // NOI18N
        PembukaanServiks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PembukaanServiksKeyPressed(evt);
            }
        });
        FormInput.add(PembukaanServiks);
        PembukaanServiks.setBounds(260, 990, 50, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("cm ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(310, 990, 25, 23);

        jLabel60.setText("Ketuban :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(330, 990, 60, 23);

        Ketuban.setFocusTraversalPolicyProvider(true);
        Ketuban.setName("Ketuban"); // NOI18N
        Ketuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanKeyPressed(evt);
            }
        });
        FormInput.add(Ketuban);
        Ketuban.setBounds(390, 990, 50, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("kep/bok");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(450, 990, 60, 23);

        jLabel62.setText("Hodge :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(490, 990, 50, 23);

        Hodge.setFocusTraversalPolicyProvider(true);
        Hodge.setName("Hodge"); // NOI18N
        Hodge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HodgeKeyPressed(evt);
            }
        });
        FormInput.add(Hodge);
        Hodge.setBounds(550, 990, 50, 23);

        jLabel65.setText("Panggul :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(600, 990, 60, 23);

        PemeriksaanPanggul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Luas", "Sedang", "Sempit", "Tidak Dilakukan Pemeriksaan" }));
        PemeriksaanPanggul.setName("PemeriksaanPanggul"); // NOI18N
        PemeriksaanPanggul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanPanggulKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanPanggul);
        PemeriksaanPanggul.setBounds(660, 990, 195, 23);

        jLabel63.setText("Inspekulo :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 1020, 98, 23);

        Inspekulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak" }));
        Inspekulo.setSelectedIndex(1);
        Inspekulo.setName("Inspekulo"); // NOI18N
        Inspekulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InspekuloKeyPressed(evt);
            }
        });
        FormInput.add(Inspekulo);
        Inspekulo.setBounds(100, 1020, 100, 23);

        KeteranganInspekulo.setFocusTraversalPolicyProvider(true);
        KeteranganInspekulo.setName("KeteranganInspekulo"); // NOI18N
        KeteranganInspekulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganInspekuloKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganInspekulo);
        KeteranganInspekulo.setBounds(210, 1020, 115, 23);

        jLabel91.setText("CTG :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(590, 1020, 40, 23);

        CTG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak" }));
        CTG.setSelectedIndex(1);
        CTG.setName("CTG"); // NOI18N
        CTG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CTGKeyPressed(evt);
            }
        });
        FormInput.add(CTG);
        CTG.setBounds(640, 1020, 100, 23);

        KeteranganCTG.setFocusTraversalPolicyProvider(true);
        KeteranganCTG.setName("KeteranganCTG"); // NOI18N
        KeteranganCTG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganCTGKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganCTG);
        KeteranganCTG.setBounds(740, 1020, 115, 23);

        jLabel132.setText("Lakmus :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(320, 1020, 55, 23);

        Lakmus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dilakukan", "Tidak" }));
        Lakmus.setSelectedIndex(1);
        Lakmus.setName("Lakmus"); // NOI18N
        Lakmus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LakmusKeyPressed(evt);
            }
        });
        FormInput.add(Lakmus);
        Lakmus.setBounds(380, 1020, 100, 23);

        KeteranganLakmus.setFocusTraversalPolicyProvider(true);
        KeteranganLakmus.setName("KeteranganLakmus"); // NOI18N
        KeteranganLakmus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganLakmusKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganLakmus);
        KeteranganLakmus.setBounds(480, 1020, 115, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 1050, 880, 1);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("III. PEMERIKSAAN UMUM");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(10, 1050, 180, 23);

        jLabel64.setText("Kepala :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 1070, 84, 23);

        PemeriksaanKepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normocephale", "Hydrocephalus", "Lain-lain" }));
        PemeriksaanKepala.setName("PemeriksaanKepala"); // NOI18N
        PemeriksaanKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanKepalaKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanKepala);
        PemeriksaanKepala.setBounds(90, 1070, 135, 23);

        jLabel133.setText("Muka :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(260, 1070, 40, 23);

        PemeriksaanMuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Pucat", "Oedem", "Lain-lain" }));
        PemeriksaanMuka.setName("PemeriksaanMuka"); // NOI18N
        PemeriksaanMuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanMukaKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanMuka);
        PemeriksaanMuka.setBounds(310, 1070, 105, 23);

        jLabel134.setText("Mata :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(450, 1070, 40, 23);

        PemeriksaanMata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Conjungtiva Merah Muda", "Conjungtiva Pucat", "Sklera Ikterik", "Pandangan Kabur", "Lain-lain" }));
        PemeriksaanMata.setName("PemeriksaanMata"); // NOI18N
        PemeriksaanMata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanMataKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanMata);
        PemeriksaanMata.setBounds(490, 1070, 180, 23);

        jLabel135.setText("Hidung :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(700, 1070, 50, 23);

        PemeriksaanHidung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Sekret", "Polip", "Lain-lain" }));
        PemeriksaanHidung.setName("PemeriksaanHidung"); // NOI18N
        PemeriksaanHidung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanHidungKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanHidung);
        PemeriksaanHidung.setBounds(750, 1070, 102, 23);

        jLabel136.setText("Telinga :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(0, 1100, 87, 23);

        PemeriksaanTelinga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Serumen", "Polip", "Lain-lain" }));
        PemeriksaanTelinga.setName("PemeriksaanTelinga"); // NOI18N
        PemeriksaanTelinga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanTelingaKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanTelinga);
        PemeriksaanTelinga.setBounds(90, 1100, 95, 23);

        PemeriksaanMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Kotor", "Lain-lain" }));
        PemeriksaanMulut.setName("PemeriksaanMulut"); // NOI18N
        PemeriksaanMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanMulutKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanMulut);
        PemeriksaanMulut.setBounds(260, 1100, 95, 23);

        jLabel137.setText("Mulut :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(210, 1100, 50, 23);

        PemeriksaanLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Pembesaran KGB", "Pembesaran Kelenjar Tiroid", "Lain-lain" }));
        PemeriksaanLeher.setName("PemeriksaanLeher"); // NOI18N
        PemeriksaanLeher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLeherKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLeher);
        PemeriksaanLeher.setBounds(440, 1100, 185, 23);

        jLabel138.setText("Leher :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(380, 1100, 50, 23);

        PemeriksaanDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mamae Simetris", "Mamae Asimetris", "Aerola Hiperpigmentasi", "Kolustrum (+)", "Tumor", "Puting Susu Menonjol" }));
        PemeriksaanDada.setName("PemeriksaanDada"); // NOI18N
        PemeriksaanDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanDadaKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanDada);
        PemeriksaanDada.setBounds(690, 1100, 165, 23);

        jLabel139.setText("Dada :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(640, 1100, 50, 23);

        jLabel140.setText("Perut :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(0, 1130, 77, 23);

        PemeriksaanPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Luka Bekas Operasi", "Nyeri Tekan (Ya)", "Nyeri Tekan (Tidak)", "Lain-lain" }));
        PemeriksaanPerut.setName("PemeriksaanPerut"); // NOI18N
        PemeriksaanPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanPerutKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanPerut);
        PemeriksaanPerut.setBounds(80, 1130, 170, 23);

        PemeriksaanGenitalia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bersih", "Kotor", "Varises", "Oedem", "Hematoma", "Hemoroid", "Lain-lain" }));
        PemeriksaanGenitalia.setName("PemeriksaanGenitalia"); // NOI18N
        PemeriksaanGenitalia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanGenitaliaKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanGenitalia);
        PemeriksaanGenitalia.setBounds(350, 1130, 83, 23);

        jLabel141.setText("Genitalia :");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(270, 1130, 77, 23);

        PemeriksaanEkstrimitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Oedem", "Refleks Patella Ada", "Lain-lain" }));
        PemeriksaanEkstrimitas.setName("PemeriksaanEkstrimitas"); // NOI18N
        PemeriksaanEkstrimitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanEkstrimitasKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanEkstrimitas);
        PemeriksaanEkstrimitas.setBounds(560, 1130, 150, 23);

        jLabel142.setText("Ekstremitas :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(480, 1130, 77, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1160, 880, 1);

        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("IV. PENGKAJIAN FUNGSI");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(10, 1160, 180, 23);

        jLabel144.setText("a. Kemampuan Aktifitas Sehari-hari  :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(0, 1180, 227, 23);

        AktifitasSehari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mandiri", "Bantuan minimal", "Bantuan Sebagian", "Ketergantungan Total" }));
        AktifitasSehari2.setName("AktifitasSehari2"); // NOI18N
        AktifitasSehari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasSehari2KeyPressed(evt);
            }
        });
        FormInput.add(AktifitasSehari2);
        AktifitasSehari2.setBounds(230, 1180, 158, 23);

        Aktifitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tirah Baring", "Duduk", "Berjalan" }));
        Aktifitas.setName("Aktifitas"); // NOI18N
        Aktifitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AktifitasKeyPressed(evt);
            }
        });
        FormInput.add(Aktifitas);
        Aktifitas.setBounds(110, 1210, 110, 23);

        jLabel145.setText("c. Aktifitas :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(0, 1210, 103, 23);

        jLabel146.setText("b. Berjalan :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(400, 1180, 70, 23);

        Berjalan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Penurunan Kekuatan/ROM", "Paralisis", "Sering Jatuh", "Deformitas", "Hilang keseimbangan", "Riwayat Patah Tulang", "Lain-lain" }));
        Berjalan.setName("Berjalan"); // NOI18N
        Berjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BerjalanKeyPressed(evt);
            }
        });
        FormInput.add(Berjalan);
        Berjalan.setBounds(480, 1180, 178, 23);

        KeteranganBerjalan.setFocusTraversalPolicyProvider(true);
        KeteranganBerjalan.setName("KeteranganBerjalan"); // NOI18N
        KeteranganBerjalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganBerjalanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganBerjalan);
        KeteranganBerjalan.setBounds(660, 1180, 200, 23);

        jLabel147.setText("d. Alat Ambulasi :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(220, 1210, 100, 23);

        AlatAmbulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Walker", "Tongkat", "Kursi Roda", "Tidak Menggunakan" }));
        AlatAmbulasi.setName("AlatAmbulasi"); // NOI18N
        AlatAmbulasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlatAmbulasiKeyPressed(evt);
            }
        });
        FormInput.add(AlatAmbulasi);
        AlatAmbulasi.setBounds(330, 1210, 147, 23);

        EkstrimitasAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Lemah", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasAtas.setName("EkstrimitasAtas"); // NOI18N
        EkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasAtas);
        EkstrimitasAtas.setBounds(600, 1210, 120, 23);

        jLabel148.setText("e. Ekstremitas Atas :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(480, 1210, 110, 23);

        KeteranganEkstrimitasAtas.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasAtas.setName("KeteranganEkstrimitasAtas"); // NOI18N
        KeteranganEkstrimitasAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasAtasKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasAtas);
        KeteranganEkstrimitasAtas.setBounds(720, 1210, 137, 23);

        jLabel149.setText("f. Ekstremitas Bawah :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(0, 1240, 153, 23);

        EkstrimitasBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TAK", "Varises", "Oedema", "Tidak Simetris", "Lain-lain" }));
        EkstrimitasBawah.setName("EkstrimitasBawah"); // NOI18N
        EkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(EkstrimitasBawah);
        EkstrimitasBawah.setBounds(160, 1240, 120, 23);

        KeteranganEkstrimitasBawah.setFocusTraversalPolicyProvider(true);
        KeteranganEkstrimitasBawah.setName("KeteranganEkstrimitasBawah"); // NOI18N
        KeteranganEkstrimitasBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEkstrimitasBawahKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEkstrimitasBawah);
        KeteranganEkstrimitasBawah.setBounds(280, 1240, 137, 23);

        jLabel150.setText("g. Kemampuan Menggenggam :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(430, 1240, 160, 23);

        KemampuanMenggenggam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Terakhir", "Lain-lain" }));
        KemampuanMenggenggam.setName("KemampuanMenggenggam"); // NOI18N
        KemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanMenggenggam);
        KemampuanMenggenggam.setBounds(590, 1240, 147, 23);

        KeteranganKemampuanMenggenggam.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanMenggenggam.setName("KeteranganKemampuanMenggenggam"); // NOI18N
        KeteranganKemampuanMenggenggam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanMenggenggamKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanMenggenggam);
        KeteranganKemampuanMenggenggam.setBounds(740, 1240, 115, 23);

        jLabel151.setText("h. Kemampuan Koordinasi :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(0, 1270, 177, 23);

        KemampuanKoordinasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Kesulitan", "Ada Masalah" }));
        KemampuanKoordinasi.setName("KemampuanKoordinasi"); // NOI18N
        KemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KemampuanKoordinasi);
        KemampuanKoordinasi.setBounds(180, 1270, 147, 23);

        KeteranganKemampuanKoordinasi.setFocusTraversalPolicyProvider(true);
        KeteranganKemampuanKoordinasi.setName("KeteranganKemampuanKoordinasi"); // NOI18N
        KeteranganKemampuanKoordinasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKemampuanKoordinasiKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganKemampuanKoordinasi);
        KeteranganKemampuanKoordinasi.setBounds(330, 1270, 137, 23);

        jLabel152.setText("i. Kesimpulan Gangguan Fungsi :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(490, 1270, 170, 23);

        KesimpulanGangguanFungsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak (Tidak Perlu Co DPJP)", "Ya (Co DPJP)" }));
        KesimpulanGangguanFungsi.setName("KesimpulanGangguanFungsi"); // NOI18N
        KesimpulanGangguanFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesimpulanGangguanFungsiKeyPressed(evt);
            }
        });
        FormInput.add(KesimpulanGangguanFungsi);
        KesimpulanGangguanFungsi.setBounds(660, 1270, 195, 23);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 1300, 880, 1);

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel153.setText("V. RIWAYAT PSIKOLOGIS – SOSIAL – EKONOMI – BUDAYA – SPIRITUAL");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(10, 1300, 490, 23);

        jLabel154.setText("a. Kondisi Psikologis :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(0, 1320, 149, 23);

        KondisiPsikologis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Marah", "Takut", "Depresi", "Cepat Lelah", "Cemas", "Gelisah", "Sulit Tidur", "Lain-lain" }));
        KondisiPsikologis.setName("KondisiPsikologis"); // NOI18N
        KondisiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KondisiPsikologis);
        KondisiPsikologis.setBounds(160, 1320, 142, 23);

        jLabel155.setText("b. Adakah Perilaku :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(300, 1320, 110, 23);

        AdakahPerilaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Masalah", "Perilaku Kekerasan", "Gangguan Efek", "Gangguan Memori", "Halusinasi", "Kecenderungan Percobaan Bunuh Diri", "Lain-lain" }));
        AdakahPerilaku.setName("AdakahPerilaku"); // NOI18N
        AdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(AdakahPerilaku);
        AdakahPerilaku.setBounds(420, 1320, 235, 23);

        KeteranganAdakahPerilaku.setFocusTraversalPolicyProvider(true);
        KeteranganAdakahPerilaku.setName("KeteranganAdakahPerilaku"); // NOI18N
        KeteranganAdakahPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganAdakahPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganAdakahPerilaku);
        KeteranganAdakahPerilaku.setBounds(650, 1320, 202, 23);

        jLabel156.setText("c. Gangguan Jiwa di Masa Lalu :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(0, 1350, 199, 23);

        GangguanJiwa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        GangguanJiwa.setName("GangguanJiwa"); // NOI18N
        GangguanJiwa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GangguanJiwaKeyPressed(evt);
            }
        });
        FormInput.add(GangguanJiwa);
        GangguanJiwa.setBounds(210, 1350, 77, 23);

        jLabel157.setText("d. Hubungan Pasien dengan Anggota Keluarga :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(290, 1350, 240, 23);

        HubunganAnggotaKeluarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Harmonis", "Kurang Harmonis", "Tidak Harmonis", "Konflik Besar" }));
        HubunganAnggotaKeluarga.setName("HubunganAnggotaKeluarga"); // NOI18N
        HubunganAnggotaKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganAnggotaKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(HubunganAnggotaKeluarga);
        HubunganAnggotaKeluarga.setBounds(530, 1350, 133, 23);

        jLabel158.setText("j. Bahasa Sehari-hari :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(610, 1410, 120, 23);

        Bahasa.setEditable(false);
        Bahasa.setFocusTraversalPolicyProvider(true);
        Bahasa.setName("Bahasa"); // NOI18N
        FormInput.add(Bahasa);
        Bahasa.setBounds(740, 1410, 120, 23);

        jLabel159.setText("f. Tinggal Dengan :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(0, 1380, 137, 23);

        TinggalDengan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sendiri", "Orang Tua", "Suami/Istri", "Keluarga", "Lain-lain" }));
        TinggalDengan.setName("TinggalDengan"); // NOI18N
        TinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(TinggalDengan);
        TinggalDengan.setBounds(140, 1380, 105, 23);

        KeteranganTinggalDengan.setFocusTraversalPolicyProvider(true);
        KeteranganTinggalDengan.setName("KeteranganTinggalDengan"); // NOI18N
        KeteranganTinggalDengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganTinggalDenganKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganTinggalDengan);
        KeteranganTinggalDengan.setBounds(250, 1380, 137, 23);

        jLabel160.setText("g. Pekerjaan :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(390, 1380, 83, 23);

        PekerjaanPasien.setEditable(false);
        PekerjaanPasien.setFocusTraversalPolicyProvider(true);
        PekerjaanPasien.setName("PekerjaanPasien"); // NOI18N
        FormInput.add(PekerjaanPasien);
        PekerjaanPasien.setBounds(480, 1380, 140, 23);

        jLabel161.setText("h. Pembayaran :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(620, 1380, 90, 23);

        CaraBayar.setEditable(false);
        CaraBayar.setFocusTraversalPolicyProvider(true);
        CaraBayar.setName("CaraBayar"); // NOI18N
        FormInput.add(CaraBayar);
        CaraBayar.setBounds(720, 1380, 140, 23);

        jLabel162.setText("i. Nilai-nilai Kepercayaan/Budaya Yang Perlu Diperhatikan :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(0, 1410, 331, 23);

        NilaiKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        NilaiKepercayaan.setName("NilaiKepercayaan"); // NOI18N
        NilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(NilaiKepercayaan);
        NilaiKepercayaan.setBounds(340, 1410, 105, 23);

        KeteranganNilaiKepercayaan.setFocusTraversalPolicyProvider(true);
        KeteranganNilaiKepercayaan.setName("KeteranganNilaiKepercayaan"); // NOI18N
        KeteranganNilaiKepercayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganNilaiKepercayaanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganNilaiKepercayaan);
        KeteranganNilaiKepercayaan.setBounds(450, 1410, 160, 23);

        jLabel163.setText("e. Agama :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(670, 1350, 60, 23);

        Agama.setEditable(false);
        Agama.setFocusTraversalPolicyProvider(true);
        Agama.setName("Agama"); // NOI18N
        FormInput.add(Agama);
        Agama.setBounds(740, 1350, 120, 23);

        PendidikanPasien.setEditable(false);
        PendidikanPasien.setFocusTraversalPolicyProvider(true);
        PendidikanPasien.setName("PendidikanPasien"); // NOI18N
        FormInput.add(PendidikanPasien);
        PendidikanPasien.setBounds(160, 1440, 100, 23);

        jLabel164.setText("k. Pendidikan Pasien :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(0, 1440, 150, 23);

        jLabel165.setText("l. Pendidikan P.J. :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(260, 1440, 100, 23);

        PendidikanPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TS", "TK", "SD", "SMP", "SMA", "SLTA/SEDERAJAT", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        PendidikanPJ.setName("PendidikanPJ"); // NOI18N
        PendidikanPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PendidikanPJKeyPressed(evt);
            }
        });
        FormInput.add(PendidikanPJ);
        PendidikanPJ.setBounds(360, 1440, 135, 23);

        jLabel166.setText("m. Edukasi Diberikan Kepada :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(500, 1440, 160, 23);

        EdukasiPsikolgis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien", "Keluarga" }));
        EdukasiPsikolgis.setName("EdukasiPsikolgis"); // NOI18N
        EdukasiPsikolgis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EdukasiPsikolgisKeyPressed(evt);
            }
        });
        FormInput.add(EdukasiPsikolgis);
        EdukasiPsikolgis.setBounds(660, 1440, 95, 23);

        KeteranganEdukasiPsikologis.setFocusTraversalPolicyProvider(true);
        KeteranganEdukasiPsikologis.setName("KeteranganEdukasiPsikologis"); // NOI18N
        KeteranganEdukasiPsikologis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganEdukasiPsikologisKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganEdukasiPsikologis);
        KeteranganEdukasiPsikologis.setBounds(760, 1440, 99, 23);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 1530, 880, 1);

        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel167.setText("VI. PENILAIAN TINGKAT NYERI");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(10, 1530, 380, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(40, 1550, 320, 130);

        jSeparator9.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator9.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator9.setName("jSeparator9"); // NOI18N
        FormInput.add(jSeparator9);
        jSeparator9.setBounds(370, 1550, 1, 140);

        Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Nyeri", "Nyeri Akut", "Nyeri Kronis" }));
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriKeyPressed(evt);
            }
        });
        FormInput.add(Nyeri);
        Nyeri.setBounds(380, 1550, 130, 23);

        jLabel168.setText("Penyebab :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(510, 1550, 60, 23);

        Provokes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proses Penyakit", "Benturan", "Lain-lain" }));
        Provokes.setName("Provokes"); // NOI18N
        Provokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProvokesKeyPressed(evt);
            }
        });
        FormInput.add(Provokes);
        Provokes.setBounds(580, 1550, 130, 23);

        KetProvokes.setFocusTraversalPolicyProvider(true);
        KetProvokes.setName("KetProvokes"); // NOI18N
        KetProvokes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetProvokesKeyPressed(evt);
            }
        });
        FormInput.add(KetProvokes);
        KetProvokes.setBounds(710, 1550, 146, 23);

        jLabel169.setText("Kualitas :");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(370, 1580, 55, 23);

        Quality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seperti Tertusuk", "Berdenyut", "Teriris", "Tertindih", "Tertiban", "Lain-lain" }));
        Quality.setName("Quality"); // NOI18N
        Quality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QualityKeyPressed(evt);
            }
        });
        FormInput.add(Quality);
        Quality.setBounds(430, 1580, 140, 23);

        KetQuality.setFocusTraversalPolicyProvider(true);
        KetQuality.setName("KetQuality"); // NOI18N
        KetQuality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetQualityKeyPressed(evt);
            }
        });
        FormInput.add(KetQuality);
        KetQuality.setBounds(580, 1580, 280, 23);

        jLabel170.setText("Wilayah :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(370, 1610, 55, 23);

        jLabel171.setText("Lokasi :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(400, 1630, 60, 23);

        Lokasi.setFocusTraversalPolicyProvider(true);
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput.add(Lokasi);
        Lokasi.setBounds(460, 1630, 220, 23);

        jLabel172.setText("Menyebar :");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(690, 1630, 79, 23);

        Menyebar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Menyebar.setName("Menyebar"); // NOI18N
        Menyebar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenyebarKeyPressed(evt);
            }
        });
        FormInput.add(Menyebar);
        Menyebar.setBounds(780, 1630, 80, 23);

        jLabel173.setText("Severity :");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(370, 1660, 55, 23);

        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel174.setText("Skala Nyeri");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(430, 1660, 60, 23);

        SkalaNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        SkalaNyeri.setName("SkalaNyeri"); // NOI18N
        SkalaNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaNyeriKeyPressed(evt);
            }
        });
        FormInput.add(SkalaNyeri);
        SkalaNyeri.setBounds(490, 1660, 70, 23);

        jLabel175.setText("Waktu / Durasi :");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(630, 1660, 90, 23);

        Durasi.setFocusTraversalPolicyProvider(true);
        Durasi.setName("Durasi"); // NOI18N
        Durasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DurasiKeyPressed(evt);
            }
        });
        FormInput.add(Durasi);
        Durasi.setBounds(720, 1660, 90, 23);

        jLabel176.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel176.setText("Menit");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(820, 1660, 35, 23);

        jLabel177.setText("Nyeri hilang bila :");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(0, 1690, 130, 23);

        NyeriHilang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Istirahat", "Medengar Musik", "Minum Obat" }));
        NyeriHilang.setName("NyeriHilang"); // NOI18N
        NyeriHilang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NyeriHilangKeyPressed(evt);
            }
        });
        FormInput.add(NyeriHilang);
        NyeriHilang.setBounds(140, 1690, 130, 23);

        KetNyeri.setFocusTraversalPolicyProvider(true);
        KetNyeri.setName("KetNyeri"); // NOI18N
        KetNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetNyeriKeyPressed(evt);
            }
        });
        FormInput.add(KetNyeri);
        KetNyeri.setBounds(270, 1690, 150, 23);

        jLabel178.setText("Diberitahukan pada dokter ?");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(480, 1690, 150, 23);

        PadaDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        PadaDokter.setName("PadaDokter"); // NOI18N
        PadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(PadaDokter);
        PadaDokter.setBounds(640, 1690, 80, 23);

        jLabel179.setText("Jam  :");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(720, 1690, 50, 23);

        KetPadaDokter.setFocusTraversalPolicyProvider(true);
        KetPadaDokter.setName("KetPadaDokter"); // NOI18N
        KetPadaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetPadaDokterKeyPressed(evt);
            }
        });
        FormInput.add(KetPadaDokter);
        KetPadaDokter.setBounds(780, 1690, 80, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        FormInput.add(jSeparator10);
        jSeparator10.setBounds(0, 1720, 880, 1);

        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel180.setText("VII. PENILAIAN RESIKO JATUH");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(10, 1720, 380, 23);

        jLabel181.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel181.setText("1. Riwayat Jatuh");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(50, 1740, 300, 23);

        SkalaResiko1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaResiko1.setName("SkalaResiko1"); // NOI18N
        SkalaResiko1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko1ItemStateChanged(evt);
            }
        });
        SkalaResiko1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko1);
        SkalaResiko1.setBounds(430, 1740, 280, 23);

        jLabel182.setText("Nilai :");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(720, 1740, 75, 23);

        NilaiResiko1.setEditable(false);
        NilaiResiko1.setFocusTraversalPolicyProvider(true);
        NilaiResiko1.setName("NilaiResiko1"); // NOI18N
        FormInput.add(NilaiResiko1);
        NilaiResiko1.setBounds(800, 1740, 60, 23);

        jLabel183.setText("Skala :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(340, 1740, 80, 23);

        jLabel184.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel184.setText("2. Diagnosis Sekunder (≥ 2 Diagnosis Medis)");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(50, 1770, 300, 23);

        jLabel185.setText("Skala :");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(340, 1770, 80, 23);

        SkalaResiko2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaResiko2.setName("SkalaResiko2"); // NOI18N
        SkalaResiko2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko2ItemStateChanged(evt);
            }
        });
        SkalaResiko2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko2);
        SkalaResiko2.setBounds(430, 1770, 280, 23);

        NilaiResiko2.setEditable(false);
        NilaiResiko2.setFocusTraversalPolicyProvider(true);
        NilaiResiko2.setName("NilaiResiko2"); // NOI18N
        FormInput.add(NilaiResiko2);
        NilaiResiko2.setBounds(800, 1770, 60, 23);

        jLabel186.setText("Nilai :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(720, 1770, 75, 23);

        jLabel187.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel187.setText("3. Alat Bantu");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(50, 1800, 300, 23);

        jLabel188.setText("Skala :");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(340, 1800, 80, 23);

        SkalaResiko3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada/Kursi Roda/Perawat/Tirah Baring", "Tongkat/Alat Penopang", "Berpegangan Pada Perabot" }));
        SkalaResiko3.setName("SkalaResiko3"); // NOI18N
        SkalaResiko3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko3ItemStateChanged(evt);
            }
        });
        SkalaResiko3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko3);
        SkalaResiko3.setBounds(430, 1800, 280, 23);

        jLabel189.setText("Nilai :");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(720, 1800, 75, 23);

        NilaiResiko3.setEditable(false);
        NilaiResiko3.setFocusTraversalPolicyProvider(true);
        NilaiResiko3.setName("NilaiResiko3"); // NOI18N
        FormInput.add(NilaiResiko3);
        NilaiResiko3.setBounds(800, 1800, 60, 23);

        jLabel190.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel190.setText("4. Terpasang Infuse");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(50, 1830, 300, 23);

        jLabel191.setText("Skala :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(340, 1830, 80, 23);

        SkalaResiko4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaResiko4.setName("SkalaResiko4"); // NOI18N
        SkalaResiko4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko4ItemStateChanged(evt);
            }
        });
        SkalaResiko4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko4);
        SkalaResiko4.setBounds(430, 1830, 280, 23);

        jLabel192.setText("Nilai :");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(720, 1830, 75, 23);

        NilaiResiko4.setEditable(false);
        NilaiResiko4.setFocusTraversalPolicyProvider(true);
        NilaiResiko4.setName("NilaiResiko4"); // NOI18N
        FormInput.add(NilaiResiko4);
        NilaiResiko4.setBounds(800, 1830, 60, 23);

        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel193.setText("5. Gaya Berjalan");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(50, 1860, 300, 23);

        jLabel194.setText("Skala :");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(340, 1860, 80, 23);

        SkalaResiko5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal/Tirah Baring/Imobilisasi", "Lemah", "Terganggu" }));
        SkalaResiko5.setName("SkalaResiko5"); // NOI18N
        SkalaResiko5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko5ItemStateChanged(evt);
            }
        });
        SkalaResiko5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko5);
        SkalaResiko5.setBounds(430, 1860, 280, 23);

        jLabel195.setText("Nilai :");
        jLabel195.setName("jLabel195"); // NOI18N
        FormInput.add(jLabel195);
        jLabel195.setBounds(720, 1860, 75, 23);

        NilaiResiko5.setEditable(false);
        NilaiResiko5.setFocusTraversalPolicyProvider(true);
        NilaiResiko5.setName("NilaiResiko5"); // NOI18N
        FormInput.add(NilaiResiko5);
        NilaiResiko5.setBounds(800, 1860, 60, 23);

        jLabel196.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel196.setText("6. Status Mental");
        jLabel196.setName("jLabel196"); // NOI18N
        FormInput.add(jLabel196);
        jLabel196.setBounds(50, 1890, 300, 23);

        jLabel197.setText("Skala :");
        jLabel197.setName("jLabel197"); // NOI18N
        FormInput.add(jLabel197);
        jLabel197.setBounds(340, 1890, 80, 23);

        SkalaResiko6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sadar Akan Kemampuan Diri Sendiri", "Sering Lupa Akan Keterbatasan Yang Dimiliki" }));
        SkalaResiko6.setName("SkalaResiko6"); // NOI18N
        SkalaResiko6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko6ItemStateChanged(evt);
            }
        });
        SkalaResiko6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko6KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko6);
        SkalaResiko6.setBounds(430, 1890, 280, 23);

        jLabel198.setText("Nilai :");
        jLabel198.setName("jLabel198"); // NOI18N
        FormInput.add(jLabel198);
        jLabel198.setBounds(720, 1890, 75, 23);

        NilaiResiko6.setEditable(false);
        NilaiResiko6.setFocusTraversalPolicyProvider(true);
        NilaiResiko6.setName("NilaiResiko6"); // NOI18N
        FormInput.add(NilaiResiko6);
        NilaiResiko6.setBounds(800, 1890, 60, 23);

        jLabel199.setText("Total :");
        jLabel199.setName("jLabel199"); // NOI18N
        FormInput.add(jLabel199);
        jLabel199.setBounds(720, 1920, 75, 23);

        NilaiResikoTotal.setEditable(false);
        NilaiResikoTotal.setFocusTraversalPolicyProvider(true);
        NilaiResikoTotal.setName("NilaiResikoTotal"); // NOI18N
        FormInput.add(NilaiResikoTotal);
        NilaiResikoTotal.setBounds(800, 1920, 60, 23);

        TingkatResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatResiko.setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        TingkatResiko.setName("TingkatResiko"); // NOI18N
        FormInput.add(TingkatResiko);
        TingkatResiko.setBounds(50, 1950, 810, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        FormInput.add(jSeparator11);
        jSeparator11.setBounds(0, 1980, 880, 1);

        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel201.setText("VIII. SKRINING GIZI");
        jLabel201.setName("jLabel201"); // NOI18N
        FormInput.add(jLabel201);
        jLabel201.setBounds(10, 1980, 380, 23);

        jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel202.setText("1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?");
        jLabel202.setName("jLabel202"); // NOI18N
        FormInput.add(jLabel202);
        jLabel202.setBounds(50, 2000, 380, 23);

        jLabel203.setText("Skor :");
        jLabel203.setName("jLabel203"); // NOI18N
        FormInput.add(jLabel203);
        jLabel203.setBounds(750, 2000, 40, 23);

        NilaiGizi1.setEditable(false);
        NilaiGizi1.setFocusTraversalPolicyProvider(true);
        NilaiGizi1.setName("NilaiGizi1"); // NOI18N
        FormInput.add(NilaiGizi1);
        NilaiGizi1.setBounds(800, 2000, 60, 23);

        SkalaGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak ada penurunan berat badan", "Tidak yakin/ tidak tahu/ terasa baju lebih longgar", "Ya 1-5 kg", "Ya 6-10 kg", "Ya 11-15 kg", "Ya > 15 kg" }));
        SkalaGizi1.setName("SkalaGizi1"); // NOI18N
        SkalaGizi1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi1ItemStateChanged(evt);
            }
        });
        SkalaGizi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi1);
        SkalaGizi1.setBounds(430, 2000, 320, 23);

        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel204.setText("2. Apakah asupan makan berkurang karena tidak nafsu makan ?");
        jLabel204.setName("jLabel204"); // NOI18N
        FormInput.add(jLabel204);
        jLabel204.setBounds(50, 2030, 380, 23);

        SkalaGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SkalaGizi2.setName("SkalaGizi2"); // NOI18N
        SkalaGizi2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaGizi2ItemStateChanged(evt);
            }
        });
        SkalaGizi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaGizi2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaGizi2);
        SkalaGizi2.setBounds(430, 2030, 320, 23);

        jLabel205.setText("Skor :");
        jLabel205.setName("jLabel205"); // NOI18N
        FormInput.add(jLabel205);
        jLabel205.setBounds(750, 2030, 40, 23);

        NilaiGizi2.setEditable(false);
        NilaiGizi2.setFocusTraversalPolicyProvider(true);
        NilaiGizi2.setName("NilaiGizi2"); // NOI18N
        FormInput.add(NilaiGizi2);
        NilaiGizi2.setBounds(800, 2030, 60, 23);

        jLabel206.setText("Total Skor :");
        jLabel206.setName("jLabel206"); // NOI18N
        FormInput.add(jLabel206);
        jLabel206.setBounds(680, 2060, 110, 23);

        NilaiGiziTotal.setEditable(false);
        NilaiGiziTotal.setFocusTraversalPolicyProvider(true);
        NilaiGiziTotal.setName("NilaiGiziTotal"); // NOI18N
        FormInput.add(NilaiGiziTotal);
        NilaiGiziTotal.setBounds(800, 2060, 60, 23);

        jLabel207.setText("Sudah dibaca dan diketahui oleh Dietisen :");
        jLabel207.setName("jLabel207"); // NOI18N
        FormInput.add(jLabel207);
        jLabel207.setBounds(450, 2090, 220, 23);

        DiketahuiDietisen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DiketahuiDietisen.setName("DiketahuiDietisen"); // NOI18N
        DiketahuiDietisen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiketahuiDietisenKeyPressed(evt);
            }
        });
        FormInput.add(DiketahuiDietisen);
        DiketahuiDietisen.setBounds(670, 2090, 80, 23);

        jLabel208.setText("Jam  :");
        jLabel208.setName("jLabel208"); // NOI18N
        FormInput.add(jLabel208);
        jLabel208.setBounds(750, 2090, 40, 23);

        KeteranganDiketahuiDietisen.setFocusTraversalPolicyProvider(true);
        KeteranganDiketahuiDietisen.setName("KeteranganDiketahuiDietisen"); // NOI18N
        KeteranganDiketahuiDietisen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDiketahuiDietisenKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDiketahuiDietisen);
        KeteranganDiketahuiDietisen.setBounds(800, 2090, 60, 23);

        jLabel209.setText("Pasien dengan diagnosis khusus : ");
        jLabel209.setName("jLabel209"); // NOI18N
        FormInput.add(jLabel209);
        jLabel209.setBounds(10, 2090, 206, 23);

        DiagnosaKhususGizi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DiagnosaKhususGizi.setName("DiagnosaKhususGizi"); // NOI18N
        DiagnosaKhususGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKhususGiziKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaKhususGizi);
        DiagnosaKhususGizi.setBounds(220, 2090, 80, 23);

        KeteranganDiagnosaKhususGizi.setFocusTraversalPolicyProvider(true);
        KeteranganDiagnosaKhususGizi.setName("KeteranganDiagnosaKhususGizi"); // NOI18N
        KeteranganDiagnosaKhususGizi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganDiagnosaKhususGiziKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganDiagnosaKhususGizi);
        KeteranganDiagnosaKhususGizi.setBounds(300, 2090, 150, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 2120, 880, 1);

        TabMasalahKeperawatan.setBackground(new java.awt.Color(255, 255, 255));
        TabMasalahKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabMasalahKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabMasalahKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabMasalahKeperawatan.setName("TabMasalahKeperawatan"); // NOI18N
        TabMasalahKeperawatan.setPreferredSize(new java.awt.Dimension(806, 437));

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(new java.awt.BorderLayout());

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbMasalahKeperawatanKebidanan.setName("tbMasalahKeperawatanKebidanan"); // NOI18N
        tbMasalahKeperawatanKebidanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasalahKeperawatanKebidananMouseClicked(evt);
            }
        });
        tbMasalahKeperawatanKebidanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKebidananKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMasalahKeperawatanKebidananKeyReleased(evt);
            }
        });
        Scroll7.setViewportView(tbMasalahKeperawatanKebidanan);

        panelBiasa3.add(Scroll7, java.awt.BorderLayout.CENTER);

        TabMasalahKeperawatan.addTab("Masalah Keperawatan Kebidanan", panelBiasa3);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Masalah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Masalah.setColumns(20);
        Masalah.setRows(5);
        Masalah.setName("Masalah"); // NOI18N
        Masalah.setOpaque(true);
        Masalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MasalahKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Masalah);

        TabMasalahKeperawatan.addTab("Masalah Keperawatan Kebidanan Lainnya", scrollPane7);

        FormInput.add(TabMasalahKeperawatan);
        TabMasalahKeperawatan.setBounds(10, 2430, 450, 143);

        TabRencanaKeperawatan.setBackground(new java.awt.Color(255, 255, 254));
        TabRencanaKeperawatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabRencanaKeperawatan.setForeground(new java.awt.Color(50, 50, 50));
        TabRencanaKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRencanaKeperawatan.setName("TabRencanaKeperawatan"); // NOI18N

        panelBiasa1.setName("panelBiasa1"); // NOI18N
        panelBiasa1.setLayout(new java.awt.BorderLayout());

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbRencanaKeperawatanKebidanan.setComponentPopupMenu(Popup);
        tbRencanaKeperawatanKebidanan.setName("tbRencanaKeperawatanKebidanan"); // NOI18N
        tbRencanaKeperawatanKebidanan.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 400));
        Scroll8.setViewportView(tbRencanaKeperawatanKebidanan);

        panelBiasa1.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Kebidanan", panelBiasa1);

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

        TabRencanaKeperawatan.addTab("Rencana Keperawatan Kebidanan Lainnya", scrollPane5);

        FormInput.add(TabRencanaKeperawatan);
        TabRencanaKeperawatan.setBounds(470, 2430, 450, 143);

        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(40, 2580, 60, 23);

        TCariMasalah.setToolTipText("Alt+C");
        TCariMasalah.setName("TCariMasalah"); // NOI18N
        TCariMasalah.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TCariMasalah);
        TCariMasalah.setBounds(100, 2580, 215, 23);

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
        BtnCariMasalah.setBounds(320, 2580, 28, 23);

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
        BtnAllMasalah.setBounds(350, 2580, 28, 23);

        BtnTambahMasalah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah1.setMnemonic('3');
        BtnTambahMasalah1.setToolTipText("Alt+3");
        BtnTambahMasalah1.setName("BtnTambahMasalah1"); // NOI18N
        BtnTambahMasalah1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalah1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahMasalah1);
        BtnTambahMasalah1.setBounds(380, 2580, 28, 23);

        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(500, 2580, 60, 23);

        TCariRencana.setToolTipText("Alt+C");
        TCariRencana.setName("TCariRencana"); // NOI18N
        TCariRencana.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariRencanaKeyPressed(evt);
            }
        });
        FormInput.add(TCariRencana);
        TCariRencana.setBounds(560, 2580, 235, 23);

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
        BtnCariRencana.setBounds(800, 2580, 28, 23);

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
        BtnAllRencana.setBounds(830, 2580, 28, 23);

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
        BtnTambahRencana.setBounds(870, 2580, 28, 23);

        jLabel200.setText("Riwayat Penyakit Saat Ini :");
        jLabel200.setToolTipText("");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(0, 250, 185, 23);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        RPS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RPS.setColumns(20);
        RPS.setRows(5);
        RPS.setName("RPS"); // NOI18N
        RPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RPSKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(RPS);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(189, 250, 250, 43);

        jLabel14.setText("Riwayat Pengobatan :");
        jLabel14.setToolTipText("");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(440, 250, 150, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        RObat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RObat.setColumns(20);
        RObat.setRows(5);
        RObat.setName("RObat"); // NOI18N
        RObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RObatKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(RObat);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(594, 250, 260, 43);

        jLabel210.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel210.setText("Kriteria Discharge Planning :");
        jLabel210.setName("jLabel210"); // NOI18N
        FormInput.add(jLabel210);
        jLabel210.setBounds(40, 2150, 590, 23);

        jLabel211.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel211.setText("1. Umur > 65 Tahun");
        jLabel211.setName("jLabel211"); // NOI18N
        FormInput.add(jLabel211);
        jLabel211.setBounds(40, 2170, 260, 23);

        jLabel212.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel212.setText("2. Keterbatasan Mobilitas");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(40, 2200, 260, 23);

        jLabel213.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel213.setText("3. Perawatan Atau Pengobatan Lanjutan");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(40, 2230, 260, 23);

        jLabel214.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel214.setText("4. Bantuan Untuk Melakukan Aktifitas Sehari-Hari");
        jLabel214.setName("jLabel214"); // NOI18N
        FormInput.add(jLabel214);
        jLabel214.setBounds(40, 2260, 260, 23);

        Kriteria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria1.setName("Kriteria1"); // NOI18N
        FormInput.add(Kriteria1);
        Kriteria1.setBounds(330, 2170, 80, 23);

        Kriteria2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria2.setName("Kriteria2"); // NOI18N
        FormInput.add(Kriteria2);
        Kriteria2.setBounds(330, 2200, 80, 23);

        Kriteria3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria3.setName("Kriteria3"); // NOI18N
        FormInput.add(Kriteria3);
        Kriteria3.setBounds(330, 2230, 80, 23);

        Kriteria4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Kriteria4.setName("Kriteria4"); // NOI18N
        FormInput.add(Kriteria4);
        Kriteria4.setBounds(330, 2260, 80, 23);

        jLabel215.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel215.setText("Apabila salah satu jawaban YA dari kriteria diatas, maka akan dilanjutkan dengan perencanaan sebagai berikut :");
        jLabel215.setName("jLabel215"); // NOI18N
        FormInput.add(jLabel215);
        jLabel215.setBounds(40, 2310, 570, 23);

        pilihan1.setBackground(new java.awt.Color(255, 255, 255));
        pilihan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan1.setForeground(new java.awt.Color(50, 50, 50));
        pilihan1.setText("Perawatan diri (Mandi, BAB, BAK)");
        pilihan1.setName("pilihan1"); // NOI18N
        FormInput.add(pilihan1);
        pilihan1.setBounds(50, 2340, 200, 19);

        pilihan2.setBackground(new java.awt.Color(255, 255, 255));
        pilihan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan2.setForeground(new java.awt.Color(50, 50, 50));
        pilihan2.setText("Pemantauan pemberian obat");
        pilihan2.setName("pilihan2"); // NOI18N
        FormInput.add(pilihan2);
        pilihan2.setBounds(50, 2360, 180, 19);

        pilihan3.setBackground(new java.awt.Color(255, 255, 255));
        pilihan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan3.setForeground(new java.awt.Color(50, 50, 50));
        pilihan3.setText("Pemantauan diet");
        pilihan3.setName("pilihan3"); // NOI18N
        FormInput.add(pilihan3);
        pilihan3.setBounds(50, 2380, 120, 19);

        pilihan4.setBackground(new java.awt.Color(255, 255, 255));
        pilihan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan4.setForeground(new java.awt.Color(50, 50, 50));
        pilihan4.setText("Bantuan medis / perawatan di rumah (Homecare)");
        pilihan4.setName("pilihan4"); // NOI18N
        FormInput.add(pilihan4);
        pilihan4.setBounds(50, 2400, 280, 19);

        pilihan5.setBackground(new java.awt.Color(255, 255, 255));
        pilihan5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan5.setForeground(new java.awt.Color(50, 50, 50));
        pilihan5.setText("Perawatan luka");
        pilihan5.setName("pilihan5"); // NOI18N
        FormInput.add(pilihan5);
        pilihan5.setBounds(330, 2340, 120, 19);

        pilihan6.setBackground(new java.awt.Color(255, 255, 255));
        pilihan6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan6.setForeground(new java.awt.Color(50, 50, 50));
        pilihan6.setText("Latihan fisik lanjutan");
        pilihan6.setName("pilihan6"); // NOI18N
        FormInput.add(pilihan6);
        pilihan6.setBounds(330, 2360, 130, 19);

        pilihan7.setBackground(new java.awt.Color(255, 255, 255));
        pilihan7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan7.setForeground(new java.awt.Color(50, 50, 50));
        pilihan7.setText("Pendampingan tenaga khusus di rumah");
        pilihan7.setName("pilihan7"); // NOI18N
        FormInput.add(pilihan7);
        pilihan7.setBounds(330, 2380, 220, 19);

        pilihan8.setBackground(new java.awt.Color(255, 255, 255));
        pilihan8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        pilihan8.setForeground(new java.awt.Color(50, 50, 50));
        pilihan8.setText("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)");
        pilihan8.setName("pilihan8"); // NOI18N
        FormInput.add(pilihan8);
        pilihan8.setBounds(330, 2400, 370, 19);

        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel289.setText("IX. SKRINING PERENCANAAN PEMULANGAN");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(10, 2130, 380, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 2420, 880, 1);

        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel297.setText("n. wajib :");
        jLabel297.setName("jLabel297"); // NOI18N
        FormInput.add(jLabel297);
        jLabel297.setBounds(40, 1470, 50, 23);

        Wajib.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Baligh", "Non baligh" }));
        Wajib.setName("Wajib"); // NOI18N
        Wajib.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WajibKeyPressed(evt);
            }
        });
        FormInput.add(Wajib);
        Wajib.setBounds(100, 1470, 100, 23);

        KetHalanganLain.setFocusTraversalPolicyProvider(true);
        KetHalanganLain.setName("KetHalanganLain"); // NOI18N
        KetHalanganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetHalanganLainKeyPressed(evt);
            }
        });
        FormInput.add(KetHalanganLain);
        KetHalanganLain.setBounds(300, 1470, 210, 23);

        jLabel216.setText("p. toharah :");
        jLabel216.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel216.setName("jLabel216"); // NOI18N
        FormInput.add(jLabel216);
        jLabel216.setBounds(510, 1470, 60, 23);

        Toharah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tayamum", "Wudhu", "Lain-lain" }));
        Toharah.setName("Toharah"); // NOI18N
        Toharah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToharahKeyPressed(evt);
            }
        });
        FormInput.add(Toharah);
        Toharah.setBounds(580, 1470, 100, 23);

        jLabel298.setText("o. halangan lain :");
        jLabel298.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel298.setName("jLabel298"); // NOI18N
        FormInput.add(jLabel298);
        jLabel298.setBounds(200, 1470, 90, 23);

        jLabel217.setText("q. sholat :");
        jLabel217.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(690, 1470, 50, 23);

        Sholat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berdiri", "Duduk", "Berbaring" }));
        Sholat.setName("Sholat"); // NOI18N
        Sholat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SholatKeyPressed(evt);
            }
        });
        FormInput.add(Sholat);
        Sholat.setBounds(740, 1470, 100, 23);

        jLabel299.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel299.setText("r. motivasi kesembuhan dan ibadah :");
        jLabel299.setName("jLabel299"); // NOI18N
        jLabel299.setPreferredSize(new java.awt.Dimension(180, 14));
        FormInput.add(jLabel299);
        jLabel299.setBounds(40, 1500, 190, 23);

        MotivasiKesembuhanIbadah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        MotivasiKesembuhanIbadah.setName("MotivasiKesembuhanIbadah"); // NOI18N
        MotivasiKesembuhanIbadah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MotivasiKesembuhanIbadahKeyPressed(evt);
            }
        });
        FormInput.add(MotivasiKesembuhanIbadah);
        MotivasiKesembuhanIbadah.setBounds(230, 1500, 60, 23);

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
        FormMasalahRencana.setLayout(new java.awt.GridLayout(5, 0, 1, 1));

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Riwayat Persalinan :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        tbRiwayatKehamilan1.setName("tbRiwayatKehamilan1"); // NOI18N
        scrollPane9.setViewportView(tbRiwayatKehamilan1);

        FormMasalahRencana.add(scrollPane9);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbMasalahDetail.setName("tbMasalahDetail"); // NOI18N
        Scroll9.setViewportView(tbMasalahDetail);

        FormMasalahRencana.add(Scroll9);

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbRencanaDetail.setName("tbRencanaDetail"); // NOI18N
        Scroll10.setViewportView(tbRencanaDetail);

        FormMasalahRencana.add(Scroll10);

        scrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Masalah Keperawatan Kebidanan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane8.setName("scrollPane8"); // NOI18N

        DetailMasalah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DetailMasalah.setColumns(20);
        DetailMasalah.setRows(5);
        DetailMasalah.setName("DetailMasalah"); // NOI18N
        scrollPane8.setViewportView(DetailMasalah);

        FormMasalahRencana.add(scrollPane8);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Rencana Keperawatan Kebidanan Lainnya :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().isEmpty()) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (KdPetugas.getText().trim().isEmpty() || NmPetugas.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Pengkaji 1");
        } else if (KdPetugas2.getText().trim().isEmpty() || NmPetugas2.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas2, "Pegkaji 2");
        } else if (KdDPJP.getText().trim().isEmpty() || NmDPJP.getText().trim().
                isEmpty()) {
            Valid.textKosong(BtnDPJP, "DPJP");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else if (RPK.getText().trim().isEmpty()) {
            Valid.textKosong(RPK, "Riwayat Penyakit Keluarga");
        } else if (PSK.getText().trim().isEmpty()) {
            Valid.textKosong(PSK, "Penyakit Selama Kehamilan");
        } else if (RBedah.getText().trim().isEmpty()) {
            Valid.textKosong(RBedah, "Riwayat Pembedahan");
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
        } else if (KdPetugas.getText().trim().isEmpty() || NmPetugas.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas, "Pengkaji 1");
        } else if (KdPetugas2.getText().trim().isEmpty() || NmPetugas2.getText().
                trim().isEmpty()) {
            Valid.textKosong(BtnPetugas2, "Pegkaji 2");
        } else if (KdDPJP.getText().trim().isEmpty() || NmDPJP.getText().trim().
                isEmpty()) {
            Valid.textKosong(BtnDPJP, "DPJP");
        } else if (KeluhanUtama.getText().trim().isEmpty()) {
            Valid.textKosong(KeluhanUtama, "Keluhan Utama");
        } else if (RPK.getText().trim().isEmpty()) {
            Valid.textKosong(RPK, "Riwayat Penyakit Keluarga");
        } else if (PSK.getText().trim().isEmpty()) {
            Valid.textKosong(PSK, "Penyakit Selama Kehamilan");
        } else if (RBedah.getText().trim().isEmpty()) {
            Valid.textKosong(RBedah, "Riwayat Pembedahan");
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
                File g = new File("file2.css");
                try (BufferedWriter bg = new BufferedWriter(new FileWriter(g))) {
                    bg.write(
                            ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"
                            + ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                            + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                    );
                }

                File f;
                BufferedWriter bw;

                ps = koneksi.prepareStatement(
                        "select penilaian_awal_keperawatan_kebidanan_ranap.no_rawat,penilaian_awal_keperawatan_kebidanan_ranap.tanggal,penilaian_awal_keperawatan_kebidanan_ranap.informasi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.tiba_diruang_rawat,penilaian_awal_keperawatan_kebidanan_ranap.cara_masuk,penilaian_awal_keperawatan_kebidanan_ranap.keluhan,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.rpk,penilaian_awal_keperawatan_kebidanan_ranap.psk,penilaian_awal_keperawatan_kebidanan_ranap.rp,penilaian_awal_keperawatan_kebidanan_ranap.alergi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.keterangan_komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_umur,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_banyaknya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_siklus,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_ket_siklus,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_dirasakan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_status,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_status,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia1,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia1,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia3,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia3,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_g,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_p,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_a,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_hidup,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_hpht,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_usiahamil,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_tp,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_imunisasi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_anc,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ancke,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ket_ancke,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_muda,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_tua,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_komplikasi,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_ket_komplikasi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_kapaberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_alasanberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_genekologi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_merokok,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_merokok,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_alkohol,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_alkohol,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_narkoba,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_mental,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_keadaan_umum,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_gcs,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_td,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_nadi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_rr,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_suhu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_spo2,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_bb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lila,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tfu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tbj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_letak,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_presentasi,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_penurunan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_his,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_kekuatan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_djj,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_djj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_portio,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_pembukaan,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ketuban,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_hodge,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_panggul,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lakmus,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_lakmus,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ctg,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_ctg,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_kepala,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_muka,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mata,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_hidung,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_telinga,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mulut,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_leher,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_dada,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_perut,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_genitalia,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_ekstrimitas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_aktifitas,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_aktivitas,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ambulasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_atas,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_bawah,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_bawah,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_menggenggam,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_kemampuan_menggenggam,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_gangguan_fungsi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_kondisipsiko,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_adakah_prilaku,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_adakah_prilaku,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_hubungan_pasien,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_tinggal_dengan,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_tinggal_dengan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_budaya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_budaya,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_pend_pj,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_edukasi_pada,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_edukasi_pada,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_penyebab,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_lokasi,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_skala,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_waktu,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_diberitahukan_dokter,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala1,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai1,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala3,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai3,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala4,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai4,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala6,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai6,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_totalnilai,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi1,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi1,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi2,penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi2,bahasa_pasien.nama_bahasa,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.nilai_total_gizi,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_ket_diagnosa_khusus,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_jam_diketahui_dietisen,"
                        + "penilaian_awal_keperawatan_kebidanan_ranap.rencana,penilaian_awal_keperawatan_kebidanan_ranap.nip1,penilaian_awal_keperawatan_kebidanan_ranap.nip2,penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter, "
                        + "pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab "
                        + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "inner join penilaian_awal_keperawatan_kebidanan_ranap on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan_ranap.no_rawat "
                        + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_kebidanan_ranap.nip1=pengkaji1.nip "
                        + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_kebidanan_ranap.nip2=pengkaji2.nip "
                        + "inner join dokter on penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter=dokter.kd_dokter "
                        + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                        + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                        + "penilaian_awal_keperawatan_kebidanan_ranap.tanggal between ? and ? "
                        + (TCari.getText().trim().isEmpty() ? "" : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_kebidanan_ranap.nip1 like ? or pengkaji1.nama like ? or penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter like ? or dokter.nm_dokter like ?)")
                        + " order by penilaian_awal_keperawatan_kebidanan_ranap.tanggal");
                try {
                    ps.setString(1, Valid.
                            SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                    ps.setString(2, Valid.
                            SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
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
                    pilihan = (String) JOptionPane.showInputDialog(null,
                            "Silahkan pilih laporan..!", "Pilihan Cetak",
                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{
                                "Laporan 1 (HTML)", "Laporan 2 (WPS)",
                                "Laporan 3 (CSV)"}, "Laporan 1 (HTML)");
                    switch (pilihan) {
                        case "Laporan 1 (HTML)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>No.Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>No.RM</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>Nama Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Tgl.Lahir</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='25px'>J.K.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>NIP Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>NIP Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Kode DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='117px'>Tgl.Asuhan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Anamnesis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Tiba di Ruang Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Cara Masuk</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='250px'>Keluhan Utama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Penyakit Selama Kehamilan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Penyakit Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Pembedahan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Riwayat Alergi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='175px'>Komplikasi Kehamilan Sebelumnya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='180px'>Keterangan Komplikasi Sebelumnya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='84px'>Umur Menarche</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='78px'>Lamanya Mens</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='108px'>Banyaknya Pembalut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Siklus Haid</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='80px'>Ket.Siklus Haid</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='136px'>Dirasakan Saat Menstruasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='81px'>Status Menikah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='54px'>Jml.Nikah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>G</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>P</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>A</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='37px'>Hidup</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>HPHT</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Usia Hamil</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Tg.Perkiraan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='97px'>Riwayat Imunisasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>ANC</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>ANC Ke</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Ket. ANC</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>Keluhan Hamil Muda</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='98px'>Keluhan Hamil Tua</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Keluarga Berencana</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Lamanya KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Komplikasi KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>Ket Komplikasi KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Berhenti KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>Alasan Berhenti KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='104px'>Riwayat Genekologi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Obat/Vitamin</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Keterangan Obat/Vitamin</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Merokok</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Rokok/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>Alkohol</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='68px'>Alkohol/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='103px'>Obat Tidur/Narkoba</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>Kesadaran Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='84px'>Keadaan Umum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>GCS(E,V,M)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>TD</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>Nadi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>RR</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>Suhu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>SpO2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>BB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>TB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>LILA</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>TFU</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>TBJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='27px'>GD</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>Letak</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Presentasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Penurunan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='75px'>Kontraksi/HIS</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Kekuatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Lamanya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>DJJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Keterangan DJJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Portio</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>Serviks</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='53px'>Ketuban</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='53px'>Hodge</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='145px'>Panggul</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Inspekulo</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Inspekulo</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Lakmus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Lakmus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>CTG</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan CTG</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='80px'>Kepala</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Muka</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='128px'>Mata</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Hidung</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Telinga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Mulut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>Leher</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Dada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Perut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Genitalia</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Ekstremitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>a. Aktivitas Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>b. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>Ket. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='68px'>c. Aktifitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='101px'>d. Alat Ambulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>e. Ekstrimitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Ket. Ekstrimitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>f. Ekstrimitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='118px'>Ket. Ekstrimitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='154px'>g. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='164px'>Ket. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='133px'>h. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='143px'>Ket. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>i. Kesimpulan Gangguan Fungsi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>a. Kondisi Psikologis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='98px'>b. Adakah Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Keterangan Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='156px'>c. Gangguan Jiwa di Masa Lalu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='198px'>d. Hubungan dengan Anggota Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>e. Agama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='95px'>f. Tinggal Dengan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='103px'>Keterangan Tinggal</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>g. Pekerjaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='130px'>h. Pembayaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='130px'>i. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='142px'>Ket. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>j. Bahasa Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='108px'>k. Pendidikan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>l. Pendidikan P.J.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='148px'>m. Edukasi Diberikan Kepada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='148px'>Keterangan Edukasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='83px'>Penilaian Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='83px'>Penyebab</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Penyebab</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Kualitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Keterangan Kualitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Lokasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>Menyebar</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>Skala Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Durasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Nyeri hilang bila</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='126px'>Keterangan Nyeri Hilang</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Diberitahukan Dokter</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Pada Jam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>1. Riwayat Jatuh</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='225px'>2. Diagnosis Sekunder (&GreaterEqual; 2 Diagnosis Medis)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='218px'>3. Alat Bantu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>4. Terpasang Infuse</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='225px'>5. Gaya Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='215px'>6. Status Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='58px'>Total Nilai</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='380px'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Skor 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='317px'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Skor 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='58px'>Total Skor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='165px'>Pasien dengan diagnosis khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='149px'>Keterangan Diagnosa Khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='209px'>Sudah dibaca dan diketahui oleh Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Jam Dibaca Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='200px'>Asesmen/Penilaian Kebidanan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='200px'>Rencana Kebidanan</td>"
                                    + "</tr>"
                            );
                            while (rs.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top'>" + rs.getString(
                                                "no_rawat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "no_rkm_medis") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nm_pasien") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tgl_lahir") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "jk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nip1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkaji1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nip2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkaji2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "kd_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nm_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tanggal") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "informasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tiba_diruang_rawat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "cara_masuk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "keluhan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "psk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rpk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rp") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "alergi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "komplikasi_sebelumnya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "keterangan_komplikasi_sebelumnya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_umur") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_banyaknya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_siklus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_ket_siklus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_dirasakan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_status") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_status") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_g") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_p") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_a") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_hidup") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_hpht") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_usiahamil") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_tp") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_imunisasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_anc") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_ancke") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_ket_ancke") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_keluhan_hamil_muda") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_keluhan_hamil_tua") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_komplikasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_ket_komplikasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_kapaberhenti") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_alasanberhenti") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_genekologi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_obat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_obat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_merokok") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_merokok") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_alkohol") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_alkohol") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_narkoba") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_mental") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_keadaan_umum") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_gcs") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_td") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_nadi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_rr") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_suhu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_spo2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_bb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lila") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tfu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tbj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_letak") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_presentasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_penurunan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_penurunan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_his") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_kekuatan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_djj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_djj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_portio") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_pembukaan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ketuban") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_hodge") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_panggul") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_inspekulo") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_inspekulo") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lakmus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_lakmus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ctg") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_ctg") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_kepala") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_muka") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_mata") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_hidung") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_telinga") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_mulut") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_leher") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_dada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_perut") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_genitalia") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_ekstrimitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kemampuan_aktifitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_berjalan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_berjalan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_aktivitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ambulasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_atas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_ekstrimitas_atas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_bawah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_ekstrimitas_bawah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kemampuan_menggenggam") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_kemampuan_menggenggam") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_koordinasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_koordinasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_gangguan_fungsi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_kondisipsiko") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_adakah_prilaku") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_adakah_prilaku") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_gangguan_jiwa") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_hubungan_pasien") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "agama") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_tinggal_dengan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_tinggal_dengan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pekerjaan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "png_jawab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_budaya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_budaya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nama_bahasa") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pnd") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_pend_pj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_edukasi_pada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_edukasi_pada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_penyebab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_penyebab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_kualitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_kualitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_lokasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_menyebar") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_skala") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_waktu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_hilang") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_hilang") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_jam_diberitahukan_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala4") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai4") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala5") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai5") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala6") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai6") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_totalnilai") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_gizi1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_gizi2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_total_gizi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_diagnosa_khusus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_ket_diagnosa_khusus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_diketahui_dietisen") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_jam_diketahui_dietisen") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "masalah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rencana") + "</td>"
                                        + "</tr>"
                                );
                            }
                            f = new File(
                                    "RMPenilaianAwalKeperawatanKebidananRanap.html");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write("<html>"
                                    + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                    + "<body>"
                                    + "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + htmlContent.toString()
                                    + "</table>"
                                    + "</body>"
                                    + "</html>"
                            );

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 2 (WPS)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>No.Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>No.RM</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>Nama Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Tgl.Lahir</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='25px'>J.K.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>NIP Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama Pengkaji 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>NIP Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama Pengkaji 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Kode DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Nama DPJP</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='117px'>Tgl.Asuhan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Anamnesis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Tiba di Ruang Rawat</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Cara Masuk</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='250px'>Keluhan Utama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Penyakit Selama Kehamilan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Penyakit Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Pembedahan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Riwayat Alergi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='175px'>Komplikasi Kehamilan Sebelumnya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='180px'>Keterangan Komplikasi Sebelumnya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='84px'>Umur Menarche</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='78px'>Lamanya Mens</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='108px'>Banyaknya Pembalut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Siklus Haid</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='80px'>Ket.Siklus Haid</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='136px'>Dirasakan Saat Menstruasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='81px'>Status Menikah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='54px'>Jml.Nikah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='96px'>Usia Perkawinan 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>Status Perkawinan 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>G</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>P</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='22px'>A</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='37px'>Hidup</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>HPHT</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='59px'>Usia Hamil</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Tg.Perkiraan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='97px'>Riwayat Imunisasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>ANC</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>ANC Ke</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Ket. ANC</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>Keluhan Hamil Muda</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='98px'>Keluhan Hamil Tua</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Riwayat Keluarga Berencana</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>Lamanya KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Komplikasi KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>Ket Komplikasi KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>Berhenti KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>Alasan Berhenti KB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='104px'>Riwayat Genekologi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Obat/Vitamin</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='150px'>Keterangan Obat/Vitamin</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Merokok</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Rokok/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>Alkohol</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='68px'>Alkohol/Hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='103px'>Obat Tidur/Narkoba</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='105px'>Kesadaran Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='84px'>Keadaan Umum</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>GCS(E,V,M)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>TD</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>Nadi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>RR</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>Suhu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>SpO2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>BB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='35px'>TB</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>LILA</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>TFU</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>TBJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='27px'>GD</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>Letak</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Presentasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Penurunan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='75px'>Kontraksi/HIS</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='65px'>Kekuatan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Lamanya</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>DJJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='85px'>Keterangan DJJ</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Portio</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='45px'>Serviks</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='53px'>Ketuban</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='53px'>Hodge</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='145px'>Panggul</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Inspekulo</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Inspekulo</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Lakmus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Lakmus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>CTG</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan CTG</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='80px'>Kepala</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Muka</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='128px'>Mata</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Hidung</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Telinga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='50px'>Mulut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>Leher</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Dada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Perut</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Genitalia</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Ekstremitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='120px'>a. Aktivitas Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>b. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='140px'>Ket. Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='68px'>c. Aktifitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='101px'>d. Alat Ambulasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>e. Ekstrimitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Ket. Ekstrimitas Atas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>f. Ekstrimitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='118px'>Ket. Ekstrimitas Bawah</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='154px'>g. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='164px'>Ket. Kemampuan Menggenggam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='133px'>h. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='143px'>Ket. Kemampuan Koordinasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>i. Kesimpulan Gangguan Fungsi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='106px'>a. Kondisi Psikologis</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='98px'>b. Adakah Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Keterangan Perilaku</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='156px'>c. Gangguan Jiwa di Masa Lalu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='198px'>d. Hubungan dengan Anggota Keluarga</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='70px'>e. Agama</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='95px'>f. Tinggal Dengan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='103px'>Keterangan Tinggal</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>g. Pekerjaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='130px'>h. Pembayaran</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='130px'>i. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='142px'>Ket. Nilai-nilai Kepercayaan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>j. Bahasa Sehari-hari</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='108px'>k. Pendidikan Pasien</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>l. Pendidikan P.J.</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='148px'>m. Edukasi Diberikan Kepada</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='148px'>Keterangan Edukasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='83px'>Penilaian Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='83px'>Penyebab</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='115px'>Keterangan Penyebab</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Kualitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Keterangan Kualitas</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='100px'>Lokasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='55px'>Menyebar</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='63px'>Skala Nyeri</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Durasi</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='87px'>Nyeri hilang bila</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='126px'>Keterangan Nyeri Hilang</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='110px'>Diberitahukan Dokter</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='60px'>Pada Jam</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='90px'>1. Riwayat Jatuh</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='225px'>2. Diagnosis Sekunder (&GreaterEqual; 2 Diagnosis Medis)</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='218px'>3. Alat Bantu</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 3</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='160px'>4. Terpasang Infuse</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 4</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='225px'>5. Gaya Berjalan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 5</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='215px'>6. Status Mental</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Nilai 6</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='58px'>Total Nilai</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='380px'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Skor 1</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='317px'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='40px'>Skor 2</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='58px'>Total Skor</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='165px'>Pasien dengan diagnosis khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='149px'>Keterangan Diagnosa Khusus</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='209px'>Sudah dibaca dan diketahui oleh Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='107px'>Jam Dibaca Dietisen</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='200px'>Asesmen/Penilaian Kebidanan</td>"
                                    + "<td valign='middle' bgcolor='#FFFAFA' align='center' width='200px'>Rencana Kebidanan</td>"
                                    + "</tr>"
                            );
                            while (rs.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top'>" + rs.getString(
                                                "no_rawat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "no_rkm_medis") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nm_pasien") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tgl_lahir") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "jk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nip1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkaji1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nip2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkaji2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "kd_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nm_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tanggal") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "informasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "tiba_diruang_rawat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "cara_masuk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "keluhan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "psk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rpk") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rp") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "alergi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "komplikasi_sebelumnya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "keterangan_komplikasi_sebelumnya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_umur") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_banyaknya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_siklus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_ket_siklus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_mens_dirasakan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_status") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_status") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_usia3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_perkawinan_ket_usia3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_g") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_p") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_a") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_persalinan_hidup") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_hpht") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_usiahamil") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_tp") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_imunisasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_anc") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_ancke") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_ket_ancke") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_keluhan_hamil_muda") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_hamil_keluhan_hamil_tua") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_komplikasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_ket_komplikasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_kapaberhenti") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kb_alasanberhenti") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_genekologi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_obat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_obat") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_merokok") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_merokok") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_alkohol") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_ket_alkohol") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_kebiasaan_narkoba") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_mental") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_keadaan_umum") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_gcs") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_td") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_nadi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_rr") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_suhu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_spo2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_bb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tb") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lila") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tfu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_tbj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_letak") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_presentasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_penurunan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_penurunan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_his") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_kekuatan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lamanya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_djj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_djj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_portio") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_pembukaan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ketuban") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_hodge") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_panggul") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_inspekulo") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_inspekulo") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_lakmus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_lakmus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ctg") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_kebidanan_ket_ctg") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_kepala") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_muka") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_mata") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_hidung") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_telinga") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_mulut") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_leher") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_dada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_perut") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_genitalia") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pemeriksaan_umum_ekstrimitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kemampuan_aktifitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_berjalan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_berjalan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_aktivitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ambulasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_atas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_ekstrimitas_atas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ekstrimitas_bawah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_ekstrimitas_bawah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_kemampuan_menggenggam") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_kemampuan_menggenggam") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_koordinasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_ket_koordinasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pengkajian_fungsi_gangguan_fungsi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_kondisipsiko") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_adakah_prilaku") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_adakah_prilaku") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_gangguan_jiwa") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_hubungan_pasien") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "agama") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_tinggal_dengan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_tinggal_dengan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pekerjaan") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "png_jawab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_budaya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_budaya") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nama_bahasa") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "pnd") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_pend_pj") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_edukasi_pada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "riwayat_psiko_ket_edukasi_pada") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_penyebab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_penyebab") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_kualitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_kualitas") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_lokasi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_menyebar") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_skala") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_waktu") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_hilang") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_ket_hilang") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_nyeri_jam_diberitahukan_dokter") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai3") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala4") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai4") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala5") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai5") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_skala6") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_nilai6") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "penilaian_jatuh_totalnilai") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_gizi1") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_gizi2") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "nilai_total_gizi") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_diagnosa_khusus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_ket_diagnosa_khusus") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_diketahui_dietisen") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "skrining_gizi_jam_diketahui_dietisen") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "masalah") + "</td>"
                                        + "<td valign='top'>" + rs.getString(
                                                "rencana") + "</td>"
                                        + "</tr>"
                                );
                            }
                            f = new File(
                                    "RMPenilaianAwalKeperawatanKebidananRanap.wps");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write("<html>"
                                    + "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"
                                    + "<body>"
                                    + "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + htmlContent.toString()
                                    + "</table>"
                                    + "</body>"
                                    + "</html>"
                            );

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                        case "Laporan 3 (CSV)":
                            htmlContent = new StringBuilder();
                            htmlContent.append(
                                    "\"No.Rawat\";\"No.RM\";\"Nama Pasien\";\"Tgl.Lahir\";\"J.K.\";\"NIP Pengkaji 1\";\"Nama Pengkaji 1\";\"NIP Pengkaji 2\";\"Nama Pengkaji 2\";\"Kode DPJP\";\"Nama DPJP\";\"Tgl.Asuhan\";\"Anamnesis\";\"Tiba di Ruang Rawat\";\"Cara Masuk\";\"Keluhan Utama\";\"Penyakit Selama Kehamilan\";\"Riwayat Penyakit Keluarga\";\"Riwayat Pembedahan\";\"Riwayat Alergi\";\"Komplikasi Kehamilan Sebelumnya\";\"Keterangan Komplikasi Sebelumnya\";\"Umur Menarche\";\"Lamanya Mens\";\"Banyaknya Pembalut\";\"Siklus Haid\";\"Ket.Siklus Haid\";\"Dirasakan Saat Menstruasi\";\"Status Menikah\";\"Jml.Nikah\";\"Usia Perkawinan 1\";\"Status Perkawinan 1\";\"Usia Perkawinan 2\";\"Status Perkawinan 2\";\"Usia Perkawinan 3\";\"Status Perkawinan 3\";\"G\";\"P\";\"A\";\"Hidup\";\"HPHT\";\"Usia Hamil\";\"Tg.Perkiraan\";\"Riwayat Imunisasi\";\"ANC\";\"ANC Ke\";\"Ket. ANC\";\"Keluhan Hamil Muda\";\"Keluhan Hamil Tua\";\"Riwayat Keluarga Berencana\";\"Lamanya KB\";\"Komplikasi KB\";\"Ket Komplikasi KB\";\"Berhenti KB\";\"Alasan Berhenti KB\";\"Riwayat Genekologi\";\"Obat/Vitamin\";\"Keterangan Obat/Vitamin\";\"Merokok\";\"Rokok/Hari\";\"Alkohol\";\"Alkohol/Hari\";\"Obat Tidur/Narkoba\";\"Kesadaran Mental\";\"Keadaan Umum\";\"GCS(E,V,M)\";\"TD\";\"Nadi\";\"RR\";\"Suhu\";\"SpO2\";\"BB\";\"TB\";\"LILA\";\"TFU\";\"TBJ\";\"GD\";\"Letak\";\"Presentasi\";\"Penurunan\";\"Kontraksi/HIS\";\"Kekuatan\";\"Lamanya\";\"DJJ\";\"Keterangan DJJ\";\"Portio\";\"Serviks\";\"Ketuban\";\"Hodge\";\"Panggul\";\"Inspekulo\";\"Keterangan Inspekulo\";\"Lakmus\";\"Keterangan Lakmus\";\"CTG\";\"Keterangan CTG\";\"Kepala\";\"Muka\";\"Mata\";\"Hidung\";\"Telinga\";\"Mulut\";\"Leher\";\"Dada\";\"Perut\";\"Genitalia\";\"Ekstremitas\";\"a. Aktivitas Sehari-hari\";\"b. Berjalan\";\"Ket. Berjalan\";\"c. Aktifitas\";\"d. Alat Ambulasi\";\"e. Ekstrimitas Atas\";\"Ket. Ekstrimitas Atas\";\"f. Ekstrimitas Bawah\";\"Ket. Ekstrimitas Bawah\";\"g. Kemampuan Menggenggam\";\"Ket. Kemampuan Menggenggam\";\"h. Kemampuan Koordinasi\";\"Ket. Kemampuan Koordinasi\";\"i. Kesimpulan Gangguan Fungsi\";\"a. Kondisi Psikologis\";\"b. Adakah Perilaku\";\"Keterangan Perilaku\";\"c. Gangguan Jiwa di Masa Lalu\";\"d. Hubungan dengan Anggota Keluarga\";\"e. Agama\";\"f. Tinggal Dengan\";\"Keterangan Tinggal\";\"g. Pekerjaan\";\"h. Pembayaran\";\"i. Nilai-nilai Kepercayaan\";\"Ket. Nilai-nilai Kepercayaan\";\"j. Bahasa Sehari-hari\";\"k. Pendidikan Pasien\";\"l. Pendidikan P.J.\";\"m. Edukasi Diberikan Kepada\";\"Keterangan Edukasi\";\"Penilaian Nyeri\";\"Penyebab\";\"Keterangan Penyebab\";\"Kualitas\";\"Keterangan Kualitas\";\"Lokasi\";\"Menyebar\";\"Skala Nyeri\";\"Durasi\";\"Nyeri hilang bila\";\"Keterangan Nyeri Hilang\";\"Diberitahukan Dokter\";\"Pada Jam\";\"1. Riwayat Jatuh\";\"Nilai 1\";\"2. Diagnosis Sekunder (≥ 2 Diagnosis Medis)\";\"Nilai 2\";\"3. Alat Bantu\";\"Nilai 3\";\"4. Terpasang Infuse\";\"Nilai 4\";\"5. Gaya Berjalan\";\"Nilai 5\";\"6. Status Mental\";\"Nilai 6\";\"Total Nilai\";\"1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?\";\"Skor 1\";\"2. Apakah asupan makan berkurang karena tidak nafsu makan ?\";\"Skor 2\";\"Total Skor\";\"Pasien dengan diagnosis khusus\";\"Keterangan Diagnosa Khusus\";\"Sudah dibaca dan diketahui oleh Dietisen\";\"Jam Dibaca Dietisen\";\"Asesmen/Penilaian Kebidanan\";\"Rencana Kebidanan\"\n"
                            );
                            while (rs.next()) {
                                htmlContent.append(
                                        "\"" + rs.getString("no_rawat") + "\";\"" + " " + rs.
                                        getString("no_rkm_medis") + "\";\"" + rs.
                                        getString("nm_pasien") + "\";\"" + rs.
                                        getString("tgl_lahir") + "\";\"" + rs.
                                        getString("jk") + "\";\"" + rs.
                                        getString("nip1") + "\";\"" + rs.
                                        getString("pengkaji1") + "\";\"" + rs.
                                        getString("nip2") + "\";\"" + rs.
                                        getString("pengkaji2") + "\";\"" + rs.
                                        getString("kd_dokter") + "\";\"" + rs.
                                        getString("nm_dokter") + "\";\"" + rs.
                                        getString("tanggal") + "\";\"" + rs.
                                        getString("informasi") + "\";\"" + rs.
                                        getString("tiba_diruang_rawat") + "\";\"" + rs.
                                        getString("cara_masuk") + "\";\"" + rs.
                                        getString("keluhan") + "\";\"" + rs.
                                        getString("psk") + "\";\"" + rs.
                                        getString("rpk") + "\";\"" + rs.
                                        getString("rp") + "\";\"" + rs.
                                        getString("alergi") + "\";\"" + rs.
                                        getString("komplikasi_sebelumnya") + "\";\"" + rs.
                                        getString(
                                                "keterangan_komplikasi_sebelumnya") + "\";\"" + rs.
                                                getString("riwayat_mens_umur") + "\";\"" + rs.
                                        getString("riwayat_mens_lamanya") + "\";\"" + rs.
                                        getString("riwayat_mens_banyaknya") + "\";\"" + rs.
                                        getString("riwayat_mens_siklus") + "\";\"" + rs.
                                        getString("riwayat_mens_ket_siklus") + "\";\"" + rs.
                                        getString("riwayat_mens_dirasakan") + "\";\"" + rs.
                                        getString("riwayat_perkawinan_status") + "\";\"" + rs.
                                        getString(
                                                "riwayat_perkawinan_ket_status") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_usia1") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_ket_usia1") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_usia2") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_ket_usia2") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_usia3") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_perkawinan_ket_usia3") + "\";\"" + rs.
                                                getString("riwayat_persalinan_g") + "\";\"" + rs.
                                        getString("riwayat_persalinan_p") + "\";\"" + rs.
                                        getString("riwayat_persalinan_a") + "\";\"" + rs.
                                        getString("riwayat_persalinan_hidup") + "\";\"" + rs.
                                        getString("riwayat_hamil_hpht") + "\";\"" + rs.
                                        getString("riwayat_hamil_usiahamil") + "\";\"" + rs.
                                        getString("riwayat_hamil_tp") + "\";\"" + rs.
                                        getString("riwayat_hamil_imunisasi") + "\";\"" + rs.
                                        getString("riwayat_hamil_anc") + "\";\"" + rs.
                                        getString("riwayat_hamil_ancke") + "\";\"" + rs.
                                        getString("riwayat_hamil_ket_ancke") + "\";\"" + rs.
                                        getString(
                                                "riwayat_hamil_keluhan_hamil_muda") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_hamil_keluhan_hamil_tua") + "\";\"" + rs.
                                                getString("riwayat_kb") + "\";\"" + rs.
                                        getString("riwayat_kb_lamanya") + "\";\"" + rs.
                                        getString("riwayat_kb_komplikasi") + "\";\"" + rs.
                                        getString("riwayat_kb_ket_komplikasi") + "\";\"" + rs.
                                        getString("riwayat_kb_kapaberhenti") + "\";\"" + rs.
                                        getString("riwayat_kb_alasanberhenti") + "\";\"" + rs.
                                        getString("riwayat_genekologi") + "\";\"" + rs.
                                        getString("riwayat_kebiasaan_obat") + "\";\"" + rs.
                                        getString("riwayat_kebiasaan_ket_obat") + "\";\"" + rs.
                                        getString("riwayat_kebiasaan_merokok") + "\";\"" + rs.
                                        getString(
                                                "riwayat_kebiasaan_ket_merokok") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_kebiasaan_alkohol") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_kebiasaan_ket_alkohol") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_kebiasaan_narkoba") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_mental") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_keadaan_umum") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_gcs") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_td") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_nadi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_rr") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_suhu") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_spo2") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_bb") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_tb") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_lila") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_tfu") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_tbj") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_letak") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_presentasi") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_penurunan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_penurunan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_his") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_kekuatan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_lamanya") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_djj") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ket_djj") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_portio") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_pembukaan") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ketuban") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_hodge") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_panggul") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_inspekulo") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ket_inspekulo") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_lakmus") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ket_lakmus") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ctg") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_kebidanan_ket_ctg") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_kepala") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_muka") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_mata") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_hidung") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_telinga") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_mulut") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_leher") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_dada") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_perut") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_genitalia") + "\";\"" + rs.
                                                getString(
                                                        "pemeriksaan_umum_ekstrimitas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_kemampuan_aktifitas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_berjalan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ket_berjalan") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_aktivitas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ambulasi") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_atas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ket_ekstrimitas_atas") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ekstrimitas_bawah") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ket_ekstrimitas_bawah") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_kemampuan_menggenggam") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ket_kemampuan_menggenggam") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_koordinasi") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_ket_koordinasi") + "\";\"" + rs.
                                                getString(
                                                        "pengkajian_fungsi_gangguan_fungsi") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_kondisipsiko") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_adakah_prilaku") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_ket_adakah_prilaku") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_gangguan_jiwa") + "\";\"" + rs.
                                                getString(
                                                        "riwayat_psiko_hubungan_pasien") + "\";\"" + rs.
                                                getString("agama") + "\";\"" + rs.
                                        getString("riwayat_psiko_tinggal_dengan") + "\";\"" + rs.
                                        getString(
                                                "riwayat_psiko_ket_tinggal_dengan") + "\";\"" + rs.
                                                getString("pekerjaan") + "\";\"" + rs.
                                        getString("png_jawab") + "\";\"" + rs.
                                        getString("riwayat_psiko_budaya") + "\";\"" + rs.
                                        getString("riwayat_psiko_ket_budaya") + "\";\"" + rs.
                                        getString("nama_bahasa") + "\";\"" + rs.
                                        getString("pnd") + "\";\"" + rs.
                                        getString("riwayat_psiko_pend_pj") + "\";\"" + rs.
                                        getString("riwayat_psiko_edukasi_pada") + "\";\"" + rs.
                                        getString(
                                                "riwayat_psiko_ket_edukasi_pada") + "\";\"" + rs.
                                                getString("penilaian_nyeri") + "\";\"" + rs.
                                        getString("penilaian_nyeri_penyebab") + "\";\"" + rs.
                                        getString("penilaian_nyeri_ket_penyebab") + "\";\"" + rs.
                                        getString("penilaian_nyeri_kualitas") + "\";\"" + rs.
                                        getString("penilaian_nyeri_ket_kualitas") + "\";\"" + rs.
                                        getString("penilaian_nyeri_lokasi") + "\";\"" + rs.
                                        getString("penilaian_nyeri_menyebar") + "\";\"" + rs.
                                        getString("penilaian_nyeri_skala") + "\";\"" + rs.
                                        getString("penilaian_nyeri_waktu") + "\";\"" + rs.
                                        getString("penilaian_nyeri_hilang") + "\";\"" + rs.
                                        getString("penilaian_nyeri_ket_hilang") + "\";\"" + rs.
                                        getString(
                                                "penilaian_nyeri_diberitahukan_dokter") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_nyeri_jam_diberitahukan_dokter") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai1") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai2") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai3") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai4") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai5") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_skala6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_nilai6") + "\";\"" + rs.
                                                getString(
                                                        "penilaian_jatuh_totalnilai") + "\";\"" + rs.
                                                getString("skrining_gizi1") + "\";\"" + rs.
                                        getString("nilai_gizi1") + "\";\"" + rs.
                                        getString("skrining_gizi2") + "\";\"" + rs.
                                        getString("nilai_gizi2") + "\";\"" + rs.
                                        getString("nilai_total_gizi") + "\";\"" + rs.
                                        getString(
                                                "skrining_gizi_diagnosa_khusus") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_ket_diagnosa_khusus") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_diketahui_dietisen") + "\";\"" + rs.
                                                getString(
                                                        "skrining_gizi_jam_diketahui_dietisen") + "\";\"" + rs.
                                                getString("masalah") + "\";\"" + rs.
                                        getString("rencana") + "\"\n"
                                );
                            }
                            f = new File(
                                    "RMPenilaianAwalKeperawatanKebidananRanap.csv");
                            bw = new BufferedWriter(new FileWriter(f));
                            bw.write(htmlContent.toString());

                            bw.close();
                            Desktop.getDesktop().browse(f.toURI());
                            break;
                    }
                } catch (HeadlessException | IOException | SQLException e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (IOException | SQLException e) {
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

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            if (Valid.daysOld("./cache/masalahkeperawatankebidanan.iyem") < 8) {
                tampilMasalah2();
            } else {
                tampilMasalah();
            }
        } catch (Exception e) {
        }

        try {
            if (Valid.daysOld("./cache/rencanakeperawatankebidanan.iyem") >= 7) {
                tampilRencana();
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
                    tbObat.getValueAt(tbObat.getSelectedRow(), 5).toString());
            param.put("finger",
                    "Dikeluarkan di " + akses.getnamars() + ", Kabupaten/Kota " + akses.
                    getkabupatenrs() + "\nDitandatangani secara elektronik oleh " + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 6).toString() + "\nID " + (finger.
                    isEmpty() ? tbObat.getValueAt(tbObat.getSelectedRow(), 5).
                                    toString() : finger) + "\n" + Valid.SetTgl3(
                            tbObat.getValueAt(tbObat.getSelectedRow(), 11).
                                    toString()));
            try {
                ps = koneksi.prepareStatement(
                        "select * from riwayat_persalinan_pasien where no_rkm_medis=? order by tgl_thn");
                try {
                    ps.setString(1, tbObat.
                            getValueAt(tbObat.getSelectedRow(), 1).toString());
                    rs = ps.executeQuery();
                    i = 1;
                    while (rs.next()) {
                        param.put("no" + i, i + "");
                        param.put("tgl" + i, rs.getString("tgl_thn"));
                        param.put("tempatpersalinan" + i, rs.getString(
                                "tempat_persalinan"));
                        param.put("usiahamil" + i, rs.getString("usia_hamil"));
                        param.put("jenispersalinan" + i, rs.getString(
                                "jenis_persalinan"));
                        param.put("penolong" + i, rs.getString("penolong"));
                        param.put("penyulit" + i, rs.getString("penyulit"));
                        param.put("jk" + i, rs.getString("jk"));
                        param.put("bbpb" + i, rs.getString("bbpb"));
                        param.put("keadaan" + i, rs.getString("keadaan"));
                        i++;
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

            Valid.MyReportqry("rptCetakPenilaianAwalKebidananRanap.jasper",
                    "report",
                    "::[ Laporan Penilaian Awal Ralan Kebidanan & Kandungan ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,penilaian_awal_keperawatan_kebidanan.tanggal,"
                    + "penilaian_awal_keperawatan_kebidanan.informasi,penilaian_awal_keperawatan_kebidanan.td,penilaian_awal_keperawatan_kebidanan.nadi,penilaian_awal_keperawatan_kebidanan.rr,penilaian_awal_keperawatan_kebidanan.suhu,penilaian_awal_keperawatan_kebidanan.bb,"
                    + "penilaian_awal_keperawatan_kebidanan.tb,penilaian_awal_keperawatan_kebidanan.nadi,penilaian_awal_keperawatan_kebidanan.rr,penilaian_awal_keperawatan_kebidanan.suhu,penilaian_awal_keperawatan_kebidanan.gcs,penilaian_awal_keperawatan_kebidanan.bb,"
                    + "penilaian_awal_keperawatan_kebidanan.tb,penilaian_awal_keperawatan_kebidanan.bmi,penilaian_awal_keperawatan_kebidanan.lila,penilaian_awal_keperawatan_kebidanan.tfu,penilaian_awal_keperawatan_kebidanan.tbj,penilaian_awal_keperawatan_kebidanan.letak,"
                    + "penilaian_awal_keperawatan_kebidanan.presentasi,penilaian_awal_keperawatan_kebidanan.penurunan,penilaian_awal_keperawatan_kebidanan.his,penilaian_awal_keperawatan_kebidanan.kekuatan,penilaian_awal_keperawatan_kebidanan.lamanya,penilaian_awal_keperawatan_kebidanan.bjj,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_bjj,penilaian_awal_keperawatan_kebidanan.portio,penilaian_awal_keperawatan_kebidanan.serviks,penilaian_awal_keperawatan_kebidanan.ketuban,penilaian_awal_keperawatan_kebidanan.hodge,penilaian_awal_keperawatan_kebidanan.inspekulo,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_inspekulo,penilaian_awal_keperawatan_kebidanan.ctg,penilaian_awal_keperawatan_kebidanan.ket_ctg,penilaian_awal_keperawatan_kebidanan.usg,penilaian_awal_keperawatan_kebidanan.ket_usg,penilaian_awal_keperawatan_kebidanan.lab,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_lab,penilaian_awal_keperawatan_kebidanan.lakmus,penilaian_awal_keperawatan_kebidanan.ket_lakmus,penilaian_awal_keperawatan_kebidanan.panggul,penilaian_awal_keperawatan_kebidanan.keluhan_utama,penilaian_awal_keperawatan_kebidanan.umur,"
                    + "penilaian_awal_keperawatan_kebidanan.lama,penilaian_awal_keperawatan_kebidanan.banyaknya,penilaian_awal_keperawatan_kebidanan.haid,penilaian_awal_keperawatan_kebidanan.siklus,penilaian_awal_keperawatan_kebidanan.ket_siklus,penilaian_awal_keperawatan_kebidanan.ket_siklus1,"
                    + "penilaian_awal_keperawatan_kebidanan.status,penilaian_awal_keperawatan_kebidanan.kali,penilaian_awal_keperawatan_kebidanan.usia1,penilaian_awal_keperawatan_kebidanan.ket1,penilaian_awal_keperawatan_kebidanan.usia2,penilaian_awal_keperawatan_kebidanan.ket2,"
                    + "penilaian_awal_keperawatan_kebidanan.usia3,penilaian_awal_keperawatan_kebidanan.ket3,penilaian_awal_keperawatan_kebidanan.hpht,penilaian_awal_keperawatan_kebidanan.usia_kehamilan,penilaian_awal_keperawatan_kebidanan.tp,penilaian_awal_keperawatan_kebidanan.imunisasi,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_imunisasi,penilaian_awal_keperawatan_kebidanan.g,penilaian_awal_keperawatan_kebidanan.p,penilaian_awal_keperawatan_kebidanan.a,penilaian_awal_keperawatan_kebidanan.hidup,penilaian_awal_keperawatan_kebidanan.ginekologi,"
                    + "penilaian_awal_keperawatan_kebidanan.kebiasaan,penilaian_awal_keperawatan_kebidanan.ket_kebiasaan,penilaian_awal_keperawatan_kebidanan.kebiasaan1,penilaian_awal_keperawatan_kebidanan.ket_kebiasaan1,penilaian_awal_keperawatan_kebidanan.kebiasaan2,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_kebiasaan2,penilaian_awal_keperawatan_kebidanan.kebiasaan3,penilaian_awal_keperawatan_kebidanan.kb,penilaian_awal_keperawatan_kebidanan.ket_kb,penilaian_awal_keperawatan_kebidanan.komplikasi,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_komplikasi,penilaian_awal_keperawatan_kebidanan.berhenti,penilaian_awal_keperawatan_kebidanan.alasan,penilaian_awal_keperawatan_kebidanan.alat_bantu,penilaian_awal_keperawatan_kebidanan.ket_bantu,"
                    + "penilaian_awal_keperawatan_kebidanan.prothesa,penilaian_awal_keperawatan_kebidanan.ket_pro,penilaian_awal_keperawatan_kebidanan.adl,penilaian_awal_keperawatan_kebidanan.status_psiko,penilaian_awal_keperawatan_kebidanan.ket_psiko,"
                    + "penilaian_awal_keperawatan_kebidanan.hub_keluarga,penilaian_awal_keperawatan_kebidanan.tinggal_dengan,penilaian_awal_keperawatan_kebidanan.ket_tinggal,penilaian_awal_keperawatan_kebidanan.ekonomi,penilaian_awal_keperawatan_kebidanan.budaya,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_budaya,penilaian_awal_keperawatan_kebidanan.edukasi,penilaian_awal_keperawatan_kebidanan.ket_edukasi,penilaian_awal_keperawatan_kebidanan.berjalan_a,penilaian_awal_keperawatan_kebidanan.berjalan_b,"
                    + "penilaian_awal_keperawatan_kebidanan.berjalan_c,penilaian_awal_keperawatan_kebidanan.hasil,penilaian_awal_keperawatan_kebidanan.lapor,penilaian_awal_keperawatan_kebidanan.ket_lapor,penilaian_awal_keperawatan_kebidanan.sg1,"
                    + "penilaian_awal_keperawatan_kebidanan.nilai1,penilaian_awal_keperawatan_kebidanan.sg2,penilaian_awal_keperawatan_kebidanan.nilai2,penilaian_awal_keperawatan_kebidanan.total_hasil,penilaian_awal_keperawatan_kebidanan.nyeri,"
                    + "penilaian_awal_keperawatan_kebidanan.provokes,penilaian_awal_keperawatan_kebidanan.ket_provokes,penilaian_awal_keperawatan_kebidanan.quality,penilaian_awal_keperawatan_kebidanan.ket_quality,penilaian_awal_keperawatan_kebidanan.lokasi,"
                    + "penilaian_awal_keperawatan_kebidanan.menyebar,penilaian_awal_keperawatan_kebidanan.skala_nyeri,penilaian_awal_keperawatan_kebidanan.durasi,penilaian_awal_keperawatan_kebidanan.nyeri_hilang,penilaian_awal_keperawatan_kebidanan.ket_nyeri,"
                    + "penilaian_awal_keperawatan_kebidanan.pada_dokter,penilaian_awal_keperawatan_kebidanan.ket_dokter,penilaian_awal_keperawatan_kebidanan.masalah,penilaian_awal_keperawatan_kebidanan.rencana,penilaian_awal_keperawatan_kebidanan.nip,petugas.nama "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_kebidanan on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan.no_rawat "
                    + "inner join petugas on penilaian_awal_keperawatan_kebidanan.nip=petugas.nip "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);

            Valid.MyReportqry("rptCetakPenilaianAwalKebidananRalan2.jasper",
                    "report",
                    "::[ Laporan Penilaian Awal Ralan Kebidanan & Kandungan ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,bahasa_pasien.nama_bahasa,penilaian_awal_keperawatan_kebidanan.tanggal,"
                    + "penilaian_awal_keperawatan_kebidanan.informasi,penilaian_awal_keperawatan_kebidanan.td,penilaian_awal_keperawatan_kebidanan.nadi,penilaian_awal_keperawatan_kebidanan.rr,penilaian_awal_keperawatan_kebidanan.suhu,penilaian_awal_keperawatan_kebidanan.bb,"
                    + "penilaian_awal_keperawatan_kebidanan.tb,penilaian_awal_keperawatan_kebidanan.nadi,penilaian_awal_keperawatan_kebidanan.rr,penilaian_awal_keperawatan_kebidanan.suhu,penilaian_awal_keperawatan_kebidanan.gcs,penilaian_awal_keperawatan_kebidanan.bb,"
                    + "penilaian_awal_keperawatan_kebidanan.tb,penilaian_awal_keperawatan_kebidanan.bmi,penilaian_awal_keperawatan_kebidanan.lila,penilaian_awal_keperawatan_kebidanan.tfu,penilaian_awal_keperawatan_kebidanan.tbj,penilaian_awal_keperawatan_kebidanan.letak,"
                    + "penilaian_awal_keperawatan_kebidanan.presentasi,penilaian_awal_keperawatan_kebidanan.penurunan,penilaian_awal_keperawatan_kebidanan.his,penilaian_awal_keperawatan_kebidanan.kekuatan,penilaian_awal_keperawatan_kebidanan.lamanya,penilaian_awal_keperawatan_kebidanan.bjj,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_bjj,penilaian_awal_keperawatan_kebidanan.portio,penilaian_awal_keperawatan_kebidanan.serviks,penilaian_awal_keperawatan_kebidanan.ketuban,penilaian_awal_keperawatan_kebidanan.hodge,penilaian_awal_keperawatan_kebidanan.inspekulo,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_inspekulo,penilaian_awal_keperawatan_kebidanan.ctg,penilaian_awal_keperawatan_kebidanan.ket_ctg,penilaian_awal_keperawatan_kebidanan.usg,penilaian_awal_keperawatan_kebidanan.ket_usg,penilaian_awal_keperawatan_kebidanan.lab,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_lab,penilaian_awal_keperawatan_kebidanan.lakmus,penilaian_awal_keperawatan_kebidanan.ket_lakmus,penilaian_awal_keperawatan_kebidanan.panggul,penilaian_awal_keperawatan_kebidanan.keluhan_utama,penilaian_awal_keperawatan_kebidanan.umur,"
                    + "penilaian_awal_keperawatan_kebidanan.lama,penilaian_awal_keperawatan_kebidanan.banyaknya,penilaian_awal_keperawatan_kebidanan.haid,penilaian_awal_keperawatan_kebidanan.siklus,penilaian_awal_keperawatan_kebidanan.ket_siklus,penilaian_awal_keperawatan_kebidanan.ket_siklus1,"
                    + "penilaian_awal_keperawatan_kebidanan.status,penilaian_awal_keperawatan_kebidanan.kali,penilaian_awal_keperawatan_kebidanan.usia1,penilaian_awal_keperawatan_kebidanan.ket1,penilaian_awal_keperawatan_kebidanan.usia2,penilaian_awal_keperawatan_kebidanan.ket2,"
                    + "penilaian_awal_keperawatan_kebidanan.usia3,penilaian_awal_keperawatan_kebidanan.ket3,penilaian_awal_keperawatan_kebidanan.hpht,penilaian_awal_keperawatan_kebidanan.usia_kehamilan,penilaian_awal_keperawatan_kebidanan.tp,penilaian_awal_keperawatan_kebidanan.imunisasi,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_imunisasi,penilaian_awal_keperawatan_kebidanan.g,penilaian_awal_keperawatan_kebidanan.p,penilaian_awal_keperawatan_kebidanan.a,penilaian_awal_keperawatan_kebidanan.hidup,penilaian_awal_keperawatan_kebidanan.ginekologi,"
                    + "penilaian_awal_keperawatan_kebidanan.kebiasaan,penilaian_awal_keperawatan_kebidanan.ket_kebiasaan,penilaian_awal_keperawatan_kebidanan.kebiasaan1,penilaian_awal_keperawatan_kebidanan.ket_kebiasaan1,penilaian_awal_keperawatan_kebidanan.kebiasaan2,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_kebiasaan2,penilaian_awal_keperawatan_kebidanan.kebiasaan3,penilaian_awal_keperawatan_kebidanan.kb,penilaian_awal_keperawatan_kebidanan.ket_kb,penilaian_awal_keperawatan_kebidanan.komplikasi,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_komplikasi,penilaian_awal_keperawatan_kebidanan.berhenti,penilaian_awal_keperawatan_kebidanan.alasan,penilaian_awal_keperawatan_kebidanan.alat_bantu,penilaian_awal_keperawatan_kebidanan.ket_bantu,"
                    + "penilaian_awal_keperawatan_kebidanan.prothesa,penilaian_awal_keperawatan_kebidanan.ket_pro,penilaian_awal_keperawatan_kebidanan.adl,penilaian_awal_keperawatan_kebidanan.status_psiko,penilaian_awal_keperawatan_kebidanan.ket_psiko,"
                    + "penilaian_awal_keperawatan_kebidanan.hub_keluarga,penilaian_awal_keperawatan_kebidanan.tinggal_dengan,penilaian_awal_keperawatan_kebidanan.ket_tinggal,penilaian_awal_keperawatan_kebidanan.ekonomi,penilaian_awal_keperawatan_kebidanan.budaya,"
                    + "penilaian_awal_keperawatan_kebidanan.ket_budaya,penilaian_awal_keperawatan_kebidanan.edukasi,penilaian_awal_keperawatan_kebidanan.ket_edukasi,penilaian_awal_keperawatan_kebidanan.berjalan_a,penilaian_awal_keperawatan_kebidanan.berjalan_b,"
                    + "penilaian_awal_keperawatan_kebidanan.berjalan_c,penilaian_awal_keperawatan_kebidanan.hasil,penilaian_awal_keperawatan_kebidanan.lapor,penilaian_awal_keperawatan_kebidanan.ket_lapor,penilaian_awal_keperawatan_kebidanan.sg1,"
                    + "penilaian_awal_keperawatan_kebidanan.nilai1,penilaian_awal_keperawatan_kebidanan.sg2,penilaian_awal_keperawatan_kebidanan.nilai2,penilaian_awal_keperawatan_kebidanan.total_hasil,penilaian_awal_keperawatan_kebidanan.nyeri,"
                    + "penilaian_awal_keperawatan_kebidanan.provokes,penilaian_awal_keperawatan_kebidanan.ket_provokes,penilaian_awal_keperawatan_kebidanan.quality,penilaian_awal_keperawatan_kebidanan.ket_quality,penilaian_awal_keperawatan_kebidanan.lokasi,"
                    + "penilaian_awal_keperawatan_kebidanan.menyebar,penilaian_awal_keperawatan_kebidanan.skala_nyeri,penilaian_awal_keperawatan_kebidanan.durasi,penilaian_awal_keperawatan_kebidanan.nyeri_hilang,penilaian_awal_keperawatan_kebidanan.ket_nyeri,"
                    + "penilaian_awal_keperawatan_kebidanan.pada_dokter,penilaian_awal_keperawatan_kebidanan.ket_dokter,penilaian_awal_keperawatan_kebidanan.masalah,penilaian_awal_keperawatan_kebidanan.rencana,penilaian_awal_keperawatan_kebidanan.nip,petugas.nama "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_kebidanan on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan.no_rawat "
                    + "inner join petugas on penilaian_awal_keperawatan_kebidanan.nip=petugas.nip "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien where reg_periksa.no_rawat='" + tbObat.
                            getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                    param);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void AnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesisKeyPressed
        Valid.pindah(evt, TanggalPersalinan, TibadiRuang);
    }//GEN-LAST:event_AnamnesisKeyPressed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        i = 1;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 20, internalFrame1.
                getHeight() - 20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed

    }//GEN-LAST:event_KdPetugasKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
        } else {
            Valid.pindah(evt, TCari, BtnPetugas);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void BtnKeluarKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarKehamilanActionPerformed
        DlgRiwayatPersalinan.dispose();
    }//GEN-LAST:event_BtnKeluarKehamilanActionPerformed

    private void BtnSimpanRiwayatKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRiwayatKehamilanActionPerformed
        if (TempatPersalinan.getText().trim().isEmpty()) {
            Valid.textKosong(TempatPersalinan, "Tempat Persalinan");
        } else if (JenisPersalinan.getText().trim().isEmpty()) {
            Valid.textKosong(JenisPersalinan, "Jenis Persalinan");
        } else if (Penolong.getText().trim().isEmpty()) {
            Valid.textKosong(Penolong, "Penolong Persalinan");
        } else if (Penyulit.getText().trim().isEmpty()) {
            Valid.textKosong(Penyulit, "Penyulit Persalinan");
        } else if (Keadaan.getText().trim().isEmpty()) {
            Valid.textKosong(Keadaan, "Keadaan Persalinan");
        } else if (UsiaHamil.getText().trim().isEmpty()) {
            Valid.textKosong(UsiaHamil, "Usia Hamil");
        } else if (BBPB.getText().trim().isEmpty()) {
            Valid.textKosong(BBPB, "BB/PB");
        } else {
            if (Sequel.menyimpantf("riwayat_persalinan_pasien",
                    "?,?,?,?,?,?,?,?,?,?", "Riwayat Persalinan", 10,
                    new String[]{
                        TNoRM.getText(), Valid.SetTgl(TanggalPersalinan.
                        getSelectedItem() + ""), TempatPersalinan.getText(),
                        UsiaHamil.getText(), JenisPersalinan.getText(),
                        Penolong.getText(), Penyulit.getText(), JK.
                        getSelectedItem().toString().substring(0, 1), BBPB.
                        getText(), Keadaan.getText()
                    }) == true) {
                emptTeksPersalinan();
                tampilPersalinan();
            }
        }
    }//GEN-LAST:event_BtnSimpanRiwayatKehamilanActionPerformed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
        Valid.pindah(evt, UsiaHamil, BBPB);
    }//GEN-LAST:event_JKKeyPressed

    private void TempatPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TempatPersalinanKeyPressed
        Valid.pindah(evt, BtnKeluarKehamilan, JenisPersalinan);
    }//GEN-LAST:event_TempatPersalinanKeyPressed

    private void JenisPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPersalinanKeyPressed
        Valid.pindah(evt, TempatPersalinan, Penolong);
    }//GEN-LAST:event_JenisPersalinanKeyPressed

    private void PenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenolongKeyPressed
        Valid.pindah(evt, JenisPersalinan, Penyulit);
    }//GEN-LAST:event_PenolongKeyPressed

    private void PenyulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyulitKeyPressed
        Valid.pindah(evt, Penolong, Keadaan);
    }//GEN-LAST:event_PenyulitKeyPressed

    private void KeadaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanKeyPressed
        Valid.pindah(evt, Penyulit, UsiaHamil);
    }//GEN-LAST:event_KeadaanKeyPressed

    private void UsiaHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaHamilKeyPressed
        Valid.pindah(evt, Keadaan, JK);
    }//GEN-LAST:event_UsiaHamilKeyPressed

    private void BBPBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBPBKeyPressed
        Valid.pindah(evt, JK, BtnSimpanRiwayatKehamilan);
    }//GEN-LAST:event_BBPBKeyPressed

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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDPJPKeyPressed

    private void TibadiRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TibadiRuangKeyPressed
        Valid.pindah(evt, Anamnesis, CaraMasuk);
    }//GEN-LAST:event_TibadiRuangKeyPressed

    private void CaraMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraMasukKeyPressed
        Valid.pindah(evt, TibadiRuang, KeluhanUtama);
    }//GEN-LAST:event_CaraMasukKeyPressed

    private void AlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlergiKeyPressed
        Valid.pindah(evt, RBedah, KomplikasiKehamilan);
    }//GEN-LAST:event_AlergiKeyPressed

    private void KeluhanUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanUtamaKeyPressed
        Valid.pindah2(evt, CaraMasuk, RPK);
    }//GEN-LAST:event_KeluhanUtamaKeyPressed

    private void PSKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSKKeyPressed
        Valid.pindah2(evt, RPK, RBedah);
    }//GEN-LAST:event_PSKKeyPressed

    private void RPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPKKeyPressed
        Valid.pindah2(evt, KeluhanUtama, PSK);
    }//GEN-LAST:event_RPKKeyPressed

    private void RBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RBedahKeyPressed
        Valid.pindah2(evt, PSK, Alergi);
    }//GEN-LAST:event_RBedahKeyPressed

    private void KomplikasiKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKehamilanKeyPressed
        Valid.pindah(evt, Alergi, KeteranganKomplikasiKehamilan);
    }//GEN-LAST:event_KomplikasiKehamilanKeyPressed

    private void UmurMinarcheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurMinarcheKeyPressed
        Valid.pindah(evt, KeteranganKomplikasiKehamilan, LamaMenstruasi);
    }//GEN-LAST:event_UmurMinarcheKeyPressed

    private void LamaMenstruasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaMenstruasiKeyPressed
        Valid.pindah(evt, UmurMinarche, BanyaknyaPembalut);
    }//GEN-LAST:event_LamaMenstruasiKeyPressed

    private void BanyaknyaPembalutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BanyaknyaPembalutKeyPressed
        Valid.pindah(evt, LamaMenstruasi, SiklusMenstruasi);
    }//GEN-LAST:event_BanyaknyaPembalutKeyPressed

    private void SiklusMenstruasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SiklusMenstruasiKeyPressed
        Valid.pindah(evt, BanyaknyaPembalut, KetSiklusMenstruasi);
    }//GEN-LAST:event_SiklusMenstruasiKeyPressed

    private void KetSiklusMenstruasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSiklusMenstruasiKeyPressed
        Valid.pindah(evt, SiklusMenstruasi, DirasakanMenstruasi);
    }//GEN-LAST:event_KetSiklusMenstruasiKeyPressed

    private void DirasakanMenstruasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DirasakanMenstruasiKeyPressed
        Valid.pindah(evt, KetSiklusMenstruasi, StatusMenikah);
    }//GEN-LAST:event_DirasakanMenstruasiKeyPressed

    private void StatusKawin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKawin1KeyPressed
        Valid.pindah(evt, UsiaKawin1, UsiaKawin2);
    }//GEN-LAST:event_StatusKawin1KeyPressed

    private void StatusMenikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusMenikahKeyPressed
        Valid.pindah(evt, DirasakanMenstruasi, KaliMenikah);
    }//GEN-LAST:event_StatusMenikahKeyPressed

    private void UsiaKawin1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaKawin1KeyPressed
        Valid.pindah(evt, KaliMenikah, StatusKawin1);
    }//GEN-LAST:event_UsiaKawin1KeyPressed

    private void UsiaKawin3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaKawin3KeyPressed
        Valid.pindah(evt, StatusKawin2, StatusKawin3);
    }//GEN-LAST:event_UsiaKawin3KeyPressed

    private void StatusKawin3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKawin3KeyPressed
        Valid.pindah(evt, UsiaKawin3, G);
    }//GEN-LAST:event_StatusKawin3KeyPressed

    private void UsiaKawin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaKawin2KeyPressed
        Valid.pindah(evt, StatusKawin1, StatusKawin2);
    }//GEN-LAST:event_UsiaKawin2KeyPressed

    private void StatusKawin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKawin2KeyPressed
        Valid.pindah(evt, UsiaKawin2, UsiaKawin3);
    }//GEN-LAST:event_StatusKawin2KeyPressed

    private void KaliMenikahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KaliMenikahKeyPressed
        Valid.pindah(evt, StatusMenikah, UsiaKawin1);
    }//GEN-LAST:event_KaliMenikahKeyPressed

    private void GKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GKeyPressed
        Valid.pindah(evt, StatusKawin3, P);
    }//GEN-LAST:event_GKeyPressed

    private void PKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PKeyPressed
        Valid.pindah(evt, G, A);
    }//GEN-LAST:event_PKeyPressed

    private void AKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AKeyPressed
        Valid.pindah(evt, P, Hidup);
    }//GEN-LAST:event_AKeyPressed

    private void HidupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HidupKeyPressed
        Valid.pindah(evt, A, HPHT);
    }//GEN-LAST:event_HidupKeyPressed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        if (TNoRM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Pilih terlebih dahulu pasien yang mau dimasukkan data kelarihannya...");
            Anamnesis.requestFocus();
        } else {
            emptTeksPersalinan();
            DlgRiwayatPersalinan.setLocationRelativeTo(internalFrame1);
            DlgRiwayatPersalinan.setVisible(true);
        }
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void BtnHapusRiwayatPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusRiwayatPersalinanActionPerformed
        if (tbRiwayatKehamilan.getSelectedRow() > -1) {
            Sequel.meghapus("riwayat_persalinan_pasien", "no_rkm_medis",
                    "tgl_thn", TNoRM.getText(), tbRiwayatKehamilan.getValueAt(
                    tbRiwayatKehamilan.getSelectedRow(), 1).toString());
            tampilPersalinan();
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusRiwayatPersalinanActionPerformed

    private void HPHTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HPHTKeyPressed
        Valid.pindah2(evt, Hidup, UsiaKehamilan);
    }//GEN-LAST:event_HPHTKeyPressed

    private void UsiaKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsiaKehamilanKeyPressed
        Valid.pindah(evt, HPHT, TP);
    }//GEN-LAST:event_UsiaKehamilanKeyPressed

    private void TPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPKeyPressed
        Valid.pindah2(evt, UsiaKehamilan, RiwayatImunisasi);
    }//GEN-LAST:event_TPKeyPressed

    private void RiwayatImunisasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatImunisasiKeyPressed
        Valid.pindah(evt, TP, ANC);
    }//GEN-LAST:event_RiwayatImunisasiKeyPressed

    private void ANCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ANCKeyPressed
        Valid.pindah(evt, RiwayatImunisasi, ANCKe);
    }//GEN-LAST:event_ANCKeyPressed

    private void ANCKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ANCKeKeyPressed
        Valid.pindah(evt, ANC, RiwayatANC);
    }//GEN-LAST:event_ANCKeKeyPressed

    private void RiwayatANCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatANCKeyPressed
        Valid.pindah(evt, ANCKe, KeluhanHamilMuda);
    }//GEN-LAST:event_RiwayatANCKeyPressed

    private void KeluhanHamilMudaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanHamilMudaKeyPressed
        Valid.pindah(evt, RiwayatANC, KeluhanHamilTua);
    }//GEN-LAST:event_KeluhanHamilMudaKeyPressed

    private void KeluhanHamilTuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanHamilTuaKeyPressed
        Valid.pindah(evt, KeluhanHamilMuda, RiwayatKB);
    }//GEN-LAST:event_KeluhanHamilTuaKeyPressed

    private void RiwayatGenekologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatGenekologiKeyPressed
        Valid.pindah(evt, AlasanBerhentiKB, KebiasaanObat);
    }//GEN-LAST:event_RiwayatGenekologiKeyPressed

    private void RiwayatKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatKBKeyPressed
        Valid.pindah(evt, KeluhanHamilTua, LamanyaKB);
    }//GEN-LAST:event_RiwayatKBKeyPressed

    private void LamanyaKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamanyaKBKeyPressed
        Valid.pindah(evt, RiwayatKB, KomplikasiKB);
    }//GEN-LAST:event_LamanyaKBKeyPressed

    private void KomplikasiKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKBKeyPressed
        Valid.pindah(evt, LamanyaKB, KeteranganKomplikasiKB);
    }//GEN-LAST:event_KomplikasiKBKeyPressed

    private void KeteranganKomplikasiKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKomplikasiKBKeyPressed
        Valid.pindah(evt, KomplikasiKB, BerhentiKB);
    }//GEN-LAST:event_KeteranganKomplikasiKBKeyPressed

    private void BerhentiKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerhentiKBKeyPressed
        Valid.pindah(evt, KeteranganKomplikasiKB, AlasanBerhentiKB);
    }//GEN-LAST:event_BerhentiKBKeyPressed

    private void AlasanBerhentiKBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlasanBerhentiKBKeyPressed
        Valid.pindah(evt, BerhentiKB, RiwayatGenekologi);
    }//GEN-LAST:event_AlasanBerhentiKBKeyPressed

    private void KebiasaanObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanObatKeyPressed
        Valid.pindah(evt, RiwayatGenekologi, KebiasaanObatDiminum);
    }//GEN-LAST:event_KebiasaanObatKeyPressed

    private void KebiasaanObatDiminumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanObatDiminumKeyPressed
        Valid.pindah(evt, KebiasaanObat, KebiasaanMerokok);
    }//GEN-LAST:event_KebiasaanObatDiminumKeyPressed

    private void KebiasaanMerokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanMerokokKeyPressed
        Valid.pindah(evt, KebiasaanObatDiminum, KebiasaanJumlahRokok);
    }//GEN-LAST:event_KebiasaanMerokokKeyPressed

    private void KebiasaanJumlahRokokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanJumlahRokokKeyPressed
        Valid.pindah(evt, KebiasaanMerokok, KebiasaanAlkohol);
    }//GEN-LAST:event_KebiasaanJumlahRokokKeyPressed

    private void KebiasaanAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanJumlahRokok, KebiasaanJumlahAlkohol);
    }//GEN-LAST:event_KebiasaanAlkoholKeyPressed

    private void KebiasaanJumlahAlkoholKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanJumlahAlkoholKeyPressed
        Valid.pindah(evt, KebiasaanAlkohol, KebiasaanNarkoba);
    }//GEN-LAST:event_KebiasaanJumlahAlkoholKeyPressed

    private void KebiasaanNarkobaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KebiasaanNarkobaKeyPressed
        Valid.pindah(evt, KebiasaanJumlahAlkohol, KesadaranMental);
    }//GEN-LAST:event_KebiasaanNarkobaKeyPressed

    private void KesadaranMentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranMentalKeyPressed
        Valid.pindah(evt, KebiasaanNarkoba, KeadaanMentalUmum);
    }//GEN-LAST:event_KesadaranMentalKeyPressed

    private void KeteranganDJJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDJJKeyPressed
        Valid.pindah(evt, DJJ, Portio);
    }//GEN-LAST:event_KeteranganDJJKeyPressed

    private void DJJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DJJKeyPressed
        Valid.pindah(evt, LamanyaKontraksi, KeteranganDJJ);
    }//GEN-LAST:event_DJJKeyPressed

    private void LamanyaKontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamanyaKontraksiKeyPressed
        Valid.pindah(evt, Kekuatan, DJJ);
    }//GEN-LAST:event_LamanyaKontraksiKeyPressed

    private void KekuatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KekuatanKeyPressed
        Valid.pindah(evt, Kontraksi, LamanyaKontraksi);
    }//GEN-LAST:event_KekuatanKeyPressed

    private void KontraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontraksiKeyPressed
        Valid.pindah(evt, Penurunan, Kekuatan);
    }//GEN-LAST:event_KontraksiKeyPressed

    private void KeadaanMentalUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeadaanMentalUmumKeyPressed
        Valid.pindah(evt, KesadaranMental, GCS);
    }//GEN-LAST:event_KeadaanMentalUmumKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt, KeadaanMentalUmum, TD);
    }//GEN-LAST:event_GCSKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt, GCS, Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt, TD, RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt, Nadi, Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        Valid.pindah(evt, RR, SpO2);
    }//GEN-LAST:event_SuhuKeyPressed

    private void SpO2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SpO2KeyPressed
        Valid.pindah(evt, Suhu, BB);
    }//GEN-LAST:event_SpO2KeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt, SpO2, TB);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt, BB, LILA);
    }//GEN-LAST:event_TBKeyPressed

    private void LILAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LILAKeyPressed
        Valid.pindah(evt, TB, TFU);
    }//GEN-LAST:event_LILAKeyPressed

    private void TFUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFUKeyPressed
        Valid.pindah(evt, LILA, TBJ);
    }//GEN-LAST:event_TFUKeyPressed

    private void TBJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBJKeyPressed
        Valid.pindah(evt, TFU, Letak);
    }//GEN-LAST:event_TBJKeyPressed

    private void GDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GDKeyPressed

    private void LetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LetakKeyPressed
        Valid.pindah(evt, TBJ, Presentasi);
    }//GEN-LAST:event_LetakKeyPressed

    private void PresentasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PresentasiKeyPressed
        Valid.pindah(evt, Letak, Penurunan);
    }//GEN-LAST:event_PresentasiKeyPressed

    private void PenurunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenurunanKeyPressed
        Valid.pindah(evt, Presentasi, Kontraksi);
    }//GEN-LAST:event_PenurunanKeyPressed

    private void PortioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PortioKeyPressed
        Valid.pindah(evt, KeteranganDJJ, PembukaanServiks);
    }//GEN-LAST:event_PortioKeyPressed

    private void PembukaanServiksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PembukaanServiksKeyPressed
        Valid.pindah(evt, Portio, Ketuban);
    }//GEN-LAST:event_PembukaanServiksKeyPressed

    private void KetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanKeyPressed
        Valid.pindah(evt, PembukaanServiks, Hodge);
    }//GEN-LAST:event_KetubanKeyPressed

    private void HodgeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HodgeKeyPressed
        Valid.pindah(evt, Ketuban, PemeriksaanPanggul);
    }//GEN-LAST:event_HodgeKeyPressed

    private void PemeriksaanPanggulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanPanggulKeyPressed
        Valid.pindah(evt, Hodge, Inspekulo);
    }//GEN-LAST:event_PemeriksaanPanggulKeyPressed

    private void InspekuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InspekuloKeyPressed
        Valid.pindah(evt, PemeriksaanPanggul, KeteranganInspekulo);
    }//GEN-LAST:event_InspekuloKeyPressed

    private void KeteranganInspekuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganInspekuloKeyPressed
        Valid.pindah(evt, Inspekulo, Lakmus);
    }//GEN-LAST:event_KeteranganInspekuloKeyPressed

    private void CTGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CTGKeyPressed
        Valid.pindah(evt, KeteranganLakmus, KeteranganCTG);
    }//GEN-LAST:event_CTGKeyPressed

    private void KeteranganCTGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganCTGKeyPressed
        Valid.pindah(evt, CTG, PemeriksaanKepala);
    }//GEN-LAST:event_KeteranganCTGKeyPressed

    private void LakmusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakmusKeyPressed
        Valid.pindah(evt, KeteranganInspekulo, KeteranganLakmus);
    }//GEN-LAST:event_LakmusKeyPressed

    private void KeteranganLakmusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganLakmusKeyPressed
        Valid.pindah(evt, Lakmus, CTG);
    }//GEN-LAST:event_KeteranganLakmusKeyPressed

    private void PemeriksaanKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanKepalaKeyPressed
        Valid.pindah(evt, KeteranganCTG, PemeriksaanMuka);
    }//GEN-LAST:event_PemeriksaanKepalaKeyPressed

    private void PemeriksaanMukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanMukaKeyPressed
        Valid.pindah(evt, PemeriksaanKepala, PemeriksaanMata);
    }//GEN-LAST:event_PemeriksaanMukaKeyPressed

    private void PemeriksaanMataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanMataKeyPressed
        Valid.pindah(evt, PemeriksaanMuka, PemeriksaanHidung);
    }//GEN-LAST:event_PemeriksaanMataKeyPressed

    private void PemeriksaanHidungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanHidungKeyPressed
        Valid.pindah(evt, PemeriksaanMata, PemeriksaanTelinga);
    }//GEN-LAST:event_PemeriksaanHidungKeyPressed

    private void PemeriksaanTelingaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanTelingaKeyPressed
        Valid.pindah(evt, PemeriksaanHidung, PemeriksaanMulut);
    }//GEN-LAST:event_PemeriksaanTelingaKeyPressed

    private void PemeriksaanMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanMulutKeyPressed
        Valid.pindah(evt, PemeriksaanTelinga, PemeriksaanLeher);
    }//GEN-LAST:event_PemeriksaanMulutKeyPressed

    private void PemeriksaanLeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLeherKeyPressed
        Valid.pindah(evt, PemeriksaanMulut, PemeriksaanDada);
    }//GEN-LAST:event_PemeriksaanLeherKeyPressed

    private void PemeriksaanDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanDadaKeyPressed
        Valid.pindah(evt, PemeriksaanLeher, PemeriksaanPerut);
    }//GEN-LAST:event_PemeriksaanDadaKeyPressed

    private void PemeriksaanPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanPerutKeyPressed
        Valid.pindah(evt, PemeriksaanDada, PemeriksaanGenitalia);
    }//GEN-LAST:event_PemeriksaanPerutKeyPressed

    private void PemeriksaanGenitaliaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanGenitaliaKeyPressed
        Valid.pindah(evt, PemeriksaanPerut, PemeriksaanEkstrimitas);
    }//GEN-LAST:event_PemeriksaanGenitaliaKeyPressed

    private void PemeriksaanEkstrimitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanEkstrimitasKeyPressed
        Valid.pindah(evt, PemeriksaanGenitalia, AktifitasSehari2);
    }//GEN-LAST:event_PemeriksaanEkstrimitasKeyPressed

    private void AktifitasSehari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasSehari2KeyPressed
        Valid.pindah(evt, PemeriksaanEkstrimitas, Berjalan);
    }//GEN-LAST:event_AktifitasSehari2KeyPressed

    private void AktifitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AktifitasKeyPressed
        Valid.pindah(evt, KeteranganBerjalan, AlatAmbulasi);
    }//GEN-LAST:event_AktifitasKeyPressed

    private void BerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BerjalanKeyPressed
        Valid.pindah(evt, AktifitasSehari2, KeteranganBerjalan);
    }//GEN-LAST:event_BerjalanKeyPressed

    private void KeteranganBerjalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganBerjalanKeyPressed
        Valid.pindah(evt, Berjalan, Aktifitas);
    }//GEN-LAST:event_KeteranganBerjalanKeyPressed

    private void AlatAmbulasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlatAmbulasiKeyPressed
        Valid.pindah(evt, Aktifitas, EkstrimitasAtas);
    }//GEN-LAST:event_AlatAmbulasiKeyPressed

    private void EkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstrimitasAtasKeyPressed
        Valid.pindah(evt, AlatAmbulasi, KeteranganEkstrimitasAtas);
    }//GEN-LAST:event_EkstrimitasAtasKeyPressed

    private void KeteranganEkstrimitasAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEkstrimitasAtasKeyPressed
        Valid.pindah(evt, EkstrimitasAtas, EkstrimitasBawah);
    }//GEN-LAST:event_KeteranganEkstrimitasAtasKeyPressed

    private void EkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstrimitasBawahKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasAtas, KeteranganEkstrimitasBawah);
    }//GEN-LAST:event_EkstrimitasBawahKeyPressed

    private void KeteranganEkstrimitasBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEkstrimitasBawahKeyPressed
        Valid.pindah(evt, EkstrimitasBawah, KemampuanMenggenggam);
    }//GEN-LAST:event_KeteranganEkstrimitasBawahKeyPressed

    private void KemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KemampuanMenggenggamKeyPressed
        Valid.pindah(evt, KeteranganEkstrimitasBawah,
                KeteranganKemampuanMenggenggam);
    }//GEN-LAST:event_KemampuanMenggenggamKeyPressed

    private void KeteranganKemampuanMenggenggamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKemampuanMenggenggamKeyPressed
        Valid.pindah(evt, KemampuanMenggenggam, KemampuanKoordinasi);
    }//GEN-LAST:event_KeteranganKemampuanMenggenggamKeyPressed

    private void KemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KeteranganKemampuanMenggenggam,
                KeteranganKemampuanKoordinasi);
    }//GEN-LAST:event_KemampuanKoordinasiKeyPressed

    private void KeteranganKemampuanKoordinasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKemampuanKoordinasiKeyPressed
        Valid.pindah(evt, KemampuanKoordinasi, KesimpulanGangguanFungsi);
    }//GEN-LAST:event_KeteranganKemampuanKoordinasiKeyPressed

    private void KesimpulanGangguanFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesimpulanGangguanFungsiKeyPressed
        Valid.pindah(evt, KeteranganKemampuanKoordinasi, KondisiPsikologis);
    }//GEN-LAST:event_KesimpulanGangguanFungsiKeyPressed

    private void KondisiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiPsikologisKeyPressed
        Valid.pindah(evt, KesimpulanGangguanFungsi, AdakahPerilaku);
    }//GEN-LAST:event_KondisiPsikologisKeyPressed

    private void AdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AdakahPerilakuKeyPressed
        Valid.pindah(evt, KondisiPsikologis, KeteranganAdakahPerilaku);
    }//GEN-LAST:event_AdakahPerilakuKeyPressed

    private void KeteranganAdakahPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganAdakahPerilakuKeyPressed
        Valid.pindah(evt, AdakahPerilaku, GangguanJiwa);
    }//GEN-LAST:event_KeteranganAdakahPerilakuKeyPressed

    private void GangguanJiwaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GangguanJiwaKeyPressed
        Valid.pindah(evt, KeteranganAdakahPerilaku, HubunganAnggotaKeluarga);
    }//GEN-LAST:event_GangguanJiwaKeyPressed

    private void HubunganAnggotaKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganAnggotaKeluargaKeyPressed
        Valid.pindah(evt, GangguanJiwa, TinggalDengan);
    }//GEN-LAST:event_HubunganAnggotaKeluargaKeyPressed

    private void TinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinggalDenganKeyPressed
        Valid.pindah(evt, HubunganAnggotaKeluarga, KeteranganTinggalDengan);
    }//GEN-LAST:event_TinggalDenganKeyPressed

    private void NilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NilaiKepercayaanKeyPressed
        Valid.pindah(evt, KeteranganTinggalDengan, KeteranganNilaiKepercayaan);
    }//GEN-LAST:event_NilaiKepercayaanKeyPressed

    private void KeteranganNilaiKepercayaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganNilaiKepercayaanKeyPressed
        Valid.pindah(evt, NilaiKepercayaan, PendidikanPJ);
    }//GEN-LAST:event_KeteranganNilaiKepercayaanKeyPressed

    private void PendidikanPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PendidikanPJKeyPressed
        Valid.pindah(evt, KeteranganNilaiKepercayaan, EdukasiPsikolgis);
    }//GEN-LAST:event_PendidikanPJKeyPressed

    private void EdukasiPsikolgisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdukasiPsikolgisKeyPressed
        Valid.pindah(evt, PendidikanPJ, KeteranganEdukasiPsikologis);
    }//GEN-LAST:event_EdukasiPsikolgisKeyPressed

    private void KeteranganEdukasiPsikologisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganEdukasiPsikologisKeyPressed
        Valid.pindah(evt, EdukasiPsikolgis, Nyeri);
    }//GEN-LAST:event_KeteranganEdukasiPsikologisKeyPressed

    private void NyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriKeyPressed
        Valid.pindah(evt, KeteranganEdukasiPsikologis, Provokes);
    }//GEN-LAST:event_NyeriKeyPressed

    private void ProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProvokesKeyPressed
        Valid.pindah(evt, Nyeri, KetProvokes);
    }//GEN-LAST:event_ProvokesKeyPressed

    private void KetProvokesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetProvokesKeyPressed
        Valid.pindah(evt, Provokes, Quality);
    }//GEN-LAST:event_KetProvokesKeyPressed

    private void QualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QualityKeyPressed
        Valid.pindah(evt, KetProvokes, KetQuality);
    }//GEN-LAST:event_QualityKeyPressed

    private void KetQualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetQualityKeyPressed
        Valid.pindah(evt, Quality, Lokasi);
    }//GEN-LAST:event_KetQualityKeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        Valid.pindah(evt, KetQuality, Menyebar);
    }//GEN-LAST:event_LokasiKeyPressed

    private void MenyebarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenyebarKeyPressed
        Valid.pindah(evt, Lokasi, SkalaNyeri);
    }//GEN-LAST:event_MenyebarKeyPressed

    private void SkalaNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaNyeriKeyPressed
        Valid.pindah(evt, Menyebar, Durasi);
    }//GEN-LAST:event_SkalaNyeriKeyPressed

    private void DurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DurasiKeyPressed
        Valid.pindah(evt, SkalaNyeri, NyeriHilang);
    }//GEN-LAST:event_DurasiKeyPressed

    private void NyeriHilangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NyeriHilangKeyPressed
        Valid.pindah(evt, Durasi, KetNyeri);
    }//GEN-LAST:event_NyeriHilangKeyPressed

    private void KetNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetNyeriKeyPressed
        Valid.pindah(evt, NyeriHilang, PadaDokter);
    }//GEN-LAST:event_KetNyeriKeyPressed

    private void PadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PadaDokterKeyPressed
        Valid.pindah(evt, KetNyeri, KetPadaDokter);
    }//GEN-LAST:event_PadaDokterKeyPressed

    private void KetPadaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetPadaDokterKeyPressed
        Valid.pindah(evt, PadaDokter, SkalaResiko1);
    }//GEN-LAST:event_KetPadaDokterKeyPressed

    private void SkalaResiko1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko1ItemStateChanged
        if (SkalaResiko1.getSelectedIndex() == 0) {
            NilaiResiko1.setText("0");
        } else {
            NilaiResiko1.setText("25");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko1ItemStateChanged

    private void SkalaResiko1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko1KeyPressed
        Valid.pindah(evt, KetPadaDokter, SkalaResiko2);
    }//GEN-LAST:event_SkalaResiko1KeyPressed

    private void SkalaResiko2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko2ItemStateChanged
        if (SkalaResiko2.getSelectedIndex() == 0) {
            NilaiResiko2.setText("0");
        } else {
            NilaiResiko2.setText("15");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko2ItemStateChanged

    private void SkalaResiko2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko2KeyPressed
        Valid.pindah(evt, SkalaResiko1, SkalaResiko3);
    }//GEN-LAST:event_SkalaResiko2KeyPressed

    private void SkalaResiko3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko3ItemStateChanged
        if (SkalaResiko3.getSelectedIndex() == 0) {
            NilaiResiko3.setText("0");
        } else if (SkalaResiko3.getSelectedIndex() == 1) {
            NilaiResiko3.setText("15");
        } else {
            NilaiResiko3.setText("30");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko3ItemStateChanged

    private void SkalaResiko3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko3KeyPressed
        Valid.pindah(evt, SkalaResiko2, SkalaResiko4);
    }//GEN-LAST:event_SkalaResiko3KeyPressed

    private void SkalaResiko4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko4ItemStateChanged
        if (SkalaResiko4.getSelectedIndex() == 0) {
            NilaiResiko4.setText("0");
        } else {
            NilaiResiko4.setText("20");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko4ItemStateChanged

    private void SkalaResiko4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko4KeyPressed
        Valid.pindah(evt, SkalaResiko3, SkalaResiko5);
    }//GEN-LAST:event_SkalaResiko4KeyPressed

    private void SkalaResiko5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko5ItemStateChanged
        if (SkalaResiko5.getSelectedIndex() == 0) {
            NilaiResiko5.setText("0");
        } else if (SkalaResiko5.getSelectedIndex() == 1) {
            NilaiResiko5.setText("10");
        } else {
            NilaiResiko5.setText("20");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko5ItemStateChanged

    private void SkalaResiko5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko5KeyPressed
        Valid.pindah(evt, SkalaResiko4, SkalaResiko6);
    }//GEN-LAST:event_SkalaResiko5KeyPressed

    private void SkalaResiko6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko6ItemStateChanged
        if (SkalaResiko6.getSelectedIndex() == 0) {
            NilaiResiko6.setText("0");
        } else {
            NilaiResiko6.setText("15");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko6ItemStateChanged

    private void SkalaResiko6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko6KeyPressed
        Valid.pindah(evt, SkalaResiko5, SkalaGizi1);
    }//GEN-LAST:event_SkalaResiko6KeyPressed

    private void SkalaGizi1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaGizi1ItemStateChanged
        if (SkalaGizi1.getSelectedIndex() == 0) {
            NilaiGizi1.setText("0");
        } else if (SkalaGizi1.getSelectedIndex() == 1) {
            NilaiGizi1.setText("2");
        } else if (SkalaGizi1.getSelectedIndex() == 2) {
            NilaiGizi1.setText("1");
        } else if (SkalaGizi1.getSelectedIndex() == 3) {
            NilaiGizi1.setText("2");
        } else if (SkalaGizi1.getSelectedIndex() == 4) {
            NilaiGizi1.setText("3");
        } else if (SkalaGizi1.getSelectedIndex() == 5) {
            NilaiGizi1.setText("4");
        }
        isTotalGizi();
    }//GEN-LAST:event_SkalaGizi1ItemStateChanged

    private void SkalaGizi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaGizi1KeyPressed
        Valid.pindah(evt, SkalaResiko6, SkalaGizi2);
    }//GEN-LAST:event_SkalaGizi1KeyPressed

    private void SkalaGizi2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaGizi2ItemStateChanged
        if (SkalaGizi2.getSelectedIndex() == 0) {
            NilaiGizi2.setText("0");
        } else if (SkalaGizi2.getSelectedIndex() == 1) {
            NilaiGizi2.setText("1");
        }
        isTotalGizi();
    }//GEN-LAST:event_SkalaGizi2ItemStateChanged

    private void SkalaGizi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaGizi2KeyPressed
        Valid.pindah(evt, SkalaGizi1, DiagnosaKhususGizi);
    }//GEN-LAST:event_SkalaGizi2KeyPressed

    private void DiketahuiDietisenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiketahuiDietisenKeyPressed
        Valid.pindah(evt, KeteranganDiagnosaKhususGizi,
                KeteranganDiketahuiDietisen);
    }//GEN-LAST:event_DiketahuiDietisenKeyPressed

    private void KeteranganDiketahuiDietisenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDiketahuiDietisenKeyPressed
        Valid.pindah(evt, DiketahuiDietisen, Rencana);
    }//GEN-LAST:event_KeteranganDiketahuiDietisenKeyPressed

    private void DiagnosaKhususGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKhususGiziKeyPressed
        Valid.pindah(evt, SkalaGizi2, KeteranganDiagnosaKhususGizi);
    }//GEN-LAST:event_DiagnosaKhususGiziKeyPressed

    private void KeteranganDiagnosaKhususGiziKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganDiagnosaKhususGiziKeyPressed
        Valid.pindah(evt, DiagnosaKhususGizi, DiketahuiDietisen);
    }//GEN-LAST:event_KeteranganDiagnosaKhususGiziKeyPressed

    private void KeteranganKomplikasiKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKomplikasiKehamilanKeyPressed
        Valid.pindah(evt, KomplikasiKehamilan, UmurMinarche);
    }//GEN-LAST:event_KeteranganKomplikasiKehamilanKeyPressed

    private void KeteranganTinggalDenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganTinggalDenganKeyPressed
        Valid.pindah(evt, TinggalDengan, NilaiKepercayaan);
    }//GEN-LAST:event_KeteranganTinggalDenganKeyPressed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        Valid.pindah2(evt, TCariMasalah, BtnSimpan);
    }//GEN-LAST:event_RencanaKeyPressed

    private void TCariMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilMasalah2();
        } else if ((evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) || (evt.
                getKeyCode() == KeyEvent.VK_TAB)) {
            Rencana.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
//            KetDokter.requestFocus();
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
//            KetDokter.requestFocus();
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

    private void BtnTambahMasalah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalah1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        MasterMasalahKeperawatan form = new MasterMasalahKeperawatan(null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahMasalah1ActionPerformed

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
        MasterRencanaKeperawatan form = new MasterRencanaKeperawatan(null, false);
        form.isCek();
        form.setSize(internalFrame1.getWidth() - 20,
                internalFrame1.getHeight() - 20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnTambahRencanaActionPerformed

    private void tbMasalahKeperawatanKebidananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKebidananMouseClicked
        if (tabModeMasalah.getRowCount() != 0) {
            try {
                tampilRencana2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKebidananMouseClicked

    private void tbMasalahKeperawatanKebidananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKebidananKeyPressed
        if (tabModeMasalah.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCariMasalah.setText("");
                TCariMasalah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKebidananKeyPressed

    private void tbMasalahKeperawatanKebidananKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasalahKeperawatanKebidananKeyReleased
        if (tabModeMasalah.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.
                    getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilRencana2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMasalahKeperawatanKebidananKeyReleased

    private void MasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MasalahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasalahKeyPressed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
            tbRencanaKeperawatanKebidanan.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
            tbRencanaKeperawatanKebidanan.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void RPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RPSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RPSKeyPressed

    private void RObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RObatKeyPressed

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
            RMPenilaianAwalKeperawatanKebidananRanap dialog = new RMPenilaianAwalKeperawatanKebidananRanap(
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
    private widget.TextBox A;
    private widget.TextBox ANC;
    private widget.TextBox ANCKe;
    private widget.ComboBox AdakahPerilaku;
    private widget.TextBox Agama;
    private widget.ComboBox Aktifitas;
    private widget.ComboBox AktifitasSehari2;
    private widget.TextBox AlasanBerhentiKB;
    private widget.ComboBox AlatAmbulasi;
    private widget.TextBox Alergi;
    private widget.ComboBox Anamnesis;
    private widget.TextBox BB;
    private widget.TextBox BBPB;
    private widget.TextBox Bahasa;
    private widget.TextBox BanyaknyaPembalut;
    private widget.TextBox BerhentiKB;
    private widget.ComboBox Berjalan;
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
    private widget.Button BtnHapusRiwayatPersalinan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluarKehamilan;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas2;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanRiwayatKehamilan;
    private widget.Button BtnTambahMasalah;
    private widget.Button BtnTambahMasalah1;
    private widget.Button BtnTambahRencana;
    private widget.ComboBox CTG;
    private widget.TextBox CaraBayar;
    private widget.ComboBox CaraMasuk;
    private widget.CekBox ChkAccor;
    private widget.TextBox DJJ;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DetailMasalah;
    private widget.TextArea DetailRencana;
    private widget.ComboBox DiagnosaKhususGizi;
    private widget.ComboBox DiketahuiDietisen;
    private widget.ComboBox DirasakanMenstruasi;
    private javax.swing.JDialog DlgRiwayatPersalinan;
    private widget.TextBox Durasi;
    private widget.ComboBox EdukasiPsikolgis;
    private widget.ComboBox EkstrimitasAtas;
    private widget.ComboBox EkstrimitasBawah;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox G;
    private widget.TextBox GCS;
    private widget.TextBox GD;
    private widget.ComboBox GangguanJiwa;
    private widget.Tanggal HPHT;
    private widget.TextBox Hidup;
    private widget.TextBox Hodge;
    private widget.ComboBox HubunganAnggotaKeluarga;
    private widget.ComboBox Inspekulo;
    private widget.ComboBox JK;
    private widget.TextBox JenisPersalinan;
    private widget.TextBox Jk;
    private widget.TextBox KaliMenikah;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas2;
    private widget.TextBox Keadaan;
    private widget.ComboBox KeadaanMentalUmum;
    private widget.ComboBox KebiasaanAlkohol;
    private widget.TextBox KebiasaanJumlahAlkohol;
    private widget.TextBox KebiasaanJumlahRokok;
    private widget.ComboBox KebiasaanMerokok;
    private widget.ComboBox KebiasaanNarkoba;
    private widget.ComboBox KebiasaanObat;
    private widget.TextBox KebiasaanObatDiminum;
    private widget.TextBox Kekuatan;
    private widget.ComboBox KeluhanHamilMuda;
    private widget.ComboBox KeluhanHamilTua;
    private widget.TextArea KeluhanUtama;
    private widget.ComboBox KemampuanKoordinasi;
    private widget.ComboBox KemampuanMenggenggam;
    private widget.TextBox KesadaranMental;
    private widget.ComboBox KesimpulanGangguanFungsi;
    private widget.TextBox KetHalanganLain;
    private widget.TextBox KetNyeri;
    private widget.TextBox KetPadaDokter;
    private widget.TextBox KetProvokes;
    private widget.TextBox KetQuality;
    private widget.ComboBox KetSiklusMenstruasi;
    private widget.TextBox KeteranganAdakahPerilaku;
    private widget.TextBox KeteranganBerjalan;
    private widget.TextBox KeteranganCTG;
    private widget.ComboBox KeteranganDJJ;
    private widget.TextBox KeteranganDiagnosaKhususGizi;
    private widget.TextBox KeteranganDiketahuiDietisen;
    private widget.TextBox KeteranganEdukasiPsikologis;
    private widget.TextBox KeteranganEkstrimitasAtas;
    private widget.TextBox KeteranganEkstrimitasBawah;
    private widget.TextBox KeteranganInspekulo;
    private widget.TextBox KeteranganKemampuanKoordinasi;
    private widget.TextBox KeteranganKemampuanMenggenggam;
    private widget.TextBox KeteranganKomplikasiKB;
    private widget.TextBox KeteranganKomplikasiKehamilan;
    private widget.TextBox KeteranganLakmus;
    private widget.TextBox KeteranganNilaiKepercayaan;
    private widget.TextBox KeteranganTinggalDengan;
    private widget.TextBox Ketuban;
    private widget.ComboBox KomplikasiKB;
    private widget.ComboBox KomplikasiKehamilan;
    private widget.ComboBox KondisiPsikologis;
    private widget.TextBox Kontraksi;
    private widget.ComboBox Kriteria1;
    private widget.ComboBox Kriteria2;
    private widget.ComboBox Kriteria3;
    private widget.ComboBox Kriteria4;
    private widget.Label LCount;
    private widget.TextBox LILA;
    private widget.Label LabelServiks;
    private widget.ComboBox Lakmus;
    private widget.TextBox LamaMenstruasi;
    private widget.TextBox LamanyaKB;
    private widget.TextBox LamanyaKontraksi;
    private widget.TextBox Letak;
    private widget.TextBox Lokasi;
    private widget.TextArea Masalah;
    private widget.ComboBox Menyebar;
    private widget.ComboBox MotivasiKesembuhanIbadah;
    private widget.TextBox Nadi;
    private widget.TextBox NilaiGizi1;
    private widget.TextBox NilaiGizi2;
    private widget.TextBox NilaiGiziTotal;
    private widget.ComboBox NilaiKepercayaan;
    private widget.TextBox NilaiResiko1;
    private widget.TextBox NilaiResiko2;
    private widget.TextBox NilaiResiko3;
    private widget.TextBox NilaiResiko4;
    private widget.TextBox NilaiResiko5;
    private widget.TextBox NilaiResiko6;
    private widget.TextBox NilaiResikoTotal;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas2;
    private widget.ComboBox Nyeri;
    private widget.ComboBox NyeriHilang;
    private widget.TextBox P;
    private widget.TextArea PSK;
    private widget.ComboBox PadaDokter;
    private widget.PanelBiasa PanelAccor;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox PekerjaanPasien;
    private widget.TextBox PembukaanServiks;
    private widget.ComboBox PemeriksaanDada;
    private widget.ComboBox PemeriksaanEkstrimitas;
    private widget.ComboBox PemeriksaanGenitalia;
    private widget.ComboBox PemeriksaanHidung;
    private widget.ComboBox PemeriksaanKepala;
    private widget.ComboBox PemeriksaanLeher;
    private widget.ComboBox PemeriksaanMata;
    private widget.ComboBox PemeriksaanMuka;
    private widget.ComboBox PemeriksaanMulut;
    private widget.ComboBox PemeriksaanPanggul;
    private widget.ComboBox PemeriksaanPerut;
    private widget.ComboBox PemeriksaanTelinga;
    private widget.ComboBox PendidikanPJ;
    private widget.TextBox PendidikanPasien;
    private widget.TextBox Penolong;
    private widget.TextBox Penurunan;
    private widget.TextBox Penyulit;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox Portio;
    private widget.TextBox Presentasi;
    private widget.ComboBox Provokes;
    private widget.ComboBox Quality;
    private widget.TextArea RBedah;
    private widget.TextArea RObat;
    private widget.TextArea RPK;
    private widget.TextArea RPS;
    private widget.TextBox RR;
    private widget.TextArea Rencana;
    private widget.ComboBox RiwayatANC;
    private widget.ComboBox RiwayatGenekologi;
    private widget.ComboBox RiwayatImunisasi;
    private widget.ComboBox RiwayatKB;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox Sholat;
    private widget.TextBox SiklusMenstruasi;
    private widget.ComboBox SkalaGizi1;
    private widget.ComboBox SkalaGizi2;
    private widget.ComboBox SkalaNyeri;
    private widget.ComboBox SkalaResiko1;
    private widget.ComboBox SkalaResiko2;
    private widget.ComboBox SkalaResiko3;
    private widget.ComboBox SkalaResiko4;
    private widget.ComboBox SkalaResiko5;
    private widget.ComboBox SkalaResiko6;
    private widget.TextBox SpO2;
    private widget.ComboBox StatusKawin1;
    private widget.ComboBox StatusKawin2;
    private widget.ComboBox StatusKawin3;
    private widget.ComboBox StatusMenikah;
    private widget.TextBox Suhu;
    private widget.TextBox TB;
    private widget.TextBox TBJ;
    private widget.TextBox TCari;
    private widget.TextBox TCariMasalah;
    private widget.TextBox TCariRencana;
    private widget.TextBox TD;
    private widget.TextBox TFU;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.Tanggal TP;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabMasalahKeperawatan;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabRencanaKeperawatan;
    private widget.Tanggal TanggalPersalinan;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextBox TempatPersalinan;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox TibadiRuang;
    private widget.ComboBox TinggalDengan;
    private widget.Label TingkatResiko;
    private widget.ComboBox Toharah;
    private widget.TextBox UmurMinarche;
    private widget.TextBox UsiaHamil;
    private widget.TextBox UsiaKawin1;
    private widget.TextBox UsiaKawin2;
    private widget.TextBox UsiaKawin3;
    private widget.TextBox UsiaKehamilan;
    private widget.ComboBox Wajib;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
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
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel15;
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
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel289;
    private widget.Label jLabel29;
    private widget.Label jLabel297;
    private widget.Label jLabel298;
    private widget.Label jLabel299;
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
    private widget.Label jLabel54;
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
    private widget.Label jLabel68;
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
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
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
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
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
    private widget.PanelBiasa panelBiasa3;
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
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbMasalahDetail;
    private widget.Table tbMasalahKeperawatanKebidanan;
    private widget.Table tbObat;
    private widget.Table tbRencanaDetail;
    private widget.Table tbRencanaKeperawatanKebidanan;
    private widget.Table tbRiwayatKehamilan;
    private widget.Table tbRiwayatKehamilan1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select penilaian_awal_keperawatan_kebidanan_ranap.no_rawat,penilaian_awal_keperawatan_kebidanan_ranap.tanggal,penilaian_awal_keperawatan_kebidanan_ranap.informasi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.tiba_diruang_rawat,penilaian_awal_keperawatan_kebidanan_ranap.cara_masuk,penilaian_awal_keperawatan_kebidanan_ranap.keluhan,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.rpk,penilaian_awal_keperawatan_kebidanan_ranap.psk,penilaian_awal_keperawatan_kebidanan_ranap.rp,penilaian_awal_keperawatan_kebidanan_ranap.rps,penilaian_awal_keperawatan_kebidanan_ranap.robat,penilaian_awal_keperawatan_kebidanan_ranap.alergi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.keterangan_komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_umur,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_banyaknya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_siklus,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_ket_siklus,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_dirasakan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_status,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_status,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia1,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia1,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia3,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia3,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_g,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_p,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_a,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_hidup,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_hpht,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_usiahamil,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_tp,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_imunisasi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_anc,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ancke,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ket_ancke,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_muda,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_tua,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_komplikasi,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_ket_komplikasi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_kapaberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_alasanberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_genekologi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_merokok,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_merokok,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_alkohol,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_alkohol,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_narkoba,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_mental,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_keadaan_umum,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_gcs,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_td,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_nadi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_rr,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_suhu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_spo2,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_bb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lila,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tfu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tbj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_letak,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_presentasi,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_penurunan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_his,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_kekuatan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_djj,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_djj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_portio,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_pembukaan,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ketuban,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_hodge,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_panggul,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lakmus,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_lakmus,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ctg,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_ctg,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_kepala,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_muka,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mata,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_hidung,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_telinga,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mulut,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_leher,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_dada,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_perut,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_genitalia,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_ekstrimitas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_aktifitas,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_aktivitas,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ambulasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_atas,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_bawah,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_bawah,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_menggenggam,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_kemampuan_menggenggam,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_gangguan_fungsi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_kondisipsiko,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_adakah_prilaku,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_adakah_prilaku,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_hubungan_pasien,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_tinggal_dengan,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_tinggal_dengan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_budaya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_budaya,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_pend_pj,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_edukasi_pada,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_edukasi_pada,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_wajib,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_halangan_lain,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_toharah,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_sholat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_motivasi_kesembuhan_ibadah,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_penyebab,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_lokasi,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_skala,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_waktu,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_diberitahukan_dokter,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala1,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai1,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala3,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai3,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala4,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai4,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala6,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai6,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_totalnilai,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi1,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi1,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi2,penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi2,bahasa_pasien.nama_bahasa,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.nilai_total_gizi,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_ket_diagnosa_khusus,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_jam_diketahui_dietisen,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.kriteria1,penilaian_awal_keperawatan_kebidanan_ranap.kriteria2,penilaian_awal_keperawatan_kebidanan_ranap.kriteria3,penilaian_awal_keperawatan_kebidanan_ranap.kriteria4,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pilihan1,penilaian_awal_keperawatan_kebidanan_ranap.pilihan2,penilaian_awal_keperawatan_kebidanan_ranap.pilihan3,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pilihan4,penilaian_awal_keperawatan_kebidanan_ranap.pilihan5,penilaian_awal_keperawatan_kebidanan_ranap.pilihan6,"
                    + "penilaian_awal_keperawatan_kebidanan_ranap.pilihan7,penilaian_awal_keperawatan_kebidanan_ranap.pilihan8,penilaian_awal_keperawatan_kebidanan_ranap.masalah,penilaian_awal_keperawatan_kebidanan_ranap.rencana,penilaian_awal_keperawatan_kebidanan_ranap.nip1,penilaian_awal_keperawatan_kebidanan_ranap.nip2,penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter, "
                    + "pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join penilaian_awal_keperawatan_kebidanan_ranap on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan_ranap.no_rawat "
                    + "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_kebidanan_ranap.nip1=pengkaji1.nip "
                    + "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_kebidanan_ranap.nip2=pengkaji2.nip "
                    + "inner join dokter on penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter=dokter.kd_dokter "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "
                    + "penilaian_awal_keperawatan_kebidanan_ranap.tanggal between ? and ? "
                    + (TCari.getText().trim().isEmpty() ? ""
                    : "and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_kebidanan_ranap.nip1 like ? or pengkaji1.nama like ? or penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter like ? or dokter.nm_dokter like ?)")
                    + " order by penilaian_awal_keperawatan_kebidanan_ranap.tanggal");

            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
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
                    tabMode.addRow(new String[]{rs.getString("no_rawat"), rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), rs.getString("tgl_lahir"), rs.getString("jk"),
                        rs.getString("nip1"), rs.getString("pengkaji1"), rs.getString("nip2"),
                        rs.getString("pengkaji2"), rs.getString("kd_dokter"), rs.getString("nm_dokter"),
                        rs.getString("tanggal"), rs.getString("informasi"), rs.getString("tiba_diruang_rawat"),
                        rs.getString("cara_masuk"), rs.getString("keluhan"), rs.getString("psk"),
                        rs.getString("rpk"), rs.getString("rp"), rs.getString("rps"), rs.getString("robat"),
                        rs.getString("alergi"), rs.getString("komplikasi_sebelumnya"),
                        rs.getString("keterangan_komplikasi_sebelumnya"), rs.getString("riwayat_mens_umur"),
                        rs.getString("riwayat_mens_lamanya"), rs.getString("riwayat_mens_banyaknya"),
                        rs.getString("riwayat_mens_siklus"), rs.getString("riwayat_mens_ket_siklus"),
                        rs.getString("riwayat_mens_dirasakan"), rs.getString("riwayat_perkawinan_status"),
                        rs.getString("riwayat_perkawinan_ket_status"), rs.getString("riwayat_perkawinan_usia1"),
                        rs.getString("riwayat_perkawinan_ket_usia1"), rs.getString("riwayat_perkawinan_usia2"),
                        rs.getString("riwayat_perkawinan_ket_usia2"), rs.getString("riwayat_perkawinan_usia3"),
                        rs.getString("riwayat_perkawinan_ket_usia3"), rs.getString("riwayat_persalinan_g"),
                        rs.getString("riwayat_persalinan_p"), rs.getString("riwayat_persalinan_a"),
                        rs.getString("riwayat_persalinan_hidup"), rs.getString("riwayat_hamil_hpht"),
                        rs.getString("riwayat_hamil_usiahamil"), rs.getString("riwayat_hamil_tp"),
                        rs.getString("riwayat_hamil_imunisasi"), rs.getString("riwayat_hamil_anc"),
                        rs.getString("riwayat_hamil_ancke"), rs.getString("riwayat_hamil_ket_ancke"),
                        rs.getString("riwayat_hamil_keluhan_hamil_muda"),
                        rs.getString("riwayat_hamil_keluhan_hamil_tua"), rs.getString("riwayat_kb"),
                        rs.getString("riwayat_kb_lamanya"), rs.getString("riwayat_kb_komplikasi"),
                        rs.getString("riwayat_kb_ket_komplikasi"), rs.getString("riwayat_kb_kapaberhenti"),
                        rs.getString("riwayat_kb_alasanberhenti"), rs.getString("riwayat_genekologi"),
                        rs.getString("riwayat_kebiasaan_obat"), rs.getString("riwayat_kebiasaan_ket_obat"),
                        rs.getString("riwayat_kebiasaan_merokok"), rs.getString("riwayat_kebiasaan_ket_merokok"),
                        rs.getString("riwayat_kebiasaan_alkohol"), rs.getString("riwayat_kebiasaan_ket_alkohol"),
                        rs.getString("riwayat_kebiasaan_narkoba"), rs.getString("pemeriksaan_kebidanan_mental"),
                        rs.getString("pemeriksaan_kebidanan_keadaan_umum"),
                        rs.getString("pemeriksaan_kebidanan_gcs"), rs.getString("pemeriksaan_kebidanan_td"),
                        rs.getString("pemeriksaan_kebidanan_nadi"), rs.getString("pemeriksaan_kebidanan_rr"),
                        rs.getString("pemeriksaan_kebidanan_suhu"), rs.getString("pemeriksaan_kebidanan_spo2"),
                        rs.getString("pemeriksaan_kebidanan_bb"), rs.getString("pemeriksaan_kebidanan_tb"),
                        rs.getString("pemeriksaan_kebidanan_lila"), rs.getString("pemeriksaan_kebidanan_tfu"),
                        rs.getString("pemeriksaan_kebidanan_tbj"), rs.getString("pemeriksaan_kebidanan_letak"),
                        rs.getString("pemeriksaan_kebidanan_presentasi"),
                        rs.getString("pemeriksaan_kebidanan_penurunan"),
                        rs.getString("pemeriksaan_kebidanan_penurunan"), rs.getString("pemeriksaan_kebidanan_his"),
                        rs.getString("pemeriksaan_kebidanan_kekuatan"),
                        rs.getString("pemeriksaan_kebidanan_lamanya"), rs.getString("pemeriksaan_kebidanan_djj"),
                        rs.getString("pemeriksaan_kebidanan_ket_djj"), rs.getString("pemeriksaan_kebidanan_portio"),
                        rs.getString("pemeriksaan_kebidanan_pembukaan"),
                        rs.getString("pemeriksaan_kebidanan_ketuban"), rs.getString("pemeriksaan_kebidanan_hodge"),
                        rs.getString("pemeriksaan_kebidanan_panggul"),
                        rs.getString("pemeriksaan_kebidanan_inspekulo"),
                        rs.getString("pemeriksaan_kebidanan_ket_inspekulo"),
                        rs.getString("pemeriksaan_kebidanan_lakmus"),
                        rs.getString("pemeriksaan_kebidanan_ket_lakmus"), rs.getString("pemeriksaan_kebidanan_ctg"),
                        rs.getString("pemeriksaan_kebidanan_ket_ctg"), rs.getString("pemeriksaan_umum_kepala"),
                        rs.getString("pemeriksaan_umum_muka"), rs.getString("pemeriksaan_umum_mata"),
                        rs.getString("pemeriksaan_umum_hidung"), rs.getString("pemeriksaan_umum_telinga"),
                        rs.getString("pemeriksaan_umum_mulut"), rs.getString("pemeriksaan_umum_leher"),
                        rs.getString("pemeriksaan_umum_dada"), rs.getString("pemeriksaan_umum_perut"),
                        rs.getString("pemeriksaan_umum_genitalia"), rs.getString("pemeriksaan_umum_ekstrimitas"),
                        rs.getString("pengkajian_fungsi_kemampuan_aktifitas"),
                        rs.getString("pengkajian_fungsi_berjalan"), rs.getString("pengkajian_fungsi_ket_berjalan"),
                        rs.getString("pengkajian_fungsi_aktivitas"), rs.getString("pengkajian_fungsi_ambulasi"),
                        rs.getString("pengkajian_fungsi_ekstrimitas_atas"),
                        rs.getString("pengkajian_fungsi_ket_ekstrimitas_atas"),
                        rs.getString("pengkajian_fungsi_ekstrimitas_bawah"),
                        rs.getString("pengkajian_fungsi_ket_ekstrimitas_bawah"),
                        rs.getString("pengkajian_fungsi_kemampuan_menggenggam"),
                        rs.getString("pengkajian_fungsi_ket_kemampuan_menggenggam"),
                        rs.getString("pengkajian_fungsi_koordinasi"),
                        rs.getString("pengkajian_fungsi_ket_koordinasi"),
                        rs.getString("pengkajian_fungsi_gangguan_fungsi"),
                        rs.getString("riwayat_psiko_kondisipsiko"), rs.getString("riwayat_psiko_adakah_prilaku"),
                        rs.getString("riwayat_psiko_ket_adakah_prilaku"),
                        rs.getString("riwayat_psiko_gangguan_jiwa"), rs.getString("riwayat_psiko_hubungan_pasien"),
                        rs.getString("agama"), rs.getString("riwayat_psiko_tinggal_dengan"),
                        rs.getString("riwayat_psiko_ket_tinggal_dengan"), rs.getString("pekerjaan"),
                        rs.getString("png_jawab"), rs.getString("riwayat_psiko_budaya"),
                        rs.getString("riwayat_psiko_ket_budaya"), rs.getString("nama_bahasa"), rs.getString("pnd"),
                        rs.getString("riwayat_psiko_pend_pj"), rs.getString("riwayat_psiko_edukasi_pada"),
                        rs.getString("riwayat_psiko_ket_edukasi_pada"), rs.getString("riwayat_psiko_wajib"),
                        rs.getString("riwayat_psiko_ket_halangan_lain"), rs.getString("riwayat_psiko_toharah"),
                        rs.getString("riwayat_psiko_sholat"),
                        rs.getString("riwayat_psiko_motivasi_kesembuhan_ibadah"), rs.getString("penilaian_nyeri"),
                        rs.getString("penilaian_nyeri_penyebab"), rs.getString("penilaian_nyeri_ket_penyebab"),
                        rs.getString("penilaian_nyeri_kualitas"), rs.getString("penilaian_nyeri_ket_kualitas"),
                        rs.getString("penilaian_nyeri_lokasi"), rs.getString("penilaian_nyeri_menyebar"),
                        rs.getString("penilaian_nyeri_skala"), rs.getString("penilaian_nyeri_waktu"),
                        rs.getString("penilaian_nyeri_hilang"), rs.getString("penilaian_nyeri_ket_hilang"),
                        rs.getString("penilaian_nyeri_diberitahukan_dokter"),
                        rs.getString("penilaian_nyeri_jam_diberitahukan_dokter"),
                        rs.getString("penilaian_jatuh_skala1"), rs.getString("penilaian_jatuh_nilai1"),
                        rs.getString("penilaian_jatuh_skala2"), rs.getString("penilaian_jatuh_nilai2"),
                        rs.getString("penilaian_jatuh_skala3"), rs.getString("penilaian_jatuh_nilai3"),
                        rs.getString("penilaian_jatuh_skala4"), rs.getString("penilaian_jatuh_nilai4"),
                        rs.getString("penilaian_jatuh_skala5"), rs.getString("penilaian_jatuh_nilai5"),
                        rs.getString("penilaian_jatuh_skala6"), rs.getString("penilaian_jatuh_nilai6"),
                        rs.getString("penilaian_jatuh_totalnilai"), rs.getString("skrining_gizi1"),
                        rs.getString("nilai_gizi1"), rs.getString("skrining_gizi2"), rs.getString("nilai_gizi2"),
                        rs.getString("nilai_total_gizi"), rs.getString("skrining_gizi_diagnosa_khusus"),
                        rs.getString("skrining_gizi_ket_diagnosa_khusus"),
                        rs.getString("skrining_gizi_diketahui_dietisen"),
                        rs.getString("skrining_gizi_jam_diketahui_dietisen"), rs.getString("kriteria1"),
                        rs.getString("kriteria2"), rs.getString("kriteria3"), rs.getString("kriteria4"),
                        rs.getString("pilihan1"), rs.getString("pilihan2"), rs.getString("pilihan3"),
                        rs.getString("pilihan4"), rs.getString("pilihan5"), rs.getString("pilihan6"),
                        rs.getString("pilihan7"), rs.getString("pilihan8"), rs.getString("masalah"),
                        rs.getString("rencana")});
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

    /**
     *
     */
    public void emptTeks() {
        TglAsuhan.setDate(new Date());
        Anamnesis.setSelectedIndex(0);
        TibadiRuang.setSelectedIndex(0);
        CaraMasuk.setSelectedIndex(0);
        KeluhanUtama.setText("");
        RPK.setText("");
        PSK.setText("");
        RBedah.setText("");
        RPS.setText("");
        RObat.setText("");
        Alergi.setText("");
        KomplikasiKehamilan.setSelectedIndex(0);
        KeteranganKomplikasiKehamilan.setText("");
        UmurMinarche.setText("");
        LamaMenstruasi.setText("");
        BanyaknyaPembalut.setText("");
        SiklusMenstruasi.setText("");
        KetSiklusMenstruasi.setSelectedIndex(0);
        DirasakanMenstruasi.setSelectedIndex(0);
        StatusMenikah.setSelectedIndex(0);
        KaliMenikah.setText("");
        UsiaKawin1.setText("");
        StatusKawin1.setSelectedIndex(0);
        UsiaKawin2.setText("");
        StatusKawin2.setSelectedIndex(0);
        UsiaKawin3.setText("");
        StatusKawin3.setSelectedIndex(0);
        G.setText("");
        P.setText("");
        A.setText("");
        Hidup.setText("");
        HPHT.setDate(new Date());
        UsiaHamil.setText("");
        TP.setDate(new Date());
        RiwayatImunisasi.setSelectedIndex(0);
        ANC.setText("");
        ANCKe.setText("");
        RiwayatANC.setSelectedIndex(0);
        KeluhanHamilTua.setSelectedIndex(0);
        KeluhanHamilMuda.setSelectedIndex(0);
        RiwayatKB.setSelectedIndex(0);
        LamanyaKB.setText("");
        KomplikasiKB.setSelectedIndex(0);
        KeteranganKomplikasiKB.setText("");
        BerhentiKB.setText("");
        AlasanBerhentiKB.setText("");
        RiwayatGenekologi.setSelectedIndex(0);
        KebiasaanObat.setSelectedItem(0);
        KebiasaanObatDiminum.setText("");
        KebiasaanMerokok.setSelectedIndex(0);
        KebiasaanJumlahRokok.setText("");
        KebiasaanAlkohol.setSelectedIndex(0);
        KebiasaanJumlahAlkohol.setText("");
        KebiasaanNarkoba.setSelectedIndex(0);
        KesadaranMental.setText("");
        KeadaanMentalUmum.setSelectedIndex(0);
        GCS.setText("");
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        SpO2.setText("");
        BB.setText("");
        TB.setText("");
        LILA.setText("");
        TFU.setText("");
        TBJ.setText("");
        GD.setText("");
        Letak.setText("");
        Presentasi.setText("");
        Penurunan.setText("");
        Kontraksi.setText("");
        Kekuatan.setText("");
        LamanyaKontraksi.setText("");
        DJJ.setText("");
        KeteranganDJJ.setSelectedIndex(0);
        Portio.setText("");
        PembukaanServiks.setText("");
        Ketuban.setText("");
        Hodge.setText("");
        PemeriksaanPanggul.setSelectedIndex(0);
        Inspekulo.setSelectedIndex(0);
        KeteranganInspekulo.setText("");
        Lakmus.setSelectedIndex(0);
        KeteranganLakmus.setText("");
        CTG.setSelectedIndex(0);
        KeteranganCTG.setText("");
        PemeriksaanKepala.setSelectedIndex(0);
        PemeriksaanMuka.setSelectedIndex(0);
        PemeriksaanMata.setSelectedIndex(0);
        PemeriksaanHidung.setSelectedIndex(0);
        PemeriksaanTelinga.setSelectedIndex(0);
        PemeriksaanMulut.setSelectedIndex(0);
        PemeriksaanLeher.setSelectedIndex(0);
        PemeriksaanDada.setSelectedIndex(0);
        PemeriksaanPerut.setSelectedIndex(0);
        PemeriksaanGenitalia.setSelectedIndex(0);
        PemeriksaanEkstrimitas.setSelectedIndex(0);
        AktifitasSehari2.setSelectedIndex(0);
        Berjalan.setSelectedIndex(0);
        KeteranganBerjalan.setText("");
        Aktifitas.setSelectedIndex(0);
        AlatAmbulasi.setSelectedIndex(0);
        EkstrimitasAtas.setSelectedIndex(0);
        KeteranganEkstrimitasAtas.setText("");
        EkstrimitasBawah.setSelectedIndex(0);
        KeteranganEkstrimitasBawah.setText("");
        KemampuanMenggenggam.setSelectedIndex(0);
        KeteranganKemampuanMenggenggam.setText("");
        KemampuanKoordinasi.setSelectedIndex(0);
        KeteranganKemampuanKoordinasi.setText("");
        KesimpulanGangguanFungsi.setSelectedIndex(0);
        KondisiPsikologis.setSelectedIndex(0);
        AdakahPerilaku.setSelectedIndex(0);
        KeteranganAdakahPerilaku.setText("");
        GangguanJiwa.setSelectedIndex(0);
        HubunganAnggotaKeluarga.setSelectedIndex(0);
        TinggalDengan.setSelectedIndex(0);
        KeteranganTinggalDengan.setText("");
        NilaiKepercayaan.setSelectedIndex(0);
        KeteranganNilaiKepercayaan.setText("");
        PendidikanPJ.setSelectedIndex(0);
        EdukasiPsikolgis.setSelectedIndex(0);
        KeteranganEdukasiPsikologis.setText("");
        Nyeri.setSelectedIndex(0);
        Provokes.setSelectedIndex(0);
        KetProvokes.setText("");
        Quality.setSelectedIndex(0);
        KetQuality.setText("");
        Lokasi.setText("");
        Menyebar.setSelectedIndex(0);
        SkalaNyeri.setSelectedIndex(0);
        Durasi.setText("");
        NyeriHilang.setSelectedIndex(0);
        KetNyeri.setText("");
        PadaDokter.setSelectedIndex(0);
        KetPadaDokter.setText("");
        SkalaResiko1.setSelectedIndex(0);
        NilaiResiko1.setText("0");
        SkalaResiko2.setSelectedIndex(0);
        NilaiResiko2.setText("0");
        SkalaResiko3.setSelectedIndex(0);
        NilaiResiko3.setText("0");
        SkalaResiko4.setSelectedIndex(0);
        NilaiResiko4.setText("0");
        SkalaResiko5.setSelectedIndex(0);
        NilaiResiko5.setText("0");
        SkalaResiko6.setSelectedIndex(0);
        NilaiResiko6.setText("0");
        NilaiResikoTotal.setText("0");
        TingkatResiko
                .setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        SkalaGizi1.setSelectedIndex(0);
        NilaiGizi1.setText("0");
        SkalaGizi2.setSelectedIndex(0);
        NilaiGizi2.setText("0");
        NilaiGiziTotal.setText("0");
        DiagnosaKhususGizi.setSelectedIndex(0);
        KeteranganDiagnosaKhususGizi.setText("");
        DiketahuiDietisen.setSelectedIndex(0);
        KeteranganDiketahuiDietisen.setText("");
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
        Masalah.setText("");
        Rencana.setText("");
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false, i, 0);
        }
        Valid.tabelKosong(tabModeRencana);
        TabRawat.setSelectedIndex(0);
        Anamnesis.requestFocus();
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            KdPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 7).toString());
            NmPetugas2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            KdDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            Anamnesis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            TibadiRuang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            CaraMasuk.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            KeluhanUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString());
            PSK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            RPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString());
            RBedah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 18).toString());
            RPS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString());
            RObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString());
            Alergi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
            KomplikasiKehamilan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString());
            KeteranganKomplikasiKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString());
            UmurMinarche.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString());
            LamaMenstruasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString());
            BanyaknyaPembalut.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString());
            SiklusMenstruasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString());
            KetSiklusMenstruasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString());
            DirasakanMenstruasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString());
            StatusMenikah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 30).toString());
            KaliMenikah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 31).toString());
            UsiaKawin1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 32).toString());
            StatusKawin1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 33).toString());
            UsiaKawin2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 34).toString());
            StatusKawin2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 35).toString());
            UsiaKawin3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 36).toString());
            StatusKawin3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 37).toString());
            G.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 38).toString());
            P.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 39).toString());
            A.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 40).toString());
            Hidup.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 41).toString());
            UsiaKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 43).toString());
            RiwayatImunisasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 45).toString());
            ANC.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 46).toString());
            ANCKe.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 47).toString());
            RiwayatANC.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 48).toString());
            KeluhanHamilMuda.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 49).toString());
            KeluhanHamilTua.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 50).toString());
            RiwayatKB.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 51).toString());
            LamanyaKB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 52).toString());
            KomplikasiKB.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 53).toString());
            KeteranganKomplikasiKB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 54).toString());
            BerhentiKB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 55).toString());
            AlasanBerhentiKB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 56).toString());
            RiwayatGenekologi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 57).toString());
            KebiasaanObat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 58).toString());
            KebiasaanObatDiminum.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 59).toString());
            KebiasaanMerokok.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 60).toString());
            KebiasaanJumlahRokok.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 61).toString());
            KebiasaanAlkohol.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 62).toString());
            KebiasaanJumlahAlkohol.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 63).toString());
            KebiasaanNarkoba.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 64).toString());
            KesadaranMental.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 65).toString());
            KeadaanMentalUmum.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 66).toString());
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 67).toString());
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 68).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 69).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 70).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 71).toString());
            SpO2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 72).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 73).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 74).toString());
            LILA.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 75).toString());
            TFU.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 76).toString());
            TBJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 77).toString());
            GD.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 78).toString());
            Letak.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 79).toString());
            Presentasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 80).toString());
            Penurunan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 81).toString());
            Kontraksi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 82).toString());
            Kekuatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 83).toString());
            LamanyaKontraksi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 84).toString());
            DJJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 85).toString());
            KeteranganDJJ.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 86).toString());
            Portio.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 87).toString());
            PembukaanServiks.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 88).toString());
            Ketuban.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 89).toString());
            Hodge.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 90).toString());
            PemeriksaanPanggul.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 91).toString());
            Inspekulo.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 92).toString());
            KeteranganInspekulo.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 93).toString());
            Lakmus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 94).toString());
            KeteranganLakmus.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 95).toString());
            CTG.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 96).toString());
            KeteranganCTG.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 97).toString());
            PemeriksaanKepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 98).toString());
            PemeriksaanMuka.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 99).toString());
            PemeriksaanMata.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 100).toString());
            PemeriksaanHidung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 101).toString());
            PemeriksaanTelinga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 102).toString());
            PemeriksaanMulut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 103).toString());
            PemeriksaanLeher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 104).toString());
            PemeriksaanDada.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 105).toString());
            PemeriksaanPerut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 106).toString());
            PemeriksaanGenitalia.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 107).toString());
            PemeriksaanEkstrimitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 108).toString());
            AktifitasSehari2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 109).toString());
            Berjalan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 110).toString());
            KeteranganBerjalan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 111).toString());
            Aktifitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 112).toString());
            AlatAmbulasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 113).toString());
            EkstrimitasAtas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 114).toString());
            KeteranganEkstrimitasAtas.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 115).toString());
            EkstrimitasBawah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 116).toString());
            KeteranganEkstrimitasBawah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 117).toString());
            KemampuanMenggenggam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 118).toString());
            KeteranganKemampuanMenggenggam.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 119).toString());
            KemampuanKoordinasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 120).toString());
            KeteranganKemampuanKoordinasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 121).toString());
            KesimpulanGangguanFungsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 122).toString());
            KondisiPsikologis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 123).toString());
            AdakahPerilaku.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 124).toString());
            KeteranganAdakahPerilaku.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 125).toString());
            GangguanJiwa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 126).toString());
            HubunganAnggotaKeluarga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 127).toString());
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 128).toString());
            TinggalDengan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 129).toString());
            KeteranganTinggalDengan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 130).toString());
            PekerjaanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 131).toString());
            CaraBayar.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 132).toString());
            NilaiKepercayaan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 133).toString());
            KeteranganNilaiKepercayaan.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 134).toString());
            Bahasa.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 135).toString());
            PendidikanPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 136).toString());
            PendidikanPJ.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 137).toString());
            EdukasiPsikolgis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 138).toString());
            KeteranganEdukasiPsikologis.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 139).toString());
            Wajib.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 140).toString());
            KetHalanganLain.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 141).toString());
            Toharah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 142).toString());
            Sholat.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 143).toString());
            MotivasiKesembuhanIbadah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 144).toString());
            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 145).toString());
            Provokes.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 146).toString());
            KetProvokes.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 147).toString());
            Quality.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 148).toString());
            KetQuality.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 149).toString());
            Lokasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 150).toString());
            Menyebar.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 151).toString());
            SkalaNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 152).toString());
            Durasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 153).toString());
            NyeriHilang.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 154).toString());
            KetNyeri.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 155).toString());
            PadaDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 156).toString());
            KetPadaDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 157).toString());
            SkalaResiko1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 158).toString());
            NilaiResiko1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 159).toString());
            SkalaResiko2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 160).toString());
            NilaiResiko2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 161).toString());
            SkalaResiko3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 162).toString());
            NilaiResiko3.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 163).toString());
            SkalaResiko4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 164).toString());
            NilaiResiko4.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 165).toString());
            SkalaResiko5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 166).toString());
            NilaiResiko5.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 167).toString());
            SkalaResiko6.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 168).toString());
            NilaiResiko6.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 169).toString());
            NilaiResikoTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 170).toString());
            SkalaGizi1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 171).toString());
            NilaiGizi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 172).toString());
            SkalaGizi2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 173).toString());
            NilaiGizi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 174).toString());
            NilaiGiziTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 175).toString());
            DiagnosaKhususGizi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 176).toString());
            KeteranganDiagnosaKhususGizi.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 177).toString());
            DiketahuiDietisen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 178).toString());
            KeteranganDiketahuiDietisen.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 179).toString());
            Kriteria1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 180).toString());
            Kriteria2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 181).toString());
            Kriteria3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 182).toString());
            Kriteria4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 183).toString());
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 184).toString().equals("Perawatan diri (Mandi, BAB, BAK)")) {
                pilihan1.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 185).toString().equals("Pemantauan pemberian obat")) {
                pilihan2.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 186).toString().equals("Pemantauan diet")) {
                pilihan3.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 187)
                    .toString()
                    .equals("Bantuan medis / perawatan di rumah (Homecare)")) {
                pilihan4.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().equals("Perawatan luka")) {
                pilihan5.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 189).toString().equals("Latihan fisik lanjutan")) {
                pilihan6.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 190)
                    .toString()
                    .equals("Pendampingan tenaga khusus di rumah")) {
                pilihan7.setSelected(true);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 191)
                    .toString()
                    .equals("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)")) {
                pilihan8.setSelected(true);
            }
            Masalah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 192).toString());
            Rencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 193).toString());
            tampilPersalinan();

            try {
                Valid.tabelKosong(tabModeMasalah);
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan_kebidanan.kode_masalah,master_masalah_keperawatan_kebidanan.nama_masalah from master_masalah_keperawatan_kebidanan "
                        + "inner join penilaian_awal_keperawatan_kebidanan_ranap_masalah on penilaian_awal_keperawatan_kebidanan_ranap_masalah.kode_masalah=master_masalah_keperawatan_kebidanan.kode_masalah "
                        + "where penilaian_awal_keperawatan_kebidanan_ranap_masalah.no_rawat=? order by penilaian_awal_keperawatan_kebidanan_ranap_masalah.kode_masalah");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeMasalah.addRow(new Object[]{true, rs.getString(1), rs.getString(2)});
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
                        "select master_rencana_keperawatan_kebidanan.kode_rencana,master_rencana_keperawatan_kebidanan.rencana_kebidanan from master_rencana_keperawatan_kebidanan "
                        + "inner join penilaian_awal_keperawatan_kebidanan_ranap_rencana on penilaian_awal_keperawatan_kebidanan_ranap_rencana.kode_rencana=master_rencana_keperawatan_kebidanan.kode_rencana "
                        + "where penilaian_awal_keperawatan_kebidanan_ranap_rencana.no_rawat=? order by penilaian_awal_keperawatan_kebidanan_ranap_rencana.kode_rencana");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeRencana.addRow(new Object[]{true, rs.getString(1), rs.getString(2)});
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

            Valid.SetTgl2(TglAsuhan, tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Valid.SetTgl(HPHT, tbObat.getValueAt(tbObat.getSelectedRow(), 40).toString());
            Valid.SetTgl(TP, tbObat.getValueAt(tbObat.getSelectedRow(), 42).toString());
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement(
                    "select pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,"
                    + "bahasa_pasien.nama_bahasa,pasien.pnd,pasien.pekerjaan,pasien.gol_darah,reg_periksa.tgl_registrasi,reg_periksa.jam_reg "
                    + "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Agama.setText(rs.getString("agama"));
                    Bahasa.setText(rs.getString("nama_bahasa"));
                    PendidikanPasien.setText(rs.getString("pnd"));
                    PekerjaanPasien.setText(rs.getString("pekerjaan"));
                    GD.setText(rs.getString("gol_darah"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"));
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

    public void setNoRm(String norwt, Date tgl2, String carabayar, String norm) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TCari.setText(norwt);
        CaraBayar.setText(carabayar);
        DTPCari2.setDate(tgl2);
        isRawat();
        tampilPersalinan();
    }

    /**
     *
     */
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        if (akses.getjml2() >= 1) {
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode());
            NmPetugas.setText(petugas.tampil3(KdPetugas.getText()));
            if (NmPetugas.getText().isEmpty()) {
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan petugas...!!");
            }
        }
        if (TANGGALMUNDUR.equals("no")) {
            if (!akses.getkode().equals("Admin Utama")) {
                TglAsuhan.setEditable(false);
                TglAsuhan.setEnabled(false);
            }
        }
    }

    public void setTampil() {
        TabRawat.setSelectedIndex(1);
    }

    private void tampilMasalah() {
        try {
            Valid.tabelKosong(tabModeMasalah);
            file = new File("./cache/masalahkeperawatankebidanan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_masalah_keperawatan_kebidanan order by master_masalah_keperawatan_kebidanan.kode_masalah");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeMasalah.addRow(new Object[]{false, rs.getString(1), rs.getString(2)});
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"NamaMasalah\":\"" + rs.getString(2)
                            + "\"},";
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
            fileWriter.write("{\"masalahkeperawatankebidanan\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
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
            for (i = 0; i < tbMasalahKeperawatanKebidanan.getRowCount(); i++) {
                if (tbMasalahKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
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
            for (i = 0; i < tbMasalahKeperawatanKebidanan.getRowCount(); i++) {
                if (tbMasalahKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbMasalahKeperawatanKebidanan.getValueAt(i, 1).toString();
                    masalah[index] = tbMasalahKeperawatanKebidanan.getValueAt(i, 2).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeMasalah);

            for (i = 0; i < jml; i++) {
                tabModeMasalah.addRow(new Object[]{pilih[i], kode[i], masalah[i]});
            }

            myObj = new FileReader("./cache/masalahkeperawatankebidanan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("masalahkeperawatankebidanan");
            if (response.isArray()) {
                for (JsonNode list : response) {
                    if (list.path("KodeMasalah").asText().toLowerCase().contains(TCariMasalah.getText().toLowerCase())
                            || list.path("NamaMasalah")
                                    .asText()
                                    .toLowerCase()
                                    .contains(TCariMasalah.getText().toLowerCase())) {
                        tabModeMasalah.addRow(new Object[]{false, list.path("KodeMasalah").asText(),
                            list.path("NamaMasalah").asText()});
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
            file = new File("./cache/rencanakeperawatankebidanan.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement(
                    "select * from master_rencana_keperawatan_kebidanan order by master_rencana_keperawatan_kebidanan.kode_rencana");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    iyem = iyem + "{\"KodeMasalah\":\"" + rs.getString(1) + "\",\"KodeRencana\":\"" + rs.getString(2)
                            + "\",\"NamaRencana\":\"" + rs.getString(3) + "\"},";
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
            fileWriter.write("{\"rencanakeperawatankebidanan\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
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
            for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
                if (tbRencanaKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
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
            for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
                if (tbRencanaKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbRencanaKeperawatanKebidanan.getValueAt(i, 1).toString();
                    masalah[index] = tbRencanaKeperawatanKebidanan.getValueAt(i, 2).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeRencana);

            for (i = 0; i < jml; i++) {
                tabModeRencana.addRow(new Object[]{pilih[i], kode[i], masalah[i]});
            }

            myObj = new FileReader("./cache/rencanakeperawatankebidanan.iyem");
            root = mapper.readTree(myObj);
            response = root.path("rencanakeperawatankebidanan");
            if (response.isArray()) {
                for (i = 0; i < tbMasalahKeperawatanKebidanan.getRowCount(); i++) {
                    if (tbMasalahKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                        for (JsonNode list : response) {
                            if (list.path("KodeMasalah")
                                    .asText()
                                    .toLowerCase()
                                    .equals(tbMasalahKeperawatanKebidanan.getValueAt(i, 1).toString())
                                    && list.path("NamaRencana")
                                            .asText()
                                            .toLowerCase()
                                            .contains(TCariRencana.getText().toLowerCase())) {
                                tabModeRencana.addRow(new Object[]{false, list.path("KodeRencana").asText(),
                                    list.path("NamaRencana").asText()});
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
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            DetailMasalah.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 192).toString());
            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 193).toString());
            Valid.tabelKosong(tabModeRiwayatKehamilan2);
            try {
                ps = koneksi.prepareStatement(
                        "select * from riwayat_persalinan_pasien where riwayat_persalinan_pasien.no_rkm_medis=? order by riwayat_persalinan_pasien.tgl_thn");
                try {
                    ps.setString(1, TNoRM1.getText());
                    rs = ps.executeQuery();
                    i = 1;
                    while (rs.next()) {
                        tabModeRiwayatKehamilan2.addRow(new String[]{i + "", rs.getString("tgl_thn"),
                            rs.getString("tempat_persalinan"), rs.getString("usia_hamil"),
                            rs.getString("jenis_persalinan"), rs.getString("penolong"), rs.getString("penyulit"),
                            rs.getString("jk"), rs.getString("bbpb"), rs.getString("keadaan")});
                        i++;
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
                Valid.tabelKosong(tabModeDetailMasalah);
                ps = koneksi.prepareStatement(
                        "select master_masalah_keperawatan_kebidanan.kode_masalah,master_masalah_keperawatan_kebidanan.nama_masalah from master_masalah_keperawatan_kebidanan "
                        + "inner join penilaian_awal_keperawatan_kebidanan_ranap_masalah on penilaian_awal_keperawatan_kebidanan_ranap_masalah.kode_masalah=master_masalah_keperawatan_kebidanan.kode_masalah "
                        + "where penilaian_awal_keperawatan_kebidanan_ranap_masalah.no_rawat=? order by penilaian_awal_keperawatan_kebidanan_ranap_masalah.kode_masalah");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailMasalah.addRow(new Object[]{rs.getString(1), rs.getString(2)});
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
                        "select master_rencana_keperawatan_kebidanan.kode_rencana,master_rencana_keperawatan_kebidanan.rencana_kebidanan from master_rencana_keperawatan_kebidanan "
                        + "inner join penilaian_awal_keperawatan_kebidanan_ranap_rencana on penilaian_awal_keperawatan_kebidanan_ranap_rencana.kode_rencana=master_rencana_keperawatan_kebidanan.kode_rencana "
                        + "where penilaian_awal_keperawatan_kebidanan_ranap_rencana.no_rawat=? order by penilaian_awal_keperawatan_kebidanan_ranap_rencana.kode_rencana");
                try {
                    ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabModeDetailRencana.addRow(new Object[]{rs.getString(1), rs.getString(2)});
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

    private void isTotalResikoJatuh() {
        try {
            NilaiResikoTotal
                    .setText((Integer.parseInt(NilaiResiko1.getText()) + Integer.parseInt(NilaiResiko2.getText())
                            + Integer.parseInt(NilaiResiko3.getText()) + Integer.parseInt(NilaiResiko4.getText())
                            + Integer.parseInt(NilaiResiko5.getText()) + Integer.parseInt(NilaiResiko6.getText())) + "");
            if (Integer.parseInt(NilaiResikoTotal.getText()) < 25) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiResikoTotal.getText()) < 45) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Sedang (25-44), Tindakan : Intervensi pencegahan risiko jatuh standar");
            } else if (Integer.parseInt(NilaiResikoTotal.getText()) >= 45) {
                TingkatResiko.setText(
                        "Tingkat Resiko : Risiko Tinggi (> 45), Tindakan : Intervensi pencegahan risiko jatuh standar dan Intervensi risiko jatuh tinggi");
            }
        } catch (Exception e) {
            NilaiResikoTotal.setText("0");
            TingkatResiko.setText(
                    "Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
        }
    }

    private void isTotalGizi() {
        try {
            NilaiGiziTotal
                    .setText((Integer.parseInt(NilaiGizi1.getText()) + Integer.parseInt(NilaiGizi2.getText())) + "");
        } catch (Exception e) {
            NilaiGiziTotal.setText("0");
        }
    }

    private void emptTeksPersalinan() {
        TempatPersalinan.setText("");
        JenisPersalinan.setText("");
        Penolong.setText("");
        Penyulit.setText("");
        Keadaan.setText("");
        UsiaHamil.setText("");
        BBPB.setText("");
        TanggalPersalinan.setDate(new Date());
        JK.setSelectedIndex(0);
        TempatPersalinan.requestFocus();
    }

    private void tampilPersalinan() {
        Valid.tabelKosong(tabModeRiwayatKehamilan);
        try {
            ps = koneksi.prepareStatement(
                    "select * from riwayat_persalinan_pasien where riwayat_persalinan_pasien.no_rkm_medis=? order by riwayat_persalinan_pasien.tgl_thn");
            try {
                ps.setString(1, TNoRM.getText());
                rs = ps.executeQuery();
                i = 1;
                while (rs.next()) {
                    tabModeRiwayatKehamilan.addRow(new String[]{i + "", rs.getString("tgl_thn"),
                        rs.getString("tempat_persalinan"), rs.getString("usia_hamil"),
                        rs.getString("jenis_persalinan"), rs.getString("penolong"), rs.getString("penyulit"),
                        rs.getString("jk"), rs.getString("bbpb"), rs.getString("keadaan")});
                    i++;
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
        if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_kebidanan_ranap where no_rawat=? and tanggal=?", 2,
                new String[]{tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString()}) == true) {
            TNoRM1.setText("");
            TPasien1.setText("");
            Sequel.meghapus("penilaian_awal_keperawatan_kebidanan_ranap_masalah", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_kebidanan_ranap_rencana", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
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
        if (Sequel.mengedittf("penilaian_awal_keperawatan_kebidanan_ranap", "no_rawat=?",
                "no_rawat=?,tanggal=?,informasi=?,tiba_diruang_rawat=?,cara_masuk=?,keluhan=?,rpk=?,psk=?,rp=?,rps=?,robat=?,alergi=?,komplikasi_sebelumnya=?,keterangan_komplikasi_sebelumnya=?,riwayat_mens_umur=?,riwayat_mens_lamanya=?,riwayat_mens_banyaknya=?,riwayat_mens_siklus=?,riwayat_mens_ket_siklus=?,riwayat_mens_dirasakan=?,riwayat_perkawinan_status=?,riwayat_perkawinan_ket_status=?,riwayat_perkawinan_usia1=?,riwayat_perkawinan_ket_usia1=?,riwayat_perkawinan_usia2=?,riwayat_perkawinan_ket_usia2=?,riwayat_perkawinan_usia3=?,riwayat_perkawinan_ket_usia3=?,riwayat_persalinan_g=?,riwayat_persalinan_p=?,riwayat_persalinan_a=?,riwayat_persalinan_hidup=?,riwayat_hamil_hpht=?,riwayat_hamil_usiahamil=?,riwayat_hamil_tp=?,riwayat_hamil_imunisasi=?,riwayat_hamil_anc=?,riwayat_hamil_ancke=?,riwayat_hamil_ket_ancke=?,riwayat_hamil_keluhan_hamil_muda=?,riwayat_hamil_keluhan_hamil_tua=?,riwayat_kb=?,riwayat_kb_lamanya=?,riwayat_kb_komplikasi=?,riwayat_kb_ket_komplikasi=?,riwayat_kb_kapaberhenti=?,riwayat_kb_alasanberhenti=?,riwayat_genekologi=?,riwayat_kebiasaan_obat=?,riwayat_kebiasaan_ket_obat=?,riwayat_kebiasaan_merokok=?,riwayat_kebiasaan_ket_merokok=?,riwayat_kebiasaan_alkohol=?,riwayat_kebiasaan_ket_alkohol=?,riwayat_kebiasaan_narkoba=?,pemeriksaan_kebidanan_mental=?,pemeriksaan_kebidanan_keadaan_umum=?,pemeriksaan_kebidanan_gcs=?,pemeriksaan_kebidanan_td=?,pemeriksaan_kebidanan_nadi=?,pemeriksaan_kebidanan_rr=?,pemeriksaan_kebidanan_suhu=?,pemeriksaan_kebidanan_spo2=?,pemeriksaan_kebidanan_bb=?,pemeriksaan_kebidanan_tb=?,pemeriksaan_kebidanan_lila=?,pemeriksaan_kebidanan_tfu=?,pemeriksaan_kebidanan_tbj=?,pemeriksaan_kebidanan_letak=?,pemeriksaan_kebidanan_presentasi=?,pemeriksaan_kebidanan_penurunan=?,pemeriksaan_kebidanan_his=?,pemeriksaan_kebidanan_kekuatan=?,pemeriksaan_kebidanan_lamanya=?,pemeriksaan_kebidanan_djj=?,pemeriksaan_kebidanan_ket_djj=?,pemeriksaan_kebidanan_portio=?,pemeriksaan_kebidanan_pembukaan=?,pemeriksaan_kebidanan_ketuban=?,pemeriksaan_kebidanan_hodge=?,pemeriksaan_kebidanan_panggul=?,pemeriksaan_kebidanan_inspekulo=?,pemeriksaan_kebidanan_ket_inspekulo=?,pemeriksaan_kebidanan_lakmus=?,pemeriksaan_kebidanan_ket_lakmus=?,pemeriksaan_kebidanan_ctg=?,pemeriksaan_kebidanan_ket_ctg=?,pemeriksaan_umum_kepala=?,pemeriksaan_umum_muka=?,pemeriksaan_umum_mata=?,pemeriksaan_umum_hidung=?,pemeriksaan_umum_telinga=?,pemeriksaan_umum_mulut=?,pemeriksaan_umum_leher=?,pemeriksaan_umum_dada=?,pemeriksaan_umum_perut=?,pemeriksaan_umum_genitalia=?,pemeriksaan_umum_ekstrimitas=?,pengkajian_fungsi_kemampuan_aktifitas=?,pengkajian_fungsi_berjalan=?,pengkajian_fungsi_ket_berjalan=?,pengkajian_fungsi_aktivitas=?,pengkajian_fungsi_ambulasi=?,pengkajian_fungsi_ekstrimitas_atas=?,pengkajian_fungsi_ket_ekstrimitas_atas=?,pengkajian_fungsi_ekstrimitas_bawah=?,pengkajian_fungsi_ket_ekstrimitas_bawah=?,pengkajian_fungsi_kemampuan_menggenggam=?,pengkajian_fungsi_ket_kemampuan_menggenggam=?,pengkajian_fungsi_koordinasi=?,pengkajian_fungsi_ket_koordinasi=?,pengkajian_fungsi_gangguan_fungsi=?,riwayat_psiko_kondisipsiko=?,riwayat_psiko_adakah_prilaku=?,riwayat_psiko_ket_adakah_prilaku=?,riwayat_psiko_gangguan_jiwa=?,riwayat_psiko_hubungan_pasien=?,riwayat_psiko_tinggal_dengan=?,riwayat_psiko_ket_tinggal_dengan=?,riwayat_psiko_budaya=?,riwayat_psiko_ket_budaya=?,riwayat_psiko_pend_pj=?,riwayat_psiko_edukasi_pada=?,riwayat_psiko_ket_edukasi_pada=?,riwayat_psiko_wajib=?,riwayat_psiko_ket_halangan_lain=?,riwayat_psiko_toharah=?,riwayat_psiko_sholat=?,riwayat_psiko_motivasi_kesembuhan_ibadah=?,penilaian_nyeri=?,penilaian_nyeri_penyebab=?,penilaian_nyeri_ket_penyebab=?,penilaian_nyeri_kualitas=?,penilaian_nyeri_ket_kualitas=?,penilaian_nyeri_lokasi=?,penilaian_nyeri_menyebar=?,penilaian_nyeri_skala=?,penilaian_nyeri_waktu=?,penilaian_nyeri_hilang=?,penilaian_nyeri_ket_hilang=?,penilaian_nyeri_diberitahukan_dokter=?,penilaian_nyeri_jam_diberitahukan_dokter=?,penilaian_jatuh_skala1=?,penilaian_jatuh_nilai1=?,penilaian_jatuh_skala2=?,penilaian_jatuh_nilai2=?,penilaian_jatuh_skala3=?,penilaian_jatuh_nilai3=?,penilaian_jatuh_skala4=?,penilaian_jatuh_nilai4=?,penilaian_jatuh_skala5=?,penilaian_jatuh_nilai5=?,penilaian_jatuh_skala6=?,penilaian_jatuh_nilai6=?,penilaian_jatuh_totalnilai=?,skrining_gizi1=?,nilai_gizi1=?,skrining_gizi2=?,nilai_gizi2=?,nilai_total_gizi=?,skrining_gizi_diagnosa_khusus=?,skrining_gizi_ket_diagnosa_khusus=?,skrining_gizi_diketahui_dietisen=?,skrining_gizi_jam_diketahui_dietisen=?,kriteria1=?,kriteria2=?,kriteria3=?,kriteria4=?,pilihan1=?,"
                + "pilihan2=?,pilihan3=?,pilihan4=?,pilihan5=?,pilihan6=?,pilihan7=?,pilihan8=?,masalah=?,rencana=?,nip1=?,nip2=?,kd_dokter=?",
                182,
                new String[]{TNoRw.getText(),
                    Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                    + TglAsuhan.getSelectedItem().toString().substring(11, 19),
                    Anamnesis.getSelectedItem().toString(), TibadiRuang.getSelectedItem().toString(),
                    CaraMasuk.getSelectedItem().toString(), KeluhanUtama.getText(), RPK.getText(), PSK.getText(),
                    RBedah.getText(), RPS.getText(), RObat.getText(), Alergi.getText(),
                    KomplikasiKehamilan.getSelectedItem().toString(), KeteranganKomplikasiKehamilan.getText(),
                    UmurMinarche.getText(), LamaMenstruasi.getText(), BanyaknyaPembalut.getText(),
                    SiklusMenstruasi.getText(), KetSiklusMenstruasi.getSelectedItem().toString(),
                    DirasakanMenstruasi.getSelectedItem().toString(), StatusMenikah.getSelectedItem().toString(),
                    KaliMenikah.getText(), UsiaKawin1.getText(), StatusKawin1.getSelectedItem().toString(),
                    UsiaKawin2.getText(), StatusKawin2.getSelectedItem().toString(), UsiaKawin3.getText(),
                    StatusKawin3.getSelectedItem().toString(), G.getText(), P.getText(), A.getText(),
                    Hidup.getText(), Valid.SetTgl(HPHT.getSelectedItem() + ""), UsiaKehamilan.getText(),
                    Valid.SetTgl(TP.getSelectedItem() + ""), RiwayatImunisasi.getSelectedItem().toString(),
                    ANC.getText(), ANCKe.getText(), RiwayatANC.getSelectedItem().toString(),
                    KeluhanHamilMuda.getSelectedItem().toString(), KeluhanHamilTua.getSelectedItem().toString(),
                    RiwayatKB.getSelectedItem().toString(), LamanyaKB.getText(),
                    KomplikasiKB.getSelectedItem().toString(), KeteranganKomplikasiKB.getText(),
                    BerhentiKB.getText(), AlasanBerhentiKB.getText(),
                    RiwayatGenekologi.getSelectedItem().toString(), KebiasaanObat.getSelectedItem().toString(),
                    KebiasaanObatDiminum.getText(), KebiasaanMerokok.getSelectedItem().toString(),
                    KebiasaanJumlahRokok.getText(), KebiasaanAlkohol.getSelectedItem().toString(),
                    KebiasaanJumlahAlkohol.getText(), KebiasaanNarkoba.getSelectedItem().toString(),
                    KesadaranMental.getText(), KeadaanMentalUmum.getSelectedItem().toString(), GCS.getText(),
                    TD.getText(), Nadi.getText(), RR.getText(), Suhu.getText(), SpO2.getText(), BB.getText(),
                    TB.getText(), LILA.getText(), TFU.getText(), TBJ.getText(), Letak.getText(),
                    Presentasi.getText(), Penurunan.getText(), Kontraksi.getText(), Kekuatan.getText(),
                    LamanyaKontraksi.getText(), DJJ.getText(), KeteranganDJJ.getSelectedItem().toString(),
                    Portio.getText(), PembukaanServiks.getText(), Ketuban.getText(), Hodge.getText(),
                    PemeriksaanPanggul.getSelectedItem().toString(), Inspekulo.getSelectedItem().toString(),
                    KeteranganInspekulo.getText(), Lakmus.getSelectedItem().toString(), KeteranganLakmus.getText(),
                    CTG.getSelectedItem().toString(), KeteranganCTG.getText(),
                    PemeriksaanKepala.getSelectedItem().toString(), PemeriksaanMuka.getSelectedItem().toString(),
                    PemeriksaanMata.getSelectedItem().toString(), PemeriksaanHidung.getSelectedItem().toString(),
                    PemeriksaanTelinga.getSelectedItem().toString(), PemeriksaanMulut.getSelectedItem().toString(),
                    PemeriksaanLeher.getSelectedItem().toString(), PemeriksaanDada.getSelectedItem().toString(),
                    PemeriksaanPerut.getSelectedItem().toString(),
                    PemeriksaanGenitalia.getSelectedItem().toString(),
                    PemeriksaanEkstrimitas.getSelectedItem().toString(),
                    AktifitasSehari2.getSelectedItem().toString(), Berjalan.getSelectedItem().toString(),
                    KeteranganBerjalan.getText(), Aktifitas.getSelectedItem().toString(),
                    AlatAmbulasi.getSelectedItem().toString(), EkstrimitasAtas.getSelectedItem().toString(),
                    KeteranganEkstrimitasAtas.getText(), EkstrimitasBawah.getSelectedItem().toString(),
                    KeteranganEkstrimitasBawah.getText(), KemampuanMenggenggam.getSelectedItem().toString(),
                    KeteranganKemampuanMenggenggam.getText(), KemampuanKoordinasi.getSelectedItem().toString(),
                    KeteranganKemampuanKoordinasi.getText(), KesimpulanGangguanFungsi.getSelectedItem().toString(),
                    KondisiPsikologis.getSelectedItem().toString(), AdakahPerilaku.getSelectedItem().toString(),
                    KeteranganAdakahPerilaku.getText(), GangguanJiwa.getSelectedItem().toString(),
                    HubunganAnggotaKeluarga.getSelectedItem().toString(),
                    TinggalDengan.getSelectedItem().toString(), KeteranganTinggalDengan.getText(),
                    NilaiKepercayaan.getSelectedItem().toString(), KeteranganNilaiKepercayaan.getText(),
                    PendidikanPJ.getSelectedItem().toString(), EdukasiPsikolgis.getSelectedItem().toString(),
                    KeteranganEdukasiPsikologis.getText(), Wajib.getSelectedItem().toString(),
                    KetHalanganLain.getText(), Toharah.getSelectedItem().toString(),
                    Sholat.getSelectedItem().toString(), MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                    Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
                    KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(),
                    Lokasi.getText(), Menyebar.getSelectedItem().toString(),
                    SkalaNyeri.getSelectedItem().toString(), Durasi.getText(),
                    NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(),
                    PadaDokter.getSelectedItem().toString(), KetPadaDokter.getText(),
                    SkalaResiko1.getSelectedItem().toString(), NilaiResiko1.getText(),
                    SkalaResiko2.getSelectedItem().toString(), NilaiResiko2.getText(),
                    SkalaResiko3.getSelectedItem().toString(), NilaiResiko3.getText(),
                    SkalaResiko4.getSelectedItem().toString(), NilaiResiko4.getText(),
                    SkalaResiko5.getSelectedItem().toString(), NilaiResiko5.getText(),
                    SkalaResiko6.getSelectedItem().toString(), NilaiResiko6.getText(), NilaiResikoTotal.getText(),
                    SkalaGizi1.getSelectedItem().toString(), NilaiGizi1.getText(),
                    SkalaGizi2.getSelectedItem().toString(), NilaiGizi2.getText(), NilaiGiziTotal.getText(),
                    DiagnosaKhususGizi.getSelectedItem().toString(), KeteranganDiagnosaKhususGizi.getText(),
                    DiketahuiDietisen.getSelectedItem().toString(), KeteranganDiketahuiDietisen.getText(),
                    Kriteria1.getSelectedItem().toString(), Kriteria2.getSelectedItem().toString(),
                    Kriteria3.getSelectedItem().toString(), Kriteria4.getSelectedItem().toString(), pilih1, pilih2,
                    pilih3, pilih4, pilih5, pilih6, pilih7, pilih8, Masalah.getText(), Rencana.getText(),
                    KdPetugas.getText(), KdPetugas2.getText(), KdDPJP.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()}) == true) {
            tbObat.setValueAt(TNoRw.getText(), tbObat.getSelectedRow(), 0);
            tbObat.setValueAt(TNoRM.getText(), tbObat.getSelectedRow(), 1);
            tbObat.setValueAt(TPasien.getText(), tbObat.getSelectedRow(), 2);
            tbObat.setValueAt(TglLahir.getText(), tbObat.getSelectedRow(), 3);
            tbObat.setValueAt(Jk.getText().substring(0, 1), tbObat.getSelectedRow(), 4);
            tbObat.setValueAt(KdPetugas.getText(), tbObat.getSelectedRow(), 5);
            tbObat.setValueAt(NmPetugas.getText(), tbObat.getSelectedRow(), 6);
            tbObat.setValueAt(KdPetugas2.getText(), tbObat.getSelectedRow(), 7);
            tbObat.setValueAt(NmPetugas2.getText(), tbObat.getSelectedRow(), 8);
            tbObat.setValueAt(KdDPJP.getText(), tbObat.getSelectedRow(), 9);
            tbObat.setValueAt(NmDPJP.getText(), tbObat.getSelectedRow(), 10);
            tbObat.setValueAt(Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                    + TglAsuhan.getSelectedItem().toString().substring(11, 19), tbObat.getSelectedRow(), 11);
            tbObat.setValueAt(Anamnesis.getSelectedItem().toString(), tbObat.getSelectedRow(), 12);
            tbObat.setValueAt(TibadiRuang.getSelectedItem().toString(), tbObat.getSelectedRow(), 13);
            tbObat.setValueAt(CaraMasuk.getSelectedItem().toString(), tbObat.getSelectedRow(), 14);
            tbObat.setValueAt(KeluhanUtama.getText(), tbObat.getSelectedRow(), 15);
            tbObat.setValueAt(PSK.getText(), tbObat.getSelectedRow(), 16);
            tbObat.setValueAt(RPK.getText(), tbObat.getSelectedRow(), 17);
            tbObat.setValueAt(RBedah.getText(), tbObat.getSelectedRow(), 18);
            tbObat.setValueAt(RPS.getText(), tbObat.getSelectedRow(), 19);
            tbObat.setValueAt(RObat.getText(), tbObat.getSelectedRow(), 20);
            tbObat.setValueAt(Alergi.getText(), tbObat.getSelectedRow(), 21);
            tbObat.setValueAt(KomplikasiKehamilan.getSelectedItem().toString(), tbObat.getSelectedRow(), 22);
            tbObat.setValueAt(KeteranganKomplikasiKehamilan.getText(), tbObat.getSelectedRow(), 23);
            tbObat.setValueAt(UmurMinarche.getText(), tbObat.getSelectedRow(), 24);
            tbObat.setValueAt(LamaMenstruasi.getText(), tbObat.getSelectedRow(), 25);
            tbObat.setValueAt(BanyaknyaPembalut.getText(), tbObat.getSelectedRow(), 26);
            tbObat.setValueAt(SiklusMenstruasi.getText(), tbObat.getSelectedRow(), 27);
            tbObat.setValueAt(KetSiklusMenstruasi.getSelectedItem().toString(), tbObat.getSelectedRow(), 28);
            tbObat.setValueAt(DirasakanMenstruasi.getSelectedItem().toString(), tbObat.getSelectedRow(), 29);
            tbObat.setValueAt(StatusMenikah.getSelectedItem().toString(), tbObat.getSelectedRow(), 30);
            tbObat.setValueAt(KaliMenikah.getText(), tbObat.getSelectedRow(), 31);
            tbObat.setValueAt(UsiaKawin1.getText(), tbObat.getSelectedRow(), 32);
            tbObat.setValueAt(StatusKawin1.getSelectedItem().toString(), tbObat.getSelectedRow(), 33);
            tbObat.setValueAt(UsiaKawin2.getText(), tbObat.getSelectedRow(), 34);
            tbObat.setValueAt(StatusKawin2.getSelectedItem().toString(), tbObat.getSelectedRow(), 35);
            tbObat.setValueAt(UsiaKawin3.getText(), tbObat.getSelectedRow(), 36);
            tbObat.setValueAt(StatusKawin3.getSelectedItem().toString(), tbObat.getSelectedRow(), 37);
            tbObat.setValueAt(G.getText(), tbObat.getSelectedRow(), 38);
            tbObat.setValueAt(P.getText(), tbObat.getSelectedRow(), 39);
            tbObat.setValueAt(A.getText(), tbObat.getSelectedRow(), 40);
            tbObat.setValueAt(Hidup.getText(), tbObat.getSelectedRow(), 41);
            tbObat.setValueAt(Valid.SetTgl(HPHT.getSelectedItem() + ""), tbObat.getSelectedRow(), 42);
            tbObat.setValueAt(UsiaKehamilan.getText(), tbObat.getSelectedRow(), 43);
            tbObat.setValueAt(Valid.SetTgl(TP.getSelectedItem() + ""), tbObat.getSelectedRow(), 44);
            tbObat.setValueAt(RiwayatImunisasi.getSelectedItem().toString(), tbObat.getSelectedRow(), 45);
            tbObat.setValueAt(ANC.getText(), tbObat.getSelectedRow(), 46);
            tbObat.setValueAt(ANCKe.getText(), tbObat.getSelectedRow(), 47);
            tbObat.setValueAt(RiwayatANC.getSelectedItem().toString(), tbObat.getSelectedRow(), 48);
            tbObat.setValueAt(KeluhanHamilMuda.getSelectedItem().toString(), tbObat.getSelectedRow(), 49);
            tbObat.setValueAt(KeluhanHamilTua.getSelectedItem().toString(), tbObat.getSelectedRow(), 50);
            tbObat.setValueAt(RiwayatKB.getSelectedItem().toString(), tbObat.getSelectedRow(), 51);
            tbObat.setValueAt(LamanyaKB.getText(), tbObat.getSelectedRow(), 52);
            tbObat.setValueAt(KomplikasiKB.getSelectedItem().toString(), tbObat.getSelectedRow(), 53);
            tbObat.setValueAt(KeteranganKomplikasiKB.getText(), tbObat.getSelectedRow(), 54);
            tbObat.setValueAt(BerhentiKB.getText(), tbObat.getSelectedRow(), 55);
            tbObat.setValueAt(AlasanBerhentiKB.getText(), tbObat.getSelectedRow(), 56);
            tbObat.setValueAt(RiwayatGenekologi.getSelectedItem().toString(), tbObat.getSelectedRow(), 57);
            tbObat.setValueAt(KebiasaanObat.getSelectedItem().toString(), tbObat.getSelectedRow(), 58);
            tbObat.setValueAt(KebiasaanObatDiminum.getText(), tbObat.getSelectedRow(), 59);
            tbObat.setValueAt(KebiasaanMerokok.getSelectedItem().toString(), tbObat.getSelectedRow(), 60);
            tbObat.setValueAt(KebiasaanJumlahRokok.getText(), tbObat.getSelectedRow(), 61);
            tbObat.setValueAt(KebiasaanAlkohol.getSelectedItem().toString(), tbObat.getSelectedRow(), 62);
            tbObat.setValueAt(KebiasaanJumlahAlkohol.getText(), tbObat.getSelectedRow(), 63);
            tbObat.setValueAt(KebiasaanNarkoba.getSelectedItem().toString(), tbObat.getSelectedRow(), 64);
            tbObat.setValueAt(KesadaranMental.getText(), tbObat.getSelectedRow(), 65);
            tbObat.setValueAt(KeadaanMentalUmum.getSelectedItem().toString(), tbObat.getSelectedRow(), 66);
            tbObat.setValueAt(GCS.getText(), tbObat.getSelectedRow(), 67);
            tbObat.setValueAt(TD.getText(), tbObat.getSelectedRow(), 68);
            tbObat.setValueAt(Nadi.getText(), tbObat.getSelectedRow(), 69);
            tbObat.setValueAt(RR.getText(), tbObat.getSelectedRow(), 70);
            tbObat.setValueAt(Suhu.getText(), tbObat.getSelectedRow(), 71);
            tbObat.setValueAt(SpO2.getText(), tbObat.getSelectedRow(), 72);
            tbObat.setValueAt(BB.getText(), tbObat.getSelectedRow(), 73);
            tbObat.setValueAt(TB.getText(), tbObat.getSelectedRow(), 74);
            tbObat.setValueAt(LILA.getText(), tbObat.getSelectedRow(), 75);
            tbObat.setValueAt(TFU.getText(), tbObat.getSelectedRow(), 76);
            tbObat.setValueAt(TBJ.getText(), tbObat.getSelectedRow(), 77);
            tbObat.setValueAt(GD.getText(), tbObat.getSelectedRow(), 78);
            tbObat.setValueAt(Letak.getText(), tbObat.getSelectedRow(), 79);
            tbObat.setValueAt(Presentasi.getText(), tbObat.getSelectedRow(), 80);
            tbObat.setValueAt(Penurunan.getText(), tbObat.getSelectedRow(), 81);
            tbObat.setValueAt(Kontraksi.getText(), tbObat.getSelectedRow(), 82);
            tbObat.setValueAt(Kekuatan.getText(), tbObat.getSelectedRow(), 83);
            tbObat.setValueAt(LamanyaKontraksi.getText(), tbObat.getSelectedRow(), 84);
            tbObat.setValueAt(DJJ.getText(), tbObat.getSelectedRow(), 85);
            tbObat.setValueAt(KeteranganDJJ.getSelectedItem().toString(), tbObat.getSelectedRow(), 86);
            tbObat.setValueAt(Portio.getText(), tbObat.getSelectedRow(), 87);
            tbObat.setValueAt(PembukaanServiks.getText(), tbObat.getSelectedRow(), 88);
            tbObat.setValueAt(Ketuban.getText(), tbObat.getSelectedRow(), 89);
            tbObat.setValueAt(Hodge.getText(), tbObat.getSelectedRow(), 90);
            tbObat.setValueAt(PemeriksaanPanggul.getSelectedItem().toString(), tbObat.getSelectedRow(), 91);
            tbObat.setValueAt(Inspekulo.getSelectedItem().toString(), tbObat.getSelectedRow(), 92);
            tbObat.setValueAt(KeteranganInspekulo.getText(), tbObat.getSelectedRow(), 93);
            tbObat.setValueAt(Lakmus.getSelectedItem().toString(), tbObat.getSelectedRow(), 94);
            tbObat.setValueAt(KeteranganLakmus.getText(), tbObat.getSelectedRow(), 95);
            tbObat.setValueAt(CTG.getSelectedItem().toString(), tbObat.getSelectedRow(), 96);
            tbObat.setValueAt(KeteranganCTG.getText(), tbObat.getSelectedRow(), 97);
            tbObat.setValueAt(PemeriksaanKepala.getSelectedItem().toString(), tbObat.getSelectedRow(), 98);
            tbObat.setValueAt(PemeriksaanMuka.getSelectedItem().toString(), tbObat.getSelectedRow(), 99);
            tbObat.setValueAt(PemeriksaanMata.getSelectedItem().toString(), tbObat.getSelectedRow(), 100);
            tbObat.setValueAt(PemeriksaanHidung.getSelectedItem().toString(), tbObat.getSelectedRow(), 101);
            tbObat.setValueAt(PemeriksaanTelinga.getSelectedItem().toString(), tbObat.getSelectedRow(), 102);
            tbObat.setValueAt(PemeriksaanMulut.getSelectedItem().toString(), tbObat.getSelectedRow(), 103);
            tbObat.setValueAt(PemeriksaanLeher.getSelectedItem().toString(), tbObat.getSelectedRow(), 104);
            tbObat.setValueAt(PemeriksaanDada.getSelectedItem().toString(), tbObat.getSelectedRow(), 105);
            tbObat.setValueAt(PemeriksaanPerut.getSelectedItem().toString(), tbObat.getSelectedRow(), 106);
            tbObat.setValueAt(PemeriksaanGenitalia.getSelectedItem().toString(), tbObat.getSelectedRow(), 107);
            tbObat.setValueAt(PemeriksaanEkstrimitas.getSelectedItem().toString(), tbObat.getSelectedRow(), 108);
            tbObat.setValueAt(AktifitasSehari2.getSelectedItem().toString(), tbObat.getSelectedRow(), 109);
            tbObat.setValueAt(Berjalan.getSelectedItem().toString(), tbObat.getSelectedRow(), 110);
            tbObat.setValueAt(KeteranganBerjalan.getText(), tbObat.getSelectedRow(), 111);
            tbObat.setValueAt(Aktifitas.getSelectedItem().toString(), tbObat.getSelectedRow(), 112);
            tbObat.setValueAt(AlatAmbulasi.getSelectedItem().toString(), tbObat.getSelectedRow(), 113);
            tbObat.setValueAt(EkstrimitasAtas.getSelectedItem().toString(), tbObat.getSelectedRow(), 114);
            tbObat.setValueAt(KeteranganEkstrimitasAtas.getText(), tbObat.getSelectedRow(), 115);
            tbObat.setValueAt(EkstrimitasBawah.getSelectedItem().toString(), tbObat.getSelectedRow(), 116);
            tbObat.setValueAt(KeteranganEkstrimitasBawah.getText(), tbObat.getSelectedRow(), 117);
            tbObat.setValueAt(KemampuanMenggenggam.getSelectedItem().toString(), tbObat.getSelectedRow(), 118);
            tbObat.setValueAt(KeteranganKemampuanMenggenggam.getText(), tbObat.getSelectedRow(), 119);
            tbObat.setValueAt(KemampuanKoordinasi.getSelectedItem().toString(), tbObat.getSelectedRow(), 120);
            tbObat.setValueAt(KeteranganKemampuanKoordinasi.getText(), tbObat.getSelectedRow(), 121);
            tbObat.setValueAt(KesimpulanGangguanFungsi.getSelectedItem().toString(), tbObat.getSelectedRow(), 122);
            tbObat.setValueAt(KondisiPsikologis.getSelectedItem().toString(), tbObat.getSelectedRow(), 123);
            tbObat.setValueAt(AdakahPerilaku.getSelectedItem().toString(), tbObat.getSelectedRow(), 124);
            tbObat.setValueAt(KeteranganAdakahPerilaku.getText(), tbObat.getSelectedRow(), 125);
            tbObat.setValueAt(GangguanJiwa.getSelectedItem().toString(), tbObat.getSelectedRow(), 126);
            tbObat.setValueAt(HubunganAnggotaKeluarga.getSelectedItem().toString(), tbObat.getSelectedRow(), 127);
            tbObat.setValueAt(Agama.getText(), tbObat.getSelectedRow(), 128);
            tbObat.setValueAt(TinggalDengan.getSelectedItem().toString(), tbObat.getSelectedRow(), 129);
            tbObat.setValueAt(KeteranganTinggalDengan.getText(), tbObat.getSelectedRow(), 130);
            tbObat.setValueAt(PekerjaanPasien.getText(), tbObat.getSelectedRow(), 131);
            tbObat.setValueAt(CaraBayar.getText(), tbObat.getSelectedRow(), 132);
            tbObat.setValueAt(NilaiKepercayaan.getSelectedItem().toString(), tbObat.getSelectedRow(), 133);
            tbObat.setValueAt(KeteranganNilaiKepercayaan.getText(), tbObat.getSelectedRow(), 134);
            tbObat.setValueAt(Bahasa.getText(), tbObat.getSelectedRow(), 135);
            tbObat.setValueAt(PendidikanPasien.getText(), tbObat.getSelectedRow(), 136);
            tbObat.setValueAt(PendidikanPJ.getSelectedItem().toString(), tbObat.getSelectedRow(), 137);
            tbObat.setValueAt(EdukasiPsikolgis.getSelectedItem().toString(), tbObat.getSelectedRow(), 138);
            tbObat.setValueAt(KeteranganEdukasiPsikologis.getText(), tbObat.getSelectedRow(), 139);
            tbObat.setValueAt(Wajib.getSelectedItem().toString(), tbObat.getSelectedRow(), 140);
            tbObat.setValueAt(KetHalanganLain.getText(), tbObat.getSelectedRow(), 141);
            tbObat.setValueAt(Toharah.getSelectedItem().toString(), tbObat.getSelectedRow(), 142);
            tbObat.setValueAt(Sholat.getSelectedItem().toString(), tbObat.getSelectedRow(), 143);
            tbObat.setValueAt(MotivasiKesembuhanIbadah.getSelectedItem().toString(), tbObat.getSelectedRow(), 144);
            tbObat.setValueAt(Nyeri.getSelectedItem().toString(), tbObat.getSelectedRow(), 145);
            tbObat.setValueAt(Provokes.getSelectedItem().toString(), tbObat.getSelectedRow(), 146);
            tbObat.setValueAt(KetProvokes.getText(), tbObat.getSelectedRow(), 147);
            tbObat.setValueAt(Quality.getSelectedItem().toString(), tbObat.getSelectedRow(), 148);
            tbObat.setValueAt(KetQuality.getText(), tbObat.getSelectedRow(), 149);
            tbObat.setValueAt(Lokasi.getText(), tbObat.getSelectedRow(), 150);
            tbObat.setValueAt(Menyebar.getSelectedItem().toString(), tbObat.getSelectedRow(), 151);
            tbObat.setValueAt(SkalaNyeri.getSelectedItem().toString(), tbObat.getSelectedRow(), 152);
            tbObat.setValueAt(Durasi.getText(), tbObat.getSelectedRow(), 153);
            tbObat.setValueAt(NyeriHilang.getSelectedItem().toString(), tbObat.getSelectedRow(), 154);
            tbObat.setValueAt(KetNyeri.getText(), tbObat.getSelectedRow(), 155);
            tbObat.setValueAt(PadaDokter.getSelectedItem().toString(), tbObat.getSelectedRow(), 156);
            tbObat.setValueAt(KetPadaDokter.getText(), tbObat.getSelectedRow(), 157);
            tbObat.setValueAt(SkalaResiko1.getSelectedItem().toString(), tbObat.getSelectedRow(), 158);
            tbObat.setValueAt(NilaiResiko1.getText(), tbObat.getSelectedRow(), 159);
            tbObat.setValueAt(SkalaResiko2.getSelectedItem().toString(), tbObat.getSelectedRow(), 160);
            tbObat.setValueAt(NilaiResiko2.getText(), tbObat.getSelectedRow(), 161);
            tbObat.setValueAt(SkalaResiko3.getSelectedItem().toString(), tbObat.getSelectedRow(), 162);
            tbObat.setValueAt(NilaiResiko3.getText(), tbObat.getSelectedRow(), 163);
            tbObat.setValueAt(SkalaResiko4.getSelectedItem().toString(), tbObat.getSelectedRow(), 164);
            tbObat.setValueAt(NilaiResiko4.getText(), tbObat.getSelectedRow(), 165);
            tbObat.setValueAt(SkalaResiko5.getSelectedItem().toString(), tbObat.getSelectedRow(), 166);
            tbObat.setValueAt(NilaiResiko5.getText(), tbObat.getSelectedRow(), 167);
            tbObat.setValueAt(SkalaResiko6.getSelectedItem().toString(), tbObat.getSelectedRow(), 168);
            tbObat.setValueAt(NilaiResiko6.getText(), tbObat.getSelectedRow(), 169);
            tbObat.setValueAt(NilaiResikoTotal.getText(), tbObat.getSelectedRow(), 170);
            tbObat.setValueAt(SkalaGizi1.getSelectedItem().toString(), tbObat.getSelectedRow(), 171);
            tbObat.setValueAt(NilaiGizi1.getText(), tbObat.getSelectedRow(), 172);
            tbObat.setValueAt(SkalaGizi2.getSelectedItem().toString(), tbObat.getSelectedRow(), 173);
            tbObat.setValueAt(NilaiGizi2.getText(), tbObat.getSelectedRow(), 174);
            tbObat.setValueAt(NilaiGiziTotal.getText(), tbObat.getSelectedRow(), 175);
            tbObat.setValueAt(DiagnosaKhususGizi.getSelectedItem().toString(), tbObat.getSelectedRow(), 176);
            tbObat.setValueAt(KeteranganDiagnosaKhususGizi.getText(), tbObat.getSelectedRow(), 177);
            tbObat.setValueAt(DiketahuiDietisen.getSelectedItem().toString(), tbObat.getSelectedRow(), 178);
            tbObat.setValueAt(KeteranganDiketahuiDietisen.getText(), tbObat.getSelectedRow(), 179);
            tbObat.setValueAt(Kriteria1.getSelectedItem(), tbObat.getSelectedRow(), 180);
            tbObat.setValueAt(Kriteria2.getSelectedItem(), tbObat.getSelectedRow(), 181);
            tbObat.setValueAt(Kriteria3.getSelectedItem(), tbObat.getSelectedRow(), 182);
            tbObat.setValueAt(Kriteria4.getSelectedItem(), tbObat.getSelectedRow(), 183);
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 184).toString().equals("Perawatan diri (Mandi, BAB, BAK)")) {
                pilihan1.setSelected(true);
                tbObat.setValueAt(pilihan1, tbObat.getSelectedRow(), 184);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 185).toString().equals("Pemantauan pemberian obat")) {
                pilihan2.setSelected(true);
                tbObat.setValueAt(pilihan2, tbObat.getSelectedRow(), 185);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 186).toString().equals("Pemantauan diet")) {
                pilihan3.setSelected(true);
                tbObat.setValueAt(pilihan3, tbObat.getSelectedRow(), 186);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 187)
                    .toString()
                    .equals("Bantuan medis / perawatan di rumah (Homecare)")) {
                pilihan4.setSelected(true);
                tbObat.setValueAt(pilihan4, tbObat.getSelectedRow(), 187);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 188).toString().equals("Perawatan luka")) {
                pilihan5.setSelected(true);
                tbObat.setValueAt(pilihan5, tbObat.getSelectedRow(), 188);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 189).toString().equals("Latihan fisik lanjutan")) {
                pilihan6.setSelected(true);
                tbObat.setValueAt(pilihan6, tbObat.getSelectedRow(), 189);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 190)
                    .toString()
                    .equals("Pendampingan tenaga khusus di rumah")) {
                pilihan7.setSelected(true);
                tbObat.setValueAt(pilihan7, tbObat.getSelectedRow(), 190);
            }
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 191)
                    .toString()
                    .equals("Bantuan untuk melakukan aktifitas fisik (kursi roda, alat bantu jalan)")) {
                pilihan8.setSelected(true);
                tbObat.setValueAt(pilihan8, tbObat.getSelectedRow(), 191);
            }
            tbObat.setValueAt(Masalah.getText(), tbObat.getSelectedRow(), 192);
            tbObat.setValueAt(Rencana.getText(), tbObat.getSelectedRow(), 193);
            Valid.tabelKosong(tabModeRiwayatKehamilan2);
            for (i = 0; i < tbRiwayatKehamilan.getRowCount(); i++) {
                tabModeRiwayatKehamilan2.addRow(new String[]{tbRiwayatKehamilan.getValueAt(i, 0).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 1).toString(), tbRiwayatKehamilan.getValueAt(i, 2).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 3).toString(), tbRiwayatKehamilan.getValueAt(i, 4).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 5).toString(), tbRiwayatKehamilan.getValueAt(i, 6).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 7).toString(), tbRiwayatKehamilan.getValueAt(i, 8).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 9).toString()});
            }
            Sequel.meghapus("penilaian_awal_keperawatan_kebidanan_ranap_masalah", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbMasalahKeperawatanKebidanan.getRowCount(); i++) {
                if (tbMasalahKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_kebidanan_ranap_masalah", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbMasalahKeperawatanKebidanan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            Sequel.meghapus("penilaian_awal_keperawatan_kebidanan_ranap_rencana", "no_rawat", "tanggal",
                    tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
                if (tbRencanaKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_kebidanan_ranap_rencana", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbRencanaKeperawatanKebidanan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            getMasalah();
            tampil();
            TNoRM1.setText(TNoRM.getText());
            TPasien1.setText(TPasien.getText());
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
        if (Sequel.menyimpantf("penilaian_awal_keperawatan_kebidanan_ranap",
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
                "No.Rawat", 181,
                new String[]{TNoRw.getText(),
                    Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                    + TglAsuhan.getSelectedItem().toString().substring(11, 19),
                    Anamnesis.getSelectedItem().toString(), TibadiRuang.getSelectedItem().toString(),
                    CaraMasuk.getSelectedItem().toString(), KeluhanUtama.getText(), RPK.getText(), PSK.getText(),
                    RBedah.getText(), RPS.getText(), RObat.getText(), Alergi.getText(),
                    KomplikasiKehamilan.getSelectedItem().toString(), KeteranganKomplikasiKehamilan.getText(),
                    UmurMinarche.getText(), LamaMenstruasi.getText(), BanyaknyaPembalut.getText(),
                    SiklusMenstruasi.getText(), KetSiklusMenstruasi.getSelectedItem().toString(),
                    DirasakanMenstruasi.getSelectedItem().toString(), StatusMenikah.getSelectedItem().toString(),
                    KaliMenikah.getText(), UsiaKawin1.getText(), StatusKawin1.getSelectedItem().toString(),
                    UsiaKawin2.getText(), StatusKawin2.getSelectedItem().toString(), UsiaKawin3.getText(),
                    StatusKawin3.getSelectedItem().toString(), G.getText(), P.getText(), A.getText(),
                    Hidup.getText(), Valid.SetTgl(HPHT.getSelectedItem() + ""), UsiaKehamilan.getText(),
                    Valid.SetTgl(TP.getSelectedItem() + ""), RiwayatImunisasi.getSelectedItem().toString(),
                    ANC.getText(), ANCKe.getText(), RiwayatANC.getSelectedItem().toString(),
                    KeluhanHamilMuda.getSelectedItem().toString(), KeluhanHamilTua.getSelectedItem().toString(),
                    RiwayatKB.getSelectedItem().toString(), LamanyaKB.getText(),
                    KomplikasiKB.getSelectedItem().toString(), KeteranganKomplikasiKB.getText(),
                    BerhentiKB.getText(), AlasanBerhentiKB.getText(),
                    RiwayatGenekologi.getSelectedItem().toString(), KebiasaanObat.getSelectedItem().toString(),
                    KebiasaanObatDiminum.getText(), KebiasaanMerokok.getSelectedItem().toString(),
                    KebiasaanJumlahRokok.getText(), KebiasaanAlkohol.getSelectedItem().toString(),
                    KebiasaanJumlahAlkohol.getText(), KebiasaanNarkoba.getSelectedItem().toString(),
                    KesadaranMental.getText(), KeadaanMentalUmum.getSelectedItem().toString(), GCS.getText(),
                    TD.getText(), Nadi.getText(), RR.getText(), Suhu.getText(), SpO2.getText(), BB.getText(),
                    TB.getText(), LILA.getText(), TFU.getText(), TBJ.getText(), Letak.getText(),
                    Presentasi.getText(), Penurunan.getText(), Kontraksi.getText(), Kekuatan.getText(),
                    LamanyaKontraksi.getText(), DJJ.getText(), KeteranganDJJ.getSelectedItem().toString(),
                    Portio.getText(), PembukaanServiks.getText(), Ketuban.getText(), Hodge.getText(),
                    PemeriksaanPanggul.getSelectedItem().toString(), Inspekulo.getSelectedItem().toString(),
                    KeteranganInspekulo.getText(), Lakmus.getSelectedItem().toString(), KeteranganLakmus.getText(),
                    CTG.getSelectedItem().toString(), KeteranganCTG.getText(),
                    PemeriksaanKepala.getSelectedItem().toString(), PemeriksaanMuka.getSelectedItem().toString(),
                    PemeriksaanMata.getSelectedItem().toString(), PemeriksaanHidung.getSelectedItem().toString(),
                    PemeriksaanTelinga.getSelectedItem().toString(), PemeriksaanMulut.getSelectedItem().toString(),
                    PemeriksaanLeher.getSelectedItem().toString(), PemeriksaanDada.getSelectedItem().toString(),
                    PemeriksaanPerut.getSelectedItem().toString(),
                    PemeriksaanGenitalia.getSelectedItem().toString(),
                    PemeriksaanEkstrimitas.getSelectedItem().toString(),
                    AktifitasSehari2.getSelectedItem().toString(), Berjalan.getSelectedItem().toString(),
                    KeteranganBerjalan.getText(), Aktifitas.getSelectedItem().toString(),
                    AlatAmbulasi.getSelectedItem().toString(), EkstrimitasAtas.getSelectedItem().toString(),
                    KeteranganEkstrimitasAtas.getText(), EkstrimitasBawah.getSelectedItem().toString(),
                    KeteranganEkstrimitasBawah.getText(), KemampuanMenggenggam.getSelectedItem().toString(),
                    KeteranganKemampuanMenggenggam.getText(), KemampuanKoordinasi.getSelectedItem().toString(),
                    KeteranganKemampuanKoordinasi.getText(), KesimpulanGangguanFungsi.getSelectedItem().toString(),
                    KondisiPsikologis.getSelectedItem().toString(), AdakahPerilaku.getSelectedItem().toString(),
                    KeteranganAdakahPerilaku.getText(), GangguanJiwa.getSelectedItem().toString(),
                    HubunganAnggotaKeluarga.getSelectedItem().toString(),
                    TinggalDengan.getSelectedItem().toString(), KeteranganTinggalDengan.getText(),
                    NilaiKepercayaan.getSelectedItem().toString(), KeteranganNilaiKepercayaan.getText(),
                    PendidikanPJ.getSelectedItem().toString(), EdukasiPsikolgis.getSelectedItem().toString(),
                    KeteranganEdukasiPsikologis.getText(), Wajib.getSelectedItem().toString(),
                    KetHalanganLain.getText(), Toharah.getSelectedItem().toString(),
                    Sholat.getSelectedItem().toString(), MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                    Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
                    KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(),
                    Lokasi.getText(), Menyebar.getSelectedItem().toString(),
                    SkalaNyeri.getSelectedItem().toString(), Durasi.getText(),
                    NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(),
                    PadaDokter.getSelectedItem().toString(), KetPadaDokter.getText(),
                    SkalaResiko1.getSelectedItem().toString(), NilaiResiko1.getText(),
                    SkalaResiko2.getSelectedItem().toString(), NilaiResiko2.getText(),
                    SkalaResiko3.getSelectedItem().toString(), NilaiResiko3.getText(),
                    SkalaResiko4.getSelectedItem().toString(), NilaiResiko4.getText(),
                    SkalaResiko5.getSelectedItem().toString(), NilaiResiko5.getText(),
                    SkalaResiko6.getSelectedItem().toString(), NilaiResiko6.getText(), NilaiResikoTotal.getText(),
                    SkalaGizi1.getSelectedItem().toString(), NilaiGizi1.getText(),
                    SkalaGizi2.getSelectedItem().toString(), NilaiGizi2.getText(), NilaiGiziTotal.getText(),
                    DiagnosaKhususGizi.getSelectedItem().toString(), KeteranganDiagnosaKhususGizi.getText(),
                    DiketahuiDietisen.getSelectedItem().toString(), KeteranganDiketahuiDietisen.getText(),
                    Kriteria1.getSelectedItem().toString(), Kriteria2.getSelectedItem().toString(),
                    Kriteria3.getSelectedItem().toString(), Kriteria4.getSelectedItem().toString(), pilih1, pilih2,
                    pilih3, pilih4, pilih5, pilih6, pilih7, pilih8, Masalah.getText(), Rencana.getText(),
                    KdPetugas.getText(), KdPetugas2.getText(), KdDPJP.getText()}) == true) {
            tabMode.addRow(new String[]{TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TglLahir.getText(),
                Jk.getText().substring(0, 1), KdPetugas.getText(), NmPetugas.getText(), KdPetugas2.getText(),
                NmPetugas2.getText(), KdDPJP.getText(), NmDPJP.getText(),
                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                + TglAsuhan.getSelectedItem().toString().substring(11, 19),
                Anamnesis.getSelectedItem().toString(), TibadiRuang.getSelectedItem().toString(),
                CaraMasuk.getSelectedItem().toString(), KeluhanUtama.getText(), PSK.getText(), RPK.getText(),
                RBedah.getText(), RPS.getText(), RObat.getText(), Alergi.getText(),
                KomplikasiKehamilan.getSelectedItem().toString(), KeteranganKomplikasiKehamilan.getText(),
                UmurMinarche.getText(), LamaMenstruasi.getText(), BanyaknyaPembalut.getText(),
                SiklusMenstruasi.getText(), KetSiklusMenstruasi.getSelectedItem().toString(),
                DirasakanMenstruasi.getSelectedItem().toString(), StatusMenikah.getSelectedItem().toString(),
                KaliMenikah.getText(), UsiaKawin1.getText(), StatusKawin1.getSelectedItem().toString(),
                UsiaKawin2.getText(), StatusKawin2.getSelectedItem().toString(), UsiaKawin3.getText(),
                StatusKawin3.getSelectedItem().toString(), G.getText(), P.getText(), A.getText(), Hidup.getText(),
                Valid.SetTgl(HPHT.getSelectedItem() + ""), UsiaKehamilan.getText(),
                Valid.SetTgl(TP.getSelectedItem() + ""), RiwayatImunisasi.getSelectedItem().toString(),
                ANC.getText(), ANCKe.getText(), RiwayatANC.getSelectedItem().toString(),
                KeluhanHamilMuda.getSelectedItem().toString(), KeluhanHamilTua.getSelectedItem().toString(),
                RiwayatKB.getSelectedItem().toString(), LamanyaKB.getText(),
                KomplikasiKB.getSelectedItem().toString(), KeteranganKomplikasiKB.getText(), BerhentiKB.getText(),
                AlasanBerhentiKB.getText(), RiwayatGenekologi.getSelectedItem().toString(),
                KebiasaanObat.getSelectedItem().toString(), KebiasaanObatDiminum.getText(),
                KebiasaanMerokok.getSelectedItem().toString(), KebiasaanJumlahRokok.getText(),
                KebiasaanAlkohol.getSelectedItem().toString(), KebiasaanJumlahAlkohol.getText(),
                KebiasaanNarkoba.getSelectedItem().toString(), KesadaranMental.getText(),
                KeadaanMentalUmum.getSelectedItem().toString(), GCS.getText(), TD.getText(), Nadi.getText(),
                RR.getText(), Suhu.getText(), SpO2.getText(), BB.getText(), TB.getText(), LILA.getText(),
                TFU.getText(), TBJ.getText(), GD.getText(), Letak.getText(), Presentasi.getText(),
                Penurunan.getText(), Kontraksi.getText(), Kekuatan.getText(), LamanyaKontraksi.getText(),
                DJJ.getText(), KeteranganDJJ.getSelectedItem().toString(), Portio.getText(),
                PembukaanServiks.getText(), Ketuban.getText(), Hodge.getText(),
                PemeriksaanPanggul.getSelectedItem().toString(), Inspekulo.getSelectedItem().toString(),
                KeteranganInspekulo.getText(), Lakmus.getSelectedItem().toString(), KeteranganLakmus.getText(),
                CTG.getSelectedItem().toString(), KeteranganCTG.getText(),
                PemeriksaanKepala.getSelectedItem().toString(), PemeriksaanMuka.getSelectedItem().toString(),
                PemeriksaanMata.getSelectedItem().toString(), PemeriksaanHidung.getSelectedItem().toString(),
                PemeriksaanTelinga.getSelectedItem().toString(), PemeriksaanMulut.getSelectedItem().toString(),
                PemeriksaanLeher.getSelectedItem().toString(), PemeriksaanDada.getSelectedItem().toString(),
                PemeriksaanPerut.getSelectedItem().toString(), PemeriksaanGenitalia.getSelectedItem().toString(),
                PemeriksaanEkstrimitas.getSelectedItem().toString(), AktifitasSehari2.getSelectedItem().toString(),
                Berjalan.getSelectedItem().toString(), KeteranganBerjalan.getText(),
                Aktifitas.getSelectedItem().toString(), AlatAmbulasi.getSelectedItem().toString(),
                EkstrimitasAtas.getSelectedItem().toString(), KeteranganEkstrimitasAtas.getText(),
                EkstrimitasBawah.getSelectedItem().toString(), KeteranganEkstrimitasBawah.getText(),
                KemampuanMenggenggam.getSelectedItem().toString(), KeteranganKemampuanMenggenggam.getText(),
                KemampuanKoordinasi.getSelectedItem().toString(), KeteranganKemampuanKoordinasi.getText(),
                KesimpulanGangguanFungsi.getSelectedItem().toString(),
                KondisiPsikologis.getSelectedItem().toString(), AdakahPerilaku.getSelectedItem().toString(),
                KeteranganAdakahPerilaku.getText(), GangguanJiwa.getSelectedItem().toString(),
                HubunganAnggotaKeluarga.getSelectedItem().toString(), Agama.getText(),
                TinggalDengan.getSelectedItem().toString(), KeteranganTinggalDengan.getText(),
                PekerjaanPasien.getText(), CaraBayar.getText(), NilaiKepercayaan.getSelectedItem().toString(),
                KeteranganNilaiKepercayaan.getText(), Bahasa.getText(), PendidikanPasien.getText(),
                PendidikanPJ.getSelectedItem().toString(), EdukasiPsikolgis.getSelectedItem().toString(),
                KeteranganEdukasiPsikologis.getText(), Wajib.getSelectedItem().toString(),
                KetHalanganLain.getText(), Toharah.getSelectedItem().toString(),
                Sholat.getSelectedItem().toString(), MotivasiKesembuhanIbadah.getSelectedItem().toString(),
                Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(), KetProvokes.getText(),
                Quality.getSelectedItem().toString(), KetQuality.getText(), Lokasi.getText(),
                Menyebar.getSelectedItem().toString(), SkalaNyeri.getSelectedItem().toString(), Durasi.getText(),
                NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(),
                PadaDokter.getSelectedItem().toString(), KetPadaDokter.getText(),
                SkalaResiko1.getSelectedItem().toString(), NilaiResiko1.getText(),
                SkalaResiko2.getSelectedItem().toString(), NilaiResiko2.getText(),
                SkalaResiko3.getSelectedItem().toString(), NilaiResiko3.getText(),
                SkalaResiko4.getSelectedItem().toString(), NilaiResiko4.getText(),
                SkalaResiko5.getSelectedItem().toString(), NilaiResiko5.getText(),
                SkalaResiko6.getSelectedItem().toString(), NilaiResiko6.getText(), NilaiResikoTotal.getText(),
                SkalaGizi1.getSelectedItem().toString(), NilaiGizi1.getText(),
                SkalaGizi2.getSelectedItem().toString(), NilaiGizi2.getText(), NilaiGiziTotal.getText(),
                DiagnosaKhususGizi.getSelectedItem().toString(), KeteranganDiagnosaKhususGizi.getText(),
                DiketahuiDietisen.getSelectedItem().toString(), KeteranganDiketahuiDietisen.getText(),
                Kriteria1.getSelectedItem().toString(), Kriteria2.getSelectedItem().toString(),
                Kriteria3.getSelectedItem().toString(), Kriteria4.getSelectedItem().toString(), pilih1, pilih2,
                pilih3, pilih4, pilih5, pilih6, pilih7, pilih8, Masalah.getText(), Rencana.getText()});
            LCount.setText("" + tabMode.getRowCount());
            Valid.tabelKosong(tabModeRiwayatKehamilan2);
            for (i = 0; i < tbRiwayatKehamilan.getRowCount(); i++) {
                tabModeRiwayatKehamilan2.addRow(new String[]{tbRiwayatKehamilan.getValueAt(i, 0).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 1).toString(), tbRiwayatKehamilan.getValueAt(i, 2).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 3).toString(), tbRiwayatKehamilan.getValueAt(i, 4).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 5).toString(), tbRiwayatKehamilan.getValueAt(i, 6).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 7).toString(), tbRiwayatKehamilan.getValueAt(i, 8).toString(),
                    tbRiwayatKehamilan.getValueAt(i, 9).toString()});
            }
            for (i = 0; i < tbMasalahKeperawatanKebidanan.getRowCount(); i++) {
                if (tbMasalahKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_kebidanan_ranap_masalah", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbMasalahKeperawatanKebidanan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            for (i = 0; i < tbRencanaKeperawatanKebidanan.getRowCount(); i++) {
                if (tbRencanaKeperawatanKebidanan.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_kebidanan_ranap_rencana", "?,?,?", 3,
                            new String[]{TNoRw.getText(), tbRencanaKeperawatanKebidanan.getValueAt(i, 1).toString(),
                                Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " "
                                + TglAsuhan.getSelectedItem().toString().substring(11, 19)});
                }
            }
            TNoRM1.setText(TNoRM.getText());
            TPasien1.setText(TPasien.getText());
            emptTeks();
        }
    }

    private static final Logger LOG = Logger.getLogger(RMPenilaianAwalKeperawatanKebidananRanap.class.getName());

}
