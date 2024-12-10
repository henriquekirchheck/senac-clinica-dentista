package br.dev.henriquekh.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;

public record SaveRepositories(Path path, AppointmentRepo appointmentRepo, DentistRepo dentistRepo, PatientRepo patientRepo) implements Closeable {
  private static final ObjectMapper mapper = new ObjectMapper();
  public void save() throws IOException {
    final File file = path.toFile();
    mapper.writeValue(file, patientRepo.getAllPatients());
  }
  public void load() throws IOException {
    final File file = path.toFile();

  }

  @Override
  public void close() throws IOException {
    save();
  }
}
