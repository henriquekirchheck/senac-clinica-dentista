package br.dev.henriquekh.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import br.dev.henriquekh.entities.Appointment;

public class AppointmentRepo {
	private final Connection conn;

	public AppointmentRepo(Connection conn) {
		this.conn = conn;
	}

	public Optional<UUID> createAppointment(String patientId,
			String dentistId, LocalDateTime appointmentDateTime,
			String description) throws SQLException {
		PreparedStatement st = conn.prepareStatement(
				"INSERT INTO appointment (patientCpf, dentistCrm, appointmentDateTime, description) VALUES (?, ?, ?, ?) RETURNING id");
		st.setString(1, patientId);
		st.setString(2, dentistId);
		st.setObject(3, appointmentDateTime);
		st.setString(4, description);

		ResultSet rs = st.executeQuery();
		if (rs.next())
			return Optional.empty();
		return Optional.of(UUID.fromString(rs.getString(1)));
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
		return appointmentMap.values().stream()
				.filter((appointment) -> appointment.getAppointmentDateTime().toLocalDate().isEqual(date))
				.collect(Collectors.toList());
	}

	public Collection<Appointment> getAllAppointmentsByDentist(CRM crm) {
		return appointmentMap.values().stream().filter((appointment) -> appointment.getDentistId().equals(crm))
				.collect(Collectors.toList());
	}

	public Collection<Appointment> getAllAppointmentsNextDays(int days) {
		return appointmentMap.values().stream()
				.filter((appointment) -> ChronoUnit.DAYS.between(Instant.now(),
						appointment.getAppointmentDateTime().atZone(ZoneId.of("America/Sao_Paulo"))) < days)
				.collect(Collectors.toList());
	}

	public HashMap<CRM, Integer> getNumberOfAppointments() {
		HashMap<CRM, Integer> map = new HashMap<>();
		appointmentMap.values().stream().forEach(
				(appointment) -> map.merge(appointment.getDentistId(), 1, Integer::sum));
		return map;
	}
}
