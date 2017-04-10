package com.kxt.kxtcjst.index.jsonBean;

import java.io.Serializable;
import java.util.List;

/** 
 * 视频实体类
 * @author  beginner 
 * @date 创建时间：2015年11月13日 下午2:30:13 
 * @version 1.0  
 */
public class VideoDetails implements Serializable{


	/**
	 * code : 200
	 * msg : 获取成功
	 * data : {"info":{"id":"462","title":"减产提振原油多头，看空弥漫全球金市","picture":"http://img.kuaixun360.com//Uploads/Picture/2016-12-07/5847b561cf8f0.jpg?x-oss-process=style/200-60","description":"","url":"http://media.kxt.com/video/cjgc/1207.mp4","publish_time":"1481094060","play_count":"15776","share_url":"http://m.kxt.com/videoArt/462","is_collect":"0"},"list":[{"id":"540","title":"回落布局原油 机不可失","picture":"http://img.kuaixun360.com//Uploads/Picture/2017-03-31/58ddf00c16331.jpg","url":"http://media.kxt.com/video/cjgc/20170331.mp4","publish_time":"1490939820","category_id":"139","play_count":"5956","description":""},{"id":"539","title":"MA均线（四）","picture":"http://img.kuaixun360.com//Uploads/Picture/2017-03-30/58dcc2f8f1438.jpg","url":"http://media.kxt.com/video/tzxt/52.mp4","publish_time":"1490862420","category_id":"135","play_count":"10534","description":""}],"moreFlag":true}
	 */

	private int status;
	private String msg;
	private DataBean data;

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

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * info : {"id":"462","title":"减产提振原油多头，看空弥漫全球金市","picture":"http://img.kuaixun360.com//Uploads/Picture/2016-12-07/5847b561cf8f0.jpg?x-oss-process=style/200-60","description":"","url":"http://media.kxt.com/video/cjgc/1207.mp4","publish_time":"1481094060","play_count":"15776","share_url":"http://m.kxt.com/videoArt/462","is_collect":"0"}
		 * list : [{"id":"540","title":"回落布局原油 机不可失","picture":"http://img.kuaixun360.com//Uploads/Picture/2017-03-31/58ddf00c16331.jpg","url":"http://media.kxt.com/video/cjgc/20170331.mp4","publish_time":"1490939820","category_id":"139","play_count":"5956","description":""},{"id":"539","title":"MA均线（四）","picture":"http://img.kuaixun360.com//Uploads/Picture/2017-03-30/58dcc2f8f1438.jpg","url":"http://media.kxt.com/video/tzxt/52.mp4","publish_time":"1490862420","category_id":"135","play_count":"10534","description":""}]
		 * moreFlag : true
		 */

		private InfoBean info;
		private boolean moreFlag;
		private List<ListBean> list;

		public InfoBean getInfo() {
			return info;
		}

		public void setInfo(InfoBean info) {
			this.info = info;
		}

		public boolean isMoreFlag() {
			return moreFlag;
		}

		public void setMoreFlag(boolean moreFlag) {
			this.moreFlag = moreFlag;
		}

		public List<ListBean> getList() {
			return list;
		}

		public void setList(List<ListBean> list) {
			this.list = list;
		}

		public static class InfoBean {
			/**
			 * id : 462
			 * title : 减产提振原油多头，看空弥漫全球金市
			 * picture : http://img.kuaixun360.com//Uploads/Picture/2016-12-07/5847b561cf8f0.jpg?x-oss-process=style/200-60
			 * description :
			 * url : http://media.kxt.com/video/cjgc/1207.mp4
			 * publish_time : 1481094060
			 * play_count : 15776
			 * share_url : http://m.kxt.com/videoArt/462
			 * is_collect : 0
			 */

			private String id;
			private String title;
			private String picture;
			private String description;
			private String url;
			private String publish_time;
			private String play_count;
			private String share_url;
			private String is_collect;

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

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getPublish_time() {
				return publish_time;
			}

			public void setPublish_time(String publish_time) {
				this.publish_time = publish_time;
			}

			public String getPlay_count() {
				return play_count;
			}

			public void setPlay_count(String play_count) {
				this.play_count = play_count;
			}

			public String getShare_url() {
				return share_url;
			}

			public void setShare_url(String share_url) {
				this.share_url = share_url;
			}

			public String getIs_collect() {
				return is_collect;
			}

			public void setIs_collect(String is_collect) {
				this.is_collect = is_collect;
			}
		}

		public static class ListBean {
			/**
			 * id : 540
			 * title : 回落布局原油 机不可失
			 * picture : http://img.kuaixun360.com//Uploads/Picture/2017-03-31/58ddf00c16331.jpg
			 * url : http://media.kxt.com/video/cjgc/20170331.mp4
			 * publish_time : 1490939820
			 * category_id : 139
			 * play_count : 5956
			 * description :
			 */

			private String id;
			private String title;
			private String picture;
			private String url;
			private String publish_time;
			private String category_id;
			private String play_count;
			private String description;
			private String cate_name;

			public String getCate_name() {
				return cate_name;
			}

			public void setCate_name(String cate_name) {
				this.cate_name = cate_name;
			}

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

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
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

			public String getPlay_count() {
				return play_count;
			}

			public void setPlay_count(String play_count) {
				this.play_count = play_count;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}
		}
	}
}
