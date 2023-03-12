package kr.co.lutes.blahblah.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import kr.co.lutes.blahblah.board.vo.BoardSelectVo;
import kr.co.lutes.blahblah.board.vo.BoardVo;
import kr.co.lutes.blahblah.board.vo.CategoryVo;
import kr.co.lutes.blahblah.board.vo.SettingVo;

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
    
}
