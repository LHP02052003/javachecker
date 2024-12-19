package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import ui.Main;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vidu {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    vidu window = new vidu();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public vidu() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 740, 417);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Bắt đầu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Main main = new Main();
            	main.main(null);
            	frame.setVisible(false);

            }
        });
        btnNewButton.setBounds(328, 87, 85, 21);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_2 = new JButton("Exit");
        btnNewButton_2.setBounds(328, 236, 85, 21);
        frame.getContentPane().add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        exitProgram(frame);
                    }
                });
                btnNewButton_2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       exitProgram(frame);
                    }
                });

                frame.getContentPane().add(btnNewButton_2);
                frame.getContentPane().setLayout(null);
               // frame.setVisible(true);
            }

            private static void exitProgram(JFrame frame) {
                int result = JOptionPane.showConfirmDialog(frame, "Bạn có muốn thoát chương trình không?", "Thoát chương trình", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.dispose(); // Đóng cửa sổ
                }
            }
        });


        JButton btnNewButton_4 = new JButton("Luật");
        btnNewButton_4.setBounds(328, 167, 85, 21);
        frame.getContentPane().add(btnNewButton_4);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String aboutMessage = "Cờ đam là một thể loại chơi cờ đơn giản mang tính giải trí tương đối cao. Bạn có thể chơi cùng bạn bè hoặc người thân của mình.\n\n";
                aboutMessage += "Cờ đam thích hợp với trẻ em do tính đơn giản dễ chơi và có thể sử dụng chung bàn cờ và các quân của trò chơi cờ Vua.\n\n";
                aboutMessage += "Luật chơi Cờ đam:\n";
                aboutMessage += "- Bàn cờ tiêu chuẩn kích thước 8×8 (có thể mở rộng thành kích thước MxN)\n";
                aboutMessage += "- Trên một hàng, luôn luôn một quân trắng và một quân đen, mỗi ô chứa không quá 1 quân cờ\n";
                aboutMessage += "- Quân cờ chỉ được di chuyển trên cùng hàng, tới một ô bất kì nhưng không trùng với ô hiện tại và không được nhảy qua đầu quân đối phương\n";
                aboutMessage += "- Có 2 người chơi, đi theo lượt, mỗi lượt phải đi một quân, ai đến lượt mình mà không đi được (hết nước đi) là thua\n";
                aboutMessage += "- Người chơi thứ nhất chỉ di chuyển quân trắng, người chơi thứ hai chỉ di chuyển quân đen\n";
                aboutMessage += "- Khi bắt đầu trò chơi, vị trí của các quân sẽ được tạo ngẫu nhiên, miễn thỏa mãn luật 'trên một hàng có một quân trắng và một quân đen'.";
                
                JOptionPane.showMessageDialog(frame, aboutMessage, "About...", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
		JLabel lblNewLabel_1 = new JLabel("CHECKERS - MASTER");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(260, 20, 259, 32);
		frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("E:\\javaproject\\gamecodam\\image\\background.jpg"));
        lblNewLabel.setBounds(10, 22, 716, 380);
        frame.getContentPane().add(lblNewLabel);
    }
}


