package service;

public class Color {
    private int red;
    private int green;
    private int blue;
    //private int iterator;

    public Color(){
        this.red = 255;
        this.green = 0;
        this.blue = 0;
        //iterator = 1;
    }

    public int nextColor(){
        nextRGB();
        return getColor();
    }

    private void nextRGB() {
        if (red == 255 && green < 255 && blue == 0 )
            green++;
        if (green == 255 && red > 0 && blue == 0 )
            red--;
        if (green == 255 && blue < 255 && red == 0 )
            blue++;
        if (blue == 255 && green > 0 && red == 0 )
            green--;
        if (blue == 255 && red < 255 && green == 0 )
            red++;
        if (red == 255 && blue > 0 && green == 0 )
            blue--;
    }

    public int getColor(){
        StringBuilder color = new StringBuilder("0x");
        if(red<=15)
            color.append("0").append(Integer.toHexString(red));
        else
            color.append(Integer.toHexString(red));
        if(green<=15)
            color.append("0").append(Integer.toHexString(green));
        else
            color.append(Integer.toHexString(green));
        if(blue<=15)
            color.append("0").append(Integer.toHexString(blue));
        else
            color.append(Integer.toHexString(blue));

        //int c = Integer.decode(color.toString());

        //System.out.println(iterator++ + ") " + color.toString());

        //return c;
        return Integer.decode(color.toString());
    }

    private void setColors(int i) {
        red = (i >> 16) & 0xFF;
        green = (i >> 8) & 0xFF;
        blue = i & 0xFF;
    }

    public int getColorForScale(double value){
        if(value==0){
            return 0xffffff;
        }
        if(value<1){
            return 0xffffff;
        }
        else if(value<=10) {
            return getAccurateColor(0x6700ff, value);
        }
        else if(value<=20) {
            return getAccurateColor(0x1700ff, value);
        }
        else if(value<=30) {
            return getAccurateColor(0x003aff, value);
        }
        else if(value<=40) {
            return getAccurateColor(0x008aff, value);
        }
        else if(value<=50) {
            return getAccurateColor(0x00daff, value);
        }
        else if(value<=60) {
            return getAccurateColor(0x00ffd3, value);
        }
        else if(value<=70) {
            return getAccurateColor(0x00ff83, value);
        }
        else if(value<=80) {
            return getAccurateColor(0x00ff33, value);
        }
        else if(value<=90) {
            return getAccurateColor(0x1eff00, value);
        }
        else if(value<=100) {
            return getAccurateColor(0x6eff00, value);
        }
        else if(value<=110) {
            return getAccurateColor(0xbeff00, value);
        }
        else if(value<=120) {
            return getAccurateColor(0xffef00, value);
        }
        else if(value<=130) {
            return getAccurateColor(0xff9f00, value);
        }
        else if(value<=140) {
            return getAccurateColor(0xff4f00, value);
        }
        else if(value<=150) {
            return getAccurateColor(0xff0000, value);
        }
        else
            return 0x9b0000;
    }

    private int getAccurateColor(int i, double value) {
        setColors(i);
        int iterationNumber;
        value = ((int) value)%10;
        if(value==1){
            iterationNumber = 72;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==2){
            iterationNumber = 64;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==3){
            iterationNumber = 56;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==4){
            iterationNumber = 48;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==5){
            iterationNumber = 40;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==6){
            iterationNumber = 32;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==7){
            iterationNumber = 24;
            return getAccurateColorLastStage(value,iterationNumber);
        } else if(value==8){
            iterationNumber = 16;
            return getAccurateColorLastStage(value,iterationNumber);
        } else{
            iterationNumber = 8;
            return getAccurateColorLastStage(value, iterationNumber);
        }
    }

    private int getAccurateColorLastStage(double value, int inumber) {
        value -= (int) value;
        int iterationNumber = inumber;
        if(value <= 0.125){
            return getColor();
        } else if(value<=0.250){
            iterationNumber -= 1;
        } else if(value<=0.375){
            iterationNumber -= 2;
        }  else if(value<=0.500){
            iterationNumber -= 3;
        } else if(value<=0.625){
            iterationNumber -= 4;
        } else if(value<=0.750){
            iterationNumber -= 5;
        } else if(value<=0.875){
            iterationNumber -= 6;
        }else if(value<=0.999){
            iterationNumber -= 7;
        }
        for (int j = 0; j < iterationNumber; j++) {
            nextRGB();
        }

        return getColor();
    }


}
