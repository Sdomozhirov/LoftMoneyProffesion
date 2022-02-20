package com.sdomozhirov.loftmoneypro.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sdomozhirov.loftmoneypro.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private List<Item> ItemList = new ArrayList<>();

    public ItemsAdapterClick itemsAdapterClick;
    private int positionTab;

    public void setData(List<Item> Item, int position) {
        ItemList.clear();
        positionTab = position;
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
        holder.bind(ItemList.get(position), positionTab);
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

        public void bind(final Item Item, int positionTab) {

            if (positionTab == 0) {
               valueTextView.setTextColor(ContextCompat.getColor(valueTextView.getContext(), R.color.apple_green));
            } else if (positionTab == 1) {
                valueTextView.setTextColor(ContextCompat.getColor(valueTextView.getContext(), R.color.dark_sky_blue));
            }

            titleTextView.setText(Item.getTitle());
            valueTextView.setText(String.valueOf(Item.getValue()));
        }
    }
}
