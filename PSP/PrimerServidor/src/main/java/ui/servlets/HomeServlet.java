package ui.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchRequest(req,resp);
    }

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer random = (Integer) req.getSession().getAttribute("random");
        Integer intento = (Integer) req.getSession().getAttribute("intentos");

        if (random == null) {
            random = new Random().nextInt(10) + 1;
            req.getSession().setAttribute("random", random);
        }

        if (intento == null) {
            intento = 1;
        } else {
            intento++;
        }
        req.getSession().setAttribute("intentos", intento);
        String mensaje;

        if (intento > 5) {
            mensaje = "¡Has perdido! El número era " + random + ". Se ha reiniciado el juego.";
            req.getSession().removeAttribute("random");
            req.getSession().removeAttribute("intentos");
        } else {
            String userInput = req.getParameter("number");

            if (userInput != null) {
                try {
                    int userNumber = Integer.parseInt(userInput);

                    if (userNumber == random) {
                        mensaje = "¡Felicidades! Has adivinado el número en " + intento + " intento(s).";
                        // Reiniciar el juego
                        req.getSession().removeAttribute("random");
                        req.getSession().removeAttribute("intentos");
                    } else if (userNumber > random) {
                        mensaje = "El número ingresado es menor. ¡Intenta de nuevo!";
                    } else {
                        mensaje = "El número ingresado es mayor. ¡Intenta de nuevo!";
                    }
                } catch (NumberFormatException e) {
                    mensaje = "Por favor, ingresa un número válido.";
                }
            } else {
                mensaje = "Introduce un número para comenzar.";
            }
        }

        resp.setContentType("text/html");
        resp.getWriter().println("<html lang=\"es\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Adivinar el número</title>" +
                "</head>" +
                "<body>" +
                "<h1>Intento: " + (intento > 5 ? 5 : intento) + " / " + 5 + "</h1>" +
                "<h2>" + mensaje + "</h2>" +
                (intento > 5 ? "" : "<form method=\"post\">" +
                        "<label for=\"numberInput\">Número:</label>" +
                        "<input type=\"number\" id=\"numberInput\" name=\"number\" required>" +
                        "<button type=\"submit\">Probar</button>" +
                        "</form>") +
                "</body>" +
                "</html>");
    }
}
