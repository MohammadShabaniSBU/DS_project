package Bank;

import KDTree.KDTree;

public class MainBank extends Bank {
    protected KDTree<Bank> branches;

    public MainBank(String name, int[] cordination) {
        super(name, cordination);
        branches = new KDTree<Bank>();
    }

    public void addBranch(Bank branch) throws Exception {
        this.branches.insert(branch, branch.getCoordination());
    }

    public KDTree<Bank> getBranches() {
        return branches;
    }

    public int countOfBranches() {
        return branches.getSize();
    }

    @Override
    public String toString() {
        return name + " is a main bank " + "which is placed in " + coordination[0] + " " + coordination[1];
    }
}
