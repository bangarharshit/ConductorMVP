package com.harshitbangar.mvpconductor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.Controller;

/**
 * BaseController is a thin wrapper over controller which tries to generify view.
 * @param <T> the view type.
 */
public abstract class BaseController<T extends BaseView> extends Controller {

  public BaseController() {
    this(null);
  }

  public BaseController(@Nullable Bundle args) {
    super(args);
  }

  @NonNull @Override
  protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    T view =  createView(inflater, container);
    view.setController(this);
    return view;
  }

  protected abstract T createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

  protected T getSafeView() {
    return (T) getView();
  }

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
}
