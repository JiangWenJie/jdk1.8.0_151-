/*
 * Copyright (c) 1997, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.util;

import java.util.function.UnaryOperator;

/**
 * An ordered collection (also known as a <i>sequence</i>).  The user of this
 * interface has precise control over where in the list each element is
 * inserted.  The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 *
 * 有序的 collection（也称为序列）。
 * 此接口的用户可以对列表中每个元素的插入位置进行精确地控制。
 * 用户可以根据元素的整数索引（在列表中的位置）访问元素，并搜索列表中的元素。
 *
 * Unlike sets, lists typically allow duplicate elements.  More formally,
 * lists typically allow pairs of elements <tt>e1</tt> and <tt>e2</tt>
 * such that <tt>e1.equals(e2)</tt>, and they typically allow multiple
 * null elements if they allow null elements at all.  It is not inconceivable
 * that someone might wish to implement a list that prohibits duplicates, by
 * throwing runtime exceptions when the user attempts to insert them, but we
 * expect this usage to be rare.<p>
 *
 * 与 set 不同，列表通常允许重复的元素。
 * 更确切地讲，对 e1 和 e2来说，列表通常允许满足 e1.equals(e2) 的元素，
 * 并且如果列表本身允许 null 元素的话，通常它们允许多个 null 元素。
 * 难免有人希望通过在用户尝试插入重复元素时抛出运行时异常的方法来禁止重复的列表，
 * 但我们希望这种用法越少越好。
 *
 * The <tt>List</tt> interface places additional stipulations, beyond those
 * specified in the <tt>Collection</tt> interface, on the contracts of the
 * <tt>iterator</tt>, <tt>add</tt>, <tt>remove</tt>, <tt>equals</tt>, and
 * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
 * also included here for convenience.<p>
 *
 * List 接口在 iterator、add、remove、equals 和 hashCode 方法的协定上加了一些
 * 其他约定，超过了 Collection 接口中指定的约定。
 * 为方便起见，这里也包括了其他继承方法的声明。
 *
 * The <tt>List</tt> interface provides four methods for positional (indexed)
 * access to list elements.  Lists (like Java arrays) are zero based.  Note
 * that these operations may execute in time proportional to the index value
 * for some implementations (the <tt>LinkedList</tt> class, for
 * example). Thus, iterating over the elements in a list is typically
 * preferable to indexing through it if the caller does not know the
 * implementation.<p>
 *
 * List 接口提供了 4 种对列表元素进行定位（索引）访问方法。
 * 列表（像 Java 数组一样）是基于 0 的。注意，
 * 这些操作可能在和某些实现（例如 LinkedList 类）的索引值成比例的时间内执行。
 * 因此，如果调用者不知道实现，那么在列表元素上迭代通常优于用索引遍历列表。
 *
 * The <tt>List</tt> interface provides a special iterator, called a
 * <tt>ListIterator</tt>, that allows element insertion and replacement, and
 * bidirectional access in addition to the normal operations that the
 * <tt>Iterator</tt> interface provides.  A method is provided to obtain a
 * list iterator that starts at a specified position in the list.<p>
 *
 * List 接口提供了特殊的迭代器，称为 ListIterator，
 * 除了允许 Iterator 接口提供的操作外，该迭代器还允许元素插入、替换和双向访问。
 * 还提供了一个方法来获取从列表中指定位置开始的列表迭代器。
 *
 * The <tt>List</tt> interface provides two methods to search for a specified
 * object.  From a performance standpoint, these methods should be used with
 * caution.  In many implementations they will perform costly linear
 * searches.<p>
 *
 * List 接口提供了两种搜索指定对象的方法。
 * 从性能的观点来看，应该小心使用这些方法。
 * 在很多实现中，它们将执行高开销的线性搜索。
 *
 * The <tt>List</tt> interface provides two methods to efficiently insert and
 * remove multiple elements at an arbitrary point in the list.<p>
 *
 * List 接口提供了两种在列表的任意位置高效插入和移除多个元素的方法
 *
 * Note: While it is permissible for lists to contain themselves as elements,
 * extreme caution is advised: the <tt>equals</tt> and <tt>hashCode</tt>
 * methods are no longer well defined on such a list.
 *
 * 注意：尽管列表允许把自身作为元素包含在内，
 * 但建议要特别小心：在这样的列表上，equals 和 hashCode 方法不再是定义良好的。
 *
 * <p>Some list implementations have restrictions on the elements that
 * they may contain.  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the list may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * 某些列表实现对列表可能包含的元素有限制。
 * 例如，某些实现禁止 null 元素，而某些实现则对元素的类型有限制。
 * 试图添加不合格的元素会抛出未经检查的异常，通常是 NullPointerException 或 ClassCastException。
 * 试图查询不合格的元素是否存在可能会抛出异常，也可能简单地返回 false；
 * 某些实现会采用前一种行为，而某些则采用后者。
 * 概括地说，试图对不合格元素执行操作时，
 * 如果完成该操作后不会导致在列表中插入不合格的元素，
 * 则该操作可能抛出一个异常，也可能成功，这取决于实现的选择。
 * 此接口的规范中将这样的异常标记为“可选”。
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements in this list
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see Collection
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Arrays#asList(Object[])
 * @see Collections#nCopies(int, Object)
 * @see Collections#EMPTY_LIST
 * @see AbstractList
 * @see AbstractSequentialList
 * @since 1.2
 */

