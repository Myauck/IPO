import java.util.Stack;
import java.util.HashMap;

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
    private Stack<Room> previousRooms;
    private HashMap<String, Item> items;
    
    /**
     * Constructeur de la classe Player
     * @param name Nom du joueur dans le jeu
     */
    public Player(final String name){
        this.name = name;
        this.previousRooms = new Stack<Room>();
        this.items = new HashMap<String, Item>();
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
    
    public String lookForItem(final String itemName) {
        return currentRoom.getItem(itemName) != null ? currentRoom.getItem(itemName).getLongDescription() : "Item not found";
    }
    
    public String takeItem(final String itemName) {
        Item foundItem = currentRoom.getItem(itemName);
        if(foundItem == null) {
            return "Item in this room is not found !";
        }
        
        this.items.put(foundItem.getName().toLowerCase(), foundItem);
        this.currentRoom.removeItem(foundItem);
        return "You took the item " + foundItem.getName() + " !"; 
    }
    
    public String dropItem(final String itemName) {
        if(!this.items.containsKey(itemName.toLowerCase()))
            return "You have any item named like this on your player !";
        
        Item item = this.items.get(itemName.toLowerCase());
        this.currentRoom.addItem(item);
        this.items.remove(itemName.toLowerCase());
        return "You dropeed the item " + item.getName() + " in the room !"; 
    }
    
}
