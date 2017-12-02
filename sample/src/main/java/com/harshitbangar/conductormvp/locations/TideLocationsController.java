package com.harshitbangar.conductormvp.locations;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.RouterTransaction;
import com.harshitbangar.conductormvp.R;
import com.harshitbangar.conductormvp.base.RefWatcherController;
import com.harshitbangar.conductormvp.details.TideDetailsController;

/**
 * Created by harshitbangar on 25/06/17.
 */

public class TideLocationsController extends RefWatcherController<TideLocationsController, TideLocationsView> {

  @Override protected TideLocationsView createView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    return (TideLocationsView) inflater.inflate(R.layout.home, container, false);
  }

  void tideLocationSelected(int noaaApiId, String name) {
    getRouter().pushController(RouterTransaction.with(new TideDetailsController(noaaApiId, name)));
  }
}
