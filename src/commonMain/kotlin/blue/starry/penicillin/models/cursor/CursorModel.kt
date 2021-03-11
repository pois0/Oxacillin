package blue.starry.penicillin.models.cursor

public interface CursorModel<T> {
    public val nextCursor: Long
    public val previousCursor: Long

    public val items: List<T>
}