package com.harshitbangar.mvpconductor;

import org.junit.Assert;
import org.junit.Test;

public class BaseControllerTest {
  private DummyController dummyController = new DummyController();

  @Test
  public void setView() {
    dummyController.onCreateView(null, null);
    DummyView dummyView = dummyController.getSafeView();
    Assert.assertEquals(dummyController, dummyView.getController());
    Assert.assertEquals(dummyView, dummyController.getSafeView());
  }
}
