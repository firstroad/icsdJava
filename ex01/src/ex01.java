import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ex01 {
    private static final HashMap <String,car> cars = new HashMap <>();
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int option=12;
        do{
            System.out.print("******************************\n*Σύστημα Διαχείρισης CarDealer*\n******************************\n0->Εμφάνιση όλων των αυτοκινήτων\n1->Προσθήκη αυτοκινήτου\n2->Διαγραφή αυτοκινήτου\n3->Αναζήτηση αυτοκινήτου\n4->Εμφάνιση κατασκευαστών\n5->Εμφάνιση αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη\n6->Εμφάνιση αυτοκινήτου με τη μικρότερη ιπποδύναμη\n7->Εμφάνιση αυτοκινήτου με τη μεγαλύτερη ιπποδύναμη συγκεκριμένου κατασκευαστή\n8->Εμφάνιση αυτοκινήτου με τη μικρότερη ιπποδύναμη συγκεκριμένου κατασκευαστή\n9->Πλήθος αυτοκινήτων συγκεκριμένου κατασκευαστή\n10->Εμφάνιση αυτοκινήτων φθηνότερα από συγκεκριμένη τιμή\n11->Εμφάνιση αυτοκινήτων ακριβότερα από συγκεκριμένη τιμή\n12->Τερματισμός Λειτουργίας\nΕισάγετε τον αριθμό της επιλογής σας και πατήστε Enter: ");
            option = in.nextInt();
            if(option!=1  && option!=12 && cars.isEmpty()) System.out.println("Δεν υπάρχουν αυτοκίνητα");
            else if(option==0)  displayAll();
            else if(option==1)  add();
            else if(option==2)  del();
            else if(option==3)  search();
            else if(option==4)  brands();
            else if(option==5)  power(1,0);
            else if(option==6)  power(0,0);
            else if(option==7)  power(1,1);
            else if(option==8)  power(0,1);
            else if(option==9)  System.out.println("Αριθμός αυτοκινήτων: "+carsOf());
            else if(option==10) displayByPrice(0);
            else if(option==11) displayByPrice(1);
            if(option!=12) { System.out.print("Πατήστε Enter για να συνεχίσετε..."); System.in.read(); }
        }while(option!=12);
    }
    private static void add(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε εταιρεία κατασκευής: ");
        String brand = in.nextLine();
        System.out.print("Εισάγετε μοντέλο: ");
        String model = in.nextLine();
        System.out.print("Εισάγετε ιπποδύναμη σε άλογα (hp): ");
        int hp = in.nextInt();
        System.out.print("Εισάγετε χωρητκότητα σε κυβικά εκατοστά(cc): ");
        int cc = in.nextInt();
        System.out.print("Εισάγετε κόστος σε ευρώ(€): ");
        int price = in.nextInt();
        System.out.print("Εισάγετε χρώμα: ");
        in.nextLine();
        String color = in.nextLine();
        String randomCode;
        do{ 
            int r = (int)(Math.random()*1000);
            randomCode = String.valueOf(r/100%10) + String.valueOf(r/10%10) + String.valueOf(r%10);
        } while(cars.get(randomCode) != null );
        cars.put(randomCode,new car(brand,model,hp,cc,price,color));
        System.out.println("Προστέθηκε το αυτοκίνητο με τα παρακάτω στοιχεία");
        tabTitle();
        System.out.println(randomCode + ' ' + cars.get(randomCode).toString());
    }
    private static void del(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε αριθμό προς διαγραφή: ");
        String tmp = in.nextLine();
        if(cars.remove(tmp)!=null)
            System.out.println("Το αυτοκίνητο με ID "+tmp+" διαγράφηκε επιτυχώς");
        else System.out.println("Δεν υπάρχει αυτοκίνητο με ID "+tmp);
    }
    private static void search(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε αριθμό προς αναζήτηση: ");
        String tmp = in.nextLine();
        if(cars.get(tmp)!=null){
            tabTitle();
            System.out.println(tmp + ' ' + cars.get(tmp).toString());
        }
        else System.out.println("Δεν υπάρχει αυτοκίνητο με ID "+tmp);
    }
    private static void brands(){
        HashSet <String> tmp = new HashSet <> ();
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();)
            tmp.add(cars.get(it.next()).getBrand());
        for(String str: tmp)
            System.out.println(str);
    }
    private static void power(int maxORmin, int byBrand){
        int max=0,min=9999;
        String maxID="", minID="";
        String brand=" ";
        if(byBrand==1){
            Scanner in = new Scanner(System.in);
            System.out.print("Εισάγεται κατασκευαστή: ");
            brand = toTitleCase(in.nextLine());
        }
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String tmp = it.next();
            if( cars.get(tmp).getBrand().equals(brand) || byBrand==0 ){
                max = Math.max( cars.get(tmp).getHP() , max);
                maxID = ( max==cars.get(tmp).getHP() ) ? tmp : maxID;
                min = Math.min( cars.get(tmp).getHP() , min);
                minID=( min == cars.get(tmp).getHP() ) ? tmp : minID;
            }
        }
        System.out.println("Το αυτοκίνητο με τη "+((maxORmin==1)?"μεγαλύτερη":"μικρότερη")+" ιπποδύναμη είναι:");
        tabTitle();
        System.out.println(((maxORmin==1)?maxID:minID)+' '+ cars.get((maxORmin==1)?maxID:minID).toString());
    }
    public static int carsOf(){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγεται κατασκευαστή και πατήστε Enter: ");
        String brand = toTitleCase(in.nextLine());
        int counter=0;
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();)
            if(cars.get(it.next()).getBrand().equals(brand))counter++;
        return counter;
    }
    private static void displayByPrice(int maxORmin){
        Scanner in = new Scanner(System.in);
        System.out.print("Εισάγετε τιμή και πατήστε Enter: ");
        int price = in.nextInt();
        tabTitle();
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String cur=it.next();
            if( maxORmin==1 ? cars.get(cur).getPrice() > price : cars.get(cur).getPrice() < price )
                System.out.println(cur + ' ' + cars.get(cur).toString());
        }
    }
    private static void displayAll(){
        tabTitle();
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String cur=it.next();
            System.out.println(cur + ' ' + cars.get(cur).toString());
        }
    }
    public static String toTitleCase(String tmp){
        return tmp.substring(0,1).toUpperCase() + tmp.substring(1).toLowerCase();
    }
    private static void tabTitle(){
        System.out.format("%3s %-20s %-25s %-4s %-5s %-7s %-18s\n", "ID", "Κατασκευαστής", "Μοντέλο", "HP","CC","Τιμή","Χρώμα");
    }
}
