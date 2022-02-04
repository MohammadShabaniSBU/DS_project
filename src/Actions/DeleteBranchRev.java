package Actions;

import Bank.Bank;
import Bank.Branch;
import Core.Container;
import Interfaces.ActionInterface;
import KDTree.KDTree;

public class DeleteBranchRev implements ActionInterface {

    private Branch branch;

    public DeleteBranchRev(Branch branch) {
        this.branch = branch;
    }

    @Override
    public void run() throws Exception {
        ((KDTree<Bank>) (Container.getInstance().resolve("banksTree"))).insert(this.branch, this.branch.getCoordination());
        this.branch.getMainBank().getBranches().insert(this.branch, this.branch.getCoordination());
    }
}
