package Model;

import java.util.function.Consumer;
import java.util.regex.Matcher;

public class Command {
    private final Regex pattern;
    private final Consumer<Matcher> consumer;

    public Command(Regex pattern, Consumer<Matcher> consumer) {
        this.pattern = pattern;
        this.consumer = consumer;
    }

    public boolean run(String input) {
        Matcher matcher = pattern.getMatcher(input);
        if(matcher.matches()) {
            consumer.accept(matcher);
            return true;
        }
        return false;
    }
}
