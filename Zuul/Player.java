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
    private Stack<Room> previousRooms;
    
    /**
     * Constructeur de la classe Player
     * @param name Nom du joueur dans le jeu
     */
    public Player(final String name){
        this.name = name;
        this.previousRooms = new Stack<Room>();
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
}
