package com.stone.commons.exception;

import com.google.common.base.Throwables;
import com.stone.commons.core.LocaleMessage;
import com.stone.commons.jaxrs.GlobalErrorCode;
import com.stone.commons.jaxrs.RespondedBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author rose
 * @date 2022/11/7 16:50
 */
@Slf4j
@RestControllerAdvice
public class AbstractRespondedExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocaleMessage localeMessage;

    protected AbstractRespondedExceptionHandler(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex) {
        this.errorLog(ex);
        return ResponseEntity.ok().body(RespondedBody.of(GlobalErrorCode.SC_FORBIDDEN, ex.getMessage()));
    }

    /**
     * 已定义API响应异常
     *
     * @param ex 自定义异常
     * @return ResponseEntity
     */
    @ExceptionHandler({RespondedException.class})
    @ResponseBody
    public ResponseEntity<Object> handleRespondedException(final RespondedException ex) {
        this.errorLog(ex);
        String errorMsg = ex.getMessage();
        String errorCode = StringUtils.substringBetween(ex.getMessage(), "{", "}");
        if (StringUtils.isNotBlank(errorCode)) {
            errorMsg = localeMessage.getMessage(errorCode);
        }
        return ResponseEntity.ok().body(RespondedBody.of(ex.getCode(), errorMsg));
    }

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntity<Object> handleRequestParameterException(final UnsatisfiedServletRequestParameterException ex) {
        this.errorLog(ex);
        String errorMsg = String.format("%s not null", Arrays.toString(ex.getParamConditions()));
        return ResponseEntity.ok().body(RespondedBody.of(GlobalErrorCode.API_ARGUMENT_INVALID, errorMsg));
    }

    /**
     * Controller 参数校验不通过
     *
     * @param ex      异常
     * @param headers Header
     * @param request 请求
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn(ex.getMessage());
        return ResponseEntity.ok().body(onMethodArgumentNotValidException(ex));
    }

    /**
     * 约束不通过
     *
     * @param ex 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.ok().body(onConstraintViolationException(ex));
    }

    /**
     * 事务异常
     *
     * @param ex      异常
     * @param request 请求
     * @return ResponseEntity
     */
    @ExceptionHandler({TransactionSystemException.class})
    @ResponseBody
    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException ex, WebRequest request) {
        Throwable cause = Throwables.getRootCause(ex);
        this.errorLog(ex);
        if (cause instanceof ConstraintViolationException) {
            return ResponseEntity.ok(onConstraintViolationException((ConstraintViolationException) cause));
        }
        return handleExceptionInternal(ex, request);
    }

    /**
     * 并发冲突异常
     *
     * @param ex      异常
     * @param request Web request
     * @return ResponseEntity
     */
    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class, DataIntegrityViolationException.class})
    @ResponseBody
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, WebRequest request) {
        this.errorLog(ex);
        return handleExceptionInternal(ex, request);
    }

    /**
     * 运行异常
     *
     * @param ex      异常
     * @param request Web request
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, WebRequest request) {
        this.errorLog(ex);
        return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * 内部异常处理
     *
     * @param ex 异常
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.errorLog(ex);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        int errorCode = status.value();
        return ResponseEntity.internalServerError().body(RespondedBody.of(errorCode, ex.getMessage()));
    }

    private RespondedBody onConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            errorMessage.append(constraintViolation.getMessage());
        }
        return RespondedBody.of(GlobalErrorCode.API_ARGUMENT_INVALID, errorMessage.toString());
    }

    private RespondedBody onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                errorMessage.append(((FieldError) error).getField());
            }
            errorMessage.append(error.getDefaultMessage()).append(StringUtils.SPACE);
        }
        return RespondedBody.of(GlobalErrorCode.API_VIOLATION_ERROR, errorMessage.toString());
    }

    private void errorLog(Exception exStack) {
        log.error(Throwables.getStackTraceAsString(exStack));
    }
}