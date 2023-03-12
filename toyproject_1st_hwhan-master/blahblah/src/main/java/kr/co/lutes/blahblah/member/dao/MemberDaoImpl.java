package kr.co.lutes.blahblah.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import kr.co.lutes.blahblah.member.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    private MongoDatabase mongoDB;

    private final String COLL_NAME_MEMBER = "hwhan.member";

    public List<MemberVo> getMemberList() {

        MongoCollection<MemberVo> coll = null;
        List<MemberVo> li = new ArrayList<>();

        try {
            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            coll.find().into(li);
            System.out.println(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
        
    }

    @Override
    public int insertMemberOne(MemberVo vo) {
        MongoCollection<MemberVo> coll = null;
        int result = 0;
        try {
            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            coll.insertOne(vo);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<MemberVo> selectMemberByEmail(String email) {
        MongoCollection<MemberVo> coll = null;
        List<MemberVo> li = new ArrayList<>();

        try {
            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            coll.find(Filters.eq("email", email)).into(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
    }
    @Override
    public List<MemberVo> selectMemberByNick(String nick) {
        MongoCollection<MemberVo> coll = null;
        List<MemberVo> li = new ArrayList<>();

        try {
            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            coll.find(Filters.eq("nick", nick)).into(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
    }

    @Override
    public MemberVo loginMember(MemberVo vo) {
        MongoCollection<MemberVo> coll = null;
        MemberVo loginMember = new MemberVo();

        List<Bson> filter = new ArrayList<>();

        try {

            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            filter.add(Filters.eq("email", vo.getEmail()));
            filter.add(Filters.eq("pwd", vo.getPwd()));
            filter.add(Filters.eq("quitYn", "N"));
            filter.add(Filters.eq("emailAuth", "1"));
            
          
            loginMember = coll.find(  Filters.and(filter)).first();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginMember;
    }

    @Override
    public int updateEmailAuth(String email) {
        MongoCollection<MemberVo> coll = null;
        Bson update = Updates.set("emailAuth", "1");
        int result = 1;
        try {
            coll =  mongoDB.getCollection(COLL_NAME_MEMBER, MemberVo.class);
            coll.updateOne(Filters.eq("email", email), update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
}
