package com.sdomozhirov.loftmoneypro.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdomozhirov.loftmoneypro.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private List<Item> ItemList = new ArrayList<>();

    public ItemsAdapterClick itemsAdapterClick;

    public void setData(List<Item> Item) {
        ItemList.clear();
        ItemList.addAll(Item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemsViewHolder(layoutInflater.inflate(R.layout.items_main, parent, false),
                itemsAdapterClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.bind(ItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    static class ItemsViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView valueTextView;
        private ItemsAdapterClick itemsAdapterClick;

        public ItemsViewHolder(@NonNull View itemView,ItemsAdapterClick itemsAdapterClick) {
            super(itemView);
            this.itemsAdapterClick = itemsAdapterClick;

            titleTextView = itemView.findViewById(R.id.itemsTitleView);
            valueTextView = itemView.findViewById(R.id.itemsValueView);

        }

        public void bind(final Item Item) {
            titleTextView.setText(Item.getTitle());
            valueTextView.setText(String.valueOf(Item.getValue()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemsAdapterClick != null) {
                        itemsAdapterClick.onCellClick(Item);
                    }
                }
            });
            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemsAdapterClick != null){
                        itemsAdapterClick.onTitleClick();
                    }
                }
            });
        }
    }
}
