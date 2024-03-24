package com.example.kingsportswear.presentation.product_detail;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kingsportswear.R;
import com.example.kingsportswear.utils.listener.ItemListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class SizeSelectorBottomSheet extends BottomSheetDialogFragment {
    private List<String> sizes;
    private ItemListener itemListener;

    public SizeSelectorBottomSheet(List<String> sizes, ItemListener itemListener) {
        this.sizes = sizes;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        ImageView closeBtn = bottomSheetDialog.findViewById(R.id.bottom_sheet_close_btn);
        closeBtn.setOnClickListener(v -> dismiss());
        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.bottom_sheet_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SizeSelectorAdapter sizeSelectorAdapter = new SizeSelectorAdapter(sizes, new ItemListener() {
            @Override
            public void onItemClick(int position) {
                itemListener.onItemClick(position);
                dismiss();
            }
        });
        recyclerView.setAdapter(sizeSelectorAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        return bottomSheetDialog;
    }
}
