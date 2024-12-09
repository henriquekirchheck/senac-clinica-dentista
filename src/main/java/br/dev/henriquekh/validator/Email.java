package br.dev.henriquekh.validator;

import org.apache.commons.validator.routines.EmailValidator;

import com.github.sviperll.result4j.Result;
import br.dev.henriquekh.Error;

public class Email {
  private final String value;

  public static Result<Email, Error> create(String value) {
    if (value == null) {
      return Result.error(Error.NullPointer);
    }
    if (!EmailValidator.getInstance().isValid(value)) {
      return Result.error(Error.InvalidArgument);
    }
    return Result.success(new Email(value));
  }

  private Email(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }

  @Override
  public int hashCode() {
    return getValue().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (this.getClass() != obj.getClass())
      return false;
    return getValue().equals(((Email) obj).getValue());
  }
}