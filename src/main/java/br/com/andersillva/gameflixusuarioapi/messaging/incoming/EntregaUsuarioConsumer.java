package br.com.andersillva.gameflixusuarioapi.messaging.incoming;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.andersillva.gameflixusuarioapi.domain.service.UsuarioJogoService;
import br.com.andersillva.gameflixusuarioapi.messaging.dto.MensagemEntregaUsuarioDTO;

@Component
public class EntregaUsuarioConsumer {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(EntregaUsuarioConsumer.class);

    private final UsuarioJogoService usuarioJogoService;

    public EntregaUsuarioConsumer(UsuarioJogoService usuarioJogoService) {
        this.usuarioJogoService = usuarioJogoService;
    }

    @KafkaListener(topics = "${app.topic.entrega-usuario}")
    @Transactional
    public void consume(@Payload String message, Acknowledgment ack) throws JsonProcessingException {

        log.info("Consumindo mensagem: " + message);

        var jogoLiberadoUsuarioDTO = mapper.readValue(message, MensagemEntregaUsuarioDTO.class);

        jogoLiberadoUsuarioDTO.getJogos().forEach(jogo -> 
        	usuarioJogoService.adicionarJogoUsuario(
        			jogoLiberadoUsuarioDTO.getIdUsuario(), 
        			jogo.getIdJogo(), 
        			jogo.getNome(),
        			jogo.getFormaInclusao())
        );

        ack.acknowledge();

    }

}
