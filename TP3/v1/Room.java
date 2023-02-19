package v1;

import java.util.HashMap;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Léo GAILLET
 * @version 1.0.0-[7.16]
 */
public class Room
{
    
    private final String aDescription;
    private final HashMap<String, Room> aExits = new HashMap<String, Room>();
    
    /**
     * Constructeur de la classe Room
     * 
     * @param pDescription Description de la salle instanciée
     */
    public Room(final String pDescription) {
        this.aDescription = pDescription;
    }
    
    public String getDescription() {
        return this.aDescription;
    }
 
    public void setExit(final String direction, final Room room) {
        
    }
    
    public void showAvailableExits() {
        System.out.print("Exits : ");
        if(this.aNorthExit != null) {
            System.out.print("north ");
        }
        if(this.aSouthExit != null) {
            System.out.print("south ");
        }
        if(this.aEastExit != null) {
            System.out.print("east ");
        }
        if(this.aWestExit != null) {
            System.out.print("west ");
        }
    }
    
} // Room
