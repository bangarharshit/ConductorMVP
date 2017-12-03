package com.harshitbangar.conductormvp.locations;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.harshitbangar.conductormvp.R;
import com.harshitbangar.conductormvp.model.TideLocations;
import com.harshitbangar.mvpconductor.BaseView;

public class TideLocationsView extends BaseView<TideLocationsController, TideLocationsView> {

  GridView tideLocationsList;

  public TideLocationsView(@NonNull Context context) {
    super(context);
  }

  public TideLocationsView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public TideLocationsView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public TideLocationsView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    tideLocationsList = findView(R.id.tideLocationsGrid);
    tideLocationsList.setAdapter(new TideLocationsListAdapter(getContext()));
  }

  private final class TideLocationsListAdapter extends ArrayAdapter<TideLocations> {

    private TideLocationsListAdapter(@NonNull Context context) {
      super(context, R.layout.tide_location_grid_item, R.id.tideLocationName,
          TideLocations.values());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      if (convertView == null) {
        convertView = inflate(getContext(), R.layout.tide_location_grid_item, null);
      }
      final TideLocations tideLocation = getItem(position);
      ImageView tideLocationImageView = (ImageView) convertView.findViewById(R.id.tideLocationImage);
      TextView tideLocationTextView = (TextView) convertView.findViewById(R.id.tideLocationName);

      tideLocationImageView.setImageResource(tideLocation.getImageId());
      tideLocationTextView.setText(tideLocation.getName());
      convertView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          getController().tideLocationSelected(tideLocation.getNoaaApiId(), tideLocation.getName());
        }
      });
      return convertView;
    }

  }
}
