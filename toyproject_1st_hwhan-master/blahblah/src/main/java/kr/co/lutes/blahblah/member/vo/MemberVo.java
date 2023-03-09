package kr.co.lutes.blahblah.member.vo;

import java.util.Date;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVo {

    private ObjectId id;
    private String email;
    private String pwd;
    private String phone;
    private String nick;
    private Date enrollDate;
    private String quitYn;
    private String emailAuth;
    
}
