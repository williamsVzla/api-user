package cl.bci.users.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public enum ErrorCatalogue {
	
	CORREO_INVALIDO(
            "EEE",
            "Correo invalido",
            HttpStatus.BAD_REQUEST),
	CORREO_OBLIGATORIO(
            "e",
            "Correo es obligatorio",
            HttpStatus.BAD_REQUEST),
	CORREO_REGISTRADO(
            "d",
            "El correo ya registrado",
            HttpStatus.BAD_REQUEST),
	CLAVE_NO_VALIDA(
            "e",
            "La contraseña debe ser Una Mayuscula, letras minúsculas, y dos numeros",
            HttpStatus.BAD_REQUEST),
	ERROR_ACTUALIZAR(
            "e",
            "El usuario que intenta actualizar no existe",
            HttpStatus.BAD_REQUEST),
	ERROR_ELIMINAR(
            "eeer",
            "El usuario que intenta eliminar no existe",
            HttpStatus.BAD_REQUEST);

    private static Map<String, ErrorCatalogue> map = new HashMap<>();
    String code;
    String message;
    private HttpStatus httpStatus;


    ErrorCatalogue(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    static {
        for (ErrorCatalogue cod : ErrorCatalogue.values()) {
            map.put(cod.code, cod);
        }
    }

    public static ErrorCatalogue getCode(String cod) {
        return map.get(cod);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
