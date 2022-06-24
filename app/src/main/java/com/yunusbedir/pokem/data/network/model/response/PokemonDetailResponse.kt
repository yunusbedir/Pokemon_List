package com.yunusbedir.pokem.data.network.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "abilities") var abilities: ArrayList<Abilities> = arrayListOf(),
    @Json(name = "base_experience") var baseExperience: Int? = null,
    @Json(name = "forms") var forms: ArrayList<Item> = arrayListOf(),
    @Json(name = "game_indices") var gameIndices: ArrayList<GameIndices> = arrayListOf(),
    @Json(name = "height") var height: Int? = null,
    @Json(name = "held_items") var heldItems: ArrayList<HeldItems> = arrayListOf(),
    @Json(name = "id") var id: Int? = null,
    @Json(name = "is_default") var isDefault: Boolean? = null,
    @Json(name = "location_area_encounters") var locationAreaEncounters: String? = null,
    @Json(name = "moves") var moves: ArrayList<Moves> = arrayListOf(),
    @Json(name = "name") var name: String? = null,
    @Json(name = "order") var order: Int? = null,
    @Json(name = "past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @Json(name = "species") var species: Item? = Item(),
    @Json(name = "sprites") var sprites: Sprites? = Sprites(),
    @Json(name = "stats") var stats: ArrayList<Stats> = arrayListOf(),
    @Json(name = "types") var types: ArrayList<Types> = arrayListOf(),
    @Json(name = "weight") var weight: Int? = null
) {

    data class Abilities(
        @Json(name = "ability") var ability: Ability? = Ability(),
        @Json(name = "is_hidden") var isHidden: Boolean? = null,
        @Json(name = "slot") var slot: Int? = null
    ) {
        data class Ability(
            @Json(name = "name") var name: String? = null,
            @Json(name = "url") var url: String? = null
        )
    }

    data class GameIndices(
        @Json(name = "game_index") var gameIndex: Int? = null,
        @Json(name = "version") var version: Item? = Item()
    )

    data class Item(
        @Json(name = "name") var name: String? = null,
        @Json(name = "url") var url: String? = null
    )

    data class VersionDetails(
        @Json(name = "rarity") var rarity: Int? = null,
        @Json(name = "version") var version: Item? = Item()
    )

    data class HeldItems(
        @Json(name = "item") var item: Item? = Item(),
        @Json(name = "version_details") var versionDetails: ArrayList<VersionDetails> = arrayListOf()
    )

    data class VersionGroupDetails(
        @Json(name = "level_learned_at") var levelLearnedAt: Int? = null,
        @Json(name = "move_learn_method") var moveLearnMethod: Item? = Item(),
        @Json(name = "version_group") var versionGroup: Item? = Item()
    )

    data class Moves(
        @Json(name = "move") var move: Item? = Item(),
        @Json(name = "version_group_details") var versionGroupDetails: ArrayList<VersionGroupDetails> = arrayListOf()
    )

    data class DreamWorld(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null
    )

    data class Home(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class OfficialArtwork(
        @Json(name = "front_default") var frontDefault: String? = null
    )

    data class Other(
        @Json(name = "dream_world") var dreamWorld: DreamWorld? = DreamWorld(),
        @Json(name = "home") var home: Home? = Home(),
        @Json(name = "official-artwork") var officialArtwork: OfficialArtwork? = OfficialArtwork()
    )

    data class RedBlue(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_gray") var backGray: String? = null,
        @Json(name = "back_transparent") var backTransparent: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_gray") var frontGray: String? = null,
        @Json(name = "front_transparent") var frontTransparent: String? = null
    )


    data class Yellow(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_gray") var backGray: String? = null,
        @Json(name = "back_transparent") var backTransparent: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_gray") var frontGray: String? = null,
        @Json(name = "front_transparent") var frontTransparent: String? = null
    )

    data class GenerationI(
        @Json(name = "red-blue") var redBlue: RedBlue? = RedBlue(),
        @Json(name = "yellow") var yellow: Yellow? = Yellow()
    )


    data class Crystal(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_transparent") var backShinyTransparent: String? = null,
        @Json(name = "back_transparent") var backTransparent: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_transparent") var frontShinyTransparent: String? = null,
        @Json(name = "front_transparent") var frontTransparent: String? = null
    )

    data class Gold(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_transparent") var frontTransparent: String? = null
    )


    data class Silver(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_transparent") var frontTransparent: String? = null
    )

    data class GenerationII(
        @Json(name = "crystal") var crystal: Crystal? = Crystal(),
        @Json(name = "gold") var gold: Gold? = Gold(),
        @Json(name = "silver") var silver: Silver? = Silver()
    )

    data class Emerald(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null
    )

    data class FireredLeafgreen(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null
    )

    data class RubySapphire(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null
    )

    data class GenerationIII(
        @Json(name = "emerald") var emerald: Emerald? = Emerald(),
        @Json(name = "firered-leafgreen") var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
        @Json(name = "ruby-sapphire") var rubySapphire: RubySapphire? = RubySapphire()
    )

    data class DiamondPearl(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class HeartgoldSoulsilver(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class Platinum(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class GenerationIV(
        @Json(name = "diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
        @Json(name = "heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
        @Json(name = "platinum") var platinum: Platinum? = Platinum()
    )

    data class Animated(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class BlackWhite(
        @Json(name = "animated") var animated: Animated? = Animated(),
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class GenerationV(
        @Json(name = "black-white") var blackWhite: BlackWhite? = BlackWhite()
    )

    data class OmegarubyAlphasapphire(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class XY(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class GenerationVI(
        @Json(name = "omegaruby-alphasapphire") var omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
        @Json(name = "x-y") var xY: XY? = XY()
    )

    data class UltraSunUltraMoon(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null
    )

    data class GenerationVII(
        @Json(name = "icons") var icons: Icons? = Icons(),
        @Json(name = "ultra-sun-ultra-moon") var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()
    )

    data class Icons(
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null
    )

    data class GenerationVIII(
        @Json(name = "icons") var icons: Icons? = Icons()
    )

    data class Versions(
        @Json(name = "generation-i") var generationI: GenerationI? = GenerationI(),
        @Json(name = "generation-ii") var generationII: GenerationII? = GenerationII(),
        @Json(name = "generation-iii") var generationIII: GenerationIII? = GenerationIII(),
        @Json(name = "generation-iv") var generationIV: GenerationIV? = GenerationIV(),
        @Json(name = "generation-v") var generationV: GenerationV? = GenerationV(),
        @Json(name = "generation-vi") var generationVI: GenerationVI? = GenerationVI(),
        @Json(name = "generation-vii") var generationVII: GenerationVII? = GenerationVII(),
        @Json(name = "generation-viii") var generationVIII: GenerationVIII? = GenerationVIII()
    )

    data class Sprites(
        @Json(name = "back_default") var backDefault: String? = null,
        @Json(name = "back_female") var backFemale: String? = null,
        @Json(name = "back_shiny") var backShiny: String? = null,
        @Json(name = "back_shiny_female") var backShinyFemale: String? = null,
        @Json(name = "front_default") var frontDefault: String? = null,
        @Json(name = "front_female") var frontFemale: String? = null,
        @Json(name = "front_shiny") var frontShiny: String? = null,
        @Json(name = "front_shiny_female") var frontShinyFemale: String? = null,
        @Json(name = "other") var other: Other? = Other(),
        @Json(name = "versions") var versions: Versions? = Versions()
    )

    data class Stats(
        @Json(name = "base_stat") var baseStat: Int? = null,
        @Json(name = "effort") var effort: Int? = null,
        @Json(name = "stat") var stat: Item? = Item()
    )

    data class Types(
        @Json(name = "slot") var slot: Int? = null,
        @Json(name = "type") var type: Item? = Item()
    )

}
/*
 */