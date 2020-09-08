package com.twu.app.exception;

public class InfoExceptionHandle {
    public void handle(RuntimeException e) { System.out.printf("[system] %s ...\n", e.getMessage()); }
}
