package org.dominokit.domino.ui.datatable;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLTableCellElement;
import elemental2.dom.Node;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.domino.ui.utils.ScreenMedia;
import org.dominokit.domino.ui.utils.TextNode;

import static java.util.Objects.nonNull;

public class ColumnConfig<T> {

    private final String name;
    private String title;
    private HTMLTableCellElement headElement;
    public HTMLDivElement contextMenu;
    private boolean header = false;
    private String minWidth;
    private String maxWidth;
    private String textAlign;
    private CellRenderer<T> cellRenderer = cell -> TextNode.of("");
    private HeaderElement headerElement = TextNode::of;
    private CellStyler<T> headerStyler = element -> {
    };
    private CellStyler<T> cellStyler = element -> {
    };
    private boolean sortable = false;
    private String width;
    private boolean fixed = false;
    private Node tooltipNode;
    private boolean showTooltip = true;

    private ScreenMedia showOn;
    private ScreenMedia hideOn;

    public static <T> ColumnConfig<T> create(String name) {
        return new ColumnConfig<>(name);
    }

    public static <T> ColumnConfig<T> create(String name, String title) {
        return new ColumnConfig<>(name, title);
    }

    public ColumnConfig(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public ColumnConfig(String name) {
        this(name, "");
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public ColumnConfig<T> asHeader() {
        this.header = true;
        return this;
    }

    public ColumnConfig<T> minWidth(String minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public ColumnConfig<T> maxWidth(String maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public ColumnConfig<T> setWidth(String width) {
        this.width = width;
        return this;
    }

    public ColumnConfig<T> textAlign(String textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public ColumnConfig<T> setHeaderElement(HeaderElement headerElement) {
        this.headerElement = headerElement;
        return this;
    }

    public boolean isHeader() {
        return header;
    }

    public String getMinWidth() {
        return minWidth;
    }

    public String getMaxWidth() {
        return maxWidth;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public boolean isFixed() {
        return fixed;
    }

    public ColumnConfig<T> setFixed(boolean fixed) {
        this.fixed = fixed;
        return this;
    }

    public ColumnConfig<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    public DominoElement<HTMLTableCellElement> getHeadElement() {
        return DominoElement.of(headElement);
    }

    protected void setHeadElement(HTMLTableCellElement headElement) {
        this.headElement = headElement;
    }

    public CellRenderer<T> getCellRenderer() {
        return cellRenderer;
    }

    public ColumnConfig<T> setCellRenderer(CellRenderer<T> cellRenderer) {
        this.cellRenderer = cellRenderer;
        return this;
    }

    public ColumnConfig<T> styleHeader(CellStyler<T> headerStyler) {
        this.headerStyler = headerStyler;
        return this;
    }

    public ColumnConfig<T> styleCell(CellStyler<T> cellStyler) {
        this.cellStyler = cellStyler;
        return this;
    }

    public boolean isSortable() {
        return sortable;
    }

    public ColumnConfig<T> setSortable(boolean sortable) {
        this.sortable = sortable;
        return this;
    }

    public ColumnConfig<T> sortable() {
        this.sortable = true;
        return this;
    }

    public void applyScreenMedia(HTMLTableCellElement element) {
        DominoElement<HTMLTableCellElement> thElement = DominoElement.of(element);

        if (nonNull(showOn)) {
            thElement.showOn(showOn);
        }

        if (nonNull(hideOn)) {
            thElement.hideOn(hideOn);
        }
    }

    public Node getTooltipNode() {
        if (nonNull(tooltipNode))
            return tooltipNode;
        else {
            return getHeaderElement().asElement(title);
        }
    }

    public ColumnConfig<T> setTooltipNode(Node tooltipNode) {
        this.tooltipNode = tooltipNode;
        return this;
    }

    public ColumnConfig<T> setTooltipText(String tooltipText) {
        this.tooltipNode = TextNode.of(tooltipText);
        return this;
    }

    public ColumnConfig<T> setShowTooltip(boolean showTooltip) {
        this.showTooltip = showTooltip;
        return this;
    }

    public ScreenMedia getShowOn() {
        return showOn;
    }

    public ColumnConfig<T> showOn(ScreenMedia showOn) {
        this.showOn = showOn;
        return this;
    }

    public ScreenMedia getHideOn() {
        return hideOn;
    }

    public ColumnConfig<T> hideOn(ScreenMedia hideOn) {
        this.hideOn = hideOn;
        return this;
    }

    void applyHeaderStyle() {
        headerStyler.styleCell(headElement);
    }

    void applyCellStyle(HTMLTableCellElement element) {
        cellStyler.styleCell(element);
    }

    public CellStyler<T> getHeaderStyler() {
        return headerStyler;
    }

    public CellStyler<T> getCellStyler() {
        return cellStyler;
    }

    public boolean isShowTooltip() {
        return showTooltip;
    }

    @FunctionalInterface
    public interface CellStyler<T> {
        void styleCell(HTMLTableCellElement element);
    }

}
