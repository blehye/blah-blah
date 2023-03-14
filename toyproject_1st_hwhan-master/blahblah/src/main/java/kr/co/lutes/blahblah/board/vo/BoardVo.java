package kr.co.lutes.blahblah.board.vo;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import kr.co.lutes.blahblah.common.vo.AttachmentVo;
import kr.co.lutes.blahblah.member.vo.MemberVo;
import lombok.Data;

@Data
public class BoardVo {

    /*
     * collection : hwhan.board
     */

    private ObjectId id;
    private String no;
    private String category;
    private String title;
    private String content;
    private List<MultipartFile> image;
    private List<AttachmentVo> imageInfoList;
    private String originFileList;
    private MemberVo writer;
    private Date enrollDate;
    private Date modifyDate;
    private String deleteYn;
    private int hit;
    private String likeCnt;
    private String cmtCnt;
    
}