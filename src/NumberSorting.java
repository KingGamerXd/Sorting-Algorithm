import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class NumberSorting extends JFrame implements ActionListener {
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel outputPanel;
    private JLabel titleLabel;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton sortButton;
    private JButton clearButton;
    private JLabel outputLabel;
    private JTextArea outputArea;
    private JProgressBar progressBar;

    public NumberSorting() {
        setTitle("Number Sorting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 500));
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        titleLabel = new JLabel("Number Sorting");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.setBackground(new Color(0, 122, 255));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        inputLabel = new JLabel("Enter numbers separated by spaces:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setPreferredSize(new Dimension(500, 50));
        inputPanel.add(inputField, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.WEST);

        buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        sortButton = new JButton("Sort Numbers");
        sortButton.addActionListener(this);
        sortButton.setFont(new Font("Arial", Font.PLAIN, 24));
        sortButton.setPreferredSize(new Dimension(250, 50));
        sortButton.setBackground(new Color(0, 122, 255));
        sortButton.setForeground(Color.WHITE);
        buttonPanel.add(sortButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setFont(new Font("Arial", Font.PLAIN, 24));
        clearButton.setPreferredSize(new Dimension(250, 50));
        clearButton.setBackground(new Color(0, 122, 255));
        clearButton.setForeground(Color.WHITE);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.CENTER);

        outputPanel = new JPanel(new BorderLayout(10, 10));
        outputPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        outputLabel = new JLabel("Sorted Numbers:");
        outputLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 24));
        outputArea.setBackground(inputField.getBackground());
        outputArea.setBorder(inputField.getBorder());
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        add(outputPanel, BorderLayout.EAST);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        add(progressBar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {
            String inputString = inputField.getText().trim(); // Trim leading/trailing spaces
            String[] numberStrings = inputString.split("\\s+"); // Split on single or multiple spaces
            int[] numbers = new int[numberStrings.length];

            try {
                for (int i = 0; i < numberStrings.length; i++) {
                    numbers[i] = Integer.parseInt(numberStrings[i]);
                }

                new Thread(() -> {
                    progressBar.setVisible(true);
                    sortButton.setEnabled(false);
                    clearButton.setEnabled(false);

                    Arrays.sort(numbers);

                    SwingUtilities.invokeLater(() -> {
                        outputArea.setText(arrayToString(numbers));
                        progressBar.setVisible(false);
                        sortButton.setEnabled(true);
                        clearButton.setEnabled(true);
                    });
                }).start();
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter only integers separated by spaces.");
            }
        } else if (e.getSource() == clearButton) {
            inputField.setText("");
            outputArea.setText("");
        }
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new NumberSorting();
    }
}