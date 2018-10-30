
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    @Autowired
    //自动注入Spring容器中的bean对象
    //在service代码中，不需要维护DAO内部的详细信息
    private BusDataDAO busDataDAO;

    public void queryBusData() throws Exception{
        List<BusData> list = this.busDataDAO.queryBusData();
        for(BusData busData : list){
            System.out.println(busData.toString());
        }
    }

}
