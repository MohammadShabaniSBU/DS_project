package Core;

import TrieMap.TrieMap;

public class Container {
    private static Container instance = null;
    private final TrieMap<Object> repo = new TrieMap<Object>();

    private Container() {}

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void bind(String key, Object service) {
        repo.insert(key, service);
    }

    public Object resolve(String key) {
        return repo.get(key);
    }
}
