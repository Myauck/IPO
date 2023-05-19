import java.util.Stack;

/**
 * Les differents attributs et fonctions li�es au Joueur, permettant
 * ainsi de se d�placer, de connaitre sa salle actuelle, de savoir ce qu'il a sur lui
 * de savoir ses salles pr�c�dentes. Tout ce qui est li� ou influence sur le Joueur.
 *
 * @author Leo Gaillet
 * @version 19/04/2023
 */
public class Player {
    
    private final String name;
    private Room currentRoom;
    private final Stack<Room> previousRooms;
    private final ItemList items;
    private int maxWeight;
    private int weight;
    
    /**
     * Constructeur de la classe Player
     * @param name Nom du joueur dans le jeu
     * @param maxWeight Poids maximal que peut poss�der l'inventaire du joueur
     */
    public Player(final String name, final int maxWeight){
        this.name = name;
        this.previousRooms = new Stack<Room>();
        this.items = new ItemList();
        
        this.maxWeight = maxWeight;
        this.weight = 0;
        
    }
    
    /**
     * Permet de r�cup�rer le nom du joueur
     * @return Nom du joueur
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Permet de r�cup�rer la salle dans laquelle le joueur se trouve actuellement
     * @return Salle du joueur actuelle
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    
    /**
     * Permet de savoir si le joueur est d�j� all� dans d'autres salles pr�c�dentes
     * @return V�rifie si le joueur peut retourner en arri�re
     */
    public boolean hasPreviousRoom() {
        return this.previousRooms.size() != 0;
    }
    
    /**
     * Permet de d�placer le joueur dans une nouvelle salle
     * @param room Nouvelle salle o� le joueur sera d�plac�
     * @param saveCurrentToPreviousRooms Autorise la sauvegarde de l'ancienne salle dans la liste des salles pr�c�dentes
     */
    public void setCurrentRoom(final Room room, final boolean saveCurrentToPreviousRooms) {
        if(saveCurrentToPreviousRooms) {
            this.previousRooms.push(this.currentRoom);
        }
        this.currentRoom = room;
    }
    
    /**
     * Permet de d�placer le joueur dans son ancienne salle
     * @return Salle o� le joueur compte se d�placer
     */
    public Room goPreviousRoom() {
        Room previousRoom = this.previousRooms.pop();
        if(previousRoom != null)
            this.setCurrentRoom(previousRoom, false);
        return previousRoom;
    }

    /**
     * Permet d'effacer l'historique des salles visitées
     */
    public void clearPreviousRooms() {
        this.previousRooms.clear();
    }
    
    public String takeItem(final String itemName) {
        Item foundItem = currentRoom.getItemList().getItem(itemName);
        
        if(foundItem == null)
            return "Item in this room is not found !";
        
        if(this.weight + foundItem.getWeight() > this.maxWeight)
            return "You can't take this item ! It's to heavy for you !";
        
        this.weight += foundItem.getWeight();
        this.items.addItem(foundItem);
        this.currentRoom.getItemList().removeItem(foundItem);
        
        return "You took the item " + foundItem.getName() + " !"; 
    }
    
    public String dropItem(final String itemName) {
        if(!this.items.hasItem(itemName.toLowerCase()))
            return "You have any item named like this on your player !";
        
        Item item = this.items.getItem(itemName.toLowerCase());
        this.currentRoom.getItemList().addItem(item);
        this.weight -= item.getWeight();
        this.items.removeItem(item);
        return "You dropeed the item " + item.getName() + " in the room !"; 
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public int getMaxWeight() {
        return this.maxWeight;
    }

    public void setMaxWeight(final int maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    public String getInventoryContent() {
        String response = "(" + this.items.getSize() + ")\n";
        for(Item item : this.items.getContent()) {
            response += "- " + item.getLongDescription() + "\n";
        }
        return response;
    }

    public ItemList getInventory() {
        return this.items;
    }
    
}
