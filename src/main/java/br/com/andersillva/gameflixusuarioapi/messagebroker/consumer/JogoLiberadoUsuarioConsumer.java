package br.com.andersillva.gameflixusuarioapi.messagebroker.consumer;

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
import br.com.andersillva.gameflixusuarioapi.messagebroker.dto.JogoLiberadoUsuarioDTO;

@Component
public class JogoLiberadoUsuarioConsumer {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(JogoLiberadoUsuarioConsumer.class);

    private final UsuarioJogoService usuarioJogoService;

    public JogoLiberadoUsuarioConsumer(UsuarioJogoService usuarioJogoService) {
        this.usuarioJogoService = usuarioJogoService;
    }

    @KafkaListener(topics = "${app.topic.jogo-liberado-para-usuario}")
    @Transactional
    public void consume(@Payload String message, Acknowledgment ack) throws JsonProcessingException {

        log.info("Consumindo mensagem: " + message);

        var jogoLiberadoUsuarioDTO = mapper.readValue(message, JogoLiberadoUsuarioDTO.class);

        jogoLiberadoUsuarioDTO.getJogos().forEach(jogo -> 
        	usuarioJogoService.adicionarJogoUsuario(jogo.getIdJogo(), jogoLiberadoUsuarioDTO.getIdUsuario())
        );

        ack.acknowledge();

    }

}
