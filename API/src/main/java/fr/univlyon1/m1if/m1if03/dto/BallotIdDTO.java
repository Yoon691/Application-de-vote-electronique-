package fr.univlyon1.m1if.m1if03.dto;

import fr.univlyon1.m1if.m1if03.classes.Ballot;

import java.util.ArrayList;
import java.util.Map;

public class BallotIdDTO extends ArrayList<String> {


    public BallotIdDTO(final int ballotId, final String rootUrl){
        add(rootUrl.concat("/vote/").concat(Integer.toString(ballotId)));
    }
}

