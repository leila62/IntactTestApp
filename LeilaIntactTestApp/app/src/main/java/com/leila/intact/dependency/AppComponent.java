package com.leila.intact.dependency;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import com.leila.intact.IntactTestApplication;
import com.leila.intact.view.MainActivity;
import com.leila.intact.view.ProductListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {
    void inject(IntactTestApplication target);
    void inject(MainActivity target);
    void inject(ProductListFragment target);
}