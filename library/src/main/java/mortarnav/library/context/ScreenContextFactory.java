package mortarnav.library.context;

import android.content.Context;

import mortar.MortarScope;
import mortarnav.library.Screen;

/**
 * @author Lukasz Piliszczuk - lukasz.pili@gmail.com
 */
public class ScreenContextFactory {

    public Context setUp(Context parentContext, Screen screen) {
        MortarScope scope = MortarScope.findChild(parentContext, screen.getScopeName());

        if (scope == null) {
            MortarScope parentScope = MortarScope.getScope(parentContext);
            MortarScope.Builder scopeBuilder = parentScope.buildChild();

            if (screen instanceof ScreenContextBuilder) {
                Builder builder = new Builder(parentContext, parentScope, scopeBuilder);
                ((ScreenContextBuilder) screen).buildScreenScope(builder);
            }

            scope = scopeBuilder.build(screen.getScopeName());
        }

        return scope.createContext(parentContext);
    }

    public static class Builder {
        private final Context parentContext;
        private final MortarScope parentScope;
        private final MortarScope.Builder scopeBuilder;

        public Builder(Context parentContext, MortarScope parentScope, MortarScope.Builder scopeBuilder) {
            this.parentContext = parentContext;
            this.parentScope = parentScope;
            this.scopeBuilder = scopeBuilder;
        }

        public Context getParentContext() {
            return parentContext;
        }

        public MortarScope getParentScope() {
            return parentScope;
        }

        public MortarScope.Builder getScopeBuilder() {
            return scopeBuilder;
        }
    }
}
