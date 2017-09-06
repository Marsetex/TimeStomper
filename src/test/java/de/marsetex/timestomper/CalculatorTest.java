package de.marsetex.timestomper;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

import de.marsetex.timestomper.bo.WorkingHoursEntry;

public class CalculatorTest {

	@Test
	public void testCalculateRemaingWorkingHours() throws Exception {
		Calculator calc = new Calculator();
		List<WorkingHoursEntry> list = new ArrayList<>();
		list.add(new WorkingHoursEntry("Montag", "04.09.2017", "8.00"));
		double result = calc.calculateRemaingWorkingHours(list);

		assertThat(result, Is.is(32.0));
	}

}
