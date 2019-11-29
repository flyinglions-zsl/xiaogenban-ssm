package com.ssm.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

/**
 * @program: xiaogenban-ssm
 * @description:
 * @author: FlyingLion
 * @create: 2019-08-07 16:32
 **/
@Api(value = "测试")
@RestController
public  class HelloController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数组大小");
        int size = scanner.nextInt();
        int array[][] = new int[size][size];
        System.out.println("请输入数据") ;
        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++)
            array[i][j]= scanner.nextInt();

        System.out.println("请输入要查找的数");
        int target = scanner.nextInt();

       /* for (int i=0;i<size;i++)
            for (int j=0;j<size;j++)
                System.out.print(array[i][j]);*/
        System.out.println(find(target, array));
    }
    public static boolean find(int target, int [][] array) {
        boolean flag = false;
        int row = 0;
        int line = array[0].length-1; //第一行的最后一个数
        while (row<=array.length-1 && line>=0) {
            if (target == array[line][row]) //第一列的最后一个
                return true;
            if (target < array[line][row])
                line--; //减小一行
            else row++;
        }
        return flag;
    }
}