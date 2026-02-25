package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.ui.UI;

public abstract class TaskCommand implements Command {
    @Override
    public abstract String execute(UI ui, Context context) throws RagebaitException;
}
