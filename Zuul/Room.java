import java.util.HashMap;

/**
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
    
    private final String name;
    private final String description;
    private final String imageName;

    private final HashMap<String, Room> exits = new HashMap<String, Room>();
    private final ItemList content;

    private Item key;
    private boolean locked;
    
    
    /**
     * Constructeur de la classe Room
     * Permet d'initialiser une nouvelle salle
     * @param name Nom de la salle
     * @param description Description de la salle instanciee
     * @param imageName Nom de l'image
     */
    public Room(final String name, final String description, final String imageName) {
        this.name = name;
        this.description = description;
        this.imageName = imageName;
        this.content = new ItemList();

        this.locked = false;
        
    }

    /**
     * Permet de récupérer le nom de la salle
     * @return Nom de la salle instanciée
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter qui recupere la description de la salle
     * @return Description de la salle
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 
     * @return
     */
    public String getShortDescription() {
        return "Room : " + this.name + " | " + this.description + " |";
    }

    /**
     * Getter qui permet de recuperer l'integralite des informations de la salle
     * @return Description complete de la salle instanciee ainsi que la presence des items
     */
    public String getLongDescription() {
        return getDescription() + "\n" + getExitsString() + "\n" + getItemString();
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
     * Permet de savoir si la direction vers une sortie existe
     * @param direction Direction de la sortie
     * @return Si la sortie est disponible
     */
    public boolean isExit(final String direction)  {
        return this.exits.containsKey(direction);
    }

    /**
     * (Surcharge de Fonction) Permet de savoir si une salle vers une sortie existe
     * @param room Salle de la sortie
     * @return Si la salle est accessible
     */
    public boolean hasExit(final Room room) {
        return this.exits.containsValue(room);
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
     * Récupére la liste des Items disponible dans la salle
     * @return ItemList de la salle
     */
    public ItemList getRoomInventory() {
        return this.content;
    }
    
    public String lookForItem(final String itemName) {
        return this.content.getItem(itemName) != null ? this.content.getItem(itemName).getLongDescription() : "Item not found";
    }
    
    /**
     * Getter qui recupere l'ensemble des informations des items dans la salle
     * @return Liste des descriptions des items dans la salle
     */
    public String getItemString() {
        if(content.isEmpty())
            return "No items here";
        
        StringBuilder itemContent = new StringBuilder();
        
        for(Item item : content.getContent()) {
            itemContent.append("\"" + item.getName() + "\": ");
            itemContent.append("\t" + item.getLongDescription() + "\n");
        }
        
        return "Available items (" + content.getSize() + ") : " + itemContent.toString();
    }


    /**
     * Permet de verifier si la salle actuelle est verrouillée
     * @return Si la salle est verrouillée
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Permet de changer le status de la salle ainsi que son accès
     * @param locked Nouveau status de la salle
     */
    public void setLocked(final boolean locked) {
        this.locked = locked;
    }

    /**
     * Permet de récupérer la clé nécessaire au déverrouillage de la salle
     * @return Clé nécessaire
     */
    public Item getKey() {
        return this.key;
    }

    /**
     * Permet de définir une clé permettant de déverrouiller la salle
     * @param key Nouvelle clé utilisée pour déverrouiller la salle
     */
    public void setKey(final Item key) {
        this.key = key;
    }

    /**
     * Permet de déverrouiller la salle avec une clé
     * @param playerInventory Clés externes utilisée pour déverrouiller la salle
     * @return Si la salle a été déverrouillée
     */
    public boolean tryToUnlock(final ItemList playerInventory) {
        for(Item item : playerInventory.getContent()) {
            if(item.equals(this.key)) {
                setLocked(false);
                return true;
            }
        }
        return false;
    }

}
