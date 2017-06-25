package com.harshitbangar.conductormvp.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.harshitbangar.rx2mvpconductor.BuildConfig;
import com.harshitbangar.rx2mvpconductor.RXBaseController;
import com.harshitbangar.rx2mvpconductor.RXBaseView;

import static com.harshitbangar.conductormvp.DemoApplication.app;

public abstract class RefWatcherController<T extends RXBaseView> extends RXBaseController<T> {

  public RefWatcherController() {
    this(null);
  }

  public RefWatcherController(Bundle args) {
    super(args);
  }

  @Override protected abstract T createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

  @Override @CallSuper protected void onDestroy() {
    super.onDestroy();
    if (BuildConfig.DEBUG) {
      app(getApplicationContext()).refWatcher().watch(this);
    }
  }
}
