package Controller;

import Model.*;
import Veiw.Out;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu extends Menu {
    public PreGameMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        /// add command
        addCommand(Regex.LOGIN_LOGIN, this::login);
        addCommand(Regex.GAME_SELECT_CHARACTER, this::selectCharacter);
    }
    private void login(Matcher matcher) {
        User user = Data.getUserByUsername(matcher.group("username"));
        if(user == null) {
            Out.print("Username doesn’t exist!");
            return;
        }
        if(!user.getPassword().equals(matcher.group("password"))) {
            Out.print("Password and Username don’t match!");
            return;
        }
        Out.print("user logged in successfully!");
        Data.setLoggedInUser2(user);
    }
    private void selectCharacter(Matcher matcher) {
        String firstCharacter = matcher.group("first");
        String secondCharacter = matcher.group("second");

        ///// check valid characters //////

        ///// select cardss ///////

        new GameMenu(scan, "Game Menu").run();
    }

}
