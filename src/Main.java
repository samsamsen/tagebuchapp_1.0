public class Main {
    //flag
    public static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("Willkommen in der Tagebuchapp");
        System.out.println("");
        while (isRunning) {
            menu.Menü(); //wird rekursiv aufgerufen ? *(siehe menu.java)
        }
    }
}

//methoden in extra klassen ausgelagert. PROBLEM: isRunning in Main geht nicht mehr auf False wenn
//menü punkt 4 gedrückt wurde.
//PROBLEM: dateiinhalt löschen geht zwar aber es werden die gewollten einträge nicht übernommen in die "neue" datei.
//GESCHAFFT: methoden aus main in extra klassen für mehr übersicht.
//todo: obige nun aus jetzt neu gemachten klassen ergebende probleme lösen: u.a wie kann man isRunning = false aus menu in das hauptprogramm bekommen.
//bisherige versuche schwierig da return außerhalb der if kaskade nur akzeptiert wurde, also nicht nach eingabe = 4 somit kommt.
//todo: rekursiven menü aufruf also menu.Menü() jetzt, rausnehmen ohne das programm probleme macht.
//todo: fehlermeldungen mit ausgeben lassen statt nur "fehler aufgetreten"
//switch case, noch mehr in methoden je einzelne aufgabe sowie variablen auf englisch -> bei nächstem mal umsetzen von anfang an.
//ebenso großkleinschreibung nochmal ansehen.