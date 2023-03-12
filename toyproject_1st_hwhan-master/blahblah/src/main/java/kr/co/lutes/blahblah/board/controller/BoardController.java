package kr.co.lutes.blahblah.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lutes.blahblah.board.service.BoardService;
import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;
import kr.co.lutes.blahblah.common.FileUploader;
import kr.co.lutes.blahblah.common.vo.AttachmentVo;
import kr.co.lutes.blahblah.member.vo.ResMsg;
import kr.co.lutes.blahblah.member.vo.ResMsg.Status;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private ResMsg res = new ResMsg();

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/category/get")
    public List<CategoryVo> getCategoryList() {      
        List<CategoryVo> li = boardService.getCategoryList();
        return li;
    }

    @PostMapping("/setting/get")
    public SettingVo getBoardSetting() {      
        SettingVo settingVo = boardService.getBoardSetting();
        return settingVo;
    }

    @PostMapping("/list/get")
    public List<BoardSelectVo> getBoardListByCategory(@RequestBody String cate) {      
        System.out.println("게시글 조회 컨트롤러");
        String category = cate.substring(0, cate.length()-1);

        List<BoardSelectVo> boardList = boardService.getBoardListByCategory(category);

        return boardList;
    }

    @PostMapping(value="/add", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResMsg writeBoardOne(HttpServletRequest req, BoardVo vo) {
        System.out.println("게시글 등록 컨트롤러");
        System.out.println(vo.getImage().get(0).getOriginalFilename());

        //이미지 업로드
        List<AttachmentVo> imageInfoList = FileUploader.uploadFile(req, vo);
        vo.setImageInfoList(imageInfoList);

        //디비엔 이미지 경로만 저장    
        int result = boardService.writeBoardOne(vo);
        System.out.println("결과값" + result);
        if(result == 1) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }

        return res;
    }
    
}
