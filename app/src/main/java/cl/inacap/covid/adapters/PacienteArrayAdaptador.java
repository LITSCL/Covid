package cl.inacap.covid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.covid.R;
import cl.inacap.covid.dto.Paciente;

public class PacienteArrayAdaptador extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> pacientes;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.pacientes_list, null, true);
        TextView datosPacienteTv = fila.findViewById(R.id.datosPacienteTv);
        ImageView imagenCovidPacienteIv = fila.findViewById(R.id.imagenCovidPacienteIv);
        Paciente pacienteActual = pacientes.get(position);
        datosPacienteTv.setText(pacienteActual.toString());
        if (pacienteActual.isPresentaSintomasCovid() == true) {
            imagenCovidPacienteIv.setImageResource(R.drawable.warning);
        }
        return fila;
    }

    public PacienteArrayAdaptador(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.pacientes = objects;
    }
}
