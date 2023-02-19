import java.util.HashMap;

/**
 * Classe Room - Un lieu du jeu d'aventure Zuul.
 *
 * @author Léo GAILLET
 * @version 1
 */
public class Room
{
    
    private final String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();
    
    
    /**
     * Constructeur de la classe Room
     * Permet d'initialiser une nouvelle salle
     * 
     * @param description Description de la salle instanciÃ©e
     */
    public Room(final String description) {
        this.description = description;
    }
    
    
    /**
     * Getter qui récupère la description de la salle
     * 
     * @return Description de la salle
     */
    public String getDescription() {
        return this.description;
    }
 
    
    /**
     * Getter qui récupère la salle d'une sortie en fonction de la direction de la sortie
     * 
     * @param direction Direction de la sortie qu'on cherche
     * @return Salle se trouvant à la sortie recherchée
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
     * Getter qui récupère les sorties disponibles dans la salle instanciée
     * 
     * @return Chaîne comportant la liste des sorties disponibles
     */
    public String getExitsString() {
        String availableExits = "Available Exits : ";
        for(String keys : this.exits.keySet()) availableExits += keys + " ";
        return availableExits;
    }
    

    /**
     * Getter qui permet de récupérer l'intégralité des informations de la salle
     * 
     * @return Description complète de la salle instanciée
     */
    public String getLongDescription() {
        return getDescription() + "\n" + this.getExitsString();
    }
    
} // Room
