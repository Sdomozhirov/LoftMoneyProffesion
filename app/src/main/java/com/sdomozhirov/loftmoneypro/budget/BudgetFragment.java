package com.sdomozhirov.loftmoneypro.budget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.sdomozhirov.loftmoneypro.AddItemActivity;
import com.sdomozhirov.loftmoneypro.LoftApp;
import com.sdomozhirov.loftmoneypro.cells.Item;
import com.sdomozhirov.loftmoneypro.cells.ItemsAdapter;
import com.sdomozhirov.loftmoneypro.databinding.FragmentBudgetBinding;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddItemActivity.class);
                intent.putExtra(AddItemActivity.ARG_POSITION, currentPosition);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        loadItems(currentPosition);
    }

    private void configureRecycler() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void loadItems(int position) {
        String type;
        if (position == 0) {
            type = "income";
        } else {
            type = "expense";
        }
        ((LoftApp) getActivity().getApplication()).loftAPI.getItems(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(remoteItems -> {
                    if (remoteItems.getStatus().equals("success")) {
                        adapter.setData(remoteItems.getMoneyItemsList(), position);
                    } else {
                        Snackbar.make(binding.getRoot(), "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Snackbar.make(binding.getRoot(), "Произошла ошибка", Snackbar.LENGTH_LONG).show();
                });
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
