package test_part;

/**
 * Created by multiangle on 2016/8/1.
 */

import com.mongodb.* ;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/*
 * 本例测试mongodb driver
 */
public class mongo_test {

    public static ArrayList<Document> getBsonText(){
        MongoClient mongoClient = new MongoClient("localhost") ;
        MongoDatabase db_fiction = mongoClient.getDatabase("fiction") ;
        MongoCollection hlm = db_fiction.getCollection("HongLouMeng") ;
        MongoCursor<Document> res = hlm.find().iterator() ;
        ArrayList<Document> ret = new ArrayList<Document>() ;
        while (res.hasNext()){
            ret.add(res.next()) ;
        }
        return ret ;
    }

    public static void main(String[] args){
        MongoClient mongoClient = new MongoClient("localhost") ;
        MongoDatabase db_fiction = mongoClient.getDatabase("fiction") ;
        MongoCollection hlm = db_fiction.getCollection("HongLouMeng") ;
        MongoCursor<Document> res = hlm.find().iterator() ;
        while(res.hasNext()){
            System.out.println(res.next().toJson());
        }
        res.close();
    }
}
