//321/2015004 Aivatidis Prodromos 2
import java.time.LocalDateTime; //Προσθήκη βιβλιοθήκης για αποθήκευση ημέρας και ώρας

public class task {
    private final StringBuilder description=new StringBuilder();    //Χρήση Stringbuilder για αποδοτικότερα Strings
    private final LocalDateTime term;   //Ημερομηνία προσθεσμίας
    private final Main.category cat;    //Καηγορία τύπου enum αρχικοποιημένη στη Main συνάρτηση
    private final char priority;        //Χαρακτήρας προτεραιότητας
    private boolean done;               //Χαρακτηρισμός εκκρεμότητας ως ολοκληρωμένη
    public StringBuilder getDescription(){ return description; }    //Απαιτούμενες set και get συναρτήσεις
    public LocalDateTime getTerm(){ return term; }
    public Main.category getCat(){ return cat; }
    public char getPriority(){ return priority; }
    public boolean getDone(){return done; }
    public void setDone(){ done=true; }
    public task(String descr, LocalDateTime term, Main.category cat, char priority){    //Constructor 0: πλήρης
        this.description.append(descr);
        this.term = term;
        this.cat = cat;
        this.priority = Character.toUpperCase(priority);    //η προτεραιότητα σώζεται ως Κεφαλαία
        done=(term.isBefore(LocalDateTime.now()));          //και η μεταβλητή done γίνεται true αν βάλουμε ημερομηνία πριν τη τωρινή
    }
    public task(String descr, LocalDateTime term, Main.category cat){   //Constructor 1, χωρίς προτεραιότητα
        this.description.append(descr);
        this.term = term;
        this.cat = cat;
        this.priority = 'Z';    //η προτεραιότητα αρχικοποιείται με Z, το τελευταίο γράμμα της αλφαβήτου
        done=(term.isBefore(LocalDateTime.now()));
    }
    public task(String descr, LocalDateTime term, char priority){   //Constructor 2, χωρίς κατηγορία
        this.description.append(descr);
        this.term = term;
        this.cat = Main.category.Allo;  //Η κατηγορία αρχικοποιείται με Allo
        this.priority = Character.toUpperCase(priority);
        done=(term.isBefore(LocalDateTime.now()));
    }
    public task(String descr, LocalDateTime term){  //Constructor 3, χωρίς κατηγορία και χωρίς προταιρεότητα
        this.description.append(descr);
        this.term = term;
        this.cat = Main.category.Allo;  //Η κατηγορία αρχικοποιείται με Allo
        this.priority = 'Z';            //η προτεραιότητα αρχικοποιείται με Z
        done=(term.isBefore(LocalDateTime.now()));
    }
    @Override
    public String toString(){   //Υλοποίηση της toString με StringBuilder για καλύτερη απόδοση
        StringBuilder tmp = new StringBuilder();
        tmp.append(' ');
        tmp.append(priority);
        tmp.append(", ");
        tmp.append(cat);
        tmp.append(", «");
        tmp.append(description);
        tmp.append("», ");
        tmp.append(term.format(Main.formatter));
        return tmp.toString();
    }
}