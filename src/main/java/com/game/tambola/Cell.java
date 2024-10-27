package com.game.tambola;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
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
}
