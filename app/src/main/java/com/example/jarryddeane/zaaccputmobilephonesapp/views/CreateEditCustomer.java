package com.example.jarryddeane.zaaccputmobilephonesapp.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarryddeane.zaaccputmobilephonesapp.R;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.*;
import com.example.jarryddeane.zaaccputmobilephonesapp.model.Login;
import com.example.jarryddeane.zaaccputmobilephonesapp.services.impl.CustomerServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEditCustomer extends AppCompatActivity {

    private TextView txtdob;
    private int month;
    private int year;
    private int day;
    static final int DATE_DIALOG_ID = 999;
    private Drawable textViewDesign;
    private final String EMAIL_FORMAT = "\\d{10}";
    private final String MONTH_FORMAT = "\\d{10}";
    private final String DAY_FORMAT = "\\d{10}";
    private final String YEAR_FORMAT = "\\d{10}";
    private Pattern pattern;
    private Matcher matcher;
    private ProgressDialog processDialog;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_customer);
        textViewDesign = ((TextView)findViewById(R.id.txtfirstname)).getBackground();
        genderList();
        calendar();
        save();
        cancel();

        String edit = getIntent().getStringExtra("Edit");

        if(edit != null) {
            //Toast.makeText(getApplicationContext(), "Want to edit." , Toast.LENGTH_SHORT).show();
            setValues();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_create_edit_customer, menu);
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

    private void genderList() {
        Spinner gender = (Spinner)findViewById(R.id.listgender);
        String[] list = new String[] {"Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, list);
        gender.setAdapter(adapter);
    }

    private void calendar() {
        txtdob = (TextView)findViewById(R.id.txtdob);

        txtdob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    showDialog(DATE_DIALOG_ID);
                }
            }
        });

        txtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth+1;
            day = selectedDay;

            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            df.setLenient(false);

            try {
                df.parse(Integer.toString(month)+"-"+Integer.toString(day)+"-"+Integer.toString(year));

                Date today = Calendar.getInstance().getTime();

                if(!df.getCalendar().getTime().after(today)) {
                    String mm = Integer.toString(month);
                    String dd = Integer.toString(day);
                    String yyyy = Integer.toString(year);

                    mm = (mm.length() == 1) ? "0"+mm : mm;
                    dd = (dd.length() == 1) ? "0"+dd : dd;

                    txtdob.setText(new StringBuilder().append(mm).append("-").append(dd).append("-").append(yyyy).append(" "));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateEditCustomer.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private void save() {
        Button save = (Button)findViewById(R.id.btnsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean mobileCorrectFormat = false;
                boolean homeCorrectFormat = false;
                boolean validDate = false;

                TextView firstname = (TextView) findViewById(R.id.txtfirstname);
                TextView lastname = (TextView) findViewById(R.id.txtlastname);
                TextView dob = (TextView) findViewById(R.id.txtdob);
                TextView mobilenumber = (TextView) findViewById(R.id.txtmobilephonenumber);
                TextView homenumber = (TextView) findViewById(R.id.txthomephonenumber);
                TextView username = (TextView) findViewById(R.id.txtusername);
                TextView password = (TextView) findViewById(R.id.txtpassword);
                TextView address = (TextView) findViewById(R.id.txtaddress);
                Spinner gender = (Spinner) findViewById(R.id.listgender);

                if (firstname.getText().toString().trim().equals("")) {
                    firstname.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    firstname.setBackground(textViewDesign);
                }

                if (lastname.getText().toString().trim().equals("")) {
                    lastname.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    lastname.setBackground(textViewDesign);
                }

                if (dob.getText().toString().trim().equals("")) {
                    dob.setBackground(getResources().getDrawable(R.drawable.text_view_error));
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

                if (username.getText().toString().trim().equals("")) {
                    username.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    username.setBackground(textViewDesign);
                }

                if (password.getText().toString().trim().equals("")) {
                    password.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    password.setBackground(textViewDesign);
                }

                if (address.getText().toString().trim().equals("")) {
                    address.setBackground(getResources().getDrawable(R.drawable.text_view_error));
                } else {
                    address.setBackground(textViewDesign);
                }


                if (!firstname.getText().toString().trim().equals("") && !lastname.getText().toString().trim().equals("") && !dob.getText().toString().trim().equals("") &&
                        !mobilenumber.getText().toString().trim().equals("") && !homenumber.getText().toString().trim().equals("") &&
                        !username.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("") &&
                        !address.getText().toString().trim().equals("") && validDate && mobileCorrectFormat && homeCorrectFormat) {

                    //Toast.makeText(getApplicationContext(), "All valid", Toast.LENGTH_SHORT).show();

                    Name name = new Name(firstname.getText().toString().trim(), null, lastname.getText().toString().trim());
                    Contact contact = new Contact(mobilenumber.getText().toString().trim(), homenumber.getText().toString().trim());
                    Address location = new Address(address.getText().toString().trim(), null, null, null);
                    Login login = new Login(username.getText().toString().trim(), password.getText().toString().trim());

                    customer = new Customer(name, gender.getSelectedItem().toString().trim(), dob.getText().toString().trim(), contact, location, null, login, "No", null, null);

                    final String edit = getIntent().getStringExtra("Edit");

                    if(edit != null) {
                        Long id = Cart.getInstance().getCustomer().getId();
                        customer.setId(id);
                    }

                    processDialog = ProgressDialog.show(CreateEditCustomer.this, "", "Please wait.....", true);
                    processDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(edit == null) {
                                    CustomerServiceImpl service = new CustomerServiceImpl();
                                    service.save(customer);
                                } else {
                                    CustomerServiceImpl service = new CustomerServiceImpl();
                                    customer.setOrderList(service.findById(Cart.getInstance().getCustomer().getId()).getOrderList());
                                    service.update(customer);
                                    Cart.getInstance().setCustomer(customer);
                                }

                                processDialog.dismiss();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                Intent intent = new Intent(CreateEditCustomer.this, MainActivity.class);
                                finish();
                                startActivity(intent);

                            } catch (Exception e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        processDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error saving customer. Please try again later.", Toast.LENGTH_SHORT).show();
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
/*
    private void save() {
        Button save = (Button)findViewById(R.id.btnsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = false; //= validation();

                if(valid) {
                    Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateEditCustomer.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check and fill in \"pink\" fields correctly.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
*/
    private void cancel() {
        Button cancel = (Button)findViewById(R.id.btncancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateEditCustomer.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private boolean isValidDate(String date) {
        boolean valid = false;

        if(!date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            valid =  false;
        } else {
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            df.setLenient(false);

            try {
                df.parse(date);
                Date today = Calendar.getInstance().getTime();

                if(!df.getCalendar().getTime().after(today)) {
                    valid = true;
                }
            } catch(ParseException e) {
                valid = false;
            }
        }

        return valid;
    }

    private void setValues() {
        Customer customer = Cart.getInstance().getCustomer();

        TextView firstname = (TextView) findViewById(R.id.txtfirstname);
        TextView lastname = (TextView) findViewById(R.id.txtlastname);
        TextView dob = (TextView) findViewById(R.id.txtdob);
        TextView mobilenumber = (TextView) findViewById(R.id.txtmobilephonenumber);
        TextView homenumber = (TextView) findViewById(R.id.txthomephonenumber);
        TextView username = (TextView) findViewById(R.id.txtusername);
        TextView password = (TextView) findViewById(R.id.txtpassword);
        TextView address = (TextView) findViewById(R.id.txtaddress);
        Spinner gender = (Spinner) findViewById(R.id.listgender);

        firstname.setText(customer.getName().getFirstName());
        lastname.setText(customer.getName().getLastName());
        dob.setText(customer.getDateOfBirth());
        mobilenumber.setText(customer.getContact().getMobilePhoneNumber());
        homenumber.setText(customer.getContact().getHomePhoneNumber());
        username.setText(customer.getLogin().getUserName());
        password.setText(customer.getLogin().getPassword());
        address.setText(customer.getAddress().getAddress());

        try {
            if(customer.getSex().equals("Male")) {
                gender.setSelection(0);
            } else {
                gender.setSelection(1);
            }
        } catch(Exception e) {
            gender.setSelection(0);
        }
    }

}
