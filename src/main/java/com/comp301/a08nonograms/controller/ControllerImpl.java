package com.comp301.a08nonograms.controller;

import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;

public class ControllerImpl implements Controller {

  private Model currModel;

  public ControllerImpl(Model model) {
    currModel = model;
  }

  @Override
  public Clues getClues() {
    return currModel.getActivePuzzleClues();
  }

  @Override
  public boolean isSolved() {
    return currModel.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return currModel.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return currModel.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    currModel.getActiveBoard().toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    currModel.getActiveBoard().toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    int index = currModel.getPuzzleIndex() + 1;
    if (index == currModel.getPuzzleCount()) {
      index = 0;
    }
    currModel.setPuzzleIndex(index);
  }

  @Override
  public void prevPuzzle() {
    int index = currModel.getPuzzleIndex() - 1;
    if (index == -1) {
      index = currModel.getPuzzleCount() - 1;
    }
    currModel.setPuzzleIndex(index);
  }

  @Override
  public void randPuzzle() {
    int index = (int) (Math.random() * currModel.getPuzzleCount() - 1);
    while (index == currModel.getPuzzleIndex()) {
      index = (int) (Math.random() * currModel.getPuzzleCount() - 1);
    }
    currModel.setPuzzleIndex(index);
  }

  @Override
  public void clearBoard() {
    currModel.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return currModel.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return currModel.getPuzzleCount();
  }
}
