package cn.axy.xc.xcloginconsumer.weibo4j;

import cn.axy.xc.xcloginconsumer.weibo4j.model.PostParameter;
import cn.axy.xc.xcloginconsumer.weibo4j.model.UserCounts;
import cn.axy.xc.xcloginconsumer.weibo4j.model.Useraa;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginconsumer.weibo4j.util.WeiboConfig;

import java.util.List;


public class Users extends Weibo {

	private static final long serialVersionUID = 4742830953302255953L;

	public Users(String access_token) {
		this.access_token = access_token;
	}

	/*----------------------------用户接口----------------------------------------*/
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return Useraa
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/show
	 * @since JDK 1.5
	 */
	public Useraa showUserById(String uid) throws WeiboException {
		return new Useraa(client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("uid", uid) },
				access_token).asJSONObject());
	}

	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param screen_name
	 *            用户昵称
	 * @return Useraa
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/show
	 * @since JDK 1.5
	 */
	public Useraa showUserByScreenName(String screen_name) throws WeiboException {
		return new Useraa(client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }, access_token).asJSONObject());
	}

	/**
	 * 通过个性化域名获取用户资料以及用户最新的一条微博
	 * 
	 * @param domain
	 *            需要查询的个性化域名。
	 * @return Useraa
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/domain_show
	 * @since JDK 1.5
	 */
	public Useraa showUserByDomain(String domain) throws WeiboException {
		return new Useraa(client.get(
				WeiboConfig.getValue("baseURL") + "users/domain_show.json",
				new PostParameter[] { new PostParameter("domain", domain) },
				access_token).asJSONObject());
	}

	/**
	 * 批量获取用户的粉丝数、关注数、微博数
	 * 
	 * @param uids
	 *            需要获取数据的用户UID，多个之间用逗号分隔，最多不超过100个
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/domain_show
	 * @since JDK 1.5
	 */
	public List<UserCounts> getUserCount(String uids) throws WeiboException {
		return UserCounts.constructUserCount(client.get(
				WeiboConfig.getValue("baseURL") + "users/counts.json",
				new PostParameter[] { new PostParameter("uids", uids) },
				access_token));
	}
}
