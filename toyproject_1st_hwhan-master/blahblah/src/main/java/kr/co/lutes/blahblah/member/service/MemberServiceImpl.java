package kr.co.lutes.blahblah.member.service;

import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.co.lutes.blahblah.common.SHA256;
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
    public int join(MemberVo vo) throws Exception{

        // Random random = new Random();
        // int craeteNum = 0;
        // String ranNom = "";
        // int letter = 6;
        // String resultNum = "";

        // for(int i=0; i<letter; i++) {
        //     createNum = random.nextInt(9);
        //     ranNum = Integer.toString(createNum);
        //     resultNum += random;
        // }

        MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[BlahBlah]회원가입 인증 메일입니다.");
		sendMail.setText(
				"<h2>BlahBlah 메일 인증</h2>"
				+"<br>BlahBlah에 가입 하신걸 환영합니다."
				+"<br>아래의 '회원가입'을 눌러주세요"
				+"<br><a href='http://localhost:3000/emailAuth/1/" + vo.getEmail()+"/'>회원가입</a>"
				);
		sendMail.setFrom("lutesBlahBlah@gmail.com", "BlahBlah");
		sendMail.setTo(vo.getEmail());
		sendMail.send();

        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();
 
        // 포맷팅
        // String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        SHA256 sha256 = new SHA256();

        vo.setPwd(sha256.encrypt(vo.getPwd()));
        vo.setQuitYn("N");
        vo.setEmailAuth("0");
        vo.setEnrollDate( java.sql.Timestamp.valueOf(now));
        return memberDao.insertMemberOne(vo);
    }

    @Override
    public Boolean checkIdDup(String email) {
        List<MemberVo> li = memberDao.selectMemberByEmail(email);

        if(li.size() == 0) {
            return false; //아이디 중복 x
        }
        return true; //아이디 중복 o
    }
    
    @Override
    public Boolean checkNickDup(String nick) {
        List<MemberVo> li = memberDao.selectMemberByNick(nick);

        if(li.size() == 0) {
            return false; //닉네임 중복 x
        }
        return true; //닉네임 중복 o
    }

    @Override
    public MemberVo login(MemberVo vo) throws Exception{
        SHA256 sha256 = new SHA256();
        vo.setPwd(sha256.encrypt(vo.getPwd()));

        MemberVo loginMember = memberDao.loginMember(vo);
        return loginMember;
     
    }

    @Override
    public int certificateEmail(String email) {

        return memberDao.updateEmailAuth(email);

    }

    
    
}
