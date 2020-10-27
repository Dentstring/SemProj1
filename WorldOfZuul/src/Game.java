public class Game {
    private Parser parser;
    private Room currentRoom;
    private static final String NORTH = "north";
    private static final String EAST = "east";
    private static final String SOUTH = "south";
    private static final String WEST = "west";
        

    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {
        Room headQuarter, field, garden, store, shed, stables;

        headQuarter = new Room("the Farm: your home, and your base of operations.");
        store = new Room("the store: the best place to barter.");
        field = new Room("the field: perfect for plow, sow and reap the benefits of your harvest.");
        garden = new Room("the bee-hives: give them bees-wax and harvest their honey");
        shed = new Room("the shed: store items, contraptions, seeds and much more.");
        stables = new Room("the stables: houses your animals");

        headQuarter.setExit(NORTH, store);
        headQuarter.setExit(SOUTH, field);
        headQuarter.setExit(EAST, shed);

        store.setExit(SOUTH, headQuarter);

        field.setExit(NORTH, headQuarter);
        field.setExit(EAST, garden);
        field.setExit(WEST, stables);

        garden.setExit(WEST, field);

        stables.setExit(EAST, field);

        shed.setExit(WEST, headQuarter);
    }

    public void play() {
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            //Why does processCommand return a boolean? -rhetorical, obviously it returns false if the given command is "quit"
            //Is it benifical to make the return type void, and simply give the method more power?
            //The method already calls other methods that change the attribute "currentRoom".
            //This change would have to make the "running" variable into a field.
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
