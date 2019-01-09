@file:Suppress("UNUSED")

package jp.nephy.penicillin.endpoints

import jp.nephy.penicillin.PenicillinClient
import jp.nephy.penicillin.core.emulation.EmulationMode
import jp.nephy.penicillin.models.Friendships
import jp.nephy.penicillin.models.Relationship
import jp.nephy.penicillin.models.User

class Friendships(override val client: PenicillinClient): Endpoint {
    fun show(sourceId: Long? = null, sourceScreenName: String? = null, targetId: Long? = null, targetScreenName: String? = null, vararg options: Pair<String, Any?>) =
        client.session.get("/1.1/friendships/show.json") {
            parameter("source_id" to sourceId, "source_screen_name" to sourceScreenName, "target_id" to targetId, "target_screen_name" to targetScreenName, *options)
        }.jsonObject<Friendships.Show>()

    fun lookup(vararg options: Pair<String, Any?>) = client.session.get("/1.1/friendships/lookup.json") {
        parameter(*options)
    }.jsonArray<Friendships.Lookup>()

    fun lookupByScreenNames(screenNames: List<String>, vararg options: Pair<String, Any?>) = client.session.get("/1.1/friendships/lookup.json") {
        parameter("screen_name" to screenNames.joinToString(","), *options)
    }.jsonArray<Friendships.Lookup>()

    fun lookupByUserIds(userIds: List<Long>, vararg options: Pair<String, Any?>) = client.session.get("/1.1/friendships/lookup.json") {
        parameter("user_id" to userIds.joinToString(","), *options)
    }.jsonArray<Friendships.Lookup>()

    fun noRetweetsIds(stringifyIds: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.get("/1.1/friendships/no_retweets/ids.json") {
        parameter("stringify_ids" to stringifyIds, *options)
    }.jsonObject<Friendships.NoRetweetsIds>()

    fun create(userId: Long, follow: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.post("/1.1/friendships/create.json") {
        body {
            form {
                add(
                    "ext" to "mediaColor",
                    "handles_challenges" to "1",
                    "include_entities" to "1",
                    "include_profile_interstitial_type" to "true",
                    "include_profile_location" to "true",
                    "include_user_entities" to "true",
                    "include_user_hashtag_entities" to "true",
                    "include_user_mention_entities" to "true",
                    "include_user_symbol_entities" to "true",
                    emulationMode = EmulationMode.TwitterForiPhone
                )
                add("user_id" to userId, "follow" to follow, *options)
            }
        }
    }.jsonObject<User>()

    fun create(screenName: String, follow: Boolean? = null, vararg options: Pair<String, Any?>) = client.session.post("/1.1/friendships/create.json") {
        body {
            form {
                add(
                        "ext" to "mediaColor",
                        "handles_challenges" to "1",
                        "include_entities" to "1",
                        "include_profile_interstitial_type" to "true",
                        "include_profile_location" to "true",
                        "include_user_entities" to "true",
                        "include_user_hashtag_entities" to "true",
                        "include_user_mention_entities" to "true",
                        "include_user_symbol_entities" to "true",
                        emulationMode = EmulationMode.TwitterForiPhone
                )
                add("screen_name" to screenName, "follow" to follow, *options)
            }
        }
    }.jsonObject<User>()

    fun destroy(userId: Long, vararg options: Pair<String, Any?>) = client.session.post("/1.1/friendships/destroy.json") {
        body {
            form {
                add("user_id" to userId, *options)
            }
        }
    }.jsonObject<User>()

    fun destroy(screenName: String, vararg options: Pair<String, Any?>) = client.session.post("/1.1/friendships/destroy.json") {
        body {
            form {
                add("screen_name" to screenName, *options)
            }
        }
    }.jsonObject<User>()

    fun update(userId: Long, device: Boolean? = null, retweets: Boolean? = null, vararg options: Pair<String, Any?>) =
        client.session.post("/1.1/friendships/update.json") {
            body {
                form {
                    add("user_id" to userId, "device" to device, "retweets" to retweets, *options)
                }
            }
        }.jsonObject<Relationship>()

    fun update(screenName: String, device: Boolean? = null, retweets: Boolean? = null, vararg options: Pair<String, Any?>) =
            client.session.post("/1.1/friendships/update.json") {
                body {
                    form {
                        add("screen_name" to screenName, "device" to device, "retweets" to retweets, *options)
                    }
                }
            }.jsonObject<Relationship>()

    @PrivateEndpoint
    fun readAll(vararg options: Pair<String, Any?>) = client.session.post("/1.1/friendships/read_all.json") {
        body {
            form {
                add(
                    "cards_platform" to "iPhone-13",
                    "contributor_details" to "1",
                    "ext" to "altText,info360,mediaColor,mediaRestrictions,mediaStats,stickerInfo",
                    "include_cards" to "1",
                    "include_carousels" to "1",
                    "include_entities" to "1",
                    "include_ext_media_color" to "true",
                    "include_media_features" to "true",
                    "include_my_retweet" to "1",
                    "include_profile_interstitial_type" to "true",
                    "include_profile_location" to "true",
                    "include_reply_count" to "1",
                    "include_user_entities" to "true",
                    "include_user_hashtag_entities" to "true",
                    "include_user_mention_entities" to "true",
                    "include_user_symbol_entities" to "true",
                    "tweet_mode" to "extended",
                    *options
                )
            }
        }
    }.jsonArray<User>()
}