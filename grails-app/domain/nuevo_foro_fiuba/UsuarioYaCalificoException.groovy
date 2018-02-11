package nuevo_foro_fiuba

class UsuarioYaCalificoException extends Exception{

    static final MENSAJE = "El usuario ya calificó"

    static constraints = {
    }

    UsuarioYaCalificoException(){}
}
