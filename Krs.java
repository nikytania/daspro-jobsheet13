import java.util.Scanner;

public class Krs {
    static String[][] dataKRS = new String[100][5]; 
    static int jumlahData = 0; 
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int menu = sc.nextInt();
            sc.nextLine(); 

            switch (menu) {
                case 1:
                    tambahDataKRS();
                    break;
                case 2:
                    tampilkanDaftarKRS();
                    break;
                case 3:
                    analisisDataKRS();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    running = false;
                    break;
                default:
                    System.out.println("Menu tidak valid! Silakan pilih lagi.");
            }
        }
    }

    public static void tambahDataKRS() {
        System.out.println("\n--- Tambah Data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = sc.nextLine();
        System.out.print("NIM: ");
        String nim = sc.nextLine();

        int totalSKS = 0;

        while (true) {
            System.out.print("Kode Mata Kuliah: ");
            String kodeMK = sc.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMK = sc.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = sc.nextInt();
            sc.nextLine(); 
            
            if (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS tidak valid! SKS harus antara 1 dan 3.");
                continue;
            }

        
            if (totalSKS + sks > 24) {
                System.out.println("Total SKS melebihi 24! Data tidak bisa ditambahkan.");
                continue;
            }

            dataKRS[jumlahData][0] = nama;
            dataKRS[jumlahData][1] = nim;
            dataKRS[jumlahData][2] = kodeMK;
            dataKRS[jumlahData][3] = namaMK;
            dataKRS[jumlahData][4] = String.valueOf(sks);
            jumlahData++;

            totalSKS += sks;
            System.out.println("Data mata kuliah berhasil ditambahkan.");

            System.out.print("Tambah mata kuliah lain? (y/t): ");
            String pilihan = sc.nextLine();
            if (pilihan.equalsIgnoreCase("t")) break;
        }

        System.out.println("Total SKS yang diambil: " + totalSKS + "\n");
    }

    public static void tampilkanDaftarKRS() {
        System.out.println("\n--- Tampilkan Daftar KRS Mahasiswa ---");
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = sc.nextLine();

        int totalSKS = 0;
        System.out.println("Daftar KRS:");
        System.out.printf("%-10s %-15s %-10s %-30s %-5s%n", "NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS"); 
      
        for (int i = 0; i < jumlahData; i++) { 
            if (dataKRS[i][1].equals(nim)) { 
                System.out.printf("%-10s %-15s %-10s %-30s %-5s%n",dataKRS[i][1], dataKRS[i][0], dataKRS[i][2], dataKRS[i][3], dataKRS[i][4]);
                totalSKS += Integer.parseInt(dataKRS[i][4]);
            } 
        }

        if (totalSKS == 0) {
            System.out.println("Tidak ada data KRS untuk NIM tersebut.");
        } else {
            System.out.println("Total SKS: " + totalSKS + "\n");
        }
    }

    public static void analisisDataKRS() {
        System.out.println("\n--- Analisis Data KRS ---");  
        int jumlahMahasiswaKurang20 = 0; 
        boolean[] mahasiswaDihitung = new boolean[jumlahData]; 
    
        for (int i = 0; i < jumlahData; i++) {
            String nim = dataKRS[i][1]; 
    
            
            if (!mahasiswaDihitung[i]) {
                int totalSKS = 0;
    
                for (int j = 0; j < jumlahData; j++) { 

                    if (dataKRS[j][1].equals(nim)) { 
                        totalSKS += Integer.parseInt(dataKRS[j][4]);
                    }
                }

                if (totalSKS < 20) {
                    jumlahMahasiswaKurang20++;
                }
    
                for (int k = 0; k < jumlahData; k++) {
                    if (dataKRS[k][1].equals(nim)) { 
                        mahasiswaDihitung[k] = true;
                    }
                }
            }
        }
    
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jumlahMahasiswaKurang20 +"\n");
    }
}