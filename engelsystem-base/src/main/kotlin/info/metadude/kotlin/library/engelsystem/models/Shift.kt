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
data class Shift internal constructor(

    /**
     * Private comment only visible to the associated user. Plain text formatted.
     */
    @Json(name = "user_comment")
    val userComment: String = "",

    /**
     * Unix timestamp of when the shift ends.
     */
    @Deprecated(
        message = "Use endsAtDate instead." +
                " See https://github.com/engelsystem/engelsystem/issues/695",
        ReplaceWith("endsAtDate")
    )
    @Json(name = "end")
    internal val endsAtInstant: Instant = DEFAULT_INSTANT,

    /**
     * Date and time with time zone offset of when the shift ends, RFC3339 compliant (Y-m-d\TH:i:sP).
     */
    @Json(name = "end_date")
    val endsAtDate: ZonedDateTime,

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
    @Deprecated(
        message = "Use startsAtDate instead." +
                " See https://github.com/engelsystem/engelsystem/issues/695",
        ReplaceWith("startsAtDate")
    )
    @Json(name = "start")
    internal val startsAtInstant: Instant = DEFAULT_INSTANT,

    /**
     * Date and time with time zone offset of when the shift starts, RFC3339 compliant (Y-m-d\TH:i:sP).
     */
    @Json(name = "start_date")
    val startsAtDate: ZonedDateTime,

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
    @Deprecated(
        message = "Retrieve the time zone offset from either startsAtDate or endsAtDate. " +
                "See https://github.com/engelsystem/engelsystem/issues/695"
    )
    @Json(name = "timezone")
    val timeZoneOffset: ZoneOffset = DEFAULT_ZONE_OFFSET,

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

    constructor(
        userComment: String = "",
        endsAt: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,
        endsAtDate: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,
        locationDescription: String = "",
        locationName: String = "",
        locationUrl: String = "",
        name: String = "",
        sID: Int = 0,
        startsAt: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,
        startsAtDate: ZonedDateTime = DEFAULT_ZONED_DATE_TIME,
        talkTitle: String = "",
        talkUrl: String = "",
        timeZoneName: String = "",
        timeZoneOffset: ZoneOffset = DEFAULT_ZONE_OFFSET,
        typeId: Int = 0
    ) : this(
        userComment = userComment,
        endsAtDate = endsAtDate,
        endsAtInstant = endsAt.toInstant(),
        locationDescriptionString = locationDescription,
        locationName = locationName,
        locationUrlString = locationUrl,
        name = name,
        sID = sID,
        startsAtDate = startsAtDate,
        startsAtInstant = startsAt.toInstant(),
        talkTitle = talkTitle,
        talkUrlString = talkUrl,
        timeZoneName = timeZoneName,
        timeZoneOffset = timeZoneOffset,
        typeId = typeId
    )

    companion object {
        internal val DEFAULT_ZONE_OFFSET = ZoneOffset.UTC
        internal val DEFAULT_INSTANT = Instant.EPOCH
        internal val DEFAULT_ZONED_DATE_TIME = ZonedDateTime.ofInstant(DEFAULT_INSTANT, DEFAULT_ZONE_OFFSET)
    }


    /**
     * Date and time with time zone offset of when the shift ends.
     */
    @Deprecated(
        message = "Use endsAtDate instead." +
                " See https://github.com/engelsystem/engelsystem/issues/695",
        ReplaceWith("endsAtDate")
    )
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
    @Deprecated(
        message = "Use startsAtDate instead." +
                " See https://github.com/engelsystem/engelsystem/issues/695",
        ReplaceWith("startsAtDate")
    )
    val startsAt: ZonedDateTime
        get() = ZonedDateTime.ofInstant(startsAtInstant, timeZoneOffset)

    /**
     * Link of the associated talk in case the shift happens at a talk.
     */
    val talkUrl: String
        get() = talkUrlString ?: ""

}
