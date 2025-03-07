// Copyright 2021 Tencent Inc. All rights reserved.
//
// 商家转账对外API
//
// * 场景及业务流程：     商户可通过该产品实现同时向多个用户微信零钱进行转账的操作，可用于发放奖金补贴、佣金货款结算、员工报销等场景。
// [https://pay.weixin.qq.com/index.php/public/product/detail?pid=108&productType=0](https://pay.weixin.qq.com/index.php/public/product/detail?pid=108&productType=0) * 接入步骤：     * 商户在微信支付商户平台开通“批量转账到零钱”产品权限，并勾选“使用API方式发起转账”。     * 调用批量转账接口，对多个用户微信零钱发起转账。     * 调用查询批次接口，可获取到转账批次详情及当前状态。     * 调用查询明细接口，可获取到单条转账明细详情及当前状态。
//
// API version: 1.0.3

// Code generated by WechatPay APIv3 Generator based on [OpenAPI
// Generator](https://openapi-generator.tech); DO NOT EDIT.

package com.wechat.pay.java.service.transferbatch.model;

import static com.wechat.pay.java.core.util.StringUtil.toIndentedString;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** GetTransferDetailByOutNoRequest */
public class GetTransferDetailByOutNoRequest {
  /** 商家明细单号 说明：商户系统内部区分转账批次单下不同转账明细单的唯一标识 */
  @SerializedName("out_detail_no")
  @Expose(serialize = false)
  private String outDetailNo;
  /** 商家批次单号 说明：商户系统内部的商家批次单号，在商户系统内部唯一 */
  @SerializedName("out_batch_no")
  @Expose(serialize = false)
  private String outBatchNo;

  public String getOutDetailNo() {
    return outDetailNo;
  }

  public void setOutDetailNo(String outDetailNo) {
    this.outDetailNo = outDetailNo;
  }

  public String getOutBatchNo() {
    return outBatchNo;
  }

  public void setOutBatchNo(String outBatchNo) {
    this.outBatchNo = outBatchNo;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetTransferDetailByOutNoRequest {\n");
    sb.append("    outDetailNo: ").append(toIndentedString(outDetailNo)).append("\n");
    sb.append("    outBatchNo: ").append(toIndentedString(outBatchNo)).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
