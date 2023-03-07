package kr.co.lutes.blahblah.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import kr.co.lutes.blahblah.member.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao{

    @Autowired
    private MongoDatabase mongoDB;

    public List<MemberVo> getMemberList() {

        MongoCollection<MemberVo> coll = null;
        List<MemberVo> li = new ArrayList<>();

        try {
            coll =  mongoDB.getCollection("member", MemberVo.class);
            coll.find().into(li);
            System.out.println(li);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return li;
        
    }

    public int insertMemberOne(MemberVo vo) {
        MongoCollection<MemberVo> coll = null;
        int result = 0;
        try {
            coll =  mongoDB.getCollection("member", MemberVo.class);
            coll.insertOne(vo);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
}
