package com.wordbase.presentation.gameplay

sealed class GameViewType {
    object BatView : GameViewType()
    object PitcherView : GameViewType()
    object SpellView: GameViewType()
}
