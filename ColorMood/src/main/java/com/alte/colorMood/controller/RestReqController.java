package com.alte.colorMood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alte.colorMood.api.ConstantsReq;
import com.alte.colorMood.entities.Sumbols;
import com.alte.colorMood.interfaces.IColorMood;

@RestController
@CrossOrigin
public class RestReqController {

	@Autowired
	IColorMood cm;

	@GetMapping(ConstantsReq.SHOW_COLOR)
	public Sumbols showColor(@RequestParam(value = "sumbols", defaultValue = "aaa") String sumbols) {
		return cm.handlerMood(sumbols);
	}
}
