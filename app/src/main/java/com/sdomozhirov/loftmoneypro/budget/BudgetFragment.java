package com.sdomozhirov.loftmoneypro.budget;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sdomozhirov.loftmoneypro.AddItemActivity;
import com.sdomozhirov.loftmoneypro.cells.Item;
import com.sdomozhirov.loftmoneypro.cells.ItemsAdapter;
import com.sdomozhirov.loftmoneypro.databinding.FragmentBudgetBinding;

import java.util.ArrayList;

public class BudgetFragment extends Fragment {

    private FragmentBudgetBinding binding;
    private ItemsAdapter adapter = new ItemsAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBudgetBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
    }

    private void zalepa() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("Молоко", 100));
        list.add(new Item("колбаса", 250));
        adapter.setData(list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
