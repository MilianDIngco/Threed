import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Model {
    private Vertex[] vertices;
    private int[][] edges;

    private Vertex center; 

    Vertex[] nVertices;

    public Model() {
        vertices = new Vertex[0];
        edges = new int[0][];

        nVertices = new Vertex[0];
    }

    public Model(Vertex[] vertices, int[][] edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.nVertices = new Vertex[vertices.length];
    }

    public void setCenter(Vertex v) {
        center = v;
    }

    public void drawHelper() {
        for(int i = 0; i < vertices.length; i++) {
            nVertices[i] = vertices[i].perspectiveTransform();
            nVertices[i].scaleVertex(App.screenSize.getWidth(), App.screenSize.getHeight());
        }
    }

    public void intrinsicTransform(Vertex centerOfRotation, double thetaXDegrees, double thetaYDegrees, double thetaZDegrees, double x, double y, double z) {
        
        for(int i = 0; i < vertices.length; i++) {
            if(thetaXDegrees != 0 || thetaYDegrees != 0 || thetaZDegrees != 0) {

                vertices[i] = Matrix.getTranslateMatrix(-centerOfRotation.arr[0][0], -centerOfRotation.arr[1][0], -centerOfRotation.arr[2][0]).multiply(vertices[i]);

                if(thetaXDegrees != 0) {
                    
                    vertices[i] = Matrix.getRotateXMatrix(thetaXDegrees).multiply(vertices[i]);
                    
                }

                if(thetaYDegrees != 0) {
                    vertices[i] = Matrix.getRotateYMatrix(thetaYDegrees).multiply(vertices[i]);
                    
                }
                if(thetaZDegrees != 0) {
                    vertices[i] = Matrix.getRotateZMatrix(thetaZDegrees).multiply(vertices[i]);
                }

                vertices[i] = Matrix.getTranslateMatrix(centerOfRotation.arr[0][0], centerOfRotation.arr[1][0], centerOfRotation.arr[2][0]).multiply(vertices[i]);

            }
        }

        if(x != 0 || y != 0 || z != 0) {
            for(int i = 0; i < vertices.length; i++) {
                vertices[i] = Matrix.getTranslateMatrix(x, y, z).multiply(vertices[i]);
            }
        }
    }

    public void extrinsicTransform() {

    }

    public void updateModel(KeyHandler keyh, Mouse mouse) {
        intrinsicTransform(center, .2, .1, .05, 0, 0, 0);
        //extrinsicTransform();
        drawHelper();
    }

    public void drawModel(Graphics2D g2) {
        Path2D path = new Path2D.Double();
        for(int i = 0; i < edges.length; i++) {
            path.moveTo(nVertices[edges[i][0]].arr[0][0], nVertices[edges[i][0]].arr[1][0]);
            path.lineTo(nVertices[edges[i][1]].arr[0][0], nVertices[edges[i][1]].arr[1][0]);
            path.lineTo(nVertices[edges[i][2]].arr[0][0], nVertices[edges[i][2]].arr[1][0]);
            path.closePath();
            g2.draw(path);
        }
    }

}
 