import java.util.Random;

public class Engine {

    int [][]table = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
    int [][]layer = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
    int [][]layer2 = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
    PropBuild prop =new PropBuild();
    int hit = 0;
    int propCount = 7;
    int propSelected;
    int propSide = 1;

    int startPoint;
    int h=0;
    int v=0;

    Random random3 = new Random();
    int color = 2;

    Engine(int x, int y){
        setH(x);
        setW(y);
        color = generateRandom(5);
        createTable();

        reset();
    }
    int generateRandom(int limit){
        int number = random3.nextInt(limit);
        while (number == 0){
            number = random3.nextInt(limit);
        }
        return number;
    }


    private void createTable() {
        //initialization
        for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
            for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
                table[k][j] = 0;
            }
        }
    }


    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return v;
    }

    public void setW(int v) {
        this.v = v;
    }

    public int[][] getLayer() {
        return layer;
    }

    void clearProp(){
        //clear
        for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
            for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
                layer[k][j] = 0;
                layer2[k][j] = 0;
            }
        }
    }

    public void move(char direction){
        //configure new coordinates to redraw
        switch (direction){
            case 'd':{
                down();
                break;
            }
            case 'r':{
                side(1);
                break;
            }
            case 'l':{
                side(-1);
                break;
            }
        }

    }
    void side(int side){
        setW(getW()+side);
        //Test border
        try {
            layer2 = prop.propBuild(propSelected,getH(), getW(),color,propSide);
            //test collision
            if(collision()==0) {
                //if ok clear and reposition
                clearProp();
                layer = prop.propBuild(propSelected,getH(), getW(),color,propSide);

            }else{
                if(side == -1) {
                    setW(getW() + 1);
                }else{
                    setW(getW() - 1);
                }
            }
        }catch (Exception e){

            if(side == -1) {
                setW(getW() + 1);
            }else{
                setW(getW() - 1);
            }
        }
        hit = 0;
        down();
    }
    void down(){
        try {
            setH(getH()+1);
            //Test border
            layer2 = prop.propBuild(propSelected,getH(), getW(),color,propSide);
            //test collision
            if(collision()!=2){
                //if ok clear and reposition
                clearProp();
                layer = prop.propBuild(propSelected,getH(), getW(),color,propSide);
            }else{
                for(int i=0;i<StaticValues.HEIGHT_UNIT;i++){
                    if(table[0][i]!=0){
                        hit=3;
                        break;
                    }
                }
            }
        }catch (Exception e){
            hit = 2;
            playSound.playSound("hit-01.wav");
        }
    }


    public void setLayer(int[][] layer) {
        this.layer = layer;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    void flip(){
        if(propSide<4){
            propSide++;
        }else{
            propSide = 1;
        }
        try {
            layer2 = prop.propBuild(propSelected,getH(), getW(),color,propSide);
            if(collision()!=0) {
                System.out.println(propSide);
                unFlip();

                layer = prop.propBuild(propSelected,getH(), getW(),color,propSide);
            }else{
                layer2 = layer;
            }
        }catch (Exception e){
            unFlip();
        }
        hit = 0;
        //down();
    }
    void unFlip(){
        if(propSide>1){
            propSide--;
        }else{
            propSide = 4;
        }
        layer2 = layer;
        hit=0;
    }

    int collision(){
        if(hit!=3){
            //Hit detection between prop and table pieces
            for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
                for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
                    if (layer2[k][j]!=0){
                        if (table[k][j]!=0){
                            hit = 2;
                            return hit;
                        }
                    }
                }
            }
        }


        return hit;
    }
    Score lineScoreDetect(){
        Score score = new Score();
        for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
            for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
                if (table[k][j]!=0){
                    score.done = true;
                    score.line = k;
                }else{
                    score.done = false;
                    break;
                }
            }
            if(score.done){
                return score;
            }
        }
        return score;
    }

    void lineScoreDownAnimation(Score score){
        for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
            table[score.line][j]=0;
        }
        int [][]tableTemp = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
        for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
            for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
                if((k-1)<score.line) {
                    if (table[k][j] != 0) {
                        tableTemp[k + 1][j] = table[k][j];
                    }
                }else {
                    tableTemp[k][j] = table[k][j];
                }
            }
        }
        table = tableTemp;
    }


    void tableAddPieces(){
        for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
            for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
                if (layer[k][j]!=0){
                    table[k][j] = layer[k][j];
                }
            }
        }
    }

    void reset(){
        //initialization
        color = generateRandom(5);

        propSelected = generateRandom(propCount);

        hit = 0;
        for(int j=0;j<StaticValues.WIDTH_UNIT;j++){
            for(int k=0;k<StaticValues.HEIGHT_UNIT;k++){
                layer[k][j] = 0;
                layer2[k][j] = 0;
            }
        }
        randomStart();

    }
    void randomStart(){
        try {
            Random random = new Random();
            startPoint = random.nextInt((StaticValues.WIDTH_UNIT));
            //Set initial position
            setW(startPoint);
            setH(0);
            //layer initial position
            layer = prop.propBuild(propSelected,getH(), getW(),color,propSide);
        }catch (Exception e){
            randomStart();
        }
    }
}
