package me.ryeong.ggjm.websocket;

import lombok.RequiredArgsConstructor;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.repository.MemberRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final MemberRepository memberRepository;


    @MessageMapping("/hello/{id}")
    @SendTo("/topic/greetings/{id}")
    public Greeting greeting(@DestinationVariable Long id,
                             Principal principal,
                             HelloMessage message) throws Exception {

        String username = principal.getName();
        Member member = memberRepository.findByUsername(username).orElseThrow();

        return new Greeting(member.getNickname(), message.getMessage());
    }

}
