package kr.co.lutes.blahblah.member.dao;

import java.util.List;

import kr.co.lutes.blahblah.member.vo.MemberVo;

public interface MemberDao {

    public List<MemberVo> getMemberList();

    public int insertMemberOne(MemberVo vo);
    
}
