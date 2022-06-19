package com.sti.lazshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    ArrayList<ItemModel> itemModels = new ArrayList<>();
    int[] itemImages =
            {R.drawable.huion, R.drawable.item_iphone, R.drawable.item_airpods,
            R.drawable.item_xiaomi,R.drawable.item_jbl, R.drawable.item_bm800};

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);
        Item_RecyclerViewAdapter adapter = new Item_RecyclerViewAdapter(requireActivity(), itemModels,
                this);
        setItemModels();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

            private void setItemModels() {
                String[] itemNames = getResources().getStringArray(R.array.item_full_txt);
                String[] itemPrices = getResources().getStringArray(R.array.item_prices);

                for (int i = 0; i < itemNames.length; i++) {
                    itemModels.add(new ItemModel(
                            itemNames[i],
                            itemPrices[i],
                            itemImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
            Intent intent = new Intent(requireActivity(),ItemDescription.class);

            intent.putExtra("NAME", itemModels.get(position).getItemName());
            intent.putExtra("ITEM_PRICE", itemModels.get(position).getItemPrice());
            intent.putExtra("IMAGE", itemModels.get(position).getImage());

            startActivity(intent);
        }
    }