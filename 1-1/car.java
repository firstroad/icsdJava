public class car {
    private final String brand;
    private final String model;
    private final int hp;
    private final int cc;
    private final int price;
    private final String color;
    car(String brand, String model, int hp, int cc, int price, String color){
        this.brand=brand;
        this.model=model;
        this.hp=hp;
        this.cc=cc;
        this.price=price;
        this.color=color;
    }
    public void display(){
        System.out.println(brand+" "+model+" "+hp+" "+cc+" "+price+" "+color);
    }
}
