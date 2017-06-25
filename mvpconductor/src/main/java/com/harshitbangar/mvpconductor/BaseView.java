package com.harshitbangar.mvpconductor;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Thin wrapper over framelayout. Wrap all your existing views inside it in the xml.
 * @param <C> the controller type.
 */
public class BaseView<C extends BaseController> extends FrameLayout {

  private C controller;

  public BaseView(Context context) {
    super(context);
  }

  public BaseView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public <T> T findView(@IdRes int id) {
    return (T) findViewById(id);
  }

  void setController(C controller) {
    this.controller = controller;
  }

  protected C getController() {
    return controller;
  }
}
