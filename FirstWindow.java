import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow {
    public static void main(String[] args) {
        // Membuat frame
        JFrame myWindow = new JFrame();

        // Mengatur judul frame
        myWindow.setTitle("Aplikasi Kalkulator");

        // Mengatur ukuran frame
        myWindow.setSize(500, 400);

        // Membuat panel utama dan mengatur layout serta background
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.GREEN);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Membuat label untuk input pertama
        JLabel label1 = new JLabel("Masukkan angka pertama:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(label1, gbc);

        // Membuat text field untuk input pertama
        JTextField textField1 = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(textField1, gbc);
        textField1.setBackground(Color.ORANGE);

        // Membuat label untuk input kedua
        JLabel label2 = new JLabel("Masukkan angka kedua:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(label2, gbc);

        // Membuat text field untuk input kedua
        JTextField textField2 = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(textField2, gbc);

        // Membuat dropdown untuk memilih operasi
        String[] operations = { "Penjumlahan", "Pengurangan", "Perkalian", "Pembagian" };
        JComboBox<String> operationBox = new JComboBox<>(operations);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(operationBox, gbc);

        // Membuat tombol untuk menghitung
        JButton button = new JButton("Hitung");
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(button, gbc);

        // Membuat tombol untuk reset
        JButton resetButton = new JButton("Reset");
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(resetButton, gbc);

        // Membuat label untuk menampilkan hasil
        JLabel resultLabel = new JLabel("Hasil akan ditampilkan di sini");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(resultLabel, gbc);

        // Membuat text area untuk riwayat perhitungan
        JTextArea historyArea = new JTextArea(5, 20);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(scrollPane, gbc);

        // Membuat tombol untuk mengubah warna latar belakang
        JButton colorButton = new JButton("Ubah Warna Latar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(colorButton, gbc);

        // Menambahkan ActionListener ke tombol hitung
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mengambil input dari text field dan melakukan operasi yang dipilih
                    double number1 = Double.parseDouble(textField1.getText());
                    double number2 = Double.parseDouble(textField2.getText());
                    String operation = (String) operationBox.getSelectedItem();
                    double result = 0;

                    switch (operation) {
                        case "Penjumlahan":
                            result = number1 + number2;
                            break;
                        case "Pengurangan":
                            result = number1 - number2;
                            break;
                        case "Perkalian":
                            result = number1 * number2;
                            break;
                        case "Pembagian":
                            if (number2 != 0) {
                                result = number1 / number2;
                            } else {
                                resultLabel.setText("Kesalahan: Pembagian dengan nol!");
                                return;
                            }
                            break;
                    }

                    // Menampilkan hasil operasi
                    resultLabel.setText("Hasil: " + result);

                    // Menambahkan hasil ke riwayat
                    historyArea.append( "input pertama:" + number1 + "\n"+ "jenis operasi:" + operation + "\n" + "input kedua:" + number2 + " \n" + "hasil = " + result + "\n");

                } catch (NumberFormatException ex) {
                    // Menampilkan pesan error jika input tidak valid
                    resultLabel.setText("Masukkan angka yang valid!");
                }
            }
        });

        // Menambahkan ActionListener ke tombol reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                resultLabel.setText("Hasil akan ditampilkan di sini");
                historyArea.setText("");
            }
        });

        // Menambahkan ActionListener ke tombol ubah warna latar
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(myWindow, "Pilih Warna Latar Belakang", mainPanel.getBackground());
                if (newColor != null) {
                    mainPanel.setBackground(newColor);
                }
            }
        });

        // Menambahkan panel utama ke frame
        myWindow.add(mainPanel);

        // Mengatur aksi default ketika jendela ditutup
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menampilkan frame
        myWindow.setVisible(true);
    }
}
