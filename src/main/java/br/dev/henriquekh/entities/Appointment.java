package br.dev.henriquekh.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.Error;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.CRM;
import de.vandermeer.asciitable.AsciiTable;

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
		if (appointmentDateTime.isAfter(LocalDateTime.now())) {
			return Result.error(Error.InvalidArgument);
		}
		return Result.success(new Appointment(uuid, patientId, dentistId, appointmentDateTime, description));
	}

	private Appointment(UUID uuid, CPF patientId, CRM dentistId, LocalDateTime appointmentDateTime,
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

	public void addToTable(AsciiTable tb) {
		tb.addRow(getDentistId(), getPatientId(), getAppointmentDateTime(), getDescription());
	}
}
