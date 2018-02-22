package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class ArchivoService {

  def serviceMethod() {}

  def getArchivoById(long idArchivo){
    Archivo.get(idArchivo)
  }
  
}
