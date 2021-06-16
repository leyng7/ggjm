package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.repository.MemberRepository;
import me.ryeong.ggjm.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public String list() {

        return "members/list";
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {

        model.addAttribute("memberForm", new MemberForm());

        return "members/form";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "members/form";
        }

        Member member = memberForm.toMember();
        memberService.join(member);

        return "redirect:/login";
    }

    @Getter @Setter
    static class MemberForm {

        @NotBlank(message = "아이디는 필수값입니다.")
        private String username;

        @NotBlank(message = "비밀번호는 필수값입니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수값입니다.")
        private String nickname;

        public Member toMember() {
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setNickname(nickname);
            member.setRole("admin");
            return member;
        }
    }

}
