package chooseadventure.data.model.resource;

import chooseadventure.data.entity.Room;
import chooseadventure.data.model.session.Player;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResource {
    private String scenario;
    private String dialog;
    private PlayerResource player;
    private RoomResource room;

}
