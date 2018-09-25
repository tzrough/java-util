package com.hongfei.util.collection;

import java.util.Collection;

public final class CollectionUtil
{

	private CollectionUtil()
	{
	}

	public static boolean isEmpty(final Collection<?> coll)
	{
		return coll == null || coll.isEmpty();
	}
}
