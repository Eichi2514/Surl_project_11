package com.koreait.surl_project_11.global.rq;

import com.koreait.surl_project_11.domain.member.member.entity.Member;
import com.koreait.surl_project_11.domain.member.member.service.MemberService;
import com.koreait.surl_project_11.global.exceptions.GlobalException;
import com.koreait.surl_project_11.standard.util.Ut;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;
    private final LocalValidatorFactoryBean defaultValidator;

    private Member member;

//    @Getter
//    @Setter
//    private Member member;

    public Member getMember() {
        if(member != null) return member;

        String actorUsername = getCookieValue("actorUsername", null);
        String actorPassword = getCookieValue("actorPassword", null);

        System.err.println(actorUsername);
        System.err.println(actorPassword);

//        String actorUsername = req.getParameter("actorUsername");
//        String actorPassword = req.getParameter("actorPassword");

        if (actorUsername == null || actorPassword == null) {
            String authorization = req.getHeader("Authorization");
            if (authorization != null) {
                authorization = authorization.substring("bearer ".length());
                String[] authorizationBits = authorization.split(" ", 2);
                actorUsername = authorizationBits[0];
                actorPassword = authorizationBits.length == 2 ? authorizationBits[1] : null;
            }
        }

//        if(actorUsername == null) actorUsername = req.getHeader("actorUsername");
//        if(actorPassword == null) actorPassword = req.getHeader("actorPassword");

        if(Ut.str.isBlank(actorUsername)) throw new GlobalException("401-1", "인증정보(아이디) 입력해줘");
        if(Ut.str.isBlank(actorPassword)) throw new GlobalException("401-2", "인증정보(비밀번호) 입력해줘");

        Member loginedMember = memberService.findByUsername(actorUsername).orElseThrow(() -> new GlobalException("403-3", "해당 회원은 없어"));
        if(!loginedMember.getPassword().equals(actorPassword)) throw new GlobalException("403-4", "비밀번호 틀림");

        member = loginedMember;

        return loginedMember;
    }

    private String getCookieValue(String cookieValue, String defaultValue) {
        if(req.getCookies() == null) return defaultValue;

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieValue))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(defaultValue);
    }

    public String getCurrentUrlPath() {
        return req.getRequestURI();
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }

    public void removeCookie(String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }

    public void setCooke(String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }
}