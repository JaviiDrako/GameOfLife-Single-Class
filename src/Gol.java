public class Gol {

    static int width;
    static int height;
    static int generations;
    static int speed;
    static String population = "";
    static int neighborhood;
    static int[][] mainGrid;
    static int[][] nextGrid;
    static int neighboors;
    static int controlOfValidParameters = 0;
    static int controlOfState = 0;

    static String widthString = "No Presente";
    static String heightString = "No Presente";
    static String generationsString = "No Presente";
    static String speedString = "No Presente";
    static String neighborhoodString = "No Presente";

    public static void main(String[] args) {
        setGol(args);
        printSettings();
        playGol(generations);
    }

    public static void setGol(String[] settings) {
        for (int i = 0; i < settings.length; i++) {
            switch (settings[i].substring(0, 2)) {
                case "w=":
                    width = parseInt(settings[i]);
                    if (width != 10 && width != 20 && width != 40 && width != 80 && width != 0) {
                        controlOfState = -2;
                        width = 0;
                    }
                    widthString = setState(widthString, width);
                    break;
                case "h=":
                    height = parseInt(settings[i]);
                    if (height != 10 && height != 20 && height != 40 && height != 0) {
                        controlOfState = -2;
                        height = 0;
                    }
                    heightString = setState(heightString, height);
                    break;
                case "g=":
                    generations = parseInt(settings[i]);
                    if (generations < 0) {
                        controlOfState = -2;
                    }
                    generationsString = setState(generationsString, generations);
                    break;
                case "s=":
                    speed = parseInt(settings[i]);
                    if (controlOfState != -1 && speed < 250 || speed > 1000) {
                        controlOfState = -2;
                    }
                    speedString = setState(speedString, speed);
                    break;
                case "p=":
                    String seed = settings[i].substring(2);
                    if (seed.equals("")) {
                        population = "No Presente";
                    } else if (seed.equals("rnd")) {
                        population = "rnd";
                        controlOfValidParameters = controlOfValidParameters + 2;
                    } else {
                        population = validatePopulation(seed);
                    }
                    if (population.equals("-2")) {
                        population = "Invalido";
                    } else if (population.equals("-1")) {
                        population = "No Presente";
                    }
                    break;
                case "n=":
                    neighborhood = parseInt(settings[i]);
                    if (neighborhood < 0 || neighborhood > 5) {
                        controlOfState = -2;
                    }
                    neighborhoodString = setState(neighborhoodString, neighborhood);
                    if (neighborhood == 0 && controlOfState != -1) {
                        neighborhood = 3;
                        neighborhoodString = "" + neighborhood;
                    }
                    break;
                default:
                    break;
            }
        }
        if (width > 0 && height > 0) {
            mainGrid = new int[height][width];
            nextGrid = new int[height][width];
        }
        if (neighborhoodString.equals("No Presente")) {
            neighborhoodString = "3";
            neighborhood = 3;
            controlOfValidParameters++;
        }
        if (population.equals("") || population.equals("No Presente")) {
            population = "No Presente";
        } else if (!population.equals("Invalido")) {
            if (!population.equals("rnd")) {
                population = validatePopulation(population);
            }
            if (population.equals("-2")) {
                population = "Invalido";
                controlOfValidParameters--;
            } else if (population.equals("-1")) {
                population = "No Presente";
                controlOfValidParameters--;
            } else {
                controlOfValidParameters--;
                setPopulation(population);
            }
        }
    }

    public static void setPopulation(String seed) {
        if (seed.equals("rnd")) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mainGrid[i][j] = (int) (Math.random() * 2);
                }
            }
        } else {
            int seedCounter = 0;
            boolean isSeedFinished = false;
            for (int i = 0; i < height && !isSeedFinished; i++) {
                for (int j = 0; j <= width && !isSeedFinished && width != 0; j++) {
                    if (seedCounter == seed.length()) {
                        isSeedFinished = true;
                    } else {
                        if (seed.charAt(seedCounter) == '#') {
                            i++;
                            j = -1;
                            seedCounter++;
                        } else {
                            mainGrid[i][j] = Integer.parseInt(seed.substring(seedCounter, seedCounter + 1));
                            seedCounter++;
                        }
                    }
                }
            }
        }
    }

    public static void addNeighboors(int row, int col) {
        neighboors = 0;
        int[][] neighborhoodTemplate;
        switch (neighborhood) {
            case 1:
                neighborhoodTemplate = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                break;
            case 2:
                neighborhoodTemplate = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}};
                break;
            case 3:
                neighborhoodTemplate = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1},
                        {1, -1}, {1, 1}};
                break;
            case 4:
                neighborhoodTemplate = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                break;
            case 5:
                neighborhoodTemplate = new int[][]{{-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                break;
            default:
                throw new IllegalArgumentException("Valor no válido");
        }

        for (int[] neighboor : neighborhoodTemplate) {
            int newRow = row + neighboor[0];
            int newCol = col + neighboor[1];
            if (isCellValid(newRow, newCol)) {
                neighboors += mainGrid[newRow][newCol];
            }
        }
    }

    public static void changeGeneration() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                addNeighboors(i, j);
                if (mainGrid[i][j] == 1) {
                    if (neighboors > 3 || neighboors < 2) {
                        nextGrid[i][j] = 0;
                    } else {
                        nextGrid[i][j] = 1;
                    }
                } else {
                    if (neighboors == 3) {
                        nextGrid[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mainGrid[i][j] = nextGrid[i][j];
                nextGrid[i][j] = 0;
            }
        }
    }

    public static void playGol(int times) {
        int currentGeneration = 0;
        if (controlOfValidParameters == 6) {
            if (times == 0) {
                while (times == 0) {
                    System.out.println("Stats: Grid Size: " + height + "x" + width +
                            "  Speed: " + speed + "ms  Generations: " + currentGeneration);
                    printGrid(mainGrid);
                    changeGeneration();
                    currentGeneration++;

                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            while (times != 0) {
                System.out.println("Stats: Grid Size: " + height + "x" + width +
                        "  Speed: " + speed + "ms  Generations: " + currentGeneration);
                printGrid(mainGrid);
                changeGeneration();
                currentGeneration++;

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (times > 0) {
                    times--;
                }
            }

            System.out.println("|---------------------|");
            System.out.println("|The game has finished|");
            System.out.println("|---------------------|");
        }
    }

    public static String setState(String parameterString, int parameter) {
        if (controlOfState == -2) {
            parameterString = "Invalido";
        } else if (controlOfState == -1) {
            parameterString = "No Presente";
        } else {
            parameterString = "" + parameter;
            controlOfValidParameters++;
        }
        return parameterString;
    }

    public static int parseInt(String setting) {
        controlOfState = 0;
        int parsedParameter = 0;
        String parameterString = setting.substring(2);
        if (!parameterString.isEmpty()) {
            try {
                parsedParameter = Integer.parseInt(parameterString);
            } catch (Exception e) {
                parsedParameter = -2;
                controlOfState = -2;
            }
        } else {
            controlOfState = -1;
        }
        return parsedParameter;
    }

    public static String validatePopulation(String seed) {
        boolean areWidthOrHeightValid = true;
        if (width == 0 || height == 0) {
            areWidthOrHeightValid = false;
        }
        int rowsCounter = 0;
        int cellsInColumns = 0;
        boolean isSeedValidated = false;
        for (int i = 0; i < seed.length() && !isSeedValidated; i++) {
            if (seed.charAt(i) == '#') {
                rowsCounter++;
                if (cellsInColumns <= width || !areWidthOrHeightValid) {
                    cellsInColumns = 0;
                } else {
                    seed = "-2";
                    isSeedValidated = true;
                }
            } else {
                if (seed.charAt(i) == '1' || seed.charAt(i) == '0') {
                    cellsInColumns++;
                } else {
                    seed = "-2";
                    isSeedValidated = true;
                }
            }
        }
        if (!seed.equals("-2")) {
            if (!areWidthOrHeightValid) {
                controlOfValidParameters++;
            } else {
                if (rowsCounter > height - 1) {
                    seed = "-2";
                } else {
                    controlOfValidParameters++;
                }
            }
        }

        return seed;
    }

    public static boolean isCellValid(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }


    public static void printSettings() {
        System.out.println("Width: " + "[" + widthString + "]");
        System.out.println("Height: " + "[" + heightString + "]");
        System.out.println("Generations: " + "[" + generationsString + "]");
        System.out.println("Speed: " + "[" + speedString + "]");
        System.out.println("Population: " + "[" + population + "]");
        System.out.println("Neighborhood: " + "[" + neighborhoodString + "]");
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mainGrid[i][j] == 1) {
                    System.out.print(" ██");
                } else {
                    System.out.print(" ░░");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
