import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.*;
import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class Main {
    //VORARBEIT für buff.w/buff.r uvm
    public static String current = System.getProperty("user.dir");
    public static Path pfad = Paths.get(current, "Einträge.txt");
    public static Path pfadzwei = Paths.get(current, "Einträgezwei.txt");
    //Datum
    public static LocalTime uhrzeit = LocalTime.now();
    public static DateTimeFormatter uhrzeitf = DateTimeFormatter.ofPattern("HH:mm");
    public static LocalDateTime datumzeit = LocalDateTime.now();
    public static LocalDate datumf = datumzeit.toLocalDate();
    //Scanner als static - für besseren zugriff
    public static Scanner sc = new Scanner(System.in);
    //VORARBEIT ENDE

    public static void main(String[] args) {
        System.out.println("Willkommen in der Tagebuchapp");
        System.out.println("");

        //hauptprogrammstart
        while (true){
            Menü();
            break;
        }
    }
    public static void Menü (){
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
            dateilesen(pfad,pfadzwei);
            Menü();
        }
        else if (eingabe == 2) {
            System.out.println("Neuen Eintrag jetzt machen...");
            String eingabezwei = sc.nextLine();
            System.out.println(eingabezwei);
            dateischreiben(pfad,pfadzwei,eingabezwei);
            Menü();
        }
        else if (eingabe == 3){
            System.out.println("Gib das Datum der zu löschenden Einträge an im Format JJJJ-MM-TT...");
            String eingabezwei = sc.nextLine();
            if (Files.exists(pfad)){
                einskopierenundlöschen(pfad,pfadzwei,eingabezwei); //kopieren löschen methode part eins
            }else if (Files.exists(pfadzwei)) {
                zweikopierenundlöschen(pfad, pfadzwei, eingabezwei); //kopieren löschen methode part zwei
            }else{
                System.out.println("Eintrag nicht möglich zu löschen! (Keine der beiden Dateien aktuell vorhanden zum kopieren und löschen.)");
            }Menü();
        }
        else if (eingabe == 4){
            System.out.println("Das Programm wurde beendet.");
        }
    }
    public static void dateischreiben (Path pfad,Path pfadzwei,String eingabezwei){
        if(Files.exists(pfad)){
            try (var bw = Files.newBufferedWriter(pfad, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                bw.write(datumf.toString());
                bw.write(" ");
                bw.write(uhrzeit.format(uhrzeitf) + " ");
                bw.write(eingabezwei + "\n\n");
                System.out.println("\n"+"Eintrag gespeichert.");
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Fehler ist aufgetreten.");
            }
        }
        else if (Files.exists(pfadzwei)){
            try (var bw = Files.newBufferedWriter(pfadzwei, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                bw.write(datumf.toString());
                bw.write(" ");
                bw.write(uhrzeit.format(uhrzeitf) + " ");
                bw.write(eingabezwei + "\n\n");
                System.out.println("\n"+"Eintrag gespeichert.");
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Fehler ist aufgetreten.");
            }
        }
        else { //wenn pfad eins und pfad zwei noch nicht existieren (aller erster programmstart z.b) pfad eins verwenden zum schreiben.
            try (var bw = Files.newBufferedWriter(pfad, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                bw.write(datumf.toString());
                bw.write(" ");
                bw.write(uhrzeit.format(uhrzeitf) + " ");
                bw.write(eingabezwei + "\n\n");
                System.out.println("\n" + "Eintrag gespeichert.");
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Fehler ist aufgetreten.");
            }
        }}
    public static void dateilesen (Path pfad,Path pfadzwei) {
        if (Files.exists(pfad)){
            try (var br = Files.newBufferedReader(pfad)){
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            }
            catch (IOException i){
                System.out.println("Fehler ist aufgetreten");
            }
        }
        else if(Files.exists(pfadzwei)) {
            try (var br = Files.newBufferedReader(pfadzwei)) {
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException i) {
                System.out.println("Fehler ist aufgetreten");
            }
        }}
    public static void altedateilöschen (Path dateizurlöschung){ //Jedes mal nach methode einskopieren bzw zweikopieren.
        try {
            Files.deleteIfExists(dateizurlöschung);
        } catch (IOException e) {
            System.out.println("Beim löschen ist ein fehler aufgetreten.");
        }
    }
    public static void einskopierenundlöschen(Path pfad, Path pfadzwei,String nutzereingabe_zurlöschung){
        try (var br = newBufferedReader(pfad)) {
            String line;
            while ((line = br.readLine()) != null) {
                try (var bw = newBufferedWriter(pfadzwei, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    if (line.contains(nutzereingabe_zurlöschung)) {
                        continue;
                    }  bw.write(line+"\n");
                } catch (IOException e) {
                    System.out.println("Ein buffered writer fehler bei kopieren von dateieins ist aufgetreten.");
                }
            }
        } catch (IOException e) {
            System.out.println("Beim kopieren im br part ist ein Fehler aufgetreten.");
        }
        altedateilöschen(pfad);
        System.out.println("Der Eintrag wurde gelöscht.");
    }
    public static void zweikopierenundlöschen (Path pfad, Path pfadzwei,String nutzereingabe_zurlöschung) {
        try(var br = newBufferedReader(pfadzwei)){
            String line;
            while ((line = br.readLine())!=null){
                try(var bw = newBufferedWriter(pfad,StandardOpenOption.CREATE,StandardOpenOption.APPEND)){
                    if (line.contains(nutzereingabe_zurlöschung)) {
                        continue;
                    }  bw.write(line+"\n");}
                catch (IOException e){
                    System.out.println("Ein buffered writer fehler bei kopieren von dateieins ist aufgetreten.");
                } }}
        catch(IOException e){
            System.out.println("Beim kopieren im br part ist ein Fehler aufgetreten.");}
        altedateilöschen(pfadzwei);
        System.out.println("Der Eintrag wurde gelöscht.");}}