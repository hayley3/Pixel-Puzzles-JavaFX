package com.comp301.a08nonograms.model;

public class ModelObserverImpl implements ModelObserver {
  private Model model;

  @Override
  public void update(Model model) {
    this.model = model;
    if (model.isSolved()) {
      System.out.println("solved!");
    }
  }
}
