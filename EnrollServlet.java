import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {


        String courseId = request.getParameter("courseId");
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        var courseList = new ArrayList<DashboardServlet.Course>();
        courseList.add(new DashboardServlet.Course("STA001", "INTRO TO STAT", "Dr. ABC"));
        courseList.add(new DashboardServlet.Course("CSC001", "INTRO TO CS", "Dr. BCC"));
        courseList.add(new DashboardServlet.Course("MAT001", "DIFFERENTIAL", "Prof. AAA"));        assert session != null;
        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        for (DashboardServlet.Course course : courseList) {
            if (course.getId().equals(courseId)) {
                enrolledCourses.add(course);
            }
        }
        session.setAttribute("enrolledCourses", enrolledCourses);

        response.sendRedirect("DashboardServlet");
    

    }
}