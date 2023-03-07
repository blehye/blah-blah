package kr.co.lutes.blahblah.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.co.lutes.blahblah.member.dao.MemberDao;
import kr.co.lutes.blahblah.member.mail.MailHandler;
import kr.co.lutes.blahblah.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{
    	
	@Autowired
	private JavaMailSender mailSender;

    @Autowired
    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public List<MemberVo> getMemberList() {

        return memberDao.getMemberList();

    }

    @Override
    public int join(MemberVo vo) throws Exception {
        MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[BlahBlah]회원가입 인증 메일입니다.");
		sendMail.setText(
				"<h1>BlahBlah 메일 인증</h1>"
				+"<br>BlahBlah에 가입 하신걸 환영합니다."
				+"<br>아래의 링크를 눌러주세요"
				+"<br>localhost:3000/member/emailAuth?email="+vo.getId()
				);
		sendMail.setFrom("lutesBlahBlah@gmail.com", "BlahBlah");
		sendMail.setTo(vo.getEmail());
		sendMail.send();
        return memberDao.insertMemberOne(vo);
    }
    
}
