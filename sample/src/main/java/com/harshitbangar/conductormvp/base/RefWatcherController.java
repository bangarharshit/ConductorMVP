package com.harshitbangar.conductormvp.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import com.harshitbangar.mvpconductor.BaseView;
import com.harshitbangar.rx2mvpconductor.BuildConfig;
import com.harshitbangar.rx2mvpconductor.RxBaseController;

import static com.harshitbangar.conductormvp.DemoApplication.app;

public abstract class RefWatcherController<T extends RefWatcherController<T, U>, U extends BaseView<T, U>> extends RxBaseController<T, U> {

  public RefWatcherController() {
    this(null);
  }

  public RefWatcherController(Bundle args) {
    super(args);
  }

  @Override @CallSuper protected void onDestroy() {
    super.onDestroy();
    if (BuildConfig.DEBUG) {
      app(getApplicationContext()).refWatcher().watch(this);
    }
  }
}
