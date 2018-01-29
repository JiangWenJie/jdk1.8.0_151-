/*
 * Copyright (c) 1995, 2004, Oracle and/or its affiliates. All rights reserved.
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

package java.lang;

/**
 * A class implements the <code>Cloneable</code> interface to
 * indicate to the {@link java.lang.Object#clone()} method that it
 * is legal for that method to make a
 * field-for-field copy of instances of that class.
 *
 * 表示实现了 Cloneable 接口的类的 Object.clone()
 * 方法可以合法地对该类实例进行按字段复制。
 *
 * <p>
 * Invoking Object's clone method on an instance that does not implement the
 * <code>Cloneable</code> interface results in the exception
 * <code>CloneNotSupportedException</code> being thrown.
 *
 * 如果在没有实现 Cloneable 接口的实例上调用 Object 的 clone 方法，
 * 则会导致抛出 CloneNotSupportedException 异常。
 *
 * <p>
 * By convention, classes that implement this interface should override
 * <tt>Object.clone</tt> (which is protected) with a public method.
 * See {@link java.lang.Object#clone()} for details on overriding this
 * method.
 *
 * 按照惯例，实现此接口的类应该使用公共方法重写 Object.clone
 * （它是受保护的）。请参阅 Object.clone()，
 * 以获得有关重写此方法的详细信息。
 *
 * <p>
 * Note that this interface does <i>not</i> contain the <tt>clone</tt> method.
 * Therefore, it is not possible to clone an object merely by virtue of the
 * fact that it implements this interface.  Even if the clone method is invoked
 * reflectively, there is no guarantee that it will succeed.
 *
 * 注意，此接口不 包含 clone 方法。
 * 因此，因为某个对象实现了此接口就克隆它是不可能的。
 * 即使 clone 方法是反射性调用的，也无法保证它将获得成功。
 *
 * @author  unascribed
 * @see     java.lang.CloneNotSupportedException
 * @see     java.lang.Object#clone()
 * @since   JDK1.0
 */
public interface Cloneable {
}
