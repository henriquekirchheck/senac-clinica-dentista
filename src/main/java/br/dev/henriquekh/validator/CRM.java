package br.dev.henriquekh.validator;

import java.util.regex.Pattern;

import com.github.sviperll.result4j.Result;
import br.dev.henriquekh.Error;

public class CRM {
  private final String value;
  private static final Pattern pattern = Pattern.compile("^CRM \\d{10}$");

  public static Result<CRM, Error> create(String value) {
    if (value == null) {
      return Result.error(Error.NullPointer);
    }
    if (!pattern.matcher(value).matches()) {
      return Result.error(Error.InvalidArgument);
    }
    return Result.success(new CRM(value));
  }

  private CRM(String value) {
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
    return getValue().equals(((CRM) obj).getValue());
  }
}
