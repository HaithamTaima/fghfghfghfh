package com.example.fainl_android.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fainl_android.Fragment.BottomFragment.MusicFragment;
import com.example.fainl_android.Fragment.BottomFragment.RecyclerFragment;
import com.example.fainl_android.Fragment.BottomFragment.VideoFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new VideoFragment();
            case 2:
                return new MusicFragment();
            default:
                return new RecyclerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
