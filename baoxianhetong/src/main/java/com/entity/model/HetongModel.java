package com.entity.model;

import com.entity.HetongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 合同
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HetongModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 投保人
     */
    private Integer kehuId;


    /**
     * 合同唯一编号
     */
    private String hetongUuidNumber;


    /**
     * 保险合同类型
     */
    private Integer hetongTypes;


    /**
     * 长短类型
     */
    private Integer changduanTypes;


    /**
     * 合同文件
     */
    private String hetongFile;


    /**
     * 保费
     */
    private Double baofeiDouble;


    /**
     * 保额
     */
    private Double baoeDouble;


    /**
     * 受益人姓名
     */
    private String shouyirenName;


    /**
     * 受益人手机号
     */
    private String shouyirenPhone;


    /**
     * 受益人身份证号
     */
    private String shouyirenIdNumber;


    /**
     * 受益人地址
     */
    private String shouyirenAddress;


    /**
     * 合同介绍
     */
    private String hetongContent;


    /**
     * 执行日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhixingTime;


    /**
     * 到期日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date daoqiTime;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：投保人
	 */
    public Integer getKehuId() {
        return kehuId;
    }


    /**
	 * 设置：投保人
	 */
    public void setKehuId(Integer kehuId) {
        this.kehuId = kehuId;
    }
    /**
	 * 获取：合同唯一编号
	 */
    public String getHetongUuidNumber() {
        return hetongUuidNumber;
    }


    /**
	 * 设置：合同唯一编号
	 */
    public void setHetongUuidNumber(String hetongUuidNumber) {
        this.hetongUuidNumber = hetongUuidNumber;
    }
    /**
	 * 获取：保险合同类型
	 */
    public Integer getHetongTypes() {
        return hetongTypes;
    }


    /**
	 * 设置：保险合同类型
	 */
    public void setHetongTypes(Integer hetongTypes) {
        this.hetongTypes = hetongTypes;
    }
    /**
	 * 获取：长短类型
	 */
    public Integer getChangduanTypes() {
        return changduanTypes;
    }


    /**
	 * 设置：长短类型
	 */
    public void setChangduanTypes(Integer changduanTypes) {
        this.changduanTypes = changduanTypes;
    }
    /**
	 * 获取：合同文件
	 */
    public String getHetongFile() {
        return hetongFile;
    }


    /**
	 * 设置：合同文件
	 */
    public void setHetongFile(String hetongFile) {
        this.hetongFile = hetongFile;
    }
    /**
	 * 获取：保费
	 */
    public Double getBaofeiDouble() {
        return baofeiDouble;
    }


    /**
	 * 设置：保费
	 */
    public void setBaofeiDouble(Double baofeiDouble) {
        this.baofeiDouble = baofeiDouble;
    }
    /**
	 * 获取：保额
	 */
    public Double getBaoeDouble() {
        return baoeDouble;
    }


    /**
	 * 设置：保额
	 */
    public void setBaoeDouble(Double baoeDouble) {
        this.baoeDouble = baoeDouble;
    }
    /**
	 * 获取：受益人姓名
	 */
    public String getShouyirenName() {
        return shouyirenName;
    }


    /**
	 * 设置：受益人姓名
	 */
    public void setShouyirenName(String shouyirenName) {
        this.shouyirenName = shouyirenName;
    }
    /**
	 * 获取：受益人手机号
	 */
    public String getShouyirenPhone() {
        return shouyirenPhone;
    }


    /**
	 * 设置：受益人手机号
	 */
    public void setShouyirenPhone(String shouyirenPhone) {
        this.shouyirenPhone = shouyirenPhone;
    }
    /**
	 * 获取：受益人身份证号
	 */
    public String getShouyirenIdNumber() {
        return shouyirenIdNumber;
    }


    /**
	 * 设置：受益人身份证号
	 */
    public void setShouyirenIdNumber(String shouyirenIdNumber) {
        this.shouyirenIdNumber = shouyirenIdNumber;
    }
    /**
	 * 获取：受益人地址
	 */
    public String getShouyirenAddress() {
        return shouyirenAddress;
    }


    /**
	 * 设置：受益人地址
	 */
    public void setShouyirenAddress(String shouyirenAddress) {
        this.shouyirenAddress = shouyirenAddress;
    }
    /**
	 * 获取：合同介绍
	 */
    public String getHetongContent() {
        return hetongContent;
    }


    /**
	 * 设置：合同介绍
	 */
    public void setHetongContent(String hetongContent) {
        this.hetongContent = hetongContent;
    }
    /**
	 * 获取：执行日期
	 */
    public Date getZhixingTime() {
        return zhixingTime;
    }


    /**
	 * 设置：执行日期
	 */
    public void setZhixingTime(Date zhixingTime) {
        this.zhixingTime = zhixingTime;
    }
    /**
	 * 获取：到期日期
	 */
    public Date getDaoqiTime() {
        return daoqiTime;
    }


    /**
	 * 设置：到期日期
	 */
    public void setDaoqiTime(Date daoqiTime) {
        this.daoqiTime = daoqiTime;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
