package Actions;

import ArrayList.ArrayList;
import Bank.Bank;
import KDTree.*;

public class AviabelesBanks extends ActionAbstract {

    @Override
    public void run() throws Exception {

        int[] center = new int[2];
        int radius = 0;

        try {
            System.out.print("Enter the coordination of center (x y): ");
            center[0] = this.scanner.nextInt();
            center[1] = this.scanner.nextInt();
            System.out.print("Enter the radius: ");
            radius = this.scanner.nextInt();
        } catch (Exception e) {
            throw new Exception("Coordination and radius have to be integer numbers.");
        }

        ArrayList<Node<Bank>> avialBanks = ((KDTree<Bank>) this.app.resolve("banksTree"))
                .getBanksInRadius(center, radius);

        avialBanks.print();
    }
}
