package chooseadventure.data.models.command;

public class Command {
    private Action action;
    private String subject;

    public Command(String action, String subject) {
        this.action = Action.valueOf(action);
        this.subject = subject;
    }

    public Action getAction() {return action;}

    public String getSubject() {return subject;}
}
