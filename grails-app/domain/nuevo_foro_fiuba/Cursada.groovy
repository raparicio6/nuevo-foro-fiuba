package nuevo_foro_fiuba

class Cursada {

    Usuario usuario
    Catedra catedra

    static constraints = {
    }

    Cursada(Usuario usuario, Catedra catedra) {
        this.usuario = usuario
        this.catedra = catedra
    }
}
