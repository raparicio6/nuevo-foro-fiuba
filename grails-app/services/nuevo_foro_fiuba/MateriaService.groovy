package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class MateriaService {

  def serviceMethod() {}

  def getMateriaById(long idMateria){
    Materia.get(idMateria)
  }

  def getAllMaterias(){
    Materia.list()
  }
  
}
