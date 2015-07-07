package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import play.libs.Akka;

import java.util.HashMap;
import java.util.Map;

public class ChatManager extends UntypedActor{

    //private final ActorRef out;
    private Map chats;

    //public static Props props(ActorRef out) {
    //    return Props.create(ChatManager.class, out);
    //}

    public ChatManager() {
        chats = new HashMap<String,ActorRef>();
        //this.out = out;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            //System.out.println("El chatManager recibe: "+message);
            if (!chats.containsKey(message)){
                chats.put(message, Akka.system().actorOf(Props.create(Chat.class)));
            }
            getSender().tell(chats.get(message), getSelf());
        }
    }
}
