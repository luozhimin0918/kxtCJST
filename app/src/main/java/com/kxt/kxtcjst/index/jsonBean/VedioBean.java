package com.kxt.kxtcjst.index.jsonBean;

import java.io.Serializable;
import java.util.List;

/** 
 * 视频实体类
 * @author  beginner 
 * @date 创建时间：2015年11月13日 下午2:30:13 
 * @version 1.0  
 */
public class VedioBean implements Serializable{


	/**
	 * code : 200
	 * msg : ok
	 * data : [{"id":"471","title":"众鼎财富 加息落地 金银影响几何","picture":"http://img.kuaixun360.com/Uploads/Picture/2016-12-15/58523c5a38db8.jpg","istoutiao":"0","play_count":"15493","publish_time":"1481785500","category_id":"141","url":"http://appapi.kxt.com/video/view/id/471"},{"id":"469","title":"华尔街连线 如何赢在EIA","picture":"http://img.kuaixun360.com/Uploads/Picture/2016-12-14/5851000079533.jpg","istoutiao":"1","play_count":"11241","publish_time":"1481704200","category_id":"141","url":"http://appapi.kxt.com/video/view/id/469"}]
	 */

	private int code;
	private String msg;
	private List<DataBean> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
		 * id : 471
		 * title : 众鼎财富 加息落地 金银影响几何
		 * picture : http://img.kuaixun360.com/Uploads/Picture/2016-12-15/58523c5a38db8.jpg
		 * istoutiao : 0
		 * play_count : 15493
		 * publish_time : 1481785500
		 * category_id : 141
		 * url : http://appapi.kxt.com/video/view/id/471
		 */

		private String id;
		private String title;
		private String picture;
		private String istoutiao;
		private String play_count;
		private String publish_time;
		private String category_id;
		private String url;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public String getIstoutiao() {
			return istoutiao;
		}

		public void setIstoutiao(String istoutiao) {
			this.istoutiao = istoutiao;
		}

		public String getPlay_count() {
			return play_count;
		}

		public void setPlay_count(String play_count) {
			this.play_count = play_count;
		}

		public String getPublish_time() {
			return publish_time;
		}

		public void setPublish_time(String publish_time) {
			this.publish_time = publish_time;
		}

		public String getCategory_id() {
			return category_id;
		}

		public void setCategory_id(String category_id) {
			this.category_id = category_id;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
