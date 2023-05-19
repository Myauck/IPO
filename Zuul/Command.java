/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author Gaillet L
 */
public class Command {

    private String commandWord;
    private String secondWord;
    
    /**
     * Constructeur de la classe Command
     * @param commandWord Commande principale saisie par l'utilisateur
     * @param secondWord Commande secondaire saisie par l'utilisateur
     */
    public Command (final String commandWord, final String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
    
    /**
     * Getter qui recupere la commande principale
     * 
     * @return Commande principale
     */
    public String getCommandWord() {
        return this.commandWord;
    }
    
    /**
     * Getter qui recupere la commande secondaire
     * @return Commande secondaire
     */
    public String getSecondWord() {
        return this.secondWord;
    }

    /**
     * Permet de savoir si la commande possede une commande secondaire
     * @return Si la commande secondaire existe
     */
    public boolean hasSecondWord() {
        return this.getSecondWord() != null;
    }
    
    /**
     * Permet de savoir si la commande principale n'est pas nulle
     * @return Si la commande est nulle
     */
    public boolean isUnknown()
    {
        return this.getCommandWord() == "" || this.getCommandWord() == null;
    }
}