public interface List<E> extends Collection<E> {
    // Query Operations

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * 返回列表中的元素数。
     * 如果列表包含多于 Integer.MAX_VALUE 个元素，则返回 Integer.MAX_VALUE。
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * 如果列表不包含元素，则返回 true。
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * 如果列表包含指定的元素，则返回 true。
     * 更确切地讲，
     * 当且仅当列表包含满足 (o==null ? e==null : o.equals(e)) 的元素 e 时才返回 true。
     *
     * @param o element whose presence in this list is to be tested
     *          要测试列表中是否存在的元素
     * @return <tt>true</tt> if this list contains the specified element
     *                       如果列表包含指定的元素，则返回 true
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         如果指定元素的类型和此列表不兼容
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         如果指定的元素为 null，并且此列表不允许 null 元素
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * 返回按适当顺序在列表的元素上进行迭代的迭代器。
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * 返回按适当顺序包含列表中的所有元素的数组（从第一个元素到最后一个元素）。
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * 由于此列表不维护对返回数组的任何引用，因而它将是“安全的”。
     * （换句话说，即使数组支持此列表，此方法也必须分配一个新数组）。
     * 因此，调用者可以随意修改返回的数组。
     *
     * 通俗点讲，就是new了一个新的数组，把列表的元素复制过去。
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * 此方法充当基于数组的 API 与基于 collection 的 API 之间的桥梁。
     *
     * @return an array containing all of the elements in this list in proper
     *         sequence
     * @see Arrays#asList(Object[])
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * 返回按适当顺序（从第一个元素到最后一个元素）包含列表中所有元素的数组；
     * 返回数组的运行时类型是指定数组的运行时类型。
     * 如果指定数组能容纳列表，则在其中返回该列表。
     * 否则，分配具有指定数组的运行时类型和此列表大小的新数组。
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to <tt>null</tt>.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * 如果指定数组能容纳列表，并剩余空间（即数组的元素比列表的多），
     * 那么会将数组中紧随列表尾部的元素设置为 null。
     * （只有在调用者知道列表不包含任何 null 元素时此方法才能用于确定列表的长度）。
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * 像 toArray() 方法一样，
     * 此方法充当基于数组的 API 与基于 collection 的 API 之间的桥梁。
     * 更进一步说，此方法允许对输出数组的运行时类型进行精确控制，
     * 在某些情况下，可以用来节省分配开销。
     *
     * <p>Suppose <tt>x</tt> is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of <tt>String</tt>:
     *
     * 假定 x 是只包含字符串的一个已知列表。
     * 以下代码用来将该列表转储到一个新分配的 String 数组：
     * String[] y = x.toArray(new String[0]);
     * 注意， toArray(new Object[0]) 和 toArray() 在功能上是相同的。
     *
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified array is null
     */
    <T> T[] toArray(T[] a);


