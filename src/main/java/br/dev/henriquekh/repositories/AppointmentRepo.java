package br.dev.henriquekh.repositories;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import br.dev.henriquekh.entities.Appointment;

public class AppointmentRepo {
	private final HashMap<UUID, Appointment> appointmentMap;

	public AppointmentRepo() {
		this.appointmentMap = new HashMap<>();
	}

	public void createAppointment(Appointment patient) {
		appointmentMap.put(patient.getUuid(), patient);
	}

	public Optional<Appointment> removeAppointment(UUID uuid) {
		return Optional.ofNullable(appointmentMap.remove(uuid));
	}

	public Optional<Appointment> getAppointment(UUID uuid) {
		return Optional.ofNullable(appointmentMap.get(uuid));
	}

	public Collection<Appointment> getAllAppointments() {
		return appointmentMap.values();
	}

	public void loadAppointments(Collection<Appointment> appointments) {
		for (Appointment appointment : appointments) {
			createAppointment(appointment);
		}
	}

	public Collection<Appointment> getAllAppointmentsOnDate(LocalDate date) {
		return appointmentMap.values().stream().filter((appointment) -> {
			return appointment.getAppointmentDateTime().toLocalDate().isEqual(date);
		}).collect(Collectors.toList());
	}
}
