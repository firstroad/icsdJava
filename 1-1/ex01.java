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
            cars.get(it.next()).toString();
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
        
        int r;
        do{ r = (int)(Math.random()*999+1); } while(cars.get(String.valueOf(r)) != null );
        
        cars.put(String.valueOf(r),new car(brand,model,hp,cc,price,color));
    }
}
