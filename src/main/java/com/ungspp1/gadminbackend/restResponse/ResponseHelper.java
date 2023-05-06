package com.ungspp1.gadminbackend.restResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.restResponse.BaseBodyError.BaseBodyDataError;

import java.util.ArrayList;
import java.util.List;


@Component
public class ResponseHelper {


    public static ResponseEntity<BaseBodyResponse<?>> simpleResponse(Object result) {
        return messageResponse("200", "info", "OK", "OK", result);
    }

    public static ResponseEntity<BaseBodyResponse<?>> errorResponse(String errorCode, String errorMessage) {
        return messageResponse(errorCode, "error", errorMessage, "error", null);
    }

    public static ResponseEntity<BaseBodyResponse<?>> errorResponse(String errorCode, String errorMessage, Object result) {
        return messageResponse(errorCode, "error", errorMessage, "error", result);
    }

    public static ResponseEntity<BaseBodyResponse<?>> messageResponse(String statusCode, String messageType, String messageKey, String value, Object result, boolean log) {
        return messageResponse(statusCode, messageType, messageKey, value, result);
    }

    public static ResponseEntity<BaseBodyResponse<?>> messageResponse(String statusCode, String messageType, String messageKey, String value, Object result) {

        BaseBodyDataError baseBodyDataError = new BaseBodyDataError();
        baseBodyDataError.setCode(statusCode);
        baseBodyDataError.setName(messageType);
        baseBodyDataError.setMessage(messageKey);
        baseBodyDataError.setValue(value);

        List<BaseBodyDataError> errorList = new ArrayList<BaseBodyDataError>();
        errorList.add(baseBodyDataError);

        BaseBodyError baseBodyError = new BaseBodyError();
        baseBodyError.setDataErrors(errorList);

        BaseBodyResponse<Object> bodyResponse = new BaseBodyResponse<>();
        bodyResponse.setError(baseBodyError);

        if (result != null) {
            bodyResponse.setResult(result);
        } else {
            ResponseMessageTO message = new ResponseMessageTO();
            message.setCode(statusCode);
            message.setName(messageType);
            message.setMessage(messageKey);
            message.setValue(statusCode);

            bodyResponse.setResult(message);
        }


        return ResponseEntity.ok(bodyResponse);

    }
}