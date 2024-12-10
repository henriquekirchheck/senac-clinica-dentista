package br.dev.henriquekh;

import java.time.ZoneId;
import java.util.Collection;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import com.github.javafaker.Faker;

import br.dev.henriquekh.entities.Dentist;
import br.dev.henriquekh.entities.Patient;
import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;
import br.dev.henriquekh.utils.IdentificationGenerator;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.CRM;
import br.dev.henriquekh.validator.Email;
import br.dev.henriquekh.validator.Phone;

public class FakeData {
	public void generateFakeData(AppointmentRepo appointmentRepo, DentistRepo dentistRepo, PatientRepo patientRepo) {
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
		for (int i = 0; i < 10; i++) {
			Collection<Patient> patients = patientRepo.getAllPatients();
			Collection<Dentist> dentists = dentistRepo.getAllDentists();
			IntStream.range(0, Math.min(patients.size(), dentists.size()))
					.<Pair<Patient, Dentist>>mapToObj(index -> Pair.of(patients.get(index), dentists.get(index)));

		}
	}
}
