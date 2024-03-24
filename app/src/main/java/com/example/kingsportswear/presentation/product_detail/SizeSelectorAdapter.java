package com.example.kingsportswear.presentation.product_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingsportswear.R;
import com.example.kingsportswear.utils.listener.ItemListener;

import java.util.List;

public class SizeSelectorAdapter extends RecyclerView.Adapter<SizeSelectorAdapter.SizeSelectorViewHolder>{
    private List<String> sizes;
    private ItemListener itemListener;

    public SizeSelectorAdapter(List<String> sizes, ItemListener itemListener) {
        this.sizes = sizes;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public SizeSelectorAdapter.SizeSelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item, parent, false);
        return new SizeSelectorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeSelectorAdapter.SizeSelectorViewHolder holder, int position) {
        String size = sizes.get(position);
        holder.sizeItem.setText(size);
        holder.sizeItem.setOnClickListener(v -> {
            if (itemListener != null) {
                itemListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sizes != null ? sizes.size() : 0;
    }

    public class SizeSelectorViewHolder extends RecyclerView.ViewHolder {
        TextView sizeItem;
        public SizeSelectorViewHolder(@NonNull View itemView) {
            super(itemView);
            sizeItem = itemView.findViewById(R.id.bottom_sheet_item_text_view);
            itemView.setOnClickListener(v -> {
                if (itemListener != null) {
                    itemListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
