package edu.depaul.csc472.zr_listviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    private static final String TAG = "DetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");

        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            TextView name = (TextView) findViewById(R.id.txtLabel);
            TextView description = (TextView) findViewById(R.id.txtLongDesc);
            ImageView icon = (ImageView) findViewById(R.id.imgFoodItem);
            name.setText(intent.getCharSequenceExtra("FoodItemName"));
            description.setText(intent.getCharSequenceExtra("FoodItemDescription"));
            icon.setImageResource(intent.getIntExtra("FoodItemIcon", -1));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
