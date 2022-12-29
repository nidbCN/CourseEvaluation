package cn.gaein.java.course_evaluation.exceptions.resolvers;

import cn.gaein.java.course_evaluation.responseHelper.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    public Response handleException(HttpServletRequest request, Exception e) {
        Response response = Response.internalServerError();
        response.setMessage("Internal server error");
        response.setData(e.getMessage());
        return response;
    }
}
