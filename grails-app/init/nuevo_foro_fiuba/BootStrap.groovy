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
      Materia materia3 = new Materia ('Fisica I', 'Esto es fisica I')
      materia3.save(failOnError:true)
      Materia materia4 = new Materia ('Fisica II', 'Esto es fisica II')
      materia4.save(failOnError:true)
      Materia materia5 = new Materia ('Estructura de las organizaciones', 'Me aburro')
      materia5.save(failOnError:true)
      Materia materia6 = new Materia ('Algoritmos y programacion III', 'Algo IIII')
      materia6.save(failOnError:true)
      Materia materia7 = new Materia ('Organizacion de datos', 'Orga de datos')
      materia7.save(failOnError:true)

      Profesor profesor = new Profesor ('Acero')
      profesor.save(failOnError:true)
      Profesor profesor2 = new Profesor ('Sirne')
      profesor2.save(failOnError:true)
      Profesor profesor3 = new Profesor ('Zammarano')
      profesor3.save(failOnError:true)
      Profesor profesor4 = new Profesor ('Fontela')
      profesor4.save(failOnError:true)
      Profesor profesor5 = new Profesor ('Argerich')
      profesor5.save(failOnError:true)
      Profesor profesor6 = new Profesor ('Piotrowsky')
      profesor6.save(failOnError:true)

      Catedra catedra = new Catedra (materia, profesor, 'DescripcionCatedra_')
      catedra.save(failOnError:true)
      Catedra catedra2 = new Catedra (materia2, profesor2, 'DescripcionCatedra2_')
      catedra2.save(failOnError:true)
      Catedra catedra3 = new Catedra (materia3, profesor, 'DescripcionCatedra3_')
      catedra3.save(failOnError:true)
      Catedra catedra4 = new Catedra (materia4, profesor, 'DescripcionCatedra4_')
      catedra4.save(failOnError:true)
      Catedra catedra5 = new Catedra (materia5, profesor3, 'DescripcionCatedra5_')
      catedra5.save(failOnError:true)
      Catedra catedra6 = new Catedra (materia6, profesor4, 'DescripcionCatedra6_')
      catedra6.save(failOnError:true)
      Catedra catedra7 = new Catedra (materia7, profesor5, 'DescripcionCatedra7_')
      catedra7.save(failOnError:true)
      Catedra catedra8 = new Catedra (materia2, profesor6, 'DescripcionCatedra7_')
      catedra8.save(failOnError:true)

      Cursada cursada = new Cursada (usuario, catedra)
      cursada.save(failOnError:true)
      Cursada cursada2 = new Cursada (usuario, catedra2)
      cursada2.save(failOnError:true)
      Cursada cursada3 = new Cursada (usuario2, catedra3)
      cursada3.save(failOnError:true)
      Cursada cursada4 = new Cursada (usuario2, catedra)
      cursada4.save(failOnError:true)
      Cursada cursada5 = new Cursada (usuario3, catedra3)
      cursada5.save(failOnError:true)
      Cursada cursada6 = new Cursada (usuario4, catedra4)
      cursada6.save(failOnError:true)
      Cursada cursada7 = new Cursada (usuario5, catedra5)
      cursada7.save(failOnError:true)
      Cursada cursada8 = new Cursada (usuario6, catedra6)
      cursada8.save(failOnError:true)
      Cursada cursada9 = new Cursada (usuario7, catedra7)
      cursada9.save(failOnError:true)
      Cursada cursada10 = new Cursada (usuario8, catedra8)
      cursada10.save(failOnError:true)
      Cursada cursada11 = new Cursada (usuario3, catedra7)
      cursada11.save(failOnError:true)
      Cursada cursada12 = new Cursada (usuario4, catedra3)
      cursada12.save(failOnError:true)
      Cursada cursada13 = new Cursada (usuario5, catedra8)
      cursada13.save(failOnError:true)
      Cursada cursada14 = new Cursada (usuario6, catedra)
      cursada14.save(failOnError:true)
      Cursada cursada15 = new Cursada (usuario7, catedra5)
      cursada15.save(failOnError:true)
      Cursada cursada16 = new Cursada (usuario8, catedra2)
      cursada16.save(failOnError:true)

      usuario.agregarCursada(cursada)
      usuario.agregarCursada(cursada2)
      usuario2.agregarCursada(cursada4)
      usuario2.agregarCursada(cursada3)
      usuario3.agregarCursada(cursada5)
      usuario3.agregarCursada(cursada11)
      usuario4.agregarCursada(cursada6)
      usuario4.agregarCursada(cursada12)
      usuario5.agregarCursada(cursada7)
      usuario5.agregarCursada(cursada13)
      usuario6.agregarCursada(cursada8)
      usuario6.agregarCursada(cursada14)
      usuario7.agregarCursada(cursada9)
      usuario7.agregarCursada(cursada15)
      usuario8.agregarCursada(cursada10)
      usuario8.agregarCursada(cursada16)

      // PUBLICACIONES Y COMENTARIOS
      Publicacion publicacion = publicacionService.formarPublicacion(usuario.id, "texto publicacion", catedra.id, , [materia.id], "Qué opinan de la cátedra?", "Buena,Mala,Me es indiferente")
      Publicacion publicacion2 = publicacionService.formarPublicacion(usuario2.id, "texto publicacion aaaa", catedra2.id, , [materia2.id, materia3.id])
      Publicacion publicacion3 = publicacionService.formarPublicacion(usuario3.id, "Cuando arrancan las clases?")
      Comentario comentario = publicacionService.comentarPublicacion(usuario2.id, "Soy el usuario2", publicacion.id)
      Comentario comentario2 = comentarioService.comentarComentario(usuario2.id, "Soy el usuario2 otra vez", comentario.id)


      // CALIFICACIONES
      publicacionService.calificarPublicacion(usuario2.id, publicacion.id, Puntaje.TipoPuntaje.ME_GUSTA)


      // MENSAJES
      mensajePrivadoService.enviarMensaje(usuario3.id, usuario.id, "Texto del mensaje")

    }

    def destroy = {
    }

}
