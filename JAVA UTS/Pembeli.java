public class Pembeli {
    private String nama;
    private String email;

    public Pembeli(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Email: " + email;
    }
}