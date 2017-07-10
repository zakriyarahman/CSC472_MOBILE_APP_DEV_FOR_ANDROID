package edu.depaul.csc472.zrahman_remote_control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String channel_holder;

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
    Button btn_fav_right;
    Button btn_fav_left;
    Button btn_fav_middle;
    Button btn_channel_plus;
    Button btn_channel_minus;
    Button btn_swch_dvr;
    Button btn_swch_config;
    String favLeft = "125";
    String favMiddle = "124";
    String favRight = "123";

    private static final int CONFIGURE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        btn_fav_right = (Button) findViewById(R.id.btn_fav_right);
        btn_fav_left = (Button) findViewById(R.id.btn_fav_left);
        btn_fav_middle = (Button) findViewById(R.id.btn_fav_middle);
        btn_channel_plus = (Button) findViewById(R.id.btn_channel_plus);
        btn_channel_minus = (Button) findViewById(R.id.btn_channel_minus);
        btn_swch_dvr = (Button) findViewById(R.id.btn_swch_dvr);
        btn_swch_config = (Button) findViewById(R.id.btn_swch_config);

        final TextView volume_control_indicator = (TextView) findViewById(R.id.volume_control_indicator);
        SeekBar sk_br_volume_control = (SeekBar) findViewById(R.id.sk_br_volume_control);
        final TextView current_channel_indicator = (TextView) findViewById(R.id.current_channel_indicator);

        sk_br_volume_control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                volume_control_indicator.setText(String.valueOf(progress));
            }
        public void onStartTrackingTouch(SeekBar seekBar){
        }
        public void onStopTrackingTouch(SeekBar seekBar){
        }
        });

        btn_swch_dvr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZR_activity_dvr.class);
                startActivity(intent);
            }
        });


        btn_swch_config.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ZRConfirgurationActivity.class);
                startActivityForResult(intent, CONFIGURE);
            }
        });

        View.OnClickListener clk_number = new View.OnClickListener(){
            public void onClick(View view){
                if(current_channel_indicator.getText().length() == 3){
                    current_channel_indicator.setText(((Button) view).getText());
                }
                else{
                    current_channel_indicator.append(((Button) view).getText());
                }
            }
        };

        View.OnClickListener clk_plus = new View.OnClickListener(){
            public void onClick(View view){
                int temp = Integer.parseInt(current_channel_indicator.getText().toString());
                    temp++;
                if(temp > 999){
                    temp = 1;
                }
                if(temp < 100){
                    current_channel_indicator.setText(paddingFunction(temp));
                }
                else
                    current_channel_indicator.setText(String.valueOf(temp));
            }
        };


        View.OnClickListener clk_minus = new View.OnClickListener(){
            public void onClick(View view){
                int temp = Integer.parseInt(current_channel_indicator.getText().toString());
                temp--;
                if(temp < 1){
                    temp = 999;
                }
                if(temp <= 100){
                    current_channel_indicator.setText(paddingFunction(temp));
                }
                else
                    current_channel_indicator.setText(String.valueOf(temp));
            }
        };

        View.OnClickListener clk_favRight = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = favRight.toString();
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        View.OnClickListener clk_favMiddle = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = favMiddle.toString();
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        View.OnClickListener clk_favLeft = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = favLeft.toString();
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        btn_fav_right.setOnClickListener(clk_favRight);
        btn_fav_middle.setOnClickListener(clk_favMiddle);
        btn_fav_left.setOnClickListener(clk_favLeft);
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


        Switch switchIn = (Switch) findViewById(R.id.swch_tv_power);
        switchIn.setChecked(true);
        switchIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Enable();
                } else {
                    Disable();
                }
            }
        });

    }

    public String paddingFunction(int number){
        if(number < 10){
            return "00" + String.valueOf(number);
        }
        else{
            return "0" + String.valueOf(number);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CONFIGURE){
            if(resultCode == RESULT_OK){
                String favButton = data.getStringExtra("favButton");
                Toast.makeText(MainActivity.this, favButton + "favButton " + data.getStringExtra("channel_label") , Toast.LENGTH_LONG);
                if(favButton.equalsIgnoreCase("Left")){
                    btn_fav_left.setText(data.getStringExtra("channel_label"));
                    favLeft = data.getStringExtra("config_channel");
                }else if(favButton.equalsIgnoreCase("Right")){
                    btn_fav_right.setText(data.getStringExtra("channel_label"));
                    favRight = data.getStringExtra("config_channel");
                }else{
                    btn_fav_middle.setText(data.getStringExtra("channel_label"));
                    favMiddle = data.getStringExtra("config_channel");
                }
                data.getStringExtra("config_channel");
                data.getStringExtra("channel_label");
            }
        }
    }

    public void Disable(){
        ((TextView) findViewById(R.id.tv_power_status_indicator)).setText("Off");
        ((Button) findViewById(R.id.btn_zero)).setEnabled(false);
        ((Button) findViewById(R.id.btn_one)).setEnabled(false);
        ((Button) findViewById(R.id.btn_two)).setEnabled(false);
        ((Button) findViewById(R.id.btn_three)).setEnabled(false);
        ((Button) findViewById(R.id.btn_four)).setEnabled(false);
        ((Button) findViewById(R.id.btn_five)).setEnabled(false);
        ((Button) findViewById(R.id.btn_six)).setEnabled(false);
        ((Button) findViewById(R.id.btn_seven)).setEnabled(false);
        ((Button) findViewById(R.id.btn_eight)).setEnabled(false);
        ((Button) findViewById(R.id.btn_nine)).setEnabled(false);
        ((Button) findViewById(R.id.btn_fav_left)).setEnabled(false);
        ((Button) findViewById(R.id.btn_fav_middle)).setEnabled(false);
        ((Button) findViewById(R.id.btn_fav_right)).setEnabled(false);
        ((Button) findViewById(R.id.btn_channel_plus)).setEnabled(false);
        ((Button) findViewById(R.id.btn_channel_minus)).setEnabled(false);
        ((SeekBar) findViewById(R.id.sk_br_volume_control)).setEnabled(false);
    }

    public void Enable(){

        ((TextView) findViewById(R.id.tv_power_status_indicator)).setText("On");
        ((Button) findViewById(R.id.btn_zero)).setEnabled(true);
        ((Button) findViewById(R.id.btn_one)).setEnabled(true);
        ((Button) findViewById(R.id.btn_two)).setEnabled(true);
        ((Button) findViewById(R.id.btn_three)).setEnabled(true);
        ((Button) findViewById(R.id.btn_four)).setEnabled(true);
        ((Button) findViewById(R.id.btn_five)).setEnabled(true);
        ((Button) findViewById(R.id.btn_six)).setEnabled(true);
        ((Button) findViewById(R.id.btn_seven)).setEnabled(true);
        ((Button) findViewById(R.id.btn_eight)).setEnabled(true);
        ((Button) findViewById(R.id.btn_nine)).setEnabled(true);
        ((Button) findViewById(R.id.btn_fav_left)).setEnabled(true);
        ((Button) findViewById(R.id.btn_fav_middle)).setEnabled(true);
        ((Button) findViewById(R.id.btn_fav_right)).setEnabled(true);
        ((Button) findViewById(R.id.btn_channel_plus)).setEnabled(true);
        ((Button) findViewById(R.id.btn_channel_minus)).setEnabled(true);
        ((SeekBar) findViewById(R.id.sk_br_volume_control)).setEnabled(true);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
