import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class menu {
    //pfade vorarbeit
    public static String current = System.getProperty("user.dir");
    public static Path pfad = Paths.get(current, "Einträge.txt");
    public static Path pfadzwei = Paths.get(current, "Einträgezwei.txt");
    //Scanner als static - für besseren zugriff
    public static Scanner sc = new Scanner(System.in);
    //flag
    public static boolean isRunning = true;

    public static void Menü() {
        System.out.println("Menü:");
        System.out.println("");
        System.out.println("1. Einträge anzeigen.");
        System.out.println("2. Neuen Eintrag machen.");
        System.out.println("3. Einträge löschen.");
        System.out.println("4. Programm beenden.");
        System.out.println("Nummer eingeben für das jeweilige Untermenü...");
        auswahl_menü(pfad);
    }

    public static void auswahl_menü(Path pfad) {
        int eingabe = sc.nextInt();
        sc.nextLine(); // Zeilenumbruch nach nextInt() konsumieren.
        if (eingabe == 1) {
            System.out.println("Bisherige Einträge:");
            System.out.println("");
            filehandling.dateilesen(pfad, pfadzwei);
            Menü(); //rekursiver aufruf hier und in gesamter methode mehrfach *
        } else if (eingabe == 2) {
            System.out.println("Neuen Eintrag jetzt machen...");
            String eingabezwei = sc.nextLine();
            System.out.println(eingabezwei);
            filehandling.dateischreiben(pfad, pfadzwei, eingabezwei);
            Menü();
        } else if (eingabe == 3) {
            System.out.println("Gib das Datum der zu löschenden Einträge an im Format JJJJ-MM-TT...");
            String eingabezwei = sc.nextLine();
            if (Files.exists(pfad)) {
                filehandling.einskopierenundlöschen(pfad, pfadzwei, eingabezwei); //kopieren löschen methode part eins
            } else if (Files.exists(pfadzwei)) {
                filehandling.zweikopierenundlöschen(pfad, pfadzwei, eingabezwei); //kopieren löschen methode part zwei
            } else {
                System.out.println("Eintrag nicht möglich zu löschen! (Keine der beiden Dateien aktuell vorhanden zum kopieren und löschen.)");
            }
            Menü();
        } else if (eingabe == 4) {
            System.out.println("Das Programm wurde beendet.");
        }
    }
}
