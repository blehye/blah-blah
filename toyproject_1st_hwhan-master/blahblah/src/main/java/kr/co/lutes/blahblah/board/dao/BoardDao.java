package kr.co.lutes.blahblah.board.dao;

import java.util.List;

import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;
import kr.co.lutes.blahblah.common.vo.AttachmentVo;

public interface BoardDao {

    public List<CategoryVo> getCategoryList();
    public SettingVo getBoardSetting();
    
    public int insertBoardOne(BoardVo vo);

    public List<BoardSelectVo> getBoardListByCategory(String category);

    public BoardSelectVo getBoardOneById(String id);

    public int deleteBoardOneById(String id);

    public int updateHit(String id);

    public String getCategoryOneByKey(String key);

    public int deleteOriginFile(List<AttachmentVo> remainFileList, String id);

    public List<AttachmentVo> getFileListById(String id);

    public int editBoardOne(BoardVo vo);
}