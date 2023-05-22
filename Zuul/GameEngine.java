import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author Gaillet Leo
 * @version 1.0.0
 */
public class GameEngine {
    
    private Player player;
    private Parser parser;
    private UserInterface userInterface;

    private final HashMap<String, Room> rooms;
    private final HashMap<String, Item> items;

    private int commandsLeft = 100;

    /**
     * Constructeur par defaut de la classe Game
     */
    public GameEngine() {
        this.parser = new Parser();
        this.player = new Player("Arthur", 2);

        this.rooms = new HashMap<String, Room>();
        this.items = new HashMap<String, Item>();

        this.createRooms();
        this.createItems();

        this.initGameStructure();
        
        this.player.setCurrentRoom(this.rooms.get("Fortress Entrance"), false);
    }
    
    /**
     * Permet de définir une interface utilisateur au moteur du jeu
     * @param userInterface Interface utilisateur necessaire au moteur du jeu
     */
    public void setGUI(final UserInterface userInterface) {
        this.userInterface = userInterface;
        this.printWelcome();
    }

    /**
     * Permet d'arrêter le jeu
     */
    public void endGame() {
        this.userInterface.println("Merci d'avoir joué !");
        this.userInterface.enable(false);
    }

    /**
     * Permet de vérifier si la fin du jeu est validée
     */
    public void winGame() {
        final Room portalsRoom = rooms.get("Portals Room");
        if(
            this.player.getCurrentRoom() == portalsRoom
            && portalsRoom.getRoomInventory().hasItem("ArtefactDeDestruction")
        ){
            this.userInterface.println("\n\n");
            this.userInterface.println("Vous avez jeté l'artéfact de destruction dans la salle des portails !");
            this.userInterface.println("Vous avez réussi votre mission ! vous avez réussi à empêcher le sorcier de détruire la forteresse");
            endGame();
        }
    }

    /**
     * Permet d'instancier les differentes salles et les differents objets du jeu
     */
    private void createRooms() {
        
        // Définis les différents endroits possible dans le jeu
        Room[] rooms = new Room[] {
            new Room("King Floor", "You are certainly appreciated by the King.", "etages-du-roi.jpg"),
            new Room("Jail", "It can be some prisoners in there.", "prison-du-fort.jpg"),
            new Room("Dungeon", "It's a privilege to be here.", "dongeon-du-fort.jpg"),
            new Room("Basements", "You may see some bats.", "sous-bassements-du-fort.jpg"),
            new Room("Artefacts Room", "You can feel strange things coming from this place.", "salle-des-artefacts.jpg"),
            new Room("Portals Room", "Portals may teleport you, even if the way by foot is impossible", "salle-des-portails.jpg"),
            new Room("Yard", "See how beautiful are flowers around here.", "jardin-du-fort.jpg"),
            new Room("Fortress Entrance", "Why is this door so tall ?", "entree-du-fort.jpg"),
            new Room("Silver River", "Is this silver or just the snow falling on it ?", "riviere-d-argent.jpg"),
            new Room("Joyful Avenue", "This avenue goes so far away that I can't see the end.", "avenue-de-la-joie.jpg"),
            new Room("Cascades of Diamondstones", "The water is so turquoise that I can't see the difference with the color of the diamond", "cascade-aux-diamants.jpg"),
            new Room("Secret Place", "Who could pretend that under the cascade it would be an door ?", "salle-secrete-cascade-aux-diamants.jpg"),
            new Room("Forbidden Forest", "The only thing that I can say about this place is it's make me goosebumps", "foret-interdite.jpg"),
            new Room("Forbidden Forest's Cave", "This place is filled by the Darkness", "grotte-de-la-foret-interdite.jpg")
        };

        for(Room room : rooms) this.rooms.put(room.getName(), room);
    }

    /**
     * Permet de créer les items et de les ajouter dans la liste des items disponibles
     */
    private void createItems() {

        Item[] items = new Item[] {
            new Item("Cookie", "Cookie magique qui permet d'avoir une capacité de stockage plus importante", 1),
            new Beamer("Déphaseur", "Permet de se téléporter au début du jeu", 3, true),
            new Item("CléDePrison", "La clé du dongeon qui permet d'ouvrir la porte du de la prison", 1),
            new Item("CléDuRoi", "La clé qui permet d'accéder à la chambre du roi", 1),
            new Beamer("ArtefactDeTéléportation", "Objet qui permet de se téléporter", 2, false),
            new Item("ArtefactDeDestruction", "Objet qui permet de détruire une salle", 1)
        };

        for(Item item : items) this.items.put(item.getName(), item);

    }

