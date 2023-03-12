package kr.co.lutes.blahblah.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lutes.blahblah.board.dao.BoardDao;
import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;

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
    public SettingVo getBoardSetting() {
        return boardDao.getBoardSetting();
    }

    @Override
    public int writeBoardOne(BoardVo vo) {
        return boardDao.insertBoardOne(vo);
    }

    @Override
    public List<BoardSelectVo> getBoardListByCategory(String category) {
        return boardDao.getBoardListByCategory(category);
    }
    
}
