package com.example.mario.calcubasica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    int valor=-1,valores=0;
    int resultado=0;
    int valorMS;
    String val="";
    char op='x';
    boolean masOperaciones=false, valorGuardado=false;
    EditText pantalla;
    Button boton0,boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8,boton9,botonI,botonPor,botonDiv,botonSum,botonRes,botonAC,MS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicializarVar();
        MS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorGuardado){
                    val=valorMS+"";
                    pantalla.setText((val));
                    valorGuardado=false;
                }else {
                    valorMS = Integer.parseInt(pantalla.getText().toString());
                    pantalla.setText("");
                    val="";
                    valorGuardado=true;
                }
            }
        });
        botonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado=0;
                valores=0;
                pantalla.setText("");
            }
        });
        boton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=0;
                pantalla.setText(val);
            }
        });
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=1;
                pantalla.setText(val);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=2;
                pantalla.setText(val);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=3;
                pantalla.setText(val);
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=4;
                pantalla.setText(val);
            }
        });
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=5;
                pantalla.setText(val);
            }
        });
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=6;
                pantalla.setText(val);
            }
        });
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=7;
                pantalla.setText(val);
            }
        });
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=8;
                pantalla.setText(val);
            }
        });
        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val+=9;
                pantalla.setText(val);
            }
        });
        botonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (masOperaciones==false) {
                    valor = Integer.parseInt(pantalla.getText().toString());
                    pantalla.setText("");
                    val = "";
                    op = 'S';
                    masOperaciones=true;
                }else{
                    valores+=valor;
                    valor = Integer.parseInt(pantalla.getText().toString());
                    pantalla.setText("");
                    val = "";
                    op = 'S';
                }
            }
        });
        botonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor = Integer.parseInt(pantalla.getText().toString());
                pantalla.setText("");
                val = "";
                op='R';
            }
        });
        botonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor = Integer.parseInt(pantalla.getText().toString());
                pantalla.setText("");
                val = "";
                op='D';
            }
        });
        botonPor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor = Integer.parseInt(pantalla.getText().toString());
                pantalla.setText("");
                val = "";
                op='M';
            }
        });
        botonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(op);
                masOperaciones=false;
            }
        });
    }
    public void InicializarVar(){
        boton0 = findViewById(R.id.btn0);
        boton1 = findViewById(R.id.btn1);
        boton2 = findViewById(R.id.btn2);
        boton3 = findViewById(R.id.btn3);
        boton4 = findViewById(R.id.btn4);
        boton5 = findViewById(R.id.btn5);
        boton6 = findViewById(R.id.btn6);
        boton7 = findViewById(R.id.btn7);
        boton8 = findViewById(R.id.btn8);
        boton9 = findViewById(R.id.btn9);
        botonI = findViewById(R.id.btnIgual);
        botonPor = findViewById(R.id.btnMultiplicacion);
        botonDiv = findViewById(R.id.btnDivision);
        botonSum = findViewById(R.id.btnSuma);
        botonRes = findViewById(R.id.btnResta);
        botonAC =  findViewById(R.id.btnAC);
        pantalla = findViewById(R.id.txtpantalla);
        MS = findViewById(R.id.btnMS);
    }
    public void operacion(char accion){
        if (accion=='S'){
            suma();
            op='x';
        }
        if (accion=='R'){
            resta();
            op='x';
        }
        if (accion=='M'){
            Multiplicacion();
            op='x';
        }
        if (accion=='D'){
            Division();
            op='x';
        }
    }
    public void suma(){
        if (masOperaciones) {
            resultado = valor + Integer.parseInt(val);
            val = resultado + "";
            pantalla.setText(val);
            val = "";
            valor=0;
        }else{
            resultado += valores + Integer.parseInt(val);
            val = resultado + "";
            pantalla.setText(val);
            val = "";
        }
    }
    public void resta(){
        resultado=valor-Integer.parseInt(val);
        val=resultado+"";
        pantalla.setText(val);
        val = "";
        valor=0;
    }
    public void Multiplicacion(){
//        valor = Integer.parseInt(pantalla.getText().toString());
//        if (resultado==0){
//            resultado=valor;
//        }else {
//            resultado *= valor;
//        }
//        pantalla.setText(resultado+" * ");
//        val = "";
        resultado=valor*Integer.parseInt(val);
        val=resultado+"";
        pantalla.setText(val);
        val = "";
        valor=0;
    }
    public void Division(){
//        valor = Integer.parseInt(pantalla.getText().toString());
//        if (resultado==0){
//            resultado=valor;
//        }else {
//            resultado /= valor;
//        }
//        pantalla.setText(resultado+" / ");
//        val = "";
        resultado=valor/Integer.parseInt(val);
        val=resultado+"";
        pantalla.setText(val);
        val = "";
        valor=0;
    }
}
