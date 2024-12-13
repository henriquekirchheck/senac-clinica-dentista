package br.dev.henriquekh.entities;

import java.util.Arrays;
import java.util.Collection;

public class Dentist {
	private final String name;
	private final String crm;
	private final String email;
	private final String phone;

	private Dentist(String name, String crm, String email,
			String phone) {
		this.name = name;
		this.crm = crm;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getCrm() {
		return crm;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Collection<Object> asTable() {
		return Arrays.asList(getCrm(), getName(), getEmail(), getPhone());
	}
}
