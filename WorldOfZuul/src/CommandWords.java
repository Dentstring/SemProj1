import java.util.HashMap;


public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    // I don't see the use, of calling this constructor more than once...
    // Consider the use of a singleton pattern. - Marcus
    public CommandWords() {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public void showAll() {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
