package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;

import java.util.ArrayList;
import java.util.Map;

public class BallotByUserDTO extends ArrayList<String> {
    public BallotByUserDTO(Map<String, Ballot> ballots, final String userId, final String rootUrl, final boolean faceUp){
        String key = null;
        for (int i = 0; i< ballots.size() ; i++){
            key = (String) ballots.keySet().toArray()[i];
            if (key.equals(userId)){
                if (faceUp) {
                    add(rootUrl.concat("/ballots/").concat(Integer.toString(i)));
                }else {
                    add(rootUrl.concat("/votes/").concat(Integer.toString(i)));
                }
            }
        }



    }
}
