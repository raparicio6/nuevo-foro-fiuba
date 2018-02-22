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
      Cursada cursada16 = new Cursada (usuario8, catedra)
      cursada16.save(failOnError:true)
      Cursada cursada17 = new Cursada (usuario, catedra6)
      cursada17.save(failOnError:true)
      Cursada cursada18 = new Cursada (usuario, catedra7)
      cursada18.save(failOnError:true)
      Cursada cursada19 = new Cursada (usuario5, catedra4)
      cursada19.save(failOnError:true)

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
      usuario5.agregarCursada(cursada19)
      usuario6.agregarCursada(cursada8)
      usuario6.agregarCursada(cursada14)
      usuario7.agregarCursada(cursada9)
      usuario7.agregarCursada(cursada15)
      usuario8.agregarCursada(cursada10)
      usuario8.agregarCursada(cursada16)
      usuario.agregarCursada(cursada17)
      usuario.agregarCursada(cursada18)

      // PUBLICACIONES Y COMENTARIOS
      Publicacion publicacion = publicacionService.formarPublicacion(usuario.id, "Buenas, estoy pensando en hacer esta materia durante el próximo cuatri y quería conocer sus opiniones respecto a la cátedra. Saludos!", catedra.id, [materia.id], "Qué opinan de la cátedra?", "Buena,Mala,Me es indiferente")
      Publicacion publicacion2 = publicacionService.formarPublicacion(usuario2.id, "Hola! Estoy armando los horarios para el cuatrimestre y me queda tiempo para cursar Analisis II o Física II. Cuál es más recomendable? Gracias!", 0, [materia2.id, materia4.id])
      Publicacion publicacion3 = publicacionService.formarPublicacion(usuario3.id, "Cuándo arrancan las clases?")
      Publicacion publicacion4 = publicacionService.formarPublicacion(usuario4.id, "Algún libro básico de la materia para ir entrando en tema? Gracias!", catedra3.id, [materia3.id])
      Publicacion publicacion5 = publicacionService.formarPublicacion(usuario5.id, "Busco miembros para formar el grupo del tp, hay alguien libre?", catedra4.id, [materia4.id])
      Publicacion publicacion6 = publicacionService.formarPublicacion(usuario6.id, "Esta materia es lo más!", catedra5.id)
      Publicacion publicacion7 = publicacionService.formarPublicacion(usuario7.id, "Quedan fechas de final este mes?", catedra6.id)
      Publicacion publicacion8 = publicacionService.formarPublicacion(usuario8.id, "Dicen que esta materia es promocionable, es cierto? Cómo es la cursada?", catedra7.id, [materia7.id])

      Comentario comentario = publicacionService.comentarPublicacion(usuario6.id, "La catedra de Acero es mejor, pero la guía es un horror en ambas", publicacion.id)
      Comentario comentario2 = comentarioService.comentarComentario(usuario2.id, "La teórica de Acero es incomparable", comentario.id)
      Comentario comentario3 = publicacionService.comentarPublicacion(usuario5.id, "Te conviene arrancar con análisis II que es más sencillo, no son correlativas igual?", publicacion2.id)
      Comentario comentario4 = publicacionService.comentarPublicacion(usuario.id, "El 12/3", publicacion3.id)

      // CALIFICACIONES
      publicacionService.calificarPublicacion(usuario2.id, publicacion.id, Puntaje.TipoPuntaje.ME_GUSTA)
      publicacionService.calificarPublicacion(usuario.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario2.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario3.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario4.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario5.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario7.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)
      publicacionService.calificarPublicacion(usuario8.id, publicacion6.id, Puntaje.TipoPuntaje.NO_ME_GUSTA)


      // MENSAJES
      mensajePrivadoService.enviarMensaje(usuario3.id, usuario.id, "Texto del mensaje")

    }

    def destroy = {
    }

}
