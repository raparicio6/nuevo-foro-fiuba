package nuevo_foro_fiuba

class PublicacionEliminadaException extends RuntimeException{

    final String MENSAJE = "La publicación está eliminada"

    static constraints = {
    }

    PublicacionEliminadaException(){}
}