    /**
     * Permet de créer la structure du jeu par rapport aux salles disponibles ainsi que les items disponibles
     */
    private void initGameStructure() {
        
        rooms.get("King Floor").setExit("down", this.rooms.get("Dungeon"));

        rooms.get("Jail").setExit("east", rooms.get("Dungeon"));
        
        rooms.get("Dungeon").setExit("west", rooms.get("Jail"));
        rooms.get("Dungeon").setExit("up", rooms.get("King Floor"));
        rooms.get("Dungeon").setExit("down", rooms.get("Basements"));
        rooms.get("Dungeon").setExit("south", rooms.get("Yard"));
        rooms.get("Dungeon").getRoomInventory().addItems(
            items.get("CléDePrison"), items.get("CléDuRoi")
        );

        rooms.get("Basements").setExit("up", rooms.get("Dungeon"));

        rooms.get("Artefacts Room").setExit("east", rooms.get("Yard"));

        rooms.get("Yard").setExit("south", rooms.get("Fortress Entrance"));
        rooms.get("Yard").setExit("north", rooms.get("Dungeon"));
        rooms.get("Yard").setExit("west", rooms.get("Artefacts Room"));
        rooms.get("Yard").setExit("east", rooms.get("Portals Room"));

        rooms.get("Portals Room").setExit("west", rooms.get("Yard"));
        rooms.get("Portals Room").getRoomInventory().addItem(
            items.get("ArtefactDeTéléportation")
        );

        rooms.get("Fortress Entrance").setExit("north", rooms.get("Yard"));
        rooms.get("Fortress Entrance").setExit("south", rooms.get("Joyful Avenue"));
        rooms.get("Fortress Entrance").getRoomInventory().addItem(
            items.get("Cookie")
        );

        rooms.get("Silver River").setExit("east", rooms.get("Joyful Avenue"));

        rooms.get("Joyful Avenue").setExit("north", rooms.get("Fortress Entrance"));
        rooms.get("Joyful Avenue").setExit("east", rooms.get("Cascades of Diamondstones"));
        rooms.get("Joyful Avenue").setExit("west", rooms.get("Silver River"));
        rooms.get("Joyful Avenue").setExit("south", rooms.get("Forbidden Forest"));

        rooms.get("Cascades of Diamondstones").setExit("west", rooms.get("Joyful Avenue"));
        rooms.get("Cascades of Diamondstones").setExit("down", rooms.get("Secret Place"));

        rooms.get("Forbidden Forest").setExit("north", rooms.get("Joyful Avenue"));
        rooms.get("Forbidden Forest").setExit("down", rooms.get("Forbidden Forest Cave"));

        rooms.get("Secret Place").setExit("up", rooms.get("Cascades of Diamondstones"));

        rooms.get("Forbidden Forest Cave").setExit("up", rooms.get("Forbidden Forest"));
        rooms.get("Forbidden Forest Case").getRoomInventory().addItem(
            items.get("ArtefactDeDestruction")
        );
        

        
    }
    
    /**
     * Affiche les messages lors du debut du jeu
     */
    private void printWelcome() {
        this.userInterface.println("Welcome to the Enchanted City !");
        this.userInterface.println("");
        this.userInterface.println("You are one of these guardian that protect the city from ennemies. A mean witcher had done something bad to the city, take the destruction artefact and throw it in the portals room");
        this.userInterface.println("Type 'help' if you need help.");
        printLocationInfo();
    }
    
    /**
     * Affiche les commandes possibles
     */
    private void printHelp() {
        this.userInterface.println("Your commands are : " + parser.getCommandString());
    }
    
    
    /**
     * Affiche la piece actuelle et ses sorties
     */
    private void printLocationInfo() {
        this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
        if(this.player.getCurrentRoom().getImageName() != null)
            this.userInterface.showImage(player.getCurrentRoom().getImageName());
    }

    private void printMovesLeft() {
        this.userInterface.println("You have " + this.commandsLeft + " commands lefts !");
    }

    private void printGameOver() {
        this.userInterface.println("----- ! GAME OVER ! -----");
    }

