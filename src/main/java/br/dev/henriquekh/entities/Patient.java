package br.dev.henriquekh.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.Error;
import br.dev.henriquekh.validator.Phone;
import br.dev.henriquekh.validator.CPF;
import br.dev.henriquekh.validator.Email;

public class Patient {
  private final String name;
  private final CPF cpf;
  private final Email email;
  private final Phone phone;
  private final String address;
  private final LocalDate birthDate;

  public static Result<Patient, Error> create(String name, CPF cpf, Email email, Phone phone, String address,
      LocalDate birthDate) {
    if (name == null || address == null) {
      return Result.error(Error.NullPointer);
    }
    if (name.trim().isEmpty()) {
      return Result.error(Error.EmptyString);
    }
    if (address.trim().isEmpty()) {
      return Result.error(Error.EmptyString);
    }
    return Result.success(new Patient(name, cpf, email, phone, address, birthDate));
  }

  @JsonCreator
  private Patient(@JsonProperty("name") String name, @JsonProperty("cpf") CPF cpf, @JsonProperty("email") Email email,
      @JsonProperty("phone") Phone phone, @JsonProperty("address") String address,
      @JsonProperty("birthDate") LocalDate birthDate) {
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public CPF getCpf() {
    return cpf;
  }

  public Email getEmail() {
    return email;
  }

  public Phone getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Collection<Object> getTable() {
    return Arrays.asList(getCpf(), getName(), getEmail(), getPhone(), getAddress(), getBirthDate());
  }
}
