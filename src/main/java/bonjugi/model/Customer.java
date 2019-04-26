package bonjugi.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "name")
public class Customer {


	private final String name;

	/**
	 * constructor
	 */
	public Customer(String name) {
		this.name = name;
	}

	public static Customer of(String name) {
		return new Customer(name);
	}
}
