package com.team1091.planning

import com.team1091.math.Rectangle
import com.team1091.math.SuggestedRectangle
import com.team1091.math.Vec2
import com.team1091.math.measurement.feet
import com.team1091.math.measurement.inches
import com.team1091.math.measurement.max

class FieldMeasurement {

    // Measurement of things on the field, used for pathfinding constants
    companion object {

        val blockSize = 1.feet
        val robotWidth = 38.inches
        val robotHeight = 34.inches
        val robotSize = max(robotHeight, robotWidth)
        val lifterMaxHeight = 7.feet

        val fieldWidth = 27.feet

        val fieldLength = 30.feet
        val switchLowerX = 7.feet
        val switchLowerY = 11.feet
        val switchUpperX = fieldWidth - switchLowerX
        val switchUpperY = switchLowerY + 4.5.feet
        val switchHeight = 2.feet

        val scaleLowerX = 8.feet
        val scaleLowerY = 261.inches
        val scaleUpperX = fieldWidth - scaleLowerX
        val scaleUpperY = scaleLowerY + 6.feet
        val scaleHeight = 4.feet

        // if a block is 3.feet, then we can take the field width and length and divide by 3

        val pathfinderBlocksWidth = fieldWidth.toFeet().toInt()
        val pathfinderBlocksLength = fieldLength.toFeet().toInt()  // we only go a little beyond half way, other half is off limits

        val switch = Rectangle(
                Vec2[switchLowerX.toFeet().toInt(), switchLowerY.toFeet().toInt()],
                Vec2[switchUpperX.toFeet().toInt(), switchUpperY.toFeet().toInt()])

        val scale = Rectangle(
                Vec2[scaleLowerX.toFeet().toInt(), scaleLowerY.toFeet().toInt()],
                Vec2[scaleUpperX.toFeet().toInt(), scaleUpperY.toFeet().toInt()])

        val boxPile = Rectangle(
                Vec2[11,8],
                Vec2[17, switchLowerY.toFeet().toInt()]
        )

        val startZone = SuggestedRectangle(
                Vec2(0,0),
                Vec2(27, 10),
                3.0
        )

        val safeDistance = 3 // blocks away from obstacles that we should avoid

        val leftStart = Vec2[4.feet.toFeet().toInt(), 2]
        val centerStart = Vec2[12.feet.toFeet().toInt(), 2]
        val rightStart = Vec2[23.feet.toFeet().toInt(), 2]


        val leftSwitchEnd = Vec2[4, 13]
        val rightSwitchEnd = Vec2[23, 13]
        val leftScaleEnd = Vec2[4, scaleLowerY.toFeet().toInt()]
        val rightScaleEnd = Vec2[23, scaleLowerY.toFeet().toInt()]

    }

}

