package com.comp301.a08nonograms.model;

public class BoardImpl implements Board {

  private int[][] currentBoard;
  private int boardWidth;
  private int boardHeight;
  private Clues activeClues;

  public BoardImpl(Clues clues) {
    activeClues = clues;
    boardWidth = clues.getWidth();
    boardHeight = clues.getHeight();
    currentBoard = new int[boardHeight][boardWidth];
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        currentBoard[i][j] = 0;
      }
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    /* if (row > activeClues.getHeight() || col > activeClues.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    } */

    return (currentBoard[row][col] == 1);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    /* if (row > activeClues.getHeight() || col > activeClues.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    } */

    return (currentBoard[row][col] == 2);
  }

  @Override
  public boolean isSpace(int row, int col) {
    /* if (row > activeClues.getHeight() || col > activeClues.getWidth()) {
      throw new ArrayIndexOutOfBoundsException();
    } */

    return (currentBoard[row][col] == 0);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    /* if (currentBoard[row][col] == 0) {
    if (currentBoard[row][col] == 1) {
      currentBoard[row][col] = 0;
    }*/

    if (currentBoard[row][col] == 0) {
      currentBoard[row][col] = 1;
    } else if (currentBoard[row][col] == 1) {
      currentBoard[row][col] = 0;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (currentBoard[row][col] == 0) {
      currentBoard[row][col] = 2;
    } else if (currentBoard[row][col] == 2) {
      currentBoard[row][col] = 0;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        currentBoard[i][j] = 0;
      }
    }
  }
}
