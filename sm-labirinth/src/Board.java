/**
 * Class Board (agent)
 * @author David Fernandes <david.paiva.fernandes@gmail.com>
 * @author João Marques <joaorodr84@gmail.com>
 */

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends Agent {

  private static final long serialVersionUID = 5L;
  private Labirinth _labirinth;
  private JFrame frame;

  @Override
  protected void setup() {
    System.out.println(String.format("LOG %s: Board is starting.", getAID().getName()));

    // registering the referee service
    DFAgentDescription dfd = new DFAgentDescription();
    dfd.setName(getAID());
    ServiceDescription sd = new ServiceDescription();
    sd.setType("labirinth_board");
    sd.setName("showoff");
    dfd.addServices(sd);
    try {
      DFService.register(this, dfd);
      System.out.println(String.format("LOG %s: Board registered.", getAID().getName()));

    } catch (FIPAException fe) {
      System.out.println(String.format("ERR %s: %s.", getAID().getName(), fe.getMessage()));
    }

    // setup a cyclic behaviour to listen to referee
    addBehaviour(new CyclicBehaviour() {
      private static final long serialVersionUID = 1L;

      @Override
      public void action() {
        ACLMessage msg = receive();
        if (msg != null) {
          String conversationId = msg.getConversationId();
          switch (conversationId) {
            case "lab":
              try {
                _labirinth = (Labirinth) msg.getContentObject();
                Graphics g = frame.getGraphics();
                _labirinth.Draw(g);
              } catch (UnreadableException ex) {
                System.out.println(String.format("ERR %s: %s", getAID().getName(), ex.getMessage()));
              }
              break;
            case "draw":
              if (_labirinth != null) {
                Graphics g = frame.getComponent(0).getGraphics();
                _labirinth.Draw(g);
                int k=0;
                for (String agent : msg.getContent().split("/")) {
                  String[] values = agent.split(";");
                  g.setColor(getColor(k++));
                  g.fillOval(Integer.parseInt(values[1])*20, Integer.parseInt(values[2])*20, 15, 15);
                }

              }
              break;
            case "end":
              doDelete();
              break;
          }

        }
      }
    });

    // show GUI
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        createAndShowGUI();
      }
    });

  }

  private Color getColor(int k) {
    if(k==0) {
      return Color.ORANGE;
    } else
    if(k==1) {
      return Color.CYAN;
    } else
    if(k==2) {
      return Color.GREEN;
    } else
    if(k==3) {
      return Color.RED;
    } else
    if(k==4) {
      return Color.MAGENTA;
    } else
    if(k==5) {
      return Color.PINK;
    } else
    if(k==6) {
      return Color.WHITE;
    } else {
      return Color.BLACK;
    }
    
  }
  
  @Override
  protected void takeDown() {
    // deregister
    try {
      DFService.deregister(this);
    } catch (FIPAException fe) {
      System.out.println(String.format("ERR %s: %s.", getAID().getName(), fe.getMessage()));
    }
    System.out.println(String.format("LOG %s: Board is terminating!", getAID().getName()));
  }

  private void createAndShowGUI() {

    frame = new JFrame("Labirinth board");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);
    panel.setBounds(50, 50, 400, 400);
    Container c = panel;
    c.setBackground(Color.GRAY);
    Dimension d = new Dimension(500, 500);
    c.setPreferredSize(d);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);

  }
}