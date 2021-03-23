package jp.pois.oxacillin.models

public interface CursorModel<T> {
    public val nextCursor: Long
    public val previousCursor: Long

    public val items: List<T>
}