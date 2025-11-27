import java.util.Scanner;

public class SinemaBiletiProjesi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SİNEMA BİLETİ HESAPLAMA SİSTEMİ ---");

        
        System.out.println("\nLütfen günü seçiniz (1-7):");
        System.out.println("1:Pzt, 2:Sal, 3:Çar, 4:Per, 5:Cum, 6:Cmt, 7:Paz");
        int gun = scanner.nextInt();

        System.out.print("Saati giriniz (0-23 arası tam sayı, örn: 14): ");
        int saat = scanner.nextInt();

        System.out.print("Yasinizi giriniz: ");
        int yas = scanner.nextInt();

        System.out.println("Meslek grubunu seciniz:");
        System.out.println("1: Ögrenci, 2: Ögretmen, 3: Diger");
        int meslek = scanner.nextInt();

        System.out.println("Film türünü seciniz:");
        System.out.println("1: 2D, 2: 3D (+25 TL), 3: IMAX (+35 TL), 4: 4DX (+50 TL)");
        int filmTuru = scanner.nextInt();

        


        double temelFiyat = calculateBasePrice(gun, saat);


        double indirimOrani = calculateDiscount(yas, meslek, gun);


        double ekstraUcret = getFormatExtra(filmTuru);


        double toplamFiyat = calculateFinalPrice(temelFiyat, indirimOrani, ekstraUcret);


        generateTicketInfo(gun, saat, filmTuru, temelFiyat, indirimOrani, ekstraUcret, toplamFiyat);

        scanner.close();
    }




    public static boolean isWeekend(int gun) {

        return (gun == 6 || gun == 7);
    }


    public static boolean isMatinee(int saat) {
        return (saat < 12);
    }


    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {

            return matine ? 55.0 : 85.0;
        } else {

            return matine ? 45.0 : 65.0;
        }
    }


    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirim = 0.0;



        if (yas < 12) {
            return 0.25;
        }
        if (yas >= 65) {
            return 0.30;
        }


        switch (meslek) {
            case 1:

                if (gun >= 1 && gun <= 4) {
                    indirim = 0.20;
                } else {
                    indirim = 0.15;
                }
                break;
            case 2:

                if (gun == 3) {
                    indirim = 0.35;
                } else {
                    indirim = 0.0;
                }
                break;
            case 3:
                indirim = 0.0;
                break;
            default:
                System.out.println("Hatalı meslek kodu!");
        }

        return indirim;
    }


    public static double getFormatExtra(int filmTuru) {
        double ekstra = 0;
        switch (filmTuru) {
            case 1:
                ekstra = 0;
                break;
            case 2:
                ekstra = 25;
                break;
            case 3:
                ekstra = 35;
                break;
            case 4:
                ekstra = 50;
                break;
            default:
                System.out.println("Hatalı film türü!");
        }
        return ekstra;
    }


    public static double calculateFinalPrice(double basePrice, double discountRate, double extraFee) {

        double indirimTutari = basePrice * discountRate;
        double indirimliFiyat = basePrice - indirimTutari;
        return indirimliFiyat + extraFee;
    }


    public static void generateTicketInfo(int gun, int saat, int filmTuru, double temel, double oran, double ekstra, double toplam) {
        System.out.println("\n=================================");
        System.out.println("      BİLET DETAYLARI");
        System.out.println("=================================");


        String gunIsmi = "";
        switch(gun) {
            case 1: gunIsmi="Pazartesi"; break;
            case 2: gunIsmi="Salı"; break;
            case 3: gunIsmi="Çarşamba"; break;
            case 4: gunIsmi="Perşembe"; break;
            case 5: gunIsmi="Cuma"; break;
            case 6: gunIsmi="Cumartesi"; break;
            case 7: gunIsmi="Pazar"; break;
        }

        String formatIsmi = "";
        switch(filmTuru) {
            case 1: formatIsmi="2D"; break;
            case 2: formatIsmi="3D"; break;
            case 3: formatIsmi="IMAX"; break;
            case 4: formatIsmi="4DX"; break;
        }

        System.out.println("Gün/Saat      : " + gunIsmi + " / " + saat + ":00");
        System.out.println("Film Formatı  : " + formatIsmi);
        System.out.println("Temel Fiyat   : " + temel + " TL");
        System.out.println("İndirim Oranı : %" + (int)(oran * 100));
        System.out.println("Ekstra Ücret  : +" + ekstra + " TL");
        System.out.println("---------------------------------");
        System.out.println("TOPLAM TUTAR  : " + toplam + " TL");
        System.out.println("=================================");
    }
}
