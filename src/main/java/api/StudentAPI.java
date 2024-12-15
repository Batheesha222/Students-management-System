package api;


import com.google.gson.Gson;
import dto.StudentDTO;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/students")
public class StudentAPI extends HttpServlet {

    private StudentService studentService;
    public StudentAPI(){
        //dependency Injection
        this.studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getHeader("content-type").equals("application/json")){
            BufferedReader reader = req.getReader();
            StudentDTO studentDTO = new Gson().fromJson(reader, StudentDTO.class);
            StudentDTO result = studentService.saveStudent(studentDTO);
            //Validation Here
            if(result!=null){
                resp.getWriter().println(new Gson().toJson(result));
            }else{
                resp.getWriter().println("something went wrong");
            }

        }else {
            resp.getWriter().println("please sent Json format");
        }
    }
    
}