    // Modification Operations

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * 向列表的尾部添加指定的元素（可选操作）。
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * 支持该操作的列表可能对列表可以添加的元素有一些限制。
     * 特别是某些列表将拒绝添加 null 元素，
     * 其他列表将在可能添加的元素类型上施加限制。
     * List 类应该在它们的文档中明确指定有关添加元素的所有限制。
     *
     * @param e element to be appended to this list
     *          要添加到列表的元素
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     *          true（根据 Collection.add(E) 的规定）
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this list
     *         如果列表不支持 add 操作
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     *         如果指定元素的类不允许它添加到此列表
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *          如果指定的元素为 null，并且此列表不允许 null 元素
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     *         如果此元素的某些属性不允许它添加到此列表
     */
    boolean add(E e);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * 从此列表中移除第一次出现的指定元素（如果存在）（可选操作）。
     * 如果列表不包含元素，则不更改列表。
     * 更确切地讲，移除满足 (o==null ? get(i)==null : o.equals(get(i)))
     * 的最低索引 i 的元素（如果存在这样的元素）。
     * 如果此列表包含指定元素（或者此列表由于调用而发生更改），则返回 true。
     *
     * @param o element to be removed from this list, if present
     *          要从该列表中移除的元素，如果存在的话
     * @return <tt>true</tt> if this list contained the specified element
     *          如果列表包含指定的元素，则返回 true
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         如果指定元素的类型和此列表不兼容（可选）
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         如果指定的元素是 null，并且此列表不允许 null 元素（可选）
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this list
     *         如果列表不支持 remove 操作
     */
    boolean remove(Object o);


