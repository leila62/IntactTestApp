package com.leila.intact.view;
/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leila.intact.IntactTestApplication;
import com.leila.intact.coordinator.BaseFragment;
import com.leila.intact.coordinator.NavigationListener;
import com.leila.intact_core.model.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public List<ProductEntity.Product> wishList = new ArrayList<>();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntactTestApplication.getApplicationComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        /**
         *
         * Navigate to ProductListFragment with no argument
         */
        replaceFragment(ProductListFragment.Companion.newInstance(), true);
    }


    /**
     * Handle all app navigation
     **/
    @Override
    public void onNavigateToDetailFragment(ProductEntity.Product product) {
        replaceFragment(ProductDetailFragment.Companion.newInstance(product), true);
    }

    @Override
    public void onNavigateToListFragment() {
        replaceFragment(ProductListFragment.Companion.newInstance(), true);
    }


    public void addToWishList(ProductEntity.Product product) {
        this.wishList.add(product);
    }
    public void removeFromWishList(ProductEntity.Product product) {
        for (ProductEntity.Product p : wishList){
            if (p.getId() == product.getId()){
                this.wishList.remove(product);
            }
        }
    }

    public List<ProductEntity.Product> getWishList() {
        return wishList;
    }

    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
       // if (fragmentManager.findFragmentByTag(fragment.getFragmentTag()) == null) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(getFragmentRoot(), fragment, fragment.getFragmentTag());
            Log.d(TAG, "Navigate to " + fragment.getFragmentTag());

            if (addToBackStack) {
                transaction.replace(getFragmentRoot(),fragment);
                //transaction.addToBackStack(fragment.getFragmentTag());
            }
            transaction.commit();

//        } else {
//            fragmentManager.popBackStackImmediate(fragment.getFragmentTag(), 0);
//        }
    }

    protected int getFragmentRoot() {
        return R.id.container;
    }
}
