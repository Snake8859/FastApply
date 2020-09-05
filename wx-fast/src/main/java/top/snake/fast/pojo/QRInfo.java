package top.snake.fast.pojo;
/**
 * 二维码类
 * @author snake8859
 *
 */
public class QRInfo {

	/**
	 * 二维码路径
	 */
	private String QRPath;
	
	/**
	 * 协会logo
	 */
	private String logo;
	
	/**
	 * 协会名称
	 */
	private String assName;

	public String getQRPath() {
		return QRPath;
	}

	public void setQRPath(String qRPath) {
		QRPath = qRPath;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAssName() {
		return assName;
	}

	public void setAssName(String assName) {
		this.assName = assName;
	}
	
	
}
