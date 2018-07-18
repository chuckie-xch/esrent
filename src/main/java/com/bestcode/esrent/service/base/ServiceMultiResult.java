package com.bestcode.esrent.service.base;

import java.util.List;

import lombok.Data;

/**
 * 通用多结果返回
 *
 * @author xch
 * @create 2018-07-18 22:26
 **/
@Data
public class ServiceMultiResult<T> {

    private long total;
    private List<T> result;

    public ServiceMultiResult() {
    }

    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public int getResultSize() {
        if (result == null) {
            return 0;
        }
        return result.size();
    }
}
