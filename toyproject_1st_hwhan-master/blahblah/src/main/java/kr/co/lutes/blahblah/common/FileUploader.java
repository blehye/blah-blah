package kr.co.lutes.blahblah.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.common.vo.AttachmentVo;


public class FileUploader {

    public static List<AttachmentVo> uploadFile(HttpServletRequest req, BoardVo vo) {
        System.out.println("파일업로더 입장");

        String rootPath = System.getProperty("user.dir");
        String fileDir = rootPath + "/blahblah/src/main/resources/static/upload/";

        List<MultipartFile> fileList = vo.getImage();
        String changeName = null;
        AttachmentVo aVo = new AttachmentVo();
        List<AttachmentVo> imageInfoList = new ArrayList<>();

        for(int i=0; i<fileList.size(); i++) {
            
            String originName = fileList.get(i).getOriginalFilename();
            
            String ext = originName.substring(originName.lastIndexOf("."), originName.length());
            // xxxxxxx.png (뒤에서부터 3자리. , 파일명 길이)
            
            changeName = "image_" + System.nanoTime() + ext; // profile_현재시간
            File target = new File(fileDir + changeName);
            
            try {
                fileList.get(i).transferTo(target);
                aVo.setOriginName(originName);
                aVo.setChangeName(changeName);
                aVo.setFilePath(fileDir + changeName);
                imageInfoList.add(aVo);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return imageInfoList;

	}

}
