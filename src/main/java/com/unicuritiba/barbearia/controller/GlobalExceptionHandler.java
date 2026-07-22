package com.unicuritiba.barbearia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Exception ex) {
		logger.error("Erro nao tratado ao processar a requisicao", ex);

		ModelAndView pagina = new ModelAndView("error");
		pagina.addObject("mensagem",
				"Ocorreu um erro ao processar sua solicitacao. Por favor, tente novamente mais tarde.");
		return pagina;
	}
}
