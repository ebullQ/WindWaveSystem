package service;

public class Color {
    private int red;
    private int green;
    private int blue;

    public Color(){
        this.red = 255;
        this.green = 0;
        this.blue = 0;
    }

    public int nextColor(){
        nextRGB();
        return getColor();
    }

    private void nextRGB() {
        if ( red == 255 && green < 255 && blue == 0 )
            green++;
        if ( green == 255 && red > 0 && blue == 0 )
            red--;
        if ( green == 255 && blue < 255 && red == 0 )
            blue++;
        if ( blue == 255 && green > 0 && red == 0 )
            green--;
        if ( blue == 255 && red < 255 && green == 0 )
            red++;
        if ( red == 255 && blue > 0 && green == 0 )
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
        if(value<0.5d){
            return 0xffffff;
        }
        else if(value<=1) {
            return getAccurateColor(0x0012ff, value);
        }
        else if(value<=2) {
            return getAccurateColor(0x0026ff, value);
        }
        else if(value<=3) {
            return getAccurateColor(0x003aff, value);
        }
        else if(value<=4) {
            return getAccurateColor(0x004eff, value);
        }
        else if(value<=5) {
            return getAccurateColor(0x0062ff, value);
        }
        else if(value<=6) {
            return getAccurateColor(0x0076ff, value);
        }
        else if(value<=7) {
            return getAccurateColor(0x008aff, value);
        }
        else if(value<=8) {
            return getAccurateColor(0x00b2ff, value);
        }
        else if(value<=9) {
            return getAccurateColor(0x00c6ff, value);
        }
        else if(value<=10) {
            return getAccurateColor(0x00daff, value);
        }
        else if(value<=11) {
            return getAccurateColor(0x00eeff, value);
        }
        else if(value<=12) {
            return getAccurateColor(0x00fffb, value);
        }
        else if(value<=13) {
            return getAccurateColor(0x00ffe7, value);
        }
        else if(value<=14) {
            return getAccurateColor(0x00ffd3, value);
        }
        else if(value<=15) {
            return getAccurateColor(0x00ffbf, value);
        }
        else if(value<=16) {
            return getAccurateColor(0x00ffab, value);
        }
        else if(value<=17) {
            return getAccurateColor(0x00ff97, value);
        }
        else if(value<=18) {
            return getAccurateColor(0x00ff83, value);
        }
        else if(value<=19) {
            return getAccurateColor(0x00ff6f, value);
        }
        else if(value<=20) {
            return getAccurateColor(0x00ff5b, value);
        }
        else if(value<=21) {
            return getAccurateColor(0x00ff47, value);
        }
        else if(value<=22) {
            return getAccurateColor(0x00ff33, value);
        }
        else if(value<=23) {
            return getAccurateColor(0x00ff1f, value);
        }
        else if(value<=24) {
            return getAccurateColor(0x00ff0b, value);
        }
        else if(value<=25) {
            return getAccurateColor(0x0aff00, value);
        }
        else if(value<=26) {
            return getAccurateColor(0x1eff00, value);
        }
        else if(value<=27) {
            return getAccurateColor(0x32ff00, value);
        }
        else if(value<=28) {
            return getAccurateColor(0x46ff00, value);
        }
        else if(value<=29) {
            return getAccurateColor(0x5aff00, value);
        }
        else if(value<=30) {
            return getAccurateColor(0x6eff00, value);
        }
        else if(value<=31) {
            return getAccurateColor(0x82ff00, value);
        }
        else if(value<=32) {
            return getAccurateColor(0x96ff00, value);
        }
        else if(value<=33) {
            return getAccurateColor(0xaaff00, value);
        }
        else if(value<=34) {
            return getAccurateColor(0xbeff00, value);
        }
        else if(value<=35) {
            return getAccurateColor(0xd2ff00, value);
        }
        else if(value<=36) {
            return getAccurateColor(0xe6ff00, value);
        }
        else if(value<=37) {
            return getAccurateColor(0xfaff00, value);
        }
        else if(value<=38) {
            return getAccurateColor(0xffef00, value);
        }
        else if(value<=39) {
            return getAccurateColor(0xffdb00, value);
        }
        else if(value<=40) {
            return getAccurateColor(0xffc700, value);
        }
        else if(value<=41) {
            return getAccurateColor(0xffb300, value);
        }
        else if(value<=42) {
            return getAccurateColor(0xff9f00, value);
        }
        else if(value<=43) {
            return getAccurateColor(0xff8b00, value);
        }
        else if(value<=44) {
            return getAccurateColor(0xff7700, value);
        }
        else if(value<=45) {
            return getAccurateColor(0xff6300, value);
        }
        else if(value<=46) {
            return getAccurateColor(0xff4f00, value);
        }
        else if(value<=47) {
            return getAccurateColor(0xff3b00, value);
        }
        else if(value<=48) {
            return getAccurateColor(0xff2700, value);
        }
        else if(value<=49) {
            return getAccurateColor(0xff1300, value);
        }
        else if(value<=50) {
            return getAccurateColor(0xff0000, value);
        }
        else
            return 0x9b0000;
    }

    private int getAccurateColor(int i, double value) {
        setColors(i);
        value -= (int) value;
        int iterationNumber;
        if(value <= 0.05){
            return i;
        } else if(value<=0.1){
            iterationNumber = 1;
        } else if(value<=0.15){
            iterationNumber = 2;
        } else if(value<=0.2){
            iterationNumber =3;
        } else if(value<=0.25){
            iterationNumber = 4;
        } else if(value<=0.3){
            iterationNumber = 5;
        } else if(value<=0.35){
            iterationNumber = 6;
        } else if(value<=0.40){
            iterationNumber = 7;
        } else if(value<=0.45){
            iterationNumber = 8;
        } else if(value<=0.50){
            iterationNumber = 9;
        } else if(value<=0.55){
            iterationNumber = 10;
        } else if(value<=0.60){
            iterationNumber = 11;
        } else if(value<=0.65){
            iterationNumber = 12;
        } else if(value<=0.70){
            iterationNumber = 13;
        } else if(value<=0.75){
            iterationNumber = 14;
        } else if(value<=0.80){
            iterationNumber = 15;
        } else if(value<=0.85){
            iterationNumber = 16;
        } else if(value<=0.90){
            iterationNumber = 17;
        } else if(value<=0.95){
            iterationNumber = 18;
        }  else{
            iterationNumber = 19;
        }
        for (int j = 0; j < iterationNumber; j++) {
            nextRGB();
        }
        return getColor();
    }


}
