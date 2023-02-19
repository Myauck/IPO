/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Gaillet L
 */
public class Game {

    private Room currentRoom;
    private Parser parser;
    

    /**
     * Constructeur par défaut de la classe Game
     */
    public Game () {
        this.parser = new Parser();
        this.createRooms();
    }
    
    
    /**
     * Affiche les messages lors du début du jeu
     */
    private void printWelcome () {
        System.out.println("Welcome to the Enchanted City !");
        System.out.println("");
        System.out.println("You are one of these guardian that protect the city from ennemies. A mean witcher had done something bad to the city");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }
    

    private void createRooms () {
        // DÃ©finis les diffÃ©rents endroits possible dans le jeu
        Room kingPalace, fortressPrison, fortressDungeon, fortressUnderground, artefactsRoom, portalsRoom,
            fortressYard, fortressEntrance, silverRiver, joyfulAvenue, cascadesOfDiamonds, secretCascadeOfDiamonds,
            forbiddenForest, forbiddenForestCave;

        // Initialise les diffÃ©rentes Rooms
        kingPalace = new Room("The King's Floor. You are certainly appreciated by the King.");
        fortressDungeon = new Room("The Dungeon of the Fortress. It's a privilege to be here.");
        fortressPrison = new Room("The Prison of the Fortress. It can be some prisoners in there.");
        fortressUnderground = new Room("The Basement of the Fortress. You may see some bats."); 
        artefactsRoom = new Room("The Artefacts Room. You can feel strange things coming from this place.");
        portalsRoom = new Room("The portal room. Portals may teleport you, even if the way by foot is impossible");
        fortressYard = new Room("The Fortress Yard. See how beautiful are flowers around here.");
        fortressEntrance = new Room("The Entrance of The Fortress. Why is this door so tall ?");
        silverRiver = new Room("The Silver River. Is this silver or just the snow falling on it ?");
        joyfulAvenue = new Room("The Joyful Avenue. This avenue goes so far away that I can't see the end.");
        cascadesOfDiamonds = new Room("The Cascades of Diamondstones, The water is so turquoise that I can't see the difference with the color of the diamond");
        secretCascadeOfDiamonds = new Room("The Secret Place of the Cascade of Diamonds, Who could pretend that under the cascade it would be an door ?");
        forbiddenForest = new Room("The Forbidden Forest. The only thing that I can say about this place is it's make me goosebumps");
        forbiddenForestCave = new Room("The Cave of The Forbidden Forest. This place is filled by the Darkness");


        // Affecte les diffÃ©rentes sorties de salles pour chaque salles
        kingPalace.setExit("down", fortressDungeon);

        fortressPrison.setExit("east", fortressDungeon);
        
        fortressDungeon.setExit("west", fortressPrison);
        fortressDungeon.setExit("up", kingPalace);
        fortressDungeon.setExit("down", fortressUnderground);
        fortressDungeon.setExit("south", fortressYard);

        fortressUnderground.setExit("up", fortressDungeon);

        artefactsRoom.setExit("east", fortressYard);

        fortressYard.setExit("south", fortressEntrance);
        fortressYard.setExit("north", fortressDungeon);
        fortressYard.setExit("west", artefactsRoom);
        fortressYard.setExit("east", portalsRoom);

        portalsRoom.setExit("west", fortressYard);

        fortressEntrance.setExit("north", fortressYard);
        fortressEntrance.setExit("south", joyfulAvenue);

        silverRiver.setExit("east", joyfulAvenue);

        joyfulAvenue.setExit("north", fortressEntrance);
        joyfulAvenue.setExit("east", cascadesOfDiamonds);
        joyfulAvenue.setExit("west", silverRiver);
        joyfulAvenue.setExit("south", forbiddenForest);

        cascadesOfDiamonds.setExit("west", joyfulAvenue);
        cascadesOfDiamonds.setExit("down", secretCascadeOfDiamonds);

        forbiddenForest.setExit("north", joyfulAvenue);
        forbiddenForest.setExit("down", forbiddenForestCave);

        secretCascadeOfDiamonds.setExit("up", cascadesOfDiamonds);

        forbiddenForestCave.setExit("up", forbiddenForest);

        // Défini la salle de départ
        this.currentRoom = fortressEntrance;
    }
    
    
    /**
     * Affiche les commandes possibles
     */
    private void printHelp()
    {
        System.out.println("Your commands are :");
        parser.showCommands();
    }
    
    
    /**
     * Affiche la pièce actuelle et ses sorties
     */
    private void printLocationInfo()
    {
        System.out.println(this.currentRoom.getLongDescription());
    }
    
    
    /**
     * Permet d'accéder à une pièce selon la direction et affiche la salle où nous sommes
     * 
     * @param instruction Direction dans laquelle aller 
     */
    private void goRoom(final Command instruction)
    {
        if (!instruction.hasSecondWord()){
            System.out.println("Go where ?");
            return;
        }
        
        Room nextRoom = this.currentRoom.getExit(instruction.getSecondWord());
        
        if (nextRoom == null){
            System.out.println("There is no door !");
            return;
        }
        
        this.currentRoom = nextRoom;
        
        printLocationInfo();
    }
    
    
    /**
     * Permet de quitter le jeu
     * 
     * @param pInstruction Commande ecrite par l'utilisateur
     * @return Vraie si le joueur Ã©crit quitter ou faux si il ya un second mot
     */
    private boolean quit(final Command pInstruction)
    {
        if (pInstruction.hasSecondWord()){
            System.out.println("Quit what?");
            return false;
        }else{
            return true;
        }// else
    }// quit ()
    
    
    /**
     * Permet d'afficher la description complète de la salle dans laquelle nous sommes actuellement
     */
    private void look(Command command) {
        if(command.hasSecondWord())
            System.out.println("I don't know how to look at something in particular yet.");
        else
            System.out.println(this.currentRoom.getLongDescription());
        }
    
        
    /**
     * Permet d'afficher que nous avons mangé
     */
    private void eat() {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
        
    
    /**
     * Permet d'exécuter une commande saisir par l'utilisateur
     * 
     * @param command Commande que l'utilisateur a écrit
     * @return Si la commande saisie arrête le jeu
     */
    private boolean processCommand(final Command command)
    {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean ...");
            return false;
        }
        
        switch(command.getCommandWord().toLowerCase()) {
            case "go":
                goRoom(command);
                return false;
            case "quit":
                return this.quit(command);
            case "help":
                printHelp();
                return false;
            case "look":
                look(command);
                return false;
            case "eat":
                eat();
                return false;
            default:
                System.out.println("Unknown command !");
                return false;
        }
    }
    
    
    /**
     * Permet de lancer la boucle principale du jeu
     */
    public void play ()
    {
        printWelcome();
        boolean vFinished = false;
        Command vCommande;
        
        while (!vFinished){
            vCommande = this.parser.getCommand();
            vFinished = processCommand(vCommande);
        }
        System.out.println("Thank you for playing. Good bye.");
    }
}
