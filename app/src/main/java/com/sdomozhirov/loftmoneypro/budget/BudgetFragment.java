package com.sdomozhirov.loftmoneypro.budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdomozhirov.loftmoneypro.AddItemActivity;
import com.sdomozhirov.loftmoneypro.cells.Item;
import com.sdomozhirov.loftmoneypro.cells.ItemsAdapter;
import com.sdomozhirov.loftmoneypro.databinding.FragmentBudgetBinding;

import java.util.ArrayList;

public class BudgetFragment extends Fragment {

    private static final String ARG_POSITION = "arg_position";
    public static final String ARG_ADD_NAME = "arg_name";
    public static final String ARG_ADD_PRICE = "arg_price";
    public static final int ARG_ADD_DATA = 100;

    private FragmentBudgetBinding binding;
    private ItemsAdapter adapter = new ItemsAdapter();
    private int currentPosition;
    ArrayList<Item> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBudgetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            currentPosition = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configureRecycler();
        zalepa();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddItemActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void configureRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void zalepa() {
        list.add(new Item("Молоко", 100));
        list.add(new Item("колбаса", 250));
        adapter.setData(list, currentPosition);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ARG_ADD_DATA) {
            Item item = new Item(data.getStringExtra(ARG_ADD_NAME), data.getIntExtra(ARG_ADD_PRICE, 0));
            list.add(item);
            adapter.setData(list, currentPosition);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static BudgetFragment newInstance(int position) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        budgetFragment.setArguments(args);
        return budgetFragment;
    }
}
