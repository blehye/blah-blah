package kr.co.lutes.blahblah.member.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResMsg {
    private String msg;
    private Status result;

    private MemberVo loginMember;

    public enum Status {
        ERROR, SUCCESS
    }


}
