package kr.co.lutes.blahblah.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    
    @GetMapping("/test")
    public String test(Model model) {
        List<MemberVo> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);
        model.addAttribute("memberCnt", memberList.size());
        return "member/test";
    }

    @PostMapping("/join")
    public ResMsg join(@RequestBody MemberVo vo) throws Exception{
        System.out.println("컨트롤러");
        System.out.println(vo);
        ResMsg res = new ResMsg();

        int result = memberService.join(vo);

        if (result == 1) {
            res.setResult(Status.SUCCESS);
        } else {
            res.setResult(Status.ERROR);
        }
        
        return res;
    }
    
}
