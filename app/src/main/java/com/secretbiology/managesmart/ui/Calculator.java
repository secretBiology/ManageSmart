package com.secretbiology.managesmart.ui;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretbiology.managesmart.R;

import java.text.DecimalFormat;

public class Calculator implements View.OnClickListener{
    private Activity activity;
    private double answer;
    private double first_number, second_number;
    private TextView Display, secondaryDisplay;
    Dialog dialog;
    private int currentFunction;
    boolean firstTransaction, isDot, nextOpNeedOne;
    ImageView backButton;
    String tempDisplay, dotString;

    public Calculator(Activity activity) {
        this.activity = activity;
    }

    public void makeCalculator() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.calculator);
        dialog.setCanceledOnTouchOutside(true);
        Display = (TextView)dialog.findViewById(R.id.cal_display);
        secondaryDisplay = (TextView)dialog.findViewById(R.id.cal_secondary_display);
        secondaryDisplay.setText("");
        Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
        Button clearall, equals, done, dot, switchSign;
        Button add, minus, multiply, divide;

        backButton = (ImageView) dialog.findViewById(R.id.cal_back);
        b1 = (Button) dialog.findViewById(R.id.cal_1);
        b2 = (Button) dialog.findViewById(R.id.cal_2);
        b3 = (Button) dialog.findViewById(R.id.cal_3);
        b4 = (Button) dialog.findViewById(R.id.cal_4);
        b5 = (Button) dialog.findViewById(R.id.cal_5);
        b6 = (Button) dialog.findViewById(R.id.cal_6);
        b7 = (Button) dialog.findViewById(R.id.cal_7);
        b8 = (Button) dialog.findViewById(R.id.cal_8);
        b9 = (Button) dialog.findViewById(R.id.cal_9);
        b0 = (Button) dialog.findViewById(R.id.cal_0);
        clearall = (Button) dialog.findViewById(R.id.cal_clearall);
        equals = (Button) dialog.findViewById(R.id.cal_equals);
        done = (Button) dialog.findViewById(R.id.cal_done);
        add = (Button) dialog.findViewById(R.id.cal_add);
        minus = (Button) dialog.findViewById(R.id.cal_minus);
        multiply = (Button) dialog.findViewById(R.id.cal_multiply);
        divide = (Button) dialog.findViewById(R.id.cal_divide);
        dot = (Button) dialog.findViewById(R.id.cal_dot);
        switchSign = (Button)dialog.findViewById(R.id.cal_plusMinus);

        backButton.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        clearall.setOnClickListener(this);
        equals.setOnClickListener(this);
        done.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        dot.setOnClickListener(this);
        switchSign.setOnClickListener(this);
        dialog.show();
        clearAll();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.cal_done: dialog.dismiss(); break;
            case R.id.cal_0: addToTempNumber(0); break;
            case R.id.cal_1: addToTempNumber(1); break;
            case R.id.cal_2: addToTempNumber(2); break;
            case R.id.cal_3: addToTempNumber(3); break;
            case R.id.cal_4: addToTempNumber(4); break;
            case R.id.cal_5: addToTempNumber(5); break;
            case R.id.cal_6: addToTempNumber(6); break;
            case R.id.cal_7: addToTempNumber(7); break;
            case R.id.cal_8: addToTempNumber(8); break;
            case R.id.cal_9: addToTempNumber(9); break;
            case R.id.cal_add: add(); break;
            case R.id.cal_minus: minus(); break;
            case R.id.cal_multiply: multiply(); break;
            case R.id.cal_divide: divide(); break;
            case R.id.cal_clearall: clearAll(); break;
            case R.id.cal_back: backSpace(); break;
            case R.id.cal_equals: getAnswer(); break;
            case R.id.cal_dot: isDot = true; break;
            case R.id.cal_plusMinus: switchSign();break;
        }
    }

    private void clearAll(){
        first_number = 0;
        second_number = 0;
        currentFunction =0;
        answer = 0.0;
        Display.setText(String.valueOf(answer));
        secondaryDisplay.setText("");
        firstTransaction = true;
        tempDisplay = "";
        isDot = false;
        dotString = "";
        nextOpNeedOne = false;
    }

    private void addToTempNumber (int number){
        if(isDot){
            dotString = dotString+number;
            first_number = Double.parseDouble(String.valueOf(first_number).split("\\.")[0]+"."+dotString);
        }
        else {
            first_number = first_number * 10 + number;
        }
        addtoSecondaryDisplay(String.valueOf(first_number));
        Display.setText(String.valueOf(first_number));
    }

    private void backSpace(){
        String tempstring = (String) Display.getText();
        String secondaryString = (String) secondaryDisplay.getText();
        tempstring = tempstring.substring(0,tempstring.length()-1);
        secondaryString = secondaryString.substring(0,secondaryString.length()-1);

        if(tempstring.length()==0){
            tempstring = "0";
        }
        if(secondaryString.length()==0){
            secondaryString = "0";
        }
        first_number = Double.parseDouble(tempstring);
        secondaryDisplay.setText(secondaryString);
        Display.setText(tempstring);
    }

    private void getAnswer(){
        isDot = false;
        dotString = "";
        switch (currentFunction){
            case 1:
                add();break;
            case 2:
                minus(); break;
            case 3:
                multiply();break;
            case 4:
                divide(); break;
            default:
                answer = first_number;
                showAnswer();
                break;
        }

        tempDisplay = "";
        secondaryDisplay.setText("");
        firstTransaction = true;
        second_number = answer;
        first_number = 0;
        currentFunction = 0;
    }

    private void add(){
        isDot = false;
        dotString = "";
        if(currentFunction!=1){
            doCalculations();
        }
        if(secondaryDisplay.getText().equals("")){
            tempDisplay = String.valueOf(answer)+" + ";
        }
        else {
            tempDisplay = secondaryDisplay.getText() + " + ";
        }
        addtoSecondaryDisplay("");
        answer = first_number + second_number;
        if(firstTransaction){
            firstTransaction = false;
        }
        else {
            showAnswer();
        }
        second_number = answer;
        first_number = 0;
        currentFunction=1;
    }

    private void minus(){
        isDot = false;
        dotString = "";
        if(currentFunction!=2){
            doCalculations();
        }
        if(secondaryDisplay.getText().equals("")){
            tempDisplay = String.valueOf(answer)+" - ";
            first_number = answer;
        }
        else {
            tempDisplay = secondaryDisplay.getText() + " - ";
        }
        addtoSecondaryDisplay("");

        if(firstTransaction){
            firstTransaction = false;
            answer = first_number;
        }
        else {
            answer = second_number - first_number;
            showAnswer();
        }
        second_number = answer;
        first_number = 0;
        currentFunction=2;
    }

    private void multiply(){
        isDot = false;
        dotString = "";
        if(currentFunction!=3){
            nextOpNeedOne = true;
            doCalculations();
        }
        if(secondaryDisplay.getText().equals("")){
            tempDisplay = "("+String.valueOf(answer)+" ) x ";
            first_number = answer;
        }
        else {
            tempDisplay = "("+secondaryDisplay.getText() + " ) x ";
        }
        addtoSecondaryDisplay("");

        if(firstTransaction){
            firstTransaction = false;
            answer = first_number;
        }
        else {
            answer = second_number * first_number;
            showAnswer();
        }
        second_number = answer;
        first_number = 0;
        currentFunction=3;
    }

    private void divide(){
        isDot = false;
        dotString = "";
        if(currentFunction!=4){
            nextOpNeedOne = true;
            doCalculations();
        }
        if(secondaryDisplay.getText().equals("")){
            tempDisplay = "("+String.valueOf(answer)+" ) / ";
            first_number = answer;
        }
        else {
            tempDisplay = "("+secondaryDisplay.getText() + " ) / ";
        }
        addtoSecondaryDisplay("");

        if(firstTransaction){
            firstTransaction = false;
            answer = first_number;
        }
        else {
            if(first_number==0){
                clearAll();
                Display.setText("ERROR");
            }
            else{
                answer = second_number / first_number;
                DecimalFormat df = new DecimalFormat("#.####");
                answer = Double.parseDouble(df.format(answer));
                showAnswer();
            }

        }
        second_number = answer;
        first_number = 0;
        currentFunction=4;
    }

    private void doCalculations(){
        isDot = false;
        dotString = "";
        switch (currentFunction){
            case 1:
                answer = first_number + second_number;
                showAnswer();
                second_number = answer;
                first_number = 0;
                break;
            case 2:
                answer = second_number - first_number;
                showAnswer();
                second_number = answer;
                first_number = 0;
                break;
            case 3:
                answer = first_number*second_number;
                showAnswer();
                second_number = answer;
                first_number = 0;
                break;
            case 4:
                if(first_number==0){
                    clearAll();
                    Display.setText("ERROR");
                }
                else {
                    answer = second_number / first_number;
                    DecimalFormat df = new DecimalFormat("#.####");
                    answer = Double.parseDouble(df.format(answer));
                    showAnswer();
                    second_number = answer;
                    first_number = 0;
                }
                break;
            default:
                break;
        }

        if(nextOpNeedOne){
            first_number = 1;
            nextOpNeedOne = false;
        }

    }

    private void showAnswer(){
        if(String.valueOf(answer).split("\\.")[1].length()>4) {
            DecimalFormat df = new DecimalFormat("#.####");
            answer = Double.parseDouble(df.format(answer));
        }
        Display.setText(String.valueOf(answer));
    }

    private void addtoSecondaryDisplay(String string){

        secondaryDisplay.setText(tempDisplay+" "+string);
    }

    private void switchSign(){
        String temp = (String) secondaryDisplay.getText();
        if(0-first_number<0) {
            temp = temp.replace(String.valueOf(first_number), "(-" + String.valueOf(first_number)+")");
        }
        else {
            if(temp.contains("(-")) {
                temp = temp.replace("(" +String.valueOf(first_number)+")", String.valueOf(0 - first_number));
            }
            else {
                temp = temp.replace(String.valueOf(first_number), String.valueOf(0 - first_number));
            }
        }
        secondaryDisplay.setText(temp);
        first_number = 0 - first_number;
        Display.setText(String.valueOf(first_number));
    }
}
