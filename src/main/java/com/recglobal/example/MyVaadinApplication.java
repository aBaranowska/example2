package com.recglobal.example;

import java.util.Collection;

import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Window;

@SuppressWarnings({ "serial", "unused" })
public class MyVaadinApplication extends Application {

    @Override
    public void init() {
        setTheme("mytheme");

        Window window = new Window("My Vaadin Application");
        setMainWindow(window);

        HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
        hsplit.setSplitPosition(25, Sizeable.UNITS_PERCENTAGE);

        Panel panel = new Panel();
        panel.setSizeFull();

        long st = System.currentTimeMillis();
        // Tree tree = buildTree();
        Tree tree = buildTree2();
        // TreeTable tree = buildTreeTable();
        long en = System.currentTimeMillis();
        System.out.println("time: " + (en - st) / 1000);
        System.out.println("size: " + tree.getItemIds().size());

        hsplit.setFirstComponent(tree);

        panel.setContent(hsplit);

        window.setContent(panel);
    }

    private Tree buildTree() {
        CustomTree tree = new CustomTree();
        tree.setImmediate(true);
        tree.setNullSelectionAllowed(false);
        tree.setDragMode(TreeDragMode.NODE);
        tree.setDropHandler(new TreeDropHandler());

        tree.removeAllItems();

        String planet = "A";
        TestBean planetBean = new TestBean(planet);
        tree.setItemCaption(planetBean, planetBean.getName());
        tree.setItemIcon(planetBean, new ThemeResource("img/themeimage.png"));
        tree.addItem(planetBean);

        String moon = "B";
        TestBean moonBean = new TestBean(moon);
        tree.setItemCaption(moonBean, moonBean.getName());
        tree.setItemIcon(moonBean, new ThemeResource("img/themeimage.png"));
        tree.addItem(moonBean);
        tree.setParent(moonBean, planetBean);

        for (int k = 0; k < 5000; k++) {
            String child = "C" + k;
            TestBean childBean = new TestBean(child);
            tree.setItemCaption(childBean, childBean.getName());
            tree.setItemIcon(childBean, new ThemeResource("img/themeimage.png"));
            tree.addItem(childBean);
            tree.setParent(childBean, moonBean);
            tree.setChildrenAllowed(childBean, false);
        }

        tree.select(planetBean);

        tree.expandItemsRecursively(planetBean);

        return tree;
    }

    private Tree buildTree2() {
        HierarchicalContainer container = new HierarchicalContainer();

        String planet = "A";
        TestBean planetBean = new TestBean(planet);
        BeanItem<TestBean> planetBeanItem = new BeanItem<TestBean>(planetBean);
        container.addItem(planetBeanItem);

        String moon = "B";
        TestBean moonBean = new TestBean(moon);
        BeanItem<TestBean> moonBeanItem = new BeanItem<TestBean>(moonBean);
        container.addItem(moonBeanItem);
        container.setParent(moonBeanItem, planetBeanItem);

        for (int k = 0; k < 5000; k++) {
            String child = "C" + k;
            TestBean childBean = new TestBean(child);
            BeanItem<TestBean> childBeanItem = new BeanItem<TestBean>(childBean);
            container.addItem(childBeanItem);
            container.setParent(childBeanItem, moonBeanItem);
            container.setChildrenAllowed(childBeanItem, false);
        }

        Tree tree = new Tree();
        tree.setImmediate(true);
        tree.setNullSelectionAllowed(false);
        tree.setDragMode(TreeDragMode.NODE);
        tree.setDropHandler(new TreeDropHandler());

        tree.removeAllItems();

        tree.setContainerDataSource(container);

        Collection<?> ids = container.getItemIds();
        for (Object id : ids) {
            tree.setItemIcon(id, new ThemeResource("img/themeimage.png"));
        }

        tree.select(planetBeanItem);

        tree.expandItemsRecursively(planetBeanItem);

        return tree;
    }

    private TreeTable buildTreeTable() {
        TreeTable tree = new TreeTable();
        tree.setSizeFull();
        tree.setImmediate(true);
        tree.setNullSelectionAllowed(false);
        tree.setDragMode(TableDragMode.ROW);
        tree.setDropHandler(new TreeDropHandler());
        tree.setSelectable(true);
        tree.addListener(new TreeItemClickListener());

        tree.addContainerProperty("name", String.class, null);

        tree.removeAllItems();

        String planet = "A";
        TestBean planetBean = new TestBean(planet);
        tree.setItemIcon(planetBean, new ThemeResource("img/themeimage.png"));
        tree.addItem(new Object[] { planetBean.getName() }, planetBean);
        tree.setCollapsed(planetBean, false);

        String moon = "B";
        TestBean moonBean = new TestBean(moon);
        tree.setItemIcon(moonBean, new ThemeResource("img/themeimage.png"));
        tree.addItem(new Object[] { moonBean.getName() }, moonBean);
        tree.setParent(moonBean, planetBean);
        tree.setCollapsed(moonBean, false);

        for (int k = 0; k < 5000; k++) {
            String child = "C" + k;
            TestBean childBean = new TestBean(child);
            tree.setItemIcon(childBean, new ThemeResource("img/themeimage.png"));
            tree.addItem(new Object[] { childBean.getName() }, childBean);
            tree.setParent(childBean, moonBean);
            tree.setCollapsed(childBean, false);
            tree.setChildrenAllowed(childBean, false);
        }

        tree.select(planetBean);

        return tree;
    }

}
