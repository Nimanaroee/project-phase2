package Model;

import Veiw.Out;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public abstract class Menu {
    private final ArrayList<Command> commands;
    protected final Scanner scan;
    protected final String menuName, exitWord;

    private void showMenu() {
        Out.print(menuName);
    }

    public Menu(Scanner scanner, String menuName, String exitWord) {
        this.scan = scanner;
        this.menuName = menuName;
        this.exitWord = exitWord;
        commands = new ArrayList<>();
    }

    protected final void addCommand(Regex pattern, Consumer<Matcher> consumer) {
        commands.add(new Command(pattern, consumer));
    }

    protected final boolean processCommands(String input) {
        for(Command command : commands) {
            if(command.run(input)) {
                return true;
            }
        }
        return false;
    }
    public Matcher runCommand(Regex regex) {
        String input = scan.nextLine();
        while (!input.matches(exitWord)) {
            Matcher matcher = regex.getMatcher(input);
            if(matcher.matches()) {
                return matcher;
            }
            Out.print("Invalid command!");
            input = scan.nextLine();
        }
        return null;
    }

    public void run() {
        String input = scan.nextLine();
        while(!input.matches(exitWord)) {
            if(!processCommands(input)) {
                Out.print("Invalid input!");
            }
            input = scan.nextLine();
        }
    }
}
