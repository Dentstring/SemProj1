import java.util.List;

public class Inventory {


    private List<Item> items;


    public Inventory(){

    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public List<Item> getItems() {
        return items;
    }
}
