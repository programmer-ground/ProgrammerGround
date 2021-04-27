package com.pg.chat.global.common;

public class ApiResponse<T> {
	private T data;
	private String message = "complete";

	public ApiResponse(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

    @Override
    public String toString() {
        return "ApiResponse{" +
            "data=" + data +
            ", message='" + message + '\'' +
            '}';
    }
}
