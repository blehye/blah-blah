package kr.co.lutes.blahblah.member.dao;

import java.util.List;

import kr.co.lutes.blahblah.member.vo.MemberVo;

public interface MemberDao {

    public List<MemberVo> getMemberList();

    public int insertMemberOne(MemberVo vo);
    public MemberVo loginMember(MemberVo vo);

    public List<MemberVo> selectMemberByEmail(String email);
    public List<MemberVo> selectMemberByNick(String nick);
    
    public int updateEmailAuth(String email);
}
