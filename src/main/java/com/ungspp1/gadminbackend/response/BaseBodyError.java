package com.ungspp1.gadminbackend.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseBodyError {
    String errorCode;
    @Singular List<String> messages;
    @Singular List<BaseBodyDataError> dataErrors;
    @Singular Map<String, Object> attributes;
    
    // we need attributes to be a not-null mutable Map in order for 'exception-handler' extension to inject error attributes
    public Map<String, Object> getAttributes() {
    	if (this.attributes == null) {
    		this.attributes = new HashMap<String, Object>();
    	} else if (!(this.attributes instanceof HashMap<?,?>)) {
    		this.attributes = new HashMap<>(this.attributes);
    	}
    	return this.attributes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BaseBodyDataError {
    	String code;
    	String name;
    	String value;
    	String message;
    }
}
