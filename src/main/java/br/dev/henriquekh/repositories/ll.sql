CREATE DOMAIN cpf AS text CONSTRAINT cpf_format CHECK (VALUE ~ '^\d{3}\.\d{3}\.\d{3}\-\d{2}$');
CREATE DOMAIN crm AS text CONSTRAINT crm_format CHECK (VALUE ~ '^\d{10}$');
CREATE DOMAIN email AS text CONSTRAINT email_format CHECK (
  VALUE ~ '^[A-Z0-9._%-]+@[A-Z0-9._%-]+\.[A-Z]{2,4}$'
);
CREATE DOMAIN phone AS text CONSTRAINT phone_format CHECK (VALUE ~ '^\(\d{2}\) \d{5}-\d{4}$');
CREATE TABLE IF NOT EXISTS patient (
  cpf cpf primary key,
  name text NOT NULL,
  email email NOT NULL,
  phone phone NOT NULL,
  address text NOT NULL,
  birthDate DATE NOT NULL
);
CREATE TABLE IF NOT EXISTS dentist (
  crm crm primary key,
  name text NOT NULL,
  email email NOT NULL,
  phone phone NOT NULL
);
CREATE TABLE IF NOT EXISTS appointment (
  id UUID primary key default gen_random_uuid(),
  patientCpf cpf REFERENCES patient,
  dentistCrm crm REFERENCES dentist,
  appointmentDateTime TIMESTAMP NOT NULL,
  description text NOT NULL
);