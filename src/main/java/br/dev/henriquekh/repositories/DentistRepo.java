package br.dev.henriquekh.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import br.dev.henriquekh.entities.Dentist;
import br.dev.henriquekh.validator.CRM;

public class DentistRepo {
  private final HashMap<CRM, Dentist> dentistsMap;

  public DentistRepo() {
    this.dentistsMap = new HashMap<>();
  }

  public void createDentist(Dentist patient) {
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
