package com.mouzu.project.interview.designpattern.factory.method;

import com.mouzu.project.interview.designpattern.factory.Car;

// 方法工厂模式 符合开闭原则
// 每新增新的对象，得新创工厂，增加代码复杂度
public interface CarFactory {
    Car getCar();
}
