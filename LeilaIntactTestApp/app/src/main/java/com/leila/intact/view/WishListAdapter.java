package com.leila.intact.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.leila.intact_core.model.model.ProductEntity;

import java.util.List;

/**
 * Created by Leila Faraghi on 2019-06-12.
 * faraghi.l@gmail.com
 */

public class WishListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductEntity.Product> products;
    private ItemSelectedListener itemSelectedListener;


    public WishListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_wish_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ProductEntity.Product product = products.get(position);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;

        //TODO: Needs to be done in the correct way ! images are coming from google drive,
        // so need to an image downloader service, then they can be loaded by Glide or ...
        // for now they are not loaded dynamicly
        switch (product.getId()) {
            case 1:
            case 2:
                productViewHolder.productImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.one));
                break;
            case 3:
                productViewHolder.productImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.three));
                break;
            case 4:
                productViewHolder.productImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.four));
                break;
            case 5:
                productViewHolder.productImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.five));
                break;

        }
        if (productViewHolder != null) {
            bindView(productViewHolder, product);
        }
    }

    private void bindView(ProductViewHolder productViewHolder, final ProductEntity.Product product) {
        productViewHolder.titleTextView.setText(product.getTitle());
        productViewHolder.priceTextView.setText("$ "+product.getPrice());
        productViewHolder.descriptionTextView.setText(product.getShort_description());
        productViewHolder.quantityTextView.setVisibility(product.getQuantity()>0 ? View.GONE : View.VISIBLE);
        ColorsAdapter colorsAdapter = new ColorsAdapter(context);
        colorsAdapter.setColors(product.getColors());
        productViewHolder.wishColorRecyclerview.setAdapter(colorsAdapter);
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemSelectedListener != null) {
                    itemSelectedListener.didItemSelected(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView,priceTextView,descriptionTextView,quantityTextView;
        RecyclerView wishColorRecyclerview;
        ImageView productImageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.wishTitleTextView);
            productImageView = itemView.findViewById(R.id.wishProductImageView);
            priceTextView = itemView.findViewById(R.id.wishPriceTextView);
            descriptionTextView = itemView.findViewById(R.id.wishDescriptionTextView);
            quantityTextView = itemView.findViewById(R.id.wishQuantityTextView);
            wishColorRecyclerview = itemView.findViewById(R.id.wishColorListRecyclerView);
        }
    }

    public void setItemSelectedListener(ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }


    public void setProducts(List<ProductEntity.Product> products) {
        this.products = products;
    }

    public interface ItemSelectedListener {
        void didItemSelected(ProductEntity.Product product);
    }
}

