package chooseadventure.data.model.session;

import chooseadventure.data.entity.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import chooseadventure.data.entity.Room;

import java.util.Optional;
import java.util.stream.Collectors;

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

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public void addToScenario(String scenario) {
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

    public Session toResource() {
        Session session = new Session();
        session.setScenario(scenario);
        session.setDialog(dialog);
        session.setRoom(room.toResource());
        session.setPlayer(player.toResource());


        return session;
    }

    public String roomDescriptionWithItems() {
        String description = room.getDescription();
        if (description == null) {
            return "";
        }

        String itemDescs = room.getItems().stream()
                .filter(item -> !item.isHidden() && !player.hasItem(item))
                .map(Item::getEnvironmentDescription)
                .collect(Collectors.joining(". "));

        return description + " " + itemDescs;
    }

    public Optional<Item> findIfRoomHasItem(String item) {
        if(player.findItem(item).isPresent()) {
            return Optional.empty();
        }

        return room.getItems()
                .stream()
                .filter(i->i.getName().equalsIgnoreCase(item))
                .findFirst();
    }
}
