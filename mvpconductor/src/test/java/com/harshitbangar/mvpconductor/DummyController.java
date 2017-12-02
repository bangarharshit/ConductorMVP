package com.harshitbangar.mvpconductor;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DummyController extends BaseController<DummyController, DummyView> {

  @Override
  protected DummyView createView(@NonNull LayoutInflater inflater, @NonNull
      ViewGroup container) {
    return new DummyView(getApplicationContext());
  }
}
