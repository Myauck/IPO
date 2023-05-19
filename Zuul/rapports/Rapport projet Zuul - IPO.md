# Compte Rendu d'IPO

## Informations

<u>Nom du projet</u> : **Les gardiens de la cité enchantée**

<u>Auteur</u> : **Léo Gaillet** (E1-5)

<u>Scénario détaillé</u> : Dans un monde fantastique peuplé de créatures magiques, la cité enchantée était autrefois un lieu de paix et de prospérité. Cependant, un groupe maléfique de sorciers a lancé un sortilège qui a plongé la cité dans l'obscurité et la destruction.

Un jeune héros a été choisi pour devenir un gardien de la cité enchantée. Les gardiens sont des guerriers magiques qui ont juré de protéger la cité et ses habitants, et de combattre les forces du mal qui menacent l'équilibre de l'univers.

Le joueur doit explorer la cité enchantée et ses environs pour trouver des alliés, des armes magiques et des artefacts anciens qui peuvent aider à défaire le sortilège maléfique. Le joueur doit également affronter des ennemis dangereux, tels que des créatures magiques corrompues, des soldats maléfiques et des sorciers puissants.

Au fil de l'aventure, le joueur découvre des indices sur l'identité des sorciers maléfiques et leur plan diabolique. Les gardiens doivent alors rassembler leurs forces et lutter pour sauver la cité enchantée et ramener la paix dans le monde fantastique.

<u>Carte du jeu</u> :

