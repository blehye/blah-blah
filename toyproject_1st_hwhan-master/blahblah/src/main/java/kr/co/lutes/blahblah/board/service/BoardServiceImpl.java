package kr.co.lutes.blahblah.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lutes.blahblah.board.dao.BoardDao;
import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;
import kr.co.lutes.blahblah.common.vo.AttachmentVo;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<CategoryVo> getCategoryList() {
        return boardDao.getCategoryList();
    }

    @Override
    public String getCategoryOneByKey(String key) {
        return boardDao.getCategoryOneByKey(key);
    }

    @Override
    public SettingVo getBoardSetting() {
        return boardDao.getBoardSetting();
    }

    @Override
    public int writeBoardOne(BoardVo vo) {

        // 조회수, 좋아요수, 댓글수 0으로 초기화
        vo.setHit(0);
        vo.setLikeCnt("0");
        vo.setCmtCnt("0");

        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();
        vo.setEnrollDate(java.sql.Timestamp.valueOf(now));
        vo.setModifyDate(java.sql.Timestamp.valueOf(now));

        // deleteYn = "N" 초기화
        vo.setDeleteYn("N");

        return boardDao.insertBoardOne(vo);
    }

    @Override
    public List<BoardSelectVo> getBoardListByCategory(String category) {
        return boardDao.getBoardListByCategory(category);
    }

    @Override
    public BoardSelectVo getBoardOneById(String id) {

        int result1 = boardDao.updateHit(id);
        
        BoardSelectVo result2 = null;

        if(result1 == 1) {
            result2 = boardDao.getBoardOneById(id);
            result2.setCategoryName(boardDao.getCategoryOneByKey(result2.getCategory())); 
        }

        return result2;
    }

    @Override
    public int deleteBoardOneById(String id) {
        return boardDao.deleteBoardOneById(id);
    }

    @Override
    public List<AttachmentVo> updateOriginFile(List<AttachmentVo> remainFileList, String id) {

        System.out.println("게시글 수정 서비스");
        // 리스트에 없는 파일 DB에서 삭제
        int result1 = boardDao.deleteOriginFile(remainFileList, id);

        List<AttachmentVo> result2 = null;

        // DB에서 파일 select
        if(result1 == 1) {
            result2 = boardDao.getFileListById(id);
        }

        return result2;

    }

    @Override
    public int editBoardOne(BoardVo vo) {
        return boardDao.editBoardOne(vo);
    }
    
}