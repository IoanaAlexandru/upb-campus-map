package com.example.upbcampus.mapmodel

enum class Building { EC, ED, UNKNOWN }

abstract class Node(
    val id: Int,
    val name: String,
    val floor: Int,
    val building: Building
)

class Entrance(id: Int, name: String, floor: Int, building: Building) :
    Node(id, name, floor, building)

class Stairs(id: Int, name: String, floor: Int, building: Building) :
    Node(id, name, floor, building)

class Intersection(id: Int, name: String, floor: Int, building: Building) :
    Node(id, name, floor, building)

class Elevator(id: Int, name: String, floor: Int, building: Building) :
    Node(id, name, floor, building)

abstract class Room(id: Int, name: String, floor: Int, building: Building) :
    Node(id, name, floor, building)

class LectureHall(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class Laboratory(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class Restroom(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class Classroom(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class Office(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class Store(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)

class UnknownRoom(id: Int, name: String, floor: Int, building: Building) :
    Room(id, name, floor, building)