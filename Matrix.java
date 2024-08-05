/**
 * The Matrix class is used to create matrices and manipulate these matrices.
 * It also holds matrices that perform common transformations of 3D vertices
 * 
 * @author Milian Ingco
 * @version 2000000000.000000
 */

public class Matrix {
    
    int width, height;
    double[][] arr;

    private static Matrix PersProjMatrix;

    /**
     * Initializes a matrix with width and height 0
     */
    public Matrix() {
        width = 0;
        height = 0;
        arr = new double[0][0];
    }

    /**
     * Initializes a matrix with a width and height
     * @param width An integer value representing the number of columns in the matrix
     * @param height An integer value representing the number of rows in the matrix
     */
    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        arr = new double[height][width];
    }

    /**
     * Initializes a matrix with a 2D array
     * @param arr A 2D array of double values
     */
    public Matrix(double[][] arr) {
        this.arr = arr;
        this.width = arr[0].length;
        this.height = arr.length;
    }

    /**
     * @return Returns the number of columns in the matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Returns the number of rows in the matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Prints the representation of the matrix
     */
    public void printMatrix() {
        for(double[] i : arr) {
            for(double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * Multiplies this matrix with a matrix B
     * @param B A matrix with height equivalent to this matrix's width
     * @return Returns the product of the two matrices if the two are compatible, otherwise null
     */
    public Matrix multiply(Matrix B) {
        if(B.width == this.height) {
            Matrix temp = new Matrix(B.width, this.height);

            for(int row = 0; row < temp.height; row++) {
                for(int col = 0; col < temp.width; col++) {
                    for(int i = 0; i < this.width; i++) {
                        temp.arr[row][col] += this.arr[row][i] * B.arr[i][col];
                    }
                }
            }

            return temp;
        } else {
            return null;
        }
    }

    /**
     * Multiplies this matrix with a vertex B
     * @param B A vertex with height equivalent to the width of the matrix
     * @return Returns a vertex multiplied by this matrix or null if the vertex is incompatible with the matrix
     */
    public Vertex multiply(Vertex B) {
        if(B.height == this.width) {
            Vertex temp = new Vertex(B.height);

            for(int row = 0; row < temp.height; row++) {
                for(int i = 0; i < this.width; i++) {
                    temp.arr[row][0] += this.arr[row][i] * B.arr[i][0];
                }
            }

            return temp;
        } else {
            return null;
        }

    }

    /**
     * Initializes the perspective projection matrix given a vertical field of view and a near and far distance
     * @param FOV the vertical field of view
     * @param far the furthest rendering distance
     * @param near the distance from the camera to the screen
     */
    public static void initPersProjMatrix(double FOV, double far, double near) {
        PersProjMatrix = new Matrix(new double[][]{
            {1/((App.screenSize.getWidth() / App.screenSize.getHeight()) * Math.tan(Math.toRadians(FOV))), 0, 0, 0},
            {0, 1/Math.tan(Math.toRadians(FOV)), 0, 0},
            {0, 0, far/(far - near), -(far * near) / (far - near)},
            {0, 0, 1, 0}
        });
    }

    /**
     * @return Returns a matrix that when applied to a vertex, will shift a vertex to a point in the canonical volume. 
     */
    public static Matrix getPersProjMatrix() {
        return PersProjMatrix;
    }

    /**
     * @param thetaXDegrees A double value representing the amount the matrix will rotate the object in degrees around the X-Axis. 
     * @return Returns a matrix that when applied will rotate a point about the X axis
     */
    public static Matrix getRotateXMatrix(double thetaXDegrees) {
        return new Matrix(new double[][] {
            {1, 0, 0, 0},
            {0, Math.cos(Math.toRadians(thetaXDegrees)), -Math.sin(Math.toRadians(thetaXDegrees)), 0},
            {0, Math.sin(Math.toRadians(thetaXDegrees)), Math.cos(Math.toRadians(thetaXDegrees)), 0},
            {0, 0, 0, 1}
        });
    }

    /**
     * @param thetaYDegreesA A double value representing the amount the matrix will rotate the object in degrees around the Y-Axis. 
     * @return Returns a matrix that when applied will rotate a point about the Y axis
     */
    public static Matrix getRotateYMatrix(double thetaYDegrees) {
        return new Matrix(new double[][] {
            {Math.cos(Math.toRadians(thetaYDegrees)), 0, Math.sin(Math.toRadians(thetaYDegrees)), 0},
            {0, 1, 0, 0},
            {-Math.sin(Math.toRadians(thetaYDegrees)), 0, Math.cos(Math.toRadians(thetaYDegrees)), 0},
            {0, 0, 0, 1}
        });
    }

    /**
     * @param thetaZDegrees A double value representing the amount the matrix will rotate the object in degrees around the Z-Axis.
     * @return Returns a matrix that when applied will rotate a point about the Z axis
     */
    public static Matrix getRotateZMatrix(double thetaZDegrees) {
        return new Matrix(new double[][] {
            {Math.cos(Math.toRadians(thetaZDegrees)), -Math.sin(Math.toRadians(thetaZDegrees)), 0, 0},
            {Math.sin(Math.toRadians(thetaZDegrees)), Math.cos(Math.toRadians(thetaZDegrees)), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        });
    }

    /**
     * @param x A double value that represents the amount the matrix will shift in the x direction
     * @param y A double value that represents the amount the matrix will shift in the y direction
     * @param z A double value that represents the amount the matrix will shift in the z direction
     * @return Returns a matrix that when applied will translate a point by a vector with the values <x, y, z>
     */
    public static Matrix getTranslateMatrix(double x, double y, double z) {
        return new Matrix(new double[][] {
            {1, 0, 0, x},
            {0, 1, 0, y},
            {0, 0, 1, z},
            {0, 0, 0, 1}
        });
    }

    /**
     * Rotates a vertex about the X value of given x, y, and z values
     * @param thetaXDegrees A double value representing the amount the matrix will rotate the object in degrees around the X point. 
     * @param x The x coordinate of the point the user will shift the vertex around
     * @param y The y coordinate of the point the user will shift the vertex around
     * @param z The z coordinate of the point the user will shift the vertex around
     * @return Returns a matrix that when applied will rotate a vertex around a given vector with the values <x, y, z>
     */
    public static Matrix getRotateXMatrix(double thetaXDegrees, double x, double y, double z) {
        return getTranslateMatrix(x, y, z).multiply(getRotateXMatrix(thetaXDegrees).multiply(getTranslateMatrix(-x, -y, -z)));
    }

    /**
     * Rotates a vertex about the X value of a given vertex
     * @param thetaXDegrees A double value representing the amount the matrix will rotate the object in degrees around the X point. 
     * @param v The vertex that the matrix will rotate around
     * @return Returns a matrix that when applied will rotate a vertex around a given vertex
     */
    public static Matrix getRotateXMatrix(double thetaXDegrees, Vertex v) {
        return getTranslateMatrix(v.arr[0][0], v.arr[1][0], v.arr[2][0]).multiply(getRotateXMatrix(thetaXDegrees).multiply(getTranslateMatrix(-v.arr[0][0], -v.arr[1][0], -v.arr[2][0])));
    }

    /**
     * Rotates a vertex about the Y value of a given coordinate
     * @param thetaYDegrees A double value representing the amount the matrix will rotate the object in degrees around the Y point. 
     * @param x The x coordinate of the point the user will shift the vertex around
     * @param y The y coordinate of the point the user will shift the vertex around
     * @param z The z coordinate of the point the user will shift the vertex around
     * @return Returns a matrix that when applied will rotate a vertex around a given vector with the values <x, y, z>
     */
    public static Matrix getRotateYMatrix(double thetaYDegrees, double x, double y, double z) {
        return getTranslateMatrix(x, y, z).multiply(getRotateYMatrix(thetaYDegrees).multiply(getTranslateMatrix(-x, -y, -z)));
    }

    /**
     * Rotates a vertex about the Y value of a given vertex
     * @param thetaXDegrees A double value representing the amount the matrix will rotate the object in degrees around the Y point. 
     * @param v The vertex that the matrix will rotate around
     * @return Returns a matrix that when applied will rotate a vertex around a given vertex
     */
    public static Matrix getRotateYMatrix(double thetaYDegrees, Vertex v) {
        return getTranslateMatrix(v.arr[0][0], v.arr[1][0], v.arr[2][0]).multiply(getRotateYMatrix(thetaYDegrees).multiply(getTranslateMatrix(-v.arr[0][0], -v.arr[1][0], -v.arr[2][0])));
    }

    /**
     * Rotates a vertex about the Z value of a given coordinate
     * @param thetaZDegrees A double value representing the amount the matrix will rotate the object in degrees around the Z point. 
     * @param x The x coordinate of the point the user will shift the vertex around
     * @param y The y coordinate of the point the user will shift the vertex around
     * @param z The z coordinate of the point the user will shift the vertex around
     * @return Returns a matrix that when applied will rotate a vertex around a given vector with the values <x, y, z>
     */
    public static Matrix getRotateZMatrix(double thetaZDegrees, double x, double y, double z) {
        return getTranslateMatrix(x, y, z).multiply(getRotateZMatrix(thetaZDegrees).multiply(getTranslateMatrix(-x, -y, -z)));
    }

    /**
     * Rotates a vertex about the Z value of a given vertex
     * @param thetaXDegrees A double value representing the amount the matrix will rotate the object in degrees around the X point. 
     * @param v The vertex that the matrix will rotate around
     * @return Returns a matrix that when applied will rotate a vertex around a given vertex
     */
    public static Matrix getRotateZMatrix(double thetaZDegrees, Vertex v) {
        return getTranslateMatrix(v.arr[0][0], v.arr[1][0], v.arr[2][0]).multiply(getRotateZMatrix(thetaZDegrees).multiply(getTranslateMatrix(-v.arr[0][0], -v.arr[1][0], -v.arr[2][0])));
    }

}
