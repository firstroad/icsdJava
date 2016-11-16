
public class car {
    private final String brand; 
    private final String model;
    private final int hp;
    private final int cc;
    private final int price;
    private final String color;
    public String getBrand() { return this.brand; } 
    int getHP() { return this.hp; }
    int getPrice() { return this.price; }
    car(String brand, String model, int hp, int cc, int price, String color){   
        brand=Main.toTitleCase(brand);  
        this.brand=brand;
        this.model=model;
        this.hp=hp;
        this.cc=cc;
        this.price=price;
        this.color=color;
    }
    @Override
    public String toString(){   
        return String.format("%-20s %-25s %-4d %-5d %-7d %-18s", brand, model, hp,cc,price,color);
    }
}
