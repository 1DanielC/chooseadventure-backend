package chooseadventure.data.model.session;

import chooseadventure.data.entity.CardinalDirection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import chooseadventure.data.entity.Room;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Session {

    private String scenario;
    private String dialog;
    private Player player;
    private Room room;

    public Session() {
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario){
        this.scenario = scenario;
    }

    public void addToScenario(String scenario){
        this.scenario += " " + scenario + ".";
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Session toResource(){
        Session session = new Session();
        session.setScenario(scenario);
        session.setDialog(dialog);
        session.setRoom(room.toResource());
        session.setPlayer(player.toResource());


        return session;
    }

}
