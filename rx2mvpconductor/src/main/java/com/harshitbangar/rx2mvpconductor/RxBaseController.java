package com.harshitbangar.rx2mvpconductor;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.bluelinelabs.conductor.rxlifecycle2.ControllerEvent;
import com.bluelinelabs.conductor.rxlifecycle2.ControllerLifecycleSubjectHelper;
import com.bluelinelabs.conductor.rxlifecycle2.RxControllerLifecycle;
import com.harshitbangar.mvpconductor.BaseController;
import com.harshitbangar.mvpconductor.BaseFrameLayoutView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * RxBaseController is a thin wrapper over {@link BaseController} and tries to add lifecycle support.
 * @param <T> the controller type.
 * @param <U> the view type.
 */
public abstract class RxBaseController<T extends RxBaseController<T, U>, U extends BaseFrameLayoutView<T, U>> extends BaseController<T, U> implements
    LifecycleProvider<ControllerEvent> {
  private final BehaviorSubject<ControllerEvent> lifecycleSubject;

  public RxBaseController() {
    this(null);
  }

  public RxBaseController(@Nullable Bundle args) {
    super(args);
    lifecycleSubject = ControllerLifecycleSubjectHelper.create(this);
  }

  @Override
  @NonNull
  @CheckResult
  public final Observable<ControllerEvent> lifecycle() {
    return lifecycleSubject.hide();
  }

  @Override
  @NonNull
  @CheckResult
  public final <X> LifecycleTransformer<X> bindUntilEvent(@NonNull ControllerEvent event) {
    return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
  }

  @Override
  @NonNull
  @CheckResult
  public final <X> LifecycleTransformer<X> bindToLifecycle() {
    return RxControllerLifecycle.bindController(lifecycleSubject);
  }
}
