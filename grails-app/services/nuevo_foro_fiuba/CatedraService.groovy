package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class CatedraService {

  def serviceMethod() {}

  def getAllCatedras(){
    Catedra.list()
  }

  def getCatedraById(long idCatedra){
    Catedra.get(idCatedra)
  }
  
}
