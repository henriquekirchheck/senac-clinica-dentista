package br.dev.henriquekh;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.Email;
import br.dev.henriquekh.validator.Phone;
import br.dev.henriquekh.entities.Patient;
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
          tb.addRow(
              "CPF",
              "Nome",
              "Email",
              "Telefone",
              "Endereco",
              "Data de Nascimento");
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

          Optional<Patient> patientOptional = errorHandler(Patient.create(name, cpf, email, phone, address, birthDate));
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

          Optional<Patient> patientOptional = errorHandler(Patient.create(name, cpf, email, phone, address, birthDate));
          if (phoneOptional.isEmpty()) {
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

  }

  public static void appointmentHandler(Scanner scanner, AppointmentRepo appointmentRepo) {

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
