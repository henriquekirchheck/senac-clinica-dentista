package br.dev.henriquekh.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.Error;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.CRM;

public class Appointment {
  private final UUID uuid;
  private final CPF patientId;
  private final CRM dentistId;
  private final LocalDateTime appointmentDateTime;
  private final String description;

  public static Result<Appointment, Error> create(CPF patientId, CRM dentistId, LocalDateTime appointmentDateTime,
      String description) {
    if (description == null) {
      return Result.error(Error.NullPointer);
    }
    if (description.trim().isEmpty()) {
      return Result.error(Error.EmptyString);
    }
    return Result.success(new Appointment(patientId, dentistId, appointmentDateTime, description));
  }

  private Appointment(CPF patientId, CRM dentistId, LocalDateTime appointmentDateTime, String description) {
    this.uuid = UUID.randomUUID();
    this.patientId = patientId;
    this.dentistId = dentistId;
    this.appointmentDateTime = appointmentDateTime;
    this.description = description;
  }

  public UUID getUuid() {
    return uuid;
  }

  public CRM getDentistId() {
    return dentistId;
  }

  public CPF getPatientId() {
    return patientId;
  }

  public LocalDateTime getAppointmentDateTime() {
    return appointmentDateTime;
  }

  public String getDescription() {
    return description;
  }
}
