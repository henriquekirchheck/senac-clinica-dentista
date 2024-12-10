package br.dev.henriquekh;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.entities.Appointment;
import br.dev.henriquekh.entities.Dentist;
import br.dev.henriquekh.entities.Patient;
import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.CRM;
import br.dev.henriquekh.validator.Email;
import br.dev.henriquekh.validator.Phone;
import de.vandermeer.asciitable.AsciiTable;

public class Manager {
	public static void patientHandler(Scanner scanner, PatientRepo patientRepo) {
		System.err.println("==> Gerencie Pacientes ");
		mainLoop: while (true) {
			System.err.println("""
					1. Listar Pacientes;
					2. Adicionar Paciente;
					3. Remover Paciente;
					4. Editar Paciente;
					0. Voltar;
					""");
			System.err.print("Selecione uma das opcoes acima: ");

			int selection = scanner.nextInt();
			scanner.nextLine();

			switch (selection) {
			case 1 -> {
				System.err.println("=> Lista de Pacientes");
				AsciiTable tb = new AsciiTable();
				tb.addRule();
				tb.addRow("CPF", "Nome", "Email", "Telefone", "Endereco", "Data de Nascimento");
				tb.addRule();
				patientRepo.getAllPatients().parallelStream().forEach((patient) -> {
					patient.addToTable(tb);
					tb.addRule();
				});
				System.out.println(tb.render());
			}
			case 2 -> {
				System.err.println("=> Adicione Paciente ");

				System.err.print("Digite o CPF: ");
				Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
				Optional<CPF> cpfOptional = errorHandler(cpfResult);
				if (cpfOptional.isEmpty()) {
					break;
				}
				CPF cpf = cpfOptional.get();

				System.err.print("Digite o nome: ");
				String name = scanner.nextLine();

				System.err.print("Digite o Email: ");
				Result<Email, Error> emailResult = Email.create(scanner.nextLine());
				Optional<Email> emailOptional = errorHandler(emailResult);
				if (emailOptional.isEmpty()) {
					break;
				}
				Email email = emailOptional.get();

				System.err.print("Digite o Telefone: ");
				Result<Phone, Error> phoneResult = Phone.create(scanner.nextLine());
				Optional<Phone> phoneOptional = errorHandler(phoneResult);
				if (phoneOptional.isEmpty()) {
					break;
				}
				Phone phone = phoneOptional.get();

				System.err.print("Digite o Endereco: ");
				String address = scanner.nextLine();

				System.err.print("Digite a Data de Nacimento (YYYY-MM-DD): ");
				LocalDate birthDate = LocalDate.parse(scanner.nextLine());

				Optional<Patient> patientOptional = errorHandler(
						Patient.create(name, cpf, email, phone, address, birthDate));
				if (phoneOptional.isEmpty()) {
					break;
				}
				Patient patient = patientOptional.get();

				patientRepo.createPatient(patient);
			}
			case 3 -> {
				System.err.println("=> Remover Paciente ");

				System.err.print("Digite o CPF: ");
				Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
				Optional<CPF> cpfOptional = errorHandler(cpfResult);
				if (cpfOptional.isEmpty()) {
					break;
				}
				CPF cpf = cpfOptional.get();

				if (patientRepo.removePatient(cpf).isEmpty()) {
					System.err.println("Paciente nao existe");
				}
				;
			}
			case 4 -> {
				System.err.println("=> Modificar Paciente ");

				System.err.print("Digite o CPF: ");
				Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
				Optional<CPF> cpfOptional = errorHandler(cpfResult);
				if (cpfOptional.isEmpty()) {
					break;
				}
				CPF cpf = cpfOptional.get();

				if (patientRepo.removePatient(cpf).isEmpty()) {
					System.err.println("Paciente nao existe");
					break;
				}
				;

				System.err.print("Digite o nome: ");
				String name = scanner.nextLine();

				System.err.print("Digite o Email: ");
				Result<Email, Error> emailResult = Email.create(scanner.nextLine());
				Optional<Email> emailOptional = errorHandler(emailResult);
				if (emailOptional.isEmpty()) {
					break;
				}
				Email email = emailOptional.get();

				System.err.print("Digite o Telefone: ");
				Result<Phone, Error> phoneResult = Phone.create(scanner.nextLine());
				Optional<Phone> phoneOptional = errorHandler(phoneResult);
				if (phoneOptional.isEmpty()) {
					break;
				}
				Phone phone = phoneOptional.get();

				System.err.print("Digite o Endereco: ");
				String address = scanner.nextLine();

				System.err.print("Digite a Data de Nacimento (YYYY-MM-DD): ");
				LocalDate birthDate = LocalDate.parse(scanner.nextLine());

				Optional<Patient> patientOptional = errorHandler(
						Patient.create(name, cpf, email, phone, address, birthDate));
				if (patientOptional.isEmpty()) {
					break;
				}
				Patient patient = patientOptional.get();

				patientRepo.createPatient(patient);
			}
			case 0 -> {
				break mainLoop;
			}
			default -> System.err.println("Opcao Invalida");
			}
		}
	}

