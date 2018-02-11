package nuevo_foro_fiuba

class PromedioInsuficienteException extends Exception{

    static final MENSAJE = "El promedio de calificaciones del usuario es insuficiente"

    static constraints = {
    }

    PromedioInsuficienteException(){}
}
