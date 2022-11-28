package skademaskinen.Commands;

import java.util.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import skademaskinen.Utils.Loggable;

public interface Command extends Loggable {
    public boolean isSuccess();
    default public boolean isEphemeral(){
        return true;
    }
    default public boolean shouldDefer(){
        return true;
    }
    public List<ActionRow> getActionRows();

    public Object run(SlashCommandInteractionEvent event);
    public default Object execute(SlashCommandInteractionEvent event){
        Object response = run(event);
        log(isSuccess(),  new String[]{
            "User: "+event.getUser().getAsTag(),
            "Guild: "+event.getGuild().getName(),
        });
        return response;
    }

    default public Object ModalExecute(){
        return null;
    }

    default public Object ButtonExecute(){
        return null;
    }
    default public String buildButtonId(String id, String data){
        if(data != null) return this.getClass().getSimpleName()+"::"+id+"::"+data;
        else return this.getClass().getSimpleName()+"::"+id;
    }

}
