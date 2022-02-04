package Actions;

import Core.Container;
import Interfaces.ActionInterface;
import Stack.Stack;
import State.State;
import TrieMap.TrieMap;

public class AddState extends ActionAbstract {

    @Override
    public void run() {

        String name;
        int[] upRight = new int[2];
        int[] downLeft = new int[2];

        System.out.print("Enter the name of State: ");
        name = this.scanner.next();

        System.out.print("Enter the coordination of down left point of state: ");
        downLeft[0] = this.scanner.nextInt();
        downLeft[1] = this.scanner.nextInt();

        System.out.print("Enter the coordination of up right point of state: ");
        upRight[0] = this.scanner.nextInt();
        upRight[1] = this.scanner.nextInt();

        TrieMap<State> states = (TrieMap<State>) this.app.resolve("states");

        State state = new State(name, downLeft, upRight);
        states.insert(name, state);

        Stack<ActionInterface> commandsStack = (Stack<ActionInterface>) (this.app.resolve("commandsStack"));
        commandsStack.push(new AddStateRev(state));

        System.out.println("New state added successfully");
    }
}
