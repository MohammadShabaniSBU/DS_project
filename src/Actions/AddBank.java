package Actions;

import Bank.Bank;
import Bank.MainBank;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import Stack.Stack;
import TrieMap.TrieMap;

public class AddBank extends ActionAbstract {

    @Override
    public void run() throws Exception {
        String name;
        int[] coordination = new int[2];

        System.out.print("Enter name of bank: ");
        name = this.scanner.next();

        System.out.print("Enter the coordination of Bank (x y): ");
        try {
            coordination[0] = this.scanner.nextInt();
            coordination[1] = this.scanner.nextInt();
        } catch (Exception e) {
            throw new Exception("Coordination has to be integer number.");
        }

        MainBank bank = new MainBank(name, coordination);
        try {

            TrieMap<Bank> banks = (TrieMap<Bank>) this.app.resolve("banks");

            Bank isExist = banks.get(name);
            if (isExist != null)
                throw new Exception("The name is already taken");

            KDTree<Bank> banksTree = (KDTree<Bank>) this.app.resolve("banksTree");

            banksTree.insert(bank, coordination);
            banks.insert(name, bank);

            Stack<ActionInterface> commandsStack = (Stack<ActionInterface>) (this.app.resolve("commandsStack"));

            commandsStack.push(new AddBankRev(bank));

            System.out.println("Bank successfully added");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
