package com.spcodage.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardResponse<T> {

    private int statusCode;
    private String message;
    private String status;
    private T data;


}
