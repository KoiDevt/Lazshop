package com.sti.lazshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carteasy.v1.lib.Carteasy;

import java.util.ArrayList;

public class ItemCart_RecyclerViewAdapter extends RecyclerView.Adapter<ItemCart_RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<ItemModel> itemModels;

    private Carteasy carteasy;

    public ItemCart_RecyclerViewAdapter(Context context, ArrayList<ItemModel> itemModels,
                                        RecyclerViewInterface recyclerViewInterface, Carteasy carteasy) {
        this.context = context;
        this.itemModels = itemModels;
        this.recyclerViewInterface = recyclerViewInterface;
        this.carteasy = carteasy;
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

        holder.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //past due, we'll just pretend that this button confirms the order
                carteasy.RemoveId(itemModels.get(holder.getAdapterPosition()).getItemName(),
                        context.getApplicationContext());

                itemModels.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());

                Toast.makeText(context, "Item confirmed!", Toast.LENGTH_SHORT).show();

            }
        });

        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // First, while we have the reference, we remove it from the
                // Carteasy instance.
                // TODO: This is rather dubious.
                carteasy.RemoveId(itemModels.get(holder.getAdapterPosition()).getItemName(),
                        context.getApplicationContext());

                // Then, properly update the adapter.
                itemModels.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition()); // We use notify here for nice animations.

                // After that, notify the user.
                Toast.makeText(context, "Item removed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView itemName, itemPrice;
        Button buttonConfirm, buttonRemove;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            itemName = itemView.findViewById(R.id.textView);
            itemPrice = itemView.findViewById(R.id.textView2);
            buttonConfirm = itemView.findViewById(R.id.confirm_button);
            buttonRemove = itemView.findViewById(R.id.remove_button);

            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            });
        }
    }

}
