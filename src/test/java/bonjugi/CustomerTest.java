package bonjugi;

import bonjugi.model.Customer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

	@Test
	public void 고객이름_동치성() {
		Customer customer = Customer.of("bonjugi");
		Customer equalsName = Customer.of("bonjugi");
		assertThat(customer).isEqualTo(equalsName);

		Customer otherName = Customer.of("다른이름");
		assertThat(customer).isNotEqualTo(otherName);
	}
}
