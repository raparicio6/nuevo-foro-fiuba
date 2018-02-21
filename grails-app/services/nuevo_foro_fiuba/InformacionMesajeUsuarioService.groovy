package nuevo_foro_fiuba

import grails.gorm.transactions.Transactional

@Transactional
class InformacionMensajeUsuarioService {

    def serviceMethod() {

    }

    def getInformacionMensajeUsuarioById(long idInformacionMensajeUsuario){
      InformacionMensajeUsuario.get(idInformacionMensajeUsuario)
    }
}
