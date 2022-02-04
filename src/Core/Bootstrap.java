package Core;

import Bank.Bank;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import Stack.Stack;
import State.State;
import TrieMap.TrieMap;

public class Bootstrap {
    public static void init() {
        Container app = Container.getInstance();

        app.bind("banks", new TrieMap<Bank>());
        app.bind("banksTree", new KDTree<Bank>());
        app.bind("states", new TrieMap<State>());
        app.bind("commands", new Commands());
        app.bind("commandsStack", new Stack<ActionInterface>());
    }
}
