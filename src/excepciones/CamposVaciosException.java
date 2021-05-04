package excepciones;

public class CamposVaciosException extends Exception{

	public String getMessage() {
		
		return "EL CAMPO NO PUEDE ESTAR VACIO";
	}

}
