package br.dev.henriquekh.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import br.dev.henriquekh.entities.Patient;
import br.dev.henriquekh.validator.CPF;

public class PatientRepo {
  private final HashMap<CPF, Patient> patientsMap;

  public PatientRepo() {
    this.patientsMap = new HashMap<>();
  }

  public void createPatient(Patient patient) {
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
