package jp.pois.oxacillin.extensions

/**
 * A entity model that has "indices" property.
 */
public interface IndexedEntityModel {
    /**
     * The entity indices. Its size must be 2.
     */
    public val indices: List<Int>
}