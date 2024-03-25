package com.example.kingsportswear.presentation.product_list;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.databinding.FragmentProductListBinding;
import com.example.kingsportswear.presentation.core.CoreFragment;
import com.example.kingsportswear.presentation.product_detail.ProductDetailFragment;
import com.example.kingsportswear.utils.listener.ItemListener;

import java.util.List;

public class ProductListFragment extends Fragment implements ItemListener {
    private FragmentProductListBinding binding;
    private List<Product> products;
    private String searchKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        if (getArguments() != null) {
            searchKey = getArguments().getString("searchKey");
            if (searchKey == null || searchKey.isEmpty())
                searchKey = getString(R.string.search_results);
            products = getArguments().getParcelableArrayList("products");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        binding.appBarTitle.setText(searchKey);
        if(products != null && !products.isEmpty()) {
            ProductListRecycleViewAdapter adapter = new ProductListRecycleViewAdapter(products, getContext());
            adapter.setClickListener(this);
            binding.productListRecyclerView.setAdapter(adapter);
        }
        else {
            binding.productListRecyclerView.setVisibility(View.GONE);
            binding.noResultFoundLayout.setVisibility(View.VISIBLE);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.actionBackProductList.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(ProductListFragment.this).popBackStack();
        });
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", products.get(position));
        NavHostFragment.findNavController(ProductListFragment.this)
                .navigate(R.id.action_ProductListFragment_to_ProductDetailFragment, bundle);
    }
}