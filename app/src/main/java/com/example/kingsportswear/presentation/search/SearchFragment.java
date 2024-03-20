package com.example.kingsportswear.presentation.search;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.databinding.FragmentSearchBinding;
import com.example.kingsportswear.utils.LoadingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    @Inject
    SearchViewModel viewModel;

    Map<String, String> categories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        categories = new HashMap<>();
        categories.put(getString(R.string.choose_category), "");
        categories.put(getString(R.string.man), "man");
        categories.put(getString(R.string.woman), "woman");
        categories.put(getString(R.string.shoes), "shoes");
        categories.put(getString(R.string.clothes), "clothes");
        categories.put(getString(R.string.bag), "bag");
        categories.put(getString(R.string.others), "others");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding.categorySpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.categories)) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSearch.setOnClickListener(v -> {
            String category = binding.categorySpinner.getSelectedItem().toString();
            String categoryId = categories.get(category);
            viewModel.getProducts(binding.searchProduct.getQuery().toString(), categoryId);
        });

        viewModel.actionState.observe(getViewLifecycleOwner(), actionState -> {
            switch (actionState) {
                case loading:
                    LoadingUtils.showLoading(getContext());
                    break;
                case success:
                    LoadingUtils.hideLoading();
                    Bundle data = new Bundle();
                    data.putString("searchKey", binding.searchProduct.getQuery().toString());
                    data.putParcelableArrayList("products", (ArrayList<Product>) viewModel.products.getValue());
                    NavHostFragment.findNavController(SearchFragment.this).navigate(R.id.action_CoreFragment_to_ProductListFragment, data);
                    break;
                case error:
                    LoadingUtils.hideLoading();
                    Toast.makeText(getContext(), getString(R.string.error_system), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}