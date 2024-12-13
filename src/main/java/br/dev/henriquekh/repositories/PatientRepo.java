package br.dev.henriquekh.repositories;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import br.dev.henriquekh.entities.Patient;

public class PatientRepo {
  private final Connection conn;

  public PatientRepo(Connection conn) {
    this.conn = conn;
  }

  public void createPatient(String name, String cpf, String email,
      String phone, String address,
      LocalDate birthDate) {
    patientsMap.put(patient.getCpf(), patient);
  }

  public Optional<Patient> removePatient(CPF cpf) {
    return Optional.ofNullable(patientsMap.remove(cpf));
  }

  public Optional<Patient> getPatient(CPF cpf) {
    return Optional.ofNullable(patientsMap.get(cpf));
  }

  public Collection<Patient> getAllPatients() {
    return patientsMap.values();
  }

  public void loadPatients(Collection<Patient> patients) {
    for (Patient patient : patients) {
      createPatient(patient);
    }
  }
}
