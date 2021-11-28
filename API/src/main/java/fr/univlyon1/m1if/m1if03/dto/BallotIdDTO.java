package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;

import java.util.ArrayList;
import java.util.Map;

public class BallotIdDTO extends ArrayList<String> {

//   private String reprBallot;

    public BallotIdDTO(final int ballotId, final String rootUrl){
                add(rootUrl.concat("/vote/").concat(Integer.toString(ballotId)));


    }

//    public void setReprBallot(String reprBallot) {
//        this.reprBallot = reprBallot;
//    }
//
//    public String getReprBallot() {
//        return reprBallot;
//    }

//    public String getBallotIdDTO(final Map<String, Ballot> ballots, final String nomUser, final String rootUrl) {
//        int i = -1;
//        String ballotId = null;
//        for (String user : ballots.keySet()) {
//            i++;
//            if (user.equals(nomUser)){
//                ballotId =  rootUrl.concat("/vote/").concat(Integer.toString(i));
//            }
//        }
//        return ballotId;
//    }
}
//
//for (int i = 0; i < ballots.size(); i++) {
//        add(rootUrl.concat("/ballots/").concat(Integer.toString(i)));
//        }
