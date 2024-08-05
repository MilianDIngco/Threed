import java.awt.Graphics2D;
import java.util.ArrayList;

public class ObjectManager {

    private ArrayList<Model> models;

    public ObjectManager() {
        models = new ArrayList<>();
    }

    public void addModel(Model model) {
        models.add(model);
    }

    public void updateModels(KeyHandler keyh, Mouse mouse) {
        for(int i = 0; i < models.size(); i++) {
            models.get(i).updateModel(keyh, mouse);
        }
    }

    public void drawModels(Graphics2D g2) {
        for(Model i : models) {
            i.drawModel(g2);
        }
    }

}
