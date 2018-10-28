import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusDataDAO {

    @Autowired
    //自动装配所依赖的boneCPDataSource对象，并省去该对象内部属性的初始化步骤
    //如此一来，在DAO的代码中，不需要维护与数据库连接有关的信息
    private BoneCPDataSource boneCPDataSource;

    public List<BusData> queryBusData() throws Exception{
        List<BusData> list = new ArrayList<BusData>(600);

        Connection conn = boneCPDataSource.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "select * from BUSDATA";
        ResultSet res = stmt.executeQuery(sql);

        while(res.next()){
            BusData busData = new BusData();
            busData.setBusNumber(res.getInt("busnumber"));
            busData.setBusName(res.getString("busname"));
            busData.setBusLoad(res.getDouble("busload"));
            busData.setDeviceName(res.getString("devicename"));
            list.add(busData);
        }
        res.close();
        stmt.close();
        conn.close();

        return list;
    }
}
