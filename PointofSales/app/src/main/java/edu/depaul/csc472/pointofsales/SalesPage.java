package edu.depaul.csc472.pointofsales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SalesPage extends AppCompatActivity {

    double cost = 0.00;
    double total;
    String summary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_page);

        Button btnNewOrder = (Button) findViewById(R.id.btnNewOrder);
        Button btnNewItem = (Button) findViewById(R.id.btnNewItem);
        Button btnTotal = (Button) findViewById(R.id.btnTotal);
        final TextView txtTotal = (TextView) findViewById(R.id.txtTotal);
        final TextView txtSummary = (TextView) findViewById(R.id.txtSummary);
        final EditText editTxtQuantity = (EditText) findViewById(R.id.editTxtQuantity);
        final EditText editTxtUnitPrice = (EditText) findViewById(R.id.editTxtUnitPrice);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ITEMS);
        final AutoCompleteTextView tv1 = (AutoCompleteTextView) findViewById(R.id.autocompleteTxtItem);
        tv1.setAdapter(adapter);
//        AutoCompleteTextView edtTxtItem = (EditText) findViewById(R.id.autocompleteTxtItem);

        btnNewItem.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        editTxtQuantity.setText("");
                        editTxtUnitPrice.setText("");
                        tv1.setText("");
                    }
                });
        btnNewOrder.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        cost = 0.00;
                        total = 0.00;
                        txtTotal.setText("$0.00");
                        editTxtQuantity.setText("");
                        editTxtUnitPrice.setText("");
                        tv1.setText("");
                        txtSummary.setText("");
                        summary = "";
                    }
                });






//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tv1.getText().equals("Shrimp Pizza")) {
//                    cost = 10.00;
//                } else if (tv1.getText().equals("Vegetable Pizza")) {
//                    cost = 11.00;
//                } else {
//                    cost = 12.00;
//                }
//                editTxtUnitPrice.setText("$" + cost);
//            }
//
//        });

        tv1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    editTxtQuantity.setText("1");
                    if(tv1.getText().toString().equals("Shrimp Pizza")){
                        cost = 10.00;
                    }else if(tv1.getText().toString().equals("Vegetable Pizza")){
                        cost = 11.00;
                    }else if(tv1.getText().toString().equals("Chicken Pizza")){
                        cost = 13.00;
                    }
//                    handled = true;
                }
                editTxtUnitPrice.setText("$" + cost);
//                if(editTxtUnitPrice.getText().equals(null)){

//                }
                return handled;
            }
        });
//        tv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (tv1.getText().equals("Shrimp Pizza")) {
//                    cost = 10.00;
//                } else if (tv1.getText().equals("Vegetable Pizza")) {
//                    cost = 11.00;
//                } else {
//                    cost = 12.00;
//                }
//                txtTotal.setText("$" + cost);
//            }
//        });
//        tv1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                public void onClick(View v) {
//                if (tv1.getText().equals("Shrimp Pizza")) {
//                    cost = 10.00;
//                } else if (tv1.getText().equals("Vegetable Pizza")) {
//                    cost = 11.00;
//                } else {
//                    cost = 12.00;
//                }
//                txtTotal.setText("$" + cost);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        btnTotal.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(
                                editTxtQuantity.getText().equals(null)
                                || editTxtUnitPrice.getText().equals(null)
                                || editTxtQuantity.getText().equals("")
                                || editTxtUnitPrice.getText().equals("")
                                || tv1.getText().equals(null)
                                || tv1.getText().equals("")
                          ){

                        }else{
                            total += Double.parseDouble(editTxtQuantity.getText().toString()) * Double.parseDouble(editTxtUnitPrice.getText().toString().substring(1));
                            txtTotal.setText(String.valueOf("$" + total));
                            summary += tv1.getText() + " x " + editTxtQuantity.getText().toString() + "\n";
                            txtSummary.setText(summary);
                        }
                    }
                }
        );
    }
    final String[] ITEMS = new String[] {
            "Shrimp Pizza", "Vegetable Pizza", "Chicken Pizza", "Gyro Pizza", "Cheese Stuff Pizza"
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sales_page, menu);
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
}
