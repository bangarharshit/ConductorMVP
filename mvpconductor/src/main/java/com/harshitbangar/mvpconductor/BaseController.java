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
 * @param <T> the controller type.
 * @param <U> the view type.
 */
public abstract class BaseController<T extends BaseController<T, U>, U extends BaseView<T, U>> extends Controller {

  public BaseController() {
    this(null);
  }

  public BaseController(@Nullable Bundle args) {
    super(args);
  }

  @NonNull @Override
  protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    U view =  createView(inflater, container);
    view.setController(getThis());
    return view;
  }

  @SuppressWarnings("unchecked")
  // This will always be true for concrete subclasses so can be safely casted.
  private T getThis() {
    return (T) this;
  }

  protected abstract U createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

  @SuppressWarnings("unchecked") protected final U getSafeView() {
    return (U) getView();
  }

  @SuppressWarnings("unchecked") @Override protected final void onAttach(@NonNull View view) {
    attachView((U) view);
  }

  @SuppressWarnings("unchecked") @Override protected final void onDetach(@NonNull View view) {
    detachView((U) view);
  }

  @SuppressWarnings("unchecked") @Override protected final void onDestroyView(@NonNull View view) {
    destroyView((U) view);
  }

  protected void attachView(U view) { };

  protected void detachView(U view) { };

  protected void destroyView(U view) { };
}
