package Actions;

import Bank.Bank;
import Bank.Branch;
import Core.Container;
import Interfaces.ActionInterface;
import KDTree.KDTree;

public class AddBranchRev implements ActionInterface {
    private Branch branch;

    public AddBranchRev(Branch branch) {
        this.branch = branch;
    }

    public void run() {
        KDTree<Bank> banks = (KDTree<Bank>) Container.getInstance().resolve("banksTree");
        banks.removeNode(this.branch.getCoordination());
        this.branch.getMainBank().getBranches().removeNode(branch.getCoordination());
    }
}
