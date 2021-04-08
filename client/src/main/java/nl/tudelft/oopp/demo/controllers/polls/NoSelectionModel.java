package nl.tudelft.oopp.demo.controllers.polls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

public class NoSelectionModel<T> extends MultipleSelectionModel<T> {

    //Removes the selectionmodel from a listview (Credits to: https://stackoverflow.com/a/46186195/14474762)

    /**
     * getSelectedIndices.
     * @return list
     */
    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.emptyObservableList();
    }

    /**
     * getSelectedItems.
     * @return list
     */
    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.emptyObservableList();
    }

    /**
     * selectIndices.
     * @param index The index
     * @param indices The indices
     */
    @Override
    public void selectIndices(int index, int... indices) {
    }

    /**
     * selectAll.
     */
    @Override
    public void selectAll() {
    }

    /**
     * selectFirst.
     */
    @Override
    public void selectFirst() {
    }

    /**
     * selectLast.
     */
    @Override
    public void selectLast() {
    }

    /**
     * clearAndSelect.
     * @param index index
     */
    @Override
    public void clearAndSelect(int index) {
    }

    /**
     * select.
     * @param index index
     */
    @Override
    public void select(int index) {
    }

    /**
     * select.
     * @param obj obj
     */
    @Override
    public void select(T obj) {
    }

    /**
     * clearSelection.
     * @param index index
     */
    @Override
    public void clearSelection(int index) {
    }

    /**
     * clearSelection.
     */
    @Override
    public void clearSelection() {
    }

    /**
     * isSelected.
     * @param index index
     * @return boolean
     */
    @Override
    public boolean isSelected(int index) {
        return false;
    }

    /**
     * isEmpty.
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * selectPrevious.
     */
    @Override
    public void selectPrevious() {
    }

    /**
     * selectNext.
     */
    @Override
    public void selectNext() {
    }
}
