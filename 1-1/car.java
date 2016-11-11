import java.util.Objects;

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
        brand=ex01.toTitleCase(brand);
        this.brand=brand;
        this.model=model;
        this.hp=hp;
        this.cc=cc;
        this.price=price;
        this.color=color;
    }
    @Override
    public String toString(){
        return String.format("%-20s %-20s %-4d %-5d %-7d %-18s", brand, model, hp,cc,price,color);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (obj instanceof car) {
            car PersonObj=(car) obj;
            return (this.toString().equals(PersonObj.toString()));
        }
        return false;
    }  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.brand);
        hash = 97 * hash + Objects.hashCode(this.model);
        hash = 97 * hash + this.hp;
        hash = 97 * hash + this.cc;
        hash = 97 * hash + this.price;
        hash = 97 * hash + Objects.hashCode(this.color);
        return hash;
    }
}
