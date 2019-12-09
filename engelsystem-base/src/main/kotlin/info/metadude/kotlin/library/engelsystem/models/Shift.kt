package info.metadude.kotlin.library.engelsystem.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant
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
    internal val endsAtInstant: Instant = DEFAULT_INSTANT,

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
    internal val startsAtInstant: Instant = DEFAULT_INSTANT,

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
     * Time zone offset associated with the time stamps in this class. Example: "+01:00"
     */
    @Json(name = "timezone")
    val timeZoneOffset: ZoneOffset = DEFAULT_ZONE_OFFSET,

    /**
     * Shift types ids are not fixed. They can be assigned whenever an instance of the Engelsystem is launched.
     */
    @Json(name = "shifttype_id")
    val typeId: Int = 0

) {

    companion object {
        internal val DEFAULT_ZONE_OFFSET = ZoneOffset.UTC
        internal val DEFAULT_INSTANT = Instant.EPOCH
    }


    /**
     * Date and time with time zone offset of when the shift ends.
     */
    val endsAt: ZonedDateTime
        get() = ZonedDateTime.ofInstant(endsAtInstant, timeZoneOffset)

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
     * Date and time with time zone offset of when the shift starts.
     */
    val startsAt: ZonedDateTime
        get() = ZonedDateTime.ofInstant(startsAtInstant, timeZoneOffset)

    /**
     * Link of the associated talk in case the shift happens at a talk.
     */
    val talkUrl: String
        get() = talkUrlString ?: ""

}
