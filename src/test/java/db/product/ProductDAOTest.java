package db.product;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import svc.bean.product.Product;
import svc.db.product.ProductDAO;

public class ProductDAOTest {

	@Test
	public void testInsert() {
		try {
			ProductDAO dao = new ProductDAO();

			Product product = new Product();
			product.setDsProduct("dsProduct");
			product.setCdproductInternal("cdproductInternal");
			product.setDsMobile("dsMobile");
			product.setQrCode("qrCode");
			product.setPrice(10.00);
			product.setDsDetailDescription("dsDetailDescription");
			product.setValidDate(new Date());
			product.setCdCompany(1);
			product.setDelivery(true);
			product.setRateDelivery(1.0);
			product.setConsumable(true);
			product.setQuantityInformed(true);
			product.setParcelQuantityCard(1);
			product.setImageProduct(new File("teste.img"));

			dao.insertProduct(product);
		} catch (Exception e) {
			fail("Insert não funcionou");
		}
	}

	@Test
	public void testGetProduct() {
		ProductDAO dao = new ProductDAO();
		Product product = dao.getProduct(1);

		if (product == null) {
			fail("Produto não retornado");
		}
	}

	@Test
	public void testUpdate() {
		try {
			ProductDAO dao = new ProductDAO();

			Product product = dao.getProduct(1);
			product.setDsProduct("dsProduct_2");
			product.setCdproductInternal("cdproductInternal_2");
			product.setDsMobile("dsMobile_2");
			product.setQrCode("qrCode_2");
			product.setPrice(20.00);
			product.setDsDetailDescription("dsDetailDescription_2");
			product.setValidDate(new Date());
			product.setCdCompany(2);
			product.setDelivery(false);
			product.setRateDelivery(2.0);
			product.setConsumable(false);
			product.setQuantityInformed(false);
			product.setParcelQuantityCard(1);
			product.setImageProduct(new File("teste.img"));

			dao.updateProduct(product);
		} catch (Exception e) {
			fail("Update não funcionou");
		}
	}

	@Test
	public void testSearch() {
		ProductDAO dao = new ProductDAO();
		List<Product> product = dao.search("cdproductInternal_2", null);

		if (product == null ||
				product.size()==0) {
			fail("Produto não encontrado");
		}
		
		product = dao.search(null, "dsProduct_2");

		if (product == null ||
				product.size()==0) {
			fail("Produto não encontrado");
		}

		product = dao.search("cdproduct", null);

		if (product == null ||
				product.size()==0) {
			fail("Produto não encontrado");
		}

		product = dao.search(null, "dsProd");

		if (product == null ||
				product.size()==0) {
			fail("Produto não encontrado");
		}
		
		product = dao.search("cdproduct", "dsProd");

		if (product == null ||
				product.size()==0) {
			fail("Produto não encontrado");
		}
		
		product = dao.search("cdproduct1", "dsProd1");

		if (product != null &&
				product.size()>0) {
			fail("Retorno inesperado");
		}
	}
}
