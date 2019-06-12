package com.leila.intact.dependency;
/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leila.intact_core.viewModel.ProductViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel.class)
    abstract ViewModel bindProductViewModel(ProductViewModel productViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
