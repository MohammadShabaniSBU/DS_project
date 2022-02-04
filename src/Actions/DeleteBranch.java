package Actions;

import Bank.*;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import Stack.Stack;

public class DeleteBranch extends ActionAbstract {

    @Override
    public void run() throws Exception {

        int[] coordination = new int[2];

        System.out.print("Enter the coordination of branch to delete (x y): ");
        coordination[0] = this.scanner.nextInt();
        coordination[1] = this.scanner.nextInt();

        KDTree<Bank> banksTree = (KDTree<Bank>) (this.app.resolve("banksTree"));

        Bank bank = banksTree.findWithCoordination(coordination);

        if (bank == null)
            throw new Exception("There is no bank in the given coordination.");

        if (bank instanceof MainBank)
            throw new Exception("The bank in the given coordination is a main bank. You cannot delete it.");

        Branch branch = (Branch) bank;

        branch.getMainBank().getBranches().removeNode(coordination);
        banksTree.removeNode(coordination);

        Stack<ActionInterface> commandsStack = (Stack<ActionInterface>) (this.app.resolve("commandsStack"));
        commandsStack.push(new DeleteBranchRev(branch));

        System.out.printf("The %s branch deleted successfully.\n", bank.getName());
    }
}
