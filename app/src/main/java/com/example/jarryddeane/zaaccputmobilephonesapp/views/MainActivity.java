package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;

public class MainActivity extends AppCompatActivity {

    //private static final int RESULT_LOAD_IMG = 1;
    ListView list;

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

    Integer[] image = {
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1,
            R.drawable.buy1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set logo on action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.cellyoulater);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        CustomList customList = new CustomList(this, name, image);
        list = (ListView)findViewById(R.id.listitems);
        list.setAdapter(customList);

        //list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedItem = name[+position];
                //Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
            ///}
       //});


        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Get item/icon selected on actionbar
        int menuid = item.getItemId();

        //check if login/logout clicked
        //if clicked display login custom dialog
        if(menuid == R.id.login) {
            displayLoginDialog();
        }


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
            /*
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMG);
                }
            });
            */

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CreateEditProduct.class);
                    //finish();
                    startActivity(intent);
                    /*
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
                    */
                }
            });


            Button register = (Button)dialog.findViewById(R.id.btnregister);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout layouterror = (RelativeLayout)dialog.findViewById(R.id.layouterror);
                    layouterror.setVisibility(View.VISIBLE);
                    layouterror.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));

                    /*
                    TextView welcome = (TextView)dialog.findViewById(R.id.textView);
                    welcome.setVisibility(View.VISIBLE);
                    welcome.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));
                    */
                }
            });


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayLoginDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setTitle("Login");
        dialog.show();

        //get login button
        Button login = (Button)dialog.findViewById(R.id.btnlogin);

        //add click event to check if details correct
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting login username and password
                TextView txtusername = (TextView)findViewById(R.id.txtusername);
                TextView txtpassword = (TextView)findViewById(R.id.txtpassword);

                //checking if username and password fields are blank
                if(txtusername.getText().toString().isEmpty() || txtpassword.getText().toString().isEmpty()) {
                    TextView txterror = (TextView)dialog.findViewById(R.id.txterror);
                    txterror.setText("Fields cannot be blank");
                    RelativeLayout layouterror = (RelativeLayout)dialog.findViewById(R.id.layouterror);
                    layouterror.setVisibility(View.VISIBLE);
                    layouterror.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_down));
                } else {
                    final ProgressDialog processDialog = ProgressDialog.show(dialog.getContext(), "", "Authenticating.....", true);
                    //processDialog.setMessage("Logging in.....");
                    processDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            processDialog.dismiss();
                        }
                    }).start();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void exit() {
        System.exit(1);
    }

}
