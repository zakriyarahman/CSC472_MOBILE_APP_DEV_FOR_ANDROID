package edu.depaul.csc472.zrahman_remote_control;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String channel_holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_zero = (Button) findViewById(R.id.btn_zero);
        Button btn_one = (Button) findViewById(R.id.btn_one);
        Button btn_two = (Button) findViewById(R.id.btn_two);
        Button btn_three = (Button) findViewById(R.id.btn_three);
        Button btn_four = (Button) findViewById(R.id.btn_four);
        Button btn_five = (Button) findViewById(R.id.btn_five);
        Button btn_six = (Button) findViewById(R.id.btn_six);
        Button btn_seven = (Button) findViewById(R.id.btn_seven);
        Button btn_eight = (Button) findViewById(R.id.btn_eight);
        Button btn_nine = (Button) findViewById(R.id.btn_nine);
        Button btn_cbs = (Button) findViewById(R.id.btn_cbs);
        Button btn_abc = (Button) findViewById(R.id.btn_abc);
        Button btn_nbc = (Button) findViewById(R.id.btn_nbc);
        Button btn_channel_plus = (Button) findViewById(R.id.btn_channel_plus);
        Button btn_channel_minus = (Button) findViewById(R.id.btn_channel_minus);

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
                    temp = 0;
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
                if(temp < 0){
                    temp = 999;
                }
                if(temp < 100){
                    current_channel_indicator.setText(paddingFunction(temp));
                }
                else
                    current_channel_indicator.setText(String.valueOf(temp));
            }
        };

        View.OnClickListener clk_abc = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = "123";
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        View.OnClickListener clk_nbc = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = "124";
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        View.OnClickListener clk_cbs = new View.OnClickListener(){
            public void onClick(View view){
                channel_holder = "125";
                current_channel_indicator.setText(channel_holder);
                channel_holder = "";
            }
        };

        btn_abc.setOnClickListener(clk_abc);
        btn_nbc.setOnClickListener(clk_nbc);
        btn_cbs.setOnClickListener(clk_cbs);
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
                } else
                    Disable();
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
        ((Button) findViewById(R.id.btn_cbs)).setEnabled(false);
        ((Button) findViewById(R.id.btn_abc)).setEnabled(false);
        ((Button) findViewById(R.id.btn_nbc)).setEnabled(false);
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
        ((Button) findViewById(R.id.btn_cbs)).setEnabled(true);
        ((Button) findViewById(R.id.btn_abc)).setEnabled(true);
        ((Button) findViewById(R.id.btn_nbc)).setEnabled(true);
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
