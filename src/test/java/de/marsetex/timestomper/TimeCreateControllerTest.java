package de.marsetex.timestomper;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class TimeCreateControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() throws Exception {
		assertThat(true, is(true));
	}

	@Test
	public void test2() throws Exception {
		assertThat(true, is(false));
	}

}
