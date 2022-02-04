package Actions;

import Core.Container;
import Interfaces.ActionInterface;
import State.State;
import TrieMap.TrieMap;

public class AddStateRev implements ActionInterface {
    private State state;

    public AddStateRev(State state) {
        this.state = state;
    }

    @Override
    public void run() {
        TrieMap<State> states = (TrieMap<State>) Container.getInstance().resolve("states");
    }
}
