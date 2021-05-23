package com.adityaikhbalm.core.cache

import com.adityaikhbalm.core.model.response.*

object SampleData {
    private val genre = listOf(
        Genre(
            id = 1,
            name = "Action"
        )
    )

    private val cast = CreditResponse(
        listOf(
            Cast(
                id = 5,
                name = "Chris Evans",
                profilePath = "/3bOGNsHlrswhyW79uvIHH1V43JI.jpg",
                order = 0
            )
        )
    )

    private val trailer = TrailerResponse(
        listOf(
            Trailer(
                id = "5720ac91c3a36816300023a5",
                key = "W4DlMggBPvc",
                name = "Captain America: The First Avenger Trailer 2",
                site = "YouTube"
            )
        )
    )

    private val similar = SimilarResponse(
        listOf(
            Similar(
                id = 297762,
                title = "Wonder Woman",
                posterPath = "/imekS7f1OuHyUP2LAiTEM0zBzUz.jpg"
            )
        )
    )

    val favorite = Movie(
        id = 1771,
        backdropPath = "/yFuKvT4Vm3sKHdFY4eG6I4ldAnn.jpg",
        genres = genre,
        overview = "During World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.",
        posterPath = "/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg",
        releaseDate = "2011-07-22",
        runtime = 124,
        title = "Captain America: The First Avenger",
        credit = cast,
        trailer = trailer,
        similar = similar
    )

    val favorite2 = Movie(
        id = 1772,
        backdropPath = "/yFuKvT4Vm3sKHdFY4eG6I4ldAnn.jpg",
        genres = genre,
        overview = "During World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.",
        posterPath = "/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg",
        releaseDate = "2011-07-22",
        runtime = 124,
        title = "Captain America: The First Avenger",
        credit = cast,
        trailer = trailer,
        similar = similar
    )
}
