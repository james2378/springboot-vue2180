package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 合同
 *
 * @author 
 * @email
 */
@TableName("hetong")
public class HetongEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HetongEntity() {

	}

	public HetongEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 投保人
     */
    @TableField(value = "kehu_id")

    private Integer kehuId;


    /**
     * 合同唯一编号
     */
    @TableField(value = "hetong_uuid_number")

    private String hetongUuidNumber;


    /**
     * 保险合同类型
     */
    @TableField(value = "hetong_types")

    private Integer hetongTypes;


    /**
     * 长短类型
     */
    @TableField(value = "changduan_types")

    private Integer changduanTypes;


    /**
     * 合同文件
     */
    @TableField(value = "hetong_file")

    private String hetongFile;


    /**
     * 保费
     */
    @TableField(value = "baofei_double")

    private Double baofeiDouble;


    /**
     * 保额
     */
    @TableField(value = "baoe_double")

    private Double baoeDouble;


    /**
     * 受益人姓名
     */
    @TableField(value = "shouyiren_name")

    private String shouyirenName;


    /**
     * 受益人手机号
     */
    @TableField(value = "shouyiren_phone")

    private String shouyirenPhone;


    /**
     * 受益人身份证号
     */
    @TableField(value = "shouyiren_id_number")

    private String shouyirenIdNumber;


    /**
     * 受益人地址
     */
    @TableField(value = "shouyiren_address")

    private String shouyirenAddress;


    /**
     * 合同介绍
     */
    @TableField(value = "hetong_content")

    private String hetongContent;


    /**
     * 执行日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "zhixing_time")

    private Date zhixingTime;


    /**
     * 到期日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "daoqi_time")

    private Date daoqiTime;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：投保人
	 */
    public Integer getKehuId() {
        return kehuId;
    }


    /**
	 * 获取：投保人
	 */

    public void setKehuId(Integer kehuId) {
        this.kehuId = kehuId;
    }
    /**
	 * 设置：合同唯一编号
	 */
    public String getHetongUuidNumber() {
        return hetongUuidNumber;
    }


    /**
	 * 获取：合同唯一编号
	 */

    public void setHetongUuidNumber(String hetongUuidNumber) {
        this.hetongUuidNumber = hetongUuidNumber;
    }
    /**
	 * 设置：保险合同类型
	 */
    public Integer getHetongTypes() {
        return hetongTypes;
    }


    /**
	 * 获取：保险合同类型
	 */

    public void setHetongTypes(Integer hetongTypes) {
        this.hetongTypes = hetongTypes;
    }
    /**
	 * 设置：长短类型
	 */
    public Integer getChangduanTypes() {
        return changduanTypes;
    }


    /**
	 * 获取：长短类型
	 */

    public void setChangduanTypes(Integer changduanTypes) {
        this.changduanTypes = changduanTypes;
    }
    /**
	 * 设置：合同文件
	 */
    public String getHetongFile() {
        return hetongFile;
    }


    /**
	 * 获取：合同文件
	 */

    public void setHetongFile(String hetongFile) {
        this.hetongFile = hetongFile;
    }
    /**
	 * 设置：保费
	 */
    public Double getBaofeiDouble() {
        return baofeiDouble;
    }


    /**
	 * 获取：保费
	 */

    public void setBaofeiDouble(Double baofeiDouble) {
        this.baofeiDouble = baofeiDouble;
    }
    /**
	 * 设置：保额
	 */
    public Double getBaoeDouble() {
        return baoeDouble;
    }


    /**
	 * 获取：保额
	 */

    public void setBaoeDouble(Double baoeDouble) {
        this.baoeDouble = baoeDouble;
    }
    /**
	 * 设置：受益人姓名
	 */
    public String getShouyirenName() {
        return shouyirenName;
    }


    /**
	 * 获取：受益人姓名
	 */

    public void setShouyirenName(String shouyirenName) {
        this.shouyirenName = shouyirenName;
    }
    /**
	 * 设置：受益人手机号
	 */
    public String getShouyirenPhone() {
        return shouyirenPhone;
    }


    /**
	 * 获取：受益人手机号
	 */

    public void setShouyirenPhone(String shouyirenPhone) {
        this.shouyirenPhone = shouyirenPhone;
    }
    /**
	 * 设置：受益人身份证号
	 */
    public String getShouyirenIdNumber() {
        return shouyirenIdNumber;
    }


    /**
	 * 获取：受益人身份证号
	 */

    public void setShouyirenIdNumber(String shouyirenIdNumber) {
        this.shouyirenIdNumber = shouyirenIdNumber;
    }
    /**
	 * 设置：受益人地址
	 */
    public String getShouyirenAddress() {
        return shouyirenAddress;
    }


    /**
	 * 获取：受益人地址
	 */

    public void setShouyirenAddress(String shouyirenAddress) {
        this.shouyirenAddress = shouyirenAddress;
    }
    /**
	 * 设置：合同介绍
	 */
    public String getHetongContent() {
        return hetongContent;
    }


    /**
	 * 获取：合同介绍
	 */

    public void setHetongContent(String hetongContent) {
        this.hetongContent = hetongContent;
    }
    /**
	 * 设置：执行日期
	 */
    public Date getZhixingTime() {
        return zhixingTime;
    }


    /**
	 * 获取：执行日期
	 */

    public void setZhixingTime(Date zhixingTime) {
        this.zhixingTime = zhixingTime;
    }
    /**
	 * 设置：到期日期
	 */
    public Date getDaoqiTime() {
        return daoqiTime;
    }


    /**
	 * 获取：到期日期
	 */

    public void setDaoqiTime(Date daoqiTime) {
        this.daoqiTime = daoqiTime;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Hetong{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", kehuId=" + kehuId +
            ", hetongUuidNumber=" + hetongUuidNumber +
            ", hetongTypes=" + hetongTypes +
            ", changduanTypes=" + changduanTypes +
            ", hetongFile=" + hetongFile +
            ", baofeiDouble=" + baofeiDouble +
            ", baoeDouble=" + baoeDouble +
            ", shouyirenName=" + shouyirenName +
            ", shouyirenPhone=" + shouyirenPhone +
            ", shouyirenIdNumber=" + shouyirenIdNumber +
            ", shouyirenAddress=" + shouyirenAddress +
            ", hetongContent=" + hetongContent +
            ", zhixingTime=" + zhixingTime +
            ", daoqiTime=" + daoqiTime +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
