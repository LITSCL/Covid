package cl.inacap.covid.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.covid.dto.Paciente;
import cl.inacap.covid.helpers.PacienteSQLiteHelper;

public class PacienteDAOSQLite implements PacienteDAO {
    private PacienteSQLiteHelper pacientesHelper;

    public PacienteDAOSQLite(Context context){
        this.pacientesHelper = new PacienteSQLiteHelper(context, "dbcovid", null, 3);
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.pacientesHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            if (reader != null) {
                Cursor c = reader.rawQuery("SELECT id, rut, nombre, apellido, fecha_examen, area_trabajo, presenta_sintomas_covid, temperatura, presenta_tos, presion_arterial" + " FROM paciente", null);
                if (c.moveToFirst()) {
                    do {
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFechaExamen(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        if (c.getString(6).equalsIgnoreCase("true")) {
                            p.setPresentaSintomasCovid(true);
                        }
                        else {
                            p.setPresentaSintomasCovid(false);
                        }
                        p.setTemperatura(c.getDouble(7));
                        if (c.getString(8).equalsIgnoreCase("true")){
                            p.setPresentaTos(true);
                        }
                        else {
                            p.setPresentaTos(false);
                        }
                        p.setPresionArterial(c.getInt(9));
                        pacientes.add(p);
                    } while (c.moveToNext());
                }
                reader.close();
            }
        } catch (Exception ex){
            pacientes = null;
        }
        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
       SQLiteDatabase writer = this.pacientesHelper.getWritableDatabase();
       String sql = String.format("INSERT INTO paciente(rut, nombre, apellido, fecha_examen, area_trabajo, presenta_sintomas_covid, temperatura, presenta_tos, presion_arterial)" +
               " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%f', '%s', '%d')", p.getRut(), p.getNombre(), p.getApellido(), p.getFechaExamen(), p.getAreaTrabajo(), Boolean.toString(p.isPresentaSintomasCovid()), p.getTemperatura(), Boolean.toString(p.isPresentaTos()), p.getPresionArterial());
       writer.execSQL(sql);
       writer.close();
       return p;
    }
}
