package com.example.calculatorapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn00, dot, add, multiply, divide, minus, c, ac, modulo, equal;
    TextView txtresult, txtexpression;


    String expression;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn00 = findViewById(R.id.btn00);

        dot = findViewById(R.id.dot);
        add = findViewById(R.id.add);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);

        ac = findViewById(R.id.ac);
        c = findViewById(R.id.c);
        modulo = findViewById(R.id.modulo);

        equal = findViewById(R.id.equal);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn00.setOnClickListener(this);

        dot.setOnClickListener(this);
        add.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        minus.setOnClickListener(this);

        ac.setOnClickListener(this);
        c.setOnClickListener(this);
        modulo.setOnClickListener(this);

        equal.setOnClickListener(this);

        txtexpression = findViewById(R.id.txtexpression);
        txtresult = findViewById(R.id.txtresult);

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        expression = txtexpression.getText().toString();

        if (buttonText.equals("AC")){
            txtexpression.setText("");
            txtresult.setText("");
            expression = "";
        } else if (buttonText.equals("C")){
            txtresult.setText("");
            expression = expression.substring(0, expression.length() - 1);
            txtexpression.setText(expression);
        } else if (buttonText.equals("=")) {
            double result = evaluateExpression(expression);
            String finalresult = String.valueOf(result);
            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0", "");
            }
            txtexpression.setText(finalresult);
            txtresult.setText(expression);
            expression =  "";

        }else {
            expression = expression + buttonText;
            txtexpression.setText(expression);
            txtresult.setText("");
        }




    }

    private double evaluateExpression(String expression) {
        try{
            double result = new ExpressionBuilder(expression).build().evaluate();
            if (Double.isNaN(result)){
                throw new ArithmeticException("Division By Zero or invalid Expression");
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return Double.NaN;
        }
    }
}