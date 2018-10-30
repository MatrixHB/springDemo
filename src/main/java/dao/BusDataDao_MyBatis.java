package dao;

import entities.BusData;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BusDataDao_MyBatis {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public BusData queryBusData(){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            BusDataMapper busDataMapper = session.getMapper(BusDataMapper.class);
            BusData busData = busDataMapper.selectBusByNum(550);
            return busData;
        }finally {
            session.close();
        }
    }
}
