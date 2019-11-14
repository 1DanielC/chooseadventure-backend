package chooseadventure.data.model.session;

import chooseadventure.data.entity.CardinalDirection;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    private String name;

    private CardinalDirection directionFacing;

    public Player() {
        directionFacing = CardinalDirection.EAST;
        name = "An Unnamed Person";
    }

    public Player(String name) { this.name = name; }

    public String getName(){ return name;}

    public CardinalDirection getDirectionFacing() { return directionFacing; }

    public void setDirectionFacing(CardinalDirection directionFacing) {
        this.directionFacing = directionFacing;
    }

    public Player toResource() {
        return new Player(name);
    }
}
