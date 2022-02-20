package com.sdomozhirov.loftmoneypro.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sdomozhirov.loftmoneypro.budget.BudgetFragment;
import com.sdomozhirov.loftmoneypro.R;
import com.sdomozhirov.loftmoneypro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXPENSES = "expenses";
    public static final String INCOMES = "incomes";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("Учет бюджета");
        binding.viewPager.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(), getLifecycle()));

        new TabLayoutMediator(binding.tabs, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Доходы");
                        break;
                    }
                    case 1: {
                        tab.setText("Расходы");
                        break;
                    }
                    case 2: {
                        tab.setText("Баланс");
                        break;
                    }
                }
            }
        }).attach();
    }

    static class BudgetPagerAdapter extends FragmentStateAdapter {

        public BudgetPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return BudgetFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
