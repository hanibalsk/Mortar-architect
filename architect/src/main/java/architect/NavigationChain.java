package architect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukasz Piliszczuk - lukasz.pili@gmail.com
 */
public class NavigationChain {

    List<Chain> chains = new ArrayList<>();

    public NavigationChain back() {
        chains.add(new Chain());
        return this;
    }

    public NavigationChain push(StackablePath path) {
        chains.add(new Chain(path, Chain.TYPE_PUSH));
        return this;
    }

    public NavigationChain show(StackablePath path) {
        chains.add(new Chain(path, Chain.TYPE_SHOW));
        return this;
    }

    public NavigationChain replace(StackablePath path) {
        chains.add(new Chain(path, Chain.TYPE_REPLACE));
        return this;
    }

    public static class Chain {

        static final int TYPE_PUSH = 1;
        static final int TYPE_SHOW = 2;
        static final int TYPE_REPLACE = 3;

        StackablePath path;
        int type;

        public Chain() {
        }

        public Chain(StackablePath path, int type) {
            this.path = path;
            this.type = type;
        }
    }
}
