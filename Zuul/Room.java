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
 * @author  Michael Kolling, David J. Barnes, Leo Gaillet
 * @version 1.0 (February 2002) DBMOD:04/04/2008, 2019, 2023
 */
public class Room
{
    
    private final String description;
    private final HashMap<String, Room> exits = new HashMap<String, Room>();
    private final String imageName;
    private final ItemList items;
    
    
    /**
     * Constructeur de la classe Room
     * Permet d'initialiser une nouvelle salle
     * @param description Description de la salle instanciee
     * @param imageName Nom de l'image
     */
    public Room(final String description, final String imageName) {
        this.description = description;
        this.imageName = imageName;
        this.items = new ItemList();
    }
    
    /**
     * Getter qui recupere la description de la salle
     * @return Description de la salle
     */
    public String getDescription() {
        return this.description;
    }
 
    /**
     * Getter qui permet de recuperer le nom de l'image
     * @return Nom de l'image
     */
    public String getImageName() {
        return this.imageName;
    }
    
    /**
     * Getter qui recupere la salle d'une sortie en fonction de la direction de la sortie
     * @param direction Direction de la sortie qu'on cherche
     * @return Salle se trouvant a la sortie recherchee
     */
    public Room getExit(final String direction) {
        return this.exits.get(direction.toLowerCase());
    }
    
    /**
     * Setter qui affecte une nouvelle salle de sortie en fonction de la direction que l'on a choisie
     * @param direction Direction de la sortie
     * @param exitRoom Salle qui se trouve dans la direction choisie
     */
    public void setExit(final String direction, final Room exitRoom) {
        if (exitRoom != null)
            this.exits.put(direction.toLowerCase(), exitRoom);
    }
    
    /**
     * Getter qui recupere les sorties disponibles dans la salle instanciee
     * @return Chaine comportant l'ensemble des sorties disponibles
     */
    public String getExitsString() {
        String availableExits = "Available Exits : ";
        for(String keys : this.exits.keySet())
            availableExits += keys + " ";
        return availableExits;
    }

    /**
     * Getter qui permet de recuperer l'integralite des informations de la salle
     * @return Description complete de la salle instanciee ainsi que la presence des items
     */
    public String getLongDescription() {
        return getDescription() + "\n" + getExitsString() + "\n" + getItemString();
    }
    
    public ItemList getItemList() {
        return this.items;
    }
    
    /**
     * Getter qui recupere l'ensemble des informations des items dans la salle
     * @return Liste des descriptions des items dans la salle
     */
    public String getItemString() {
        if(items.isEmpty())
            return "No items here";
        
        StringBuilder itemContent = new StringBuilder();
        for(Item item : items.getContent()) {
            itemContent.append("\"" + item.getName() + "\": ");
            itemContent.append("\t" + item.getLongDescription() + "\n");
        }
        return "Available items (" + items.getSize() + ") : " + itemContent.toString();
    }
}
