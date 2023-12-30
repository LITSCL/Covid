package cl.inacap.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.inacap.covid.utils.RutUtils;

public class MainActivity extends AppCompatActivity {
    private EditText nombreUsuarioEt;
    private EditText claveUsuarioEt;
    private RutUtils rutUtils = new RutUtils();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreUsuarioEt = findViewById(R.id.nombreUsuarioEt);
        this.claveUsuarioEt = findViewById(R.id.claveUsuarioEt);
        nombreUsuarioEt.getBackground().setColorFilter(R.color.colorAccent, PorterDuff.Mode.SRC_ATOP);
        claveUsuarioEt.getBackground().setColorFilter(R.color.colorAccent, PorterDuff.Mode.SRC_ATOP);
    }

    @SuppressLint("ResourceAsColor")
    public void ingresar(View view) {
        if (nombreUsuarioEt.getText().toString().trim().length() != 0) {
            if (rutUtils.verificaRutChileno(nombreUsuarioEt.getText().toString().trim())) {
                String rutVerificado = nombreUsuarioEt.getText().toString().trim();
                String password = "";
                if (rutVerificado.length() == 9) {
                    password = Character.toString(rutVerificado.charAt(3)) +
                        Character.toString(rutVerificado.charAt(4)) +
                        Character.toString(rutVerificado.charAt(5)) +
                        Character.toString(rutVerificado.charAt(6));
                }
                else {
                    password = Character.toString(rutVerificado.charAt(4)) +
                        Character.toString(rutVerificado.charAt(5)) +
                        Character.toString(rutVerificado.charAt(6)) +
                        Character.toString(rutVerificado.charAt(7));
                }
                if (claveUsuarioEt.getText().toString().trim().equals(password)) {
                    startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                }
                else {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    nombreUsuarioEt.getBackground().setColorFilter(R.color.colorAccent, PorterDuff.Mode.SRC_ATOP);
                }
            }
            else {
                Toast.makeText(this, "Nombre de usuario invalido", Toast.LENGTH_SHORT).show();
                nombreUsuarioEt.getBackground().setColorFilter(R.color.colorAccent, PorterDuff.Mode.SRC_ATOP);
            }
        }
        else {
            //Aca se debe cambiar el color de borde del EditText Nombre.
            Toast.makeText(this, "Debe ingresar el nombre de usuario", Toast.LENGTH_SHORT).show();
            nombreUsuarioEt.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }
    }
}