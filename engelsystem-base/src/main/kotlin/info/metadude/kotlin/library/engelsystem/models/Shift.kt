package info.metadude.kotlin.library.engelsystem.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.recipes.DefaultOnDataMismatchAdapter
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

/**
 * Represents a work unit of an angel.
 */
@JsonClass(generateAdapter = true)
data class Shift constructor(

    /**
     * Private comment only visible to the associated user.
     */
    @Json(name = "Comment")
    val userComment: String = "",

    /**
     * Date and time with time zone offset of when the shift ends, RFC3339 compliant (Y-m-d\TH:i:sP).
     */
    @Json(name = "end_date")
    val endsAtDate: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,

    /**
     * Description of the shift location.
     * Empty string if property is missing in JSON response. See [DefaultOnDataMismatchAdapter].
     */
    @Json(name = "description")
    val locationDescription: String = "",

    /**
     * Name of the location where the shift takes place.
     */
    @Json(name = "Name")
    val locationName: String = "",

    /**
     * Link to the location of the shift.
     * Empty string if property is missing in JSON response. See [DefaultOnDataMismatchAdapter].
     */
    @Json(name = "map_url")
    val locationUrl: String = "",

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
     * Date and time with time zone offset of when the shift starts, RFC3339 compliant (Y-m-d\TH:i:sP).
     */
    @Json(name = "start_date")
    val startsAtDate: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,

    /**
     * Title of the associated talk in case the shift happens at a talk.
     */
    @Json(name = "title")
    val talkTitle: String = "",

    /**
     * Link of the associated talk in case the shift happens at a talk.
     * Empty string if property is missing in JSON response. See [DefaultOnDataMismatchAdapter].
     */
    @Json(name = "URL")
    val talkUrl: String = "",

    /**
     * Time zone name associated with the physical location of the event, e.g. "Europe/Berlin".
     */
    @Json(name = "event_timezone")
    val timeZoneName: String,

    /**
     * Shift types ids are not fixed. They can be assigned whenever an instance of the Engelsystem is launched.
     */
    @Json(name = "shifttype_id")
    val typeId: Int = 0

) {

    companion object {
        internal val DEFAULT_ZONE_OFFSET = ZoneOffset.UTC
        private val DEFAULT_INSTANT = Instant.EPOCH
        internal val DEFAULT_ZONED_DATE_TIME =
            ZonedDateTime.ofInstant(DEFAULT_INSTANT, DEFAULT_ZONE_OFFSET)
    }

}
