package kr.co.lutes.blahblah.board.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.lutes.blahblah.common.vo.AttachmentVo;
import lombok.Data;

@Data
public class BoardVo {

    private String no;
    private String category;
    private String title;
    private String content;
    private List<MultipartFile> image;
    private List<AttachmentVo> imageInfoList;
    private String writer;
    private String enrollDate;
    private String modifyDate;
    private String deleteYn;
    private String hit;
    private String likeCnt;
    private String cmtCnt;
    
}
