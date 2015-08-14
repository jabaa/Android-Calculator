package de.sdwuppertal.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private enum Op{None, Addition, Subtraction};
    private static final String tag = "TS";
    private TextView editText;
    private Button[] buttons;
    private double tempNum;
    private Op lastOperation;
    private boolean oldNum;
    private TextView textViewDebug;
    private static final String dot = ".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.editText);
        textViewDebug = (TextView) findViewById(R.id.textViewDebug);
        buttons = new Button[13];
        buttons[0] = (Button) findViewById(R.id.button0);
        buttons[1] = (Button) findViewById(R.id.button1);
        buttons[2] = (Button) findViewById(R.id.button2);
        buttons[3] = (Button) findViewById(R.id.button3);
        buttons[4] = (Button) findViewById(R.id.button4);
        buttons[5] = (Button) findViewById(R.id.button5);
        buttons[6] = (Button) findViewById(R.id.button6);
        buttons[7] = (Button) findViewById(R.id.button7);
        buttons[8] = (Button) findViewById(R.id.button8);
        buttons[9] = (Button) findViewById(R.id.button9);
        buttons[10] = (Button) findViewById(R.id.buttonDot);
        buttons[11] = (Button) findViewById(R.id.buttonPlus);
        buttons[12] = (Button) findViewById(R.id.buttonMinus);
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }
        lastOperation = Op.None;
        oldNum = true;
    }

    void pushNumber(int i){
        String s = editText.getText().toString();
        if(s.equals("0") || oldNum){
            s = Integer.toString(i);
            oldNum = false;
        }else{
            s += Integer.toString(i);
        }
        editText.setText(s);
    }

    void pushDot(){
        Log.v(tag, "buttonDot");
        String s = editText.getText().toString();
        if(s.equals("0") || oldNum){
            s = "0" + dot;
            oldNum = false;
        }else{
            s += dot;
        }
        editText.setText(s);
    }

    void mathOperation(Op op){
        double result = 0;
        switch(lastOperation){
            case None:
                result = Double.parseDouble(editText.getText().toString());
                break;
            case Addition:
                result = tempNum + Double.parseDouble(editText.getText().toString());
                break;
            case Subtraction:
                result = tempNum - Double.parseDouble(editText.getText().toString());
                break;
        }
        if(result != (long) result) {
            editText.setText(String.format("%s", result));
        }else{
            editText.setText(String.format("%d", (long) result));
        }
        tempNum = result;
        oldNum = true;
        lastOperation = op;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button0:
                pushNumber(0);
                break;
            case R.id.button1:
                pushNumber(1);
                break;
            case R.id.button2:
                pushNumber(2);
                break;
            case R.id.button3:
                pushNumber(3);
                break;
            case R.id.button4:
                pushNumber(4);
                break;
            case R.id.button5:
                pushNumber(5);
                break;
            case R.id.button6:
                pushNumber(6);
                break;
            case R.id.button7:
                pushNumber(7);
                break;
            case R.id.button8:
                pushNumber(8);
                break;
            case R.id.button9:
                pushNumber(9);
                break;
            case R.id.buttonPlus:
                mathOperation(Op.Addition);
                break;
            case R.id.buttonMinus:
                mathOperation(Op.Subtraction);
                break;
            case R.id.buttonDot:
                pushDot();
                break;
        }
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
