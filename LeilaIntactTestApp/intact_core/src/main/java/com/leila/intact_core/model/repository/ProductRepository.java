package com.leila.intact_core.model.repository;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import android.content.Context;

import com.google.gson.Gson;
import com.leila.intact_core.model.model.ProductEntity;

import java.io.IOException;
import java.io.InputStream;

public class ProductRepository {
    private static Context context;
    public final static String ProductFileName = "products.json";

    public ProductRepository(Context context) {
        this.context = context;
    }

    /**
     * Load data from asset file
     *
     * @return
     */

    public ProductEntity fetchDataFromFile() {
        ProductEntity productEntity;
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(ProductFileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * Parsing json by Gson library because it's faster and less code and less exception handling
         * */
        productEntity = new Gson().fromJson(json, ProductEntity.class);
        return productEntity;
    }
}
