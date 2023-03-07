package kr.co.lutes.blahblah.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lutes.blahblah.member.dao.MemberDao;
import kr.co.lutes.blahblah.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public List<MemberVo> getMemberList() {

        return memberDao.getMemberList();

    }

    public int join(MemberVo vo) {
        return memberDao.insertMemberOne(vo);
    }
    
}
