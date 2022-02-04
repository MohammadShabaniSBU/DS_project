package Actions;

import Bank.*;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import Stack.Stack;
import TrieMap.TrieMap;

public class AddBranch extends ActionAbstract {

    @Override
    public void run() throws Exception {

        String name;
        String mainBankName;
        int[] coordination = new int[2];

        System.out.print("Enter name of branch: ");
        name = this.scanner.next();

        System.out.print("Enter name of main bank: ");
        mainBankName = this.scanner.next();

        TrieMap<Bank> banks = (TrieMap<Bank>) this.app.resolve("banks");
        Bank bank = banks.get(mainBankName);
        if (!(bank instanceof MainBank))
            throw new Exception("The main bank doesn't found");
        MainBank mainBank = (MainBank) bank;

        System.out.print("Enter the coordination of Bank (x y): ");
        try {
            coordination[0] = this.scanner.nextInt();
            coordination[1] = this.scanner.nextInt();
        } catch (Exception e) {
            throw new Exception("Coordination has to be integer number.");
        }

        Branch branch = new Branch(name, coordination);
        branch.setMainBank(mainBank);
        try {

            KDTree<Bank> banksTree = (KDTree<Bank>) this.app.resolve("banksTree");

            Bank isExist = banks.get(name);
            if (isExist != null)
                throw new Exception("The name is already taken");

            banksTree.insert(branch, coordination);

            mainBank.addBranch(branch);

            Stack<ActionInterface> commandsStack = (Stack<ActionInterface>) (this.app.resolve("commandsStack"));
            commandsStack.push(new AddBranchRev(branch));
            System.out.println("Branch successfully added");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