	public static void dentistHandler(Scanner scanner, DentistRepo dentistRepo) {
		System.err.println("==> Gerencie Dentistas ");
		mainLoop: while (true) {
			System.err.println("""
					1. Listar Dentistas;
					2. Adicionar Dentista;
					3. Remover Dentista;
					4. Editar Dentista;
					0. Voltar;
					""");
			System.err.print("Selecione uma das opcoes acima: ");

			int selection = scanner.nextInt();
			scanner.nextLine();

			switch (selection) {
			case 1 -> {
				System.err.println("=> Lista de Dentistas");
				AsciiTable tb = new AsciiTable();
				tb.addRule();
				tb.addRow("CRM", "Nome", "Email", "Telefone");
				tb.addRule();
				dentistRepo.getAllDentists().parallelStream().forEach((dentist) -> {
					dentist.addToTable(tb);
					tb.addRule();
				});
				System.out.println(tb.render());
			}
			case 2 -> {
				System.err.println("=> Adicione Dentista ");

				System.err.print("Digite o CRM: ");
				Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
				Optional<CRM> crmOptional = errorHandler(crmResult);
				if (crmOptional.isEmpty()) {
					break;
				}
				CRM crm = crmOptional.get();

				System.err.print("Digite o nome: ");
				String name = scanner.nextLine();

				System.err.print("Digite o Email: ");
				Result<Email, Error> emailResult = Email.create(scanner.nextLine());
				Optional<Email> emailOptional = errorHandler(emailResult);
				if (emailOptional.isEmpty()) {
					break;
				}
				Email email = emailOptional.get();

				System.err.print("Digite o Telefone: ");
				Result<Phone, Error> phoneResult = Phone.create(scanner.nextLine());
				Optional<Phone> phoneOptional = errorHandler(phoneResult);
				if (phoneOptional.isEmpty()) {
					break;
				}
				Phone phone = phoneOptional.get();

				Optional<Dentist> dentistOptional = errorHandler(Dentist.create(name, crm, email, phone));
				if (dentistOptional.isEmpty()) {
					break;
				}
				Dentist dentist = dentistOptional.get();

				dentistRepo.createDentist(dentist);
			}
			case 3 -> {
				System.err.println("=> Remover Dentista ");

				System.err.print("Digite o CRM: ");
				Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
				Optional<CRM> crmOptional = errorHandler(crmResult);
				if (crmOptional.isEmpty()) {
					break;
				}
				CRM crm = crmOptional.get();

				if (dentistRepo.removeDentist(crm).isEmpty()) {
					System.err.println("Dentista nao existe");
				}
			}
			case 4 -> {
				System.err.println("=> Modificar Dentista ");

				System.err.print("Digite o CRM: ");
				Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
				Optional<CRM> crmOptional = errorHandler(crmResult);
				if (crmOptional.isEmpty()) {
					break;
				}
				CRM crm = crmOptional.get();

				if (dentistRepo.removeDentist(crm).isEmpty()) {
					System.err.println("Dentista nao existe");
				}

				System.err.print("Digite o nome: ");
				String name = scanner.nextLine();

				System.err.print("Digite o Email: ");
				Result<Email, Error> emailResult = Email.create(scanner.nextLine());
				Optional<Email> emailOptional = errorHandler(emailResult);
				if (emailOptional.isEmpty()) {
					break;
				}
				Email email = emailOptional.get();

				System.err.print("Digite o Telefone: ");
				Result<Phone, Error> phoneResult = Phone.create(scanner.nextLine());
				Optional<Phone> phoneOptional = errorHandler(phoneResult);
				if (phoneOptional.isEmpty()) {
					break;
				}
				Phone phone = phoneOptional.get();

				Optional<Dentist> dentistOptional = errorHandler(Dentist.create(name, crm, email, phone));
				if (dentistOptional.isEmpty()) {
					break;
				}
				Dentist dentist = dentistOptional.get();

				dentistRepo.createDentist(dentist);
			}
			case 0 -> {
				break mainLoop;
			}
			default -> System.err.println("Opcao Invalida");
			}
		}

	}

