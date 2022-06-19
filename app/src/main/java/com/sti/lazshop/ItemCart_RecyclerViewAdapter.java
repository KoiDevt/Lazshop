package com.sti.lazshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemCart_RecyclerViewAdapter extends RecyclerView.Adapter<ItemCart_RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ItemModel> itemModels;



    public ItemCart_RecyclerViewAdapter(Context context, ArrayList<ItemModel> itemModels,
                                        RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.itemModels = itemModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ItemCart_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_cart_view_row, parent, false);
        return new ItemCart_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCart_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.itemName.setText(itemModels.get(position).getItemName());
        holder.itemPrice.setText(itemModels.get(position).getItemPrice());
        holder.imageView.setImageResource(itemModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView itemName, itemPrice;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            itemName = itemView.findViewById(R.id.textView);
            itemPrice = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }

}
