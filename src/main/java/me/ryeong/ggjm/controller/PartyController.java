package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.repository.MemberRepository;
import me.ryeong.ggjm.repository.PartyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Controller
@RequiredArgsConstructor
public class PartyController {

    private final MemberRepository memberRepository;

    private final PartyRepository partyRepository;

    @GetMapping("/parties")
    public String list(Pageable pageable,
                       Model model) {

        Page<Party> page = partyRepository.findAll(pageable);
        model.addAttribute("page", page);

        return "parties/list";
    }

    @GetMapping("/parties/new")
    public String createForm(Model model) {

        model.addAttribute("partyForm", new PartyForm());

        return "parties/form";
    }

    @PostMapping("/parties/new")
    public String create(@Valid PartyForm partyForm,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "parties/form";
        }

        Party party = partyForm.toParty();
        partyRepository.save(party);

        return "redirect:/parties";
    }

    @Getter @Setter
    static class PartyForm {

        @NotBlank(message = "이름은 필수값입니다.")
        private String name;

        public Party toParty() {
            Party party = new Party();
            party.setName(name);
            return party;
        }
    }

}
