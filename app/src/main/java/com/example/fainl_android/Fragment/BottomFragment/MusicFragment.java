package com.example.fainl_android.Fragment.BottomFragment;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainl_android.Adapter.ServiceProAdapter;
import com.example.fainl_android.Service.ProService;
import com.example.fainl_android.databinding.FragmentMusicBinding;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MusicFragment extends Fragment {
    ServiceProAdapter testAdapter;
    FragmentMusicBinding binding;
    ServiceConnection serviceConnection;
    ProService musicPlayerService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMusicBinding.inflate(inflater, container, false);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Both");
        strings.add("Fast");
        strings.add("Minimal");
        strings.add("Rock");
        strings.add("Simple");
        strings.add("Trailer");

        RecyclerView recyclerview = binding.recyclerview;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
        testAdapter = new ServiceProAdapter(getContext(), strings);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(testAdapter);

        //-------------------------------------------------------
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("onServiceConnected", "Doune");
                ProService.LocalBinder localBinder = (ProService.LocalBinder) iBinder;
                musicPlayerService = localBinder.getService();
                binding.seekBar.setMax(musicPlayerService.getDuration());
                //-------------------عشان يمشي السيك يار مع الاغنية
                final Handler mHandler = new Handler();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.seekBar.setProgress(musicPlayerService.getCurrentPosition());
                        binding.time2.setText(TimeUnit.MILLISECONDS.toSeconds(musicPlayerService.getCurrentPosition()) + "s");
                        mHandler.postDelayed(this, 1000);
                    }
                });
                //----------------------------------


            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        binding.playPauseSong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicPlayerService.isPlaying()) {
                    musicPlayerService.pauseSonge();
                } else {
                    musicPlayerService.startSonge();
                }
            }
        });


        binding.perSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.perSong();
                int postion = musicPlayerService.getPosition();
                testAdapter.setSelectedPosition(postion);
                binding.seekBar.setMax(musicPlayerService.getDuration());
            }
        });

        binding.nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.nextSong();
                int postion = musicPlayerService.getPosition();
                testAdapter.setSelectedPosition(postion);
                binding.seekBar.setMax(musicPlayerService.getDuration());
            }
        });


        //--------------------------------------------------------
        getActivity().bindService(new Intent(getContext(), ProService.class)
                , serviceConnection
                , BIND_AUTO_CREATE);


        //--------------------------لسينر على سيك بار
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                musicPlayerService.seekTo(seekBar.getProgress());
            }
        });


        return binding.getRoot();
    }
}