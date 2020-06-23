public enum Number {

    n2,n3,n4,n5,n6,n7,n8,n9,nT,nJ,nQ,nK,nA;

    public static Number getNumber(char letter){
        switch (letter){
            case '2':
                return Number.n2;
            case '3':
                return Number.n3;
            case '4':
                return Number.n4;
            case '5':
                return Number.n5;
            case '6':
                return Number.n6;
            case '7':
                return Number.n7;
            case '8':
                return Number.n8;
            case '9':
                return Number.n9;
            case 'T':
                return Number.nT;
            case 'J':
                return Number.nJ;
            case 'Q':
                return Number.nQ;
            case 'K':
                return Number.nK;
            case 'A':
                return Number.nA;
        }
        return null;
    }
}
