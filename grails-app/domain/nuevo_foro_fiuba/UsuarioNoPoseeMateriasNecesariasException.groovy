package nuevo_foro_fiuba

class UsuarioNoPoseeMateriasNecesariasException extends Exception{

    static final String MENSAJE = "El usuario no posee las materias necesarias para comentar"

    static constraints = {
    }

    UsuarioNoPoseeMateriasNecesariasException(){}
}
