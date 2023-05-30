package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView entryTV, resultTV;
    MaterialButton ButtonC, ButtonOPBrack, ButtonCLBrack, ButtonDiv;
    MaterialButton Button7, Button8, Button9, ButtonMul;
    MaterialButton Button4, Button5, Button6, ButtonAdd;
    MaterialButton Button1, Button2, Button3, ButtonSub;
    MaterialButton ButtonAC, Button0, ButtonPoint, ButtonEQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryTV = findViewById(R.id.entry_tv);
        resultTV = findViewById(R.id.result_tv);
        assignid(ButtonC,R.id.btn_C);
        assignid(ButtonOPBrack,R.id.btn_OPbrac);
        assignid(ButtonCLBrack,R.id.btn_CLbrac);
        assignid(ButtonDiv,R.id.btn_div);
        assignid(Button7,R.id.btn_7);
        assignid(Button8,R.id.btn_8);
        assignid(Button9,R.id.btn_9);
        assignid(ButtonMul,R.id.btn_mul);
        assignid(Button4,R.id.btn_4);
        assignid(Button5,R.id.btn_5);
        assignid(Button6,R.id.btn_6);
        assignid(ButtonAdd,R.id.btn_add);
        assignid(Button1,R.id.btn_1);
        assignid(Button2,R.id.btn_2);
        assignid(Button3,R.id.btn_3);
        assignid(ButtonSub,R.id.btn_sub);
        assignid(ButtonAC,R.id.btn_AC);
        assignid(Button0,R.id.btn_0);
        assignid(ButtonPoint,R.id.btn_point);
        assignid(ButtonEQ,R.id.btn_equal);
    }
    void assignid(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String btnText = button.getText().toString();
        String Cal = entryTV.getText().toString();

           if(btnText.equals("AC")){
               entryTV.setText("");
               resultTV.setText("0");
               return;
           }
           if(btnText.equals("=")){
               entryTV.setText(resultTV.getText());
               return;
           }
           if(btnText.equals("C")){
               Cal = Cal.substring(0,Cal.length()-1);
           }
           else {
               Cal = Cal+btnText;
           }




        entryTV.setText(Cal);
        String finalResult = getResult(Cal);

        if(!finalResult.equals("ERR")){
            resultTV.setText(finalResult);
        }


    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "ERR";
        }

    }
}