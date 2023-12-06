package cl.inacap.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.covid.adapters.VerPacienteArrayAdaptador;
import cl.inacap.covid.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView verPacientesLv;
    private List<Paciente> paciente = new ArrayList<>();
    private VerPacienteArrayAdaptador adaptador;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.verPacientesLv = findViewById(R.id.verPacientesLv);
        if (getIntent() != null) {
            Paciente pacienteElegido = (Paciente)getIntent().getSerializableExtra("paciente");
            paciente.add(pacienteElegido);
            adaptador = new VerPacienteArrayAdaptador(this, R.layout.ver_paciente_list, paciente);
            verPacientesLv.setAdapter(adaptador);
        }
    }
}