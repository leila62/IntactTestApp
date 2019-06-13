package com.leila.intact.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.leila.intact.IntactTestApplication
import com.leila.intact.coordinator.BaseFragment
import com.leila.intact.coordinator.NavigationListener
import com.leila.intact.utils.DialogUtils
import com.leila.intact_core.model.model.ProductEntity
import com.leila.intact_core.viewModel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_product_list.*


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
    private var productEntity: ProductEntity? = null
    private var listAdapter: ProductListAdapter? = null
    private var wishListAdapter: WishListAdapter? = null
    private var navigationListener: NavigationListener? = null


    override fun getFragmentTag(): String {
        return TAG;
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
        setupView()
        productViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductViewModel::class.java)
        productViewModel!!.getProductEntities().observe(this, object : Observer<ProductEntity> {
            override fun onChanged(@Nullable p: ProductEntity?) {
                if (p != null) {
                    listAdapter!!.setProducts(p.products)
                }
            }
        })
    }

    private fun setupView() {

        //Setup Product list
        listAdapter = ProductListAdapter(context)
        productListRecyclerView.adapter = listAdapter

        //Setup Wish list
        wishListAdapter = WishListAdapter(context)
        wishListAdapter!!.setProducts((activity as MainActivity).getWishList())
        wishListRecyclerView.adapter = wishListAdapter
        wishListAdapter!!.setItemSelectedListener(object :WishListAdapter.ItemSelectedListener {
            override fun didItemSelected(product: ProductEntity.Product?) {
                navigationListener!!.onNavigateToDetailFragment(product)
            }
        })

        checkoutButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                DialogUtils.showConfirmationDialog(activity, object : DialogPositiveClickListener {
                    override fun didOkButtonClicked() {
                        resetWishList()
                    }
                })
            }
        })

        if (navigationListener == null) {
            navigationListener = activity as NavigationListener
        }


        //Listen to item click
        listAdapter!!.setItemSelectedListener(object : ProductListAdapter.ItemSelectedListener {
            override fun didItemSelected(product: ProductEntity.Product?) {
                navigationListener!!.onNavigateToDetailFragment(product)
            }
        })

        // Check if wish list is empty hide labels
        setLabelsVisibility()

        //Show total amount
        var total: Double = 0.0
        if ((activity as MainActivity).getWishList() != null) {
            for (p: ProductEntity.Product in (activity as MainActivity).getWishList()) {
                total += p.price
            }
        }
        totalTextView.setText("Total $ ${total}")
        subtotalTextView.setText("Sub-total $ ${total}")
    }


    //Helpers
    fun resetWishList(){
        (activity as MainActivity).wishList = ArrayList<ProductEntity.Product>()
        wishListAdapter!!.setProducts((activity as MainActivity).getWishList())
        wishListAdapter!!.notifyDataSetChanged()
        setLabelsVisibility()
    }

    fun setLabelsVisibility(){
        if ((activity as MainActivity).getWishList().size > 0) {
            subtotalTextView.visibility = View.VISIBLE
            shippingTextView.visibility = View.VISIBLE
            totalTextView.visibility = View.VISIBLE
        } else {
            subtotalTextView.visibility = View.GONE
            shippingTextView.visibility = View.GONE
            totalTextView.visibility = View.GONE
        }
    }

    interface DialogPositiveClickListener{
        fun didOkButtonClicked()
    }
}