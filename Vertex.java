/**
 * The Vertex class is a subclass of the Matrix class, where the width is 1 and the height is usually 4
 * This class is used to store homogeneous coordinates, in the form (x, y, z, w). 
 * 
 * @author Milian Ingco
 * @version 1.0
 */

public class Vertex extends Matrix{

    /**
     * The default constructor initializes a vertex with all values set to 0
     * (0, 0, 0, 0)
     */
    public Vertex() {
        super(1, 4);
        for(int i = 0; i < 3; i++) {
            arr[i][0] = 0;
        }
    }

    /**
     * Initializes a vertex with a specified height
     * @param height The number of entries in the vertex
     */
    public Vertex(int height) {
        super(1, height);
        for(int i = 0; i < height; i++) {
            arr[i][0] = 0;
        }
    }

    /**
     * Initializes the vertex with values (x, y, z, 1)
     * @param x The x-coordinate of the vertex
     * @param y The y-coordinate of the vertex
     * @param z The z-coordinate of the vertex
     */
    public Vertex(double x, double y, double z) {
        super(1, 4);
        arr[0][0] = x;
        arr[1][0] = y;
        arr[2][0] = z;
        arr[3][0] = 1;
    }

    /**
     * Initializes the vertex with values (x, y, z, a)
     * @param x The x-coordinate of the vertex
     * @param y The y-coordinate of the vertex
     * @param z The z-coordinate of the vertex
     * @param a Fourth entry
     */
    public Vertex(double x, double y, double z, double a) {
        super(1, 4);
        arr[0][0] = x;
        arr[1][0] = y;
        arr[2][0] = z;
        arr[3][0] = a;
    }
    
    /**
     * Divides each component of this vertex by the fourth value, w, if w != 0
     * @param v
     * @return Returns a vertex with all values divided by Z
     */
    public Vertex divideZ(Vertex v) {
        if(v.arr[3][0] != 0) {
            return new Vertex(
                v.arr[0][0] / v.arr[3][0], 
                v.arr[1][0] / v.arr[3][0], 
                v.arr[2][0] / v.arr[3][0]);
        } else {
            return v;
        }
    }

    /**
     * Scales each parameter of the vertex to the screen's width and height
     * @param width The width of the screen in pixels
     * @param height The height of the screen in pixels
     */
    public void scaleVertex(double width, double height) {
        arr[0][0] *= 0.5 * width;
        arr[1][0] *= 0.5 * height;
    }

    /**
     * Uses the perspective projection matrix to transform this vertex to a vertex that will be drawn onto the screen
     * @return Returns a transformed vertex that can be drawn onto the screen
     */
    public Vertex perspectiveTransform() {
        return divideZ(Matrix.getPersProjMatrix().multiply(this));
    }

    /**
     * Prints the vertex to the console
     */
    public void printVertex() {
        for(int i = 0; i < height; i++) {
            System.out.println(arr[i][0]);
        }
    }

}
