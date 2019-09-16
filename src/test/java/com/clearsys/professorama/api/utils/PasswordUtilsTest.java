package com.clearsys.professorama.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {

	private static final String SENHA = "Portugues";
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNula() throws Exception {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}

	@Test
	public void testGeraHashSenha() throws Exception {
		String hash = PasswordUtils.gerarBCrypt(SENHA);
		assertTrue(passwordEncoder.matches(SENHA, hash));
	}

}
