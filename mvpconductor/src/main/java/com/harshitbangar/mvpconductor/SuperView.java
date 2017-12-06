package com.harshitbangar.mvpconductor;

import android.view.ViewGroup;

public interface SuperView<C extends BaseController<C, U>, U extends ViewGroup & SuperView<C, U>> {
  void setController(C controller);
  C getController();
}
