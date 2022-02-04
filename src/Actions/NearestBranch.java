package Actions;

import Bank.Bank;
import Bank.MainBank;
import Interfaces.ActionInterface;
import Stack.Stack;
import TrieMap.TrieMap;

public class NearestBranch extends ActionAbstract {

    @Override
    public void run() throws Exception {

        String mainBankName;
        int[] coordination = new int[2];

        System.out.print("Enter the name of main bank: ");
        mainBankName = this.scanner.next();

        System.out.print("Enter your coordination (x y): ");
        coordination[0] = this.scanner.nextInt();
        coordination[1] = this.scanner.nextInt();

        MainBank bank = (MainBank) ((TrieMap<Bank>) this.app.resolve("banks")).get(mainBankName);

        if (bank == null) {
            throw new Exception("Error: There is no bank with given name.");
        }

        Bank branch = bank.getBranches().getNearest(coordination);

        if (branch == null) {
            System.out.println("There is no branch for this bank in the world :)");
        } else {
            System.out.printf("%s branch is nearest branch of %s bank to %d %d which is placed int %d %d\n",
                    branch.getName(),
                    mainBankName,
                    coordination[0], coordination[1],
                    bank.getCoordination()[0], bank.getCoordination()[1]
            );
            ((Stack<ActionInterface>) (this.app.resolve("commandsStack"))).push(null);
        }
    }
}
