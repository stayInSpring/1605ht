package cn.tarena.ht.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5HashAuth {

	public static void main(String[] args) {
		//1.第一个参数，要加密的密码
		//2.盐，盐不同，生成的密文就不同
		//3.hash次数不同，生成的密码就不同,hash此处越高，越不容易被破解
		//但是要考虑到次数太多的话，会影响性能。
		Md5Hash md5=new Md5Hash("123456","chenchen",3);
		System.out.println(md5.toString());
	}

	public static String md5(String loginPassword, String loginUsername) {
		// TODO Auto-generated method stub
		return new Md5Hash(loginPassword,loginUsername,3).toString();
	}
}
