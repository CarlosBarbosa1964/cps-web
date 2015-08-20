package br.com.ibtechnology.cpsweb.support.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Named(value = "myUtil")
@SessionScoped
public class MyUtil extends BaseBeans {
	
	private static final long serialVersionUID = 1195002574775522306L;
	
	private String basePathLogin;

	public MyUtil() {
		basePathLogin = "/cps-web/";
	}
	
	public String CriptoPass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		String pass = hexString.toString();
		return pass;
	}

	public String getBasePathLogin() {
		return basePathLogin;
	}
	
}
