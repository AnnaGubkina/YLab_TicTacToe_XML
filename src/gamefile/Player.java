package gamefile;

public class Player {

    String id;
    String name;
    String symbol;

    public Player(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }


    @Override
    public String toString() {
        return "Player " + id + " -> "
                + name + " is winner as "
                + "'" + symbol + "'!";
    }
}
