package dsw.concessionaria.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorViewController implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		        
		ModelAndView model = new ModelAndView("erro");
		model.addObject("status", status.value());
		switch (status.value()) {
		case 403:
			model.addObject("error", "erro.403.titulo");
			model.addObject("message", "erro.403.mensagem");
			break;
		case 404:
			model.addObject("error", "erro.404.titulo");
			model.addObject("message", "erro.404.mensagem");
			break;
		case 500:
			model.addObject("error", "erro.500.titulo");
			model.addObject("message", "erro.500.mensagem");
		default:
			model.addObject("error", "erro.default.titulo");
			model.addObject("message", "erro.default.mensagem");
			break;
		}
		return model;
	}
}
