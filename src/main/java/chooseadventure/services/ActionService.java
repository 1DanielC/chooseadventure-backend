package chooseadventure.services;


import chooseadventure.data.model.session.Session;

public interface ActionService {

    Session execute(Session session, String subject);
}
