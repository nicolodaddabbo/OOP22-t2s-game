package it.unibo.t2sgame.view.api;

import it.unibo.t2sgame.model.api.Component;

public interface GraphicComponent extends Component {
    
    /**
     * This method receives the graphic that the Entity is going to use
     * @param graphic 
     */
    void setGraphics(Graphic graphic);
}
