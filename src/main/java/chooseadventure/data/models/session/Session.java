package chooseadventure.data.models.session;

import com.fasterxml.jackson.annotation.JsonInclude;
import chooseadventure.data.entities.Room;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Session {

    private String dialog;
    private String result;
    private Player player;
    private Room room;

    public Session() {
        this.player = new Player("");
        this.room = new Room(0, 0);
    }

    public Session(Room room) {
        player = new Player("");
        this.room =  room;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog){
        this.dialog = dialog;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
}
