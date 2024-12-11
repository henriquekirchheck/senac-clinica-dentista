package br.dev.henriquekh.entities;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
		return createWithUUID(UUID.randomUUID(), patientId, dentistId, appointmentDateTime, description);
	}

	public static Result<Appointment, Error> createWithUUID(UUID uuid, CPF patientId, CRM dentistId,
			LocalDateTime appointmentDateTime, String description) {
		if (description == null) {
			return Result.error(Error.NullPointer);
		}
		if (description.trim().isEmpty()) {
			return Result.error(Error.EmptyString);
		}
		if (appointmentDateTime.isBefore(LocalDateTime.now())) {
			return Result.error(Error.InvalidArgument);
		}
		return Result.success(new Appointment(uuid, patientId, dentistId, appointmentDateTime, description));
	}

	@JsonCreator
	private Appointment(@JsonProperty("uuid") UUID uuid, @JsonProperty("patientId") CPF patientId,
			@JsonProperty("dentistId") CRM dentistId, @JsonProperty("appointmentDateTime") LocalDateTime appointmentDateTime,
			@JsonProperty("description") String description) {
		this.uuid = uuid;
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

	public Collection<Object> getTable() {
		return Arrays.asList(getDentistId(), getPatientId(), getAppointmentDateTime(), getDescription());
	}
}
