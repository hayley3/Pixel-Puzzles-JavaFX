package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class IsSolvedBox implements FXComponent {

  private final Controller controller;

  public IsSolvedBox(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane boardHolder = new StackPane();
    GridPane board = new GridPane();

    // adding row labels
    for (int i = 0; i < controller.getClues().getHeight(); i++) {
      CluesCell rowTitle = new CluesCell(controller, controller.getClues().getRowClues(i));
      board.add(rowTitle.render(), 0, i + 1, 1, 1);
    }

    // adding col labels
    for (int i = 0; i < controller.getClues().getWidth(); i++) {
      ColCluesCell colTitle = new ColCluesCell(controller, controller.getClues().getColClues(i));
      board.add(colTitle.render(), i + 1, 0, 1, 1);
    }

    for (int i = 0; i < controller.getClues().getHeight(); i++) {
      for (int j = 0; j < controller.getClues().getWidth(); j++) {
        Button cell = new Button();
        cell.setStyle("-fx-background-color: #FFFFFF; ");
        cell.setText(" ");
        board.add(cell, j + 1, i + 1, 1, 1);
        int currRow = i;
        int currCol = j;

        cell.setOnMouseClicked(
            (MouseEvent event) -> {
              Label congrats = new Label("Solved!");
              congrats.setStyle("-fx-background-color: SKYBLUE;");
              congrats.setMinWidth(50);
              congrats.setMinHeight(50);
              congrats.setAlignment(Pos.CENTER);
              GridPane holder = board;
              boardHolder.getChildren().clear();
              boardHolder.getChildren().add(holder);

              if (event.getButton() == MouseButton.PRIMARY) {
                controller.toggleShaded(currRow, currCol);
                if (controller.isShaded(currRow, currCol)) {
                  cell.setStyle("-fx-background-color: #000000; ");
                } else if (!controller.isEliminated(currRow, currCol)) {
                  cell.setStyle("-fx-background-color: #FFFFFF; ");
                }
              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.toggleEliminated(currRow, currCol);
                if (controller.isEliminated(currRow, currCol)) {
                  cell.setStyle("-fx-background-color: #FF0000; ");
                } else if (!controller.isShaded(currRow, currCol)) {
                  cell.setStyle("-fx-background-color: #FFFFFF; ");
                }
              }
              if (controller.isSolved()) {
                boardHolder.getChildren().add(congrats);
              }
            });
      }
    }

    board.setPadding(new Insets(10, 10, 10, 10));
    board.setAlignment(Pos.CENTER);
    board.setGridLinesVisible(true);
    boardHolder.getChildren().add(board);
    return boardHolder;
  }
}
