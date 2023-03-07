package kr.co.lutes.blahblah.member.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResMsg {
    private String msg;
    private Status result;

    public enum Status {
        ERROR, SUCCESS
    }


}
