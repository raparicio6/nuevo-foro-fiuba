

import grails.test.mixin.TestFor
import nuevo_foro_fiuba.PromedioInsuficienteException
import spock.lang.Specification
import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import nuevo_foro_fiuba.*

@TestFor(UsuarioService)
class UsuarioServiceTests {

    UsuarioService usuarioService

    void setUp(){
        usuarioService = new UsuarioService()
    }

    void tearDown() {

    }

    @Test
    void 'test creo un usuario y lo puedo buscar por el id' () {
        mockDomain(Usuario)

        Usuario usuario = usuarioService.crearUsuario('Homero', 'Simpson', 'HSimpson')
        assert usuarioService.getUsuarioById(usuario.id) == usuario
    }

    @Test
    void 'test busco usuario ya creado por id' () {
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        assert usuarioService.getUsuarioById(usuario.id) == usuario
    }

    @Test
    void 'test busco usuarios con un filtro de calificaciones' () {
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 2)
        usuario2.save()
        List<Usuario> usuarios = usuarioService.filtrarPorPromedio(3,5)
        assert usuarios.size() == 1
        assert usuarios.remove(0) == usuario
    }

    @Test
    void 'test busco todos los usuarios' () {
        mockDomain(Usuario)

        Usuario usuario = new Usuario('Homero', 'Simpson', 'HSimpson', 4)
        usuario.save()
        Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 2)
        usuario2.save()
        List<Usuario> usuarios = usuarioService.getAllUsuarios()
        assert usuarios.size() == 2
        assert usuarios.remove(0) == usuario
        assert usuarios.remove(0) == usuario2
    }

    /*@Test
     void 'test busco usuarios con un filtro de materia' () {
         mockDomain(Usuario)
         mockDomain(Cursada)
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
         Cursada cursada = new Cursada(usuario, catedra)
         usuario.agregarCursada(cursada)
         Usuario usuario2 = new Usuario('Marge', 'Simpson', 'MSimpson', 2)
         usuario2.save()
         ArrayList<Usuario> usuariosTemp = []
         usuariosTemp.add(usuario)
         usuariosTemp.add(usuario2)
         List<Usuario> usuarios = usuarioService.filtrarPorMateria(usuariosTemp, materia.id)
         assert usuarios.remove(0) == usuario
    }*/
}
