package com.example.kingsportswear.presentation.product_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kingsportswear.R;
import com.example.kingsportswear.data.model.Product;

import java.util.List;

public class ProductListRecycleViewAdapter extends RecyclerView.Adapter<ProductListRecycleViewAdapter.ProductListViewHolder> {
    private List<Product> products;
    private Context context;

    public ProductListRecycleViewAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListRecycleViewAdapter.ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListRecycleViewAdapter.ProductListViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));

        // Calculate available width for the item
        int availableWidth = (int) (context.getResources().getDisplayMetrics().widthPixels / 2);  // Adjust for margin dimension resource

        // Set image width and height to the calculated width
        holder.productImage.getLayoutParams().width = availableWidth;
        holder.productImage.getLayoutParams().height = availableWidth;

        Glide.with(context)
                .load(product.getImage().get(0))
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName, productPrice;
        ImageView productImage;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_product_list_product_name);
            productPrice = itemView.findViewById(R.id.tv_product_list_product_price);
            productImage = itemView.findViewById(R.id.iv_product_list_product_image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
