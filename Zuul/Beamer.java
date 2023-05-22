import java.util.HashMap;

public class Beamer extends Item {
    
    private static final HashMap<Player, Room> chargedRooms;

    private final boolean infinite;
    private boolean used;

    static {
        chargedRooms = new HashMap<>();
    }

    /**
     * Constructeur de la classe Beamer
     * @param beamerName Nom du Téléporteur
     * @param weight Poids du téléporteur
     * @param infinite Si le téléporteur peut être utilisé plusieurs fois
     */
    public Beamer(final String beamerName, final String description, final int weight, final boolean infinite) {
        super(beamerName, description, weight);
        
        this.infinite = infinite;
        this.used = false;

    }

    public void charge(final Player player, final Room room) {
        Beamer.chargedRooms.put(player, room);
    }

    /**
     * Permet de savoir si le joueur est initisé dans un téléporteur
     * @param player Player qu'on cherche
     * @return Si le joueur a chargé un téléporteur
     */
    public boolean isCharged(final Player player) {
        return Beamer.chargedRooms.get(player) != null;
    }

    /**
     * Permet de savoir si le téléporteur est usagé et obselète
     * @return Si le téléporteur a été utilisé
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Permet de téléporter un joueur
     * @param player
     * @return Salle ou le joueur va se déplacer
     */
    public Room fire(final Player player) {
        final Room teleportedRoom = Beamer.chargedRooms.get(player);

        if(used) return null;
        if(!infinite) {
            this.used = true;
            Beamer.chargedRooms.put(player, null);
        }

        return teleportedRoom;
    }

}
