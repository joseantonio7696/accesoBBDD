package excepciones;

public class IsbnException extends Exception{

	public String getMessage() {
		
		return "EL ISBN ES INCORRECTO";
	}
}
