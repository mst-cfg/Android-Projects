package com.example.siawns.connectfive.model;

public class ConnectFiveModel {
    private static ConnectFiveModel instance = null;

    private ConnectFiveModel() {
    }

    public static ConnectFiveModel getInstance() {
        if (instance == null) {
            instance = new ConnectFiveModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;


    private short[][] model = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
    };
    private short nextPlayer = CIRCLE;

    public void resetModel() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                model[i][j] = EMPTY;
            }
        }
        nextPlayer = CIRCLE;
    }

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short setFieldContent(int x, int y, short content) {
        return model[x][y] = content;
    }

    public short getNextPlayer() {
        return nextPlayer;
    }

    public void changeNextPlayer() {
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;
    }


    public int getWinner() {
        int counter = 0;
        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 15; i++) {
                if (model[i][j] == CIRCLE) {
                    counter++;
                    if ((i + 4) <= 14) {
                        if ((j + 4) <= 14) {
                            if (model[i + 1][j + 1] == CIRCLE && model[i + 2][j + 2] == CIRCLE &&
                                    model[i + 3][j + 3] == CIRCLE && model[i + 4][j + 4] == CIRCLE)
                            {
                                return CIRCLE;
                            }

                            if (model[i][j + 1] == CIRCLE && model[i][j + 2] == CIRCLE
                                    && model[i][j + 3] == CIRCLE && model[i][j + 4] == CIRCLE) {
                                return CIRCLE;
                            }
                        }
                        if ((j - 4) >= 0) {
                            if (model[i + 1][j - 1] == CIRCLE && model[i + 2][j - 2] == CIRCLE &&
                                    model[i + 3][j - 3] == CIRCLE && model[i + 4][j - 4] == CIRCLE)
                            {
                                return CIRCLE;
                            }
                        }
                        if (model[i + 1][j] == CIRCLE && model[i + 2][j] == CIRCLE
                                && model[i + 3][j] == CIRCLE && model[i + 4][j] == CIRCLE) {
                            return CIRCLE;
                        }
                    }
                    else if ((j - 4) >= 0) {
                        if ((i - 4) >= 0) {
                            if (model[i -1][j - 1] == CIRCLE && model[i - 2][j - 2] == CIRCLE &&
                                    model[i - 3][j - 3] == CIRCLE && model[i - 4][j - 4] == CIRCLE)
                            {
                                return CIRCLE;
                            }
                        }
                        if ((i + 4) <= 14) {
                            if (model[i + 1][j - 1] == CIRCLE && model[i + 2][j - 2] == CIRCLE &&
                                    model[i + 3][j - 3] == CIRCLE && model[i + 4][j - 4] == CIRCLE)
                            {
                                return CIRCLE;
                            }
                        }
                        if (model[i][j - 1] == CIRCLE && model[i][j - 2] == CIRCLE
                                && model[i][j - 3] == CIRCLE && model[i][j - 4] == CIRCLE) {
                            return CIRCLE;
                        }
                    }
                }
                else if (model[i][j] == CROSS) {
                    counter++;
                    if ((i + 4) <= 14) {
                        if ((j + 4) <= 14) {
                            if (model[i + 1][j + 1] == CROSS && model[i + 2][j + 2] == CROSS &&
                                    model[i + 3][j + 3] == CROSS && model[i + 4][j + 4] == CROSS) {
                                return CROSS;
                            }

                            if (model[i][j + 1] == CROSS && model[i][j + 2] == CROSS
                                    && model[i][j + 3] == CROSS && model[i][j + 4] == CROSS) {
                                return CROSS;
                            }
                        }
                        if ((j - 4) >= 0) {
                            if (model[i + 1][j - 1] == CROSS && model[i + 2][j - 2] == CROSS &&
                                    model[i + 3][j - 3] == CROSS && model[i + 4][j - 4] == CROSS) {
                                return CROSS;
                            }
                        }
                        if (model[i + 1][j] == CROSS && model[i + 2][j] == CROSS
                                && model[i + 3][j] == CROSS && model[i + 4][j] == CROSS) {
                            return CROSS;
                        }
                    }
                    else if ((j - 4) >= 0) {
                        if ((i - 4) >= 0) {
                            if (model[i -1][j - 1] == CROSS && model[i - 2][j - 2] == CROSS
                                    && model[i - 3][j - 3] == CROSS && model[i - 4][j - 4] == CROSS)
                            {
                                return CROSS;
                            }
                        }
                        if ((i + 4) <= 14) {
                            if (model[i + 1][j - 1] == CROSS && model[i + 2][j - 2] == CROSS
                                    && model[i + 3][j - 3] == CROSS && model[i + 4][j - 4] == CROSS)
                            {
                                return CROSS;
                            }
                        }
                        if (model[i][j - 1] == CROSS && model[i][j - 2] == CROSS
                                && model[i][j - 3] == CROSS && model[i][j - 4] == CROSS) {
                            return CROSS;
                        }
                    }
                }
            }
        }
        if (counter == 225) {
            return -1;
        }
        else {
            return EMPTY;
        }
    }

}