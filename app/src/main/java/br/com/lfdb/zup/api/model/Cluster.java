package br.com.lfdb.zup.api.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Cluster implements Serializable {

    private List<Integer> itemIds;
    private List<Double> position;
    private Long categoryId;
    private List<Long> categoriesIds;
    private int count;

    private boolean report;

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getCategoriesIds() {
        return categoriesIds == null ? Collections.singletonList(categoryId) : categoriesIds;
    }

    public void setCategoriesIds(List<Long> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getLatitude() {
        return position.get(0);
    }

    public double getLongitude() {
        return position.get(1);
    }

    public boolean isSingleCategory() {
        return categoryId != null;
    }

    public boolean isReport() {
        return report;
    }

    public Cluster setReport(boolean report) {
        this.report = report;
        return this;
    }
}
