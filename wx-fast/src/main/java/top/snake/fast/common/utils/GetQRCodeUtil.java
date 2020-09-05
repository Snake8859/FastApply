package top.snake.fast.common.utils;
 
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 获得小程序固定页面二维码工具类
 * @author snake8859
 *
 */
public class GetQRCodeUtil {
	
	 public static String getQRCode(String token,String path,int width,String REALPATH) {  
		 	//参数输入流
		 	PrintWriter out = null;
		 	//参数输出流
		 	BufferedInputStream in = null;  
		 	FileOutputStream fos = null;
		 	//访问地址
	        String url="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+token;
	        try {  
				URL weChatUrl = new URL(url);  
	            // 打开和URL之间的连接  
				HttpURLConnection connection = (HttpURLConnection) weChatUrl.openConnection();
	            // 设置通用的请求属性  
	            connection.setConnectTimeout(5000);  
	            connection.setReadTimeout(5000);  
	            //设置为POST请求
	            connection.setDoInput(true);
	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            //设置本次连接的Content-type，配置为application/json
	            connection.setRequestProperty("content-type", "application/json");
	            connection.setUseCaches(false);
	            //发送请求参数 Json拼接参数
	            out = new PrintWriter(connection.getOutputStream());
	            Map<String, Object> map = new HashMap<String,Object>();
	            map.put("path", path);
	            map.put("width", width);
	            Gson gson = new Gson();
	            String params = gson.toJson(map);
	            //System.out.println(params);
	            out.print(params);
	            out.flush();
	            // 建立实际的连接  
	            connection.connect();  
	            // 定义 BufferedInputStream输入流来读取URL的响应  
	            in = new BufferedInputStream(connection.getInputStream());
	            //写入到文件中 统一为JPG格式
	            String fileName = UuidUtil.get8UUID()+".jpg";
	            File file = new File(REALPATH+"//"+fileName);
	            fos = new FileOutputStream(file);
	            int len;
	            byte[] buf = new byte[1024];
	            while ((len = in.read(buf)) != -1) {  
	                fos.write(buf, 0, len);
	            }
	            return fileName;  
	        } 
	        catch(IOException e){
	        	//捕获异常
	        	e.printStackTrace();
	        	throw new RuntimeException();
	        }
	        finally {  
	        	//使用finally块来关闭输入流  
	        	try {
	        		if(in!=null){
	        			in.close();
	        		}
	        		if(fos!=null){
	        			fos.close();
	        		}
	        		if(out!=null){
	        			out.close();
	        		}
	        	} catch (IOException e) {
					e.printStackTrace();
				}  
	        }
	    }  
}
