package br.dev.henriquekh.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import br.dev.henriquekh.entities.Appointment;
import br.dev.henriquekh.entities.Dentist;
import br.dev.henriquekh.entities.Patient;
import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;

public record SaveRepositories(Path path, AppointmentRepo appointmentRepo, DentistRepo dentistRepo,
    PatientRepo patientRepo) {

  private static final ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule())
      .registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

  private record Repositories(Collection<Appointment> appointments, Collection<Dentist> dentists,
      Collection<Patient> patients) {
  }

  public void save() throws IOException {
    final File file = path.toFile();
    mapper.writeValue(file, new Repositories(appointmentRepo.getAllAppointments(), dentistRepo.getAllDentists(),
        patientRepo.getAllPatients()));
  }

  public void load() throws IOException {
    final File file = path.toFile();
    Repositories repos = mapper.readValue(file, Repositories.class);
    appointmentRepo.loadAppointments(repos.appointments);
    dentistRepo.loadDentists(repos.dentists);
    patientRepo.loadPatients(repos.patients);
  }
}
