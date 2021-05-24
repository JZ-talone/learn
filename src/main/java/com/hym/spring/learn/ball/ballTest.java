package com.hym.spring.learn.ball;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ${DESCRIPTION}
 *
 * @author huangyiming
 * @since 2021/2/13 16:39
 */
public class ballTest {

    private static List<Integer> redHisList = Lists.newArrayList();
    private static List<Integer> blueHisList = Lists.newArrayList();
    private static Integer multiBuy = 1;
    private static Boolean blueBuy = false;

    static {
        for (int i = 0; i < 34; i++) {
            redHisList.add(0);
        }
        for (int i = 0; i < 17; i++) {
            blueHisList.add(0);
        }

        // mock history
        for (int i = 0; i < 100; i++) {
            staticHisBall((ArrayList<Integer>) randomNewOne());
        }

        // todo his static
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 33, 16));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 33, 16));
        //staticHisBall(Lists.newArrayList(1, 2, 3, 4, 5, 33, 16));

        Integer tryTime = 10000;
        Integer totalMoney = 0;
        Integer two = 0;
        Integer one = 0;
        Integer redX = 1;
        for (int i = 0; i < multiBuy; i++) {
            redX *= 33 - 7 + multiBuy - i;
        }
        Integer zhuCur = (blueBuy ? 16 : 1) * redX;


        for (int i = 0; i < tryTime; i++) {
            List<List<Integer>> anaList = anaNext();

            List<Integer> thisTime = randomNewOne();

            for (List<Integer> anal : anaList) {
                Integer level = anaMoney(Lists.newArrayList(anal), Lists.newArrayList(thisTime));
                if (level == 1) {
                    one += 1;
                }
                if (level == 2) {
                    two += 1;
                }
                if (level == 3) {
                    totalMoney += 3000;
                }
                if (level == 4) {
                    totalMoney += 200;
                }
                if (level == 5) {
                    totalMoney += 10;
                }
                if (level == 6) {
                    totalMoney += 5;
                }
            }


            staticHisBall((ArrayList<Integer>) thisTime);
        }

        System.out.println(String.format("总参与%s次,每次%s注,每注2元，每次%s元,总投入%s元，一等奖%s次，二等奖%s次，奖金%s元",
                tryTime, zhuCur, zhuCur * 2, zhuCur * 2 * tryTime, one, two, totalMoney));
    }

    private static Integer anaMoney(List<Integer> anaList, List<Integer> thisTime) {
        boolean blueTrue = blueBuy || anaList.get(anaList.size() - 1).equals(thisTime.get(6));
        //boolean blueTrue = true;
        anaList = anaList.subList(0, anaList.size() - 1);
        thisTime = thisTime.subList(0, 6);
        anaList.retainAll(thisTime);
        Integer redSize = anaList.size();
        if (blueTrue) {
            if (redSize < 3) {
                return 6;
            }
            if (redSize < 4) {
                return 5;
            }
            if (redSize < 5) {
                return 4;
            }
            if (redSize < 6) {
                return 3;
            }
            if (redSize < 7) {
                return 1;
            }
        } else {
            if (redSize == 6) {
                return 2;
            }
            if (redSize == 5) {
                return 4;
            }
            if (redSize == 4) {
                return 5;
            }
        }
        return 0;
    }

    private static List<List<Integer>> anaNext() {
        List<List<Integer>> ans = Lists.newArrayList();
        List<Integer> redAfter = Lists.newArrayList(redHisList).stream().sorted(Integer::compareTo).collect(Collectors.toList());
        List<Integer> blueAfter = Lists.newArrayList(blueHisList).stream().sorted(Integer::compareTo).collect(Collectors.toList());
        redAfter = redAfter.subList(1, 7 - multiBuy);
        blueAfter = blueAfter.subList(1, 2);
        List<Integer> answer = Lists.newArrayList();
        for (int i = 1; i < redHisList.size(); i++) {
            if (redAfter.contains(redHisList.get(i))) {
                answer.add(i);
            }
        }
        answer = answer.subList(0, 6 - multiBuy);
        for (int i = 1; i < blueHisList.size(); i++) {
            if (blueAfter.contains(blueHisList.get(i))) {
                answer.add(i);
            }
        }
        answer = answer.subList(0, 7 - multiBuy);

        for (int i = 1; i < 34; i++) {
            List ma = Lists.newArrayList(answer);
            if (!answer.subList(0, 6).contains(i)) {
                ma.add(5, i);
                ans.add(ma);
            }

        }

        return ans;
    }

    public static void main(String[] args) {


    }

    private static List<Integer> randomNewOne() {
        List<Integer> newOne = Lists.newArrayList();
        while (newOne.size() < 6) {
            Integer key = RandomUtils.nextInt(1, 34);
            if (!newOne.contains(key)) {
                newOne.add(key);
            }
        }
        Integer key = RandomUtils.nextInt(1, 17);
        newOne.add(key);
        return newOne;
    }

    private static void staticHisBall(ArrayList<Integer> newArrayList) {
        for (int i = 0; i < newArrayList.size(); i++) {
            if (i < 6) {
                Integer idx = newArrayList.get(i);
                redHisList.set(idx, redHisList.get(idx) + 1);
            } else {
                Integer idx = newArrayList.get(i);
                blueHisList.set(idx, blueHisList.get(idx) + 1);
            }
        }

    }


}
