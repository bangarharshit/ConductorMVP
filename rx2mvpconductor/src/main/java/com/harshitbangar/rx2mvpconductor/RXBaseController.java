package com.harshitbangar.rx2mvpconductor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.rxlifecycle2.RxController;

/**
 * RXBaseController is a thin wrapper over RXController which tries to generify view.
 * @param <T> the view type.
 */
public abstract class RXBaseController<T extends RXBaseView> extends RxController {

  public RXBaseController() {
    this(null);
  }

  public RXBaseController(@Nullable Bundle args) {
    super(args);
  }

  @NonNull @Override
  protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    T view =  createView(inflater, container);
    view.setController(this);
    return view;
  }

  protected abstract T createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

  @Override protected final void onAttach(@NonNull View view) {
    attachView((T) view);
  }

  @Override protected final void onDetach(@NonNull View view) {
    detchView((T) view);
  }

  @Override protected final void onDestroyView(@NonNull View view) {
    destroyView((T) view);
  }

  protected void attachView(T view) { };

  protected void detchView(T view) { };

  protected void destroyView(T view) { };

  protected T getSafeView() {
    return (T) getView();
  }
}
