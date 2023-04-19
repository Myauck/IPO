
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
     * @param name Nom de l'item
     * @param description Description de l'item
     * @param weight poids de l'item
     */
    public Item(final String name, final String description, final int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Getter qui permet de recuperer le nom de l'item
     * @return Nom de l'item
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter qui permet de recuperer la description de l'item
     * @return Description de l'item
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Getter qui permet de recuperer le poids de l'item
     * @return Poids de l'item
     */
    public int getWeight() {
        return this.weight;
    }
    
    /**
     * Getter qui permet de recuperer une description complete de l'item
     * @return Description complete de l'item
     */
    public String getLongDescription() {
        return "Item name: " + this.name + " is described as \"" + this.description + "\" and costs " + this.weight + " pounds.";
    }
    
}
