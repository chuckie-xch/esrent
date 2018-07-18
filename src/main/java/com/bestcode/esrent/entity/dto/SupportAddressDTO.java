package com.bestcode.esrent.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xch
 * @create 2018-07-18 22:11
 **/
@Data
public class SupportAddressDTO {

    private Long id;
    @JsonProperty(value = "belong_to")
    private String belongTo;
    @JsonProperty(value = "en_name")
    private String enName;
    @JsonProperty(value = "cn_name")
    private String cnName;

    private String level;

}
