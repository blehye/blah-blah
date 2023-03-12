package kr.co.lutes.blahblah.board.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class BoardSelectVo {

    private String no;
    private String category;
    private String title;
    private String content;
    private List<Map<String, String>> imageInfoList;
    private String writer;
    private String enrollDate;
    private String modifyDate;
    private String deleteYn;
    private String hit;
    private String likeCnt;
    private String cmtCnt;
    
}
