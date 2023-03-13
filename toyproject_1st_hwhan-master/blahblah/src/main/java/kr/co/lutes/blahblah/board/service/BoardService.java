package kr.co.lutes.blahblah.board.service;

import java.util.List;

import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;

public interface BoardService {

    public List<CategoryVo> getCategoryList();
    public SettingVo getBoardSetting();

    public int writeBoardOne(BoardVo vo);

    public List<BoardSelectVo> getBoardListByCategory(String category);
    BoardSelectVo getBoardOneById(String id);

    public int deleteBoardOneById(String id);

    public String getCategoryOneByKey(String key);
    
}