package com.leila.intact.view;
/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leila.intact.IntactTestApplication;
import com.leila.intact.coordinator.BaseFragment;
import com.leila.intact.coordinator.NavigationListener;
import com.leila.intact_core.model.model.ProductEntity;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
     * **/
    @Override
    public void onNavigateToDetailFragment(ProductEntity.Product product) {
        replaceFragment(ProductDetailFragment.Companion.newInstance(product), true);
    }

    @Override
    public void onNavigateToListFragment(ProductEntity.Product product) {
        replaceFragment(ProductListFragment.Companion.newInstance(product), true);
    }


    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(fragment.getFragmentTag()) == null) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(getFragmentRoot(), fragment, fragment.getFragmentTag());
            Log.d(TAG, "Navigate to " + fragment.getFragmentTag());

            if (addToBackStack) {
                transaction.addToBackStack(fragment.getFragmentTag());
            }
            transaction.commit();

        } else {
            fragmentManager.popBackStackImmediate(fragment.getFragmentTag(), 0);
        }
    }

    protected int getFragmentRoot() {
        return R.id.container;
    }
}
