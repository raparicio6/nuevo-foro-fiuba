package nuevo_foro_fiuba

class UsuarioYaVotoException extends Exception {

    final String MENSAJE = "El usuario ya votó en esta encuesta"

    static constraints = {
    }

    UsuarioYaVotoException(){}
}
