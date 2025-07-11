import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Card {
    private String cardType; 
    private String cardNumber;
    private String expiryDate;
    private double balance;

    public Card(String cardType, String cardNumber, String expiryDate, double balance) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.balance = balance;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public double getBalance() {
        return balance;
    }
}

public class errorclearing {

    private Frame frame;
    private List<Card> cards = new ArrayList<>();

    public errorclearing() {
        frame = new Frame("Card Management System");

      
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());
        
        
        Button viewCardsButton = new Button("View All Cards");
        Button addCardButton = new Button("Add New Card");
        Button blockCardButton = new Button("Block a Card");
        Button exitButton = new Button("Exit");

    
        viewCardsButton.addActionListener(e -> viewAllCards());
        addCardButton.addActionListener(e -> addCard());
        blockCardButton.addActionListener(e -> blockCard());
        exitButton.addActionListener(e -> System.exit(0));

       
        frame.add(viewCardsButton);
        frame.add(addCardButton);
        frame.add(blockCardButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    private void viewAllCards() {
        if (cards.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No cards available.", "View Cards", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder cardDetails = new StringBuilder();
        for (Card card : cards) {
            cardDetails.append("Card Type: ").append(card.getCardType()).append("\n");
            cardDetails.append("Card Number: ").append(card.getCardNumber()).append("\n");
            cardDetails.append("Expiry Date: ").append(card.getExpiryDate()).append("\n");
            cardDetails.append("Balance: ₹").append(card.getBalance()).append("\n");
            cardDetails.append("-------------------\n");
        }

        JOptionPane.showMessageDialog(frame, cardDetails.toString(), "View Cards", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addCard() {
        TextField cardTypeField = new TextField();
        TextField cardNumberField = new TextField();
        TextField expiryDateField = new TextField();
        TextField balanceField = new TextField();

        
        Object[] fields = {
                "Card Type (Credit/Debit):", cardTypeField,
                "Card Number:", cardNumberField,
                "Expiry Date (MM/YYYY):", expiryDateField,
                "Initial Balance:", balanceField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Card", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String cardType = cardTypeField.getText();
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                double balance = Double.parseDouble(balanceField.getText());

                
                cards.add(new Card(cardType, cardNumber, expiryDate, balance));

                JOptionPane.showMessageDialog(frame, "Card added successfully!", "Add Card", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid balance. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void blockCard() {
        String cardNumber = JOptionPane.showInputDialog(frame, "Enter Card Number to block:", "Block Card", JOptionPane.PLAIN_MESSAGE);

        if (cardNumber != null) {
            boolean cardFound = cards.removeIf(card -> card.getCardNumber().equals(cardNumber));

            if (cardFound) {
                JOptionPane.showMessageDialog(frame, "Card blocked successfully!", "Block Card", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Card not found.", "Block Card", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(errorclearing::new);
    }
}
