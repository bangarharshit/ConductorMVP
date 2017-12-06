package com.harshitbangar.mvpconductor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BaseFrameLayoutView<T extends BaseController<T, U>, U extends BaseFrameLayoutView<T, U>> extends
    FrameLayout implements SuperView<T, U> {

  private T controller;
  public BaseFrameLayoutView(Context context) {
    super(context);
  }

  public BaseFrameLayoutView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BaseFrameLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public final void setController(T controller) {
    this.controller = controller;
  }

  @Override public final T getController() {
    return controller;
  }
}
