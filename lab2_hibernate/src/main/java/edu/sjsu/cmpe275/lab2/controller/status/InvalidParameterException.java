package edu.sjsu.cmpe275.lab2.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by xiaoxiaoli on 11/2/15.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException {
}
