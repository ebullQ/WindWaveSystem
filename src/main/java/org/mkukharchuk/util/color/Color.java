package org.mkukharchuk.util.color;

public class Color{

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

    void nextRGB() {
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

    void setColors(int i) {
        red = (i >> 16) & 0xFF;
        green = (i >> 8) & 0xFF;
        blue = i & 0xFF;
    }

    public int getColor(double value) {
        if (value == 0) {
            return 0xffffff;
        }
        if (value <= 1d) {
            return 0xffffff;
        } else if (value <= 1.25d) {
            return 0x0019ff;
        } else if (value <= 2.5d) {
            return 0x0033ff;
        } else if (value <= 3.75d) {
            return 0x004cff;
        } else if (value <= 5d) {
            return 0x0065ff;
        } else if (value <= 6.25d) {
            return 0x007fff;
        } else if (value <= 7.5d) {
            return 0x0099ff;
        } else if (value <= 8.75d) {
            return 0x00b2ff;
        } else if (value <= 10) {
            return 0x00cbff;
        } else if (value <= 11.25d) {
            return 0x00e5ff;
        } else if (value <= 12.5d) {
            return 0x00ffff;
        } else if (value <= 13.75d) {
            return 0x00ffe5;
        } else if (value <= 15d) {
            return 0x00ffcb;
        } else if (value <= 16.25d) {
            return 0x00ffb2;
        } else if (value <= 17.5d) {
            return 0x00ff99;
        } else if (value <= 18.75d) {
            return 0x00ff7f;
        } else if (value <= 20) {
            return 0x00ff65;
        } else if (value <= 21.25d) {
            return 0x00ff4c;
        } else if (value <= 22.5d) {
            return 0x00ff33;
        } else if (value <= 23.75d) {
            return 0x00ff19;
        } else if (value <= 25d) {
            return 0x00ff00;
        } else if (value <= 26.25d) {
            return 0x19ff00;
        } else if (value <= 27.5d) {
            return 0x32ff00;
        } else if (value <= 28.75d) {
            return 0x4cff00;
        } else if (value <= 30d) {
            return 0x65ff00;
        } else if (value <= 31.25d) {
            return 0x7fff00;
        } else if (value <= 32.5d) {
            return 0x99ff00;
        } else if (value <= 33.75d) {
            return 0xb2ff00;
        } else if (value <= 35d) {
            return 0xccff00;
        } else if (value <= 36.25d) {
            return 0xe5ff00;
        } else if (value <= 37.5d) {
            return 0xffff00;
        } else if (value <= 38.75d) {
            return 0xffe500;
        } else if (value <= 40d) {
            return 0xffcc00;
        } else if (value <= 41.25d) {
            return 0xffb200;
        } else if (value <= 42.5d) {
            return 0xff9900;
        } else if (value <= 43.75d) {
            return 0xff7f00;
        } else if (value <= 45d) {
            return 0xff6600;
        } else if (value <= 46.25d) {
            return 0xff4c00;
        } else if (value <= 47.5d) {
            return 0xff3200;
        } else if (value <= 48.75d) {
            return 0xff1900;
        } else if (value <= 50d) {
            return 0xff0000;
        } else if(value <=52.5d) {
            return 0xdd0000;
        } else if(value <=55d) {
            return 0xbc0000;
        } else if(value <=57.5d) {
            return 0xa30000;
        } else if(value <=60d) {
            return 0x870000;
        }
        else
            return 0x600000;
    }
}
