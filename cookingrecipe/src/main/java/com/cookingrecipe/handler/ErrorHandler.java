package com.cookingrecipe.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value = Exception.class)
	public String errorHandle(Exception e, Model model) {
        model.addAttribute("error", e);
		return "error";
	}
}
