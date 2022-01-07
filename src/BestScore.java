public class BestScore {
    String n1="0";
    String n2="0";
    String n3="0";
    String n4="0";
    String nick1="0";
    String nick2="0";
    String nick3="0";
    String nick4="0";

    public String getNick1() {
        return nick1;
    }

    public void setNick1(String nick1) {
        this.nick1 = nick1;
    }

    public String getNick2() {
        return nick2;
    }

    public void setNick2(String nick2) {
        this.nick2 = nick2;
    }

    public String getNick3() {
        return nick3;
    }

    public void setNick3(String nick3) {
        this.nick3 = nick3;
    }

    public String getNick4() {
        return nick4;
    }

    public void setNick4(String nick4) {
        this.nick4 = nick4;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }

    public String getN4() {
        return n4;
    }

    public void setN4(String n4) {
        this.n4 = n4;
    }

    public void loadScore() {
        CSV csv = new CSV();
        String data = csv.readCSVFile(csv.url,"score");
        String[] bestScore = data.split(",");
        try {
            setN1(bestScore[0]);
            setN2(bestScore[1]);
            setN3(bestScore[2]);
            setN4(bestScore[3]);
        } catch (Exception ignored) {}
        data = csv.readCSVFile(csv.url,"nick");
        bestScore = data.split(",");
        try {
            setNick1(bestScore[0]);
            setNick2(bestScore[1]);
            setNick3(bestScore[2]);
            setNick4(bestScore[3]);
        } catch (Exception ignored) {}

    }

    public void recordScore(String score, String nick) {
//        System.out.println(score);
//        System.out.println(nick);
        if (Integer.parseInt(score) > Integer.parseInt(getN1())) {
            setN4(getN3());
            setN3(getN2());
            setN2(getN1());
            setNick4(getNick3());
            setNick3(getNick2());
            setNick2(getNick1());

            setN1(score);
            setNick1(nick);
        } else {
            if (Integer.parseInt(score) > Integer.parseInt(getN2())) {
                setN4(getN3());
                setN3(getN2());
                setNick4(getNick3());
                setNick3(getNick2());
                setN2(score);
                setNick2(nick);
            } else {
                if (Integer.parseInt(score) > Integer.parseInt(getN3())) {
                    setN4(getN3());
                    setNick4(getNick3());
                    setN3(score);
                    setNick3(nick);
                } else {
                    if (Integer.parseInt(score) > Integer.parseInt(getN4())) {
                        setN4(score);
                        setNick4(nick);
                    }
                }
            }
        }
    }

    public void saveScore() {
        CSV csv = new CSV();
        String data = getN1() + "," + getN2() + "," + getN3() + "," + getN4();
        String nickData = getNick1() + "," + getNick2() + "," + getNick3() + "," + getNick4();
        csv.writeCSV(csv.getUrl(), data,"score");
        csv.writeCSV(csv.getUrl(), nickData,"nick");
    }
}
