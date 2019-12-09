package info.metadude.kotlin.library.engelsystem.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

/**
 * Represents a work unit of an angel.
 */
@JsonClass(generateAdapter = true)
data class Shift(

    /**
     * Private comment only visible to the associated user.
     */
    @Json(name = "Comment")
    val userComment: String = "",

    /**
     * Unix timestamp of when the shift ends.
     */
    @Json(name = "end")
    val endsAt: ZonedDateTime = DEFAULT_DATE_TIME,

    /**
     * Description of the shift location.
     */
    @Json(name = "description")
    internal val locationDescriptionString: String? = "",

    /**
     * Name of the location where the shift takes place.
     */
    @Json(name = "Name")
    val locationName: String = "",

    /**
     * Link to the location of the shift.
     */
    @Json(name = "map_url")
    internal val locationUrlString: String? = "",

    /**
     * Name of the shift.
     */
    @Json(name = "name")
    val name: String = "",

    /**
     * ID of the shift.
     */
    @Json(name = "SID")
    val sID: Int = 0,

    /**
     * Unix timestamp of when the shift starts.
     */
    @Json(name = "start")
    val startsAt: ZonedDateTime = DEFAULT_DATE_TIME,

    /**
     * Title of the associated talk in case the shift happens at a talk.
     */
    @Json(name = "title")
    val talkTitle: String = "",

    /**
     * Link of the associated talk in case the shift happens at a talk.
     */
    @Json(name = "URL")
    internal val talkUrlString: String? = "",

    /**
     * Shift types ids are not fixed. They can be assigned whenever an instance of the Engelsystem is launched.
     */
    @Json(name = "shifttype_id")
    val typeId: Int = 0

) {

    companion object {
        internal val DEFAULT_DATE_TIME = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)
    }

    /**
     * Description of the shift location.
     */
    val locationDescription: String
        get() = locationDescriptionString ?: ""

    /**
     * Link to the location of the shift.
     */
    val locationUrl: String
        get() = locationUrlString ?: ""

    /**
     * Link of the associated talk in case the shift happens at a talk.
     */
    val talkUrl: String
        get() = talkUrlString ?: ""

}
