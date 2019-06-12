package com.leila.intact.dependency;


import android.app.Application;

import com.leila.intact.IntactTestApplication;
import com.leila.intact_core.model.repository.ProductRepository;

import javax.inject.Singleton;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {
    private IntactTestApplication application;

    public ApplicationModule(IntactTestApplication application) {
        this.application = application;
    }
    @Singleton
    @Provides
    Application provideIntactTestApplication() {
        return application;
    }

    @Provides
    @Singleton
    public ProductRepository providesProductRepository() {
        return new ProductRepository(application);
    }
}
