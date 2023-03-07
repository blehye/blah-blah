package kr.co.lutes.blahblah.member.service;

import java.util.List;

import kr.co.lutes.blahblah.member.vo.MemberVo;

public interface MemberService {

    public List<MemberVo> getMemberList();

    public int join(MemberVo vo) throws Exception;
    
}
