package br.dev.henriquekh.utils;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;

public record SaveRepositories(Path path, AppointmentRepo appointmentRepo, DentistRepo dentistRepo, PatientRepo patientRepo) implements Closeable {
  public void save() throws IOException {
    
  }
  public void load() throws IOException {
  }

  @Override
  public void close() throws IOException {
    save();
  }
}
