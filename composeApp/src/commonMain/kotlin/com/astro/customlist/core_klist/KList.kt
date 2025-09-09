package com.astro.customlist.core_klist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay


class KList private constructor() {

    private var padding: Dp? = null
    private var isDividerEnabled: Boolean = false
    private var onClick: (() -> Unit)? = null
    private var emptyState: (@Composable () -> Unit)? = null
    private var isAnimationEnabled: Boolean = false

    // Section data
    private val sections = mutableListOf<Section>()

    data class Section(
        val title: String?,
        val items: List<Any>,
        val itemContent: @Composable (Any) -> Unit
    )


    companion object {
        val instance: KList
            get() = KList()
    }

    fun padding(padding: Dp): KList {
        this.padding = padding
        return this
    }

    fun divider(): KList {
        this.isDividerEnabled = true
        return this
    }

    // For Click on Any item
    fun clickable(onClick: () -> Unit): KList {
        this.onClick = onClick
        return this
    }

    // For Empty State to Handle on Empty Product
    fun emptyState(content: @Composable () -> Unit): KList {
        this.emptyState = content
        return this
    }

    //For Animate Items
    fun animateItems(): KList {
        this.isAnimationEnabled = true
        return this
    }

    // For multiple items like Lazy column items
    fun <T> sections(sections: List<Section>): KList {
        sections.forEach { section ->
            // Safe cast like before
            val safeContent: @Composable (Any) -> Unit = { item ->
                section.itemContent(item)
            }
            this.sections.add(
                Section(
                    section.title,
                    section.items as List<Any>,
                    safeContent
                )
            )
        }
        return this
    }

    // For Single Section
    fun <T> section(title: String? = null, list: List<T>, itemContent: @Composable (T) -> Unit): KList {
        val safeContent: @Composable (Any) -> Unit = { item ->
            itemContent(item as T)
        }
        sections.add(Section(title, list as List<Any>, safeContent))
        return this
    }

    @Composable
    fun render() {
        val modifier = Modifier
            .let { if (padding != null) it.padding(padding!!) else it }
            .let { if (onClick != null) it.clickable { onClick?.invoke() } else it }

        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(0.dp)
        ) {
            if (sections.isEmpty()) {
                item {
                    emptyState?.invoke() ?: Text("Nothing to show")
                }
            } else {
                sections.forEach { section ->
                    section.title?.let {
                        item {
                            Text(
                                text = it,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    if (section.items.isEmpty()) {
                        item {
                            emptyState?.invoke() ?: Text("Nothing to show")
                        }
                    }

                    itemsIndexed(section.items, key = { _, item -> item.hashCode() }) { index, item ->
                        var visible by remember { mutableStateOf(false) }

                        // Delay item for animation
                        LaunchedEffect(Unit) {
                            delay(index * 150L)
                            visible = true
                        }

                        AnimatedVisibility(
                            visible = visible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 }, //
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ) + fadeIn(
                                animationSpec = tween(
                                    durationMillis = 600,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                            exit = fadeOut(
                                animationSpec = tween(
                                    durationMillis = 400,
                                    easing = FastOutLinearInEasing
                                )
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                            ) {
                                section.itemContent(item)
                            }
                        }
                    }
//                    items(
//                        items = section.items,
//                        key = { item -> item.hashCode() }
//                    ) { item ->
//                        val itemModifier = if (isAnimationEnabled) {
//                            Modifier.animateItem(
//                                fadeInSpec = tween(300),
//                                fadeOutSpec = tween(300),
//                                placementSpec = tween(300)
//                            )
//                        } else Modifier
//
//                        Box(modifier = itemModifier) {
//                            section.itemContent(item)
//                        }
//                    }

                    item {
                        if (isDividerEnabled) {
                            HorizontalDivider(
                                Modifier,
                                DividerDefaults.Thickness,
                                DividerDefaults.color
                            )
                        }
                    }
                }


            }
        }
    }
}
