package Actions;

import Bank.MainBank;
import Bank.Bank;
import Interfaces.ActionInterface;
import Stack.Stack;
import TrieMap.TrieMap;

public class ListBranches extends ActionAbstract {

    @Override
    public void run() throws Exception {

        String name;

        System.out.print("Enter the name of main bank: ");
        name = this.scanner.next();

        Bank bank = ((TrieMap<Bank>) app.resolve("banks")).get(name);

        if (!(bank instanceof MainBank))
            throw new Exception("Main bank doesn't found");

        MainBank mainBank = (MainBank) bank;
        mainBank.getBranches().print();
        ((Stack<ActionInterface>) (this.app.resolve("commandsStack"))).push(null);
    }
}
