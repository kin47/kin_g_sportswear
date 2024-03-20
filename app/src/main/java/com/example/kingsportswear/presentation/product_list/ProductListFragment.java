package com.example.kingsportswear.presentation.product_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.databinding.FragmentProductListBinding;

import java.util.List;

public class ProductListFragment extends Fragment {
    private FragmentProductListBinding binding;

    private List<Product> products;
    private String searchKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        if (getArguments() != null) {
            searchKey = getArguments().getString("searchKey");
            if (searchKey == null || searchKey.isEmpty()) searchKey = getString(R.string.search_results);
            products = getArguments().getParcelableArrayList("products");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        binding.actionBackProductList.setOnClickListener(view -> NavHostFragment.findNavController(ProductListFragment.this).popBackStack());
        binding.productListRecyclerView.setAdapter(new ProductListRecycleViewAdapter(products, getContext()));
        binding.appBarTitle.setText(searchKey);
        return binding.getRoot();
    }
}