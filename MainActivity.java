package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //workings = "2+3-5" ---input
    //result = "0" ---output
    //eval -- use this to compute
    //2^3 = 8? ^ is XoR operator x
    //math.pow(2,3) = 8

    TextView resultsTV;
    TextView workingsTV;

    String formula = "";
    String workings = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //3^2+5^2
    //0123456

    private void intTextviews(){
        workingsTV = (TextView) findViewById(R.id.workingsTextView);
        resultsTv = (TextView) findViewById(R.id.workingsTextView);
    }
    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }
    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i=0;i<workings.length();i++)
        {
            if(workings.charAt(i)=='^')
            {
                indexOfPowers.add(i);
            }
        }
        formula=workings;
        for(int i=0;i<indexOfpowers.size();i++)
        {
            changeFormula(indexOfPowers.get(i));
        }
        formula = tempFormula;
    }
    private void changeFormula(Integer index)
    {
        String numberLeft ="";
        String numberRight ="";
        for(int i = index;i<workings.length();i++) {
            if (isNumeric(workings.charAt(i))) {
                numberRight = numberRight + workings.charAt(i);
            } else {
                break;
            }
        }
        for(int i =index-1;i>0;i++)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberLeft = workings.charAt(i) + numberLeft;
            }
            else
                break;
        }
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }
    private boolean isNumeric(char c)
    {
        if((c<='9' && c>='0') || c =='.')
        {
            return true;
        }
        else
            return false;
    }
    public void clearOnClick(View view){
        workingsTV.setText("");
        resultsTV.setText("");
        workings = "";
        leftBracket = true;
    }
    public void bracketsOnClick(View view){
        if(leftBracket == true){
            setWorkings("(");
            leftBracket = false;
        }
        else{
            setWorkings("(");
            leftBracket = true;
        }
    }
    public void powerOfOnClick(View view){
        setWorkings("^");
    }
    public void oneOnClick(View view){
        setWorkings("1");
    }
    public void twoOnClick(View view){
        setWorkings("2");
    }
    public void divisionOnClick(View view){
        setWorkings("/");
    }
}