    // Bulk Modification Operations

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * 如果列表包含指定 collection 的所有元素，则返回 true。
     *
     * @param  c collection to be checked for containment in this list
     *           c - 要在列表中检查其包含性的 collection
     * @return <tt>true</tt> if this list contains all of the elements of the
     *         specified collection
     *         如果列表包含指定 collection 的所有元素，则返回 true
     * @throws ClassCastException if the types of one or more elements
     *         in the specified collection are incompatible with this
     *         list
     *         如果指定 collection 中的一个或多个元素的类型和此列表不兼容（可选）
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this list does not permit null
     *         elements
     *         如果指定的 collection 包含一个或多个 null 元素，
     *         并且此列表不允许 null 元素（可选）。
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #contains(Object)
     */
    boolean containsAll(Collection<?> c);

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * 添加指定 collection 中的所有元素到此列表的结尾，
     * 顺序是指定 collection 的迭代器返回这些元素的顺序（可选操作）。
     * 如果在操作正在进行中修改了指定的 collection，
     * 那么此操作的行为是不确定的（注意，如果指定的 collection 是此列表本身，
     * 并且它是非空的，则会发生这种情况。）
     *
     * @param c collection containing elements to be added to this list
     *          包含要添加到此列表的元素的 collection
     * @return <tt>true</tt> if this list changed as a result of the call
     *          如果此列表由于调用而发生更改，则返回 true
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *         is not supported by this list
     * @throws ClassCastException if the class of an element of the specified
     *         collection prevents it from being added to this list
     *         如果列表不支持 addAll 操作
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this list does not permit null
     *         elements, or if the specified collection is null
     *         如果指定的 collection 包含一个或多个 null 元素，
     *         并且该列表不允许 null 元素，或者指定的 collection 为 null
     * @throws IllegalArgumentException if some property of an element of the
     *         specified collection prevents it from being added to this list
     *          如果指定 collection 的元素的某些属性不允许它添加此列表
     * @see #add(Object)
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * 将指定 collection 中的所有元素都插入到列表中的指定位置（可选操作）。
     * 将当前处于该位置的元素（如果有的话）和所有后续元素向右移动（增加其索引）。
     * 新元素将按照它们通过指定 collection 的迭代器所返回的顺序出现在此列表中。
     * 如果在操作正在进行中修改了指定的 collection，那么该操作的行为是不确定的
     * （注意，如果指定的 collection 是此列表，并且它是非空的，则会发生这种情况。）
     *
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     *              将指定 collection 的第一个元素所插入位置的索引
     * @param c collection containing elements to be added to this list
     *          c - 包含要添加到此列表的元素的 collection
     * @return <tt>true</tt> if this list changed as a result of the call
     *          如果此列表由于调用而发生更改，则返回 true
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *         is not supported by this list
     *         如果列表不支持 addAll 操作
     * @throws ClassCastException if the class of an element of the specified
     *         collection prevents it from being added to this list
     *         如果指定 collection 中某个元素的类不允许它添加到此列表
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this list does not permit null
     *         elements, or if the specified collection is null
     *         如果指定的 collection 包含一个或多个 null 元素，
     *         并且该列表不允许 null 元素，或者指定的 collection 为 null
     * @throws IllegalArgumentException if some property of an element of the
     *         specified collection prevents it from being added to this list
     *         如果指定 collection 的元素的某些属性不允许它添加到此列表
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     *         如果索引超出范围 ( index < 0 || index > size())
     */
    boolean addAll(int index, Collection<? extends E> c);

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * 从列表中移除指定 collection 中包含的其所有元素（可选操作）。
     *
     * @param c collection containing elements to be removed from this list
     *          c - 包含从此列表中移除的元素的 collection
     * @return <tt>true</tt> if this list changed as a result of the call
     *          如果此列表由于调用而发生更改，则返回 true
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *         is not supported by this list
     *         如果列表不支持 removeAll 操作
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     *         如果此列表中的元素的类和指定的 collection 不兼容（可选）
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *          如果此列表包含一个 null 元素，
     *          并且指定的 collection 不允许 null 元素（可选），
     *          或者指定的 collection 为 null
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(Collection<?> c);

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * 仅在列表中保留指定 collection 中所包含的元素（可选操作）。
     * 换句话说，该方法从列表中移除未包含在指定 collection 中的所有元素。
     *
     * @param c collection containing elements to be retained in this list
     *          包含将保留在此列表中的元素的 collection
     * @return <tt>true</tt> if this list changed as a result of the call
     *          如果此列表由于调用而发生更改，则返回 true
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *         is not supported by this list
     *         如果列表不支持 retainAll 操作
     * @throws ClassCastException if the class of an element of this list
     *         is incompatible with the specified collection
     *         如果此列表的元素的类和指定的 collection 不兼容（可选）
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *         如果此列表包含一个 null 元素，并且指定的 collection 不允许 null 元素（可选），
     *         或者指定的 collection 为 null
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(Collection<?> c);

