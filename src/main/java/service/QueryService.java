package service;

import dao.BusDataDao_MyBatis;
import entities.BusData;
import dao.BusDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    @Autowired
    //自动注入Spring容器中的bean对象
    //在service代码中，不需要维护DAO内部的详细信息
//    private BusDataDAO busDataDAO;
    private BusDataDao_MyBatis busDataDao_myBatis;

    public void queryBusData() throws Exception{

        BusData busData = this.busDataDao_myBatis.queryBusData();

        System.out.println(busData.toString());
    }

}
