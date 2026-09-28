package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AvanzadaActivity extends AppCompatActivity {


    TextView txResultado;

    char operador = '0';
    String primerOperando = "";
    String segundoOperando = "";
    Calculadora calculadora;
    String ultimoResultado = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanzada);
        calculadora = new Calculadora();

        txResultado = findViewById(R.id.txResultado);

    }

    public void limpiar(View view){
        txResultado.setText("");
        operador = '0';
        primerOperando = "";
        segundoOperando = "";
    }
    public void botonNumeroPulsar(View view){
        Button btn = (Button) view;
        txResultado.append(btn.getText());
        if (operador == '0') {
            primerOperando = primerOperando.concat(btn.getText().toString());
        } else {
            segundoOperando = segundoOperando.concat(btn.getText().toString());
        }
    }

    public void botonOperacionPulsar(View view){
        Button btn = (Button) view;
        if (primerOperando.isEmpty()) {
            if (btn.getText().toString().charAt(0) == '-'){
                primerOperando = "-";
                txResultado.append("-");
                return;
            } else return;
        }
        if (primerOperando.length() == 1 && primerOperando.charAt(0) == '-') return;
        if (primerOperando.charAt(primerOperando.length() - 1) == '.') return;
        if (operador == '0') {
           operador = btn.getText().toString().charAt(0);
           txResultado.append(btn.getText());
        }
    }

    public void botonDecimal(View view) {
        if (primerOperando.isEmpty() || (primerOperando.length() == 1 && primerOperando.charAt(0) == '-')) {
            txResultado.append("0.");
            primerOperando = "0.";
        } else if (operador == '0') {
            if (!primerOperando.contains(".")) {
                primerOperando = primerOperando.concat(".");
                txResultado.append(".");
            }
        } else {
            if (segundoOperando.isEmpty()) {
                txResultado.append("0.");
                segundoOperando = "0.";
            } else if (!segundoOperando.contains(".")) {
                segundoOperando = segundoOperando.concat(".");
                txResultado.append(".");
            }
        }
    }


    public void botonIgual(View view){
        if (primerOperando.isEmpty() || segundoOperando.isEmpty()
                || segundoOperando.charAt(segundoOperando.length() - 1) == '.'
                || (segundoOperando.length() == 1 && segundoOperando.charAt(0) == '-')) return;
        calculadora.setOper1(Double.valueOf(primerOperando));
        calculadora.setOper2(Double.valueOf(segundoOperando));
        calculadora.setOperacion(Calculadora.OPERACION.fromSymbol(String.valueOf(operador)));
        double result = calculadora.opera();
        String resultado = String.valueOf(result);
        if (resultado.charAt(resultado.length()-1) == '0' && resultado.charAt(resultado.length() - 2) == '.') resultado = resultado.substring(0, resultado.length() - 2);
        txResultado.setText(resultado);
        operador = '0';
        primerOperando = resultado;
        ultimoResultado = resultado;
        segundoOperando = "";
    }

    public void botonRetroceso(View view){
        if (segundoOperando.isEmpty()){
            if (operador == '0'){
                if (primerOperando.isEmpty()) return;
                primerOperando = primerOperando.substring(0, primerOperando.length() - 1);
            } else {
                operador = '0';
            }
        } else {
            segundoOperando = segundoOperando.substring(0, segundoOperando.length() - 1);
        }
        txResultado.setText(txResultado.getText().subSequence(0,txResultado.getText().length() - 1));
    }

    public void botonUltimoResultado(View view){
        if (primerOperando.isEmpty()) {
            primerOperando = ultimoResultado;
        } else if (segundoOperando.isEmpty() && operador != '0'){
            segundoOperando = ultimoResultado;
        } else {
            return;
        }
        txResultado.append(ultimoResultado);
    }

}