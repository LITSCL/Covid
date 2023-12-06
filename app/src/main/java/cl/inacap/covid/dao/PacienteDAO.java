package cl.inacap.covid.dao;

import java.util.List;

import cl.inacap.covid.dto.Paciente;

public interface PacienteDAO {
    List<Paciente> getAll();
    Paciente save(Paciente p);
}
