package mainGui;

import helper.ReadWriteCSV;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    TextArea promptEndDevices;
    @FXML
    TextArea promptNetworkDevices;
    @FXML
    TextArea promptNetwork;
    @FXML
    TextArea promptConfig;
    @FXML
    Label testLabel;
    @FXML
    Button runButton;

    public void initialize() {
        runButton.setDisable(true);
    }

    public void reset() {
        promptEndDevices.clear();
        promptNetwork.clear();
        promptNetworkDevices.clear();
        promptConfig.clear();
    }

    public void autoFill() {
        promptEndDevices.setText("-pc, pc0, 192.153.11.13\n" +
                "-pc, pc1, 192.153.12.14\n" +
                "-pc, pc2, 192.153.21.15\n" +
                "-pc, pc3, 192.153.12.16\n");
        promptNetworkDevices.setText("-Switch, sw0\n");
        promptNetwork.setText("-Network, ntw0\n");
        promptConfig.setText("-ntw0, pc0, sw0\n" +
                "-ntw0, pc1, sw0\n" +
                "-ntw0, pc2, sw0\n" +
                "-ntw0, pc3, sw0\n");

    }

    @FXML
    public void apply() throws IOException {
        ReadWriteCSV.writeFile("C:\\Users\\iyade\\IdeaProjects\\Java\\PersonalProjects\\CPCAutomator\\src\\inputData\\endDevices.csv", promptEndDevices.getText());
        ReadWriteCSV.writeFile("C:\\Users\\iyade\\IdeaProjects\\Java" +
                        "\\PersonalProjects\\CPCAutomator\\src\\inputData\\networkDevices" +
                        ".csv",
                promptNetworkDevices.getText());
        ReadWriteCSV.writeFile(("C:\\Users\\iyade\\IdeaProjects\\Java\\PersonalProjects\\CPCAutomator\\src\\inputData\\network.csv"), promptNetwork.getText());
        ReadWriteCSV.writeFile("C:\\Users\\iyade\\IdeaProjects\\Java\\PersonalProjects\\CPCAutomator\\src\\inputData\\config.csv", promptConfig.getText());


    }

    @FXML
    public void testInput() {
        if (runTest()) {
            testLabel.setText("Test Successful");
            testLabel.setTextFill(Color.GREEN);
            runButton.setDisable(false);
        } else {
            testLabel.setText("Test Not Successful");
            testLabel.setTextFill(Color.RED);
        }
    }

    private boolean runTest() {
        String endDev = promptEndDevices.getText();
        String netwDev = promptNetworkDevices.getText();
        String netw = promptNetwork.getText();
        String config = promptConfig.getText();


        for (int i = 23; i < endDev.length(); i += 24) {
            Pattern pattern = Pattern.compile("-\\w\\w, \\w\\w\\d, \\d\\d\\d" +
                    ".\\d\\d\\d.\\d\\d.\\d\\d");
            Matcher matcher = pattern.matcher(endDev.substring(i - 23, i));
            if (matcher.matches() == false) {
                return false;
            }
        }

        for (int i = 13; i < netwDev.length(); i += 14) {
            Pattern pattern = Pattern.compile("-\\w\\w\\w\\w\\w\\w, \\w\\w\\d");
            Matcher matcher = pattern.matcher(netwDev.substring(i - 13, i));
            if (matcher.matches() == false) {
                return false;
            }
        }

        for (int i = 14; i < netw.length(); i += 15) {
            Pattern pattern = Pattern.compile("-\\w\\w\\w\\w\\w\\w\\w, " +
                    "\\w\\w\\w\\d");
            Matcher matcher = pattern.matcher(netw.substring(i - 14, i));
            System.out.println();
            if (matcher.matches() == false) {
                return false;
            }
        }


        for (int i = 15; i < config.length(); i += 16) {
            Pattern pattern = Pattern.compile("-\\w\\w\\w\\d, \\w\\w\\d, \\w\\w\\d");
            Matcher matcher = pattern.matcher(config.substring(i - 15, i));
            if (matcher.matches() == false) {
                return false;
            }
        }


        return true;
    }

    @FXML
    public void run() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("C:\\Users\\iyade" +
                "\\PycharmProjects\\Python-Personal-Projects\\CPTAutomator\\dist" +
                "\\main.exe");
    }


}