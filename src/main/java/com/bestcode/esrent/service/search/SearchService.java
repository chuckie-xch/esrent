package com.bestcode.esrent.service.search;

/**
 * @author xch
 * @create 2018-07-30 22:39
 **/
public interface SearchService {

    /**
     * 索引目标房源
     * @param houseId
     */
    boolean index(Long houseId);

    /**
     * 删除索引房源
     * @param houseId
     */
    void remove(Long houseId);
}
