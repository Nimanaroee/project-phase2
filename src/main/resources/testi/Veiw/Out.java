package Veiw;

import Model.Card;
import Model.User;

public class Out {
    public static void print(String output) {
        System.out.println(output);
    }
    public static void showInfoOfUser(User user) {
        System.out.println("username : "+user.getUsername());
        System.out.println("nickname : "+user.getNickname());
        System.out.println("password : "+user.getPassword());
        System.out.println("email    : "+user.getEmail());
    }
    public static void askQuestion() {
        System.out.println("User created successfully. Please choose a security question :");
        System.out.println("• 1-What is your father’s name ?");
        System.out.println("• 2-What is your favourite color ?");
        System.out.println("• 3-What was the name of your first pet?");
    }
    public static void showInfoOfCard(Card card) {
        ///// update
        System.out.println("name : "+card.getName());
        System.out.println("attack/defence : "+card.getAttack());
        System.out.println("duration : "+card.getDuration());
        System.out.println("player damage : "+card.getDamage());
        System.out.println("upgrade level : "+card.getUpgradeLevel());
        System.out.println("upgrade coast : "+card.getUpgradeCoast());
    }
}
