package com.leila.intact;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import android.app.Application;
import android.content.Context;

import com.leila.intact.dependency.AppComponent;
import com.leila.intact.dependency.ApplicationModule;
import com.leila.intact.dependency.DaggerAppComponent;


public class IntactTestApplication extends Application {
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        IntactTestApplication.getApplicationComponent(this).inject(this);

    }

    protected ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }

    public static AppComponent getApplicationComponent(Context context) {
        IntactTestApplication application = (IntactTestApplication) context.getApplicationContext();
        if (application.appComponent == null) {
            application.appComponent = DaggerAppComponent.builder()
                    .applicationModule(application.getApplicationModule())
                    .build();
        }
        return application.appComponent;
    }
}
