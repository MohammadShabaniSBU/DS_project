package Bank;

public abstract class Bank {
    protected int id;
    protected String name;
    protected int[] coordination;

    public Bank(String name, int[] coordination) {
        this.id = id;
        this.name = name;
        this.coordination = coordination;
    }

    public int[] getCoordination() {
        return coordination;
    }

    public String getName() {
        return name;
    }
}
