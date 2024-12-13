package br.dev.henriquekh.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import br.dev.henriquekh.Error;
import de.vandermeer.asciitable.AsciiTable;

import com.github.sviperll.result4j.Result;

public class ManagerUtils {
  private ManagerUtils() {
  }

  public static Optional<CPF> getCPF(Scanner scanner) {
    System.err.print("Digite o CPF: ");
    Result<CPF, Error> cpfResult = CPF.create(scanner.nextLine());
    return errorHandler(cpfResult);
  }

  public static Optional<CRM> getCRM(Scanner scanner) {
    System.err.print("Digite o CRM: ");
    Result<CRM, Error> crmResult = CRM.create(scanner.nextLine());
    return errorHandler(crmResult);
  }

  public static Optional<Email> getEmail(Scanner scanner) {
    System.err.print("Digite o Email: ");
    Result<Email, Error> emailResult = Email.create(scanner.nextLine());
    return errorHandler(emailResult);
  }

  public static Optional<Phone> getPhone(Scanner scanner) {
    System.err.print("Digite o Telefone: ");
    Result<Phone, Error> phoneResult = Phone.create(scanner.nextLine());
    return errorHandler(phoneResult);
  }

  public static <T> Optional<T> errorHandler(Result<T, Error> result) {
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

  public static <T> void printTable(Collection<?> headers, Collection<T> values, Function<T, Collection<?>> supplier) {
    AsciiTable tb = new AsciiTable();
    tb.addRule();
    tb.addRow(headers);
    tb.addRule();
    values.stream().map(supplier).forEach((col) -> {
      tb.addRow(col);
      tb.addRule();
    });
    System.out.println(tb.render());
  }
}
