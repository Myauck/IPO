
/**
 * Décrivez votre classe Item ici.
 *
 * @author Gaillet Leo
 * @version 23/04/16
 */
public class Item
{
    private final String name;
    private final String description;
    private final int weight;
    
    /**
     * Constructeur d'objets de classe Item
     */
    public Item(final String name, final String description, final int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public String getLongDescription() {
        return "Item name: " + this.name + " is described as \"" + this.description + "\" and costs " + this.weight + " pounds.";
    }
    
}
