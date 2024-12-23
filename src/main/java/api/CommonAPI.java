package api;

import com.google.gson.Gson;
import dto.CommonDTO;
import util.FactoryConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet(urlPatterns = "/common")
public class CommonAPI extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryConfiguration.getInstance();
        if(req.getHeader("content-type").equals("application.json")){
            BufferedReader reader = req.getReader();
            CommonDTO commonDTO = new Gson().fromJson(reader, CommonDTO.class);
            System.out.println(commonDTO);
        }
        resp.getWriter().println("Request received");
    }
}
