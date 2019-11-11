package chooseadventure.data.models.session;

import chooseadventure.data.entities.CardinalDirection;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    private String name;

    private CardinalDirection directionFacing;

    public Player() {}

    public Player(String name) { this.name = name; }

    public String getName(){ return name;}
}
