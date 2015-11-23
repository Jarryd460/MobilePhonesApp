package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Cart;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Customer;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Product;

import java.util.List;

public class Checkout extends AppCompatActivity {

    private List<Product> products;
    private ListView list;
    private static TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //set logo on action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.cellyoulater);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        displayProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Checkout.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    public void displayProducts() {
        final ProgressDialog processDialog = ProgressDialog.show(Checkout.this, "", "Loading products.....", true);
        processDialog.setCancelable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //get all products
                final Cart cart = Cart.getInstance();
                products = cart.getProducts();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomList customList = new CustomList(Checkout.this, products);
                        list = (ListView)findViewById(R.id.checkoutitems);
                        list.setAdapter(customList);

                        Customer customer = cart.getCustomer();

                        TextView name = (TextView)findViewById(R.id.txtname);
                        TextView contact = (TextView)findViewById(R.id.txtcontact);
                        TextView address = (TextView)findViewById(R.id.txtaddress);
                        total = (TextView)findViewById(R.id.txttotal);
                        Button order = (Button)findViewById(R.id.btnorder);
                        //TextView price = (TextView)list.findViewById(R.id.txtprice);

                        name.setText(customer.getName().getFirstName() + " " + customer.getName().getLastName());
                        contact.setText(customer.getContact().getMobilePhoneNumber() + "/" + customer.getContact().getHomePhoneNumber());
                        address.setText(customer.getAddress().getAddress());
                        total.setText("R" + cart.orderTotal().toString() + "0");
                        //price.setText(price.getText());

                        //ImageView remove = (ImageView)list.findViewById(R.id.imgsubtract);
                        //remove.setVisibility(View.VISIBLE);

                        order.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(total.getText().toString().equals("R0.00")) {
                                    Toast.makeText(getApplicationContext(), "Cart is empty!", Toast.LENGTH_SHORT).show();
                                } else {
                                    final ProgressDialog processDialog = ProgressDialog.show(Checkout.this, "", "Placing order.....", true);
                                    processDialog.setCancelable(false);

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cart.placeOrder();
                                            processDialog.dismiss();
                                            Intent intent = new Intent(Checkout.this, MainActivity.class);
                                            finish();
                                            startActivity(intent);
                                        }
                                    }).start();
                                }
                            }
                        });

                        //list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            //@Override
                            //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getApplicationContext(), "Phone was Clicked!!!", Toast.LENGTH_SHORT).show();
                            //}
                       //});
                    }
                });

                processDialog.dismiss();
            }
        }).start();
    }

    public static TextView getTotal() {
        return total;
    }

}
