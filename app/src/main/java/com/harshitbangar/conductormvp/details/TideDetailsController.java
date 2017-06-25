package com.harshitbangar.conductormvp.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.harshitbangar.conductormvp.DemoApplication;
import com.harshitbangar.conductormvp.NoaaApi;
import com.harshitbangar.conductormvp.R;
import com.harshitbangar.conductormvp.base.RefWatcherController;
import com.harshitbangar.conductormvp.model.Observation;
import com.harshitbangar.conductormvp.model.TideInfo;
import com.harshitbangar.conductormvp.utils.BundleBuilder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

public class TideDetailsController extends RefWatcherController<TideDetailsView> {

  private static final Comparator<Observation> OBSERVATION_COMPARATOR = new Comparator<Observation>() {
    @Override
    public int compare(Observation o1, Observation o2) {
      return o1.getVerifiedWaterLevel().compareTo(o2.getVerifiedWaterLevel());
    }
  };

  private static final String TIDE_LOCATION_NAME = "TIDE_LOCATION_NAME";
  private static final String NO_API_ID = "NO_API_ID";

  @Inject NoaaApi noaaApi;
  private final String tideLocationName;
  private int noaaApiId;

  public TideDetailsController(Bundle args) {
    super(args);
    this.noaaApiId = args.getInt(NO_API_ID);
    this.tideLocationName = args.getString(TIDE_LOCATION_NAME);
  }

  public TideDetailsController(int noaaApiId, String tideLocationName) {
    this(new BundleBuilder(new Bundle())
        .putInt(NO_API_ID, noaaApiId)
        .putString(TIDE_LOCATION_NAME, tideLocationName)
        .build());
  }

  @Override protected TideDetailsView createView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container) {
    return (TideDetailsView) inflater.inflate(R.layout.tide_detail, container, false);
  }

  @Override protected void attachView(final TideDetailsView view) {
    super.attachView(view);
    DemoApplication.app(getApplicationContext()).injector().inject(this);
    view.setTitle(tideLocationName);
    noaaApi.getTideInfo(noaaApiId)
        .observeOn(AndroidSchedulers.mainThread())
        .compose(this.<TideInfo>bindToLifecycle())
        .subscribe(new Observer<TideInfo>() {
          @Override public void onSubscribe(@NonNull Disposable d) {

          }

          @Override public void onNext(@NonNull TideInfo tideInfo) {
            if (tideInfo.getData() != null && !tideInfo.getData().isEmpty()) {
              List<Observation> observations = tideInfo.getData();
              BigDecimal highestMeasuredTideHeight =
                  Collections.max(filterOutNullMeasurements(observations), OBSERVATION_COMPARATOR)
                      .getVerifiedWaterLevel();
              BigDecimal lowestMeasuredTideHeight =
                  Collections.min(filterOutNullMeasurements(observations), OBSERVATION_COMPARATOR)
                      .getVerifiedWaterLevel();
              BigDecimal latestMeasuredTideHeight =
                  observations.get(observations.size() - 1).getVerifiedWaterLevel();
              view.setTideHeights(latestMeasuredTideHeight, lowestMeasuredTideHeight,
                  highestMeasuredTideHeight);
            }
          }

          @Override public void onError(@NonNull Throwable e) {
            view.showError();
          }

          @Override public void onComplete() {

          }
        });
  }

  private static List<Observation> filterOutNullMeasurements(List<Observation> listWithNulls) {
    List<Observation> result = new ArrayList<>();
    for (Observation item : listWithNulls) {
      if (item.getVerifiedWaterLevel() != null) {
        result.add(item);
      }
    }
    return result;
  }

  void goBack() {
    getRouter().popCurrentController();
  }
}
