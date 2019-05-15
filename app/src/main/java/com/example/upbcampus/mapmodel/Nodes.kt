package com.example.upbcampus.mapmodel

enum class Building { EC, ED }

abstract class Node(
    val id: Int, val name: String, val floor: Int, val building: Building
)

class Entrance(
    id: Int, name: String, floor: Int, building: Building,
    val from: Int?, val to: Int
) :
    Node(id, name, floor, building)

abstract class Vertical(
    id: Int, name: String, floor: Int, building: Building,
    val up: Int?, val down: Int?
) :
    Node(id, name, floor, building)

class Stairs(
    id: Int, name: String, floor: Int, building: Building,
    up: Int?, down: Int?
) :
    Vertical(id, name, floor, building, up, down)

class Elevator(
    id: Int, name: String, floor: Int, building: Building,
    up: Int, down: Int
) :
    Vertical(id, name, floor, building, up, down)

class Intersection(
    id: Int, name: String, floor: Int, building: Building,
    val north: Int?, val south: Int?, val east: Int?, val west: Int?
) :
    Node(id, name, floor, building)

abstract class Room(
    id: Int, name: String, floor: Int, building: Building,
    val neighbor: Int
) :
    Node(id, name, floor, building)

class LectureHall(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class Laboratory(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class Restroom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class Classroom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class Office(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class Store(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)

class UnknownRoom(
    id: Int, name: String, floor: Int, building: Building,
    neighbor: Int
) :
    Room(id, name, floor, building, neighbor)