package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Product;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.ProductServiceImpl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

public class CreateEditProduct extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    private Drawable textViewDesign;
    private Product product;
    private Product validProduct;
    private ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_product);
        textViewDesign = ((TextView)findViewById(R.id.txtname)).getBackground();
        touchScreen();
        save();
        cancel();

        String edit = getIntent().getStringExtra("ID");

        if(edit != null) {
            setValues();
        }

        /*
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMG);
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_create_edit_product, menu);
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

    private void save() {
        Button save = (Button)findViewById(R.id.btnsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean mobileCorrectFormat = false;
                boolean homeCorrectFormat = false;
                boolean validDate = false;

                TextView name = (TextView) findViewById(R.id.txtname);
                TextView manufacturer = (TextView) findViewById(R.id.txtmanufacturer);
                TextView price = (TextView) findViewById(R.id.txtprice);
                TextView os = (TextView) findViewById(R.id.txtoperatingsystem);
                TextView screensize = (TextView) findViewById(R.id.txtscreensize);
                Spinner touchscreen = (Spinner) findViewById(R.id.listtouchscreen);
                TextView camera = (TextView) findViewById(R.id.txtcamera);
                TextView memory = (TextView) findViewById(R.id.txtmemory);

                /*

                if (price.getText().toString().trim().equals("")) {
                    price.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    validDate = isValidDate(dob.getText().toString().trim());

                    if (!validDate) {
                        dob.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                    } else {
                        dob.setBackground(textViewDesign);
                    }
                }


                if (mobilenumber.getText().toString().trim().equals("")) {
                    mobilenumber.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    pattern = Pattern.compile(EMAIL_FORMAT);
                    matcher = pattern.matcher(mobilenumber.getText().toString().trim());
                    mobileCorrectFormat = matcher.matches();

                    if (!mobileCorrectFormat) {
                        mobilenumber.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                    } else {
                        mobilenumber.setBackground(textViewDesign);
                    }
                }

                if (homenumber.getText().toString().trim().equals("")) {
                    homenumber.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    pattern = Pattern.compile(EMAIL_FORMAT);
                    matcher = pattern.matcher(homenumber.getText().toString().trim());
                    homeCorrectFormat = matcher.matches();

                    if (!homeCorrectFormat) {
                        homenumber.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                    } else {
                        homenumber.setBackground(textViewDesign);
                    }
                }
                */

                if (name.getText().toString().trim().equals("")) {
                    name.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    name.setBackground(textViewDesign);
                }

                if (manufacturer.getText().toString().trim().equals("")) {
                    manufacturer.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    manufacturer.setBackground(textViewDesign);
                }

                if (price.getText().toString().trim().equals("")) {
                    price.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    price.setBackground(textViewDesign);
                }

                if (os.getText().toString().trim().equals("")) {
                    os.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    os.setBackground(textViewDesign);
                }

                if (screensize.getText().toString().trim().equals("")) {
                    screensize.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    screensize.setBackground(textViewDesign);
                }

                if (camera.getText().toString().trim().equals("")) {
                    camera.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    camera.setBackground(textViewDesign);
                }

                if (memory.getText().toString().trim().equals("")) {
                    memory.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    memory.setBackground(textViewDesign);
                }


                if (!name.getText().toString().trim().equals("") && !manufacturer.getText().toString().trim().equals("") && !price.getText().toString().trim().equals("") &&
                        !os.getText().toString().trim().equals("") && !screensize.getText().toString().trim().equals("") &&
                        !camera.getText().toString().trim().equals("") && !memory.getText().toString().trim().equals("")) {

                    //Toast.makeText(getApplicationContext(), "All valid", Toast.LENGTH_SHORT).show();
                    validProduct = new Product(
                            name.getText().toString(),
                            manufacturer.getText().toString(),
                            new BigDecimal(price.getText().toString()),
                            os.getText().toString(),
                            screensize.getText().toString(),
                            touchscreen.getSelectedItem().toString(),
                            camera.getText().toString(),
                            memory.getText().toString(),
                            null,
                            null,
                            null,
                            null
                    );

                    final String edit = getIntent().getStringExtra("ID");

                    if(edit != null) {
                        validProduct.setId(product.getId());
                    }

                    processDialog = ProgressDialog.show(CreateEditProduct.this, "", "Please wait.....", true);
                    processDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(edit == null) {
                                    ProductServiceImpl service = new ProductServiceImpl();
                                    service.save(validProduct);
                                } else {
                                    ProductServiceImpl service = new ProductServiceImpl();
                                    validProduct.setOrderProductList(product.getOrderProductList());
                                    validProduct.setProductPriceList(product.getProductPriceList());
                                    service.update(validProduct);
                                }

                                processDialog.dismiss();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                Intent intent = new Intent(CreateEditProduct.this, MainActivity.class);
                                finish();
                                startActivity(intent);

                            } catch (Exception e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        processDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error saving product. Please try again later.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            //Intent intent = new Intent(CreateEditCustomer.this, MainActivity.class);
                            //finish();
                            //startActivity(intent);
                        }
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Please check and fill in \"pink\" fields correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cancel() {
        Button cancel = (Button)findViewById(R.id.btncancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEditProduct.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void touchScreen() {
        Spinner touchscreen = (Spinner)findViewById(R.id.listtouchscreen);
        String[] list = new String[] {"Yes", "No"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, list);
        touchscreen.setAdapter(adapter);
    }

    private void setValues() {
        //Toast.makeText(getApplicationContext(), "Editing", Toast.LENGTH_SHORT).show();
        final String id = getIntent().getStringExtra("ID");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ProductServiceImpl service = new ProductServiceImpl();
                product = service.findById(new Long(id));
            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView name = (TextView) findViewById(R.id.txtname);
        TextView manufacturer = (TextView) findViewById(R.id.txtmanufacturer);
        TextView price = (TextView) findViewById(R.id.txtprice);
        TextView os = (TextView) findViewById(R.id.txtoperatingsystem);
        TextView screensize = (TextView) findViewById(R.id.txtscreensize);
        Spinner touchscreen = (Spinner) findViewById(R.id.listtouchscreen);
        TextView camera = (TextView) findViewById(R.id.txtcamera);
        TextView memory = (TextView) findViewById(R.id.txtmemory);

        name.setText(product.getName());
        manufacturer.setText(product.getManufacturer());
        price.setText(product.getPrice().toString());
        os.setText(product.getOperatingSystem());
        screensize.setText(product.getScreenSize());
        camera.setText(product.getCamera());
        memory.setText(product.getMemory());

        try {
            if(product.getTouchScreen().equals("Yes")) {
                touchscreen.setSelection(0);
            } else {
                touchscreen.setSelection(1);
            }
        } catch(Exception e) {
            touchscreen.setSelection(0);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateEditProduct.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {


        if(requestCode == RESULT_LOAD_IMG) {
            String[] columns = {MediaStore.Images.Media.DATA};

            Uri imageUri = intent.getData();

            Cursor cursor = getContentResolver().query(imageUri, columns, null, null, null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(columns[0]);

            String imagePath = cursor.getString(columnIndex);

            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Bitmap bitmap2 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ImageView image = (ImageView)findViewById(R.id.imageView);
            image.setImageBitmap(bitmap2);

            //image.setImageURI(Uri.parse(imagePath));
        }

    }
    */
}