    /**
     * Replaces each element of this list with the result of applying the
     * operator to that element.  Errors or runtime exceptions thrown by
     * the operator are relayed to the caller.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code list}:
     * <pre>{@code
     *     final ListIterator<E> li = list.listIterator();
     *     while (li.hasNext()) {
     *         li.set(operator.apply(li.next()));
     *     }
     * }</pre>
     *
     * If the list's list-iterator does not support the {@code set} operation
     * then an {@code UnsupportedOperationException} will be thrown when
     * replacing the first element.
     *
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this list is unmodifiable.
     *         Implementations may throw this exception if an element
     *         cannot be replaced or if, in general, modification is not
     *         supported
     * @throws NullPointerException if the specified operator is null or
     *         if the operator result is a null value and this list does
     *         not permit null elements
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @implSpec
     * The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     *
     * @implNote
     * This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does
     *         not support the {@code set} operation
     * @throws IllegalArgumentException
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     *         if the comparator is found to violate the {@link Comparator}
     *         contract
     * @since 1.8
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * 从列表中移除所有元素（可选操作）。此调用返回后该列表将是空的。
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this list
     *         如果列表不支持 clear 操作
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this list for equality.  Returns
     * <tt>true</tt> if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements <tt>e1</tt> and
     * <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>.)  In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.  This
     * definition ensures that the equals method works properly across
     * different implementations of the <tt>List</tt> interface.
     *
     * 比较指定的对象与列表是否相等。
     * 当且仅当指定的对象也是一个列表、两个列表有相同的大小，
     * 并且两个列表中的所有相应的元素对 相等 时才返回 true
     * （ 如果 (e1==null ? e2==null :e1.equals(e2))，
     * 则两个元素 e1 和 e2 是 相等 的）。
     * 换句话说，如果所定义的两个列表以相同的顺序包含相同的元素，
     * 那么它们是相等的。
     * 该定义确保了 equals 方法在 List 接口的不同实现间正常工作。
     *
     * @param o the object to be compared for equality with this list
     * @return <tt>true</tt> if the specified object is equal to this list
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:
     * <pre>{@code
     *     int hashCode = 1;
     *     for (E e : list)
     *         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
     * }</pre>
     * This ensures that <tt>list1.equals(list2)</tt> implies that
     * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
     * <tt>list1</tt> and <tt>list2</tt>, as required by the general
     * contract of {@link Object#hashCode}.
     *
     * 返回列表的哈希码值。列表的哈希码定义为以下计算的结果：
     * int hashCode = 1;
     * Iterator<E> i = list.iterator();
     * while (i.hasNext()) {
     *      E obj = i.next();
     *      hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     * }
     * 这确保了 list1.equals(list2) 意味着对于任何两个列表 list1 和 list2 而言，
     * 可实现 list1.hashCode()==list2.hashCode()，
     * 正如 Object.hashCode() 的常规协定所要求的。
     *
     * @return the hash code value for this list
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    int hashCode();


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     *返回列表中指定位置的元素。
     *
     * @param index index of the element to return
     *              要返回的元素的索引
     * @return the element at the specified position in this list
     *          列表中指定位置的元素
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     *         如果索引超出范围 ( index < 0 || index >= size())
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * 用指定元素替换列表中指定位置的元素（可选操作）。
     *
     * @param index index of the element to replace
     *              index - 要替换的元素的索引
     * @param element element to be stored at the specified position
     *                element - 要在指定位置存储的元素
     * @return the element previously at the specified position
     *          以前在指定位置的元素,也就是被替换掉的元素
     * @throws UnsupportedOperationException if the <tt>set</tt> operation
     *         is not supported by this list
     *          如果列表不支持 set 操作
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     *         如果指定元素的类不允许它添加到此列表
     * @throws NullPointerException if the specified element is null and
     *         this list does not permit null elements
     *          如果指定的元素为 null，并且此列表不允许 null 元素
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this list
     *          如果指定元素的某些属性不允许它添加到此列表
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     *          如果索引超出范围 ( index < 0 || index >= size())
     */
    E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * 在列表的指定位置插入指定元素（可选操作）。
     * 将当前处于该位置的元素（如果有的话）和所有后续元素向右移动（在其索引中加 1）。
     *
     * @param index index at which the specified element is to be inserted
     *              要在其中插入指定元素处的索引
     * @param element element to be inserted
     *                要插入的元素
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this list
     *         如果列表不支持 add 操作
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     *         如果指定元素的类不允许它添加到此列表
     * @throws NullPointerException if the specified element is null and
     *         this list does not permit null elements
     *         如果指定的元素为 null，并且此列表不允许 null 元素
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this list
     *         如果指定元素的某些属性不允许它添加到此列表
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     *          如果索引超出范围 ( index < 0 || index > size())
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * 移除列表中指定位置的元素（可选操作）。
     * 将所有的后续元素向左移动（将其索引减 1）。返回从列表中移除的元素。
     *
     * @param index the index of the element to be removed
     *              要移除的元素的索引
     * @return the element previously at the specified position
     *          以前在指定位置的元素
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this list
     *         如果列表不支持 remove 操作
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     *          如果索引超出范围 ( index < 0 || index >= size())
     */
    E remove(int index);


    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * 返回此列表中第一次出现的指定元素的索引；
     * 如果此列表不包含该元素，则返回 -1。
     * 更确切地讲，返回满足 (o==null ? get(i)==null : o.equals(get(i))) 的最低索引 i；
     * 如果没有这样的索引，则返回 -1。
     *
     * @param o element to search for
     *          要搜索的元素
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     *         此列表中第一次出现的指定元素的索引，如果列表不包含该元素，则返回 -1
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         如果指定元素的类型和此列表不兼容（可选）
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         如果指定的元素是 null，并且此列表不允许 null 元素（可选）
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * 返回此列表中最后出现的指定元素的索引；
     * 如果列表不包含此元素，则返回 -1。
     * 更确切地讲，
     * 返回满足(o==null ? get(i)==null : o.equals(get(i))) 的最高索引 i；
     * 如果没有这样的索引，则返回 -1。
     *
     * @param o element to search for
     *          要搜索的元素
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     *         列表中最后出现的指定元素的索引；如果列表不包含此元素，则返回 -1
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         如果指定元素的类型和此列表不兼容（可选）
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         如果指定的元素是 null，并且此列表不允许 null 元素（可选）
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    int lastIndexOf(Object o);


