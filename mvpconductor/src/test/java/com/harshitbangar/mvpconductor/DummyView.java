package com.harshitbangar.mvpconductor;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by harshitbangar on 25/06/17.
 */
public class DummyView extends BaseView<DummyController, DummyView> {

  public DummyView(Context context) {
    super(context);
  }

  public DummyView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public DummyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public DummyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }
}
