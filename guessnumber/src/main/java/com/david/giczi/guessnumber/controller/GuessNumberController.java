package com.david.giczi.guessnumber.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.david.giczi.guessnumber.service.GuessNumberService;

@Controller
public class GuessNumberController {

	@Autowired
	private GuessNumberService service;

	@RequestMapping(value = "/guessnumber")
	public String startGame(HttpSession session, Model model) {

		int theNumber = service.randNumber();
		session.setAttribute("task", theNumber);
		session.setAttribute("inputStore", "[]");
		
		return "gameboard";
	}

	@RequestMapping(value = "/guessnumber/send", method = RequestMethod.POST)
	public String playGame(@RequestParam("tipp") String tipp, Model model, HttpSession session) {

		if (service.validateInputData(tipp)) {

			int inputNumber = Integer.parseInt(tipp);
			int taskNumber = (int) session.getAttribute("task");
			
			
			if(service.isTheEndOfTheGame(inputNumber, taskNumber)) {
				
				model.addAttribute("message", "Kitaláltad a számot (" + taskNumber + "), ügyes vagy!");
				model.addAttribute("newgame", "true");
			}
			
			else {
				
				session.setAttribute("inputStore",
						service.collectTippNumbers((String) session.getAttribute("inputStore"), tipp));
				String resp = service.evaluate(inputNumber, taskNumber) + " "
						+ (String) session.getAttribute("inputStore");
				model.addAttribute("message", resp);
				model.addAttribute("newgame", "false");
			}
					
			
		} else {
			
			String resp = "Nem megfelelő bemeneti érték. " + (String) session.getAttribute("inputStore");
			model.addAttribute("message", resp);
			model.addAttribute("newgame", "false");
		}

		return "gameboard";
	}

}
