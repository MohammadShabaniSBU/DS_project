package Actions;

import Bank.Bank;
import Bank.MainBank;
import Core.Container;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import TrieMap.TrieMap;

public class AddBankRev implements ActionInterface {
    private MainBank mainBank;

    public AddBankRev(MainBank mainBank) {
        this.mainBank = mainBank;
    }

    public void run() {
        TrieMap<Bank> banks = (TrieMap<Bank>) Container.getInstance().resolve("banks");
        KDTree<Bank> banksTree = (KDTree<Bank>) Container.getInstance().resolve("banksTree");
        banksTree.removeNode(this.mainBank.getCoordination());
    }
}
