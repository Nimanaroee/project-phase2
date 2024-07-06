package Controller;

import Model.*;
import Veiw.Out;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu {
    public ProfileMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");

        addCommand(Regex.PROFILE_SHOW_INFORMATION, this::showInformation);
        addCommand(Regex.PROFILE_CHANGE_USERNAME, this::changeUsername);
        addCommand(Regex.PROFILE_CHANGE_NICKNAME, this::changeNickname);
        addCommand(Regex.PROFILE_CHANGE_PASSWORD, this::changePassword);
        addCommand(Regex.PROFILE_CHANGE_EMAIL, this::changeEmail);
    }

    private void showInformation(Matcher matcher) {
        Out.showInfoOfUser(Data.getLoggedInUser1());
    }
    private void changeUsername(Matcher matcher) {
        User user = Data.getLoggedInUser1();
        if(Regex.VALID_USERNAME.matches(matcher.group("username"))) {
            Out.print("invalid username, try again!");
            return;
        }
        user.setUsername(matcher.group("username"));
        Out.print("username changed successfully!");
    }
    private void changeNickname(Matcher matcher) {
        User user = Data.getLoggedInUser1();
        if(Regex.VALID_NICKNAME.matches(matcher.group("nickname"))) {
            Out.print("invalid nickname, Try again!");
            return;
        }
        user.setNickname(matcher.group("nickname"));
        Out.print("nickname changed successfully!");
    }
    private void changePassword(Matcher matcher) {
        User user = Data.getLoggedInUser1();
        if(!user.getPassword().equals(matcher.group("oldpassword"))) {
            Out.print("Current password is incorrect!");
            return;
        }
        if(user.getPassword().equals(matcher.group("newpassword"))) {
            Out.print("Please enter a new password!");
        }
        if(!Regex.PROFILE_CHANGE_PASSWORD.matches(matcher.group("newpassword"))) {
            Out.print("Please enter your new password again");
        }
        Ascii.captchaChecker(scan);
        user.setPassword(matcher.group("newpassword"));
        Out.print("password changed successfully!");
    }
    private void changeEmail(Matcher matcher) {
        User user = Data.getLoggedInUser1();
        if(Regex.VALID_EMAIL.matches(matcher.group("email"))) {
            Out.print("invalid email, try again!");
            return;
        }
        user.setEmail(matcher.group("email"));
        Out.print("email changed successfully");
    }
}
