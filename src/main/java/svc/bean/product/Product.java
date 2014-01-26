package svc.bean.product;

import java.io.File;
import java.util.Date;

public class Product {
	private int cdProduct;
	private String dsProduct;
	private String cdproductInternal;
	private String dsMobile;
	private String qrCode;
	private double price;
	private String dsDetailDescription;
	private Date validDate;
	private int cdCompany;
	private boolean delivery;
	private double rateDelivery;
	private boolean consumable;
	private boolean quantityInformed;
	private boolean parcelQuantityCard;
	private File imageProduct;

	public int getCdProduct() {
		return cdProduct;
	}

	public void setCdProduct(int cdProduct) {
		this.cdProduct = cdProduct;
	}

	public String getDsProduct() {
		return dsProduct;
	}

	public void setDsProduct(String dsProduct) {
		this.dsProduct = dsProduct;
	}

	public String getCdproductInternal() {
		return cdproductInternal;
	}

	public void setCdproductInternal(String cdproductInternal) {
		this.cdproductInternal = cdproductInternal;
	}

	public String getDsMobile() {
		return dsMobile;
	}

	public void setDsMobile(String dsMobile) {
		this.dsMobile = dsMobile;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDsDetailDescription() {
		return dsDetailDescription;
	}

	public void setDsDetailDescription(String dsDetailDescription) {
		this.dsDetailDescription = dsDetailDescription;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public int getCdCompany() {
		return cdCompany;
	}

	public void setCdCompany(int cdCompany) {
		this.cdCompany = cdCompany;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public double getRateDelivery() {
		return rateDelivery;
	}

	public void setRateDelivery(double rateDelivery) {
		this.rateDelivery = rateDelivery;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public boolean isQuantityInformed() {
		return quantityInformed;
	}

	public void setQuantityInformed(boolean quantityInformed) {
		this.quantityInformed = quantityInformed;
	}

	public boolean isParcelQuantityCard() {
		return parcelQuantityCard;
	}

	public void setParcelQuantityCard(boolean parcelQuantityCard) {
		this.parcelQuantityCard = parcelQuantityCard;
	}

	public File getImageProduct() {
		return imageProduct;
	}

	public void setImageProduct(File imageProduct) {
		this.imageProduct = imageProduct;
	}

}