    // List Iterators

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * 返回此列表元素的列表迭代器（按适当顺序）。
     *
     * @return a list iterator over the elements in this list (in proper
     *         sequence)
     *         此列表元素的列表迭代器（按适当顺序）
     */
    ListIterator<E> listIterator();

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * 返回列表中元素的列表迭代器（按适当顺序），从列表的指定位置开始。
     * 指定的索引表示 next 的初始调用所返回的第一个元素。
     * previous 方法的初始调用将返回索引比指定索引少 1 的元素。
     *
     * @param index index of the first element to be returned from the
     *        list iterator (by a call to {@link ListIterator#next next})
     *        从列表迭代器返回的第一个元素的索引（通过调用 next 方法）
     * @return a list iterator over the elements in this list (in proper
     *         sequence), starting at the specified position in the list
     *         此列表中元素的列表迭代器（按适当顺序），从列表中的指定位置开始
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     *         如果索引超出范围 ( index < 0 || index > size())
     */
    ListIterator<E> listIterator(int index);

    // View

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     *
     * 返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。
     * （如果 fromIndex 和 toIndex 相等，则返回的列表为空）。
     * 返回的列表由此列表支持，因此返回列表中的非结构性更改将反映在此列表中，
     * 反之亦然。返回的列表支持此列表支持的所有可选列表操作
     *
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>, and all of the algorithms in the
     * <tt>Collections</tt> class can be applied to a subList.<p>
     *
     * 此方法省去了显式范围操作（此操作通常针对数组存在）。
     * 通过传递 subList 视图而非整个列表，期望列表的任何操作可用作范围操作。
     * 例如，下面的语句从列表中移除了元素的范围：
     * list.subList(from, to).clear();
     * 可以对 indexOf 和 lastIndexOf 构造类似的语句，
     * 而且 Collections 类中的所有算法都可以应用于 subList。
     *
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * 如果支持列表（即此列表）通过任何其他方式（而不是通过返回的列表）
     * 从结构上修改，则此方法返回的列表语义将变为未定义
     * （从结构上修改是指更改列表的大小，或者以其他方式打乱列表，
     * 使正在进行的迭代产生错误的结果）。
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     *                  fromIndex - subList 的低端（包括）
     * @param toIndex high endpoint (exclusive) of the subList
     *                toIndex - subList 的高端（不包括）
     * @return a view of the specified range within this list
     *          列表中指定范围的视图
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *         (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *         fromIndex &gt; toIndex</tt>)
     *         非法的端点值 ( fromIndex < 0 || toIndex > size || fromIndex > toIndex)
     */
    List<E> subList(int fromIndex, int toIndex);

    /**
     * Creates a {@link Spliterator} over the elements in this list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Implementations should document the
     * reporting of additional characteristic values.
     *
     * @implSpec
     * The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the list's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the list's iterator.
     *
     * @implNote
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, Spliterator.ORDERED);
    }
}
