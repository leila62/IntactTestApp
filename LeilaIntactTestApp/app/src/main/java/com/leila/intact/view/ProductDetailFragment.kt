package com.leila.intact.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.leila.intact.coordinator.BaseFragment
import com.leila.intact_core.model.model.ProductEntity


/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

public class ProductDetailFragment : BaseFragment() {
    private val TAG = ProductListFragment::class.java.simpleName

    companion object {
        fun newInstance(@NonNull product: ProductEntity.Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            fragment.product = product
            return fragment
        }
    }

    private var product: ProductEntity.Product? = null

    override fun getFragmentTag(): String {
        return TAG;
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}