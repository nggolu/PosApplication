
package com.nagarro.pos.service;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.dao.impl.EmployeeCashDrawerRepositoryImplment;
import com.nagarro.pos.model.EmployeeCashDrawer;

/**
 * User is verification is done here
 * 
 * @author nishantgarg
 *
 */

@Service
public class VerificationService {

	private static String key = "Bar12345Bar12345";
	private static String initVector = "RandomInitVector";

	@Autowired
	private EmployeeCashDrawerRepositoryImplment employeeCashDrawerRepositoryImplment;
	Cipher cipher;

	/**
	 * Encryption is done for the encrypted string using AES
	 * 
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			System.out.println("encrypt");
			System.out.println(skeySpec);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			System.out.println("encrypted string: " + Base64.encodeBase64String(encrypted));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Decryption is done for the encrypted string using AES
	 * 
	 * @param encrypted
	 * @return
	 */
	public static String decrypt(String encrypted) {
		try {

			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			System.out.println(skeySpec);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public EmployeeCashDrawer verifyingEmployee(String token) {
		
		// because token is changed from token to "token"
		token = token.substring(1, token.length() - 1);
		String decrption = "";
		try {
			decrption = decrypt(token);
			Gson gson = new Gson();
			
			//convert the string into gson
			EmployeeCashDrawer employeeCashDrawers = gson.fromJson(decrption, EmployeeCashDrawer.class);
			if (employeeCashDrawers == null) {
				System.out.println("syso nul");
				return null;
			}
			
			//if employeeCashDrawer
			if (employeeCashDrawers.getId() != 0) {
				//checking that id is present or not
				EmployeeCashDrawer empCashDrawer = employeeCashDrawerRepositoryImplment
						.getCashDrawerById(employeeCashDrawers.getId());
				if (empCashDrawer == null)
					return null;
				employeeCashDrawers.setEmployees(empCashDrawer.getEmployees());
			}else return null;
			
				
			if (employeeCashDrawers.getEmployees() == null
					|| employeeCashDrawers.getEmployees().getUsername() == null) {
				return null;
			}
			
			//if employee last order place is  more than the Time  then token is expired
			if (((new Date()).getHours() - employeeCashDrawers.getEndTime().getHours()) > ConfigurationConstant.TIME) {
				System.out.println((new Date()).getHours() + " i " + employeeCashDrawers.getEndTime().getHours());
				return null;
			}
			return employeeCashDrawers;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
