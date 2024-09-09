package tn.esprit.vromvrom;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.vromvrom.Model.Conversation;
import tn.esprit.vromvrom.Model.Messages;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceConversation;
import tn.esprit.vromvrom.service.ServiceMessages;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChatUrgenceController implements Initializable {

    @FXML
    private ListView<String> chatHistory ;
    @FXML
    private TextArea messageInput ;

    @FXML
    private Button sendButton ;

    @FXML
    private TextField idConv;


    // je doit la modifier lors de l'integration du travaille

//    Role role1 = new Role(1,"chauffeur");
//    Role role2 = new Role(2,"admin");
//
//    //l'admin
//   // user user1 = new user(role2,"rzem","mohamed","med@gmail.com","meddooo","12345678","hihihi");
//    //le chauffeur
//    User user2 = new User(role1,"ali","ali","@ali","alilouuuu","918273645","disp");

    //user user9 = new user();



    
    Conversation conversation = new Conversation(6);



    ServiceMessages serviceMessages =new ServiceMessages();
    List<Messages> l = serviceMessages.selectAll();



    public ChatUrgenceController() throws SQLException {
        }





//
//    public void TheUser(User u){
//        user2=u;
//        //System.out.println(user2);
//        //chatHistory.refresh();
//
//    }

    public void TheConversationId(Conversation conversation){
        this.conversation.setId(conversation.getId());
    }







    public boolean verif_Existance_Conver(){
        for (int i =0;i<l.size();i++){
            if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(User.connecte.getNomd()) && l.get(i).getConversation().getHeureFin() == null){
                return true;
            }
        }
        return false;
    }


    @FXML
        public void Envoyer () throws SQLException {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()) {
                // Send message to chat server
               // chatHistory.getItems().add(message);
                messageInput.clear();
                if (!User.connecte.getId_role().getRole().equals("Admin")) {
                    boolean verif = verif_Existance_Conver();
                    if (verif == true) {
                        for (int i = 0; i < l.size(); i++) {
                            if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(User.connecte.getNomd()) && l.get(i).getConversation().getHeureFin() == null) {
                                Messages messages = new Messages(l.get(i).getConversation(), User.connecte.getNomd(), "Admin", message);
                                serviceMessages.createOne(messages);
                                chatHistory.getItems().clear();
                                //chatHistory.refresh();
                                l = serviceMessages.selectAll();
                                afficherMessage();
                                chatHistory.refresh();
                                break;
                            }
                        }
                    } else {
                        Conversation conversation = new Conversation(User.connecte);
                        ServiceConversation serviceConversation = new ServiceConversation();
                        serviceConversation.createOne(conversation);
                        List<Conversation> listConv = serviceConversation.selectAll();
                        Messages messages = new Messages(listConv.get(0), User.connecte.getNomd(), "Admin", message);
                        System.out.println(listConv);
                        serviceMessages.createOne(messages);
                        chatHistory.getItems().clear();
                        //chatHistory.refresh();
                        l = serviceMessages.selectAll();
                        afficherMessage();
                        chatHistory.refresh();
                    }
                }else {
                    for (int i=0;i<l.size();i++){
                        if (l.get(i).getConversation().getId()==conversation.getId()){
                            String client = l.get(i).getExpediteur();
                            Messages messages = new Messages(conversation,"Admin",client,message);
                            serviceMessages.createOne(messages);
                            chatHistory.getItems().clear();
                            //chatHistory.refresh();
                            l = serviceMessages.selectAll();
                            afficherMessage();
                            chatHistory.refresh();

                            break;
                        }
                    }

                }



            }
        };




    //l.get(i).getConversation().getUtilisateur1().getNomd().equals(nom) &&
    public void afficherMessage(){
        if (!User.connecte.getId_role().getRole().equals("Admin")) {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).getConversation().getUtilisateur1().getNomd().equals(User.connecte.getNomd()) && l.get(i).getConversation().getHeureFin() == null) {

                    for (int j = 0; j < l.size(); j++) {
                        if (l.get(i).getConversation().getId() == l.get(j).getConversation().getId()) {
                            chatHistory.getItems().add(l.get(j).getExpediteur() + "\t\t\t\t" + l.get(j).getTexte() + "\t\t\t\t\t\t\t\t\t\t" + l.get(j).getHeureEnvoi());
                        }
                    }
                    break;
                }
            }
        }else {
            //Conversation conversation = new Conversation(Integer.valueOf(idConv.getText()));
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).getConversation().getId()==conversation.getId() && l.get(i).getConversation().getHeureFin() == null) {
                    chatHistory.getItems().add(l.get(i).getExpediteur() + "\t\t\t\t" + l.get(i).getTexte() + "\t\t\t\t\t\t\t\t\t\t" + l.get(i).getHeureEnvoi());
                }
            }
        }
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        //System.out.println(l);
        //System.out.println(l.get(2).getId());
        //afficherMessage();
        //System.out.println(l.get(0).getConversation().getUtilisateur1().getNomd());
/*
        sendButton.setOnMouseClicked((MouseEvent event) -> {
            String message = messageInput.getText().trim();
            if (!message.isEmpty()){
                if (event.getClickCount() > 0) {
                    chatHistory.getItems().clear();
                    //afficherMessage();
                }
            }
        });

 */





    }
}







