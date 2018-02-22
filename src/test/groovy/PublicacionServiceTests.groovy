

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(PublicacionService)
class PublicacionServiceTests {

    PublicacionService publicacionService

    void setUp(){
        publicacionService = new PublicacionService()
    }

    void tearDown() {

    }

    /*@Test
    void 'test creo una publicacion y la puedo buscar por el id' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacionService.getPublicacionById(publicacion.id) == publicacion
    }

    @Test
    void 'test creo una publicacion y el usuario aparece como duenio' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)

        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Publicacion publicacion = publicacionService.crearPublicacion(usuario,'texto')
        assert publicacionService.usuarioEsDueñoDeLaPublicacion(usuario, publicacion)
    }

    @Test
    void 'test cambio el estado de una publicacion y el estado aparece cambiado' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacion.estado == Publicacion.EstadoPublicacion.ABIERTA
        publicacionService.cambiarEstado(publicacion.id)
        assert publicacion.estado == Publicacion.EstadoPublicacion.CERRADA
    }

    @Test
    void 'test elimino una publicacion y el estado aparece eliminada' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacion.estado == Publicacion.EstadoPublicacion.ABIERTA
        publicacionService.eliminarPublicacion( publicacion.id)
        assert publicacion.estado == Publicacion.EstadoPublicacion.ELIMINADA
    }

    @Test
    void 'test comento una publicacion y puedo encontrar el comentario' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)
        mockDomain(Comentario)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacionService.obtenerComentariosNoEliminados(publicacion).size() == 0
        Comentario comentario = publicacionService.comentarPublicacion(usuario.id, 'texto', publicacion.id)
        List<Comentario> comentarios = publicacionService.obtenerComentariosNoEliminados(publicacion)
        assert comentarios.size() == 1
        assert comentarios.remove(0) == comentario
    }

    @Test
    void 'test agrego una materia requerida para comentar' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario,'texto', catedra,   materia)
        publicacionService.agregarMateriaRequeridaParaComentar(publicacion.id, materia.id)
        assert publicacion.materiasNecesariasParaComentar.contains(materia)
    }

    @Test
    void 'test modifico el texto de una publicacion' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacion.texto == 'texto'
        publicacionService.modificarTextoPublicacion(publicacion.id, 'otro texto')
        assert publicacion.texto == 'otro texto'
    }

    @Test
    void 'test modifico la catedra de una publicacion' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Catedra catedra2 = new Catedra(materia, profesor, 'la desc')
        catedra2.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,   materia)
        assert publicacion.catedraRelacionada == catedra
        publicacionService.modificarCatedraPublicacion(publicacion.id, catedra2.id)
        assert publicacion.catedraRelacionada == catedra2
    }

    @Test
    void 'test modifico el promedio requerido de una publicacion' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Materia materia2 = new Materia('Nuclear', 'la desc')
        materia2.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()

        Publicacion publicacion = publicacionService.crearPublicacion(usuario, 'texto', catedra,  materia)
        assert publicacion.promedioRequeridoParaComentar == 0
        publicacionService.modificarPromedioRequeridoParaComentar(publicacion.id, 4)
        assert publicacion.promedioRequeridoParaComentar == 4
    }

    @Test
    void 'test adjunto un archivo a una publicacion' () {
        mockDomain(Publicacion)
        mockDomain(Usuario)
        mockDomain(Materia)
        mockDomain(Profesor)
        mockDomain(Catedra)
        mockDomain(Archivo)


        Profesor profesor = new Profesor('Burns')
        profesor.save()
        Materia materia = new Materia('Nuclear', 'la desc')
        materia.save()
        Materia materia2 = new Materia('Nuclear', 'la desc')
        materia2.save()
        Catedra catedra = new Catedra(materia, profesor, 'la desc')
        catedra.save()
        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Archivo archivo = new Archivo('nombre', 'path')
        archivo.save()

        Publicacion publicacion = new Publicacion('texto', usuario)
        publicacion.save()
        assert publicacion.archivoAdjunto == null
        publicacionService.adjuntarArchivo(usuario, publicacion, archivo)
        assert publicacion.archivoAdjunto == archivo
    }*/


}
