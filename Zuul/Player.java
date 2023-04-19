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
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private Item item;
    
    /**
     * Constructeur de la classe Player
     * @param name Nom du joueur dans le jeu
     */
    public Player(final String name){
        this.name = name;
        this.previousRooms = new Stack<Room>();
    }
    
    /**
     * Permet de récupérer le nom du joueur
     * @return Nom du joueur
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Permet de récupérer la salle dans laquelle le joueur se trouve actuellement
     * @return Salle du joueur actuelle
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    
    /**
     * Permet de savoir si le joueur est déjà allé dans d'autres salles précédentes
     * @return Vérifie si le joueur peut retourner en arrière
     */
    public boolean hasPreviousRoom() {
        return this.previousRooms.size() != 0;
    }
    
    /**
     * Permet de déplacer le joueur dans une nouvelle salle
     * @param room Nouvelle salle où le joueur sera déplacé
     * @param saveCurrentToPreviousRooms Autorise la sauvegarde de l'ancienne salle dans la liste des salles précédentes
     */
    public void setCurrentRoom(final Room room, final boolean saveCurrentToPreviousRooms) {
        if(saveCurrentToPreviousRooms) {
            this.previousRooms.push(this.currentRoom);
        }
        this.currentRoom = room;
    }
    
    /**
     * Permet de déplacer le joueur dans son ancienne salle
     * @return Salle où le joueur compte se déplacer
     */
    public Room goPreviousRoom() {
        Room previousRoom = this.previousRooms.pop();
        if(previousRoom != null)
            this.setCurrentRoom(previousRoom, false);
        return previousRoom;
    }
    
    public String lookForItem(final String itemName) {
        return currentRoom.getItem(itemName) != null ? currentRoom.getItem(itemName).getLongDescription() : "Item not found";
    }
    
    public String takeItem(final String itemName) {
        if(this.item != null)
            return "You already have an item on your player !";
        
        Item foundItem = currentRoom.getItem(itemName);
        if(foundItem == null) {
            return "Item in this room is not found !";
        }
        
        this.item = foundItem;
        this.currentRoom.removeItem(foundItem);
        return "You took the item " + foundItem.getName() + " !"; 
    }
    
    public String dropItem() {
        if(this.item == null)
            return "You have any item on your player !";
        
        Item item = this.item;
        this.currentRoom.addItem(this.item);
        this.item = null;
        return "You dropeed the item " + item.getName() + " in the room !"; 
    }
    
}
