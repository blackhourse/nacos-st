package cn.boot.st.base;

import java.util.Objects;

/**
 * 实体转换器
 */
public interface EntityConvert<S, T> {

    default T convert(S source) {
        return null;
    }

    default T convertPlus(S source, Objects... objects) {
        return null;
    }

}
