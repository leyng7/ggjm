package me.ryeong.ggjm.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ryeong.ggjm.common.CurrentUser;
import me.ryeong.ggjm.domain.Member;
import me.ryeong.ggjm.domain.Party;
import me.ryeong.ggjm.domain.Restaurant;
import me.ryeong.ggjm.repository.RestaurantRepository;
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
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

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
        restaurant.setMember(member);
        restaurantRepository.save(restaurant);

        return "redirect:/restaurants";
    }

    @Getter @Setter
    static class RestaurantForm {

        @NotBlank(message = "식당명은 필수값입니다.")
        private String name;

        @NotBlank(message = "구분값은 필수값입니다.")
        private String kind;

        public Restaurant toRestaurant() {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(name);
            restaurant.setKind(kind);
            return restaurant;
        }

    }

}
