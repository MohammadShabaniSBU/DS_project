package Actions;

import ArrayList.ArrayList;
import Bank.Bank;
import KDTree.*;
import State.State;
import TrieMap.TrieMap;

public class ListBanksInState extends ActionAbstract {

    @Override
    public void run() throws Exception {

        System.out.print("Enter the name of state: ");
        String state = this.scanner.next();

        TrieMap<State> states = (TrieMap<State>) this.app.resolve("states");
        State stateObject = states.get(state);

        if (stateObject == null)
            throw new Exception("State doesn't found.");

        int[] downLeft = stateObject.getDownLeft();
        int[] upRight = stateObject.getUpRight();

        KDTree<Bank> banksTree = (KDTree<Bank>) this.app.resolve("banksTree");
        ArrayList<Node<Bank>> banks = banksTree.getNodesIn(downLeft, upRight);

        banks.print();
    }
}
