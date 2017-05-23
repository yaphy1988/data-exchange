package com.ai.bdex.dataexchange.busi.aip.entity;

import com.ai.bdex.dataexchange.common.dto.BaseResponseDTO;

import java.util.Date;

/**
 * Created by yx on 2017/5/23.
 */
public class AipProviderInfoVO extends BaseResponseDTO {
    private String providerId;

    private String providerName;

    private String status;

    private Date createTime;

    private String createStaff;

    private Date updateTime;

    private String updateStaff;
}
