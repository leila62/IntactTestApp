package com.leila.intact.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.leila.intact.coordinator.BaseFragment
import com.leila.intact.coordinator.NavigationListener
import com.leila.intact_core.model.model.ProductEntity
import kotlinx.android.synthetic.main.fragment_product_detail.*


/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

class ProductDetailFragment : BaseFragment() {
    private val TAG = ProductDetailFragment::class.java.simpleName

    companion object {
        fun newInstance(@NonNull product: ProductEntity.Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            fragment.product = product
            return fragment
        }
    }

    private var product: ProductEntity.Product? = null
    private var navigationListener: NavigationListener? = null
    private var colorsAdapter: ColorsAdapter? = null

    override fun getFragmentTag(): String {
        return TAG
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        if (navigationListener == null) {
            navigationListener = activity as NavigationListener
        }

        //To set button color
        addWishListButton.isSelected = (activity as MainActivity).getWishList().any { it -> it.id == product!!.id }

        // TODO : save on sharePreferences or.. to keep wish list if needed
        addWishListButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if ((activity as MainActivity).getWishList().any { it -> it.id == product!!.id }) {
                    (activity as MainActivity).removeFromWishList(product)
                    navigationListener!!.onNavigateToListFragment()
                } else {
                    (activity as MainActivity).addToWishList(product)
                    navigationListener!!.onNavigateToListFragment()
                }
            }
        })
    }

    fun setupView() {
        priceTextView.setText("$" + product!!.price.toString())
        shortDescriptionTextView.setText(product!!.short_description)
        descriptionTextView.setText(product!!.description)
        measureTextView.setText(product!!.size.h + "\n" + product!!.size.d + "\n" + product!!.size.w)

        //Load images
        //TODO: Needs to be done in the correct way ! images are coming from google drive,
        // so need to an image downloader service, then they can be loaded by Glide or ...
        // for now they are not loaded dynamicly

        when (product!!.getId()) {
            1, 2 -> productDetailImageView.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.one))
            3 -> productDetailImageView.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.three))
            4 -> productDetailImageView.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.four))
            5 -> productDetailImageView.setImageDrawable(ContextCompat.getDrawable(this.context!!, R.drawable.five))
        }

        //Setup color list UI
        colorsAdapter = ColorsAdapter(context)
        colorsAdapter!!.setColors(product!!.colors)
        colorsRecyclerView.adapter = colorsAdapter
    }
}