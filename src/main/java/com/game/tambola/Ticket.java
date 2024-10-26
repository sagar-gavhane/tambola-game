package com.game.tambola;

import java.util.*;

public class Ticket {
    private final Cell[][] cells = new Cell[3][9];
    private final NumberDrawer numberDrawer = new NumberDrawer();
    private final Map<Integer, Cell> map = new HashMap<>();
    private Integer crossedCounter = 0;
    private Integer topLineCounter = 0;
    private Integer middleLineCounter = 0;
    private Integer bottomLineCounter = 0;

    public Ticket() {
        initializeCells();
        populateCells();
    }

    private void initializeCells() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                cells[row][column] = new Cell(null, -1, -1);
            }
        }
    }

    private void populateCells() {
        for (int row = 0; row < 3; row++) {
            Set<Integer> filledColumns = new HashSet<>();
            int counter = 0;

            while (counter < 5) {
                int column = new Random().nextInt(9);

                if (!filledColumns.contains(column)) {
                    int drawnNumber = drawNumberForColumn(column);
                    cells[row][column] = new Cell(drawnNumber, row, column);
                    map.put(drawnNumber, cells[row][column]);
                    filledColumns.add(column);
                    counter++;
                }
            }
        }
    }

    private int drawNumberForColumn(int column) {
        int minRange = column * 10 + 1;
        int maxRange = column * 10 + 9;
        return numberDrawer.draw(minRange, maxRange, 0);
    }

    public void crossCell(int drawnNumber) {
        Cell cell = map.get(drawnNumber);

        if (cell != null && !cell.isCrossed()) {
            updateCounters(cell);
            cell.cross();
            crossedCounter++;
        }
    }

    private void updateCounters(Cell cell) {
        switch (cell.getRowId()) {
            case 0 -> topLineCounter++;
            case 1 -> middleLineCounter++;
            case 2 -> bottomLineCounter++;
        }
    }

    public void print(String name) {
        System.out.println("🤽" + name + "'s ticket");
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                String value = (cell.getValue() != null) ? cell.getValue().toString() : "__";
                System.out.print(String.format("%-4s", value));
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getCrossedCounter() {
        return crossedCounter;
    }

    public int getTopLineCounter() {
        return topLineCounter;
    }

    public int getMiddleLineCounter() {
        return middleLineCounter;
    }

    public int getBottomLineCounter() {
        return bottomLineCounter;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
