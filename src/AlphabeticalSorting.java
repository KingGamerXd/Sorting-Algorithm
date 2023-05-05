import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlphabeticalSorting extends JFrame implements ActionListener {
    JTextField input;
    JTextArea output;
    JButton sortButton;

    public AlphabeticalSorting() {
        setLayout(new FlowLayout());

        input = new JTextField(20);
        add(input);

        sortButton = new JButton("Sort");
        sortButton.addActionListener(this);
        add(sortButton);

        output = new JTextArea(10, 20);
        add(output);

        setTitle("Alphabetical Sorting");
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String inputString = input.getText();
        if (inputString.isEmpty()) {
            output.setText("Please enter a string of words.");
        } else if (!inputString.matches("[a-zA-Z ]+")) {
            output.setText("Invalid input. Please enter only alphabetic characters and spaces.");
        } else {
            String[] words = inputString.split(" ");
            StringBuilder outputString = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                String currentMin = words[i];
                int currentMinIndex = i;

                for (int j = i + 1; j < words.length; j++) {
                    if (currentMin.compareTo(words[j]) > 0) {
                        currentMin = words[j];
                        currentMinIndex = j;
                    }
                }

                words[currentMinIndex] = words[i];
                words[i] = currentMin;
                outputString.append(currentMin).append(" ");
            }

            output.setText(outputString.toString());
        }
    }

    //Stop program on Gui close

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlphabeticalSorting sorting = new AlphabeticalSorting();
            sorting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}