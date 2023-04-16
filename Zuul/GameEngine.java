import java.util.Stack;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Gaillet Leo
 */
public class GameEngine {

    private Room currentRoom;
    private final Stack<Room> previousRooms = new Stack<Room>();
    private Parser parser;
    private UserInterface userInterface;
    

    /**
     * Constructeur par defaut de la classe Game
     */
    public GameEngine() {
        this.parser = new Parser();
        this.createRooms();
    }
    
    
    /**
     * Affiche les messages lors du dï¿½but du jeu
     */
    private void printWelcome() {
        this.userInterface.println("Welcome to the Enchanted City !");
        this.userInterface.println("");
        this.userInterface.println("You are one of these guardian that protect the city from ennemies. A mean witcher had done something bad to the city");
        this.userInterface.println("Type 'help' if you need help.");
        printLocationInfo();
        if(this.currentRoom.getImageName() != null)
            this.userInterface.showImage(this.currentRoom.getImageName());
    }
    

    private void createRooms() {
        
        // DÃ©finis les diffÃ©rents endroits possible dans le jeu
        Room kingPalace, fortressPrison, fortressDungeon, fortressUnderground, artefactsRoom, portalsRoom,
            fortressYard, fortressEntrance, silverRiver, joyfulAvenue, cascadesOfDiamonds, secretCascadeOfDiamonds,
            forbiddenForest, forbiddenForestCave;

        // Initialise les diffÃ©rentes Rooms
        kingPalace = new Room("The King's Floor. You are certainly appreciated by the King.", "etages-du-roi.jpg");
        fortressDungeon = new Room("The Dungeon of the Fortress. It's a privilege to be here.", "dongeon-du-fort.jpg");
        fortressPrison = new Room("The Prison of the Fortress. It can be some prisoners in there.", "prison-du-fort.jpg");
        fortressUnderground = new Room("The Basement of the Fortress. You may see some bats.", "sous-bassements-du-fort.jpg"); 
        artefactsRoom = new Room("The Artefacts Room. You can feel strange things coming from this place.", "salle-des-artefacts.jpg");
        portalsRoom = new Room("The portal room. Portals may teleport you, even if the way by foot is impossible", "salle-des-portails.jpg");
        fortressYard = new Room("The Fortress Yard. See how beautiful are flowers around here.", "jardin-du-fort.jpg");
        fortressEntrance = new Room("The Entrance of The Fortress. Why is this door so tall ?", "entree-du-fort.jpg");
        silverRiver = new Room("The Silver River. Is this silver or just the snow falling on it ?", "riviere-d-argent.jpg");
        joyfulAvenue = new Room("The Joyful Avenue. This avenue goes so far away that I can't see the end.", "avenue-de-la-joie.jpg");
        cascadesOfDiamonds = new Room("The Cascades of Diamondstones, The water is so turquoise that I can't see the difference with the color of the diamond", "cascade-aux-diamants.jpg");
        secretCascadeOfDiamonds = new Room("The Secret Place of the Cascade of Diamonds, Who could pretend that under the cascade it would be an door ?", "salle-secrete-cascade-aux-diamants.jpg");
        forbiddenForest = new Room("The Forbidden Forest. The only thing that I can say about this place is it's make me goosebumps", "foret-interdite.jpg");
        forbiddenForestCave = new Room("The Cave of The Forbidden Forest. This place is filled by the Darkness", "grotte-de-la-foret-interdite.jpg");


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
        
        // Définis les différents item dans le jeu
        Item fleur, chaise, epee;
        
        fleur = new Item("Fleur", "Il y en a une dans la salle de début", 1);
        chaise = new Item("Chaise", "Bon, même si elle ne sert à rien, il faut quand même la mettre", 5);
        epee = new Item("Epee", "Qui dit, monde fantastique, dit aussi, épée stylée", 2);
        this.currentRoom.addItem(fleur);
        
        fortressDungeon.addItem(chaise);
        fortressDungeon.addItem(epee);
    }
    
    
    /**
     * Affiche les commandes possibles
     */
    private void printHelp() {
        this.userInterface.println("Your commands are : " + parser.getCommandString());
    }
    
    
    /**
     * Affiche la piï¿½ce actuelle et ses sorties
     */
    private void printLocationInfo() {
        this.userInterface.println(this.currentRoom.getLongDescription());
        if(this.currentRoom.getImageName() != null)
            this.userInterface.showImage(currentRoom.getImageName());
    }
    
    
    /**
     * Permet d'accï¿½der ï¿½ une piï¿½ce selon la direction et affiche la salle oï¿½ nous sommes
     * 
     * @param command Direction dans laquelle aller 
     */
    private void goRoom(final Command command) {
        if (!command.hasSecondWord()){
            this.userInterface.println("Go where ?");
            return;
        }
        
        Room nextRoom = this.currentRoom.getExit(command.getSecondWord());
        
        if (nextRoom == null){
            this.userInterface.println("There is no door !");
            return;
        }
        
        this.previousRooms.push(this.currentRoom);
        this.currentRoom = nextRoom;
        
        printLocationInfo();
    }
    
    
    /**
     * Permet de quitter le jeu
     * 
     * @param command Commande ecrite par l'utilisateur
     * @return Vraie si le joueur Ã©crit quitter ou faux si il ya un second mot
     */
    private boolean quit(final Command command) {
        if (command.hasSecondWord()){
            this.userInterface.println("Quit what?");
            return false;
        }else{
            this.userInterface.println("Thank you for playing.  Good bye.");
            this.userInterface.enable(false);
            return true;
        }// else
    } 
    
    /**
     * Permet d'afficher la description complete de la salle dans laquelle nous sommes actuellement
     */
    private void look(Command command) {
        if(command.hasSecondWord()) {
            String commandWord = command.getSecondWord();
            if(this.currentRoom.getItem(commandWord) != null)
                this.userInterface.println(this.currentRoom.getItem(commandWord).getLongDescription());
            else
                this.userInterface.println("Item not found");
        }
        else
            this.userInterface.println(this.currentRoom.getLongDescription());
    }
     
    /**
     * Permet d'afficher que nous avons mangï¿½
     */
    private void eat() {
        this.userInterface.println("You have eaten now and you are not hungry any more.");
    }

    /**
     * Permet d'executer une commande saisir par l'utilisateur
     * 
     * @param command Commande que l'utilisateur a ecrit
     * @return Si la commande saisie arrete le jeu
     */
    public void interpretCommand(final String rawCommand) {
        this.userInterface.println( "> " + rawCommand );
        Command command = this.parser.getCommand( rawCommand.toLowerCase() );

        if (command.isUnknown()) {
            this.userInterface.println("I don't know what you mean ...");
            return;
        }
        
        switch(command.getCommandWord().toLowerCase()) {
            case "go":
                goRoom(command);
                return;
            case "quit":
                quit(command);
                return;
            case "help":
                printHelp();
                return;
            case "look":
                look(command);
                return;
            case "eat":
                eat();
                return;
            case "back":
                back(command);
                return;
            default:
                this.userInterface.println("Unknown command !");
                return;
        }
    }
    
    public void back(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("Back what ?");
            return;
        }
        
        if(this.previousRooms.size() == 0 || this.previousRooms.peek() == null) {
            this.userInterface.println("There is any previous room !");
            return;
        }
        
        this.currentRoom = this.previousRooms.pop();
        printLocationInfo();
    }
    
    /**
     * Permet de définir une interface utilisateur au moteur du jeu
     */
    public void setGUI(final UserInterface userInterface) {
        this.userInterface = userInterface;
        this.printWelcome();
    }
}
