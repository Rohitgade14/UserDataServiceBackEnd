package com.spcodage.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardResponse<T> {

    private int statusCode;
    private String message;
    private String status;
    private T data;


}
