package cn.gaein.java.course_evaluation.exceptions.resolvers;

import javax.servlet.http.HttpServletRequest;

import cn.gaein.java.course_evaluation.responseHelper.Response;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MissingPathVariableExceptionResolver {

    @ExceptionHandler(MissingPathVariableException.class)
    public Response handleMissingPathVariableException(HttpServletRequest request,
                                                       MissingPathVariableException e) {
        Response response = Response.badRequest();
        response.setMessage("Required path variable is missing in this request.");
        response.setData(e.getMessage());
        return response;
    }
}
