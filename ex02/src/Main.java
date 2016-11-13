//321/2015004 Aivatidis Prodromos 2
import java.time.LocalDate;                 //Προσθήκη κατάλληλων βιβλιοθηκών, για ημερομηνία μόνο
import java.time.LocalDateTime;             //για ημερομηνία και ώρα
import java.time.format.DateTimeFormatter;  //για επεξεργασία μορφής ημερομηνίας
import java.util.Comparator;                //για να γίνονται οι ταξινομήσεις στη δομή όπως επιθυμούμε
import java.util.Scanner;                   //για ανάγνωση εισαγωγής από το χρήστη
import java.util.TreeSet;                   //για την δομή μας, το treeset, επιλέχθηκε επειδή με την υλοποίηση του comparator μπορούμε πολύ εύκολα να εισάγουμε στοιχεία με ταξινόμηση, και ταυχτόχρονα πολύ καλή απόδοση O(log n)

public class Main {
    protected static enum category { Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo }  //Δημιουργία enum για επιλογή κατηγορίας
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");   //Δημιουργία επεξεργαστή μορφής ημερομηνίας και ώρας για ανάγνωση και τύπωση με τη μορφή πουθέλουμε
    protected static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("d/M/yyyy");    //Δημιουργία επεξεργαστή μορφής ημερομηνίας και ώρας
    private static final TreeSet <task> todo = new TreeSet <>(new cmp());   //Δημιουργία δυναμικής δομής treeset
    static class cmp implements Comparator<task>{       //Δημιουργία κλάσσης που υλοποιεί τον comparator
        @Override
        public int compare(task e1, task e2) {  //όπου κάνουμε override την compare μέθοδο
            return e1.getTerm().compareTo(e2.getTerm());    //για να συγκρίνουμε βάση προθεσμίας και να ταξινομούμε τα στοιχεία κατά την εισαγωγή
        }
    }
    private static Scanner in = new Scanner(System.in);     //Δημιουργία ενός Scanner για όλες τις συναρτήσεις της Main
    public static void main(String[] args) {   
        todo.add(new task("Anaplhrwsh tennis", LocalDateTime.now().minusDays(2), Main.category.Proswpika, 'D'));    //Αρχικοποίηση κάποιων εργασιών
        todo.add(new task("Agora trofhs skulou", LocalDateTime.now().plusDays(1).withHour(14).withMinute(0), category.Katoikidio, 'A'));
        todo.add(new task("Volta me to makh prin fugei", LocalDateTime.now().plusDays(2), Main.category.Hobby, 'B'));
        todo.add(new task("Eksoflhsh OTE", LocalDateTime.now().plusDays(10), Main.category.Ypoxrewseis, 'C'));
        todo.add(new task("Eksoflhsh DEH", LocalDateTime.now().plusDays(12), Main.category.Ypoxrewseis, 'C'));
        todo.add(new task("Paradosh project", LocalDateTime.now().plusDays(2), Main.category.Ergasia, 'A'));
        todo.add(new task("Dwro gia th Maria", LocalDateTime.now().plusSeconds(6), Main.category.Proswpika, 'B'));  //PROSOXH auth h ergasia ξεκινά ως εκκρεμότητα αφού είναι 6 δευτερόλεπτα μετά την τωρινή ώρα αλλά μετά με την ανανέωση μπορεί να γίνει ολοκληρωμενη (σχεδιάστηκε για χρήση στην update()
        int option = 9;
        do{ //Δημιουργία μενού επιλογής
            System.out.println("***********\n*TaskMngr*\n***********\n0->Εισαγωγή νέας εκκρεμότητας\n1->Εμφάνιση ολόκληρης της λίστας εκκρεμοτήτων\n2->Αναζήτηση εκκρεμοτήτων με βάση την προτεραιότητα\n3->Αναζήτηση εκκρεμοτήτων με βάση την κατηγορία\n4->Αναζήτηση εκκρεμοτήτων για συγκεκριμένη ημέρα\n5->Αναζήτηση εκκρεμοτήτων μέχρι κάποιο συγκεκριμένο χρονικό διάστημα\n6->Χαρακτηρισμός μιας εργασίας ως ολοκληρωμένη\n7->Ανανέωση εκκρεμοτήτων\n8->Εμφάνιση ιστορικού με τις ολοκληρωμένες εργασίες\n9->Τερματισμός");
            option = in.nextInt();in.nextLine();    //Μετά από κάθε nextInt έχω προσθέσει nextLine για να κρατάει το Enter που πατιέται αλλιώς δημιουργεί πρόβλημα στις επόμενες nextLine
            if(option!=1  && option!=9 && todo.isEmpty()) System.out.println("Δεν υπάρχουν εργασίες ή εκκρεμότητες");   //Εμφάνιση μηνύματος όταν η το δέντρο είναι άδειο και προσπαθούμε να κάνουμε κάτι άλλο εκτός από το να προσθέσουμε ή να δημιουργήσουμε εργασία
            else if(option==0)  add();          //Προσθήκη
            else if(option==1)  find(false);    //Εύρεση εκκρεμοτήτων done=false
            else if(option==2)  {   //Αναζήτηση βάση προτεραιότητας, με έλεγχο εγκυρότητας
                char priority=' ';
                do {
                    System.out.print("Εισάγετε προτεραιότητα (A-Z): ");
                    priority = Character.toUpperCase(in.nextLine().charAt(0));  //Το priority γίνεται ο κεφαλαίος πρώτος χαρακτήρας από το string που διαβάστηκε
                }while(!Character.isAlphabetic(priority));  //Τρέξε μέχρι να είναι γράμμα
                find(priority);
            }
            else if(option==3)  {   //Αναζήτηση βάση κατηγορίας, με έλεγχο εγκυρότητας
                category cat=null;
                do{
                    System.out.print("Επιλέξτε κατηγορία (Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo): ");
                    try{cat = category.valueOf(in.nextLine());}catch(Exception e){} //Το cat θα γίνει η enum τιμή του string που διαβάστηκε και σε περίπτωση exception δεν τερματίζεται το πρόγραμμα
                }while (cat==null);
                find(cat);
            }
            else if(option==4)  {   //Αναζήτηση για συγκεκριμένη ημερομηνία, με έλεγχο εγκυρότητας
                LocalDate term=null;//Μεταβλητή αποθήκευσης μόνο ημερομηνίας
                do{ 
                    System.out.print("Εισάγετε ημερομηνία για εμφάνιση (μορφή: Η/Μ/ΕΕΕΕ): ");
                    try{term= LocalDate.parse(in.nextLine(),formatterD);}catch(Exception e){}   //Το term θα γίνει το string που διαβάστηκε σύμφωνα με τον formatterD που ορίσαμε προηγομένως και σε περίπτωση exception δεν τερματίζεται το πρόγραμμα
                }while (term==null);
                find(term);
            }
            else if(option==5)  {   //Εμφάνιση μέχρι συγκεκριμένης ημερομηνίας και ώρας, με έλεγχο εγκυρότητας
                LocalDateTime term=null;    //Μεταβλητή αποθήκευσης ημερομηνίας και ώρας
                do{ 
                    System.out.print("Εισάγετε ημερομηνία και ώρα προθεσμίας(μορφή: Η/Μ/ΕΕΕΕ ΩΩ:ΛΛ): ");
                    try{term= LocalDateTime.parse(in.nextLine(),formatter);}catch(Exception e){}    //Το term θα γίνει το string που διαβάστηκε σύμφωνα με τον formatter που ορίσαμε προηγομένως και σε περίπτωση exception δεν τερματίζεται το πρόγραμμα
                }while (term==null);
                find(term);
            }
            else if(option==6)  { //Ολοκλήρωση εκκρεμότητας
                System.out.print("Εισάγετε περιγραφή: ");
                done(in.nextLine());    
            }
            else if(option==7)  update();   //Ολοκλήρωσε όσες εργασίες έχει περάσει η ημερομηνία τους
            else if(option==8)  find(true); //Αναζήτηση ολοκληρωμένων εργασιών
            if(option!=9) {System.out.print("Πατήστε Enter για να συνεχίσετε: ");in.nextLine();}
        }while(option!=9);
    }
    private static void add(){
        System.out.print("Εισάγετε περιγραφή: ");
        String description=in.nextLine();
        LocalDateTime term=null;
        do{ //Ανάγνωση ημερομηνία και ώρα με έλεγχο εγκυρότητας
            System.out.print("Εισάγετε ημερομηνία και ώρα προθεσμίας (μορφή: Η/Μ/ΕΕΕΕ ΩΩ:ΛΛ): ");
            try{term= LocalDateTime.parse(in.nextLine(),formatter);}catch(Exception e){}        //Το term θα γίνει το string που διαβάστηκε σύμφωνα με τον formatter που ορίσαμε προηγομένως και σε περίπτωση exception δεν τερματίζεται το πρόγραμμα
        }while (term==null);
        category cat=null;
        String tmp;
        do{ //Ανάγνωση κατηγορίας με έλεγχο εγκυρότητας
            System.out.print("Επιλέξτε κατηγορία (Ergasia, Proswpika, Hobby, Spiti, Katoikidio, Ypoxrewseis, Allo): ");
             tmp = in.nextLine();
            try{cat = category.valueOf(tmp);}catch(Exception e){}   //Το cat θα γίνει η enum τιμή του string που διαβάστηκε και σε περίπτωση exception δεν τερματίζεται το πρόγραμμα
        }while (cat==null && !tmp.isEmpty());
        char priority=' ';
        String temp;
        do {    //Ανάγνωση προτεραιότητας με έλεγχο εγκυρότητας
            System.out.print("Εισάγετε προτεραιότητα (A-Z): ");
            temp=in.nextLine();
            priority = temp.isEmpty()?' ':temp.charAt(0); //Το priority γίνεται ο κεφαλαίος πρώτος χαρακτήρας από το string που διαβάστηκε αν δεν είναι άδειο το string, αλλιώς θα γίνει ο κενός χαρακτήρας
        }while(!Character.isWhitespace(priority) && !Character.isAlphabetic(priority));
        if(tmp.isEmpty() && Character.isWhitespace(priority))   //Αν το προσωρινό String που διαβάστηκε στη κατηγορία είναι κενό και η μεταβλητή της προτεραιότητας άδεια
            todo.add(new task(description,term));               //κάλεσε τον constructor 3
        else if(tmp.isEmpty())                                  //Αλλιώς αν μόνο το προσωρινό String που διαβάστηκε στη κατηγορία είναι κενό
            todo.add(new task(description,term,priority));      //Κάλεσε τον constructor 2
        else if(Character.isWhitespace(priority))               //Αλλιώς αν μόνο η μεταβλητή της προτεραιότητας άδεια
            todo.add(new task(description,term,cat));           //Κάλεσε τον constructor 1
        else
            todo.add(new task(description,term,cat,priority));  //Αλλιως κάλεσε τον constructor 0
    }
    public static void find(boolean done){  //Μέθοδος για εμφάνιση εκκρεμοτήτων ή ολοκληρωμένων εργασιών, παίρνει boolean
        for (task tmp : todo) { //Προσπέλαση του δέντρου
            if(tmp.getDone() == done)   //Αν το done είναι true εμφανίζει όσες έψχουν done=true δηλαδή όσες έχουν γίνει αλλιώς το ανάποδο για να δείξει όσες εκκρεμούν
                System.out.println(tmp);    //τυπώνουμε το αντικείμενο ( γίνεται String από την .toString() )
        }
    }
    public static void find(char priority){ //Υπερφορτωμένη μέθοδος για εμφάνιση εκκρεμοτήτων ανα προτεραιότητα, παίρνει χαρακτήρα
        for (task tmp : todo) {
            if(!tmp.getDone() && priority==tmp.getPriority())   //Αν το αντικείμενο έχει προτεραιότητα ίση με αυτή που εισάγαμε το εμφανίζει
                System.out.println(tmp);
        }
    }
    public static void find(category cat){  //Υπερφορτωμένη μέθοδος για εμφάνιση εκκρεμοτήτων ανα κατηγορία, παίρνει category μεταβλητή
        for (task tmp : todo) {
            if(!tmp.getDone() && cat.equals(tmp.getCat()))  //Δουλεύει όπως και η προηγούμενη αλλά χρησιμοποιούμε την equals λόγω enum (ασφαλέστερη)
                System.out.println(tmp);
        }
    }
    public static void find(LocalDate term){    //Υπερφορτωμένη μέθοδος για εμφάνιση εκκρεμοτήτων συγκεκριμένης ημερομηνίας, δέχεται ημερομηνία
        for (task tmp : todo) {
            if(!tmp.getDone() && tmp.getTerm().toLocalDate().isEqual(term)) //Αν δεν έχει ολοκληρωθεί η εργασία και η ημερομηνία ΜΟΝΟ της προθεσμίας είναι ίδια με την ημερομηνία που εισάγαμε εμφανίζεται η εργασία
                System.out.println(tmp);
        }
    }
    public static void find(LocalDateTime term){    //Υπερφορτωμένη μέθοδος για εμφάνιση εκκρεμοτήτων μέχρι συγκεκριμένη ημερομηνία, δέχεται ημερομηνία και ώρα
        for (task tmp : todo) {
            if(!tmp.getDone() && tmp.getTerm().isBefore(term)) //Αν δεν έχει ολοκληρωθεί η εργασία και η ημερομηνία και ώρα της προθεσμίας είναι πριν την ημερομηνία και ώρα που εισάγαμε εμφανίζεται η εργασία
                System.out.println(tmp);
        }
    }
    public static void done(String description){    //Μέθοδος για να ορίσουμε ως ολοκληρωμένη μία εργασία
        for (task tmp : todo) {
            if(tmp.getDescription().toString().equals(description)) //αν ισούται το string που εισάγαμε με αυτό της εργασίας όρισε το done=true
                tmp.setDone();
        }
        System.out.println("Egine!");
    }
    public static void update(){    //Μέθοδος για να ορίσουμε ως ολοκληρωμένες όσες εργασίες έχουν περάσει την τωρινή ημερομηνία και ώρα
        for (task tmp : todo) {
            if(tmp.getTerm().isBefore(LocalDateTime.now())){ //Αν η ημερομηνία και ώρα της εργασίας είναι πριν τη τωρινή ημερομηνία
                tmp.setDone();  //όρισε το done=true
            }
        }
        System.out.println("Egine!");
    }
}