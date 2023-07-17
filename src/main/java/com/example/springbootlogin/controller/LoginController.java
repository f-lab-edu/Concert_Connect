package com.example.springbootlogin.controller;

import com.example.springbootlogin.dao.UserDao;
import com.example.springbootlogin.dto.UserDto;
import com.example.springbootlogin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginController(UserDao userDao, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        System.out.println("로그인 핸들러");
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignupPage(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);

        userDao.addUser(user);

        userDao.addAuthority(user.getUsername(), "ROLE_USER");

        System.out.println(user.getUsername() + user.getPassword());
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String handleLoginForm(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();

        User user = userDao.getUserByUsername(username);

        if (user == null) {
            System.out.println("유저 몰름: " + username);
            return "redirect:/login";
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            // Check if the user has the ROLE_USER authority
            if (hasUserRole(username)) {
                return "redirect:/home";
            } else {
                System.out.println("로그인 실패" + username);
                return "redirect:/login";
            }
        } else {
            System.out.println("로그인 실패" + username);
            return "redirect:/login";
        }
    }
    private boolean hasUserRole(String username) {
        String sql = "SELECT COUNT(*) FROM authorities WHERE username = ? AND authority = 'ROLE_USER'";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count > 0;
    }
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
/*

/login uri로 GET 요청을 하면 loginPage 메소드가 동작됩니다. "login" 뷰페이지 반환.

/signup uri로 GET 요청을 하면 signupPage 메소드가 동작. "signup" 보기를 반환합니다.

사용자 가입 데이터로 /signup uri에 POST 요청이 이루어지면 handleSignupPage 메소드가 실행됩니다.

제공된 사용자 이름으로 새로운 User 객체를 생성합.
PasswordEncoder를 사용하여 암호를 인코딩합니다.
UserDao를 사용하여 데이터베이스에 사용자를 추가하고 사용자에 대한 "ROLE_USER" 권한을 추가합니다.
사용자를 /로그인 페이지로 리다이렉트.

사용자 로그인 데이터로 /login uri에 POST 요청이 이루어지면 handleLoginForm 메소드가 실행됩니다.

보낸 데이터에서 사용자 이름과 비밀번호를 검색.
UserDao로 데이터베이스에서 해당 사용자를 가져오고.
사용자가 존재하지 않으면 /login 페이지로 다시 리다이렉트.

사용자가 존재하는 경우 PasswordEncoder를 사용하여 비밀번호가 일치하는지 체크.
비밀번호가 일치하면 hasUserRole 메소드를 호출하여 사용자에게 "ROLE_USER" 권한이 있는지 치크.
사용자에게 권한이 있는 경우 /home 페이지로 리디렉션됩니다. 그렇지 않으면 /login 페이지로 다시 리디렉션됩니다.

hasUserRole 메서드는 JdbcTemplate을 사용하여 데이터베이스를 사용해서 사용자에게 "ROLE_USER" 권한이 있는지 확인합니다.

/home uri로 GET 요청이 이루어지면 homePage 메소드가 실행됩니다. "home" 반환합니다.

UserDao 클래스는 User 엔터티에서 CRUD 작업을 수행하기 위해 데이터베이스와 상호 작용하는 DAO.

JdbcTemplate을 사용하여 SQL 쿼리를 실행하고 결과를 User 개체에 매핑합니다(UserRowMapper).

UserDto 클래스는 가입 및 로그인 작업을 위한 사용자 데이터를 보유하는 DTO.

User 클래스는 사용자 이름과 암호 필드를 가진 사용자를 나타내는 엔티티 클래스.

SecurityConfig 클래스는 Spring Security 구성 클래스입니다.

Pbkdf2PasswordEncoder(?)를 사용하도록 PasswordEncoder 빈을 구성합니다.
JdbcUserDetailsManager를 사용하도록 UserDetailsService 빈을 구성합니다.

다양한 uri에 대한 보안 규칙을 구성하여 /login 및 /signup uri에 대한 인증되지 않은 요청을 허용하고 /login 및 /signup uri에 대한 POST 요청에 대한 인증을 하고 다른 모든 uri 대한 인증을 함.
로그인 페이지, 기본 성공 URL, 실패 URL, 로그아웃 URL을 구성하고 CSRF 보호를 비활성화합니다.
보안 설정을 적용한 SecurityFilterChain 빈을 반환합니다.
 */