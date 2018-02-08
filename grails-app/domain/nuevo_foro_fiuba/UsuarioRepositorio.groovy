package nuevo_foro_fiuba

class UsuarioRepositorio {

    static constraints = {
    }

    def guardar(Usuario unUsuario){
        unUsuario.save()
    }

}
