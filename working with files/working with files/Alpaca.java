public class Alpaca {

    String name;
    int weight;
    int wooliness;

    public static void main(String[] args) {

    }

    Alpaca (String name, int weight, int wooliness) {
        this.name = name;
        this.weight = weight;
        this.wooliness = wooliness;
    }

    public String getTheName() {
        return this.name;
    }

    public int getTheWeight() {
        return this.weight;
    }
    public int getTheWooliness() {
        return this.wooliness;
    }

    public Boolean compare(Alpaca toCompare) {
        if (this.name.equals(toCompare.getTheName()) & this.weight == toCompare.getTheWeight() & this.wooliness == toCompare.getTheWooliness()) {
            return true;
        }
        return false;
    }
}
