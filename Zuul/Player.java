import java.util.Stack;

/**
 * Les differents attributs et fonctions liées au Joueur, permettant
 * ainsi de se déplacer, de connaitre sa salle actuelle, de savoir ce qu'il a sur lui
 * de savoir ses salles précédentes. Tout ce qui est lié ou influence sur le Joueur.
 *
 * @author Leo Gaillet
 * @version 19/04/2023
 */
public class Player {
    
    private final String name;
    private int maxWeight;
    private Room currentRoom;

    private final Stack<Room> previousRooms;
    private final ItemList playerInventory;
    
    /**
     * Constructeur de la classe Player
     * @param name Nom du joueur dans le jeu
     * @param maxWeight Poids maximal que peut posséder l'inventaire du joueur
     */
    public Player(final String name, final int maxWeight){
        this.name = name;
        this.maxWeight = maxWeight;
        
        this.previousRooms = new Stack<Room>();
        this.playerInventory = new ItemList();
        
    }
    
    /**
     * Permet de récupérer le nom du joueur
     * @return Nom du joueur
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Permet de récupérer la capacité au joueur de porter des items
     * @return Poids maximal que le joueur peut porter
     */
    public int getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * Permet de définir une nouvelle capacité au joueur de porter des items
     * @param maxWeight Nouvelle capacité de port du joueur
     */
    public void setMaxWeight(final int maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    /**
     * Permet de récupérer la salle dans laquelle le joueur se trouve actuellement
     * @return Salle du joueur actuelle
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    
    /**
     * Permet de savoir si le joueur est déjé allé dans d'autres salles précédentes
     * @return Vérifie si le joueur peut retourner en arriére
     */
    public boolean hasPreviousRoom() {
        return this.previousRooms.size() != 0;
    }
    
    /**
     * Permet de déplacer le joueur dans une nouvelle salle
     * @param room Nouvelle salle oé le joueur sera déplacé
     * @param savePreviousRoom Autorise la sauvegarde de l'ancienne salle dans la liste des salles précédentes
     */
    public void setCurrentRoom(final Room room, final boolean savePreviousRoom) {
        if(savePreviousRoom) this.previousRooms.push(this.currentRoom);
        this.currentRoom = room;
    }
    
    /**
     * Permet de déplacer le joueur dans son ancienne salle
     * @return Salle oé le joueur compte se déplacer
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
        Item foundItem = currentRoom.getRoomInventory().getItem(itemName);
        
        if(foundItem == null)
            return "Item in this room is not found !";
        
        if(this.playerInventory.getContentWeight() + foundItem.getWeight() > this.maxWeight)
            return "You can't take this item ! It's to heavy for you !";
        
        this.playerInventory.addItem(foundItem);
        this.currentRoom.getRoomInventory().removeItem(foundItem);
        
        return "You took the item " + foundItem.getName() + " !"; 
    }
    
    public String dropItem(final String itemName) {
        if(!this.playerInventory.hasItem(itemName.toLowerCase()))
            return "You have any item named like this on your player !";
        
        Item item = this.playerInventory.getItem(itemName.toLowerCase());
        this.currentRoom.getRoomInventory().addItem(item);
        this.playerInventory.removeItem(item);
        return "You dropeed the item " + item.getName() + " in the room !"; 
    }

    public ItemList getPlayerInventory() {
        return this.playerInventory;
    }
    
}
