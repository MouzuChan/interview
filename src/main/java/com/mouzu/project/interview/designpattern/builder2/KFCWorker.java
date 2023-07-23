package com.mouzu.project.interview.designpattern.builder2;

public class KFCWorker extends KFCBuilder{
    private KFCProduct kfcProduct;

    public KFCWorker(){
        kfcProduct = new KFCProduct();
    }

    @Override
    KFCBuilder buildEggTart() {
        kfcProduct.setEggTart("蛋挞");
        return this;
    }

    @Override
    KFCBuilder buildSprint() {
        kfcProduct.setSprint("雪碧");
        return this;
    }

    @Override
    KFCBuilder buildFriedChicken() {
        kfcProduct.setFriedChicken("炸鸡");
        return this;
    }

    @Override
    KFCBuilder buildFamilyBucket() {
        kfcProduct.setFamilyBucket("全家桶");
        return this;
    }

    @Override
    KFCProduct getProduct() {
        return kfcProduct;
    }
}
