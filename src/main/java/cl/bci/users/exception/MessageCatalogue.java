package cl.bci.users.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public enum MessageCatalogue {
	
	CORREO_INVALIDO(
            "1",
            "Correo invalido",
            HttpStatus.BAD_REQUEST),
	CORREO_OBLIGATORIO(
            "2",
            "Correo es obligatorio",
            HttpStatus.BAD_REQUEST),
	CORREO_REGISTRADO(
            "3",
            "El correo ya registrado",
            HttpStatus.BAD_REQUEST),
	CLAVE_NO_VALIDA(
            "4",
            "La contraseña debe ser Una Mayuscula, letras minúsculas, y dos numeros",
            HttpStatus.BAD_REQUEST),
	ERROR_CONSULTA(
            "5",
            "El usuario que intenta actualizar no existe",
            HttpStatus.OK),
	ERROR_ACTUALIZAR(
            "6",
            "El usuario que intenta actualizar no existe",
            HttpStatus.OK),
	ERROR_ELIMINAR(
            "7",
            "El usuario que intenta eliminar no existe",
            HttpStatus.OK),
	ERROR_JSON(
            "8",
            "El request no es formato JSON",
            HttpStatus.BAD_REQUEST);

    private static Map<String, MessageCatalogue> map = new HashMap<>();
    String code;
    String message;
    private HttpStatus httpStatus;


    MessageCatalogue(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    static {
        for (MessageCatalogue cod : MessageCatalogue.values()) {
            map.put(cod.code, cod);
        }
    }

    public static MessageCatalogue getCode(String cod) {
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
