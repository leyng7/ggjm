package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ryeong.ggjm.common.CurrentUser;
import me.ryeong.ggjm.domain.*;
import me.ryeong.ggjm.repository.PartyRepository;
import me.ryeong.ggjm.repository.RestaurantRepository;
import me.ryeong.ggjm.service.RestaurantService;
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
import javax.validation.constraints.NotNull;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;
    private final PartyRepository partyRepository;

    @GetMapping("/restaurants")
    public String list(Pageable pageable,
                       Model model) {

        Page<Restaurant> page = restaurantRepository.findAll(pageable);
        model.addAttribute("page", page);

        return "restaurants/list";
    }

    @GetMapping("/restaurants/new")
    public String createForm(Model model) {

        model.addAttribute("restaurantForm", new RestaurantForm());

        return "restaurants/form";
    }

    @PostMapping("/restaurants/new")
    public String create(@Valid RestaurantForm restaurantForm,
                         BindingResult result,
                         @CurrentUser Member member,
                         Model model) {
        if (result.hasErrors()) {
            return "restaurants/form";
        }

        Restaurant restaurant = restaurantForm.toRestaurant();

        if (restaurant.getId() != null) {
            restaurantService.update(restaurant);
        }

        restaurant.setMember(member);
        restaurantRepository.save(restaurant);

        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String view(@PathVariable Long id,
                       @CurrentUser Member member,
                       Model model) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        model.addAttribute("restaurant", restaurant);

        return "restaurants/view";
    }


    @GetMapping("/restaurants/{id}/edit")
    public String edit(@PathVariable Long id,
                       @CurrentUser Member member,
                       Model model) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        RestaurantForm restaurantForm = new RestaurantForm();
        restaurantForm.setId(restaurant.getId());
        restaurantForm.setName(restaurant.getName());
        restaurantForm.setLet(restaurant.getLet());
        restaurantForm.setLng(restaurant.getLng());
        restaurantForm.setType(restaurant.getType());
        restaurantForm.setArea(restaurant.getArea());

        model.addAttribute("restaurantForm", restaurantForm);

        return "restaurants/form";
    }


    @GetMapping("/restaurants/{id}/new-party")
    public String createPartyForm(@PathVariable Long id,
                                  @CurrentUser Member member,
                                  Model model) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("partyForm", new PartyForm());

        return "restaurants/newParty";
    }


    @PostMapping("/restaurants/{id}/new-party")
    public String createParty(@PathVariable Long id,
                              @Valid PartyForm partyForm,
                              BindingResult result,
                              @CurrentUser Member member,
                              Model model) {

        if (result.hasErrors()) {
            return "restaurants/newParty";
        }

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        Party party = partyForm.toParty();
        party.setRestaurant(restaurant);
        party.setMember(member);
        partyRepository.save(party);

        return "redirect:/parties";
    }


    @Getter
    @Setter
    static class RestaurantForm {

        private Long id;

        @NotBlank(message = "식당명은 필수값입니다.")
        private String name;

        @NotNull(message = "구분값은 필수값입니다.")
        private RestaurantType type;

        @NotNull(message = "지역은 필수값입니다.")
        private RestaurantArea area;

        @NotNull(message = "let은 필수값입니다.")
        private Double let;

        @NotNull(message = "lng는 필수값입니다.")
        private Double lng;

        public Restaurant toRestaurant() {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setName(name);
            restaurant.setType(type);
            restaurant.setArea(area);
            restaurant.setLng(lng);
            restaurant.setLet(let);
            return restaurant;
        }
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
