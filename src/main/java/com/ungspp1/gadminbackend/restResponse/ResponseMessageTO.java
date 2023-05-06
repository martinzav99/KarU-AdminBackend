package com.ungspp1.gadminbackend.restResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessageTO implements Serializable {
	private static final long serialVersionUID = 3822288368719948317L;
	
	private String code;
	private String name;
	private String message;
	private String value;

	@Override
	public String toString() {
		return "\"code\":"  	+ "\"" + String.valueOf(code)    + "\""
			+  ",\"name\":"  	+ "\"" + String.valueOf(name)    + "\""
			+  ",\"message\":" 	+ "\"" + String.valueOf(message) + "\""
			+  ",\"value\":"	+ "\"" + String.valueOf(value)   + "\"";
	}

}
