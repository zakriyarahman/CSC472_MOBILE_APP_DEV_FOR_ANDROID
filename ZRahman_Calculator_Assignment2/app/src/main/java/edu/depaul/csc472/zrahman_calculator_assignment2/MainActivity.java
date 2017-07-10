package edu.depaul.csc472.zrahman_calculator_assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int number_limit = 7;
    int counter = 0;
    int answer = 0;
    boolean startNum = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtAnswer = (TextView) findViewById(R.id.answer);

        Button btn_one = (Button) findViewById(R.id.btn_one);
        Button btn_two = (Button) findViewById(R.id.btn_two);
        Button btn_three = (Button) findViewById(R.id.btn_three);
        Button btn_four = (Button) findViewById(R.id.btn_four);
        Button btn_five = (Button) findViewById(R.id.btn_five);
        Button btn_six = (Button) findViewById(R.id.btn_six);
        Button btn_seven = (Button) findViewById(R.id.btn_seven);
        Button btn_eight = (Button) findViewById(R.id.btn_eight);
        Button btn_nine = (Button) findViewById(R.id.btn_nine);
        Button btn_plus = (Button) findViewById(R.id.btn_plus);
        Button btn_zero = (Button) findViewById(R.id.btn_zero);
        Button btn_equals = (Button) findViewById(R.id.btn_equals);

        View.OnClickListener clk_number = new View.OnClickListener(){
            public void onClick(View view){
                counter ++;
                if(!startNum){
                    if(counter < 7)
                    txtAnswer.append(((Button) view).getText());
                }else{
                    startNum = false;
                    txtAnswer.setText(((Button) view).getText());
                }
            }
        };

        View.OnClickListener clk_plus = new View.OnClickListener(){
            public void onClick(View view){
                startNum = true;
                int temp = Integer.parseInt(txtAnswer.getText().toString());
                answer += temp;
                txtAnswer.setText(String.valueOf(answer));
                counter = 0;
            }
        };

        View.OnClickListener clk_equals = new View.OnClickListener(){
            public void onClick(View view){
                startNum = true;
                int temp = Integer.parseInt(txtAnswer.getText().toString());
                answer += temp;
                txtAnswer.setText(String.valueOf(answer));
                answer = 0;
                counter = 0;
            }
        };

        btn_one.setOnClickListener(clk_number);
        btn_two.setOnClickListener(clk_number);
        btn_three.setOnClickListener(clk_number);
        btn_four.setOnClickListener(clk_number);
        btn_five.setOnClickListener(clk_number);
        btn_six.setOnClickListener(clk_number);
        btn_seven.setOnClickListener(clk_number);
        btn_eight.setOnClickListener(clk_number);
        btn_nine.setOnClickListener(clk_number);
        btn_plus.setOnClickListener(clk_plus);
        btn_equals.setOnClickListener(clk_equals);
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
