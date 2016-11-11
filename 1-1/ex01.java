import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
public class ex01 {
    private static final HashMap <String,car> cars = new HashMap <>();
    public static void main(String[] args) {
        cars.put("123",new car("Toyota","Celica",255,2000,15000,"White"));
        add();
        displayAll();
    }
    private static void displayAll(){
        for (Iterator<String> it = cars.keySet().iterator(); it.hasNext();) {
            System.out.println(cars.get(it.next()).toString());
        }
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
}
