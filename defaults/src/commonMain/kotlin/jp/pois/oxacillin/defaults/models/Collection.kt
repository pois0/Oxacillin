package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

public class Collection private constructor(){
    @Serializable
    public data class Model(
        public val response: Response,
        public val objects: Objects
    ) {
        @Serializable
        public data class Response(
            public val position: Position? = null,
            public val timeline: kotlin.collections.List<Timeline>? = null,
            @SerialName("timeline_id") public val timelineId: String
        ) {
            @Serializable
            public data class Position(
                @SerialName("max_position") public val maxPosition: String,
                @SerialName("min_position") public val minPosition: String,
                @SerialName("was_truncated") public val wasTruncated: Boolean
            )

            @Serializable
            public data class Timeline(
                @SerialName("feature_context") public val featureContext: String,
                public val tweet: Tweet
            ) {
                @Serializable
                public data class Tweet(
                    public val id: String,
                    @SerialName("sort_index") public val sortIndex: String
                )
            }
        }
    }

    @Serializable
    public data class DestroyResult(
        public val destroyed: Boolean
    )

    @Serializable
    public data class List(
        public val response: Response,
        public val objects: Objects
    ) {
        @Serializable
        public data class Response(
            public val results: kotlin.collections.List<Model.Response>,
            public val cursors: Cursors
        ) {
            @Serializable
            public data class Cursors(
                @SerialName("next_cursor") public val nextCursor: String
            )
        }
    }

    @Serializable
    public data class Objects(
        public val timelines: Map<String, Timeline>,
        public val tweets: LinkedHashMap<String, Status>? = null,
        public val users: LinkedHashMap<String, User>? = null
    ) {
        @Serializable
        public data class Timeline(
            @SerialName("collection_type") public val collectionType: String? = null,
            @SerialName("collection_url") public val collectionUrl: String? = null,
            public val description: String? = null,
            public val name: String,
            @SerialName("timeline_order") public val timelineOrder: String? = null,
            public val url: String? = null,
            @SerialName("user_id") public val userId: String,
            public val visibility: String? = null
        )
    }

    public class Entry private constructor() {
        @Serializable
        public data class Result(
            public val response: Response,
            public val objects: JsonObject
        ) {
            @Serializable
            public data class Response(
                public val errors: kotlin.collections.List<Error>
            ) {
                @Serializable
                public data class Error(
                    public val change: Change,
                    public val reason: String
                ) {
                    @Serializable
                    public data class Change(
                        public val op: CollectionOp,
                        @SerialName("tweet_id") public val tweetId: String
                    ) {
                        @Serializable
                        public enum class CollectionOp{
                            @SerialName("add") ADD,
                            @SerialName("move") MOVE,
                            @SerialName("remove") REMOVE
                        }
                    }
                }
            }
        }
    }
}
