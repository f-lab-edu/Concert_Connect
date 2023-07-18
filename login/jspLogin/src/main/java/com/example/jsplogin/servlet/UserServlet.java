package com.example.jsplogin.servlet;


import com.example.jsplogin.models.User;
import com.example.jsplogin.repogitory.InMemoryUserRepository;
import com.example.jsplogin.repogitory.UserRepository;
import com.example.jsplogin.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        UserRepository userRepository = new InMemoryUserRepository();
        this.userService = new UserService(userRepository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "login":
                    handleLogin(request, response);
                    break;
                case "signup":
                    handleSignup(request, response);
                    break;
            }
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (userService.authenticate(id, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            response.sendRedirect(request.getContextPath() + "/login/home");
        } else {
            request.setAttribute("error", "잘못된 로그인 요청");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        User user = new User(id, password);
        userService.registerUser(user);

        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        response.sendRedirect(request.getContextPath() + "/login/home");
    }
}

/*
클래스변수
유저서비스를 사용할수있게 변수로 만드는 과정

init 메서드
인터페이스변수에 구현체객체 넣기
클래스변수에 객체 넣기

doPost메서드
 요청에서 action 가져오기
 가져온 액션으로 if, switch 돌려서 login signup 뷰페이지로 전달하기

handleLogin메서드
id,password를 요청데이터에서 가져오고
if문에서 검증메서드 동작해서 맞으면 요청데이터에서 세션 가져와서
안에 id를 넣고 home 뷰로 리다이렉트
아니라면 에러를 요청에실어서 login 뷰페이지로 req,resp포워드 한다

handleSignup메서드
요청데이터에서 id, password를 갖고와서 User 클래스로만든 user객체에 생성할때 매개변수로 넣고
reqister 메서드에  매개변수로 user를 넣어서 실행

요청에 세션을 만들어서 세션에 id를 실고 요청에 home뷰로 리다이렉트 한다
 */

