package com.leila.intact.coordinator;


import com.leila.intact_core.model.model.ProductEntity;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

public interface NavigationListener {

    void onNavigateToDetailFragment(ProductEntity.Product product);
    void onNavigateToListFragment();
}
