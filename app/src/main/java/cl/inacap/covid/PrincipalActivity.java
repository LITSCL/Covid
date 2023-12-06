package cl.inacap.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.covid.adapters.PacienteArrayAdaptador;
import cl.inacap.covid.dao.PacienteDAO;
import cl.inacap.covid.dao.PacienteDAOSQLite;
import cl.inacap.covid.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {
    private ListView pacientesLv;
    private List<Paciente> pacientes;
    private PacienteArrayAdaptador adaptador;
    private PacienteDAO daoPacientes = new PacienteDAOSQLite(this);
    private FloatingActionButton agregarBtn;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void onResume() {
        super.onResume();
        pacientes = daoPacientes.getAll();
        adaptador = new PacienteArrayAdaptador(this, R.layout.pacientes_list, pacientes);
        pacientesLv = findViewById(R.id.pacientesLv);
        pacientesLv.setAdapter(adaptador);
        pacientesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PrincipalActivity.this, VerPacienteActivity.class);
                Paciente pacienteActual = pacientes.get(i);
                intent.putExtra("paciente", pacienteActual);
                startActivity(intent);
            }
        });
        agregarBtn=findViewById(R.id.agregarBtn);
        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, RegistrarPacienteActivity.class));
            }
        });
    }
}