package chooseadventure.services;


import chooseadventure.data.models.session.Session;

public interface ActionService {

    Session execute(Session session, String subject);
}
