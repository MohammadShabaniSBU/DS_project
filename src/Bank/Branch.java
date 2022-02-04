package Bank;

public class Branch extends Bank {
    private MainBank mainBank;

    public Branch(String name, int[] cordination) {
        super(name, cordination);
    }

    public void setMainBank(MainBank mainBank) {
        this.mainBank = mainBank;
    }

    public MainBank getMainBank() {
        return mainBank;
    }

    @Override
    public String toString() {
        return name + " is a branch of " + mainBank + " which is placed in " + coordination[0] + " " + coordination[1];
    }
}
