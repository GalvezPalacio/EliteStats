/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import controlador.Consultas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import modelo.Ligas;

/**
 *
 * @author gabri
 */
@WebServlet(name = "ConsultaServlet", urlPatterns = {"/consultar"})
public class ConsultaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String liga = request.getParameter("liga");
        String anio = request.getParameter("anio");
        String dato = request.getParameter("dato");

        // Selección del fondo según el dato
        String fondo;
        switch (dato.toLowerCase()) {
            case "campeón":
                fondo = "background: url('img/campeon_copa.jpeg') center center / cover no-repeat fixed;";
                break;
            case "subcampeón":
                fondo = "background: url('img/subcampeon_medalla_2.jpeg') center center / cover no-repeat fixed;";
                break;
            case "pichichi":
                fondo = "background: url('img/pichichi_gol.jpeg') center center / cover no-repeat fixed;";
                break;
            case "zamora":
                fondo = "background: url('img/zamora_portero.jpeg') center center / cover no-repeat fixed;";
                break;
            case "todos":
                fondo = "background: url('img/todos_juntos.jpeg') center center / cover no-repeat fixed;";
                break;
            default:
                fondo = "background-color: #0f0f0f;";
                break;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Resultado</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Orbitron&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body { font-family: 'Orbitron', sans-serif; " + fondo + " color: #00ff99; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
        out.println(".card { background: rgba(0, 0, 0, 0.7); padding: 40px; border-radius: 15px; box-shadow: 0 0 20px #00ff9977; border: 2px solid #00ff99; max-width: 600px; text-align: center; }");
        out.println("h2 { margin-bottom: 20px; font-size: 28px; color: #00ffcc; }");
        out.println("p { font-size: 20px; margin: 10px 0; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='card'>");
        out.println("<h2>Resultado</h2>");

        if (dato.equalsIgnoreCase("Todos")) {
            Ligas datos = Consultas.mostrarFilaEnTabla(liga, anio);

            if (datos != null) {
                out.println("<p><strong>Liga:</strong> " + datos.getNombre() + "</p>");
                out.println("<p><strong>Año:</strong> " + datos.getAnio() + "</p>");
                out.println("<p><strong>Campeón:</strong> " + datos.getCampeon() + "</p>");
                out.println("<p><strong>Subcampeón:</strong> " + datos.getSubcampeon() + "</p>");
                out.println("<p><strong>Pichichi:</strong> " + datos.getPichichi() + "</p>");
                out.println("<p><strong>Zamora:</strong> " + datos.getZamora() + "</p>");
            } else {
                out.println("<p>No se encontraron datos para esa liga y año.</p>");
            }

        } else {
            String resultado = Consultas.realizarConsultas(liga, anio, dato);
            out.println("<p>" + dato + " de " + liga + " en " + anio + " es: <strong>" + resultado + "</strong></p>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
