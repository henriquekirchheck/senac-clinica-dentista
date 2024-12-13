package br.dev.henriquekh.entities;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class Appointment {
	private final UUID uuid;
	private final String patientId;
	private final String dentistId;
	private final LocalDateTime appointmentDateTime;
	private final String description;

	public Appointment(UUID uuid, String patientId,
			String dentistId, LocalDateTime appointmentDateTime,
			String description) {
		this.uuid = uuid;
		this.patientId = patientId;
		this.dentistId = dentistId;
		this.appointmentDateTime = appointmentDateTime;
		this.description = description;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getDentistId() {
		return dentistId;
	}

	public String getPatientId() {
		return patientId;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Object> asTable() {
		return Arrays.asList(getDentistId(), getPatientId(), getAppointmentDateTime(), getDescription());
	}
}
