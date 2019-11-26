package com.xm.web.xm.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorPage implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
		ModelAndView mv=new ModelAndView();
		switch (status) {
		case NOT_FOUND:
			mv.setViewName("redirect:/404.html");
			break;
		case INTERNAL_SERVER_ERROR:
			mv.setViewName("/500.html");
			break;

		default:
			break;
		}
		return mv;
	}
 
	
	
}
