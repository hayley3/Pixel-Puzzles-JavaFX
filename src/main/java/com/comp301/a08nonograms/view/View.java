package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class View implements FXComponent {

  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane layout = new BorderPane();

    // create the top part with prev, next, current puzzle index
    HeaderBox header = new HeaderBox(controller);
    layout.setTop(header.render());

    GridPane bottom = new GridPane();
    // IsSolvedBox isSolved = new IsSolvedBox(controller);
    // bottom.add(isSolved.render(), 0, 0, 1, 1);

    // create the footer with reset button and random
    FooterBox footer = new FooterBox(controller);
    bottom.add(footer.render(), 0, 1, 1, 1);
    bottom.setAlignment(Pos.CENTER);
    layout.setBottom(bottom);

    IsSolvedBox solved = new IsSolvedBox(controller);
    layout.setCenter(solved.render());

    return layout;
  }
}
