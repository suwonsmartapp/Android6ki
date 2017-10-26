package com.example.multichat;

/**
 * Created by junsuk on 2017. 10. 26..
 */

public class MessageEvent {
    public static final int CODE_MESSAGE = 0;
    public static final int CODE_ERROR = 1;
    public static final int CODE_GREETING = 2;

    int code;
    String message;

    public MessageEvent(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
