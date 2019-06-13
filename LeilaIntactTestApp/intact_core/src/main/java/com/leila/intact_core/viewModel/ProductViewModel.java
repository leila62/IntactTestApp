package com.leila.intact_core.viewModel;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.leila.intact_core.model.model.ProductEntity;
import com.leila.intact_core.model.repository.ProductRepository;

import javax.inject.Inject;

public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;

    @Inject
    public ProductViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Load data from asset file
     *
     */
    public LiveData<ProductEntity> getProductEntities(){
        return productRepository.fetchDataFromFile();
    }
}
