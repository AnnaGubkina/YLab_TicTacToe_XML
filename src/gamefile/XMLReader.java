package gamefile;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLReader {
    private static final String[][] field = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    private static final ArrayList<Step> steps = new ArrayList<>();
    private static final ArrayList<String> coordinates = new ArrayList<>();


    public static void readXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document document = builder.parse(new File("history.xml"));
        NodeList stepsElements = document.getDocumentElement().getElementsByTagName("Step");
        for (int i = 0; i < stepsElements.getLength(); i++) {
            Node step = stepsElements.item(i);
            //сохраняем координаты ходов
            coordinates.add(step.getTextContent());
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = step.getAttributes();
            //Добавление шагов.
            steps.add(new Step(attributes.getNamedItem("num").getNodeValue(), attributes.getNamedItem("playerId").getNodeValue()));
        }

        printHistoryGame();
        printTheWinner(document);
    }

    private static void printHistoryGame () {
        for (int y = 0; y < coordinates.size(); y++) {
            String string = coordinates.get(y);
            Step step = steps.get(y);

            switch (string) {
                case "11":
                    if (step.getValue().equals("1")) {
                        field[0][0] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[0][0] = "0";
                    }
                    break;
                case "12":
                    if (step.getValue().equals("1")) {
                        field[0][1] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[0][1] = "0";
                    }
                    break;
                case "13":
                    if (step.getValue().equals("1")) {
                        field[0][2] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[0][2] = "0";
                    }
                    break;
                case "21":
                    if (step.getValue().equals("1")) {
                        field[1][0] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[1][0] = "0";
                    }
                    break;
                case "22":
                    if (step.getValue().equals("1")) {
                        field[1][1] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[1][1] = "0";
                    }
                    break;
                case "23":
                    if (step.getValue().equals("1")) {
                        field[1][2] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[1][2] = "0";
                    }
                    break;
                case "31":
                    if (step.getValue().equals("1")) {
                        field[2][0] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[2][0] = "0";
                    }
                    break;
                case "32":
                    if (step.getValue().equals("1")) {
                        field[2][1] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[2][1] = "0";
                    }
                    break;
                case "33":
                    if (step.getValue().equals("1")) {
                        field[2][2] = "X";
                    } else if (step.getValue().equals("2")) {
                        field[2][2] = "0";
                    }
                    break;
                default:
                    break;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(" " + field[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();

    }
    private static void printTheWinner (Document document) {

        NodeList gameResult = document.getElementsByTagName("GameResult");
        for (int j = 0; j < gameResult.getLength(); j++) {
            Element gameRes = (Element) gameResult.item(j);

            NodeList result = document.getDocumentElement().getElementsByTagName("Player");
            for (int i = 0; i < result.getLength(); i++) {
                Element win = (Element) result.item(i);
                if (win.getParentNode() == gameRes) {
                    Node res = result.item(i);
                    NamedNodeMap attributes = res.getAttributes();
                    Player player = new Player(attributes.getNamedItem("id").getNodeValue(), attributes.getNamedItem("name").getNodeValue(), attributes.getNamedItem("symbol").getNodeValue());
                    System.out.println(player.toString());
                }
            }
        }
    }
}




