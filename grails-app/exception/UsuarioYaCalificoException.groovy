package nuevo_foro_fiuba

class UsuarioYaCalificoException extends RuntimeException{

    final String MENSAJE = "El usuario ya calificó"

    static constraints = {
    }

    UsuarioYaCalificoException(){}
}
