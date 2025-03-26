package com.entity.view;

import com.entity.HetongEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 合同
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("hetong")
public class HetongView extends HetongEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 保险合同类型的值
		*/
		private String hetongValue;
		/**
		* 长短类型的值
		*/
		private String changduanValue;



		//级联表 kehu
			/**
			* 客户姓名
			*/
			private String kehuName;
			/**
			* 客户手机号
			*/
			private String kehuPhone;
			/**
			* 客户身份证号
			*/
			private String kehuIdNumber;
			/**
			* 客户照片
			*/
			private String kehuPhoto;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;

	public HetongView() {

	}

	public HetongView(HetongEntity hetongEntity) {
		try {
			BeanUtils.copyProperties(this, hetongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 保险合同类型的值
			*/
			public String getHetongValue() {
				return hetongValue;
			}
			/**
			* 设置： 保险合同类型的值
			*/
			public void setHetongValue(String hetongValue) {
				this.hetongValue = hetongValue;
			}
			/**
			* 获取： 长短类型的值
			*/
			public String getChangduanValue() {
				return changduanValue;
			}
			/**
			* 设置： 长短类型的值
			*/
			public void setChangduanValue(String changduanValue) {
				this.changduanValue = changduanValue;
			}














				//级联表的get和set kehu
					/**
					* 获取： 客户姓名
					*/
					public String getKehuName() {
						return kehuName;
					}
					/**
					* 设置： 客户姓名
					*/
					public void setKehuName(String kehuName) {
						this.kehuName = kehuName;
					}
					/**
					* 获取： 客户手机号
					*/
					public String getKehuPhone() {
						return kehuPhone;
					}
					/**
					* 设置： 客户手机号
					*/
					public void setKehuPhone(String kehuPhone) {
						this.kehuPhone = kehuPhone;
					}
					/**
					* 获取： 客户身份证号
					*/
					public String getKehuIdNumber() {
						return kehuIdNumber;
					}
					/**
					* 设置： 客户身份证号
					*/
					public void setKehuIdNumber(String kehuIdNumber) {
						this.kehuIdNumber = kehuIdNumber;
					}
					/**
					* 获取： 客户照片
					*/
					public String getKehuPhoto() {
						return kehuPhoto;
					}
					/**
					* 设置： 客户照片
					*/
					public void setKehuPhoto(String kehuPhoto) {
						this.kehuPhoto = kehuPhoto;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}





				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}




}
