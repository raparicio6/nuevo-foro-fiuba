package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class BootStrap {

    def usuarioService
    def publicacionService
    def mensajePrivadoService
    def comentarioService

    def init = { servletContext ->
      // USUARIOS
      Usuario usuario = usuarioService.crearUsuario ("Mariano", "Martin", "mmartin")
      Usuario usuario2 = usuarioService.crearUsuario ("Rodrigo", "Aparicio", "raparicio")
      Usuario usuario3 = usuarioService.crearUsuario ("German", "Rotili", "grotili")
      Usuario usuario4 = usuarioService.crearUsuario ("Tomas", "Gomez", "tgomez")
      Usuario usuario5 = usuarioService.crearUsuario ("Fernando", "Diaz", "fdiaz")
      Usuario usuario6 = usuarioService.crearUsuario ("Federico", "Gonzalez", "fede")
      Usuario usuario7 = usuarioService.crearUsuario ("Sofia", "Gonzalez", "sofi")
      Usuario usuario8 = usuarioService.crearUsuario ("Josefina", "Gonzalez", "jose")


      // MATERIAS, PROFESORES, CATEDRAS Y CURSADAS
      Materia materia = new Materia ('Matematica discreta', 'Esto es mate discreta')
      materia.save(failOnError:true)
      Materia materia2 = new Materia ('Analisis II', 'Esto es analisis II')
      materia2.save(failOnError:true)
      Materia materia3 = new Materia ('Fisica II', 'Esto es fisica II')
      materia3.save(failOnError:true)

      Profesor profesor = new Profesor ('Acero')
      profesor.save(failOnError:true)
      Profesor profesor2 = new Profesor ('Sirne')
      profesor2.save(failOnError:true)

      Catedra catedra = new Catedra (materia, profesor, 'DescripcionCatedra_')
      catedra.save(failOnError:true)
      Catedra catedra2 = new Catedra (materia2, profesor2, 'DescripcionCatedra2_')
      catedra2.save(failOnError:true)
      Catedra catedra3 = new Catedra (materia3, profesor, 'DescripcionCatedra3_')
      catedra3.save(failOnError:true)

      Cursada cursada = new Cursada (usuario, catedra)
      cursada.save(failOnError:true)
      Cursada cursada2 = new Cursada (usuario, catedra2)
      cursada2.save(failOnError:true)
      Cursada cursada3 = new Cursada (usuario2, catedra3)
      cursada3.save(failOnError:true)
      Cursada cursada4 = new Cursada (usuario2, catedra)
      cursada4.save(failOnError:true)

      usuario.agregarCursada(cursada)
      usuario.agregarCursada(cursada2)
      usuario2.agregarCursada(cursada4)
      usuario2.agregarCursada(cursada3)


      // PUBLICACIONES Y COMENTARIOS
      Publicacion publicacion = publicacionService.formarPublicacion(usuario.id, catedra.id, "texto publicacion", materia.id, 0)
      Publicacion publicacion2 = publicacionService.formarPublicacion(usuario2.id, catedra2.id, "texto publicacion aaaa", materia2.id, 0)
      Comentario comentario = publicacionService.comentarPublicacion(usuario2.id, "Soy el usuario2", publicacion.id)
      Comentario comentario2 = comentarioService.comentarComentario(usuario2.id, "Soy el usuario2 otra vez", comentario.id)


      // CALIFICACIONES
      publicacionService.calificarPublicacion(usuario2.id, publicacion.id, Puntaje.TipoPuntaje.ME_GUSTA)


      // MENSAJES
      mensajePrivadoService.enviarMensaje(usuario3.id, usuario.id, "Texto del mensaje")


      // ENCUESTAS --> cuando se agregue la encuesta en formarPublicacion pasarlo para alla
      Opcion opcion1 = new Opcion ("Buena")
      opcion1.save(failOnError:true)
      Opcion opcion2 = new Opcion ("Mala")
      opcion2.save(failOnError:true)
      Opcion opcion3 = new Opcion ("Me es indiferente")
      opcion3.save(failOnError:true)
      Encuesta encuesta = new Encuesta ("Qué opinan de la cátedra?", [opcion1,opcion2,opcion3].toSet())
      encuesta.save(failOnError:true)
      publicacion.agregarEncuesta(encuesta)

    }

    def destroy = {
    }

}
