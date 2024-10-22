package ru.nstu.networks.client.view.gui;

import ru.nstu.networks.client.controller.Controller;
import ru.nstu.networks.client.view.View;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DesktopView implements View {
    private Controller controller;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chat");
            configureFrameWithStartSettings(frame);

            JLabel inputAddressLabel = new JLabel("Server address:");
            JTextField inputAddressTextField = new JTextField(15);

            JLabel inputPortLabel = new JLabel("Port number:");
            JTextField inputPortTextField = new JTextField(15);

            JLabel inputUsernameLabel = new JLabel("Your name:");
            JTextField inputUsernameTextField = new JTextField("hello", 15);

            JButton connectButton = new JButton("Connect");
            connectButton.addActionListener(e -> {
                try {
                    String serverAddress = inputAddressTextField.getText();
                    int port = Integer.parseInt(inputPortTextField.getText());
                    String username = inputUsernameTextField.getText();

                    controller.startModel(InetAddress.getByName(serverAddress), port, username);
                } catch (UnknownHostException ex) {
                    JOptionPane.showMessageDialog(frame, "Unknown host name.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Port must be an integer.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "All fields are required.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            panel.add(inputAddressLabel);
            panel.add(inputAddressTextField);

            panel.add(inputPortLabel);
            panel.add(inputPortTextField);

            panel.add(inputUsernameLabel);
            panel.add(inputUsernameTextField);

            panel.add(connectButton);
            frame.add(panel);
        });
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void sendMessage(String message) {
        controller.sendMessage(message);
    }

    private static void configureFrameWithStartSettings(JFrame frame) {
        frame.setSize(FrameConfigureConstants.FRAME_START_WIDTH, FrameConfigureConstants.FRAME_START_HEIGHT);
        frame.setResizable(FrameConfigureConstants.IS_RESIZABLE_BY_DEFAULT);
        frame.setLocationRelativeTo(FrameConfigureConstants.DEFAULT_LOCATION_RELATIVE_TO);
        frame.setDefaultCloseOperation(FrameConfigureConstants.DEFAULT_CLOSE_OPERATION);
        frame.setBackground(FrameConfigureConstants.DEFAULT_BACKGROUND_COLOR);
        frame.setVisible(FrameConfigureConstants.IS_VISIBLE_BY_DEFAULT);
    }
}