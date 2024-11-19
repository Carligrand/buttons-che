import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        JLabel mobileLabel = new JLabel("Mobile:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(mobileLabel, gbc);

        JTextField mobileField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(mobileField, gbc);

        JLabel genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(genderLabel, gbc);

        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        maleButton.setSelected(true);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        JLabel dobLabel = new JLabel("DOB:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(dobLabel, gbc);

        JComboBox<Integer> dayBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayBox.addItem(i);
        }

        JComboBox<String> monthBox = new JComboBox<>(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        JComboBox<Integer> yearBox = new JComboBox<>();
        for (int i = 1900; i <= 2024; i++) {
            yearBox.addItem(i);
        }

        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.add(dayBox);
        dobPanel.add(monthBox);
        dobPanel.add(yearBox);
        gbc.gridx = 1;
        formPanel.add(dobPanel, gbc);

        JLabel addressLabel = new JLabel("Address:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(addressLabel, gbc);

        JTextArea addressArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(addressArea);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);

        JCheckBox termsBox = new JCheckBox("Accept Terms and Conditions");
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(termsBox, gbc);

        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(buttonPanel, gbc);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "DOB", "Address", "Mobile"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            private int id = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String mobile = mobileField.getText().trim();
                String gender = maleButton.isSelected() ? "Male" : "Female";
                String dob = dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem();
                String address = addressArea.getText().trim();

                if (name.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!termsBox.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Please accept the terms and conditions!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tableModel.addRow(new Object[]{id++, name, gender, dob, address, mobile});

                nameField.setText("");
                mobileField.setText("");
                maleButton.setSelected(true);
                dayBox.setSelectedIndex(0);
                monthBox.setSelectedIndex(0);
                yearBox.setSelectedIndex(0);
                addressArea.setText("");
                termsBox.setSelected(false);
            }
        });

        resetButton.addActionListener(e -> {
            nameField.setText("");
            mobileField.setText("");
            maleButton.setSelected(true);
            dayBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            addressArea.setText("");
            termsBox.setSelected(false);
        });

        frame.setVisible(true);
    }
}
