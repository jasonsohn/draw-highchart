package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.HighchartService;

@Controller
@RequestMapping("/draw")
public class HighchartController {
	@Autowired
	private HighchartService highchartService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		
		highchartService.drawHighchart();
		return "test.html";
	}
}
