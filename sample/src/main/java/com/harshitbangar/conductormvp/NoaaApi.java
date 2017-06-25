package com.harshitbangar.conductormvp;

import com.harshitbangar.conductormvp.model.TideInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoaaApi {

  @GET("/api/datagetter?format=json&application=wealthfront&product=water_level&date=today&datum=mllw&time_zone=GMT&units=english")
  Observable<TideInfo> getTideInfo(@Query("station") int stationId);
}
