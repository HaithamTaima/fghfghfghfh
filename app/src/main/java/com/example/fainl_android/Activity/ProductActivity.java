package com.example.fainl_android.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fainl_android.Adapter.ProductAdapter;
import com.example.fainl_android.Class.Product_class;
import com.example.fainl_android.Dialog.DialogFragment;
import com.example.fainl_android.Interface.on_item_click_listener;
import com.example.fainl_android.R;
import com.example.fainl_android.databinding.ActivityProductBinding;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements DialogFragment.onokclicklistener {
    ActivityProductBinding binding;
    ArrayList<Product_class> productsClicked;
    double sum = 0;
    RemoteViews remoteViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Product_class> productClasses = new ArrayList<>();
        productsClicked = new ArrayList<>();

        productClasses.add(new Product_class(1, 2, "حقيبة نخب ياغالي", 100, R.drawable.ic_bag, "حقيبة"));
        productClasses.add(new Product_class(2, 4, "حذاء تركي", 200, R.drawable.ic_high_heel, "حذاء"));
        productClasses.add(new Product_class(3, 6, "سلسلة تركية", 400, R.drawable.ic_necklace, "سلسلة"));
        productClasses.add(new Product_class(4, 8, "تشيرت تركي نخب يا كبير", 600, R.drawable.ic_shirt, "تشيرت"));
        productClasses.add(new Product_class(5, 10, "بنطال شباب", 800, R.drawable.ic_trousers, "بنطال"));

        ProductAdapter adapter = new ProductAdapter(productClasses, new on_item_click_listener() {
            @Override
            public void OnItemClicked(Product_class product) {
                productsClicked.add(product);
                sum = sum + product.getPrice();
            }
        });
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setAdapter(adapter);
        binding.buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = DialogFragment.newInstance();
                fragment.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public void OnOkClicked(String name, String address) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Notification("Name : " + name, "Address : " + address, "Total : " +
                        String.valueOf(sum));
                sum = 0;
                remoteViews = null;
            }
        });
        t1.start();
    }

    private void Notification(String s1, String s2, String s3) {
        String channelId = "channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setOngoing(false);
        builder.setContent(getCustomDesign(s1, s2, s3));
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_network);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "s", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());
    }


    private RemoteViews getCustomDesign(String s1, String s2, String s3) {
        RemoteViews rv = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notific_customi);
        for (int x = 0; x < productsClicked.size(); x++) {

            remoteViews = new RemoteViews(getPackageName(), R.layout.notific_custom_product);
            remoteViews.setTextViewText(R.id.name, productsClicked.get(x).getName());
            remoteViews.setTextViewText(R.id.desc, productsClicked.get(x).getDescription());
            remoteViews.setImageViewResource(R.id.image_view, productsClicked.get(x).getImage());
            remoteViews.setTextViewText(R.id.price, productsClicked.get(x).getPrice() + "");
            remoteViews.setTextViewText(R.id.count, productsClicked.get(x).getCount() + "");
            rv.addView(R.id.products_notific, remoteViews);
        }
        rv.setTextViewText(R.id.Tview_name, s1);
        rv.setTextViewText(R.id.Tview_address, s2);
        rv.setTextViewText(R.id.Tview_Sum, s3);
        return rv;
    }
}