package svc.command.product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import svc.bean.product.Product;
import svc.db.CommonDAO;
import svc.db.DBFactory;
import util.TextProperties;

public class ProductDAO extends CommonDAO {
	public void insertProduct(Product product) {
		Connection c = DBFactory.getInstance().getConnection();

		try {
			PreparedStatement statement = c
					.prepareStatement(getSql("product.insert"));

			statement.setString(1, product.getDsProduct());
			statement.setString(2, product.getCdproductInternal());
			statement.setString(3, product.getDsMobile());
			statement.setString(4, product.getQrCode());
			statement.setDouble(5, product.getPrice());
			statement.setString(6, product.getDsDetailDescription());
			statement.setDate(7, new java.sql.Date(
					product.getValidDate() != null ? product.getValidDate()
							.getTime() : null));
			statement.setInt(8, product.getCdCompany());
			statement.setBoolean(9, product.isDelivery());
			statement.setDouble(10, product.getRateDelivery());
			statement.setBoolean(11, product.isConsumable());
			statement.setBoolean(12, product.isQuantityInformed());
			statement.setBoolean(13, product.isParcelQuantityCard());
			if (product.getImageProduct() != null) {
				FileInputStream is;
				try {
					is = new FileInputStream(product.getImageProduct());
					statement.setBinaryStream(14, is, product.getImageProduct()
							.length());
				} catch (FileNotFoundException e) {
					statement.setNull(14, Types.CLOB);
				}
			} else {
				statement.setNull(14, Types.CLOB);
			}

			statement.executeUpdate();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty(
					"err.database.queryerror"));
		}
	}
	
	public void updateProduct(Product product) {
		Connection c = DBFactory.getInstance().getConnection();

		try {
			PreparedStatement statement = c
					.prepareStatement(getSql("product.update"));

			statement.setString(1, product.getDsProduct());
			statement.setString(2, product.getCdproductInternal());
			statement.setString(3, product.getDsMobile());
			statement.setString(4, product.getQrCode());
			statement.setDouble(5, product.getPrice());
			statement.setString(6, product.getDsDetailDescription());
			statement.setDate(7, new java.sql.Date(
					product.getValidDate() != null ? product.getValidDate()
							.getTime() : null));
			statement.setInt(8, product.getCdCompany());
			statement.setBoolean(9, product.isDelivery());
			statement.setDouble(10, product.getRateDelivery());
			statement.setBoolean(11, product.isConsumable());
			statement.setBoolean(12, product.isQuantityInformed());
			statement.setBoolean(13, product.isParcelQuantityCard());
			if (product.getImageProduct() != null) {
				FileInputStream is;
				try {
					is = new FileInputStream(product.getImageProduct());
					statement.setBinaryStream(14, is, product.getImageProduct()
							.length());
				} catch (FileNotFoundException e) {
					statement.setNull(14, Types.CLOB);
				}
			} else {
				statement.setNull(14, Types.CLOB);
			}
			statement.setInt(15, product.getCdProduct());

			statement.executeUpdate();
			c.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty(
					"err.database.queryerror"));
		}
	}
	
	public Product getProduct(int cdProduct){
		Product ret = null;
		Connection c = DBFactory.getInstance().getConnection();
		
		try {
			PreparedStatement statement = c.prepareStatement(getSql("product.select"));
			statement.setInt(1, cdProduct);
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				ret = new Product();
				
				ret.setCdProduct(rs.getInt("cd_product"));
				ret.setDsProduct(rs.getString("ds_product"));
				ret.setCdproductInternal(rs.getString("cd_product_internal"));
				ret.setDsMobile(rs.getString("ds_mobile"));
				ret.setQrCode(rs.getString("qrcode"));
				ret.setPrice(rs.getDouble("price"));
				ret.setDsDetailDescription(rs.getString("ds_detail_description"));
				ret.setValidDate(rs.getDate("valid_date"));
				ret.setCdCompany(rs.getInt("cd_company"));
				ret.setDelivery(rs.getBoolean("delivery"));
				ret.setRateDelivery(rs.getDouble("rate_delivery"));
				ret.setConsumable(rs.getBoolean("consumable"));
				ret.setQuantityInformed(rs.getBoolean("quantity_informed"));
				ret.setParcelQuantityCard(rs.getBoolean("parcel_quantity_card"));
				//ret.setImageProduct(rs.getClob("image_product"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(TextProperties.getInstance().getProperty("err.database.queryerror"));
		}
		return ret;
	}
}
