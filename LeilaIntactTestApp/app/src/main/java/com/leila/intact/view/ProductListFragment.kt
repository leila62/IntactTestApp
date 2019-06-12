package com.leila.intact.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.leila.intact.IntactTestApplication
import com.leila.intact.coordinator.BaseFragment
import com.leila.intact_core.model.model.ProductEntity
import com.leila.intact_core.viewModel.ProductViewModel
import javax.inject.Inject


/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

/**
 *
 * Using Kotlin for this fragment because of facility to use ui data binding and less code
 * ***/

class ProductListFragment : BaseFragment() {
    private val TAG = ProductListFragment::class.java.simpleName

    companion object {
        fun newInstance(@NonNull product: ProductEntity.Product): ProductListFragment {
            val fragment = ProductListFragment()
            fragment.product = product
            return fragment
        }

        fun newInstance(): ProductListFragment {
            val fragment = ProductListFragment()
            return fragment
        }
    }


    // Injecting Dependencies
    @set:Inject
    internal var viewModelFactory: ViewModelProvider.Factory? = null

    var productViewModel: ProductViewModel? = null
    private var product: ProductEntity.Product? = null
    private var products: ProductEntity? = null


    override fun getFragmentTag(): String {
        return TAG;
    }

    override fun onStart() {
        super.onStart()
        productViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductViewModel::class.java)
        products = productViewModel!!.getProductEntities()
        Log.i("show data", "" + products)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntactTestApplication.getApplicationComponent(activity!!.applicationContext).inject(this)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}