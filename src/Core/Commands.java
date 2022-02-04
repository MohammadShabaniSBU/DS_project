package Core;

import Actions.*;
import Interfaces.ActionInterface;
import TrieMap.TrieMap;

public class Commands {
    private TrieMap<ActionInterface> commands;

    public Commands() {
        commands = new TrieMap<ActionInterface>();

        // commands will define here
        commands.insert("addN", new AddState());
        commands.insert("addB", new AddBank());
        commands.insert("addBr", new AddBranch());
        commands.insert("delBr", new DeleteBranch());
        commands.insert("listB", new ListBanksInState());
        commands.insert("listBrs", new ListBranches());
        commands.insert("nearB", new NearestBank());
        commands.insert("nearBr", new NearestBranch());
        commands.insert("availB", new AviabelesBanks());
        commands.insert("undo", new Undo());
    }

    public void resolve(String key) throws Exception {
        ActionInterface command = commands.get(key);

        if (command != null) {
            command.run();
        } else if (key.equals("quit")) {
            System.out.println("Bye Bye");
        } else {
            throw new Exception("Invalid command.");
        }
    }
}
