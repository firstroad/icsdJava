import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*TODO
 * Dhmiourgia metavlhtwn max gia swsth diaxeirish tou pinaka
 * del kai search: dhmiourgia if gia elegxo an den uparxei kai emfanish katallhlwn mhnumatwn
*/
public class ex01 {
    private static final HashMap <String,car> cars = new HashMap <>();
    public static void main(String[] args) {
        cars.put("123",new car("toyota","Celica",255,2000,15000,"White"));
        cars.put("555",new car("Fiat","Cinquecento",39,900,700,"Black"));
        cars.put("753",new car("FIAT","Punto",45,1100,500,"red"));
        cars.put("666",new car("Subaru","Impreza WRX STI 1999",300,2500,50000,"Blue"));
        cars.put("359",new car("Toyota","Starlet",43,1000,5000,"Gray"));
    }
    private static void add(){
        Scanner in = new Scanner(System.in);
        System.out.println("Εισάγετε εταιρεία κατασκευής: ");
        String brand = in.next();
        System.out.println("Εισάγετε μοντέλο: ");
        String model = in.next();
        System.out.println("Εισάγετε ιπποδύναμη σε άλογα (hp): ");
        int hp = in.nextInt();
        System.out.println("Εισάγετε χωρητκότητα σε κυβικά εκατοστά(cc): ");
        int cc = in.nextInt();
        System.out.println("Εισάγετε κόστος σε ευρώ(€): ");
        int price = in.nextInt();
        System.out.println("Εισάγετε χρώμα: ");
        String color = in.next();
        String randomCode;
        do{ 
            int r = (int)(Math.random()*1000);
            randomCode = String.valueOf(r/100%10) + String.valueOf(r/10%10) + String.valueOf(r%10);
        } while(cars.get(randomCode) != null );
        cars.put(randomCode,new car(brand,model,hp,cc,price,color));
    }    
    private static void del(){
        Scanner in = new Scanner(System.in);
        System.out.println("Εισάγετε αριθμό προς διαγραφή: ");
        String tmp = in.next();
        cars.remove(tmp);
        System.out.println("Το αυτοκίνητο με αριθμό "+tmp+" διαγράφηκε επιτυχώς");
    }
    private static void search(){
        Scanner in = new Scanner(System.in);
        System.out.println("Εισάγετε αριθμό προς αναζήτηση: ");
        String tmp = in.next();
        System.out.println(cars.get(tmp).toString());
    }
    private static void brands(){
        HashSet <String> tmp = new HashSet <> ();
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            tmp.add(cars.get(it.next()).getBrand());
        }
        for(String str: tmp){
            System.out.println(str);
        }
    }
    private static void power(String maxORmin, String brand){
        int max=0,min=9999;
        String maxID="", minID="";
        brand=(brand!=null)?toTitleCase(brand):null;
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String tmp = it.next();
            if( cars.get(tmp).getBrand().equals(brand) || brand==null ){
                max=Math.max(cars.get(tmp).getHP(),max);
                maxID=(max==cars.get(tmp).getHP())?tmp:maxID;
                min=Math.min(cars.get(tmp).getHP(),min);
                minID=(min==cars.get(tmp).getHP())?tmp:minID;
            }
        }
        if(maxORmin.equals("max"))
            System.out.println("To autokinhto me th megaluterh ippodunamh einai:\n"+maxID+" "+cars.get(maxID).toString());
        else if(maxORmin.equals("min"))
            System.out.println("To autokinhto me th mikroterh ippodunamh einai:\n"+minID+" "+cars.get(minID).toString());
        else System.out.println("Lathos parametros, eisagete min h max gia mikroterh h megaluterh ippodunamh antistoixa");
    }
    public static void power(String maxORmin){
        power(maxORmin,null);
    }
    public static int brandCars(String brand){
        brand=toTitleCase(brand);
        int counter=0;
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            if(cars.get(it.next()).getBrand().equals(brand))counter++;
        }
        return counter;
    }
    private static void displayByPrice(String maxORmin, int price){
        System.out.format("%3s %-20s %-20s %-4s %-5s %-7s %-18s\n", "ID", "Brand", "Model", "HP","CC","Price","Color");
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String cur=it.next();
            Boolean lol = (maxORmin.equals("max")?price>cars.get(cur).getPrice():price<cars.get(cur).getPrice());
            if((maxORmin.equals("max")?price>cars.get(cur).getPrice():price<cars.get(cur).getPrice()))
                System.out.println(cur + ' ' + cars.get(cur).toString());
        }
    }
    private static void displayAll(){
        System.out.format("%3s %-20s %-20s %-4s %-5s %-7s %-18s\n", "ID", "Brand", "Model", "HP","CC","Price","Color");
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            String cur=it.next();
            System.out.println(cur + ' ' + cars.get(cur).toString());
        }
    }
    public static String toTitleCase(String tmp){
        return tmp.substring(0,1).toUpperCase() + tmp.substring(1).toLowerCase();
    }
}
