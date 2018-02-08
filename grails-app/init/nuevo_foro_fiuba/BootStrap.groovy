package nuevo_foro_fiuba
import nuevo_foro_fiuba.Puntaje.TipoPuntaje

class BootStrap {

    Usuario usuarioLogin

    def init = { servletContext ->
      Usuario usuario = new Usuario ("NombreUsuario_", "ApellidoUsuario_", "NickUsuario_")
      usuario.save(failOnError:true)
      Usuario usuario2 = new Usuario ("NombreUsuario2_", "ApellidoUsuario2_", "NickUsuario2_")
      usuario2.save(failOnError:true)
      usuarioLogin = usuario2
      Materia materia = new Materia ('NombreMateria_', 'DescripcionMateria_')
      materia.save(failOnError:true)
      Materia materia2 = new Materia ('NombreMateria2_', 'DescripcionMateria2_')
      materia2.save(failOnError:true)
      Materia materia3 = new Materia ('NombreMateria3_', 'DescripcionMateria3_')
      materia3.save(failOnError:true)
      Profesor profesor = new Profesor ('NombreProfesor_')
      profesor.save(failOnError:true)
      Profesor profesor2 = new Profesor ('NombreProfesor2_')
      profesor2.save(failOnError:true)
      Catedra catedra = new Catedra (materia, profesor, 'DescripcionCatedra_')
      catedra.save(failOnError:true)
      Catedra catedra2 = new Catedra (materia2, profesor2, 'DescripcionCatedra2_')
      catedra2.save(failOnError:true)
      Catedra catedra3 = new Catedra (materia3, profesor, 'DescripcionCatedra3_')
      catedra3.save(failOnError:true)
      Cursada cursada = new Cursada (usuario, catedra)
      cursada.save(failOnError:true)
      usuario.setCursadas(usuario.cursadas+=[cursada])
      Publicacion publicacion = new Publicacion ('TextoPublicacion_', usuario, materia, catedra)
      publicacion.save(failOnError:true)
      Publicacion publicacion2 = new Publicacion ('TextoPublicacion_', usuario2, materia, catedra)
      publicacion2.save(failOnError:true)
      Comentario comentario = new Comentario ("Soy el usuario2", usuario2, publicacion, null)
      comentario.save(failOnError:true)
      Comentario comentario2 = new Comentario ("Soy el usuario2 otra vez", usuario2, null, comentario)
      comentario2.save(failOnError:true)
      publicacion.comentarios += [comentario]
      comentario.comentarios += [comentario2]
      usuarioLogin = Usuario.get(1)
      usuario.setPuntajeActual(3)
      usuario2.setPuntajeActual(3)
      Cursada cursada2 = new Cursada (usuario, catedra2)
      cursada2.save(failOnError:true)
      usuario.setCursadas(usuario.cursadas+=[cursada2])
      Cursada cursada3 = new Cursada (usuario2, catedra3)
      cursada3.save(failOnError:true)
      usuario2.setCursadas(usuario2.cursadas+=[cursada3])
      Puntaje puntaje = new Puntaje (TipoPuntaje.meGusta,4)
      puntaje.save(failOnError:true)
      Calificacion calificacion = new Calificacion (usuario2, puntaje, publicacion, null)
      calificacion.save(failOnError:true)
      publicacion.agregarCalificacion(calificacion)
    }
    def destroy = {
    }
}
