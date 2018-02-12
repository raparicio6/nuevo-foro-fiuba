package nuevo_foro_fiuba

class Cursada {

    Usuario usuario
    Catedra catedra

    static constraints = {
      usuario nullable:false
      catedra nullable:false
    }

    Cursada(Usuario usuario, Catedra catedra) {
        this.usuario = usuario
        this.catedra = catedra
    }
}
