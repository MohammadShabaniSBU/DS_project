package Actions;

import Interfaces.ActionInterface;
import Stack.Stack;

public class Undo extends ActionAbstract {

    @Override
    public void run() throws Exception {
        Stack<ActionInterface> commandsStack = (Stack<ActionInterface>) (this.app.resolve("commandsStack"));

        int whichCommand;

        System.out.print("Enter the command you wanna go to: ");
        whichCommand = this.scanner.nextInt();
        int count = commandsStack.getSize() - whichCommand;

        if (count < 0)
            throw new Exception("The given number of command is invalid.");

        while (count > 0) {
            count -= 1;
            ActionInterface command = commandsStack.pop();
            if (command == null)
                continue;

            command.run();
        }
        System.out.printf("App turned back to %dth level\n", whichCommand);
    }
}
