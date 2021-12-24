package jp.co.internous.sirius.model.domain.dto;

import jp.co.internous.sirius.model.domain.MstDestination;
import jp.co.internous.sirius.model.domain.MstProduct;

/**
 * 購入履歴画面に表示するためのDTO
 * @author wataharu-84
 */
public class PurchaseHistoryDto {
	
	private String purchasedAt;
	private String productName;
	private long price;
	private long productCount;
	private String familyName;
	private String firstName;
	private String address;

	/**
	 * 購入履歴画面に表示するためのDTO。
	 * @return 購入履歴情報
	 */
	public PurchaseHistoryDto() {}

	/**
	 * 購入履歴画面に表示するためのDTO
	 * @param product 商品のドメイン destination 宛先のドメイン
	 * @return 購入履歴情報
	 */
	public PurchaseHistoryDto(MstProduct product, MstDestination destination) {
		this.setPurchasedAt(getPurchasedAt().toString());
		this.setProductName(product.getProductName());
		this.setPrice(product.getPrice());
		this.setProductCount(getProductCount());
		this.setFamilyName(destination.getFamilyName());
		this.setFirstName(destination.getFirstName());
		this.setAddress(destination.getAddress());
	}

	/**
	 * 購入日時を取得する
	 * @return 購入日時
	 */
	public String getPurchasedAt() {
		return purchasedAt;
	}
	
	/**
	 * 購入日時を設定する
	 * @param purchasedAt 購入日時
	 */
	public void setPurchasedAt(String purchasedAt) {
		this.purchasedAt = purchasedAt;
	}
	
	/**
	 * 商品名を取得する
	 * @return 商品名
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * 商品名を設定する
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 価格を取得する
	 * @return 価格
	 */
	public long getPrice() {
		return price;
	}
	
	/**
	 * 価格を設定する
	 * @param price 価格
	 */
	public void setPrice(long price) {
		this.price = price;
	}
	
	/**
	 * 個数を取得する
	 * @return 個数
	 */
	public long getProductCount() {
		return productCount;
	}
	
	/**
	 * 個数を設定する
	 * @param productCount 個数
	 */
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	
	/**
	 * 宛先姓を取得する
	 * @return 宛先姓
	 */
	public String getFamilyName() {
		return familyName;
	}
	
	/**
	 * 宛先姓を設定する
	 * @param familyName 宛先姓
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	/**
	 * 宛先名を取得する
	 * @return 宛先名
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 宛先名を設定する
	 * @param firstName 宛先名
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 住所を取得する
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 住所を設定する
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}