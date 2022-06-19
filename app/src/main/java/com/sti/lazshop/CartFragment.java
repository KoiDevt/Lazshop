package com.sti.lazshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carteasy.v1.lib.Carteasy;

import java.util.ArrayList;
import java.util.Map;

public class CartFragment extends Fragment implements RecyclerViewInterface {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ArrayList<ItemModel> itemModels = new ArrayList<>();

    public CartFragment() {

    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.mCartRecyclerView);
        ItemCart_RecyclerViewAdapter adapter = new ItemCart_RecyclerViewAdapter(requireActivity(), itemModels, this);
        setItemModels();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private void setItemModels() {
        Map<Integer, Map> data;
        Carteasy cs = new Carteasy(); // TODO: Use an application-wide reference later.
        data = cs.ViewAll(requireActivity());

        // Guard early and guard lazily.
        if (data == null) return;

        //TODO: There's a better way to do this.
        for (Map.Entry<Integer, Map> entry : data.entrySet()) {
            Map<String, String> innerValue = entry.getValue();

            //TODO: Use constants to prevent confusion.
            itemModels.add(new ItemModel(innerValue.get("Name"),
                    innerValue.get("Price"),
                    Integer.parseInt(innerValue.get("ProductImage"))));
        }

    }

    @Override
    public void onItemClick(int position) {
        // TODO: Unused.
    }
}