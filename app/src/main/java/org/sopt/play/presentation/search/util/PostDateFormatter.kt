package org.sopt.play.presentation.search.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

object PostDateFormatter {

    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern(
        "yyyy.MM.dd",
        Locale.KOREA,
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private val zone = ZoneId.of("Asia/Seoul")

    @RequiresApi(Build.VERSION_CODES.O)
    fun format(value: String): String {
        if (value.isBlank()) return "-"

        return try {
            OffsetDateTime.parse(value)
                .atZoneSameInstant(zone)
                .format(formatter)
        } catch (e: DateTimeParseException) {
            try {
                LocalDateTime.parse(value)
                    .format(formatter)
            } catch (e: DateTimeParseException) {
                "-"
            }
        }
    }
}
