# Conductor MVP
It is a tiny library (17kb  and just contain 2 classes) to add MVP support to [Conductor](https://github.com/bluelinelabs/Conductor). 
It achieves the same by removing view logic into a separate class.

## Design considerations
1. Controller should only contain the logic.
2. All the view related code will be moved into a separate class.
3. Controller can access view with the correct type. Currently, getView returns either View or null (if it is destroyed or not created).
4. Similarly, the view also has access to the controller with the correct type.
5. Controller survives rotation but the view is recreated. It is safe to access controller in view by getController() 
but it is unsafe to directly access view in the controller by getView(). It should be either null checked or used in combination with RXLifeCycle.


## Installation

Add to your project/build.gradle
``` 
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add to your app/build.gradle
```

compile 'com.github.bangarharshit.ConductorMVP:mvpconductor:v0.11'

// If you are using conductor-rxlifecycle
compile 'com.github.bangarharshit.ConductorMVP:rxmvpconductor:v0.11'

// If you are using conductor-rxlifecycle2
compile 'com.github.bangarharshit.ConductorMVP:rx2mvpconductor:v0.11'

// Soon - Autodispose support
```

## Show me some code code.

### Minimal Activity Implementation
```java
public class MainActivity extends AppCompatActivity {

  private Router router;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewGroup container = (ViewGroup)findViewById(R.id.controller_container);

    router = Conductor.attachRouter(this, container, savedInstanceState);
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(new HomeController()));
    }
  }

  @Override
  public void onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed();
    }
  }
}
```

### Minimal Controller Implementation
Use BaseController (RXBaseController) instead of Controller(RXController).
```java
import com.harshitbangar.mvpconductor.BaseController;

public class HomeController extends BaseController<HomeView> {

  @Override
  protected HomeView createView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return (HomeView) inflater.inflate(R.layout.home, container, false);
  }

  public void backPress() {
    getRouter().popCurrentController();
  }
}
```

### Minimal View Implementation
Use BaseView (or RXBaseView).
```java
import com.harshitbangar.mvpconductor.BaseView;
    
class HomeView extends BaseView<HomeController> {
  public HomeView(Context context) {
    super(context);
    inflate(context, R.layout.home, this);
    Toolbar toolbar = findView(R.id.toolbar);
    toolbar.setNavigationOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        getController().backPress();
      }
    });
  }
}
```

### Layout for view
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.harshitbangar.conductormvp.HomeView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

  <android.support.v7.widget.Toolbar
      android:layout_width="match_parent"
      android:background="?colorPrimary"
      android:layout_height="wrap_content"/>
  <GridView
      android:id="@+id/tideLocationsGrid"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:numColumns="2"
      />

</com.harshitbangar.conductormvp.HomeView>
```

### Caveats
1. Use getSafeView instead of getView for accessing view with type.
2. Similarly, all the corresponding lifeCycle methods are copied into a type-safe method. onCreateView(View view) became createView(T view)
3. Use getController to access controller in view.
4. All the views should extend BaseView and therefore it must be the parent in xml or the xml is inflated into it.
