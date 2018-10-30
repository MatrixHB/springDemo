package dao;

import entities.BusData;

public interface BusDataMapper {
    public BusData selectBusByNum (Integer num);
}
