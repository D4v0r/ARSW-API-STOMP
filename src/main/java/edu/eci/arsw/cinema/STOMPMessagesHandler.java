package edu.eci.arsw.cinema;

import edu.eci.arsw.cinema.model.Seat;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Autowired
    CinemaServices cinemaServices;

    @MessageMapping("/buyticket.{cinemaName}.{functionDate}.{movieName}")
    public void handleBuyEvent(Seat st, @DestinationVariable String cinemaName, @DestinationVariable String functionDate, @DestinationVariable String movieName) throws Exception {
        System.out.println("Nuevo asiento recibido en el servidor!:"+st);
        messagingTemplate.convertAndSend("/topic/buyticket."+cinemaName+"."+functionDate+"."+movieName, st);
    }
}
