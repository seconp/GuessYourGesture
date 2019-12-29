package com.qf.control;

import java.beans.beancontext.BeanContextChildSupport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.qf.model.BaseBean;
import com.qf.model.ScoreBean;
import com.qf.model.UserBean;
import com.qf.tool.SQLConnection;

public class UserControl {
	/**
	 * 注册
	 * 登录
	 * 修改密码
	 * 榜单
	 */
	/**
	 * 注册方法
	 * @param username 账号
	 * @param password 密码
	 * @param likename 昵称
	 * @param phone 联系方式
	 * @return
	 * 	结果
	 */
	public BaseBean regist(String username,String password,String likename,String phone){
		/**
		 * 判断账号是否存在
		 */
		ArrayList<UserBean> list = getUserInfo(username);
		if(list.size() > 0){
			//账户已存在
			return new BaseBean(2, "账号已存在", null);
		}
		
		//1,获取数据库连接
		PreparedStatement ps = SQLConnection.openConn("INSERT INTO userinfo (username,upassword,likename,phone) VALUES (?,?,?,?)");
		try {
			//2,填充占位符
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, likename);
			ps.setString(4, phone);
			//3,执行,返回值为影响行数
			int num = ps.executeUpdate();
			
			SQLConnection.closeConn();
			if(num > 0){
				return new BaseBean(0, "注册成功", null);
			}else{
				return new BaseBean(1, "注册失败", null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BaseBean(3, "未知错误", null);
	}
	/**
	 * 通过账户查询用户信息
	 * @param username 账号
	 * @return
	 * 	查询结果
	 */
	public ArrayList<UserBean> getUserInfo(String username){
		//1,获取数据库连接
		PreparedStatement ps = SQLConnection.openConn("SELECT * FROM userinfo where username=?");
		
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		try {
			//2,填充占位符
			ps.setString(1, username);
			//3,执行,返回值为查询结果
			ResultSet resultSet = ps.executeQuery();
			//4,遍历查询结果
			while(resultSet.next()){
				String id = resultSet.getString("id");
				String name = resultSet.getString("username");
				String word = resultSet.getString("upassword");
				String likename = resultSet.getString("likename");
				String phone = resultSet.getString("phone");
				UserBean bean = new UserBean(id, name, word, likename, phone);
				list.add(bean);
			}
			SQLConnection.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 登录
	 * @param username 账号
	 * @param password 密码
	 * @return
	 * 	结果
	 */
	public BaseBean login(String username,String password){
		ArrayList<UserBean> list = getUserInfo(username);
		if(list.size() == 0){
			return new BaseBean(1, "账号不存在", null);
		}
		UserBean bean = list.get(0);
		if(password.equals(bean.getWord())){
			return new BaseBean(0, "登录成功", bean);
		}else{
			return new BaseBean(2, "账号密码不匹配", null);
		}
	}
	/**
	 * 修改密码
	 * @param username	账号
	 * @param oldPassword	原密码
	 * @param newPassword	新密码
	 * @return
	 * 	结果
	 */
	public BaseBean xgmm(String username,String oldPassword,String newPassword){
		BaseBean bean = login(username, oldPassword);
		if(bean.getCode() == 0){
			PreparedStatement ps = SQLConnection.openConn("UPDATE userinfo SET upassword = ? WHERE username = ?");
			try {
				ps.setString(1, newPassword);
				ps.setString(2, username);
				int num = ps.executeUpdate();
				SQLConnection.closeConn();
				if(num > 0){
					return new BaseBean(0, "修改成功", null);
				}else{
					return new BaseBean(1, "修改失败", null);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new BaseBean(1, "修改失败", null);
		}else {
			return new BaseBean(1, "账号密码不匹配", null);
		}
	}
	/**
	 * 榜单
	 * @return
	 * 	结果
	 */
	public BaseBean phb(){
		PreparedStatement ps = SQLConnection.openConn("SELECT userinfo.likename,score.score FROM userinfo INNER JOIN score ON userinfo.id= score.u_id");
		ArrayList<ScoreBean> list = new ArrayList<ScoreBean>();
		try {
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				String likename = resultSet.getString("likename");
				String score = resultSet.getString("score");
				ScoreBean bean = new ScoreBean(likename, score);
				list.add(bean);
			}
			SQLConnection.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BaseBean(0, "查询成功", list);
	}
	/**
	 * 上传分数
	 */
	public BaseBean upScore(String username,String score){
		ArrayList<UserBean> list = getUserInfo(username);
		UserBean bean = list.get(0);
		String id = bean.getId();
		//1,获取数据库连接
		PreparedStatement ps = SQLConnection.openConn("INSERT INTO score (u_id,score) VALUES (?,?)");
		try {
			//2,填充占位符
			ps.setString(1, id);
			ps.setString(2, score);
			
			//3,执行,返回值为影响行数
			int num = ps.executeUpdate();
					
			SQLConnection.closeConn();
			if(num > 0){
				return new BaseBean(0, "上传成功", null);
			}else{
				return new BaseBean(1, "上传失败", null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BaseBean(3, "未知错误", null);
	}
}
