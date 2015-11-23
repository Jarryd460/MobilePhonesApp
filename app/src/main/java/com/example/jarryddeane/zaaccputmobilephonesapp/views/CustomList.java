package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Cart;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Product;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.ProductServiceImpl;

import java.util.List;

/**
 * Created by Jarryd Deane on 2015/11/07.
 */
public class CustomList extends ArrayAdapter<Product> {

    private final Activity context;
    private final List<Product> products;
    private Dialog dialog;
    private Product product;

    public CustomList(Activity context, List<Product> products) {
        super(context, R.layout.list_view_items, products);

        this.context = context;
        this.products = products;
    }

    public View getView(final int position, final View view, final ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_view_items, null, true);

        final TextView id = (TextView) rowView.findViewById(R.id.txtid);
        TextView title = (TextView) rowView.findViewById(R.id.txtname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgproduct);
        final TextView price = (TextView) rowView.findViewById(R.id.txtprice);
        final ImageView add = (ImageView) rowView.findViewById(R.id.imgadd);
        final ImageView remove = (ImageView) rowView.findViewById(R.id.imgsubtract);

        title.setText(products.get(position).getName());

        if(products.get(position).getPicture() == null) {
            imageView.setImageResource(R.drawable.login);
        } else {
            imageView.setImageResource(R.drawable.login);
        }

        try {
            Cart cart = Cart.getInstance();
            int quantity = cart.getProductQuantity(products.get(position));
            if(quantity != 0) {
                remove.setVisibility(View.VISIBLE);
                price.setText("R"+products.get(position).getPrice().toString()+ " x" + cart.getProductQuantity(products.get(position)));
            } else {
                price.setText("R"+products.get(position).getPrice().toString());
            }
        } catch(Exception e) {
            price.setText("R"+products.get(position).getPrice().toString());
        }

        id.setText(products.get(position).getId().toString());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Cart cart = Cart.getInstance();

                if(cart.getCustomer() == null) {
                    Toast.makeText(getContext(), "Please Login before\nattempting to shop" , Toast.LENGTH_SHORT).show();
                } else {
                    cart.addProduct(products.get(position));
                    remove.setVisibility(View.VISIBLE);

                    new Handler(view.getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int index = price.getText().toString().indexOf(" ");
                            if (index != -1) {
                                price.setText(price.getText().toString().subSequence(0, price.getText().toString().indexOf(" ")) + " x" + cart.getProductQuantity(products.get(position)));
                            } else {
                                price.setText(price.getText().toString() + " x" + cart.getProductQuantity(products.get(position)));
                            }

                            if(Checkout.getTotal() != null) {
                                Checkout.getTotal().setText("R" + cart.orderTotal().toString() + "0");
                            }
                        }
                    });
                    //Toast.makeText(getContext(), products.get(position).getName() + " added.\nQuantity: " + cart.getProductQuantity(products.get(position)) , Toast.LENGTH_SHORT).show();
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Cart cart = Cart.getInstance();

                if(cart.getProductQuantity(products.get(position)) == 1) {
                    cart.removeProduct(products.get(position));
                    remove.setVisibility(View.GONE);

                    new Handler(view.getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            price.setText(price.getText().toString().subSequence(0, price.getText().toString().indexOf(" ")));
                            if(Checkout.getTotal() != null) {
                                Checkout.getTotal().setText("R" + cart.orderTotal().toString() + "0");
                            }
                        }
                    });
                } else {
                    cart.removeProduct(products.get(position));
                    new Handler(view.getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            price.setText(price.getText().toString().subSequence(0, price.getText().toString().indexOf(" ")) + " x" + cart.getProductQuantity(products.get(position)));
                            if(Checkout.getTotal() != null) {
                                Checkout.getTotal().setText("R" + cart.orderTotal().toString() + "0");
                            }
                       }
                    });
                }
                //Toast.makeText(getContext(), products.get(position).getName() + " removed.\nQuantity: " + cart.getProductQuantity(products.get(position)), Toast.LENGTH_SHORT).show();
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.activity_view_product);
                dialog.setTitle("Product Details");
                final ProgressDialog processDialog = ProgressDialog.show(dialog.getContext(), "", "Loading product details.....", true);
                processDialog.setCancelable(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductServiceImpl service = new ProductServiceImpl();
                        product = service.findById(new Long(id.getText().toString()));

                        new Handler(dialog.getContext().getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Window view = dialog.getWindow();

                                //ImageView picture = (ImageView)view.findViewById(R.id.imgpicture);
                                TextView name = (TextView) view.findViewById(R.id.txtname);
                                TextView manufacturer = (TextView) view.findViewById(R.id.txtmanufacturer);
                                TextView price = (TextView) view.findViewById(R.id.txtprice);
                                TextView operatingsystem = (TextView) view.findViewById(R.id.txtoperatingsystem);
                                TextView screensize = (TextView) view.findViewById(R.id.txtscreensize);
                                TextView touchscreen = (TextView) view.findViewById(R.id.txttouchscreen);
                                TextView camera = (TextView) view.findViewById(R.id.txtcamera);
                                TextView memory = (TextView) view.findViewById(R.id.txtmemory);

                                name.setText(product.getName());
                                manufacturer.setText(product.getManufacturer());
                                price.setText(product.getPrice().toString());
                                operatingsystem.setText(product.getOperatingSystem());
                                screensize.setText(product.getScreenSize());
                                touchscreen.setText(product.getTouchScreen());
                                camera.setText(product.getCamera());
                                memory.setText(product.getMemory());
                                dialog.show();
                            }
                        });
                        processDialog.dismiss();
                    }
                });
                thread.start();
            }
        });
        return rowView;
    }

}
