import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
 
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        
        var courseList = new ArrayList<DashboardServlet.Course>();
        courseList.add(new DashboardServlet.Course("STA001", "INTRO TO STAT", "Dr. ABC"));
        courseList.add(new DashboardServlet.Course("CSC001", "INTRO TO CS", "Dr. BCC"));
        courseList.add(new DashboardServlet.Course("MAT001", "DIFFERENTIAL", "Prof. AAA"));
        
        request.setAttribute("courses", courseList);

        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }

        assert session != null;
        request.setAttribute("enrolledCourses", enrolledCourses);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }

    public static class Course {
        private String id;
        private String name;
        private String instructor;

        public Course(String id, String name, String instructor) {
            this.id = id;
            this.name = name;
            this.instructor = instructor;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getInstructor() {
            return instructor;
        }
    }
}