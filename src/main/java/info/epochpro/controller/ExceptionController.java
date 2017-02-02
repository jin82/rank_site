package info.epochpro.controller;

import info.epochpro.exceptions.ServiceException;
import info.epochpro.model.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static info.epochpro.model.Result.failure;

/**
 * Created by jin on 2017/2/2.
 */
@ControllerAdvice
public class ExceptionController {

    private final Log log = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletResponse response,ServiceException e) {
        log.error(e);
        return failure(e);
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindExceptionHandler(BindException e) {
        List<String> errorMsg = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errorMsg.add(fieldError.getDefaultMessage());
        }
        return failure(errorMsg);
    }
}
