package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ryeong.ggjm.common.CurrentUser;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.domain.PartyMember;
import me.ryeong.ggjm.repository.PartyMemberRepository;
import me.ryeong.ggjm.repository.PartyRepository;
import me.ryeong.ggjm.service.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;

    @GetMapping("/parties")
    public String list(Pageable pageable,
                       Model model) {

        Page<Party> page = partyRepository.findAllWithMembers(pageable);
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
                         @CurrentUser Member member,
                         Model model) {
        if (result.hasErrors()) {
            return "parties/form";
        }

        Party party = partyForm.toParty();
        party.setMember(member);
        partyRepository.save(party);

        return "redirect:/parties";
    }

    @GetMapping("/parties/{id}")
    public String view(@PathVariable Long id,
                       @CurrentUser Member member,
                       Model model) {

        Party party = partyRepository.findByIdWithMember(id).orElseThrow();
        List<PartyMember> partyMembers = partyMemberRepository.findByPartyWithMember(party);

        model.addAttribute("party", party);
        model.addAttribute("partyMembers", partyMembers);

        Optional<PartyMember> existMember = partyMembers.stream().filter(partyMember -> partyMember.getMember().getId().equals(member.getId())).findFirst();
        model.addAttribute("entered", existMember.isPresent());

        return "parties/view";
    }

    @GetMapping("/parties/{id}/enter")
    public String enter(@PathVariable Long id,
                        @CurrentUser Member member,
                        Model model) {

        partyService.enterParty(id, member);

        return "redirect:/parties/" + id;
    }

    @GetMapping("/parties/{id}/leave")
    public String leave(@PathVariable Long id,
                        @CurrentUser Member member,
                        Model model) {

        partyService.leaveParty(id, member);

        return "redirect:/parties/" + id;
    }

    @Getter
    @Setter
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
