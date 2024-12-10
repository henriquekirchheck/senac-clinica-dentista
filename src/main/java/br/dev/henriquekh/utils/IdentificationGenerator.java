package br.dev.henriquekh.utils;

public final class IdentificationGenerator {
	private static int randomNumber(int n) {
		return (int) (Math.random() * n);
	}

	private static int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	public static String cpf(boolean formatted) {
		int n = 9;
		int n1 = randomNumber(n);
		int n2 = randomNumber(n);
		int n3 = randomNumber(n);
		int n4 = randomNumber(n);
		int n5 = randomNumber(n);
		int n6 = randomNumber(n);
		int n7 = randomNumber(n);
		int n8 = randomNumber(n);
		int n9 = randomNumber(n);
		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10)
			d1 = 0;

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - (mod(d2, 11));

		String retorno = null;

		if (d2 >= 10)
			d2 = 0;
		retorno = "";

		if (formatted)
			retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
		else
			retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

		return retorno;
	}

	public static String phoneNumber() {
		return String.format("(%d%d) %d%d%d%d%d-%d%d%d%d", randomNumber(9), randomNumber(9), randomNumber(9),
				randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9),
				randomNumber(9), randomNumber(9));
	}

	public static String crm() {
		return String.format("CRM %d%d%d%d%d%d%d%d%d%d", randomNumber(9), randomNumber(9), randomNumber(9),
				randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9), randomNumber(9),
				randomNumber(9));
	}
}