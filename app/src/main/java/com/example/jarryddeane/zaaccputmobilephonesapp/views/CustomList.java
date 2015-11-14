package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;

/**
 * Created by Jarryd Deane on 2015/11/07.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final Integer[] image;

    public CustomList(Activity context, String[] name, Integer[] image) {
        super(context, R.layout.list_view_items, name);

        this.context = context;
        this.name = name;
        this.image = image;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_view_items, null, true);

        TextView title = (TextView) rowView.findViewById(R.id.txtname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgproduct);
        TextView price = (TextView) rowView.findViewById(R.id.txtprice);

        title.setText(name[position]);
        imageView.setImageResource(image[position]);
        price.setText("R20");

        ImageView add = (ImageView) rowView.findViewById(R.id.imgadd);
        ImageView remove = (ImageView) rowView.findViewById(R.id.imgsubtract);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Add Clicked", Toast.LENGTH_SHORT).show();
            }
        });
/*
        add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), "Add Clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
*/

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Remove Clicked", Toast.LENGTH_SHORT).show();
            }
        });
/*
        remove.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), "Remove Clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
*/

        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Phone Clicked", Toast.LENGTH_SHORT).show();

                final ProgressDialog processDialog = ProgressDialog.show(getContext(), "", "Logging in.....", true);
                processDialog.setCancelable(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        processDialog.dismiss();
                    }
                }).start();
            }
        });

        /*layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), "Phone Clicked", Toast.LENGTH_SHORT).show();

                final ProgressDialog processDialog = ProgressDialog.show(getContext(), "", "Logging in.....", true);
                processDialog.setCancelable(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        processDialog.dismiss();
                    }
                }).start();

                        //(getContext(), ProgressDialog.STYLE_SPINNER);

                return false;
            }
        });*/

        return rowView;
    }

}
