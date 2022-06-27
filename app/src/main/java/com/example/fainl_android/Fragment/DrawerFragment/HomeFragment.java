package com.example.fainl_android.Fragment.DrawerFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fainl_android.Adapter.ViewPagerAdapter;
import com.example.fainl_android.R;
import com.example.fainl_android.databinding.FragmentHomeBinding;
import com.google.android.material.navigation.NavigationBarView;


public class HomeFragment extends Fragment {
    ViewPagerAdapter viewPagerAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        binding.viewPager2.setAdapter(viewPagerAdapter);


        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Recycler) {
                    binding.viewPager2.setCurrentItem(0);
                } else if (id == R.id.Product) {
                    binding.viewPager2.setCurrentItem(1);

                } else if (id == R.id.Music) {
                    binding.viewPager2.setCurrentItem(2);

                }
                return false;
            }
        });


        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.Recycler).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.Product).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.Music).setChecked(true);
                        break;
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}