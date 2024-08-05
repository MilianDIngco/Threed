public class Test {
    public static void main(String[] args) {
        Matrix.initPersProjMatrix(100, 10, 1);

        Model model = new Model(new Vertex[]{
            new Vertex(-0.5, 0.5, 0),
            new Vertex(0.5, 0.5, 0),
            new Vertex(0.5, -0.5, 0),
            new Vertex(-0.5, -0.5, 0),
            new Vertex(-0.5, 0.5, 2),
            new Vertex(0.5, 0.5, 2),
            new Vertex(0.5, -0.5, 2),
            new Vertex(-0.5, -0.5, 2)
        }, new int[][]{
            {1, 3, 4}, {1, 2, 3},
            {2, 7, 3}, {2, 6, 7},
            {6, 8, 7}, {6, 5, 8},
            {5, 4, 8}, {5, 1, 4}
        });

        model.drawHelper();

        Matrix a = new Matrix(new double[][] {
            {1, 2, 3, 4},
            {4, 5, 6, 4},
            {7, 8, 9, 4},
            {4, 3, 2, 5}
        });

        Vertex b = new Vertex(-.4, 0.4, 2);
        Vertex c = Matrix.getRotateXMatrix(100).multiply(b);
        c.printVertex();
        c = Matrix.getRotateXMatrix(0).multiply(c);
        c.printVertex();

    }
}
