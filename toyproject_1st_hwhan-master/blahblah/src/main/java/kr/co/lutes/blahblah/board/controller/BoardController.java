package kr.co.lutes.blahblah.board.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import kr.co.lutes.blahblah.member.vo.MemberVo;
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

    @PostMapping("/category/one/get")
    public String getCategoryOneByKey(String cate) {     
        String key = cate.substring(0, cate.length()-1); 
        String result = boardService.getCategoryOneByKey(key);
        return result;
    }

    @PostMapping("/setting/get")
    public SettingVo getBoardSetting() {      
        SettingVo settingVo = boardService.getBoardSetting();
        return settingVo;
    }

    @PostMapping("/list/get")
    public List<BoardSelectVo> getBoardListByCategory(@RequestBody String cate) {      
        System.out.println("카테고리로 게시글 리스트 조회 컨트롤러");
        String category = cate.substring(0, cate.length()-1);

        List<BoardSelectVo> boardList = boardService.getBoardListByCategory(category);

        // ObjectID -> String
        for(int i=0; i<boardList.size(); i++) {
            boardList.get(i).setNo(boardList.get(i).getId().toHexString());
        }

        return boardList;
    }

    @PostMapping("/one/get")
    public BoardSelectVo getBoardOneById(@RequestBody String id) {      
        System.out.println("게시글 한개 조회 컨트롤러");

        String no = id.substring(0, id.length()-1);
        BoardSelectVo board = boardService.getBoardOneById(no);
        System.out.println(board);
        return board;
    }

    // @PostMapping("/one/set")
    // public BoardSelectVo getBoardOneById(@RequestBody String id) {      
    //     System.out.println("게시글 한개 조회 컨트롤러");
    //     System.out.println(id);
    //     // BoardSelectVo board = boardService.getBoardOneById(id);

    //     return null;
    // }

    @PostMapping("/one/del")
    public ResMsg deleteBoardOneById(@RequestBody String id) {      
        ResMsg res = new ResMsg();
        System.out.println("게시글 한개 삭제 컨트롤러");

        String no = id.substring(0, id.length()-1);
        int result = boardService.deleteBoardOneById(no);

        if(result == 1) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }

        return res;
    }

    @PostMapping(value="/add", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResMsg writeBoardOne(HttpServletRequest req, BoardVo vo, HttpSession session) {
        System.out.println("게시글 등록 컨트롤러");

        //작성자 정보에 loginMember set하기 
        MemberVo writer = (MemberVo)session.getAttribute("loginMember");
        vo.setWriter(writer);

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

    @GetMapping("/download/{x}")
    public ResponseEntity<ByteArrayResource> down(@PathVariable String x){
        System.out.println("파일 다운로드 입장");
        
        File f = null;
        ByteArrayResource data = null;
        
        String rootPath = System.getProperty("user.dir");
        String fileDir = rootPath + "/frontend/src/assets/upload/";
        
        System.out.println(fileDir+x);
        try {
            f = new File(fileDir + x);
            byte[] fileData = FileUtils.readFileToByteArray(f);
            data = new ByteArrayResource(fileData);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(f.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image_537887404990999.jpg")
                .body(data)
                ;
    }

    
}