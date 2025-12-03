package game.buildings;

public abstract class House extends Building{

    private final int capacity;

    public House(String name, int cost, int capacity){
        super(name, cost);
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }
}
