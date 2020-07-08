package ru.job4j.collection.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> foundParent = findBy(parent);
        if (findBy(child).isEmpty() && foundParent.isPresent()) {
            rsl = foundParent.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return foundNode(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        return foundNode(el -> el.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> foundNode(Predicate<Node<E>> predicate) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }
}
