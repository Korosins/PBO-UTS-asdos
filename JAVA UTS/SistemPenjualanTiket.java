import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemPenjualanTiket {
    // List untuk menyimpan data Tempat/Kegiatan dan Pembeli
    private List<TempatKegiatan> daftarKegiatan;
    private List<Pembeli> daftarPembeli;
    private Scanner scanner;

    public SistemPenjualanTiket() {
        daftarKegiatan = new ArrayList<>();
        daftarPembeli = new ArrayList<>();
        scanner = new Scanner(System.in);
        // Menambahkan data awal untuk contoh
        daftarKegiatan.add(new TempatKegiatan("Konser Musik Rock", 100, 150000.0));
        daftarKegiatan.add(new TempatKegiatan("Workshop Java", 50, 50000.0));
    }

    // --- FITUR: Menambahkan data tempat/kegiatan ---
    public void tambahKegiatan() {
        System.out.println("\n--- Tambah Kegiatan Baru ---");
        System.out.print("Masukkan Nama Kegiatan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Kapasitas Tiket: ");
        int kapasitas = scanner.nextInt();
        System.out.print("Masukkan Harga Tiket: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); // membersihkan buffer

        TempatKegiatan baru = new TempatKegiatan(nama, kapasitas, harga);
        daftarKegiatan.add(baru);
        System.out.println("✅ Kegiatan '" + nama + "' berhasil ditambahkan.");
    }

    // --- FITUR: Menambahkan data pembeli tiket ---
    public void tambahPembeli() {
        System.out.println("\n--- Tambah Data Pembeli ---");
        System.out.print("Masukkan Nama Pembeli: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Email Pembeli: ");
        String email = scanner.nextLine();

        Pembeli baru = new Pembeli(nama, email);
        daftarPembeli.add(baru);
        System.out.println("✅ Pembeli '" + nama + "' berhasil ditambahkan.");
    }

    // --- FITUR: Melakukan pembelian tiket ---
    public void beliTiket() {
        System.out.println("\n--- Pembelian Tiket ---");
        
        if (daftarKegiatan.isEmpty()) {
            System.out.println("❌ Belum ada kegiatan yang tersedia.");
            return;
        }

        // Tampilkan daftar kegiatan
        System.out.println("Daftar Kegiatan Tersedia:");
        for (int i = 0; i < daftarKegiatan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarKegiatan.get(i).toString());
        }

        System.out.print("Pilih nomor kegiatan (1-" + daftarKegiatan.size() + "): ");
        int pilihanKegiatan = scanner.nextInt();
        scanner.nextLine();

        if (pilihanKegiatan < 1 || pilihanKegiatan > daftarKegiatan.size()) {
            System.out.println("❌ Pilihan kegiatan tidak valid.");
            return;
        }

        TempatKegiatan kegiatanTerpilih = daftarKegiatan.get(pilihanKegiatan - 1);

        System.out.print("Masukkan Nama Pembeli: ");
        String namaPembeli = scanner.nextLine();
        // Cari pembeli, jika tidak ada, asumsikan pembeli baru
        Pembeli pembeli = daftarPembeli.stream()
                                      .filter(p -> p.getNama().equalsIgnoreCase(namaPembeli))
                                      .findFirst()
                                      .orElse(null);

        if (pembeli == null) {
            System.out.println("⚠ Pembeli belum terdaftar. Silakan daftarkan dulu (atau masukkan email untuk pendaftaran otomatis):");
            System.out.print("Masukkan Email Pembeli: ");
            String email = scanner.nextLine();
            pembeli = new Pembeli(namaPembeli, email);
            daftarPembeli.add(pembeli);
            System.out.println("✅ Pembeli baru otomatis didaftarkan.");
        }

        System.out.print("Masukkan jumlah tiket yang dibeli: ");
        int jumlahTiket = scanner.nextInt();
        scanner.nextLine();

        if (kegiatanTerpilih.jualTiket(jumlahTiket)) {
            double totalHarga = jumlahTiket * kegiatanTerpilih.getHargaTiket();
            System.out.println("\n--- NOTA PEMBELIAN ---");
            System.out.println("Kegiatan: " + kegiatanTerpilih.getNama());
            System.out.println("Pembeli: " + pembeli.getNama());
            System.out.println("Jumlah Tiket: " + jumlahTiket);
            System.out.printf("Total Harga: Rp %,.2f%n", totalHarga);
            System.out.println("✅ Pembelian berhasil!");
        } else {
            System.out.println("❌ Gagal melakukan pembelian. Sisa tiket hanya " + kegiatanTerpilih.getSisaTiket() + ".");
        }
    }
    
    // --- FITUR TAMBAHAN: Tampilkan Status ---
    public void tampilkanStatus() {
        System.out.println("\n--- STATUS SISTEM ---");
        System.out.println("\n[ Daftar Kegiatan ]");
        if (daftarKegiatan.isEmpty()) {
            System.out.println("Tidak ada kegiatan.");
        } else {
            for (TempatKegiatan k : daftarKegiatan) {
                System.out.println(" > " + k.toString());
            }
        }
        
        System.out.println("\n[ Daftar Pembeli ]");
        if (daftarPembeli.isEmpty()) {
            System.out.println("Tidak ada pembeli terdaftar.");
        } else {
            for (Pembeli p : daftarPembeli) {
                System.out.println(" > " + p.toString());
            }
        }
    }

    // --- MENU UTAMA ---
    public void tampilkanMenu() {
        int pilihan = 0;
        do {
            System.out.println("\n==================================");
            System.out.println("    SISTEM PENJUALAN TIKET JAVA");
            System.out.println("==================================");
            System.out.println("1. Tambah Data Kegiatan");
            System.out.println("2. Tambah Data Pembeli");
            System.out.println("3. Lakukan Pembelian Tiket");
            System.out.println("4. Tampilkan Status Sistem");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-4): ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // membersihkan buffer
                
                switch (pilihan) {
                    case 1:
                        tambahKegiatan();
                        break;
                    case 2:
                        tambahPembeli();
                        break;
                    case 3:
                        beliTiket();
                        break;
                    case 4:
                        tampilkanStatus();
                        break;
                    case 0:
                        System.out.println("Terima kasih. Program selesai.");
                        break;
                    default:
                        System.out.println("❌ Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Masukkan harus berupa angka. Silakan coba lagi.");
                scanner.nextLine(); // membersihkan input yang salah
                pilihan = -1; // untuk mengulang loop
            }

        } while (pilihan != 0);
    }

    public static void main(String[] args) {
        SistemPenjualanTiket sistem = new SistemPenjualanTiket();
        sistem.tampilkanMenu();
    }
}