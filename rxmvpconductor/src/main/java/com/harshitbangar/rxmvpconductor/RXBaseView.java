package com.harshitbangar.rxmvpconductor;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RXBaseView<C extends RXBaseController> extends FrameLayout {
  private C controller;

  public RXBaseView(@NonNull Context context) {
    super(context);
  }

  public RXBaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public RXBaseView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public RXBaseView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
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
