package com.evanpatchouli.demo.util;

public enum HttpStatus {
    OK(200),
    ResetContent(205),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404);

    public final int code;
    HttpStatus(int code) {
        this.code = code;
    }
}
