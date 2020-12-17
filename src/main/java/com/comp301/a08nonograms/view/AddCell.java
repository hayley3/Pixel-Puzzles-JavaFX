package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class AddCell implements FXComponent {

  private final Controller controller;
  private int row;
  private int col;

  public AddCell(Controller controller, int row, int col) {
    this.controller = controller;
    this.row = row;
    this.col = col;
  }

  @Override
  public Parent render() {
    Button cell = new Button();
    cell.setStyle("-fx-background-color: #FFFFFF; ");
    cell.setText(" ");
    return cell;
  }
}

