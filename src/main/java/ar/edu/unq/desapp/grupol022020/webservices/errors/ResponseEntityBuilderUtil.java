package ar.edu.unq.desapp.grupol022020.webservices.errors;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilderUtil {
	public static ResponseEntity<Object> build(ApiError apiError) {
	      return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}