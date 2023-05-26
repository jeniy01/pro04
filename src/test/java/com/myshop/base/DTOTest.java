package com.myshop.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myshop.dto.SampleDTO;

public class DTOTest {
	private static final Logger logger = LoggerFactory.getLogger(DTOTest.class);

	@Test
	public void testDTO() {
		SampleDTO sample = new SampleDTO();
		sample.setField1("박지현");
		sample.setField2(100);
		sample.setField3(164.2);
		logger.info(sample.toString());
	}
}
