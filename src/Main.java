public class Main {
    //flag
    public static boolean isRunning = true; //in methode auswahl_menü die widerrum in menü stattfindet wird isRunning auf false gesetzt. wie hierher in main bringen ?

    public static void main(String[] args) {
        System.out.println("Willkommen in der Tagebuchapp");
        System.out.println("");

        while (isRunning) {
            menu.Menü(); //wird rekursiv aufgerufen ? *(siehe menu.java)
            isRunning = false;
        }
    }
}

//16.53/-55 uhr ist guter stamp.
//GESCHAFFT: isRunning geht jetzt wieder.
//GESCHAFFT: dateiinhalt löschen geht zwar aber es werden die gewollten einträge nicht übernommen in die "neue" datei. -> geht 10.7 plötzlich nach mehrfachen testen . Gut.
//GESCHAFFT/NICHT SO LÖSBAR: try catch abfangen bei menü wenn statt 1-4 , ein string eingegeben wird. Dazu glaub nötig filehandle.... usw als try with ressources zu machen. Sehr aufwendig neu zu schreiben.
//GESCHAFFT/NICHT SO LÖSBAR: switch case kommt nicht klar mit:  switch (Files.exists(pfad)){ case 0: try..(weiterer code...) fehlermeldung: Selector type of 'boolean' is not supported at language level -> bei nächstem projekt jetzt direkt umsetzen switch case statt if.
//OFFEN: rekursiver menü aufruf -> bei nächstem projekt direkt nicht rekursive methoden beachten.