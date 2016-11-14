//321/2015004 Aivatidis Prodromos 3
import java.util.ArrayList; //Κλήση κατάλληλων βιβλιοθηκών, για τα γράμματα
import java.util.HashMap;   //για δημιουργία της δομής μας hashmap για τις λέξεις
import java.util.Iterator;  //για χρήση iterator

public final class round {
    private HashMap <String,String> words = new HashMap<>();    //Αποθήκευση 3 λέξεων του γύρου
    private ArrayList <Character> letters = new ArrayList<>();  //Αποθήκευση των γρμμάτων που εμφανίζονται
    private int[] found= new int[3];    //Αν βρέθηκε μία λέξεη γίνεται 1 το κατάλληλο κελί
    int difficulty;
    int number; //αιρθμός γύρου
    int tries;
    public int getTries(){ return tries; }
    public boolean isNotDone(){ //Αν ο πίνακας found είναι γεμάτος με άσσους τότε έχει ολοκληρωθεί ο γύρος
        return !(found[0]==1 && found[1]==1 && found[2]==1);
    }
    public int triesMinus(){    //Μείωση προσπαθειών
        return --tries;
    }
    public void showLetters(){  //Εμφάνιση γραμμάτων που έμειναν με χρήση iterator
        System.out.println();
        Iterator<Character> itr = letters.iterator();
        while(itr.hasNext()) System.out.print(itr.next().toString()+" ");
        System.out.println();
    }
    public round(int number, int difficulty){   //Κλήση constructor
        this.number=number; //Κρατάμε τον αριθμό του γύρου
        this.tries=6;       //Αρχικοποιούμε τον αριθμό προσπαθειών με 6
        this.difficulty=difficulty;
        this.found = new int[]{0,0,0};  //Αρχικοποιούμε τον πίνακα με 0
        System.out.println("\nΓύρος παιχνιδιού: "+number);
        for(int i=0;i<3;i++){   //Για να κρατήσουμε 3 λέξεις για τον γύρο
            for (String tmp : Main.words.keySet()) {    //Προσπαελάσουμε το hashmap με όλες τις λέξεις
                words.put(tmp, Main.words.get(tmp));    //και προσθέτουμε τη λέξη και το κείμενο της στο τοπικό hashmap
                Main.words.remove(tmp);                 //Και τις διαγράφουμε από τo hashmap της main για να μην υπάρχουν σε επόμενο γύρο
                break;
            }
        }
        for(String tmp:words.keySet()){
            System.out.format("%-55s \t",words.get(tmp));
            if(difficulty==1){
                for(int j=0;j<tmp.length();j++){
                    System.out.print(" _");
                    letters.add((int)(Math.random() * letters.size()),tmp.charAt(j));
                }
                System.out.println();
            }
            else {
                System.out.print(tmp.charAt(0));
                for(int j=1;j<(tmp.length()-1);j++){
                    System.out.print(" _");
                    letters.add((int)(Math.random() * letters.size()),tmp.charAt(j));
                }
                System.out.println(" "+tmp.charAt(tmp.length()-1));
            }
        }
        showLetters();
    }
    public boolean check(int word, String guess){
        Iterator<String> itr = words.keySet().iterator();
        int i=1;
        while(itr.hasNext()) {
            String tmp=itr.next();
            if(word==i++){
                if(guess.equals(tmp) && found[i-2]==0){
                    for(int j=0;j<tmp.length();j++){
                        if( ( difficulty==0 && j!=0 && j!=(tmp.length()-1) ) || difficulty==1 )
                         letters.remove((Character)tmp.charAt(j));
                        
                    }
                    found[i-2]=1;
                    return true;
                }
            }
        }
        return false;
    }
}
