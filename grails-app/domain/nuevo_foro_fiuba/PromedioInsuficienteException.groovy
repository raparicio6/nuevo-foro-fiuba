package nuevo_foro_fiuba

class PromedioInsuficienteException extends Exception{

    final String MENSAJE = "El promedio de calificaciones del usuario es insuficiente"

    static constraints = {
    }

    PromedioInsuficienteException(){}
}
