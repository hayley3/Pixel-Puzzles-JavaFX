package com.comp301.a08nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private List<Clues> cluesList;
  private Clues activePuzzle;
  private List<ModelObserver> observers = new ArrayList<>();
  private Board activeBoard;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException("null constructor");
    }

    cluesList = new ArrayList<>();
    cluesList = clues;
    activePuzzle = cluesList.get(0);
    activeBoard = new BoardImpl(activePuzzle);
  }

  public Board getActiveBoard() {
    return activeBoard;
  }

  @Override
  public int getPuzzleCount() {
    return cluesList.size();
  }

  @Override
  public int getPuzzleIndex() {
    return cluesList.indexOf(activePuzzle);
  }

  @Override
  public void setPuzzleIndex(int index) {
    activePuzzle = cluesList.get(index);
    activeBoard = new BoardImpl(activePuzzle);
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {

    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      if (!rowTotal(activePuzzle.getRowClues(i), i)) {
        return false;
      }
    }

    for (int i = 0; i < activePuzzle.getWidth(); i++) {
      if (!colTotal(activePuzzle.getColClues(i), i)) {
        return false;
      }
    }

    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      if (!rowSpaces(activePuzzle.getRowClues(i), i)) {
        return false;
      }
    }

    for (int i = 0; i < activePuzzle.getWidth(); i++) {
      if (!colSpaces(activePuzzle.getColClues(i), i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row >= activePuzzle.getHeight() || col >= activePuzzle.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return activeBoard.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row >= activePuzzle.getHeight() || col >= activePuzzle.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return activeBoard.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row >= activePuzzle.getHeight() || col >= activePuzzle.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return activeBoard.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    activeBoard.toggleCellShaded(row, col);
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    activeBoard.toggleCellEliminated(row, col);
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public void clear() {
    activeBoard.clear();
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  @Override
  public int getWidth() {
    return activePuzzle.getWidth();
  }

  @Override
  public int getHeight() {
    return activePuzzle.getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return activePuzzle.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return activePuzzle.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return activePuzzle.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return activePuzzle.getColCluesLength();
  }

  public boolean rowTotal(int[] arg, int rowNum) {
    int total = 0;
    int needed = 0;

    for (int j : arg) {
      needed = needed + j;
    }

    for (int i = 0; i < activePuzzle.getWidth(); i++) {
      if (activeBoard.isShaded(rowNum, i)) {
        total++;
      }
    }

    if (total == needed) {
      return true;
    }
    return false;
  }

  public boolean colTotal(int[] arg, int colNum) {
    int total = 0;
    int needed = 0;

    for (int j : arg) {
      needed = needed + j;
    }

    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      if (activeBoard.isShaded(i, colNum)) {
        total++;
      }
    }

    if (total == needed) {
      return true;
    }
    return false;
  }

  public boolean rowSpaces(int[] arg, int rowNum) {
    int currCol = 0;

    if (arg[arg.length - 1] == 0) {
      return true;
    }

    for (int x = 0; x < arg.length; x++) {

      int currClue = arg[x];
      while (currClue == 0) {
        x++;
        currClue = arg[x];
      }

      int shaded = 0;

      while (currCol < activePuzzle.getWidth() && !activeBoard.isShaded(rowNum, currCol)) {
        currCol++;
      }

      while (currCol < activePuzzle.getWidth() && activeBoard.isShaded(rowNum, currCol)) {
        currCol++;
        shaded++;
      }

      if (shaded != currClue) {
        return false;
      }
    }
    return true;
  }

  public boolean colSpaces(int[] arg, int colNum) {
    int currRow = 0;

    if (arg[arg.length - 1] == 0) {
      return true;
    }

    for (int x = 0; x < arg.length; x++) {
      int currClue = arg[x];
      while (currClue == 0) {
        x++;
        currClue = arg[x];
      }

      while (currRow < activePuzzle.getHeight() && !activeBoard.isShaded(currRow, colNum)) {
        currRow++;
      }

      int shaded = 0;

      while (currRow < activePuzzle.getHeight() && activeBoard.isShaded(currRow, colNum)) {
        currRow++;
        shaded++;
      }

      if (shaded != currClue) {
        return false;
      }
    }
    return true;
  }

  public Clues getActivePuzzleClues() {
    return activePuzzle;
  }
}
