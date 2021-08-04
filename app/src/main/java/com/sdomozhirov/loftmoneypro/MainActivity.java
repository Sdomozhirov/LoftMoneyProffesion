package com.sdomozhirov.loftmoneypro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity {

/*    private RecyclerView itemsView;
    private Button btnClick;
    private ItemsAdapter itemsAdapter = new ItemsAdapter(); */
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        toolbar.setTitle("Учет бюджета");

        viewPager.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(), getLifecycle()));

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
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
            return new BudgetFragment();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

  /*      routerTo(new Fragment());

        btnClick = findViewById(R.id.btn_click);
        itemsView = findViewById(R.id.recycler_view);

        configureRecyclerView();
        generateMoney();

                btnClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(view.getContext(), "На меня нажали", Toast.LENGTH_LONG).show();
                        Intent newActivity = new Intent(getApplicationContext(), AddItemActivity.class);
                        startActivity(newActivity);
                    }
                });
*/


/*    private void generateMoney(){
        List<Item> Items = new ArrayList<>();
        Items.add(new Item("МОЛОКО","15000"));
        Items.add(new Item("Salary","150000"));

        itemsAdapter.setData(Items);
    }

    private void configureRecyclerView() {
        itemsView.setAdapter(itemsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);

        itemsView.setLayoutManager(layoutManager);
        itemsView.setAdapter(itemsAdapter);

        itemsAdapter.itemsAdapterClick = new ItemsAdapterClick() {
            @Override
            public void onCellClick(Item Item) {

            }

            @Override
            public void onTitleClick() {

            }
        };
    }

 */
}