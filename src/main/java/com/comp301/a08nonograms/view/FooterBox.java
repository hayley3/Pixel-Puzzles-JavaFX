package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FooterBox implements FXComponent {

  private final Controller controller;
  private HBox addition;
  private String answer = "You have ";

  public FooterBox(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    if (addition != null) {
      return addition;
    }

    GridPane temp = new GridPane();

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

    temp.add(addition, 0, 1, 1, 1);

    return temp;
  }
}
