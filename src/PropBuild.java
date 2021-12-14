public class PropBuild {
    int[][] propBuild(int type, int h, int w, int color, int side){
        int [][]layer = new int[StaticValues.HEIGHT_UNIT][StaticValues.WIDTH_UNIT];
        switch (type){
            case 1:{ // prop z type
                if (side == 1 || side == 3) {
                    layer[h+1][w+2]=color;
                    layer[h+1][w+1]=color;
                    layer[h][w+1]=color;
                    layer[h][w]=color;
                }
                if (side == 2 || side == 4) {
                    layer[h][w+1]=color;
                    layer[h+1][w+1]=color;
                    layer[h+1][w]=color;
                    layer[h+2][w]=color;
                }

                break;
            }
            case 7:{ // prop z type
                if (side == 1 || side == 3) {
                    layer[h][w+1]=color;
                    layer[h][w+2]=color;
                    layer[h+1][w+1]=color;
                    layer[h+1][w]=color;
                }
                if (side == 2 || side == 4) {
                    layer[h][w]=color;
                    layer[h+1][w]=color;
                    layer[h+1][w+1]=color;
                    layer[h+2][w+1]=color;
                }

                break;
            }

            case 2:{ // prop CUBE type
                layer[h][w]=color;
                layer[h][w+1]=color;
                layer[h+1][w]=color;
                layer[h+1][w+1]=color;
                break;
            }
            case 3:{ // prop T type
                if (side == 1) {
                    layer[h][w] = color;
                    layer[h][w + 1] = color;
                    layer[h][w + 2] = color;
                    layer[h + 1][w + 1] = color;
                    break;
                }
                if (side == 2) {
                    layer[h][w] = color;
                    layer[h+1][w] = color;
                    layer[h+2][w] = color;
                    layer[h+1][w+1] = color;
                    break;
                }
                if (side == 3) {
                    layer[h+1][w] = color;
                    layer[h+1][w + 1] = color;
                    layer[h+1][w + 2] = color;
                    layer[h][w + 1] = color;
                    break;
                }
                if (side == 4) {
                    layer[h][w+1] = color;
                    layer[h+1][w+1] = color;
                    layer[h+2][w + 1] = color;
                    layer[h + 1][w] = color;
                    break;
                }

            }
            case 4:{ // prop I type
                if (side == 2 || side == 4) {
                    layer[h][w] = color;
                    layer[h + 1][w] = color;
                    layer[h + 2][w] = color;
                    layer[h + 3][w] = color;
                    break;
                }
                if (side == 1 || side == 3) {
                    layer[h][w] = color;
                    layer[h][w+1] = color;
                    layer[h][w+2]= color;
                    layer[h][w+3] = color;
                    break;
                }

            }
            case 5:{ // prop L type
                if (side == 1) {
                    layer[h][w] = color;
                    layer[h][w + 1] = color;
                    layer[h][w + 2] = color;
                    layer[h + 1][w + 2] = color;
                    break;
                }
                if (side == 2) {
                    layer[h][w] = color;
                    layer[h+1][w] = color;
                    layer[h+2][w] = color;
                    layer[h][w+1] = color;
                    break;
                }
                if (side == 3) {
                    layer[h+1][w] = color;
                    layer[h+1][w + 1] = color;
                    layer[h+1][w + 2] = color;
                    layer[h][w] = color;
                    break;
                }
                if (side == 4) {
                    layer[h][w+1] = color;
                    layer[h+1][w+1] = color;
                    layer[h+2][w + 1] = color;
                    layer[h +2][w] = color;
                    break;
                }

            }
            case 6:{ // prop L type
                if (side == 1) {
                    layer[h][w] = color;
                    layer[h][w + 1] = color;
                    layer[h][w + 2] = color;
                    layer[h + 1][w] = color;
                    break;
                }
                if (side == 2) {
                    layer[h][w+1] = color;
                    layer[h+1][w+1] = color;
                    layer[h+2][w+1] = color;
                    layer[h][w] = color;
                    break;
                }
                if (side == 3) {
                    layer[h+1][w] = color;
                    layer[h+1][w + 1] = color;
                    layer[h+1][w + 2] = color;
                    layer[h][w+2] = color;
                    break;
                }
                if (side == 4) {
                    layer[h][w] = color;
                    layer[h+1][w] = color;
                    layer[h+2][w] = color;
                    layer[h +2][w+1] = color;
                    break;
                }

            }

            default:{
                layer[h][w]=color;
                layer[h][w+1]=color;
                layer[h+1][w]=color;
                layer[h+1][w+1]=color;
                break;
            }


        }
        return layer;

    }
}
