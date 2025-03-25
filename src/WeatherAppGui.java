//import org.jetbrains.annotations.Nullable;
//import org.json.simple.JSONObject;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class WeatherAppGui extends JFrame {
//    private JSONObject weatherData;
//
//    public WeatherAppGui(){
//        // setup our gui and add a title
//        super("Weather App");
//
//        // configure gui to end the program's process once it has been closed
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        // set the size of our gui (in pixels)
//        setSize(450, 650);
//
//        // load our gui at the center of the screen
//        setLocationRelativeTo(null);
//
//        // make our layout manager null to manually position our components within the gui
//        setLayout(null);
//
//        // prevent any resize of our gui
//        setResizable(false);
//
//        addGuiComponents();
//    }
//
//    private void addGuiComponents(){
//        // search field
//        JTextField searchTextField = new JTextField();
//
//        // set the location and size of our component
//        searchTextField.setBounds(15, 15, 351, 45);
//
//        // change the font style and size
//        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
//
//        add(searchTextField);
//
//        // weather image
//        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
//        weatherConditionImage.setBounds(0, 125, 450, 217);
//        add(weatherConditionImage);
//
//        // temperature text
//        JLabel temperatureText = new JLabel("10 C");
//        temperatureText.setBounds(0, 350, 450, 54);
//        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
//
//        // center the text
//        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
//        add(temperatureText);
//
//        // weather condition description
//        JLabel weatherConditionDesc = new JLabel("Cloudy");
//        weatherConditionDesc.setBounds(0, 405, 450, 36);
//        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
//        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
//        add(weatherConditionDesc);
//
//        // humidity image
//        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
//        humidityImage.setBounds(15, 500, 74, 66);
//        add(humidityImage);
//
//        // humidity text
//        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
//        humidityText.setBounds(90, 500, 85, 55);
//        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
//        add(humidityText);
//
//        // windspeed image
//        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
//        windspeedImage.setBounds(220, 500, 74, 66);
//        add(windspeedImage);
//
//        // windspeed text
//        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
//        windspeedText.setBounds(310, 500, 85, 55);
//        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
//        add(windspeedText);
//
//        // search button
//        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
//
//        // change the cursor to a hand cursor when hovering over this button
//        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        searchButton.setBounds(375, 13, 47, 45);
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // get location from user
//                String userInput = searchTextField.getText();
//
//                // validate input - remove whitespace to ensure non-empty text
//                if(userInput.replaceAll("\\s", "").length() <= 0){
//                    return;
//                }
//
//                // retrieve weather data
//                weatherData = WeatherApp.getWeatherData(userInput);
//
//                // update gui
//
//                // update weather image
//                String weatherCondition = (String) weatherData.get("weather_condition");
//
//                // depending on the condition, we will update the weather image that corresponds with the condition
//                switch(weatherCondition){
//                    case "Clear":
//                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
//                        break;
//                    case "Cloudy":
//                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
//                        break;
//                    case "Rain":
//                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
//                        break;
//                    case "Snow":
//                        weatherConditionImage.setIcon(loadImage("src/assets/snow.pngImage"));
//                        break;
//                }
//
//                // update temperature text
//                double temperature = (double) weatherData.get("temperature");
//                temperatureText.setText(temperature + " C");
//
//                // update weather condition text
//                weatherConditionDesc.setText(weatherCondition);
//
//                // update humidity text
//                long humidity = (long) weatherData.get("humidity");
//                humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");
//
//                // update windspeed text
//                double windspeed = (double) weatherData.get("windspeed");
//                windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
//            }
//        });
//        add(searchButton);
//    }
//
//    // used to create images in our gui components
//    private @Nullable ImageIcon loadImage(String resourcePath){
//        try{
//            // read the image file from the path given
//            BufferedImage image = ImageIO.read(new File(resourcePath));
//
//            // returns an image icon so that our component can render it
//            return new ImageIcon(image);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//        System.out.println("Could not find resource");
//        return null;
//    }
//}
//
//
//
//
//
//
//
//
//


// adding my content
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
    private JLabel weatherConditionImage;
    private Timer bounceTimer;
    private int bounceOffset = 0;

    public WeatherAppGui(){
        super("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use our animated panel as the content pane
        AnimatedPanel animatedPanel = new AnimatedPanel();
        animatedPanel.setLayout(null);
        setContentPane(animatedPanel);

        addGuiComponents(animatedPanel);
        startBouncingAnimation();
    }

    private void addGuiComponents(JPanel panel){
        // Search field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));
        panel.add(searchTextField);

        // Weather image
        weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0, 725, 450, 217);
        weatherConditionImage.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(weatherConditionImage);

        // Temperature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(temperatureText);

        // Weather condition description
        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(weatherConditionDesc);

        // Humidity image
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        panel.add(humidityImage);

        // Humidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        panel.add(humidityText);

        // Windspeed image
        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220, 500, 74, 66);
        panel.add(windspeedImage);

        // Windspeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15km/h</html>");
        windspeedText.setBounds(310, 500, 85, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        panel.add(windspeedText);

        // Search button with a transparent background
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTextField.getText().trim();
                if(userInput.isEmpty()){
                    return;
                }

                weatherData = WeatherApp.getWeatherData(userInput);
                if (weatherData == null) {
                    JOptionPane.showMessageDialog(null, "Error fetching weather data.");
                    return;
                }

                // Update weather image based on condition
                String weatherCondition = (String) weatherData.get("weather_condition");
                switch(weatherCondition){
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.png"));
                        break;
                    default:
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                }

                // Update temperature
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                // Update condition description
                weatherConditionDesc.setText(weatherCondition);

                // Update humidity
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                // Update windspeed
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
            }
        });
        panel.add(searchButton);
    }

    // Start bouncing animation for the weather icon
    private void startBouncingAnimation(){
        int delay = 30; // milliseconds
        bounceTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Bounce effect using a sine wave calculation
                bounceOffset = (int)(10 * Math.sin(System.currentTimeMillis() / 200.0));
                weatherConditionImage.setLocation(weatherConditionImage.getX(), 125 + bounceOffset);
            }
        });
        bounceTimer.start();
    }

    // Utility method to load images from a given resource path
    private @Nullable ImageIcon loadImage(String resourcePath){
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));
            return new ImageIcon(image);
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Could not find resource: " + resourcePath);
        return null;
    }

    // Inner class for animated gradient background
    class AnimatedPanel extends JPanel {
        private float hue = 0f;
        private Timer backgroundTimer;

        public AnimatedPanel(){
            backgroundTimer = new Timer(50, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    hue += 0.001f;
                    if(hue > 1f){
                        hue = 0f;
                    }
                    repaint();
                }
            });
            backgroundTimer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = Color.getHSBColor(hue, 0.5f, 1f);
            Color color2 = Color.getHSBColor((hue + 0.5f) % 1.0f, 0.5f, 1f);
            GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    @Override
    public void dispose(){
        if(bounceTimer != null){
            bounceTimer.stop();
        }
        super.dispose();
    }

    // For testing the GUI independently
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new WeatherAppGui().setVisible(true);
            }
        });
    }
}
