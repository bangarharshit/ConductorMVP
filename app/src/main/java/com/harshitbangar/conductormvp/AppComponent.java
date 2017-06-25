package com.harshitbangar.conductormvp;

import com.harshitbangar.conductormvp.details.TideDetailsController;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

  void inject(MainActivity activity);

  void inject(TideDetailsController screen);
}
