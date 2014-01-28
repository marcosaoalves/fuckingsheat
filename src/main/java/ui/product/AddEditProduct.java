package ui.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import svc.command.product.ProductCommand;
import ui.MessageObservable;
import ui.common.JButton;
import ui.common.JPanel;
import ui.common.JToolBar;
import util.TextProperties;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AddEditProduct extends JPanel {
	private JLabel lblTaxaDeEntrega;

	private JTextField txtCodigoInterno;
	private JTextField txtDescricao;
	private JTextField txtDescricaoMovel;
	private JTextField txtPreco;
	private JTextField txtImagem;
	private JCheckBox checkDelivery;
	private JCheckBox checkConsumable;
	private JCheckBox checkQuantityInformed;
	private JTextField txtTaxaDeEntrega;
	private JTextField txtrDataValidade;
	private JTextField txtrParcelas;
	private JTextField txtCodigoProduto;
	private JTextArea txtDescricaoDetalhada;

	/**
	 * Create the panel.
	 */
	public AddEditProduct(Observer observer, String product) {
		super(observer);
		setPreferredSize(new Dimension(1000, 500));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JToolBar toolBar = new JToolBar();
		add(toolBar);

		JButton btnSave = new JButton(TextProperties.getInstance().getProperty(
				"app.btn.save"));
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ProductCommand command = new ProductCommand();
				command.save(txtCodigoProduto.getText(),
						txtDescricao.getText(), txtCodigoInterno.getText(),
						txtDescricaoMovel.getText(), txtPreco.getText(),
						txtDescricaoDetalhada.getText(),
						txtrDataValidade.getText(),
						"" + checkDelivery.isSelected(),
						txtTaxaDeEntrega.getText(),
						"" + checkConsumable.isSelected(), ""
								+ checkQuantityInformed.isSelected(),
						txtrParcelas.getText(), null);
				loadProduct(null);
				observable
						.changeData(
								MessageObservable.MESSAGE,
								TextProperties.getInstance().getProperty(
										"product.addedit.okmessage"));
			}
		});
		toolBar.add(btnSave);

		JButton btnCancel = new JButton(TextProperties.getInstance()
				.getProperty("app.btn.cancel"));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO implementar o save

				observable.changeData(MessageObservable.BACK_PANEL, null);
			}
		});
		toolBar.add(btnCancel);

		javax.swing.JPanel panel = new javax.swing.JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		javax.swing.JPanel panel2 = new javax.swing.JPanel();
		panel2.setBorder(new TitledBorder(new LineBorder(new Color(192, 192,
				192)), "Dados Principais", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(192, 192, 192)));
		panel.add(panel2, BorderLayout.NORTH);
		panel2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));

		JLabel lblCodigo = new JLabel(TextProperties.getInstance().getProperty(
				"product.cdProduct"));
		panel2.add(lblCodigo, "2, 2, right, default");

		txtCodigoInterno = new JTextField();
		panel2.add(txtCodigoInterno, "4, 2, fill, default");
		txtCodigoInterno.setColumns(10);
		txtCodigoProduto = new JTextField();
		txtCodigoProduto.setVisible(false);

		JLabel lblDescrio = new JLabel(TextProperties.getInstance()
				.getProperty("product.dsProduct"));
		panel2.add(lblDescrio, "2, 4, right, default");

		txtDescricao = new JTextField();
		panel2.add(txtDescricao, "4, 4, fill, default");
		txtDescricao.setColumns(10);

		JLabel lblPreco = new JLabel(TextProperties.getInstance().getProperty(
				"product.price"));
		panel2.add(lblPreco, "2, 6, right, default");

		txtPreco = new JTextField();
		panel2.add(txtPreco, "4, 6, fill, default");
		txtPreco.setColumns(10);

		javax.swing.JPanel panel3 = new javax.swing.JPanel();
		panel3.setBorder(new TitledBorder(new LineBorder(new Color(192, 192,
				192)), "Dados Complementares", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.LIGHT_GRAY));
		panel.add(panel3, BorderLayout.CENTER);
		panel3.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:80dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));

		JLabel lblDescricaomovel = new JLabel(TextProperties.getInstance()
				.getProperty("product.dsMobile"));
		panel3.add(lblDescricaomovel, "2, 2, right, default");

		txtDescricaoMovel = new JTextField();
		panel3.add(txtDescricaoMovel, "4, 2, fill, default");
		txtDescricaoMovel.setColumns(10);

		javax.swing.JPanel panel5 = new javax.swing.JPanel();
		panel5.setBorder(new TitledBorder(new LineBorder(new Color(192, 192,
				192)), "Imagem", TitledBorder.LEADING, TitledBorder.TOP, null,
				Color.LIGHT_GRAY));
		panel3.add(panel5, "6, 2, 1, 7, fill, fill");

		JLabel lblDescricaodetalhada = new JLabel(TextProperties.getInstance()
				.getProperty("product.dsDetailDescription"));
		panel3.add(lblDescricaodetalhada, "2, 4, right, top");

		txtDescricaoDetalhada = new JTextArea();
		panel3.add(txtDescricaoDetalhada, "4, 4, fill, fill");

		JLabel lblDatavalidade = new JLabel(TextProperties.getInstance()
				.getProperty("product.validDate"));
		panel3.add(lblDatavalidade, "2, 6, right, default");

		txtrDataValidade = new JTextField();
		panel3.add(txtrDataValidade, "4, 6, fill, default");

		JLabel lblParcelas = new JLabel(TextProperties.getInstance()
				.getProperty("product.parcelQuantityCard"));
		panel3.add(lblParcelas, "2, 8, right, default");

		txtrParcelas = new JTextField();
		panel3.add(txtrParcelas, "4, 8, fill, default");

		javax.swing.JPanel panel4 = new javax.swing.JPanel();
		panel4.setBorder(new TitledBorder(new LineBorder(new Color(192, 192,
				192)), "Parametros", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.LIGHT_GRAY));
		panel.add(panel4, BorderLayout.SOUTH);

		checkDelivery = new JCheckBox(TextProperties.getInstance().getProperty(
				"product.delivery"));
		checkDelivery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (checkDelivery.isSelected()) {
					txtTaxaDeEntrega.setVisible(true);
					lblTaxaDeEntrega.setVisible(true);
				} else {
					txtTaxaDeEntrega.setVisible(false);
					lblTaxaDeEntrega.setVisible(false);
				}
			}
		});
		panel4.add(checkDelivery);

		lblTaxaDeEntrega = new JLabel("Taxa de Entrega");
		panel4.add(lblTaxaDeEntrega);

		txtTaxaDeEntrega = new JTextField();
		txtTaxaDeEntrega.setColumns(4);
		panel4.add(txtTaxaDeEntrega);

		txtTaxaDeEntrega.setVisible(false);
		lblTaxaDeEntrega.setVisible(false);

		checkConsumable = new JCheckBox(TextProperties.getInstance()
				.getProperty("product.consumable"));
		panel4.add(checkConsumable);

		checkQuantityInformed = new JCheckBox(TextProperties.getInstance()
				.getProperty("product.quantityInformed"));
		panel4.add(checkQuantityInformed);

		loadProduct(product);
	}

	public void loadProduct(String cdProduct) {
		String[] p;
		if (cdProduct == null || "".equals(cdProduct)) {
			p = new String[13];
		} else {
			ProductCommand cmd = new ProductCommand();
			p = cmd.getProduct(cdProduct);
		}

		txtCodigoProduto.setText(p[0]);
		txtCodigoInterno.setText(p[1]);
		txtDescricao.setText(p[2]);
		txtPreco.setText(p[3]);
		txtDescricaoMovel.setText(p[4]);
		txtDescricaoDetalhada.setText(p[5]);
		txtrDataValidade.setText(p[6]);
		// txtImagem.setText(p[7]);
		checkDelivery.setSelected(Boolean.TRUE.equals(p[8]));
		txtTaxaDeEntrega.setText(p[9]);
		checkConsumable.setSelected(Boolean.TRUE.equals(p[10]));
		checkQuantityInformed.setSelected(Boolean.TRUE.equals(p[11]));
		txtrParcelas.setText(p[12]);
	}
}
