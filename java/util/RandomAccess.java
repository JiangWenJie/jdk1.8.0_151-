/*
 * Copyright (c) 2000, 2006, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Marker interface used by <tt>List</tt> implementations to indicate that
 * they support fast (generally constant time) random access.  The primary
 * purpose of this interface is to allow generic algorithms to alter their
 * behavior to provide good performance when applied to either random or
 * sequential access lists.
 *
 * List 实现所使用的标记接口，
 * 用来表明其支持快速（通常是固定时间）随机访问。
 * 此接口的主要目的是允许一般的算法更改其行为，
 * 从而在将其应用到随机或连续访问列表时能提供良好的性能。
 *
 * <p>The best algorithms for manipulating random access lists (such as
 * <tt>ArrayList</tt>) can produce quadratic behavior when applied to
 * sequential access lists (such as <tt>LinkedList</tt>).  Generic list
 * algorithms are encouraged to check whether the given list is an
 * <tt>instanceof</tt> this interface before applying an algorithm that would
 * provide poor performance if it were applied to a sequential access list,
 * and to alter their behavior if necessary to guarantee acceptable
 * performance.
 *
 * 将操作随机访问列表的最佳算法（如 ArrayList）
 * 应用到连续访问列表（如 LinkedList）时，可产生二次项的行为。
 * 如果 将某个算法应用到连续访问列表，那么在应用可能提供较差性能的算法前，
 * 鼓励使用一般的列表算法检查给定列表是否为此接口的一个 instanceof，
 * 如果需要保证可接受的性能，还可以更改其行为。
 *
 * <p>It is recognized that the distinction between random and sequential
 * access is often fuzzy.  For example, some <tt>List</tt> implementations
 * provide asymptotically linear access times if they get huge, but constant
 * access times in practice.  Such a <tt>List</tt> implementation
 * should generally implement this interface.  As a rule of thumb, a
 * <tt>List</tt> implementation should implement this interface if,
 * for typical instances of the class, this loop:
 *
 * 现在已经认识到，随机和连续访问之间的区别通常是模糊的。
 * 例如，如果列表很大时，某些 List 实现提供渐进的线性访问时间，
 * 但实际上是固定的访问时间。这样的 List 实现通常应该实现此接口。
 * 实际经验证明，如果是下列情况，则 List 实现应该实现此接口，
 * 即对于典型的类实例而言，此循环：
 * <pre>
 *     for (int i=0, n=list.size(); i &lt; n; i++)
 *         list.get(i);
 * </pre>
 * runs faster than this loop:
 * 的运行速度要快于以下循环：
 * <pre>
 *     for (Iterator i=list.iterator(); i.hasNext(); )
 *         i.next();
 * </pre>
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.4
 */
public interface RandomAccess {
}
