import java.util.HashMap;

/**
 * ItemList est une classe dédiée à la gestion d'une collection des
 * Item présent pour dans un joueur sous forme d'inventaire ou 
 * sous forme d'un ensemble de fonctionnalités permettant
 * de manipuler les Items qui se trouvent à l'interieur de celle-ci
 *
 * @author Leo GAILLET
 * @version 20/04/2023
 */
public class ItemList {
    
    private final HashMap<String, Item> items = new HashMap<String, Item>();
    
    public Item getItem(final String itemName) {
        return items.get(itemName.toLowerCase());
    }
    
    public void addItem(final Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }
    
    public void removeItem(final Item item) {
        this.items.remove(item.getName().toLowerCase());
    }
    
    public boolean hasItem(final String itemName) {
        return this.items.containsKey(itemName.toLowerCase());
    }
    
    public boolean isEmpty() {
        return getSize() == 0;
    }
    
    public int getSize() {
        return this.items.size();
    }
    
    public Item[] getContent() {
        Item[] availableItems = new Item[items.size()];
        int i = 0;
        for(Item item : items.values()) {
            availableItems[i] = item;
            i++;
        }
        return availableItems;
    }
    
}
