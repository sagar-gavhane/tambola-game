package com.game.tambola;

public class Cell {
    private final Integer value;
    private final int rowId;
    private final int columnId;
    private boolean crossed;

    public Cell(Integer value, int rowId, int columnId) {
        this.value = value;
        this.rowId = rowId;
        this.columnId = columnId;
        this.crossed = false;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isCrossed() {
        return crossed;
    }

    public void cross() {
        this.crossed = true;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }
}
