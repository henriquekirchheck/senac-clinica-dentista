package br.dev.henriquekh.validator;

import java.util.regex.Pattern;

import com.github.sviperll.result4j.Result;
import br.dev.henriquekh.Error;

public class CPF {
  private final String value;
  private static final Pattern pattern = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}$\\-\\d{2}");

  public static Result<CPF, Error> create(String value) {
    if (value == null) {
      return Result.error(Error.NullPointer);
    }
    if (pattern.matcher(value).matches()) {
      return Result.error(Error.InvalidArgument);
    }
    return Result.success(new CPF(value));
  }

  private CPF(String value) {
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
    return getValue().equals(((CPF) obj).getValue());
  }
}
