public class TempatKegiatan {
    private String nama;
    private int kapasitas;
    private int tiketTerjual;
    private double hargaTiket;

    public TempatKegiatan(String nama, int kapasitas, double hargaTiket) {
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.hargaTiket = hargaTiket;
        this.tiketTerjual = 0;
    }

    public String getNama() {
        return nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getSisaTiket() {
        return kapasitas - tiketTerjual;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public boolean jualTiket(int jumlah) {
        if (getSisaTiket() >= jumlah) {
            tiketTerjual += jumlah;
            return true;
        } else {
            return false; // Tiket tidak cukup
        }
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Sisa Tiket: " + getSisaTiket() + ", Harga: " + hargaTiket;
    }
}