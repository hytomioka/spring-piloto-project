package br.com.tomioka.projetospring.config.validacao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	MessageSource messageSource;
	
	/*
	 * ResponseStatus determina o Status de resposta de requisição, o padrão do 
	 * RestControllerAdvice é 200.
	 *
	 * "MethodArgumentNotValidException" é a exceção lançada pelo Spring quando não tratada,
	 * para este caso.
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> erroDto = new ArrayList<>();
		
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {
			/*
			 * LocaleContextHolder lê o "Accepted-Language" na requição do formulario e 
			 * retorna a mensagem no idioma solicitado.
			 */
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			erroDto.add(erro);
		});
		return erroDto;
	}
	
}
