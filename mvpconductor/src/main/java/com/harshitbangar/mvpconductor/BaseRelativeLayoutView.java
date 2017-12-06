package com.harshitbangar.mvpconductor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class BaseRelativeLayoutView<T extends BaseController<T, U>, U extends BaseLinearLayoutView<T, U>> extends
    RelativeLayout implements SuperView<T, U> {

  private T controller;
  public BaseRelativeLayoutView(Context context) {
    super(context);
  }

  public BaseRelativeLayoutView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BaseRelativeLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public final void setController(T controller) {
    this.controller = controller;
  }

  @Override public final T getController() {
    return controller;
  }
}