    /**
     * Permet d'executer une commande saisie par l'utilisateur
     * @param rawCommand Commande que l'utilisateur a saisie
     */
    public void interpretCommand(final String rawCommand) {
        this.userInterface.println( "> " + rawCommand );
        
        Command command = this.parser.getCommand(rawCommand.toLowerCase());
        CommandWord commandWord = this.parser.getCommandWords().getCommand(command.getCommandWord());
        
        boolean successCommand = true;

        switch(commandWord) {
            case GO:
                successCommand = goRoom(command);
                break;
            case QUIT:
                successCommand = quit(command);
                return;
            case HELP:
                successCommand = help(command);
                break;
            case LOOK:
                successCommand = look(command);
                break;
            case EAT:
                successCommand = eat(command);
                break;
            case BACK:
                successCommand = back(command);
                break;
            case TEST:
                successCommand = test(command);
                break;
            case TAKE:
                successCommand = take(command);
                break;
            case DROP:
                successCommand = drop(command);
                break;
            case INVENTORY:
                successCommand = inventory(command);
                break;
            case CHARGE:
                successCommand = charge(command);
                break;
            case FIRE:
                successCommand = fire(command);
                break;
            default:
                this.userInterface.println("Unknown command !");
                break;
        }

        if(successCommand) {
            this.commandsLeft--;
            printMovesLeft();
            if(this.commandsLeft == 0) {
                printGameOver();
                endGame();
            }
        }
    }
    
    
    /**
     * Permet d'acceder a une piece selon la direction et affiche la salle ou nous sommes
     * @param command Direction dans laquelle le joueur souhaite aller
     * @return Si la commande a fonctionné
     */
    private boolean goRoom(final Command command) {
        if(!command.hasSecondWord()){
            this.userInterface.println("Go where ?");
            return false;
        }
        
        final Room nextRoom = this.player.getCurrentRoom().getExit(command.getSecondWord());
        
        if(nextRoom == null){
            this.userInterface.println("There is no door !");
            return false;
        }

        final Room currentRoom = this.player.getCurrentRoom();

        if(nextRoom.isLocked()) {
            if(nextRoom.tryToUnlock(this.player.getPlayerInventory()))
                this.player.getPlayerInventory().removeItem(nextRoom.getKey());
            else {
                this.userInterface.println("Vous n'avez pas la clé nécessaire pour ouvrir la porte !");
                return false;
            }
        }
        
        this.player.setCurrentRoom(nextRoom, true);
        printLocationInfo();

        if(!nextRoom.hasExit(currentRoom)) {
            this.userInterface.println("You can't go back ! You are trapped !");
            this.player.clearPreviousRooms();
        }

        return true;
    }

    public boolean help(final Command command) {
        this.printHelp();
        return true;
    }
    
    
    /**
     * Permet de quitter le jeu
     * @param command Commande ecrite par l'utilisateur
     * @return Si la commande a fonctionné
     */
    public boolean quit(final Command command) {
        if (command.hasSecondWord()){
            this.userInterface.println("Quit what?");
            return false;
        }
        
        this.userInterface.println("Thank you for playing.  Good bye.");
        this.endGame();
        return true;
    } 
    
    /**
     * Permet d'afficher la description complete de la salle dans laquelle nous sommes actuellement
     * @param command Commande look
     * @return Si la commande a fonctionné
     */
    public boolean look(Command command) {
        if(command.hasSecondWord()) {
            String commandWord = command.getSecondWord();
            this.userInterface.println(this.player.getCurrentRoom().lookForItem(commandWord));
        }
        else
            this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
        return true;
    }
     
    /**
     * Permet d'afficher que nous avons mange
     * @param command Commande eat
     * @return Si la commande a fonctionné
     */
    public boolean eat(final Command command) {
        if(command.hasSecondWord()) {
            if(!command.getSecondWord().equalsIgnoreCase("cookie")) {
                this.userInterface.println("You can't eat something else than cookie.");
                return false;
            }
        }

        if(!this.player.getPlayerInventory().hasItem("cookie")) {
            this.userInterface.println("You haven't cookie in your inventory !");
            return false;
        }

        final Item cookie = this.player.getPlayerInventory().getItem("cookie");
        this.player.getPlayerInventory().removeItem(cookie);
        this.player.setMaxWeight(this.player.getMaxWeight() * 2);

        this.userInterface.println("You have eaten " + cookie.getName() + " ! You are able to carry 2 times more !");
        return true;
    }
    
    /**
     * Permet de retourner dans la salle precedente
     * @param command Commande back
     */
    public boolean back(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("Back what ?");
            return false;
        }
        
        if(!this.player.hasPreviousRoom()) {
            this.userInterface.println("There is any previous room !");
            return false;
        }
        
