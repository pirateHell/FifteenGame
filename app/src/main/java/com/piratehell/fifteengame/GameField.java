package com.piratehell.fifteengame;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Stack;

class GameField {
    private static int[][] fieldArray = new int[4][4];
    private static Stack<int[][]> fieldArrayHistory = new Stack<>();
    public static Button[][] fieldUI;
    public static TextView tvTurns;
    public View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int x = -1, y = -1;
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (fieldUI[i][j].getId() == v.getId()) {
                        x = i;
                        y = j;
                        flag = true;
                        break;
                    }
                    if (flag) break;
                }
            }
            if (flag) {
                if (x - 1 >= 0 && flag) {
                    if (fieldArray[x - 1][y] == 0) {
                        turns++;
                        tvTurns.setText("" + turns);
                        fieldArray[x - 1][y] = fieldArray[x][y];
                        fieldArray[x][y] = 0;
                        int[][] a = new int[4][4];
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                a[i][j] = fieldArray[i][j];
                            }
                        }
                        fieldArrayHistory.push(a);
                        flag = false;
                    }
                }
                if (x + 1 <= 3 && flag) {
                    if (fieldArray[x + 1][y] == 0) {
                        turns++;
                        tvTurns.setText("" + turns);
                        fieldArray[x + 1][y] = fieldArray[x][y];
                        fieldArray[x][y] = 0;
                        int[][] a = new int[4][4];
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                a[i][j] = fieldArray[i][j];
                            }
                        }
                        fieldArrayHistory.push(a);
                        flag = false;
                    }
                }
                if (y - 1 >= 0 && flag) {
                    if (fieldArray[x][y - 1] == 0) {
                        turns++;
                        tvTurns.setText("" + turns);
                        fieldArray[x][y - 1] = fieldArray[x][y];
                        fieldArray[x][y] = 0;
                        int[][] a = new int[4][4];
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                a[i][j] = fieldArray[i][j];
                            }
                        }
                        fieldArrayHistory.push(a);
                        flag = false;
                    }
                }
                if (y + 1 <= 3 && flag) {
                    if (fieldArray[x][y + 1] == 0) {
                        turns++;
                        tvTurns.setText("" + turns);
                        fieldArray[x][y + 1] = fieldArray[x][y];
                        fieldArray[x][y] = 0;
                        int[][] a = new int[4][4];
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                a[i][j] = fieldArray[i][j];
                            }
                        }
                        fieldArrayHistory.push(a);
                        flag = false;
                    }
                }

                String s = "";
                Log.d("TTT Size", "" + fieldArrayHistory.size());
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        s += (fieldArrayHistory.peek())[i][j] + " ";
                    }
                }
                Log.d("TTT Value", s);

                drawFieldArray();
                checkWin();
            }
            //Toast.makeText(getApplicationContext(), "x: " + x + ", y:" + y, Toast.LENGTH_LONG).show();
        }
    };
    private static int turns;
    private Context ctx;

    GameField(Button[][] b, TextView tvTurns, Context ctx) {
        fieldUI = b;
        this.tvTurns = tvTurns;
        this.ctx = ctx;
        while (true) {
            fillFieldArray();
            if (validateFieldArray()) break;
        }
        fieldArrayHistory.clear();
        int[][] a = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = fieldArray[i][j];
            }
        }
        fieldArrayHistory.push(a);
        turns = 0;
        tvTurns.setText("" + turns);

        drawFieldArray();
        setOnClickFieldUI();
        adaptiveFieldUIGrid();
    }

    public void adaptiveFieldUIGrid() {
        CountDownTimer cdt = new CountDownTimer(10, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        fieldUI[i][j].setHeight(fieldUI[i][j].getWidth());
                    }
                }
            }

            @Override
            public void onFinish() {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        fieldUI[i][j].setHeight(fieldUI[i][j].getWidth());
                    }
                }
            }
        };
        cdt.start();
    }

    public void setOnClickFieldUI() {
        for (int i = 0; i < 4; i++) {
            for  (int j = 0; j < 4; j++) {
                //fieldUI[i][j].setHeight(fieldUI[i][j].getWidth());
                fieldUI[i][j].setOnClickListener(ocl);
            }
        }
    }
    public static void fillFieldArray() {
        boolean[] isGenerated = new boolean[16];
        int tmp;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp = (int)(Math.random() * 16);
                if (!isGenerated[tmp]) {
                    isGenerated[tmp] = true;
                    fieldArray[i][j] = tmp;
                }
                else j--;
            }
        }
    }
    public void drawFieldArray() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //fieldUI[i][j].setHeight(fieldUI[i][j].getWidth());
                if (fieldArray[i][j] != 0) {
                    fieldUI[i][j].setText("" + fieldArray[i][j]);
                    fieldUI[i][j].setEnabled(true);
                }
                else {
                    fieldUI[i][j].setText("");
                    fieldUI[i][j].setEnabled(false);
                }
            }
        }
    }
    public boolean validateFieldArray() {
        /*fieldArray[0][0] = 1;
        fieldArray[0][1] = 2;
        fieldArray[0][2] = 3;
        fieldArray[0][3] = 4;
        fieldArray[1][0] = 5;
        fieldArray[1][1] = 6;
        fieldArray[1][2] = 7;
        fieldArray[1][3] = 8;
        fieldArray[2][0] = 9;
        fieldArray[2][1] = 10;
        fieldArray[2][2] = 11;
        fieldArray[2][3] = 12;
        fieldArray[3][0] = 13;
        fieldArray[3][1] = 15;
        fieldArray[3][2] = 14;
        fieldArray[3][3] = 0;*/
        int summ = 0;
        int zeroLine = -1;
        int[] arr = new int[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i * 4 + j] = fieldArray[i][j];
                if (fieldArray[i][j] == 0) zeroLine = i + 1;
            }
        }
        summ += zeroLine * 15;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) continue;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i] && arr[j] != 0) summ += 1;
            }
        }
        if (summ % 2 == 1) {
            return false;
        } else {
            return true;
        }
    }
    public void checkWin() {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3) break;
                if (fieldArray[i][j] != i * 4 + j + 1) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }
        if (flag) {
            Toast.makeText(ctx, R.string.victoryMessage, Toast.LENGTH_LONG).show();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    fieldUI[i][j].setEnabled(false);
                }
            }
        }
    }
    public void restart() {
        while (true) {
            fillFieldArray();
            if (validateFieldArray()) break;
        }
        fieldArrayHistory.clear();
        int[][] a = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = fieldArray[i][j];
            }
        }
        fieldArrayHistory.push(a);
        turns = 0;
        tvTurns.setText("" + turns);
        drawFieldArray();
        setOnClickFieldUI();
    }
    public void cancel() {
        if (fieldArrayHistory.size() > 1) {
            fieldArrayHistory.pop();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    fieldArray[i][j] = (fieldArrayHistory.peek())[i][j];
                }
            }
            turns--;
            tvTurns.setText("" + turns);
            drawFieldArray();
        }
    }
}