package com.gbyzzz.bar_spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anton Pinchuk
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String error;

}
