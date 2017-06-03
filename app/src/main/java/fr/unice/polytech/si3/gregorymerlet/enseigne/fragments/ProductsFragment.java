package fr.unice.polytech.si3.gregorymerlet.enseigne.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.gregorymerlet.enseigne.ProductsAdapter;
import fr.unice.polytech.si3.gregorymerlet.enseigne.ProductsRecyclerAdapter;
import fr.unice.polytech.si3.gregorymerlet.enseigne.R;
import fr.unice.polytech.si3.gregorymerlet.enseigne.animations.SizeAnimation;
import fr.unice.polytech.si3.gregorymerlet.enseigne.model.Firm;

public class ProductsFragment extends Fragment {

    private static final String FIRM = "firm";

    private View rootView;
    private RecyclerView productsGrid;
    private Firm firm;
    private boolean isSearchOpen;
    private SizeAnimation actualAnimation;

    public static ProductsFragment newInstance(Firm firm) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putSerializable(FIRM, firm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.firm = (Firm) getArguments().getSerializable(FIRM);
        this.isSearchOpen = false;

        rootView = inflater.inflate(R.layout.fragment_products, container, false);
        productsGrid = (RecyclerView) rootView.findViewById(R.id.productsGrid);

        RelativeLayout searchLayout = (RelativeLayout) rootView.findViewById(R.id.searchLayout);
        final ImageButton openSearchButton = (ImageButton) rootView.findViewById(R.id.openSearchButton);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchBar();
            }
        });
        openSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchBar();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        RadioGroup searchTypeRadioGroup = (RadioGroup) rootView.findViewById(R.id.searchTypeRadioGroup);
        searchTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                fillProductGrid();
            }
        });

        RadioButton allTypesButton = new RadioButton(this.getContext());
        allTypesButton.setText(getResources().getString(R.string.search_all_types));
        allTypesButton.setTag("all");
        searchTypeRadioGroup.addView(allTypesButton);
        for(String type : firm.getTypes()){
            RadioButton radioButton = new RadioButton(this.getContext());
            radioButton.setText(type);
            radioButton.setTag(type);
            searchTypeRadioGroup.addView(radioButton);
        }
        searchTypeRadioGroup.check(allTypesButton.getId());

        RadioGroup searchSortRadioGroup = (RadioGroup) rootView.findViewById(R.id.searchSortRadioGroup);
        searchSortRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                fillProductGrid();
            }
        });

        Spinner searchShopSpinner = (Spinner) rootView.findViewById(R.id.searchShopSpinner);
        searchShopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillProductGrid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> shops = new ArrayList<>();
        shops.add(getResources().getString(R.string.search_all_shops));
        shops.addAll(firm.getShopNames());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, shops);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchShopSpinner.setAdapter(adapter);

        fillProductGrid();
    }

    private void fillProductGrid(){
        RadioGroup searchTypeRadioGroup = (RadioGroup) rootView.findViewById(R.id.searchTypeRadioGroup);
        String checkedType = rootView.findViewById(searchTypeRadioGroup.getCheckedRadioButtonId()).getTag().toString();

        RadioGroup searchSortRadioGroup = (RadioGroup) rootView.findViewById(R.id.searchSortRadioGroup);
        String[] checkedSort = rootView.findViewById(searchSortRadioGroup.getCheckedRadioButtonId()).getTag().toString().split(" ");
        String sortType = checkedSort[0];
        boolean ascendingSort = (checkedSort[1].equals("true"))? true : false;

        Spinner searchShopSpinner = (Spinner) rootView.findViewById(R.id.searchShopSpinner);
        String shopName;
        if(searchShopSpinner.getSelectedItem() != null)
            shopName = searchShopSpinner.getSelectedItem().toString();
        else
            shopName = "all";
        if(shopName.equals(getResources().getString(R.string.search_all_shops)))
            shopName = "all";

        /*ProductsAdapter productsAdapter = new ProductsAdapter(this.getContext(), firm.getProducts(checkedType, sortType, ascendingSort, shopName), firm);
        productsGrid.setAdapter(productsAdapter);*/

        GridLayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2, GridLayoutManager.VERTICAL, false);
        productsGrid.setLayoutManager(mLayoutManager);

        ProductsRecyclerAdapter productsRecyclerAdapter = new ProductsRecyclerAdapter(this.getContext(), firm.getProducts(checkedType, sortType, ascendingSort, shopName), firm);
        productsGrid.setAdapter(productsRecyclerAdapter);
    }

    private void openSearchBar() {
        RelativeLayout searchContent = (RelativeLayout) rootView.findViewById(R.id.searchContent);
        ImageButton openSearchButton = (ImageButton) rootView.findViewById(R.id.openSearchButton);
        int searchContentSize = getRealHeightOf((RelativeLayout) ((ScrollView) searchContent.getChildAt(0)).getChildAt(0));
        if (actualAnimation == null || actualAnimation.hasEnded()){
            if (!isSearchOpen) {
                openSearchButton.setImageResource(R.drawable.ic_keyboard_arrow_up);
                isSearchOpen = true;
                actualAnimation = new SizeAnimation(searchContent, searchContentSize, searchContent.getHeight());
                actualAnimation.setDuration(500);
                searchContent.startAnimation(actualAnimation);
            } else {
                openSearchButton.setImageResource(R.drawable.ic_keyboard_arrow_down);
                isSearchOpen = false;
                actualAnimation = new SizeAnimation(searchContent, -searchContentSize, searchContent.getHeight());
                actualAnimation.setDuration(500);
                searchContent.startAnimation(actualAnimation);
            }
        }
    }

    private int getRealHeightOf(RelativeLayout relativeLayout){
        int result = 0;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        TypedValue typedValue = new TypedValue();
        int actionBarHeight = 0;
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data,getResources().getDisplayMetrics());
        }
        RelativeLayout searchLayout = (RelativeLayout) rootView.findViewById(R.id.searchLayout);
        int maxSize = displayMetrics.heightPixels - actionBarHeight - searchLayout.getHeight() - 25;

        for(int i = 0; i < relativeLayout.getChildCount(); i++){
            result += relativeLayout.getChildAt(i).getHeight();
        }
        result += 30;

        if(result > maxSize)
            result = maxSize;

        return result;
    }
}
