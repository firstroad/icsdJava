//321/2015004 Aivatidis Prodromos 1
public class car {
    private final String brand; //Δημιουργία μεταβλητών κλάσσης
    private final String model;
    private final int hp;
    private final int cc;
    private final int price;
    private final String color;
    public String getBrand() { return this.brand; } //Δημιουργία απαραίτητων get μεθόδων
    int getHP() { return this.hp; }
    int getPrice() { return this.price; }
    car(String brand, String model, int hp, int cc, int price, String color){   //Δημιουργία constructor, για το σχεδιασμό απαιτούμε όλα τα στοιχεία
        brand=Main.toTitleCase(brand);  //επέλεξα όλες οι μάρκες να έχουν το πρώτο κεφαλαίο και τα άλλα μικρά για αν απαοφύγω τις συγκρίσεις με συναρτήσεις toLowerCase ή toUpperCase
        this.brand=brand;
        this.model=model;
        this.hp=hp;
        this.cc=cc;
        this.price=price;
        this.color=color;
    }
    @Override
    public String toString(){   //Override την toString με επιστροφή επεξεργασμένου String για να αφήνει ομοιόμορφα κενά μεταξύ των στοιχείων
        return String.format("%-20s %-25s %-4d %-5d %-7d %-18s", brand, model, hp,cc,price,color);
    }
}
