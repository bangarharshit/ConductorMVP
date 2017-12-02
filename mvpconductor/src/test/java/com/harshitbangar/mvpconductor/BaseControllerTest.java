package com.harshitbangar.mvpconductor;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class BaseControllerTest {
  private DummyController dummyController = new DummyController();

  @Test
  public void setView() {
    DummyView dummyView = (DummyView) dummyController.onCreateView(null, null);
    Assert.assertEquals(dummyController, dummyView.getController());
  }
}