![](https://raw.githubusercontent.com/Myauck/IPO/master/Zuul/rapports/diagrams/rooms.drawio.png)

<u>Détails des lieux</u> :

- **Etages du roi** : "The King's Floor. You are certainly appreciated by the King."
- **Donjon du Fort** : "The Dungeon of the Fortress. It's a privilege to be here." 
- **Prison du Fort** : "The Prison of the Fortress. It can be some prisoners in there."
- **Sous-sol du Fort** : "The Basement of the Fortress. You may see some bats."
- **Salle des Artéfacts** : "The Artefacts Room. You can feel strange things coming from this place."
- **Salle des Portails** : "The portal room. Portals may teleport you, even if the way by foot is impossible"
- **Cours du Fort** : "The Fortress Yard. See how beautiful are flowers around here."
- **Entrée du Fort** : "The Entrance of The Fortress. Why is this door so tall ?"
- **La Rivière d'Argent** : "The Silver River. Is this silver or just the snow falling on it ?"
- **L'Avenue de la Bonté** : "The Joyful Avenue. This avenue goes so far away that I can't see the end."
- **La Forêt Interdite** : "The Forbidden Forest. The only thing that I can say about this place is it's make me goosebumps"
- **La Grotte de la Forêt Interdite** : "The Cave of The Forbidden Forest. This place is filled by the Darkness"
- **Les Cascades de Diamants** : "The Cascades of Diamonds, The water is so turquoise that I can't see the difference with the color of the diamond"
- **Salle secrète de la Cascade de Diamants** : "The Secret Place of the Cascade of Diamonds, Who could pretend that under the cascade it would be an door ?"

<u>Items disponibles</u> : [SOON]

<u>Personnages</u> :

- Le héro

- [SOON]

<u>Situations gagnantes</u> : Quand le héros tue le grand nécromancien ET libère la cité de son sortilège

<u>Situations perdantes</u> : Si le joueur meurt, si la phase principale du sortilège maléfique commence, si un sorcier touche le joueur avec une attaque "attrape rêve" qui l'enferme dans un rêve sans fin.

### Partie exercices

<hr>

###### Exercice 7.6

Dans `Room.java` Nous avons cette procédure:

```java
/**
 * Permet de définir les différentes sorties de la salle instanciée
 * 
 * @param northRoom Salle se trouvant au Nord de cette salle
 * @param southRoom Salle se trouvant au Sud de cette salle
 * @param eastRoom Salle se trouvant à l'Est de cette salle
 * @param westRoom Salle se trouvant à l'Ouest de cette salle
 */
public void setExits(final Room northRoom, final Room southRoom, final Room eastRoom, final Room westRoom) {
    this.northRoom = northRoom;
    this.southRoom = southRoom;
    this.eastRoom = eastRoom;
    this.westRoom = westRoom;
}
```

Ajoutons quelques éléments pour augmenter notre nombre de sorties possibles:

```java
public Room {

    // [...]

    public Room upRoom;
    public Room downRoom;

    // [...]

    public void setExits(final Room northRoom, final Room southRoom, final Room eastRoom, final Room westRoom, final Room upRoom, final Room downRoom) {
        this.northRoom = northRoom;
        this.southRoom = southRoom;
        this.eastRoom = eastRoom;
        this.westRoom = westRoom;
        this.upRoom = upRoom;
        this.downRoom = downRoom;
    }

}
```

<u>Explications</u> : 

Nous remarquons que nous avons une procédure qui à besoin maintenant de 6 paramètres, car ces 6 paramètres représentent les 6 sorties possibles. Notre code ne permet donc pas de dynamiser le nombre de sorties possible.

Et puisque nous avons 6 paramètre dans ce programme, nous devons forcément modifier notre procédure privée `createRooms()` dans le fichier `Game.java` puisque maintenant, il n'y a plus 4 sorties mais 6 sorties.

Dans la procédure `createRooms()` dans  `Game` remplaçons les instructions de la manière suivante:

```java
fortressEntrance.setExits(fortressYard, joyfulAvenue, null, null);
// maSortie     .setExits(Nord,         Sud,          Est,  Ouest);
```

par:

```java
fortressEntrance.setExits(fortressYard, joyfulAvenue, null,   null,  null, null);
// maSortie     .setExits(Nord,         Sud,          Est,    Ouest, Haut, Bas);
```

<hr>

###### Exercice 7.7

Dans la classe `Room` créons une méthode `getExitString()`

```java
/**
 * Permet de récupérer l'ensemble des sorties possible de la salle instanciée
 *
 * @return Ensemble des sorties possible
 */
public String getExitString() {
   String exits = "Available Exits : ";

   if(this.northExit != null) exits += "north ";
   if(this.southExit != null) exits += "south ";
   if(this.eastExit != null) exits += "east ";
   if(this.westExit != null) exits += "west ";

   // ainsi que les deux nouvelles sorties
   if(this.upExit != null) exits += "up ";
   if(this.downExit != null) exits += "down";

   return exits;
}
```

<hr>

###### Exercice 7.8

Dans le fichier `Room.java` Nous pouvons ajouter/remplacer :

```java
import java.util.HashMap;

public class Room {

    // [...]

    private final HashMap<String, Room> exits = new HashMap<String, Room>();   

    /**
     * Setter qui permet de définir une salle en fonction de la direction de sortie
     * Cette procédure n'est pas sensible à la casse ! 
     *
     * @param direction Direction de la sortie qu'on souhaite modifier
     * @param exitRoom Salle qui va être définie comme sortie à la direction indiquée
     */
    public void setExit(final String direction, final Room exitRoom) {
        // Vérifie si la salle n'est pas nul, ça reviendrait à dire
        // qu'on a pas de salle en argument
        if(exitRoom != null)
            return this.exits.get(direction.toLowerCase());
    }

    /**
     * Getter qui récupère la salle d'une sortie en fonction de la direction
     * de sortie. Cette procédure n'est pas sensible à la casse ! 
     *
     * @param direction Direction de la sortie qu'on cherche
     * @return Salle se trouvant à la sortie recherchée
     */
    public Room getExit(final String direction) {
        return this.exits.get(direction.toLowerCase());
    }

    // [...]
}
```

Ce qui permet de rendre plus modulable nos sorties et ainsi permettre à nos salles de ne pas dépendre uniquement de 6 sorties mais en définir une infinité et les dynamiser (*à condition que la RAM suive aussi*) 

<u>Explications</u> : 

La fonction `toLowerCase()` permet de remplacer les caractères en majuscules dans la chaîne et les mettre en minuscule, permettant ainsi d'empêcher de créer deux directions qui sont similaires (*par exemple East, EaST et eAsT ici appartiendraient à la même direction : east*)

<hr>

###### Exercice 7.8.1

Pour ajouter dans le scénario le déplacement vertical, nous avons ajouter les deux directions nécessaire : `up` et `down`

Pour les représenter, j'ai décidé de définir leur direction verticale, j'ai représenté leurs flèches sous forme de pointiés : 

- **up** : flèche en pointillés qui commence vers la droite et pars vers la gauche OU commence vers le bas pour aller vers le haut (par rapport à la position relative de la salle)
- **down** : flèche en pointillés qui commence vers la gauche et pars vers la droite OU commencer vers le haut pour aller vers le bas (par rapport à la position relative de la salle)

###### Exercice 7.9

Maintenant que nous avons dynamiser l'ajout de sorties dans notre classe `Room` Nous devons maintenant récupérer les sorties disponibles de ces sorties

Dans la classe `Room` nous pouvons remplacer le contenu de la méthode `getExitString`:

```java
/**
 * Getter qui récupère les sorties disponibles dans la salle instanciée
 * 
 * @return Chaîne comportant la liste des sorties disponibles
 */
public String getExitString() {
    String availableExits = "Available Exits : ";
    for(String keys : this.exits.keySet())
        availableExits += keys + " ";
    return availableExits;
}
```

<u>Explications</u> : 

`this.exists.keySet()` permet de récupérer un `Set<T>`  (un ensemble) avec `T`le type des variables de référence en entrée du "dictionnaire". En effet, les HashMap sont un peu comme des dictionnaires (car clé => valeur). Ici la Map à une clé de type **`String`** (*la direction*) et renvoie une valeur de type **`Room`** (la salle en sortie) car nous avons écrit `HashMap<String, Room>`.

<hr>

###### Exercice 7.10.1

*Documentation Java complètée*

<hr>

###### Exercice 7.10.2

*Documentation Java générée*

<hr>

###### Exercice 7.11

Dans notre classe `Room`, pour ajouter une description complète de notre salle, nous allons ajouter une méthode nommée `getLongDescription()` permettant de récupérer les informations de notre salle :

```java
/**
 * Permet de récupérer l'intégralité des informations de notre salle:
 * La description de notre salle ainsi que les sorties disponible
 *
 * @return Description complète de la salle instanciée
 */
public String getLongDescription() {
    return "You are in " + this.getDescription() + "\n" + this.getExitString();
}
```

<hr>

###### Exercice 7.12

![](https://raw.githubusercontent.com/Myauck/IPO/master/Zuul/rapports/diagrams/diagrammes-programme.drawio.png)

<hr>

###### Exercice 7.13

*Comment change le diagramme des objets instanciés pendant au moment de l'exécution de la commande go ?*

Lorsque nous regardons plus attentivement les étapes du programme quand on entre la commande "go" nous savons déjà que nous avons récupéré une nouvelle instance d'une commande (en effet, dans la classe `Parser` au moment où nous appelons `getCommand()`, nous observons à la fin de la méthode , un `return new Command(parametre1, parametre2);` ce qui montre bien que nous avons un nouvel objet de type `Command`.

qui dit commande "go" dit appel de la procédure `goRoom()` Nous pouvons observer que chaque fois que nous appelons `goRoom()`, nous définissons une nouvelle variable locale `vNextRoom` (celle-ci sera détruite une fois la méthode finie). Or tout ce que nous faisons c'est récupérer une salle qui se trouve stockée elle-même dans la salle où nous sommes actuellement.

<hr>

###### Exercice 7.14

Dans la classe `Game` ajoutons une nouvelle procédure :

```java
/**
 * Permet d'afficher la description complète de la salle dans laquelle nous sommes actuellement
 */
private void look(Command command) {
    if(command.hasSecondWord()) 
        System.out.println("I don't know how to look at something in particular yet");
    else
        System.out.println(this.currentRoom.getLongDescription());
}
```

Cette méthode, si nous observons bien, elle permet de récupérer la description complète de la salle dans laquelle nous somme et de nous l'afficher.

Et puisqu'il s'agit d'une nouvelle commande, il faut l'ajouter dans le tableau de commandes disponibles dans `CommandWords` et dans les commandes à vérifier dans la procédure `processCommand` dans la classe `Game`.

Nous avons donc comme procédure `processCommand `:

```java
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
        default:
            System.out.println("Unknown command !");
            return false;
    }
}
```

<hr>

###### Exercice 7.15

Faisons la même chose pour la procédure `eat()`, ajoutons là dans la liste dans `CommandWords` et dans la méthode `processCommand()` :

Ecrivons la procédure :

```java
/**
 * Permet d'afficher qu'on a mangé
 */
private void eat() {
    System.out.println();
}
```

Dans la méthode `processCommand()` :

```java
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
```

<hr>

Pour dynamiser et rendre plus accessibles nos commandes, nous pouvons afficher l'ensemble des commandes sous forme de boucle.

Puisque les commandes enregistrées se trouvent dans la classe, nous allons l'écrire dans la classe `CommandWords` :

```java
/**
 * Permet d'afficher l'ensemble des commandes enregistrées.
 */
public void showAll() {
    for(String command : this.registeredCommands) {
        System.out.print(command + " ");
    }
    System.out.println("");
}
```

Faire la liaison de cette méthode dans `Parser`

```java
/**
 * Permet d'afficher les commandes disponibles
 */
public void showCommands() {
   this.commands.showAll();
}
```

Et appeler cette procédure dans `printHelp()` dans `Game`

```java
/**
 * Permet d'afficher la liste d'aide pour le joueur
 */
private void printHelp() {
    System.out.println("Available Commands : ");
    this.parser.showCommands();
}
```

<u>Explications</u> : la boucle `foreach` est de la forme `for(T type : Collection<T>)`  ou de la forme `for(T type : T[])` car il faut noter qu'une classe qui implémente l'interface générique `Collection` n'est pas un tableau de `T` mais peut représenter un tableau de `T`.

<hr>

###### Exercice 7.17

Lorsque nous ajouterons une nouvelle commande, ce ne sera plus à `Game`de les gérer mais bel et bien à `CommandWords` qui enregistrera toutes les commandes possibles.

Donc quand nous ajouterons une nouvelle commande, nous écrirons dans la classe `CommandWords` dans son constructeur : 

`this.registeredCommands[X] = "ma_nouvelle_commande";` 

avec `X` qui correspond à l'indice suivant le dernier a être utilisé pour stocker une commande.

<hr>

###### Exercice 7.18

Pour ajouter la liste des commandes, nous devons tout d'abord placer le code au bon endroit, ici, puisqu'il s'agit de récupérer des commandes, le mieux c'est de l'intégrer dans le fichier `CommandWords.java` où se trouve la classe `CommandWords`.

```java
/**
 * Permet de récupérer l'ensemble des commandes disponibles dans le jeu
 * @return Commandes disponibles
 */
public String getCommandList() {
    String commands = "";
    for(String command : registeredCommands) {
        commands += command + " ";
    }
    return commands;
}
```

On se permet de modifier aussi dans le fichier `Parser`. On remplace :

```java
public void showCommands() {
    this.commands.showAll();
}
```

Par

```java
public CommandWords getCommandWords() {
    return this.commands;
}
```

Et dans `Game` on modifie :

```java
/**
 * Affiche les commandes possibles
 */
private void printHelp()
{
    System.out.println("Your commands are :");
    // On commente cette instruction : parser.showCommands();
    System.out.println(parser.getCommandWords().getCommandList()); // On ajoute celle-ci
}
```

<hr>

###### Exercice 7.18.1

Les différences qu'il y a entre `zuul-better` et le `zuul-bad` sont multiples :

- Dans la classe `Room`, il y a un nouveau paramètre demandé: `String imageName` s'agit certainement d'une image qu'on va devoir charger pour représenter la salle instanciée. Il y a aussi le getter `String getImageName()`
- Dans la classe `Room` on a accès à présent d'un nouveau getter `String getShortDescription()` qui permet probablement, comme son nom l'indique, de récupérer une petite description de la salle. 
- Dans la classe `Parser`, nous n'utilisons plus l'interface `Scanner` mais un `StringTokenizer` c'est à dire qu'on aura, probablement, plus à taper sur le clavier pour entrer les différentes commandes.
- On remarque aussi que toutes les sorties (affichage de messages) c'est à dire les appels de procédure `System.out.println(msg);` sont désormais utilisées que dans la classe `Game`. Il s'agit donc d'un refactorisation des appels de différentes fonctions. 

<hr>

###### Exercice 7.18.2

Le `StringBuilder` est une classe qui est un utilitaire pour les chaînes de caractères, un peu comme `Arrays` pour les listes.
`StringBuilder` a donc, logiquement, plusieurs fonctionnalités. Nous pouvons par exemple modifier l'exercice [7.18](#Exercice 7.18) en ajoutant les `StringBuilder` :

On importe avec `import java.util.StringBuilder;`.

```java
/**
 * Permet de récupérer l'ensemble des commandes disponibles dans le jeu
 * @return Commandes disponibles
 */
public String getCommandList() {
    StringBuilder strBuilder = new StringBuilder();
    for(String command : registeredCommands) {
        strBuilder.append(command);
        strBuilder.append(" ");
    }
    return strBuilder.toString().trim();
}
```

<hr>

###### Exercice 7.18.3

###### Exercice 7.18.4

###### Exercice 7.18.5 OPTIONNEL TEMPORAIREMENT

<hr>

###### Exercice 7.18.6

Les classes commençant par `J` (6) :

- `javax.swing.JFrame` correspond au cadre de la fenêtre, la barre de menu, le titre, la taille de la fenêtre, etc...
- `javax.swing.JTextField` correspond à une entrée où l'utilisateur pourra envoyer des informations au programme, un peu comme un `<input>` en HTML, dans notre situation il s'agit d'un champ pour les commandes.
- `javax.swing.JTextArea` correspond à une large zone de texte, éditable ou non. Ici, cela correspond à l'affichage de la console, les informations des différentes sorties des commandes du jeu
- `javax.swing.JLabel` correspond à un label, un objet, mais qui ne peut pas être modifiable par l'utilisateur. Ici cela correspondra à un endroit pour afficher nos images
- `javax.swing.JPanel` correspond à l'interface qui permettra de récupérer les évènements que le joueur aura appelé. Le `JPanel` peut être comparé au contenu du `JFrame` c'est à dire l'intérieur du cadre de notre fenêtre.
- `javax.swing.JScrollPane` correspond à la barre qui permet de faire défiler une zone qui possède un gros contenu. Ici ca correspondra à l'historique des entrées / sorties des commandes de l'utilisateur.

<hr>

###### Exercice 7.18.7

`addActionListener();` est une fonction appartenant à l'interface `Component` qui permet d'ajouter un composant qui hérite de `Component` dans une liste d'éléments pouvant être appelé lors d'une action de l'utilisateur (touche tapée, clic sur un élément de l'interface, etc...) et donc d'appelé les éléments enregistrés selon les différents évènements.

<hr>

###### Exercice 7.18.8

Pour ajouter le bouton Help nous devons importer la classe `javax.swing.JButton` qui permet d'utiliser les bouton dans notre interface et ajoutons comme attribut `private JButton helpButton;`

dans la fonction `createGUI()` dans la classe `UserInterface` ajoutons

```java
private void createGUI() {
    // [...]
    this.aImage = new JLabel();

    // ajoutons ceci 
    this.helpButton = new JButton("Help");

    JPanel vPanel = new JPanel();
    // [...]
    this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

    // Et ceci aussi
    this.helpButton.addActionListener(this);
    
    this.aEntryField.addActionListener(this);
    // [...]
    this.aEntryField.requestFocus();
}
```

Et modifions la fonction `actionPerformed()`:

```java
/**
 * Actionlistener interface for entries.
 */
@Override public void actionPerformed( final ActionEvent pE ) 
{
    if(pE.getSource() == this.aEntryField) {
        this.processCommand();
    }
    else if(pE.getSource() == this.helpButton) {
        if(pE.getActionCommand().equalsIgnoreCase("help")) {
            this.aEngine.interpretCommand("help");
        }
    }
}
```

<hr>

###### Exercice 7.20

Pour ajouter un item nous devons d'abord créer une classe Item : 

`Item.java` : 

```java

/**
 * Décrivez votre classe Item ici.
 *
 * @author Gaillet Leo
 * @version 23/04/16
 */
public class Item
{
    private final String name;
    private final String description;
    private final int weight;
    
    /**
     * Constructeur d'objets de classe Item
     */
    public Item(final String name, final String description, final int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getWeight() {
        return this.weight;
    }
}
```

Ensuite dans `Room` ajoutons :

```java

    private Item item;

    public Item getItem() {
        return this.item;
    }
    
    public void setItem(final Item item) {
        this.item = item;
    }
    
    public String getItemString() {
        return this.item == null ? "No item here" : "Available item: " + this.item.getName();
    }
    
```

Et modifions la fonction `getLongDescription()`:

```java
	public String getLongDescription() {
        //BEFORE:
        //return getDescription() + "\n" + getExitsString();
        //AFTER:
        return getDescription() + "\n" + getExitsString() + "\n" + getItemString();
    }
```

<hr>

###### Exercice 7.21

Pour avoir une description détaillée de l'item dans une salle, nous allons créer une nouvelle fonction dans la classe `Item` car il s'agit de la description détaillée de l'Item.

```java
    public String getLongDescription() {
        return "Item name: " + this.name + " is described as \"" + this.description + "\" and costs " + this.weight + " pounds.";
    }
```

Nous devons donc modifier `getItemString()` dans la classe `Room`: 

```java
	public String getItemString() {
        return this.item == null ? "No item here" : "Available item: " + /* this.item.getName(); */ this.item.getLongDescription();
    }
    
```

<hr>

###### Exercice 7.21.1

Pour faire correctement fonctionner la fonction `look()` dans `GameEngine` pour faire en sorte que si un second mot est donné, on puisse afficher l'item disponible dans la salle en question

```java
/**
 * Permet d'afficher la description complete de la salle dans laquelle nous sommes actuellement
 */
private void look(Command command) {
    if(command.hasSecondWord()) {
        String commandWord = command.getSecondWord();
        if(this.currentRoom.getItem() != null) {
            if(this.currentRoom.getItem().getName().equalsIgnoreCase(commandWord)) {
                this.userInterface.println(this.currentRoom.getItemString());
            }
            else
                this.userInterface.println("Item not found");
        }
        else
            this.userInterface.println(this.currentRoom.getItemString());
    }
    else
        this.userInterface.println(this.currentRoom.getLongDescription());
}
```

<hr>

###### Exercice 7.22

Nous allons effectuer quelques modifications dans la classe `Room`:

```java
	/*
	Nous retirons l'attribut
	private Item item;
	
	pour le remplacer par:
	*/	
    private final HashMap<String, Item> items = new HashMap<String, Item>();

    public Item getItem(final String itemName) {
        return this.items.get(itemName);
    }

	/*
	On retire la fonction
	public void setItem(final Item item)
	*/
    
    public void addItem(final Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }
    
    public String getItemString() {
        if(items.size() == 0)
            return "No items here";
        
        StringBuilder itemContent = new StringBuilder();
        for(Item item : items.values()) {
            itemContent.append("\"" + item.getName() + "\": ");
            itemContent.append("\t" + item.getLongDescription() + "\n");
        }
        return "Available items (" + items.size() + ") :";
    }
```

Et nous devons aussi modifier la fonction `look` dans la classe `GameEngine` : 

```java
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
```

<hr>

###### Exercice 7.22.1

Pour avoir la possibilité d'ajouter plusieurs items dans la salle nous pouvons utiliser une `HashMap` ou une `ArrayList` selon la façon dont nous allons chercher les items dans une salle. Ici nous allons chercher les items selon leur nom qui a été défini, il est donc préférable d'utiliser une `HashMap<String, Item>` avec `String` qui correspond au type de la valeur que nous allons entrer dans la `HashMap` pour trouver l'`Item` que nous recherchons.

<hr>

###### Exercice 7.22.2

Nous allons ajouter les items nécessaire à cet exercice (3 items minimum, dont 1 dans la salle de début, et 2 autres dans une seule et même salle).

Dans la classe `GameEngine` dans la fonction qui doit initialiser les différentes salles (`createRooms()`) nous allons ajouter les Items

```java
private void createRooms() {
    // [...]
    
    // Défini la salle de départ (elle était déjà censé être là)
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
```

<hr>

###### Exercice 7.23

Pour ajouter la commande `back` modifions d'abord la fonction `interpretCommand()` dans `GameEngine` :

```java
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
		// Ajoutons-la ici
        case "back":
            back(command);
            return;
        default:
            this.userInterface.println("Unknown command !");
            return;
    }
}

private Room previousRoom;

public void back(final Command command) {
    if(command.hasSecondWord()) {
        this.userInterface.println("Back what ?");
        return;
    }

    if(this.previousRoom == null) {
        this.userInterface.println("There is any previous room !");
        return;
    }

    final Room alternativeRoom = this.previousRoom;
    this.previousRoom = this.currentRoom;
    this.currentRoom = alternativeRoom;
    
    printLocationInfo();
}
```

et dans `goRoom` nous devons ajouter à la toute fin de la fonction :

```java
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

    // Ajoutons ceci
    this.previousRoom = this.currentRoom;
    this.currentRoom = nextRoom;

    printLocationInfo();
}
```

<hr>

###### Exercice 7.26

Pour retourner sur les anciennes salles que nous avons traversé, nous devons faire une Pile (FILO - First In Last Out) pour pouvoir accéder aux anciennes salles au fur et à mesure que nous avançons.

Pour cela nous allons modifier `goRoom` dans la classe `GameEngine`  ainsi que l'attribut par défaut :

```java
	
/*
	Remplaçons
	private Room previousRoom;
	
	par
 */
	private final Stack<Room> previousRooms = new Stack<Room>();

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

    /*
     Remplaçons
	 this.previousRoom = this.currentRoom;

	 par:
	*/
    this.previousRooms.push(this.currentRoom);

    this.currentRoom = nextRoom;

    printLocationInfo();
}
```

De plus nous devons modifier la fonction `back()` : 

```java
public void back(final Command command) {
    if(command.hasSecondWord()) {
        this.userInterface.println("Back what ?");
        return;
    }

    if(this.previousRooms.size() == 0 || this.previousRooms.peek() == null) {
        this.userInterface.println("There is any previous room !");
        return;
    }

    this.currentRoom = this.previousRooms.pop(); // On modifie ça
    printLocationInfo();
}

```

<hr>

###### Exercice 7.26.1

Pour générer les deux Javadocs, nous avons deja besoin de savoir la différence entre ces deux là

- La javadoc dans  `userdoc` fournie par la commande "*javadoc -d userdoc -author -version \*.java*" est celle qui est utile aux personnes de l'exterieur qui souhaite comprendre le comportement du programme 
- La javadoc dans `progdoc` fournie par la commande "*javadoc -d progdoc -author -version -private -linksource \*.java*" est celle qui est utile au programmeur et aux développeurs du projet et permet ainsi de ne pas se perdre dans l'ensemble des fonctions créée.

<hr>

###### Exercice 7.28.1

Pour créer une nouvelle commande `test` nous avons d'abord besoin d'intégrer `"test"` dans notre liste de commandes existantes (définies dans la classe `CommandWords`) :

```java
public class CommandWords
{
    private final String[] registeredCommands;

    public CommandWords() {
        this.registeredCommands = new String[]  {
            "go", "help", "quit", "look", "eat", "back", "test" // <--- On l'ajoute juste là
        };
    }
    
    // [...]
}
```

Ensuite nous devons l'ajouter dans le multi-bloc conditionnel (les `if`, `else if ` *en boucle*) / `switch` dans la fonction `interpretCommand` dans `GameEngine`:

```java
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
        // Nous l'ajoutons juste là
        case "test":
            test(command);
            return;
        default:
            this.userInterface.println("Unknown command !");
            return;
    }
}
```

Or pour que cela fonctionne, nous devons créer une nouvelle fonction (dans `GameEngine` aussi) qui permettra de faire nos différents `tests` demandés :

```java
public void test(final Command command) {

    // Vérifie si la commande n'a pas de second mot
    if(!command.hasSecondWord()) {
        this.userInterface.println("You need to choose a file to test commands !");
        return;
    }
    
    // Dans le cas où la commande à un second mot, on poursuit directement ici
    
    // Défini un nouveau fichier
    File file = new File(command.getSecondWord() + ".txt");

    // Vérifie si le fichier n'existe pas
    if(!file.exists()) {
        this.userInterface.println("file named " + file.getName() + " is not found !");
        return;
    }
    
    // Dans le cas où le fichier existe, on continue ici

    try {
        // Scanne chaque lignes du fichier et les exécute
        Scanner commandScanner = new Scanner(file);

        while(commandScanner.hasNextLine()) {
            String rawCommand = commandScanner.nextLine();
            this.interpretCommand(rawCommand);
        }
    }
    catch (FileNotFoundException e) {
        // Préviens le joueur que une erreur est survenue au lieu d'afficher une exception
        this.userInterface.println("Unable to read file " + file.getName() + "!");
        return;
    }
}
```

<hr>

###### Exercice 7.28.2

Pour créer nos fichier de test de commandes, nous devons aller dans le dossier de notre projet et créer les fichiers nous même, `court.txt`, `visite.txt` et `ideal.txt`, ensuite rouvrir BlueJ, et normalement, nous sommes censé avoir les 3 fichiers automatiquement implémentés dans notre projet.

<hr>

###### Exercice 7.29

Tout d'abord il faut connaître le terme de Refactoring. Le Refactoring est une expression qu'on utiliser pour "factoriser" des fonctions et des classes. Par exemple Pour Refactoring notre classe `GameEngine` en deux classes distinctes, nous devons comprendre tout d'abord quelle fonction doit aller dans laquelle des deux classes, soit celle de `GameEngine` qui comporte les éventuelles actions et commande effectuées lors de la partie, et une autre, `Player` qui contient les différentes variables qui vont influencer le joueur.

Par exemple on peut noter que la fonction `goRoom(final Room room)` prend en compte un système d'affichage, un système de déplacement du joueur et un autre système d'affichage

```java
private void goRoom(final Command command) {
    
    // Condition qui vérifie la commande POUR L'AFFICHAGE
    if (!command.hasSecondWord()){
        this.userInterface.println("Go where ?");
        return;
    }

    Room nextRoom = this.currentRoom.getExit(command.getSecondWord());

    // Conditin qui vérifie si la salle récupérée est nulle POUR L'AFFICHAGE
    if (nextRoom == null){
        this.userInterface.println("There is no door !");
        return;
    }

    // SYSTEME QUI DEPLACE LE JOUEUR et enregistre la position précédente 
    this.previousRooms.push(this.currentRoom);
    this.currentRoom = nextRoom;

    // AFFICHE les informations de la position
    printLocationInfo();
}
```

Or tout ce bloc de code n'a pas forcément besoin d'être déplacé dans la classe `Player`. En effet, la classe va stocker uniquement ce qu'a besoin le joueur, c'est à dire la position actuelle `currentRoom`, ses positions précédentes `previousRooms`, son nom `nom`, et peut être d'autres attributs qui <u>dépendront du joueur</u>. Il faut penser qu'il peut y avoir plusieurs joueurs, par exemple, en multijoueur, les joueurs auront le même affichage dans `GameEngine` mais n'auront pas le même contenu dans leurs variables, deux joueurs différents peuvent être dans deux salles différentes.

C'est donc pour cela que nous allons faire ceci :

```java
public class GameEngine {
    
    /*
    Nous allons supprimer:
    
    private Room currentRoom;
    private Stack<Room> previousRooms;
    
    ---------
    
    Pour ajouter:
    */
    
    private final Player player;
    
    // [...]
    
    public GameEngine() {
        // [...]
        this.player = new Player("Nom de mon joueur");
       	// [...]
        this.createRooms();
    }
}
```

Comme vous pouvez le voir, on initialise `this.player` avant d'appeler la procédure `createRooms();`. En effet, `createRooms` aura besoin de l'attribut `player` pour définir sa salle par défaut. On aurait pu directement définir `this.player = new Player("nom");` directement dans la fonction `createRooms()` Or, par mesure de convention et d'organisation de code, il est toujours préférable d'initialiser les variables dans le constructeur.

Dans cette situation on se retrouve quand même face à plusieurs erreurs : puisque les attributs `currentRoom`, `previousRooms` ont été déplacées et que la classe `Player` n'existe pas. Nous allons y remédier, tout d'abord en créant la classe `Player` :

```java
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
    
}

```

Et grâce à cette classe, nous pouvons modifier quasiment tous les `this.currentRoom` dans la classe `GameEngine` qui provoquent des erreurs par des `this.player.getCurrentRoom()`. Il y aura malgré ça quelques exceptions, comme dans la commande `back()` : 

<u>Remplaçons cette ancienne version de `back`</u> :

```java
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
```

<u>Par</u> :

```java
public void back(final Command command) {
    if(command.hasSecondWord()) {
        this.userInterface.println("Back what ?");
        return;
    }

    if(this.player.hasPreviousRoom()) {
        this.userInterface.println("There is any previous room !");
        return;
    }

    this.player.goPreviousRoom();
    printLocationInfo();
}
```

Ainsi que l'<u>ancienne version de la commande `goRoom`</u> :

```java
private void goRoom(final Command command) {
    if (!command.hasSecondWord()){
        this.userInterface.println("Go where ?");
        return;
    }

    Room nextRoom = this.player.getCurrentRoom().getExit(command.getSecondWord());

    if (nextRoom == null){
        this.userInterface.println("There is no door !");
        return;
    }

    this.player.setCurrentRoom(nextRoom, true);
    /*
    	Le deuxième argument dans la fonction (le 'true') ici, permet d'indiquer
		à la fonction `setCurrentRoom` dans la classe Player de sauvegarder la derniere position
		du joueur pour qu'il puisse revenir en arrière.
     */
   
    printLocationInfo();
}
```

Et finalement pour la fonction `look`, on modifie `this.currentRoom` par `this.player.getCurrentRoom()` :

```java
private void look(Command command) {
    if(command.hasSecondWord()) {
        String commandWord = command.getSecondWord();
        this.userInterface.println(this.player.lookForItem(commandWord));
        return;
    }
    this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
}
```




<hr>

###### Exercice 7.30

Pour créer les nouvelles commandes `take` et `drop` pour que le joueur puisse prendre des items présents ou lâcher des items qui lui appartiennent dans la salle, nous avons tout d'abord besoin de les créer :

Comme à son habitude, nous devons aller dans la classe `CommandWords` pour définir nos nouvelles commandes :

```java
public class CommandWords
{
    private final String[] registeredCommands;

    public CommandWords() {
        this.registeredCommands = new String[]  {
            "go", "help", "quit", "look", "eat", "back", "test", "take", "drop"
        };
    }
    
    // [...]
}
```

Ensuite, ajoutons ces deux nouvelles commande dans le bloc principale des commande à vérifier dans la fonction `interpretCommand()`:

```java
public void interpretCommand(final String rawCommand) {
    // [...]

    switch(command.getCommandWord().toLowerCase()) {
            
         // [...]
            
        case "take":
            take(command);
            return;
        case "drop":
            drop(command);
            return;
            
         // [...]
            
        default:
            this.userInterface.println("Unknown command !");
            return;
    }
}
```

Maintenant, ne reste plus qu'à créer les deux commandes, la première qui sera liée à l'affichage (du coup dans la classe `GameEngine`) et la seconde partie de la commande (qui va s'occuper des contrôles du déplacement de l'item) dans la classe `Player`.

Donc dans la classe `Player` nous ajoutons ceci :

```java
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
```

Attention ! N'oublions pas d'ajouter un attribut de type `Item` dans la classe `Player`:

```java
private Item item;
```

Et dans la classe `GameEngine` nous ajoutons ces deux procédures là :

```java
/**
 * Permet d'afficher la réponse du jeu quand le joueur veut récupérer un objet dans une pièce
 * @param command Commande "take" au moment de l'exécution
 */
public void take(final Command command) {
    if(!command.hasSecondWord()) {
        this.userInterface.println("Take what ?");
        return;
    }
    this.userInterface.println(this.player.takeItem(command.getSecondWord()));
}

/**
 * Permet d'afficher la réponse du jeu quand le joueur veut déposer un objet dans la pièce
 * @param command Commande "drop" que le joueur a saisi
 */
public void drop(final Command command) {
    if(command.hasSecondWord()) {
        this.userInterface.println("Wrong prompt, there is any argument !");
        return;
    }
    this.userInterface.println(this.player.dropItem());
}
```

<hr>

###### Exercice 7.31

La différence entre cet exercice et le précédent, c'est que dans cette situation, nous pouvons prendre plusieurs objet sur nous, ce qui va modifier pas mal notre programme :

Tout vas quasiment se passer dans la classe `Player`, en effet, le traitement se passe sur le joueur, car c'est lui qui subit des modification (son inventaire), donc il s'agit de la classe `Player` qui va être modifiée.

Il y aura aussi la classe `Room` puisqu'elle va aussi subir les modifications liées à son inventaire (par exemple, le joueur prend un item dans la salle, la salle va perdre un item de son inventaire, alors que le joueur va récupérer l'item qui a été pris dans de l'inventaire de la salle).

Tout d'abord retirons cet attribut

```java
private Item item;
```

Et remplaçons le par:

```java
private final HashMap<String, Item> items = new HashMap<String, Item>();
// Sans oublier d'importer avec "import java.util.HashMap";
```

et remplaçons nos deux fonctions `takeItem` et `dropItem` dans `Player` par ça : 

```java
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
```

Et puisque nous avons modifié la signature de `public String dropItem()` par `public String dropItem(final String itemName)` (Nous avons donné un paramètre à notre fonction `dropItem` car maintenant, puisque nous pouvons avoir plusieurs Items dans notre inventaire, notre programme aura besoin de savoir ce que nous voulions jeter comme item de notre inventaire) Nous devons aller dans la classe `GameEngine` pour effectuer quelques modifications (car la procédure `drop`qui se trouve dans `GameEngine` appelle la fonction `dropItem` dans la classe `Player`) :

Dans la classe `GameEngine` modifions alors `drop`: 

```java
public void drop(final Command command) {
    // Nous avons inversé la condition (avec le point d'exclamation '!')
    if(!command.hasSecondWord()) {
        this.userInterface.println("Drop what ?");
        return;
    }
    // De :
    // this.userInterface.println(this.player.dropItem());
    // Par :
    this.userInterface.println(this.player.dropItem(command.getSecondWord()));
}
```

<hr>

###### Exercice 7.31.1

Comme il nous est demandé de créer une classe commune à Player et Room, nous devons comprendre qu'est qu'il peut y avoir en commun avec ces deux classes. On remarque depuis quelques exercices que la classe Player et la classe Room possèdent plusieurs points communs :

- Ils peuvent avoir des items dans une liste d'items
- On peut ajouter ou retirer des éléments de la liste d'items
- La liste des items ne doit pas être accessible de extérieur (car l'attribut est privé)

Nous pouvons créer une classe `ItemList` qui mettra tous ces points commun dans une seule et même classe au lieu de mettre trop de ligne de code répétitives. C'est un peu comme si la classe `ItemList` était la représentation général de notre inventaire dans le jeu,
Il y aura donc un inventaire pour le joueur, et un inventaire pour chaque salle :

```java
import java.util.HashMap;

/**
 * ItemList est une classe dédiée à la gestion d'une collection des
 * Item présent pour dans un joueur sous forme d'inventaire ou 
 * sous forme d'un ensemble de fonctionnalités permettant
 * de manipuler les Items qui se trouvent à l'interieur de celle-ci
 *
 * @author Leo GAILLET
 * @version 20/04/2023
 */
public class ItemList {
    
    private final HashMap<String, Item> items = new HashMap<String, Item>();
    
    public Item getItem(final String itemName) {
        return items.get(itemName.toLowerCase());
    }
    
    public void addItem(final Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }
    
    public void removeItem(final Item item) {
        this.items.remove(item.getName().toLowerCase());
    }
    
    public boolean hasItem(final String itemName) {
        return this.items.containsKey(itemName.toLowerCase());
    }
    
    public boolean isEmpty() {
        return getSize() == 0;
    }
    
    public int getSize() {
        return this.items.size();
    }
    
    public Item[] getContent() {
        Item[] availableItems = new Item[items.size()];
        int i = 0;
        for(Item item : items.values()) {
            availableItems[i] = item;
            i++;
        }
        return availableItems;
    }
    
}
```

 Ainsi, dans la classe `Player`, tout ce qui touche à son inventaire, c'est à dire l'attribut `private final HashMap<String, Item> items;` n'a plus besoin d'être là. On peut directement faire comme ceci :

```java
public class Player {
    
    private final String name;
    private Room currentRoom;
    private final Stack<Room> previousRooms;
    // Remplaçons
    // private final HashMap<String, Item> items;
    // Par:
    private final ItemList items;
    
    public Player(final String name){
        this.name = name;
        this.previousRooms = new Stack<Room>();
        // Initialisons la ItemList
        this.items = new ItemList();
    }
 
    // [...]
    
    public String lookForItem(final String itemName) {
        // Remplaçons "currentRoom.getItem(itemName)" par "currentRoom.getItemList().getItem(itemName)"
        return currentRoom.getItemList().getItem(itemName) != null ? currentRoom.getItemList().getItem(itemName).getLongDescription() : "Item not found";
    }
    
    public String takeItem(final String itemName) {
        // Remplaçons "currentRoom.getItem(itemName)" par "currentRoom.getItemList().getItem(itemName)"
        Item foundItem = currentRoom.getItemList().getItem(itemName);
        if(foundItem == null) {
            return "Item in this room is not found !";
        }
        
        // Ici, à la place de faire this.items.put(...) utilisons directement
        // la fonction proposée dans la classe ItemList
        this.items.addItem(foundItem);
        
        // Pareil ici, on peut directement appeler la fonction proposé dans ItemList dans la salle dans laquelle nous sommes
        this.currentRoom.getItemList().removeItem(foundItem);
        // Si vous ne comprennez pas le bout de code juste au dessus
        // Faites une simple traduction
        // Joueur.laSalleDansLaquelleJeSuis().recupererListeItems().retirerItem(itemTrouvé)
        // Ce qui donne:
        // Retire moi l'item "itemTrouvé" dans la liste des items de la salle, là où le joueur se trouve actuellement
        
        return "You took the item " + foundItem.getName() + " !"; 
    }
    
    public String dropItem(final String itemName) {
        if(!this.items.hasItem(itemName.toLowerCase()))
            return "You have any item named like this on your player !";
        
        Item item = this.items.getItem(itemName.toLowerCase());
        this.currentRoom.getItemList().addItem(item);
        this.items.removeItem(item);
        return "You dropeed the item " + item.getName() + " in the room !"; 
    }
    
}
```

Et procédons de la même manière dans la classe `Room` :

```java
import java.util.HashMap;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling, David J. Barnes, Leo Gaillet
 * @version 1.0 (February 2002) DBMOD:04/04/2008, 2019, 2023
 */
public class Room
{
    private final String description;
    private final HashMap<String, Room> exits = new HashMap<String, Room>();
    private final String imageName;
    private final ItemList items;
    
    public Room(final String description, final String imageName) {
        this.description = description;
        this.imageName = imageName;
        this.items = new ItemList();
    }
    
    public ItemList getItemList() {
        return this.items;
    }
    
    public String getItemString() {
        if(items.isEmpty())
            return "No items here";
        
        StringBuilder itemContent = new StringBuilder();
        for(Item item : items.getContent()) {
            itemContent.append("\"" + item.getName() + "\": ");
            itemContent.append("\t" + item.getLongDescription() + "\n");
        }
        return "Available items (" + items.getSize() + ") : " + itemContent.toString();
    }
}

```

Malgré ça, on aura toujours des erreurs, nous avons oublié une classe importante, qui n'a pas été modifiée, il s'agit de `GameEngine`, 

```java
private void look(Command command) {
    if(command.hasSecondWord()) {
        String commandWord = command.getSecondWord();
        this.userInterface.println(this.player.lookForItem(commandWord));
        return;
    }
    this.userInterface.println(this.player.getCurrentRoom().getLongDescription());
}

private void take(final Command command) {
    if(!command.hasSecondWord()) {
        this.userInterface.println("Take what ?");
        return;
    }
    this.userInterface.println(this.player.takeItem(command.getSecondWord()));
}   

private void drop(final Command command) {
    if(!command.hasSecondWord()) {
        this.userInterface.println("Drop what ?");
        return;
    }
    this.userInterface.println(this.player.dropItem(command.getSecondWord()));
}
```

<hr>

###### Exercice 7.32

Pour ajouter la fonctionnalité qui permet de fixer un poids maximal au joueur, et ainsi porter une quantité limitée d'items, c'est déjà créer les nouveau attributs qui lui seront utiles.

Pour commencer, créons nos attributs dans `Player` et ajoutons ses getters : 

```java
public class Player {
    
    // [...]
    
    private final int maxWeight;
    private int weight;
    
    // Changeons la signature du constructeur
    public Player(final String name, final int maxWeight) {
        
        // [...]
        
        this.maxWeight = maxWeight;
        // "this.maxWeight" pointe vers l'attribut qui se nomme "maxWeight"
        // "maxWeight" pointe vers une variable locale qui se nomme "maxWeight" (ici, il s'agit du paramètre)

        this.weight = 0;
    }
    
    // Getter de l'attribut "weight"
    public int getWeight() {
        return this.weight;
    }
    
    // Getter de l'attribut "maxWeight"
    public int getMaxWeight() {
        return this.maxWeight;
    }
    
}
```

Maintenant, il ne reste plus qu'a ajouter les poids ou retirer les poids, selon l'action du joueur:

- Si le joueur ajoute un élément dans son inventaire, il va s'allourdir
- Si le joueur retire un élément de son inventaire, il va s'alléger
- Si le joueur n'a plus de place, empêche le joueur de prendre un objet

Commençons par modifier la fonction `takeItem(final String itemName)` dans la classe `Player`:

```java
public String takeItem(final String itemName) {
    Item foundItem = currentRoom.getItemList().getItem(itemName);
    if(foundItem == null) {
        return "Item in this room is not found !";
    }
	
    // Vérifie si le poids qui va être ajouté par l'item ne dépasse pas la limite autorisée
    if(this.weight + foundItem.getWeight() > this.maxWeight) {
        // retourne un message et quitte la fonction (la suite de la fonction n'est donc pas lue)
        return "You can't take this item ! It's to heavy for you !";
    }
    
    // Si ça passe ici, ça veut dire que le poids est convenable, pour l'ajouter faisons :
    this.weight += foundItem.getWeight();
    
    this.items.addItem(foundItem);
    this.currentRoom.getItemList().removeItem(foundItem);
    return "You took the item " + foundItem.getName() + " !"; 
}
```

Et procéder de la même manière avec `dropItem(final String itemName)` dans la même classe :

```java
public String dropItem(final String itemName) {
    
    if(!this.items.hasItem(itemName.toLowerCase())) {
        return "You have any item named like this on your player !";
    }

    Item item = this.items.getItem(itemName.toLowerCase());
    this.currentRoom.getItemList().addItem(item);
    
    // Ici pas besoin de vérifier si le poids est inférieur au poids maximal puisque nous allons
    // retirer un item, donc forcément, l'item nous fera perdre du poids
    this.weight -= item.getWeight();
    
    this.items.removeItem(item);
    return "You dropeed the item " + item.getName() + " in the room !"; 
}
```

<hr>

###### Exercice 7.33

Pour afficher la liste des items dans l'inventaire du joueur, nous devons déjà enregistrer une nouvelle commande dans `CommandWords` et l'ajouter dans notre multi-bloc conditionnel dans notre fonction `interpretCommand()` dans notre classe `GameEngine`. 

`CommandWords`:

```java
public class CommandWords
{
    private final String[] registeredCommands;

    public CommandWords() {
        this.registeredCommands = new String[]  {
            "go", "help", "quit", "look", "eat", "back", "test", "take", "drop", "inventory"
        };
    }
    
    // [...]
    
}
```

`GameEngine.java`:

```java

public void interpretCommand(final String rawCommand) {
    // [...]
    if (command.isUnknown()) {
        this.userInterface.println("I don't know what you mean ...");
        return;
    }

    switch(command.getCommandWord().toLowerCase()) {
        case "go":
            goRoom(command);
            return;
        
        // [...]
            
        case "inventory":
            inventory(command);
            return;
        
        // [...]
            
        default:
            this.userInterface.println("Unknown command !");
            return;
    }
}

private void inventory(final Command command) {
    if(command.hasSecondWord()) {
        this.userInterface.println("I dont understand what do you want...");
        return;
    }
    this.userInterface.println("My Inventory : " + this.player.getInventoryContent());
}
```

Suivi de cela, nous allons créer une nouvelle méthode dans la classe `Player` tout en utilisant les fonctions déjà inscrites dans l'attribut `this.items ` (qui pointent vers une classe `ItemList`):

```java
public class Player {
    
    // [...]
    
    public String getInventoryContent() {
        String response = "(" + this.items.getSize() + ")\n";
        for(Item item : this.items.getContent()) {
            response += "- " + item.getLongDescription() + "\n";
        }
        return response;
    }
    
    // [...]

}
```

<hr>

###### Exercice 7.35

**Explication d'une énumération** :

Une énumération est un modèle de classe utilisé pour représenter un état ou un ensemble de possibilités. On peut comparer une énumération à une liste qui n'est pas modifiable (on dit aussi d'une liste immutable) comme les jours de la semaine :

*immutable -> non mutable -> non modifiable*

Une énumération qui liste les jours de la semaine est par exemple *LUNDI*, *MARDI*, *MERCREDI*, *JEUDI*, *VENDREDI*, *SAMEDI*, *DIMANCHE*. Et aucune autre valeur dans les jours de la semaine n'existent à part celle-ci. Il s'agit donc d'une liste non modifiable définie par défaut.

Un autre exemple d'un modèle d'énumération qui peut être intégré à notre jeu serait, par exemple, l'ensemble de nos salles dans notre jeu, ou l'ensemble de nos commandes: dans cette situation ca serait :

<u>Enumération de mes commandes</u> : *GO*, *BACK*, *HELP*, *LOOK*, *QUIT*, *EAT*, *TAKE*, *DROP* (car on n'a aucune autre commande)

<u>Enumération de mes salles</u> : *KING_PALACE*, *FORTRESS_DUNGEON*, *FORTRESS_JAIL*, *FORTRESS_UNDERGROUND*, *ARTEFACTS_ROOM*, *FORTRESS_YARD*, *FORTRESS_ENTRANCE*, *SILVER_RIVER*, *JOYFUL_AVENUE*, *CASCADE_OF_DIAMONDS*, *SECRET_CASCADE_OF_DIAMONDS*, *FORBIDDEN_FOREST*, *FORBIDDEN_FOREST_CAVE* (car on a aucune autre salle)

**Convention** : une énumération doit être écrit toujours en majuscules : "King palace" => "KING_PALACE"

<u>**Programme**</u> :

Nous souhaitons intégrer une énumération à notre jeu, et plus particulièrement l'énumération commande. Pour cela nous allons effectuer différents ajouts et modifications :

Tout d'abord créons une nouvelle énumération du nom de `CommandWord` :

![](C:\Users\leoga\Projects\IPO\Zuul\rapports\screens\new-enum.jpg)

Puis écrivons dedans nos commandes disponibles :

```java
/**
 * Enumeration CommandWord - énumération qui représente la liste
 * des commandes disponibles dans le jeu
 *
 * @author Leo Gaillet
 * @version 1.0.1
 */
public enum CommandWord {
    // Il s'agit de mes commandes disponible dans le jeu
    GO, HELP, QUIT, LOOK, EAT, BACK, TEST, TAKE, DROP, INVENTORY;
}

```

