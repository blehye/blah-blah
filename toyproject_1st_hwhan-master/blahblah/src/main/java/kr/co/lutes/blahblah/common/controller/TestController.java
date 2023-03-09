package kr.co.lutes.blahblah.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.lutes.blahblah.member.vo.MemberVo;

public class TestController {

    @PostMapping("/api/member/hello")
    @ResponseBody
    public String redirctRoot(@RequestBody MemberVo vo) {
        System.out.println("테스트컨트롤러");
        System.out.println(vo);
        return "testtest";
    }
    
}
