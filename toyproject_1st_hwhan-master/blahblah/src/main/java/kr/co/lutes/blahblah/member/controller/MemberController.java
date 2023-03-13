package kr.co.lutes.blahblah.member.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lutes.blahblah.member.service.MemberService;
import kr.co.lutes.blahblah.member.vo.MemberVo;
import kr.co.lutes.blahblah.member.vo.ResMsg;
import kr.co.lutes.blahblah.member.vo.ResMsg.Status;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResMsg join(@RequestBody MemberVo vo) throws Exception{
        System.out.println("회원가입 컨트롤러");
        ResMsg res = new ResMsg();

        int result = memberService.join(vo);

        if (result == 1) {
            res.setResult(Status.SUCCESS);
        } else {
            res.setResult(Status.ERROR);
        }
        
        return res;
    }

    @PostMapping("/checkIdDup")
    public ResMsg checkIdDup(@RequestBody MemberVo vo) {
        boolean result = memberService.checkIdDup(vo.getEmail());
        ResMsg res = new ResMsg();
        if(!result) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }
        return res;
    }

    @PostMapping("/checkNickDup")
    public ResMsg checkNickDup(@RequestBody MemberVo vo) {
        boolean result = memberService.checkNickDup(vo.getNick());
        ResMsg res = new ResMsg();
        if(!result) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }
        return res;
    }

    @PostMapping("/login")
    public ResMsg login(@RequestBody MemberVo vo, HttpSession session) {
        ResMsg res = new ResMsg();
        System.out.println("로그인 컨트롤러");
        try {
            MemberVo loginMember = memberService.login(vo);
            System.out.println(loginMember);
            
            if (loginMember != null) {
                session.setAttribute("loginMember", loginMember);
                res.setLoginMember(loginMember);
                res.setResult(Status.SUCCESS);
            } else {
                res.setResult(Status.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @PostMapping("/logout")
    public String login(HttpSession session) {
        session.invalidate();
        System.out.println("로그아웃 컨트롤러");

        return "";
    }

    @PostMapping("/emailAuth")
    public ResMsg login(@RequestBody String email) {
        ResMsg res = new ResMsg();
        System.out.println("인증 컨트롤러");
        
        String decEmail = "";
        
        try {
            decEmail = URLDecoder.decode(email, "utf-8");
            System.out.println(decEmail);
            int result = memberService.certificateEmail(decEmail.substring(0, decEmail.length() - 1));
            
            if (result == 1) {
                res.setResult(Status.SUCCESS);
            } else {
                res.setResult(Status.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
    
}