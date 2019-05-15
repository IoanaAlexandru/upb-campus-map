package com.example.upbcampus.mapmodel

abstract class Direction(
    val src: Node,
    val dst: Node,
    val message: String
)

class LocationDirection(loc: Node, message: String) :
    Direction(loc, loc, message)

class StairsDirection(src: Stairs, dst: Stairs, message: String) :
    Direction(src, dst, message)

class ElevatorDirection(src: Elevator, dst: Elevator, message: String) :
    Direction(src, dst, message)

class IntersectionDirection(src: Node, dst: Node, message: String) :
    Direction(src, dst, message)

class WalkDirection(src: Node, dst: Node, message: String) :
    Direction(src, dst, message)