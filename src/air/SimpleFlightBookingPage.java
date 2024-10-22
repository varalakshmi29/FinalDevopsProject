package air;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Properties;
import org.jdatepicker.impl.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L; 

    private BufferedImage backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {     
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
        }
    }
}

class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (row % 2 == 0) {  
            cell.setBackground(new Color(223, 200, 220, 255)); 
        } else {
            cell.setBackground(new Color(255, 255, 255)); 
        }

        cell.setForeground(new Color(30, 60, 120));  

        if (isSelected) {
            cell.setBackground(new Color(70, 130, 180));  
            cell.setForeground(Color.WHITE);  
        }

        return cell;
    }
}

public class SimpleFlightBookingPage {
    public static void main(String[] args) {
        
    
        JFrame frame = new JFrame("Devops_project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);  

        BackgroundPanel mainPanel = new BackgroundPanel("C:\\Users\\varalakshmi\\Desktop\\image90.jpg"); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

       
        JLabel title = new JLabel("Airline Travel Reservation Platform");
        title.setFont(new Font("Tahoma", Font.BOLD, 35));
        title.setForeground(new Color(255,255,255));  
        title.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(title);

        JLabel subtitle = new JLabel("Let us get you there safely & quickly.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(new Color(255,255,255)); 
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(subtitle);     
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] columnNames = {"Airline", "From", "To", "Departure Time", "Arrival Time"};
        Object[][] data = {
            {"AirHorizon", "Tokyo (Haneda) - HND", "Nha Trang - CXR", "09:00 AM", "01:30 PM"},
            {"Skyways", "Los Angeles - LAX", "New York - JFK", "12:15 PM", "04:50 PM"},
            {"Oceanic Air", "Sydney - SYD", "Melbourne - MEL", "02:30 PM", "03:45 PM"},
            {"GlobalWings", "Paris - CDG", "London - LHR", "10:00 AM", "11:00 AM"},
            {"FlyHigh", "Mumbai - BOM", "Singapore - SIN", "06:45 AM", "02:10 PM"},
            {"DeltaSky", "Seattle - SEA", "Chicago - ORD", "07:30 AM", "01:00 PM"},
            {"BlueAir", "Rome - FCO", "Berlin - BER", "11:45 AM", "02:00 PM"},
            {"CloudWings", "Bangkok - BKK", "Phuket - HKT", "05:15 PM", "07:30 PM"}
        };

        // Create table with the data
        JTable flightTable = new JTable(new DefaultTableModel(data, columnNames));
        flightTable.setFillsViewportHeight(true);
        flightTable.setRowHeight(30);
        flightTable.setFont(new Font("Arial", Font.PLAIN, 14));
        flightTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

       
        Color borderColor = new Color(113,102,144,255);  
        flightTable.setBorder(BorderFactory.createLineBorder(borderColor, 4)); 
        flightTable.getTableHeader().setBorder(BorderFactory.createLineBorder(borderColor, 4)); 
        
        CustomTableCellRenderer customRenderer = new CustomTableCellRenderer();
        for (int i = 0; i < flightTable.getColumnCount(); i++) {
            flightTable.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
        
              
      
        mainPanel.add(flightTable.getTableHeader(), BorderLayout.NORTH); 
        mainPanel.add(flightTable, BorderLayout.CENTER);

        JLabel afterTableTitle = new JLabel("   ");
        afterTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT); 
        mainPanel.add(afterTableTitle);

        // Subtitle after the table
        JLabel afterTableSubtitle = new JLabel("Where do you want to go?");
        afterTableSubtitle.setFont(new Font("Arial", Font.BOLD, 28));
        afterTableSubtitle.setForeground(new Color(30, 60, 120));  
        afterTableSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(afterTableSubtitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));

        JPanel bookingPanel = new JPanel(new GridBagLayout());
        bookingPanel.setOpaque(false);  
        bookingPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        Color labelColor = new Color(30, 60, 120);  
        Color inputColor = new Color(240, 248, 255);  

        Font labelFont = new Font("Arial", Font.BOLD, 18);  
        JLabel airlineLabel = new JLabel("Airline:");
        airlineLabel.setForeground(labelColor);
        airlineLabel.setFont(labelFont);  
        airlineLabel.setOpaque(false);  
        JComboBox<String> airlineDropdown = new JComboBox<>(new String[]{
            "AirHorizon", "Skyways", "Oceanic Air", "GlobalWings", "FlyHigh", "DeltaSky", "BlueAir", "CloudWings"
        });
        
        airlineDropdown.setBackground(inputColor);
        gbc.gridx = 0;
        gbc.gridy = 0;
        bookingPanel.add(airlineLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(airlineDropdown, gbc);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(labelColor);
        fromLabel.setFont(labelFont); 
        fromLabel.setOpaque(false);
        JComboBox<String> fromDropdown = new JComboBox<>(new String[]{
            "Tokyo (Haneda) - HND", "Los Angeles - LAX", "Sydney - SYD", "Paris - CDG", "Mumbai - BOM", 
            "Seattle - SEA", "Rome - FCO", "Bangkok - BKK"
        });
        fromDropdown.setBackground(inputColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        bookingPanel.add(fromLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(fromDropdown, gbc);
        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(labelColor);
        toLabel.setFont(labelFont);  
        toLabel.setOpaque(false);  
        JComboBox<String> toDropdown = new JComboBox<>(new String[]{
            "Nha Trang - CXR", "New York - JFK", "Melbourne - MEL", "London - LHR", "Singapore - SIN", 
            "Chicago - ORD", "Berlin - BER", "Phuket - HKT"
        });
        toDropdown.setBackground(inputColor);

        gbc.gridx = 0;
        gbc.gridy = 2;
        bookingPanel.add(toLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(toDropdown, gbc);

        JLabel departLabel = new JLabel("Depart:");
        departLabel.setForeground(labelColor);
        departLabel.setFont(labelFont);  
        departLabel.setOpaque(false);  
        UtilDateModel departDateModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl departDatePanel = new JDatePanelImpl(departDateModel, p);
        JDatePickerImpl departDatePicker = new JDatePickerImpl(departDatePanel, new DateComponentFormatter());
        departDatePicker.setBackground(inputColor);

        gbc.gridx = 0;
        gbc.gridy = 3;
        bookingPanel.add(departLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(departDatePicker, gbc);

        JLabel passengersLabel = new JLabel("Passengers:");
        passengersLabel.setForeground(labelColor);
        passengersLabel.setFont(labelFont); 
        passengersLabel.setOpaque(false); 
        JComboBox<String> passengersDropdown = new JComboBox<>(new String[]{
            "1 Adult", "2 Adults", "3 Adults", "4 Adults", "1 Adult, 1 Child", "2 Adults, 1 Child", 
            "2 Adults, 2 Children", "1 Adult, 2 Children"
        });
        passengersDropdown.setBackground(inputColor);

        gbc.gridx = 0;
        gbc.gridy = 5;
        bookingPanel.add(passengersLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(passengersDropdown, gbc);
        
        JLabel classLabel = new JLabel("Class:");
        classLabel.setForeground(labelColor);
        classLabel.setFont(labelFont);
        classLabel.setOpaque(false); 
        JComboBox<String> classDropdown = new JComboBox<>(new String[]{
            "Economy", "Business", "First Class"
        });
        classDropdown.setBackground(inputColor);

        gbc.gridx = 0;
        gbc.gridy = 6; 
        bookingPanel.add(classLabel, gbc);
        gbc.gridx = 1;
        bookingPanel.add(classDropdown, gbc);
        mainPanel.add(bookingPanel);

        gbc.gridx = 0; 
        gbc.gridy = 7; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
       

        JButton bookButton = new JButton("Book Flight");
        bookButton.setBackground(new Color(70, 130, 180));  
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  
        bookButton.setFocusPainted(false); 
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
       
        mainPanel.add(bookingPanel);
        mainPanel.add(bookButton);
        // Add spacing after the booking panel if needed
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
       bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String airline = (String) airlineDropdown.getSelectedItem();
                String from = (String) fromDropdown.getSelectedItem();
                String to = (String) toDropdown.getSelectedItem();
                String departureDate = departDatePicker.getJFormattedTextField().getText();
                String passengers = (String) passengersDropdown.getSelectedItem();
                String flightClass = (String) classDropdown.getSelectedItem();
               
                String message = "<html>" +
                    "<h2>Flight Booking Confirmation</h2>" +
                    "<p><b>Airline:</b> " + airline + "</p>" +
                    "<p><b>Departure:</b> " + from + "</p>" +
                    "<p><b>Destination:</b> " + to + "</p>" +
                    "<p><b>Departure Date:</b> " + departureDate + "</p>" +
                    "<p><b>Number of Passengers:</b> " + passengers + "</p>" +
                    "<p><b>Class:</b> " + flightClass + "</p>" +
                    "<br/><i>Thank you for choosing AirHorizon! We wish you a safe journey.</i>" +
                    "</html>";

                JLabel label = new JLabel(message);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                JOptionPane.showMessageDialog(frame,
                    label,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
