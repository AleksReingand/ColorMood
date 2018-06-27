package com.alte.colorMood.handler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.alte.colorMood.api.dto.ColorDTO;
import com.alte.colorMood.entities.Sumbols;
import com.alte.colorMood.interfaces.IColorMood;

@Repository
public class ColorMoodHandler implements IColorMood {

	@Override
	public Sumbols handlerMood(String str) {
		String code = strToHexCode(str);
		String name = responceColorName(code);
		Sumbols instance = new Sumbols(code, name);
		return instance;
	}

	private String strToHexCode(String str) {
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			res = res + Integer.toHexString(str.charAt(i));
		}
		return res;
	}

	private String responceColorName(String code) {
		String url = "http://thecolorapi.com/";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(code, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ColorDTO> response = restTemplate.exchange(url + "/id?hex=" + code, HttpMethod.GET,
				requestEntity, ColorDTO.class);
		return response.getBody().getName().getValue();
	}
}
