/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The root interface in the <i>collection hierarchy</i>.  A collection
 * represents a group of objects, known as its <i>elements</i>.  Some
 * collections allow duplicate elements and others do not.  Some are ordered
 * and others unordered.  The JDK does not provide any <i>direct</i>
 * implementations of this interface: it provides implementations of more
 * specific subinterfaces like <tt>Set</tt> and <tt>List</tt>.  This interface
 * is typically used to pass collections around and manipulate them where
 * maximum generality is desired.
 *
 * Collection 层次结构中的根接口。Collection表示一组对象，这些对象也称为
 * collection 的元素。一些 collection 允许有重复的元素，而另一些则不允许。
 * 一些collection是有序的，而另一些则是无序的。JDK不提供此接口的任何直接实现：
 * 它提供更具体的子接口（如 Set 和 List）实现。此接口通常用来传递 collection，
 * 并在需要最大普遍性的地方操作这些 collection。
 *
 * <p><i>Bags</i> or <i>multisets</i> (unordered collections that may contain
 * duplicate elements) should implement this interface directly.
 *
 * 包 (bag) 或多集合 (multiset)（可能包含重复元素的无序 collection）应该直接实现此接口。
 *
 * <p>All general-purpose <tt>Collection</tt> implementation classes (which
 * typically implement <tt>Collection</tt> indirectly through one of its
 * subinterfaces) should provide two "standard" constructors: a void (no
 * arguments) constructor, which creates an empty collection, and a
 * constructor with a single argument of type <tt>Collection</tt>, which
 * creates a new collection with the same elements as its argument.  In
 * effect, the latter constructor allows the user to copy any collection,
 * producing an equivalent collection of the desired implementation type.
 * There is no way to enforce this convention (as interfaces cannot contain
 * constructors) but all of the general-purpose <tt>Collection</tt>
 * implementations in the Java platform libraries comply.
 *
 * 所有通用的 Collection 实现类（通常通过它的一个子接口间接实现 Collection）
 * 应该提供两个“标准”构造方法：一个是 void（无参数）构造方法，
 * 用于创建空 collection；另一个是带有 Collection 类型单参数的构造方法，
 * 用于创建一个具有与其参数相同元素新的 collection。实际上，
 * 后者允许用户复制任何 collection，以生成所需实现类型的一个等效 collection。
 * 尽管无法强制执行此约定（因为接口不能包含构造方法），
 * 但是 Java 平台库中所有通用的 Collection 实现都遵从它。
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the collection on which they operate, are specified to
 * throw <tt>UnsupportedOperationException</tt> if this collection does not
 * support the operation.  If this is the case, these methods may, but are not
 * required to, throw an <tt>UnsupportedOperationException</tt> if the
 * invocation would have no effect on the collection.  For example, invoking
 * the {@link #addAll(Collection)} method on an unmodifiable collection may,
 * but is not required to, throw the exception if the collection to be added
 * is empty.
 *
 * 此接口中包含的“破坏性”方法，是指可修改其所操作的 collection 的那些方法，
 * 如果此 collection 不支持该操作，则指定这些方法抛出 UnsupportedOperationException。
 * 如果是这样，那么在调用对该 collection 无效时，
 * 这些方法可能（但并不一定)抛出 UnsupportedOperationException。
 * 例如，如果要添加的 collection 为空且不可修改，
 * 则对该 collection 调用 addAll(Collection) 方法时，可能但并不一定抛出异常。
 *
 * <p><a name="optional-restrictions">
 * Some collection implementations have restrictions on the elements that
 * they may contain.</a>  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the collection may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * 一些 collection 实现对它们可能包含的元素有所限制。
 * 例如，某些实现禁止 null 元素，而某些实现则对元素的类型有限制。
 * 试图添加不合格的元素将抛出一个未经检查的异常，
 * 通常是 NullPointerException 或 ClassCastException。
 * 试图查询是否存在不合格的元素可能抛出一个异常，或者只是简单地返回 false；
 * 某些实现将表现出前一种行为，而某些实现则表现后一种。
 * 较为常见的是，试图对某个不合格的元素执行操作且该操作的完成不会导致将
 * 不合格的元素插入 collection 中，将可能抛出一个异常，也可能操作成功，
 * 这取决于实现本身。这样的异常在此接口的规范中标记为“可选”。
 *
 * <p>It is up to each collection to determine its own synchronization
 * policy.  In the absence of a stronger guarantee by the
 * implementation, undefined behavior may result from the invocation
 * of any method on a collection that is being mutated by another
 * thread; this includes direct invocations, passing the collection to
 * a method that might perform invocations, and using an existing
 * iterator to examine the collection.
 *
 * 由每个 collection 来确定其自身的同步策略。
 * 在没有通过实现提供强烈保证的情况下，
 * 调用由另一进程正在更改的 collection 的方法可能会出现不确定行为；
 * 这包括直接调用，将 collection 传递给可能执行调用的方法，
 * 以及使用现有迭代器检查 collection。
 *
 * <p>Many methods in Collections Framework interfaces are defined in
 * terms of the {@link Object#equals(Object) equals} method.  For example,
 * the specification for the {@link #contains(Object) contains(Object o)}
 * method says: "returns <tt>true</tt> if and only if this collection
 * contains at least one element <tt>e</tt> such that
 * <tt>(o==null ? e==null : o.equals(e))</tt>."  This specification should
 * <i>not</i> be construed to imply that invoking <tt>Collection.contains</tt>
 * with a non-null argument <tt>o</tt> will cause <tt>o.equals(e)</tt> to be
 * invoked for any element <tt>e</tt>.  Implementations are free to implement
 * optimizations whereby the <tt>equals</tt> invocation is avoided, for
 * example, by first comparing the hash codes of the two elements.  (The
 * {@link Object#hashCode()} specification guarantees that two objects with
 * unequal hash codes cannot be equal.)  More generally, implementations of
 * the various Collections Framework interfaces are free to take advantage of
 * the specified behavior of underlying {@link Object} methods wherever the
 * implementor deems it appropriate.
 *
 * Collections Framework 接口中的很多方法是根据 equals 方法定义的。
 * 例如，contains(Object o) 方法的规范声明：
 * “当且仅当此 collection 包含至少一个满足(o==null?e==null:o.equals(e))的元素e时，
 * 返回 true。”不应将此规范理解为它暗指调用具有非空参数 o 的 Collection.contains
 * 方法会导致为任意的 e 元素被调用 o.equals(e) 方法。
 * 可随意对各种实现执行优化，只要避免调用 equals 即可，
 * 例如，通过首先比较两个元素的哈希码。（Object.hashCode() 规范保证哈希码不相等的
 * 两个对象不会相等）。较为常见的是，各种 Collections Framework 接口的实现可随意
 * 利用底层 Object 方法的指定行为，而不管实现程序认为它是否合适。
 *
 * <p>Some collection operations which perform recursive traversal of the
 * collection may fail with an exception for self-referential instances where
 * the collection directly or indirectly contains itself. This includes the
 * {@code clone()}, {@code equals()}, {@code hashCode()} and {@code toString()}
 * methods. Implementations may optionally handle the self-referential scenario,
 * however most current implementations do not do so.
 *
 * 一些对集合进行递归遍历的操作可能会失败，因为这些集合直接或间接地包含了自己的自引用实例。
 * 这包含了clone()，equals()，hashCode()和toString()方法。
 * 实现可以有选择地处理自引用场景，但是大多数当前的实现都不这样做。
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @implSpec
 * The default method implementations (inherited or otherwise) do not apply any
 * synchronization protocol.  If a {@code Collection} implementation has a
 * specific synchronization protocol, then it must override default
 * implementations to apply that protocol.
 *
 * 默认方法的实现（继承或者其他）不能应用于同步方案，如果一个collection的实现拥有一个
 * 特殊的同步方案，它必须重写默认的实现以便能够应用于同步协议。
 *
 * @param <E> the type of elements in this collection
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see     Set
 * @see     List
 * @see     Map
 * @see     SortedSet
 * @see     SortedMap
 * @see     HashSet
 * @see     TreeSet
 * @see     ArrayList
 * @see     LinkedList
 * @see     Vector
 * @see     Collections
 * @see     Arrays
 * @see     AbstractCollection
 * @since 1.2
 */

public interface Collection<E> extends Iterable<E> {
    // Query Operations

    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * 返回此 collection 中的元素数。
     * 如果此 collection 包含的元素大于 Integer.MAX_VALUE，
     * 则返回 Integer.MAX_VALUE。
     *
     * @return the number of elements in this collection
     */
    int size();

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * 如果此 collection 不包含元素，则返回 true。
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * 如果此 collection 包含指定的元素，则返回 true。
     * 更确切地讲，当且仅当此 collection 至少包含一个满足
     * (o==null ? e==null : o.equals(e)) 的元素 e 时，返回 true。
     *
     * @param o element whose presence in this collection is to be tested
     *          测试在此 collection 中是否存在的元素。
     * @return <tt>true</tt> if this collection contains the specified
     *         element
     *         如果此 collection 包含指定的元素，则返回 true
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this collection
     *         如果指定元素的类型与此 collection 不兼容（可选）。
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     *         如果指定的元素为 null，并且此 collection 不允许 null 元素（可选）。
     *         (<a href="#optional-restrictions">optional</a>)
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * 返回在此 collection 的元素上进行迭代的迭代器。
     * 关于元素返回的顺序没有任何保证（除非此 collection 是某个能提供保证顺序的类实例）。
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    Iterator<E> iterator();

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * 返回包含此 collection 中所有元素的数组。
     * 如果 collection 对其迭代器返回的元素顺序做出了某些保证，
     * 那么此方法必须以相同的顺序返回这些元素。
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * 返回的数组将是“安全的”，因为此 collection 并不维护对返回数组的任何引用。
     * （换句话说，即使 collection 受到数组的支持，此方法也必须分配一个新的数组）。
     * 因此，调用者可以随意修改返回的数组。
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * 此方法充当了基于数组的 API 与基于 collection 的 API 之间的桥梁。
     *
     * @return an array containing all of the elements in this collection
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     *
     * 返回包含此 collection 中所有元素的数组；
     * 返回数组的运行时类型与指定数组的运行时类型相同。
     * 如果指定的数组能容纳该 collection，则返回包含此 collection 元素的数组。
     * 否则，将分配一个具有指定数组的运行时类型和此 collection 大小的新数组。
     *
     * <p>If this collection fits in the specified array with room to spare
     * (i.e., the array has more elements than this collection), the element
     * in the array immediately following the end of the collection is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * collection <i>only</i> if the caller knows that this collection does
     * not contain any <tt>null</tt> elements.)
     *
     * 如果指定的数组能容纳 collection，并有剩余空间（即数组的元素比 collection 的元素多），
     * 那么会将数组中紧接 collection 尾部的元素设置为 null。
     * （只有 在调用者知道此 collection 没有包含任何 null 元素时才能用此方法确定
     * collection 的长度。）
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * 如果此 collection 对其迭代器返回的元素顺序做出了某些保证，
     * 那么此方法必须以相同的顺序返回这些元素。
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * 像 toArray() 方法一样，此方法充当基于数组的 API 与基于 collection 的 API 之间的桥梁。
     * 更进一步说，此方法允许对输出数组的运行时类型进行精确控制，
     * 并且在某些情况下，可以用来节省分配开销。
     *
     * <p>Suppose <tt>x</tt> is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly
     * allocated array of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * 假定 x 是只包含字符串的一个已知 collection。以下代码用来将 collection
     * 转储到一个新分配的 String 数组：
     *      String[] y = x.toArray(new String[0]);
     * 注意， toArray(new Object[0]) 和 toArray() 在功能上是相同的。
     *
     * @param <T> the runtime type of the array to contain the collection
     *           包含集合的数组的运行时类型
     * @param a the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     *          存储此 collection 元素的数组（如果其足够大）；
     *          否则，将为此分配一个具有相同运行时类型的新数组。
     * @return an array containing all of the elements in this collection
     *          包含此 collection 中所有元素的数组
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every element in
     *         this collection
     *         如果指定数组的运行时类型不是此 collection 每个元素运行时类型的超类型
     * @throws NullPointerException if the specified array is null
     *          如果指定的数组为 null
     */
    <T> T[] toArray(T[] a);

    // Modification Operations

    /**
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns <tt>true</tt> if this collection changed as a
     * result of the call.  (Returns <tt>false</tt> if this collection does
     * not permit duplicates and already contains the specified element.)<p>
     *
     * 确保此 collection 包含指定的元素（可选操作）。
     * 如果此 collection 由于调用而发生更改，则返回 true。
     * （如果此 collection 不允许有重复元素，并且已经包含了指定的元素，则返回 false。）
     *
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add <tt>null</tt> elements, and others will
     * impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     *
     * 支持此操作的 collection 可以限制哪些元素能添加到此 collection 中来。
     * 需要特别指出的是，一些 collection 拒绝添加 null 元素，
     * 其他一些 collection 将对可以添加的元素类型强加限制。
     * Collection 类应该在其文档中清楚地指定能添加哪些元素方面的所有限制。
     *
     * If a collection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning <tt>false</tt>).  This preserves
     * the invariant that a collection always contains the specified element
     * after this call returns.
     *
     * 如果 collection 由于某些原因（已经包含该元素的原因除外）拒绝添加特定的元素，
     * 那么它必须 抛出一个异常（而不是返回 false）。
     * 这确保了在此调用返回后，collection 总是包含指定的元素。
     *
     * @param e element whose presence in this collection is to be ensured
     * @return <tt>true</tt> if this collection changed as a result of the
     *         call
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this collection
     *         如果此 collection 不支持 add 操作
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this collection
     *          如果指定元素的类不允许它添加到此 collection 中
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     *         如果指定的元素为 null，并且此 collection 不允许 null 元素
     * @throws IllegalArgumentException if some property of the element
     *         prevents it from being added to this collection
     *         如果元素的某属性不允许它添加到此 collection 中
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to insertion restrictions
     *          如果由于插入限制，元素不能在此时间添加
     */
    boolean add(E e);

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this collection contains one or more such elements.  Returns
     * <tt>true</tt> if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * 从此 collection 中移除指定元素的单个实例，如果存在的话（可选操作）。
     * 更确切地讲，如果此 collection 包含一个或多个满足
     * (o==null ? e==null : o.equals(e)) 的元素 e，则移除这样的元素。
     * 如果此 collection 包含指定的元素（或者此 collection 由于调用而发生更改），
     * 则返回 true 。
     *
     * @param o element to be removed from this collection, if present
     *          要从此 collection 中移除的元素（如果存在）
     * @return <tt>true</tt> if an element was removed as a result of this call
     *          如果此调用将移除一个元素，则返回 true
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this collection
     *         如果指定元素的类型与此 collection 不兼容（可选）
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         collection does not permit null elements
     *         如果指定的元素为 null，并且此 collection 不允许 null 元素（可选）。
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this collection
     *         如果此 collection 不支持 remove 操作
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the specified collection.
     *
     * 如果此 collection 包含指定 collection 中的所有元素，则返回 true。
     *
     * @param  c collection to be checked for containment in this collection
     * @return <tt>true</tt> if this collection contains all of the elements
     *         in the specified collection
     * @throws ClassCastException if the types of one or more elements
     *         in the specified collection are incompatible with this
     *         collection
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this collection does not permit null
     *         elements
     *         (<a href="#optional-restrictions">optional</a>),
     *         or if the specified collection is null.
     * @see    #contains(Object)
     */
    boolean containsAll(Collection<?> c);

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * 将指定 collection 中的所有元素都添加到此 collection 中（可选操作）。
     * 如果在进行此操作的同时修改指定的 collection，那么此操作行为是不确定的。
     * （这意味着如果指定的 collection 是此 collection，并且此 collection 为非空，
     * 那么此调用的行为是不确定的。）
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *         is not supported by this collection
     * @throws ClassCastException if the class of an element of the specified
     *         collection prevents it from being added to this collection
     * @throws NullPointerException if the specified collection contains a
     *         null element and this collection does not permit null elements,
     *         or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     *         specified collection prevents it from being added to this
     *         collection
     * @throws IllegalStateException if not all the elements can be added at
     *         this time due to insertion restrictions
     * @see #add(Object)
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * 移除此 collection 中那些也包含在指定 collection 中的所有元素（可选操作）。
     * 此调用返回后，collection 中将不包含任何与指定 collection 相同的元素。
     *
     * @param c collection containing elements to be removed from this collection
     * @return <tt>true</tt> if this collection changed as a result of the
     *         call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
     *         is not supported by this collection
     * @throws ClassCastException if the types of one or more elements
     *         in this collection are incompatible with the specified
     *         collection
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if this collection contains one or more
     *         null elements and the specified collection does not support
     *         null elements
     *         (<a href="#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(Collection<?> c);

    /**
     * Removes all of the elements of this collection that satisfy the given
     * predicate.  Errors or runtime exceptions thrown during iteration or by
     * the predicate are relayed to the caller.
     *
     * @implSpec
     * The default implementation traverses all elements of the collection using
     * its {@link #iterator}.  Each matching element is removed using
     * {@link Iterator#remove()}.  If the collection's iterator does not
     * support removal then an {@code UnsupportedOperationException} will be
     * thrown on the first matching element.
     *
     * @param filter a predicate which returns {@code true} for elements to be
     *        removed
     * @return {@code true} if any elements were removed
     * @throws NullPointerException if the specified filter is null
     * @throws UnsupportedOperationException if elements cannot be removed
     *         from this collection.  Implementations may throw this exception if a
     *         matching element cannot be removed or if, in general, removal is not
     *         supported.
     * @since 1.8
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * 仅保留此 collection 中那些也包含在指定 collection 的元素（可选操作）。
     * 换句话说，移除此 collection 中未包含在指定 collection 中的所有元素。
     *
     * @param c collection containing elements to be retained in this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *         is not supported by this collection
     * @throws ClassCastException if the types of one or more elements
     *         in this collection are incompatible with the specified
     *         collection
     *         (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if this collection contains one or more
     *         null elements and the specified collection does not permit null
     *         elements
     *         (<a href="#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(Collection<?> c);

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * 移除此 collection 中的所有元素（可选操作）。
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this collection
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this collection for equality. <p>
     *
     * 比较此 collection 与指定对象是否相等。
     *
     * While the <tt>Collection</tt> interface adds no stipulations to the
     * general contract for the <tt>Object.equals</tt>, programmers who
     * implement the <tt>Collection</tt> interface "directly" (in other words,
     * create a class that is a <tt>Collection</tt> but is not a <tt>Set</tt>
     * or a <tt>List</tt>) must exercise care if they choose to override the
     * <tt>Object.equals</tt>.  It is not necessary to do so, and the simplest
     * course of action is to rely on <tt>Object</tt>'s implementation, but
     * the implementor may wish to implement a "value comparison" in place of
     * the default "reference comparison."  (The <tt>List</tt> and
     * <tt>Set</tt> interfaces mandate such value comparisons.)<p>
     *
     * 当 Collection 接口没有对 Object.equals 的常规协定添加任何约定时，
     * “直接”实现该 Collection 接口（换句话说，创建一个 Collection，
     * 但它不是 Set 或 List 的类）的程序员选择重写 Object.equals 方法时必须小心。
     * 没必要这样做，最简单的方案是依靠 Object 的实现，
     * 然而实现者可能希望实现“值比较”，而不是默认的“引用比较”。
     * （List 和 Set 接口要求进行这样的值比较。）
     *
     * The general contract for the <tt>Object.equals</tt> method states that
     * equals must be symmetric (in other words, <tt>a.equals(b)</tt> if and
     * only if <tt>b.equals(a)</tt>).  The contracts for <tt>List.equals</tt>
     * and <tt>Set.equals</tt> state that lists are only equal to other lists,
     * and sets to other sets.  Thus, a custom <tt>equals</tt> method for a
     * collection class that implements neither the <tt>List</tt> nor
     * <tt>Set</tt> interface must return <tt>false</tt> when this collection
     * is compared to any list or set.  (By the same logic, it is not possible
     * to write a class that correctly implements both the <tt>Set</tt> and
     * <tt>List</tt> interfaces.)
     *
     * Object.equals 方法的常规协定声称相等必须是对称的
     * （换句话说，当且仅当存在 b.equals(a) 时，才存在 a.equals(b)）。
     * List.equals 和 Set.equals 的协定声称列表只能与列表相等，set 只能与 set 相等。
     * 因此，对于一个既不实现 List 又不实现 Set 接口的 collection 类，
     * 当将此 collection 与任何列表或 set 进行比较时，
     * 常规的 equals 方法必须返回 false。
     * （按照相同的逻辑，不可能编写一个同时正确实现 Set 和 List 接口的类。）
     *
     * @param o object to be compared for equality with this collection
     * @return <tt>true</tt> if the specified object is equal to this
     * collection
     *
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.  While the
     * <tt>Collection</tt> interface adds no stipulations to the general
     * contract for the <tt>Object.hashCode</tt> method, programmers should
     * take note that any class that overrides the <tt>Object.equals</tt>
     * method must also override the <tt>Object.hashCode</tt> method in order
     * to satisfy the general contract for the <tt>Object.hashCode</tt> method.
     * In particular, <tt>c1.equals(c2)</tt> implies that
     * <tt>c1.hashCode()==c2.hashCode()</tt>.
     *
     * 返回此 collection 的哈希码值。
     * 当 Collection 接口没有为 Object.hashCode 方法的常规协定添加任何约束时，
     * 为了满足 Object.hashCode 方法的常规协定，
     * 程序员应该注意任何重写 Object.equals 方法的类必须重写 Object.hashCode 方法。
     * 需要特别指出的是， c1.equals(c2) 暗示着 c1.hashCode()==c2.hashCode()。
     *
     * @return the hash code value for this collection
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();

    /**
     * Creates a {@link Spliterator} over the elements in this collection.
     *
     * Implementations should document characteristic values reported by the
     * spliterator.  Such characteristic values are not required to be reported
     * if the spliterator reports {@link Spliterator#SIZED} and this collection
     * contains no elements.
     *
     * <p>The default implementation should be overridden by subclasses that
     * can return a more efficient spliterator.  In order to
     * preserve expected laziness behavior for the {@link #stream()} and
     * {@link #parallelStream()}} methods, spliterators should either have the
     * characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or be
     * <em><a href="Spliterator.html#binding">late-binding</a></em>.
     * If none of these is practical, the overriding class should describe the
     * spliterator's documented policy of binding and structural interference,
     * and should override the {@link #stream()} and {@link #parallelStream()}
     * methods to create streams using a {@code Supplier} of the spliterator,
     * as in:
     * <pre>{@code
     *     Stream<E> s = StreamSupport.stream(() -> spliterator(), spliteratorCharacteristics)
     * }</pre>
     * <p>These requirements ensure that streams produced by the
     * {@link #stream()} and {@link #parallelStream()} methods will reflect the
     * contents of the collection as of initiation of the terminal stream
     * operation.
     *
     * @implSpec
     * The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
     * from the collections's {@code Iterator}.  The spliterator inherits the
     * <em>fail-fast</em> properties of the collection's iterator.
     * <p>
     * The created {@code Spliterator} reports {@link Spliterator#SIZED}.
     *
     * @implNote
     * The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     *
     * <p>If a spliterator covers no elements then the reporting of additional
     * characteristic values, beyond that of {@code SIZED} and {@code SUBSIZED},
     * does not aid clients to control, specialize or simplify computation.
     * However, this does enable shared use of an immutable and empty
     * spliterator instance (see {@link Spliterators#emptySpliterator()}) for
     * empty collections, and enables clients to determine if such a spliterator
     * covers no elements.
     *
     * @return a {@code Spliterator} over the elements in this collection
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @implSpec
     * The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     *
     * @return a sequential {@code Stream} over the elements in this collection
     * @since 1.8
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @implSpec
     * The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     *
     * @return a possibly parallel {@code Stream} over the elements in this
     * collection
     * @since 1.8
     */
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
