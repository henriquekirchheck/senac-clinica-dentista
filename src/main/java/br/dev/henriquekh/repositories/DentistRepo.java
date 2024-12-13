package br.dev.henriquekh.repositories;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import br.dev.henriquekh.entities.Dentist;

public class DentistRepo {
  private final Connection conn;

  public DentistRepo(Connection conn) {
    this.conn = conn;
  }

  public void createDentist(String name, String crm, String email,
  String phone) {
    dentistsMap.put(patient.getCrm(), patient);
  }

  public Optional<Dentist> removeDentist(CRM crm) {
    return Optional.ofNullable(dentistsMap.remove(crm));
  }

  public Optional<Dentist> getDentist(CRM crm) {
    return Optional.ofNullable(dentistsMap.get(crm));
  }

  public Collection<Dentist> getAllDentists() {
    return dentistsMap.values();
  }

  public void loadDentists(Collection<Dentist> dentists) {
    for (Dentist dentist : dentists) {
      createDentist(dentist);
    }
  }
}
