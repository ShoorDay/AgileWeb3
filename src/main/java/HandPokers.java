import java.util.*;
import java.util.regex.Pattern;

public class HandPokers {
    private List<Poker> pokers;
    Boolean flush = true;
    Boolean straight = true;
    Boolean onePair = false;
    Boolean twoPair = false;
    Boolean threeOfAKind = false;

    private int num1, num2;

    HandPokers(String patten) throws Exception {

        if (!Pattern.matches("([2-9TJQKA][HDSC] ){4}([2-9TJQKA][HDSC])",patten)){
            throw new Exception("your pattern is wrong");
        }
        patten = patten.replace(" ","");
        pokers = new ArrayList<>();
        for(int i = 0; i < 10; i += 2){
            char a = patten.charAt(i);
            Color color = Color.getColor(patten.charAt(i));
            pokers.add(new Poker(Color.getColor(patten.charAt(i + 1)),Number.getNumber(patten.charAt(i))));
        }
        Collections.sort(pokers);
        load();
    }

    private static int getRank(HandPokers handPokers){
        if (handPokers.flush && handPokers.straight){
            return 1;
        }else if (handPokers.flush){
            return 2;
        }else if (handPokers.straight){
            return 3;
        }else if (handPokers.threeOfAKind){
            return 4;
        }else if (handPokers.twoPair){
            return 5;
        }else if (handPokers.onePair){
            return 6;
        }else {
            return 7;
        }
    }

    public Boolean vs(HandPokers opponent) {

        int thisRank = getRank(this);
        int opponentRank = getRank(opponent);
        if (thisRank < opponentRank){
            return true;
        } else if (thisRank > opponentRank) {
            return false;
        }else {
            int thisLargeNumber = this.pokers.get(this.pokers.size() - 1).getNumber().ordinal();
            int opponentLargeNumber = opponent.pokers.get(opponent.pokers.size() - 1).getNumber().ordinal();

            switch (thisRank){
                case 1:
                case 3:
                    return compareLarge(thisLargeNumber,opponentLargeNumber);
                case 2:
                    return compareAll(this.pokers, opponent.pokers);
                case 4:
                    return compareLarge(this.num1,opponent.num1);
                case 5:
                    if(compareLarge(this.num2, opponent.num2) != null){
                        return compareLarge(this.num2, opponent.num2);
                    }else if(compareLarge(this.num1, opponent.num1) != null){
                        return compareLarge(this.num1, opponent.num1);
                    }else {
                        return compareAll(this.pokers, opponent.pokers);
                    }
                case 6:
                    if(compareLarge(this.num2, opponent.num2) != null){
                        return compareLarge(this.num2, opponent.num2);
                    }else {
                        return compareAll(this.pokers, opponent.pokers);
                    }
            }
            return compareAll(this.pokers, opponent.pokers);
        }

    }

    private Boolean compareAll(List<Poker> thisPokers, List<Poker> opponentPokers){
        Boolean trial;
        for (int i = 4; i >= 0; i--){
            if ((trial = compareLarge(thisPokers.get(i).getNumber().ordinal(),opponentPokers.get(i).getNumber().ordinal())) != null){
                return trial;
            }
        }
        return null;
    }

    private Boolean compareLarge(int thisNumber,int opponentNumber){
        if(opponentNumber < thisNumber){
            return true;
        }else if(opponentNumber > thisNumber){
            return false;
        }else {
            return null;
        }
    }

    private void load() {
        Color upColor = pokers.get(0).getColor();
        int upNumber = -1;
        int nowNumber = 0;
        boolean sameNumber = false;
        for(Poker temp : pokers){

            //judge flush
            if (upColor != temp.getColor()){
                flush = false;
            }

            //judge straight
            if(upNumber == -1){
                upNumber = temp.getNumber().ordinal();
                continue;
            }else {
                nowNumber = temp.getNumber().ordinal();
                if ((upNumber + 1) != nowNumber){
                    straight = false;
                }
            }

            //judge onePair
            if(upNumber == nowNumber){
                onePair = true;
                sameNumber = true;
                num1 = nowNumber;
            }

            //judge twoPair
            if(onePair && upNumber == nowNumber){
                twoPair = true;
                num2 = nowNumber;
            }

            //judge threeOfKind
            if (sameNumber == true){
                if (upNumber == nowNumber){
                    threeOfAKind = true;
                    num1 = nowNumber;
                }else {
                    sameNumber = false;
                }
            }
            upNumber = nowNumber;
        }
    }

}
