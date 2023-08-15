import java.util.Random;

class Main {
    static char mazeWayObject = '1';
    static char mazeWallObject = '0';
    static char mazePlayerObject = 'X';
    static char[][] maze = new char[][] {
            { mazeWayObject, mazeWayObject, mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWayObject, mazeWayObject, mazeWayObject, mazeWayObject, mazeWayObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject, mazeWallObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject, mazeWayObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWallObject, mazeWallObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject, mazeWallObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWallObject,
                    mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject, mazeWayObject,
                    mazeWayObject, mazeWayObject, mazeWayObject, mazeWallObject },
            { mazeWallObject, mazeWallObject, mazeWallObject, mazeWallObject, mazeWayObject, mazeWayObject,
                    mazeWayObject, mazeWayObject, mazeWayObject, mazeWayObject }
    };

    static int playerCurrentCoordinateX = -1;
    static int playerCurrentCoordinateY = 0;

    public static void main(String[] args) throws InterruptedException {
        /* For Random Map */
        initMazeMap();
        playerInit();

        int directionX = 1;
        int directionY = 1;
        while (!findCorrectWay()) {
            Thread.sleep(2000);

            if (!moveX(directionX)) {
                directionX = -1;
                if (!moveY(directionY)) {
                    directionY = -1;
                } else {
                    directionX = 1;
                }
            } else {
                directionY = 1;
            }

            if (directionX > 0 || directionY > 0) {
                printCurrentState();
            } else {
                maze[playerCurrentCoordinateY][playerCurrentCoordinateX] = mazeWallObject;
            }
        }

        System.out.println("********************************************");
        System.out.println("Tebrikler cikisa ulastiniz!");
        System.out.println("********************************************");
    }

    private static boolean moveX(int direction) {
        boolean isCorrectWay = false;

        if (direction < 0) {
            if (playerCurrentCoordinateX > 0) {
                if (maze[playerCurrentCoordinateY][playerCurrentCoordinateX - 1] == mazeWayObject) {
                    playerCurrentCoordinateX += direction;
                    isCorrectWay = true;
                }
            }
        } else if (direction > 0) {
            if (playerCurrentCoordinateX < 9) {
                if (maze[playerCurrentCoordinateY][playerCurrentCoordinateX + 1] == mazeWayObject) {
                    playerCurrentCoordinateX += direction;
                    isCorrectWay = true;
                }
            }
        }

        return isCorrectWay;
    }

    private static boolean moveY(int direction) {
        boolean isCorrectWay = false;

        if (direction < 0) {
            if (playerCurrentCoordinateY > 0) {
                if (maze[playerCurrentCoordinateY + direction][playerCurrentCoordinateX] == mazeWayObject) {
                    playerCurrentCoordinateY += direction;
                    isCorrectWay = true;
                }
            }
        } else if (direction > 0) {
            if (playerCurrentCoordinateY < 9) {
                if (maze[playerCurrentCoordinateY + direction][playerCurrentCoordinateX] == mazeWayObject) {
                    playerCurrentCoordinateY += direction;
                    isCorrectWay = true;
                }
            }
        }

        return isCorrectWay;
    }

    private static void playerInit() {
        for (int y = playerCurrentCoordinateY; y < maze.length; y++) {
            if (maze[y][0] == mazeWayObject) {
                playerCurrentCoordinateY = y;
                playerCurrentCoordinateX = 0;
                break;
            }
        }
        printCurrentState();
    }

    private static boolean findCorrectWay() {
        return playerCurrentCoordinateX == 9
                && maze[playerCurrentCoordinateY][playerCurrentCoordinateX] == mazeWayObject;
    }

    private static void printCurrentState() {
        System.out.println("********************************************");
        System.out.println("currentX: " + playerCurrentCoordinateX + " currentY: " + playerCurrentCoordinateY);
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (playerCurrentCoordinateY == y && playerCurrentCoordinateX == x) {
                    System.out.print(mazePlayerObject + " ");
                } else {
                    System.out.print(maze[y][x] + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("********************************************");
    }

    private static void initMazeMap() {
        Random random = new Random();
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                maze[x][y] = random.nextDouble() < 0.7 ? mazeWayObject : mazeWallObject;
            }
        }

        maze[random.nextInt(maze.length)][0] = mazeWayObject;
        maze[random.nextInt(maze.length)][maze[0].length - 1] = mazeWayObject;
    }
}