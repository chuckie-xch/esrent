package com.bestcode.esrent.base;

import lombok.Data;
import lombok.Getter;

/**
 * @author xch
 * @create 2018-07-16 20:09
 **/
@Data
public class ApiResponse {

    private Integer code;
    private String message;
    private Object data;
    private Boolean more;

    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandardMessage();
    }

    public static ApiResponse ofMessage(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(Status.SUCCESS.getCode(), Status.SUCCESS.getStandardMessage(), data);
    }

    public static ApiResponse ofStatus(Status status) {
        return new ApiResponse(status.getCode(), status.getStandardMessage(), null);
    }

    @Getter
    public enum Status {
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not Valid Param"),
        NOT_SUPPORT_OPERATION(40006, "Operation Not Supported "),
        NOT_LOGIN(50000, "Not Login"),
        NOT_FOUND(404, "Not Found"),
        ;

        private Integer code;
        private String standardMessage;

        Status(Integer code, String standardMessage) {
            this.code = code;
            this.standardMessage = standardMessage;
        }
    }
}
