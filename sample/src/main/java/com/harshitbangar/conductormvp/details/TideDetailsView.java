package com.harshitbangar.conductormvp.details;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.harshitbangar.conductormvp.R;
import com.harshitbangar.mvpconductor.BaseFrameLayoutView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import static com.harshitbangar.conductormvp.R.*;

public class TideDetailsView extends BaseFrameLayoutView<TideDetailsController, TideDetailsView> {

  Toolbar toolbar;
  ProgressBar loading;
  View content;
  TextView currentWaterLevel;
  TextView highestWaterLevel;
  TextView lowestWaterLevel;
  View currentWaterLevelBottomSpacing;
  View currentWaterLevelTopSpacing;

  public TideDetailsView(@NonNull Context context) {
    super(context);
  }

  public TideDetailsView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TideDetailsView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    toolbar = (Toolbar) findViewById(id.toolbar);
    loading = (ProgressBar) findViewById(id.loading);
    content = findViewById(id.tideDetailsContent);
    currentWaterLevel = (TextView) findViewById(id.currentWaterLevel);
    highestWaterLevel = (TextView) findViewById(id.highestWaterLevel);
    lowestWaterLevel = (TextView) findViewById(id.lowestWaterLevel);
    currentWaterLevelBottomSpacing = findViewById(id.currentWaterLevelBottomSpacing);
    currentWaterLevelTopSpacing = findViewById(id.currentWaterLevelTopSpacing);
    setToolbar();
  }

  public void setTideHeights(
      BigDecimal latestMeasuredTideHeight, BigDecimal lowestMeasuredTideHeight,
      BigDecimal highestMeasuredTideHeight) {
    showContent();
    displayWaterLevelText(latestMeasuredTideHeight, lowestMeasuredTideHeight,
        highestMeasuredTideHeight);
    setVisibleWaterLevel(latestMeasuredTideHeight, lowestMeasuredTideHeight,
        highestMeasuredTideHeight);
  }

  private void showContent() {
    content.setAlpha(0f);
    content.setVisibility(VISIBLE);
    loading.animate().alpha(0f).setListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        content.animate().alpha(1f).start();
        loading.setVisibility(GONE);
      }
    }).start();
  }


  private void displayWaterLevelText(
      BigDecimal latestMeasuredTideHeight, BigDecimal lowestMeasuredTideHeight,
      BigDecimal highestMeasuredTideHeight) {
    currentWaterLevel.setText(String.format(Locale.US, "Current Water Level: %.2f ft",
        latestMeasuredTideHeight.setScale(2, RoundingMode.HALF_UP)));
    highestWaterLevel.setText(String.format(Locale.US, "Today's Highest Water Level: %.2f ft",
        highestMeasuredTideHeight.setScale(2, RoundingMode.HALF_UP)));
    lowestWaterLevel.setText(String.format(Locale.US, "Today's Lowest Water Level: %.2f ft",
        lowestMeasuredTideHeight.setScale(2, RoundingMode.HALF_UP)));
  }

  private void setVisibleWaterLevel(
      BigDecimal latestMeasuredTideHeight, BigDecimal lowestMeasuredTideHeight,
      BigDecimal highestMeasuredTideHeight) {
    float percentOfMax = latestMeasuredTideHeight.subtract(lowestMeasuredTideHeight)
        .divide(highestMeasuredTideHeight.subtract(lowestMeasuredTideHeight), RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).floatValue();
    currentWaterLevelBottomSpacing.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, 2, percentOfMax));
    currentWaterLevelTopSpacing.setLayoutParams(new LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, 2, 100 - percentOfMax));
  }

  private void setToolbar() {
    toolbar.inflateMenu(menu.tide_details);
    toolbar.setNavigationIcon(drawable.ic_arrow_back_white_24px);
    toolbar.setNavigationOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        getController().goBack();
      }
    });
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == id.delete) {
          Toast.makeText(TideDetailsView.this.getContext(), "Deleting", Toast.LENGTH_LONG).show();
        }
        return false;
      }
    });
  }

  public void setTitle(String title) {
    toolbar.setTitle(title);
  }

  public void showError() {
    Toast.makeText(getContext(), string.cannot_retrieve_tide_info, Toast.LENGTH_SHORT).show();
  }
}
