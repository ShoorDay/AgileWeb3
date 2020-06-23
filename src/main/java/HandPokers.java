import java.util.*;
import java.util.regex.Pattern;

public class HandPokers {
    private List<Poker> pokers;
    Boolean flush = true;//同花
    Boolean straight = true;//顺子
    Boolean onePair = false;//一对
    Boolean twoPair = false;//两对
    Boolean threeOfAKind = false;//三条

    private int num1, num2;

    HandPokers(String patten) throws Exception {
        //patten.trim();
        if (!Pattern.matches("([2-9TJQKA][HDSC] ){4}([2-9TJQKA][HDSC])",patten)){
            throw new Exception("输入格式有误！！！");
        }
        pokers = new ArrayList<>();
        for(int i = 0; i < 10; i += 2){
            pokers.add(new Poker(Color.getColor(patten.charAt(i)),Number.getNumber(patten.charAt(++i))));
        }
        Collections.sort(pokers);
        load();
    }

    public HandPokers(String pattern) throws Exception {
    }

    public Boolean vs(HandPokers opponent) {
        int thisLargeNumber = this.pokers.get(this.pokers.size() - 1).getNumber().ordinal();
        int opponentLargeNumber = opponent.pokers.get(opponent.pokers.size() - 1).getNumber().ordinal();
        if (flush && straight){
            if (opponent.flush && opponent.straight){
                return compareLarge(thisLargeNumber,opponentLargeNumber);
            }else {
                return true;
            }
        }

        if (flush){
            if (opponent.flush){
                return compareAll(this.pokers, opponent.pokers);
            }else {
                return true;
            }
        }

        if (straight){
            if (opponent.straight){
                return compareLarge(thisLargeNumber,opponentLargeNumber);
            }else {
                return true;
            }
        }

        if (threeOfAKind){
            if(opponent.threeOfAKind){
                return compareLarge(this.num1,opponent.num1);
            }else {
                return true;
            }
        }

        if (twoPair){
            if (opponent.twoPair){
                if(compareLarge(this.num2, opponent.num2) != null){
                    return compareLarge(this.num2, opponent.num2);
                }else if(compareLarge(this.num1, opponent.num1) != null){
                    return compareLarge(this.num1, opponent.num1);
                }else {
                    return compareAll(this.pokers, opponent.pokers);
                }
            }else {
                return true;
            }
        }

        if (onePair){
            if (opponent.onePair){
                if(compareLarge(this.num2, opponent.num2) != null){
                    return compareLarge(this.num2, opponent.num2);
                }else {
                    return compareAll(this.pokers, opponent.pokers);
                }
            }else {
                return true;
            }
        }

        return compareAll(this.pokers, opponent.pokers);
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

    public void load() {
        Color upColor = pokers.get(0).getColor();
        int upNumber = -1;
        int nowNumber = 0;
        boolean sameNumber = false;
        for(Poker temp : pokers){

            //判断同花
            if (upColor != temp.getColor()){
                flush = false;
                break;
            }

            //判断顺子
            if(upNumber == -1){
                upNumber = temp.getNumber().ordinal();
            }else {
                nowNumber = temp.getNumber().ordinal();
                if ((upNumber + 1) != nowNumber){
                    straight = false;
                    break;
                }
                upNumber = nowNumber;
            }

            //判断一对
            if(upNumber == nowNumber){
                onePair = true;
                sameNumber = true;
                num1 = nowNumber;
            }

            //判断两对
            if(onePair && upNumber == nowNumber){
                twoPair = true;
                num2 = nowNumber;
            }

            //判断三条
            if (sameNumber == true){
                if (upNumber == nowNumber){
                    threeOfAKind = true;
                    num1 = nowNumber;
                }else {
                    sameNumber = false;
                }
            }
        }
    }

}
