package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ColCluesCell implements FXComponent {

  private final Controller controller;
  private int[] clues;

  public ColCluesCell(Controller controller, int[] clues) {
    this.controller = controller;
    this.clues = clues;
  }

  @Override
  public Parent render() {
    GridPane clueCell = new GridPane();
    int temp = 0;
    for (int i = 0; i < clues.length; i++) {
      if (clues[i] == 0) {
        Text holderSpace = new Text("  ");
        clueCell.add(holderSpace, 0, i, 1, 1);
      } else {
        Text holder = new Text(clues[i] + " ");
        clueCell.add(holder, 0, i, 1, 1);
      }
      temp = i;
    }

    if (clues[clues.length - 1] == 0) {
      Text allZeros = new Text("0");
      clueCell.add(allZeros, 0, temp, 1, 1);
    }

    return clueCell;
  }
}
