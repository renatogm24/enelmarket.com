package com.codingdojo.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.UserService;

@Controller
public class HomeController {

	// Añadir una vez implementado el servicio:
	@Autowired
	private UserService userServ;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {

		// Enlazar objetos User y LoginUser vacíos al JSP
		// para capturar la entrada del formulario
		Long id = (Long) session.getAttribute("userid");

		if (id == null) {
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {			
			return "redirect:/books";
		}

	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		// PARA HACER después: llamar a un método de registro en el servicio
		// ¡para hacer algunas validaciones adicionales y crear un nuevo usuario!

		User registerUser = userServ.register(newUser, result);

		if (result.hasErrors()) {
			// Asegúrate de enviar el LoginUser vacío antes
			// de volver a renderizar la página.
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}

		// ¡Sin errores!
		// PARA HACER después: Almacena sus ID de la base de datos en sesión,
		// en otras palabras, inicia sus sesiones.
		session.setAttribute("userid", registerUser.getId());

		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		// Añadir una vez implementado el servicio:
		User user = userServ.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}

		// ¡Sin errores!
		// PARA HACER después: Almacena sus ID de la base de datos en sesión,
		// en otras palabras, inicia sus sesiones.
		session.setAttribute("userid", user.getId());

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}