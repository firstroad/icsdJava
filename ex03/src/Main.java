import java.util.HashMap;   
import java.util.Scanner;   

public class Main {
    public static HashMap <String,String> words = new HashMap<>();
    private static Scanner in = new Scanner(System.in);     
    public static void main(String[] args) {
        handler();  
    }
    public static void handler(){
        dictionary();   
        System.out.println("ΚΑΛΩΣΗΡΘΑΤΕ ΣΤΟ ΠΑΙΧΝΙΔΙ «ΒΡΕΣ ΤΙΣ ΛΕΞΕΙΣ»");
        System.out.print("Εισάγετε το όνομα σας: ");
        String name = in.nextLine();
        System.out.print("Επιλέξτε αριθμό επιπέδου δυσκολίας ( 0-Εύκολο 1-Δύσκολο ) : ");
        int difficulty = in.nextInt(); in.nextLine();   
        play(1, difficulty);    
        System.out.println("Ευχαριστούμε που παίξατε");
    }
    private static void play(int round, int difficulty){
        round curRound = new round(round,difficulty);   
        while(curRound.isNotDone() && curRound.getTries()>0){
            System.out.println("Επιλέξτε λέξη που θέλετε να μαντέψετε: ");
            int word = in.nextInt(); in.nextLine();
            System.out.println("Εισάγετε τη λέξη: ");
            String guess = in.nextLine();
            if(curRound.check(word, guess)) System.out.println("Μπράβο. Μαντέψατε σωστά!!!");
            else {
                if(curRound.triesMinus()==0) System.out.println("Λυπάμαι χάσατε");
                else System.out.println("Λάθος. Έχετε ακόμα "+curRound.getTries()+" προσπάθειες");
            }
            curRound.showLetters();
        }
        if(!curRound.isNotDone() && !words.isEmpty()){
            System.out.println("Θα θέλατε να συνεχίσετε με 3 νέες λέξεις (ΝΑΙ για να συνεχίσετε); ");
            String tmp = in.nextLine();
            if(tmp.equals("ΝΑΙ"))
                play(++round,difficulty);
        }
    }
    private static void dictionary(){
        words.put("ΠΡΑΣΙΝΟ", "Χρώμα γνωστού φρούτου και Ελληνικής ομάδας ποδοσφαίρου");
        words.put("ΚΑΡΠΟΥΖΙ", "Φρούτο του καλοκαιριού με μέγεθος μπάλας ποδοσφαίρου");
        words.put("ΜΑΡΓΑΡΙΤΑ", "Γνωστό λουλούδι και το πιο απλό είδος πίτσας");
        words.put("ΜΑΚΑΡΟΝΙΑ", "Γνωστό φαγητό που κατάγεται από την Ιταλία");
        words.put("ΑΛΕΞΑΝΔΡΑ", "Θηλυκό όνομα για αυτή που διώχνει τους άνδρες");
        words.put("ΤΡΙΓΩΝΟ", "Σχήμα από τα βασικά με τις λιγότερες πλευρές και γωνίες");
        words.put("ΚΑΝΑΠΕΣ", "Ακριβό έπιπλο, απαραίτητο για το καθιστικό");
        words.put("ΑΙΓΥΠΤΟΣ", "Χώρα με μεγάλη ιστορία, πρωτεύουσα της το Κάιρο");
        words.put("ΑΦΡΟΔΙΤΗ", "Θηλυκό όνομα γυναίκας και πλανήτη στο ηλιακό μας σύστημα");
        words.put("ΠΑΠΑΓΑΛΟΣ", "Ζώο γνωστό για την ομιλία του και τα χρώματα του");
        words.put("ΑΘΗΝΑ", "Όνομα πόλης, γυναίκας και Θεα του δωδεκάθεου");
        words.put("ΤΟΥΛΙΠΑ", "Το λουλούδι που είναι γνωστό ως το έμβλημα της αγάπης");
        words.put("ΠΟΔΗΛΑΤΟ", "Δίτροχο όχημα που δουλεύει με χρήση μυϊκής δύναμης ");
        words.put("ΚΡΑΣΙ", "Αλκοολούχο προϊόν για το οποίο είναι γνωστή η Σάμος");
        words.put("ΧΡΥΣΟΣ", "Από τα πιο ακριβά μέταλλα με χαρακτηριστικό χρώμα");
        words.put("ΚΙΤΡΙΝΟ", "Χρώμα της σοφίας και ελληνικού μουσικού συγκρτήματος");
        words.put("ΡΟΔΑΚΙΝΟ", "Πορτοκαλί φρούτο γνωστό για το χνούδι του");
        words.put("ΠΡΟΔΡΟΜΟΣ", "Το όνομα του πρώτου προφήτη σύμφωνα με το Χριστιανισμό");
        words.put("ΩΚΕΑΝΙΑ", "Η ήπειρος στην οποία ανήκει η Αυστραλία");
        words.put("ΣΥΡΟΣ", "Νησί του κεντρικού Αιγαίου γνωστό για τα λουκούμια");
        words.put("ΔΗΜΑΡΧΕΙΟ", "Δημόσια υπηρεσία της βασικής αυτοδιοικητικής μονάδας");
    }
}
