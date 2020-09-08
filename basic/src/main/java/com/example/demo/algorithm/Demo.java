package com.example.demo.algorithm;

/**
 * @program: nacos-st
 * @author: maht
 * @create: 2020-09-07 10:47
 **/
public class Demo {

    public static void main(String[] args) {
        int[] list = {2, 4, 1, 5, 6};
//        bubbleSort(list);
        quickSort(list, 0, 4);
    }

    //1 冒泡排序
    public static void bubbleSort(int[] list) {
        int temp = 0;
        for (int i = 0; i < list.length; i++) {
            for (int j = list.length - 1; j > i; j--) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (list[j - 1] > list[j]) {
                    temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }
            }
            System.out.format("第 %d 趟：\t", i);
            print(list);

        }
        System.out.println(111111);
    }

    //1 冒泡排序-优化版-加入标志位
    public static void bubbleSort2(int[] list) {
        int temp = 0;
        boolean exChange = false;// 标志位， 如果数组没有发生数据交换，则数据是有序的，理解结束排序
        for (int i = 0; i < list.length; i++) {
            for (int j = list.length - 1; j > i; j--) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (list[j - 1] > list[j]) {
                    temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                    exChange = true;
                }
            }
            if (!exChange) {
                break;
            }
            System.out.format("第 %d 趟：\t", i);
            print(list);

        }
        System.out.println(111111);
    }


    public static int division(int[] list, int left, int right) {
        // 以最左边的数(left)为基准
        int base = list[left];
        while (left < right) {
            // 从序列右端开始，向左遍历，直到找到小于base的数
            while (left < right && list[right] >= base) {
                right--;
            }
            // 找到了比base小的元素，将这个元素放到最左边的位置
            list[left] = list[right];

            // 从序列左端开始，向右遍历，直到找到大于base的数
            while (left < right && list[left] <= base) {
                left++;
            }
            // 找到了比base大的元素，将这个元素放到最右边的位置
            list[right] = list[left];
        }

        // 最后将base放到left位置。此时，left位置的左侧数值应该都比left小；
        // 而left位置的右侧数值应该都比left大。
        list[left] = base;
        return left;
    }

    private static void quickSort(int[] list, int left, int right) {

        // 左下标一定小于右下标，否则就越界了
        if (left < right) {
            // 对数组进行分割，取出下次分割的基准标号
            int base = division(list, left, right);

            System.out.format("base = %d:\t", list[base]);
//            printPart(list, left, right);

            // 对“基准标号“左侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, left, base - 1);

            // 对“基准标号“右侧的一组数值进行递归的切割，以至于将这些数值完整的排序
            quickSort(list, base + 1, right);
        }
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i] + "___");
        }
    }

    private static void print(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + "___");
        }
    }


}
