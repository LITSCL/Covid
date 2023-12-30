package cl.inacap.covid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.inacap.covid.dao.PacienteDAO;
import cl.inacap.covid.dao.PacienteDAOSQLite;
import cl.inacap.covid.dto.Paciente;
import cl.inacap.covid.utils.RutUtils;

public class RegistrarPacienteActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ingresarRutPacienteEt;
    EditText ingresarNombrePacienteEt;
    EditText ingresarApellidoPacienteEt;
    EditText fechaEt;
    EditText ingresarTemperaturaPacienteEt;
    EditText ingresarPresionAterialPacienteEt;
    Switch ingresarPresentaSintomasCovidPacienteSw;
    Switch ingresarPresentaTosPacienteSw;
    Spinner ingresarAreaDeTrabajoPacienteSpn;
    Button fechaBtn;
    Button registrarPacienteBtn;
    private int dia, mes, year;
    private String rut, nombre, apellido, fecha, areaTrabajo;
    private int presion;
    private double temperatura;
    private boolean presentaSintomasCovid, presentaTos;
    private PacienteDAO daoPacientes = new PacienteDAOSQLite(this);
    private RutUtils rutUtils = new RutUtils();
    private String diaString, mesString, yearString;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        this.setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.ingresarRutPacienteEt = findViewById(R.id.ingresarRutPacienteEt);
        this.ingresarNombrePacienteEt = findViewById(R.id.ingresarNombrePacienteEt);
        this.ingresarApellidoPacienteEt = findViewById(R.id.ingresarApellidoPacienteEt);
        this.fechaEt=findViewById(R.id.fechaEt);
        this.ingresarTemperaturaPacienteEt = findViewById(R.id.ingresarTemperaturaPacienteEt);
        this.ingresarPresionAterialPacienteEt = findViewById(R.id.ingresarPresionAterialPacienteEt);
        this.ingresarPresentaSintomasCovidPacienteSw = findViewById(R.id.ingresarPresentaSintomasCovidPacienteSw);
        this.ingresarPresentaTosPacienteSw = findViewById(R.id.ingresarPresentaTosPacienteSw);
        this.ingresarAreaDeTrabajoPacienteSpn = findViewById(R.id.ingresarAreaDeTrabajoPacienteSpn);
        this.fechaBtn=findViewById(R.id.fechaBtn);
        this.registrarPacienteBtn = findViewById(R.id.registrarPacienteBtn);
        fechaBtn.setOnClickListener(this);

        ArrayList<String> listaAreaTrabajo = new ArrayList<>();
        listaAreaTrabajo.add("Ateción a público");
        listaAreaTrabajo.add("Otro");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaAreaTrabajo);
        ingresarAreaDeTrabajoPacienteSpn.setAdapter(adapter);

        ingresarAreaDeTrabajoPacienteSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                areaTrabajo = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        registrarPacienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                rut=ingresarRutPacienteEt.getText().toString().trim();
                if (rutUtils.verificaRutChileno(rut) != true) {
                    errores.add("Rut no válido");
                }
                nombre = ingresarNombrePacienteEt.getText().toString().trim();
                if (nombre.isEmpty()) {
                    errores.add("Nombre no válido");
                }
                apellido = ingresarApellidoPacienteEt.getText().toString().trim();
                if (apellido.isEmpty()) {
                    errores.add("Apellido no válido");
                }

                fecha = fechaEt.getText().toString().trim();
                if (fecha.isEmpty()) {
                    errores.add("Fecha no ingresada");
                }
                else {
                    Calendar c = Calendar.getInstance();
                    String diaActual = Integer.toString(c.get(Calendar.DATE));
                    String mesActual = Integer.toString(c.get(Calendar.MONTH) + 1);
                    String yearActual = Integer.toString(c.get(Calendar.YEAR));
                    if (diaActual.length() == 1) {
                        diaActual = "0" + diaActual;
                    }
                    if (mesActual.length() == 1) {
                        mesActual = "0" + mesActual;
                    }

                    try {
                        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaIngresada = sdformat.parse(fecha);
                        Date fechaActual = sdformat.parse(diaActual + "/" + mesActual + "/" + yearActual);

                        int contador = 0;
                        if (fechaIngresada.after(fechaActual) == false) {
                           contador++;
                        }
                        if (fechaIngresada.equals(fechaActual)){
                            contador = 0;
                        }
                        if (contador != 0){
                            errores.add("Fecha no válida");
                        }
                    } catch (ParseException ex) {

                    }
                }

                presentaSintomasCovid = ingresarPresentaSintomasCovidPacienteSw.isChecked();
                try {
                    temperatura = Double.parseDouble(ingresarTemperaturaPacienteEt.getText().toString().trim());
                } catch (Exception ex) {
                    errores.add("Temperatura no ingresada");
                }
                presentaTos = ingresarPresentaTosPacienteSw.isChecked();
                try {
                    presion = Integer.parseInt(ingresarPresionAterialPacienteEt.getText().toString().trim());
                } catch (Exception ex) {
                    errores.add("Presion no ingresada");
                }

                if (errores.size() != 0){
                    mostrarErrores(errores);
                }
                else {
                    Paciente p = new Paciente();
                    p.setRut(rut);
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setFechaExamen(fecha);
                    p.setPresentaSintomasCovid(presentaSintomasCovid);
                    p.setTemperatura(temperatura);
                    p.setPresentaTos(presentaTos);
                    p.setPresionArterial(presion);
                    p.setAreaTrabajo(areaTrabajo);
                    daoPacientes.save(p);

                    startActivity(new Intent(RegistrarPacienteActivity.this, PrincipalActivity.class));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == fechaBtn) {
            final Calendar ca = Calendar.getInstance();
            dia = ca.get(Calendar.DAY_OF_MONTH);
            mes = ca.get(Calendar.MONTH);
            year = ca.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int mes, int dia) {
                    String fecha = dia + "/" + (mes + 1) + "/" + year;
                    yearString = Integer.toString(year);
                    if (Integer.toString(dia).length() == 1 && Integer.toString(mes + 1).length() == 1) {
                        diaString = "0" + dia;
                        mesString = "0" + (mes + 1);
                        fechaEt.setText("0" + dia + "/" + "0" + (mes + 1) + "/" + year);
                    }
                    else if (Integer.toString(dia).length() == 1) {
                        diaString = "0" + dia;
                        mesString = Integer.toString(mes + 1);
                        fechaEt.setText("0" + dia + "/" + (mes + 1) + "/" + year);
                    }
                    else if (Integer.toString(mes + 1).length() == 1) {
                        diaString = Integer.toString(dia);
                        mesString = "0" + (mes + 1);
                        fechaEt.setText(dia + "/" + "0" + (mes+1) + "/" + year);
                    }
                    else {
                        diaString = Integer.toString(dia);
                        mesString = Integer.toString(mes + 1);
                        fechaEt.setText(fecha);
                    }
                }
            }, dia, mes, year);
            datePickerDialog.show(); //Aca es donde se hace visible el DatePickerDialog.
        }
    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e : errores) {
            mensaje+="-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(RegistrarPacienteActivity.this); //En el argumento se establece que Activity mostrará el mensaje.
        alertBuilder
            .setTitle("Error de validación") //Define el titulo.
            .setMessage(mensaje) //Define el mensaje del dialogo.
            .setPositiveButton("Aceptar", null) //Agrega el botón aceptar.
            .create() //Crea el Alert.
            .show(); //Se muestra el Alert.
    }
}