package kr.co.lutes.blahblah.member.vo;

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
    
}
