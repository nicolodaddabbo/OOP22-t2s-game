package it.unibo.t2sgame.components.api;

import it.unibo.t2sgame.view.api.Graphic;

public interface GraphicComponent extends Component {
    
    /**
     * This method receives the graphic that the Entity is going to use
     * @param graphic 
     */
    void setGraphics(Graphic graphic);
}