        this.player.goPreviousRoom();
        printLocationInfo();
        return true;
    }
    
    
    /**
     * Permet de tester un ensemble de commandes enregistrées dans un fichier et les exécute
     * @param command Commande test lors de l'exécution
     * @return Si la commande a fonctionné
     */
    public boolean test(final Command command) {

        if(!command.hasSecondWord()) {
            this.userInterface.println("You need to choose a file to test commands !");
            return false;
        }
        
        File file = new File(command.getSecondWord() + ".txt");

        if(!file.exists()) {
            this.userInterface.println("file named " + file.getName() + " is not found !");
            return false;
        }

        try {
            Scanner commandScanner = new Scanner(file);

            while(commandScanner.hasNextLine()) {
                String rawCommand = commandScanner.nextLine();
                this.interpretCommand(rawCommand);
            }

            commandScanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            this.userInterface.println("Unable to read file " + file.getName() + "!");
            return false;
        }

        return true;
    }
    
    /**
     * Permet d'afficher la réponse du jeu quand le joueur veut récupérer un objet dans une piéce
     * @param command Commande "take" au moment de l'exécution
     * @return Si la commande a fonctionné
     */
    public boolean take(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("Take what ?");
            return false;
        }
        else {
            this.userInterface.println(this.player.takeItem(command.getSecondWord()));
            return true;
        }
    }   
    
    /**
     * Permet d'afficher la réponse du jeu quand le joueur veut déposer un objet dans la piéce
     * @param command Commande "drop" que le joueur a saisi
     * @return Si la commande a fonctionné
     */
    public boolean drop(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("Drop what ?");
            return false;
        }
        else {
            this.userInterface.println(this.player.dropItem(command.getSecondWord()));
            return true;
        }
    }
    
    /**
     * Permet d'afficher l'inventaire du joueur
     * @param command Commande "inventory" pour afficher la commande
     * @return Si la commande a fonctionné
     */
    public boolean inventory(final Command command) {
        if(command.hasSecondWord()) {
            this.userInterface.println("I dont understand what do you want...");
            return false;
        }

        this.userInterface.println("My Inventory : " + this.player.getPlayerInventory().getStringContent());
        return true;
    }

    /**
     * Permet de charger un objet
     * @param command Commande "charge" pour charger un objet
     * @return Si la commande a fonctionné
     */
    public boolean charge(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("What do you want to charge ?");
            return false;
        }
        
        Item item = this.player.getPlayerInventory().getItem(command.getCommandWord());

        if(item == null) {
            this.userInterface.println("Vous n'avez aucun item qui s'appelle " + command.getCommandWord() + " dans votre inventaire");
            return false;
        }

        if(item instanceof Beamer) {
            Beamer beamer = (Beamer) item;
            beamer.charge(this.player, this.player.getCurrentRoom());

            if(beamer.isCharged(this.player)) {
                this.userInterface.println("Votre téléporteur est déjà chargé !");
                return false;
            }

            if(beamer.isUsed()) {
                this.userInterface.println("Le téléporteur que vous portez est déjà utilisé et non réutilisable !");
                return false;
            }

            this.userInterface.println("Vous avez chargé le téléporteur");
            return true;
        }

        this.userInterface.println("Votre objet n'est pas un appareil qui peut être chargé !");
        return false;

    }

    /**
     * Permet d'exécuter un objet
     * @param command Commande "fire" pour exécuter un objet
     * @return Si la commande a fonctionné
     */
    public boolean fire(final Command command) {
        if(!command.hasSecondWord()) {
            this.userInterface.println("What do you want to fire ?");
            return false;
        }
        
        Item item = this.player.getPlayerInventory().getItem(command.getCommandWord());

        if(item == null) {
            this.userInterface.println("Vous n'avez aucun item qui s'appelle " + command.getCommandWord() + " dans votre inventaire");
            return false;
        }

        if(item instanceof Beamer) {
            Beamer beamer = (Beamer) item;

            if(!beamer.isCharged(player)) {
                this.userInterface.println("Votre téléporteur n'est pas chargé");
                return false;
            }

            final Room teleportedRoom = beamer.fire(this.player);

            player.setCurrentRoom(teleportedRoom, false);
            player.clearPreviousRooms();

            this.userInterface.println("Vous avez chargé le téléporteur qui vous a téléporté");
            return true;
        }

        this.userInterface.println("Votre objet n'est pas un appareil qui peut être chargé !");
        return false;

    }

}
