package Actions;

import Core.Container;
import Interfaces.ActionInterface;

import java.util.Scanner;

public abstract class ActionAbstract implements ActionInterface {
    protected Container app;
    protected Scanner scanner;

    public ActionAbstract() {
        app = Container.getInstance();
        scanner = new Scanner(System.in);
    }
}
