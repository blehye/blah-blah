package kr.co.lutes.blahblah.member.service;

import java.util.List;

import kr.co.lutes.blahblah.member.vo.MemberVo;

public interface MemberService {

    public List<MemberVo> getMemberList();

    public int join(MemberVo vo) throws Exception;
    public MemberVo login(MemberVo vo) throws Exception;

    public Boolean checkIdDup(String email);
    public Boolean checkNickDup(String nick);

    public int certificateEmail(String email);
    
}
