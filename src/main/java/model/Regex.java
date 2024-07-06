package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Regex {
    /// add regexes here
    VALID_USERNAME("(?<username>[a-zA-Z0-9_]+)"),
    VALID_NICKNAME("(?<nickname>[a-zA-Z]+)"),
    VALID_EMAIL("(?<email>.+)@(?<domain>.+).com"),
    STRONG_PASSWORD("(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*])[A-Za-z\\d\\!\\@\\#\\$\\%\\^\\&\\*]{6,20}$)"),

    /// login menu
    LOGIN_CREATE_USER("user create -u +(?<username>.+) -p (?<password>.+) (?<passwordconfirm>.+) -email +(?<email>.+) -n (?<nickname>.+)"),
    LOGIN_CREATE_USER_WITH_PASSWORD("user create -u +(?<username>.+) -p random -email +(?<email>.+) -n (?<nickname>.+)"),
    LOGIN_QUESTION_PICK("question pick -q (?<number>[0-9]+) -a (?<answer>.+) -c (?<confirm>.+)"),
    LOGIN_FORGET_PASSWORD("Forgot my password -u (?<username>.+)"),
    LOGIN_LOGIN("user login -u (?<username>.+) -p (?<password>.+)"),

    //// Main menu
    MAIN_START_GAME("start game"),
    MAIN_SHOW_CARDS("show cards"),
    MAIN_SHOW_USER_INFO("show info user"),
    MAIN_SHOW_HISTORY("show history"),
    MAIN_ENTER_SHOPMENU("enter shop menu"),
    MAIN_ENTER_PROFILEMENU("enter profile menu"),

    //// Profile menu
    PROFILE_SHOW_INFORMATION("Show information"),
    PROFILE_CHANGE_USERNAME("Profile change -u (?<username>.+)"),
    PROFILE_CHANGE_NICKNAME("Profile change -n (?<nickname>.+)"),
    PROFILE_CHANGE_PASSWORD("profile change password -o (?<oldpassword>.+) -n (?<newpassword>.+)"),
    PROFILE_CHANGE_EMAIL("profile change -e (?<email>.+)"),


    //// Shop menu
    SHOP_SHOW_ALL_CARDS("show all cards"),
    SHOP_UPGRADE_CARD("upgrade card -n (?<name>.+)"),
    SHOP_BUY_CARD("buy card -n (?<name>.+)"),


    /// admin menu
    ADMIN_ADD_CARD("add card -name (?<name>.+) -attack (?<attck>.+) -duration (?<duration>.+) -damage (?<damage>.+) -upgradeLevel (?<upgradeLevel>.+) -upgradeCost (?<upgradeCost>.+)"),
    ADMIN_UPDATE_CARD("update card -name (?<name>.+) -attack (?<attck>.+) -duration (?<duration>.+) -damage (?<damage>.+) -upgradeLevel (?<upgradeLevel>.+) -upgradeCost (?<upgradeCost>.+)"),
    ADMIN_DELETE_CARD("delete card -name (?<name>.+)"),
    ADMIN_SHOW_PLAYERS("show players"),



    //// history menu
    HISTORY_SORT("sort by (?<type>.+)"),
    HISTORY_NEXT_PAGE("next page"),
    HISTORY_PREVIOUS("previous page"),
    HISTORY_NUMBERPAGE("page no (?<number>.+)"),

    ///// pre game menu
    GAME_SELECT_CHARACTER("select character -(?<first>.+) -(?<second>.+)"),

    ///// game menu


    ;

    private final Pattern pattern;

    Regex(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }

    public boolean matches(String input) { return pattern.matcher(input).matches();}
}
