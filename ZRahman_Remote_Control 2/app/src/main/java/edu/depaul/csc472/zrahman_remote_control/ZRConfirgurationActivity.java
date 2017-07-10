package edu.depaul.csc472.zrahman_remote_control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ZRConfirgurationActivity extends AppCompatActivity {

    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;
    Button btn_channel_plus;
    Button btn_channel_minus;
    EditText channel_label;
    TextView config_channel;
    Button btn_save;
    Button btn_cancel;
    String favButton;
    RadioButton rdnbtn_favLeft;
    RadioButton rdnbtn_favMiddle;
    RadioButton rdnbtn_favRight;
    RadioGroup rdobtn_channel_selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zrconfirguration);

        btn_zero = (Button) findViewById(R.id.btn_zero);
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        btn_five = (Button) findViewById(R.id.btn_five);
        btn_six = (Button) findViewById(R.id.btn_six);
        btn_seven = (Button) findViewById(R.id.btn_seven);
        btn_eight = (Button) findViewById(R.id.btn_eight);
        btn_nine = (Button) findViewById(R.id.btn_nine);
        btn_channel_plus = (Button) findViewById(R.id.btn_channel_plus);
        btn_channel_minus = (Button) findViewById(R.id.btn_channel_minus);
        channel_label = (EditText) findViewById(R.id.channel_label);
        config_channel = (TextView) findViewById(R.id.config_channel);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        rdobtn_channel_selector = (RadioGroup) findViewById(R.id.rdobtn_channel_selector);
        rdnbtn_favLeft = (RadioButton) findViewById(R.id.rdnbtn_favLeft);
        rdnbtn_favMiddle = (RadioButton) findViewById(R.id.rdnbtn_favMiddle);
        rdnbtn_favRight = (RadioButton) findViewById(R.id.rdnbtn_favRight);

//        rdobtn_channel_selector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
////                RadioButton radioButton = (RadioButton) findViewById(checkedId);
////                favButton = radioButton.getText().toString();
//            }
//        });

        CompoundButton.OnCheckedChangeListener list = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                favButton = buttonView.getText().toString();
            }
        };

        rdnbtn_favLeft.setOnCheckedChangeListener(list);
        rdnbtn_favMiddle.setOnCheckedChangeListener(list);
        rdnbtn_favRight.setOnCheckedChangeListener(list);

        View.OnClickListener clk_number = new View.OnClickListener(){
            public void onClick(View view){
                if(config_channel.getText().length() == 3){
                    config_channel.setText(((Button) view).getText());
                }
                else{
                    config_channel.append(((Button) view).getText());
                }
            }
        };

        View.OnClickListener clk_plus = new View.OnClickListener(){
            public void onClick(View view){
                int temp = Integer.parseInt(config_channel.getText().toString());
                temp++;
                if(temp > 999){
                    temp = 0;
                }
                if(temp < 100){
                    config_channel.setText(paddingFunction(temp));
                }
                else
                    config_channel.setText(String.valueOf(temp));
            }
        };


        View.OnClickListener clk_minus = new View.OnClickListener(){
            public void onClick(View view){
                int temp = Integer.parseInt(config_channel.getText().toString());
                temp--;
                if(temp < 0){
                    temp = 999;
                }
                if(temp < 100){
                    config_channel.setText(paddingFunction(temp));
                }
                else
                    config_channel.setText(String.valueOf(temp));
            }
        };

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = rdobtn_channel_selector.getCheckedRadioButtonId();
                Intent intentConfig = new Intent();
                if(config_channel.getText().toString().length() < 4){
                    config_channel.setText(paddingFunction(Integer.parseInt(config_channel.toString())));
                }
//                if(temp == -1){
//                    Toast.makeText(btn_save.getText() + " this")
//                }
//                if(temp == R.id.btn_fav_left){
//                    intentConfig.putExtra("favButton", "Left");
//                } else if(temp == R.id.btn_fav_right){
//                    intentConfig.putExtra("favButton", "Right");
//                } else
//                    intentConfig.putExtra("favButton", "Middle");
//                Intent intentConfig = new Intent();
                intentConfig.putExtra("favButton", favButton.toString());
                intentConfig.putExtra("config_channel", config_channel.getText().toString());
                intentConfig.putExtra("channel_label", channel_label.getText().toString());

                setResult(RESULT_OK, intentConfig);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btn_zero.setOnClickListener(clk_number);
        btn_one.setOnClickListener(clk_number);
        btn_two.setOnClickListener(clk_number);
        btn_three.setOnClickListener(clk_number);
        btn_four.setOnClickListener(clk_number);
        btn_five.setOnClickListener(clk_number);
        btn_six.setOnClickListener(clk_number);
        btn_seven.setOnClickListener(clk_number);
        btn_eight.setOnClickListener(clk_number);
        btn_nine.setOnClickListener(clk_number);
        btn_channel_plus.setOnClickListener(clk_plus);
        btn_channel_minus.setOnClickListener(clk_minus);

    }
    public void Enable(){
        btn_zero.setEnabled(true);
        btn_one.setEnabled(true);
        btn_two.setEnabled(true);
        btn_three.setEnabled(true);
        btn_four.setEnabled(true);
        btn_five.setEnabled(true);
        btn_six.setEnabled(true);
        btn_seven.setEnabled(true);
        btn_eight.setEnabled(true);
        btn_nine.setEnabled(true);
        btn_channel_minus.setEnabled(true);
        btn_channel_plus.setEnabled(true);
    }

    public void Disable(){
        btn_zero.setEnabled(false);
        btn_one.setEnabled(false);
        btn_two.setEnabled(false);
        btn_three.setEnabled(false);
        btn_four.setEnabled(false);
        btn_five.setEnabled(false);
        btn_six.setEnabled(false);
        btn_seven.setEnabled(false);
        btn_eight.setEnabled(false);
        btn_nine.setEnabled(false);
        btn_channel_minus.setEnabled(false);
        btn_channel_plus.setEnabled(false);
    }

    public String paddingFunction(int number){
        if(number < 10){
            return "00" + String.valueOf(number);
        }
        else{
            return "0" + String.valueOf(number);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zrconfirguration, menu);
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
