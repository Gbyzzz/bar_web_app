package com.gbyzzz.bar_web_app.bar_backend.exception;

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
