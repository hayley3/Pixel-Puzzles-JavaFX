package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HeaderBox implements FXComponent {

  private final Controller controller;
  private HBox layout;

  public HeaderBox(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    if (layout != null) {
      return layout;
    }

    layout = new HBox();
    layout.setAlignment(Pos.CENTER);

    Button prev = new Button("\u25C4");
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });
    layout.getChildren().add(prev);

    layout
        .getChildren()
        .add(
            new Label(
                "Puzzle " + (controller.getPuzzleIndex() + 1) + "/" + controller.getPuzzleCount()));

    Button next = new Button("\u25BA");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    layout.getChildren().add(next);

    HBox addition = new HBox();
    addition.setAlignment(Pos.BOTTOM_CENTER);

    Button randomButton = new Button("Random");
    randomButton.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });
    addition.getChildren().add(randomButton);

    Button reset = new Button("Reset");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });
    addition.getChildren().add(reset);

    return layout;
  }
}
