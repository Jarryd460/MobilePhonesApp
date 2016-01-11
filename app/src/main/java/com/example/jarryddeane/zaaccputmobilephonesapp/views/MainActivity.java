package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.*;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Orders;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.CustomerServiceImpl;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.ProductServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private static final int RESULT_LOAD_IMG = 1;
    private ListView list;
    private List<Customer> customers = null;
    private List<Product> products = null;
    private List<Orders> orders = null;
    private boolean found;
    private Dialog dialog;
    private Product product;
    private Menu mainMenu;
    private String displaying = "Products";
    private ProgressDialog processDialog;
    private CustomList customList;

    String[] name = {
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3"
    };

    String[] price = {
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3",
            "Note 5",
            "Samsung S6",
            "Nexus 5",
            "Note 3"
    };

    Integer[] image = {
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy,
            R.drawable.buy
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set logo on action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.cellyoulater);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        displayProducts();

        if(displaying.equals("Products") && Cart.getInstance().getCustomer() != null) {
            if(Cart.getInstance().getCustomer().getIsAdmin().equals("Yes")) {
                registerForContextMenu(findViewById(R.id.listitems));
            }
        }

        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(displaying.equals("Products") && Cart.getInstance().getCustomer() != null) {
            if(Cart.getInstance().getCustomer().getIsAdmin().equals("Yes")) {
                super.onCreateContextMenu(menu, v, menuInfo);
                getMenuInflater().inflate(R.menu.menu_context_product, menu);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(displaying.equals("Products") && Cart.getInstance().getCustomer() != null) {
            if(Cart.getInstance().getCustomer().getIsAdmin().equals("Yes")) {
                final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                Intent i;

                switch(item.getItemId()){
                    case R.id.addproduct:
                        //Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                        i = new Intent(MainActivity.this, CreateEditProduct.class);
                        finish();
                        startActivity(i);
                        break;
                    case R.id.editproduct:
                        //Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                        TextView id = (TextView)info.targetView.findViewById(R.id.txtid);
                        i = new Intent(MainActivity.this, CreateEditProduct.class);
                        i.putExtra("ID", id.getText().toString());
                        finish();
                        startActivity(i);
                        break;
                    case R.id.deleteproduct:
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Deleting.....");
                        alertDialog.setMessage("Are you sure you want to delete product?");

                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                processDialog = ProgressDialog.show(MainActivity.this, "", "Please wait.....", true, false);
                                processDialog.setCancelable(false);

                                View view = info.targetView;
                                final TextView id = (TextView)view.findViewById(R.id.txtid);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        boolean found = false;

                                        for(int i = 0; i < products.size() && found == false; i++) {
                                            if(products.get(i).getId().equals(Long.parseLong(id.getText().toString()))) {
                                                found = true;
                                                ProductServiceImpl service = new ProductServiceImpl();
                                                service.delete(products.get(i));
                                                final int finalI = i;
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        customList.remove(products.get(finalI));
                                                        customList.notifyDataSetChanged();
                                                    }
                                                });
                                            }
                                        }

                                        processDialog.dismiss();
                                        dialog.cancel();
                                    }
                                }).start();
                            }
                        });

                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        //display alert dialog
                        AlertDialog alert = alertDialog.create();
                        alert.show();
                        break;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mainMenu = menu;

        Cart cart = Cart.getInstance();
        if(cart.getCustomer() != null) {
            menu.getItem(1).setIcon(R.drawable.logout);
            menu.getItem(2).setVisible(true);
            menu.getItem(3).setVisible(true);
        } else {
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //check if login/logout clicked
        if(item.getItemId() == R.id.login) {
            //if login picture visible display login dialog
            if (isImageDisplayed(item.getIcon(), R.drawable.login)) {
                displayLoginDialog(item);
            } else {
                //logout picture visible
                //displaying alert dialog(about to logout)
                displayLogoutDialog(item);
            }
        } else if(item.getItemId() == R.id.shopping_cart) {
            if(Cart.getInstance().getCustomer() == null) {
                Toast.makeText(getApplicationContext(), "Please Login before\nattempting to view cart" , Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, Checkout.class);
                finish();
                startActivity(intent);
            }
        } else if(item.getItemId() == R.id.myprofile) {
            //Toast.makeText(getApplicationContext(), "My Profile" , Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, CreateEditCustomer.class);
            i.putExtra("Edit", "Edit");
            finish();
            startActivity(i);
        } else if(item.getItemId() == R.id.myorders) {
            displayOrders();
        }

/*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            //Toast.makeText(getApplicationContext(), "Login Clicked!!!", Toast.LENGTH_LONG).show();
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.activity_login);

            dialog.setTitle("Login");
            dialog.show();

            Button login = (Button)dialog.findViewById(R.id.btnlogin);

            //displays view for user to select image
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMG);
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CreateEditProduct.class);
                    //finish();
                    startActivity(intent);

                    final ProgressDialog processDialog = ProgressDialog.show(dialog.getContext(), "", "Logging in.....", true);
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


            Button register = (Button)dialog.findViewById(R.id.btnregister);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout layouterror = (RelativeLayout)dialog.findViewById(R.id.layouterror);
                    layouterror.setVisibility(View.VISIBLE);
                    layouterror.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));

                    TextView welcome = (TextView)dialog.findViewById(R.id.textView);
                    welcome.setVisibility(View.VISIBLE);
                    welcome.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));

                }
            });


            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    public void displayLoginDialog(final MenuItem item) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setTitle("");
        dialog.show();

        //get login button
        Button login = (Button)dialog.findViewById(R.id.btnlogin);
        //get register button
        Button register = (Button)dialog.findViewById(R.id.btnregister);


        //add click event to login button to check if details correct
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting login username and password
                final TextView txtusername = (TextView) dialog.findViewById(R.id.textView7);
                final TextView txtpassword = (TextView) dialog.findViewById(R.id.textView8);


                //checking if username and password fields are blank
                if (txtusername.getText().toString().equals("") || txtpassword.getText().toString().equals("")) {
                    TextView txterror = (TextView) dialog.findViewById(R.id.txterror);
                    txterror.setText("Fields cannot be blank");
                    RelativeLayout layouterror = (RelativeLayout) dialog.findViewById(R.id.layouterror);
                    layouterror.setVisibility(View.VISIBLE);
                    layouterror.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));
                } else {
                    final ProgressDialog processDialog = ProgressDialog.show(dialog.getContext(), "", "Logging in.....", true);
                    processDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                found = false;
                                CustomerServiceImpl service = new CustomerServiceImpl();
                                customers = service.findAll();

                                int counter = 0;

                                while (!found && counter < customers.size()) {
                                    Customer customer = customers.get(counter);
                                    if (customer.getLogin().getUserName().equals(txtusername.getText().toString()) && customer.getLogin().getPassword().equals(txtpassword.getText().toString())) {
                                        found = true;
                                        Cart.getInstance().setCustomer(customer);
                                    }
                                    counter++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!found) {
                                        TextView txterror = (TextView) dialog.findViewById(R.id.txterror);
                                        txterror.setText("Invalid credentials");
                                        RelativeLayout layouterror = (RelativeLayout) dialog.findViewById(R.id.layouterror);
                                        layouterror.setVisibility(View.VISIBLE);
                                        layouterror.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));
                                    } else {
                                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                        finish();
                                        startActivity(intent);
                                        mainMenu.getItem(2).setVisible(true);
                                        mainMenu.getItem(3).setVisible(true);
                                        item.setIcon(R.drawable.logout);
                                        dialog.cancel();
                                    }
                                }
                            });

                            processDialog.dismiss();
                        }
                    }).start();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, CreateEditCustomer.class);
                        finish();
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    public void displayLogoutDialog(final MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Logging Out.....");
        alertDialog.setMessage("Are you sure you want to logout?");

        //change icon to login if yes clicked
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                //display logging out progress dialog
                final ProgressDialog processDialog = ProgressDialog.show(MainActivity.this, "", "Logging out.....", true);
                processDialog.setCancelable(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Cart.getInstance().destroyCart();
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                finish();
                                startActivity(intent);
                                mainMenu.getItem(2).setVisible(false);
                                mainMenu.getItem(3).setVisible(false);
                                item.setIcon(R.drawable.login);
                            }
                        });
                        processDialog.dismiss();
                        dialog.cancel();
                    }
                }).start();
            }
        });

        //cancel alert dialog
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //display alert dialog
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        if(displaying.equals("Products")) {
            exit();
        } else {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

    private void exit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Exiting.....");
        alertDialog.setMessage("Are you sure you want to exit?");

        //exit app when yes clicked
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                dialog.cancel();
                System.exit(1);
            }
        });

        //cancel exit
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //display alert dialog
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private boolean isImageDisplayed(Drawable displayedImage, int expectedImage) {
        Drawable image = getResources().getDrawable(expectedImage);

        if(displayedImage != null && image != null) {
            Bitmap displayedBitmap = getBitmapOfDrawable(displayedImage);
            Bitmap expectedBitmap = getBitmapOfDrawable(image);

            return bitmapEqual(displayedBitmap, expectedBitmap);
        }
        return false;
    }

    private static Bitmap getBitmapOfDrawable(Drawable drawable) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }

    private static boolean bitmapEqual(Bitmap displayedBitmap, Bitmap expectedBitmap) {
        byte[] displayedByteArray = bitmapToByteArray(displayedBitmap);
        byte[] expectedByteArray = bitmapToByteArray(expectedBitmap);

        if(java.util.Arrays.equals(displayedByteArray, expectedByteArray)) {
            return true;
        }
        return false;
    }

    private static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void displayProducts() {
        final ProgressDialog processDialog = ProgressDialog.show(MainActivity.this, "", "Loading products.....", true);
        processDialog.setCancelable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //get all products
                final ProductServiceImpl service = new ProductServiceImpl();
                products = service.findAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customList = new CustomList(MainActivity.this, products);
                        list = (ListView) findViewById(R.id.listitems);
                        list.setAdapter(customList);
                        productDetails();
                    }
                });
                processDialog.dismiss();
            }
        }).start();



        /*
        new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ProductServiceImpl service = new ProductServiceImpl();
                                        TextView productid = (TextView)view.findViewById(R.id.txtid);

                                        //Toast.makeText(getApplicationContext(), id.getText().toString(), Toast.LENGTH_SHORT).show();

                                        product = service.findById(new Long(id.getText().toString()));

                                        TextView name = (TextView)view.findViewById(R.id.txtname);
                                        TextView manufacturer = (TextView)view.findViewById(R.id.txtmanufacturer);
                                        TextView price = (TextView)view.findViewById(R.id.txtprice);
                                        TextView operatingsystem = (TextView)view.findViewById(R.id.txtoperatingsystem);
                                        TextView screensize = (TextView)view.findViewById(R.id.txtscreensize);
                                        TextView touchscreen = (TextView)view.findViewById(R.id.txttouchscreen);
                                        TextView camera = (TextView)view.findViewById(R.id.txtcamera);
                                        TextView memory = (TextView)view.findViewById(R.id.txtmemory);

                                        name.setText(product.getName());
                                        manufacturer.setText(product.getManufacturer());
                                        price.setText(product.getPrice().toString());
                                        operatingsystem.setText(product.getOperatingSystem());
                                        screensize.setText(product.getScreenSize());
                                        touchscreen.setText(product.getTouchScreen());
                                        camera.setText(product.getCamera());
                                        memory.setText(product.getMemory());

                                        dialog.setTitle("Product Details");
                                        dialog.show();

    }
}).start();
         */


    }

    private void productDetails() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Clicking!!!", Toast.LENGTH_SHORT).show();


                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_view_product);
                dialog.setTitle("Product Details");
                final ProgressDialog processDialog = ProgressDialog.show(dialog.getContext(), "", "Loading product details.....", true);
                processDialog.setCancelable(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ProductServiceImpl service = new ProductServiceImpl();
                        TextView id = (TextView) view.findViewById(R.id.txtid);
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
    }

    private void displayOrders() {
        final ProgressDialog processDialog = ProgressDialog.show(MainActivity.this, "", "Loading orders.....", true);
        processDialog.setCancelable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //get all orders
                displaying = "Orders";
                orders = Cart.getInstance().getCustomer().getOrderList();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final CustomList2 customList2 = new CustomList2(MainActivity.this, orders);
                        list = (ListView) findViewById(R.id.listitems);
                        list.setAdapter(customList2);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                customList2.notifyDataSetChanged();
                            }
                        });
                    }
                });
                processDialog.dismiss();
            }
        }).start();
    }

}
