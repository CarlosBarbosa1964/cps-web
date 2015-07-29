package br.com.ibtechnology.cpsweb.support.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.util.BaseBeans;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "myUtil")
public class MyUtil extends BaseBeans {
	
	private static final long serialVersionUID = 1195002574775522306L;
	
	private static UserEntity userLogged;
	private String basePathLogin;

	public MyUtil() {
		this.userLogged = new UserEntity();
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

	public static UserEntity getUserLogged() {
		return userLogged;
	}

	public static void setUserLogged(UserEntity userLogged) {
		MyUtil.userLogged = userLogged;
	}
	
}
