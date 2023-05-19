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
    
    /**
     * Permet de récupérer un item dans la liste des items
     * @param itemName Nom de l'item
     * @return Item s'il est trouvé
     */
    public Item getItem(final String itemName) {
        return items.get(itemName.toLowerCase());
    }
    
    /**
     * Permet d'ajouter un item à la liste des items
     * @param item Nouvel item à ajouter
     */
    public void addItem(final Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }
    
    /**
     * Permet de retirer un item de la liste des items
     * @param item que l'on souhaite retirer
     */
    public void removeItem(final Item item) {
        this.items.remove(item.getName().toLowerCase());
    }
    
    /**
     * Permet de savoir s'il existe un item qui correspond
     * @param itemName Item que l'on cherche
     * @return Vrai s'il est trouvé, faux s'il ne l'est pas
     */
    public boolean hasItem(final String itemName) {
        return this.items.containsKey(itemName.toLowerCase());
    }
    
    /**
     * Permet de savoir si la liste des items est vide
     * @return Si la liste est vide
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }
    
    /**
     * Permet de récupérer la taille actuelle de la liste
     * (Le nombre d'items disponible dans la liste d'items)
     * @return Taille de la liste
     */
    public int getSize() {
        return this.items.size();
    }
    
    /**
     * Permet de récupérer l'ensemble des items
     * @return Tableau
     */
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
