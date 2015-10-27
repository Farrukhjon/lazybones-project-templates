package org.farrukh.examples.rest.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;

/**
 * Custom error controller.
 */
public class CustomErrorController implements ErrorController {

    /**
     * Error path.
     */
    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
