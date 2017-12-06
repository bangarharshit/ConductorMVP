package com.harshitbangar.mvpconductor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BaseLinearLayoutView<T extends BaseController<T, U>, U extends BaseLinearLayoutView<T, U>> extends
    LinearLayout implements SuperView<T, U> {

  private T controller;
  public BaseLinearLayoutView(Context context) {
    super(context);
  }

  public BaseLinearLayoutView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BaseLinearLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public final void setController(T controller) {
    this.controller = controller;
  }

  @Override public final T getController() {
    return controller;
  }
}
