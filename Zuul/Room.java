import java.util.HashMap;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling, David J. Barnes, Léo Gaillet
 * @version 1.0 (February 2002) DBMOD:04/04/2008, 2019, 2023
 */
public class Room
{
    
    private final String description;
    private final HashMap<String, Room> exits = new HashMap<String, Room>();
    private final String imageName;
    private final HashMap<String, Item> items = new HashMap<String, Item>();
    
    
    /**
     * Constructeur de la classe Room
     * Permet d'initialiser une nouvelle salle
     * 
     * @param description Description de la salle instanciée
     */
    public Room(final String description, final String imageName) {
        this.description = description;
        this.imageName = imageName;
    }
    
    /**
     * Getter qui r�cup�re la description de la salle
     * 
     * @return Description de la salle
     */
    public String getDescription() {
        return this.description;
    }
 
    public String getImageName() {
        return this.imageName;
    }
    
    /**
     * Getter qui r�cup�re la salle d'une sortie en fonction de la direction de la sortie
     * 
     * @param direction Direction de la sortie qu'on cherche
     * @return Salle se trouvant � la sortie recherch�e
     */
    public Room getExit(final String direction) {
        return this.exits.get(direction.toLowerCase());
    }
    
    /**
     * Setter qui affecte une nouvelle salle de sortie en fonction de la direction que l'on a choisie
     * 
     * @param direction Direction de la sortie
     * @param exitRoom Salle qui se trouve dans la direction choisie
     */
    public void setExit(final String direction, final Room exitRoom) {
        if (exitRoom != null)
            this.exits.put(direction.toLowerCase(), exitRoom);
    }
    
    
    /**
     * Getter qui r�cup�re les sorties disponibles dans la salle instanci�e
     * 
     * @return Cha�ne comportant la liste des sorties disponibles
     */
    public String getExitsString() {
        String availableExits = "Available Exits : ";
        for(String keys : this.exits.keySet())
            availableExits += keys + " ";
        return availableExits;
    }
    

    /**
     * Getter qui permet de r�cup�rer l'int�gralit� des informations de la salle
     * 
     * @return Description compl�te de la salle instanci�e
     */
    public String getLongDescription() {
        return getDescription() + "\n" + getExitsString() + "\n" + getItemString();
    }
    
    public Item getItem(final String itemName) {
        return this.items.get(itemName.toLowerCase());
    }
    
    public void addItem(final Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }
    
    public String getItemString() {
        if(items.size() == 0)
            return "No items here";
        
        StringBuilder itemContent = new StringBuilder();
        for(Item item : items.values()) {
            itemContent.append("\"" + item.getName() + "\": ");
            itemContent.append("\t" + item.getLongDescription() + "\n");
        }
        return "Available items (" + items.size() + ") : " + itemContent.toString();
    }
}
