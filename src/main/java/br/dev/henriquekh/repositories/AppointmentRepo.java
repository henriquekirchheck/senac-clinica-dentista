package br.dev.henriquekh.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import br.dev.henriquekh.entities.Appointment;

public class AppointmentRepo {
  private final HashMap<UUID, Appointment> dentistsMap;

  public AppointmentRepo() {
    this.dentistsMap = new HashMap<>();
  }

  public void createAppointment(Appointment patient) {
    dentistsMap.put(patient.getUuid(), patient);
  }

  public Optional<Appointment> removeAppointment(UUID uuid) {
    return Optional.ofNullable(dentistsMap.remove(uuid));
  }

  public Optional<Appointment> getAppointment(UUID uuid) {
    return Optional.ofNullable(dentistsMap.get(uuid));
  }

  public Collection<Appointment> getAllAppointments() {
    return dentistsMap.values();
  }
}
