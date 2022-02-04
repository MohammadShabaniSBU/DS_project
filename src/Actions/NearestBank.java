package Actions;

import Bank.Bank;
import Interfaces.ActionInterface;
import KDTree.KDTree;
import Stack.Stack;

public class NearestBank extends ActionAbstract {

    @Override
    public void run() throws Exception {

        int[] coordination = new int[2];

        System.out.print("Enter your coordination (x y): ");
        try {
            coordination[0] = this.scanner.nextInt();
            coordination[1] = this.scanner.nextInt();
        } catch (Exception e) {
            throw new Exception("Coordination has to be integer number.");
        }

        Bank bank = ((KDTree<Bank>) this.app.resolve("banksTree")).getNearest(coordination);

        if (bank == null) {
            System.out.println("There is no bank in the world :)");
        } else {
            System.out.printf("%s bank is nearest bank to %d %d which is placed in %d %d\n",
                    bank.getName(),
                    coordination[0], coordination[1],
                    bank.getCoordination()[0], bank.getCoordination()[1]
            );
            ((Stack<ActionInterface>) (this.app.resolve("commandsStack"))).push(null);
        }
    }
}
