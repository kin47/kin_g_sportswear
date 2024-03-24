package com.example.kingsportswear.presentation.product_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.common.CommonFunctions;
import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.databinding.FragmentProductDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {
    private Product product;

    private FragmentProductDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        if (getArguments() != null) {
            product = getArguments().getParcelable("product");
        }
        super.onCreate(savedInstanceState);
    }

    String getCategories(List<String> categoryId) {
        StringBuilder categories = new StringBuilder();
        for (int i = 0; i < categoryId.size(); i++) {
            if (i != 0)
                categories.append("/");
            switch (categoryId.get(i)) {
                case "clothes":
                    categories.append(getString(R.string.clothes));
                    break;
                case "shoes":
                    categories.append(getString(R.string.shoes));
                    break;
                case "bag":
                    categories.append(getString(R.string.bag));
                    break;
                case "man":
                    categories.append(getString(R.string.man));
                    break;
                case "woman":
                    categories.append(getString(R.string.woman));
                    break;
                case "others":
                    categories.append(getString(R.string.others));
                    break;
            }
        }
        return categories.toString();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        binding.productDetailAppBarTitle.setText(product.getName());
        binding.productDetailActionBack.setOnClickListener(view ->
                NavHostFragment.findNavController(ProductDetailFragment.this).popBackStack());
        binding.productDetailName.setText(product.getName());
        binding.productDetailPrice.setText(CommonFunctions.formatPrice(product.getPrice()));
        binding.productDetailCategory.setText(getCategories(product.getCategory_id()));
        binding.productDetailDescription.setText(product.getDescription());

        ProductDetailPagerAdapter adapter = new ProductDetailPagerAdapter(getContext(), product.getImage());
        binding.productDetailImageSlider.setAdapter(adapter);
        binding.btnSelectSize.setOnClickListener(view -> {
            List<String> productSize;
            if(product.getCategory_id().contains("shoes")) productSize = CommonFunctions.getShoesSize();
            else productSize = CommonFunctions.getClothesSize();
            SizeSelectorBottomSheet sizeSelectorBottomSheet = new SizeSelectorBottomSheet(productSize, position -> {
                binding.btnSelectSize.setText(productSize.get(position));
            });
            sizeSelectorBottomSheet.show(getParentFragmentManager(), sizeSelectorBottomSheet.getTag());
        });
        return binding.getRoot();
    }
}