package br.dev.henriquekh;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import br.dev.henriquekh.repositories.AppointmentRepo;
import br.dev.henriquekh.repositories.DentistRepo;
import br.dev.henriquekh.repositories.PatientRepo;
import br.dev.henriquekh.utils.FakeData;
import br.dev.henriquekh.utils.SaveRepositories;

public class Main {
  public static void main(String[] args) throws IOException {
    final Scanner scanner = new Scanner(System.in);

    final AppointmentRepo appointmentRepo = new AppointmentRepo();
    final DentistRepo dentistRepo = new DentistRepo();
    final PatientRepo patientRepo = new PatientRepo();

    final SaveRepositories saveRepositories = new SaveRepositories(Path.of("./Save.json"), appointmentRepo,
        dentistRepo,
        patientRepo);

    saveRepositories.load();

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

    saveRepositories.save();
    scanner.close();
  }
}
