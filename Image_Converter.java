import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;

public class Image_Converter extends JFrame implements ActionListener {

	private JPanel contentPane; // ana panel
	JPanel panel_1 = new JPanel();
	JPanel panel2 = new JPanel();

	private JButton btnOpenFile = new JButton(" Dosya Seç");
	JButton btncevir = new JButton("Çevir");

	JFileChooser fileChooser = new JFileChooser();

	String uzanti = null;
	File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Image_Converter frame = new Image_Converter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Image_Converter() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 235);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();// labelin bulundugu panel
		panel.setBackground(new Color(0, 102, 102));
		panel.setBounds(6, 6, 492, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("IMAGE    CONVERTER");
		lblNewLabel.setBounds(26, 5, 335, 36);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setForeground(new Color(176, 196, 222));
		lblNewLabel.setBackground(new Color(0, 153, 153));

		panel_1.setBackground(new Color(102, 153, 204));
		panel_1.setBounds(6, 57, 492, 150);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Çevirmek İstediğiniz Image Dosyasını Seçiniz:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel_1.setBounds(6, 19, 480, 34);
		panel_1.add(lblNewLabel_1);

		btnOpenFile.addActionListener(this);
		btnOpenFile.setBounds(6, 100, 117, 29);

		panel_1.add(btnOpenFile);

		btncevir.setBounds(286, 100, 117, 29);
		btncevir.setEnabled(false);
		panel_1.add(btncevir);

		btncevir.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOpenFile) {
			panel2.setVisible(true);
		

			int returnVal = fileChooser.showOpenDialog(Image_Converter.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();

				uzanti = file.getPath().substring(file.getPath().length() - 3);
	
				if (uzanti.equals("jpg") || uzanti.equals("png")) {
					showImage(file.getPath());
				} else {
					JOptionPane.showMessageDialog(null, "Dosya uzantısı jpg yada png olmalıdır !","CONVERTER",JOptionPane.WARNING_MESSAGE);
				}
				

			}

		} else if (e.getSource() == btncevir) {
				System.out.println("CEVIR");
		
					panel2.setVisible(false);
					setVisible(false);
					setBounds(100, 100, 504, 235);
					setLocationRelativeTo(null);
					setVisible(true);
					btncevir.setEnabled(false);
					

					
		
					
						convertImage(file.getPath());
					
					
		}
	}

	public void convertImage(String path) {
		 
		

		BufferedImage bufferedImage;

		if (uzanti.equals("png")) {

			try {
				

				// read image file
				bufferedImage = ImageIO.read(new File(path));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(newBufferedImage, "jpg", new File(path.substring(0, path.length() - 3) + "jpg"));

				System.out.println("Done");

				JOptionPane.showMessageDialog(null, "Seçtiğiniz " + uzanti + "  dosyası \n "
						+ path.substring(0, path.length() - 3) + "jpg olarak kaydedildi","CONVERTER",JOptionPane.INFORMATION_MESSAGE);
				

			} catch (IOException e) {

				e.printStackTrace();

			}
		} else
			{
			try {
				

				// read image file
				bufferedImage = ImageIO.read(new File(path));

				// create a blank, RGB, same width and height, and a white
				// background
				BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

				// write to jpeg file
				ImageIO.write(newBufferedImage, "png", new File(path.substring(0, path.length() - 3) + "png"));

				System.out.println("Done");

				JOptionPane.showMessageDialog(null,"Seçtiğiniz " + uzanti + "  dosyası \n "
						+ path.substring(0, path.length() - 3) + "png olarak kaydedildi","CONVERTER",JOptionPane.INFORMATION_MESSAGE);
				

			} catch (IOException e) {

				e.printStackTrace();

			}
		}

	}

	public void showImage(String path) {
		// contentPane.add(panel_1);
		panel2.removeAll();

		btncevir.setEnabled(true);

		panel2.setBackground(new Color(102, 153, 204));//
		panel2.setBounds(6, 214, 492, 300);
		panel2.add(new LoadImageApp(path));

		// btnConvert.setBounds(60, 221, 117, 29);
		// panel.add(btnConvert);

		contentPane.add(panel2);
		contentPane.revalidate();
		contentPane.repaint();

		setVisible(true);
		setVisible(false);
		setBounds(100, 100, 504, 542);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
