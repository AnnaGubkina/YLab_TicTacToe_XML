package gamefile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLWriter {


    public static void write(String somePlayer1, String somePlayer2, List<String> steps, String[] playerWinner) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        //создаем корневой узел
        Element rootGame = document.createElement("Gameplay");
        document.appendChild(rootGame);
        //добавляем узлы в корневой узел
        //игроки
        Element player1 = document.createElement("Player");
        player1.setAttribute("id", "1");
        player1.setAttribute("name", somePlayer1);
        player1.setAttribute("symbol", "X");
        rootGame.appendChild(player1);
        Element player2 = document.createElement("Player");
        player2.setAttribute("id", "2");
        player2.setAttribute("name", somePlayer2);
        player2.setAttribute("symbol", "0");
        rootGame.appendChild(player2);
        //игра
        Element game = document.createElement("Game");
        rootGame.appendChild(game);

        createSteps(document, steps, game);
        printResult(document, rootGame, playerWinner);
        printXML(document);


    }

    private static void createSteps(Document document, List<String> steps, Element game) {
        //записываем шаги в каждую клетку

        Element step1 = document.createElement("Step");
        step1.setAttribute("num", "1");
        step1.setAttribute("playerId", "1");
        step1.appendChild(document.createTextNode(steps.get(0)));
        game.appendChild(step1);

        Element step2 = document.createElement("Step");
        step2.setAttribute("num", "2");
        step2.setAttribute("playerId", "2");
        step2.appendChild(document.createTextNode(steps.get(1)));
        game.appendChild(step2);

        Element step3 = document.createElement("Step");
        step3.setAttribute("num", "3");
        step3.setAttribute("playerId", "1");
        step3.appendChild(document.createTextNode(steps.get(2)));
        game.appendChild(step3);

        Element step4 = document.createElement("Step");
        step4.setAttribute("num", "4");
        step4.setAttribute("playerId", "2");
        step4.appendChild(document.createTextNode(steps.get(3)));
        game.appendChild(step4);

        Element step5 = document.createElement("Step");
        step5.setAttribute("num", "5");
        step5.setAttribute("playerId", "1");
        step5.appendChild(document.createTextNode(steps.get(4)));
        game.appendChild(step5);

        Element step6 = document.createElement("Step");
        step6.setAttribute("num", "6");
        step6.setAttribute("playerId", "2");
        step6.appendChild(document.createTextNode(steps.get(5)));
        game.appendChild(step6);

        Element step7 = document.createElement("Step");
        step7.setAttribute("num", "7");
        step7.setAttribute("playerId", "1");
        step7.appendChild(document.createTextNode(steps.get(6)));
        game.appendChild(step7);

        Element step8 = document.createElement("Step");
        step8.setAttribute("num", "8");
        step8.setAttribute("playerId", "2");
        step8.appendChild(document.createTextNode(steps.get(7)));
        game.appendChild(step8);

        Element step9 = document.createElement("Step");
        step9.setAttribute("num", "9");
        step9.setAttribute("playerId", "1");
        step9.appendChild(document.createTextNode(steps.get(8)));
        game.appendChild(step9);

    }

    private static void printResult(Document document, Element rootGame, String[] playerWinner) {
        //записываем результаты игры
        Element gameResult = document.createElement("GameResult");
        rootGame.appendChild(gameResult);
        Element player = document.createElement("Player");
        player.setAttribute("id", playerWinner[0]);
        player.setAttribute("name", playerWinner[1]);
        player.setAttribute("symbol", playerWinner[2]);
        gameResult.appendChild(player);


    }

    private static void printXML(Document document) throws TransformerException {
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("history.xml"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, streamResult);
    }
}
