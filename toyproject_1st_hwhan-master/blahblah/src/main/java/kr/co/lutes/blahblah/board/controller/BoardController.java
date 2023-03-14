package kr.co.lutes.blahblah.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.lutes.blahblah.board.service.BoardService;
import kr.co.lutes.blahblah.board.vo.BoardJsonVo;
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
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @Autowired
    private ResMsg res = new ResMsg();

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/api/board/category/get")
    public List<CategoryVo> getCategoryList() {      
        List<CategoryVo> li = boardService.getCategoryList();
        return li;
    }

    @PostMapping("/api/board/category/one/get")
    public String getCategoryOneByKey(String cate) {     
        String key = cate.substring(0, cate.length()-1); 
        String result = boardService.getCategoryOneByKey(key);
        return result;
    }

    @PostMapping("/api/board/setting/get")
    public SettingVo getBoardSetting() {      
        SettingVo settingVo = boardService.getBoardSetting();
        return settingVo;
    }

    @PostMapping("/api/board/list/get")
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

    @PostMapping("/api/board/one/get")
    public BoardSelectVo getBoardOneById(@RequestBody String id) {      
        System.out.println("게시글 한개 조회 컨트롤러");

        String no = id.substring(0, id.length()-1);
        BoardSelectVo board = boardService.getBoardOneById(no);
        System.out.println(board);
        return board;
    }



    @PostMapping("/api/board/one/del")
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

    @PostMapping(value="/api/board/add", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResMsg writeBoardOne(HttpServletRequest req, BoardVo vo, HttpSession session) {
        System.out.println("게시글 등록 컨트롤러");

        //작성자 정보에 loginMember set하기 
        MemberVo writer = (MemberVo)session.getAttribute("loginMember");
        vo.setWriter(writer);

        //이미지 업로드
        List<AttachmentVo> imageInfoList = FileUploader.uploadFile(req, vo);
        //디비엔 이미지 경로만 저장    
        vo.setImageInfoList(imageInfoList);

        int result = boardService.writeBoardOne(vo);
        System.out.println("결과값" + result);
        if(result == 1) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }

        return res;
    }

    @PostMapping(value="/api/board/json/add", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResMsg writeBoardOneByFile(@RequestBody String str) {
        System.out.println("게시글 json으로 등록 컨트롤러");

        System.out.println(str);

        Gson gson = new Gson();
        BoardJsonVo vo = gson.fromJson(str, BoardJsonVo.class);
        System.out.println(vo);

        // int result = boardService.writeBoardOne(vo);
        
        // if(result == 1) {
        //     res.setResult(Status.SUCCESS);
        // }else {
        //     res.setResult(Status.ERROR);
        // }

        return null;
    }

    @PostMapping(value="/api/board/set", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResMsg editBoardOneById(HttpServletRequest req, BoardVo vo, HttpSession session) {      
        System.out.println("게시글 수정 컨트롤러");

        //DB에서 imageInfoList를 불러온다
        //originFileList에 없는 파일들은 delete한다
        //추가적으로 업로드하는 imageInfoList에 합친다.
        //imageInfoList update한다.

        Gson gson = new Gson();

        AttachmentVo[] array = gson.fromJson(vo.getOriginFileList(), AttachmentVo[].class);
        List<AttachmentVo> filelist = Arrays.asList(array);

        List<AttachmentVo> joinedFileList = new ArrayList<>();

        //구첨부파일 수정
        List<AttachmentVo> originFileList = boardService.updateOriginFile(filelist, vo.getId().toHexString());
        joinedFileList.addAll(originFileList);


        if(vo.getImage() != null) {
            //파일 업로드
            List<AttachmentVo> newFileList = FileUploader.uploadFile(req, vo);
            joinedFileList.addAll(newFileList);
        }

        //구파일 신파일 합치기

        //디비엔 파일 경로만 저장    
        vo.setImageInfoList(joinedFileList);

        int result = boardService.editBoardOne(vo);

        if(result == 1) {
            res.setResult(Status.SUCCESS);
        }else {
            res.setResult(Status.ERROR);
        }

        return res;
    }

    // public List<AttachmentVo> updateOriginFile(List<AttachmentVo> remainFileList, String id) {
    //     //DB에서 imageInfoList를 불러온다
    //     //originFileList에 없는 파일들은 delete한다
    //     System.out.println(remainFileList);

    //     return boardService.updateOriginFile(remainFileList);

    // }

    @PostMapping("/api/b/download/{x}/{y}")
    public ResponseEntity<ByteArrayResource> down(@PathVariable String x,@PathVariable String y) throws Exception{
        System.out.println("파일 다운로드 입장");
        
        String rootPath = System.getProperty("user.dir");
        //String fileDir = rootPath + "/frontend/src/assets/upload/";
        String fileDir = rootPath + "/blahblah/src/main/resources/static/upload/";
        // String fileDir = "C:/dev/";
        
        System.out.println(fileDir+x);
        File f = new File(fileDir + x);
        byte[] fileData = FileUtils.readFileToByteArray(f);
        ByteArrayResource data = new ByteArrayResource(fileData);
            
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(f.length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+y)
                .body(data)
                ;
    }
    
}