	public static void appointmentHandler(Scanner scanner, AppointmentRepo appointmentRepo) {
		System.err.println("==> Gerencie Consultas ");
		mainLoop: while (true) {
			System.err.println("""
					1. Listar Consultas;
					2. Adicionar Consulta;
					3. Remover Consulta;
					4. Editar Consulta;
					5. Listar Consultas Data Especifica;
					0. Voltar;
					""");
			System.err.print("Selecione uma das opcoes acima: ");

			int selection = scanner.nextInt();
			scanner.nextLine();

			switch (selection) {
			case 1 -> {
				System.err.println("=> Lista de Consultas");
				AsciiTable tb = new AsciiTable();
				tb.addRule();
				tb.addRow("Paciente", "Dentista", "Hora Marcada", "Descrição");
				tb.addRule();
				appointmentRepo.getAllAppointments().parallelStream().forEach((appointment) -> {
					appointment.addToTable(tb);
					tb.addRule();
				});
				System.out.println(tb.render());
			}
			case 2 -> {
				System.err.println("=> Adicione Consulta ");

				System.err.print("Digite o CPF: ");
				Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
				Optional<CPF> cpfOptional = errorHandler(cpfResult);
				if (cpfOptional.isEmpty()) {
					break;
				}
				CPF cpf = cpfOptional.get();

				System.err.print("Digite o CRM: ");
				Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
				Optional<CRM> crmOptional = errorHandler(crmResult);
				if (crmOptional.isEmpty()) {
					break;
				}
				CRM crm = crmOptional.get();

				System.err.print("Digite a Data e Hora da Consulta (YYYY-MM-DDThh:mm): ");
				LocalDateTime appointmentDateTime = LocalDateTime.parse(scanner.nextLine());

				System.err.print("Digite a Descrição: ");
				String description = scanner.nextLine();

				Optional<Appointment> appointmentOptional = errorHandler(
						Appointment.create(cpf, crm, appointmentDateTime, description));
				if (appointmentOptional.isEmpty()) {
					break;
				}
				Appointment appointment = appointmentOptional.get();

				appointmentRepo.createAppointment(appointment);
			}
			case 3 -> {
				System.err.println("=> Remover Dentista ");

				System.err.print("Digite o ID: ");
				try {
					UUID uuid = UUID.fromString(scanner.nextLine());
					if (appointmentRepo.removeAppointment(uuid).isEmpty()) {
						System.err.println("Consulta nao existe");
					}
				} catch (IllegalArgumentException e) {
					System.err.println("UUID invalido");
				}
			}
			case 4 -> {
				System.err.println("=> Modificar Consulta ");

				System.err.print("Digite o ID: ");
				try {
					UUID uuid = UUID.fromString(scanner.nextLine());
					if (appointmentRepo.removeAppointment(uuid).isEmpty()) {
						System.err.println("Consulta nao existe");
					}

					System.err.print("Digite o CPF: ");
					Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
					Optional<CPF> cpfOptional = errorHandler(cpfResult);
					if (cpfOptional.isEmpty()) {
						break;
					}
					CPF cpf = cpfOptional.get();

					System.err.print("Digite o CRM: ");
					Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
					Optional<CRM> crmOptional = errorHandler(crmResult);
					if (crmOptional.isEmpty()) {
						break;
					}
					CRM crm = crmOptional.get();

					System.err.print("Digite a Data e Hora da Consulta (YYYY-MM-DDThh:mm): ");
					LocalDateTime appointmentDateTime = LocalDateTime.parse(scanner.nextLine());

					System.err.print("Digite a Descrição: ");
					String description = scanner.nextLine();

					Optional<Appointment> appointmentOptional = errorHandler(
							Appointment.createWithUUID(uuid, cpf, crm, appointmentDateTime, description));
					if (appointmentOptional.isEmpty()) {
						break;
					}
					Appointment appointment = appointmentOptional.get();

					appointmentRepo.createAppointment(appointment);
				} catch (IllegalArgumentException e) {
					System.err.println("UUID invalido");
				}
			}
			case 5 -> {
				System.err.println("=> Lista de Consultas");

				System.err.print("Digite a Data das Consultas (YYYY-MM-DD): ");
				LocalDate appointmentsDate = LocalDate.parse(scanner.nextLine());

				AsciiTable tb = new AsciiTable();
				tb.addRule();
				tb.addRow("Paciente", "Dentista", "Hora Marcada", "Descrição");
				tb.addRule();
				appointmentRepo.getAllAppointmentsOnDate(appointmentsDate).parallelStream().forEach((appointment) -> {
					appointment.addToTable(tb);
					tb.addRule();
				});
				System.out.println(tb.render());
			}
			case 0 -> {
				break mainLoop;
			}
			default -> System.err.println("Opcao Invalida");
			}
		}

	}

	private static <T> Optional<T> errorHandler(Result<T, Error> result) {
		switch (result) {
		case Result.Success<T, Error>(T val) -> {
			return Optional.of(val);
		}
		case Result.Error<T, Error>(Error error) -> {
			switch (error) {
			case InvalidArgument -> System.err.println("Argumento invalido, tente novamente");
			case EmptyString -> System.err.println("Argumento invalido, tente novamente");
			case NullPointer -> throw new NullPointerException();
			}
		}
		}
		return Optional.empty();
	}
}
