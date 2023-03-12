package kr.co.lutes.blahblah.board.dao;

import java.util.List;

import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;

public interface BoardDao {

    public List<CategoryVo> getCategoryList();
    public SettingVo getBoardSetting();
    
    public int insertBoardOne(BoardVo vo);

    public List<BoardSelectVo> getBoardListByCategory(String category);
}
