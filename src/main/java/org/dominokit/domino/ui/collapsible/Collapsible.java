package org.dominokit.domino.ui.collapsible;

import elemental2.dom.HTMLElement;
import org.dominokit.domino.ui.style.Style;
import org.dominokit.domino.ui.utils.IsCollapsible;
import org.jboss.gwt.elemento.core.IsElement;

import java.util.ArrayList;
import java.util.List;

public class Collapsible implements IsElement<HTMLElement>, IsCollapsible<Collapsible> {


    private final HTMLElement element;
    private final Style<HTMLElement, IsElement<HTMLElement>> style;

    private boolean collapsed = false;

    private HideCompletedHandler onHidden = () -> {
    };
    private ShowCompletedHandler onShown = () -> {
    };

    private List<HideCompletedHandler> hideHandlers = new ArrayList<>();
    private List<ShowCompletedHandler> showHandlers = new ArrayList<>();

    public Collapsible(HTMLElement element) {
        this.element = element;
        style = Style.of(element);
    }

    public static Collapsible create(HTMLElement element) {
        return new Collapsible(element);
    }

    public static Collapsible create(IsElement isElement) {
        return create(isElement.asElement());
    }

    /**
     * @return T
     * @deprecated use {@link #show()}
     */
    @Deprecated
    @Override
    public Collapsible collapse() {
        return hide();
    }

    /**
     * @return T
     * @deprecated use {@link #show()}
     */
    @Deprecated
    @Override
    public Collapsible expand() {
        return show();
    }

    @Override
    public Collapsible show() {
        onShowCompleted();
        style.removeProperty("display");
        this.collapsed = false;
        return this;
    }

    @Override
    public Collapsible hide() {
        style.setDisplay("none");
        onHideCompleted();
        this.collapsed = true;
        return this;
    }

    private void onHideCompleted() {
        onHidden.onHidden();
        hideHandlers.forEach(HideCompletedHandler::onHidden);
    }

    private void onShowCompleted() {
        onShown.onShown();
        showHandlers.forEach(ShowCompletedHandler::onShown);
    }

    @Override
    public boolean isHidden() {
        return this.collapsed;
    }

    @Override
    public Collapsible toggleDisplay() {
        if (isHidden())
            show();
        else
            hide();
        return this;
    }


    @Override
    public Collapsible toggleDisplay(boolean state) {
        if (state) {
            show();
        } else {
            hide();
        }
        return this;
    }

    void setOnHidden(HideCompletedHandler onHidden) {
        this.onHidden = onHidden;
    }

    void setOnShown(ShowCompletedHandler onShown) {
        this.onShown = onShown;
    }


    /**
     * @deprecated use {@link #addHideHandler(HideCompletedHandler)}
     */
    @Deprecated
    public Collapsible addCollapseHandler(CollapseCompletedHandler handler) {
        return addHideHandler(handler::onCollapsed);
    }

    /**
     * @deprecated use {@link #removeHideHandler(HideCompletedHandler)}
     */
    @Deprecated
    public void removeCollapseHandler(CollapseCompletedHandler handler) {
        removeHideHandler(handler::onCollapsed);
    }

    /**
     * @deprecated use {@link #addShowHandler(ShowCompletedHandler)}
     */
    @Deprecated
    public Collapsible addExpandHandler(ExpandCompletedHandler handler) {
        return addShowHandler(handler::onExpanded);
    }

    /**
     * @deprecated use {@link #removeShowHandler(ShowCompletedHandler)}
     */
    @Deprecated
    public void removeExpandHandler(ExpandCompletedHandler handler) {
        removeShowHandler(handler::onExpanded);
    }

    public Collapsible addHideHandler(HideCompletedHandler handler) {
        hideHandlers.add(handler);
        return this;
    }

    public void removeHideHandler(HideCompletedHandler handler) {
        hideHandlers.remove(handler);
    }

    public Collapsible addShowHandler(ShowCompletedHandler handler) {
        showHandlers.add(handler);
        return this;
    }

    public void removeShowHandler(ShowCompletedHandler handler) {
        showHandlers.remove(handler);
    }


    @Override
    public HTMLElement asElement() {
        return element;
    }

    /**
     * @deprecated use {@link HideCompletedHandler()}
     */
    @Deprecated
    @FunctionalInterface
    public interface CollapseCompletedHandler {
        void onCollapsed();
    }

    /**
     * @deprecated use {@link ShowCompletedHandler()}
     */
    @Deprecated
    @FunctionalInterface
    public interface ExpandCompletedHandler {
        void onExpanded();
    }

    @FunctionalInterface
    public interface HideCompletedHandler {
        void onHidden();
    }

    @FunctionalInterface
    public interface ShowCompletedHandler {
        void onShown();
    }

}
