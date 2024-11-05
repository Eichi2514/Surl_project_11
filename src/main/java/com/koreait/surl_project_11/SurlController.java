package com.koreait.surl_project_11;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SurlController {

    private List<Surl> surls = new ArrayList<>();
    private long surlsLastId;

    @GetMapping("/add")
    @ResponseBody
    public Surl add(String body, String url) {
        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();

        return surl;
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public String add(@PathVariable String body, HttpServletRequest req) {
        String url = req.getRequestURI();

        System.out.println("url1 : "+ url);

        if(req.getQueryString() != null) {
            url += "?" + req.getQueryString();
        }

        System.out.println("url2 : "+ url);

        String[] urlBits = url.split("/",4);

        System.out.println("urlBits : "+ Arrays.toString(urlBits));

        url = urlBits[3];

        System.out.println("urlBits[3] : "+ urlBits[3]);

        return url;
    }
}
