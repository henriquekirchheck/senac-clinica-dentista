package br.dev.henriquekh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;
import br.dev.henriquekh.utils.FakeData;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
  public static void main(String[] args) throws IOException, SQLException {
    final Dotenv dotenv = Dotenv.load();
    final Scanner scanner = new Scanner(System.in);

    Properties props = new Properties();
    props.setProperty("user", dotenv.get("POSTGRES_USER"));
    props.setProperty("password", dotenv.get("POSTGRES_PASSWORD"));
    final Connection conn = DriverManager.getConnection(String.format("jdbc:postgresql:%s", dotenv.get("POSTGRES_DB")), props);

    final AppointmentRepo appointmentRepo = new AppointmentRepo(conn);
    final DentistRepo dentistRepo = new DentistRepo(conn);
    final PatientRepo patientRepo = new PatientRepo(conn);

    System.err.println("=== Sistema da Clinica Odontologica ===");
    mainLoop: while (true) {
      System.err.println("""
          1. Gerenciar Pacientes;
          2. Gerenciar Dentistas;
          3. Gerenciar Consultas;
          4. Criar dados falsos;
          0. Sair do programa;
          """);
      System.err.print("Selecione uma das opcoes acima: ");

      int selection = scanner.nextInt();
      scanner.nextLine();

      switch (selection) {
        case 1 -> {
          Manager.patientHandler(scanner, patientRepo);
        }
        case 2 -> {
          Manager.dentistHandler(scanner, dentistRepo);
        }
        case 3 -> {
          Manager.appointmentHandler(scanner, appointmentRepo);
        }
        case 4 -> {
          FakeData.generateFakeData(appointmentRepo, dentistRepo, patientRepo);
        }
        case 0 -> {
          break mainLoop;
        }
        default -> System.err.println("Opcao Invalida");
      }
    }

    scanner.close();
  }
}
