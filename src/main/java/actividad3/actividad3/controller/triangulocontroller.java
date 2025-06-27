package actividad3.actividad3.controller;

import actividad3.actividad3.model.triangulo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class triangulocontroller {
    /**
     * Muestra el formulario para calcular el área y perímetro de un triángulo.
     * Si hay datos en las cookies, los muestra en el formulario.
     *
     * @param session   La sesión HTTP para almacenar el nombre del usuario.
     * @param base      El valor de la base del triángulo desde la cookie.
     * @param altura    El valor de la altura del triángulo desde la cookie.
     * @param area      El área del triángulo desde la cookie.
     * @param perimetro El perímetro del triángulo desde la cookie.
     * @param model     El modelo para pasar datos a la vista.
     * @return La vista del formulario.
     */
    @GetMapping("/")
    public String mostrarFormulario(HttpSession session,
                                    @CookieValue(value = "base", defaultValue = "") String base,
                                    @CookieValue(value = "altura", defaultValue = "") String altura,
                                    @CookieValue(value = "area", defaultValue = "") String area,
                                    @CookieValue(value = "perimetro", defaultValue = "") String perimetro,
                                    Model model) {

        String nombre = (String) session.getAttribute("nombreUsuario");
        model.addAttribute("nombreUsuario", nombre);

        if (nombre == null) {
            model.addAttribute("preguntarNombre", true);
        } else {
            model.addAttribute("preguntarNombre", false);
        }

        if (!base.isEmpty()) {
            model.addAttribute("ultimoCalculo", true);
            model.addAttribute("base", base);
            model.addAttribute("altura", altura);
            model.addAttribute("area", area);
            model.addAttribute("perimetro", perimetro);
        }

        return "index";
    }

    @PostMapping("/calcular")
    public String procesarFormulario(@RequestParam String base,
                                     @RequestParam String altura,
                                     @RequestParam(required = false) String nombre,
                                     HttpSession session,
                                     HttpServletResponse response,
                                     Model model) {

        double b = Double.parseDouble(base);
        double h = Double.parseDouble(altura);
        triangulo t = new triangulo(b, h);

        double area = t.calcularArea();
        double perimetro = t.calcularPerimetro();

        // Guardar en sesión
        if (session.getAttribute("nombreUsuario") == null && nombre != null) {
            session.setAttribute("nombreUsuario", nombre);
        }

        // Guardar en cookies
        response.addCookie(new Cookie("base", base));
        response.addCookie(new Cookie("altura", altura));
        response.addCookie(new Cookie("area", String.format("%.2f", area)));
        response.addCookie(new Cookie("perimetro", String.format("%.2f", perimetro)));

        // Pasar datos al template con formato
        model.addAttribute("base", base);
        model.addAttribute("altura", altura);
        model.addAttribute("area", String.format("%.2f", area));
        model.addAttribute("perimetro", String.format("%.2f", perimetro));

        return "resultado";
    }
}
