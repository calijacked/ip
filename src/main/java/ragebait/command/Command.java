package ragebait.command;

import ragebait.exception.RagebaitException;
import ragebait.ui.UI;

public interface Command {
    String execute(UI ui, Context context) throws RagebaitException;
}
