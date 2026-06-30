import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class filehandling {
    //Datum
    public static LocalTime uhrzeit = LocalTime.now();
    public static DateTimeFormatter uhrzeitf = DateTimeFormatter.ofPattern("HH:mm");
    public static LocalDateTime datumzeit = LocalDateTime.now();
    public static LocalDate datumf = datumzeit.toLocalDate();

    public static void dateilesen(Path pfad, Path pfadzwei) {
        if (Files.exists(pfad)) {
            try (var br = Files.newBufferedReader(pfad)) {
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException i) {
                System.out.println("Fehler ist aufgetreten");
            }
        } else if (Files.exists(pfadzwei)) {
            try (var br = Files.newBufferedReader(pfadzwei)) {
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException i) {
                System.out.println("Fehler ist aufgetreten");
            }
        }
    }

    public static void dateischreiben(Path pfad, Path pfadzwei, String eingabezwei) {
        if (Files.exists(pfad)) {
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
        } else if (Files.exists(pfadzwei)) {
            try (var bw = Files.newBufferedWriter(pfadzwei, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                bw.write(datumf.toString());
                bw.write(" ");
                bw.write(uhrzeit.format(uhrzeitf) + " ");
                bw.write(eingabezwei + "\n\n");
                System.out.println("\n" + "Eintrag gespeichert.");
                System.out.println("");
            } catch (IOException e) {
                System.out.println("Fehler ist aufgetreten.");
            }
        } else { //wenn pfad eins und pfad zwei noch nicht existieren (aller erster programmstart z.b) pfad eins verwenden zum schreiben.
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
        }
    }

    public static void altedateilöschen(Path dateizurlöschung) { //Jedes mal nach methode einskopieren bzw zweikopieren.
        try {
            Files.deleteIfExists(dateizurlöschung);
        } catch (IOException e) {
            System.out.println("Beim löschen ist ein fehler aufgetreten.");
        }
    }

    public static void einskopierenundlöschen(Path pfad, Path pfadzwei, String nutzereingabe_zurlöschung) {
        try (var br = newBufferedReader(pfad)) {
            String line;
            while ((line = br.readLine()) != null) {
                try (var bw = newBufferedWriter(pfadzwei, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    if (line.contains(nutzereingabe_zurlöschung)) {
                        continue;
                    }
                    bw.write(line + "\n");
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

    public static void zweikopierenundlöschen(Path pfad, Path pfadzwei, String nutzereingabe_zurlöschung) {
        try (var br = newBufferedReader(pfadzwei)) {
            String line;
            while ((line = br.readLine()) != null) {
                try (var bw = newBufferedWriter(pfad, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    if (line.contains(nutzereingabe_zurlöschung)) {
                        continue;
                    }
                    bw.write(line + "\n");
                } catch (IOException e) {
                    System.out.println("Ein buffered writer fehler bei kopieren von dateieins ist aufgetreten.");
                }
            }
        } catch (IOException e) {
            System.out.println("Beim kopieren im br part ist ein Fehler aufgetreten.");
        }
        altedateilöschen(pfadzwei);
        System.out.println("Der Eintrag wurde gelöscht.");
    }
}

