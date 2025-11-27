import java.util.Scanner;

public class RestoranSistemi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- LEZZET RESTORAN SİPARİŞ SİSTEMİ ---");
        System.out.println("\nANA YEMEKLER:");
        System.out.println("1: Izgara Tavuk (85 TL)\n2: Adana Kebap (120 TL)\n3: Levrek (110 TL)\n4: Mantı (65 TL)\n0: İstemiyorum");
        System.out.print("Seçiminiz: ");
        int anaYemekSecim = scanner.nextInt();
        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);


        System.out.println("\nBAŞLANGIÇLAR:");
        System.out.println("1: Çorba (25 TL)\n2: Humus (45 TL)\n3: Sigara Böreği (55 TL)\n0: İstemiyorum");
        System.out.print("Seçiminiz: ");
        int baslangicSecim = scanner.nextInt();
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);


        System.out.println("\nİÇECEKLER:");
        System.out.println("1: Kola (15 TL)\n2: Ayran (12 TL)\n3: Taze Meyve Suyu (35 TL)\n4: Limonata (25 TL)\n0: İstemiyorum");
        System.out.print("Seçiminiz: ");
        int icecekSecim = scanner.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);


        System.out.println("\nTATLILAR:");
        System.out.println("1: Künefe (65 TL)\n2: Baklava (55 TL)\n3: Sütlaç (35 TL)\n0: İstemiyorum");
        System.out.print("Seçiminiz: ");
        int tatliSecim = scanner.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);



        System.out.print("\nSaat kaç? (0-23 arası tam sayı): ");
        int saat = scanner.nextInt();

        System.out.print("Hafta içi mi? (Evet: true, Hayır: false): ");
        boolean haftaIci = scanner.nextBoolean(); // "true" veya "false" yazılmalı

        System.out.print("Öğrenci misiniz? (Evet: true, Hayır: false): ");
        boolean ogrenciMi = scanner.nextBoolean();


        if (isHappyHour(saat) && icecekFiyat > 0) {
            System.out.println(">> Happy Hour Saati! İçeceğe %20 indirim uygulandı.");
            icecekFiyat = icecekFiyat * 0.80;
        }

        double hamTutar = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;


        boolean anaVar = anaYemekFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;
        boolean isCombo = isComboOrder(anaVar, icecekVar, tatliVar);


        boolean ogrenciIndirimiGecerli = (ogrenciMi && haftaIci);


        double indirimTutari = calculateDiscount(hamTutar, isCombo, ogrenciIndirimiGecerli, saat);

        double indirimliTutar = hamTutar - indirimTutari;


        double bahsis = calculateServiceTip(indirimliTutar);

        double sonOdenecek = indirimliTutar + bahsis;


        System.out.println("\n=================================");
        System.out.println("          HESAP ÖZETİ");
        System.out.println("=================================");
        System.out.println("Ana Yemek      : " + anaYemekFiyat + " TL");
        System.out.println("Başlangıç      : " + baslangicFiyat + " TL");
        System.out.println("İçecek         : " + icecekFiyat + " TL " + (isHappyHour(saat) ? "(Happy Hour)" : ""));
        System.out.println("Tatlı          : " + tatliFiyat + " TL");
        System.out.println("---------------------------------");
        System.out.println("Ara Toplam     : " + hamTutar + " TL");
        System.out.println("İndirimler     : -" + indirimTutari + " TL");
        if(isCombo) System.out.println("  * Combo Menü İndirimi");
        if(hamTutar > 200) System.out.println("  * 200 TL Üzeri İndirimi");
        if(ogrenciIndirimiGecerli) System.out.println("  * Öğrenci İndirimi");

        System.out.println("---------------------------------");
        System.out.println("Net Tutar      : " + indirimliTutar + " TL");
        System.out.println("Garson Bahşiş  : " + bahsis + " TL");
        System.out.println("=================================");
        System.out.println("TOPLAM ÖDENECEK: " + sonOdenecek + " TL");
        System.out.println("=================================");

        scanner.close();
    }


    public static double getMainDishPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 85; break;  // Izgara Tavuk
            case 2: fiyat = 120; break; // Adana
            case 3: fiyat = 110; break; // Levrek
            case 4: fiyat = 65; break;  // Mantı
            default: fiyat = 0; break;
        }
        return fiyat;
    }


    public static double getAppetizerPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 25; break; // Çorba
            case 2: fiyat = 45; break; // Humus
            case 3: fiyat = 55; break; // Sigara Böreği
            default: fiyat = 0; break;
        }
        return fiyat;
    }


    public static double getDrinkPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 15; break; // Kola
            case 2: fiyat = 12; break; // Ayran
            case 3: fiyat = 35; break; // Taze Meyve Suyu
            case 4: fiyat = 25; break; // Limonata
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static double getDessertPrice(int secim) {
        double fiyat = 0;
        switch (secim) {
            case 1: fiyat = 65; break; // Künefe
            case 2: fiyat = 55; break; // Baklava
            case 3: fiyat = 35; break; // Sütlaç
            default: fiyat = 0; break;
        }
        return fiyat;
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {

        return (anaVar && icecekVar && tatliVar);
    }


    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 17);
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double toplamIndirim = 0;


        if (combo) {
            toplamIndirim += (tutar * 0.15);
        }

        if (tutar > 200) {
            toplamIndirim += (tutar * 0.10);
        }

        if (ogrenci) {
            toplamIndirim += (tutar * 0.10);
        }
        
        return toplamIndirim;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
