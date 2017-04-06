package com.kxt.kxtcjst.index.jsonBean;

import java.io.Serializable;
import java.util.List;

/** 
 * 视频
 * 栏目
 * @author  beginner 
 * @date 创建时间：2015年11月13日 下午2:30:13 
 * @version 1.0  
 */
public class VedioTitleBean implements Serializable{


	/**
	 * status : 200
	 * msg : ok
	 * data : [{"id":"","cat_name":"推荐"},{"id":"139","cat_name":"财经观察"},{"id":"135","cat_name":"投资学堂"},{"id":"141","cat_name":"财经汇客厅"},{"id":"134","cat_name":"特别节目"},{"id":"132","cat_name":"财经数据show"},{"id":"130","cat_name":"财经译点通"}]
	 */

	private int status;
	private String msg;
	private String aud;

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	private List<DataBean> data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id :
		 * cat_name : 推荐
		 */

		private String id;
		private String cat_name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCat_name() {
			return cat_name;
		}

		public void setCat_name(String cat_name) {
			this.cat_name = cat_name;
		}
	}
}
