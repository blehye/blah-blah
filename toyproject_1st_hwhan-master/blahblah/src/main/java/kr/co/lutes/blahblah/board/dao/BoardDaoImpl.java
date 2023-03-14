package kr.co.lutes.blahblah.board.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;
import kr.co.lutes.blahblah.common.vo.AttachmentVo;

@Repository
public class BoardDaoImpl implements BoardDao{

    @Autowired
    private MongoDatabase mongoDB;

    private final String COLL_NAME_BOARD = "hwhan.board";
    private final String COLL_NAME_CATEGORY = "hwhan.board.category";
    private final String COLL_NAME_SETTING = "hwhan.board.setting";

    @Override
    public List<CategoryVo> getCategoryList() {
        
        MongoCollection<CategoryVo> coll = null;
        List<CategoryVo> li = new ArrayList<>();

        List<Bson> filter = new ArrayList<>();
        filter.add(Filters.eq("name", null));
        try {
            coll =  mongoDB.getCollection(COLL_NAME_CATEGORY, CategoryVo.class);
            coll.find().into(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
    }

    @Override
    public String getCategoryOneByKey(String key) {
        MongoCollection<CategoryVo> coll = null;

        CategoryVo vo = new CategoryVo();

        List<Bson> filter = new ArrayList<>();
        filter.add(Filters.eq("key", key));
        try {
            coll =  mongoDB.getCollection(COLL_NAME_CATEGORY, CategoryVo.class);
            vo = coll.find(Filters.and(filter)).first();
            System.out.println(vo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo.getName();
    }

    @Override
    public SettingVo getBoardSetting() {
        MongoCollection<SettingVo> coll = null;
        SettingVo settingVo = null;

        try {
            coll =  mongoDB.getCollection(COLL_NAME_SETTING, SettingVo.class);
            settingVo = coll.find().first();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return settingVo;
    }

    @Override
    public int insertBoardOne(BoardVo vo) {
        MongoCollection<BoardVo> coll = null;

        int result = 0;
        try {
            vo.setImage(null);
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardVo.class);
            coll.insertOne(vo);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BoardSelectVo> getBoardListByCategory(String category) {
        MongoCollection<BoardSelectVo> coll = null;
        List<BoardSelectVo> li = new ArrayList<>();

        List<Bson> filter = new ArrayList<>();
        filter.add(Filters.eq("category", category));
        filter.add(Filters.eq("deleteYn", "N"));


        try {
            System.out.println(category);
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            coll.find(Filters.and(filter)).into(li);
            System.out.println("dao");
            System.out.println(li);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
    }

    @Override
    public BoardSelectVo getBoardOneById(String id) {
        MongoCollection<BoardSelectVo> coll = null;
        BoardSelectVo board = new BoardSelectVo();

        System.out.println("게시글 한개 조회 dao");
        System.out.println(id);
        List<Bson> filter = new ArrayList<>();
        filter.add(Filters.eq("_id", new ObjectId(id)));

        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            board = coll.find(Filters.and(filter)).first();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public int updateHit(String id) {
        System.out.println("조회수 1 증가 dao");
    
        MongoCollection<BoardSelectVo> coll = null;
        Bson update = Updates.inc("hit", 1);
        int result = 0;
        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            coll.updateOne(Filters.eq("_id", new ObjectId(id)), update);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("조회수결과"+result);
        return result;
    }

    @Override
    public int deleteBoardOneById(String id) {
        System.out.println("게시글 삭제 dao");
    
        MongoCollection<BoardSelectVo> coll = null;
        Bson update = Updates.set("deleteYn", "Y");
        int result = 1;
        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            coll.updateOne(Filters.eq("_id", new ObjectId(id)), update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteOriginFile(List<AttachmentVo> remainFileList, String id) {

        System.out.println("첨부파일 삭제 dao");
        System.out.println(id);

        MongoCollection<BoardSelectVo> coll = null;
        Bson update = Updates.set("imageInfoList", remainFileList);

        int result = 0;

        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            coll.updateOne(Filters.eq("_id", new ObjectId(id)), update);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<AttachmentVo> getFileListById(String id) {
        MongoCollection<BoardSelectVo> coll = null;
        BoardSelectVo board = new BoardSelectVo();

        List<Bson> filter = new ArrayList<>();
        filter.add(Filters.eq("_id", new ObjectId(id)));

        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            board = coll.find(Filters.and(filter)).first();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return board.getImageInfoList();
    }

    @Override
    public int editBoardOne(BoardVo vo) {

        MongoCollection<BoardSelectVo> coll = null;

        LocalDateTime now = LocalDateTime.now();
        vo.setModifyDate(java.sql.Timestamp.valueOf(now));

        List<Bson> list = new ArrayList<>();
        list.add(Updates.set("category", vo.getCategory()));
        list.add(Updates.set("title", vo.getTitle()));
        list.add(Updates.set("content", vo.getContent()));
        list.add(Updates.set("imageInfoList", vo.getImageInfoList()));
        list.add(Updates.set("modifyDate", vo.getModifyDate()));
        Bson update = Updates.combine(list);

        int result = 0;

        try {
            coll =  mongoDB.getCollection(COLL_NAME_BOARD, BoardSelectVo.class);
            coll.updateOne(Filters.eq("_id", vo.getId()), update);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
}