/*
 * UserObjectWapper.java created on 2010-7-28 上午08:48:35 by bwl (Liu Daoru)
 */

package cn.axy.xc.xcloginprovider.cn.weibo4j.model;

import java.io.Serializable;
import java.util.List;

/**
 * 对User对象列表进行的包装，以支持cursor相关信息传递
 * @author sinaWeibo
 */
public class UserWapper implements Serializable {

	private static final long serialVersionUID = -3119107701303920284L;

	/**
	 * 用户对象列表
	 */
	private List<Useraa> useraas;

	/**
	 * 向前翻页的cursor
	 */
	private long previousCursor;

	/**
	 * 向后翻页的cursor
	 */
	private long nextCursor;
	
	private long totalNumber;
	
	private String hasvisible;

	public UserWapper(List<Useraa> useraas, long previousCursor, long nextCursor, long totalNumber, String hasvisible) {
		this.useraas = useraas;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber=totalNumber;
		this.hasvisible = hasvisible;
	}

	public List<Useraa> getUseraas() {
		return useraas;
	}

	public void setUseraas(List<Useraa> useraas) {
		this.useraas = useraas;
	}

	public long getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(long previousCursor) {
		this.previousCursor = previousCursor;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getHasvisible() {
		return hasvisible;
	}

	public void setHasvisible(String hasvisible) {
		this.hasvisible = hasvisible;
	}

	@Override
	public String toString() {
		String str="";
		str += "UserWapper [useraas=[";
		for(Useraa u : useraas) {
			str += u.toString() + " ";
		}
		str += "], ";
		str += "previousCursor=" + previousCursor + ", ";
		str += "nextCursor=" + nextCursor + ", ";
		str += "totalNumber=" + totalNumber + ", ";
		str += "hasvisible=" + hasvisible + "]";
		return str;
	}
}
