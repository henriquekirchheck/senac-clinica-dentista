package br.dev.henriquekh.utils;

import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import com.github.javafaker.Faker;

import br.dev.henriquekh.entities.Appointment;
import br.dev.henriquekh.entities.Dentist;
import br.dev.henriquekh.entities.Patient;
import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;

public class FakeData {
	public static void generateFakeData(AppointmentRepo appointmentRepo, DentistRepo dentistRepo,
			PatientRepo patientRepo) {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			patientRepo.createPatient(Patient
					.create(faker.name().name(), CPF.create(IdentificationGenerator.cpf(true)).discardError().get(),
							Email.create(faker.internet().emailAddress()).discardError().get(),
							Phone.create(IdentificationGenerator.phoneNumber()).discardError().get(),
							faker.address().fullAddress(),
							faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					.discardError().get());
		}
		for (int i = 0; i < 10; i++) {
			dentistRepo.createDentist(Dentist
					.create(faker.name().name(), CRM.create(IdentificationGenerator.crm()).discardError().get(),
							Email.create(faker.internet().emailAddress()).discardError().get(),
							Phone.create(IdentificationGenerator.phoneNumber()).discardError().get())
					.discardError().get());
		}
		List<Patient> patients = List.copyOf(patientRepo.getAllPatients());
		List<Dentist> dentists = List.copyOf(dentistRepo.getAllDentists());
		IntStream.range(0, Math.min(patients.size(), dentists.size()))
				.<Pair<Patient, Dentist>>mapToObj(index -> Pair.of(patients.get(index), dentists.get(index)))
				.forEach((pair) -> {
					appointmentRepo.createAppointment(Appointment.create(pair.getLeft().getCpf(), pair.getRight().getCrm(),
							faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
							faker.chuckNorris().fact()).discardError().get());
				});
	}
}
