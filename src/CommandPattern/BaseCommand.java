package CommandPattern;

import CommandPattern.Enumerable.CommandsE;

public abstract class BaseCommand implements ICommand{
    CommandsE type;

    public CommandsE getType() {
        return type;
    }
}
