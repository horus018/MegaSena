package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Gerador", urlPatterns = {"/gerar","/numeros"})
public class Gerador extends HttpServlet {
Random randomico = new Random(System.currentTimeMillis());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Integer> sorteados = new ArrayList<>(6);
        String numUsuario = request.getParameter("numSorte"); // na url => /MegaSena/gerar?numSorte=14
        
        //esse request.getParameter faz uma requisição com o servidor
        
        /*essa list e essa string estão dentro do "throws ServletException, IOException" pois pra cada requisição
          ele cria uma lista e uma string, se voce botar os dois fora igual o "Random randomico" ele faria um pra todo mundo
          q acessar o servidor
        */ 
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Números da Megasena</title>");            
            out.println("</head>");
            out.println("<body>");
            int MAX = 6;
            if(numUsuario != null){
                out.println("<p>"+numUsuario+"</p>");
                sorteados.add(Integer.parseInt(numUsuario));
                MAX = 5;
            }
            for(int i = 0; i < MAX;){
                int novo = randomico.nextInt(61);
                if(!sorteados.contains(novo)){
                    out.print("<p>");
                    out.print(novo);
                    out.print("</p>");
                    sorteados.add(novo);
                    i++;
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
