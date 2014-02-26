/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.labirinth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author david.paiva.fernandes@gmail.com
 * @author joaorodr84@gmail.com
 */
public class Referee extends jade.core.Agent {

  private static final long serialVersionUID = 1L;

  private Labirinth _labirinth;
  private List<Agent> _agents;

  public Referee(String labirinthFilename) throws IOException {
    this._labirinth = new Labirinth(labirinthFilename);
    this._agents = new ArrayList<Agent>();
  }

  public List<Agent> getAgents() {
    return Collections.unmodifiableList(_agents);
  }

  public Labirinth getLabirinth() {
    return _labirinth;
  }

}
