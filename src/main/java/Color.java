public enum Color {

    cD,cS,cH,cC;

    public static Color getColor(char letter){
        switch (letter){
            case 'D':
                return Color.cD;
            case 'S':
                return Color.cS;
            case 'H':
                return Color.cH;
            case 'C':
                return Color.cC;
        }
        return null;
    }
}
