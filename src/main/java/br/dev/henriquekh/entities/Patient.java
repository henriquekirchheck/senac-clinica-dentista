package br.dev.henriquekh.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

public class Patient {
  private final String name;
  private final String cpf;
  private final String email;
  private final String phone;
  private final String address;
  private final LocalDate birthDate;

  private Patient(String name, String cpf, String email,
      String phone, String address,
      LocalDate birthDate) {
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

  public String getCpf() {
    return cpf;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Collection<Object> asTable() {
    return Arrays.asList(getCpf(), getName(), getEmail(), getPhone(), getAddress(), getBirthDate());
  }
}
