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

public class ZR_activity_dvr extends AppCompatActivity {

    Button btn_pause;
    Button btn_stop;
    Button btn_play;
    Button btn_forward;
    Button btn_rewind;
    Button btn_record;
    Button swch_to_tv;
    Switch swch_tv_power;
    TextView tv_power_status_indicator;
    TextView state_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zr_activity_dvr);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_forward = (Button) findViewById(R.id.btn_forward);
        btn_rewind = (Button) findViewById(R.id.btn_rewind);
        btn_record = (Button) findViewById(R.id.btn_record);
        swch_to_tv = (Button) findViewById(R.id.swch_to_tv);
        swch_tv_power = (Switch) findViewById(R.id.swch_tv_power);
        tv_power_status_indicator = (TextView) findViewById(R.id.tv_power_status_indicator);
        state_status = (TextView) findViewById(R.id.state_status);

        if(SingletonDVR.getTheInstance().powerStatusIndicator == PowerState.ON){
            swch_tv_power.setChecked(true);
            tv_power_status_indicator.setText(SingletonDVR.getTheInstance().powerStatusIndicator.toString());
            state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
            Enable();
        }else{
            swch_tv_power.setChecked(false);
            tv_power_status_indicator.setText(SingletonDVR.getTheInstance().powerStatusIndicator.toString());
            state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
            Disable();

        }

        swch_tv_power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Enable();
                } else {
                    Disable();
                    SingletonDVR.getTheInstance().stateStatus = States.Stopped;
                    SingletonDVR.getTheInstance().powerStatusIndicator = PowerState.OFF;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                    tv_power_status_indicator.setText(SingletonDVR.getTheInstance().powerStatusIndicator.toString());
                }
            }
        });

        swch_to_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (States states : States.values()) {
                    if (states.toString().equalsIgnoreCase(state_status.getText().toString())) {
                        SingletonDVR.getTheInstance().stateStatus = states;
                    }
                }
                for (PowerState powerState : PowerState.values()) {
                    if (powerState.toString().equalsIgnoreCase(tv_power_status_indicator.getText().toString())) {
                        SingletonDVR.getTheInstance().powerStatusIndicator = powerState;
                    }
                }
                finish();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonDVR.getTheInstance().stateStatus == States.Recording){
                    toaster("Cannot " + States.Playing.toString() + " while in " + SingletonDVR.getTheInstance().stateStatus.toString());

                }else{
                    SingletonDVR.getTheInstance().stateStatus = States.Playing;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonDVR.getTheInstance().stateStatus = States.Stopped;
                state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
            }
        });
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonDVR.getTheInstance().stateStatus == States.Stopped){
                    SingletonDVR.getTheInstance().stateStatus = States.Recording;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                }else{
                    toaster("Cannot " + States.Recording.toString() + " while in " + SingletonDVR.getTheInstance().stateStatus.toString());
                }
            }
        });

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonDVR.getTheInstance().stateStatus == States.Playing){
                    SingletonDVR.getTheInstance().stateStatus = States.Fast_Forwarding;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                }else{
                    toaster("Cannot " + States.Fast_Forwarding.toString() + " while in " + SingletonDVR.getTheInstance().stateStatus.toString());
                }
            }
        });

        btn_rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonDVR.getTheInstance().stateStatus == States.Playing){
                    SingletonDVR.getTheInstance().stateStatus = States.Fast_Rewinding;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                }else{
                    toaster("Cannot " + States.Fast_Rewinding.toString() + " while in " + SingletonDVR.getTheInstance().stateStatus.toString());
                }
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonDVR.getTheInstance().stateStatus == States.Playing){
                    SingletonDVR.getTheInstance().stateStatus = States.Pause;
                    state_status.setText(SingletonDVR.getTheInstance().stateStatus.toString());
                }else{
                    toaster("Cannot " + States.Pause.toString() + " while in " + SingletonDVR.getTheInstance().stateStatus.toString());
                }
            }
        });

    }

    public void toaster(String err){
        Toast.makeText(this,err, Toast.LENGTH_LONG).show();
    }

    public void Disable(){
        ((TextView) findViewById(R.id.tv_power_status_indicator)).setText("Off");
        ((Button) findViewById(R.id.btn_play)).setEnabled(false);
        ((Button) findViewById(R.id.btn_stop)).setEnabled(false);
        ((Button) findViewById(R.id.btn_pause)).setEnabled(false);
        ((Button) findViewById(R.id.btn_record)).setEnabled(false);
        ((Button) findViewById(R.id.btn_forward)).setEnabled(false);
        ((Button) findViewById(R.id.btn_rewind)).setEnabled(false);
    }

    public void Enable(){

        ((TextView) findViewById(R.id.tv_power_status_indicator)).setText("On");
        ((Button) findViewById(R.id.btn_play)).setEnabled(true);
        ((Button) findViewById(R.id.btn_stop)).setEnabled(true);
        ((Button) findViewById(R.id.btn_pause)).setEnabled(true);
        ((Button) findViewById(R.id.btn_record)).setEnabled(true);
        ((Button) findViewById(R.id.btn_forward)).setEnabled(true);
        ((Button) findViewById(R.id.btn_rewind)).setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zr_activity_dvr, menu);
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

    public  void onEnable(Button buttonIn){
        buttonIn.setEnabled(true);
    }

    public  void onDisable(Button buttonIn){
        buttonIn.setEnabled(false);
    }
}
