//321/2015004 Aivatidis Prodromos 1
import java.util.HashMap;   //Κλήση κατάλληλων βιβλιοθηκών, για δημιουργία της δομής μας hashmap
import java.util.HashSet;   //για την μέθοδο brands
import java.util.Iterator;  //για προσπέλαση του hashmap
import java.util.Scanner;   //για ανάγνωση από το χρήστη

public class Main {
    public static Scanner in = new Scanner(System.in);  //Δημιουργία ενός Scanner για όλες τις συναρτήσεις της Main
    private static final HashMap <String,car> cars = new HashMap <>(); //Δημιουργία και αρχικοποίηση hashmap, επιλέχθηκε γιατί υλοποιούμε μεθόδους με χρήση ενός κλειδιού και κάνει πολύ εύκολη, τη διαγραφή, αναζήτηση κλπ με απόδοση O(1)
    public static void main(String[] args) {
        int option;
        do{
            System.out.print("******************************\n*Σύστημα Διαχείρισης CarDealer*\n******************************\n0->Εμφάνιση όλων των αυτοκινήτων\n1->Προσθήκη αυτοκινήτου\n2->Διαγραφή αυτοκινήτου\n3->Αναζήτηση αυτοκινήτου\n4->Εμφάνιση κατασκευαστών\n5->Εμφάνιση αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη\n6->Εμφάνιση αυτοκινήτου με τη μικρότερη ιπποδύναμη\n7->Εμφάνιση αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη συγκεκριμένου κατασκευαστή\n8->Εμφάνιση αυτοκινήτου με τη μικρότερη ιπποδύναμη συγκεκριμένου κατασκευαστή\n9->Πλήθος αυτοκινήτων συγκεκριμένου κατασκευαστή\n10->Εμφάνιση αυτοκινήτων φθηνότερα από συγκεκριμένη τιμή\n11->Εμφάνιση αυτοκινήτων ακριβότερα από συγκεκριμένη τιμή\n12->Τερματισμός Λειτουργίας\nΕισάγετε τον αριθμό της επιλογής σας και πατήστε Enter: ");
            option = in.nextInt();in.nextLine(); //Μετά από κάθε nextInt έχω προσθέσει nextLine για να κρατάει το Enter που πατιέται αλλιώς δημιουργεί πρόβλημα στις επόμενες nextLine
            if(option!=1  && option!=12 && cars.isEmpty()) System.out.println("Δεν υπάρχουν αυτοκίνητα"); //Εμφάνιση μηνύματος όταν η το hashmap είναι άδειο και προσπαθούμε να κάνουμε κάτι άλλο εκτός από το να προσθέσουμε ή να προσθέσουμε αυτοκίνητο
            else if(option==0)  displayAll();
            else if(option==1)  add();
            else if(option==2)  del();
            else if(option==3)  search();
            else if(option==4)  brands();
            else if(option==5)  power(1,0); //το 1,0 σημαίνει αναζήτηση του αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη ανεξαρτήτου μάρκας
            else if(option==6)  power(0,0); //το 0,0 σημαίνει αναζήτηση του αυτοκινήτου με τη μικρότερη ιπποδύναμη ανεξαρτήτου μάρκας
            else if(option==7)  power(1,1); //το 1,1 σημαίνει αναζήτηση του αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη για συγκεκριμένη μάρκα
            else if(option==8)  power(0,1); //το 0,1 σημαίνει αναζήτηση του αυτοκινήτου με τη μιρκότερη ιπποδύναμη για συγκεκριμένη μάρκα
            else if(option==9)  System.out.println("Αριθμός αυτοκινήτων: "+carsOf());
            else if(option==10) displayByPrice(0);  //αυτοκίνητα φθηνότερα από...
            else if(option==11) displayByPrice(1);  //αυτοκίνητα ακριβότερα από...
            if(option!=12) { System.out.print("Πατήστε Enter για να συνεχίσετε..."); in.nextLine(); }   // Λόγω όγκου του μενού θεώρησα σωστό να υπάρχει μία παύση για να μπορούμε να δούμε τα αποτελέσματα των μεθόδων
        }while(option!=12);
    }
    private static void add(){
        System.out.print("Εισάγετε εταιρεία κατασκευής: ");
        String brand = in.nextLine();
        System.out.print("Εισάγετε μοντέλο: ");
        String model = in.nextLine();
        System.out.print("Εισάγετε ιπποδύναμη σε άλογα (hp): ");
        int hp = in.nextInt();
        System.out.print("Εισάγετε χωρητικότητα σε κυβικά εκατοστά(cc): ");
        int cc = in.nextInt();
        System.out.print("Εισάγετε κόστος σε ευρώ(€): ");
        int price = in.nextInt();
        System.out.print("Εισάγετε χρώμα: ");
        in.nextLine();  //βλ. σχόλια γραμμής 14
        String color = in.nextLine();
        String randomCode;
        do{ 
            int r = (int)(Math.random()*1000);  //Για τη δημιουργία τυχαίου τριψήφιου αριθμού δημιουργούμε έναν αριθμό από το 0 μέχρι το 999
            randomCode = String.valueOf(r/100%10) + String.valueOf(r/10%10) + String.valueOf(r%10); //και κάθε ψηφίο του το κάνουμε String αφού στο τέλος θα το αποθηκεύσουμε σαν String, αυτό γίνεται ώστε να πρστεθούν ακόμα 100 αριθμοί όπως 005 ή 055
        } while(cars.get(randomCode) != null ); //και τρέχει όσο δεν υπάρχει αυτός ο αριθμός στο hashmap
        cars.put(randomCode,new car(brand,model,hp,cc,price,color));    //Κλήση put με το randomcode και κλήση constructor me τα στοιχεία που προστέθηκαν
        System.out.println("Προστέθηκε το αυτοκίνητο με τα παρακάτω στοιχεία");
        tabTitle(); //Μέθοδος για εμφάνιση τίτλων πίνακα, εξηγείται στο τέλος
        System.out.println(randomCode + ' ' + cars.get(randomCode)); //Εμφάνιση τυχαίου αριθμού και του αυτοκινήτου
    }
    private static void del(){  
        System.out.print("Εισάγετε αριθμό προς διαγραφή: ");
        String tmp = in.nextLine();
        if(cars.remove(tmp)!=null)  //Αν το remove γυρίσει null σημαίνει ότι δεν υπήρχε ο αριθμός αλλιώς επιστρέφει την toString πριν το διαγράψει
            System.out.println("Το αυτοκίνητο με ID "+tmp+" διαγράφηκε επιτυχώς");
        else System.out.println("Δεν υπάρχει αυτοκίνητο με ID "+tmp);
    }
    private static void search(){
        System.out.print("Εισάγετε αριθμό προς αναζήτηση: ");
        String tmp = in.nextLine();
        if(cars.get(tmp)!=null){    //Αν το get γυρίσει null σημαίνει ότι δεν υπήρχε ο αριθμός
            tabTitle();
            System.out.println(tmp + ' ' + cars.get(tmp).toString());
        }
        else System.out.println("Δεν υπάρχει αυτοκίνητο με ID "+tmp);
    }
    private static void brands(){   //Για την εμφάνιση όλων των κατασκευαστών...
        HashSet <String> tmp = new HashSet <> ();   //...δημιουργούμε ένα HashSet..
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) //...και για κάθε αντικείμενο στο hashmap cars...
            tmp.add(cars.get(it.next()).getBrand());    //...περνάμε το brand του στο hashset έτσι τα διπλότυπα δεν μπαίνουν
        tmp.forEach(System.out::println);   //Εμφανίζουμε το hashset
    }
    private static void power(int maxORmin, int byBrand){   //Η μέθοδος power εμφανίζει ττο αυτοκίνητο με τη μεγαλύτερη ή τη μικρότερη ιπποδύναμη ανα μάρκα ή όχι αντί να χρησιμοποιήσω overloading με πολλάπλές ΊΔΙΕΣ γραμμές κώδικα (DRY no WET)
        int max=0,min=9999;
        String maxID="", minID="";
        String brand="";
        if(byBrand==1){ //Αν το bybrand είναι 1 έχουμε να κάνουμε με αναζήτηση ανα μάρκα..
            System.out.print("Εισάγεται κατασκευαστή: ");   //...οπότε ζητάμε τον κατασκευαστή...
            brand = toTitleCase(in.nextLine()); //...και τον κάνουμε toTitleCase όπως είναι γίνεται σε όλους τους κατασκευαστές όταν εισέρχονται στο hashmap
        }
        for (String tmp : cars.keySet()) {  //κάθε στοιχείο του cars
            if( cars.get(tmp).getBrand().equals(brand) || byBrand==0 ){ //Κάνουμε συγκρίσεις αν ο κατασκευαστής του αυτοκινήτου είναι ο ίδιος με αυτόν που έχουμε εισάγει ή το byBrand  είναι 0 όπου έχουμε αναζήτηση ανεξαρτήτου μάρκας
                max = Math.max( cars.get(tmp).getHP() , max);           //το max παίρνει τη μέγιστη τιμή μεταξύ max και την ιπποδύναμη του στοιχείου με χρήση της συνάρτησης
                maxID = ( max==cars.get(tmp).getHP() ) ? tmp : maxID;   //το maxID παίρνει τo ID (μοναδικό κωδικό) του αυτοκινήτου με τη μέγιστη τιμή αν το max άλλαξε τιμή αλλιώς συνεχίζει με την προηγούμεη τιμή του
                min = Math.min( cars.get(tmp).getHP() , min);           //Ίδια λογική για min
                minID=( min == cars.get(tmp).getHP() ) ? tmp : minID;
            }
        }
        System.out.println("Το αυτοκίνητο με τη "+((maxORmin==1)?"μεγαλύτερη":"μικρότερη")+" ιπποδύναμη είναι:");   //Εμφανίζεται το αντίστοιχο μήνυμα ανάλογα με το τι θέλουμε
        tabTitle(); //Εμφανίζονται οι τίτλοι των στοιχείων
        System.out.println(((maxORmin==1)?maxID:minID)+' '+ cars.get((maxORmin==1)?maxID:minID));   //και τέλος το ID και το κατάλληλο στοιχείο αναλογα με το ποιο ζητήσαμε
    }
    public static int carsOf(){
        System.out.print("Εισάγεται κατασκευαστή και πατήστε Enter: ");
        String brand = toTitleCase(in.nextLine());
        int counter=0;
        for (String cur : cars.keySet())    //για κάθε στοιχείου του hashmap...
            if(cars.get(cur).getBrand().equals(brand))counter++;    //...αν είναι ίδιος ο κατασκευαστής που εισάγαμε με του στοιχείου αυξάνουμε το counter κατά ένα
        return counter;
    }
    private static void displayByPrice(int maxORmin){
        System.out.print("Εισάγετε τιμή και πατήστε Enter: ");
        int price = in.nextInt(); in.nextLine();
        tabTitle();
        for (String cur : cars.keySet()) {  //για κάθε στοιχείου του hashmap...
            if( maxORmin==1 ? cars.get(cur).getPrice() > price : cars.get(cur).getPrice() < price ) //Αν το maxORmin είναι 1 τότε συγκρίνουμε ωστε η τιμή του στοιχείου να είναι μεγαλύτερη από την εισαγμένη τιμή για να εμφανιστούν τα αυτοκίνητα που είναι ακριβότερα από την τιμή που εισάγαμε αλλιώς συγκρίνουμε για να εμφανιστούν τα φθηνότερα
                System.out.println(cur + ' ' + cars.get(cur).toString());
        }   //Δεν υπάρχει περίπτωση το maxORmin να είναι διαφορετικό από 0 ή 1 αφού καλείται στατικά
    }       //Δεν έλαβα υπόψη μου τη περίπτωση του να έχει ίδια τιμή γιατί δεν αναφερόταν τίποτα για αυτό στο pdf
    private static void displayAll(){   //Εμφάνιση όλων των αυτοκινήτων
        tabTitle();
        for (String cur : cars.keySet()) {  //Για κάθε στοιχείο κρατάμε τον κωδικό του στο cur
            System.out.println(cur + ' ' + cars.get(cur));   //τον τυπώνουμε και μετά τυπώνουμε το στοιχείο με get και το κωδικό που κρατήσαμε sto cur
        }
    }
    public static String toTitleCase(String tmp){   //Αυτή η μέθοδος χρησιμοποιείται σε συγκρίσεις με κατασκευαστές
        return tmp.substring(0,1).toUpperCase() + tmp.substring(1).toLowerCase();   //το πρώτο στοιχείο του δωθέν string γίνεται κεφαλαίο και το υπόλοιπο substring πεζό
    }
    private static void tabTitle(){ //Αυτή η μέθοδος χρησιμοποιείται πριν την εμφάνιση αυτοκινήτου/ων ώστε να ξέρουμε η κάθε τιμή σε ποιο πεδίο αντιστοιχεί
        System.out.format("%3s %-20s %-25s %-4s %-5s %-7s %-18s\n", "ID", "Κατασκευαστής", "Μοντέλο", "HP","CC","Τιμή","Χρώμα");    //Χρησιμοποιούμε παρόμοιο format με την toString της car, λαμβάνοντας υπόψη και το ID
    }
}
