package br.dev.henriquekh.entities;

import com.github.sviperll.result4j.Result;

import br.dev.henriquekh.Error;
import br.dev.henriquekh.validator.CRM;
import br.dev.henriquekh.validator.Email;
import br.dev.henriquekh.validator.Phone;
import de.vandermeer.asciitable.AsciiTable;

public class Dentist {
	private final String name;
	private final CRM crm;
	private final Email email;
	private final Phone phone;

	public static Result<Dentist, Error> create(String name, CRM crm, Email email, Phone phone) {
		if (name == null) {
			return Result.error(Error.NullPointer);
		}
		if (name.trim().isEmpty()) {
			return Result.error(Error.EmptyString);
		}
		return Result.success(new Dentist(name, crm, email, phone));
	}

	private Dentist(String name, CRM crm, Email email, Phone phone) {
		this.name = name;
		this.crm = crm;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public CRM getCrm() {
		return crm;
	}

	public Email getEmail() {
		return email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void addToTable(AsciiTable tb) {
		tb.addRow(getCrm(), getName(), getEmail(), getPhone());
	}
}
