package cl.inacap.covid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.covid.R;
import cl.inacap.covid.dto.Paciente;

public class VerPacienteArrayAdaptador extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> paciente;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.ver_paciente_list, null, true);
        TextView verRutPacienteTv = fila.findViewById(R.id.verRutPacienteTv);
        TextView verNombrePacienteTv = fila.findViewById(R.id.verNombrePacienteTv);
        TextView verApellidoPacienteTv = fila.findViewById(R.id.verApellidoPacienteTv);
        TextView verFechaExamenPacienteTv = fila.findViewById(R.id.verFechaExamenPacienteTv);
        TextView verPresentaSintomasCovidPacienteTv = fila.findViewById(R.id.verPresentaSintomasCovidPacienteTv);
        TextView verTemperaturaPacienteTv = fila.findViewById(R.id.verTemperaturaPacienteTv);
        TextView verPresentaTosPacienteTv = fila.findViewById(R.id.verPresentaTosPacienteTv);
        TextView verPresionAterialPacienteTv = fila.findViewById(R.id.verPresionAterialPacienteTv);

        Paciente pacienteElegido = paciente.get(position);

        verRutPacienteTv.setText("Rut: " + pacienteElegido.getRut());
        verNombrePacienteTv.setText("Nombre: " + pacienteElegido.getNombre());
        verApellidoPacienteTv.setText("Apellido: " + pacienteElegido.getApellido());
        verFechaExamenPacienteTv.setText("Fecha: " + pacienteElegido.getFechaExamen());
        if (pacienteElegido.isPresentaSintomasCovid() == true) {
            verPresentaSintomasCovidPacienteTv.setText("Sintomas Covid: " + "true");
        }
        else {
            verPresentaSintomasCovidPacienteTv.setText("Sintomas Covid: " + "false");
        }
        verTemperaturaPacienteTv.setText("Temperatura: " + Double.toString(pacienteElegido.getTemperatura()));
        if (pacienteElegido.isPresentaTos()) {
            verPresentaTosPacienteTv.setText("Sintomas Tos: " + "true");
        }
        else {
            verPresentaTosPacienteTv.setText("Sintomas Tos: " + "false");
        }
        verPresionAterialPacienteTv.setText("Presión: " + Integer.toString(pacienteElegido.getPresionArterial()));
        return fila;
    }

    public VerPacienteArrayAdaptador(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.paciente = objects;
        this.activity = context;
    }
}
