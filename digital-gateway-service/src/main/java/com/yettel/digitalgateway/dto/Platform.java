package com.yettel.digitalgateway.dto;

public enum Platform {
    MOBILE,
    CONSOLE,
    WEB;

    public static Platform from(String value) {
        try {
            return Platform.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return WEB;
        }
    }
}