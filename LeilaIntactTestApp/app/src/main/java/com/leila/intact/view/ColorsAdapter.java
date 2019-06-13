package com.leila.intact.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.leila.intact_core.model.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

/***
 * Adapter to show colors
 * **/
public class ColorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ProductEntity.Product.Color> colors = new ArrayList<>();

    public ColorsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_colors, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductEntity.Product.Color color = colors.get(position);
        ColorViewHolder colorViewHolder = (ColorViewHolder) holder;
        if (colorViewHolder != null) {
            bindView(colorViewHolder, color);
        }
    }

    private void bindView(ColorViewHolder colorViewHolder, ProductEntity.Product.Color color) {
        colorViewHolder.colorLayout.setBackgroundColor(Color.parseColor(color.getCode()));
    }

    @Override
    public int getItemCount() {
        if (colors != null) {
            return colors.size();
        } else {
            return 0;
        }
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder {
        CardView colorLayout;

        public ColorViewHolder(View itemView) {
            super(itemView);
            colorLayout = itemView.findViewById(R.id.colorLayout);
        }
    }

    public void setColors(List<ProductEntity.Product.Color> colors) {
        this.colors = colors;
    }
}
